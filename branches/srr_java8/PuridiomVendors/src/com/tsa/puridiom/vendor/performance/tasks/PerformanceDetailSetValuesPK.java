package com.tsa.puridiom.vendor.performance.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsa.puridiom.entity.PerformanceDetailPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PerformanceDetailSetValuesPK extends Task
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
			PerformanceDetailPK pk = new PerformanceDetailPK();


			if (incomingRequest.containsKey("PerformanceDetail_evalNumber"))
			{
				String evalNumber = (String ) incomingRequest.get("PerformanceDetail_evalNumber");
				pk.setEvalNumber(evalNumber);
			}
			if (incomingRequest.containsKey("PerformanceDetail_evalSection"))
			{
				String evalSection = (String ) incomingRequest.get("PerformanceDetail_evalSection");
				pk.setEvalSection(evalSection);
			}

			if (incomingRequest.containsKey("PerformanceDetail_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("PerformanceDetail_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				pk.setIcPoHeader(icPoHeader);
			}
			performanceDetail.setComp_id(pk);
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