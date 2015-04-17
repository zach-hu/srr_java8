package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequest;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceLocator;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequest;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequestPostOptions;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequest;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse;
import com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequest;
import com.coda.www.efinance.schemas.inputext.DocumentWideData;
import com.coda.www.efinance.schemas.inputext.PostData;
import com.coda.www.efinance.schemas.transaction.Header;

import com.coda.www.efinance.schemas.transaction.Line;
import com.coda.www.efinance.schemas.transaction.Transaction;
import com.coda.www.efinance.schemas.transaction.TxnKey;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.util.WSProxyCall;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

public class CodaCheckPostInvoice extends Task{

	private String excludeGLList = "" ;
	private int accountSequence = 0 ;
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		this.setStatus(Status.SUCCEEDED) ;

		try
		{
			List resultList = new ArrayList() ; ;
			try {
				String organizationId = (String) incomingRequest.get("organizationId");
				String userId = (String) incomingRequest.get("userId") ;

				String codaLevel = (String) incomingRequest.get("codaLevel");

				String portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
				String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PROXYURL", "") ;
				String userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
				String passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
				String companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;
				String creditAccount = PropertiesManager.getInstance(organizationId).getProperty("CODA", "CREDITACCOUNT", "200100") ;

				for (int i = 1; i < 25; i++) {
					String glCodes = PropertiesManager.getInstance(organizationId).getProperty("CODA", "ExcludeGL" + i, "") ;
					if (HiltonUtility.isEmpty(glCodes)) {
						break ;
					}
					excludeGLList =  excludeGLList + "," + glCodes ;
				}

				UserManager userManager = UserManager.getInstance() ;
				UserProfile user = userManager.getUser(organizationId, userId) ;

				// Coda User code
				String userCode = user.getNameUdf3() ;
				Log.debug(this, "companyCode=" + companyCode) ;
				Log.debug(this, "userName=" + userName + "/" + passWord) ;
				Log.debug(this, "port=" + portAddress) ;

				if (portAddress.length() > 0) {
					portAddress = portAddress + "/finance/inputext/input-11.2" ;
				}

				InputServiceSOAPBindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					InputServiceLocator locator = new InputServiceLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (InputServiceSOAPBindingStub) locator.getInputServicePort(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (InputServiceSOAPBindingStub)locator.getInputServicePort() ;
					}

					// Set username and password
					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				InvoiceHeader header = (InvoiceHeader) incomingRequest.get("invoiceHeader");
				List lineList = (List) incomingRequest.get("invoiceLineList");
				for (int i = 0; i < lineList.size(); i++) {
					InvoiceLine invoiceLine = (InvoiceLine) lineList.get(i) ;
				}
				String vendorCoda = header.getApReference() ;
				String vendorName = header.getVendorName() ;
				String vendorId = header.getVendorId() ;

				String overRideCreditCode = header.getUdf5Code() ;
				if (! HiltonUtility.isEmpty(overRideCreditCode)) {
					creditAccount = overRideCreditCode ;
				}

				if (vendorCoda == null) vendorCoda = "" ;
				if (vendorName == null) vendorName = "" ;
				if (vendorName.length() > 30) {
					vendorName = vendorName.substring(0,30) ;
				}
				Date dateEntered = header.getDateEntered() ;
				Date invoiceDate = header.getInvoiceDate() ;

				CheckPostRequest  checkPostRequest = new CheckPostRequest() ;
				CheckPostRequestCheckPostOptions  checkPostOptions = new CheckPostRequestCheckPostOptions() ;
				checkPostOptions.setPostto("books") ;

				Transaction txn = new Transaction() ;
				Header hdr = new Header() ;
				PostData postData = new PostData() ;
				DocumentWideData docData = new DocumentWideData() ;

				docData.setExtRef1(header.getPoNumber());
				docData.setExtRef2(header.getInvoiceNumber()) ;

				postData.setDocumentWideData(docData) ;

				TxnKey txnKey = new TxnKey() ;
				txnKey.setCmpCode(companyCode) ;
				txnKey.setCode("APIN") ;

				hdr.setKey(txnKey) ;

				Calendar calDate = Calendar.getInstance() ;
				Calendar calInput = Calendar.getInstance() ;

				calDate.setTime(invoiceDate) ;
				calInput.setTime(dateEntered) ;

				String description = HiltonUtility.ckNull(vendorName);
				if (description.length() > 30) {
					description = description.substring(0, 30);
				}
				hdr.setDescription(description);
				hdr.setDate(calDate) ;

				hdr.setTimeStamp((short) 0) ;
				String yr = Integer.toString(calInput.get(Calendar.YEAR));
				String period = Integer.toString(calInput.get(Calendar.MONTH) + 1) ;
//				if (period.length() == 1) period = "0" + period ;

				hdr.setPeriod(yr + "/" + period) ;
				hdr.setInputDate(calInput) ;


// 				Should not specify userCode
//				if (HiltonUtility.isEmpty(userCode)) {
//					hdr.setUserCode(userCode) ;
//				}
//				hdr.setUserCode(userName) ;

				ArrayList lines = new ArrayList() ;

				ArrayList acHdrList = new ArrayList() ;
				acHdrList.add(header.getAccountList()) ;
				acHdrList.add( incomingRequest.get("taxAccountList") ) ;
				acHdrList.add( incomingRequest.get("usetaxAccountList")) ;
				acHdrList.add(incomingRequest.get("shippingAccountList")) ;
				acHdrList.add( incomingRequest.get("otherAccountList")) ;

				BigDecimal credAmount = new BigDecimal(0) ;
				// Add the Credit Line
				Line hln = this.buildCreditLine(header, creditAccount + "." + vendorCoda , vendorName, credAmount) ;
				lines.add(hln) ;

				int excludedCount = 0 ;
				BigDecimal invoiceTotal = header.getInvoiceTotal();
				BigDecimal invoiceAlloc = new BigDecimal(0) ;

				// Add Header Debit Lines
				for (int l = 0; l < acHdrList.size(); l++) {
					List achList = (List) acHdrList.get(l) ;
					if (achList == null) continue ;
					for (int a = 0; a < achList.size(); a++) {
						Account account = (Account) achList.get(a) ;
						invoiceAlloc = invoiceAlloc.add(account.getAllocAmount()) ;
						if (! inExcludeGLList(account.getFld1())) {
							Line ln = buildDebitLine(header, account, vendorName) ;
							credAmount = credAmount.add(ln.getDocValue()) ;
							lines.add(ln) ;
						} else {
							excludedCount++ ;
						}
					}
				}

				// Add Header Crebit Lines
				for (int i = 0; i < lineList.size(); i++) {
					InvoiceLine line = (InvoiceLine) lineList.get(i) ;
					List acList = new ArrayList() ;
					if (line.getAccountList().size() > 0) {
						acList = line.getAccountList() ;
						for (int a = 0; a < acList.size(); a++) {
							Account account = (Account) acList.get(a) ;
							invoiceAlloc = invoiceAlloc.add(account.getAllocAmount()) ;
							if (! inExcludeGLList(account.getFld1())) {
								Line ln = buildDebitLine(header, account, vendorName) ;
								credAmount = credAmount.add(ln.getDocValue()) ;
								lines.add(ln) ;
							} else {
								excludedCount++ ;
							}
						}
					}
				}

				// Convert to array for Coda
				Line[] lnArray = new Line[lines.size()] ;
				for (int i = 0; i < lines.size(); i++) {
					lnArray[i] = (Line)lines.get(i) ;
				}
				// Set Credit Doc Value
				lnArray[0].setDocValue(credAmount) ;

				txn.setHeader(hdr) ;
				txn.setLines(lnArray) ;

				checkPostRequest.setPostData(postData) ;
				checkPostRequest.setTransaction(txn) ;
				checkPostRequest.setCheckPostOptions(checkPostOptions) ;
				if (invoiceTotal.compareTo(invoiceAlloc) == 0) {
					if (lines.size() > 1 || excludedCount == 0) {
						PostResponse postResponse = null ;
						CheckPostResponse checkPostResponse = null ;
						if (! HiltonUtility.isEmpty(proxyAddress)) {
							WSProxyCall proxy = new WSProxyCall() ;
							Map parameters = new HashMap() ;
							parameters.put("serviceName", "com.coda.www.efinance.schemas.inputext.input_11_2.webservice.Input") ;
							parameters.put("serviceLocatorMethod", "getInputServicePort") ;
							parameters.put("serviceMethod", "checkPost") ;
							parameters.put("serviceUrl", portAddress) ;
							parameters.put("paramObject",checkPostRequest) ;
							parameters.put("userName", userName) ;
							parameters.put("userPassword", BlackBox.getDecrypt(passWord)) ;

		//					postResponse = (PostResponse) proxy.wsProxyCall(proxyAddress, parameters) ;
							Object resp = proxy.wsProxyCall(proxyAddress, parameters) ;
							incomingRequest.put("processedValidateError", false) ;
							if (resp instanceof String) {
								// Error
								incomingRequest.put("processedErrorText", (String) resp) ;
								incomingRequest.put("processedValidateError", true) ;
							}
						} else {
							checkPostResponse = (CheckPostResponse) port.checkPost(checkPostRequest) ;
						}
					}
				} else {
					incomingRequest.put("processedErrorText", "Invoice total (" + invoiceTotal.toString() + ") does not match allocated total (" + invoiceAlloc.toString() + ").") ;
					incomingRequest.put("processedValidateError", true) ;
				}

				Log.debug(this, "Invoice Post Result: " ) ;

			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setStatus(Status.FAILED) ;
				throw new TsaException("Invoice update to CODA failed! " + e.getMessage(),  e);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setStatus(Status.FAILED) ;
				throw new TsaException("Invoice update to CODA failed! " + e.getMessage(), e);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setStatus(Status.FAILED) ;
				throw new TsaException("Invoice update to CODA failed! " + e.getCause(), e);
			}

			result = resultList ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	private Line buildDebitLine(InvoiceHeader header, Account account, String vendorName) {
		Line ln = new Line() ;
		this.accountSequence++ ;
		ln.setNumber(this.accountSequence) ;
//		ln.setExtRef1(header.getPoNumber()) ;
//		ln.setExtRef2(header.getInvoiceNumber()) ;
		ln.setAccountCode(buildAccountCode(account, header)) ;
		ln.setDocValue(account.getAllocAmount()) ;

		ln.setDescription(vendorName) ;
		ln.setLineSense("Debit") ;
		return ln ;
	}

	private Line buildCreditLine(InvoiceHeader header, String accountString, String vendorName, BigDecimal value) {
		Line ln = new Line() ;
		this.accountSequence++ ;
		ln.setNumber(this.accountSequence) ;
//		ln.setExtRef1(header.getPoNumber()) ;
//		ln.setExtRef2(header.getInvoiceNumber()) ;

		ln.setAccountCode(accountString) ;
		ln.setDocValue(value) ;
		ln.setDescription(vendorName) ;
		ln.setLineSense("Credit") ;
		return ln ;
	}

	private String buildAccountCode(Account acc, InvoiceHeader header) {
		String result = "" ;
				String acFld1 = HiltonUtility.ckNull(acc.getFld1());
				result = acFld1 + "." +
				HiltonUtility.ckNull(acc.getFld2()) + "." ;

				String acFld3 = acc.getFld3() ;
				if (acFld3 == null) acFld3 = "" ;
				if (acFld3.length() > 2) acFld3 = acFld3.substring(0, 2) ;
				result = result + acFld3 ;

				String acFld4 = acc.getFld4();
				if (acFld4 == null) acFld4 = "" ;
				if (acFld1.startsWith("4") || acFld1.startsWith("5")) {
					result = result + "." + acFld4 ;
				} else {
					if (header.getUdf3Code().equalsIgnoreCase("PARTS") && ! header.getUdf1Code().equalsIgnoreCase("CONSUMABLES")) {
						result = result + "." ;
					} else {
						return result ;
					}
				}
				String acFld5 = acc.getFld5();
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
						acFld1.startsWith("56") ||
						(header.getUdf3Code().equalsIgnoreCase("PARTS") && ! header.getUdf1Code().equalsIgnoreCase("CONSUMABLES"))
						) {
					result = result + "." + acFld5 ;
				} else {
					return result ;
				}
				String acFld6 = acc.getFld6();
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
					result = result + "." + acFld6 ;
				} else {
					return result ;
				}

				String acFld7 = acc.getFld7();
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
					result = result + "." + acFld7 ;
				} else {
					return result ;
				}
// Fld 8 never
//				+ "." +
//				HiltonUtility.ckNull(acc.getFld4()) + "." +
//				HiltonUtility.ckNull(acc.getFld5()) + "." +
//				HiltonUtility.ckNull(acc.getFld6()) + "." +
//				HiltonUtility.ckNull(acc.getFld7()) + "." +
//				HiltonUtility.ckNull(acc.getFld8()) ;

		return result ;
	}
	private boolean inExcludeGLList(String glAcct) {
		return (excludeGLList.indexOf(glAcct) >= 0) ;
	}
}