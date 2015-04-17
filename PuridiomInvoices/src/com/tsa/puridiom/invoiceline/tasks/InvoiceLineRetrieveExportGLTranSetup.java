package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.account.tasks.AccountRetrieveByLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.InvoiceLineExport;
import com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceLineRetrieveExportGLTranSetup extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List newInvoiceLineExportListGL1 = new ArrayList();
		List newInvoiceLineExportListGL2 = new ArrayList();
		List newInvoiceLineExportListGL2B = new ArrayList();

		InvoiceLineUpdateById invoiceLineUpdateGL1 = new InvoiceLineUpdateById();
		InvoiceLineUpdateById invoiceLineUpdateGL2A = new InvoiceLineUpdateById();
		InvoiceLineUpdateById invoiceLineUpdateGL2B = new InvoiceLineUpdateById();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List invoiceLineListGL1 = (List)incomingRequest.get("invoiceLineListGL1");
			List invoiceLineListGL2A = (List)incomingRequest.get("invoiceLineListGL2A");
			List invoiceLineListGL2B = (List)incomingRequest.get("invoiceLineListGL2B");

			//			case2: If the status of the invoice is 6010 and first flag is N
			if (invoiceLineListGL1 != null && invoiceLineListGL1.size() > 0)
			{
				for (int i=0; i<invoiceLineListGL1.size(); i++)
				{
					InvoiceLine invoiceLine = (InvoiceLine)invoiceLineListGL1.get(i);

					Map updateParameters = new HashMap();

					updateParameters.put("dbsession", dbs);
					updateParameters.put("InvoiceHeader_icIvcHeader", invoiceLine.getIcIvcHeader().toString());

					InvoiceHeaderRetrieveById taskInvoice = new InvoiceHeaderRetrieveById();
					InvoiceHeader invoiceHeader = (InvoiceHeader)taskInvoice.executeTask(updateParameters);

					if (invoiceHeader != null && invoiceHeader.getStatus().equals(DocumentStatus.IVC_APPROVED))
					{
						updateParameters.put("dbsession", dbs);
						updateParameters.put("Account_icHeader", invoiceLine.getIcIvcHeader().toString());
						updateParameters.put("Account_icLine", invoiceLine.getIcIvcLine().toString());

						AccountRetrieveByLine taskAccount = new AccountRetrieveByLine();
						List accountLineList = (List)taskAccount.executeTask(updateParameters);

						if (accountLineList == null || accountLineList.size() == 0)
						{
							updateParameters.put("Account_icLine", "0");
							accountLineList = (List)taskAccount.executeTask(updateParameters);
						}
						InvoiceLineExport invoiceLineExport = new InvoiceLineExport();
						if (accountLineList != null && accountLineList.size() > 0)
						{
							Account account = (Account)accountLineList.get(0);
							invoiceLineExport.setFld1(account.getFld1());
							invoiceLineExport.setFld2(account.getFld2());
							invoiceLineExport.setFld3(account.getFld3());
						}

						BigDecimal lineTotalExport = HiltonUtility.ckNull(invoiceHeader.getInvoiceTotal().subtract(invoiceHeader.getInvoiceAdjustment()));
						invoiceLineExport.setLineTotal(lineTotalExport);
						invoiceLineExport.setVendorName(invoiceHeader.getVendorName());
						invoiceLineExport.setInvoiceNumber(invoiceLine.getInvoiceNumber());
						newInvoiceLineExportListGL1.add(invoiceLineExport);

						invoiceLine.setExportFlag1("Y");
						updateParameters.put("invoiceLine", invoiceLine);
						invoiceLineUpdateGL1.executeTask(updateParameters);
					}
				}
			}

			//			case3: If the status of the invoice is 6030 AND the first flag is Y , BUT the second flag is N,
			if (invoiceLineListGL2A != null && invoiceLineListGL2A.size() > 0)
			{
				for (int i=0; i<invoiceLineListGL2A.size(); i++)
				{
					InvoiceLine invoiceLine = (InvoiceLine)invoiceLineListGL2A.get(i);

					Map updateParameters = new HashMap();

					updateParameters.put("dbsession", dbs);
					updateParameters.put("InvoiceHeader_icIvcHeader", invoiceLine.getIcIvcHeader().toString());

					InvoiceHeaderRetrieveById taskInvoice = new InvoiceHeaderRetrieveById();
					InvoiceHeader invoiceHeader = (InvoiceHeader)taskInvoice.executeTask(updateParameters);

					if (invoiceHeader != null && invoiceHeader.getStatus().equals(DocumentStatus.IVC_PAID))
					{
						updateParameters.put("dbsession", dbs);
						updateParameters.put("Account_icHeader", invoiceLine.getIcIvcHeader().toString());
						updateParameters.put("Account_icLine", invoiceLine.getIcIvcLine().toString());

						AccountRetrieveByLine taskAccount = new AccountRetrieveByLine(); //retrieve account from line. if it is empty retrieve from header
						List accountLineList = (List)taskAccount.executeTask(updateParameters);

						if (accountLineList == null || accountLineList.size() == 0)
						{
							updateParameters.put("Account_icLine", "0");
							accountLineList = (List)taskAccount.executeTask(updateParameters);
						}
						InvoiceLineExport invoiceLineExport = new InvoiceLineExport();
						if (accountLineList != null && accountLineList.size() > 0)
						{
							Account account = (Account)accountLineList.get(0);
							invoiceLineExport.setFld1(account.getFld1());
							invoiceLineExport.setFld2(account.getFld2());
							invoiceLineExport.setFld3(account.getFld3());
						}
						invoiceLineExport.setLineTotal(invoiceLine.getLineTotal());
						invoiceLineExport.setVendorName(invoiceHeader.getVendorName());
						invoiceLineExport.setInvoiceNumber(invoiceLine.getInvoiceNumber());
						newInvoiceLineExportListGL2.add(invoiceLineExport);

						BigDecimal lineTotalExport = HiltonUtility.ckNull(invoiceHeader.getInvoiceTotal().
								subtract(invoiceHeader.getInvoiceAdjustment()));
						BigDecimal checkAmount = HiltonUtility.ckNull(invoiceHeader.getInvoiceTotal().
								subtract(invoiceHeader.getInvoiceAdjustment().subtract(invoiceHeader.getInvoiceDiscount())));
						BigDecimal discountAmount = HiltonUtility.ckNull(invoiceHeader.getInvoiceDiscount());

						invoiceLineExport.setLineTotal(lineTotalExport);
						invoiceLineExport.setCheckAmount(checkAmount);
						invoiceLineExport.setDiscountAmount(discountAmount);

						invoiceLine.setExportFlag2("Y");
						updateParameters.put("invoiceLine", invoiceLine);
						invoiceLineUpdateGL2A.executeTask(updateParameters);
					}
				}
			}

			//			case 4:  If the status of the invoice is 6030 AND the first flag is N AND the second flag is N
			if (invoiceLineListGL2B != null && invoiceLineListGL2B.size() > 0)
			{
				for (int i=0; i<invoiceLineListGL2B.size(); i++)
				{
					InvoiceLine invoiceLine = (InvoiceLine)invoiceLineListGL2B.get(i);

					Map updateParameters = new HashMap();

					updateParameters.put("dbsession", dbs);
					updateParameters.put("InvoiceHeader_icIvcHeader", invoiceLine.getIcIvcHeader().toString());

					InvoiceHeaderRetrieveById taskInvoice = new InvoiceHeaderRetrieveById();
					InvoiceHeader invoiceHeader = (InvoiceHeader)taskInvoice.executeTask(updateParameters);

					if (invoiceHeader != null && invoiceHeader.getStatus().equals(DocumentStatus.IVC_PAID))
					{
						updateParameters.put("dbsession", dbs);
						updateParameters.put("Account_icHeader", invoiceLine.getIcIvcHeader().toString());
						updateParameters.put("Account_icLine", invoiceLine.getIcIvcLine().toString());

						AccountRetrieveByLine taskAccount = new AccountRetrieveByLine(); //retrieve account from line. if it is empty retrieve from header
						List accountLineList = (List)taskAccount.executeTask(updateParameters);

						if (accountLineList == null || accountLineList.size() == 0)
						{
							updateParameters.put("Account_icLine", "0");
							accountLineList = (List)taskAccount.executeTask(updateParameters);
						}
						InvoiceLineExport invoiceLineExport = new InvoiceLineExport();
						if (accountLineList != null && accountLineList.size() > 0)
						{
							Account account = (Account)accountLineList.get(0);
							invoiceLineExport.setFld1(account.getFld1());
							invoiceLineExport.setFld2(account.getFld2());
							invoiceLineExport.setFld3(account.getFld3());
						}

						BigDecimal lineTotalExport = HiltonUtility.ckNull(invoiceHeader.getInvoiceTotal().
								subtract(invoiceHeader.getInvoiceAdjustment()));
						BigDecimal checkAmount = HiltonUtility.ckNull(invoiceHeader.getInvoiceTotal().
								subtract(invoiceHeader.getInvoiceAdjustment().subtract(invoiceHeader.getInvoiceDiscount())));
						BigDecimal discountAmount = HiltonUtility.ckNull(invoiceHeader.getInvoiceDiscount());
						invoiceLineExport.setLineTotal(lineTotalExport);
						invoiceLineExport.setCheckAmount(checkAmount);
						invoiceLineExport.setDiscountAmount(discountAmount);
						invoiceLineExport.setVendorName(invoiceHeader.getVendorName());
						invoiceLineExport.setInvoiceNumber(invoiceLine.getInvoiceNumber());
						newInvoiceLineExportListGL2B.add(invoiceLineExport);

						invoiceLine.setExportFlag1("Y");
						invoiceLine.setExportFlag2("Y");
						updateParameters.put("invoiceLine", invoiceLine);
						invoiceLineUpdateGL2B.executeTask(updateParameters);
					}
				}
			}

			incomingRequest.put("newInvoiceLineExportListGL1", newInvoiceLineExportListGL1);
			incomingRequest.put("newInvoiceLineExportListGL2", newInvoiceLineExportListGL2);
			incomingRequest.put("newInvoiceLineExportListGL2B", newInvoiceLineExportListGL2B);

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.getMessage());
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}