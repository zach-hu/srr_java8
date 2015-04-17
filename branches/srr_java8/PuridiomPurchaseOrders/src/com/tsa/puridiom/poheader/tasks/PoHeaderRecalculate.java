/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderRecalculate extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			PoHeader poh = (PoHeader)incomingRequest.get("poHeader") ;
			List polList = (List)incomingRequest.get("poLineList") ;
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("poline-recalculate.xml");
			
			String discountPercent = (String) incomingRequest.get("PoHeader_discountPercent");
			if (discountPercent == null){	discountPercent = Utility.tsaToString(poh.getDiscountPercent()) ;	}
			BigDecimal bdDiscountPercent = new BigDecimal(discountPercent);
			
			String discountAmount = (String) incomingRequest.get("PoHeader_discountAmount");
			if (discountAmount == null){	discountAmount = Utility.tsaToString(poh.getDiscountAmount()) ;	}
			BigDecimal bdDiscountAmount = new BigDecimal(discountAmount) ;
			
	 		String taxPercent = (String) incomingRequest.get("PoHeader_taxPercent") ;
			if (taxPercent == null){		taxPercent = Utility.tsaToString(poh.getTaxPercent());		}
			BigDecimal bdTaxPercent = new BigDecimal(taxPercent);
			
			String taxAmount = (String) incomingRequest.get("PoHeader_taxAmount") ;
			if (taxAmount == null){	taxAmount = Utility.tsaToString(poh.getTaxAmount());		}
			BigDecimal bdTaxAmount = new BigDecimal(taxAmount);
				
			String shippingCharges = (String) incomingRequest.get("PoHeader_shippingCharges");
			if (shippingCharges == null){		shippingCharges = Utility.tsaToString(poh.getShippingCharges());		}
			BigDecimal bdShippingCharges = new BigDecimal(shippingCharges);
	
			String otherCharges = (String) incomingRequest.get("PoHeader_otherCharges");
			if (otherCharges == null){		otherCharges = Utility.tsaToString(poh.getOtherCharges());		}
			BigDecimal bdOtherCharges = new BigDecimal(otherCharges);

			String taxShipping = (String) incomingRequest.get("PoHeader_shippingTaxable");
			if (taxShipping == null)
			{		
				taxShipping = poh.getShippingTaxable();	
				if (taxShipping == null){		taxShipping = "N";		}
			}

			String taxOther = (String) incomingRequest.get("PoHeader_otherTaxable");
			if (taxOther == null) 
			{
				taxOther = poh.getOtherTaxable();
				if (taxOther == null){		taxOther = "N";		}
			}

			boolean recalcTaxPercent = (bdTaxPercent.compareTo(poh.getTaxPercent()) != 0);
			boolean recalcTaxAmount = (bdTaxAmount.compareTo(poh.getTaxAmount()) != 0);
			boolean recalcDiscountPercent = (bdDiscountPercent.compareTo(poh.getDiscountPercent()) != 0);
			boolean recalcDiscountAmount =  (bdDiscountAmount.compareTo(poh.getDiscountAmount()) != 0);
			boolean recalcShippingCharges = (bdShippingCharges.compareTo(poh.getShippingCharges()) != 0);
			boolean recalcOtherCharges =  (bdOtherCharges.compareTo(poh.getOtherCharges()) != 0);

			BigDecimal bdSubtotal = new BigDecimal(0);
			BigDecimal bdDiscountTotal = new BigDecimal(0);			
			BigDecimal bdOtherTotal = new BigDecimal(0);			
			BigDecimal bdShippingTotal = new BigDecimal(0);			
			BigDecimal bdTaxTotal = new BigDecimal(0);
			BigDecimal bdOtherTaxTotal = new BigDecimal(0);			
			BigDecimal bdShippingTaxTotal = new BigDecimal(0);
			BigDecimal bdTaxableTotal = new BigDecimal(0);
			BigDecimal bdShippingTaxableTotal = new BigDecimal(0);
			BigDecimal bdOtherTaxableTotal = new BigDecimal(0);
			
			if (polList != null) 
			{
				/* Get Line Totals */
			    for (int i = 0; i < polList.size(); i++) 
			    {
						PoLine pol = (PoLine) polList.get(i);
						bdSubtotal = bdSubtotal.add(pol.getLineTotal());
			    }
			    
			    BigDecimal percent = null;
			    
			    for (int i = 0;i < polList.size(); i++) 
			    {
						PoLine pol = (PoLine) polList.get(i);

						if (pol.getLineTotal().compareTo(new BigDecimal(0)) != 0) 
						{
							 percent = pol.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP);
						} 
						else 
						{
							percent = new BigDecimal(0);
						}
						
						if (recalcDiscountPercent) 
						{
							if (pol.getDiscountPercent().compareTo(bdDiscountPercent) != 0) 
							{
								pol.setDiscountPercent(bdDiscountPercent);
								BigDecimal bdTemp = bdDiscountPercent.multiply(new BigDecimal(0.01));
								bdTemp = bdTemp.multiply(pol.getLineTotal());
								bdTemp = bdTemp.setScale(2,BigDecimal.ROUND_HALF_UP);
								pol.setDiscountAmount(bdTemp);
							}
						} 
						else if (recalcDiscountAmount) 
						{
							if (pol.getLineTotal().compareTo(new BigDecimal(0)) != 0) 
							{
								BigDecimal perc = pol.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP);
								BigDecimal bdTemp = perc.multiply(pol.getLineTotal());
								bdTemp = bdTemp.setScale(2,BigDecimal.ROUND_HALF_UP);
								pol.setDiscountAmount(bdTemp);
							}
						}
						
						if (recalcOtherCharges) 
						{
							if (pol.getLineTotal().compareTo(new BigDecimal(0)) != 0) 
							{
								//pol.setOtherCharges(percent.multiply(pol.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP));
								BigDecimal bdTemp = percent.multiply(bdOtherCharges).setScale(2,BigDecimal.ROUND_HALF_UP); 
								pol.setOtherCharges(bdTemp);
							}
						}

						if (recalcShippingCharges) 
						{
							if (pol.getLineTotal().compareTo(new BigDecimal(0)) != 0) 
							{
								//pol.setShippingCharges(percent.multiply(pol.getLineTotal()).setScale(2,BigDecimal.ROUND_HALF_UP));
								BigDecimal bdTemp = percent.multiply(bdShippingCharges).setScale(2,BigDecimal.ROUND_HALF_UP);
								pol.setShippingCharges(bdTemp);
							}
						}
								
						if (recalcTaxPercent) 
						{
							if (pol.getTaxPercent().compareTo(bdTaxPercent) != 0) 
							{
								pol.setTaxPercent(bdTaxPercent);
								BigDecimal bdTemp = bdTaxPercent.multiply(new BigDecimal(0.01));
								bdTemp = bdTemp.multiply(pol.getLineTotal());
								bdTemp = bdTemp.setScale(2, BigDecimal.ROUND_HALF_UP);
								pol.setTaxAmount(bdTemp);
								if (taxShipping.equals("Y")) 
								{
									bdTemp = bdTaxPercent.multiply(new BigDecimal(0.01));
									bdTemp = bdTemp.multiply(pol.getShippingCharges());
									bdTemp = bdTemp.setScale(2,BigDecimal.ROUND_HALF_UP);
									pol.setShippingTax(bdTemp);
								}
								if (taxOther.equals("Y")) 
								{
									bdTemp = bdTaxPercent.multiply(new BigDecimal(0.01));
									bdTemp = bdTemp.multiply(pol.getOtherCharges());
									bdTemp = bdTemp.setScale(2,BigDecimal.ROUND_HALF_UP);
									pol.setOtherTax(bdTemp);
								}
							}
						} 
						else if (recalcTaxAmount) 
						{
							if (pol.getLineTotal().compareTo(new BigDecimal(0)) != 0) 
							{
								BigDecimal perc = pol.getLineTotal().divide(bdSubtotal,4,BigDecimal.ROUND_HALF_UP);
								BigDecimal bdTemp = pol.getLineTotal().subtract(pol.getDiscountAmount());
								perc = perc.multiply(bdTemp).setScale(2,BigDecimal.ROUND_HALF_UP);
								pol.setTaxAmount(perc);
							}
						}
						
						bdDiscountTotal = bdDiscountTotal.add(pol.getDiscountAmount());
						bdOtherTotal = bdOtherTotal.add(pol.getOtherCharges());
						bdShippingTotal = bdShippingTotal.add(pol.getShippingCharges());
						
						if (!pol.getTaxable().equals("N")) 
						{
							bdTaxTotal = bdTaxTotal.add(pol.getTaxAmount());
							bdOtherTaxTotal = bdOtherTaxTotal.add(pol.getOtherTax());
							bdShippingTaxTotal = bdShippingTaxTotal.add(pol.getShippingTax());
							
							bdTaxableTotal = bdTaxableTotal.add(pol.getLineTotal().subtract(pol.getDiscountAmount()));
							bdShippingTaxableTotal = bdShippingTaxableTotal.add(pol.getLineTotal().subtract(pol.getDiscountAmount()));
							bdOtherTaxableTotal = bdOtherTaxableTotal.add(pol.getLineTotal().subtract(pol.getDiscountAmount()));
						}
						
						if (i == polList.size()) 
						{
							if (bdOtherTotal.compareTo(bdOtherCharges) != 0) 
							{
								pol.setOtherCharges(pol.getOtherCharges().add(bdOtherCharges.subtract(bdOtherTotal)));
							}
							if (bdShippingTotal.compareTo(bdShippingCharges) != 0) 
							{
								pol.setShippingCharges(pol.getShippingCharges().add(bdShippingCharges.subtract(bdShippingTotal)));
							}
							if (bdDiscountTotal.compareTo(bdDiscountAmount) != 0) 
							{
								pol.setDiscountAmount(pol.getDiscountAmount().add(bdDiscountAmount.subtract(bdDiscountTotal)));
							}
							if (bdTaxTotal.compareTo(bdTaxAmount) != 0) 
							{
								pol.setTaxAmount(pol.getTaxAmount().add(bdTaxAmount.subtract(bdTaxTotal)));
							}
						}
						
						/* Update Line */
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("PoLine_icPoHeader", pol.getIcPoHeader().toString());
						updateParameters.put("PoLine_icPoLine", pol.getIcPoLine().toString());
						updateParameters.put("poLine", pol);
						process.executeProcess(updateParameters);
			    }
			}
			    
			BigDecimal bdAllocateTotal = bdSubtotal.subtract(bdDiscountTotal).add(bdTaxTotal);
			bdAllocateTotal = bdAllocateTotal.add(bdShippingTotal);
			bdAllocateTotal = bdAllocateTotal.add(bdShippingTaxTotal);
			bdAllocateTotal = bdAllocateTotal.add(bdOtherTotal);
			bdAllocateTotal = bdAllocateTotal.add(bdOtherTaxTotal);
				
		    incomingRequest.put("PoHeader_subtotal", bdSubtotal.toString());
			incomingRequest.put("PoHeader_total", bdAllocateTotal.toString());
			incomingRequest.put("PoHeader_discountAmount", bdDiscountTotal.toString());
			incomingRequest.put("PoHeader_taxAmount", bdTaxTotal.toString());
			incomingRequest.put("PoHeader_shippingCharges", bdShippingTotal.toString());		
			incomingRequest.put("PoHeader_shippingTax", bdShippingTaxTotal.toString());		
			incomingRequest.put("PoHeader_otherCharges", bdOtherTotal.toString());		
			incomingRequest.put("PoHeader_otherTax", bdOtherTaxTotal.toString());
			incomingRequest.put("allocateTotal", bdAllocateTotal.toString());
			
			incomingRequest.put("Account_icHeader",poh.getIcPoHeader().toString());
			incomingRequest.put("Account_icLine", "0");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}