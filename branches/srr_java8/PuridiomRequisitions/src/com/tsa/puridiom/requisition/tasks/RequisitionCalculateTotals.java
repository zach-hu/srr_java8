package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.tax.TaxManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionCalculateTotals extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		try
		{
			List lineList = (List) incomingRequest.get("requisitionLineList");
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			this.defaultNullValues(header, lineList);
			this.calculateTotals(header, lineList, (String)incomingRequest.get("organizationId"));
			BigDecimal total = header.getTotal();

			incomingRequest.put("allocateTotal", total.toString());
			incomingRequest.put("allocateHeaderTotal", total.toString());
			//this.accountCalc();
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED) ;
			Log.error(this,e.toString()) ;
			throw e;
		}

		return null;
	}

    public BigDecimal extCost(RequisitionLine requisitionLine) throws Exception
    {
	    BigDecimal bdTemp = new BigDecimal(0);

		try
		{
		    if(!requisitionLine.getStatus().equals(DocumentStatus.CANCELLED))
		    {
		        BigDecimal bdQty = requisitionLine.getQuantity();
		        BigDecimal bdUnitPrice = requisitionLine.getUnitPrice();
		        BigDecimal bdUmFactor = requisitionLine.getUmFactor();

				if (bdQty == null) { bdQty = new BigDecimal(0);					}
				if (bdUnitPrice == null) { bdUnitPrice = new BigDecimal(0); 	}
				if (bdUmFactor == null)
				{
				    bdUmFactor = new BigDecimal(1);
				}
				else if(bdUmFactor.compareTo(new BigDecimal(0)) == 0)
				{
					bdUmFactor = new BigDecimal(1);
				}

		        bdTemp = bdQty.multiply(bdUnitPrice).multiply(bdUmFactor).setScale(2, BigDecimal.ROUND_HALF_UP);
		    }
		}
		catch (Exception e)
		{
			throw new TsaException(this.getName(), e);
		}
	    return bdTemp;
    }

    /**
    *	Method calculateTotals.
    *		Calculate line totals and assign to header
    *	@param header - RequisitionHeader entity
    *	@param lineList   - line items list
    */
    public void calculateTotals(RequisitionHeader header, List lineList, String organizationId) throws Exception
    {
        int iTaxLine = 0;
        boolean bDiscOvr = false, bTaxOvr = false, bShipOvr = false, bOtherOvr = false, bAllZeroLines = true;
        BigDecimal bdExtCost = new BigDecimal(0.0), bdSubtotal = new BigDecimal(0.0);
        BigDecimal bdLineTotal = new BigDecimal(0.0), bdLineTaxable = new BigDecimal(0.0);
		BigDecimal bdHdrTax = new BigDecimal(0.0), bdUseHdrTax = new BigDecimal(0.0),  bdTaxTotal = new BigDecimal(0.0), bdUseTaxTotal = new BigDecimal(0.0), bdTaxableSubtotal = new BigDecimal(0.0), bdDiff = new BigDecimal(0.0);
        BigDecimal bdHdrShipping = new BigDecimal(0.0), bdShipTotal = new BigDecimal(0.0), bdShipTaxTotal = new BigDecimal(0.0), bdHdrShipTax = new BigDecimal(0.0);
        BigDecimal bdHdrOther = new BigDecimal(0.0), bdOtherTotal = new BigDecimal(0.0), bdOtherTaxTotal = new BigDecimal(0.0), bdHdrOtherTax = new BigDecimal(0.0);
        BigDecimal bdHdrDisct = new BigDecimal(0.0), bdDiscountTotal = new BigDecimal(0.0);
        BigDecimal bdZero = new BigDecimal(0);
        BigDecimal bdTemp = new BigDecimal(0);
        BigDecimal bdPercent = new BigDecimal("0.01");

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
	                	RequisitionLine line = (RequisitionLine) lineList.get(i);
	                	if(line.getStatus().equals(DocumentStatus.CANCELLED))
						{
						    continue;
						}
	                    bdExtCost = this.extCost(line);

	                    bdSubtotal = bdSubtotal.add(bdExtCost);
	                    Log.debug(this, "subtotal: " + bdSubtotal.toString());

	                    if(bdExtCost.compareTo(bdZero) == 1)
	                    {
	                        bAllZeroLines = false;
	                    }
	                    if (line.getDiscOvr().equals("Y")) { bDiscOvr = true; }
	                    if (line.getTaxOvr().equals("Y")) { bTaxOvr = true;  }
	                    if (line.getShipOvr().equals("Y")) { bShipOvr = true; }
	                    if (line.getOtherOvr().equals("Y")) { bOtherOvr = true; }
	                }
	                Log.debug(this, "calculateTotals subtotal: " + bdSubtotal.toString() );

	                header.setSubtotal(bdSubtotal);

	                if (bdSubtotal.compareTo(bdZero) == 1 || bAllZeroLines)
	                {
	                    //line items should be sorted by line number so the last item is the one that is adjusted if necessary
	                    Log.debug(this, "calculateTotals header subtotal: " + String.valueOf(header.getSubtotal()));

	                    //get header discount amount in case you need it for distributing
	                    bdHdrDisct = header.getDiscountAmount().setScale(2, BigDecimal.ROUND_HALF_UP);

	                    //calculate new discount amounts; must be done first so you know header discount before you try to calculate tax
	                    for(int i = 0; i < iLines; i++)
	                    {
							RequisitionLine line = (RequisitionLine) lineList.get(i);
							if(line.getStatus().equals(DocumentStatus.CANCELLED))
							{
							    continue;
							}

	                        //make sure discount override flag is set properly (especially necessary for items added after the user's last visit to the totals page)
	                        if (bDiscOvr)
	                        {
	                            line.setDiscOvr("Y");
	                        }
	                        bdExtCost = this.extCost(line);

	                        // if discount amount entered at header
	                        if(line.getDiscountPercent().compareTo(bdZero) == 0 && Utility.ckNull(line.getDiscOvr()).equals("N"))
	                        {
	                            if(bAllZeroLines)
	                            {
	                                line.setDiscountAmount(bdHdrDisct.divide(new BigDecimal(iLines), 2, BigDecimal.ROUND_HALF_UP));
	                            }
	                            else
	                            {
	                                bdTemp = bdExtCost.divide(bdSubtotal, 5, BigDecimal.ROUND_UP).multiply(bdHdrDisct).setScale(2, BigDecimal.ROUND_HALF_UP);
	                                line.setDiscountAmount(bdTemp);
	                            }
	                        }
	                        else if(line.getDiscountPercent().compareTo(bdZero) == 1)//if discount calculated by percent
	                        {
	                            bdTemp = bdExtCost.multiply(line.getDiscountPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP);
	                            line.setDiscountAmount(bdTemp);
	                        }
	                        bdDiscountTotal = bdDiscountTotal.add(line.getDiscountAmount());  //keep running total for comparison to header amount or for new header amount
	                        //get subtotal of lines that are taxable
							if( line.getTaxable().equals("Y"))
	                        {
	                            bdTaxableSubtotal = bdTaxableSubtotal.add(bdExtCost).subtract(line.getDiscountAmount());
	                        }
	                    }
	                    Log.debug(this, "calculateTotals discount total: " + bdDiscountTotal.toString());

	                    if(!bDiscOvr && header.getDiscountPercent().compareTo(bdZero) == 0)//if discount was distributed then make sure total of lines matches header
	                    {
	                        bdDiff = bdHdrDisct.subtract(bdDiscountTotal);
	                        if(bdDiff.compareTo(bdZero) != 0)
	                        {
								RequisitionLine line = (RequisitionLine) lineList.get(iLines - 1);
								line.setDiscountAmount(line.getDiscountAmount().add(bdDiff));
	                        }
	                    }
	                    else
	                    {
	                        header.setDiscountAmount(bdDiscountTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                    }

	                    //get header amounts in case you need them for distributing
	                    bdHdrTax = header.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	                    bdUseHdrTax = header.getUseTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	                    bdHdrShipping = header.getShippingCharges().setScale(2, BigDecimal.ROUND_HALF_UP);
	                    bdHdrOther = header.getOtherCharges().setScale(2, BigDecimal.ROUND_HALF_UP);
						bdHdrShipTax	= header.getShippingTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP);
						bdHdrOtherTax	= header.getOtherTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP);

						Log.debug(this, "calculateTotals usetaxable subtotal: " + bdTaxableSubtotal.toString());

						BigDecimal bdCanadianPercent = new BigDecimal(0);

						if(HiltonUtility.isQriCanadian(organizationId, header.getUdf1Code()))
						{
							bdCanadianPercent = new BigDecimal(PropertiesManager.getInstance(organizationId).getProperty("REQ DEFAULTS","USETAXPERCENTCANADIAN","0"));
						}
						bdUseHdrTax = bdTaxableSubtotal.multiply(bdCanadianPercent.multiply(bdPercent)).setScale(2, BigDecimal.ROUND_HALF_UP);
						header.setUseTaxAmount(bdUseHdrTax);
						header.setUseTaxPercent(bdCanadianPercent);

	                    Log.debug(this, "calculateTotals taxable subtotal: " + bdTaxableSubtotal.toString());
	                    for (int li = 0; li < iLines; li++)
	                    {
							RequisitionLine line = (RequisitionLine) lineList.get(li);
							if(line.getStatus().equals(DocumentStatus.CANCELLED)){		 continue;		}

	                        //make sure override flags are set properly (especially necessary for items added after the user's last visit to the totals page)
	                        if( bDiscOvr ) { line.setDiscOvr("Y"); }
	                        if( bTaxOvr )  { line.setTaxOvr("Y"); }
	                        if( bShipOvr ) { line.setShipOvr("Y"); }
	                        if( bOtherOvr ){ line.setOtherOvr("Y"); }

	                        //keep track of last line that was taxable in case you need to adjust later
	                        if(line.getTaxable().equals("Y")){		iTaxLine = li;		}
	                        bdExtCost = this.extCost(line);
	                        bdLineTaxable = bdExtCost.subtract(line.getDiscountAmount()); //get taxable amount of line item

	                        /************** start calculate tax */
							TaxManager mt = new TaxManager();
							mt.getTax("REQ", header, line, bdTaxableSubtotal, bAllZeroLines, bdExtCost, iLines, organizationId, header.getRequisitionerCode());
							/************** end calculate tax */

	                        //ship charges
	                        if(line.getShipOvr().equals("N") && bdHdrShipping.compareTo(bdZero) == 1)//if shipping entered at header
	                        {
	                            if(bAllZeroLines)
	                            {
	                                line.setShippingCharges(bdHdrShipping.divide(new BigDecimal(iLines), 2, BigDecimal.ROUND_HALF_UP));
	                            }
	                            else
	                            {
									bdTemp = bdExtCost.divide(bdSubtotal, 5, BigDecimal.ROUND_HALF_UP);
									bdTemp = bdTemp.multiply(bdHdrShipping).setScale(2, BigDecimal.ROUND_HALF_UP);
									line.setShippingCharges(bdTemp);
	                            }
	                        }

							if ((header.getTaxPercent().compareTo(bdZero) == 1) && line.getTaxOvr().equals("N"))
	                        {
								if (header.getTaxShipping().equals("Y"))
								{
									if((iLines - li) == 1)
									{
									    BigDecimal diff = header.getShippingTaxAmt().subtract(bdShipTaxTotal);
									    line.setShippingTaxAmt(diff.setScale(2, BigDecimal.ROUND_HALF_UP));
									}
									else
									{
									    line.setShippingTaxAmt(line.getShippingCharges().multiply(line.getTaxPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
									}
								}
							}
							else
							{
								//do nothing; no way to know if shipping tax amounts entered at line or distributed
							}
							//other charges
							if (line.getOtherOvr().equals("N")) //if other entered at header
							{
								line.setOtherDescription(header.getOtherChargDesc());

	                            if (bdHdrOther.compareTo(bdZero) == 1)
	                            {
	                                if (bAllZeroLines)
	                                {
	                                    line.setOtherCharges(bdHdrOther.divide(new BigDecimal(iLines), 2, BigDecimal.ROUND_HALF_UP));
	                                }
	                                else
	                                {
										bdTemp = bdExtCost.divide(bdSubtotal, 5, BigDecimal.ROUND_HALF_UP);
										bdTemp = bdTemp.multiply(bdHdrOther).setScale(2, BigDecimal.ROUND_HALF_UP);
										line.setOtherCharges(bdTemp);
	                                }
	                            }
	                        }

	                        if ((header.getTaxPercent().compareTo(bdZero) == 1) && line.getTaxOvr().equals("N"))
	                        {
	                            if (header.getTaxOther().equals("Y"))
	                            {

	                                if((iLines - li) == 1)
									{
									    BigDecimal diff = header.getOtherTaxAmount().subtract(bdOtherTaxTotal);
									    line.setOtherTaxAmount(diff.setScale(2));
									}
	                                else
	                                {
	                                    line.setOtherTaxAmount(line.getOtherCharges().multiply(line.getTaxPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
	                                }
	                            }
	                        }
	                        else
	                        {
	                            //do nothing; no way to know if other tax amounts entered at line or distributed
	                        }

	                        //keep running total of all columns for comparison to header amount or for new header amount
	                        bdTaxTotal = bdTaxTotal.add(line.getTaxAmount());
	                        bdUseTaxTotal = bdUseTaxTotal.add(line.getUseTaxAmount());
	                        Log.debug(this, "calculateTotals current total line tax: " + bdTaxTotal.toString());

	                        bdShipTotal = bdShipTotal.add(line.getShippingCharges());
	                        bdShipTaxTotal = bdShipTaxTotal.add(line.getShippingTaxAmt());
	                        bdOtherTotal = bdOtherTotal.add(line.getOtherCharges());
							bdOtherTaxTotal = bdOtherTaxTotal.add(line.getOtherTaxAmount());
	                    }
	                    Log.debug(this, "calculateTotals line tax total: " + bdTaxTotal.toString());

	                    //set header tax amount
	                    if (bdTaxTotal.compareTo(bdZero) == 0)
	                    {
	                        header.setTaxAmount(bdZero);
	                    }
	                    else if (!bTaxOvr && header.getTaxPercent().compareTo(bdZero) == 0) //if tax was distributed then make sure total of lines matches header
	                    {
	                        Log.debug(this, "calculateTotals tax total: " + bdTaxTotal.toString());
	                        bdDiff = bdHdrTax.subtract(bdTaxTotal);
	                        if (bdDiff.compareTo(bdZero) != 0)
	                        {
								RequisitionLine line = (RequisitionLine) lineList.get(iTaxLine);
	                            line.setTaxAmount(line.getTaxAmount().add(bdDiff).setScale(2, BigDecimal.ROUND_HALF_UP));
	                        }
	                        else
	                        {
	                            header.setTaxAmount(bdTaxTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                        }
	                    }
	                    else
	                    {
	                        Log.debug(this, "calculateTotals tax total: " + bdTaxTotal.toString());
	                        header.setTaxAmount(bdTaxTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                    }

	                    //useTax rounding
	                    Log.debug(this, "calculateTotals use tax total: " + bdUseTaxTotal.toString());
                        bdDiff = bdUseHdrTax.subtract(bdUseTaxTotal);
                        if (bdDiff.compareTo(bdZero) != 0)
                        {
							RequisitionLine line = (RequisitionLine) lineList.get(iLines -1);
                            line.setUseTaxAmount(line.getUseTaxAmount().add(bdDiff).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        else
                        {
                            header.setUseTaxAmount(bdUseTaxTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

	                    //set header shipping amount
	                    if (!bShipOvr && bdHdrShipping.compareTo(bdZero) == 1)//if shipping was distributed then make sure total of lines matches header
	                    {
	                        bdDiff = bdHdrShipping.subtract(bdShipTotal);
	                        if (bdDiff.compareTo(bdZero) != 0)
	                        {
								RequisitionLine line = (RequisitionLine) lineList.get(iLines - 1);
	                            line.setShippingCharges(line.getShippingCharges().add(bdDiff));
	                        }
	                    }
	                    else
	                    {
	                        header.setShippingCharges(bdShipTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                    }
	                    //set header shipping tax amount
	                    if ((header.getTaxPercent().compareTo(bdZero) == 1) && !bTaxOvr)
	                    {
	                       if (header.getTaxShipping().equals("Y"))
	                        {

	                            header.setShippingTaxAmt(bdShipTaxTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                        }
	                    }
	                    //set header other amount
	                    if(!bOtherOvr && bdHdrOther.compareTo(bdZero) == 1)//if other was distributed then make sure total of lines matches header
	                    {
	                        bdDiff = bdHdrOther.subtract(bdOtherTotal);
	                        if(bdDiff.compareTo(bdZero) != 0)
	                        {
								RequisitionLine line = (RequisitionLine) lineList.get(iLines - 1);
	                            line.setOtherCharges(line.getOtherCharges().add(bdDiff));
	                        }
	                    }
	                    else
	                    {
	                        header.setOtherCharges(bdOtherTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                    }
	                    //set header other tax amount
	                    if((header.getTaxPercent().compareTo(bdZero) == 1) && !bTaxOvr)
	                    {
	                        if(header.getTaxOther().equals("Y"))
	                        {
	                            header.setOtherTaxAmount(bdOtherTaxTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	                        }
	                    }
	                }

	            //calculate the total for each line
	            for(int li = 0; li < iLines; li++)
	            {
					RequisitionLine line = (RequisitionLine) lineList.get(li);
					if(line.getStatus().equals(DocumentStatus.CANCELLED))
					{
					    continue;
					}

	                bdExtCost = this.extCost(line);
	                BigDecimal bdOthersTotal = line.getTaxAmount().add(
	                        								line.getShippingCharges()).add(
	                        								line.getShippingTaxAmt()).add(
	                        								line.getOtherCharges()).add(
	                        								line.getOtherTaxAmount()).add(
	                        								line.getUseTaxAmount());

	                bdLineTotal = bdExtCost.subtract(line.getDiscountAmount()).add(bdOthersTotal);
	                bdLineTotal = bdLineTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	                StringBuffer sb = new StringBuffer();
	                sb.append("line:\t" + line.getLineNumber().toString());
	                sb.append("tax:\t" + line.getTaxAmount());
	                sb.append("shipping:\t" + line.getShippingCharges());
	                sb.append("shp tax:\t" + line.getShippingTaxAmt());
	                sb.append("other:\t" + line.getOtherCharges());
	                sb.append("other tax:\t" + line.getOtherTaxAmount());
	                sb.append("others total:\t" + bdOthersTotal.toString());
	                sb.append("ext cost:\t" + bdExtCost );
	                sb.append("line total:\t" + bdLineTotal.toString());
	                Log.debug(this, sb.toString());

	                line.setLineTotal(bdLineTotal);
	                Log.debug(this, "calculateTotals line total for " + String.valueOf(li) + ": " + bdLineTotal.toString());
	            }

	            setHeaderTotals(header, lineList);  //set total of form
			}
	        else
	        {
	            setHeaderTotals(header, lineList);  //set total of form
	        }
		}
		catch (Exception e)
		{
			throw e;
		}
    }

    /**
    *	Method lineTotals
    *		Calculate line totals and assign to header
    *	@param header - header entity
    *	@param lineList   - line items list
    *	@return int
    *		 1 - Success
    *		-1 - Failure
    */
    public void setHeaderTotals(RequisitionHeader header, List lineList) throws Exception
    {
        BigDecimal bdTotal = new BigDecimal("0.00");
        int iRows = 0;

		try
		{
			if (lineList != null)
			{
				iRows = lineList.size();
			}
	        if (iRows == 0)
	        {
	            header.setSubtotal(new BigDecimal("0.00"));
	            header.setDiscountAmount(new BigDecimal("0.00"));
	            header.setTaxAmount(new BigDecimal("0.00"));
	            header.setShippingCharges(new BigDecimal("0.00"));
	            header.setOtherCharges(new BigDecimal("0.00"));
	            header.setShippingTaxAmt(new BigDecimal("0.00"));
	            header.setOtherTaxAmount(new BigDecimal("0.00"));
	            header.setUseTaxAmount(new BigDecimal("0.00"));
	        }
	        else
	        {
	            for (int i = 0; i < iRows; i++)
	            {
					RequisitionLine line = (RequisitionLine) lineList.get(i);
					if(line.getStatus().equals(DocumentStatus.CANCELLED))
					{
					    continue;
					}
	                bdTotal =  bdTotal.add(line.getLineTotal());
	            }
	        }
	        header.setTotal(bdTotal); //set header total

	        Log.debug(this, "setHeaderTotals complete ... total = " + String.valueOf(bdTotal));
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
	 public void defaultNullValues(RequisitionHeader header, List lineList) throws Exception
	 {
		BigDecimal bdDefault = new BigDecimal("0.00");
		String	ovrDefault = "N";
		int iRows = 0;

		try
		{
			if (header != null)
			{
				if (header.getDiscountAmount() == null)	{	header.setDiscountAmount(bdDefault);	}
				if (header.getDiscountPercent() == null)	{	header.setDiscountPercent(bdDefault);	}
				if (header.getEstimatedCost() == null)	{	header.setEstimatedCost(bdDefault);	}
				if (header.getOtherCharges() == null)	{	header.setOtherCharges(bdDefault);	}
				if (header.getOtherTaxAmount() == null)	{	header.setOtherTaxAmount(bdDefault);	}
				if (header.getReqRecalc() == null)	{	header.setReqRecalc("");	}
				if (header.getShippingCharges() == null)	{	header.setShippingCharges(bdDefault);	}
				if (header.getShippingTaxAmt() == null)	{	header.setShippingTaxAmt(bdDefault);	}
				if (header.getSubtotal() == null)	{	header.setSubtotal(bdDefault);	}
				if (header.getTaxAmount() == null)	{	header.setTaxAmount(bdDefault);	}
				if (header.getTaxCode() == null)	{	header.setTaxCode("");	}
				if (header.getTaxOther() == null)	{	header.setTaxOther("");	}
				if (header.getTaxPercent() == null)	{	header.setTaxPercent(bdDefault);	}
				if (header.getTaxShipping() == null)	{	header.setTaxShipping("");	}
				if (header.getTotal() == null)	{	header.setTotal(bdDefault);	}
			}
			if (lineList != null)
			{
				iRows = lineList.size();
			}
			for (int i = 0; i < iRows; i++)
			{
				RequisitionLine line = (RequisitionLine) lineList.get(i);

				if (line.getAllocated() == null)	{	line.setAllocated(bdDefault);	}
				if (line.getDiscountAmount() == null)	{	line.setDiscountAmount(bdDefault);	}
				if (line.getDiscountPercent() == null)	{	line.setDiscountPercent(bdDefault);	}
				if (line.getDiscOvr() == null)	{	line.setDiscOvr(ovrDefault);	}
				if (line.getLineTotal() == null)	{	line.setLineTotal(bdDefault);	}
				if (line.getOtherCharges() == null)	{	line.setOtherCharges(bdDefault);	}
				if (line.getOtherOvr() == null)	{	line.setOtherOvr(ovrDefault);	}
				if (line.getOtherTaxAmount() == null)	{	line.setOtherTaxAmount(bdDefault);	}
				if (line.getQuantity() == null)	{	line.setQuantity(bdDefault);	}
				if (line.getShipOvr() == null)	{	line.setShipOvr(ovrDefault);	}
				if (line.getShippingCharges() == null)	{	line.setShippingCharges(bdDefault);	}
				if (line.getShippingTaxAmt() == null)	{	line.setShippingTaxAmt(bdDefault);	}
				if (line.getTaxable() == null)	{	line.setTaxable("");	}
				if (line.getTaxAmount() == null)	{	line.setTaxAmount(bdDefault);	}
				if (line.getTaxCode() == null)	{	line.setTaxCode("");	}
				if (line.getTaxOther() == null)	{	line.setTaxOther("");	}
				if (line.getTaxOvr() == null)	{	line.setTaxOvr(ovrDefault);	}
				if (line.getTaxPercent() == null)	{	line.setTaxPercent(bdDefault);	}
				if (line.getTaxShipping() == null)	{	line.setTaxShipping("");	}
				if (line.getUmCode() == null)	{	line.setUmCode("");	}
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
