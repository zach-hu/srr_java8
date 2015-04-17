/*
 * Created on Aug 22, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvoiceLineUpdateByLine extends Task
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
					Set keySet = incomingRequest.keySet();

					BigDecimal bdDiscountAlreadyApplied = new BigDecimal(0);
					BigDecimal bdShippingAlreadyApplied = new BigDecimal(0);
					BigDecimal bdOtherAlreadyApplied = new BigDecimal(0);
					BigDecimal bdTaxAlreadyApplied = new BigDecimal(0);
					BigDecimal bdUseTaxAlreadyApplied = new BigDecimal(0);

					int lastIndex = arraySize - 1;
					for (int j=0; j < arraySize; j++)
					{
						Object obj =  incomingRequest.get("InvoiceLine_quantity");
						String s_tax =  HiltonUtility.ckNull((String)incomingRequest.get("InvoiceLine_taxable_" + j));
						if (obj instanceof String[])
						{
							String arrayObj[] = (String[]) obj;
							BigDecimal lineQuantity = new BigDecimal(arrayObj[j]);
							if (lineQuantity.compareTo(new BigDecimal(0)) != 0 && s_tax.equalsIgnoreCase("Y"))
							{
								lastIndex = j;
							}
						}
					}

					for (int i=0; i < arraySize; i++)
					{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("invoiceline-update.xml");

						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InvoiceHeader_icIvcHeader", incomingRequest.get("InvoiceHeader_icIvcHeader"));
						updateParameters.put("InvoiceLine_icIvcHeader", incomingRequest.get("InvoiceLine_icIvcHeader"));
						updateParameters.put("discountPercent", incomingRequest.get("discountPercent"));
						updateParameters.put("totalTaxableAmount", incomingRequest.get("totalTaxableAmount"));
						updateParameters.put("discountAlreadyApplied", bdDiscountAlreadyApplied);
						updateParameters.put("shippingAlreadyApplied", bdShippingAlreadyApplied);
						updateParameters.put("otherAlreadyApplied", bdOtherAlreadyApplied);
						updateParameters.put("taxAlreadyApplied", bdTaxAlreadyApplied);
						updateParameters.put("useTaxAlreadyApplied", bdUseTaxAlreadyApplied);
						updateParameters.put("i", String.valueOf(i));
						updateParameters.put("arraySize", String.valueOf(arraySize));
						updateParameters.put("lastIndex", String.valueOf(lastIndex));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext())
						{
							String key = (String) iterator.next();
							if (key.indexOf("InvoiceLine_") == 0 || key.indexOf("InvoiceHeader_") == 0)
							{
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[])
								{
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
								else if (key.indexOf("InvoiceLine_taxable_" + i) == 0)
								{
									updateParameters.put("InvoiceLine_taxable", obj);
								}
								else if (key.indexOf("InvoiceLine_asset_" + i) == 0)
								{
									updateParameters.put("InvoiceLine_asset", obj);
								}
								else if (key.indexOf("InvoiceLine_udf1Code_" + i) == 0)
								{
									updateParameters.put("InvoiceLine_udf1Code", obj);
								}
								else
								{
									updateParameters.put(key, obj);
								}
							}
						}
						process.executeProcess(updateParameters);

						this.setStatus(process.getStatus());
						if (process.getStatus() == Status.FAILED)
						{
							break ;
						}

						bdDiscountAlreadyApplied = HiltonUtility.ckNull((BigDecimal) updateParameters.get("discountAlreadyApplied"));
						bdShippingAlreadyApplied = HiltonUtility.ckNull((BigDecimal) updateParameters.get("shippingAlreadyApplied"));
						bdOtherAlreadyApplied = HiltonUtility.ckNull((BigDecimal) updateParameters.get("otherAlreadyApplied"));
						bdTaxAlreadyApplied = HiltonUtility.ckNull((BigDecimal) updateParameters.get("taxAlreadyApplied"));
						bdUseTaxAlreadyApplied = HiltonUtility.ckNull((BigDecimal) updateParameters.get("useTaxAlreadyApplied"));
					}
				}
				else
				{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("invoiceline-update.xml");

						if (incomingRequest.containsKey("InvoiceLine_taxable_0"))
						{
							String taxable = (String) incomingRequest.get("InvoiceLine_taxable_0");
							incomingRequest.put("InvoiceLine_taxable", taxable);
						}
						if (incomingRequest.containsKey("InvoiceLine_asset_0"))
						{
							String taxable = (String) incomingRequest.get("InvoiceLine_asset_0");
							incomingRequest.put("InvoiceLine_asset", taxable);
						}

						incomingRequest.put("discountAlreadyApplied", new BigDecimal(0));
						incomingRequest.put("shippingAlreadyApplied", new BigDecimal(0));
						incomingRequest.put("otherAlreadyApplied", new BigDecimal(0));
						incomingRequest.put("taxAlreadyApplied", new BigDecimal(0));
						incomingRequest.put("useTaxAlreadyApplied", new BigDecimal(0));

						process.executeProcess(incomingRequest);
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
