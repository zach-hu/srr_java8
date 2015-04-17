package com.tsa.puridiom.assetlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AssetLocationSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			AssetLocationPK comp_id = new AssetLocationPK();
			AssetLocation assetLocation = (AssetLocation) incomingRequest.get("assetLocation");
			if (assetLocation == null)
			{
				assetLocation = new AssetLocation();
			}

			if (incomingRequest.containsKey("AssetLocation_tagNumber"))
			{
				String tagNumber = (String ) incomingRequest.get("AssetLocation_tagNumber");
				comp_id.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("AssetLocation_sequenceNo"))
			{
				String sequenceNoString = (String) incomingRequest.get("AssetLocation_sequenceNo");
				if (Utility.isEmpty(sequenceNoString))
				{
					sequenceNoString = "0";
				}
				BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
				comp_id.setSequenceNo(sequenceNo);
			}
			if (incomingRequest.containsKey("AssetLocation_locationType"))
			{
				String locationType = (String ) incomingRequest.get("AssetLocation_locationType");
				assetLocation.setLocationType(locationType);
			}
			if (incomingRequest.containsKey("AssetLocation_division"))
			{
				String division = (String ) incomingRequest.get("AssetLocation_division");
				assetLocation.setDivision(division);
			}
			if (incomingRequest.containsKey("AssetLocation_department"))
			{
				String department = (String ) incomingRequest.get("AssetLocation_department");
				assetLocation.setDepartment(department);
			}
			if (incomingRequest.containsKey("AssetLocation_facility"))
			{
				String facility = (String ) incomingRequest.get("AssetLocation_facility");
				assetLocation.setFacility(facility);
			}
			if (incomingRequest.containsKey("AssetLocation_building"))
			{
				String building = (String ) incomingRequest.get("AssetLocation_building");
				assetLocation.setBuilding(building);
			}
			if (incomingRequest.containsKey("AssetLocation_room"))
			{
				String room = (String ) incomingRequest.get("AssetLocation_room");
				assetLocation.setRoom(room);
			}
			if (incomingRequest.containsKey("AssetLocation_userId"))
			{
				String userId = (String ) incomingRequest.get("AssetLocation_userId");
				assetLocation.setUserId(userId);
			}
			if (incomingRequest.containsKey("AssetLocation_dateAssigned"))
			{
				String dateAssignedString = (String) incomingRequest.get("AssetLocation_dateAssigned");
				Date dateAssigned = Dates.getDate(dateAssignedString, userDateFormat);
				assetLocation.setDateAssigned(dateAssigned);
			}
			if (incomingRequest.containsKey("AssetLocation_telephone"))
			{
				String telephone = (String ) incomingRequest.get("AssetLocation_telephone");
				assetLocation.setTelephone(telephone);
			}
			if (incomingRequest.containsKey("AssetLocation_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("AssetLocation_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				assetLocation.setDateChanged(dateChanged);
			}
			if (incomingRequest.containsKey("AssetLocation_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("AssetLocation_lastChgBy");
				assetLocation.setLastChgBy(lastChgBy);
			}
			assetLocation.setComp_id(comp_id);

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