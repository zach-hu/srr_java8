/*
 * Created on Oct 25, 2006
 */
package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqQuestion;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.VendorResponse;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqVendorCalculateEvaluationScore extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
		    List rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			List rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			
			if (rfqVendorList == null || rfqQuestionList == null)
			{
			    return rfqVendorList;
			}
			
			for (int iv = 0; iv < rfqVendorList.size(); iv++)
			{
			    RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(iv);
			    BigDecimal totalEvaluationScore = new BigDecimal(0);
			    String evaluationStatus = DocumentStatus.EVALUATION_INCOMPLETE;

			    if (rfqVendor == null)
			    {
			        continue;
			    }
			    
				for (int i = 0; i < rfqQuestionList.size(); i++)
				{
				    RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(i);
				    if (rfqQuestion != null)
				    {
					    Map vendorResponseMap = rfqQuestion.getVendorResponseMap();

				        VendorResponse vendorResponse = (VendorResponse) vendorResponseMap.get(rfqVendor.getVendorId());
				        if (vendorResponse != null)
				        {
					        BigDecimal score = vendorResponse.getScore();
					        
					        BigDecimal weighteScore = score.multiply(rfqQuestion.getWeight());
					        
					        totalEvaluationScore = totalEvaluationScore.add(weighteScore);
					        if (!evaluationStatus.equals(DocumentStatus.EVALUATION_PENDING))
					        {
					            evaluationStatus = DocumentStatus.EVALUATION_COMPLETE;
					        }
				        }
				        else
				        {
				            evaluationStatus = DocumentStatus.EVALUATION_PENDING;
				        }
				    }
			    }
				
				rfqVendor.setTotalScore(totalEvaluationScore);
	            rfqVendor.setEvaluationStatus(evaluationStatus);
				
				rfqVendorList.set(iv, rfqVendor);
			}
			
			this.setStatus(Status.SUCCEEDED);
			result = rfqVendorList;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}
