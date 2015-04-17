/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.systemreport.tasks;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

//import datasource.EntityDataSource;
//import datasource.HibernateQueryResultDataSource;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SystemReportExecute extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = "";
        try
        {
            Map incomingRequest = (Map)object;

            ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");

            String organizationId = (String)incomingRequest.get("organizationId");
            String userId = (String)incomingRequest.get("userId");
            String path = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "");
            DBSession session = (DBSession)incomingRequest.get("dbsession");
            Connection con = session.getSqlConnection();
            BrowseObject reportObject = (BrowseObject)incomingRequest.get("browseObject");

            Map parameters = new HashMap();

            parameters.put("webreport", (String)incomingRequest.get("webreport"));
            parameters.put("isReport", "Y");
            parameters.put("reportPath", path);
            String reportName = (String)incomingRequest.get("reportName");
            parameters.put("filePath", path + reportName);
            parameters.put("organizationId", organizationId);
            parameters.put("reportTitle", reportQueue.getAlias());

            Map reportParameters = new HashMap();
            reportParameters.put("session", session);
            reportParameters.put("organizationId", organizationId);
            reportParameters.put("oid", organizationId);
            reportParameters.put("userId", userId);
            reportParameters.put("reportTitle", reportQueue.getAlias());
            reportParameters.put("reportCriteria", (String) reportQueue.getUserWhereClause(organizationId));
            parameters.put("reportParameters", reportParameters);
            parameters.put("sqlConnection", con);
            List data = (List)incomingRequest.get("datasource");
            int dataSize = data.size();
            int restricted = 0;
            List subData = new ArrayList();
            if(reportName.toLowerCase().indexOf("top") > -1)
            {
            	if(dataSize > 25)
            	{
            		subData = data.subList(0, 25);
            		restricted = 10;
            	}
            	else
            	{
            		subData = data;
            		restricted = dataSize;
            	}
            	parameters.put("datasource", subData);
            }
            else
            {
            	if(dataSize > 10000)
            	{
            		Log.error(this, "Restricting result for report: " + reportName);
            		incomingRequest.put("tooMuchData", "Y");
            		subData = data.subList(0, 10000);
            		parameters.put("datasource", subData);
            		restricted = 10000;
            	}
            	else
            	{
            		Log.error(this, reportName + " Returned: " + data.size());
            		incomingRequest.put("tooMuchData", "N");
            		parameters.put("datasource", incomingRequest.get("datasource"));
            		restricted = dataSize;
            	}
            }
            parameters.put("dataSize", new java.math.BigDecimal(dataSize));
            parameters.put("dataSizeRestricted", new java.math.BigDecimal(restricted));


            List dataList = (List)incomingRequest.get("datasource");
            if(dataList.size() < 1)
            {
            	parameters.put("reportName", "nodata");
            	parameters.put("errorType", "nodata");
            }
            else
            {
            	parameters.put("reportName", (String)incomingRequest.get("reportName"));
            }


            parameters.put("reportObject", reportObject);
            String noParams = (String)incomingRequest.get("noParams");
            if(noParams == null) {      noParams = "N";     }
            if(noParams.equalsIgnoreCase("Y"))
            {
                parameters.put("isDatasource", new Boolean(false));
            }
            else
            {
                parameters.put("isDatasource", new Boolean(true));
                //parameters.put("filters", reportObject.getQueryFilter());
            }

            String format = (String)incomingRequest.get("format");
            if(format == null)
            {
                if(noParams.equalsIgnoreCase("Y"))
                {
                    format = "html";
                }
                else
                {
                    format = "pdf";
                }
            }
            parameters.put("format", format);
            long start = System.currentTimeMillis();
            ret = JasperReportsHelper.runReport(parameters);
            long end = System.currentTimeMillis();
            Log.debug(this, "It took " + ((end - start)/1000) +" seconds to execute the report" + (String)incomingRequest.get("reportName"));

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
