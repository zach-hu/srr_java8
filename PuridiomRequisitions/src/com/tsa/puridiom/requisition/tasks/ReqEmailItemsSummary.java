/*
 * Created on Apr 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class ReqEmailItemsSummary
{
    protected List itemsList = null;
    protected String Buyer;
    protected String requisitionNumber;
    protected BigDecimal icReqHeader;
    protected String requisitioner;

    public String getBuyer()
    {
        return Buyer;
    }
    public void setBuyer(String buyer)
    {
        Buyer = buyer;
    }
    public BigDecimal getIcReqHeader()
    {
        return icReqHeader;
    }
    public void setIcReqHeader(BigDecimal icReqHeader)
    {
        this.icReqHeader = icReqHeader;
    }
    public String getRequisitioner()
    {
        return requisitioner;
    }
    public void setRequisitioner(String requisitioner)
    {
        this.requisitioner = requisitioner;
    }
    public String getRequisitionNumber()
    {
        return requisitionNumber;
    }
    public void setRequisitionNumber(String requisitionNumber)
    {
        this.requisitionNumber = requisitionNumber;
    }

    public ReqEmailItemsSummary()
    {
    	super();
    }

    public ReqEmailItemsSummary(RequisitionHeader header, Object itemsListReason)
    {
        this(header);
        Log.debug(this, "ReqEmailItemsSummary starts");
        List itemsList = null;
        String _reason = "";
        if (itemsListReason instanceof List)
        {
			itemsList = (List) itemsListReason;
		}
        else if (itemsListReason instanceof Object[])
        {
        	Object temp[] = (Object[]) itemsListReason;
        	itemsList = (List) temp[0];
        	_reason = (String)temp[1];
        }
        if(itemsList != null)
        {
	        for(int i = 0; i < itemsList.size(); i++)
	        {
	            MiniItem item = new MiniItem();
	            RequisitionLine line = (RequisitionLine)itemsList.get(i);

	            item.setDescription(line.getDescription());
	            item.setItemNumber(line.getItemNumber());
	            item.setQuantity(line.getQuantity().intValue());
	            item.setReason(_reason);
	            item.setCatalog(line.getCatalogId());
	            Log.debug(this, "item added: " + item.toString());
	            this.itemsList.add(item);
	        }
        }
    }
    /**
     * @param header
     */
    public ReqEmailItemsSummary(RequisitionHeader header)
    {
        this.setBuyer(header.getAssignedBuyer());
        this.setIcReqHeader(header.getIcReqHeader());
        this.setRequisitioner(header.getRequisitionerCode());
        this.setRequisitionNumber(header.getRequisitionNumber());
        this.itemsList = new ArrayList();
    }
    public List getItemsList()
    {
        return itemsList;
    }
    public void setItemsList(List itemsList)
    {
        this.itemsList = itemsList;
    }
}
