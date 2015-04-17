package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter;
import com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange;
import com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceLocator;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindRequest;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse;

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

public class BrowseRetrieveElementFinder extends Task{
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
				String shortNameFilter = (String) incomingRequest.get("shortNameFilter");
				String startRow = (String) incomingRequest.get("startRow");
				String endRow = (String) incomingRequest.get("endRow");
	            String userId = (String) incomingRequest.get("userId");
	            String userTimeZone = (String) incomingRequest.get("userTimeZone");
	            String userDateFormat = (String) incomingRequest.get("userDateFormat");
	            BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");


				if (codeFilter == null) codeFilter = "*" ;
				if (shortNameFilter == null) shortNameFilter = "*" ;

				String portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
				String proxyAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PROXYURL", "") ;
				String userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
				String passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
				String companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;

				if (portAddress.length() > 0) {
					portAddress = portAddress + "/finance/elementmaster/elementfinder-11.2" ;
				}
				ElementFinderServiceSOAPBindingStub port = null ;
				if (HiltonUtility.isEmpty(proxyAddress)) {
					ElementFinderServiceLocator locator = new ElementFinderServiceLocator() ;
					if (portAddress.length() > 0) {
						try {
							URL url = new URL(portAddress) ;
							port = (ElementFinderServiceSOAPBindingStub) locator.getElementFinderServicePort(url) ;
						}
						catch (Exception E)
						{

						}
					}

					if (port == null) {
						port = (ElementFinderServiceSOAPBindingStub)locator.getElementFinderServicePort() ;
					}

					// Set username and password

					port.setUsername(userName) ;
					port.setPassword(BlackBox.getDecrypt(passWord)) ;
				}

				FindRequest finderRequest = new FindRequest() ;
				ElementFinderFilter finderFilter = new ElementFinderFilter() ;
				finderFilter.setLevel(Short.parseShort(codaLevel)) ;
				ElementFinderNumberRange finderNumberRange = new ElementFinderNumberRange() ;

				// For now retrieve all ( 0 - 10000)
				if (startRow == null) startRow = "0" ;
				if (endRow == null) endRow = "10000" ;
				finderNumberRange.setBegin(Short.parseShort(startRow)) ;
				finderNumberRange.setExtent(Short.parseShort(endRow)) ;
				finderFilter.setNumberRange(finderNumberRange) ;
				finderFilter.setCompanyCode(companyCode) ;

				finderFilter.setUmbrella("No") ;
				if (codeFilter.length() > 0) {
					finderFilter.setCode("*" + codeFilter + "*") ;
				} else {
					finderFilter.setCode("*") ;
				}

				if (shortNameFilter.length() > 0) {
					finderFilter.setShortName("*" + shortNameFilter + "*") ;
				} else {
					finderFilter.setShortName("*") ;
				}
				finderRequest.setFinderFilter(finderFilter) ;

				FindResponse findResponse = null ;
				if (! HiltonUtility.isEmpty(proxyAddress)) {
					WSProxyCall proxy = new WSProxyCall() ;
					Map parameters = new HashMap() ;
					parameters.put("serviceName", "com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinder") ;
					parameters.put("serviceLocatorMethod", "getElementFinderServicePort") ;
					parameters.put("serviceMethod", "find") ;
					parameters.put("serviceUrl", portAddress) ;
					parameters.put("paramObject",finderRequest) ;
					parameters.put("userName", userName) ;
					parameters.put("userPassword", BlackBox.getDecrypt(passWord)) ;
					findResponse = (FindResponse) proxy.wsProxyCall(proxyAddress, parameters) ;
				} else {
					findResponse = port.find(finderRequest) ;
				}

				ElmKeyDataElementNamed keys[] = findResponse.getKeys() ;
				for (int i = 0; i < keys.length; i++) {
					String  data[] = { keys[i].getCode(), keys[i].getName(), keys[i].getShortName() } ;
					resultList.add(data) ;
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