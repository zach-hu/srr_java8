package com.tsa.puridiom.vendorregistration;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.Date;

/**
 * Creation date: July 25, 2006
 * @author: Kelli Knisely
 */
public class RegisterUser
{
	private String contactCode = "";
	private String contactPassword = "";
	private String emailAddress = "";
	private String faxNumber = "";
	private String firstName = "";
	private String lastName = "";
	private String middleInitial = "";
	private String organizationId = "";
	private String phoneNumber = "";
	private String vendorEin = "";
	private String vendorId = "";
	private String vendorName = "";
	private String contactType = "";
	private String addressCode = "";
	private String passChanged = "";
	private String status = "";
	private String termsAccepted = "";
	private boolean alternate = false;
	private boolean authenticated = false;
	private boolean qualified = false;
	private boolean registered = false;
	private boolean inactive = false;
	private boolean einVaild = false;
	private boolean emailAddressDuplicated = false;
    private boolean guest = false;
    private boolean tempPassword = false;
	private int loginAttempts = 0;
	private Date loginTime = null;

	/**
	 * RegisterUser constructor
	 */
	public RegisterUser() {
		super();
	}
	/**
	 * @return boolean
	 */
	public boolean isAlternate() {
		return alternate;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setAlternate(boolean newBoolean) {
		alternate = newBoolean;
	}
	/**
	 * @return boolean
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setAuthenticated(boolean newBoolean) {
		authenticated = newBoolean;
	}
	/**
	 * @return String
	 */
	public String getContactCode() {
		return contactCode;
	}
	/**
	 * @param newString String
	 */
	public void setContactCode(String newString) {
		contactCode = newString;
	}
	/**
	 * @return String
	 */
	public String getContactPassword() {
		return contactPassword;
	}
	/**
	 * @param newString String
	 */
	public void setContactPassword(String newString) {
		contactPassword = newString;
	}
	/**
	 * @return boolean
	 */
	public boolean isEinValid() {
		return einVaild;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setEinValid(boolean newBoolean) {
		einVaild = newBoolean;
	}
	/**
	 * @return String
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param newString String
	 */
	public void setEmailAddress(String newString) {
		emailAddress = newString;
	}
	/**
	 * @return boolean
	 */
	public boolean isEmailAddressDuplicated() {
		return emailAddressDuplicated;
	}
	/**
	 * @param newBoolean boolean
	 */
	public void setEmailAddressDuplicated(boolean newBoolean) {
		emailAddressDuplicated = newBoolean;
	}
	/**
	 * @return String
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param newString String
	 */
	public void setFaxNumber(String newString){
		faxNumber = newString;
	}
	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param newString String
	 */
	public void setFirstName(String newString){
		firstName = newString;
	}
	/**
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param newString String
	 */
	public void setLastName(String newString){
		lastName = newString;
	}
	/**
	 * @return String
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	/**
	 * @param newString String
	 */
	public void setMiddleInitial(String newString) {
		middleInitial = newString;
	}
	/**
	 * @return String
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param newString String
	 */
	public void setOrganizationId(String newString) {
		organizationId = newString;
	}
	/**
	 * @return String
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param newString String
	 */
	public void setPhoneNumber(String newString) {
		phoneNumber = newString;
	}
	/**
	 * @return String
	 */
	public String getVendorEin() {
		return vendorEin;
	}
	/**
	 * @param newString String
	 */
	public void setVendorEin(String newString) {
		vendorEin = newString;
	}
	/**
	 * @return String
	 */
	public String getVendorId() {
		return vendorId;
	}
	/**
	 * @param newString String
	 */
	public void setVendorId(String newString) {
		vendorId = newString;
	}
	/**
	 * @return String
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param newString String
	 */
	public void setVendorName(String newString) {
		vendorName = newString;
	}
	/**
	 * @return boolean
	 */
	public boolean isQualified() {
		return qualified;
	}
	/**
	 * @param boolean newBoolean
	 */
	public void setQualified(boolean newBoolean) {
		qualified = newBoolean;
	}
	/**
	 * @return boolean
	 */
	public boolean isRegistered() {
		return registered;
	}
	/**
	 * @param boolean newBoolean
	 */
	public void setRegistered(boolean newBoolean) {
		registered = newBoolean;
	}
	/**
	 * @return boolean
	 */
	public boolean isInactive()
	{
		return inactive;
	}
    /**
     * @return Returns the einVaild.
     */
    public boolean isEinVaild() {
        return einVaild;
    }
    /**
     * @param einVaild The einVaild to set.
     */
    public void setEinVaild(boolean einVaild) {
        this.einVaild = einVaild;
    }
    /**
     * @param inactive The inactive to set.
     */
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
	/**
	 * @return int
	 */
	public int getLoginAttempts()
	{
		return loginAttempts;
	}
    /**
     * @param loginAttempts The loginAttempts to set.
     */
    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
	/**
	 * @return Date
	 */
	public Date getLoginTime()
	{
		return loginTime;
	}
    /**
     * @param loginTime The loginTime to set.
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    /**
     * @return Returns the addressCode.
     */
    public String getAddressCode() {
        return addressCode;
    }
    /**
     * @param addressCode The addressCode to set.
     */
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }
    /**
     * @return Returns the contactType.
     */
    public String getContactType() {
        return contactType;
    }
    /**
     * @param contactType The contactType to set.
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    /**
     * @return Returns the passChanged.
     */
    public String getPassChanged() {
        return passChanged;
    }
    /**
     * @param passChanged The passChanged to set.
     */
    public void setPassChanged(String passChanged) {
        this.passChanged = passChanged;
    }
    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return Returns the termsAccepted.
     */
    public String getTermsAccepted() {
        return termsAccepted;
    }
    /**
     * @param termsAccepted The termsAccepted to set.
     */
    public void setTermsAccepted(String termsAccepted) {
        this.termsAccepted = termsAccepted;
    }
    public boolean isTermsAcceptedSet() {
        if (HiltonUtility.isEmpty(this.termsAccepted) || this.termsAccepted.equalsIgnoreCase("N")) {
            return false;
        }
        return true;
    }
    public void setTempPassword(boolean tempPassword) {
        this.tempPassword = tempPassword;
    }
    public boolean isTempPassword() {
        return tempPassword;
    }
    public void setGuest(boolean guest) {
        this.guest = guest;
    }
    public boolean isGuest() {
        return this.guest;
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
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the User
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.vendorregistration.RegisterUser, ");
		sb.append("ALTERNATE=" + this.isAlternate() + "; ");
		sb.append("EIN VALID=" + this.isEinValid() + "; ");
		sb.append("EMAIL ADDRESS=" + this.getEmailAddress() + "; ");
		sb.append("EMAIL DUPLICATED=" + this.isEmailAddressDuplicated() + "; ");
		sb.append("FAX NUMBER=" + this.getFaxNumber() + "; ");
		sb.append("FIRST NAME=" + this.getFirstName() + "; ");
		sb.append("FIRST NAME=" + this.getFirstName()  + "; ");
		sb.append("MIDDLE INITIAL=" + this.getMiddleInitial() + "; ");
		sb.append("LAST NAME=" + this.getLastName() + "; ");
		sb.append("PHONE NUMBER=" + this.getPhoneNumber() + "; ");
		sb.append("QUALIFIED=" + this.isQualified() + "; ");
		sb.append("REGISTERED=" + this.isRegistered() + "; ");
		sb.append("VENDOR EIN=" + this.getVendorEin() + "; ");
		sb.append("VENDOR NAME=" + this.getVendorName() + "; ");
		sb.append("]");

		return sb.toString();
	}
}
