package com.tsa.puridiom.vendordocument.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorDocumentSetValuesPK
{
	public void setValues(Map incomingRequest, VendorDocument vendordocument ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("VendorDocument_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String vendorId = (String ) incomingRequest.get("VendorDocument_vendorId");
			String docIcString = (String) incomingRequest.get("VendorDocument_docIc");
			BigDecimal docIc = new BigDecimal ( docIcString );
			VendorDocumentPK comp_id = new VendorDocumentPK();
			comp_id.setDocIc(docIc);
			comp_id.setIcRfqHeader(icRfqHeader);
			comp_id.setVendorId(vendorId);
			vendordocument.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}