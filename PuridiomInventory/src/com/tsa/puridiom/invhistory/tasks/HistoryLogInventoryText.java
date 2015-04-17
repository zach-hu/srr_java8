/*
 * Created on Jul 19, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogPoText.java
 *
 */
package com.tsa.puridiom.invhistory.tasks;

import java.math.BigDecimal;
import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.historylog.tasks.HistoryBaseText;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogInventoryText extends HistoryBaseText
{
	private String reason = "";
    /**
     * @param rHeader
     * @param rLine
     */
    public HistoryLogInventoryText(Object rHeader, Object rLine)
    {
        super(rHeader, rLine);
        // TODO Auto-generated constructor stub
    }
    public HistoryLogInventoryText(InvItem newInvItem)
    {
        super(newInvItem, null);
    }

    public String headerText(String text)
    {
        return super.headerText(text, this.getInvItem().getItemNumber());
    }

    public String forwardText()
    {
        return this.headerText("Forwarded ");
    }

    public String createInventory(String user)
    {
    	//Write a History_log record: Inventory Item xxxxxx created by xxxxxx.
    	String text = "Inventory Item ";
    	if(!Utility.isEmpty(this.getInvItem().getItemNumber()))
    	{
    		text = text + this.getInvItem().getItemNumber() + " created by " + user;
    	}
    	return text;
    }
    
    public String approvingText(String user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Approved Item "));
        sb.append(".");
        if(!Utility.isEmpty(user))
        {
            sb.append(" Forwarded to " + user);
            sb.append(" for Approval.");
        }
        return sb.toString();
    }
    
    public String approveText()
    {
        StringBuffer createText = new StringBuffer();
        createText.append(this.headerText("Approved Item "));

        String temp = ".";
        createText.append(temp);

        return createText.toString();
    }
    
    public String rejectText(String user)
    {
        StringBuffer createText = new StringBuffer();
        createText.append(this.headerText("Reject Item "));

        createText.append(".");
        if(!Utility.isEmpty(user))
        {
        	createText.append(" reject by " + user + ".");
        }
        return createText.toString();
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogPoText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }
    public String getDescription()
    {
        return this.getPoLine().getDescription();
    }
    public InvItem getInvItem()
    {
        return (InvItem)this.getHeader();
    }
    public String getItemNumber()
    {
        return this.getPoLine().getItemNumber();
    }
    public String getLineNumber()
    {
        return this.getPoLine().getLineNumber().toString();
    }
    public PoLine getPoLine()
    {
        return (PoLine)this.getLine();
    }
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
