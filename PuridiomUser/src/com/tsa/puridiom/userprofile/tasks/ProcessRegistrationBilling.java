package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.properties.DictionaryManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class ProcessRegistrationBilling extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object	result = null ;
		Hashtable return_values = new Hashtable();

		try
		{
			String	termsAccepted = (String) incomingRequest.get("termsAccepted");
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			String	companyName = OrganizationManager.getInstance().getOrganizationName(organizationId);
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	anid = DictionaryManager.getInstance("host", organizationId).getProperty("anid");
			String	anky = DictionaryManager.getInstance("host", organizationId).getProperty("anky");
			String	anUrl = DictionaryManager.getInstance("host", organizationId).getProperty("anUrl");
			String	anTest = DictionaryManager.getInstance("host", organizationId).getProperty("anTest");

			String	cFirstName = (String) incomingRequest.get("as_firstName") ;
			String	cLastName = (String) incomingRequest.get("as_lastName") ;
			String	cNumber = (String) incomingRequest.get("as_creditCardNumber") ;
			String	cExpMonth = (String) incomingRequest.get("as_expMonth") ;
			String	cExpYear = (String) incomingRequest.get("as_expYear") ;
			String	cType = (String) incomingRequest.get("as_creditCardType") ;
			String	cExp = (String) incomingRequest.get("as_cardExpires") ;
			String	cBillAddr1 = (String) incomingRequest.get("as_billingAddressLine1") ;
			String	cBillAddr2 = (String) incomingRequest.get("as_billingAddressLine2") ;
			String	cBillCity = (String) incomingRequest.get("as_billingCity") ;
			String	cBillState = (String) incomingRequest.get("as_billingState") ;
			String	cBillZip = (String) incomingRequest.get("as_billingZip") ;
			String	cBillCountry = (String) incomingRequest.get("as_billingCountry") ;
			String	asCardCode = (String) incomingRequest.get("as_cardCode") ;
			String  packageName = (String) incomingRequest.get("packageName") ;
			String  packagePrice = (String) incomingRequest.get("packagePrice") ;

			String  cAction = "AUTH_CAPTURE" ;
			if (packageName.toUpperCase().startsWith("FREE")) {
				cAction = "AUTH_ONLY" ;
				packagePrice = "500.00";
			}

			userId = userId.toUpperCase();
			organizationId = organizationId.toUpperCase();
			
			if (HiltonUtility.isEmpty(mailId)) {
				mailId = HiltonUtility.ckNull((String) incomingRequest.get("loginId"));
			}

			// By default, this sample code is designed to post to our test server for
			// developer accounts: https://test.authorize.net/gateway/transact.dll
			// for real accounts (even in test mode), please make sure that you are
			// posting to: https://secure.authorize.net/gateway/transact.dll
			// URL post_url = new URL("https://test.authorize.net/gateway/transact.dll");
			if (HiltonUtility.isEmpty(anUrl)) {
				anUrl = "https://secure.authorize.net/gateway/transact.dll" ;
			}
			URL post_url = new URL(anUrl);

			Hashtable post_values = new Hashtable();

			// the API Login ID and Transaction Key must be replaced with valid values
			post_values.put ("x_login", anid.trim());
			post_values.put ("x_tran_key", anky.trim());

			post_values.put ("x_version", "3.1");
			post_values.put ("x_delim_data", "TRUE");
			post_values.put ("x_delim_char", "|");
			post_values.put ("x_relay_response", "FALSE");

			post_values.put ("x_type", cAction);
			post_values.put ("x_method", "CC");
			post_values.put ("x_card_num", cNumber);
			post_values.put ("x_exp_date", cExp);

			post_values.put ("x_amount", packagePrice);
			post_values.put ("x_description", packageName);

			post_values.put ("x_first_name", cFirstName);
			post_values.put ("x_last_name", cLastName);
			post_values.put ("x_company", companyName);
			post_values.put ("x_address", cBillAddr1);
			post_values.put ("x_city", cBillCity);
			post_values.put ("x_state", cBillState);
			post_values.put ("x_zip", cBillZip);
			post_values.put ("x_country", cBillCountry);
			post_values.put ("x_email", mailId);
			post_values.put ("x_cust_id", organizationId);			 


			if (anTest.equals("Y")) {
				post_values.put("x_test_request", "TRUE") ;
			} else {
				post_values.put("x_test_request", "FALSE") ;
			}

			// Additional fields can be added here as outlined in the AIM integration
			// guide at: http://developer.authorize.net

			// This section takes the input fields and converts them to the proper format
			// for an http post.  For example: "x_login=username&x_tran_key=a1B2c3D4"
			StringBuffer post_string = new StringBuffer();
			Enumeration keys = post_values.keys();
			while( keys.hasMoreElements() ) {
			  String key = URLEncoder.encode(keys.nextElement().toString(),"UTF-8");
			  String value = URLEncoder.encode(post_values.get(key).toString(),"UTF-8");
			  post_string.append(key + "=" + value + "&");
			}

			// Open a URLConnection to the specified post url
			URLConnection connection = post_url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// this line is not necessarily required but fixes a bug with some servers
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

			DataOutputStream requestObject = new DataOutputStream( connection.getOutputStream() );
			requestObject.write(post_string.toString().getBytes());
			requestObject.flush();
			requestObject.close();

			// process and read the gateway response
			BufferedReader rawResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String responseData = rawResponse.readLine();
			rawResponse.close();	                     // no more data

			// split the response into an array
			String [] responses = responseData.split("\\|");

			// Get the Results

			int ikey = 0 ;
			for(Iterator iter=Arrays.asList(responses).iterator(); iter.hasNext();) {
				Object obj = iter.next();
				ikey++ ;
				return_values.put("ID" + ikey , obj) ;
				//System.out.println("ID" + ikey + " = " + obj);
			}
			
			if (responses[0].equals("1")) {
				result = "APPROVED" ;
			} else if (responses[0].equals("2")) {
				result = "DECLINED" ;
			} else if (responses[0].equals("3")) {
				result = "ERROR" ;
			} else if (responses[0].equals("4")) {
				result = "HELD FOR REVIEW" ;
			}

			// individual elements of the array could be accessed to read certain response
			// fields.  For example, response_array[0] would return the Response Code,
			// response_array[2] would return the Response Reason Code.
			// for a list of response fields, please review the AIM Implementation Guide

			incomingRequest.put("fullResponse", return_values) ;
			incomingRequest.put("ccResult", result) ;
			incomingRequest.put("ccMessage", responses[3]) ;
			incomingRequest.put("ccTranId", responses[6]) ;


			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}