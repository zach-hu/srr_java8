/*
 * Created on Nov 9, 2004
 */
package com.tsa.puridiom.vendordocument.tasks;

import com.tsa.puridiom.entity.VendorDocument;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class VendorDocumentDeleteList extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a VendorDocument List from incoming request(vendorDocumentList)</p>
	 * <p> and calls vendordocument-delete-by-id process for each.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List vendorDocumentList = (List)incomingRequest.get("vendorDocumentList");
		
		for (int row = 0; row < vendorDocumentList.size(); row++)
		{
		    VendorDocument vendorDocument = (VendorDocument) vendorDocumentList.get(row);

			incomingRequest.put("vendorDocument", vendorDocument);
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("vendordocument-delete-by-id.xml") ;
			
			process.executeProcess(incomingRequest);

			if (process.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("VendorDocument Delete process failed (docattachment-delete-by-id.xml).");
			}			
			
			vendorDocument = (VendorDocument) incomingRequest.get("vendorDocument");
			vendorDocumentList.set(row, vendorDocument);

			this.setStatus(Status.SUCCEEDED);
		}
		if(vendorDocumentList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return vendorDocumentList;
	}

}
