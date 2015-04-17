package com.tsa.puridiom.assetservice.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AssetServiceSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain assetService */
			AssetServicePK comp_id = new AssetServicePK();
			AssetService	originalAssetService = (AssetService) incomingRequest.get("assetService");
			AssetService	assetService = new AssetService();

			comp_id.setTagNumber(originalAssetService.getComp_id().getTagNumber());
			comp_id.setSequenceNo(originalAssetService.getComp_id().getSequenceNo());
			assetService.setServiceCallDate(originalAssetService.getServiceCallDate());
			assetService.setCallInitiatedBy(originalAssetService.getCallInitiatedBy());
			assetService.setDateInitiated(originalAssetService.getDateInitiated());
			assetService.setResponseDate(originalAssetService.getResponseDate());
			assetService.setCompletionDate(originalAssetService.getCompletionDate());
			assetService.setServiceAction(originalAssetService.getServiceAction());
			assetService.setServiceCost(originalAssetService.getServiceCost());
			assetService.setLastChgBy(originalAssetService.getLastChgBy());
			assetService.setDateChanged(originalAssetService.getDateChanged());
			assetService.setComp_id(comp_id);

			incomingRequest.put("assetService", assetService);

			AssetServiceAdd addTask = new AssetServiceAdd();
			assetService = (AssetService) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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