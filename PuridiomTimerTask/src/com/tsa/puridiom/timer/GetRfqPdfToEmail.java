/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailRfqPdf;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 */
public class GetRfqPdfToEmail implements GetPdfToEmail
{

	/* (non-Javadoc)
	 * @see com.tsa.puridiom.timer.GetPdfToEmail#getPdf(java.util.Map)
	 */
	public List getPdf(Map object)
	{
		SendQueue sendQueue = (SendQueue)object.get("sendQueue");
		String organizationId = (String)object.get("organizationId");
		String pathToTemplate = (String)object.get("pathToTemplate");
		String args[] = sendQueue.getArgs().split(";");
		List rfqPdf = new ArrayList();
		List pdfs = new ArrayList();

		for (int i = 0; i < args.length; i++)
		{
			if(!Utility.isEmpty(args[i]))
			{
				if(!args[i].equalsIgnoreCase("none"))
				{
	    			Object tempObj[] = new Object[3];
	    			EmailRfqPdf pdf = new EmailRfqPdf();

	            	pdf.setOrganization(organizationId);
	                pdf.setTemplateDir(pathToTemplate);
	                pdf.setMessageText(sendQueue.getMessagetext());
	                tempObj[0] = pdf.createMe(sendQueue.getDocic().toString(), "N", args[i]);
	                tempObj[1] = pdf.getMessageText();
	                tempObj[2] = pdf.getEmailTo();
	                pdfs.add(tempObj);
				}
			}
		}
		if(!Utility.isEmpty(sendQueue.getSendto()))
		{
			EmailRfqPdf pdf = new EmailRfqPdf();

        	pdf.setOrganization(organizationId);
            pdf.setTemplateDir(pathToTemplate);
            pdf.setMessageText(sendQueue.getMessagetext());
            Object tempObj[] = new Object[3];
            tempObj[0] = pdf.createMe(sendQueue.getDocic().toString(), "N", "");
            tempObj[1] = pdf.getMessageText();
            tempObj[2] = sendQueue.getSendto();
            pdfs.add(tempObj);
		}

		rfqPdf.add(pdfs);
		rfqPdf.add("");

		System.out.println("RFQ.getPdf done");
    	return rfqPdf;
	}

}
