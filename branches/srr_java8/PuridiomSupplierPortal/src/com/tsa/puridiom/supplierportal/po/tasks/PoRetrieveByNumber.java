/**
 *
 * Created on August 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoice.tasks.InvoiceCreateFromOrderSetup.java
 *
 */
package com.tsa.puridiom.supplierportal.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoRetrieveByNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	userId = (String) incomingRequest.get("userId");
			String	oid = (String) incomingRequest.get("organizationId");
            RegisterUser	user = VendorRegistrationUserManager.getInstance().getUserInCache(oid, userId);
			String vendorId = user.getVendorId();

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = Utility.ckNull((String) incomingRequest.get("PoHeader_poNumber"));
			String releaseNumberString = (String) incomingRequest.get("PoHeader_releaseNumber");
			if (Utility.isEmpty(releaseNumberString))
			{
				releaseNumberString = "0";
			}
			BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );

			String queryString = "select PoHeader.icPoHeader from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.releaseNumber = ? AND PoHeader.lastRevision = 'C' AND PoHeader.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] { poNumber, releaseNumber, vendorId } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.STRING });

			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal icPoHeader = (BigDecimal)resultList.get(0);
				result = String.valueOf(icPoHeader);
			}
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