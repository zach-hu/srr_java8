package com.tsa.puridiom.invhistory.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class InvHistorySetValuesPK
{
	public void setValues(Map incomingRequest, InvHistory invhistory )
	{
		String seqNumberString = (String) incomingRequest.get("InvHistory.seqNumber");
		BigDecimal seqNumber = new BigDecimal ( seqNumberString );
		String itemNumber = (String ) incomingRequest.get("InvHistory.itemNumber");
		InvHistoryPK comp_id = new InvHistoryPK();
		comp_id.setItemNumber(itemNumber);
		comp_id.setSeqNumber(seqNumber);
		invhistory.setComp_id(comp_id);
	}
}