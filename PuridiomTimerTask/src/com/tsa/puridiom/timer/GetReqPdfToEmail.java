/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailReqPdf;

/**
 * @author renzo
 *
 */
public class GetReqPdfToEmail implements GetPdfToEmail
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
		EmailReqPdf pdf = new EmailReqPdf();

    	pdf.setOrganization(organizationId);
    	pdf.setForwardedBy(sendQueue.getOwner());
        pdf.setTemplateDir(pathToTemplate);
        pdf.setMessageText(sendQueue.getMessagetext());
        pdfObject.add(pdf.createMe(sendQueue.getDocic().toString()));
        pdfObject.add(pdf.getMessageText());

    	return pdfObject;
	}

}
