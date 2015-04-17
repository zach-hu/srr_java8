package com.tsa.puridiom.invoiceline.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class InvoiceLineRetrieveExportGLTranPrint extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			List newInvoiceLineExportListGL1 = (List)incomingRequest.get("newInvoiceLineExportListGL1");
			List newInvoiceLineExportListGL2 = (List)incomingRequest.get("newInvoiceLineExportListGL2");
			List newInvoiceLineExportListGL2B = (List)incomingRequest.get("newInvoiceLineExportListGL2B");
			List jasperPrintList = new ArrayList();

			if (newInvoiceLineExportListGL1 == null)
				newInvoiceLineExportListGL1 = new ArrayList();
			if (newInvoiceLineExportListGL2 == null)
				newInvoiceLineExportListGL2 = new ArrayList();
			if (newInvoiceLineExportListGL2B == null)
				newInvoiceLineExportListGL2B = new ArrayList();

			HibernateQueryResultDataSource invoiceLineExportListGL1DS = new HibernateQueryResultDataSource(newInvoiceLineExportListGL1);
			HibernateQueryResultDataSource invoiceLineExportListGL2DS = new HibernateQueryResultDataSource(newInvoiceLineExportListGL2);
			HibernateQueryResultDataSource invoiceLineExportListGL2DSB = new HibernateQueryResultDataSource(newInvoiceLineExportListGL2B);

			String date = Dates.today("MMddyyyy", "");
			//SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
			String time = new SimpleDateFormat("HHmmss").format(new Date());
			String nameExport = "GL_" + date + "_" + time;

			Map parameters = new HashMap();
			parameters.put("organizationId", organizationId.toUpperCase());
			parameters.put("oid", organizationId.toUpperCase());
			parameters.put("invoiceLineExportListGL1DS", invoiceLineExportListGL1DS);
			parameters.put("invoiceLineExportListGL2DS", invoiceLineExportListGL2DS);
			parameters.put("invoiceLineExportListGL2DSB", invoiceLineExportListGL2DSB);
			parameters.put("jasperPrintList", jasperPrintList);
			parameters.put("webreport", "R");
			parameters.put("nameExport", "vchextract/" + nameExport + ".xls");
			parameters.put("namePdf", nameExport);
			parameters.put("formType", "exportGLTran-xls.jasper");

			JasperReportsHelper.printInvoiceLineExport(parameters);

			result = newInvoiceLineExportListGL1;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}