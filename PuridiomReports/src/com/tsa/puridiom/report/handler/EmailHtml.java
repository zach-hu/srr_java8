/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.report.handler;

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
public class EmailHtml
{
    private String organization = "";
    private String subject = "";
    private String templateDir = "";

    private String reportName = "";
    private String messageText = "";

    private String mailId = "";
    private String uid = "";

    public String createMe(String icPoHeader)
    {
        String reportName = "";
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("po-email-approvals.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("PoHeader_icPoHeader", icPoHeader);
            incomingRequest.put("webreport", "N");
            incomingRequest.put("organizationId", this.getOrganization());
            incomingRequest.put("pathToTemplate", this.getTemplateDir());
            incomingRequest.put("userId", this.getUid());
            incomingRequest.put("mid", this.getMailId());
            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                reportName = (String)incomingRequest.get("report");
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
        EmailHtml pdf = new EmailHtml();
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
