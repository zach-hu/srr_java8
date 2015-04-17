package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Map;

public class VendorUpdateApReference extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			if (vendor == null)
			{
				throw new Exception ("Vendor was not found.");
			}

			String apReference = (String) incomingRequest.get("Vendor_apReference");
			if (!HiltonUtility.isEmpty(apReference))
			{
				String originalApReference = HiltonUtility.ckNull(vendor.getApReference());
				if (!apReference.equalsIgnoreCase(originalApReference))
				{
					vendor.setApReference(apReference);

					BigDecimal icHistory = vendor.getIcHistory();
					if (icHistory == null || icHistory.equals(new BigDecimal(0)))
					{
						UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				        vendor.setIcHistory(new BigDecimal(ukg.getUniqueKey().toString()));
					}

					String status = vendor.getStatus();
					if (status == null)
					{
						vendor.setStatus("02");
					}

					DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
					dbs.update(vendor);

					result = vendor;
					incomingRequest.put("apReferenceChanged", "TRUE");
					this.setStatus(dbs.getStatus()) ;
				}
				else
				{
					incomingRequest.put("apReferenceChanged", "FALSE");
					this.setStatus(Status.SUCCEEDED);
				}
			}
			else
			{
				incomingRequest.put("apReferenceChanged", "FALSE");
				this.setStatus(Status.SUCCEEDED);
			}

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}