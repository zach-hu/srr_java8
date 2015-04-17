package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqQuestionSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqQuestion */
			RfqQuestionPK comp_id = new RfqQuestionPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqQuestion	originalRfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
			RfqQuestion	rfqQuestion = new RfqQuestion();
			String	newIcQuestion = ukg.getUniqueKey().toString(); 

			comp_id.setIcRfqHeader(new BigDecimal((String) incomingRequest.get("newRfqQuestion_icRfqHeader")));
			comp_id.setIcQuestion(new BigDecimal(newIcQuestion));
			rfqQuestion.setSequence(originalRfqQuestion.getSequence());
			rfqQuestion.setQuestionText(originalRfqQuestion.getQuestionText());
			rfqQuestion.setResponseType(originalRfqQuestion.getResponseType());
			rfqQuestion.setWeight(originalRfqQuestion.getWeight());
			rfqQuestion.setRatingMethod(originalRfqQuestion.getRatingMethod());
			rfqQuestion.setMaxPoints(originalRfqQuestion.getMaxPoints());
			rfqQuestion.setComp_id(comp_id);

			incomingRequest.put("rfqQuestion", rfqQuestion);
			incomingRequest.put("newResponseValue_icQuestion", newIcQuestion);

//			RfqQuestionAdd addTask = new RfqQuestionAdd();
//			rfqQuestion = (RfqQuestion) addTask.executeTask(incomingRequest);
//			this.setStatus(addTask.getStatus()) ;

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
