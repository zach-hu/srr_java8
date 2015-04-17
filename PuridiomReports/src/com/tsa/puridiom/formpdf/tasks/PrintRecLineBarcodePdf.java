/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
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
public class PrintRecLineBarcodePdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
            ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            PoLine poLine = (PoLine)incomingRequest.get("poLine");
//            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(receiptHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", receiptHeader);

            parameters.put("formType", "rec-line-barcode-pdf.jasper");
            parameters.put("receiptHeader", receiptHeader);
            parameters.put("receiptLine", receiptLine);
            parameters.put("poHeader", poHeader);
            parameters.put("poLine", poLine);
            
//            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(receiptHeader.getReceiptLineList());
//            parameters.put("linesDS", itemDS);
//            HibernateQueryResultDataSource itemDSRec = new HibernateQueryResultDataSource(receiptHeader.getReceiptLineList());
//            parameters.put("linesDSRec", itemDSRec);
//            parameters.put("lines", receiptHeader.getReceiptLineList());

//            Address shipToAddress = null;
//            Address vendorAddress = null;
//            String shipToContact = null;
//
//            if(!receiptHeader.getReceiptType().equals("T")){
//            	shipToAddress = poHeader.getShipToAddress();
//                vendorAddress = poHeader.getVendorAddress();
//                shipToContact = poHeader.getShipToContact();
//			}else{
//				shipToAddress = requisitionHeader.getShipToAddress();
//	            vendorAddress = requisitionHeader.getVendorAddress();
//	            shipToContact = requisitionHeader.getShipToContact();
//	            parameters.put("isRequisition","true");
//			}
//            EntityDataSource shiptoDS = new EntityDataSource(shipToAddress);
//            parameters.put("shipToDS", shiptoDS);
//            EntityDataSource vendorDS = new EntityDataSource(vendorAddress);
//            parameters.put("vendorDS", vendorDS);
//
//            parameters.put("poHeader", poHeader);
//            parameters.put("requisitionHeader", requisitionHeader);
//            parameters.put("shipToContact", shipToContact);
//            HibernateQueryResultDataSource attachmentsDS = new HibernateQueryResultDataSource(receiptHeader.getDocAttachmentList());
//            parameters.put("attachmentsDS", attachmentsDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

//            List routingList = (List)incomingRequest.get("routingList");
//            if(routingList == null)
//            {
//            	routingList = new ArrayList();
//            }
//            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
//            parameters.put("routingListDS", routingListDS);

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
