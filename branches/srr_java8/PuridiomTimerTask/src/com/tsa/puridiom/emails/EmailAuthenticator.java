/*
 * Created on Aug 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 */
public class EmailAuthenticator extends Authenticator
{
    private String userName = "";
    private String userPassword = "";
    public String props = "";
    private String organizationId = "";
    public String jobType;

    public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public void setProps()
    {
		if(!HiltonUtility.isEmpty(jobType))
		{
			String user = DictionaryManager.getInstance("emails", this.organizationId).getProperty(jobType + ".userid", "");
			if(HiltonUtility.isEmpty(user))
			{
				user = DictionaryManager.getInstance("emails", this.organizationId).getProperty("mail.userid", "test1");
			}
			this.setUserName(user);
			String password = DictionaryManager.getInstance("emails", this.organizationId).getEncryptedProperty(jobType + ".password", "");
			if(HiltonUtility.isEmpty(password))
			{
				password = DictionaryManager.getInstance("emails", this.organizationId).getEncryptedProperty("mail.password", "tester1");
			}
			this.setUserPassword(password);
		}
		else
		{
	       this.setUserName(DictionaryManager.getInstance("emails", this.organizationId).getProperty("mail.userid", "test1"));
	       this.setUserPassword(DictionaryManager.getInstance("emails", this.organizationId).getEncryptedProperty("mail.password", "tester1"));
		}
    }

    /* (non-Javadoc)
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    public PasswordAuthentication getPasswordAuthentication()
    {
        this.setProps();
        Log.debug(this, "userName: " + this.getUserName());
        Log.debug(this, "userPassword: " + this.getUserPassword());
        Log.debug(this, "organizationId: " + this.getOrganizationId());
        return new PasswordAuthentication(this.getUserName(), this.getUserPassword());
    }
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return Returns the userPassword.
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * @param userPassword The userPassword to set.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	/**
	 * test main method
	 */
	public static void main (String [] args) {
		EmailAuthenticator emailAuthenticator = new EmailAuthenticator();
		
		emailAuthenticator.setOrganizationId("p4test");
		emailAuthenticator.setProps();
		
		System.out.println("username: " + emailAuthenticator.getUserName());
		System.out.println("password: " + emailAuthenticator.getUserPassword());
	}
}
