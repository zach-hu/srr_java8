package com.tsagate.foundation.utility.test;

import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.sql.Date;

/**
 * @author kelli
 */
public class UniqueKeyGeneratorTest
{
	public UniqueKeyGeneratorTest ()
	{
		super();
	}

	public static void main(String args[])
	{
		UniqueKeyGeneratorTest test = new UniqueKeyGeneratorTest();

		try
		{
			test.outPutKeyTest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//system.out.println("COMPLETE");
	}
	public void outPutKeyTest()
	{
		boolean success = false;

		try
		{
	        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			for (int i=1; i< 125; i++) {
				System.out.println(ukg.getUniqueKey()) ;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
