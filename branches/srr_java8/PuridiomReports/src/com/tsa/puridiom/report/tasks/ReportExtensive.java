package com.tsa.puridiom.report.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.ReportDates;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class ReportExtensive extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		StringBuffer query = new StringBuffer();
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userTimeZone = HiltonUtility.ckNull((String) incomingRequest.get("userTimeZone"));
			int dataMaxSize = Integer.parseInt(PropertiesManager.getInstance(organizationId).getProperty("REPORTS", "SCHEDULEMAXROWS", "10000"));

			String sqlWhere = HiltonUtility.ckNull((String)incomingRequest.get("sqlWhereForCount"));
			if (HiltonUtility.isEmpty(sqlWhere)) {
				sqlWhere = (String)incomingRequest.get("sqlWhere");
			}

			List dateArguments = new ArrayList();

			sqlWhere = ReportDates.dateWhereClauseDecoder(sqlWhere, dateArguments, organizationId, userTimeZone);

			if (Utility.isEmpty(sqlWhere))
			{
				sqlWhere = "";
			}

			query.append("Select count(*)");
			query.append(" from " + b.getSqlFrom());
			query.append(" " + sqlWhere);
			Log.debug(this, incomingRequest.get("userId") + " - query: " + query.toString());

			long start = System.currentTimeMillis();

			List resultList = dbs.query(query.toString(), dateArguments.toArray(), b.getMaxRows());

			long end = System.currentTimeMillis();
			Log.error(this, "It took " + ((end - start) / 1000) + " seconds to execute the query: " + query.toString());
			Log.debug(this, incomingRequest.get("userId") + " - dbs.query COMPLETE.");

			if(resultList == null || resultList.size() < 1)
			{
				result = "N";
			}
			else
			{
				Long numRows = (Long)resultList.get(0);
				int nrows=0;

				if(numRows != null)
				{
					nrows = numRows.intValue();
				}

				if(nrows > dataMaxSize){
					result = "Y";
				}else{
					result = "N";
				}
			}

			this.setStatus(dbs.getStatus());

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			e.printStackTrace();
			Log.error(this, e.getMessage());
			throw e;
		}
		return result;

	}

}
