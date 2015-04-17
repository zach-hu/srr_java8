package com.tsa.puridiom.validationrules.invoice.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class CommodityCodeInvoiceLineRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
			String isWithinCommodityInvoiceTolerance = "Y";
			List invoiceLineList = (List) incomingRequest.get("lineitems");
	    	String organizationId = (String)incomingRequest.get("organizationId");
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); )
	        {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	PoLine poLine = (PoLine) invoiceLine.getPoLine();
	        	if( poLine!=null)
	        	{
	        		BigDecimal  bdUnitCostIvcLine = (HiltonUtility.ckNull(invoiceLine.getUnitPrice()));
					BigDecimal  bdUnitCostPoLine = (HiltonUtility.ckNull(poLine.getUnitPrice()));
					if(organizationId.equalsIgnoreCase("hoy08p")){
		        	if(bdUnitCostIvcLine.compareTo(bdUnitCostPoLine) > 0){
			        	Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, poLine.getCommodity());
			        	if( invoiceLine!= null && commodity!=null )
						{
			        		//invoice unit cost can be >= cost unit order + % invoice varience specified at the commodity

							BigDecimal  bdVchVariance =  commodity.getVchVariance();
							BigDecimal  bdUnitTolerance = new BigDecimal(0);

							BigDecimal bdVariance = bdVchVariance.multiply(new BigDecimal(".01")).add(new BigDecimal(1));

							bdUnitTolerance =  bdUnitCostPoLine.multiply(bdVariance);

							Log.debug(this, " : " + bdUnitTolerance + " " + bdUnitCostIvcLine);
							Log.debug(this, " : compareTo " + bdUnitCostIvcLine.compareTo(bdUnitTolerance));

							if(  commodity.getVchFailsafe().equalsIgnoreCase("Y") &&
									bdUnitCostIvcLine.compareTo(bdUnitTolerance) < 0 )
							{
								isWithinCommodityInvoiceTolerance = "N";
								break;
							}
						}
		        	}
				}
				else{
					Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, poLine.getCommodity());
		        	if( invoiceLine!= null && commodity!=null )
					{
		        		//invoice unit cost can be >= cost unit order + % invoice varience specified at the commodity

						BigDecimal  bdVchVariance =  commodity.getVchVariance();
						BigDecimal  bdUnitTolerance = new BigDecimal(0);

						BigDecimal bdVariance = bdVchVariance.multiply(new BigDecimal(".01")).add(new BigDecimal(1));

						bdUnitTolerance =  bdUnitCostPoLine.multiply(bdVariance);

						Log.debug(this, " : " + bdUnitTolerance + " " + bdUnitCostIvcLine);
						Log.debug(this, " : compareTo " + bdUnitCostIvcLine.compareTo(bdUnitTolerance));

						if(  commodity.getVchFailsafe().equalsIgnoreCase("Y") &&
								bdUnitCostIvcLine.compareTo(bdUnitTolerance) < 0 )
						{
							isWithinCommodityInvoiceTolerance = "N";
							break;
						}
					}
				}
	        	}
	        }
	        incomingRequest.put("isWithinCommodityInvoiceTolerance",isWithinCommodityInvoiceTolerance);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at CommodityCodeInvoiceLineRules" + e);
            e.printStackTrace();
            throw e;
		}
		return result;
    }
}