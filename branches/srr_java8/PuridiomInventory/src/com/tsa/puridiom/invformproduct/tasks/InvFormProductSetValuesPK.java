package com.tsa.puridiom.invformproduct.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class InvFormProductSetValuesPK
{
	public void setValues(Map incomingRequest, InvFormProduct invformproduct ) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvFormProduct_itemNumber");
			String productId = (String ) incomingRequest.get("InvFormProduct_productId");
			InvFormProductPK comp_id = new InvFormProductPK();
			comp_id.setItemNumber(itemNumber);
			comp_id.setProductId(productId);
			invformproduct.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}