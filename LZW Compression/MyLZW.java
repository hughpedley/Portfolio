/*************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/

public class MyLZW {
    private static final int R = 256;        // number of input chars
    private static int W = 9;    		     // codeword width
    private static int L = 512;      		 // number of codewords = 2^W
    private static double numerator = 0;
    private static double denominator = 0;
    private static boolean ratio = true;
    private static double savedRatio = 0;
    private static double currentRatio = 0;
    
    /**
     * Compress method. The same as the standard LZW.
     * This is the "do nothing mode" method. It continues to use the full codebook once it has filled up.
     */
    public static void compressN() { 
    	String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        
        //write n first to file
        BinaryStdOut.write('n');
        while (input.length() > 0) {
        	L = (int)Math.pow(2, W);
        	String s = st.longestPrefixOf(input);  // Find max prefix match s.
        	BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
        	int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            //this else if statement makes it variable length
            if(W < 16 && ((int)Math.pow(2, W) == code)){
            	W++;
            	L = (int) Math.pow(2, W);
            	st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    } 

    /**
     * Compress method. This is the "reset mode" method.
     * It resets the codebook once it has filled up.
     */
    public static void compressR() { 
    	String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        
        //write n first to file
        BinaryStdOut.write('r');
        while (input.length() > 0) {
        	L = (int)Math.pow(2, W);
        	String s = st.longestPrefixOf(input);  // Find max prefix match s.
        	BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
        	int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            //this else if statement makes it variable length
            if(W < 16 && ((int)Math.pow(2, W) == code)){
            	W++;
            	L = (int) Math.pow(2, W);
            	st.put(input.substring(0, t + 1), code++);
            }
            if(code == 65536){
            	st = new TST<Integer>();
            	for(int i = 0; i < R; i++){
            		st.put("" + (char) i, i);
            	}
            	code = R+1;
            	W = 9;
            	L = 512;
            }
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    /**
     * Monitor compress method. Once the dictionary is filled up, will monitor compression ratio
     * and decide when to erase the symbol table
     */
    public static void compressM() { 
    	String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        
        //write n first to file
        BinaryStdOut.write('m');
        while (input.length() > 0) {
        	L = (int)Math.pow(2, W);
        	String s = st.longestPrefixOf(input);  // Find max prefix match s.
        	numerator += s.length() * 8;
        	BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
        	denominator += W;
        	currentRatio = numerator/denominator;
        	int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            //this else if statement makes it variable length
            if(W < 16 && ((int)Math.pow(2, W) == code)){
            	W++;
            	L = (int) Math.pow(2, W);
            	st.put(input.substring(0, t + 1), code++);
            }
            if(code == 65536){
            	if(ratio){
            		savedRatio = currentRatio;
            		ratio = false;
            	}
            	if(savedRatio / currentRatio > 1.1){
            		st = new TST<Integer>();
            		for(int i = 0; i < R; i++){
            			st.put("" + (char) i, i);
            		}
            		code = R + 1;
            		W = 9;
            		L = 512;
            		savedRatio = 0;
            		currentRatio = 0;
            		ratio = true;
            	}
            }
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }
    
    public static void expand() {
    	String[] st = new String[(int)Math.pow(2, 16)];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        char compressMethod = BinaryStdIn.readChar();
        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);
            numerator += val.length() * 8;
            codeword = BinaryStdIn.readInt(W);
            denominator += W;
            currentRatio = numerator/denominator;
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            //fill symbol table for do nothing mode
            if (i < L - 1) st[i++] = val + s.charAt(0);
            //fill symbol table like normal for reset mode, until table is full
            if(i == L - 1 && W < 16){
            	st[i++] = val + s.charAt(0);
            	W++;
            	L = (int)Math.pow(2, W);
            }
            val = s;
            
            //reset symbol table for reset mode
            if(i == 65535 && compressMethod == 'r'){
            	W = 9;
            	L = 512;
            	st = new String[(int)Math.pow(2, 16)];
            	for(i = 0; i < R; i++){
            		st[i] = "" + (char) i;
            	}
            	st[i++] = "";
            	codeword = BinaryStdIn.readInt(W);
            	if(codeword == R) return;
            	val = st[codeword];
            }
           
            //monitor ratios for monitor mode
            if(i == 65535 && compressMethod == 'm'){
            	if(ratio){
            		savedRatio = currentRatio;
            		ratio = false;
            	}
            	if(savedRatio/currentRatio > 1.1){
            		W = 9;
            		L = 512;
            		st = new String[(int)Math.pow(2, 16)];
            		for(i = 0; i < R; i++){
            			st[i] = "" + (char) i;
            		}
            		st[i++] = "";
            		codeword = BinaryStdIn.readInt(W);
            		if(codeword == R)return;
            		val = st[codeword];
            		savedRatio = 0;
            		currentRatio = 0;
            		ratio = true;
            	}
            }
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        //calls Do nothing mode if "n" was passed in
    	if      (args[0].equals("-") && args[1].equals("n")) compressN();
    	else if (args[0].equals("-") && args[1].equals("r")) compressR();
    	else if (args[0].equals("-") && args[1].equals("m")) compressM();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
