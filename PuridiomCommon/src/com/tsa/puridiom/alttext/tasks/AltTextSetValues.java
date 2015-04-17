package com.tsa.puridiom.alttext.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class AltTextSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AltTextPK comp_id = new AltTextPK();
			AltText altText = (AltText) incomingRequest.get("altText");
			if (altText == null)
			{
				altText = new AltText();
                UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
                incomingRequest.put("AltText_icText",ukg.getUniqueKey().toString());
			}

			if (incomingRequest.containsKey("AltText_source"))
			{
				String source = (String) incomingRequest.get("AltText_source");
				comp_id.setSource(source);
			}
			if (incomingRequest.containsKey("AltText_id"))
			{
				String id = (String) incomingRequest.get("AltText_id");
				comp_id.setId(id);
			}
			if (incomingRequest.containsKey("AltText_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("AltText_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("AltText_language"))
			{
				String language = (String) incomingRequest.get("AltText_language");
				comp_id.setLanguage(language);
			}
			if (incomingRequest.containsKey("AltText_icText"))
			{
				String icTextString = (String) incomingRequest.get("AltText_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				altText.setIcText(icText);
			}
			altText.setComp_id(comp_id);

			result = altText;
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