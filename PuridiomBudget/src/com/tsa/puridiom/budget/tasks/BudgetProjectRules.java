package com.tsa.puridiom.budget.tasks;


import com.tsa.puridiom.entity.BudgetCenter;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.List;
import java.util.Map;

public class BudgetProjectRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";
		String status = "succeeded";
		String budget2 = "0";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	    	BudgetCenter bc = (BudgetCenter) incomingRequest.get("budgetCenter");

        	if( bc!=null )
        	{
    			budget2 = bc.getBudget2();
        	}


			String queryString = "";
			List resultList = null;

			queryString = "from BudgetCenter as BudgetCenter where " + this.getSql(budget2) ;

				resultList = dbs.query(queryString) ;
				if (resultList == null || resultList.size() < 1)
				{
					status="failed";
				}

			incomingRequest.put("statusProjectWildcards", status);

        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at BudgetProjectRules", e);
		}
		return failed;
    }

	public String getSql(String cadena){

		String sqlWhere = "1=0";

		for(int i = 0 ; i < cadena.length() ; i++ )
		{
			sqlWhere = sqlWhere + " OR BudgetCenter.budget2 = '" + cadena.substring(0,i) + "*'" ;
		}
		return sqlWhere;
	}	
}