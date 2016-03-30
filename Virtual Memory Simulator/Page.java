/**
 * Constructors for a page entry, to be placed into a page table
 * To be used in CS 1550 Project 3's vmsim
 */
public class Page {
	
	//fields
	int frame;
	int index;
	boolean referenced;
	boolean valid;
	boolean dirty;
	
	/**
	 * Empty constructor
	 */
	public Page() {
		this.frame = -1;
		this.index = 0;
		this.referenced = false;
		this.valid = false;
		this.dirty = false;
	}
	
	/**
	 * Copy constructor
	 * @param toBeCopied - the page being copied
	 */
	public Page(Page toBeCopied) {
		this.frame = toBeCopied.frame;
		this.index = toBeCopied.index;
		this.referenced = toBeCopied.referenced;
		this.valid = toBeCopied.valid;
		this.dirty = toBeCopied.dirty;
	}
}