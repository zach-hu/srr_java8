package com.tsa.puridiom.sungard.keyid.tasks;

import com.tsa.puridiom.entity.sungard.KeyId;
import com.tsa.puridiom.entity.sungard.KeyIdPK;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

public class SungardKeyIdSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    KeyIdPK comp_id = new KeyIdPK();
			KeyId keyId = (KeyId) incomingRequest.get("keyId");
			if (keyId == null)
			{
				keyId = new KeyId();
			}

			if (incomingRequest.containsKey("KeyId_tableName"))
			{
				String tableName = (String) incomingRequest.get("KeyId_tableName");
				comp_id.setTableName(tableName);
			}
			if (incomingRequest.containsKey("KeyId_keyRangeLow"))
			{
			    String keyRangeLowString = (String) incomingRequest.get("KeyId_keyRangeLow");
				if (Utility.isEmpty(keyRangeLowString))
				{
				    keyRangeLowString = "0";
				}
				BigDecimal keyRangeLow = new BigDecimal ( keyRangeLowString );
				comp_id.setKeyRangeLow(keyRangeLow);
			}
			if (incomingRequest.containsKey("KeyId_keyRangeHigh"))
			{
			    String keyRangeHighString = (String) incomingRequest.get("KeyId_keyRangeHigh");
				if (Utility.isEmpty(keyRangeHighString))
				{
				    keyRangeHighString = "0";
				}
				BigDecimal keyRangeHigh = new BigDecimal ( keyRangeHighString );
				keyId.setKeyRangeHigh(keyRangeHigh);
			}
			if (incomingRequest.containsKey("KeyId_lastCheckedTo"))
			{
			    String lastCheckedToString = (String) incomingRequest.get("KeyId_lastCheckedTo");
				if (Utility.isEmpty(lastCheckedToString))
				{
				    lastCheckedToString = "0";
				}
				BigDecimal lastCheckedTo = new BigDecimal ( lastCheckedToString );
				keyId.setLastCheckedTo(lastCheckedTo);
			}
			
			keyId.setComp_id(comp_id);

			result = keyId;
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