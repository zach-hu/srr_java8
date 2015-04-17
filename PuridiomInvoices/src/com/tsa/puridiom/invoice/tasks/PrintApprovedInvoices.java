/*
 * Created on Nov 21, 2006
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kathleen
 */
public class PrintApprovedInvoices extends Task
{

	/**
	 * Gets a list of Invoices and attachments to be printed
	 * @param icIvcHeader
	 * @param organizationId
	 * @return
	 * @throws Exception
	 */
	public Object getInvoicePdfs(String icIvcHeader, String organizationId) throws Exception
	{
		Object ret = null;
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
        PuridiomProcess process = processLoader.loadProcess("invoice-batch-pdf.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("InvoiceHeader_icIvcHeader", icIvcHeader);
        incomingRequest.put("webreport", "R");
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

        Log.debug("JasperReportsHelper", "printPdf starts");
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
        		Log.error(this, "PDF for Invoice: " + parameters.get("InvoiceHeader_icIvcHeader") + " could not be generated. Cause: " + e.getMessage());
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
        PuridiomProcess process = processLoader.loadProcess("print-all-jasper-approved-invoices.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("pdfParameters", parameters);
        incomingRequest.put("organizationId", organizationId);
        process.executeProcess(incomingRequest);
        List invoicePdf = new ArrayList();
        if (process.getStatus() == Status.SUCCEEDED)
        {
//        	update dateprinted and status
        	int updateStatus = this.updateDatePrinted((String)parameters.get("InvoiceHeader_icIvcHeader"), organizationId);
        	if(updateStatus == Status.SUCCEEDED)
        	{
        		//add jasperList
        		invoicePdf = (List)incomingRequest.get("invoiceJasperList");
        	}
        }
        return invoicePdf;
	}

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
        PuridiomProcess process = processLoader.loadProcess("print-all-pdf-approved-invoices.xml");
        Map incomingRequest = new HashMap();
        incomingRequest.put("pdfParameters", parameters);
        incomingRequest.put("organizationId", organizationId);
        incomingRequest.put("reportList", reportList);
        process.executeProcess(incomingRequest);

        if (process.getStatus() == Status.SUCCEEDED)
        {
        	return incomingRequest.get("invoicePdf");
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

            Object icIvcHeaderObj = incomingRequest.get("InvoiceHeader_icIvcHeader");
            String organizationId = (String)incomingRequest.get("organizationId");
            List pdfParameters = new ArrayList();

            if (icIvcHeaderObj instanceof String[])
            {
                String	icIvcHeaderArray[] = (String[]) icIvcHeaderObj;
                for (int i = 0; i < icIvcHeaderArray.length; i++)
                {
                	pdfParameters.add( this.getInvoicePdfs(icIvcHeaderArray[i], organizationId));
                }
            }
            else
            {
            	String icIvcHeader = (String) icIvcHeaderObj;
            	pdfParameters.add( this.getInvoicePdfs(icIvcHeader, organizationId));
            }

            ret = this.getBatchPdf(pdfParameters, organizationId);
            incomingRequest.put("report", ret);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Invoice print process failed!" + e.getMessage(), e);
        }

        return ret;
    }
}
