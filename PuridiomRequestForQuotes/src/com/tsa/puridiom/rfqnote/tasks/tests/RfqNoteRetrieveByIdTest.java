package com.tsa.puridiom.rfqnote.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqnote.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqNoteRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqNoteRetrieveById test = new RfqNoteRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqNote_icHeader", "2241456600010");
			incomingRequest.put("RfqNote_icLine", "2243407900056");
			incomingRequest.put("RfqNote_VendorId", "ACME");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			RfqNote rfqNote = (RfqNote) test.executeTask(incomingRequest);
			if (rfqNote == null) {
				System.out.println("No record found.");
			} else {
				System.out.println("Rfq Note: " + rfqNote.toString());
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}