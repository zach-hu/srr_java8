package com.tsa.puridiom.coda.test;

import java.rmi.*;
import java.util.Enumeration;

import javax.xml.rpc.ServiceException;

import com.coda.www.efinance.schemas.common.Reason;
import com.coda.www.efinance.schemas.common.ReasonText;
import com.coda.www.efinance.schemas.transaction.ChkAccCodeData;
import com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceLocator;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeRequest;
import com.coda.www.efinance.schemas.transaction.account_11_2.webservice.CheckCodeResponse;

public class AccountValidationTest {
	AccountValidationTest(){
		try {
			AccountServiceLocator locator = new AccountServiceLocator() ;
			// locator.getAccountServicePort(portAddress) ;
			AccountServiceSOAPBindingStub port = (AccountServiceSOAPBindingStub)locator.getAccountServicePort() ;

			// Set username and password
			String userName = "PURIDIOM";
			String passWord = "PURIDIOM";

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

			if (! checkCodeResponse.getAnswer().isGood()) {
				Reason reason = checkCodeResponse.getAnswer().getFailed().getReason() ;
				for (int i = 0; i < reason.getText().length; i++) {
					System.out.println("Failed Reason " + (i + 1) + reason.getText(i)) ;
				}
			} else {
				String passed[] = checkCodeResponse.getAnswer().getPassed() ;
				for (int i = 0;i < passed.length; i++) {
					System.out.println("Passed: " + passed[i]) ;
				}
			}

			System.out.println("Is Good: " + checkCodeResponse.getAnswer().isGood()) ;


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
		new AccountValidationTest();
	}
}
