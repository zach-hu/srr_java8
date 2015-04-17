package com.tsa.puridiom.checklistresponse.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ChecklistResponseSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ChecklistResponsePK comp_id = new ChecklistResponsePK();
			ChecklistResponse checklistResponse = (ChecklistResponse) incomingRequest.get("checklistResponse");
			if (checklistResponse == null)
			{
				checklistResponse = new ChecklistResponse();
			}

			if (incomingRequest.containsKey("ChecklistResponse_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ChecklistResponse_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("ChecklistResponse_icQuestion"))
			{
				String icQuestionString = (String) incomingRequest.get("ChecklistResponse_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				comp_id.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("ChecklistResponse_response"))
			{
				String response = (String) incomingRequest.get("ChecklistResponse_response");
				checklistResponse.setResponse(response);
			}
			if (incomingRequest.containsKey("ChecklistResponse_textResponse"))
			{
				String textResponse = (String) incomingRequest.get("ChecklistResponse_textResponse");
				checklistResponse.setTextResponse(textResponse);
			}
			checklistResponse.setComp_id(comp_id);

			result = checklistResponse;
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