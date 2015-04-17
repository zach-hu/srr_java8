/*
 * Created on Jul 19, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogPoText.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogPoText extends HistoryBaseText
{
	private String reason = "";
    /**
     * @param rHeader
     * @param rLine
     */
    public HistoryLogPoText(Object rHeader, Object rLine)
    {
        super(rHeader, rLine);
        // TODO Auto-generated constructor stub
    }
    public HistoryLogPoText(PoHeader rHeader)
    {
        super(rHeader, null);
    }

    public HistoryLogPoText(PoLine rLine)
    {
        super(null, rLine);
    }

    public String headerText(String text)
    {
        return super.headerText(text, this.getPoHeader().getDisplayPoNumber().toString());
    }

    public String createText()
    {
    	String text = "Created ";
    	if(Utility.isEmpty(this.getPoHeader().getRequisitionNumber()))
    	{
    		text = text + this.getPoHeader().getRequisitionNumber() + " ";
    	}
    	else
    	{
    		if(this.getPoHeader().getRevisionNumber().intValue() > 0)
    			text = text + this.getPoHeader().getRequisitionNumber() + " ";
    		else
    			text = text + " from Requisition " + this.getPoHeader().getRequisitionNumber() + " ";
    	}
    	return this.headerText(text);
    }
    public String forwardText(boolean autoReleased)
    {
        if(!autoReleased)
        {
            return this.headerText("Forwarded ");
        }
        else
        {
            return this.autoReleasedText();
        }
    }

    public String approvingLineText(String user)
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", " + this.getPoHeader().getDisplayPoNumber());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(". Forwarded to " + user);
            text.append(" for approval.");
        }

        return text.toString();
    }

    public String approvingText(String user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getPoHeader().getDisplayPoNumber());
        if(!Utility.isEmpty(user))
        {
            sb.append(" Forwarded to " + user);
            sb.append(" for Approval.");
        }
        return sb.toString();
    }

    public String autoReleasedLineText()
    {
        PoHeader poHeader = this.getPoHeader();
        PoLine poLine = this.getPoLine();

        String pattern = "Item {0} awarded on Order {1} {2}. ";
        if(!Utility.isEmpty(this.getPoLine().getRequisitionNumber()))
        {
        	pattern = pattern + "From Requisition " + this.getPoLine().getRequisitionNumber() + " ";
        }

        Object args[] = new Object[6];
        args[0] = poLine.getItemNumber();
        args[3] = poHeader.getPoNumber();
        args[4] = poHeader.getReleaseNumber();
        args[5] = poHeader.getVendorName();

        //1 if release order is > 0
        //0 if release order number < 1
        double[] releaseLimits = {0,1};
        String [] fileStrings = {"{3}", "{3}-{4}" };
        ChoiceFormat poNumberOptions = new ChoiceFormat(releaseLimits, fileStrings);

        double[] supplierLimits = {0,1};
        String [] supplierStrings = {"", "and awarded to supplier {5}"};
        ChoiceFormat supplierOptions = new ChoiceFormat(supplierLimits, supplierStrings);

        Format formats[] = {null, poNumberOptions, supplierOptions, null, null, null};
        MessageFormat msg = new MessageFormat("");
        msg.applyPattern(pattern);
        msg.setFormats(formats);

        if(poHeader.getReleaseNumber().compareTo(new BigDecimal(0)) > 0)
        {
            args[1] = new Integer(1);
        }
        else
        {
            args[1] = new Integer(0);
        }

        if(!Utility.isEmpty(poHeader.getVendorName()))
        {
            args[2] = new Integer(1);
        }
        else
        {
            args[2] = new Integer(0);
        }

        String result = msg.format(args);
        return result;
    }

    /**
     * @return
     */
    public String autoReleasedText()
    {
        PoHeader poHeader = this.getPoHeader();

        String pattern = "Order {0} was automatically created {1} {2}.";
        Object args[] = new Object[6];

        if(!Utility.isEmpty(poHeader.getRequisitionNumber()))
        {
        	args[1] = "from Req" + poHeader.getRequisitionNumber();
        }
        else
        {
        	args[1] = "";
        }
        //args[2] = "and awarded to supplier {5}";
        args[3] = poHeader.getPoNumber();
        args[4] = poHeader.getReleaseNumber().toString();
        args[5] = poHeader.getVendorName();
        //1 if release order is > 0
        //0 if release order number < 1
        double[] releaseLimits = {0,1};
        String [] fileStrings = {"{3}",
                "{3}-{4}"
                };
        ChoiceFormat numberOptions = new ChoiceFormat(releaseLimits, fileStrings);


        double[] supplierLimits = {0,1};
        String [] supplierStrings = {"",
                "and awarded to supplier {5}"};

        ChoiceFormat supplierOptions = new ChoiceFormat(supplierLimits, supplierStrings);

        Format formats[] = {numberOptions, null, supplierOptions, null, null, null};
        MessageFormat msg = new MessageFormat("");
        msg.applyPattern(pattern);
        msg.setFormats(formats);
        if(poHeader.getReleaseNumber().compareTo(new BigDecimal(0)) > 0)
        {
            args[0] = new Integer(1);
        }
        else
        {
            args[0] = new Integer(0);
        }

        if(!Utility.isEmpty(poHeader.getVendorName()))
        {
            args[2] = new Integer(1);
        }
        else
        {
            args[2] = new Integer(0);
        }

        String result = msg.format(args);
        return result;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardText()
    {
        return this.forwardText(false);
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        StringBuffer text = new StringBuffer(this.headerText("Cancelled "));
        text = this.addReason(text);
        return text.toString();
    }

    public String closeText()
    {
        StringBuffer text = new StringBuffer(this.headerText("Closed "));
        text = this.addReason(text);
        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String rejectText(String rejectedBY)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Rejected Order #"));
        sb.append(" by " + rejectedBY);

        return sb.toString();
    }
    public String deleteText()
    {
        return this.headerText("Deleted ");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteLineText()
     */
    public String deleteLineText()
    {
        //StringBuffer createText = new StringBuffer("Line Deleted");

        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(this.getPoLine().getQuantity(), this.getOrganizationId()) + " (" + this.getPoLine().getUmCode() + ")");
        text.append(", Deleted from " + this.getPoHeader().getDisplayPoNumber());

        if (!HiltonUtility.isEmpty(this.getReason()))
        {
        	text.append(", Reason: " + this.getReason());
        }
        return text.toString();

    }


    public String linkedLineToReq(String requisitionNumber)
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(this.getPoLine().getQuantity(), this.getOrganizationId()) + " (" + this.getPoLine().getUmCode() + ")");

        text.append(", Linked to Requisition #" + requisitionNumber);
        return text.toString();

    }


    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#approveText()
     */
    public String approveText(boolean autoReleased)
    {
    	//return this.headerText("Approved ");
        if(!autoReleased)
        {
            return this.headerText("Approved ");
        }
        else
        {
            return this.autoReleasedText();
        }
    }

    public String awardedText()
    {
    	return this.headerText("Awarded ");
        /*if(!autoReleased)
        {
            return this.headerText("Approved ");
        }
        else
        {
            return this.autoReleasedText();
        }*/
    }
    
    public String partiallyReceivedText()
    {
    	return this.headerText("Partially Received ");     
    }
    
    public String receivedText()
    {
    	return this.headerText("Received ");     
    }

    public String quickReceivedText(String user)
    {
    	StringBuffer sb = new StringBuffer();
        sb.append("");
        
        if(!Utility.isEmpty(user))
        {
            sb.append("Quick-Receipt logged " + user);
        }
        
        return sb.toString();
    }
    
    public String Text()
    {
    	return this.headerText("Awarded ");     
    }

    public String approveText()
    {
        return this.approveText(false);
    }
    public String approveLineText()
    {
        StringBuffer text = this.baseLineText();
        text.append(", ");
        text.append(this.approveText());

        return text.toString();
    }
    public StringBuffer baseLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(this.getPoLine().getQuantity(), this.getOrganizationId()) + " (" + this.getPoLine().getUmCode() + ")");
        text.append(", Placed on " + this.getPoHeader().getDisplayPoNumber());

        return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
    	StringBuffer createText = new StringBuffer("Cancelled ");
        createText.append(this.getItemText() + ", ");
        if (!HiltonUtility.isEmpty(this.getReason()))
        {
        	createText.append("Reason: " + this.getReason());
        }

        return createText.toString();
    }

    public StringBuffer addReason(StringBuffer text)
    {
    	if (!HiltonUtility.isEmpty(this.getReason()))
        {
    		text.append("Reason: " + this.getReason());
        }
    	return text;
    }

    public String closeLineText()
    {
        StringBuffer createText = new StringBuffer("Line Closed ");
        if (!HiltonUtility.isEmpty(this.getReason()))
        {
        	createText.append("Reason: " + this.getReason());
        }

        return createText.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String createLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.baseLineText());

        return text.toString();
    }

    public String forwardLineText()
    {
        return forwardLineText(false);
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardLineText(boolean autoReleased)
    {
        if(!autoReleased)
        {
            StringBuffer createText = this.baseLineText();
            createText.append(", Forward ");
            createText.append(this.getPoHeader().getDisplayPoNumber());
            return createText.toString();
        }
        else
        {
            return this.autoReleasedLineText();
        }
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changeLineInfoText(BigDecimal originalQty, BigDecimal originalPrice)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Quantity changed from " +HiltonUtility.getFormattedQuantity(originalQty, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedQuantity(this.getPoLine().getQuantity(), this.getOrganizationId()));
        text.append(", Price changed from " +HiltonUtility.getFormattedPrice(originalPrice, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedPrice(this.getPoLine().getUnitPrice(), this.getOrganizationId()));

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changeQuantityLineText(BigDecimal originalQty)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Quantity changed from " +HiltonUtility.getFormattedQuantity(originalQty, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedQuantity(this.getPoLine().getQuantity(), this.getOrganizationId()));

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String changePriceLineText(BigDecimal originalPrice)
    {
        StringBuffer text = new StringBuffer("");

        text.append(this.getItemText());
        text.append(", Price changed from " +HiltonUtility.getFormattedPrice(originalPrice, this.getOrganizationId()) + " to " + HiltonUtility.getFormattedPrice(this.getPoLine().getUnitPrice(), this.getOrganizationId()));

        return text.toString();
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
    public PoHeader getPoHeader()
    {
        return (PoHeader)this.getHeader();
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
