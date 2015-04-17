package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.Map;

public class InvoiceMapOrderUdfs extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			String udf1 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf01","") ;
			String udf2 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf02","") ;
			String udf3 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf03","") ;
			String udf4 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf04","") ;
			String udf5 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf05","") ;
			String udf6 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf06","") ;
			String udf7 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf07","") ;
			String udf8 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf08","") ;
			String udf9 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf09","") ;
			String udf10 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf10","") ;

			if (! Utility.isEmpty(udf1)) {
				udf1 = "InvoiceHeader_" + udf1;
				incomingRequest.put(udf1, poHeader.getUdf1Code());
			}
			if (! Utility.isEmpty(udf2)) {
				udf2 = "InvoiceHeader_" + udf2;
				incomingRequest.put(udf2, poHeader.getUdf2Code());
			}
			if (! Utility.isEmpty(udf3)) {
				udf3 = "InvoiceHeader_" + udf3;
				incomingRequest.put(udf3, poHeader.getUdf3Code());
			}
			if (! Utility.isEmpty(udf4)) {
				udf4 = "InvoiceHeader_" + udf4;
				incomingRequest.put(udf4, poHeader.getUdf4Code());
			}
			if (! Utility.isEmpty(udf5)) {
				udf5 = "InvoiceHeader_" + udf5;
				incomingRequest.put(udf5, poHeader.getUdf5Code());
			}
			if (! Utility.isEmpty(udf6)) {
				udf6 = "InvoiceHeader_" + udf6;
				incomingRequest.put(udf6, poHeader.getUdf6Code());
			}
			if (! Utility.isEmpty(udf7)) {
				udf7 = "InvoiceHeader_" + udf7;
				incomingRequest.put(udf7, poHeader.getUdf7Code());
			}
			if (! Utility.isEmpty(udf8)) {
				udf8 = "InvoiceHeader_" + udf8;
				incomingRequest.put(udf8, poHeader.getUdf8Code());
			}
			if (! Utility.isEmpty(udf9)) {
				udf9 = "InvoiceHeader_" + udf9;
				incomingRequest.put(udf9, poHeader.getUdf9Code());
			}
			if (! Utility.isEmpty(udf10)) {
				udf10 = "InvoiceHeader_" + udf10;
				incomingRequest.put(udf10, poHeader.getUdf10Code());
			}

		    this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null;
	}

}