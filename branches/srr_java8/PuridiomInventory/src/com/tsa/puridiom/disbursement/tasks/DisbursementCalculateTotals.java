package com.tsa.puridiom.disbursement.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.common.documents.DocumentStatus;
import java.math.BigDecimal;
import java.util.*;

public class DisbursementCalculateTotals extends Task {


	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		try {
			List lineList = (List) incomingRequest.get("disbLineList");
			DisbHeader header = (DisbHeader) incomingRequest.get("disbHeader");

			this.defaultNullValues(header, lineList);
			this.calculateTotals(header, lineList);

			incomingRequest.put("allocateTotal", String.valueOf(header.getSubtotal()));
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED) ;
			Log.error(this,e.toString()) ;
			throw e;
		}

		return null;
	}

    public BigDecimal extCost(DisbLine disbLine) throws Exception
    {
	    BigDecimal bdTemp = new BigDecimal(0);

		try {
	        BigDecimal bdQty = disbLine.getQuantity();
	        BigDecimal bdUnitPrice = disbLine.getUnitPrice();
	        BigDecimal bdUmFactor = disbLine.getUmFactor();

			if (bdQty == null) {
				bdQty = new BigDecimal(0);
			}
			if (bdUnitPrice == null) {
				bdUnitPrice = new BigDecimal(0);
			}
			if (bdUmFactor == null) {
				bdUmFactor = new BigDecimal(1);
			}
	        bdTemp = bdQty.multiply(bdUnitPrice).multiply(bdUmFactor).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		catch (Exception e)
		{
			throw e;
		}
	        return bdTemp;
    }

    /**
    *	Method calculateTotals.
    *		Calculate line totals and assign to header
    *	@param header - DisbHeader entity
    *	@param lineList   - line items list
    */
    public void calculateTotals(DisbHeader header, List lineList) throws Exception
    {
        BigDecimal bdLineTotal = new BigDecimal(0.0), bdSubtotal = new BigDecimal(0.0);
        BigDecimal bdZero = new BigDecimal(0);
        BigDecimal bdTemp = new BigDecimal(0);

        int iLines = 0;

		try
		{
			if (lineList != null)
			{
				iLines = lineList.size();
			}

	        if (iLines > 0)
	        {
                for (int i = 0; i < iLines; i++) // get new subtotals
                {
                	DisbLine line = (DisbLine) lineList.get(i);
                    bdLineTotal = this.extCost(line);
                    line.setLineTotal(bdLineTotal);

                    if (! (line.getStatus().equals(DocumentStatus.CANCELLED))) {
                    		bdSubtotal = bdSubtotal.add(bdLineTotal);
                    }
                    Log.debug(this, "calculateTotals line total for " + String.valueOf(i) + ": " + bdLineTotal.toString());
                }
                Log.debug(this, "calculateTotals subtotal: " + bdSubtotal.toString() );

                header.setSubtotal(bdSubtotal);
	        }
	        else
	        {
	        	header.setSubtotal(bdSubtotal);
	        }
		}
		catch (Exception e)
		{
			throw e;
		}
    }

	/**
	 *	Method defaultNullValues
	 *		If values used in calculations are null, set to default value
	 *			-prevents null pointer exceptions
	 *	@param header - header entity
	 *	@param lineList   - line items list
	 *	@return int
	 *		 1 - Success
	 *		-1 - Failure
	 */
	 public void defaultNullValues(DisbHeader header, List lineList) throws Exception
	 {
		BigDecimal bdDefault = new BigDecimal("0.00");
		String	ovrDefault = "N";
		int iRows = 0;

		try
		{
			if (header != null)
			{
				if (header.getSubtotal() == null)	{	header.setSubtotal(bdDefault);	}
			}
			if (lineList != null)
			{
				iRows = lineList.size();
			}
			for (int i = 0; i < iRows; i++)
			{
				DisbLine line = (DisbLine) lineList.get(i);

				if (line.getLineTotal() == null)	{	line.setLineTotal(bdDefault);	}
				if (line.getQuantity() == null)	{	line.setQuantity(bdDefault);	}
				if (line.getUmCode() == null)		{	line.setUmCode("");	}
				if (line.getUmFactor() == null)	{	line.setUmFactor(bdDefault);	}
				if (line.getUnitPrice() == null)	{	line.setUnitPrice(bdDefault);	}
			}

 			Log.debug(this, "defaultNullValues complete... ");
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
