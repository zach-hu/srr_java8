package com.tsa.puridiom.vendordocument.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorDocumentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorDocumentPK comp_id = new VendorDocumentPK();
			VendorDocument vendorDocument = (VendorDocument) incomingRequest.get("vendorDocument");
			if (vendorDocument == null)
			{
				vendorDocument = new VendorDocument();
			}

			if (incomingRequest.containsKey("VendorDocument_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("VendorDocument_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("VendorDocument_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorDocument_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorDocument_docIc"))
			{
				String docIcString = (String) incomingRequest.get("VendorDocument_docIc");
				if (Utility.isEmpty(docIcString))
				{
					docIcString = "0";
				}
				BigDecimal docIc = new BigDecimal ( docIcString );
				comp_id.setDocIc(docIc);
			}
			if (incomingRequest.containsKey("VendorDocument_docTitle"))
			{
				String docTitle = (String ) incomingRequest.get("VendorDocument_docTitle");
				vendorDocument.setDocTitle(docTitle);
			}
			if (incomingRequest.containsKey("VendorDocument_docFilename"))
			{
				String docFilename = (String ) incomingRequest.get("VendorDocument_docFilename");
				vendorDocument.setDocFilename(docFilename);
			}
			if (incomingRequest.containsKey("VendorDocument_docType"))
			{
				String docType = (String ) incomingRequest.get("VendorDocument_docType");
				vendorDocument.setDocType(docType);
			}
			if (incomingRequest.containsKey("VendorDocument_datePosted"))
			{
				String datePostedString = (String) incomingRequest.get("VendorDocument_datePosted");
				Date datePosted = Dates.getDate(datePostedString);
				vendorDocument.setDatePosted(datePosted);
			}
			vendorDocument.setComp_id(comp_id);

			result = vendorDocument;
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