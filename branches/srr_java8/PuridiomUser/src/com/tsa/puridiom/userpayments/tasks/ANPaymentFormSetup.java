package com.tsa.puridiom.userpayments.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;

import com.tsa.puridiom.organization.OrganizationManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.properties.DictionaryManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class ANPaymentFormSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object	resultObj = null ;

		try
		{
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			String	companyName = OrganizationManager.getInstance().getOrganizationName(organizationId);
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	firstName = (String) incomingRequest.get("UserProfile_firstName");
			String	lastName = (String) incomingRequest.get("UserProfile_lastName");
			String	packagePrice = (String) incomingRequest.get("packagePrice");
			String	packageName = (String) incomingRequest.get("packageName");
			String	anid = DictionaryManager.getInstance("host", organizationId).getProperty("anid");
			String	anky = DictionaryManager.getInstance("host", organizationId).getProperty("anky");
			String	anUrl = DictionaryManager.getInstance("host", organizationId).getProperty("anUrl");
			String	anTest = DictionaryManager.getInstance("host", organizationId).getProperty("anTest");
			String	anLogoUrl = DictionaryManager.getInstance("host", organizationId).getProperty("anLogoUrl");
			String	anRelayUrl = DictionaryManager.getInstance("host", organizationId).getProperty("anRelayUrl");
			String	colorBackground = "#FFFFFF" ;
			String	relayResponse = "TRUE" ;
			String	contextPath = "/puridiom";

			organizationId = organizationId.toUpperCase();

			String  cAction = "AUTH_CAPTURE" ;
			if (packageName.toUpperCase().startsWith("FREE")) {
				cAction = "AUTH_ONLY" ;
				packagePrice = "500.00";
			}
			packagePrice = HiltonUtility.ckNull(packagePrice);
			if (packagePrice.startsWith("$")) {
				packagePrice = packagePrice.substring(1).trim();
			}
			try {
				packagePrice = HiltonUtility.getBigDecimalFormatted(packagePrice, 2).toString();
			} catch (Exception e) {
				packagePrice = "0.00";
			}

			if (HiltonUtility.isEmpty(mailId)) {
				mailId = HiltonUtility.ckNull((String) incomingRequest.get("loginId"));
			}

			// for real accounts (even in test mode), please make sure that you are
			// posting to: https://secure.authorize.net/gateway/transact.dll
			if (HiltonUtility.isEmpty(anUrl)) {
				anUrl = "https://secure.authorize.net/gateway/transact.dll" ;
			}

			if (anRelayUrl.indexOf("/puridiom") > 0) {
				contextPath = anRelayUrl.substring(0, anRelayUrl.indexOf("/puridiom")) + "/puridiom4";
			}

			// an invoice is generated using the date and time
			Date myDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String invoice = dateFormat.format(myDate);
			// a sequence number is randomly generated
			Random generator = new Random();
			int sequence = generator.nextInt(1000);
			// a timestamp is generated
			long timeStamp = System.currentTimeMillis()/1000;

			//This section uses Java Cryptography functions to generate a fingerprint
			// First, the Transaction key is converted to a "SecretKey" object
			//KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
			SecretKey key = new SecretKeySpec(anky.getBytes(), "HmacMD5");
			// A MAC object is created to generate the hash using the HmacMD5 algorithm
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(key);
			String inputstring = anid + "^" + sequence + "^" + timeStamp + "^" + packagePrice + "^";
			byte[] result = mac.doFinal(inputstring.getBytes());
			// Convert the result from byte[] to hexadecimal format
			StringBuffer strbuf = new StringBuffer(result.length * 2);
			for(int i=0; i< result.length; i++)
			{
				if(((int) result[i] & 0xff) < 0x10)
					strbuf.append("0");
				strbuf.append(Long.toString((int) result[i] & 0xff, 16));
			}
			String fingerprint = strbuf.toString();
			// end of fingerprint generation

			incomingRequest.put("x_login", anid);
			incomingRequest.put("x_type", cAction);
			incomingRequest.put("x_amount", packagePrice);
			incomingRequest.put("x_description", packageName);
			incomingRequest.put("x_invoice_num", invoice);
			incomingRequest.put("x_fp_sequence", String.valueOf(sequence));
			incomingRequest.put("x_fp_timestamp", String.valueOf(timeStamp));
			incomingRequest.put("x_fp_hash", fingerprint);
			incomingRequest.put("x_show_form", "PAYMENT_FORM");
			incomingRequest.put("x_relay_response", relayResponse);
			incomingRequest.put("x_relay_url", anRelayUrl);
			incomingRequest.put("x_logo_url", anLogoUrl);
			incomingRequest.put("x_color_background", colorBackground);

			if (anTest.equals("Y")) {
				incomingRequest.put("x_test_request", "TRUE") ;
			} else {
				incomingRequest.put("x_test_request", "FALSE") ;
			}

			//Default Data
			incomingRequest.put("x_first_name", firstName);
			incomingRequest.put("x_last_name", lastName);
			incomingRequest.put("x_company", companyName);
			incomingRequest.put("x_email", mailId);
			incomingRequest.put("x_cust_id", organizationId);
			incomingRequest.put("contextPath", contextPath);
			incomingRequest.put("anUrl", anUrl);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return resultObj;
	}
}