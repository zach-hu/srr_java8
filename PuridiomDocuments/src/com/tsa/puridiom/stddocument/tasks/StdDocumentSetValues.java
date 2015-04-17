package com.tsa.puridiom.stddocument.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class StdDocumentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdDocument stdDocument = (StdDocument) incomingRequest.get("stdDocument");
			if (stdDocument == null)
			{
				stdDocument = new StdDocument();
			}

			if (incomingRequest.containsKey("StdDocument_fileName"))
			{
				String fileName = (String ) incomingRequest.get("StdDocument_fileName");
				stdDocument.setFileName(fileName);
			}
			if (incomingRequest.containsKey("StdDocument_title"))
			{
				String title = (String ) incomingRequest.get("StdDocument_title");
				stdDocument.setTitle(title);
			}
			if (incomingRequest.containsKey("StdDocument_description"))
			{
				String description = (String ) incomingRequest.get("StdDocument_description");
				stdDocument.setDescription(description);
			}
			if (incomingRequest.containsKey("StdDocument_docType"))
			{
				String docType = (String ) incomingRequest.get("StdDocument_docType");
				stdDocument.setDocType(docType);
			}
			if (incomingRequest.containsKey("StdDocument_datePosted"))
			{
				String datePostedString = (String) incomingRequest.get("StdDocument_datePosted");
				Date datePosted = Dates.getDate(datePostedString);
				stdDocument.setDatePosted(datePosted);
			}
			if (incomingRequest.containsKey("StdDocument_hits"))
			{
				String hitsString = (String) incomingRequest.get("StdDocument_hits");
				if (Utility.isEmpty(hitsString))
				{
					hitsString = "0";
				}
				BigDecimal hits = new BigDecimal ( hitsString );
				stdDocument.setHits(hits);
			}

			result = stdDocument;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}