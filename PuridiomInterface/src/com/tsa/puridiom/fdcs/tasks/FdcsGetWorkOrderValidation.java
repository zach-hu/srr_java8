package com.tsa.puridiom.fdcs.tasks;

import iseries.wsbeans.getworkordervalidation.*;
import iseries.wsbeans.getworkordervalidation.xsd.* ;
import iseries.wsbeans.updatepolinetomiscwo.xsd.POI951Result;

import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceSOAPBindingStub;
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
import com.tsa.puridiom.entity.RequisitionHeader;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import sun.util.logging.resources.logging;

public class FdcsGetWorkOrderValidation extends Task{
	private String organizationId ;
	private String userId ;
	private String userTimeZone ;
	private String userDateFormat ;
	private String portAddress ;
	private String proxyAddress ;
	private String userName ;
	private String passWord ;
	private String errorStr ;
	private boolean serviceError = false ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			organizationId = (String) incomingRequest.get("organizationId");
            userId = (String) incomingRequest.get("userId");
            userTimeZone = (String) incomingRequest.get("userTimeZone");
            userDateFormat = (String) incomingRequest.get("userDateFormat");

			String portAddress = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "URL", "") ;
			String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "PROXYURL", "") ;
			String userName = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "USERNAME", "PURIDIOM") ;
			String passWord = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;

			Log.debug(this, "userName=" + userName + "/" + passWord) ;
			Log.debug(this, "port=" + portAddress) ;

			try {
				String handler = (String) incomingRequest.get("handler") ;
				if (handler == null) handler = "" ;
				String custNumber = "" ;
				String workNumber = "" ;
				String opNumber = "" ;
				String segNumber = "" ;
				String tranType = "" ;
				String resaleType = "" ;

				if (handler.equalsIgnoreCase("PoValidateHandler")) {
					PoHeader header = (PoHeader) incomingRequest.get("header");
					tranType = header.getUdf1Code() ;
					resaleType = header.getUdf3Code();
					workNumber = header.getUdf7Code() ;
					segNumber = header.getUdf8Code();
					opNumber = header.getUdf9Code();
					custNumber = header.getUdf11Code() ;
				} else {
					// Requisition
					RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
					tranType = header.getUdf1Code() ;
					resaleType = header.getUdf3Code();
					workNumber = header.getUdf7Code() ;
					segNumber = header.getUdf8Code();
					opNumber = header.getUdf9Code();
					custNumber = header.getUdf11Code() ;
				}
				if (workNumber == null ) workNumber = "" ;
				if (segNumber == null) segNumber = "" ;
				if (opNumber == null) opNumber = "" ;
				if (custNumber == null) custNumber = "" ;
				if (tranType == null) tranType = "" ;
				if (resaleType == null) resaleType = "" ;

				if ((! (tranType.startsWith("RESALE") && resaleType.equals("OSL"))) || (workNumber.equalsIgnoreCase("override")) || tranType.equals("USEDPARTS")) {
					// skip if not above
					incomingRequest.put("fdcsWOErrorText", "") ;
					incomingRequest.put("fdcsWOValidateError", false) ;
					this.setStatus(Status.SUCCEEDED) ;
					return null ;
				}

				if (workNumber.trim().length() == 0) {
					incomingRequest.put("fdcsWOErrorText", "[Work Order # is required]") ;
					incomingRequest.put("fdcsWOValidateError", true) ;
					this.setStatus(Status.SUCCEEDED) ;
					return null ;
				}


				if (portAddress.length() > 0) {
					portAddress = portAddress + "/GetWorkOrderValidation" ;
				}
				GetWorkOrderValidationSOAP11BindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					GetWorkOrderValidationLocator locator = new GetWorkOrderValidationLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (GetWorkOrderValidationSOAP11BindingStub) locator.getGetWorkOrderValidationSOAP11port_http(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (GetWorkOrderValidationSOAP11BindingStub)locator.getGetWorkOrderValidationSOAP11port_http() ;
					}

					// Set username and password
					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				POI957Input input = new POI957Input() ;

				input.set_PI_CUNO(custNumber) ;
				input.set_PI_WONO(workNumber) ;
				input.set_PI_WOOPNO(opNumber) ;
				input.set_PI_WOSGNO(segNumber) ;

				POI957Result res = null ;
				if (! HiltonUtility.isEmpty(proxyAddress)) {
					WSProxyCall proxy = new WSProxyCall() ;
					Map parameters = new HashMap() ;
					parameters.put("serviceName", "iseries.wsbeans.getworkordervalidation.GetWorkOrderValidation") ;
					parameters.put("serviceLocatorMethod", "getGetWorkOrderValidationSOAP11port_http") ;
					parameters.put("serviceMethod", "poi957") ;
					parameters.put("serviceUrl", portAddress) ;
					parameters.put("paramObject",input) ;
					parameters.put("userName", userName) ;
					parameters.put("userPassword", passWord) ;
					res = (POI957Result) proxy.wsProxyCall(proxyAddress, parameters) ;
				} else {
					res = port.poi957(input) ;
				}
				boolean ok = false ;
				if (! HiltonUtility.isEmpty(res.get_PO_ERRORCODE())){
					errorStr = "FdcsGetWorkOrderValidation - WO: " + workNumber + " OpNo: " + opNumber + " SegNo: " + segNumber + " Error: "+ res.get_PO_ERRORCODE() + " - " + res.get_PO_ERRORDESC() ;
					Log.error(this, errorStr) ;
				} else {
					Long count = res.get_PO_ARRAY_COUNT() ;
					PO_DATA_ARRAY ar[] = res.get_PO_DATA_ARRAY() ;

					if (count.compareTo(new Long(1)) >= 0) {
						for (int i = 0; i < count.intValue(); i++) {
							if (custNumber.equalsIgnoreCase(ar[i].get_CUNO_O()) &&
									opNumber.equalsIgnoreCase(ar[i].get_WOOPNO_O()) &&
									segNumber.equalsIgnoreCase(ar[i].get_WOSGNO_O())) {
								ok = true ;
							}
						}
					}

//					System.out.println("Cust #: " + ar[i].get_CUNO_O()) ;
//					System.out.println("AUTOREQ: " + ar[i].get_AUTREQ_O()) ;
				}


//				System.out.println("ErrorCode: " + res.get_PO_ERRORCODE()) ;
//				System.out.println("ErrorDesc: " + res.get_PO_ERRORDESC()) ;
//				System.out.println("Count = " + count) ;

				if (! ok ) {
					incomingRequest.put("fdcsWOErrorText", "[Work Order: " + workNumber + " , Seg #: " + segNumber + " , Operation #: " + opNumber + " , Customer #: " + custNumber + " ]") ;
					incomingRequest.put("fdcsWOValidateError", (! ok)) ;
				}
				result = res ;

			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
				this.serviceError = true ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
				this.serviceError = true ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
				this.serviceError = true ;
			}
		}
		catch (Exception e) {

		}
		if (serviceError) {
			incomingRequest.put("fdcsWOErrorText", "[Service error occurred, DBS System may be down.]") ;
			incomingRequest.put("fdcsWOValidateError", true) ;
		}
		this.setStatus(Status.SUCCEEDED) ;

		return result;
	}

	public String getErrorStr() {
		return errorStr;
	}

	private void setErrorStr(String errorStr) {
		this.errorStr = errorStr;
	}

}