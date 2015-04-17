/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.report.handler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.ReportUtils;
import com.tsa.puridiom.usermanager.UserManager;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReqEmailHtml
{
    private String organization = "";
    private String subject = "";
    private String templateDir = "";

    private String reportName = "";
    private String messageText = "";

    private String mailId = "";
    private String uid = "";

    public String createMe(String icHeader,String type)
    {
        String reportName = "";
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = null;
            Map newIncomingRequest = new HashMap();
            if (type.equals("REQ"))
            {
                  process = processLoader.loadProcess("req-email-approvals.xml");
                  newIncomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
            }
            else if (type.equals("PO"))
           {
                 process = processLoader.loadProcess("po-email-approvals.xml");
                 newIncomingRequest.put("PoHeader_icPoHeader", icHeader);
            }

            newIncomingRequest.put("webreport", "N");
            newIncomingRequest.put("organizationId", this.getOrganization());
            newIncomingRequest.put("pathToTemplate", this.getTemplateDir());
            newIncomingRequest.put("userId", this.getUid());
            newIncomingRequest.put("mid", this.getMailId());
            process.executeProcess(newIncomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                reportName = (String)newIncomingRequest.get("report");
                if(Utility.isEmpty(reportName))
                {
                    reportName = "";
                }
                this.setReportName(reportName);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return reportName;
    }


    public static void main(String[] args)
    {
        System.out.println("start");
        ReqEmailHtml pdf = new ReqEmailHtml();
        pdf.setOrganization("TEST");
        System.out.println(pdf.createMe("8498527400062","REQ"));
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
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
