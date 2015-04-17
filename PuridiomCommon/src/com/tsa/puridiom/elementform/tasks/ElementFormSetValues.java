package com.tsa.puridiom.elementform.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ElementFormSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ElementFormPK comp_id = new ElementFormPK();
			ElementForm elementForm = (ElementForm) incomingRequest.get("elementForm");
			if (elementForm == null)
			{
				elementForm = new ElementForm();
			}

			if (incomingRequest.containsKey("ElementForm_formId"))
			{
				String formId = (String) incomingRequest.get("ElementForm_formId");
				comp_id.setFormId(formId);
			}
			if (incomingRequest.containsKey("ElementForm_elementIndex"))
			{
				String elementIndexString = (String) incomingRequest.get("ElementForm_elementIndex");
				if (Utility.isEmpty(elementIndexString))
				{
					elementIndexString = "0";
				}
				BigDecimal elementIndex = new BigDecimal ( elementIndexString );
				comp_id.setElementIndex(elementIndex);
			}
			if (incomingRequest.containsKey("ElementForm_elementModule"))
			{
				String elementModule = (String) incomingRequest.get("ElementForm_elementModule");
				elementForm.setElementModule(elementModule);
			}
			if (incomingRequest.containsKey("ElementForm_elementId"))
			{
				String elementId = (String) incomingRequest.get("ElementForm_elementId");
				elementForm.setElementId(elementId);
			}
			if (incomingRequest.containsKey("ElementForm_elementType"))
			{
				String elementType = (String) incomingRequest.get("ElementForm_elementType");
				elementForm.setElementType(elementType);
			}
			if (incomingRequest.containsKey("ElementForm_elementLength"))
			{
				String elementLengthString = (String) incomingRequest.get("ElementForm_elementLength");
				if (Utility.isEmpty(elementLengthString))
				{
					elementLengthString = "0";
				}
				BigDecimal elementLength = new BigDecimal ( elementLengthString );
				elementForm.setElementLength(elementLength);
			}
			if (incomingRequest.containsKey("ElementForm_elementCase"))
			{
				String elementCase = (String) incomingRequest.get("ElementForm_elementCase");
				elementForm.setElementCase(elementCase);
			}
			if (incomingRequest.containsKey("ElementForm_elementFormat"))
			{
				String elementFormat = (String) incomingRequest.get("ElementForm_elementFormat");
				elementForm.setElementFormat(elementFormat);
			}
			if (incomingRequest.containsKey("ElementForm_elementLabel"))
			{
				String elementLabel = (String) incomingRequest.get("ElementForm_elementLabel");
				elementForm.setElementLabel(elementLabel);
			}
			if (incomingRequest.containsKey("ElementForm_elementDefault"))
			{
				String elementDefault = (String) incomingRequest.get("ElementForm_elementDefault");
				elementForm.setElementDefault(elementDefault);
			}
			if (incomingRequest.containsKey("ElementForm_elementTb"))
			{
				String elementTbString = (String) incomingRequest.get("ElementForm_elementTb");
				if (Utility.isEmpty(elementTbString))
				{
					elementTbString = "0";
				}
				BigDecimal elementTb = new BigDecimal ( elementTbString );
				elementForm.setElementTb(elementTb);
			}
			if (incomingRequest.containsKey("ElementForm_elementTc"))
			{
				String elementTcString = (String) incomingRequest.get("ElementForm_elementTc");
				if (Utility.isEmpty(elementTcString))
				{
					elementTcString = "0";
				}
				BigDecimal elementTc = new BigDecimal ( elementTcString );
				elementForm.setElementTc(elementTc);
			}
			elementForm.setComp_id(comp_id);

			result = elementForm;
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