package com.tsa.puridiom.assetnote.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class AssetNoteSetValuesPK
{
	public void setValues(Map incomingRequest, AssetNote assetnote ) throws Exception
	{
		try
		{
			String tagNumber = (String ) incomingRequest.get("AssetNote_tagNumber");
			String sequenceNoString = (String) incomingRequest.get("AssetNote_sequenceNo");
			BigDecimal sequenceNo = new BigDecimal ( sequenceNoString );
			AssetNotePK comp_id = new AssetNotePK();
			comp_id.setSequenceNo(sequenceNo);
			comp_id.setTagNumber(tagNumber);
			assetnote.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}