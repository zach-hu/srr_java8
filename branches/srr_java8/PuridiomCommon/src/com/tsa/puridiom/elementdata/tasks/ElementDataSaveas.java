package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class ElementDataSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain elementData */
			ElementDataPK comp_id = new ElementDataPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			ElementData	originalElementData = (ElementData) incomingRequest.get("elementData");
			ElementData	elementData = new ElementData();

			comp_id.setFormId(originalElementData.getComp_id().getFormId());
			comp_id.setIcHeader(originalElementData.getComp_id().getIcHeader());
			comp_id.setIcLine(originalElementData.getComp_id().getIcLine());
			comp_id.setElementId(originalElementData.getComp_id().getElementId());
			comp_id.setIcSequence(originalElementData.getComp_id().getIcSequence()) ;
			elementData.setElementValue(originalElementData.getElementValue());
			elementData.setComp_id(comp_id);

			incomingRequest.put("elementData", elementData);

			ElementDataAdd addTask = new ElementDataAdd();
			elementData = (ElementData) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = elementData;
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