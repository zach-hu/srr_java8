package com.tsa.puridiom.vendor.performance.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PerformanceDetailSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PerformanceDetail performanceDetail = (PerformanceDetail) incomingRequest.get("performanceDetail");
			if (performanceDetail == null)
			{
				performanceDetail = new PerformanceDetail();
			}

			if (incomingRequest.containsKey("PerformanceDetail_evalName"))
			{
				String evalName = (String ) incomingRequest.get("PerformanceDetail_evalName");
				performanceDetail.setEvalName(evalName);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalRating"))
			{
				String sevalRating = (String ) incomingRequest.get("PerformanceDetail_evalRating");
				if (Utility.isEmpty(sevalRating))
				{
					sevalRating = "0";
				}
				BigDecimal evalRating = new BigDecimal ( sevalRating );
				performanceDetail.setEvalRating(evalRating);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalDate"))
			{
				String dateString = (String) incomingRequest.get("PerformanceDetail_evalDate");
				if(!Utility.isEmpty(dateString))
				{
					Date evalDate = Dates.getDate(dateString);
					performanceDetail.setEvalDate(evalDate);
				}
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalNotes"))
			{
				String evalNotes = (String ) incomingRequest.get("PerformanceDetail_evalNotes");
				performanceDetail.setEvalNotes(evalNotes);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalSequence"))
			{
				String evalSequenceString = (String) incomingRequest.get("PerformanceDetail_evalSequence");
				if (Utility.isEmpty(evalSequenceString))
				{
					evalSequenceString = "0";
				}
				BigDecimal evalSequence = new BigDecimal ( evalSequenceString );
				performanceDetail.setEvalSequence(evalSequence);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalNotify"))
			{
				String evalNotify = (String ) incomingRequest.get("PerformanceDetail_evalNotify");
				performanceDetail.setEvalNotify(evalNotify);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalWeight"))
			{
				String evalWeightString = (String) incomingRequest.get("PerformanceDetail_evalWeight");
				if (Utility.isEmpty(evalWeightString))
				{
					evalWeightString = "0";
				}
				BigDecimal evalWeight = new BigDecimal ( evalWeightString );
				performanceDetail.setEvalWeight(evalWeight);
			}
			if (incomingRequest.containsKey("PerformanceDetail_dateEntered"))
			{
				String dateApprovedString = (String) incomingRequest.get("PerformanceDetail_dateApproved");
				Date dateApproved = Dates.getDate(dateApprovedString);
				performanceDetail.setDateApproved(dateApproved);
			}
			if (incomingRequest.containsKey("PerformanceDetail_dateAssigned"))
			{
				String dateAssigned = (String) incomingRequest.get("PerformanceDetail_dateAssigned");
				performanceDetail.setDateAssigned(dateAssigned);
			}
			if (incomingRequest.containsKey("PerformanceDetail_timeAssigned"))
			{
				String timeAssigned = (String) incomingRequest.get("PerformanceDetail_timeAssigned");
				performanceDetail.setTimeAssigned(timeAssigned);
			}
			if (incomingRequest.containsKey("PerformanceDetail_assignedBy"))
			{
				String assignedBy = (String) incomingRequest.get("PerformanceDetail_assignedBy");
				performanceDetail.setAssignedBy(assignedBy);
			}
			if (incomingRequest.containsKey("PerformanceDetail_status"))
			{
				String status = (String ) incomingRequest.get("PerformanceDetail_status");
				performanceDetail.setStatus(status);
			}
			if (incomingRequest.containsKey("PerformanceDetail_internalNotes"))
			{
				String internalNotes = (String ) incomingRequest.get("PerformanceDetail_internalNotes");
				performanceDetail.setInternalNotes(internalNotes);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evaluator"))
			{
				String evaluator = (String ) incomingRequest.get("PerformanceDetail_evaluator");
				performanceDetail.setEvaluator(evaluator);
			}
			result = performanceDetail;
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