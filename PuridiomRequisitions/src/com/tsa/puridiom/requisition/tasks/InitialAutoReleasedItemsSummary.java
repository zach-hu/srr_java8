package com.tsa.puridiom.requisition.tasks;

import java.util.List;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

public class InitialAutoReleasedItemsSummary extends NonReleasedItemsSummary
{
	public InitialAutoReleasedItemsSummary(RequisitionHeader header, Object itemsList)
    {
        super(header, itemsList);
    }

    public InitialAutoReleasedItemsSummary(RequisitionHeader header, Object itemsList, String assignedBuyer)
    {
    	super(header, itemsList, assignedBuyer);
    }

    public String getEmailText(String organizationId)
    {
        StringBuffer emailTxt = new StringBuffer("\r\n");
        Log.debug(this, "NonReleasedItems.getText, " + organizationId);


        List itemsList = this.getItemsList();
        for(int i = 0; i < itemsList.size(); i++)
        {
            RequisitionLine line = (RequisitionLine)itemsList.get(i);
            try
            {
            	String desc = line.getDescription();
            	if(desc.length() > 40)
            	{
            		desc = desc.substring(0, 41) + "...";
            	}
            	emailTxt.append("Line " + String.valueOf(i + 1) + ". " + line.getItemNumber() + "\t" + desc + "\t Qty: " + line.getQuantity() + ".\t");
				emailTxt.append(" Assigned to " + UserManager.getInstance().getUser(organizationId, line.getAssignedBuyer()).getDisplayName() + " \r\n");
			}
            catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

        Log.debug(this, "NonReleasedItems.getText, returns: " + emailTxt.toString());
        return emailTxt.toString();
    }

}
