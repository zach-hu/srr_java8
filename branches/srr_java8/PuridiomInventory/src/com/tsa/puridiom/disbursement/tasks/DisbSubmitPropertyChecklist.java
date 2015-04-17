package com.tsa.puridiom.disbursement.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

/**
 * build the property map from the inv_property_checklist form submission
 *
 * @author corbinj
 */
public class DisbSubmitPropertyChecklist extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		int propertyListSize = Integer.parseInt((String)map.get("propertyListSize"));
		String binLocationIcRc = (String)map.get("InvBinLocation_icRc");
		Map selectedPropertyMap = (Map)map.get("selectedProperty");

		if (selectedPropertyMap == null) {
			selectedPropertyMap = new HashMap();
			map.put("selectedProperty", selectedPropertyMap);
		}

		List selectedProperty = new ArrayList();
		for (int i = 0; i < propertyListSize; i++) {
			if (map.get("checkbox_" + i) != null) {
				String icProperty = (String)map.get("invProperty_" + i);

				selectedProperty.add(icProperty);
			}
		}

		selectedPropertyMap.put(binLocationIcRc, selectedProperty);

		return selectedPropertyMap;
	}
}
