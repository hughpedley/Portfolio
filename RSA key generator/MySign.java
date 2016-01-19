/**
 * MySign, the second file for assignment 5 for CS1501.
 * Used to sign files and verify signatures.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class MySign
{
	public static void main(String[] args)
	{
		//Program ends if user did not pass in required arguments
		if(args.length != 2){
			System.out.println("Please pass in two arguments - one specifying signing or verifying, and the file to be signed/verified.");
			System.out.println("Exiting program.");
			System.exit(1);
		}
		
		String flag = args[0];
		String filename = args[1];
		
		//Sign
		if(flag.equals("s")){
			File file = new File(filename);
			try{
				//Read in original contents of file
				Scanner reader = new Scanner(file);
				ArrayList<String> arrayOfContents = new ArrayList<>();
				String stringFile = "";
				while(reader.hasNext())
				{
					String s = reader.nextLine();
					arrayOfContents.add(s);
					stringFile = stringFile + s;
				}
				try{
					//Generate SHA-256 hash of the original file
					MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
					BigInteger hash = new BigInteger(algorithm.digest(stringFile.getBytes())).abs();

					//Get d and n from privkey, and get decrypted hash from original
					File privateKey = new File("privkey.rsa");
					reader = new Scanner(privateKey);
					BigInteger d = new BigInteger(reader.nextLine());
					BigInteger n = new BigInteger(reader.nextLine());
					BigInteger decrypt = hash.modPow(d, n);
					
					try{
						//Write out signed file to 'file'.signed
						FileWriter fw = new FileWriter(file + ".signed");
						fw.write(decrypt + "\n");
						for(String line : arrayOfContents){
							fw.write(line + "\n");
						}
						fw.close();
						System.out.println("Signed file written to " + file + ".signed.");
					}catch(IOException ioe){
						System.out.println("IO Exception. Ending program.");
						System.exit(1);
					}
				}catch(NoSuchAlgorithmException nsae){
					System.out.println("Could not use algorithm. Ending program.");
					System.exit(1);
				}
			}
			catch(FileNotFoundException fnfe){
				System.out.println("Could not find the file you passed in. Ending program.");
				System.exit(1);
			}
		}
		else if(flag.equals("v")){
			File file = new File(filename);
			try{
				//Read in contents of file
				Scanner reader = new Scanner(file);
				ArrayList<String> arrayOfContents = new ArrayList<>();
				String stringFile = "";
				BigInteger decrypt = new BigInteger(reader.nextLine());
				while(reader.hasNext())
				{
					String s = reader.nextLine();
					arrayOfContents.add(s);
					stringFile = stringFile + s;
				}
				reader.close();
				
				//Generate hash of file
				MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
				BigInteger hash = new BigInteger(algorithm.digest(stringFile.getBytes())).abs();
				
				//Get public key from previously generated file
				File pubKey = new File("pubkey.rsa");
				reader = new Scanner(pubKey);
				String stringE = reader.nextLine();
				BigInteger e = new BigInteger(stringE);
				String stringN = reader.nextLine();
				BigInteger n = new BigInteger(stringN);
				
				//Get 'new' signature from file
				BigInteger encrypt = decrypt.modPow(e, n);
				
				//Checks hash to see if it's equal to desired signature
				if(hash.equals(encrypt)){
					System.out.println("Valid signature!");
				}
				else{
					System.out.println("Invalid signature.");
				}
				reader.close();
				
			}catch(FileNotFoundException fnfe) {
				System.out.println("Could not find the file you passed in. Exiting program.");
				System.exit(1);
			}catch (NoSuchAlgorithmException nsae) {
				System.out.println("Could not use algorithm. Ending program.");
				System.exit(1);
			}
		}
		else{
			System.out.println("Please use 's' or 'v' as your first argument.");
			System.out.println("Exiting program.");
			System.exit(1);
		}
	}
}