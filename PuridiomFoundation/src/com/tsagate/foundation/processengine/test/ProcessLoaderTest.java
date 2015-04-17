/*
 * Created on Nov. 5, 2003
 */
package com.tsagate.foundation.processengine.test;

import com.tsagate.foundation.processengine.*;
/**
 * @author Kelli
 */
public class ProcessLoaderTest {

	public static void main(String args[]) {
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("process-template.xml");
			System.out.println(process.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("COMPLETE.");
	}

}
