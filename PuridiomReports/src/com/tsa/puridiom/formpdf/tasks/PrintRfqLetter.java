package com.tsa.puridiom.formpdf.tasks;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PrintRfqLetter extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(rfqHeader, organizationId);
            String PdfRfq_vendorId = (String)incomingRequest.get("PdfRfq_vendorId");
            parameters.put("PdfRfq_vendorId", PdfRfq_vendorId);
            RfqVendor currentVendor = (RfqVendor)incomingRequest.get("currentVendor");
            String contactCode = "001";
            Date dateResponseRecv = null;
            if (currentVendor != null)
            {
            	contactCode = currentVendor.getContactId();
            	dateResponseRecv = currentVendor.getDateResponseRecv();
            }
            else
            {
            	contactCode = "001";
            }

            String letter = HiltonUtility.ckNull((String)incomingRequest.get("letter"));

            String commodities = "";
            if (rfqHeader.getRfqLineList() != null && rfqHeader.getRfqLineList().size() > 0)
            {
            	for (int i = 0; i < rfqHeader.getRfqLineList().size(); i++)
            	{
            		RfqLine rfqLine = (RfqLine)rfqHeader.getRfqLineList().get(i);
            		if (rfqLine != null && !HiltonUtility.isEmpty(rfqLine.getCommodity()))
            		{
            			if (HiltonUtility.isEmpty(commodities)) {
            				commodities = rfqLine.getCommodity();
            			} else {
            				commodities = commodities + ", " + rfqLine.getCommodity();
            			}
            		}
            	}
            }
            parameters.put("RfqLine_commodities", commodities);

            parameters.put("RfqVendor_dateResponseRecv", dateResponseRecv);
            parameters.put("PdfRfq_vendContactCode", contactCode);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", rfqHeader);
            //parameters.put("rddSeeBelow",incomingRequest.get("rddSeeBelow"));
            String typeLetter = " Letter Award";
            if (letter.equalsIgnoreCase("award")) {
            	parameters.put("formType", "rfq-pdf-letter-award.jasper");
            	typeLetter = " Letter Award";
            } else {
            	parameters.put("formType", "rfq-pdf-letter-regrets.jasper");
            	typeLetter = " Letter Regrets";
            }
            EntityDataSource vendorDS = new EntityDataSource(rfqHeader.getVendorAddress(), organizationId);
            parameters.put("vendorDS", vendorDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

            if(HiltonUtility.isEmpty(PdfRfq_vendorId))
            {
            	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + typeLetter + "[" + rfqHeader.getRfqNumber() + "].pdf");
            }
            else
            {
            	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + typeLetter + "[" + rfqHeader.getRfqNumber() + "]-" + PdfRfq_vendorId + ".pdf");
            }

            String stdRfq = RfqType.REQUEST_FOR_PROPOSAL + "-" + RfqType.REQUEST_FOR_INFORMATION + "-" +  RfqType.INVITATION_TO_BID;
            String pdfType = rfqHeader.getRfqType();
            if(stdRfq.indexOf(rfqHeader.getRfqType()) > -1)
            {
            	pdfType = "std";
            }
            parameters.put("pdfType", pdfType);

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
