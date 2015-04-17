package com.tsa.puridiom.requisition.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Matthew
 */
public class RequisitionValidProcurementLevel extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");

			boolean validTraceability = true;
			boolean validProcurementSpec = true;
			boolean validInspectionType = true;
			boolean validInspectionTypeMSR = true;
			boolean validTraceabilityMSR = true;
			boolean requestCat = false;
			requestCat = requestCatValid(header);

			if (lineItems != null)
			{
				for (int i = 0; i < lineItems.size(); i++)
				{
					RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);

					String	level = requisitionLine.getUdf2Code();

					if(requestCat && "P".equalsIgnoreCase(header.getRequisitionType()) && ("LEVEL_1".equalsIgnoreCase(level) || "LEVEL_2".equalsIgnoreCase(level)))
					{
						if (!"INV".equalsIgnoreCase(requisitionLine.getItemSource()) &&  requisitionLine.getCatalogId().indexOf("SRNS") != 0)
						{
							if (!"A".equalsIgnoreCase(requisitionLine.getUdf5Code()) && !"B".equalsIgnoreCase(requisitionLine.getUdf5Code()))
							{
								validTraceability = false;
							}
						}
						if (!"INV".equalsIgnoreCase(requisitionLine.getItemSource()))
						{
							validInspectionType = false;
							if ("RI".equalsIgnoreCase(requisitionLine.getUdf8Code()) || "RIPDT".equalsIgnoreCase(requisitionLine.getUdf8Code()) ||
									"RISI".equalsIgnoreCase(requisitionLine.getUdf8Code()) || "RISIPDT".equalsIgnoreCase(requisitionLine.getUdf8Code()))
							{
								validInspectionType = true;
								continue;
							}
						}
					}

					if(requestCat && "M".equalsIgnoreCase(header.getRequisitionType()) && ( "LEVEL_1".equalsIgnoreCase(level) || "LEVEL_2".equalsIgnoreCase(level)))
					{
						if (!"INV".equalsIgnoreCase(requisitionLine.getItemSource()) && requisitionLine.getCatalogId().indexOf("SRNS") != 0)
						{

							if (!"A".equalsIgnoreCase(requisitionLine.getUdf5Code()) && !"B".equalsIgnoreCase(requisitionLine.getUdf5Code()))
							{
								validTraceabilityMSR = false;
							}
						}
						if (!"INV".equalsIgnoreCase(requisitionLine.getItemSource()))
						{
							validInspectionTypeMSR = false;
							if ("RI".equalsIgnoreCase(requisitionLine.getUdf8Code()) || "RIPDT".equalsIgnoreCase(requisitionLine.getUdf8Code()) ||
									"RISI".equalsIgnoreCase(requisitionLine.getUdf8Code()) || "RISIPDT".equalsIgnoreCase(requisitionLine.getUdf8Code()))
							{
								validInspectionTypeMSR = true;
								continue;
							}

						}
					}

					if("LEVEL_1".equalsIgnoreCase(level))
					{
						if (Utility.isEmpty(requisitionLine.getUdf1Code()))
						{
							validProcurementSpec = false;
						}
					}
				}
			}

			incomingRequest.put("validTraceability", String.valueOf(validTraceability));
			incomingRequest.put("validProcurementSpec", String.valueOf(validProcurementSpec));
			incomingRequest.put("validInspectionType", String.valueOf(validInspectionType));
			incomingRequest.put("validTraceabilityMSR", String.valueOf(validTraceabilityMSR));
			incomingRequest.put("validInspectionTypeMSR", String.valueOf(validInspectionTypeMSR));
		}
		catch(Exception e)
		{
			Log.error(this, "RequisitionValidProcurementLevel error " + e.getMessage());

			throw e;
		}
		return result;
	}

	public boolean requestCatValid(RequisitionHeader header)
	{
		String requestCat = "";
		boolean validate = false;
		requestCat = (HiltonUtility.ckNull(header.getRequestCat()));

		if (requestCat.equalsIgnoreCase("SPA") || requestCat.equalsIgnoreCase("SA") || requestCat.equalsIgnoreCase("SL12") || requestCat.equalsIgnoreCase("SL3"))
		{
			return validate;
		}
		else
		{
			validate = true;
			return validate;
		}
	}
}