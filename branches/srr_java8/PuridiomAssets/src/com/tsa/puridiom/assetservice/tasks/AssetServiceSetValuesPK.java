package com.tsa.puridiom.assetservice.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class AssetServiceSetValuesPK
{
	public void setValues(Map incomingRequest, AssetService assetservice ) throws Exception
	{
		try
		{
			String tagNumber = (String ) incomingRequest.get("AssetService_tagNumber");
			String sequenceNoString = (String) incomingRequest.get("AssetService_sequenceNo");
			BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
			AssetServicePK comp_id = new AssetServicePK();
			comp_id.setSequenceNo(sequenceNo);
			comp_id.setTagNumber(tagNumber);
			assetservice.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}