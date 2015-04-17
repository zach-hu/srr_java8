package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceLineRetrieveExportPrint extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			List invoiceLineExportList = (List)incomingRequest.get("invoiceLineExportList");
			List jasperPrintList = new ArrayList();
			if (invoiceLineExportList == null)
				invoiceLineExportList = new ArrayList();

			HibernateQueryResultDataSource invoiceLineExportListDS = new HibernateQueryResultDataSource(invoiceLineExportList);

			String date = Dates.today("MMddyyyy", "");
			Date today = new Date();
			String time= new SimpleDateFormat("HHmmss").format(today);

			String nameExport = "FA_" + date + "_" + time;

			Map parameters = new HashMap();
			parameters.put("organizationId", organizationId.toUpperCase());
			parameters.put("oid", organizationId.toUpperCase());
			parameters.put("invoiceLineExportListDS", invoiceLineExportListDS);
			parameters.put("jasperPrintList", jasperPrintList);
			parameters.put("webreport", "R");
			parameters.put("nameExport", "vchextract/" + nameExport + ".xls");
			parameters.put("namePdf", nameExport);
			parameters.put("formType", "exportFixedAssets-xls.jasper");

			JasperReportsHelper.printInvoiceLineExport(parameters);

			result = invoiceLineExportList;

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