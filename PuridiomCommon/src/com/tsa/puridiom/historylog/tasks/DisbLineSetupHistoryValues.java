/*
 * Created on Jul 22, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.DisbLineSetupHistoryValues.java
 * 
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.HistoryLog;

public class DisbLineSetupHistoryValues extends HistoryLogSetupValues
{
    private BigDecimal backOrder = null;
    
    public HistoryLog disbLineSetup(HistoryLog history, DisbLine line, DisbHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(header.getIcReqHeader());
        history.setIcLine(line.getIcReqLine());
 	    history.setDoctype("DSL");
 	    history.setStatus(line.getStatus());
 	    history.setUserid(header.getOwner());
 	    
 	    return history;
 	   
    }
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getLineHistoryLog(DisbLine newLine, DisbHeader header)
    {
        HistoryLog history = null;
        
        /*if(!oldLine.getStatus().equals(newLine.getStatus()) || (Utility.isEmpty(oldLine.getDisbNumber()) && newLine.getStatus().equals(DocumentStatus.REQ_INPROGRESS)))
        {*/
            history = this.getHistoryRecord(newLine, header);
        //}
        
        return history;
    }
    
    public HistoryLog setText(DisbLine line, HistoryLog history, DisbHeader header)
    {
        HistoryLogDisbText text = new HistoryLogDisbText(header, line);
        text.setBackOrder(this.getBackOrder());
        String status = line.getStatus();
        if(status.equals(DocumentStatus.INV_DISBURSED))
        {
            history.setDescription(text.approveLineText());
        }
        else if(status.equals(DocumentStatus.INV_INPROGRESS) || status.equals(DocumentStatus.INV_PARTIAL))
        {
            history.setDescription(text.createLineText());
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelLineText());
        }
        else if(status.equals(DocumentStatus.INV_BACKORDERED))
        {
            history.setDescription(text.backorderLineText());
        }
        else
        {
            history = null;
        }
        return history;
    }
    
    /**
     * @param status
     */
    private HistoryLog getHistoryRecord(DisbLine line, DisbHeader header)
    {
        HistoryLog history = this.disbLineSetup(null, line, header);
        history = this.setText(line, history, header);
        
        return history;
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
