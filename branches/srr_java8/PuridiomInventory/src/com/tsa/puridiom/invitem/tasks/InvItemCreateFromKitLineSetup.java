package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import java.util.ArrayList ;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class InvItemCreateFromKitLineSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			String organizationId = (String)incomingRequest.get("organizationId");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");

			if (HiltonUtility.isEmpty(userDateFormat)) {
				userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
			}

			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;
			if (poLine != null)
			{
				incomingRequest.put("InvItem_itemNumber", itemNumber);
				incomingRequest.put("InvItem_description", poLine.getDescription());
				incomingRequest.put("InvItem_commodity", poLine.getCommodity());
				incomingRequest.put("InvItem_cost", poLine.getUnitPrice().toString());
				incomingRequest.put("InvItem_taxable", poLine.getTaxable());
				incomingRequest.put("InvItem_dateEntered", Dates.today(userDateFormat));
				incomingRequest.put("InvItem_dateExpires", Dates.today(userDateFormat));
				incomingRequest.put("InvItem_owner", poLine.getRequisitionerCode());
				incomingRequest.put("InvItem_unitOfOrder", poLine.getUmCode());
				incomingRequest.put("InvItem_unitOfIssue", poLine.getUmCode());

				incomingRequest.put("InvItem_udf1Code", poLine.getUdf1Code()) ;
				incomingRequest.put("InvItem_udf2Code", poLine.getUdf2Code()) ;
				incomingRequest.put("InvItem_udf3Code", poLine.getUdf3Code()) ;
				incomingRequest.put("InvItem_udf4Code", poLine.getUdf4Code()) ;
				incomingRequest.put("InvItem_udf5Code", poLine.getUdf5Code()) ;
				incomingRequest.put("InvItem_udf6Code", poLine.getUdf6Code()) ;
				incomingRequest.put("InvItem_udf7Code", poLine.getUdf7Code()) ;
				incomingRequest.put("InvItem_udf8Code", poLine.getUdf8Code()) ;
				incomingRequest.put("InvItem_udf9Code", poLine.getUdf9Code()) ;
				incomingRequest.put("InvItem_udf10Code", poLine.getUdf10Code()) ;

				incomingRequest.put("InvItem_restrictedItem", "Y");
				incomingRequest.put("InvItem_assetCode", "N");
				incomingRequest.put("InvItem_shelfLifeRqd",poLine.getShelfLifeRqd()) ;
				incomingRequest.put("InvLocation_itemNumber", itemNumber) ;
			}

			if (poLine == null && reqLine != null)
			{
				incomingRequest.put("InvItem_itemNumber", itemNumber);
				incomingRequest.put("InvItem_description", reqLine.getDescription());
				incomingRequest.put("InvItem_commodity", reqLine.getCommodityCode());
				incomingRequest.put("InvItem_cost", reqLine.getUnitPrice().toString());
				incomingRequest.put("InvItem_taxable", reqLine.getTaxable());
				incomingRequest.put("InvItem_dateEntered", Dates.today(userDateFormat));
				incomingRequest.put("InvItem_dateExpires", Dates.today(userDateFormat));
				incomingRequest.put("InvItem_owner", reqLine.getRequisitionerCode());
				incomingRequest.put("InvItem_unitOfOrder", reqLine.getUmCode());
				incomingRequest.put("InvItem_unitOfIssue", reqLine.getUmCode());

				incomingRequest.put("InvItem_udf1Code", reqLine.getUdf1Code()) ;
				incomingRequest.put("InvItem_udf2Code", reqLine.getUdf2Code()) ;
				incomingRequest.put("InvItem_udf3Code", reqLine.getUdf3Code()) ;
				incomingRequest.put("InvItem_udf4Code", reqLine.getUdf4Code()) ;
				incomingRequest.put("InvItem_udf5Code", reqLine.getUdf5Code()) ;
				incomingRequest.put("InvItem_udf6Code", reqLine.getUdf6Code()) ;
				incomingRequest.put("InvItem_udf7Code", reqLine.getUdf7Code()) ;
				incomingRequest.put("InvItem_udf8Code", reqLine.getUdf8Code()) ;
				incomingRequest.put("InvItem_udf9Code", reqLine.getUdf9Code()) ;
				incomingRequest.put("InvItem_udf10Code", reqLine.getUdf10Code()) ;

				incomingRequest.put("InvItem_restrictedItem", "Y");
				incomingRequest.put("InvItem_assetCode", "N");
				incomingRequest.put("InvItem_shelfLifeRqd",reqLine.getShelfLifeRqd()) ;
				incomingRequest.put("InvLocation_itemNumber", itemNumber) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}