/*
 * Created on Jul 19, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.HistoryLogDisbText.java
 * 
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;

public class HistoryLogDisbText extends HistoryBaseText
{
    private BigDecimal backOrder = null;
    /**
     * @param rHeader
     * @param rLine
     */
    public HistoryLogDisbText(Object rHeader, Object rLine)
    {
        super(rHeader, rLine);
        // TODO Auto-generated constructor stub
    }
    public HistoryLogDisbText(DisbHeader rHeader)
    {
        super(rHeader, null);
    }
    
    public HistoryLogDisbText(DisbLine rLine)
    {
        super(null, rLine);
    }
    
    public String headerText(String text)
    {
        return super.headerText(text, this.getDisbHeader().getDisbNumber());
    }

    public String createText()
    {
        return this.headerText("Created Disbursement #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardText()
    {
        return this.headerText("Forwarded Disbursement #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        return this.headerText("Cancelled Disbursement #");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#approveText()
     */
    public String approveText()
    {
        StringBuffer createText = new StringBuffer("Disbursed!");
        
		return createText.toString();
    }
    
    public String approveLineText()
    {
        StringBuffer text = this.baseLineText();
        text.append(" ");
        text.append(approveText());
        
		return text.toString();
    }
    public StringBuffer baseLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.getItemText());
        text.append(", Quantity: " + HiltonUtility.getFormattedQuantity(this.getDisbLine().getQuantity(), this.getOrganizationId()) + " (" + this.getDisbLine().getUmCode() + ")");
        if(this.getBackOrder().compareTo(new BigDecimal(0)) > 0)
        {
            text.append("\n" + HiltonUtility.getFormattedQuantity(this.getBackOrder(), this.getOrganizationId()) + " was placed on Backorder.");
        }
		
		return text;
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelLineText()
     */
    public String cancelLineText()
    {
        StringBuffer text = new StringBuffer("Cancelled Disbursement ");
        text.append(this.getItemText());
        
		return text.toString();
    }
    
    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#addLineText()
     */
    public String createLineText()
    {
        StringBuffer text = new StringBuffer("");
        text.append(this.baseLineText());
        text.append(", Placed on Disbursement #" + this.getDisbHeader().getDisbNumber());
        
		return text.toString();
    }
    
    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#forwardText()
     */
    public String forwardLineText()
    {
        
        StringBuffer createText = this.baseLineText();
        createText.append(", Disbursed on Disbursement #" + this.getDisbHeader().getDisbNumber());
		
		return createText.toString();
    }
    
    public String backorderText()
    {
        StringBuffer createText = new StringBuffer("Backordered!");
		
		return createText.toString();
    }
    
    public String backorderLineText()
    {
        StringBuffer text = this.baseLineText();
        text.append(", Placed on Backorder!");
		
		return text.toString();
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
    
    public String toString() 
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogDisbursementText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }
    public String getDescription()
    {
        return this.getDisbLine().getDescription();
    }
    public DisbHeader getDisbHeader()
    {
        return (DisbHeader)this.getHeader();
    }
    public String getItemNumber()
    {
        return this.getDisbLine().getItemNumber();
    }
    public String getLineNumber()
    {
        return this.getDisbLine().getLineNumber().toString();
    }
    public DisbLine getDisbLine()
    {
        return (DisbLine)this.getLine();
    }
    public BigDecimal getBackOrder()
    {
        if(this.backOrder == null)
        {
            this.backOrder = new BigDecimal(0);
        }
        return backOrder;
    }
    public void setBackOrder(BigDecimal backOrder)
    {
        this.backOrder = backOrder;
    }
}
