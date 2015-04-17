package com.tsa.puridiom.reportuser.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ReportUser;

public class ReportUserSetValuesPK
{
	public void setValues(Map incomingRequest, ReportUser reportUser ) throws Exception
	{
		try
		{
			String s_icReportUser = (String) incomingRequest.get("ReportQueue_icReportUser");
			BigDecimal icReportUser = new BigDecimal(s_icReportUser);
			reportUser.setIcReportUser(icReportUser);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}