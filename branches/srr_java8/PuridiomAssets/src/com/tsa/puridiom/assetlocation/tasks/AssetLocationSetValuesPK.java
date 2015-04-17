package com.tsa.puridiom.assetlocation.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class AssetLocationSetValuesPK
{
	public void setValues(Map incomingRequest, AssetLocation assetlocation ) throws Exception
	{
		try
		{
			String tagNumber = (String ) incomingRequest.get("AssetLocation_tagNumber");
			String sequenceNoString = (String) incomingRequest.get("AssetLocation_sequenceNo");
			BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
			AssetLocationPK comp_id = new AssetLocationPK();
			comp_id.setSequenceNo(sequenceNo);
			comp_id.setTagNumber(tagNumber);
			assetlocation.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}