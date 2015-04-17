package com.tsa.puridiom.supplierportal;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.Date;

/**
 * Creation date: March 2004
 * @author: Kelli Knisely
 */
public class BidBoardUser
{
	private boolean authenticated = false;
	private String emailAddress = "";
	private String firstName = "";
	private String middleInitial = "";
	private String lastName = "";
	private String organizationId = "";
	private String status = "";
	private String userPassword = "";
	private String vendorId = "";
	private String vendorName = "";
	private String contactCode = "";
	private String contactType = "";
	private String addressCode = "";
	private String passChanged = "";
	private boolean guest = false;
	private boolean qualified = false;
	private boolean inactive = false;
	private int loginAttempts = 0;
	private Date loginTime = null;
	private String lockLogin = "N";

	/**
	 * BidBoardUser constructor
	 */
	public BidBoardUser()
	{
		super();
	}

	/**
	 * @return boolean
	 */
	public boolean isAuthenticated()
	{
		return this.authenticated;
	}
	/**
	 * @return String
	 */
	public String getDisplayName()
	{
    	StringBuffer name = new StringBuffer();
    	if (!HiltonUtility.isEmpty(this.firstName)) {
    		name.append(this.firstName.trim());
    	}
    	if (!HiltonUtility.isEmpty(this.middleInitial)) {
    		if (name.length() > 0) {
    			name.append(" " + this.middleInitial.trim());
    		} else {
    			name.append(this.middleInitial.trim());
    		}
    	}
    	if (!HiltonUtility.isEmpty(this.lastName)) {
    		if (name.length() > 0) {
    			name.append(" " + this.lastName.trim());
    		} else {
    			name.append(this.lastName.trim());
    		}
    	}
    	return name.toString();
	}
	/**
	 * @return String
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}
	/**
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}
	/**
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @return String
	 */
	public String getMiddleInitial()
	{
		return middleInitial;
	}
	/**
	 * @return String
	 */
	public String getOrganizationId()
	{
		return organizationId;
	}
	/**
	 * @return String
	 */
	public String getStatus()
	{
		return status;
	}
	/**
	 * @return String
	 */
	public String getUserPassword()
	{
		return userPassword;
	}
	/**
	 * @return String
	 */
	public String getVendorId()
	{
		return vendorId;
	}
	/**
	 * @return String
	 */
	public String getContactCode()
	{
		return contactCode;
	}
	/**
	 * @return String
	 */
	public String getContactType()
	{
		return contactType;
	}
	/**
	 * @return String
	 */
	public String getAddressCode()
	{
		return addressCode;
	}
	/**
	 * @return String
	 */
	public String getPassChanged()
	{
		return passChanged;
	}
	/**
	 * @return boolean
	 */
	public boolean isGuest()
	{
		return guest;
	}
	/**
	 * @return boolean
	 */
	public boolean isQualified()
	{
		return qualified;
	}
	/**
	 * @return boolean
	 */
	public boolean isInactive()
	{
		return inactive;
	}
	/**
	 * @return int
	 */
	public int getLoginAttempts()
	{
		return loginAttempts;
	}
	/**
	 * @return Date
	 */
	public Date getLoginTime()
	{
		return loginTime;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setAuthenticated(boolean newBoolean)
	{
		authenticated = newBoolean;
	}
	/**
	 * @param newString String
	 */
	public void setEmailAddress(String newString)
	{
		emailAddress = newString;
	}
	/**
	 * @param newString String
	 */
	public void setFirstName(String newString)
	{
		firstName = newString;
	}
	/**
	 * @param newString String
	 */
	public void setLastName(String newString)
	{
		lastName = newString;
	}
	/**
	 * @param newString String
	 */
	public void setMiddleInitial(String newString)
	{
		middleInitial = newString;
	}
	/**
	 * @param newString String
	 */
	public void setOrganization(String newString)
	{
		organizationId = newString;
	}
	/**
	 * @param newString String
	 */
	public void setStatus(String newString)
	{
		status = newString;
	}
	/**
	 * @param newString String
	 */
	public void setUserPassword(String newString)
	{
		userPassword = newString;
	}
	/**
	 * @param newString String
	 */
	public void setVendorId(String newString)
	{
		vendorId = newString;
	}
	/**
	 * @param newString String
	 */
	public void setContactCode(String newString)
	{
		contactCode = newString;
	}
	/**
	 * @param newString String
	 */
	public void setContactType(String newString)
	{
		contactType = newString;
	}
	/**
	 * @param newString String
	 */
	public void setAddressCode(String newString)
	{
		addressCode = newString;
	}
	/**
	 * @param newString String
	 */
	public void setPassChanged(String newString)
	{
		passChanged = newString;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setGuest(boolean newBoolean)
	{
		guest = newBoolean;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setQualified(boolean newBoolean)
	{
		qualified = newBoolean;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setInactive(boolean newBoolean)
	{
		inactive = newBoolean;
	}

    /**
     * @return Returns the vendorName.
     */
    public String getVendorName() {
        return HiltonUtility.ckNull(vendorName);
    }
    /**
     * @param vendorName The vendorName to set.
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    /**
	 * @param newInt int
	 */
	public void setLoginAttempts(int newInt)
	{
		loginAttempts = newInt;
	}
	/**
	 * @param newDate Date
	 */
	public void setLoginTime(Date newDate)
	{
		loginTime = newDate;
	}

	/**
     * @return Returns the lockLogin.
     */
    public String getLockLogin() {
        return HiltonUtility.ckNull(lockLogin);
    }
    /**
     * @param lockLogin The lockLogin to set.
     */
    public void setLockLogin(String lockLogin) {
    	this.lockLogin = lockLogin;
    }

	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the User
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsagate.common.service.security.User, ");
		sb.append("FIRST NAME=" + this.getFirstName() + ", ");
		sb.append("MIDDLE INITIAL=" + this.getMiddleInitial() + ", ");
		sb.append("LAST NAME=" + this.getLastName() + ", ");
		sb.append("STATUS=" + this.getStatus() + ", ");
		sb.append("EMAIL ADDRESS=" + this.getEmailAddress() + ", ");
		sb.append("]");

		return sb.toString();
	}
}
