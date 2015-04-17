package com.tsa.puridiom.elementform.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class ElementFormSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain elementForm */
			ElementFormPK comp_id = new ElementFormPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			ElementForm	originalElementForm = (ElementForm) incomingRequest.get("elementForm");
			ElementForm	elementForm = new ElementForm();

			comp_id.setFormId(originalElementForm.getComp_id().getFormId());
			comp_id.setElementIndex(originalElementForm.getComp_id().getElementIndex());
			elementForm.setElementModule(originalElementForm.getElementModule());
			elementForm.setElementId(originalElementForm.getElementId());
			elementForm.setElementType(originalElementForm.getElementType());
			elementForm.setElementLength(originalElementForm.getElementLength());
			elementForm.setElementCase(originalElementForm.getElementCase());
			elementForm.setElementFormat(originalElementForm.getElementFormat());
			elementForm.setElementLabel(originalElementForm.getElementLabel());
			elementForm.setElementDefault(originalElementForm.getElementDefault());
			elementForm.setElementTb(originalElementForm.getElementTb());
			elementForm.setElementTc(originalElementForm.getElementTc());
			elementForm.setComp_id(comp_id);

			incomingRequest.put("elementForm", elementForm);

			ElementFormAdd addTask = new ElementFormAdd();
			elementForm = (ElementForm) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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