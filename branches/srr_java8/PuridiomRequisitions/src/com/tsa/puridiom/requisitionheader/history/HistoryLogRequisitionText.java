/*
 * Created on Jul 19, 2004
 *
 * @author  * renzo
 * project: HiltonCommon
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogRequisitionText.java
 *
 */
package com.tsa.puridiom.requisitionheader.history;

import java.math.BigDecimal;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.historylog.tasks.HistoryBaseText;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogRequisitionText extends HistoryBaseText
{
    /**
     * @param rHeader
     * @param rLine
     */
    public HistoryLogRequisitionText(Object rHeader, Object rLine)
    {
        super(rHeader, rLine);
        // TODO Auto-generated constructor stub
    }
    public HistoryLogRequisitionText(RequisitionHeader rHeader)
    {
        super(rHeader, null);
    }

    public HistoryLogRequisitionText(RequisitionLine rLine)
    {
        super(null, rLine);
    }

    public String headerText(String text)
    {
    	if (this.getReqHeader().getRequisitionType().equalsIgnoreCase("C"))
    		text = text.replaceAll("Requisition", "Change Request");
    	return super.headerText(text, this.getReqHeader().getRequisitionNumber());
    }

    public String createText()
    {
        return this.headerText("Created Requisition #");
    }
    public String createTextReqFromTemplate()
    {
        return this.headerText("Created Requisition # from Template");
    }


    public String forwardText(String user)
    {
        StringBuffer sb = new StringBuffer(this.headerText("Forwarded Requisition #"));
        if(!Utility.isEmpty(user))
        {
            sb.append(" to " + user + ".");
        }

        return sb.toString();
    }
    public String forwardedText(String user)
    {
        StringBuffer sb = new StringBuffer(this.headerText("Forwarded Requisition #"));
        if(!Utility.isEmpty(user))
        {
            sb.append(" to " + user + ".");
        }

        return sb.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardText()
    {
        return this.forwardText("");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        return this.headerText("Cancelled Requisition #");
    }
    public String closeText()
    {
        return this.headerText("Closed Requisition #");
    }
    public String recallText()
    {
        return this.headerText("Recalled Requisition #");
    }

    public String onOrderText()
    {
        return "Placed on Order";
    }

    public String sourcedText()
    {
        return "Sourced to Request";
    }

    public String partiallyRcvdText()
    {
        return this.headerText("Partially Received Requisition #");
    }

    public String fullyRcvdText()
    {
        return this.headerText("Received Requisition #");
    }

    public String awardedText()
    {
    	return this.headerText("Awarded Requisition #");
    }

    public String orderedText()
    {
    	return this.headerText("Ordered Requisition #");
    }

    public String partiallyKittedText()
    {
    	return this.headerText("Partially Kitted Requisition #");
    }

    public String kittedText()
    {
    	return this.headerText("Kitted Requisition #");
    }
    
    public String partiallyIssuedText()
    {
    	return this.headerText("Partially Issued Requisition #");
    }

    public String issuedText()
    {
    	return this.headerText("Issued Requisition #");
    }

    public String reservedText()
    {
    	return this.headerText("Reserved Requisition #");
    }

    public String rejectText(String rejectedBY)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Rejected Requisition #"));
        if(!Utility.isEmpty(rejectedBY))
        {
            sb.append(" by " + rejectedBY);
        }

        return sb.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String deleteText()
    {
        return this.headerText("Deleted Requisition #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteLineText()
     */
    public String deleteLineText()
    {
        String text = "Deleted Line " + this.getItemText();

        return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#approveText()
     */
    public String approveText()
    {
        StringBuffer createText = new StringBuffer();
        createText.append(this.headerText("Approved Requisition #"));

        String temp = "";

        if(this.getReqHeader().getAssignedBuyer().equalsIgnoreCase("PURCHASING"))
        {
            temp = ". Assigned to PURCHASING.";
        }
        else
        {
            String requisitionerName = "";
            try
            {
                requisitionerName = UserManager.getInstance().getUser(this.getOrganizationId(),
                        this.getReqHeader().getAssignedBuyer()).getDisplayName(this.getReqHeader().getRequisitionType());
                temp = ". Assigned to " + requisitionerName + ".";
            }
            catch (Exception e)
            {
                temp = "";
            }

        }
        createText.append(temp);

        return createText.toString();
    }

    public String buyerAssignment()
    {
    	StringBuffer createText = new StringBuffer();
    	createText.append(this.headerText("Requisition #"));
    	
    	String temp = "";
    	
    	if(this.getReqHeader().getAssignedBuyer().equalsIgnoreCase("PURCHASING"))
    	{
    		temp = ". Assigned to PURCHASING.";
    	}
    	else
    	{
    		String assignedBuyer = "";
    		try
    		{
    			assignedBuyer = UserManager.getInstance().getUser(this.getOrganizationId(),
    					this.getReqHeader().getAssignedBuyer()).getDisplayName(this.getReqHeader().getRequisitionType());
    			temp = ". Assigned to Buyer " + assignedBuyer + ".";
    		}
    		catch (Exception e)
    		{
    			temp = "";
    		}
    		
    	}
    	createText.append(temp);
    	
    	return createText.toString();
    }

    public String approveLineText(String user)
    {
        StringBuffer text = new StringBuffer("Approved");
        text.append(this.getItemText());
        text.append(",  Requisition #" + this.getReqHeader().getRequisitionNumber());

        String temp = "";
        if(this.getReqLine().getAssignedBuyer().equalsIgnoreCase("PURCHASING"))
        {
            temp = ". Assigned to PURCHASING.";
        }
        else
        {
            String requisitionerName = "";
            try
            {
                requisitionerName = UserManager.getInstance().getUser(this.getOrganizationId(),
                        this.getReqLine().getAssignedBuyer()).getDisplayName();
                temp = ". Assigned to " + requisitionerName + ".";
            }
            catch (Exception e)
            {
                temp = "";
            }
        }
        text.append(temp);

        return text.toString();
    }


    public String approveLineText()
    {
        StringBuffer text = new StringBuffer("Approved ");
        text.append(this.getItemText());
        text.append(", ");
        String temp = "";

        if(this.getReqLine().getAssignedBuyer().equalsIgnoreCase("PURCHASING"))
        {
            temp = ". Assigned to PURCHASING.";
        }
        if(this.getReqLine().getAssignedBuyer().equalsIgnoreCase("AUTORELEASE"))
        {
            temp = ". AutoReleased .";
        }
        else
        {
            String buyerName = "";
            try
            {
                buyerName = UserManager.getInstance().getUser(this.getOrganizationId(),
                        this.getReqLine().getAssignedBuyer()).getDisplayName();
                temp = ". Assigned to " + buyerName + ".";
            }
            catch (Exception e)
            {
                temp = "";
            }

        }
        text.append(temp);

        return text.toString();
    }

    private StringBuffer baseLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(this.getReqLine().getQuantity(), this.getOrganizationId()) + " (" + this.getReqLine().getUmCode() + ")");
        text.append(", Price: " + HiltonUtility.getFormattedDollar(this.getReqLine().getUnitPrice(), this.getOrganizationId()));

        return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
        StringBuffer text = new StringBuffer("Cancelled Requisition ");
        text.append(this.getItemText());

        return text.toString();
    }

    public String closeLineText()
    {
        StringBuffer text = new StringBuffer("Closed Requisition ");
        text.append(this.getItemText());

        return text.toString();
    }

    public String recallLineText()
    {
        StringBuffer text = new StringBuffer("Recalled Requisition ");
        text.append(this.getItemText());

        return text.toString();
    }

    public String rejectLineText()
    {
        StringBuffer text = new StringBuffer("Rejected RequisitionLine ");
        text.append(this.getItemText());

        return text.toString();
    }
    
    public String orderLineText()
    {
    	StringBuffer text = new StringBuffer("Ordered RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String partiallyReceiveLineText()
    {
    	StringBuffer text = new StringBuffer("Partially Received RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String receiveLineText()
    {
    	StringBuffer text = new StringBuffer("Received RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String partiallyKitLineText()
    {
    	StringBuffer text = new StringBuffer("Partially Kitted RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String kitLineText()
    {
    	StringBuffer text = new StringBuffer("Kitted RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }
    
    public String partiallyIssueLineText()
    {
    	StringBuffer text = new StringBuffer("Partially Issued RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String issueLineText()
    {
    	StringBuffer text = new StringBuffer("Issued RequisitionLine ");
    	text.append(this.getItemText());
    	
    	return text.toString();
    }

    public String onOrderText(PoLine poLine)
    {
    	StringBuffer text = new StringBuffer("");

    	if(poLine != null)
    	{
	        text.append(this.getItemText());
	        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(poLine.getQuantity(), this.getOrganizationId()) + " (" + this.getReqLine().getUmCode() + ")");
	        text.append(". Placed on Order [" + poLine.getPoNumber() + "-" + poLine.getReleaseNumber() + "].");
    	}
        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String createLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.baseLineText());
        text.append(", Placed on Requisition #" + this.getReqHeader().getRequisitionNumber());

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardLineText()
    {
        return this.forwardLineText("");
    }

    public String forwardLineText(String user)
    {
        StringBuffer text = new StringBuffer("Forwarded ");
        text.append(this.getItemText());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(" to " + user);
        }
        text.append(" for approval.");

        text.append(" Quantity: " + HiltonUtility.getFormattedQuantity(this.getReqLine().getQuantity(), this.getOrganizationId()) + " (" + this.getReqLine().getUmCode() + ")");
        text.append(", Price: " + HiltonUtility.getFormattedDollar(this.getReqLine().getUnitPrice(), this.getOrganizationId()));
        text.append(", Requisition #" + this.getReqHeader().getRequisitionNumber());

        return text.toString();
    }

    public String disbursedText()
    {
        StringBuffer createText = new StringBuffer("Disbursed!");

        return createText.toString();
    }

    public String disbursedLineText()
    {
        StringBuffer createText = new StringBuffer("Line Disbursed!");

        return createText.toString();
    }

    public String backorderLineText()
    {
        StringBuffer createText = new StringBuffer(this.baseLineText().toString());
        createText.append(".\n Quantity: (");
        createText.append(HiltonUtility.getFormattedQuantity(this.getReqLine().getBackordered(), this.getOrganizationId()));
        createText.append(") was placed on Backorder.");

        return createText.toString();
    }

    public String pendingDisbursementText()
    {
        StringBuffer createText = new StringBuffer("Pending Disbursement.");

        return createText.toString();
    }

    public String pendingDisbursementLineText()
    {
        StringBuffer createText = new StringBuffer("Line Pending Disbursement.");

        return createText.toString();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogRequisitionText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }
    public String getDescription()
    {
        return this.getReqLine().getDescription();
    }
    public RequisitionHeader getReqHeader()
    {
        return (RequisitionHeader)this.getHeader();
    }
    public String getItemNumber()
    {
        return this.getReqLine().getItemNumber();
    }

    public String getLineNumber()
    {
        return this.getReqLine().getLineNumber().toString();
    }
    public RequisitionLine getReqLine()
    {
        return (RequisitionLine)this.getLine();
    }
    /**
     * @return
     */
    public String approvingText(String user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Approved Requisition #"));
        sb.append(".");
        if(!Utility.isEmpty(user))
        {
            sb.append(" Forwarded to " + user);
        }
        sb.append(" for Approval.");
        return sb.toString();
    }

    public String manualAssignment()
    {
    	StringBuffer text = new StringBuffer("Assigned ");

    	try
    	{
    		text.append(this.getItemText());
        	text.append(" to: ");
			text.append(UserManager.getInstance().getUser(this.getOrganizationId(), this.getReqHeader().getAssignedBuyer()).getDisplayName());
		}
    	catch (Exception e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return text.toString();
    }

    public String approvingLineText(String user)
    {
        StringBuffer text = new StringBuffer("Approved");
        text.append(this.getItemText());
        text.append(",  Requisition #" + this.getReqHeader().getRequisitionNumber());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(". Forwarded to " + user);
            text.append(" for approval.");
        }

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changeLineInfoText(BigDecimal originalQty, BigDecimal originalPrice)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Quantity changed from " +HiltonUtility.getFormattedQuantity(originalQty, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedQuantity(this.getReqLine().getQuantity(), this.getOrganizationId()));
        text.append(", Price changed from " +HiltonUtility.getFormattedPrice(originalPrice, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedPrice(this.getReqLine().getUnitPrice(), this.getOrganizationId()));

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changeQuantityLineText(BigDecimal originalQty)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Quantity changed from " +HiltonUtility.getFormattedQuantity(originalQty, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedQuantity(this.getReqLine().getQuantity(), this.getOrganizationId()));

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changePriceLineText(BigDecimal originalPrice)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Price changed from " +HiltonUtility.getFormattedPrice(originalPrice, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedPrice(this.getReqLine().getUnitPrice(), this.getOrganizationId()));

        return text.toString();
    }

    public String buyerRemarksText()
    {
        StringBuffer text = new StringBuffer("");
        text.append("Procurement Remarks: " + this.getReqHeader().getBuyerRemarks());

        return text.toString();
    }
}
