/*
 * Created on January 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
/**
 * @author Renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoicePrintPdf extends Task
{
    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");

            Log.debug(this, "printing invoice: " + invoiceHeader.getInvoiceNumber());
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(invoiceHeader);
            parameters.put("datasource",ds);
            //parameters.put("oid", organizationId.toUpperCase());
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);
            parameters.put("formType", "invoice-pdf.jasper");
//            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(invoiceHeader.getInvoiceLineList());
//            parameters.put("linesDS", itemDS);
//            parameters.put("lines", invoiceHeader.getInvoiceLineList());
//            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(invoiceHeader.getAccountList());
//            parameters.put("accountsDS", accountsDS);
//            HibernateQueryResultDataSource commentsDS = new HibernateQueryResultDataSource(invoiceHeader.getDocCommentList());
//            parameters.put("commentsDS", commentsDS);
            InvoiceAddress vendorAddress  = (InvoiceAddress) incomingRequest.get("invoiceAddress");
            EntityDataSource vendorDS = new EntityDataSource(vendorAddress);
            parameters.put("vendorDS", vendorDS);

            List accountRollupList = (List) incomingRequest.get("accountRollupList");
            HibernateQueryResultDataSource accountRollupDS = new HibernateQueryResultDataSource(accountRollupList);
            parameters.put("accountRollupDS", accountRollupDS);

            List routingList = (List)incomingRequest.get("routingList");
            if(routingList == null)
            {
            	routingList = new ArrayList();
            }
            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("routingListDS", routingListDS);

            List requisitionRoutingList = (List)incomingRequest.get("requisitionRoutingList");
            if(requisitionRoutingList == null){		requisitionRoutingList = new ArrayList();		}
            HibernateQueryResultDataSource reqRoutingListDS = new HibernateQueryResultDataSource(requisitionRoutingList);
            parameters.put("reqRoutingListDS", reqRoutingListDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){	webreport = "Y";}

            //parameters.put("namePdf", InvoiceType.toString(invoiceHeader.getInvoiceType(), organizationId) + "[" + invoiceHeader.getInvoiceNumber() + "].pdf");
            parameters.put("namePdf", "Invoice [" + invoiceHeader.getInvoiceNumber() + "].pdf");
            parameters.put("webreport", webreport);
//            EntityDataSource poDataDs = new EntityDataSource(poHeader);
//            parameters.put("poData",poDataDs);

            if (organizationId.equalsIgnoreCase("akdoc"))
            {
            	if (invoiceHeader.getInvoicePrintedDate() == null)
            	{
            		parameters.put("printDraft", "Y");
            	}
            }

            ret = JasperReportsHelper.printPdf(parameters);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
          this.setStatus(Status.FAILED);
          throw e;
        }
        return ret;

    }
}
