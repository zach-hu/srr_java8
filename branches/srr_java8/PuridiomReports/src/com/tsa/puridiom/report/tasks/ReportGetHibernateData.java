package com.tsa.puridiom.report.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.jasperreports.ReportDates;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class ReportGetHibernateData extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		StringBuffer query = new StringBuffer();
		Object result = null;
		Map incomingRequest = (Map) object;
		try
		{

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
			String sqlWhere = (String) incomingRequest.get("sqlWhere");
			List dateArguments = new ArrayList();

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

			sqlWhere = ReportDates.dateWhereClauseDecoder(sqlWhere, dateArguments, organizationId, userTimeZone, userDateFormat);

			if (Utility.isEmpty(sqlWhere))
			{
				sqlWhere = "";
			}
			String sqlSelect = b.getSqlSelect();

			query.append(sqlSelect);
			query.append(" from " + b.getSqlFrom());
			query.append(" " + sqlWhere);
			Log.debug(this, incomingRequest.get("userId") + " - query: " + query.toString());

			long start = System.currentTimeMillis();
			System.out.println("Report Query: " + query.toString());
			List list = dbs.query(query.toString(), dateArguments.toArray(), b.getMaxRows());
			long end = System.currentTimeMillis();
			Log.error(this, "It took " + ((end - start) / 1000) + " seconds to execute the query: " + query.toString());
			Log.debug(this, incomingRequest.get("userId") + " - dbs.query COMPLETE.");

			result = list;

			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			incomingRequest.put("failed", Boolean.TRUE);
			Log.error(this, "Error executing Report Query: " + query.toString() + " -Exception: " + e.getMessage());
			e.printStackTrace();

			this.setStatus(Status.FAILED);
		}
		return result;
	}
}