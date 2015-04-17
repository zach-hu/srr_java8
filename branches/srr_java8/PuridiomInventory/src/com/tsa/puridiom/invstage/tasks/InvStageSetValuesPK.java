package com.tsa.puridiom.invstage.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvStageSetValuesPK
{
	public void setValues(Map incomingRequest, InvStage invstage ) throws Exception
	{
		try
		{
			String stageId = (String ) incomingRequest.get("InvStage_stageId");
			invstage.setStageId(stageId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}