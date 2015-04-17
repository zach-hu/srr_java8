/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class PoSentToSupplier extends EmailSentTo
{
    public PoSentToSupplier(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	PoHeader poHeader = (PoHeader)header;
        String buyerName = "";
        String buyerEmail = "";
        String buyerPhone = "";
        String buyerLocale = "";
        try
        {
        	UserProfile buyerProfile = UserManager.getInstance().getUser(this.getOrganizationId(), poHeader.getBuyerCode());
            buyerName = buyerProfile.getDisplayName();
            buyerPhone = buyerProfile.getPhoneNumber();
            buyerEmail = buyerProfile.getMailId();
            buyerLocale = buyerProfile.getLocale();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.setLocale(buyerLocale);
        StringBuffer temp = this.readFile(this.getTemplatePath());
        String message = temp.toString();
        message = EmailUtils.replace(EmailSentTo.BUYER, message, buyerName);
        message = this.getBuyerDetailsFormat(buyerEmail, buyerPhone, buyerLocale, message);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, poHeader.getDisplayPoNumber().toString());
        message = EmailUtils.replace(EmailSentTo.GCS_DELIVERY_DATE, message, HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), this.getOrganizationId()));
        message = EmailUtils.replace(EmailSentTo.GCS_TOTAL, message, HiltonUtility.getCurrency(poHeader.getTotal(), poHeader.getCurrencyCode(), this.getOrganizationId()).toString());
        message = EmailUtils.replace(EmailSentTo.GCS_DESCRIPTION, message, poHeader.getInternalComments());
        message = EmailUtils.replace(EmailSentTo.GCS_VENDOR_NAME, message, poHeader.getVendorName());
        message = EmailUtils.replace(EmailSentTo.ACKNOWLEDGEMENT_LINK, message, this.getEmailLink());

        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    private String getBuyerDetailsFormat(String buyerEmail, String buyerPhone, String buyerLocale, String message)
    {
		if(!HiltonUtility.isEmpty(buyerEmail))
		{
			message = EmailUtils.replace(EmailSentTo.COMMENT_START, message, "");
			message = EmailUtils.replace(EmailSentTo.COMMENT_END, message, "");
			message = EmailUtils.replace(EmailSentTo.BUYER_EMAIL, message, buyerEmail);
		}
		message = EmailUtils.replace(EmailSentTo.BUYER_PHONE, message, buyerPhone);
		message = EmailUtils.replace(EmailSentTo.BUYER_LOCALE, message, buyerLocale);
		return message;
	}

	public String fileName(String path)
    {
		String locale = this.getLocale();
		if(HiltonUtility.isEmpty(locale))
		{
			locale="US";
		}
		if(locale.equalsIgnoreCase("PL"))
		{
			path = this.getDirectory(path, "pdftosupplierpl.htm");
		}
		else if (locale.equalsIgnoreCase("DE"))
		{
			path = this.getDirectory(path, "pdftosupplierde.htm");
		}
		else
		{
			path = this.getDirectory(path, "pdftosupplier.htm");
		}
		Log.debug(this, "file: " + path);
        return path;
    }
}
