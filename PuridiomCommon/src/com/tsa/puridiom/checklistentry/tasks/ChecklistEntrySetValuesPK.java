package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ChecklistEntrySetValuesPK
{
	public void setValues(Map incomingRequest, ChecklistEntry checklistentry ) throws Exception
	{
		try
		{
			String icQuestionString = (String) incomingRequest.get("ChecklistEntry_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			checklistentry.setIcQuestion(icQuestion);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}