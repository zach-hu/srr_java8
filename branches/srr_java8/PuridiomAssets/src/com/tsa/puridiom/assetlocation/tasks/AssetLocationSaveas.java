package com.tsa.puridiom.assetlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AssetLocationSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain assetLocation */
			AssetLocationPK comp_id = new AssetLocationPK();
			AssetLocation	originalAssetLocation = (AssetLocation) incomingRequest.get("assetLocation");
			AssetLocation	assetLocation = new AssetLocation();

			comp_id.setTagNumber(originalAssetLocation.getComp_id().getTagNumber());
			comp_id.setSequenceNo(originalAssetLocation.getComp_id().getSequenceNo());
			assetLocation.setLocationType(originalAssetLocation.getLocationType());
			assetLocation.setDivision(originalAssetLocation.getDivision());
			assetLocation.setDepartment(originalAssetLocation.getDepartment());
			assetLocation.setFacility(originalAssetLocation.getFacility());
			assetLocation.setBuilding(originalAssetLocation.getBuilding());
			assetLocation.setRoom(originalAssetLocation.getRoom());
			assetLocation.setUserId(originalAssetLocation.getUserId());
			assetLocation.setDateAssigned(originalAssetLocation.getDateAssigned());
			assetLocation.setDateChanged(originalAssetLocation.getDateChanged());
			assetLocation.setTelephone(originalAssetLocation.getTelephone());
			assetLocation.setComp_id(comp_id);

			incomingRequest.put("assetLocation", assetLocation);

			AssetLocationAdd addTask = new AssetLocationAdd();
			assetLocation = (AssetLocation) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = assetLocation;
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