/*
 * Created on August 17, 2004
 */
package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author kat
 */
public class InvoiceHeaderRetrieveShipToAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			if (invoiceHeader == null)
			{
				throw new Exception("The InvoiceHeader entity was not found.");
			}
			String shipToCode = invoiceHeader.getShipToCode();

			incomingRequest.put("Address_addressCode", shipToCode);
			incomingRequest.put("Address_addressType", "ADDR");

			if (invoiceHeader.getIcPoHeader() != null && (invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0))>0))
			{
				incomingRequest.put("PoHeader_icPoHeader", invoiceHeader.getIcPoHeader().toString());
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-by-id.xml");
				process.executeProcess(incomingRequest);

				PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
				if(poHeader == null)
				{
					poHeader = new PoHeader();
				}
				if (poHeader != null && poHeader.getRequisitionNumber().equals(invoiceHeader.getShipToCode()))
				{
					incomingRequest.put("Address_addressType", "SHIPTO");
				}
				if(poHeader != null)
				{
					incomingRequest.put("PoHeader_status", poHeader.getStatus());
					incomingRequest.put("PoHeader_udf4Code", poHeader.getUdf4Code());
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
