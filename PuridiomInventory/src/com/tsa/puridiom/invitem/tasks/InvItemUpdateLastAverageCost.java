package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.common.utility.HiltonUtility;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvItemUpdateLastAverageCost extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			if (incomingRequest.containsKey("PoLine_altItemNumber"))
			{
				Object altItemNumberObj = incomingRequest.get("PoLine_altItemNumber");
				Object unitPriceObj = incomingRequest.get("PoLine_unitPrice");
				Object qtyReceivedObj = incomingRequest.get("ReceiptLine_qtyReceived");

				if (altItemNumberObj instanceof String[])
				{
					String altItemNumberArray[] = (String[]) altItemNumberObj;
					String unitPriceArray[] = (String[]) unitPriceObj;
					String qtyReceivedArray[] = (String[]) qtyReceivedObj;

					int altItemNumberSize = ((String[]) altItemNumberObj).length;

					for (int i=0; i<altItemNumberSize; i++)
					{
						String altItemNumber = HiltonUtility.ckNull((String)altItemNumberArray[i]);
						String unitPrice = HiltonUtility.ckNull((String)unitPriceArray[i]);

						/**** Calculate Average Cost ****/
						BigDecimal averageCost = new BigDecimal(0);
						BigDecimal sumQtyCost = new BigDecimal(0);
						BigDecimal sumQty = new BigDecimal(0);
						for (int j=0; j<altItemNumberSize; j++)
						{
							if (altItemNumber.equals(HiltonUtility.ckNull((String)altItemNumberArray[j])))
							{
								String costString = HiltonUtility.ckNull((String)unitPriceArray[j]);
								String qtyString = HiltonUtility.ckNull((String)qtyReceivedArray[j]);
								if (!HiltonUtility.isEmpty(costString) && !HiltonUtility.isEmpty(qtyString))
								{
									BigDecimal Cost = new BigDecimal(costString);
									BigDecimal Qty = new BigDecimal(qtyString);
									sumQtyCost = sumQtyCost.add(Cost.multiply(Qty));
									sumQty = sumQty.add(Qty);
								}
							}
						}
						if (sumQtyCost.compareTo(new BigDecimal(0)) != 0 && sumQty.compareTo(new BigDecimal(0)) != 0)
						{
							averageCost = sumQtyCost.divide(sumQty, 2, BigDecimal.ROUND_HALF_UP);
						}
						/*****************************/

						Map newParameters = new HashMap();
						newParameters.put("dbsession", incomingRequest.get("dbsession"));
						newParameters.put("InvItem_itemNumber", altItemNumber);

						InvItemRetrieveById invItemRetrieveById = new InvItemRetrieveById();
						InvItem invItem = (InvItem)invItemRetrieveById.executeTask(newParameters);

						if (invItem != null && !HiltonUtility.isEmpty(unitPrice))
						{
							invItem.setLastCost(new BigDecimal(unitPrice));
							if (averageCost.compareTo(new BigDecimal(0)) != 0)
							{
								invItem.setAverageCost(averageCost);
							}
							newParameters.put("invItem", invItem);

							InvItemUpdate invItemUpdate = new InvItemUpdate();
							invItemUpdate.executeTask(newParameters);
						}
					}
				}
				else
				{
					String altItemNumber = HiltonUtility.ckNull((String)incomingRequest.get("PoLine_altItemNumber"));
					String unitPrice = HiltonUtility.ckNull((String)incomingRequest.get("PoLine_unitPrice"));

					Map newParameters = new HashMap();
					newParameters.put("dbsession", incomingRequest.get("dbsession"));
					newParameters.put("InvItem_itemNumber", altItemNumber);

					InvItemRetrieveById invItemRetrieveById = new InvItemRetrieveById();
					InvItem invItem = (InvItem)invItemRetrieveById.executeTask(newParameters);

					if (invItem != null && !HiltonUtility.isEmpty(unitPrice))
					{
						invItem.setLastCost(new BigDecimal(unitPrice));
						invItem.setAverageCost(new BigDecimal(unitPrice));
						newParameters.put("invItem", invItem);

						InvItemUpdate invItemUpdate = new InvItemUpdate();
						invItemUpdate.executeTask(newParameters);
					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
