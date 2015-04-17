/*
 * Created on August 27, 2004
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogRfqText.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;

import com.tsagate.foundation.utility.Utility;

public class HistoryLogInvoiceText extends HistoryBaseText
{

    public HistoryLogInvoiceText(InvoiceHeader rHeader, InvoiceLine rLine)
    {
        super(rHeader, rLine);
    }

    public HistoryLogInvoiceText(InvoiceHeader rHeader)
    {
        super(rHeader, null);
    }

    public HistoryLogInvoiceText(InvoiceLine rLine)
    {
        super(null, rLine);
    }

    public String headerText(String text)
    {
        return super.headerText(text, this.getInvoiceHeader().getInvoiceNumber());
    }

    public String ResendText(String user)
    {
    	StringBuffer sb = new StringBuffer();
        sb.append("Invoice #");
        sb.append(this.getInvoiceHeader().getInvoiceNumber());
        if(!Utility.isEmpty(user))
        {
            sb.append(" has been Resent to " + user);
            sb.append(" for Approval.");
        }
        return sb.toString();
    }
    
    public String createText()
    {
        return this.headerText("Created Invoice #");
    }

    public String recallText()
    {
        return this.headerText("Recalled Invoice #");
    }
    
    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardText()
    {
        return this.headerText("Forward Invoice #");
    }

    public String approvingLineText(String user)
    {
    	StringBuffer text = this.baseLineText();
        text.append(", Invoice # ");
        text.append(this.getInvoiceLine().getInvoiceNumber());
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
        sb.append("Invoice #");
        sb.append(this.getInvoiceHeader().getInvoiceNumber());
        if(!Utility.isEmpty(user))
        {
            sb.append(" Forwarded to " + user);
            sb.append(" for Approval.");
        }
        return sb.toString();
    }

    public String rejectText(String rejectedBY)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.headerText("Rejected Invoice #"));
        if(!Utility.isEmpty(rejectedBY))
        {
            sb.append(" by " + rejectedBY);
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

/*
    public String openText()
    {
        StringBuffer createText = new StringBuffer("Solicitation  #");
		createText = createText.append(this.getInvoiceHeader().getInvoiceNumber());
		createText = createText.append(" open for Solicitation!");

		return createText.toString();
    }
*/

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        return this.headerText("Cancelled Invoice #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String deleteText()
    {
        StringBuffer createText = new StringBuffer("Invoice Deleted");

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
        StringBuffer createText = new StringBuffer();
        createText.append(this.headerText("Approved Invoice #"));

		return createText.toString();
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
        String item = this.getInvoiceLine().getItemNumber();
        if(!Utility.isEmpty(item))
        {
            text.append("Item #");
            text.append(this.getInvoiceLine().getItemNumber());
            text.append(", ");
        }
		//text.append("Quantity ");
		//text.append(this.getInvoiceLine().getQuantity());

		return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
        StringBuffer createText = new StringBuffer("Line Cancelled");

		return createText.toString();
    }
    
    public String resendLineText(String user)
    {
    	StringBuffer text = new StringBuffer("Resent ");
        text.append(this.getItemText());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(" to " + user);
        }
        text.append(" for approval.");

        /*text.append(" Quantity: " + HiltonUtility.getFormattedQuantity(this.getInvoiceLine().getQuantity(), this.getOrganizationId()) + " (" + this.getInvoiceLine().getUmCode() + ")");
        text.append(", Price: " + HiltonUtility.getFormattedDollar(this.getInvoiceLine().getUnitPrice(), this.getOrganizationId()));
        text.append(", Invoice #" + this.getInvoiceLine().getInvoiceNumber());*/

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String createLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.baseLineText());
        text.append(" Placed on Invoice #" + this.getInvoiceLine().getInvoiceNumber());

		return text.toString();
    }
    
    public String recallLineText()
    {
        StringBuffer text = new StringBuffer("Recalled Invoice ");
        text.append(this.getItemText());

        return text.toString();
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardLineText(String user)
    {
    	StringBuffer text = new StringBuffer("Forwarded ");
        text.append(this.getItemText());

        if(!HiltonUtility.isEmpty(user))
        {
            text.append(" to " + user);
        }
        text.append(" for approval.");

        text.append(" Quantity: " + HiltonUtility.getFormattedQuantity(this.getInvoiceLine().getQuantity(), this.getOrganizationId()) + " (" + this.getInvoiceLine().getUmCode() + ")");
        text.append(", Price: " + HiltonUtility.getFormattedDollar(this.getInvoiceLine().getUnitPrice(), this.getOrganizationId()));
        text.append(", Invoice #" + this.getInvoiceLine().getInvoiceNumber());

        return text.toString();
    }

    public String rejectLineText()
    {
        StringBuffer text = new StringBuffer("Rejected Invoice Line ");
        text.append(this.getItemText());

        return text.toString();
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogInvoiceText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }
    public String getDescription()
    {
        return this.getInvoiceLine().getDescription();
    }
    public InvoiceHeader getInvoiceHeader()
    {
        return (InvoiceHeader)this.getHeader();
    }
    public String getItemNumber()
    {
        return this.getInvoiceLine().getItemNumber();
    }
    public String getLineNumber()
    {
        return this.getInvoiceLine().getLineNumber().toString();
    }
    public InvoiceLine getInvoiceLine()
    {
        return (InvoiceLine)this.getLine();
    }
}
