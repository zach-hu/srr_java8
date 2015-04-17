/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.DateRanges;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintReportPdf extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String userId = (String)incomingRequest.get("userId");
            String path = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "");
            DBSession session = (DBSession)incomingRequest.get("dbsession");
            Connection con = session.getSqlConnection();
            BrowseObject reportObject = (BrowseObject)incomingRequest.get("browseObject");
            ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");
            //String reportCriteria = HiltonUtility.ckNull((String) reportQueue.getUserWhereClause(organizationId));
            String reportCriteria = "";
            String[] filtertxt = (String[]) incomingRequest.get("filter_txt");
            String[] filter_operator = (String[]) incomingRequest.get("filter_operator");            
            String[] filter_label  = (String[]) incomingRequest.get("filter_label");
            
            if (filtertxt != null && filter_operator != null && filter_label != null) {
            	for(int index = 0 ; index < filter_label.length ; index++){
            		if(!HiltonUtility.isEmpty(filtertxt[index]))
            		{	
            			reportCriteria += filter_label[index] + " " + filter_operator[index] + " " 
            						   +  DateRanges.toString(filtertxt[index], organizationId) + "\n";
            		}
            	}
			} 


            Map parameters = new HashMap();

            parameters.put("webreport", (String)incomingRequest.get("webreport"));
            parameters.put("isReport", "Y");
            parameters.put("reportPath", path);
            String reportName = (String)incomingRequest.get("reportName");
            parameters.put("reportName", (String)incomingRequest.get("reportName"));
            parameters.put("filePath", path + reportName);
            parameters.put("organizationId", organizationId);
            parameters.put("reportTitle", reportQueue.getAlias());

            Map reportParameters = new HashMap();
            reportParameters.put("organizationId", organizationId);
            reportParameters.put("oid", organizationId);
            reportParameters.put("userId", userId);
            reportParameters.put("reportTitle", reportQueue.getAlias());
            reportParameters.put("reportCriteria", reportCriteria);

            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("userId", userId);
            parameters.put("reportTitle", reportQueue.getAlias());
            parameters.put("reportCriteria", (String) reportQueue.getUserWhereClause(organizationId));

            parameters.put("reportParameters", reportParameters);
            parameters.put("sqlConnection", con);
            parameters.put("reportObject", reportObject);

            String format = (String)incomingRequest.get("format");
            if(format.equalsIgnoreCase("html"))
            {
            	reportName = reportName + "-html.jasper";
            }
            else if(format.equalsIgnoreCase("csv"))
            {
            	reportName = reportName + "-csv.jasper";
            }
            else if(format.equalsIgnoreCase("xls"))
            {
            	reportName = reportName + "-xls.jasper";
            }
            else
            {
                reportName = reportName + ".jasper";
            }
            parameters.put("fileName",reportName);

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource((List)incomingRequest.get("datasource"));

            parameters.put("datasource", itemDS);
            parameters.put("namePdf", "100000");

            parameters.put("format", format);
            long start = System.currentTimeMillis();
            ret = JasperReportsHelper.printReportPdf(parameters);
            long end = System.currentTimeMillis();
            Log.error(this, "It took " + ((end - start)/1000) +" seconds to execute the report" + (String)incomingRequest.get("reportName"));

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
            this.setStatus(Status.FAILED);

        }
        return ret;
    }
}
