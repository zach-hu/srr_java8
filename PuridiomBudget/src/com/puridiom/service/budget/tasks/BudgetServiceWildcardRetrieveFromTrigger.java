/*
 * Created on June 14, 2008
 */
package com.puridiom.service.budget.tasks;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Renzo
 */
public class BudgetServiceWildcardRetrieveFromTrigger extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Statement stmt = null;
		ResultSet resultset = null;
		BigDecimal budgetId = new BigDecimal(0);
		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
	        Object budgetID[] = (Object[])incomingRequest.get("budgetId");
	        Object [] args = budgetID;
	        
	        List argList = new ArrayList();
	        
	        argList.add("2008");
	        
	        String parString = "?";
	        
	        for (int i = 0; i < args.length; i++) {
	        	if (HiltonUtility.isEmpty(args[i].toString())) {
	        		parString += ", null";
	        	} else {
	        		parString += ", ?";
	        		
	        		argList.add(args[i]);
	        	}
	        }

	        String sql = "select wildcardsearch(" +
	        		parString +
	        		") as budid  from dual";
	        
	        args = argList.toArray();
	        
	        Integer [] types = new Integer[args.length];
	        for (int i = 0; i < args.length; i++) {
	        	types[i] = Types.VARCHAR;
	        }
	        
	        budgetId = (BigDecimal) dbs.sqlQuery(sql, args, types, BigDecimal.class);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			budgetId = new BigDecimal(0);
			this.setStatus(Status.FAILED);
		}
		finally
		{
			stmt.close();
			resultset.close();
		}

		return budgetId.toString();
	}
}
