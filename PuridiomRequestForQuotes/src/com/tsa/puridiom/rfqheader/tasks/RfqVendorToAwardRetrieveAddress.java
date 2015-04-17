/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqVendorToAwardRetrieveAddress extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List rfqVendorList = (List)incomingRequest.get("rfqVendorList");
			List rfqVendorAwardedList = (List)incomingRequest.get("rfqVendorAwardedList");
			List vendorsRejected = new ArrayList();
			for(int i = 0; i < rfqVendorList.size(); i++)
			{
				RfqVendor rfqVendor = (RfqVendor)rfqVendorList.get(i);
				boolean found = false;
				for(int j = 0; j < rfqVendorAwardedList.size(); j++)
				{
					RfqVendor rfqVendorAwarded = (RfqVendor)rfqVendorAwardedList.get(j);
					if(rfqVendor.getVendorId().equalsIgnoreCase(rfqVendorAwarded.getVendorId()))
					{
						found = true;
						j = rfqVendorAwardedList.size();
					}
				}
				if(!found)
				{
					vendorsRejected.add(rfqVendor);
				}
			}

			//rejected vendor's address
			for(int i = 0; i < vendorsRejected.size(); i++)
			{
				RfqVendor rfqVendor = (RfqVendor)vendorsRejected.get(i);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
	            newIncomingRequest.put("userId", incomingRequest.get("userId"));
	            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("awardedVendor", rfqVendor.getVendorId());
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	            PuridiomProcess process = processLoader.loadProcess("retrieve-vendor-address.xml");
	            process.executeProcess(newIncomingRequest);

	            if(process.getStatus() == Status.SUCCEEDED)
	            {
	            	Address vendorToAwardAddress = (Address)newIncomingRequest.get("vendorToAwardAddress");
	            	rfqVendor.setAddress(vendorToAwardAddress);
	            	vendorsRejected.set(i, rfqVendor);
	            }
			}

			//awarded vendor's address
			for(int i = 0; i < rfqVendorAwardedList.size(); i++)
			{
				RfqVendor rfqVendor = (RfqVendor)rfqVendorAwardedList.get(i);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
	            newIncomingRequest.put("userId", incomingRequest.get("userId"));
	            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("awardedVendor", rfqVendor.getVendorId());
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	            PuridiomProcess process = processLoader.loadProcess("retrieve-vendor-address.xml");
	            process.executeProcess(newIncomingRequest);

	            if(process.getStatus() == Status.SUCCEEDED)
	            {
	            	Address vendorToAwardAddress = (Address)newIncomingRequest.get("vendorToAwardAddress");
	            	rfqVendor.setAddress(vendorToAwardAddress);
	            	rfqVendorAwardedList.set(i, rfqVendor);
	            }
			}
			incomingRequest.put("rfqVendorAwardedList", rfqVendorAwardedList);

			this.setStatus(Status.SUCCEEDED);
			result = vendorsRejected;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
