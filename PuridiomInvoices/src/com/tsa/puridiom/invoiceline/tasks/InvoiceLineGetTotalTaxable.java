/*
 * Created on November 27, 2007
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvoiceLineGetTotalTaxable extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("InvoiceLine_itemNumber"))
			{
				Object lineitems = incomingRequest.get("InvoiceLine_itemNumber");

				if (lineitems instanceof String[])
				{
					int	arraySize = ((String[]) lineitems).length;
					Object obj =  incomingRequest.get("InvoiceLine_lineTotal");
					String arrayObj[] = null;

					if (obj instanceof String[])
					{
						arrayObj = (String[]) obj;

					}

					String taxable = "N";
					String sExtCost = "";
					BigDecimal bdExtCost = new BigDecimal(0);
					BigDecimal bdTotalTaxable = new BigDecimal(0);

					for (int i=0; i < arraySize; i++)
					{
						taxable = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceLine_taxable_" + i));
						if (taxable.equalsIgnoreCase("Y"))
						{
							sExtCost = (String) arrayObj[i];
							if ( HiltonUtility.isEmpty(sExtCost) )
							{
								sExtCost = "0";
							}
							bdExtCost = new BigDecimal ( sExtCost );
							bdTotalTaxable = bdTotalTaxable.add(bdExtCost);
						}

						incomingRequest.put("totalTaxableAmount", bdTotalTaxable.toString());
					}
				}
				else
				{
					if (incomingRequest.containsKey("InvoiceLine_taxable_0"))
					{
						String taxable = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceLine_taxable_0"));
						if (taxable.equalsIgnoreCase("Y"))
						{
							String sExtCost = (String) incomingRequest.get("InvoiceLine_lineTotal");
							incomingRequest.put("totalTaxableAmount", sExtCost);
						}
					}
				}
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("The value for InvoiceLine_itemNumber was not found.");
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
