package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.Log;

import java.util.*;
import java.math.*;


/**
 * Creation date: August 2003
 * @author: Jeff
 */
public class LDAPUserProfileUpdateSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean success = Boolean.FALSE;

		LDAPProperties ldap = LDAPProperties.getInstance();
		String	oid = ldap.getLDAPOrganization() ;
		String	update = ldap.getLDAPUserUpdate() ;
		String  uid = (String) incomingRequest.get("UserProfile_userId");

		// Force user profile review for add
		incomingRequest.put("organizationId", oid) ;
		incomingRequest.put("resetPassword", "false");
		incomingRequest.put("setSecurityQuestion", "false");
		incomingRequest.put("ldapUserUpdate", update) ;

		String	wpcLocation = (String) incomingRequest.get("UserProfile_wpcLocation") ;
		String	department = (String) incomingRequest.get("UserProfile_department") ;
		String	title = (String) incomingRequest.get("UserProfile_title") ;
		String  gpiCosting = department ;

		Log.debug(this,"wpcLocation=" + wpcLocation) ;
		Log.debug(this,"GpiCosting=" + gpiCosting) ;
		Log.debug(this,"title=" + title) ;

		if (oid.startsWith("WPC")) {
			if (department == null) {
			      incomingRequest.put("UserProfile_department", "001") ;
			} else {
			   if (department.equals("5010") || department.startsWith("96")) {
			      incomingRequest.put("UserProfile_department", "001") ;
			   }
			   if (department.startsWith("0") && department.length() > 3) {
				   department = department.substring(1) ;
				   incomingRequest.put("UserProfile_department", department) ;
			   }
			}

			if (gpiCosting.startsWith("09")) {
			   incomingRequest.put("UserProfile_nameUdf2", "9600") ;
			} else if (gpiCosting.startsWith("96")) {
			   incomingRequest.put("UserProfile_nameUdf2", gpiCosting) ;
			}

			if (wpcLocation == null) wpcLocation = "0" ;
			if (wpcLocation.length() <= 0) wpcLocation = "0";

			BigDecimal shipToDec = new BigDecimal(wpcLocation) ;
			if (shipToDec.compareTo(new BigDecimal(10)) < 0) {
			   wpcLocation = "960" + wpcLocation.trim();
			} else {
			   wpcLocation = "96" + wpcLocation.trim();
			}
			incomingRequest.put("UserProfile_shipToCode", wpcLocation) ;

			// Approval Rules
			BigDecimal approvalAmount = new BigDecimal(0) ;
			if (title == null) title = "" ;
			title = title.toLowerCase().trim() ;
			if ( title.equals("president")) {
				approvalAmount = new BigDecimal(250000) ;
			} else if (title.equals("vice president, finance")) {
				approvalAmount = new BigDecimal(249999) ;
			} else if (title.equals("executive vice president")) {
				approvalAmount = new BigDecimal(74999) ;
			} else if (title.indexOf("vice president") >= 0 || title.indexOf("vp") >= 0) {
				approvalAmount = new BigDecimal(24999) ;
			} else if (title.equals("manager, computing & networks")) {
				approvalAmount = new BigDecimal(4999) ;
			} else if (title.equals("corporate manager of facilities")) {
				approvalAmount = new BigDecimal(4999) ;
			} else if (title.equals("manager, hr east")) {
				approvalAmount = new BigDecimal(4999) ;
			} else if (title.equals("distribution center operations manager")) {
				approvalAmount = new BigDecimal(999) ;
			} else if (title.equals("branch operations manager")) {
				approvalAmount = new BigDecimal(999) ;
			} else if (title.equals("assistant branch operations manager")) {
				approvalAmount = new BigDecimal(0) ;
			}

			Log.debug(this,"approvalAmount=" + approvalAmount.toString()) ;

			incomingRequest.put("UserProfile_approvalAmount", approvalAmount.toString()) ;
		}
		return success;
	}

}
