package com.tsa.puridiom.invformdata.tasks.tests;

import com.tsa.puridiom.entity.InvFormData;
import com.tsa.puridiom.invformdata.tasks.InvFormDataSaveas;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class InvFormDataSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			dbs.startTransaction();
			
			InvFormDataSaveas saveasTask = new InvFormDataSaveas();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "KELLI");
			
			InvFormData invFormData = new InvFormData();
			
			invFormData.setItemNumber("ORIGINAL TEST ITEM");
			invFormData.setAdhesive("ADHESIVE");
			invFormData.setAppointedFlag("Y");
			invFormData.setAutomaticReprint("Y");
			invFormData.setBinding("BINDING");
			invFormData.setBindingCover("binding cover");
			
			incomingRequest.put("invFormData", invFormData) ;
			incomingRequest.put("InvFormData_itemNumber", invFormData.getItemNumber());
			incomingRequest.put("newInvFormData_itemNumber", "NEW ITEM NUMBER");
			
			saveasTask.executeTask(incomingRequest);
			
			if (saveasTask.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Status: " + saveasTask.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}