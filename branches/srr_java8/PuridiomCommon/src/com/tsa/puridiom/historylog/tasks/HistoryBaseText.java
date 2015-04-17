/*
 * Created on Dec 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HistoryBaseText implements IHistoryText
{
    private String organizationId = "PURIDIOM";
    private Object header = null;
    private Object line = null;
    private Vendor vendor = null;

    public HistoryBaseText(Object rHeader, Object rLine)
    {
        this.setHeader(rHeader);
        this.setLine(rLine);
    }

    public HistoryBaseText(Vendor vendor)
    {
        this.setVendor(vendor);
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#createText()
     */
    public String headerText(String text, String number)
    {
        StringBuffer ret = new StringBuffer(text);
        ret.append(number);

		return ret.toString();
    }
    
    public String headerText(String text, String number, String subtext, String subnumber)
    {
        StringBuffer ret = new StringBuffer(text);
        ret.append(number);
        ret.append(subtext);
        ret.append(subnumber);

		return ret.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#createLineText()
     */
    public String createLineText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#approveText()
     */
    public String approveText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String deleteText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteLineText()
     */
    public String deleteLineText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#getItemText()
     */
    public String getItemText()
    {
        StringBuffer text = new StringBuffer("");

        String itemNumber = this.getItemNumber();
        String lineNumber = this.getLineNumber();
        text.append("Line " + lineNumber + " ");
        if(!Utility.isEmpty(itemNumber))
        {
            text.append("Item #" + itemNumber);
        }
        else
        {
            String description = this.getDescription();
            if(description.length() > 30)
            {
                description = description.substring(0, 30) + "...";
            }
            text.append("Item - " + description);
        }

        return text.toString();
    }

    /**
     * @return
     */
    public String getLineNumber()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    public String getDescription()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    public String getItemNumber()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getHeader()
    {
        return header;
    }
    public void setHeader(Object header)
    {
        this.header = header;
    }
    public Object getLine()
    {
        return line;
    }
    public void setLine(Object line)
    {
        this.line = line;
    }
    public String getOrganizationId()
    {
        return organizationId;
    }
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
    public Vendor getVendor()
    {
    	return vendor;
    }
    public void setVendor(Vendor vendor)
    {
    	this.vendor = vendor;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(" organizationId: ");
        buffer.append(organizationId);
        buffer.append(" header: ");
        buffer.append(header);
        buffer.append(" line: ");
        buffer.append(line);
        buffer.append(" vendor: ");
        buffer.append(vendor);

        return buffer.toString();
    }
}
