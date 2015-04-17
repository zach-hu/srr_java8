package com.tsa.puridiom.assetcost.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class AssetCostSetValuesPK
{
	public void setValues(Map incomingRequest, AssetCost assetcost ) throws Exception
	{
		try
		{
			String tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
			String sequenceNoString = (String) incomingRequest.get("AssetCost_sequenceNo");
			BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
			AssetCostPK comp_id = new AssetCostPK();
			comp_id.setSequenceNo(sequenceNo);
			comp_id.setTagNumber(tagNumber);
			assetcost.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}