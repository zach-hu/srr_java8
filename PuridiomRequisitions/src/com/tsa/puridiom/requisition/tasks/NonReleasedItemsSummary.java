/*
 * Created on Apr 29, 2005
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class NonReleasedItemsSummary extends ReqEmailItemsSummary
{
    private String assignedBuyer = "";
    private String reason;


    public NonReleasedItemsSummary()
    {
    	super();
    }
    /**
     * @param header
     * @param itemsList
     */
    public NonReleasedItemsSummary(RequisitionHeader header, Object itemsList)
    {
        super(header, itemsList);
    }

    public NonReleasedItemsSummary(RequisitionHeader header, Object itemsList, String assignedBuyer)
    {
        this(header, itemsList);
        this.setAssignedBuyer(assignedBuyer);
    }

    public String getEmailText(String organizationId)
    {
        StringBuffer emailTxt = new StringBuffer("");
        Log.debug(this, "NonReleasedItems.getText, " + organizationId);
        emailTxt.append("The following items have been assigned to " + this.getAssignedBuyerName(organizationId) + ":\r\n");

        List itemsList = this.getItemsList();
        for(int i = 0; i < itemsList.size(); i++)
        {
            MiniItem line = (MiniItem)itemsList.get(i);
            String temp = line.getDescription();
            if(temp.length() > 40)
            {
            	temp = temp.substring(0, 40);
            }
            emailTxt.append(String.valueOf(i + 1) + ". " + line.getItemNumber() + "\t" + temp + "\t Qty: " + line.getQuantity() + "\r\n");
            if(!Utility.isEmpty(line.getReason()))
            {
            	if(line.getReason().equalsIgnoreCase(NoAutoReleaseReasons.CATALOG_NO_RELEASES))
            	{
            		if(!Utility.isEmpty(line.getCatalog()))
            		{
            			emailTxt.append("Reason : " + "Catalog [" + line.getCatalog() + "]" + NoAutoReleaseReasons.getMessage(line.getReason(), organizationId) + "\r\n");
            		}
            		else
            		{
            			emailTxt.append("Item " + NoAutoReleaseReasons.getMessage(line.getReason(), organizationId) + "\r\n");
            		}
            	}
            	else
            	{
            		emailTxt.append("Reason: " + NoAutoReleaseReasons.getMessage(line.getReason(), organizationId) + "\r\n");
            	}
            }
        }

        Log.debug(this, "NonReleasedItems.getText, returns: " + emailTxt.toString());
        return emailTxt.toString();
    }

    public String getAssignedBuyer()
    {
        return assignedBuyer;
    }

    public void setAssignedBuyer(String assignedBuyer)
    {
        this.assignedBuyer = assignedBuyer;
    }

    public String getAssignedBuyerName(String organizationId)
    {
        String name = this.getAssignedBuyer();
        if(!name.equals("PURCHASING"))
        {
            try
            {
                name = UserManager.getInstance().getUser(organizationId, this.getAssignedBuyer()).getDisplayName();
            }
            catch (Exception e)
            {
                Log.error(this, "Couldn't find name for: " + this.getAssignedBuyer());
            }
        }
        return name;
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
