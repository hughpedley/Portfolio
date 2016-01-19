/**
 * MyKeyGen.java, first part of assignment 5 for CS1501
 * Generates a private and public key and writes them to a files
 */

import java.math.BigInteger;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class MyKeyGen
{
	public static void main(String[] args)
	{
		//Generate p, q, and n for our encryption
		BigInteger p = new BigInteger(1024, 1, new Random());
		BigInteger q = new BigInteger(1024, 1, new Random());
		BigInteger n = p.multiply(q);
		
		//Generate phi(n)
		BigInteger pLess1 = p.subtract(new BigInteger("1"));
		BigInteger qLess1 = q.subtract(new BigInteger("1"));
		BigInteger phi = pLess1.multiply(qLess1);
		
		//Generate e
		BigInteger e = new BigInteger(1024, 1, new Random());
		while(phi.compareTo(e) !=1 || !phi.gcd(e).equals(new BigInteger("1"))){
			e = new BigInteger(1024, 1, new Random());
		}
		
		//Generate d
		BigInteger d = e.modInverse(phi);
		
		try{
			//Save e and n to file
			FileWriter filewriter = new FileWriter("pubkey.rsa");
			filewriter.write(e.toString() + "\n");
			filewriter.write(n.toString());
			filewriter.close();
			
			//Save d and n to file
			filewriter = new FileWriter("privkey.rsa");
			filewriter.write(d.toString() + "\n");
			filewriter.write(n.toString() + "\n");
			filewriter.close();
		}catch(IOException ioe){
			System.out.println("Unable to write to file. Exiting program.");
		}
		
		System.out.println("Public key saved to pubkey.rsa, and private key saved to privkey.rsa.");
	}
}