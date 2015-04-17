package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.common.Key;
import com.coda.www.efinance.schemas.elementmaster.Element;
import com.coda.www.efinance.schemas.elementmaster.ElmFullKey;
import com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed;
import com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceLocator;
import com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequest;
import com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse;

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

public class CodaRetrieveElementMaster extends Task{
	public synchronized  Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List resultList = new ArrayList() ; ;
			try {
				String organizationId = (String) incomingRequest.get("organizationId");
				String codaLevel = (String) incomingRequest.get("codaLevel");
				String codeFilter = (String) incomingRequest.get("codeFilter");
	            String userId = (String) incomingRequest.get("userId");
	            String userTimeZone = (String) incomingRequest.get("userTimeZone");
	            String userDateFormat = (String) incomingRequest.get("userDateFormat");

				if (codeFilter == null) codeFilter = "*" ;

				String portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
				String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PROXYURL", "") ;
				String userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
				String passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
				String companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;
				String fromTestApp = (String) incomingRequest.get("fromTestApp") ;

	            if (fromTestApp == null) fromTestApp = "no" ;
				if (fromTestApp.equalsIgnoreCase("yes")) proxyAddress = "";

				if (portAddress.length() > 0) {
					portAddress = portAddress + "/finance/elementmaster/elementmaster-6.0" ;
				}
				ElementMasterServiceSOAPBindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					ElementMasterServiceLocator locator = new ElementMasterServiceLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (ElementMasterServiceSOAPBindingStub) locator.getElementMasterServicePort(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (ElementMasterServiceSOAPBindingStub)locator.getElementMasterServicePort() ;
					}

					// Set username and password

					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				GetRequest getRequest = new GetRequest() ;
				ElmFullKey key = new ElmFullKey() ;
				key.setCmpCode(companyCode) ;
				key.setCode(codeFilter) ;
				key.setLevel(Short.parseShort(codaLevel)) ;

				getRequest.setKey(key) ;

				GetResponse getResponse = null ;
				if (! HiltonUtility.isEmpty(proxyAddress)) {
					WSProxyCall proxy = new WSProxyCall() ;
					Map parameters = new HashMap() ;
					parameters.put("serviceName", "com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMaster") ;
					parameters.put("serviceLocatorMethod", "getElementMasterServicePort") ;
					parameters.put("serviceMethod", "find") ;
					parameters.put("serviceUrl", portAddress) ;
					parameters.put("paramObject",getRequest) ;
					parameters.put("userName", userName) ;
					parameters.put("userPassword", BlackBox.getDecrypt(passWord)) ;
					getResponse = (GetResponse) proxy.wsProxyCall(proxyAddress, parameters) ;
				} else {
					getResponse = port.get(getRequest) ;
				}
				Element el = getResponse.getElement() ;
				result = el ;
				System.out.println(el) ;

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