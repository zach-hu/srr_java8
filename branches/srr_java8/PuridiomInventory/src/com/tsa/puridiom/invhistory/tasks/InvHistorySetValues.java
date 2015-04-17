package com.tsa.puridiom.invhistory.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class InvHistorySetValues
{
	public void setValues(Map incomingRequest, InvHistory invhistory )
	{
		InvHistoryPK comp_id = new InvHistoryPK();
		if(incomingRequest.containsKey("InvHistory.seqNumber"))
		{
			String seqNumberString = (String) incomingRequest.get("InvHistory.seqNumber");
			BigDecimal seqNumber = new BigDecimal ( seqNumberString );
			comp_id.setSeqNumber(seqNumber);
		}
		if(incomingRequest.containsKey("InvHistory.itemNumber"))
		{
			String itemNumber = (String ) incomingRequest.get("InvHistory.itemNumber");
			comp_id.setItemNumber(itemNumber);
		}
		if(incomingRequest.containsKey("InvHistory.docPrtDate"))
		{
			String docPrtDateString = (String) incomingRequest.get("InvHistory.docPrtDate");
			long ddocPrtDate = java.sql.Date.parse(docPrtDateString);
			Date docPrtDate = new Date(ddocPrtDate);
			invhistory.setDocPrtDate(docPrtDate);
		}
		if(incomingRequest.containsKey("InvHistory.primUser"))
		{
			String primUser = (String ) incomingRequest.get("InvHistory.primUser");
			invhistory.setPrimUser(primUser);
		}
		if(incomingRequest.containsKey("InvHistory.puAppDate"))
		{
			String puAppDateString = (String) incomingRequest.get("InvHistory.puAppDate");
			long dpuAppDate = java.sql.Date.parse(puAppDateString);
			Date puAppDate = new Date(dpuAppDate);
			invhistory.setPuAppDate(puAppDate);
		}
		if(incomingRequest.containsKey("InvHistory.faId"))
		{
			String faId = (String ) incomingRequest.get("InvHistory.faId");
			invhistory.setFaId(faId);
		}
		if(incomingRequest.containsKey("InvHistory.faAppDate"))
		{
			String faAppDateString = (String) incomingRequest.get("InvHistory.faAppDate");
			long dfaAppDate = java.sql.Date.parse(faAppDateString);
			Date faAppDate = new Date(dfaAppDate);
			invhistory.setFaAppDate(faAppDate);
		}

		invhistory.setComp_id(comp_id);
	}
}