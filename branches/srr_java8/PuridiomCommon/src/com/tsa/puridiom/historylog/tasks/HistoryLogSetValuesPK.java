package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;

public class HistoryLogSetValuesPK
{
	public void setValues(Map incomingRequest, HistoryLog historylog ) throws Exception
	{
		try
		{
			String icHistoryString = (String) incomingRequest.get("HistoryLog_icHistory");
			BigDecimal icHistory = new BigDecimal ( icHistoryString );
			historylog.setIcHistory(icHistory);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}