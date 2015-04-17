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

import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
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
public class ReqEmailApprovals extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            Log.debug(this, "printing req for: " + requisitionHeader.getRequisitionNumber());

            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(requisitionHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId.toUpperCase());
            Log.debug(this, "printing po pdf [organization : "+ organizationId + "]");
            parameters.put("entity", requisitionHeader);
            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);

            parameters.put("formType", "req-email-approvals1.jasper");

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(requisitionHeader.getRequisitionLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", requisitionHeader.getRequisitionLineList());
            EntityDataSource shiptoDS = new EntityDataSource(requisitionHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(requisitionHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            EntityDataSource billToDS = new EntityDataSource(requisitionHeader.getBillToAddress());
            parameters.put("billToDS", billToDS);
            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(requisitionHeader.getAccountList());
            parameters.put("accountsDS", accountsDS);
            HibernateQueryResultDataSource attachmentsDS = new HibernateQueryResultDataSource(requisitionHeader.getDocAttachmentList());
            parameters.put("attachmentsDS", attachmentsDS);
            String siteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
            parameters.put("GCS_SITE_URL", siteUrl);
            parameters.put("uid", incomingRequest.get("userId"));
            parameters.put("mid", incomingRequest.get("mid"));

            List docCommentList = requisitionHeader.getDocCommentList();
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
            List routingList = (List)incomingRequest.get("routingList");
            if(routingList == null)
            {
            	routingList = new ArrayList();
            }
            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("routingListDS", routingListDS);
            HibernateQueryResultDataSource approverNotesDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("approverNotesDS", approverNotesDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(webreport == null) {     webreport = "Y";    }
            Log.debug(this, "this is a webreport: " + webreport);

            parameters.put("namePdf", "po" + requisitionHeader.getRequisitionNumber() + ".html");
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
