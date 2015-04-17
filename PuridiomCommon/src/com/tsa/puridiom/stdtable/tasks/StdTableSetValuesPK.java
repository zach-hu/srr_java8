package com.tsa.puridiom.stdtable.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class StdTableSetValuesPK
{
	public void setValues(Map incomingRequest, StdTable stdtable ) throws Exception
	{
		try
		{
			String tableType = (String ) incomingRequest.get("StdTable_tableType");
			String tableKey = (String ) incomingRequest.get("StdTable_tableKey");
			StdTablePK comp_id = new StdTablePK();
			comp_id.setTableKey(tableKey);
			comp_id.setTableType(tableType);
			stdtable.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}