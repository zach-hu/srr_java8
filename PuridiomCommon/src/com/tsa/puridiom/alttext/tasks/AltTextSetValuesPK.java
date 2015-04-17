package com.tsa.puridiom.alttext.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AltTextSetValuesPK
{
	public void setValues(Map incomingRequest, AltText alttext ) throws Exception
	{
		try
		{
			String source = (String ) incomingRequest.get("AltText_source");
			String id = (String ) incomingRequest.get("AltText_id");
			String itemNumber = (String ) incomingRequest.get("AltText_itemNumber");
			String language = (String ) incomingRequest.get("AltText_language");
			AltTextPK comp_id = new AltTextPK();
			comp_id.setId(id);
			comp_id.setItemNumber(itemNumber);
			comp_id.setLanguage(language);
			comp_id.setSource(source);
			alttext.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}