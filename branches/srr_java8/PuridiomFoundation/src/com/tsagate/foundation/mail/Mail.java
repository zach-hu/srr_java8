/*
 * mail.java
 *
 * Created on August 16, 2002, 9:43 AM
 */

package com.tsagate.foundation.mail;

/**
 *
 * @author  renzo
 */


public class Mail 
{    
    /** Holds value of property to. */
    private String to;
    
    private String from;
    
    private String bcc;
    
    private String cc;
    
    private String title;
    
    private String message;
    
    private String smtp;
        
    /** Holds value of property extras. */
    private String extras;
    
    /** Creates a new instance of mail */
    public Mail() 
    {
    }
    
    public Mail(String asSmtp, String asTo, String asFrom, java.lang.String asBCC, java.lang.String asCC, java.lang.String asTitle, java.lang.String asMessage) 
    {
        this.setTo(asTo);
        this.setFrom(asFrom);
        this.setBcc(asBCC);
        this.setCc(asCC);
        this.setMessage(asMessage);
        this.setTitle(asTitle);
        this.setSmtp(asSmtp);
    }
    
    /** Getter for property to.
     * @return Value of property to.
     */
    public String getTo() 
    {
        return this.to;
    }
    
    /** Setter for property to.
     * @param to New value of property to.
     */
    public void setTo(String to) 
    {
        this.to = to;
    }
    
    /** Getter for property bcc.
     * @return Value of property bcc.
     */
    public java.lang.String getBcc() 
    {
        return bcc;
    }
    
    /** Setter for property bcc.
     * @param bcc New value of property bcc.
     */
    public void setBcc(java.lang.String bcc) 
    {
        this.bcc = bcc;
    }
    
    /** Getter for property cc.
     * @return Value of property cc.
     */
    public java.lang.String getCc() 
    {
        return cc;
    }
    
    /** Setter for property cc.
     * @param cc New value of property cc.
     */
    public void setCc(java.lang.String cc) 
    {
        this.cc = cc;
    }
    
    /** Getter for property from.
     * @return Value of property from.
     */
    public java.lang.String getFrom() 
    {
        return from;
    }
    
    /** Setter for property from.
     * @param from New value of property from.
     */
    public void setFrom(java.lang.String from) 
    {
        this.from = from;
    }
    
    /** Getter for property message.
     * @return Value of property message.
     */
    public java.lang.String getMessage() 
    {
        return message;
    }
    
    /** Setter for property message.
     * @param message New value of property message.
     */
    public void setMessage(java.lang.String message) 
    {
        this.message = message;
    }
    
    /** Getter for property title.
     * @return Value of property title.
     */
    public java.lang.String getTitle() 
    {
        return title;
    }
    
    /** Setter for property title.
     * @param title New value of property title.
     */
    public void setTitle(java.lang.String title) 
    {
        this.title = title;
    }
    
    
    /** Getter for property smtp.
     * @return Value of property smtp.
     */
    public java.lang.String getSmtp() 
    {
        return smtp;
    }
    
    /** Setter for property smtp.
     * @param smtp New value of property smtp.
     */
    public void setSmtp(java.lang.String smtp) 
    {
        this.smtp = smtp;
    }
    
    /** Getter for property extras.
     * @return Value of property extras.
     */
    public String getExtras() 
    {
        return this.extras;
    }
    
    /** Setter for property extras.
     * @param extras New value of property extras.
     */
    public void setExtras(String extras) 
    {
        this.extras = extras;
    }    
}
