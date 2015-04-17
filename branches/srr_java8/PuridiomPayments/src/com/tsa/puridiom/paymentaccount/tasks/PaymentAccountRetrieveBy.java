package com.tsa.puridiom.paymentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class PaymentAccountRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from PaymentAccount as paymentAccount where 1=1 ");

		if (incomingRequest.containsKey("PaymentAccount_icPayment"))
		{
			String icPayment= (String) incomingRequest.get("PaymentAccount_icPayment");
			queryString.append(" AND paymentAccount.icPayment = '" + icPayment + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_invoiceNumber"))
		{
			String invoiceNumber = (String) incomingRequest.get("PaymentAccount_invoiceNumber");
			queryString.append(" AND paymentAccount.invoiceNumber = '" + invoiceNumber + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_poNumber"))
		{
			String poNumber = (String) incomingRequest.get("PaymentAccount_poNumber");
			queryString.append(" AND paymentAccount.poNumber = '" + poNumber + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_releaseNumber"))
		{
			String releaseNumber = (String) incomingRequest.get("PaymentAccount_releaseNumber");
			queryString.append(" AND paymentAccount.releaseNumber = '" + releaseNumber + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_invoiceDate"))
		{
			String invoiceDate = (String) incomingRequest.get("PaymentAccount_invoiceDate");
			queryString.append(" AND paymentAccount.invoiceDate = '" + invoiceDate + "'");
		}
		//TSR YEAR
		//TSR MONTH
		if (incomingRequest.containsKey("PaymentAccount_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("PaymentAccount_vendorId");
			queryString.append(" AND paymentAccount.vendorId = '" + vendorId + "'");
		}
		//CAC CODE
		//ACCOUNT CODE
		//COST CENTER
		//UTIL CODE
		//ALLOC AMOUNT
		if (incomingRequest.containsKey("PaymentAccount_invoiceAmount"))
		{
			String invoiceAmount = (String) incomingRequest.get("PaymentAccount_invoiceAmount");
			queryString.append(" AND paymentAccount.invoiceAmount = '" + invoiceAmount + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_checkNumber"))
		{
			String checkNumber = (String) incomingRequest.get("PaymentAccount_checkNumber");
			queryString.append(" AND paymentAccount.checkNumber = '" + checkNumber + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_checkAmount"))
		{
			String checkAmount = (String) incomingRequest.get("PaymentAccount_checkAmount");
			queryString.append(" AND paymentAccount.checkAmount = '" + checkAmount + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_checkDate"))
		{
			String checkDate = (String) incomingRequest.get("PaymentAccount_checkDate");
			queryString.append(" AND paymentAccount.checkDate = '" + checkDate + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_cancelDate"))
		{
			String cancelDate = (String) incomingRequest.get("PaymentAccount_cancelDate");
			queryString.append(" AND paymentAccount.cancelDate = '" + cancelDate + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_voucherNumber"))
		{
			String voucherNumber = (String) incomingRequest.get("PaymentAccount_voucherNumber");
			queryString.append(" AND paymentAccount.voucherNumber = '" + voucherNumber + "'");
		}
		if (incomingRequest.containsKey("PaymentAccount_icPoHeader"))
		{
			String icPoHeader = (String) incomingRequest.get("PaymentAccount_icPoHeader");
			queryString.append(" AND paymentAccount.id.icPoHeader = '" + icPoHeader + "'");
		}
		//TSR FLAG
		//TRANS DATE
		//VOUCHER DESC
		if (incomingRequest.containsKey("PaymentAccount_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("PaymentAccount_dateEntered");
			queryString.append(" AND paymentAccount.id.dateEntered = '" + dateEntered + "'");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus());
		return result;
	}
}