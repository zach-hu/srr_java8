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
import com.tsa.puridiom.entity.InvoiceHeader;
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
public class EmailInvoicePdf
{
    private String organization = "";
    private String subject = "";
    private String templateDir = "";

    private String reportName = "";
    private String messageText = "";

    public Object createMe(String icIvcHeader)
    {
        return this.createMe(icIvcHeader, "N");
    }

    public Object createMe(String icIvcHeader, String TCs)
    {
    	Log.debug(this, "INV PDF, " + "createMessage: ic:" + icIvcHeader );
        String reportName = "";
        Object ret = null;
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("invoice-pdf.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("InvoiceHeader_icIvcHeader", icIvcHeader);
            incomingRequest.put("webreport", "N");
            incomingRequest.put("organizationId", this.getOrganization());
            incomingRequest.put("pathToTemplate", this.getTemplateDir());
            incomingRequest.put("notes", this.getMessageText());
            incomingRequest.put("TCs", TCs);
            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
                reportName = (String)incomingRequest.get("report");
                if(Utility.isEmpty(reportName))
                {
                    reportName = "";
                }
                this.setReportName(reportName);
                Log.debug(this, "INVOICE PDF, " + "createMessage reportName:" + this.getReportName());
                this.setMessageText((String)incomingRequest.get("messageText"));
                InvoiceHeader invHeader =  (InvoiceHeader)incomingRequest.get("invoiceHeader");
                ret = this.getReturnObject(invHeader.getDocAttachmentList());
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        Log.debug(this, "INVOICE PDF, " + "done");
        return ret;
    }

    public Object getReturnObject(List attchList)
    {
    	Object ret = null;
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

    	return ret;
    }
//    public Object getTIFFasPDF(String filename)
//    {
//    	Image img = null;
//    	try
//    	{
//
//	    	SeekableStream stream = new FileSeekableStream(filename);
//	    	ParameterBlock params = new ParameterBlock();
//	    	params.add(stream);
//	//    	 Specify to TIFF decoder to decode images as they are and
//	//    	 not to convert unsigned short images to byte images.
//	    	TIFFDecodeParam decodeParam = new TIFFDecodeParam();
//	    	decodeParam.setDecodePaletteAsShorts(true);
//	//    	 Store the decode parameters in a RenderingHints to be sent
//	//    	 to the operation registry, and eventually to the TIFF decoder.
//	    	RenderingHints hints = new RenderingHints(JAI.KEY_TILE_DECODING_PARAM,decodeParam);
//	//    	 Create an operator to decode the TIFF file.
//	    	RenderedOp image1 = JAI.create("tiff", params, hints);
//	//    	 Find out the first image's data type.
//	    	int dataType = image1.getSampleModel().getDataType();
//	    	RenderedOp image2 = null;
//	    	if (dataType == DataBuffer.TYPE_BYTE)
//	    	{
//	    		//    	 Display the byte image as it is.
//		    	Log.debug(this, "TIFF image is type byte.");
//		    	image2 = image1;
//	    	}
//	    	else if (dataType == DataBuffer.TYPE_USHORT)
//	    	{
//	    		//    	 Convert the unsigned short image to byte image.
//	    		Log.debug(this, "TIFF image is type ushort.");
//	    		//    	 	Setup a standard window-level lookup table. */
//	    		byte[] tableData = new byte[0x10000];
//		    	for (int i = 0; i < 0x10000; i++)
//		    	{
//		    		tableData[i] = (byte)(i >> 8);
//		    	}
//				//    	 Create a LookupTableJAI object to be used with the
//				//    	 "lookup" operator.
//		    	LookupTableJAI table = new LookupTableJAI(tableData);
//		    	//    	 Create an operator to lookup image1.
//		    	image2 = JAI.create("lookup", image1, table);
//	    	}
//	    	else
//	    	{
//	    		Log.debug(this, "TIFF image is type " + dataType +", and will not be displayed.");
//	    	}
//	//    	 Attach image2 to a scrolling panel to be displayed.
//	    	img = new BufferedImage(image2.getColorModel(),image2.copyData(), false, new Hashtable());
//    	}
//    	catch (Exception e) {
//    		e.printStackTrace();
//			// TODO: handle exception
//		}
//    	return img;
//    }
    public String getAttachmentName(DocAttachment attachment)
    {
    	Log.debug(this, "INVOICE PDF, " + "getAttachmentName");
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
