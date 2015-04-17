/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.DisbSetupValues.java
 * 
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.HistoryLog;

public class DisbSetupValues extends HistoryLogSetupValues
{
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(DisbHeader newHeader)
    {
        
        HistoryLog history = null;
        history = this.getHistoryRecord(newHeader);
        
        return history;
    }
    
    public HistoryLog setText(DisbHeader header, HistoryLog history)
    {
        HistoryLogDisbText text = new HistoryLogDisbText(header);
        String status = header.getStatus();
        if(status.equals(DocumentStatus.INV_DISBURSED))
        {
            history.setDescription(text.approveText());
        }
        else if(status.equals(DocumentStatus.INV_INPROGRESS))
        {
            history.setDescription(text.createText());
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelText());
        }
        else if(status.equals(DocumentStatus.INV_BACKORDERED))
        {
            history.setDescription(text.backorderText());
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
    private HistoryLog getHistoryRecord(DisbHeader header)
    {
        HistoryLog history = this.disbHeaderSetup(null, header);
        history = this.setText(header, history);
        
        return history;
    }


    public HistoryLog disbHeaderSetup(HistoryLog history, DisbHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(header.getIcDsbHeader());
        history.setIcLine(new BigDecimal("0"));
 	    history.setDoctype("DSH");
 	    history.setStatus(header.getStatus());
 	    history.setUserid(header.getOwner());
 	    
 	    return history;
 	   
    }
}
