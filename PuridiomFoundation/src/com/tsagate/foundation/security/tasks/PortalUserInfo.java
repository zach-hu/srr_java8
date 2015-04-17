/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsagate.foundation.security.tasks;

/**
 * @author JEFF
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;
import java.util.Calendar;
import java.text.NumberFormat;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

import com.tsagate.foundation.service.properties.LDAPProperties;
import com.tsagate.foundation.utility.* ;

public class PortalUserInfo
{
	private Map attrMap = new Hashtable() ;
	private Map userInfoMap = new Hashtable() ;
	private String statusCode;
	private String statusDesc;
	private String errorUrl;
	private String userType;
	private String loginId;
	private String firstName;
	private String lastName;
	private String middleInit;
	private String title;
	private String salutation;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String bPhone1;
	private String bPhone2;
	private String fax;
	private String pager;
	private String email;
	private String cPhone;
	private String hPhone;
	private String role;
	private String districtCode;
	private String branchCode;
	private String divisionCode;
	private String ticket;
	private String targetUrl;
	private String appName;
	private String location ;
	private String organization ;
	private String password ;
	private String hashValue ;
	private String expireDateTime ;

	public PortalUserInfo()
	{
		statusCode = "";
		statusDesc = "";
		errorUrl = "";
		userType = "";
		loginId = "";
		firstName = "";
		lastName = "";
		middleInit = "";
		title = "";
		salutation = "";
		address1 = "";
		address2 = "";
		city = "";
		state = "";
		zip = "";
		country = "";
		bPhone1 = "";
		bPhone2 = "";
		fax = "";
		pager = "";
		email = "";
		cPhone = "";
		hPhone = "";
		role = "";
		districtCode = "";
		branchCode = "";
		divisionCode = "";
		ticket = "";
		targetUrl = "";
		appName = "";
		password = "" ;
		hashValue = "" ;
	}

	public PortalUserInfo(String targetUrl, String ticket, String appName, String hostApp)
	{
		this() ;
		processTicket(targetUrl, ticket, appName, hostApp) ;
	}

	public void processTicket(String targetUrl,String ticket, String appName, String hostApp) {
		String fullUrl = targetUrl + "?SessionID=" + URLEncoder.encode(ticket) + "&AppName=" + URLEncoder.encode(appName)+ "&Application=" + URLEncoder.encode(hostApp) ;
		String	errorMsg = "" ;
		int		status = 0;

//		UrlPost u = new UrlPost() ;
//		u.ticketPost(targetUrl, ticket, appName) ;
		UrlPost u = new UrlPost(fullUrl) ;
		String inputString = u.getResult() ;

		Document jdoc = null;

		if (inputString == null) inputString= "" ;
		SAXBuilder parser = new SAXBuilder();
		StringReader sReader = new StringReader(inputString);
		try
		{
			jdoc = parser.build(sReader);
		}
		catch(JDOMException e)
		{
			System.out.println("Connection Exception: " + e.toString());
			errorMsg = "Connection returned an invalid result!" ;
			status = -1;
		}

		if (status == -1) {
			setStatusCode("8") ;
			setStatusDesc(errorMsg) ;
		} else {
			Element root = jdoc.getRootElement();
			setStatusCode(getChildValue(root, "Status", "Code"));
			setStatusDesc(getChildValue(root, "Status", "Desc"));
			setErrorUrl(getChildValue(root, "Status", "ErrorURL"));
			setUserType(getChildValue(root, "UserType"));
			setLoginId(getChildValue(root, "LoginID"));
			setFirstName(getChildValue(root, "FirstName"));
			setLastName(getChildValue(root, "LastName"));
			setMiddleInit(getChildValue(root, "MiddleInit"));
			setTitle(getChildValue(root, "Title"));
			setSalutation(getChildValue(root, "Salutation"));
			setAddress1(getChildValue(root, "Address", "Address1"));
			setAddress2(getChildValue(root, "Address", "Address2"));
			setCity(getChildValue(root, "Address", "City"));
			setState(getChildValue(root, "Address", "State"));
			setZip(getChildValue(root, "Address", "Zip"));
			setCountry(getChildValue(root, "Address", "Country"));
			setBPhone1(getChildValue(root, "BPhone1"));
			setBPhone2(getChildValue(root, "BPhone2"));
			setFax(getChildValue(root, "Fax"));
			setPager(getChildValue(root, "Pager"));
			setEmail(getChildValue(root, "Email"));
			setCPhone(getChildValue(root, "CPhone"));
			setHPhone(getChildValue(root, "HPhone"));
			setRole(getChildValue(root, "Role"));
			setOrganization(getChildValue(root, "Organization"));
			setLocation(getChildValue(root, "Location"));

			setDistrictCode(getChildValue(root, "Organization", "DistrictCode"));
			setBranchCode(getChildValue(root, "Organization", "BranchCode"));
			setDivisionCode(getChildValue(root, "Organization", "DivisionCode"));

			this.populateUserInfoMap() ;
			this.createUserInfoMap();
		}
		return ;

	}

	private void populateUserInfoMap() {

		this.attrMap.put("loginid",this.getLoginId());
		this.attrMap.put("firstname",this.getFirstName());
		this.attrMap.put("lastname",this.getLastName());
		this.attrMap.put("middleinit",this.getMiddleInit());
		this.attrMap.put("title",this.getTitle());
		this.attrMap.put("salutation",this.getSalutation());
		this.attrMap.put("address1",this.getAddress1());
		this.attrMap.put("address2",this.getAddress2());
		this.attrMap.put("city",this.getCity());
		this.attrMap.put("state",this.getState());
		this.attrMap.put("zip",this.getZip());
		this.attrMap.put("country",this.getCountry());
		this.attrMap.put("bphone1",this.getBPhone1());
		this.attrMap.put("bphone2",this.getBPhone2());
		this.attrMap.put("fax",this.getFax());
		this.attrMap.put("pager",this.getPager());
		this.attrMap.put("email",this.getEmail());
		this.attrMap.put("cphone",this.getCPhone());
		this.attrMap.put("hphone",this.getHPhone());
		this.attrMap.put("role",this.getRole());
		this.attrMap.put("location",this.getLocation());
		this.attrMap.put("divisioncode", this.getDivisionCode() ) ;
		this.attrMap.put("districtcode", this.getDistrictCode() ) ;
		return ;
	}

	public void processPortalUserInfo() {
		this.populateUserInfoMap() ;
		this.createUserInfoMap() ;
	}


	private String getChildValue(Element root, String key1)
	{
		String value = null;
		try
		{
			value = root.getChild(key1).getTextTrim();
		}
		catch(Exception _ex)
		{
			value = "";
		}
		return value;
	}

	private String getChildValue(Element root, String key1, String key2)
	{
		String value = null;
		try
		{
			value = root.getChild(key1).getChild(key2).getTextTrim();
		}
		catch(Exception _ex)
		{
			value = "";
		}
		return value;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getStatusDesc()
	{
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc)
	{
		this.statusDesc = statusDesc;
	}

	public String getErrorUrl()
	{
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl)
	{
		this.errorUrl = errorUrl;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleInit()
	{
		return middleInit;
	}

	public void setMiddleInit(String middleInit)
	{
		this.middleInit = middleInit;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSalutation()
	{
		return salutation;
	}

	public void setSalutation(String salutation)
	{
		this.salutation = salutation;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getBPhone1()
	{
		return bPhone1;
	}

	public void setBPhone1(String bPhone1)
	{
		this.bPhone1 = bPhone1;
	}

	public String getBPhone2()
	{
		return bPhone2;
	}

	public void setBPhone2(String bPhone2)
	{
		this.bPhone2 = bPhone2;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getPager()
	{
		return pager;
	}

	public void setPager(String pager)
	{
		this.pager = pager;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCPhone()
	{
		return cPhone;
	}

	public void setCPhone(String cPhone)
	{
		this.cPhone = cPhone;
	}

	public String getHPhone()
	{
		return hPhone;
	}

	public void setHPhone(String hPhone)
	{
		this.hPhone = hPhone;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getDistrictCode()
	{
		return districtCode;
	}

	public void setDistrictCode(String districtCode)
	{
		this.districtCode = districtCode;
	}

	public String getBranchCode()
	{
		return branchCode;
	}

	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Map getUserInfoMap() {
		return userInfoMap;
	}

	public String getExpireDateTime() {
		return expireDateTime;
	}

	public void setExpireDateTime(String expireDateTime) {
		this.expireDateTime = expireDateTime;
	}

	public String getHashValue() {
		return hashValue;
	}

	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void createUserInfoMap() {

		LDAPProperties ldap = LDAPProperties.getInstance();
		Map	columnMap = ldap.getLDAPColumnMap() ;
		String  oid = this.getOrganization() ;

		// Map Ldap attributes to database columns
		Collection c = columnMap.keySet() ;
		Iterator it = c.iterator() ;
		while (it.hasNext()) {
			String 	key = (String) it.next() ;
			String	attr = (String) columnMap.get(key) ;
			String  value = (String) this.attrMap.get(attr.toLowerCase()) ;

			if (value == null) value = "" ;
			if (key.equalsIgnoreCase("userId") && value.length() > 15) {
				value = value.substring(0, 15) ;
			}
			if (value != null) {
				userInfoMap.put("UserProfile_" + key, value) ;
			}
		}
		if (oid.startsWith("WPC")) {
			Calendar	cal =  Calendar.getInstance() ;
			String curYear = "0000" ;
			String curMonth = "00" ;
			String curDay = "00" ;
			String curHour = "00" ;
			String curMin = "00";
			String curSec = "00" ;
			try {
				curYear = Utility.formatString("0000", Integer.toString(cal.get(Calendar.YEAR))) ;
				curMonth = Utility.formatString("00", Integer.toString(cal.get(Calendar.MONTH)) + 1) ;
				curDay = Utility.formatString("00", Integer.toString(cal.get(Calendar.DAY_OF_MONTH))) ;
				curHour = Utility.formatString("00", Integer.toString(cal.get(Calendar.HOUR_OF_DAY))) ;
				curMin = Utility.formatString("00", Integer.toString(cal.get(Calendar.MINUTE))) ;
				curSec = Utility.formatString("00", Integer.toString(cal.get(Calendar.SECOND))) ;
			}
			catch (Exception e) {
				e.printStackTrace() ;
			}
			String curDate = curYear + curMonth + curDay + curHour + curMin + curSec ;
			HashGenerator hash = new HashGenerator("SHA1") ;

			String tempHash = hash.getHash(this.getLoginId() + this.getExpireDateTime() + "WorldPacPuridiom") ;
			if (this.getHashValue().equals(tempHash)) {
				wpcMapOverrides() ;
				if (curDate.compareTo(this.getExpireDateTime()) > 0) {
					this.setStatusCode("2") ;
					this.setStatusDesc("The login values contained in your request have expired!") ;
				}
			} else {
				this.setStatusCode("1") ;
				this.setStatusDesc("The login values failed to pass the verification process!") ;
			}
		}
	}

	private void wpcMapOverrides() {

		String  oid = this.getOrganization() ;
		String	wpcLocation = this.getLocation() ;
		String	department = this.getDivisionCode() ;
		String	title = this.getTitle() ;
		String  gpiCosting = department ;

		if (department == null) {
			this.userInfoMap.put("UserProfile_department", "001") ;
		} else {
		   if (department.equals("5010") || department.startsWith("96")) {
				this.userInfoMap.put("UserProfile_department", "001") ;
		   }
		   if (department.startsWith("0") && department.length() > 3) {
			   department = department.substring(1) ;
				this.userInfoMap.put("UserProfile_department", department) ;
			   }
			}

			if (gpiCosting.startsWith("09")) {
			   this.userInfoMap.put("UserProfile_nameUdf2", "9600") ;
			} else if (gpiCosting.startsWith("96")) {
			   this.userInfoMap.put("UserProfile_nameUdf2", gpiCosting) ;
			}

			if (wpcLocation == null) wpcLocation = "0" ;
			if (wpcLocation.length() <= 0) wpcLocation = "0";

			BigDecimal shipToDec = new BigDecimal(wpcLocation) ;
			if (shipToDec.compareTo(new BigDecimal(10)) < 0) {
			   wpcLocation = "960" + wpcLocation.trim();
			} else {
			   wpcLocation = "96" + wpcLocation.trim();
			}
			userInfoMap.put("UserProfile_shipToCode", wpcLocation) ;

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
			userInfoMap.put("UserProfile_approvalAmount", approvalAmount.toString()) ;
		}
}
