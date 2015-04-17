/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.report.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.documentfile.tasks.DocumentFileCopyToTemp;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.rfq.tasks.RfqSupplierEmailUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmailItaPdf
{
    private String organization = "";
    private String subject = "";
    private String templateDir = "";

    private String reportName = "";
    private String messageText = "";
    private String PdfRfq_vendorId = "";
    private String emailTo;

    public Object createMe(String icRfqHeader)
    {
        return this.createMe(icRfqHeader, "N");
    }

    public Object createMe(String icRfqHeader, String TCs)
    {
    	Log.debug(this, "PDF createMe, " + icRfqHeader);
        String reportName = "";
        Object ret = null;
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("emailitapdf.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("RfqHeader_icRfqHeader", icRfqHeader);
            incomingRequest.put("webreport", "N");
            incomingRequest.put("organizationId", this.getOrganization());
            incomingRequest.put("pathToTemplate", this.getTemplateDir());
            //incomingRequest.put("PdfRfq_vendorId", this.getPdfRfq_vendorId());
            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                reportName = (String)incomingRequest.get("report");
                if(Utility.isEmpty(reportName))
                {
                    reportName = "";
                }
                this.setReportName(reportName);
                this.setMessageText((String)incomingRequest.get("messageText"));
                RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
                List attchList = rfqHeader.getDocAttachmentList();
                if(attchList != null)
                {
                	if(attchList.size() > 0)
                	{
	                	List tmpList = new ArrayList();
	                	for(int i = 0; i < attchList.size(); i++)
	                	{
	                		DocAttachment attachment = (DocAttachment)attchList.get(i);
	                		//attachements[i] = DictionaryManager.getInstance("host", this.getOrganization()).getProperty("internal-document-path") + File.separator + attachment.getDocFilename();
	                		tmpList.add(this.getAttachmentName(attachment));
	                	}
	                	tmpList.add(this.getReportName());
	                	Object attachements[] = tmpList.toArray();
	                	ret = attachements;
                	}
                	else
                	{
                		ret = this.getReportName();
                	}
                }
                else
                {
                	ret = this.getReportName();
                }
                this.setSendTo();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return ret;
    }

    public void setSendTo()
    {
    	String vendorIdArray[] = this.getPdfRfq_vendorId().split(";");
    	RfqSupplierEmailUtility emailUtility = new RfqSupplierEmailUtility();
    	Map incomingRequest = new HashMap();
        incomingRequest.put("webreport", "N");
        incomingRequest.put("organizationId", this.getOrganization());
        incomingRequest.put("pathToTemplate", this.getTemplateDir());
        String email = "";

    	for (int i = 0; i < vendorIdArray.length; i++)
    	{
    		incomingRequest.put("PdfRfq_vendorId", vendorIdArray[i]);
    		email = email = ";" +emailUtility.getToEmail(incomingRequest);
		}
    	this.setEmailTo(email);
    }

    public String getAttachmentName(DocAttachment attachment)
    {
    	Log.debug(this, "PO PDF, " + "getAttachmentName");
    	DocumentFileCopyToTemp doccopy = new DocumentFileCopyToTemp();
    	Map incomingRequest = new HashMap();
    	incomingRequest.put("docAttachment", attachment);
    	incomingRequest.put("organizationId", this.getOrganization());
    	String fileName = "";
		try
		{
			fileName = (String)doccopy.executeTask(incomingRequest);
			if(doccopy.getStatus() != Status.SUCCEEDED)
	    	{
	    		fileName = (String)incomingRequest.get("DocAttachment_docFilename");
	    	}
		}
		catch (Exception e)
		{
			fileName = DictionaryManager.getInstance("host", this.getOrganization()).getProperty("internal-document-path", "/") + File.separator + attachment.getDocFilename();
			e.printStackTrace();
		}
		return fileName;

    }
    public static void main(String[] args)
    {
        System.out.println("start");
        EmailPdf pdf = new EmailPdf();
        System.out.println(pdf.createMe("1559715900012"));
        System.out.println("done");
    }
    public String getOrganization()
    {
        return organization;
    }
    public void setOrganization(String organization)
    {
        this.organization = organization;
    }
    public String getSubject()
    {
        return subject;
    }
    public String getTemplateDir()
    {
        return templateDir;
    }
    public void setTemplateDir(String templateDir)
    {
        this.templateDir = templateDir;
    }
    public String getMessageText()
    {
        return messageText;
    }
    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }
    public String getReportName()
    {
        return reportName;
    }
    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

	public String getPdfRfq_vendorId() {
		return PdfRfq_vendorId;
	}

	public void setPdfRfq_vendorId(String pdfRfq_vendorId) {
		PdfRfq_vendorId = pdfRfq_vendorId;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
}
