/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.StdDocument;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.requisitionline.tasks.RequisitionLineRetrieveById;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintPoPdf extends Task
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
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            Log.debug(this, "printing po for po: " + poHeader.getPoNumber());

            String organizationId = (String)incomingRequest.get("organizationId");
            String viewNow = HiltonUtility.ckNull((String) incomingRequest.get("viewNow"));

            UserProfile buyer = UserManager.getInstance().getUser(organizationId, poHeader.getBuyerCode());
            String tcsFile = "tcs.jasper";
            if (buyer.getLocale().equals("CA")&& organizationId.equalsIgnoreCase("QRI06P"))
            {
            	tcsFile = "tcs-ca.jasper";
            }
            if (buyer.getLocale().equals("DE")&& organizationId.equalsIgnoreCase("BLY07P"))
            {
            	tcsFile = "tcs-de.jasper";
            }

            String jasperName = "po-pdf.jasper";
            String poFormMultiLanguages = PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "POFORMMULTIPLELANGUAJE", "N") ;
            if(poFormMultiLanguages.equalsIgnoreCase("Y") )
            {
             if( !HiltonUtility.isEmpty(poHeader.getUdf7Code()) )
            	{
            		String Locale =  "po-pdf-" + poHeader.getUdf7Code().toLowerCase() + ".jasper";
            		String path = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath");
           			File reportFile = HiltonUtility.getOidFile(path, jasperName, Locale, organizationId);
           			jasperName = reportFile.toString();
            	}
            }
            Log.debug(this, "printing use JasperName: " + jasperName);

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(poHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId.toUpperCase());

            Log.debug(this, "printing po pdf [organization : "+ organizationId + "]");
            parameters.put("entity", poHeader);
            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);
            if(organizationId.equalsIgnoreCase("msg07p")){
            	String printMode = poHeader.getPrintMode();
            	parameters.put("printMode", printMode);
            }
            if(organizationId.equalsIgnoreCase("bsc04p")){
            	parameters.put("viewNow", viewNow);
            }
            String TCs = (String)incomingRequest.get("TCs");
            if(!Utility.isEmpty(TCs))
            {
                if(TCs.equalsIgnoreCase("Y"))
                {
                    List jasperPrintList = new ArrayList();
                    Object poPdf[] = {jasperName, ds};
                    jasperPrintList.add(poPdf);
                    Object tcsPdf[] = {tcsFile, new JREmptyDataSource(1)};

                    boolean canPrintService = (PropertiesManager.getInstance(organizationId).getProperty("PRINT ORDERS", "PRINTSERVICETCS", "N").equalsIgnoreCase("Y")) ? true : false;

					if (canPrintService && (poHeader.getPoType().equalsIgnoreCase("SO") || poHeader.getPoType().equalsIgnoreCase("SB") || poHeader.getPoType().equalsIgnoreCase("SR")))
					{
						tcsPdf = new Object[] { "tcs_service.jasper", new JREmptyDataSource(1) };
					}

                    jasperPrintList.add(tcsPdf);
                    parameters.put("jasperPrintList", jasperPrintList);
                    parameters.put("TCs", "Y");
                }
                else
                {
                    parameters.put("formType", jasperName);
                }
            }
            else
            {
                parameters.put("formType", jasperName);
            }
            //parameters.put("pdfType", poHeader.getPoType());

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(poHeader.getPoLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", poHeader.getPoLineList());
            EntityDataSource shiptoDS = new EntityDataSource(poHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(poHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            EntityDataSource billToDS = new EntityDataSource(poHeader.getBillToAddress());
            parameters.put("billToDS", billToDS);
            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(poHeader.getAccountList());
            parameters.put("accountsDS", accountsDS);
            HibernateQueryResultDataSource attachmentsDS = new HibernateQueryResultDataSource(poHeader.getDocAttachmentList());
            parameters.put("attachmentsDS", attachmentsDS);


            List docCommentList = poHeader.getDocCommentList();
            List commentsBeforeList = new ArrayList();
            List commentsAfterList = new ArrayList();
            for (int i = 0; i < docCommentList.size(); i++)
            {
                DocComment docComment = (DocComment) docCommentList.get(i);
                //Check to see if comment should be printed on PDF
                if (docComment.getCommentPrint().equals("Y"))
                {
                	if (docComment.getCommentPlace().equals("B"))
                	{
                		commentsBeforeList.add(docComment);
                	}
                	else
                	{
                		commentsAfterList.add(docComment);
                	}
                }
            }

            HibernateQueryResultDataSource commentsBeforeDS = new HibernateQueryResultDataSource(commentsBeforeList);
            parameters.put("commentsBeforeDS", commentsBeforeDS);
            HibernateQueryResultDataSource commentsAfterDS = new HibernateQueryResultDataSource(commentsAfterList);
            parameters.put("commentsAfterDS", commentsAfterDS);
            String webreport = (String)incomingRequest.get("webreport");
            if(webreport == null) {     webreport = "Y";    }

            parameters.put("namePdf", poHeader.getDisplayPoNumber() + ".pdf");
            parameters.put("webreport", webreport);
            parameters.put("requisition_lastApprover", incomingRequest.get("Requisition_lastApprover"));

            List lastApproverList = (List)incomingRequest.get("Requisition_lastApprover");
            if (lastApproverList == null) { lastApproverList = new ArrayList(); }
            HibernateQueryResultDataSource requisition_lastApproverDS = new HibernateQueryResultDataSource(lastApproverList);
            parameters.put("requisition_lastApproverDS", requisition_lastApproverDS);

            //List userApproverList = (List)incomingRequest.get("Requisition_userApprover");
            //if (userApproverList == null) { userApproverList = new ArrayList(); }
            //Map mapApprover = new HashMap();
            //mapApprover.put("requisition_userApprover", userApproverList);
            //parameters.put("mapApprover", mapApprover);

            String ReqDescShipVia = (String)incomingRequest.get("ReqDescShipVia");
            String ReqDescTerms = (String)incomingRequest.get("ReqDescTerms");
            parameters.put("reqDescShipVia", ReqDescShipVia);
            parameters.put("reqDescTerms", ReqDescTerms);

            String documentsUrl = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-url", "/");
			StringBuffer hostDocuments = new StringBuffer();
			Map applications = PropertiesManager.getInstance(organizationId).getSection("APPLICATION");
			String url = (String)applications.get("URL");
			String [] parts = url.split("/");
			hostDocuments.append(parts[0]); hostDocuments.append("//"); hostDocuments.append(parts[2]);
			parts = documentsUrl.split("/");
			for (int a=3; a<parts.length; a++) {
				hostDocuments.append("/"); hostDocuments.append(parts[a]);
			}
			documentsUrl = hostDocuments.toString();
			if (documentsUrl.lastIndexOf("/") != documentsUrl.length()) {
				documentsUrl = documentsUrl + "/";
			}
            parameters.put("documentsUrl", documentsUrl);

            String locale = "";
            if (poHeader != null)
            {
            	if (poHeader.getAccountList() != null && poHeader.getAccountList().size() > 0)
            	{
            		for (int i = 0; i < poHeader.getAccountList().size(); i++)
            		{
            			Account account = (Account)poHeader.getAccountList().get(i);
            			if (account != null && !HiltonUtility.isEmpty(account.getFld1()))
            			{
            				locale = account.getFld1();
            				break;
            			}
            		}
            	}
            	else if (poHeader.getPoLineList() != null && poHeader.getPoLineList().size() > 0)
            	{
            		for (int i = 0; i < poHeader.getPoLineList().size(); i++)
            		{
            			PoLine poLine = (PoLine)poHeader.getPoLineList().get(i);
            			if (poLine != null && poLine.getAccountList() != null && poLine.getAccountList().size() > 0)
            			{
            				for (int j = 0; j < poLine.getAccountList().size(); j++)
                    		{
                    			Account account = (Account)poLine.getAccountList().get(j);
                    			if (account != null && !HiltonUtility.isEmpty(account.getFld1()))
                    			{
                    				locale = account.getFld1();
                    				break;
                    			}
                    		}
            			}
            		}
            	}
            }

            String document = "";
            if (locale.equalsIgnoreCase("00200")) {
            	document = "HWI";
            } else {
            	document = "BCBSRI";
            }
            String fileName = "";
            String title = "";
            List stdDocumentList = (List)incomingRequest.get("stdDocumentList");
            if (stdDocumentList != null && stdDocumentList.size() > 0)
            {
            	for (int i = 0; i < stdDocumentList.size(); i++)
            	{
            		StdDocument stdDocument = (StdDocument)stdDocumentList.get(i);
            		if (stdDocument != null && stdDocument.getTitle().toUpperCase().indexOf(document) >= 0)
            		{
            			fileName = stdDocument.getFileName();
            			title = stdDocument.getTitle();
            			break;
            		}
            	}
            }
            parameters.put("StdDocument_fileName", fileName);
            parameters.put("StdDocument_title", title);

            String pathImg = "";
            String udfMapping = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "POH", "");
            if(!udfMapping.equals("")){
            	Class cls = poHeader.getClass() ;
            	Method mth = cls.getMethod("get" + udfMapping,null);
            	String valueUdf = (String)mth.invoke(poHeader, null);
            	if(!valueUdf.equals("") && valueUdf != null){
            		valueUdf = HiltonUtility.convertYtoYesOrNtoNo(valueUdf);
            		String nameLogo = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "POH-"+valueUdf, "");
            		if(!nameLogo.equals("")){
            			pathImg = JasperReportsHelper.getImgUrl(organizationId, nameLogo);
            		}
            	}
            }
            if(pathImg.equals("")){
            	pathImg = JasperReportsHelper.getLogo(organizationId, "Udf10", poHeader.getUdf10Code());
            }
        	parameters.put("pathImg", pathImg);

        	String poLineLabelActive = HiltonUtility.ckNull((String)PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "POLINELABELACTIVE", "N"));

        	if(poLineLabelActive.equalsIgnoreCase("Y") && "-PO-PR".indexOf(poHeader.getPoType())>=0){
        		parameters.put("poHeader", poHeader);
        		parameters.put("reqLineLabel", incomingRequest.get("reqLineLabel"));
        		parameters.put("msrLine", incomingRequest.get("msrLine"));
        		if(incomingRequest.get("msrHeader")!=null)
        		{
        			if(!poHeader.getPoLineList().isEmpty()){
        				for(int p = 0 ; p < poHeader.getPoLineList().size(); p++){
        					((PoLine)poHeader.getPoLineList().get(p)).setMsrHeader((RequisitionHeader)incomingRequest.get("msrHeader"));
        				}
        			}
        		}
        		parameters.put("poLineLabel", incomingRequest.get("poLineLabel"));
        		parameters.put("statusRI", incomingRequest.get("statusRI"));
        		parameters.put("routeNumber", incomingRequest.get("routeNumber"));


        		String isCatalogVinimaya = HiltonUtility.ckNull((String) incomingRequest.get("isCatalogVinimaya"));

        		if (isCatalogVinimaya.equalsIgnoreCase("Y"))
        		{
        			parameters.put("isCatalogLabelActive", "Y");
        			Log.debug(this, "PoLineLabel is active for " + poHeader.getPoNumber());
        		}
        		else
        		{
        			parameters.put("isCatalogLabelActive", "N");
        			Log.debug(this, "PoLineLabel is inactive for  " + poHeader.getPoNumber());
        		}
        	}
        	else {
        		  parameters.put("isCatalogLabelActive", "N");
        		  Log.debug(this, "PoLineLabel is inactive for " + poHeader.getPoNumber());
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
