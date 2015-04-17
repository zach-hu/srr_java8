/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailInvoicePdf;

/**
 * @author renzo
 *
 */
public class GetInvoicePdfToEmail implements GetPdfToEmail
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


		EmailInvoicePdf pdf = new EmailInvoicePdf();
    	pdf.setOrganization(organizationId);
        pdf.setTemplateDir(pathToTemplate);
        pdf.setMessageText(sendQueue.getMessagetext());
        pdfObject.add(pdf.createMe(sendQueue.getDocic().toString()));
        pdfObject.add(pdf.getMessageText());

    	return pdfObject;
	}

}
