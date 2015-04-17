/**
 *
 */
package com.tsa.puridiom.cxml.tasks;

import java.util.Map;

import org.jdom.Document;

import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Johnny Zapana
 */
public class CXMLDocumentBuilder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			SendQueue sendQueue = (SendQueue) incomingRequest.get("sendQueue");

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String nameIt = ukg.getUniqueKey().toString();
			String fileName = nameIt + "_" + sendQueue.getQueueid().toString();
			Document requestDocument = (Document) incomingRequest.get("requestDocument");
			Document responseDocument = (Document) incomingRequest.get("responseDocument");
			String cxmlOmitEncoding = (String) incomingRequest.get("cxmlOmitEncoding");

			if (requestDocument != null)
			{
				CXML.toFileSystem(requestDocument, cxmlOmitEncoding, organizationId, fileName + "_rqst");
			} else
			{
				throw new Exception("Unable to store cXML request for sendQueueId: " + sendQueue.getQueueid().toString());
			}

			if (responseDocument != null)
			{
				CXML.toFileSystem(responseDocument, organizationId, fileName + "_resp");
			}

			Log.debug(this, "CXML Request and Response file were stored successfully");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			e.printStackTrace();

			Log.error(this, "Unable to store cXML files: \r\n" + e.getMessage());

			throw e;
		}

		return null;
	}

}
