/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.text.MessageFormat;
import java.util.List;

import com.tsa.puridiom.entity.SendPoFlag;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 */
public class AutoReleasedEmailText
{
    private AutoReleasedItemsSummary items;
    private Dictionary emailMessage;

    public AutoReleasedEmailText(Object object, String organizationId)
    {
        Log.debug(this, "AutoReleasedEmailText starts");
        items = (AutoReleasedItemsSummary)object;
        emailMessage = DictionaryManager.getInstance("emailmsg", organizationId);
    }

    public String getSupplierSendVia(String actionFlag)
    {
        String via = SendPoFlag.XML_PO_TEXT;
        if(actionFlag.equalsIgnoreCase(SendPoFlag.EMAIL_PO))
        {
            via = SendPoFlag.EMAIL_PO_TEXT;
        }
        else if(actionFlag.equalsIgnoreCase(SendPoFlag.PRINT_PO))
        {
            via = SendPoFlag.PRINT_PO_TEXT;
        }
        else if(actionFlag.equalsIgnoreCase(SendPoFlag.FAX_PO))
        {
            via = SendPoFlag.FAX_PO_TEXT;
        }
        else if(actionFlag.equalsIgnoreCase(SendPoFlag.EDI_PO))
        {
            via = SendPoFlag.EDI_PO_TEXT;
        }
        return via;
    }

    public String createHeaderText()
    {
        String header = emailMessage.getProperty("autorelease");
        String timeZone = items.getReleaseOrder().getTimeZone();

        Object args[] = new Object[5];
        args[0] = this.items.getReleaseOrder().getDisplayPoNumber();
        args[1] = items.getRequisitionNumber();
        args[2] = Dates.today("", timeZone) + " " +  Dates.getNow(null, timeZone);
        args[3] = this.items.getReleaseOrder().getVendorName();
        args[4] = this.getSupplierSendVia(items.getReleaseOrder().getEdiOrder());

        String result = MessageFormat.format(header, args);
        Log.debug(this, "header text: " + result);
        return result;
    }

    public String createXmlItemsText()
    {
        List itemsList = items.getItemsList();
        String xmlItemsLine = emailMessage.getProperty("itemLine");
        StringBuffer linesText = new StringBuffer();

        for(int i = 0; i < itemsList.size(); i++)
        {
            MiniItem item = (MiniItem)itemsList.get(i);
            Object args[] = new Object[4];
            args[0] = String.valueOf(i + 1);
            args[1] = item.getItemNumber();
            String temp = item.getDescription();
            if(temp.length() > 40)
            {
            	temp = temp.substring(0, 40);
            }
            args[2] = temp;
            args[3] = new Integer(item.getQuantity());

            linesText.append(MessageFormat.format(xmlItemsLine, args));
        }
        Log.debug(this, "lines text: " + linesText.toString());
        return linesText.toString();
    }

    public String buildEmail()
    {
        String header = this.createHeaderText();
        String line = this.createXmlItemsText();
        return header + line + "\n";
    }
}
