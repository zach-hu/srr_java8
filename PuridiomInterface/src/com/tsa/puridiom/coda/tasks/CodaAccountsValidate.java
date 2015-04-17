package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse;
import com.coda.www.efinance.schemas.transaction.ChkAccCodeData;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceLocator;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.util.WSProxyCall;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import sun.util.logging.resources.logging;

public class CodaAccountsValidate extends Task{
	private String organizationId ;
	private String userId ;
	private String userTimeZone ;
	private String userDateFormat ;
	private String portAddress ;
	private String proxyAddress ;
	private String userName ;
	private String passWord ;
	private String companyCode ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		boolean isReq = false ;
		boolean isRfq = false ;
		boolean isPo = false ;
		boolean isInvoice = false ;

		int vType = 0 ;
		try
		{
			organizationId = (String) incomingRequest.get("organizationId");
            userId = (String) incomingRequest.get("userId");
            userTimeZone = (String) incomingRequest.get("userTimeZone");
            userDateFormat = (String) incomingRequest.get("userDateFormat");
			portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
			proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PROXYURL", "") ;
			userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
			passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
			companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;

			if (portAddress.length() > 0) {
				portAddress = portAddress + "/finance/transaction/account-11.2" ;
			}
			List accountList = null ;
			String handler = (String) incomingRequest.get("handler") ;
			isReq = handler.equalsIgnoreCase("RequisitionValidateHandler") ;
			isRfq = handler.equalsIgnoreCase("RfqValidateHandler") ;
			isPo = handler.equalsIgnoreCase("PoValidateHandler") ;
			isInvoice = handler.equalsIgnoreCase("InvoiceValidateHandler") ;
			String tranType = "" ;
			String resaleType = "" ;
			
			boolean validationRequisitionLine = false;
			if(incomingRequest.containsKey("valType") && ((String)incomingRequest.get("valType")).equals("REQUISITIONLINE"))
			{
				validationRequisitionLine = true;
			}

			if (isReq) {
				RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
				if(!validationRequisitionLine)
				{
					accountList = header.getAccountList();
				}else{
					accountList = new ArrayList();
				}
				List lineList = (List) incomingRequest.get("lineitems");
				for (int i = 0; i < lineList.size(); i++) {
					RequisitionLine reqLine = (RequisitionLine) lineList.get(i) ;
					accountList.addAll(reqLine.getAccountList()) ;
				}
				tranType = header.getUdf1Code() ;
				resaleType = header.getUdf3Code();
			} else if (isPo) {
				PoHeader header = (PoHeader) incomingRequest.get("header");
				accountList = header.getAccountList() ;
				List lineList = (List) incomingRequest.get("lineitems");
				for (int i = 0; i < lineList.size(); i++) {
					PoLine poLine = (PoLine) lineList.get(i) ;
					accountList.addAll(poLine.getAccountList()) ;
				}

				tranType = header.getUdf1Code() ;
				resaleType = header.getUdf3Code();
			} else if (isRfq) {
			} else if (isInvoice) {
			}

			if (accountList == null) accountList = new ArrayList() ;

			Log.debug(this, "companyCode=" + companyCode) ;
			Log.debug(this, "userName=" + userName + "/" + passWord) ;
			Log.debug(this, "port=" + portAddress) ;

			if (tranType == null) tranType = "" ;
			if (resaleType == null) resaleType = "" ;

			int elements = 8 ;
			if (tranType.toUpperCase().indexOf("RESALE") >= 0 && resaleType.toUpperCase().indexOf("PARTS") >= 0) {
				elements = 3;
			}
			List eList = checkForAccountErrors(accountList, elements, tranType, resaleType) ;
			String listText = "" ;
			for (int i = 0; i < eList.size(); i++) {
				listText = listText + "[" + (String) eList.get(i) + "] ";
			}

			incomingRequest.put("processedErrorText", listText) ;
			incomingRequest.put("processedValidateError", (eList.size() > 0)) ;
			incomingRequest.put("processedErrorList",eList) ;

		}
		catch (Exception e) {

		}
		this.setStatus(Status.SUCCEEDED) ;

		return result;
	}

	private List checkForAccountErrors(List accounts, int elements, String tranType, String resaleType)
	{
		List errorList = new ArrayList();

		if ((accounts != null) && (!accounts.isEmpty()) && elements >= 1)
		{
			for (Iterator iterator = accounts.iterator(); iterator.hasNext();)
			{
				Account account = (Account) iterator.next();

				String acFld1 = account.getFld1() ;
				String acLine = acFld("", acFld1) ;
				if (elements >= 2) acLine = acFld(acLine, account.getFld2()) ;
				if (elements >= 3) {
					String acFld3 = account.getFld3() ;
					if (acFld3 == null) acFld3 = "" ;
					if (acFld3.length() > 2) acFld3 = acFld3.substring(0, 2) ;
					acLine = acFld(acLine, acFld3) ;
				}
				String acFld4 = account.getFld4();
				if (acFld4 == null) acFld4 = "" ;
				if (acFld1.startsWith("4") || acFld1.startsWith("5")) {
					acLine = acLine + "." + acFld4 ;
				}
				String acFld5 = account.getFld5();
				if (acFld5 == null) acFld4 = "" ;
				if (acFld1.startsWith("40") ||
						acFld1.startsWith("41") ||
						acFld1.startsWith("42") ||
						acFld1.startsWith("43") ||
						acFld1.startsWith("44") ||
						acFld1.startsWith("45") ||
						acFld1.startsWith("46") ||
						acFld1.startsWith("50") ||
						acFld1.startsWith("51") ||
						acFld1.startsWith("52") ||
						acFld1.startsWith("53") ||
						acFld1.startsWith("54") ||
						acFld1.startsWith("55") ||
						acFld1.startsWith("56")
						) {
					acLine = acLine + "." + acFld5 ;
				}
				String acFld6 = account.getFld6();
				if (acFld6 == null) acFld6 = "" ;
				if (acFld1.startsWith("40") ||
						acFld1.startsWith("41") ||
						acFld1.startsWith("42") ||
						acFld1.startsWith("43") ||
						acFld1.startsWith("44") ||
						acFld1.startsWith("50") ||
						acFld1.startsWith("51") ||
						acFld1.startsWith("52") ||
						acFld1.startsWith("53") ||
						acFld1.startsWith("54")
						) {
					acLine = acLine + "." + acFld6 ;
				}

				String acFld7 = account.getFld7();
				if (acFld7 == null) acFld7 = "" ;
				if (acFld1.startsWith("40") ||
						acFld1.startsWith("41") ||
						acFld1.startsWith("42") ||
						acFld1.startsWith("43") ||
						acFld1.startsWith("44") ||
						acFld1.startsWith("50") ||
						acFld1.startsWith("51") ||
						acFld1.startsWith("52") ||
						acFld1.startsWith("53") ||
						acFld1.startsWith("54")
						) {
					acLine = acLine + "." + acFld7 ;
				}

				if (! isCodaAccountValid(acLine)) {
					errorList.add(acLine) ;
				}
			}
		}

		return errorList ;
	}

	private String acFld(String line, String fld) {

		if (! HiltonUtility.isEmpty(fld)) {
			if (line.length() > 0) line = line + "." ;
			line = line + fld ;
		}

		return line ;
	}

	private boolean isCodaAccountValid(String accountString) {
		boolean isValid = true ;
		try {
			AccountServiceSOAPBindingStub port = null ;
			if (HiltonUtility.isEmpty(proxyAddress)) {
				AccountServiceLocator locator = new AccountServiceLocator() ;
				// locator.getAccountServicePort(portAddress) ;
				// AccountServiceSOAPBindingStub port = (AccountServiceSOAPBindingStub)locator.getAccountServicePort() ;
				if (portAddress.length() > 0) {
					try {
						URL url = new URL(portAddress) ;
						port = (AccountServiceSOAPBindingStub) locator.getAccountServicePort(url) ;
					}
					catch (Exception E)
					{

					}
				}

				if (port == null) {
					port = (AccountServiceSOAPBindingStub)locator.getAccountServicePort() ;
				}


				// Set username and password
				port.setUsername(userName) ;
				port.setPassword(BlackBox.getDecrypt(passWord)) ;
			}
			CheckCodeRequest checkCodeRequest = new CheckCodeRequest() ;

			Log.debug(this, "Validating Coda account: " + accountString) ;
			ChkAccCodeData chkAccCodeData = new ChkAccCodeData() ;
			chkAccCodeData.setCmpCode(companyCode) ;
			chkAccCodeData.setAccountCode(accountString) ;
			chkAccCodeData.setCheckingType("checkaccode") ;
			chkAccCodeData.setCodeComplete(true) ;

			checkCodeRequest.setChkCodeData(chkAccCodeData) ;
			CheckCodeResponse checkCodeResponse = null ;
			if (! HiltonUtility.isEmpty(proxyAddress)) {
				WSProxyCall proxy = new WSProxyCall() ;
				Map parameters = new HashMap() ;
				parameters.put("serviceName", "com.coda.www.efinance.schemas.transaction.account_11_2.webservice.Account") ;
				parameters.put("serviceLocatorMethod", "getAccountServicePort") ;
				parameters.put("serviceMethod", "checkCode") ;
				parameters.put("serviceUrl", portAddress) ;
				parameters.put("paramObject",checkCodeRequest) ;
				parameters.put("userName", userName) ;
				parameters.put("userPassword", BlackBox.getDecrypt(passWord)) ;
				checkCodeResponse = (CheckCodeResponse) proxy.wsProxyCall(proxyAddress, parameters) ;
			} else {
				checkCodeResponse = port.checkCode(checkCodeRequest) ;
			}

			isValid = checkCodeResponse.getAnswer().isGood() ;
			Log.debug(this, "Coda Account Result: " + isValid) ;

			System.out.println("Is Good: " + isValid ) ;


		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isValid ;
	}

}