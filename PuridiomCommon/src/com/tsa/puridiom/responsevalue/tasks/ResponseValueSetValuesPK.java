package com.tsa.puridiom.responsevalue.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class ResponseValueSetValuesPK
{
	public void setValues(Map incomingRequest, ResponseValue responsevalue ) throws Exception
	{
		try
		{
			String icQuestionString = (String) incomingRequest.get("ResponseValue_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			String responseValue = (String ) incomingRequest.get("ResponseValue_responseValue");
			ResponseValuePK comp_id = new ResponseValuePK();
			comp_id.setIcQuestion(icQuestion);
			comp_id.setResponseValue(responseValue);
			responsevalue.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}