package com.tsa.puridiom.timer;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.properties.DictionaryManager;

public class GetPdfToEmailManager
{
	/**
	 * @param object
	 * 		needs: organizationId - oid
	 * 				   sendQueue - SendQueue
	 * 				   pathToTemplate
	 * @return
	 */
	public static List getPDF(Map object)
	{
		String organizationId = (String)object.get("organizationId");
		String pathToTemplate = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.htmpath", "");
		object.put("pathToTemplate", pathToTemplate);
		SendQueue sendQueue = (SendQueue)object.get("sendQueue");
		GetPdfToEmail getPdf;

		if(sendQueue.getDoctype().equalsIgnoreCase("REQ"))
		{
			getPdf = new GetReqPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("RFQ"))
		{
			getPdf = new GetRfqPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("WRK"))
		{
			getPdf = new GetWrkPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("ITA"))
		{
			getPdf = new GetItaPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("LTA"))
		{
			object.put("letter", "award");
			getPdf = new GetLetterPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("LTR"))
		{
			object.put("letter", "regrets");
			getPdf = new GetLetterPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("REC"))
		{
			getPdf = new GetRecPdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("IVC"))
		{
			getPdf = new GetInvoicePdfToEmail();
		}
		else if(sendQueue.getDoctype().equalsIgnoreCase("DSH"))
		{
			getPdf = new GetDisbursementPdfToEmail();
		}
		else
		{
			getPdf = new GetPoPdfToEmail();
		}
		return getPdf.getPdf(object);
	}
}
