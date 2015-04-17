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
import net.sf.jasperreports.engine.JasperPrint;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.TiffDataSource;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
/**
 * @author Renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoicePrintAllJasperApprovedPdf extends Task
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
            Map parameters = (Map)incomingRequest.get("pdfParameters");
            String organizationId = (String)incomingRequest.get("organizationId");
            List jasperPrintList = (List)parameters.get("jasperPrintList");
            List reportList = new ArrayList();
            for(int i = 0; i < jasperPrintList.size(); i++)
            {
                Object reportParms[] = (Object[])jasperPrintList.get(i);
                String fileName = (String)reportParms[0];
                if(fileName.toLowerCase().indexOf(".pdf") < 0)
                {
                	JasperPrint jasperPrint = JasperReportsHelper.fill((String)reportParms[0], parameters, organizationId, (JRDataSource)reportParms[1]);
                	fileName = JasperReportsHelper.nameReport(parameters);
                	fileName= (String)JasperReportsHelper.selectRunner("pdf", jasperPrint, fileName, parameters);
                }
                reportList.add(fileName);
            }
            ret = reportList;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
          this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;

    }

    public List getAttchmentPdfs(List attchList, String organizationId) throws Exception
	{

    	List tmpList = new ArrayList();
    	if(attchList != null)
        {
        	if(attchList.size() > 0)
        	{
            	for(int i = 0; i < attchList.size(); i++)
            	{
            		DocAttachment attachment = (DocAttachment)attchList.get(i);
            		if(attachment.getDocPrint().equalsIgnoreCase("Y"))
            		{
	            		tmpList.add(this.getAttachmentName(attachment, organizationId));
            		}
            	}
        	}
        }

    	return tmpList;
	}
    public Object getAttachmentName(DocAttachment attachment, String organizationId) throws Exception
    {
    	Object tcsPdf[] = new Object[2];
    	Log.debug(this, "INVOICE PDF, " + "getAttachmentName");
    	if(attachment.getDocPrint().equalsIgnoreCase("Y"))
    	{
	    	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	        PuridiomProcess process = processLoader.loadProcess("document-file-copy-to-temp.xml");
	        Map incomingRequest = new HashMap();
	        incomingRequest.put("docAttachment", attachment);
	    	incomingRequest.put("organizationId", organizationId);
	        process.executeProcess(incomingRequest);

	        if (process.getStatus() == Status.SUCCEEDED)
	        {
	        	String fileName = (String)incomingRequest.get("DocAttachment_docFilename");
	        	tcsPdf[0] = "tiff1.jasper";
	        	tcsPdf[1] = new TiffDataSource(fileName, organizationId);
	        }
    	}

		return tcsPdf;

    }


}
