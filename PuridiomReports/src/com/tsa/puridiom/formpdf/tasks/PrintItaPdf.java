/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.math.BigDecimal;
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
import com.tsa.puridiom.rfq.RfqBidWorksheet;
import com.tsa.puridiom.rfq.worksheet.WorkSheetHeader;
import com.tsa.puridiom.rfq.worksheet.WorkSheetManager;
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
public class PrintItaPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
            String organizationId = (String)incomingRequest.get("organizationId");
            String printTotal = (String)incomingRequest.get("printTotal");

            Map parameters = new HashMap();

            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", rfqHeader);

            parameters.put("formType", "rfq-pdf.jasper");
            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);
        	 EntityDataSource ds = new EntityDataSource(rfqHeader);
             parameters.put("datasource", ds);
             List vendorsRejectedList = (List)incomingRequest.get("vendorsRejectedList");
            parameters.put("linesDS", new HibernateQueryResultDataSource(vendorsRejectedList));
            //parameters.put("vendorsRejectedDS", new HibernateQueryResultDataSource((List)incomingRequest.get("vendorsRejectedList")));

            String vendorToAward = (String)incomingRequest.get("awardedVendor");
            //EntityDataSource vendorDS = new EntityDataSource(incomingRequest.get("vendorToAwardAddress"));
            parameters.put("vendorDS", new HibernateQueryResultDataSource((List)incomingRequest.get("rfqVendorAwardedList")));

            parameters.put("pdfType", "ita");
            parameters.put("numberVendorsRejected", new BigDecimal(vendorsRejectedList.size()));

            String nameMe = RfqType.toString(rfqHeader.getRfqType(), organizationId) + "[" + rfqHeader.getRfqNumber() + "].pdf";
            parameters.put("namePdf", nameMe);
            parameters.put("webreport", webreport);

            parameters.put("printTotal", printTotal);

            ret = JasperReportsHelper.printPdf(parameters);

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
