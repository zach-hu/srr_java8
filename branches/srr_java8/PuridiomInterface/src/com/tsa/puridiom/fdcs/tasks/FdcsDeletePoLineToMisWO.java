package com.tsa.puridiom.fdcs.tasks;

import iseries.wsbeans.addpolinetomiscwo.xsd.POI950Result;
import iseries.wsbeans.deletepolinetomiscwo.*;
import iseries.wsbeans.deletepolinetomiscwo.xsd.* ;

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

public class FdcsDeletePoLineToMisWO extends Task{
	private String organizationId ;
	private String userId ;
	private String userTimeZone ;
	private String userDateFormat ;
	private String portAddress ;
	private String proxyAddress ;
	private String userName ;
	private String passWord ;
	private String errorStr = "" ;

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
			String chargeCodeFiller = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "CHARGECODEFILLER", "") ;

			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			Log.debug(this, "userName=" + userName + "/" + passWord) ;
			Log.debug(this, "port=" + portAddress) ;

			try {

				if (portAddress.length() > 0) {
					portAddress = portAddress + "/DeletePOLineToMiscWO" ;
				}
				DeletePOLineToMiscWOSOAP11BindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					DeletePOLineToMiscWOLocator locator = new DeletePOLineToMiscWOLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (DeletePOLineToMiscWOSOAP11BindingStub) locator.getDeletePOLineToMiscWOSOAP11port_http(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (DeletePOLineToMiscWOSOAP11BindingStub)locator.getDeletePOLineToMiscWOSOAP11port_http() ;
					}

					// Set username and password
					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				String poNumber = (String) incomingRequest.get("poNumber") ;
				String workNumber = (String) incomingRequest.get("workNumber");
				String opNumber = (String) incomingRequest.get("opNumber") ;
				String segNumber = (String) incomingRequest.get("segNumber") ;
				String chargeCode = (String) incomingRequest.get("chargeCode");
				BigDecimal seqNumber = (BigDecimal) incomingRequest.get("seqNumber") ;
				BigDecimal lineNumber = (BigDecimal) incomingRequest.get("lineNumber") ;
				String dbsKey = (String) incomingRequest.get("dbsKey");

				POI952Input input = new POI952Input() ;

				input.set_PI_CHGCD(chargeCodeFiller + chargeCode ) ; 	// charge code
				input.set_PI_PONUMB(poNumber) ;  	// PO Number
				input.set_PI_SQN01(seqNumber) ;     // Sequence Number
				input.set_PI_POSEQN(lineNumber) ; 	// Line Number
				input.set_PI_WONO(workNumber) ;		// Work Order
				input.set_PI_WOOPNO(opNumber) ;		// Operation
				input.set_PI_WOSGNO(segNumber) ;    // Segment
				POI952Result res = null ;
				if (! HiltonUtility.isEmpty(proxyAddress)) {
					WSProxyCall proxy = new WSProxyCall() ;
					Map parameters = new HashMap() ;
					parameters.put("serviceName", "iseries.wsbeans.deletepolinetomiscwo.DeletePOLineToMiscWO") ;
					parameters.put("serviceLocatorMethod", "getDeletePOLineToMiscWOSOAP11port_http") ;
					parameters.put("serviceMethod", "poi952") ;
					parameters.put("serviceUrl", portAddress) ;
					parameters.put("paramObject",input) ;
					parameters.put("userName", userName) ;
					parameters.put("userPassword", passWord) ;
					res = (POI952Result ) proxy.wsProxyCall(proxyAddress, parameters) ;
				} else {
					res = port.poi952(input) ;
				}

				if (! HiltonUtility.isEmpty(res.get_PO_ERRORCODE())){
					errorStr = "FdcsDeletePoLineToMiscWO - Order: " + poNumber.toString() + " Line: " + lineNumber.toString() + " Error: "+ res.get_PO_ERRORCODE() + " - " + res.get_PO_ERRORDESC() ;
					Log.error(this, errorStr) ;
				}
				result = res ;

			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorStr = e.getMessage();
			}
		}
		catch (Exception e) {

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