/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DsbPdfSentTo extends EmailSentTo
{
    public DsbPdfSentTo(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	DisbHeader disbHeader = (DisbHeader)header;
        StringBuffer temp = super.readFile(this.getTemplatePath());
        String message = temp.toString();
        String owner;
		try
		{
			owner = UserManager.getInstance().getUser(super.getOrganizationId(), disbHeader.getOwner()).getDisplayName();
		}
		catch (Exception e)
		{
			owner = disbHeader.getOwner();
		}
        message = EmailUtils.replace(EmailSentTo.OWNER, message, owner);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, disbHeader.getRequisitionNumber());
        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    public String fileName(String path)
    {
        path = this.getDirectory(path, "dsbpdfto.htm");
        Log.debug(this, "file: " + path);

        return path;
    }
}
