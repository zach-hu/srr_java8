package com.tsa.puridiom.vendorquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorquestion.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class VendorQuestionAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorQuestionAdd test = new VendorQuestionAdd();
			Map incomingRequest = new HashMap();

			VendorQuestion vendorQuestion = new VendorQuestion();

			vendorQuestion.setIcVendorQuestion(new BigDecimal(2));
			vendorQuestion.setIcRfqHeader(new BigDecimal(999999));
			vendorQuestion.setVendorId("TESTVENDOR");
			vendorQuestion.setQuestionTitle("VENDOR QUESTION TEST3");
			vendorQuestion.setQuestionText("This is another test for the vendor question add task - different rfq.");
			vendorQuestion.setDatePosted(Dates.getDate(Dates.today("", "")));
			vendorQuestion.setTimePosted(Dates.getTimeString(Dates.today("hh:mm:ss", "")));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorQuestion", vendorQuestion);

			vendorQuestion = (VendorQuestion) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorQuestionAddTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("VendorQuestionAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}