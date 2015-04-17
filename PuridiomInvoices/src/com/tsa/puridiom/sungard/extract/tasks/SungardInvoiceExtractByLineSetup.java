package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SungardInvoiceExtractByLineSetup extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		ArrayList	extractAccountList = new ArrayList() ;
		List	defaultAccountList = invoiceHeader.getAccountList();

		List	invoiceLineList = (List) incomingRequest.get("invoiceLineList");
		ExtractTemplate extractTemplate = (ExtractTemplate) incomingRequest.get("extractTemplate");

		for (int i = 0; i < invoiceLineList.size(); i++) {
		    InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
		    List accountList = invoiceLine.getAccountList();
	    	ArrayList acList = new ArrayList() ;

		    if (accountList == null || accountList.size() == 0) {
		        // No account allocations for the line... use default header allocation
		        if (defaultAccountList != null) {
		        	acList = this.copyAccountList(defaultAccountList) ;

		            BigDecimal	lineTotal = invoiceLine.getLineTotal();

		            for (int ia = 0; ia < acList.size(); ia++) {
		                Account account = (Account) acList.get(ia);
		                account.setAllocAmount(lineTotal.multiply(account.getAllocPercent().multiply(new BigDecimal(".01"))));
		            }
		        }
		    } else {
	        	acList = this.copyAccountList(accountList) ;
		    }
	        extractAccountList.add(i,acList) ;
		}

		incomingRequest.put("extractAccountList",extractAccountList) ;

		this.setStatus(Status.SUCCEEDED);

		return null;
	}

	private ArrayList copyAccountList(List originalList)
	{
		ArrayList newList = new ArrayList();
		for (int i = 0; i < originalList.size(); i++) {
            Account account = (Account) originalList.get(i);
            newList.add(copyAccount(account));
        }
		return newList ;
	}

	private Account copyAccount(Account originalAccount)
	{
		AccountPK	comp_id = new AccountPK();
		Account	account = new Account();

		comp_id.setIcHeader(originalAccount.getComp_id().getIcHeader());
		comp_id.setIcLine(originalAccount.getComp_id().getIcLine());
		comp_id.setSequence(originalAccount.getComp_id().getSequence());
		account.setAccountType(originalAccount.getAccountType());
		account.setFld1(originalAccount.getFld1());
		account.setFld2(originalAccount.getFld2());
		account.setFld3(originalAccount.getFld3());
		account.setFld4(originalAccount.getFld4());
		account.setFld5(originalAccount.getFld5());
		account.setFld6(originalAccount.getFld6());
		account.setFld7(originalAccount.getFld7());
		account.setFld8(originalAccount.getFld8());
		account.setFld9(originalAccount.getFld9());
		account.setFld10(originalAccount.getFld10());
		account.setFld11(originalAccount.getFld11());
		account.setFld12(originalAccount.getFld12());
		account.setFld13(originalAccount.getFld13());
		account.setFld14(originalAccount.getFld14());
		account.setFld15(originalAccount.getFld15());
		account.setAllocPercent(originalAccount.getAllocPercent());
		account.setAllocAmount(originalAccount.getAllocAmount());
		account.setAccountTitle(originalAccount.getAccountTitle());
		account.setDateEntered(new Date());
		account.setDateExpires(new Date());
		account.setStatus(originalAccount.getStatus());
		account.setOwner(originalAccount.getOwner());
		account.setAllocMethod(originalAccount.getAllocMethod());
		account.setAllocQty(originalAccount.getAllocQty());
		account.setRecQty(originalAccount.getRecQty());
		account.setIcLastRec(originalAccount.getIcLastRec());
		account.setComp_id(comp_id);
		account.setAllocAmount(originalAccount.getAllocAmount());

		return account ;
	}
}