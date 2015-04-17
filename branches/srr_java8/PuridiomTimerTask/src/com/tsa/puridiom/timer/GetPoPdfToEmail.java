/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.report.handler.EmailPdf;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 */
public class GetPoPdfToEmail implements GetPdfToEmail
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
		EmailPdf pdf = new EmailPdf();
		pdf.setOrganization(organizationId);
    	pdf.setVendorId(HiltonUtility.ckNull((String)sendQueue.getVendorId()));
        pdf.setMessageText(HiltonUtility.ckNull((String)sendQueue.getMessagetext()));
        pdf.setTemplateDir(pathToTemplate);
        String args = HiltonUtility.ckNull((String)sendQueue.getArgs());
        boolean noArgs = true;
        Object tmpFile = null;
        if(!com.tsagate.foundation.utility.Utility.isEmpty(args))
        {
        	Log.debug(this,"create with terms & conditions");
            if(args.indexOf("TCs") > -1)
            {
                String arg1[] = args.split("@@");
                String temp[] = arg1[0].split("=");
                tmpFile = pdf.createMe(sendQueue.getDocic().toString(), temp[1]);
                noArgs = false;
            }
        }
        if(noArgs)
        {
        	Log.debug(this, "normal create");
            tmpFile = pdf.createMe(sendQueue.getDocic().toString());
        }

        pdfObject.add(tmpFile);
        pdfObject.add(pdf.getMessageText());

        return pdfObject;
	}

}
