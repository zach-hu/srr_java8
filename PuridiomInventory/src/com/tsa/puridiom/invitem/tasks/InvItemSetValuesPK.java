package com.tsa.puridiom.invitem.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class InvItemSetValuesPK
{
	public void setValues(Map incomingRequest, InvItem invitem )
	{
		String itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
		invitem.setItemNumber(itemNumber);
	}
}