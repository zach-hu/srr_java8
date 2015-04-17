package com.tsa.puridiom.timer;

import java.util.Calendar;
import java.util.TimeZone;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;

import com.tsagate.foundation.utility.Dates;

public class ScheduleStart
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword("tsapuridiom");

		HibernatePBEEncryptorRegistry registry =
			HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("hibernateEncryptor", strongEncryptor);

		if(args == null || args.length < 1)
		{
			args = new String[1];
			args[0] = "bsc04p";
		}
		if(args != null && args.length > 0)
		{
			Runtime runtime = Runtime.getRuntime();
			long heapMaxSize = runtime.maxMemory() / 1024;
			System.err.println("Allocated memory: " + heapMaxSize) ;
			System.err.println("free memory: " + runtime.freeMemory()/ 1024) ;

			for (int i = 0; i < args.length; i++)
			{
				System.out.println("Schedule starts at " + Calendar.getInstance().getTime() + "for oid: " + args[i]);


				System.err.println("Initial used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;

				ScheduleManager.getInstance().start(args[i]);
				System.out.println("Schedule done: " + Dates.getNow("", "") + " " + TimeZone.getDefault().getID());
				System.err.println("Used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;
				System.err.println("free memory: " + runtime.freeMemory()/ 1024) ;
				runtime.gc();
				runtime.gc();
				System.err.println("after gc: " + runtime.freeMemory()/ 1024) ;
				System.err.println("after gc used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;
			}
		}
		System.exit(0);
	}
}
