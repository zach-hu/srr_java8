/*
 * Created on Nov 9, 2004
 */
package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class DocAttachmentDeleteList extends Task
{

	/**
	 * executeTask
	 * <p>This method takes a DocAttachment List from incoming request(docAttachmentList)</p>
	 * <p> and calls docattachment-delete-by-id process for each.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List docAttachmentList = (List)incomingRequest.get("docAttachmentList");
			if(docAttachmentList == null)
			{
				this.setStatus(Status.SUCCEEDED);
				return null;
			}
			if(docAttachmentList.size() == 0)
			{
				this.setStatus(Status.SUCCEEDED);
				return docAttachmentList;
			}

			for (int row = 0; row < docAttachmentList.size(); row++)
			{
			    DocAttachment docAttachment = (DocAttachment) docAttachmentList.get(row);

				incomingRequest.put("docAttachment", docAttachment);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
				PuridiomProcess process = processLoader.loadProcess("docattachment-delete-by-id.xml") ;

				process.executeProcess(incomingRequest);

				if (process.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(Status.FAILED);
					throw new Exception("DocAttachment Delete process failed (docattachment-delete-by-id.xml).");
				}

				docAttachment = (DocAttachment) incomingRequest.get("docAttachment");
				docAttachmentList.set(row, docAttachment);

				this.setStatus(Status.SUCCEEDED);
			}

			return docAttachmentList;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Attachment List could not be deleted!", e);
		}
	}

}
