package com.tsa.puridiom.assetnote.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.property.PropertiesManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AssetNoteSetValues extends Task
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

			AssetNotePK comp_id = new AssetNotePK();
			AssetNote assetNote = (AssetNote) incomingRequest.get("assetNote");
			if (assetNote == null)
			{
				assetNote = new AssetNote();
			}

			if (incomingRequest.containsKey("AssetNote_tagNumber"))
			{
				String tagNumber = (String ) incomingRequest.get("AssetNote_tagNumber");
				comp_id.setTagNumber(tagNumber);
			}
			if (incomingRequest.containsKey("AssetNote_sequenceNo"))
			{
				String sequenceNoString = (String) incomingRequest.get("AssetNote_sequenceNo");
				if (Utility.isEmpty(sequenceNoString))
				{
					sequenceNoString = "0";
				}
				BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
				comp_id.setSequenceNo(sequenceNo);
			}
			if (incomingRequest.containsKey("AssetNote_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("AssetNote_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				assetNote.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("AssetNote_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("AssetNote_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				assetNote.setDateChanged(dateChanged);
			}
			if (incomingRequest.containsKey("AssetNote_userId"))
			{
				String userId = (String ) incomingRequest.get("AssetNote_userId");
				assetNote.setUserId(userId);
			}
			if (incomingRequest.containsKey("AssetNote_stdText"))
			{
				String stdText = (String ) incomingRequest.get("AssetNote_stdText");
				assetNote.setStdText(stdText);
			}
			if (incomingRequest.containsKey("AssetNote_lastChgBy"))
			{
				String userId = (String ) incomingRequest.get("AssetNote_lastChgBy");
				assetNote.setLastChgBy(userId);
			}
			assetNote.setComp_id(comp_id);

			result = assetNote;
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