package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.Account;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountExternalCatalogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			Catalog catalog = (Catalog)incomingRequest.get("externalCatalogSetup");
			BigDecimal bd_icHeader = new BigDecimal(0);
			if (catalog != null)
			{
				bd_icHeader = catalog.getIcAccount();
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";			

			List resultList = dbs.query(queryString, new Object[] {bd_icHeader, new BigDecimal(0)}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL});
			if (resultList != null && resultList.size() > 0)
			{
				String userNameUdf3 = ((Account)resultList.get(0)).getFld3();
				incomingRequest.put("userNameUdf3", userNameUdf3);
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}