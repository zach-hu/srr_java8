/*
 * Created on August 27, 2004
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogRfqText.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogRfqText extends HistoryBaseText
{

    public HistoryLogRfqText(RfqHeader rHeader, RfqLine rLine)
    {
        super(rHeader, rLine);
    }

    public HistoryLogRfqText(RfqHeader rHeader)
    {
        super(rHeader, null);
    }

    public HistoryLogRfqText(RfqLine rLine)
    {
        super(null, rLine);
    }

    public String headerText(String text)
    {
    	if (!HiltonUtility.isEmpty(this.getRfqHeader().getRequisitionNumber()))
    	{
    		return super.headerText(text, this.getRfqHeader().getRfqNumber(), " From Requisition # ", this.getRfqHeader().getRequisitionNumber());
    	}
    	else
    	{
    		return super.headerText(text, this.getRfqHeader().getRfqNumber());
    	}
    }

    public String createText()
    {
        return this.headerText("Create Solicitation #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String intentText()
    {
        return this.headerText("Approved Solicitation # ");
    }

    public String rejectText()
    {
        return this.headerText("Rejected Solicitation # ");
    }
    
    public String rejectText(String rejectedBY)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Rejected Solicitation #"));
        sb.append(" by " + rejectedBY);

        return sb.toString();
    }

    public String poInProgress()
    {
        return this.headerText("Assigned to Purchase Orders all Solicitation # ");
    }

    /*public String forwardText(boolean autoReleased)
    {
        if(!autoReleased)
        {
            return this.headerText("Forwarded ");
        }
        else
        {
            return this.autoReleasedText();
        }
    }*/

    public String approvingLineText(String user)
    {
    	StringBuffer text = this.baseLineText();
        text.append(", Solicitation # ");
        text.append(this.getRfqLine().getRfqNumber());
        text.append(", forwarded for approval.");

		return text.toString();

    	/*StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", " + this.getRfqHeader().getDisplayRfqNumber());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(". Forwarded to " + user);
            text.append(" for approval.");
        }

        return text.toString();*/
    }

    public String approvingText(String user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Solicitation #");
        sb.append(this.getRfqHeader().getRfqNumber());
        if(!Utility.isEmpty(user))
        {
            sb.append(" Forwarded to " + user);
            sb.append(" for Approval.");
        }
        return sb.toString();
    }

    /*public String autoReleasedLineText()
    {
    	RfqHeader rfqHeader = this.getRfqHeader();
    	RfqLine rfqLine = this.getRfqLine();

        String pattern = "Item {0} awarded on Order {1} {2}.";

        Object args[] = new Object[6];
        args[0] = rfqLine.getItemNumber();
        args[3] = rfqHeader.getRfqNumber();
        args[4] = rfqHeader.getReleaseNumber();
        args[5] = rfqHeader.getVendorName();

        //1 if release order is > 0
        //0 if release order number < 1
        double[] releaseLimits = {0,1};
        String [] fileStrings = {"{3}", "{3}-{4}" };
        ChoiceFormat poNumberOptions = new ChoiceFormat(releaseLimits, fileStrings);

        args[5] = poHeader.getVendorName();

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
    }*/

    /**
     * @return
     */
    /*public String autoReleasedText()
    {
    	RfqHeader rfqHeader = this.getRfqHeader();

        String pattern = "Order {0} was automatically awarded from Req {1} {2}.";
        Object args[] = new Object[6];

        args[1] = poHeader.getRequisitionNumber();
        //args[2] = "and awarded to supplier {5}";
        args[3] = poHeader.getPoNumber();
        args[4] = poHeader.getReleaseNumber();
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
    }*/


    public String openText()
    {
        StringBuffer createText = new StringBuffer("Solicitation  #");
		createText = createText.append(this.getRfqHeader().getRfqNumber());
		createText = createText.append(" open for Solicitation!");

		return createText.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        return this.headerText("Cancelled Solicitation #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#awardedText()
     */
    public String awardedText()
    {
    	return this.headerText("Awarded Solicitation #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String deleteText()
    {
        StringBuffer createText = new StringBuffer("Solicitation Deleted");

		return createText.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteLineText()
     */
    public String deleteLineText()
    {
        StringBuffer createText = new StringBuffer("Line Deleted");

		return createText.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#approveText()
     */
    public String approveText()
    {
        StringBuffer createText = new StringBuffer("Approved!");

		return createText.toString();
    }

    public String approveLineText()
    {
        StringBuffer text = this.baseLineText();
		text.append(", ");
		text.append(this.approveText());

		return text.toString();
    }

    public String poInProgressText()
    {
        StringBuffer createText = new StringBuffer("Assigned to an Order!");

		return createText.toString();
    }

    public String poInProgressLineText()
    {
        StringBuffer text = this.baseLineText();
		text.append(", ");
		text.append(this.poInProgressText());

		return text.toString();
    }

    public StringBuffer baseLineText()
    {
        StringBuffer text = new StringBuffer("");
        String item = this.getRfqLine().getItemNumber();
        if(!Utility.isEmpty(item))
        {
            text.append("Item ");
            text.append(this.getRfqLine().getItemNumber());
            text.append(", ");
        }
		text.append("Quantity ");
		text.append(this.getRfqLine().getQuantity());

		return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
    	StringBuffer text = new StringBuffer("Cancelled Solicitation ");
        text.append(this.getItemText());

        return text.toString();

    	/*StringBuffer createText = new StringBuffer("Line Cancelled");

		return createText.toString();*/
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String createLineText()
    {
        StringBuffer text = this.baseLineText();
        text.append(", Solicitation # ");
        text.append(this.getRfqLine().getRfqNumber());

		return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardLineText()
    {

        StringBuffer createText = this.baseLineText();
        createText.append(", Forward Solicitation #");
		createText.append(this.getRfqHeader().getRfqNumber());

		return createText.toString();
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogRfqText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }
    public String getDescription()
    {
        return this.getRfqLine().getDescription();
    }
    public RfqHeader getRfqHeader()
    {
        return (RfqHeader)this.getHeader();
    }
    public String getItemNumber()
    {
        return this.getRfqLine().getItemNumber();
    }
    public String getLineNumber()
    {
        return this.getRfqLine().getRfqLine().toString();
    }
    public RfqLine getRfqLine()
    {
        return (RfqLine)this.getLine();
    }
}
