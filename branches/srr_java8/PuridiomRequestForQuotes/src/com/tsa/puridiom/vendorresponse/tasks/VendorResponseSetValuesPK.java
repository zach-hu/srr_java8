package com.tsa.puridiom.vendorresponse.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class VendorResponseSetValuesPK
{
	public void setValues(Map incomingRequest, VendorResponse vendorresponse ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("VendorResponse_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icQuestionString = (String) incomingRequest.get("VendorResponse_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			String vendorId = (String ) incomingRequest.get("VendorResponse_vendorId");
			VendorResponsePK comp_id = new VendorResponsePK();
			comp_id.setIcQuestion(icQuestion);
			comp_id.setIcRfqHeader(icRfqHeader);
			comp_id.setVendorId(vendorId);
			vendorresponse.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}