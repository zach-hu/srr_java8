package com.tsa.puridiom.invreturn.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvProperty;
import com.tsagate.foundation.processengine.Task;

/**
 * build the property list from the inv_return_property_fields form submission
 *
 * @author corbinj
 */
public class InvReturnSetProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		List serialNumbers = new ArrayList();
		map.put("addedProperty", serialNumbers);
		if (map.get("inv_property_size") != null) {
			int size = Integer.parseInt(map.get("inv_property_size").toString());


			for (int i = 0; i < size; i++) {
				InvProperty invProperty = new InvProperty();

				invProperty.setSerialNumber((String)map.get("inv_property_" + i));
				invProperty.setRemarks((String)map.get("inv_property_remarks_" + i));
				invProperty.setStatus("02"); //active

				serialNumbers.add(invProperty);
			}
		}

		this.setStatus(80);

		return serialNumbers;
	}
}
