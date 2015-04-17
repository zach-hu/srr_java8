/*
 * Created on June 27, 2007
 */
package com.tsa.puridiom.vendor.performance.tasks;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class PerformanceDetailSaveasList extends Task
{

	/**
	 * executeTask
	 * <p>This method takes a PerformanceDetail List from incoming request(performanceDetailList)</p>
	 * <p> and calls performancedetail-saveas-by-id process for each.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List performanceDetailList = (List)incomingRequest.get("performanceDetailList");

		for (int row = 0; row < performanceDetailList.size(); row++)
		{
            PerformanceDetail performanceDetail = (PerformanceDetail) performanceDetailList.get(row);

			incomingRequest.put("performanceDetail", performanceDetail);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("performancedetail-saveas-by-id.xml") ;

			process.executeProcess(incomingRequest);

			if (process.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("PerformanceDetailSaveAs process failed (performancedetail-saveas-by-id.xml).");
			}

            performanceDetail = (PerformanceDetail) incomingRequest.get("performanceDetail");
            performanceDetailList.set(row, performanceDetail);

			this.setStatus(Status.SUCCEEDED);
		}
		if(performanceDetailList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return performanceDetailList;
	}

}
