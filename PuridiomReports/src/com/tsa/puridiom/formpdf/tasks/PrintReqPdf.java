/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintReqPdf extends Task
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
            RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String organizationId = (String)incomingRequest.get("organizationId");

            //JasperReportsHelper.compile("shiptosb.jrxml", organizationId);
            //JasperReportsHelper.compile("poitems.jrxml", organizationId);
            //JasperReportsHelper.compile("requisition.jrxml", organizationId);

            //JasperReport shipToSubReport = JasperReportsHelper.loadSubReport("shiptosb.jasper", organizationId);
            //JasperReport itemsSubReport = JasperReportsHelper.loadSubReport("poitems.jasper", organizationId);

            //*************** start shipto compilation **************
            //JasperDesign subDesign = JasperManager.loadXmlDesign(path + "shiptosb.jrxml");
            //JasperReport subReport = JasperManager.compileReport(subDesign);
            Map parameters = new HashMap();
            //parameters.put("shipto_subreport", shipToSubReport);
            //parameters.put("lines_subreport", itemsSubReport);
            EntityDataSource ds = new EntityDataSource(reqHeader);
            //EntityDataSource shiptoDS = new EntityDataSource(poHeader.getShipToAddress());
            //parameters.put("shipToDS", shiptoDS);
            //EntityDataSource itemsDS = new EntityDataSource(poHeader.getl);
            //List itemsList = (List)incomingRequest.get("poLineList");
            //HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(itemsList);
            //parameters.put("linesDS", itemDS);
            //JasperReportsHelper.fill("serviceBlanket1.jasper", parameters, organizationId, ds);
            //JasperReportsHelper.exportToPdf("serviceBlanket1.jasper", organizationId, parameters, ds, "serviceBlanket1.pdf");
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", reqHeader);

            parameters.put("formType", "requisition.jasper");
            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(reqHeader.getRequisitionLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", reqHeader.getRequisitionLineList());
            EntityDataSource shiptoDS = new EntityDataSource(reqHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(reqHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(reqHeader.getAccountList());
            parameters.put("accountsDS", accountsDS);
            List routingList = (List)incomingRequest.get("routingList");
            addRequestAndFpeToRoutingList(reqHeader, routingList);

            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("routingListDS", routingListDS);

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
            parameters.put("namePdf", RequisitionType.toString(reqHeader.getRequisitionType(), organizationId) + "[" + reqHeader.getRequisitionNumber() + "].pdf");

            List docAthamentsList = reqHeader.getDocAttachmentList();
            HibernateQueryResultDataSource docAttachmentDS = new HibernateQueryResultDataSource(docAthamentsList);
            parameters.put("docAttachmentDS", docAttachmentDS);


            String pathImg = "";
            String udfMapping = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "RQH", "");
            if(!udfMapping.equals("")){
            	Class cls = reqHeader.getClass() ;
            	Method mth = cls.getMethod("get" + udfMapping,null);
            	String valueUdf = (String)mth.invoke(reqHeader, null);
            	if(!valueUdf.equals("") && valueUdf != null){
            		valueUdf = HiltonUtility.convertYtoYesOrNtoNo(valueUdf);
            		String nameLogo = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "RQH-"+valueUdf, "");
            		if(!nameLogo.equals("")){
            			pathImg = JasperReportsHelper.getImgUrl(organizationId, nameLogo);
            		}
            	}
            }
            if(pathImg.equals("")){
            	pathImg = JasperReportsHelper.getLogo(organizationId, "Udf10", reqHeader.getUdf10Code());
            }
        	parameters.put("pathImg", pathImg);

            ret = JasperReportsHelper.printPdf(parameters);

            //JasperPrint jasperPrint = JasperManager.fillReport(subReport, parameters, shiptoDS);
            //*************** end shipto compilation **************


            //JasperDesign jasperDesign = JasperManager.loadXmlDesign(path + "serviceBlanket1.jrxml");
            //JasperReport jasperReport = JasperManager.compileReport(jasperDesign);

            //Second, create a map of parameters to pass to the report.
            //Preparing parameters
            //Map MasterParameters = new HashMap();
            //MasterParameters.put("shipto_subreport", path + "shiptosb.jasper");

            //Fourth, create JasperPrint using fillReport() method
            //List addyList = (List)incomingRequest.get("addressList");

            //EntityDataSource ds = new EntityDataSource(poHeader);

            //MasterParameters.put("shipToDS", shiptoDS);

            //HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(temp);
            //JRDataSource ds = new JRDataSource();
            //JasperPrint jasperPrintMaster = JasperManager.fillReport(jasperReport, MasterParameters, ds);
            //JasperPrint jasperPrint = JasperManager.fillReport(jasperReport, parameters, ds);

            //JasperPrint jasperPrintMaster = JasperManager.fillReport(jasperReport, MasterParameters, WebappDataSource.getConnection());

            //You can use JasperPrint to create PDF
            //JasperManager.printReportToPdfFile(jasperPrintMaster, "serviceBlanket_template.pdf");
            //JasperReportsHelper.exportToPdf("serviceBlanket1.jasper", organizationId);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

    private void addRequestAndFpeToRoutingList(RequisitionHeader reqHeader, List routingList){

       	//"F,L" ->> Request and FPE
     	//"A"  ->>	"Current Approver"
     	//"Y"  ->>  $F{ApprovalLog_dateApproved}
     	//"N"  ->>  [--]

    	 ApprovalLog requesterAppLog = new ApprovalLog();
         ApprovalLogPK requesterAppLogPK = new ApprovalLogPK();

         requesterAppLogPK.setIcHeader(reqHeader.getIcReqHeader());
         requesterAppLogPK.setIcLine(new BigDecimal(0));
         requesterAppLogPK.setSequence(new BigDecimal(0));
         requesterAppLogPK.setUserId(reqHeader.getRequisitionerCode());

         requesterAppLog.setComp_id(requesterAppLogPK);
         requesterAppLog.setApproved("F");

         ApprovalLog fpeAppLog = new ApprovalLog();
         ApprovalLogPK fpeAppLogPK = new ApprovalLogPK();

         fpeAppLogPK.setIcHeader(reqHeader.getIcReqHeader());
         fpeAppLogPK.setIcLine(new BigDecimal(0));
         fpeAppLogPK.setSequence(new BigDecimal(0));
         fpeAppLogPK.setUserId(reqHeader.getOwner());

         fpeAppLog.setComp_id(fpeAppLogPK);
         fpeAppLog.setApproved("L");
         fpeAppLog.setDateApproved(reqHeader.getRequisitionDate());

         if(routingList == null || routingList.size() == 0)
         {
         	routingList = new ArrayList();
         }
         else {
         	ApprovalLog first = (ApprovalLog) routingList.get(0);
         	requesterAppLog.setDateApproved(first.getDateAssigned());
         	routingList.add(0, requesterAppLog);

         	fpeAppLogPK.setSequence(new BigDecimal(routingList.size()));
         	fpeAppLog.setComp_id(fpeAppLogPK);
         	routingList.add(fpeAppLog);
         }

    }

}
