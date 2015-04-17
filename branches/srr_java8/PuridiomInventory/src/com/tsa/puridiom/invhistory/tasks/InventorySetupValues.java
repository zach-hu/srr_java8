/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.PoSetupValues.java
 *
 */
package com.tsa.puridiom.invhistory.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.historylog.tasks.HistoryLogPoText;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;

public class InventorySetupValues extends HistoryLogSetupValues
{
    private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";
    private String buyerRemarks;
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(InvItem newInvItem)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newInvItem);


        return history;
    }

    public HistoryLog setText(InvItem newInvItem, HistoryLog history)
    {
    	HistoryLogInventoryText text = new HistoryLogInventoryText(newInvItem);
        text.setReason(this.getReason());
        history.setUserid(this.getUserId());
        String status = newInvItem.getStatus();

        text.setOrganizationId(this.getOrganizationId());
        String historyStatus = (String)this.getHistoryStatus();

        if (historyStatus.equals("CREATE"))
        {
        	history.setDescription(text.createInventory(this.getUserId()));
        }
        else if(historyStatus.equals("5115"))
        {
            history.setDescription(text.approvingText(this.getForwardedTo()));
        }
        else if(historyStatus.equals("5130"))
        {
            history.setDescription(text.approveText());
        }
        else if (historyStatus.equals("REJECT"))
        {
        	history.setDescription(text.rejectText(this.getUserId()));
        }
        else
        {
            history = null;
        }
        return history;
    }

    private HistoryLog getHistoryRecord(InvItem newInvItem)
    {
        HistoryLog history = this.invItemSetup(null, newInvItem);
        history = this.setText(newInvItem, history);

        return history;
    }


    public HistoryLog invItemSetup(HistoryLog history, InvItem newInvItem)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(newInvItem.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(newInvItem.getIcHeaderHistory());
        history.setIcLine(new BigDecimal("0"));
        history.setDoctype("INV");
        history.setStatus(newInvItem.getStatus());
        return history;
    }
    public boolean isAutoReleased()
    {
        return autoReleased;
    }
    public void setAutoReleased(boolean autoReleased)
    {
        this.autoReleased = autoReleased;
    }

	public String getNextUser() {
		return nextUser;
	}

	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBuyerRemarks() {
		return buyerRemarks;
	}

	public void setBuyerRemarks(String buyerRemarks)
	{
		this.buyerRemarks = "Procurement Remarks: " + buyerRemarks;
	}
}
