package com.tsa.puridiom.recentreceipt.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentReceiptSetValuesPK
{
	public void setValues(Map incomingRequest, RecentReceipt recentreceipt ) throws Exception
	{
		try
		{
			String	receivedBy = (String) incomingRequest.get("RecentReceipt_receivedBy");
			String icRecHeaderString = (String) incomingRequest.get("RecentReceipt_icRecHeader");
			if (Utility.isEmpty(icRecHeaderString))
			{
				icRecHeaderString = "0";
			}
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			
			RecentReceiptPK comp_id = new RecentReceiptPK();
			comp_id.setReceivedBy(receivedBy);
			comp_id.setIcRecHeader(icRecHeader);
			recentreceipt.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}