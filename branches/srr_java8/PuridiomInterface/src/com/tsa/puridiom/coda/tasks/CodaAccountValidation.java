package com.tsa.puridiom.coda.tasks;

import com.coda.www.efinance.schemas.common.Reason;
import com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter;
import com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange;
import com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceLocator;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindRequest;
import com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse;
import com.coda.www.efinance.schemas.transaction.ChkAccCodeData;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceLocator;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse;

import com.tsa.puridiom.browse.BrowseFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.ReportDates;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
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
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

public class CodaAccountValidation extends Task{

	private String organizationId ;
	private String userId ;
	private String userTimeZone ;
	private String userDateFormat ;
	private String portAddress ;
	private String userName ;
	private String passWord ;
	private String companyCode ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			try {
				organizationId = (String) incomingRequest.get("organizationId");
	            userId = (String) incomingRequest.get("userId");
	            userTimeZone = (String) incomingRequest.get("userTimeZone");
	            userDateFormat = (String) incomingRequest.get("userDateFormat");
				portAddress = PropertiesManager.getInstance(organizationId).getProperty("CODA", "URL", "") ;
				userName = PropertiesManager.getInstance(organizationId).getProperty("CODA", "USERNAME", "PURIDIOM") ;
				passWord = PropertiesManager.getInstance(organizationId).getProperty("CODA", "PASSWORD", BlackBox.getEncrypt("PURIDIOM")) ;
				companyCode = PropertiesManager.getInstance(organizationId).getProperty("CODA", "COMPANY", "TTCO") ;

				AccountServiceLocator locator = new AccountServiceLocator() ;
				// locator.getAccountServicePort(portAddress) ;
				// AccountServiceSOAPBindingStub port = (AccountServiceSOAPBindingStub)locator.getAccountServicePort() ;
				AccountServiceSOAPBindingStub port = null ;
				portAddress="" ;
				if (portAddress.length() > 0) {
					try {
						portAddress = portAddress + "/finance/elementmaster/elementfinder-11.2" ;
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
				port.setPassword(passWord) ;
				CheckCodeRequest checkCodeRequest = new CheckCodeRequest() ;

				ChkAccCodeData chkAccCodeData = new ChkAccCodeData() ;
				chkAccCodeData.setCmpCode("TTCO") ;
				chkAccCodeData.setAccountCode("634100") ;
				chkAccCodeData.setCheckingType("checkaccode") ;
				chkAccCodeData.setCodeComplete(true) ;

				checkCodeRequest.setChkCodeData(chkAccCodeData) ;

				CheckCodeResponse checkCodeResponse = port.checkCode(checkCodeRequest) ;

				if (checkCodeResponse.getAnswer().isGood()) {
					result = "true" ;
				} else {
					result = "false" ;
				}

				System.out.println("Is Good: " + result ) ;


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