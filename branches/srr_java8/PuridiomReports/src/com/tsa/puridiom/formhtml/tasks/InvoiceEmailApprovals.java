/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formhtml.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateMultipleEntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
/**
 * @author
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvoiceEmailApprovals extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
        	 Map incomingRequest = (Map)object;
             InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
             String organizationId = (String)incomingRequest.get("organizationId");
             InvoiceAddress vendorAdrress  = (InvoiceAddress) incomingRequest.get("invoiceAddress");

             Map parameters = new HashMap();
             EntityDataSource ds = new EntityDataSource(invoiceHeader);
             parameters.put("datasource", ds);
             parameters.put("organizationId", organizationId);
             parameters.put("oid", organizationId);
             parameters.put("entity", invoiceHeader);
             parameters.put("InvoiceHeader_invoiceDesc", invoiceHeader.getInvoiceDesc());
             //EntityDataSource shiptoDS = new EntityDataSource(invoiceHeader.getShipToAddress());
             //parameters.put("shipToDS", shiptoDS);
             //EntityDataSource billToDS = new EntityDataSource(invoiceHeader.getBillToAddress());
             //parameters.put("billToDS", billToDS);

             parameters.put("formType", "invoice-email-approvals.jasper");
             //HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(invoiceHeader.getInvoiceLineList());
             //parameters.put("linesDS", itemDS);
             //parameters.put("lines", invoiceHeader.getInvoiceLineList());
             EntityDataSource vendorDS = new EntityDataSource(vendorAdrress);
             parameters.put("vendorDS", vendorDS);

             List accountRollupList = (List) incomingRequest.get("accountRollupList");
             HibernateQueryResultDataSource accountRollupDS = new HibernateQueryResultDataSource(accountRollupList);
             parameters.put("accountRollupDS", accountRollupDS);

             List lines =   (List)   invoiceHeader.getInvoiceLineList() ;
     		 List budgetInfoList = new ArrayList();
             for(int i=0;i< lines.size();i++)
             {
            	 InvoiceLine pol = (InvoiceLine) lines.get(i);
            	 for(int y=0;y< pol.getBudgetList().size() ;y++){
            		 BudgetCenter bc = (BudgetCenter) pol.getBudgetList().get(y);
            		 budgetInfoList.add(bc);
            	 }
             }
             HibernateQueryResultDataSource budgetInfoDS = new HibernateQueryResultDataSource(budgetInfoList);
             parameters.put("budgetInfoDS", budgetInfoDS);

             String webreport = (String)incomingRequest.get("webreport");
             if(Utility.isEmpty(webreport)){		webreport = "Y";	}
             parameters.put("webreport", webreport);
             //parameters.put("namePdf", InvoiceType.toString(invoiceHeader.getInvoiceType(), organizationId) + "[" + invoiceHeader.getInvoiceNumber() + "].html");
             parameters.put("namePdf", "Invoice [" + invoiceHeader.getInvoiceNumber() + "].html");
          //   EntityDataSource poHeader = new EntityDataSource(poHeader);
             //parameters.put("poHeader",poHeader);
             //HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(invoiceHeader.getAccountList());
             //parameters.put("accountsDS", accountsDS);
             List routingList = (List)incomingRequest.get("routingList");
             parameters.put("uid", incomingRequest.get("userId"));
             parameters.put("mid", incomingRequest.get("mid"));
             
             String defaultSiteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
             String siteUrl = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", defaultSiteUrl);
             
             parameters.put("GCS_SITE_URL", siteUrl);
             if(routingList == null)
             {
             	routingList = new ArrayList();
             }
             HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
             parameters.put("routingListDS", routingListDS);
             HibernateQueryResultDataSource approverNotesDS = new HibernateQueryResultDataSource(routingList);
             parameters.put("approverNotesDS", approverNotesDS);
             List docCommentList = invoiceHeader.getDocCommentList();
             List commentsBeforeList = new ArrayList();
             List commentsAfterList = new ArrayList();
             for (int i = 0; i < docCommentList.size(); i++)
             {
                 DocComment docComment = (DocComment) docCommentList.get(i);
                 if (docComment.getCommentPlace().equals("B"))
                 {
                     commentsBeforeList.add(docComment);
                 }
                 else
                 {
                     commentsAfterList.add(docComment);
                 }
             }

             List budgetReviewList = (List)incomingRequest.get("budgetReviewList");
             HibernateMultipleEntityDataSource budgetReviewDS = new HibernateMultipleEntityDataSource(budgetReviewList);
             parameters.put("budgetReviewListDS", budgetReviewDS);
             parameters.put("budgetColumns", (String)incomingRequest.get("budgetColumns"));

             HibernateQueryResultDataSource commentsBeforeDS = new HibernateQueryResultDataSource(commentsBeforeList);
             parameters.put("commentsBeforeDS", commentsBeforeDS);
             HibernateQueryResultDataSource commentsAfterDS = new HibernateQueryResultDataSource(commentsAfterList);
             parameters.put("commentsAfterDS", commentsAfterDS);
              //parameters.put("namePdf", InvoiceType.toString(invoiceHeader.getInvoiceType(), organizationId) + "[" + invoiceHeader.getInvoiceNumber() + "].html");
             parameters.put("namePdf", "Invoice [" + invoiceHeader.getInvoiceNumber() + "].html");
              String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
              parameters.put("imgUrl", imgUrl);
              Log.debug(this, "imgUrl "+ imgUrl);
           //  parameters.put("namePdf",  "report.pdf");
             parameters.put("format", "html");
             ret = JasperReportsHelper.poEmailApp(parameters);
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
