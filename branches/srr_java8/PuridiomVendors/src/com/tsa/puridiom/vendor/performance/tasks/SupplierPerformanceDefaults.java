/**
 *
 */
package com.tsa.puridiom.vendor.performance.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.SupplierPerformanceRatings;
import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsa.puridiom.entity.PerformanceDetailPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 */
public class SupplierPerformanceDefaults extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			String icHeader = (String)incomingRequest.get("PerformanceDetail_icPoHeader");
			List ratingsList = (List)incomingRequest.get("ratingsList");
			if(ratingsList == null)
			{
				ratingsList = new ArrayList();
			}
			List newRatingsList = new ArrayList();
			String evalQty = propertiesManager.getProperty("SUPPLIER EVAL", "QTY","10");
			int qty = Integer.parseInt(evalQty);

			for(int i = 0; i < qty; i++)
			{
				boolean newRating = false;
				PerformanceDetail perfDetail = null;

				if(ratingsList.size() > i)
				{
					perfDetail = (PerformanceDetail)ratingsList.get(i);
					if(perfDetail == null)
					{
						newRating = true;
					}
				}
				else
				{
					newRating = true;
				}
				if(newRating)
				{
					perfDetail = new PerformanceDetail();
					String evaluationType = propertiesManager.getProperty("SUPPLIER EVAL", String.valueOf(i),"");
					String evaluationWeight = propertiesManager.getProperty("SUPPLIER EVAL W", String.valueOf(i),"0");
					if(Utility.isEmpty(evaluationWeight)){	evaluationWeight = "0";	}
					PerformanceDetailPK pk = new PerformanceDetailPK();
					pk.setEvalSection(evaluationType);
					pk.setEvalNumber("0");
					//pk.setSectionName(evaluationType);
					pk.setIcPoHeader(new BigDecimal(icHeader));
					perfDetail.setComp_id(pk);
					perfDetail.setEvalRating(SupplierPerformanceRatings.NONE);
					perfDetail.setStatus(DocumentStatus.SUP_PERFORMANCE_INCOMPLETE);
					perfDetail.setEvalWeight(new BigDecimal(evaluationWeight));
					perfDetail.setEvalDate(Dates.getDate(Dates.today("", userTimeZone)));
					perfDetail.setIsNew("Y");
				}
				else
				{
					perfDetail.setIsNew("F");
				}
				newRatingsList.add(perfDetail);
			}
			ret = newRatingsList;

			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Default Values could not be set.", e);
		}
		return ret;
	}

}
