/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class RecSentTo extends EmailSentTo
{
    public RecSentTo(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	ReceiptHeader receiptHeader = (ReceiptHeader)header;

        StringBuffer temp = this.readFile(this.getTemplatePath());
        String message = temp.toString();
        String owner = "";
        try
		{
			owner = UserManager.getInstance().getUser(super.getOrganizationId(), receiptHeader.getOwner()).getDisplayName();
		}
		catch (Exception e)
		{
			owner = receiptHeader.getOwner();
		}
        message = EmailUtils.replace(EmailSentTo.OWNER, message, owner);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, receiptHeader.getReceiptNumber());
        message = EmailUtils.replace(EmailSentTo.PO_NUMBER, message, receiptHeader.getRefNumber());
        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    public String fileName(String path)
    {
        path = this.getDirectory(path, "recpdfto.htm");
        Log.debug(this, "file: " + path);

        return path;
    }
}
