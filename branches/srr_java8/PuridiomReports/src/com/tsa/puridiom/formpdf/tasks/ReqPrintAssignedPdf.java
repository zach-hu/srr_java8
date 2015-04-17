package com.tsa.puridiom.formpdf.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.reports.datasource.TiffDataSource;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class ReqPrintAssignedPdf extends Task
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
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String batchPrint = HiltonUtility.ckNull((String) incomingRequest.put("batchPrint", "Y"));

            Log.debug(this, "printing requisition: " + requisitionHeader.getRequisitionNumber());
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(requisitionHeader);
            parameters.put("RequisitionHeader_icReqHeader", requisitionHeader.getIcReqHeader().toString());
            parameters.put("datasource",ds);
            parameters.put("oid", organizationId.toUpperCase());
            parameters.put("organizationId", organizationId.toUpperCase());

            EntityDataSource shiptoDS = new EntityDataSource(requisitionHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(requisitionHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            List routingList = (List)incomingRequest.get("routingList");
            if(routingList == null)
            {
            	routingList = new ArrayList();
            }
            HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
            parameters.put("routingListDS", routingListDS);

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(requisitionHeader.getRequisitionLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", requisitionHeader.getRequisitionLineList());
            HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(requisitionHeader.getAccountList());
            parameters.put("accountsDS", accountsDS);

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

            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);
            parameters.put("formType", "requisition.jasper");
            List jasperPrintList = new ArrayList();
            Object poPdf[] = {"requisition.jasper", ds};
            jasperPrintList.add(poPdf);
            //Add attachments
            if((requisitionHeader.getDocAttachmentList().size() > 0) && (!batchPrint.equalsIgnoreCase("Y")))
            {
	            jasperPrintList.addAll(this.getAttchmentPdfs(requisitionHeader.getDocAttachmentList(), organizationId));
	            parameters.put("TCs", "Y");
            }
            parameters.put("jasperPrintList", jasperPrintList);

            //it could be too.
            //InvoiceAddress vendorAddress  = (InvoiceAddress) incomingRequest.get("invoiceAddress");
            //EntityDataSource vendorDS = new EntityDataSource(vendorAddress);
            //parameters.put("vendorDS", vendorDS);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){	webreport = "R";}

            //parameters.put("namePdf", InvoiceType.toString(invoiceHeader.getInvoiceType(), organizationId) + "[" + invoiceHeader.getInvoiceNumber() + "].pdf");
            parameters.put("namePdf", "Requisition [" + requisitionHeader.getRequisitionNumber() + "].pdf");
            parameters.put("webreport", webreport);
//            EntityDataSource poDataDs = new EntityDataSource(poHeader);
//            parameters.put("poData",poDataDs);
            //ret = JasperReportsHelper.printPdf(parameters);
            ret = parameters;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
          this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;

    }

    public List getAttchmentPdfs(List attchList, String organizationId) throws Exception
	{

    	List tmpList = new ArrayList();
    	if(attchList != null)
        {
        	if(attchList.size() > 0)
        	{
            	for(int i = 0; i < attchList.size(); i++)
            	{
            		DocAttachment attachment = (DocAttachment)attchList.get(i);
            		if(attachment.getDocPrint().equalsIgnoreCase("Y"))
            		{
	            		tmpList.add(this.getAttachmentName(attachment, organizationId));
            		}
            	}
        	}
        }

    	return tmpList;
	}
    public Object getAttachmentName(DocAttachment attachment, String organizationId) throws Exception
    {
    	Object tcsPdf[] = new Object[2];
    	Log.debug(this, "INVOICE PDF, " + "getAttachmentName");
    	if(attachment.getDocPrint().equalsIgnoreCase("Y"))
    	{
	    	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	        PuridiomProcess process = processLoader.loadProcess("document-file-copy-to-temp.xml");
	        Map incomingRequest = new HashMap();
	        incomingRequest.put("docAttachment", attachment);
	    	incomingRequest.put("organizationId", organizationId);
	        process.executeProcess(incomingRequest);

	        if (process.getStatus() == Status.SUCCEEDED)
	        {
	        	String fileName = (String)incomingRequest.get("DocAttachment_docFilename");
	        	int extPos = fileName.lastIndexOf(".");
	        	String extension = "";
	        	if(extPos >= 0)
	        	{
	        		extension = fileName.substring(extPos + 1).toUpperCase();
	        	}
	        	if(("GIF JPG JPEG PNG BMP").indexOf(extension) >= 0)
	        	{
	        		tcsPdf[0] = "img.jasper";
	        		String tempUrl = DictionaryManager.getInstance("host", organizationId).getProperty("temporary-document-url", "/");
	        		if (tempUrl.lastIndexOf("/") != tempUrl.length()) {
	    				tempUrl = tempUrl + "/";
	    			}
	        		File tmpFile = new File(fileName);
	        		fileName = tempUrl + tmpFile.getName();
	        	}
	        	else if(extension.equalsIgnoreCase("PDF"))
	        	{
	        		tcsPdf[0] = fileName;
	        	}
	        	else if(extension.equalsIgnoreCase("tif"))
	        	{
	        		tcsPdf[0] = "tiff1.jasper";
	        	}

	        	tcsPdf[1] = new TiffDataSource(fileName, organizationId);
	        }
    	}

		return tcsPdf;

    }


}
