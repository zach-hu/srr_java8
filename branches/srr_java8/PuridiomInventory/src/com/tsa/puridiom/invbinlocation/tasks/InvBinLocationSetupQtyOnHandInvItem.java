package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvBinLocationSetupQtyOnHandInvItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;
		String inventoryFlag = "";
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvItem invItem = (InvItem)incomingRequest.get("invItem");
		List invBinLocationList = (List)incomingRequest.get("invBinLocationList");
		BigDecimal qtyOnHandInvBinLocation = new BigDecimal(0);
		if(HiltonUtility.ckNull(invBinLocationList) != null)
		{
			for(int ibl = 0 ; ibl < invBinLocationList.size(); ibl++)
			{
				InvBinLocation invBinLocation = (InvBinLocation)invBinLocationList.get(ibl);
				qtyOnHandInvBinLocation = qtyOnHandInvBinLocation.add(invBinLocation.getQtyOnHand());
			}
		}
		invItem.setQtyOnHandInvBinLocation(qtyOnHandInvBinLocation);
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}