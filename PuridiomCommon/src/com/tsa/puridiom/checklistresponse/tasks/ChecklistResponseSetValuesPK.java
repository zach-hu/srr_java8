package com.tsa.puridiom.checklistresponse.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ChecklistResponseSetValuesPK
{
	public void setValues(Map incomingRequest, ChecklistResponse checklistresponse ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("ChecklistResponse_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icQuestionString = (String) incomingRequest.get("ChecklistResponse_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			ChecklistResponsePK comp_id = new ChecklistResponsePK();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcQuestion(icQuestion);
			checklistresponse.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}