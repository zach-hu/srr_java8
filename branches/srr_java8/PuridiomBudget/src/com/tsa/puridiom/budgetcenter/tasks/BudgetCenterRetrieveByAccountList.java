package com.tsa.puridiom.budgetcenter.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BudgetCenterRetrieveByAccountList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List resultList = new ArrayList();

		String oid = (String)incomingRequest.get("organizationId");
		String	sYear 	= (String) incomingRequest.get("budgetYear") ;
		if ( HiltonUtility.isEmpty(sYear) )
		{
			sYear = "2008";
		}

		String udfs[] = new String[15];
		//String labelArray[] = new String[15];

		for(int i = 0; i < 15; i++)
		{
			udfs[i] = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Udf" + String.valueOf(i + 1),"BUDGET_" + String.valueOf(i + 1) );
			//labelArray[i] = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Label Udf" + String.valueOf(i + 1),"BUDGET_" + String.valueOf(i + 1) );
		}

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List accountList = (List) incomingRequest.get("accountList");

			if (accountList != null && accountList.size() > 0 )
			{
				for (Iterator it = accountList.iterator(); it.hasNext(); )
		        {
					Account account = (Account) it.next();
					String[] accountFld = new String[15];

					if ( account != null)
					{
						accountFld[0]	= account.getFld1();
						accountFld[1]	= account.getFld2();
						accountFld[2]	= account.getFld3();
						accountFld[3]	= account.getFld4();
						accountFld[4]	= account.getFld5();
						accountFld[5]	= account.getFld6();
						accountFld[6]	= account.getFld7();
						accountFld[7]	= account.getFld8();
						accountFld[8]	= account.getFld9();
						accountFld[9]	= account.getFld10();
						accountFld[10]	= account.getFld11();
						accountFld[11]	= account.getFld12();
						accountFld[12]	= account.getFld13();
						accountFld[13]	= account.getFld14();
						accountFld[14]	= account.getFld15();
					}

					String sql = "from BudgetCenter bc where 1=1 ";
					String sWhere = "" ;
					//String sOrderBy = " order by bCenter.budgetPeriod" ;

					//for (int ib = 0; ib < budgetColumns; ib++)
					for (int i = 0; i < udfs.length; i++)
					{
				       	if (Utility.isEmpty(udfs[i]))
				       	{
				       		break;
				       	}
				       	if (udfs[i].startsWith("Fld") )
				       	{
						   sWhere = sWhere + " AND ( bc.budget" + (i + 1) + " = '" + accountFld[i] + "' OR bc.budget" + (i + 1) + " LIKE '%*%' )";
				       	}
				    }

					sql = sql + sWhere;

					List accountBudgetList = dbs.query(sql) ;

					if (accountBudgetList != null && accountBudgetList.size() > 0 )
					{
						for (Iterator it2 = accountBudgetList.iterator(); it2.hasNext(); )
				        {
							BudgetCenter budgetCenter = (BudgetCenter) it2.next();
							resultList.add(budgetCenter);
				        }
					}
		        }
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}

}