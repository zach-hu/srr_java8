package com.tsa.puridiom.assetservice.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AssetServiceSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String)incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

            AssetServicePK comp_id = new AssetServicePK();
			AssetService assetService = (AssetService) incomingRequest.get("assetService");
			if (assetService == null)
			{
				assetService = new AssetService();
			}

			if (incomingRequest.containsKey("AssetService_tagNumber"))
			{
				String tagNumber = (String ) incomingRequest.get("AssetService_tagNumber");
				comp_id.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("AssetService_sequenceNo"))
			{
				String sequenceNoString = (String) incomingRequest.get("AssetService_sequenceNo");
				if (Utility.isEmpty(sequenceNoString))
				{
					sequenceNoString = "0";
				}
				BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
				comp_id.setSequenceNo(sequenceNo);
			}
			if (incomingRequest.containsKey("AssetService_serviceCallDate"))
			{
				String serviceCallDateString = (String) incomingRequest.get("AssetService_serviceCallDate");
				Date serviceCallDate = Dates.getDate(serviceCallDateString, userDateFormat);
				assetService.setServiceCallDate(serviceCallDate);
			}
			if (incomingRequest.containsKey("AssetService_callInitiatedBy"))
			{
				String callInitiatedBy = (String ) incomingRequest.get("AssetService_callInitiatedBy");
				assetService.setCallInitiatedBy(callInitiatedBy);
			}
			if (incomingRequest.containsKey("AssetService_dateInitiated"))
			{
				String dateInitiatedString = (String) incomingRequest.get("AssetService_dateInitiated");
				Date dateInitiated = Dates.getDate(dateInitiatedString, userDateFormat);
				assetService.setDateInitiated(dateInitiated);
			}
			if (incomingRequest.containsKey("AssetService_responseDate"))
			{
				String responseDateString = (String) incomingRequest.get("AssetService_responseDate");
				Date responseDate = Dates.getDate(responseDateString, userDateFormat);
				assetService.setResponseDate(responseDate);
			}
			if (incomingRequest.containsKey("AssetService_completionDate"))
			{
				String completionDateString = (String) incomingRequest.get("AssetService_completionDate");
				Date completionDate = Dates.getDate(completionDateString, userDateFormat);
				assetService.setCompletionDate(completionDate);
			}
			if (incomingRequest.containsKey("AssetService_serviceAction"))
			{
				String serviceAction = (String ) incomingRequest.get("AssetService_serviceAction");
				assetService.setServiceAction(serviceAction);
			}
			if (incomingRequest.containsKey("AssetService_serviceCost"))
			{
				String serviceCostString = (String) incomingRequest.get("AssetService_serviceCost");
				if (Utility.isEmpty(serviceCostString))
				{
					serviceCostString = "0";
				}
				BigDecimal serviceCost = new BigDecimal ( serviceCostString );
				assetService.setServiceCost(serviceCost);
			}
			if (incomingRequest.containsKey("AssetService_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("AssetService_lastChgBy");
				assetService.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("AssetService_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("AssetService_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				assetService.setDateChanged(dateChanged);
			}
			assetService.setComp_id(comp_id);

			result = assetService;
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