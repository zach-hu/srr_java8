package com.tsa.puridiom.invformproduct.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvFormProductSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvFormProductPK comp_id = new InvFormProductPK();
			InvFormProduct invFormProduct = (InvFormProduct) incomingRequest.get("invFormProduct");
			if (invFormProduct == null)
			{
				invFormProduct = new InvFormProduct();
			}

			if (incomingRequest.containsKey("InvFormProduct_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvFormProduct_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvFormProduct_productId"))
			{
				String productId = (String ) incomingRequest.get("InvFormProduct_productId");
				comp_id.setProductId(productId);
			}
			invFormProduct.setComp_id(comp_id);

			result = invFormProduct;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}