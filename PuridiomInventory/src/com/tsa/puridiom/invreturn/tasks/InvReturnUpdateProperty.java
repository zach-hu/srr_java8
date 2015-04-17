package com.tsa.puridiom.invreturn.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvProperty;
import com.tsagate.foundation.processengine.Task;

/**
 * build the property list from the inv_return_property_fields form submission
 *
 * @author corbinj
 */
public class InvReturnUpdateProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		List serialNumbers = (List) map.get("addedProperty");

		InvProperty invProperty = (InvProperty) serialNumbers
			.get(Integer.parseInt((String)map.get("indexOfPropertyToEdit")));

		invProperty.setSerialNumber((String)map.get("InvProperty_serialNumber"));
		invProperty.setRemarks((String)map.get("InvProperty_remarks"));

		this.setStatus(80);

		return serialNumbers;
	}
}
