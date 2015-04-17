package com.tsa.puridiom.fdcs.tasks;

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
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;

import iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationLocator;
import iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationSOAP11BindingStub;
import iseries.wsbeans.getworkordervalidation.xsd.POI957Input;
import iseries.wsbeans.getworkordervalidation.xsd.POI957Result;
import iseries.wsbeans.getworkordervalidation.xsd.PO_DATA_ARRAY;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

public class BrowseRetrieveWoElements extends Task{
	public synchronized  Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		List resultList = new ArrayList() ; ;
		try {
			String organizationId = (String) incomingRequest.get("organizationId");

			String workNumber = (String) incomingRequest.get("workNumber");
			String segNumber = (String) incomingRequest.get("segNumber");
			String opNumber = (String) incomingRequest.get("opNumber");
			String custNumber = (String) incomingRequest.get("custNumber");

			String shortNameFilter = (String) incomingRequest.get("shortNameFilter");
            String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");


			String portAddress = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "URL", "") ;
			String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "PROXYURL", "") ;
			String userName = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "USERNAME", "PURIDIOM") ;
			String passWord = PropertiesManager.getInstance(organizationId).getProperty("FDCS", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;

			if (portAddress.length() > 0) {
				portAddress = portAddress + "/GetWorkOrderValidation" ;
			}

			Log.debug(this, "userName=" + userName + "/" + passWord) ;
			Log.debug(this, "port=" + portAddress) ;

			try {
				if (workNumber == null ) workNumber = "" ;
				if (segNumber == null) segNumber = "" ;
				if (opNumber == null) opNumber = "" ;
				if (custNumber == null) custNumber = "" ;

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
				} else {
					Long count = res.get_PO_ARRAY_COUNT() ;
					PO_DATA_ARRAY ar[] = res.get_PO_DATA_ARRAY() ;

					if (count.compareTo(new Long(1)) >= 0) {
						for (int i = 0; i < count.intValue(); i++) {
							String  data[] = { ar[i].get_WOSGNO_O(), ar[i].get_WOOPNO_O(), ar[i].get_CUNO_O(), ar[i].get_TRNTYP_O() } ;
							resultList.add(data) ;
						}
					}
				}


//					System.out.println("ErrorCode: " + res.get_PO_ERRORCODE()) ;
//					System.out.println("ErrorDesc: " + res.get_PO_ERRORDESC()) ;
//					System.out.println("Count = " + count) ;

				result = resultList ;

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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.setStatus(Status.SUCCEEDED) ;

		return result;
	}

}