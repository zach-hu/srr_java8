package com.tsa.puridiom.invoiceheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvoiceHeaderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvoiceHeader as invoiceheader where 1=1 ");
		if(incomingRequest.containsKey("InvoiceHeader_icIvcHeader"))
		{
			String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
			queryString.append(" AND invoiceheader.id.icIvcHeader = '" + icIvcHeader + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_icPoHeader"))
		{
			String icPoHeader = (String) incomingRequest.get("InvoiceHeader_icPoHeader");
			queryString.append(" AND invoiceheader.icPoHeader = '" + icPoHeader + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceNumber"))
		{
			String invoiceNumber = (String) incomingRequest.get("InvoiceHeader_invoiceNumber");
			queryString.append(" AND invoiceheader.invoiceNumber = '" + invoiceNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceDate"))
		{
			String invoiceDate = (String) incomingRequest.get("InvoiceHeader_invoiceDate");
			queryString.append(" AND invoiceheader.invoiceDate = '" + invoiceDate + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceTime"))
		{
			String invoiceTime = (String) incomingRequest.get("InvoiceHeader_invoiceTime");
			queryString.append(" AND invoiceheader.invoiceTime = '" + invoiceTime + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceDesc"))
		{
			String invoiceDesc = (String) incomingRequest.get("InvoiceHeader_invoiceDesc");
			queryString.append(" AND invoiceheader.invoiceDesc = '" + invoiceDesc + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceSubtotal"))
		{
			String invoiceSubtotal = (String) incomingRequest.get("InvoiceHeader_invoiceSubtotal");
			queryString.append(" AND invoiceheader.invoiceSubtotal = '" + invoiceSubtotal + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceDiscount"))
		{
			String invoiceDiscount = (String) incomingRequest.get("InvoiceHeader_invoiceDiscount");
			queryString.append(" AND invoiceheader.invoiceDiscount = '" + invoiceDiscount + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceTax"))
		{
			String invoiceTax = (String) incomingRequest.get("InvoiceHeader_invoiceTax");
			queryString.append(" AND invoiceheader.invoiceTax = '" + invoiceTax + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceShipping"))
		{
			String invoiceShipping = (String) incomingRequest.get("InvoiceHeader_invoiceShipping");
			queryString.append(" AND invoiceheader.invoiceShipping = '" + invoiceShipping + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceOther"))
		{
			String invoiceOther = (String) incomingRequest.get("InvoiceHeader_invoiceOther");
			queryString.append(" AND invoiceheader.invoiceOther = '" + invoiceOther + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceTotal"))
		{
			String invoiceTotal = (String) incomingRequest.get("InvoiceHeader_invoiceTotal");
			queryString.append(" AND invoiceheader.invoiceTotal = '" + invoiceTotal + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_specialInst"))
		{
			String specialInst = (String) incomingRequest.get("InvoiceHeader_specialInst");
			queryString.append(" AND invoiceheader.specialInst = '" + specialInst + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_status"))
		{
			String status = (String) incomingRequest.get("InvoiceHeader_status");
			queryString.append(" AND invoiceheader.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvoiceHeader_dateEntered");
			queryString.append(" AND invoiceheader.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_paymentDueDate"))
		{
			String paymentDueDate = (String) incomingRequest.get("InvoiceHeader_paymentDueDate");
			queryString.append(" AND invoiceheader.paymentDueDate = '" + paymentDueDate + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_termsCode"))
		{
			String termsCode = (String) incomingRequest.get("InvoiceHeader_termsCode");
			queryString.append(" AND invoiceheader.termsCode = '" + termsCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_termsDiscperc"))
		{
			String termsDiscperc = (String) incomingRequest.get("InvoiceHeader_termsDiscperc");
			queryString.append(" AND invoiceheader.termsDiscperc = '" + termsDiscperc + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_termsDiscdays"))
		{
			String termsDiscdays = (String) incomingRequest.get("InvoiceHeader_termsDiscdays");
			queryString.append(" AND invoiceheader.termsDiscdays = '" + termsDiscdays + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_terms2Discperc"))
		{
			String terms2Discperc = (String) incomingRequest.get("InvoiceHeader_terms2Discperc");
			queryString.append(" AND invoiceheader.terms2Discperc = '" + terms2Discperc + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_terms2Discdays"))
		{
			String terms2Discdays = (String) incomingRequest.get("InvoiceHeader_terms2Discdays");
			queryString.append(" AND invoiceheader.terms2Discdays = '" + terms2Discdays + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_termsNetdays"))
		{
			String termsNetdays = (String) incomingRequest.get("InvoiceHeader_termsNetdays");
			queryString.append(" AND invoiceheader.termsNetdays = '" + termsNetdays + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_poNumber"))
		{
			String poNumber = (String) incomingRequest.get("InvoiceHeader_poNumber");
			queryString.append(" AND invoiceheader.poNumber = '" + poNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_poRelease"))
		{
			String poRelease = (String) incomingRequest.get("InvoiceHeader_poRelease");
			queryString.append(" AND invoiceheader.poRelease = '" + poRelease + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_poDate"))
		{
			String poDate = (String) incomingRequest.get("InvoiceHeader_poDate");
			queryString.append(" AND invoiceheader.poDate = '" + poDate + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_poTotal"))
		{
			String poTotal = (String) incomingRequest.get("InvoiceHeader_poTotal");
			queryString.append(" AND invoiceheader.poTotal = '" + poTotal + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("InvoiceHeader_vendorId");
			queryString.append(" AND invoiceheader.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("InvoiceHeader_vendorName");
			queryString.append(" AND invoiceheader.vendorName = '" + vendorName + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_contactName"))
		{
			String contactName = (String) incomingRequest.get("InvoiceHeader_contactName");
			queryString.append(" AND invoiceheader.contactName = '" + contactName + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_contactEmail"))
		{
			String contactEmail = (String) incomingRequest.get("InvoiceHeader_contactEmail");
			queryString.append(" AND invoiceheader.contactEmail = '" + contactEmail + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_contactPhone"))
		{
			String contactPhone = (String) incomingRequest.get("InvoiceHeader_contactPhone");
			queryString.append(" AND invoiceheader.contactPhone = '" + contactPhone + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_contactFax"))
		{
			String contactFax = (String) incomingRequest.get("InvoiceHeader_contactFax");
			queryString.append(" AND invoiceheader.contactFax = '" + contactFax + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_shipToCode"))
		{
			String shipToCode = (String) incomingRequest.get("InvoiceHeader_shipToCode");
			queryString.append(" AND invoiceheader.shipToCode = '" + shipToCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_shipToContact"))
		{
			String shipToContact = (String) incomingRequest.get("InvoiceHeader_shipToContact");
			queryString.append(" AND invoiceheader.shipToContact = '" + shipToContact + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_billToCode"))
		{
			String billToCode = (String) incomingRequest.get("InvoiceHeader_billToCode");
			queryString.append(" AND invoiceheader.billToCode = '" + billToCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_billToContact"))
		{
			String billToContact = (String) incomingRequest.get("InvoiceHeader_billToContact");
			queryString.append(" AND invoiceheader.billToContact = '" + billToContact + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_fobCode"))
		{
			String fobCode = (String) incomingRequest.get("InvoiceHeader_fobCode");
			queryString.append(" AND invoiceheader.fobCode = '" + fobCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_pcardName"))
		{
			String pcardName = (String) incomingRequest.get("InvoiceHeader_pcardName");
			queryString.append(" AND invoiceheader.pcardName = '" + pcardName + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_pcardHolder"))
		{
			String pcardHolder = (String) incomingRequest.get("InvoiceHeader_pcardHolder");
			queryString.append(" AND invoiceheader.pcardHolder = '" + pcardHolder + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_pcardNumber"))
		{
			String pcardNumber = (String) incomingRequest.get("InvoiceHeader_pcardNumber");
			queryString.append(" AND invoiceheader.pcardNumber = '" + pcardNumber + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_pcardExpires"))
		{
			String pcardExpires = (String) incomingRequest.get("InvoiceHeader_pcardExpires");
			queryString.append(" AND invoiceheader.pcardExpires = '" + pcardExpires + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_eftBankName"))
		{
			String eftBankName = (String) incomingRequest.get("InvoiceHeader_eftBankName");
			queryString.append(" AND invoiceheader.eftBankName = '" + eftBankName + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_eftBankAccount"))
		{
			String eftBankAccount = (String) incomingRequest.get("InvoiceHeader_eftBankAccount");
			queryString.append(" AND invoiceheader.eftBankAccount = '" + eftBankAccount + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_eftAbaAch"))
		{
			String eftAbaAch = (String) incomingRequest.get("InvoiceHeader_eftAbaAch");
			queryString.append(" AND invoiceheader.eftAbaAch = '" + eftAbaAch + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_eftAbaWire"))
		{
			String eftAbaWire = (String) incomingRequest.get("InvoiceHeader_eftAbaWire");
			queryString.append(" AND invoiceheader.eftAbaWire = '" + eftAbaWire + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_eftHolderAccount"))
		{
			String eftHolderAccount = (String) incomingRequest.get("InvoiceHeader_eftHolderAccount");
			queryString.append(" AND invoiceheader.eftHolderAccount = '" + eftHolderAccount + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_federalId"))
		{
			String federalId = (String) incomingRequest.get("InvoiceHeader_federalId");
			queryString.append(" AND invoiceheader.federalId = '" + federalId + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_orderByName"))
		{
			String orderByName = (String) incomingRequest.get("InvoiceHeader_orderByName");
			queryString.append(" AND invoiceheader.orderByName = '" + orderByName + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_orderByEmail"))
		{
			String orderByEmail = (String) incomingRequest.get("InvoiceHeader_orderByEmail");
			queryString.append(" AND invoiceheader.orderByEmail = '" + orderByEmail + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_orderByPhone"))
		{
			String orderByPhone = (String) incomingRequest.get("InvoiceHeader_orderByPhone");
			queryString.append(" AND invoiceheader.orderByPhone = '" + orderByPhone + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_ownerEmail"))
		{
			String ownerEmail = (String) incomingRequest.get("InvoiceHeader_ownerEmail");
			queryString.append(" AND invoiceheader.ownerEmail = '" + ownerEmail + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_enteredBy"))
		{
			String enteredBy = (String) incomingRequest.get("InvoiceHeader_enteredBy");
			queryString.append(" AND invoiceheader.enteredBy = '" + enteredBy + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_prePaid"))
		{
			String prePaid = (String) incomingRequest.get("InvoiceHeader_prePaid");
			queryString.append(" AND invoiceheader.prePaid = '" + prePaid + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("InvoiceHeader_udf1Code");
			queryString.append(" AND invoiceheader.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("InvoiceHeader_udf2Code");
			queryString.append(" AND invoiceheader.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("InvoiceHeader_udf3Code");
			queryString.append(" AND invoiceheader.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("InvoiceHeader_udf4Code");
			queryString.append(" AND invoiceheader.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("InvoiceHeader_udf5Code");
			queryString.append(" AND invoiceheader.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("InvoiceHeader_udf6Code");
			queryString.append(" AND invoiceheader.udf6Code = '" + udf6Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("InvoiceHeader_udf7Code");
			queryString.append(" AND invoiceheader.udf7Code = '" + udf7Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("InvoiceHeader_udf8Code");
			queryString.append(" AND invoiceheader.udf8Code = '" + udf8Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("InvoiceHeader_udf9Code");
			queryString.append(" AND invoiceheader.udf9Code = '" + udf9Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("InvoiceHeader_udf10Code");
			queryString.append(" AND invoiceheader.udf10Code = '" + udf10Code + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceRejecting"))
		{
			String invoiceRejecting = (String) incomingRequest.get("InvoiceHeader_invoiceRejecting");
			queryString.append(" AND invoiceheader.invoiceRejecting = '" + invoiceRejecting + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_reasonCode"))
		{
			String reasonCode = (String) incomingRequest.get("InvoiceHeader_reasonCode");
			queryString.append(" AND invoiceheader.reasonCode = '" + reasonCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_apReference"))
		{
			String apReference = (String) incomingRequest.get("InvoiceHeader_apReference");
			queryString.append(" AND invoiceheader.apReference = '" + apReference + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceType"))
		{
			String invoiceType = (String) incomingRequest.get("InvoiceHeader_invoiceType");
			queryString.append(" AND invoiceheader.invoiceType = '" + invoiceType + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_vendorAddrCode"))
		{
			String vendorAddrCode = (String) incomingRequest.get("InvoiceHeader_vendorAddrCode");
			queryString.append(" AND invoiceheader.vendorAddrCode = '" + vendorAddrCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_lastExtract"))
		{
			String lastExtract = (String) incomingRequest.get("InvoiceHeader_lastExtract");
			queryString.append(" AND invoiceheader.lastExtract = '" + lastExtract + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoiceReceivedDate"))
		{
			String invoiceReceivedDate = (String) incomingRequest.get("InvoiceHeader_invoiceReceivedDate");
			queryString.append(" AND invoiceheader.invoiceReceivedDate = '" + invoiceReceivedDate + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_invoicePrintedDate"))
		{
			String invoicePrintedDate = (String) incomingRequest.get("InvoiceHeader_invoicePrintedDate");
			queryString.append(" AND invoiceheader.invoicePrintedDate = '" + invoicePrintedDate + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_currencyCode"))
		{
			String currencyCode = (String) incomingRequest.get("InvoiceHeader_currencyCode");
			queryString.append(" AND invoiceheader.currencyCode = '" + currencyCode + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_currencyFactor"))
		{
			String currencyFactor = (String) incomingRequest.get("InvoiceHeader_currencyFactor");
			queryString.append(" AND invoiceheader.currencyFactor = '" + currencyFactor + "'");
		}
		if(incomingRequest.containsKey("InvoiceHeader_fiscalYear"))
		{
			String fiscalYear = (String) incomingRequest.get("InvoiceHeader_fiscalYear");
			queryString.append(" AND invoiceheader.fiscalYear = '" + fiscalYear + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
