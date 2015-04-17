/*
 * Created on July 18, 2008
 */
package com.tsa.puridiom.account.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class AccountRetrieveFldDescription extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     *   Retrieves the description of each account segment for all account records in accountList
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String code = (String) incomingRequest.get("accountCode");
            String type = (String) incomingRequest.get("accountFld");
            String description = "";

            if (!Utility.isEmpty(code)) {
            	ResultSet rs = null;
                String sql = "select DESCRIPTION from STD_TABLE where TABLE_KEY = '" + code + "' and TABLE_TYPE = 'AC" + type + "'";
                Object [] args = {code, type};
                Integer [] types = {Types.VARCHAR, Types.VARCHAR};
                // Execute the Query to retrieve description
                try {
                    description = (String) dbs.sqlQuery(sql, args, types, String.class);
                    Log.debug(this, "DBSession.executeSqlQuery(String " + sql +") ResultSet = " + rs.toString());
                }
                catch (Exception e) {
                    Log.error(this, "SQLException ERROR in - DBSession.executeSqlQuery(String " + sql +") during executeQuery: " + e.toString());
                    this.setStatus(Status.FAILED) ;
                } finally {
                	if (rs != null) {
                		rs.close();
                	}
                }
            }

            result = description;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Accounts could not be retrieved! ", e);
		}

		return result;
	}
}