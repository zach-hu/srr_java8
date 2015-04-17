package com.tsa.puridiom.purchasecard.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class PurchaseCardSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain purchaseCard */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			PurchaseCard	originalPurchaseCard = (PurchaseCard) incomingRequest.get("purchaseCard");
			PurchaseCard	purchaseCard = new PurchaseCard();

			purchaseCard.setPcardCode(originalPurchaseCard.getPcardCode());
			purchaseCard.setPcardNumber(originalPurchaseCard.getPcardNumber());
			purchaseCard.setPcardSecureCode(originalPurchaseCard.getPcardSecureCode());
			purchaseCard.setPcardName(originalPurchaseCard.getPcardName());
			purchaseCard.setPcardExpires(originalPurchaseCard.getPcardExpires());
			purchaseCard.setPcardHolder(originalPurchaseCard.getPcardHolder());
			purchaseCard.setDateEntered(originalPurchaseCard.getDateEntered());
			purchaseCard.setDateExpires(originalPurchaseCard.getDateExpires());
			purchaseCard.setStatus(originalPurchaseCard.getStatus());
			purchaseCard.setOwner(originalPurchaseCard.getOwner());

			incomingRequest.put("purchaseCard", purchaseCard);

			PurchaseCardAdd addTask = new PurchaseCardAdd();
			purchaseCard = (PurchaseCard) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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