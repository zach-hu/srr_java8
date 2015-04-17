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
public class DisbSetProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		Map selectedPropertyMap = new HashMap();
		map.put("selectedProperty", selectedPropertyMap);
		if (map.get("disb_property_numberOfKeys") != null) {
			int numberOfKeys = Integer.parseInt(map.get("disb_property_numberOfKeys").toString());

			for (int i = 0; i < numberOfKeys; i++) {
				String invBinLocationIcRc = map.get("disb_property_" + i).toString();
				int numberOfSerialNumbers = Integer.parseInt(map.get("disb_property_" + i + "_size").toString());

				List serialNumbers = new ArrayList();
				for (int j = 0; j < numberOfSerialNumbers; j++) {
					serialNumbers.add(map.get("disb_property_" + i + "_" + j));
				}

				selectedPropertyMap.put(invBinLocationIcRc, serialNumbers);
			}
		}

		this.setStatus(80);

		return selectedPropertyMap;
	}
}
