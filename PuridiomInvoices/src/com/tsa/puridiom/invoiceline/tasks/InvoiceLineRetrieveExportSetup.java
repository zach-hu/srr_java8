package com.tsa.puridiom.invoiceline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.account.tasks.AccountRetrieveByLine;
import com.tsa.puridiom.address.tasks.AddressRetrieveById;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.InvoiceLineExport;
import com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceLineRetrieveExportSetup extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List newInvoiceLineExportList = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");

			if (invoiceLineList != null && invoiceLineList.size() > 0)
			{
				for (int i=0; i<invoiceLineList.size(); i++)
				{
					InvoiceLine invoiceLine = (InvoiceLine)invoiceLineList.get(i);

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

						if (accountLineList != null && accountLineList.size() > 0)
						{
							Account account = (Account)accountLineList.get(0);
							if (account.getFld3().startsWith("16") || account.getFld3().startsWith("17"))
							{
								updateParameters.put("dbsession", dbs);
								updateParameters.put("Address_addressType", "ADDR");
								//updateParameters.put("Address_addressCode", invoiceHeader.getShipToCode());
								updateParameters.put("Address_addressCode", account.getFld2());

								AddressRetrieveById taskAddress = new AddressRetrieveById();
								Address address = (Address)taskAddress.executeTask(updateParameters);

								InvoiceLineExport invoiceLineExport = new InvoiceLineExport();
								invoiceLineExport.setFld1(account.getFld1());
								invoiceLineExport.setFld2(account.getFld2());
								invoiceLineExport.setFld3(account.getFld3());
								invoiceLineExport.setLineTotal(invoiceLine.getLineTotal());
								invoiceLineExport.setVendorId(invoiceHeader.getVendorId());
								invoiceLineExport.setVendorName(invoiceHeader.getVendorName());
								invoiceLineExport.setInvoiceNumber(invoiceLine.getInvoiceNumber());
								invoiceLineExport.setInvoiceDate(invoiceHeader.getInvoiceDate());
								invoiceLineExport.setDescription(invoiceLine.getDescription());
								if (address != null)
								{
									invoiceLineExport.setState(address.getState());
									invoiceLineExport.setCity(address.getCity());
									invoiceLineExport.setCounty(address.getAddressLine4());
								}
								//invoiceLineExport.setShipToCode(invoiceHeader.getShipToCode());
								invoiceLineExport.setShipToCode(account.getFld2());
								invoiceLineExport.setMajorCategory(invoiceLine.getCommodity());
								invoiceLineExport.setSubCategory(invoiceLine.getCommodity());
								newInvoiceLineExportList.add(invoiceLineExport);

								invoiceLine.setAssetExported("Y");
								InvoiceLineUpdateById invoiceLineUpdate = new InvoiceLineUpdateById();
								updateParameters.put("invoiceLine", invoiceLine);
								invoiceLineUpdate.executeTask(updateParameters);
							}
						}
					}
				}
			}

			result = newInvoiceLineExportList;
			this.setStatus(dbs.getStatus());
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