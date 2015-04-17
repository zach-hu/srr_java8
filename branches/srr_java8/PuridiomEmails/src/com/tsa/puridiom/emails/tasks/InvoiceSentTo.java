/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.entity.InvoiceHeader;
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

public class InvoiceSentTo extends EmailSentTo
{
    public InvoiceSentTo(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	InvoiceHeader invoiceHeader = (InvoiceHeader)header;
        String buyerName = "";
        String buyerEmail = "";
        String buyerPhone = "";
        try
        {
        	//UserProfile buyerProfile = UserManager.getInstance().getUser(this.getOrganizationId(), invoiceHeader.getOrderByName());
            buyerName = invoiceHeader.getOrderByName();
            buyerPhone = invoiceHeader.getOrderByPhone();
            buyerEmail = invoiceHeader.getOrderByEmail();
            //buyerEmail = buyerProfile.getMailId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        StringBuffer temp = this.readFile(this.getTemplatePath());
        String message = temp.toString();
        message = EmailUtils.replace(EmailSentTo.BUYER, message, buyerName);
        //message = this.getBuyerDetailsFormat(buyerEmail, buyerPhone, message);
        message = EmailUtils.replace(EmailSentTo.BUYER_EMAIL, message, buyerEmail);
        message = EmailUtils.replace(EmailSentTo.BUYER_PHONE, message, buyerPhone);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, "Invoice #" + invoiceHeader.getInvoiceNumber());
        //message = EmailUtils.replace(EmailSentTo.GCS_DELIVERY_DATE, message, HiltonUtility.getFormattedDate(invoiceHeader.getPromisedDate(), this.getOrganizationId()));
        message = EmailUtils.replace(EmailSentTo.GCS_TOTAL, message, HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), this.getOrganizationId()).toString());
        message = EmailUtils.replace(EmailSentTo.GCS_DESCRIPTION, message, invoiceHeader.getInvoiceDesc());
        //message = EmailUtils.replace(EmailSentTo.GCS_VENDOR_NAME, message, invoiceHeader.getVendorName());

        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    private String getBuyerDetailsFormat(String buyerEmail, String buyerPhone, String message)
    {
		if(!HiltonUtility.isEmpty(buyerEmail))
		{
			message = EmailUtils.replace(EmailSentTo.COMMENT_START, message, "");
			message = EmailUtils.replace(EmailSentTo.COMMENT_END, message, "");
			message = EmailUtils.replace(EmailSentTo.BUYER_EMAIL, message, buyerEmail);
		}
		message = EmailUtils.replace(EmailSentTo.BUYER_PHONE, message, buyerPhone);
		return message;
	}

	public String fileName(String path)
    {
        path = this.getDirectory(path, "invoicesentto.htm");
        Log.debug(this, "file: " + path);

        return path;
    }
}
