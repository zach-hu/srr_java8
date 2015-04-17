package com.tsa.puridiom.purchasecard.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class PurchaseCardSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PurchaseCard purchaseCard = (PurchaseCard) incomingRequest.get("purchaseCard");
			if (purchaseCard == null)
			{
				purchaseCard = new PurchaseCard();
			}

			if (incomingRequest.containsKey("PurchaseCard_pcardCode"))
			{
				String pcardCode = (String) incomingRequest.get("PurchaseCard_pcardCode");
				purchaseCard.setPcardCode(pcardCode);
			}
			if (incomingRequest.containsKey("PurchaseCard_pcardNumber"))
			{
				String pcardNumber = (String) incomingRequest.get("PurchaseCard_pcardNumber");
				purchaseCard.setPcardNumber(pcardNumber);
			}
			if (incomingRequest.containsKey("PurchaseCard_pcardSecureCode"))
			{
				String pcardSecureCode = (String) incomingRequest.get("PurchaseCard_pcardSecureCode");
				purchaseCard.setPcardSecureCode(pcardSecureCode);
			}
			if (incomingRequest.containsKey("PurchaseCard_pcardHolder"))
			{
				String pcardHolder = (String) incomingRequest.get("PurchaseCard_pcardHolder");
				purchaseCard.setPcardHolder(pcardHolder);
			}
			if (incomingRequest.containsKey("PurchaseCard_pcardExpires"))
			{
				String pcardExpires = (String) incomingRequest.get("PurchaseCard_pcardExpires");
				purchaseCard.setPcardExpires(pcardExpires);
			}
			if (incomingRequest.containsKey("PurchaseCard_pcardName"))
			{
				String pcardName = (String) incomingRequest.get("PurchaseCard_pcardName");
				purchaseCard.setPcardName(pcardName);
			}
			if (incomingRequest.containsKey("PurchaseCard_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("PurchaseCard_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				purchaseCard.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("PurchaseCard_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("PurchaseCard_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				purchaseCard.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("PurchaseCard_status"))
			{
				String status = (String) incomingRequest.get("PurchaseCard_status");
				purchaseCard.setStatus(status);
			}
			if (incomingRequest.containsKey("PurchaseCard_owner"))
			{
				String owner = (String) incomingRequest.get("PurchaseCard_owner");
				purchaseCard.setOwner(owner);
			}

			result = purchaseCard;
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