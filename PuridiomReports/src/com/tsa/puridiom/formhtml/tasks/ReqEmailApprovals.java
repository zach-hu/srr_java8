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

import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
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
public class ReqEmailApprovals extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
        	 Map incomingRequest = (Map)object;
             RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
             String organizationId = (String)incomingRequest.get("organizationId");
             String messageAdditional = HiltonUtility.ckNull((String)incomingRequest.get("messagetext"));

             Map parameters = new HashMap();
             EntityDataSource ds = new EntityDataSource(reqHeader);
             parameters.put("datasource", ds);
             parameters.put("organizationId", organizationId);
             parameters.put("oid", organizationId);
             parameters.put("entity", reqHeader);
             
             if(!HiltonUtility.isEmpty(messageAdditional))
             {
            	 parameters.put("messagetext", messageAdditional);
            	 parameters.put("formType", "req-email-additional-msg-approvals.jasper");
             }
             else
             {
            	 parameters.put("formType", "req-email-approvals.jasper");
             }
             HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(reqHeader.getRequisitionLineList());
             parameters.put("linesDS", itemDS);
             parameters.put("lines", reqHeader.getRequisitionLineList());

            List lines =   (List)   reqHeader.getRequisitionLineList();
			List budgetInfoList = new ArrayList();

            for( int i=0; i < lines.size(); i++ )
            {
            	RequisitionLine rql = (RequisitionLine)lines.get(i);
            	if (rql.getBudgetList() != null)
            	{
            		for(int j=0; j < rql.getBudgetList().size(); j++)
            		{
            			BudgetCenter bc = (BudgetCenter)rql.getBudgetList().get(j);
	            		budgetInfoList.add(bc);
	            	}
	            	rql.getBudgetList();
            	 }
             }
             HibernateQueryResultDataSource budgetInfoDS = new HibernateQueryResultDataSource(budgetInfoList);
             parameters.put("budgetInfoDS", budgetInfoDS);

             EntityDataSource shiptoDS = new EntityDataSource(reqHeader.getShipToAddress());
             parameters.put("shipToDS", shiptoDS);
             EntityDataSource vendorDS = new EntityDataSource(reqHeader.getVendorAddress());
             parameters.put("vendorDS", vendorDS);

             HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(reqHeader.getAccountList());
             parameters.put("accountsDS", accountsDS);
             List routingList = (List)incomingRequest.get("routingList");
             List routingListOrderedByUserId = (List)incomingRequest.get("routingListOrderedByUserId");
             parameters.put("uid", incomingRequest.get("userId"));
             parameters.put("mid", incomingRequest.get("mid"));
             
             String defaultSiteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
             String siteUrl = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", defaultSiteUrl);
             
             parameters.put("GCS_SITE_URL", siteUrl);
             String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
             parameters.put("imgUrl", imgUrl);
             Log.debug(this, "imgUrl "+ imgUrl);
             
             if(routingList == null)
             {
             	routingList = new ArrayList();
             }
             
             if (routingListOrderedByUserId == null) 
             {
            	 routingListOrderedByUserId = new ArrayList();
             }
             
             HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
             parameters.put("routingListDS", routingListDS);

             HibernateQueryResultDataSource approverNotesDS = new HibernateQueryResultDataSource(routingList);
             parameters.put("approverNotesDS", approverNotesDS);

             HibernateQueryResultDataSource approverNotesOrderedByUserIdDS = new HibernateQueryResultDataSource(routingListOrderedByUserId);
             parameters.put("approverNotesOrderedByUserIdDS", approverNotesOrderedByUserIdDS);

             String webreport = (String)incomingRequest.get("webreport");
             if(Utility.isEmpty(webreport)){		webreport = "Y";	}
             parameters.put("webreport", webreport);

             List docCommentList = reqHeader.getDocCommentList();
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

             List docAttachmentList = reqHeader.getDocAttachmentList();
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

             //parameters.put("nameHtml", RequisitionType.toString(reqHeader.getRequisitionType(), organizationId) + "[" + reqHeader.getRequisitionNumber() + "].html");
             parameters.put("namePdf", RequisitionType.toString(reqHeader.getRequisitionType(), organizationId) + "[" + reqHeader.getRequisitionNumber() + "].html");
             String htmlFyi = (String)incomingRequest.get("fyi");
             if(Utility.isEmpty(htmlFyi)) {		htmlFyi = SendQueue.HTML_EMAIL_ACTION;		}
             if(htmlFyi.equalsIgnoreCase(SendQueue.HTML_FYI_EMAIL_ACTION))
             {
            	 parameters.put("fyi", Boolean.TRUE);
             }
             else
             {
            	 parameters.put("fyi", Boolean.FALSE);
             }
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
