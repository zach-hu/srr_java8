package com.tsa.puridiom.supplierportal;

/**
 * Creation date: March 2004
 * @author: Kelli Knisely
 */
public class RegisterUser
{
	private boolean alternate = false;
	private boolean authenticated = false;
	private String contactCode = "";
	private String contactPassword = "";
	private boolean einVaild = false;
	private String emailAddress = "";
	private boolean emailAddressDuplicated = false;
	private String faxNumber = "";
	private String firstName = "";
	private String lastName = "";
	private String middleInitial = "";
	private String organizationId = "";
	private String phoneNumber = "";
	private boolean qualified = false;
	private boolean registered = false;
	private String vendorEin = "";
	private String vendorId = "";
	private String vendorName = "";
		
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
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the User
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.supplierportal.RegisterUser, ");
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
