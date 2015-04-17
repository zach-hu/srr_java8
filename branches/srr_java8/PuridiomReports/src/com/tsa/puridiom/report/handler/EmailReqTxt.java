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
public class EmailReqTxt
{
    private String organization = "";
    private String mailId = "";
    private String uid = "";

    public String createMe(String icReqHeader)
    {
        String approvalTextVersion = "";
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("requisition-retrieve-wirelless.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
            incomingRequest.put("webreport", "N");
            incomingRequest.put("organizationId", this.getOrganization());

            incomingRequest.put("userId", this.getUid());
            incomingRequest.put("mid", this.getMailId());

            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
            	approvalTextVersion = (String)incomingRequest.get("approvalTextVersion");
                if(Utility.isEmpty(approvalTextVersion))
                {
                	approvalTextVersion = "";
                }

            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return approvalTextVersion;
    }
    public static void main(String[] args)
    {
        System.out.println("start");
        EmailReqTxt pdf = new EmailReqTxt();
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
