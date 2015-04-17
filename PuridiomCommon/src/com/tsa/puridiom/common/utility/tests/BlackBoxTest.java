package com.tsa.puridiom.common.utility.tests;

import com.tsa.puridiom.common.utility.BlackBox;

public class BlackBoxTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		    String	encyrpted = BlackBox.getEncrypt(testString);
		    String	decrypted = BlackBox.getDecrypt(encyrpted);

		    String testString2 = "abcdefghijklmnopqrstuvwxyz0123456789";
		    String	encyrpted2 = BlackBox.getEncrypt(testString2);
		    String	decrypted2 = BlackBox.getDecrypt(encyrpted2);

		    System.out.println("Test String	= " + testString);
		    System.out.println("Test String	= " + testString2);
		    
		    System.out.println("Encrypted	= " + encyrpted);
		    System.out.println("Encrypted	= " + encyrpted2);
		    
		    System.out.println("Decrypted	= " + decrypted);
		    System.out.println("Decrypted	= " + decrypted2);
		    
			System.out.println("BlackBoxTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}