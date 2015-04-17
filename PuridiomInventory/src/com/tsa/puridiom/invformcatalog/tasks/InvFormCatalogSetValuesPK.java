package com.tsa.puridiom.invformcatalog.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class InvFormCatalogSetValuesPK
{
	public void setValues(Map incomingRequest, InvFormCatalog invformcatalog) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvFormCatalog_itemNumber");
			String invCatid = (String ) incomingRequest.get("InvFormCatalog_invCatid");
			InvFormCatalogPK comp_id = new InvFormCatalogPK();
			comp_id.setItemNumber(itemNumber);
			comp_id.setInvCatid(invCatid);
			invformcatalog.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}