package com.tsa.puridiom.vendorresponse.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class VendorResponseSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorResponsePK comp_id = new VendorResponsePK();
			VendorResponse vendorResponse = (VendorResponse) incomingRequest.get("vendorResponse");
			if (vendorResponse == null)
			{
				vendorResponse = new VendorResponse();
			}

			if (incomingRequest.containsKey("VendorResponse_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("VendorResponse_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("VendorResponse_icQuestion"))
			{
				String icQuestionString = (String) incomingRequest.get("VendorResponse_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				comp_id.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("VendorResponse_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("VendorResponse_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorResponse_response"))
			{
				String response = (String) incomingRequest.get("VendorResponse_response");
				vendorResponse.setResponse(response);
			}
			if (incomingRequest.containsKey("VendorResponse_textResponse"))
			{
				String textResponse = (String) incomingRequest.get("VendorResponse_textResponse");
				vendorResponse.setTextResponse(textResponse);
			}
			if (incomingRequest.containsKey("VendorResponse_score"))
			{
				String scoreString = (String) incomingRequest.get("VendorResponse_score");
				if (Utility.isEmpty(scoreString))
				{
					scoreString = "0";
				}
				BigDecimal score = new BigDecimal ( scoreString );
				vendorResponse.setScore(score);
			}
			vendorResponse.setComp_id(comp_id);

			result = vendorResponse;
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
