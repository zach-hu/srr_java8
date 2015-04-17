package com.tsa.puridiom.invoiceline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvoiceLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvoiceLine as invoiceline where 1=1 ");
		if(incomingRequest.containsKey("InvoiceLine_icIvcHeader"))
		{
			String icIvcHeader = (String) incomingRequest.get("InvoiceLine_icIvcHeader");
			queryString.append(" AND invoiceline.icIvcHeader = '" + icIvcHeader + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_icIvcLine"))
		{
			String icIvcLine = (String) incomingRequest.get("InvoiceLine_icIvcLine");
			queryString.append(" AND invoiceline.id.icIvcLine = '" + icIvcLine + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_icPoHeader"))
		{
			String icPoHeader = (String) incomingRequest.get("InvoiceLine_icPoHeader");
			queryString.append(" AND invoiceline.icPoHeader = '" + icPoHeader + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_icPoLine"))
		{
			String icPoLine = (String) incomingRequest.get("InvoiceLine_icPoLine");
			queryString.append(" AND invoiceline.icPoLine = '" + icPoLine + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_invoiceNumber"))
		{
			String invoiceNumber = (String) incomingRequest.get("InvoiceLine_invoiceNumber");
			queryString.append(" AND invoiceline.invoiceNumber = '" + invoiceNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_lineNumber"))
		{
			String lineNumber = (String) incomingRequest.get("InvoiceLine_lineNumber");
			queryString.append(" AND invoiceline.lineNumber = '" + lineNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("InvoiceLine_itemNumber");
			queryString.append(" AND invoiceline.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_description"))
		{
			String description = (String) incomingRequest.get("InvoiceLine_description");
			queryString.append(" AND invoiceline.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_quantity"))
		{
			String quantity = (String) incomingRequest.get("InvoiceLine_quantity");
			queryString.append(" AND invoiceline.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_umCode"))
		{
			String umCode = (String) incomingRequest.get("InvoiceLine_umCode");
			queryString.append(" AND invoiceline.umCode = '" + umCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_umFactor"))
		{
			String umFactor = (String) incomingRequest.get("InvoiceLine_umFactor");
			queryString.append(" AND invoiceline.umFactor = '" + umFactor + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_unitPrice"))
		{
			String unitPrice = (String) incomingRequest.get("InvoiceLine_unitPrice");
			queryString.append(" AND invoiceline.unitPrice = '" + unitPrice + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_taxable"))
		{
			String taxable = (String) incomingRequest.get("InvoiceLine_taxable");
			queryString.append(" AND invoiceline.taxable = '" + taxable + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_taxPercent"))
		{
			String taxPercent = (String) incomingRequest.get("InvoiceLine_taxPercent");
			queryString.append(" AND invoiceline.taxPercent = '" + taxPercent + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_taxAmount"))
		{
			String taxAmount = (String) incomingRequest.get("InvoiceLine_taxAmount");
			queryString.append(" AND invoiceline.taxAmount = '" + taxAmount + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_discountPercent"))
		{
			String discountPercent = (String) incomingRequest.get("InvoiceLine_discountPercent");
			queryString.append(" AND invoiceline.discountPercent = '" + discountPercent + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_discountAmount"))
		{
			String discountAmount = (String) incomingRequest.get("InvoiceLine_discountAmount");
			queryString.append(" AND invoiceline.discountAmount = '" + discountAmount + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_lineTotal"))
		{
			String lineTotal = (String) incomingRequest.get("InvoiceLine_lineTotal");
			queryString.append(" AND invoiceline.lineTotal = '" + lineTotal + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_status"))
		{
			String status = (String) incomingRequest.get("InvoiceLine_status");
			queryString.append(" AND invoiceline.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvoiceLine_commodity"))
		{
			String commodity = (String) incomingRequest.get("InvoiceLine_commodity");
			queryString.append(" AND invoiceline.commodity = '" + commodity + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}