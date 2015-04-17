/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
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
public class PrintRfqPdfSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
        	String suppliers[] = (String[])incomingRequest.get("suppliers");
        	for (int i = 0; i < suppliers.length; i++)
        	{
				PrintRfqPdf pdfTask = new PrintRfqPdf();
				incomingRequest.put("PdfRfq_vendorId", suppliers[i]);
				pdfTask.executeTask(incomingRequest);
			}
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
