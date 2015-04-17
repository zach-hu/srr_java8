package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqQuestionSetValuesPK
{
	public void setValues(Map incomingRequest, RfqQuestion rfqquestion ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icQuestionString = (String) incomingRequest.get("RfqQuestion_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			RfqQuestionPK comp_id = new RfqQuestionPK();
			comp_id.setIcQuestion(icQuestion);
			comp_id.setIcRfqHeader(icRfqHeader);
			rfqquestion.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}