package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.selector.selector_1_0.webservice.*;
import com.coda.www.efinance.schemas.selectormaster.DetailsLocation;
import com.coda.www.efinance.schemas.selectormaster.VocListDataElement;
import com.coda.www.efinance.schemas.selector.*;

import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.ReportDates;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.util.WSProxyCall;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import com.tsa.puridiom.common.utility.BlackBox;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

public class CodaRetrieveSelector extends Task{
	public synchronized  Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List resultList = new ArrayList() ; ;
			try {
				String organizationId = (String) incomingRequest.get("organizationId");
	            String userId = (String) incomingRequest.get("userId");
	            String userTimeZone = (String) incomingRequest.get("userTimeZone");
	            String userDateFormat = (String) incomingRequest.get("userDateFormat");
				String invoiceNumber = (String) incomingRequest.get("invoiceNumber");
				String poNumber = (String) incomingRequest.get("poNumber");
				String fromTestApp = (String) incomingRequest.get("fromTestApp") ;
	            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");

	            if (fromTestApp == null) fromTestApp = "N" ;

				String portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
				String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PROXYURL", "") ;
				String userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
				String passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
				String companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;

				if (fromTestApp.equalsIgnoreCase("yes")) proxyAddress = "";

				// Define column list
//				String[] columns = {"vocab_doccode", "vocab ref1", "vocab_ref2",
//						"Line description", "Document header description", "Document value",
//						"Document date", "Document status", "Document number", "Pay status",
//						"User reference 1", "User reference 2", "User reference 3",
//						"matching Reference" } ;
//				String[] columns = {"vocab_doccode", "vocab_ref1", "vocab_ref2",
//						"vocab_descr", "vocab_doc_descr", "vocab_valuedoc",
//						"vocab_docdate", "vocab_docstat", "vocab_docnum", "vocab_statpay",
//						"vocab_pay_user_ref1", "vocab_pay_user_ref3", "vocab_pay_user_ref3",
//						"vocab_matching_ref"} ;
				String[] columns = {"vocab_doccode", "vocab_ref1", "vocab_ref2",
						 "vocab_statpay",
						"vocab_pay_user_ref1", "vocab_pay_user_ref2", "vocab_pay_user_ref3",
						"vocab_matching_ref"} ;

				if (portAddress.length() > 0) {
					portAddress = portAddress + "/finance/selector/selector-1.0" ;
				}
				SelectorServiceSOAPBindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					SelectorServiceLocator locator = new SelectorServiceLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (SelectorServiceSOAPBindingStub) locator.getSelectorServicePort(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (SelectorServiceSOAPBindingStub)locator.getSelectorServicePort() ;
					}

					// Set username and password
					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				SelectDetailsRequest selectRequest = new SelectDetailsRequest() ;
				VocListDataElement[] userFilters = new VocListDataElement[0];
				String[] fldNames = {"vocab_ref1", "vocab_ref2", "vocab_doccode"} ;
				String[] operators = {"op_eq", "op_eq", "op_eq", "op_eq", "op_eq", "op_eq"  } ;
				String[] dataValues = {poNumber, invoiceNumber, "APIN", "APWR", "APEF", "APQP"} ;
				String[] parens = {"none", "none", "none", "none", "none", "none"  } ;

				VocListDataElement[] appFilters = new VocListDataElement[fldNames.length];
				VocListDataElement appFilter = null ;

				for (int i= 0; i < fldNames.length; i++) {
					appFilter = new VocListDataElement() ;
					// Build filter
					if (parens[i].startsWith("left")) {
						appFilter.setOpenBracket(parens[i]) ;
					}
					appFilter.setVocabID(fldNames[i]) ;
//					appFilter1.setModify("Allow");
//					appFilter.setLogical(logicals[i]) ;
					appFilter.setFrom(dataValues[i]) ;
					appFilter.setOperatorID(operators[i]) ;
					if (parens[i].startsWith("right")) {
						appFilter.setCloseBracket(parens[i]) ;
					}
					appFilters[i] = appFilter ;
				}

				selectRequest.setApplicationFilter(appFilters) ;
				selectRequest.setUserFilter(userFilters) ;
				selectRequest.setWholeDocuments(true) ;
				selectRequest.setColumns(columns) ;

				DetailsLocation detailsLocation = new DetailsLocation() ;
				detailsLocation.setBooks(true) ;
				selectRequest.setSelType(detailsLocation) ;
				selectRequest.setGslCode("APIN") ;

				SelectDetailsResponse selectResponse = null;

				if (! HiltonUtility.isEmpty(proxyAddress)) {
					WSProxyCall proxy = new WSProxyCall() ;
					Map parameters = new HashMap() ;
					parameters.put("serviceName", "com.coda.www.efinance.schemas.selector.selector_1_0.webservice.Selector") ;
					parameters.put("serviceLocatorMethod", "getSelectorServicePort") ;
					parameters.put("serviceMethod", "selectDetails") ;
					parameters.put("serviceUrl", portAddress) ;
					parameters.put("paramObject",selectRequest) ;
					parameters.put("userName", userName) ;
					parameters.put("userPassword", BlackBox.getDecrypt(passWord)) ;
					selectResponse = (SelectDetailsResponse) proxy.wsProxyCall(proxyAddress, parameters) ;
				} else {
					selectResponse = (SelectDetailsResponse) port.selectDetails(selectRequest) ;
				}

				if (selectResponse != null) {
					Selection selection = selectResponse.getSelection() ;
					SelectionRow[] rows = selection.getDataSet() ;
					for (int i = 0; i < rows.length; i++) {
						System.out.println(rows[i].getCells()[0]) ;
						if (rows[i].getCells()[4].equalsIgnoreCase("APCK") ||
								rows[i].getCells()[4].equalsIgnoreCase("APWR") ||
								rows[i].getCells()[4].equalsIgnoreCase("APEF") ||
								rows[i].getCells()[4].equalsIgnoreCase("APQP")
								) {

//							for (int x = 0; x < columns.length; x++) {
//								System.out.println(columns[x] + ": " + rows[i].getCells()[x]) ;
//							}

							resultList.add(rows[i].getCells()) ;
						}
					}
				}

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

			result = resultList ;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}