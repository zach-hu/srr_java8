/**
 * 
 */
package com.tsa.puridiom.common.documents;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class DocumentLineGetAccountsFromHeader extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("account-update-by-line.xml");

			String documentType = (String) incomingRequest.get("formtype");
			List documentLineList = (List) incomingRequest.get("lineItems");
			List accountHeaderList = (List) incomingRequest.get("accountHeaderList");
			BigDecimal allocAmountTotal = this.getAllocAmountTotal(accountHeaderList);

			for (Iterator it = documentLineList.iterator(); it.hasNext();)
			{
				Map newIncomingRequest = new HashMap();
				String formType = "";
				String accountIcHeader = "";
				String accountIcLine = "";
				String defaultReferenceType = "";
				BigDecimal lineTotal = new BigDecimal(0);

				if (documentType.equalsIgnoreCase("REQ"))
				{
					RequisitionLine requisitionLine = (RequisitionLine) it.next();

					newIncomingRequest.put("RequisitionHeader_icReqHeader", requisitionLine.getIcReqHeader().toString());
					newIncomingRequest.put("RequisitionLine_icReqLine", requisitionLine.getIcReqLine().toString());

					formType = "RQL";
					accountIcHeader = requisitionLine.getIcReqHeader().toString();
					accountIcLine = requisitionLine.getIcReqLine().toString();
					defaultReferenceType = "RQL";
					lineTotal = requisitionLine.getLineTotal();

				} else if (documentType.equalsIgnoreCase("PO"))
				{
					PoLine poLine = (PoLine) it.next();

					newIncomingRequest.put("PoHeader_icPoHeader", poLine.getIcPoHeader().toString());
					newIncomingRequest.put("PoLine_icPoLine", poLine.getIcPoLine().toString());

					formType = "POL";
					accountIcHeader = poLine.getIcPoHeader().toString();
					accountIcLine = poLine.getIcPoLine().toString();
					defaultReferenceType = "POL";
					lineTotal = poLine.getLineTotal();

				} else if (documentType.equalsIgnoreCase("IVC"))
				{
					InvoiceLine invoiceLine = (InvoiceLine) it.next();

					newIncomingRequest.put("InvoiceHeader_icIvcHeader", invoiceLine.getIcIvcHeader().toString());
					newIncomingRequest.put("InvoiceLine_icIvcLine", invoiceLine.getIcIvcLine().toString());

					formType = "IVL";
					accountIcHeader = invoiceLine.getIcIvcHeader().toString();
					accountIcLine = invoiceLine.getIcIvcLine().toString();
					defaultReferenceType = "IVL";
					lineTotal = invoiceLine.getLineTotal();
				}

				newIncomingRequest.put("formType", formType);
				newIncomingRequest.put("Account_icHeader", accountIcHeader);
				newIncomingRequest.put("Account_icLine", accountIcLine);

				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

				this.setAccountParameters(newIncomingRequest, accountHeaderList, lineTotal, allocAmountTotal, defaultReferenceType);

				process.executeProcess(newIncomingRequest);

				if (process.getStatus() == Status.FAILED)
				{
					throw new TsaException("Error loading Accounts from Header for Line Item " + accountIcLine);
				}

			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "DocumentLineGetAccountsFromHeader failed: " + e.getMessage());

			throw e;
		}

		return result;
	}

	public void setAccountParameters(Map incomingRequest, List accountHeaderList, BigDecimal lineTotal, BigDecimal allocAmountTotal, String referenceType)
	{
		String[] a_accountAllocMethod = new String[accountHeaderList.size()];
		String[] a_accountAccountTitle = new String[accountHeaderList.size()];
		String[] a_accountFld1 = new String[accountHeaderList.size()];
		String[] a_accountFld2 = new String[accountHeaderList.size()];
		String[] a_accountFld3 = new String[accountHeaderList.size()];
		String[] a_accountFld4 = new String[accountHeaderList.size()];
		String[] a_accountFld5 = new String[accountHeaderList.size()];
		String[] a_accountFld6 = new String[accountHeaderList.size()];
		String[] a_accountFld7 = new String[accountHeaderList.size()];
		String[] a_accountFld8 = new String[accountHeaderList.size()];
		String[] a_accountFld9 = new String[accountHeaderList.size()];
		String[] a_accountFld10 = new String[accountHeaderList.size()];
		String[] a_accountFld11 = new String[accountHeaderList.size()];
		String[] a_accountFld12 = new String[accountHeaderList.size()];
		String[] a_accountFld13 = new String[accountHeaderList.size()];
		String[] a_accountFld14 = new String[accountHeaderList.size()];
		String[] a_accountFld15 = new String[accountHeaderList.size()];
		String[] a_accountAllocPercent = new String[accountHeaderList.size()];
		String[] a_accountAllocAmount = new String[accountHeaderList.size()];
		String[] a_accountAllocQty = new String[accountHeaderList.size()];
		String[] a_accountType = new String[accountHeaderList.size()];

		for (int i = 0; i < accountHeaderList.size(); i++)
		{
			Account account = (Account) accountHeaderList.get(i);
			BigDecimal percent;
			String allocMethod = (account.getAllocMethod().startsWith("A")) ? "AL" : "PL";

			if (allocMethod.equals("AL"))
			{
				percent = account.getAllocAmount().divide(allocAmountTotal, 4, BigDecimal.ROUND_HALF_UP);
			} else
			{
				percent = account.getAllocPercent().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
			}

			a_accountAllocMethod[i] = allocMethod;
			a_accountAccountTitle[i] = account.getAccountTitle();
			a_accountFld1[i] = account.getFld1();
			a_accountFld2[i] = account.getFld2();
			a_accountFld3[i] = account.getFld3();
			a_accountFld4[i] = account.getFld4();
			a_accountFld5[i] = account.getFld5();
			a_accountFld6[i] = account.getFld6();
			a_accountFld7[i] = account.getFld7();
			a_accountFld8[i] = account.getFld8();
			a_accountFld9[i] = account.getFld9();
			a_accountFld10[i] = account.getFld10();
			a_accountFld11[i] = account.getFld11();
			a_accountFld12[i] = account.getFld12();
			a_accountFld13[i] = account.getFld13();
			a_accountFld14[i] = account.getFld14();
			a_accountFld15[i] = account.getFld15();
			a_accountAllocPercent[i] = account.getAllocPercent().toString();
			a_accountAllocAmount[i] = percent.multiply(lineTotal).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			a_accountAllocQty[i] = account.getAllocQty().toString();

			a_accountType[i] = referenceType;
		}

		incomingRequest.put("Account_allocMethod", a_accountAllocMethod);
		incomingRequest.put("Account_accountTitle", a_accountAccountTitle);
		incomingRequest.put("Account_fld1", a_accountFld1);
		incomingRequest.put("Account_fld2", a_accountFld2);
		incomingRequest.put("Account_fld3", a_accountFld3);
		incomingRequest.put("Account_fld4", a_accountFld4);
		incomingRequest.put("Account_fld5", a_accountFld5);
		incomingRequest.put("Account_fld6", a_accountFld6);
		incomingRequest.put("Account_fld7", a_accountFld7);
		incomingRequest.put("Account_fld8", a_accountFld8);
		incomingRequest.put("Account_fld9", a_accountFld9);
		incomingRequest.put("Account_fld10", a_accountFld10);
		incomingRequest.put("Account_fld11", a_accountFld11);
		incomingRequest.put("Account_fld12", a_accountFld12);
		incomingRequest.put("Account_fld13", a_accountFld13);
		incomingRequest.put("Account_fld14", a_accountFld14);
		incomingRequest.put("Account_fld15", a_accountFld15);
		incomingRequest.put("Account_allocPercent", a_accountAllocPercent);
		incomingRequest.put("Account_allocAmount", a_accountAllocAmount);
		incomingRequest.put("Account_allocQty", a_accountAllocQty);
		incomingRequest.put("Account_accountType", a_accountType);
	}

	private BigDecimal getAllocAmountTotal(List accountList)
	{
		BigDecimal allocAmountTotal = new BigDecimal(0);

		for (int i = 0; i < accountList.size(); i++)
		{
			Account account = (Account) accountList.get(i);
			allocAmountTotal = allocAmountTotal.add(account.getAllocAmount());
		}
		
		if (allocAmountTotal.compareTo(new BigDecimal(0)) == 0)
		{
			allocAmountTotal = new BigDecimal(1);
		}

		return allocAmountTotal;
	}
}
