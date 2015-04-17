package com.tsa.puridiom.vendor.performance.tasks;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsa.puridiom.entity.PerformanceDetailPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.*;

public class PerformanceDetailSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain performanceDetail */
			PerformanceDetailPK comp_id = new PerformanceDetailPK();
            PerformanceDetail	originalPerformanceDetail = (PerformanceDetail) incomingRequest.get("performanceDetail");
            PerformanceDetail	performanceDetail = new PerformanceDetail();
			String	icHeader = (String)incomingRequest.get("newPerformanceDetail_icPoHeader");

            comp_id.setEvalNumber(originalPerformanceDetail.getComp_id().getEvalNumber());
            comp_id.setEvalSection(originalPerformanceDetail.getComp_id().getEvalSection());
            comp_id.setIcPoHeader(new BigDecimal(icHeader));

            performanceDetail.setComp_id(comp_id);
            performanceDetail.setAssignedBy(originalPerformanceDetail.getAssignedBy());
            performanceDetail.setDateApproved(originalPerformanceDetail.getDateApproved());
            performanceDetail.setDateAssigned(originalPerformanceDetail.getDateAssigned());
            performanceDetail.setEvalDate(originalPerformanceDetail.getEvalDate());
            performanceDetail.setEvalName(originalPerformanceDetail.getEvalName());
            performanceDetail.setEvalNotes(originalPerformanceDetail.getEvalNotes());
            performanceDetail.setEvalNotify(originalPerformanceDetail.getEvalNotify());
            performanceDetail.setEvalRating(originalPerformanceDetail.getEvalRating());
            performanceDetail.setEvalSequence(originalPerformanceDetail.getEvalSequence());
            performanceDetail.setEvaluator(originalPerformanceDetail.getEvaluator());
            performanceDetail.setEvalWeight(originalPerformanceDetail.getEvalWeight());
            performanceDetail.setInternalNotes(originalPerformanceDetail.getInternalNotes());
            performanceDetail.setIsNew(originalPerformanceDetail.getIsNew());
            performanceDetail.setStatus(originalPerformanceDetail.getStatus());
            performanceDetail.setTimeAssigned(originalPerformanceDetail.getTimeAssigned());

			incomingRequest.put("performanceDetail", performanceDetail);

            PerformanceDetailAdd addTask = new PerformanceDetailAdd();
            performanceDetail = (PerformanceDetail) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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