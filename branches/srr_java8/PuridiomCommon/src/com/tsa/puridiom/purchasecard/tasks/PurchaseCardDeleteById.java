package com.tsa.puridiom.purchasecard.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class PurchaseCardDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		PurchaseCard purchaseCard = (PurchaseCard)incomingRequest.get("purchaseCard");
		if(purchaseCard == null)
		{
			purchaseCard = new PurchaseCard();
		}
		PurchaseCardSetValuesPK setValues = new PurchaseCardSetValuesPK();
		setValues.setValues(incomingRequest, purchaseCard);
		dbs.delete(purchaseCard);
		this.setStatus(dbs.getStatus()) ;
		return purchaseCard ;
	}

}