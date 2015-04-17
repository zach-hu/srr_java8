/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.jasperreports.requisitionline;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

//import datasource.EntityDataSource;
//import datasource.HibernateQueryResultDataSource;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineReport extends Task
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
            
	        Map parameters = new HashMap();
	        parameters.put("reportTitle", "Requisition Line Report");
	        String userId = (String)incomingRequest.get("userId");
	        parameters.put("userId", userId);
	        DBSession session = (DBSession)incomingRequest.get("dbsession");
	        Connection con = session.getSqlConnection();
	        
	        String filePath = path + "requisition-line.jasper";
	        //JasperDesign jasperDesign = JasperManager.loadXmlDesign(filePath);
	        //JasperReport jasperReport = JasperManager.compileReport(jasperDesign);
	        JasperReport jasperReport = JasperManager.loadReport(filePath);
	        JasperPrint jasperPrint = JasperManager.fillReport(jasperReport, parameters, con);
	        
	        
	        //JasperCompileManager.compileReportToFile(filePath);
	       // filePath = path + "requisition-line.jasper";
	        //JasperFillManager.fillReportToFile(filePath, parameters, con);
	        
	        //filePath = path + "requisitionLine.pdf";
	        //JasperExportManager.exportReportToPdfFile(filePath);

	        //You can use JasperPrint to create PDF
	        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
	        String nameIt = ukg.getUniqueKey().toString();
	        filePath =  DictionaryManager.getInstance("host", organizationId).getProperty("reportsOut", "") + nameIt + ".pdf";
	        JasperManager.printReportToPdfFile(jasperPrint, filePath);
	        ret = nameIt + ".pdf";
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
