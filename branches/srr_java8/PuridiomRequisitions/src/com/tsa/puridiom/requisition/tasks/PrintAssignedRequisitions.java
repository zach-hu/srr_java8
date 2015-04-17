/*
 * Created on Nov 21, 2006
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;


public class PrintAssignedRequisitions extends Task
{

	public Object getRequisitionPdfs(String icReqHeader, String organizationId) throws Exception
	{
		Object ret = null;
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
        PuridiomProcess process = processLoader.loadProcess("requisition-batch-pdf.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
        incomingRequest.put("webreport", "R");
        incomingRequest.put("batchPrint", "Y");
        incomingRequest.put("organizationId", organizationId);
        process.executeProcess(incomingRequest);

        if (process.getStatus() == Status.SUCCEEDED)
        {
        	ret = incomingRequest.get("pdfParameters");
        }
        return ret;
	}

	/**
	 * For each invoice with attachments generates and fills the jasper file.
	 * @param pdfParameters
	 * @param organizationId
	 * @return
	 */
	public Object getBatchPdf(List pdfParameters, String organizationId)
	{
        Object reportName = null;
        //Log.debug("JasperReportsHelper", "printPdf starts");
        List reportList = new ArrayList();
        //for each pdf generate the jasper files

        for(int i = 0; i < pdfParameters.size(); i++)
        {
        	Map parameters = (Map)pdfParameters.get(i);
        	List tempPdfs = null;
        	//generate individual pdfs per invoice

        	try
        	{
        		tempPdfs = this.getPdfList(parameters, organizationId);
			}
        	catch (Exception e)
        	{
				// TODO: Send email with a notification of which pdfs were not generated.
        		tempPdfs = new ArrayList();
        		Log.error(this, "PDF for Invoice: " + parameters.get("RequisitionHeader_icReqHeader") + " could not be generated. Cause: " + e.getMessage());
			}
        	reportList.addAll(tempPdfs);
        }

        //export to one PDF
        try
        {
        	reportName = this.getPdfName(organizationId, reportList);
		}
        catch (Exception e)
        {
			// TODO: if a pdf could not be generated reset status of all selected invoices to previous status.
        	Log.error(this, "Pdf could not be generated for selected Invoices");
		}


        Log.debug("JasperReportsHelper", "printPdf is done");

        return reportName;
	}

	public List getPdfList(Map parameters, String organizationId) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
        PuridiomProcess process = processLoader.loadProcess("print-all-jasper-assigned-requisitions.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("pdfParameters", parameters);
        incomingRequest.put("organizationId", organizationId);
        process.executeProcess(incomingRequest);
        List requisitionPdf = new ArrayList();
        if (process.getStatus() == Status.SUCCEEDED)
        {
       		requisitionPdf = (List)incomingRequest.get("requisitionJasperList");
        }
        return requisitionPdf;
	}

/*
	public int updateDatePrinted(String icIvcHeader, String organizationId) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
        PuridiomProcess process = processLoader.loadProcess("invoiceheader-update.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("organizationId", organizationId);
        incomingRequest.put("InvoiceHeader_status", DocumentStatus.IVC_PENDING_PAYMENT);
        incomingRequest.put("InvoiceHeader_invoicePrintedDate", Dates.today("", ""));
        incomingRequest.put("InvoiceHeader_icIvcHeader", icIvcHeader);
        process.executeProcess(incomingRequest);

        return process.getStatus();
	}
*/
	/**
	 * Generates PDF including all selected invoices and attachments
	 * @param organizationId
	 * @param reportList
	 * @return
	 * @throws Exception
	 */
	public Object getPdfName(String organizationId, List reportList) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
        PuridiomProcess process = processLoader.loadProcess("print-all-pdf-assigned-requisitions.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("pdfParameters", parameters);
        incomingRequest.put("organizationId", organizationId);
        incomingRequest.put("reportList", reportList);
        process.executeProcess(incomingRequest);

        if (process.getStatus() == Status.SUCCEEDED)
        {
        	return incomingRequest.get("requisitionPdf");
        }
        else
        {
        	return "notfound.html";
        }
	}
    public Object executeTask(Object object) throws Exception {
        Object ret = null;

        try
        {
            Map incomingRequest = (Map) object;

            Object icReqHeaderObj = incomingRequest.get("RequisitionHeader_icReqHeader");
            String organizationId = (String)incomingRequest.get("organizationId");
            List pdfParameters = new ArrayList();

            if (icReqHeaderObj instanceof String[])
            {
                String	icReqHeaderArray[] = (String[]) icReqHeaderObj;
                for (int i = 0; i < icReqHeaderArray.length-2; i++)
                {
                	pdfParameters.add( this.getRequisitionPdfs(icReqHeaderArray[i], organizationId));
                }
            }
            else
            {
            	String icReqHeader = (String) icReqHeaderObj;
            	pdfParameters.add( this.getRequisitionPdfs(icReqHeader, organizationId));
            }

            ret = this.getBatchPdf(pdfParameters, organizationId);
            incomingRequest.put("report", ret);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Requisition print process failed!" + e.getMessage(), e);
        }
        return ret;
    }
}