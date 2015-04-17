package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.type.Type;

public class AccountValidateCostCenter extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;
		String costCenterEndQValidHeader = "Y";
		String costCenterEndQValidLine   = "Y";

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List accounts = (List)incomingRequest.get("accounts");
			List lineitems = (List)incomingRequest.get("lineitems");

			String query = "from StdTable as StdTable where StdTable.id.tableType = 'AC04' and " +
				"StdTable.id.tableKey = ? and StdTable.status = '02' ";

			if (accounts != null && accounts.size() > 0)
			{
				for (int i=0; i<accounts.size(); i++)
				{
					Account account = (Account)accounts.get(i);
					if (account.getComp_id().getIcLine().compareTo(new BigDecimal(0)) <= 0 &&
						account.getFld2().endsWith("Q"))
					{
						List resultQuery = dbs.query(query, new Object[] {account.getFld4()}, new Type[] {Hibernate.STRING});
						if (!(resultQuery != null && resultQuery.size() > 0))
						{
							costCenterEndQValidHeader = "N";
							break;
						}
					}
				}
			}
			if (lineitems != null && lineitems.size() > 0)
			{
				for (int i=0; i<lineitems.size(); i++)
				{
					List accountList = new ArrayList();
					if (lineitems.get(i) instanceof PoLine)
						accountList = ((PoLine)lineitems.get(i)).getAccountList();
					else if (lineitems.get(i) instanceof RequisitionLine)
						accountList = ((RequisitionLine)lineitems.get(i)).getAccountList();

					for (int j=0; j<accountList.size(); j++)
					{
						Account account = (Account)accountList.get(j);
						if (account.getFld2().endsWith("Q"))
						{
							List resultQuery = dbs.query(query, new Object[] {account.getFld4()}, new Type[] {Hibernate.STRING});
							if (!(resultQuery != null && resultQuery.size() > 0))
							{
								costCenterEndQValidLine = "N";
								break;
							}
						}
					}
				}
			}
			incomingRequest.put("costCenterEndQValidHeader", costCenterEndQValidHeader);
			incomingRequest.put("costCenterEndQValidLine"  , costCenterEndQValidLine);
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}