/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqVendor;
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
 */
public class PrintRfqPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(rfqHeader, organizationId);
            String PdfRfq_vendorId = (String)incomingRequest.get("PdfRfq_vendorId");
            parameters.put("PdfRfq_vendorId", PdfRfq_vendorId);
            RfqVendor currentVendor = (RfqVendor)incomingRequest.get("currentVendor");
            String contactCode = "001";
            if(currentVendor!= null)
            {
            	contactCode = currentVendor.getContactId();
            }
            else
            {
            	contactCode = "001";
            }
            parameters.put("PdfRfq_vendContactCode", contactCode);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", rfqHeader);
            //parameters.put("rddSeeBelow",incomingRequest.get("rddSeeBelow"));
            parameters.put("formType", "rfq-pdf.jasper");
            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(rfqHeader.getRfqLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", rfqHeader.getRfqLineList());
            EntityDataSource shiptoDS = new EntityDataSource(rfqHeader.getShipToAddress(), organizationId);
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(rfqHeader.getVendorAddress(), organizationId);
            parameters.put("vendorDS", vendorDS);
            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(new ArrayList());
            parameters.put("accountsDS", accountsDS);
            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(new ArrayList());
            parameters.put("routingListDS", routingListDS);
            Address buyerShipToAddress = (Address)incomingRequest.get("userShipToAddress");
            EntityDataSource buyerAddressDS = new EntityDataSource(buyerShipToAddress, organizationId);
            parameters.put("buyerAddressDS", buyerAddressDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

            List docAthamentsList = rfqHeader.getDocAttachmentList();
            HibernateQueryResultDataSource docAttachmentDS = new HibernateQueryResultDataSource(docAthamentsList);
            parameters.put("docAttachmentDS", docAttachmentDS);

            List docCommentList = rfqHeader.getDocCommentList();
            List commentsBeforeList = new ArrayList();
            List commentsAfterList = new ArrayList();

            for (int i = 0; i < docCommentList.size(); i++)
            {
                DocComment docComment = (DocComment) docCommentList.get(i);
                if (docComment.getCommentPlace().equals("B") && docComment.getCommentPrint().equalsIgnoreCase("Y"))
                {
                    commentsBeforeList.add(docComment);
                }
                else if (docComment.getCommentPrint().equalsIgnoreCase("Y"))
                {
                    commentsAfterList.add(docComment);
                }
            }
            HibernateQueryResultDataSource commentsBeforeDS = new HibernateQueryResultDataSource(commentsBeforeList);
            parameters.put("commentsBeforeDS", commentsBeforeDS);
            HibernateQueryResultDataSource commentsAfterDS = new HibernateQueryResultDataSource(commentsAfterList);
            parameters.put("commentsAfterDS", commentsAfterDS);

            if(HiltonUtility.isEmpty(PdfRfq_vendorId))
            {
            	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + "[" + rfqHeader.getRfqNumber() + "].pdf");
            }
            else
            {
            	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + "[" + rfqHeader.getRfqNumber() + "]-" + PdfRfq_vendorId + ".pdf");
            }

            String stdRfq = RfqType.REQUEST_FOR_PROPOSAL + "-" + RfqType.REQUEST_FOR_INFORMATION + "-" +  RfqType.INVITATION_TO_BID;
            String pdfType = rfqHeader.getRfqType();
            if(stdRfq.indexOf(rfqHeader.getRfqType()) > -1)
            {
            	pdfType = "std";
            }
            parameters.put("pdfType", pdfType);

            String pathImg = "";
            String udfMapping = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "RFH", "");
            if(!udfMapping.equals("")){
            	Class cls = rfqHeader.getClass() ;
            	Method mth = cls.getMethod("get" + udfMapping,null);
            	String valueUdf = (String)mth.invoke(rfqHeader, null);
            	if(!valueUdf.equals("") && valueUdf != null){
            		valueUdf = HiltonUtility.convertYtoYesOrNtoNo(valueUdf);
            		String nameLogo = PropertiesManager.getInstance(organizationId).getProperty("LOGO", "RFH-"+valueUdf, "");
            		if(!nameLogo.equals("")){
            			pathImg = JasperReportsHelper.getImgUrl(organizationId, nameLogo);
            		}
            	}
            }
            if(pathImg.equals("")){
            	pathImg = JasperReportsHelper.getLogo(organizationId, "Udf10", rfqHeader.getUdf10Code());
            }
        	parameters.put("pathImg", pathImg);


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
