package com.tsa.puridiom.coda.test;

import java.net.URL;
import java.rmi.*;
import java.util.Enumeration;

import javax.xml.rpc.ServiceException;

import com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter;
import com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange;
import com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceLocator;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindRequest;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.property.PropertiesManager;

public class ElementFinderTest {
	ElementFinderTest(){
		try {
			String organizationId = "TTR09P" ;
			String portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
			String userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
			String passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
			ElementFinderServiceLocator locator = new ElementFinderServiceLocator() ;
			ElementFinderServiceSOAPBindingStub port = null ;
			portAddress = "http://localhost:8080/puridiom/requestheader.jsp" ;
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

			FindRequest finderRequest = new FindRequest() ;
			ElementFinderFilter finderFilter = new ElementFinderFilter() ;
			finderFilter.setLevel((short) 6) ;
			ElementFinderNumberRange finderNumberRange = new ElementFinderNumberRange() ;
			finderNumberRange.setBegin((short) 0) ;
			finderNumberRange.setExtent((short) 10000) ;
			finderFilter.setNumberRange(finderNumberRange) ;
			finderFilter.setCompanyCode("TTCO") ;
			finderFilter.setUmbrella("No") ;
			finderFilter.setCode("*") ;
			finderFilter.setShortName("*") ;
			finderRequest.setFinderFilter(finderFilter) ;

			FindResponse findResponse = port.find(finderRequest) ;
			System.out.println(findResponse.toString()) ;

			ElmKeyDataElementNamed keys[] = findResponse.getKeys() ;
			for (int i = 0; i < keys.length; i++) {
				String code = keys[i].getCode();
				String name = keys[i].getName() ;
				String shortName = keys[i].getShortName() ;
				System.out.println( (i + 1) + "> " + code + " - " + name + " - " + shortName) ;
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
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ElementFinderTest();
	}
}
