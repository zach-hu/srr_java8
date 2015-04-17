package com.tsa.puridiom.invreturn.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvProperty;
import com.tsagate.foundation.processengine.Task;

/**
 * Add property
 *
 * @author corbinj
 */
public class InvReturnAddProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		List propertyList = (List) map.get("addedProperty");

		InvProperty invProperty = new InvProperty();

		invProperty.setSerialNumber((String)map.get("InvProperty_serialNumber"));
		invProperty.setRemarks((String)map.get("InvProperty_remarks"));

		propertyList.add(invProperty);

		this.setStatus(80);

		return propertyList;
	}
}
