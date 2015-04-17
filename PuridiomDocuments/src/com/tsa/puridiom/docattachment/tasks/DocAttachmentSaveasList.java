/*
 * Created on Nov 9, 2004
 */
package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class DocAttachmentSaveasList extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a DocAttachment List from incoming request(docAttachmentList)</p>
	 * <p> and calls docattachment-saveas-by-id process for each.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List docAttachmentList = (List)incomingRequest.get("docAttachmentList");
		
		for (int row = 0; row < docAttachmentList.size(); row++)
		{
		    DocAttachment docAttachment = (DocAttachment) docAttachmentList.get(row);

			incomingRequest.put("docAttachment", docAttachment);
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("docattachment-saveas-by-id.xml") ;
			
			process.executeProcess(incomingRequest);

			if (process.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("DocAttachmentSaveAs process failed (docattachment-saveas-by-id.xml).");
			}			
			
			docAttachment = (DocAttachment) incomingRequest.get("docAttachment");
			docAttachmentList.set(row, docAttachment);

			this.setStatus(Status.SUCCEEDED);
		}
		if(docAttachmentList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return docAttachmentList;
	}

}
