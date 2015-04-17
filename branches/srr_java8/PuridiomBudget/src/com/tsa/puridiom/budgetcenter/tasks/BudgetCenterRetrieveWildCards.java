package com.tsa.puridiom.budgetcenter.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class BudgetCenterRetrieveWildCards extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String	sYear = (String) incomingRequest.get("budgetYear");
		String	budgetServiceYear = (String) incomingRequest.get("budgetServiceYear");
        String	udfs[] = (String[])incomingRequest.get("budgetUdfArray");
        
        if (!HiltonUtility.isEmpty(budgetServiceYear))
		{
			sYear = budgetServiceYear;
		}

        String sql = "from BudgetCenter bCenter where bCenter.budgetPeriod = '" + sYear + "'";
		String sWhere = "" ;
		String sOrderBy = " order by bCenter.budgetPeriod" ;

        for (int lx = 0; lx < udfs.length; lx++) {
        	if (Utility.isEmpty(udfs[lx])) break ;
        	if (udfs[lx].startsWith("Fld") || udfs[lx].startsWith("Commodity")) {
			     if(! Utility.isEmpty(sWhere))
			     {
						sWhere = sWhere + " OR bCenter.budget" + (lx + 1) + " LIKE '%*%' ";
			     } else {
						sWhere = sWhere + " bCenter.budget" + (lx + 1) + " LIKE '%*%' ";
				}
				sOrderBy = sOrderBy + ",  bCenter.budget" + (lx + 1) + " desc";
        	}
        }
        if (sWhere.length() > 0) sql = sql + " AND (" + sWhere  + ")";
        sql = sql + sOrderBy + " ";

		List result = dbs.query(sql) ;


		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

	public static void main(String[] args)
	{
		String oid = "dtn07p";
		String udfs[] = new String[16];
		for(int li = 0; li < 15; li++)
		{
			String sUdf = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Udf" + String.valueOf(li + 1),"BUDGET_" + String.valueOf(li + 1) );
			if(Utility.isEmpty(sUdf))	sUdf = "budget_" + String.valueOf(li + 1);
			udfs[li] = sUdf;
		}
		// Commodity
		udfs[15] = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Commodity" ,"BUDGET_COMMODITY" );
		Map incomingRequest = new HashMap();


		String sql = "from BudgetCenter bCenter where bCenter.budgetPeriod = '2008'"; ;
		String sWhere = "" ;
		String sOrderBy = " order by bCenter.budgetPeriod" ;

        for (int lx = 0; lx < udfs.length; lx++) {
        	if (Utility.isEmpty(udfs[lx])) break ;
        	if (udfs[lx].startsWith("Fld") || udfs[lx].startsWith("Commodity")) {
			     if(! Utility.isEmpty(sWhere))
			     {
						sWhere = sWhere + " OR bCenter.budget" + (lx + 1) + " LIKE '%*%' ";
			     } else {
						sWhere = sWhere + " bCenter.budget" + (lx + 1) + " LIKE '%*%' ";
				}
				sOrderBy = sOrderBy + ",  bCenter.budget" + (lx + 1) ;
        	}
        }
        if (sWhere.length() > 0) sql = sql + " AND (" + sWhere  + ")";
        sql = sql + sOrderBy + " desc";
        System.out.println("sql: " + sql);
	}

}