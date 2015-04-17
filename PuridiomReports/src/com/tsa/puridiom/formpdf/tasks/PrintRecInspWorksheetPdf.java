/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


/**
 * @author Jael
 *
 */

@SuppressWarnings(value = { "unchecked" })
public class PrintRecInspWorksheetPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String userId = (String)incomingRequest.get("userId");
            
            ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
            ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            PoLine poLine = (PoLine)incomingRequest.get("poLine");
            InspectionHeader inspectionHeader = (InspectionHeader)incomingRequest.get("inspectionHeader");
            
            
            Address vendorAddress = (Address)incomingRequest.get("vendorAddress");
            Contact contact = (Contact) incomingRequest.get("contact");
            
            List poLineComments = (List)incomingRequest.get("docCommentList");
            poLine.setDocCommentList(poLineComments);
            
            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(receiptHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("userId", userId);
            parameters.put("entity", receiptHeader);
            
            parameters.put("formType", "receiving-inspection-worksheet.jasper");
            parameters.put("receiptHeader", receiptHeader);
            parameters.put("receiptLine", receiptLine);
            parameters.put("poHeader", poHeader);
            parameters.put("poLine", poLine);
            parameters.put("inspectionHeader", inspectionHeader);
            
            parameters.put("vendorAddress", vendorAddress);
            parameters.put("contact", contact);
            
            parameters.put("reportTitle", "Puridiom Tracking System");
            parameters.put("reportCriteria", "Receiving Worksheet");
            
            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);


			parameters.put("namePdf", ReceiptType.toString(receiptHeader.getReceiptType(), organizationId) + "[" + receiptHeader.getReceiptNumber() + "].pdf");

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
