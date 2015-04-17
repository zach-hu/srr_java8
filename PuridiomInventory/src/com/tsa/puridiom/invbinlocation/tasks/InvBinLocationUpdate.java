package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvBinLocationUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String userId = (String) incomingRequest.get("userId") ;
		String disbType = (String) incomingRequest.get("DisbHeader_disbType") ;
		ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine") ;

		try
		{
			InvBinLocation invBinLocation = (InvBinLocation)incomingRequest.get("invBinLocation");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icInvReturn = (String)incomingRequest.get("InvReturn_icInvReturn") ;
			if (invBinLocation == null)
			{
			//	throw new Exception ("InvBinLocation was not found.");
			} else {
				invBinLocation.setOwner(userId) ;
				if (icInvReturn != null) {
					invBinLocation.setUdf5Code("RETURN") ;
				} else if (disbType != null) {
					if (disbType.equals("T")) {
						invBinLocation.setUdf5Code("DSB-XFER") ;
					} else {
						invBinLocation.setUdf5Code("DSB-INV") ;
					}
				} else if (receiptLine != null) {
					invBinLocation.setUdf5Code("RECEIPT") ;
				} else {
					invBinLocation.setUdf5Code("ADJUSTMENT") ;
				}

				dbs.getSession().merge(invBinLocation) ;
//				dbs.getSession().saveOrUpdateCopy(invBinLocation) ;
//				dbs.update(invBinLocation);
			}
			result = invBinLocation;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
