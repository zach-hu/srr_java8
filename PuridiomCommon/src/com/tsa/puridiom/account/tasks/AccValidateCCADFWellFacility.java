package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.property.PropertiesManager;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.type.Type;

public class AccValidateCCADFWellFacility extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;
		String isCCADFWellFacilityValidLine = "N";

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List lineitems = (List)incomingRequest.get("lineitems");

			String organizationId = (String)incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			String userLocale = HiltonUtility.ckNull((String)incomingRequest.get("userLocale"));

			String ac02Type = propertiesManager.getProperty("ACCOUNTS", "AC02 TYPE", "AC02");
			String ac04Type = propertiesManager.getProperty("ACCOUNTS", "AC04 TYPE", "AC04");
			String ac05Type = propertiesManager.getProperty("ACCOUNTS", "AC05 TYPE", "AC05");
			if (userLocale.equalsIgnoreCase("US")) {
				ac02Type = "AC02";
				ac04Type = "AC04";
				ac05Type = "AC05";
			}

			String queryAC04 = "from StdTable as StdTable where StdTable.id.tableType = '" + ac04Type + "' and " +
				"StdTable.id.tableKey = ? and StdTable.status = '02' ";

			String queryAC02 = "from StdTable as StdTable where StdTable.id.tableType = '" + ac02Type + "' and " +
			"StdTable.id.tableKey = ? and StdTable.status = '02' ";

			String queryAC05 = "from StdTable as StdTable where StdTable.id.tableType = '" + ac05Type + "' and " +
			"StdTable.id.tableKey = ? and StdTable.status = '02' ";

			if (lineitems != null && lineitems.size() > 0)
			{
				int lineItemPassValidation = 0;
				for (int i=0; i<lineitems.size(); i++)
				{

					List accountList = new ArrayList();
					if (lineitems.get(i) instanceof PoLine)
						accountList = ((PoLine)lineitems.get(i)).getAccountList();
					else if (lineitems.get(i) instanceof RequisitionLine)
						accountList = ((RequisitionLine)lineitems.get(i)).getAccountList();
					else if (lineitems.get(i) instanceof DisbLine)
						accountList = ((DisbLine)lineitems.get(i)).getAccountList();
					if(accountList != null && accountList.size() > 0)
					{
						int accPassValidation=0;
						for (int j=0; j<accountList.size(); j++)
						{
							Account account = (Account)accountList.get(j);

							List resultQueryAC02 = dbs.query(queryAC02, new Object[] {account.getFld2()}, new Type[] {Hibernate.STRING});
							List resultQueryAC04 = dbs.query(queryAC04, new Object[] {account.getFld4()}, new Type[] {Hibernate.STRING});
							List resultQueryAC05 = dbs.query(queryAC05, new Object[] {account.getFld5()}, new Type[] {Hibernate.STRING});

							if (resultQueryAC02 != null && resultQueryAC02.size() > 0)
							{
								if (resultQueryAC04 != null && resultQueryAC04.size() > 0) {
									accPassValidation++;
								}
							}
							else if ((resultQueryAC04 != null && resultQueryAC04.size() > 0) || (resultQueryAC05 != null && resultQueryAC05.size() > 0))
							{
								accPassValidation++;
							}

							/*if (((resultQueryAC02 != null && resultQueryAC02.size() > 0) && (resultQueryAC04 != null && resultQueryAC04.size() > 0)) ||
								(resultQueryAC05 != null && resultQueryAC05.size() > 0))
							{
								accPassValidation++;
							}*/
						}

						if(accPassValidation == accountList.size() )
						{
							lineItemPassValidation++;
						}
					}else{
						lineItemPassValidation++;
					}

				}
				if(lineItemPassValidation==lineitems.size())
				{
					isCCADFWellFacilityValidLine = "Y";
				}

			}else
			{
				isCCADFWellFacilityValidLine = "Y";
			}

			incomingRequest.put("isCCADFWellFacilityValidLine", isCCADFWellFacilityValidLine);
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