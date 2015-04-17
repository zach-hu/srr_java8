package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class DocAttachmentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DocAttachmentPK comp_id = new DocAttachmentPK();
			DocAttachment docAttachment = (DocAttachment) incomingRequest.get("docAttachment");
			String organizationId = (String) incomingRequest.get("organizationId");
			String dateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy");

			if (docAttachment == null)
			{
				docAttachment = new DocAttachment();
			}

			if (incomingRequest.containsKey("DocAttachment_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("DocAttachment_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("DocAttachment_docIc"))
			{
				String docIcString = (String) incomingRequest.get("DocAttachment_docIc");
				if (Utility.isEmpty(docIcString))
				{
				    docIcString = "0";
				}
				BigDecimal docIc = new BigDecimal ( docIcString );
				comp_id.setDocIc(docIc);
			}
			if (incomingRequest.containsKey("DocAttachment_icLine"))
			{
				String icLineString = (String) incomingRequest.get("DocAttachment_icLine");

				if (Utility.isEmpty(icLineString)   || icLineString.equalsIgnoreCase("null"))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("DocAttachment_docSource"))
			{
				String docSource = (String ) incomingRequest.get("DocAttachment_docSource");
				docAttachment.setDocSource(docSource);
			}
			if (incomingRequest.containsKey("DocAttachment_docTitle"))
			{
				String docTitle = (String ) incomingRequest.get("DocAttachment_docTitle");
				docAttachment.setDocTitle(docTitle);
			}
			if (incomingRequest.containsKey("DocAttachment_docFilename"))
			{
				String docFilename = (String ) incomingRequest.get("DocAttachment_docFilename");
				docAttachment.setDocFilename(docFilename);
			}
			if (incomingRequest.containsKey("DocAttachment_docPrint"))
			{
				String docPrint = (String ) incomingRequest.get("DocAttachment_docPrint");
				docAttachment.setDocPrint(docPrint);
			}
			if (incomingRequest.containsKey("DocAttachment_docPost"))
			{
				String docPost = (String ) incomingRequest.get("DocAttachment_docPost");
				docAttachment.setDocPost(docPost);
			}
			if (incomingRequest.containsKey("DocAttachment_uploadedBy"))
			{
				String uploadedBy = (String ) incomingRequest.get("DocAttachment_uploadedBy");
				docAttachment.setUploadedBy(uploadedBy);
			}
			if (incomingRequest.containsKey("DocAttachment_dateUploaded"))
			{
				String dateUploadedString = (String) incomingRequest.get("DocAttachment_dateUploaded");
				Date dateUploaded = Dates.getSqlDate(dateUploadedString, dateFormat);
				docAttachment.setDateUploaded(dateUploaded);
			}

			docAttachment.setComp_id(comp_id);

			result = docAttachment;
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