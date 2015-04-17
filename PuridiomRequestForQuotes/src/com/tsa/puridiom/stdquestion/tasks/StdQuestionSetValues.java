package com.tsa.puridiom.stdquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;

public class StdQuestionSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdQuestion stdQuestion = (StdQuestion) incomingRequest.get("stdQuestion");
			if (stdQuestion == null)
			{
				stdQuestion = new StdQuestion();
			}

			if (incomingRequest.containsKey("StdQuestion_icQuestion"))
			{
				String icQuestionString = (String ) incomingRequest.get("StdQuestion_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				stdQuestion.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("StdQuestion_questionTitle"))
			{
				String questionTitle = (String) incomingRequest.get("StdQuestion_questionTitle");
				stdQuestion.setQuestionTitle(questionTitle);
			}
			if (incomingRequest.containsKey("StdQuestion_questionText"))
			{
				String questionText = (String) incomingRequest.get("StdQuestion_questionText");
				stdQuestion.setQuestionText(questionText);
			}
			if (incomingRequest.containsKey("StdQuestion_responseType"))
			{
				String responseType = (String) incomingRequest.get("StdQuestion_responseType");
				stdQuestion.setResponseType(responseType);
			}
			if (incomingRequest.containsKey("StdQuestion_ratingMethod"))
			{
				String ratingMethod = (String) incomingRequest.get("StdQuestion_ratingMethod");
				stdQuestion.setRatingMethod(ratingMethod);
			}
			if (incomingRequest.containsKey("StdQuestion_weight"))
			{
				String weightString = (String) incomingRequest.get("StdQuestion_weight");
				if (Utility.isEmpty(weightString))
				{
					weightString = "1";
				}
				BigDecimal weight = new BigDecimal ( weightString );
				stdQuestion.setWeight(weight);
			}
			if (incomingRequest.containsKey("StdQuestion_maxPoints"))
			{
				String maxPointsString = (String) incomingRequest.get("StdQuestion_maxPoints");
				if (Utility.isEmpty(maxPointsString))
				{
					maxPointsString = "0";
				}
				BigDecimal maxPoints = new BigDecimal ( maxPointsString );
				stdQuestion.setMaxPoints(maxPoints);
			}

			result = stdQuestion;
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
