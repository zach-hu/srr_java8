/*
 * Created on January 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
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
public class InvoicePrintApprovedPdf extends Task
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
            InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");

            Log.debug(this, "printing invoice: " + invoiceHeader.getInvoiceNumber());
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(invoiceHeader);
            parameters.put("InvoiceHeader_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
            parameters.put("datasource",ds);
            parameters.put("oid", organizationId.toUpperCase());
            parameters.put("organizationId", organizationId.toUpperCase());

            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);
            parameters.put("formType", "invoice-pdf.jasper");
            List jasperPrintList = new ArrayList();
            Object poPdf[] = {"invoice-pdf.jasper", ds};
            jasperPrintList.add(poPdf);
            //Add attachments
            if(invoiceHeader.getDocAttachmentList().size() > 0)
            {
	            jasperPrintList.addAll(this.getAttchmentPdfs(invoiceHeader.getDocAttachmentList(), organizationId));
	            parameters.put("TCs", "Y");
            }
            parameters.put("jasperPrintList", jasperPrintList);

            InvoiceAddress vendorAddress  = (InvoiceAddress) incomingRequest.get("invoiceAddress");
            EntityDataSource vendorDS = new EntityDataSource(vendorAddress);
            parameters.put("vendorDS", vendorDS);
            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){	webreport = "R";}

            //parameters.put("namePdf", InvoiceType.toString(invoiceHeader.getInvoiceType(), organizationId) + "[" + invoiceHeader.getInvoiceNumber() + "].pdf");
            parameters.put("namePdf", "Invoice [" + invoiceHeader.getInvoiceNumber() + "].pdf");
            parameters.put("webreport", webreport);
//            EntityDataSource poDataDs = new EntityDataSource(poHeader);
//            parameters.put("poData",poDataDs);
            //ret = JasperReportsHelper.printPdf(parameters);
            ret = parameters;
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
	        	int extPos = fileName.lastIndexOf(".");
	        	String extension = "";
	        	if(extPos >= 0)
	        	{
	        		extension = fileName.substring(extPos + 1).toUpperCase();
	        	}
	        	if(("GIF JPG JPEG PNG BMP").indexOf(extension) >= 0)
	        	{
	        		tcsPdf[0] = "img.jasper";
	        		String tempUrl = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-url", "/");
	        		if (tempUrl.lastIndexOf("/") != tempUrl.length()) {
	    				tempUrl = tempUrl + "/";
	    			}
	        		File tmpFile = new File(fileName);
	        		fileName = tempUrl + tmpFile.getName();
	        	}
	        	else if(extension.equalsIgnoreCase("PDF"))
	        	{
	        		tcsPdf[0] = fileName;
	        	}
	        	else if(extension.equalsIgnoreCase("tif"))
	        	{
	        		tcsPdf[0] = "tiff1.jasper";
	        	}

	        	tcsPdf[1] = new TiffDataSource(fileName, organizationId);
	        }
    	}

		return tcsPdf;

    }


}
