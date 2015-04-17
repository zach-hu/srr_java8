/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailWrkPdf;

/**
 * @author renzo
 *
 */
public class GetWrkPdfToEmail implements GetPdfToEmail
{

	/* (non-Javadoc)
	 * @see com.tsa.puridiom.timer.GetPdfToEmail#getPdf(java.util.Map)
	 */
	public List getPdf(Map object)
	{
		List pdfObject = new ArrayList();
		SendQueue sendQueue = (SendQueue)object.get("sendQueue");
		String organizationId = (String)object.get("organizationId");
		String pathToTemplate = (String)object.get("pathToTemplate");
		EmailWrkPdf pdf = new EmailWrkPdf();

    	pdf.setOrganization(organizationId);
        pdf.setTemplateDir(pathToTemplate);
        pdf.setPdfRfq_vendorId(sendQueue.getArgs());
        pdfObject.add(pdf.createMe(sendQueue.getDocic().toString()));
        pdfObject.add(pdf.getMessageText());

    	return pdfObject;
	}

}
