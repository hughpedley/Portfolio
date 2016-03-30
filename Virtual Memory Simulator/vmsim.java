import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Hashtable;

/**
 * Project 3 for CS1550
 * 
 * We will be simulating four algorithms for page replacement in an operating system,
 * and recording statistics (number of page faults, number of dirty frames).
 */

/**
 * Of note! The way my program parses args, to use opt or clock, user must enter
 * java vmsim -n <number of frames> -a <algorithm> <tracefile>
 * 
 * To use nru or aging, user must enter
 * java vmsim -n <number of frames> -a <algorithm> -r <refresh rate> <tracefile>
 *
 */
public class vmsim {	
	public static void main(String[] args) {
		int numberFrames = 0;
		String algorithm = "";
		int refreshRate = 0;
		String file = "";
		BufferedReader traceFile;
		int faults = 0;
		int diskWrites = 0;
		int memoryAccesses = 0;
		int clockHand = 0;
		int[][] agingCounter;
		int[] referenceBit;
		
		//parse arguments and assign them appropriately, ending the program
		//if an unexpected argument is entered
		if(!args[0].equals("-n") || args[0] == null) {
			System.out.println("First argument must be -n");
			System.exit(1);
		}
		try {
			numberFrames = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("Number of frames must be an int");
			System.exit(1);
		}
		if(!((numberFrames == 8) || (numberFrames == 16) || (numberFrames == 32) || (numberFrames == 64))) {
			System.out.println("Number of frames must be 8, 16, 32, or 64.");
			System.exit(1);
		}
		if(!args[2].equals("-a")) {
			System.out.println("Third argument must be 'a");
			System.exit(1);
		}
		algorithm = args[3];
		if(!(algorithm.equals("opt") || algorithm.equals("clock") || algorithm.equals("nru") || algorithm.equals("aging"))) {
			System.out.println("Fourth argument must be an algorithm, either 'opt', 'clock', 'nru', or 'aging'");
			System.exit(1);
		}
		if((algorithm.equals("nru") || algorithm.equals("aging")) && args[4].equals("-r")) {
			try {
				refreshRate = Integer.parseInt(args[5]);
				file = args[6];
			} catch ( Exception e) {
				System.out.println("If the nru algorithm is selected, the fifth argument must be an integer refresh rate.");
				System.exit(1);
			}
		} else {
			file = args[4];
		}
		try {
			traceFile = new BufferedReader(new FileReader(file));
			//Initialize page table
			Hashtable<Integer, Page> pageTable = new Hashtable<Integer, Page>();
			Hashtable<Integer, LinkedList<Integer>> future = new Hashtable<Integer, LinkedList<Integer>>();
			for(int i = 0; i < 1048576; i++) {
				Page page = new Page();
				pageTable.put(i, page);
				future.put(i,  new LinkedList<Integer>());
			}
			
			//Initialize frames
			int[] frames = new int[numberFrames];
			for(int i = 0; i < numberFrames; i++) {
				frames[i] = -1;
			}
		
			if(algorithm.equals("opt")) {
				//optimal algorithm
				int line = 0;
				//preprocess hashtables for optimal algorithm
				while(traceFile.ready()) {
					String[] lines = traceFile.readLine().split(" ");
					int pageNumber = Integer.decode("0x" + lines[0].substring(0, 5));
					future.get(pageNumber).add(line);
					line++;
				}
				
				//begin the page replacement portion/the meat of the algorithm
				int currentFrame = 0;
				//Start back at beginning of tracefile
				traceFile = new BufferedReader(new FileReader(file));
				while(traceFile.ready()) {
					//Begin listing attributes of page from tracefile
					String[] lines = traceFile.readLine().split(" ");
					int pageNumber = Integer.decode("0x" + lines[0].substring(0, 5));
					memoryAccesses++;
					future.get(pageNumber).removeFirst();
					Page page = pageTable.get(pageNumber);
					page.referenced = true;
					page.index = pageNumber;
					if(lines[1].equals("W")) {
						page.dirty = true;
					}
					if(page.valid) {
						//No page fault, so report page was hit
						System.out.println(lines[0] + " hit");
					} else {
						//Page fault, increment counter
						faults++;
						if(currentFrame < numberFrames) {
							//Page frames aren't full, so just fill first available frame
							frames[currentFrame] = pageNumber;
							page.frame = currentFrame;
							page.valid = true;
							currentFrame++;
							System.out.println(lines[0] + " page fault - no eviction");
						} else {
							//Evict page with longest distance from page being worked on
							int distance = locateLongestPage(frames, future);
							Page tempPage = pageTable.get(distance);
							if(tempPage.dirty) {
								//if page is dirty, increase counter for disk writes
								diskWrites++;
								System.out.println(lines[0] + " page fault - evict dirty");
							} else {
								System.out.println(lines[0] + " page fault - evict clean");
							}
							//Swap pages in page table
							frames[tempPage.frame] = page.index;
							page.frame = tempPage.frame;
							page.valid = true;
							tempPage.dirty = false;
							tempPage.referenced = false;
							tempPage.valid = false;
							tempPage.frame = -1;
							pageTable.put(distance, tempPage);
						}
					}
					pageTable.put(pageNumber, page);
				}
				System.out.println("Opt");
				System.out.println("Number of frames:       " + numberFrames);
				System.out.println("Total memory accesses:  " + memoryAccesses);
				System.out.println("Total page faults:      " + faults);
				System.out.println("Total writes to disk:   " + diskWrites);
			} else if(algorithm.equals("clock")) {
				//clock algorithm
				int currentFrame = 0;
				while(traceFile.ready()) {
					//swap in page
					String[] lines = traceFile.readLine().split(" ");
					int pageNumber = Integer.decode("0x" + lines[0].substring(0, 5));
					memoryAccesses++;
					Page page = pageTable.get(pageNumber);
					page.index = pageNumber;
					page.referenced = true;
					if(lines[1].equals("W")) {
						page.dirty = true;
					}
					if(page.valid) {
						//no page fault
						System.out.println(lines[0] + " hit");
					} else {
						faults++;
						if(currentFrame < numberFrames) {
							//frames aren't yet full, so swap in current page
							frames[currentFrame] = pageNumber;
							page.frame = currentFrame;
							page.valid = true;
							currentFrame++;
							System.out.println(lines[0] + " page fault - no eviction");
						} else {
							//frames are full, evict a page
							int pageEvict = 0;
							boolean found = false;
							while(found == false) {
								//go around the clock until you find an unreferenced page
								if(clockHand == frames.length || clockHand < 0) {
									clockHand = 0;
								}
								if(!pageTable.get(frames[clockHand]).referenced) {
									pageEvict = frames[clockHand];
									found = true;
								} else {
									//Once clock hand strikes the page, change referenced to false for next revolution
									pageTable.get(frames[clockHand]).referenced = false;
								}
								clockHand++;
							}
							Page tempPage = pageTable.get(pageEvict);
							if(tempPage.dirty) {
								//dirty evict, so write to disk
								diskWrites++;
								System.out.println(lines[0] + " page fault - evict dirty");
							} else {
								System.out.println(lines[0] + " page fault - evict clean");
							}
							frames[tempPage.frame] = page.index;
							page.frame = tempPage.frame;
							page.valid = true;
							tempPage.dirty = false;
							tempPage.referenced = false;
							tempPage.valid = false;
							tempPage.frame = -1;
							pageTable.put(pageEvict, tempPage);
						}
					}
					pageTable.put(pageNumber, page);
				}
				System.out.println("Clock");
				System.out.println("Number of frames:       " + numberFrames);
				System.out.println("Total memory accesses:  " + memoryAccesses);
				System.out.println("Total page faults:      " + faults);
				System.out.println("Total writes to disk:   " + diskWrites);
			} else if(algorithm.equals("nru")) {
				//not recently used algorithm
				int currentFrame = 0;
				while(traceFile.ready()) {
					//based on preselected refresh rate, clear r bit of all pages up to current frame
					memoryAccesses++;
					if(memoryAccesses % refreshRate == 0) {
						for(int i = 0; i < currentFrame; i++) {
							Page page = pageTable.get(frames[i]);
							page.referenced = false;
							pageTable.put(page.index, page);
						}
					}
					String[] lines = traceFile.readLine().split(" ");
					int pageNumber = Integer.decode("0x" + lines[0].substring(0, 5));
					Page page = pageTable.get(pageNumber);
					page.index = pageNumber;
					page.referenced = true;
					if(lines[1].equals("W")) {
						page.dirty = true;
					}
					if(page.valid) {
						//no page fault
						System.out.println(lines[0] + " hit");
					} else {
						//page fault, so increment counter
						faults++;
						if(currentFrame < numberFrames) {
							//frames aren't full
							frames[currentFrame] = pageNumber;
							page.frame = currentFrame;
							page.valid = true;
							currentFrame++;
							System.out.println(lines[0] + " page fault - no eviction");
						} else {
							Page pageEvict = null;
							int done = 0;
							while(done == 0) {
								for(int i = 0; i < frames.length; i++) {
									Page tempPage = pageTable.get(frames[i]);
									if(!tempPage.referenced && !tempPage.dirty && tempPage.valid) {
										//Not referenced and not modified
										page.frame = tempPage.frame;
										if(tempPage.dirty) {
											diskWrites++;
											System.out.println(lines[0] + " page fault - evict dirty");
										} else {
											System.out.println(lines[0] + " page fault - evict clean");
										}
										frames[page.frame] = page.index;
										tempPage.valid = false;
										tempPage.dirty = false;
										tempPage.referenced = false;
										tempPage.frame = -1;
										pageTable.put(tempPage.index, tempPage);
										page.valid = true;
										pageTable.put(page.index, page);
										done = 1;
										break;
									} else {
										if(!tempPage.referenced && tempPage.dirty && tempPage.valid) {
											//not referenced, but has been modified
											pageEvict = new Page(tempPage);
											continue;
										} else {
											if(tempPage.referenced && !tempPage.dirty && tempPage.valid && pageEvict == null) {
												//has been referenced, but not modified
												pageEvict = new Page(tempPage);
												continue;
											} else {
												if(tempPage.referenced && tempPage.dirty && tempPage.valid && pageEvict == null) {
													//has been referenced and modified
													pageEvict = new Page(tempPage);
													continue;
												}
											}
										}
									}
								}
								if(done == 1) {
									continue;
								}
								page.frame = pageEvict.frame;
								if(pageEvict.dirty) {
									diskWrites++;
									System.out.println(lines[0] + " page fault - evict dirty");
								} else {
									System.out.println(lines[0] + " page fault - evict clean");
								}
								frames[page.frame] = page.index;
								pageEvict.valid = false;
								pageEvict.dirty = false;
								pageEvict.frame = -1;
								pageEvict.referenced = false;
								pageTable.put(pageEvict.index, pageEvict);
								page.valid = true;
								pageTable.put(page.index, page);
								done = 1;
							}
						}
					}
					pageTable.put(pageNumber, page);
				}
				System.out.println("NRU");
				System.out.println("Number of frames:       " + numberFrames);
				System.out.println("Total memory accesses:  " + memoryAccesses);
				System.out.println("Total page faults:      " + faults);
				System.out.println("Total writes to disk:   " + diskWrites);
			} else if(algorithm.equals("aging")) {
				//aging algorithm
				int currentFrame = 0;
				//set agingCounter for 00000000 for every frame
				agingCounter = new int[numberFrames][8];
				for(int i = 0; i < numberFrames; i++) {
					for(int j = 0; j < 8; j++) {
						agingCounter[i][j] = 0;
					}
				}
				referenceBit = new int[numberFrames];
				for(int i = 0; i < numberFrames; i++) {
					
				}
				while(traceFile.ready()) {
					memoryAccesses++;
					if(memoryAccesses % refreshRate == 0) {
						//Move all bits on aging counter to the right one bit
						for(int i = 0; i < currentFrame; i++) {
							for(int j = 1; j < 8; j++) {
								agingCounter[i][j] = agingCounter[i][j - 1];
							}
							agingCounter[i][0] = referenceBit[i];
							referenceBit[i] = 0;
						}
					}
					String[] lines = traceFile.readLine().split(" ");
					int pageNumber = Integer.decode("0x" + lines[0].substring(0, 5));
					Page page = pageTable.get(pageNumber);
					page.index = pageNumber;
					page.referenced = true;
					if(lines[1].equals("W")) {
						page.dirty = true;
					}
					if(page.valid) {
						//no page fault
						referenceBit[page.frame] = 1;
						System.out.println(lines[0] + " hit");
					} else {
						//page fault, so increment counter
						faults++;
						if(currentFrame < numberFrames) {
							//frames aren't full
							frames[currentFrame] = pageNumber;
							page.frame = currentFrame;
							page.valid = true;
							referenceBit[currentFrame] = 1;
							currentFrame++;
							System.out.println(lines[0] + " page fault - no eviction");
						} else {
							int temp = getLRU(numberFrames, agingCounter, referenceBit);
							Page tempPage = pageTable.get(frames[temp]);
							for(int i = 0; i < 8; i++) {
								agingCounter[temp][i] = 0;
							}
							referenceBit[temp] = 1;
							if(tempPage.dirty == true) {
								//dirty evict, so write to disk
								diskWrites++;
								System.out.println(lines[0] + " page fault - evict dirty");
							} else {
								System.out.println(lines[0] + " page fault - evict clean");
							}
							//swap pages
							frames[tempPage.frame] = page.index;
							page.frame = tempPage.frame;
							page.valid = true;
							tempPage.dirty = false;
							tempPage.referenced = false;
							tempPage.valid = false;
							tempPage.frame = -1;
							pageTable.put(tempPage.index, tempPage);
						}
					}
					pageTable.put(page.index, page);
				}
				System.out.println("Aging");
				System.out.println("Number of frames:       " + numberFrames);
				System.out.println("Total memory accesses:  " + memoryAccesses);
				System.out.println("Total page faults:      " + faults);
				System.out.println("Total writes to disk:   " + diskWrites);
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("Filename not found. The last argument must be a valid filename.");
			System.exit(1);
		} catch(IOException ioe) {
			System.out.println("IO Exception. Ensure the file is workable and try again.");
			System.exit(1);
		}
		
		System.exit(0);
	}
	
	public static int locateLongestPage(int[] frames, Hashtable<Integer, LinkedList<Integer>> future) {
		int index = 0;
		int max = 0;
		for(int i = 0; i < frames.length; i++) {
			if(future.get(frames[i]).isEmpty()) {
				return frames[i];
			} else {
				if(future.get(frames[i]).get(0) > max) {
					max = future.get(frames[i]).get(0);
					index = frames[i];
				}
			}
		}
		return index;
	}
	
	public static int getLRU(int numberFrames, int[][] counter, int rBit[]) {
		int index = 0;
		int min = 9999999;
		String bit = "";
		for(int i = 0; i < numberFrames; i++) {
			int temp = 0;
			for(int j = 0; j < 8; j++) {
				bit += counter[i][j];
			}
			temp = Integer.parseInt(bit, 2);
			if(rBit[i] != 1) {
				if(temp < min) {
					min = temp;
					index = i;
				}
			}
			bit = "";
		}
		return index;
	}
}