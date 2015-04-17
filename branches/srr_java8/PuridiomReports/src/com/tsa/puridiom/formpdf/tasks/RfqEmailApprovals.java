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

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateMultipleEntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RfqEmailApprovals extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
            Log.debug(this, "printing rfq for rfq: " + rfqHeader.getRfqNumber());

            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(rfqHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId.toUpperCase());
            Log.debug(this, "printing rfq pdf [organization : "+ organizationId + "]");
            parameters.put("entity", rfqHeader);
            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);

            parameters.put("formType", "rfq-email-approvals.jasper");

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(rfqHeader.getRfqLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", rfqHeader.getRfqLineList());

            List lines =   (List)   rfqHeader.getRfqLineList();
    		List budgetInfoList = new ArrayList();
    		
            /*for(int i=0;i< lines.size();i++)
            {
           	 	RfqLine rfqLine = (RfqLine) lines.get(i);
           	 
           	 	if (rfqLine.getBudgetList() != null)
				{
					for (int y = 0; y < rfqLine.getBudgetList().size(); y++)
					{
						BudgetCenter bc = (BudgetCenter) rfqLine.getBudgetList().get(y);
						budgetInfoList.add(bc);
					}
				}
            }*/
            
            HibernateQueryResultDataSource budgetInfoDS = new HibernateQueryResultDataSource(budgetInfoList);
            parameters.put("budgetInfoDS", budgetInfoDS);

            EntityDataSource shiptoDS = new EntityDataSource(rfqHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(rfqHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            EntityDataSource billToDS = new EntityDataSource(rfqHeader.getBillToAddress());
            parameters.put("billToDS", billToDS);
            //HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(rfqHeader.getAccountList());
            //parameters.put("accountsDS", accountsDS);
            //HibernateQueryResultDataSource attachmentsDS = new HibernateQueryResultDataSource(rfqHeader.getDocAttachmentList());
            //parameters.put("attachmentsDS", attachmentsDS);
            
            String defaultSiteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
            String siteUrl = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", defaultSiteUrl);
            
            parameters.put("GCS_SITE_URL", siteUrl);
            parameters.put("uid", incomingRequest.get("userId"));
            parameters.put("mid", incomingRequest.get("mid"));

            List docCommentList = rfqHeader.getDocCommentList();
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
            HibernateQueryResultDataSource commentsBeforeDS = new HibernateQueryResultDataSource(commentsBeforeList);
            parameters.put("commentsBeforeDS", commentsBeforeDS);
            HibernateQueryResultDataSource commentsAfterDS = new HibernateQueryResultDataSource(commentsAfterList);
            parameters.put("commentsAfterDS", commentsAfterDS);
            
            List docAttachmentList = rfqHeader.getDocAttachmentList();
            List docAttachmentPrintList = new ArrayList();
            for (int i = 0; i < docAttachmentList.size(); i++)
            {
           	 	DocAttachment docAttachment = (DocAttachment) docAttachmentList.get(i);
                if (docAttachment.getDocPrint().equalsIgnoreCase("Y"))
                {
               	 docAttachmentPrintList.add(docAttachment);
                }
            }
            HibernateQueryResultDataSource attachmentsDS = new HibernateQueryResultDataSource(docAttachmentPrintList);
            parameters.put("attachmentsDS", attachmentsDS);

            List routingList = (List)incomingRequest.get("routingList");
            if(routingList == null)
            {
            	routingList = new ArrayList();
            }
            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("routingListDS", routingListDS);
            HibernateQueryResultDataSource approverNotesDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("approverNotesDS", approverNotesDS);

            List budgetReviewList = (List)incomingRequest.get("budgetReviewList");
            
            if(budgetReviewList == null)
            {
            	budgetReviewList = new ArrayList();
            }
            
            HibernateMultipleEntityDataSource budgetReviewDS = new HibernateMultipleEntityDataSource(budgetReviewList);
            parameters.put("budgetReviewListDS", budgetReviewDS);
            parameters.put("budgetColumns", (String)incomingRequest.get("budgetColumns"));

            String webreport = (String)incomingRequest.get("webreport");
            if(webreport == null) {     webreport = "Y";    }
            Log.debug(this, "this is a webreport: " + webreport);

            parameters.put("namePdf", "rfq" + rfqHeader.getDisplayRfqNumber() + ".html");
            parameters.put("webreport", webreport);
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
