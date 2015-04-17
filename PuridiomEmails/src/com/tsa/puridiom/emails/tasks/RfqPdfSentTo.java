/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class RfqPdfSentTo extends EmailSentTo
{
    public RfqPdfSentTo(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	RfqHeader rfqHeader = (RfqHeader)header;
        StringBuffer temp = super.readFile(this.getTemplatePath());
        String message = temp.toString();
        String owner;
		try
		{
			owner = UserManager.getInstance().getUser(super.getOrganizationId(), rfqHeader.getOwner()).getDisplayName();
		}
		catch (Exception e)
		{
			owner = rfqHeader.getOwner();
		}
        message = EmailUtils.replace(EmailSentTo.OWNER, message, owner);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, rfqHeader.getRfqNumber());
        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    public String fileName(String path)
    {
        path = this.getDirectory(path, "rfqpdfto.htm");
        Log.debug(this, "file: " + path);

        return path;
    }
}
