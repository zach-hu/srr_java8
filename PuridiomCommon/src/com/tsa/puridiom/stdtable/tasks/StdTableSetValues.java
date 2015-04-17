package com.tsa.puridiom.stdtable.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class StdTableSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

	if (Utility.isEmpty(userDateFormat)) {
		userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	}
		try
		{
			StdTablePK comp_id = new StdTablePK();
			StdTable stdTable = (StdTable) incomingRequest.get("stdTable");
			if (stdTable == null)
			{
				stdTable = new StdTable();
			}

			if (incomingRequest.containsKey("StdTable_tableType"))
			{
				String tableType = (String ) incomingRequest.get("StdTable_tableType");
				comp_id.setTableType(tableType);
			}
			if (incomingRequest.containsKey("StdTable_tableKey"))
			{
				String tableKey = (String ) incomingRequest.get("StdTable_tableKey");
				comp_id.setTableKey(tableKey);
			}
			if (incomingRequest.containsKey("StdTable_description"))
			{
				String description = (String ) incomingRequest.get("StdTable_description");
				stdTable.setDescription(description);
			}
			if (incomingRequest.containsKey("StdTable_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("StdTable_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				stdTable.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("StdTable_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("StdTable_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString, userDateFormat);
				stdTable.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("StdTable_status"))
			{
				String status = (String ) incomingRequest.get("StdTable_status");
				stdTable.setStatus(status);
			}
			if (incomingRequest.containsKey("StdTable_owner"))
			{
				String owner = (String ) incomingRequest.get("StdTable_owner");
				stdTable.setOwner(owner);
			}
			if (incomingRequest.containsKey("StdTable_dateChanged"))
			{
				String dateChangedString = (String) incomingRequest.get("StdTable_dateChanged");
				Date dateChanged = Dates.getDate(dateChangedString, userDateFormat);
				stdTable.setDateChanged(dateChanged);
			}
			else
			{
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
				stdTable.setDateChanged(Dates.getDate(Dates.today("", userTimeZone)));
			}
			if (incomingRequest.containsKey("StdTable_tableInd"))
			{
				String tableInd = (String ) incomingRequest.get("StdTable_tableInd");
				stdTable.setOwner(tableInd);
			}
			if (incomingRequest.containsKey("StdTable_tableNum"))
			{
				String tableNumString = (String) incomingRequest.get("StdTable_tableNum");
				if (Utility.isEmpty(tableNumString)){		tableNumString = "0";		}
				BigDecimal tableNum = new BigDecimal ( tableNumString );
				stdTable.setTableNum(tableNum);
			}
			if (incomingRequest.containsKey("StdTable_tableOther"))
			{
				String tableOther = (String ) incomingRequest.get("StdTable_tableOther");
				stdTable.setOwner(tableOther);
			}
			if (incomingRequest.containsKey("StdTable_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("StdTable_udf1Code");
				stdTable.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("StdTable_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("StdTable_udf2Code");
				stdTable.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("StdTable_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("StdTable_udf3Code");
				stdTable.setUdf3Code(udf3Code);
			}

			stdTable.setComp_id(comp_id);

			result = stdTable;
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