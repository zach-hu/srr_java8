package com.tsa.puridiom.common.tax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;


public class TaxManager
{

	public void getTax(String type, Object entity, Object entityLine, BigDecimal bdTaxableSubtotal, boolean bAllZeroLines, BigDecimal bdExtCost, int iLines, String organizationId, String userId)
	{
		try
		{
			if(type.equals("REQ"))
			{
				ICalculateTax req = new ReqStandarTax();
	    		req.calculateTax(entity, entityLine, bdTaxableSubtotal, bAllZeroLines, bdExtCost, iLines, organizationId);

			    ICalculateTax useTax = new ReqUseTax();
		    	useTax.calculateTax(entity, entityLine, bdTaxableSubtotal, bAllZeroLines, bdExtCost, iLines, organizationId);
			}
			else if(type.equals("PO"))
			{
				ICalculateTax po = new PoStandarTax();
	    		po.calculateTax(entity, entityLine, bdTaxableSubtotal, bAllZeroLines, bdExtCost, iLines, organizationId);

			    ICalculateTax useTax = new PoUseTax();
		    	useTax.calculateTax(entity, entityLine, bdTaxableSubtotal, bAllZeroLines, bdExtCost, iLines, organizationId);
			}

			if(type.equals("IVC"))
			{
				//ICalculateTax ivc = new IvcStandarTax();
				//ivc.calculateTax(entity, entityLine, bdTaxableSubtotal, bAllZeroLines, bdExtCost, lineList);
			}

		}
		catch (Exception e)
		{
			Log.error(this, e.getMessage() + " - TaxManager");
			e.printStackTrace();
		}

	}


}
