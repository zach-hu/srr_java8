/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formhtml.tasks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
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
 * @author
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendGeneralEmailHtml extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
        	 Map incomingRequest = (Map)object;
        	 String entityNameFull   = (String) incomingRequest.get("entityNameFull");
     		 String entityLine =  (String)incomingRequest.get("entityLine");
     		 String ShipToAddress = (String)incomingRequest.get("ShipToAddress");
     		 String VendorAddress = (String)incomingRequest.get("VendorAddress");
     		 String AccountList = (String)incomingRequest.get("AccountList");
     		 String DocCommentList = (String)incomingRequest.get("DocCommentList");
     		 String EntityType = (String)incomingRequest.get("RequisitionType");
     		 String EntityNumber = (String)incomingRequest.get("RequisitionNumber");


     		Class entityHeader = Class.forName(entityNameFull);
     		Object entityHeaderInstance = entityHeader.newInstance();


     		Method getEntityLine = entityHeader.getMethod(entityLine, null);
       		List LineResult = (List) getEntityLine.invoke(entityHeaderInstance,null);

     		Method getShipToAddress = entityHeader.getMethod(ShipToAddress,null);
       		Address adressResult = (Address) getShipToAddress.invoke(entityHeaderInstance,null);

       		Method getVendorAddress = entityHeader.getMethod(VendorAddress,null);
       		Address vendorResult = (Address) getVendorAddress.invoke(entityHeaderInstance,null);

       		Method getAccountList = entityHeader.getMethod(AccountList,null);
     		List accounResult = (List) getAccountList.invoke(entityHeaderInstance,null);

     		Method getDocCommentList = entityHeader.getMethod(DocCommentList,null);
     		List docResult = (List) getDocCommentList.invoke(entityHeaderInstance,null);

     		Method getEntityType = entityHeader.getMethod(EntityType,null);
     		String entityResult = (String) getEntityType.invoke(entityHeaderInstance,null);

     		Method getEntityNumber = entityHeader.getMethod(EntityNumber,null);
     		String NumberResult = (String) getEntityNumber.invoke(entityHeaderInstance,null);

     		String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(entityHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", entityHeader);
            String templateName = (String) incomingRequest.get("parameterValues[1]");
            parameters.put("formType",templateName); // templateName


             //HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(task.getRequisitionLineList());
            if(Utility.isObjectEmpty(LineResult)){
            	HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(LineResult);
            	parameters.put("linesDS", itemDS);

             parameters.put("lines", LineResult);
            }

            if(Utility.isObjectEmpty(adressResult)){
            	EntityDataSource shiptoDS = new EntityDataSource(adressResult);
            	parameters.put("shipToDS", shiptoDS);
            }

            if(Utility.isObjectEmpty(vendorResult)){
            	EntityDataSource vendorDS = new EntityDataSource(vendorResult);
                parameters.put("vendorDS", vendorDS);

            }

            if(Utility.isObjectEmpty(accounResult)) {
            	HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(accounResult);
                parameters.put("accountsDS", accountsDS);
            }
             List routingList = (List)incomingRequest.get("routingList");
             parameters.put("uid", incomingRequest.get("userId"));
             parameters.put("mid", incomingRequest.get("mid"));
             String siteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
             parameters.put("GCS_SITE_URL", siteUrl);
             String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
             parameters.put("imgUrl", imgUrl);
             Log.debug(this, "imgUrl "+ imgUrl);
             if(routingList == null)
             {
             	routingList = new ArrayList();
             }
             HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
             parameters.put("routingListDS", routingListDS);

             String webreport = (String)incomingRequest.get("webreport");
             if(Utility.isEmpty(webreport)){		webreport = "Y";	}
             parameters.put("webreport", webreport);

             List docCommentList = docResult;
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
             parameters.put("namePdf", RequisitionType.toString(entityResult, organizationId) + "[" + NumberResult + "].html");

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
