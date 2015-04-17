package com.tsa.puridiom.timer;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailLetterPdf;
import com.tsagate.foundation.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetLetterPdfToEmail implements GetPdfToEmail
{
	public List getPdf(Map object)
	{
		SendQueue sendQueue = (SendQueue)object.get("sendQueue");
		String organizationId = (String)object.get("organizationId");
		String pathToTemplate = (String)object.get("pathToTemplate");
		String letter = HiltonUtility.ckNull((String)object.get("letter"));
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
	    			EmailLetterPdf pdf = new EmailLetterPdf();

	            	pdf.setOrganization(organizationId);
	                pdf.setTemplateDir(pathToTemplate);
	                pdf.setMessageText(sendQueue.getMessagetext());
	                tempObj[0] = pdf.createMe(sendQueue.getDocic().toString(), "N", args[i], letter);
	                tempObj[1] = pdf.getMessageText();
	                tempObj[2] = pdf.getEmailTo();
	                pdfs.add(tempObj);
				}
			}
		}
		if(!Utility.isEmpty(sendQueue.getSendto()))
		{
			EmailLetterPdf pdf = new EmailLetterPdf();

        	pdf.setOrganization(organizationId);
            pdf.setTemplateDir(pathToTemplate);
            pdf.setMessageText(sendQueue.getMessagetext());
            Object tempObj[] = new Object[3];
            tempObj[0] = pdf.createMe(sendQueue.getDocic().toString(), "N", "", letter);
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
