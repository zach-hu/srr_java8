package com.tsa.puridiom.purchasecard.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PurchaseCardSetValuesPK
{
	public void setValues(Map incomingRequest, PurchaseCard purchasecard ) throws Exception
	{
		try
		{
			String pcardCode = (String ) incomingRequest.get("PurchaseCard_pcardCode");
			purchasecard.setPcardCode(pcardCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}