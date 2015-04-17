package com.tsa.puridiom.responsevalue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class ResponseValueSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ResponseValuePK comp_id = new ResponseValuePK();
			ResponseValue responseValue = (ResponseValue) incomingRequest.get("responseValue");
			if (responseValue == null)
			{
				responseValue = new ResponseValue();
			}

			if (incomingRequest.containsKey("ResponseValue_icQuestion"))
			{
				String icQuestionString = (String) incomingRequest.get("ResponseValue_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				comp_id.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("ResponseValue_responseValue"))
			{
				String value = (String) incomingRequest.get("ResponseValue_responseValue");
				comp_id.setResponseValue(value);
			}
			if (incomingRequest.containsKey("ResponseValue_responseText"))
			{
				String responseText = (String) incomingRequest.get("ResponseValue_responseText");
				responseValue.setResponseText(responseText);
			}
			if (incomingRequest.containsKey("ResponseValue_weight"))
			{
				String weightString = (String) incomingRequest.get("ResponseValue_weight");
				if (Utility.isEmpty(weightString))
				{
					weightString = "0";
				}
				BigDecimal weight = new BigDecimal ( weightString );
				responseValue.setWeight(weight);
			}
			if (incomingRequest.containsKey("ResponseValue_rating"))
			{
				String ratingString = (String) incomingRequest.get("ResponseValue_rating");
				if (Utility.isEmpty(ratingString))
				{
					ratingString = "0";
				}
				BigDecimal rating = new BigDecimal ( ratingString );
				responseValue.setRating(rating);
			}
			responseValue.setComp_id(comp_id);

			result = responseValue;
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
