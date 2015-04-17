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

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.documentfile.tasks.DocumentFileCopyToTemp;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.SendQueue;
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
public class EmailReqPdf
{
    private String organization = "";
    private String subject = "";
    private String forwardedBy = "";
    private String templateDir = "";

    private String reportName = "";
    private String messageText = "";

    public Object createMe(String icReqHeader)
    {
        return this.createMe(icReqHeader, "N");
    }

    public Object createMe(String icReqHeader, String TCs)
    {
        String reportName = "";
        Object ret = null;
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("emailreqpdf.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
            incomingRequest.put("webreport", "N");
            incomingRequest.put("organizationId", this.getOrganization());
            incomingRequest.put("forwardedBy", this.getForwardedBy());
            incomingRequest.put("pathToTemplate", this.getTemplateDir());
            incomingRequest.put("notes", this.getMessageText());
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
                RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
                List attchList = requisitionHeader.getDocAttachmentList();
                if(attchList != null)
                {
                	if(attchList.size() > 0)
                	{
	                	List tmpList = new ArrayList();
	                	for(int i = 0; i < attchList.size(); i++)
	                	{
	                		DocAttachment attachment = (DocAttachment)attchList.get(i);
	                		if(attachment.getDocPrint().equalsIgnoreCase("Y"))
	                		{
		                		//attachements[i] = DictionaryManager.getInstance("host", this.getOrganization()).getProperty("internal-document-path") + File.separator + attachment.getDocFilename();
		                		tmpList.add(this.getAttachmentName(attachment));
	                		}
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
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return ret;
    }

    public String getAttachmentName(DocAttachment attachment)
    {
    	Log.debug(this, "REQ PDF, " + "getAttachmentName");
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
			addSendQueueNotificationAttachmentsMissing(incomingRequest, fileName);
			Log.error(this, "Error file " + fileName + " was not found");
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

	public String getForwardedBy()
	{
		return forwardedBy;
	}

	public void setForwardedBy(String forwardedBy)
	{
		this.forwardedBy = forwardedBy;
	}
	
	public void addSendQueueNotificationAttachmentsMissing(Object object, String fileName)
	{
		try
		{
			Map incomingRequest = (Map) object;
			
			String organizationId = (String)incomingRequest.get("organizationId");
			SendQueue sendqueue = (SendQueue)incomingRequest.get("sendQueue");
			DocAttachment docAttachment =(DocAttachment) incomingRequest.get("docAttachment");
			String docIc = "";
			String docTitle = "";
			String docSource = "";
			String docFileName = "";
			
			if(docAttachment!=null)
			{
				docIc = docAttachment.getComp_id().getDocIc().toString();
				docTitle = docAttachment.getDocTitle();
				docSource = docAttachment.getDocSource();
				docFileName = docAttachment.getDocFilename();
			
				String subject =  organizationId + " - Alert adding attachment "  + docTitle +  " to the email " + sendqueue.getQueueid() ;
				String messageText = "An error ocurred to adding attachment " + docTitle +  " to the email. \n" +
								 " DocIc 	 : " + docIc + "\n" +
							 	 " File Name :"  + docFileName + "\n" + 
							 	 " Title     :"  + docTitle + "\n" ;
			
				messageText = messageText + "\n The internal-document-path [" + fileName + "] does not have read access.";
			
				Map newIncomingRequest = new HashMap();
			
				if( sendqueue!=null )
				{
					newIncomingRequest.put("SendQueue_doctype", sendqueue.getDoctype());			
					newIncomingRequest.put("SendQueue_docic", sendqueue.getDocic());
					newIncomingRequest.put("SendQueue_subject", subject);
					newIncomingRequest.put("SendQueue_messagetext", messageText);
					newIncomingRequest.put("SendQueue_owner", sendqueue.getOwner());
					newIncomingRequest.put("SendQueue_sendfromtype", "E");
					newIncomingRequest.put("SendQueue_sendfrom", "support@puridiom.com");
					newIncomingRequest.put("SendQueue_sendtotype", "E");
					newIncomingRequest.put("SendQueue_sendto", "support@puridiom.com");
					newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

				}
				else
				{
					newIncomingRequest.put("SendQueue_doctype", docSource);			
					newIncomingRequest.put("SendQueue_docic", docIc);
					newIncomingRequest.put("SendQueue_subject", subject);
					newIncomingRequest.put("SendQueue_messagetext", messageText);
					newIncomingRequest.put("SendQueue_owner", "Puridiom");
					newIncomingRequest.put("SendQueue_sendfromtype", "E");
					newIncomingRequest.put("SendQueue_sendfrom", "support@puridiom.com");
					newIncomingRequest.put("SendQueue_sendtotype", "E");
					newIncomingRequest.put("SendQueue_sendto", "support@puridiom.com");
					newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);				
				}
						

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

				process.executeProcess(newIncomingRequest);
				
				if(process.getStatus() == Status.SUCCEEDED ){
					Log.debug(this, "An emials was inserted on sendqueue " );
				}
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
