package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqQuestionSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqQuestionPK comp_id = new RfqQuestionPK();
			RfqQuestion rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
			if (rfqQuestion == null)
			{
				rfqQuestion = new RfqQuestion();
			}

			if (incomingRequest.containsKey("RfqQuestion_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("RfqQuestion_icQuestion"))
			{
				String icQuestionString = (String) incomingRequest.get("RfqQuestion_icQuestion");
				if (Utility.isEmpty(icQuestionString))
				{
					icQuestionString = "0";
				}
				BigDecimal icQuestion = new BigDecimal ( icQuestionString );
				comp_id.setIcQuestion(icQuestion);
			}
			if (incomingRequest.containsKey("RfqQuestion_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("RfqQuestion_sequence");
				if (Utility.isEmpty(sequenceString))
				{
					sequenceString = "0";
				}
				BigDecimal sequence = new BigDecimal ( sequenceString );
				rfqQuestion.setSequence(sequence);
			}
			if (incomingRequest.containsKey("RfqQuestion_questionText"))
			{
				String questionText = (String ) incomingRequest.get("RfqQuestion_questionText");
				rfqQuestion.setQuestionText(questionText);
			}
			if (incomingRequest.containsKey("RfqQuestion_responseType"))
			{
				String responseType = (String ) incomingRequest.get("RfqQuestion_responseType");
				rfqQuestion.setResponseType(responseType);
			}
			if (incomingRequest.containsKey("RfqQuestion_weight"))
			{
				String weightString = (String) incomingRequest.get("RfqQuestion_weight");
				if (Utility.isEmpty(weightString))
				{
					weightString = "1";
				}
				BigDecimal weight = new BigDecimal ( weightString );
				rfqQuestion.setWeight(weight);
			}
			if (incomingRequest.containsKey("RfqQuestion_ratingMethod"))
			{
				String ratingMethod = (String) incomingRequest.get("RfqQuestion_ratingMethod");
				rfqQuestion.setRatingMethod(ratingMethod);
			}
			if (incomingRequest.containsKey("RfqQuestion_maxPoints"))
			{
				String maxPointsString = (String) incomingRequest.get("RfqQuestion_maxPoints");
				if (Utility.isEmpty(maxPointsString))
				{
					maxPointsString = "0";
				}
				BigDecimal maxPoints = new BigDecimal ( maxPointsString );
				rfqQuestion.setMaxPoints(maxPoints);
			}
			rfqQuestion.setComp_id(comp_id);

			result = rfqQuestion;
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
