/*
 * Created on January 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;

import com.tsa.puridiom.MergePdfs;
import com.tsa.puridiom.common.documents.InvoiceType;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.reports.datasource.TiffDataSource;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
/**
 * @author Renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoicePrintAllPdfApprovedPdf extends Task
{
	public Object executeTask(Object object) throws Exception
    {
		Map incomingRequest = (Map)object;
        Object ret = null;
        try
        {
        	Map parameters = new HashMap();
            parameters.put("webreport", "Y");
            parameters.put("format", "pdf");
            parameters.put("organizationId", incomingRequest.get("organizationId"));
            String outName = JasperReportsHelper.nameReport(parameters);
            String outFile = JasperReportsHelper.fileNameReport(parameters, outName );
            ret = JasperReportsHelper.nameWebReport(parameters, outName);
            List reportList = (List)incomingRequest.get("reportList");
        	MergePdfs.merge(outFile, reportList);
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
          this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }


    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTaskOld(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Map parameters = new HashMap();
            parameters.put("webreport", "Y");
            parameters.put("format", "pdf");
            parameters.put("organizationId", incomingRequest.get("organizationId"));
            String fileName = JasperReportsHelper.nameReport(parameters);
            List reportList = (List)incomingRequest.get("reportList");
            ret= JasperReportsHelper.selectRunner("pdf", reportList, fileName, parameters);

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
