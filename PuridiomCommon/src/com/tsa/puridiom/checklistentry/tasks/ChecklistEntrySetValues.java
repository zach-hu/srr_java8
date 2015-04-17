package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ChecklistEntrySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ChecklistEntry checklistEntry = (ChecklistEntry) incomingRequest.get("checklistEntry");
			if (checklistEntry == null)
			{
				checklistEntry = new ChecklistEntry();
			}

			if (incomingRequest.containsKey("ChecklistEntry_icQuestion"))
			{
				String icQuestionString = (String) incomingRequest.get("ChecklistEntry_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				checklistEntry.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("ChecklistEntry_referenceType"))
			{
				String referenceType = (String) incomingRequest.get("ChecklistEntry_referenceType");
				checklistEntry.setReferenceType(referenceType);
			}
			if (incomingRequest.containsKey("ChecklistEntry_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("ChecklistEntry_sequence");
				if (Utility.isEmpty(sequenceString))
				{
					sequenceString = "0";
				}
				BigDecimal sequence = new BigDecimal ( sequenceString );
				checklistEntry.setSequence(sequence);
			}
			if (incomingRequest.containsKey("ChecklistEntry_questionText"))
			{
				String questionText = (String) incomingRequest.get("ChecklistEntry_questionText");
				checklistEntry.setQuestionText(questionText);
			}
			if (incomingRequest.containsKey("ChecklistEntry_responseType"))
			{
				String responseType = (String) incomingRequest.get("ChecklistEntry_responseType");
				checklistEntry.setResponseType(responseType);
			}

			result = checklistEntry;
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