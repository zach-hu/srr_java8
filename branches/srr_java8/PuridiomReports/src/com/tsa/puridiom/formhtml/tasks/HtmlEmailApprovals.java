/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formhtml.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HtmlEmailApprovals extends Task
{
	private String organization = "";
	private String subject = "";
	private String templateDir = "";

	private String reportName = "";
	private String messageText = "";

	private String mailId = "";
	private String uid = "";

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String type = (String)incomingRequest.get("type");
			String icHeader = (String)incomingRequest.get("icHeader");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = null;
			Map newIncomingRequest = new HashMap();
			String organizationId = (String)incomingRequest.get("organizationId");
			newIncomingRequest.put("organizationId", organizationId);

			if (type.equals("REQ"))
			{
				  process = processLoader.loadProcess("req-email-approvals.xml");
				  newIncomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
				  newIncomingRequest.put("messagetext", HiltonUtility.ckNull((String) incomingRequest.get("messagetext")));
			}
			else if (type.equals("PO"))
			{
				 process = processLoader.loadProcess("po-email-approvals.xml");
				 newIncomingRequest.put("PoHeader_icPoHeader", icHeader);
			}
			else if (type.equals("RFQ"))
			{
				 process = processLoader.loadProcess("rfq-email-approvals.xml");
				 newIncomingRequest.put("RfqHeader_icRfqHeader", icHeader);
			}
			else if (type.equals("INV"))
			{
				 process = processLoader.loadProcess("inventory-email-approvals.xml");
				 newIncomingRequest.put("InvItem_icHeaderHistory", icHeader);
			}
			else if (type.equals("IVC"))
			{
				 process = processLoader.loadProcess("invoice-email-approvals.xml");
				 newIncomingRequest.put("InvoiceHeader_icIvcHeader", icHeader);
			}
            else if (type.equals("VNE"))
            {
                 process = processLoader.loadProcess("po-email-vendor-evaluation.xml");
                 newIncomingRequest.put("PoHeader_icPoHeader", icHeader);
            }
            else if (type.equals("POA"))
            {
                 process = processLoader.loadProcess("po-email-acknowledged.xml");
                 newIncomingRequest.put("PoHeader_icPoHeader", icHeader);
            }
            else if (type.equals("USR"))
            {
                 process = processLoader.loadProcess("user-email-activation.xml");
            }
            else if (type.equals("UGS"))
            {
                 process = processLoader.loadProcess("user-email-getting-started.xml");
            }

			newIncomingRequest.put("webreport", "N");
			newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
			UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
			String user = "";
			if(userProfile == null)
			{
				user = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
			}
			else
			{
				user = userProfile.getUserId();

				if (type.equals("REQ"))
				{
					newIncomingRequest.put("emailVersion", userProfile.getEmailVersion());
				}
			}
			newIncomingRequest.put("userId", user);
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
			
			String mailId = (String)incomingRequest.get("mailId");
			if(!HiltonUtility.isEmpty(mailId)){
				newIncomingRequest.put("mid", mailId);
			}else if(!HiltonUtility.isEmpty((String)incomingRequest.get("mid"))){
				newIncomingRequest.put("mid",(String)incomingRequest.get("mid"));
			}
			newIncomingRequest.put("fyi", incomingRequest.get("fyi"));
			process.executeProcess(newIncomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				reportName = (String)newIncomingRequest.get("report");
				if(Utility.isEmpty(reportName)){		reportName = "";		}
				this.setReportName(reportName);
			}
			ret = reportName;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Html Emails failed.", e);
		}
		return ret;
	}

	public String createMe(String icHeader,String type)
	{
		String reportName = "";
		try
		{

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
		HtmlEmailApprovals pdf = new HtmlEmailApprovals();
		System.out.println(pdf.createMe("1690130100026","REQ"));
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
