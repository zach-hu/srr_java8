package com.tsa.puridiom.stdquestion.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class StdQuestionSetValuesPK
{
	public void setValues(Map incomingRequest, StdQuestion stdquestion ) throws Exception
	{
		try
		{
			String icQuestionString = (String ) incomingRequest.get("StdQuestion_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			stdquestion.setIcQuestion(icQuestion);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}