package com.tsa.puridiom.requisition.tasks;

import java.util.List;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;

public class AutoReleasedItemsEmail extends NonReleasedItemsSummary
{
	public AutoReleasedItemsEmail()
    {
		super();
    }
	public AutoReleasedItemsEmail(List autoLinesObject)
    {
		this.setItemsList(autoLinesObject);
    }

    public String getEmailText(String organizationId)
    {
        StringBuffer emailTxt = new StringBuffer("");
        Log.debug(this, "AutoReleasedItemsEmail.getText, " + organizationId);
        List itemsList = this.getItemsList();
        RequisitionHeader header = ((RequisitionLineAutoReleaseObject)itemsList.get(0)).getRequisitionHeader();
        emailTxt.append("The following item(s) have been AutoReleased:\r\n");

        for(int i = 0; i < itemsList.size(); i++)
        {
        	RequisitionLineAutoReleaseObject autoLine = (RequisitionLineAutoReleaseObject)itemsList.get(i);
        	RequisitionLine line = autoLine.getRequistionLine();
        	PoHeader poHeader = autoLine.getPoHeader();
        	PoLine poLine = autoLine.getPoLine();
            try
            {
            	emailTxt.append("Line " + line.getLineNumber() + ". " + line.getItemNumber() + "\t" + line.getDescription() + "\t Qty: " + poLine.getQuantity() + "\r\n");
				emailTxt.append("was placed on " + poHeader.getDisplayPoNumber() + ". Assigned to " + poHeader.getVendorName() + ".\r\n");
			}
            catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Requisition " + line.getRequisitionNumber() + " placed on order: " + poHeader.getDisplayPoNumber());
        }

        Log.debug(this, "NonReleasedItems.getText, returns: " + emailTxt.toString());
        return emailTxt.toString();
    }
}