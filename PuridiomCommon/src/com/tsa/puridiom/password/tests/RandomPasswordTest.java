package com.tsa.puridiom.password.tests;

import com.tsa.puridiom.password.RandomPassword;

public class RandomPasswordTest
{
	public static void  main (String[] args) throws Exception
	{
		int passwordLength = 8;
		int mask = RandomPassword.ALLS;

		RandomPassword randomPassword = new RandomPassword(passwordLength, mask);
		String password = randomPassword.getNext();

		System.out.println("password = " + password);
	}
}