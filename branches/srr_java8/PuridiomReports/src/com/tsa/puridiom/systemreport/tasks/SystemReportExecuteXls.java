/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.systemreport.tasks;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
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
public class SystemReportExecuteXls extends Task
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
            
            String organizationId = (String)incomingRequest.get("organizationId");
            String path = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "");
            String userId = (String)incomingRequest.get("userId");
            DBSession session = (DBSession)incomingRequest.get("dbsession");
            Connection con = session.getSqlConnection();
            BrowseObject reportObject = (BrowseObject)incomingRequest.get("browseObject");
            
	        Map parameters = new HashMap();
	        parameters.put("reportName", (String)incomingRequest.get("reportName"));
	        parameters.put("filePath", path + (String)incomingRequest.get("reportName"));
	        parameters.put("organizationId", organizationId);
	        parameters.put("reportParameters", new HashMap());
	        parameters.put("sqlConnection", con);
	        parameters.put("datasource", incomingRequest.get("datasource"));
	        
	        parameters.put("reportObject", reportObject);
	        parameters.put("isDatasource", new Boolean(true));
	        parameters.put("filters", reportObject.getQueryFilter());
	        parameters.put("format","xls");
	        ret = JasperReportsHelper.runReport(parameters);
	        
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
