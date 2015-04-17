package com.tsa.puridiom.responsevalue.tasks;

import com.tsa.puridiom.entity.ResponseValue;
import com.tsa.puridiom.entity.ResponseValuePK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.Map;

public class ResponseValueSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain responseValue */
			ResponseValuePK comp_id = new ResponseValuePK();
			ResponseValue	originalResponseValue = (ResponseValue) incomingRequest.get("responseValue");
			ResponseValue	responseValue = new ResponseValue();
			String	newIcQuestion = (String) incomingRequest.get("newResponseValue_icQuestion");
			
			comp_id.setIcQuestion(new BigDecimal(newIcQuestion));
			comp_id.setResponseValue(originalResponseValue.getComp_id().getResponseValue());
			responseValue.setResponseText(originalResponseValue.getResponseText());
			responseValue.setWeight(originalResponseValue.getWeight());
			responseValue.setRating(originalResponseValue.getRating());
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
