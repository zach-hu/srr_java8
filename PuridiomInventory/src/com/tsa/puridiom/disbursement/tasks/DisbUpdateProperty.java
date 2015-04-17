package com.tsa.puridiom.disbursement.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvProperty;
import com.tsa.puridiom.invproperty.tasks.InvPropertyRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * update inventory property records
 *
 * @author corbinj
 */
public class DisbUpdateProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		//key is bin location icrc
		//value is serial number
		Map selectedPropertyMap = (Map) map.get("selectedProperty");
		List disbLines = (List)map.get("disbLines");
		DBSession dbs = (DBSession)map.get("dbsession") ;
		Set disbLineIcRcs = new HashSet();
		Map disbLineMap = new HashMap();

		for (Object obj: disbLines) {
			DisbLine disbLine = (DisbLine) obj;

			disbLineIcRcs.add(disbLine.getIcRc().toString());
			disbLineMap.put(disbLine.getIcRc(), disbLine.getIcDsbLine());
		}

		Set keys = selectedPropertyMap.keySet();

		InvPropertyRetrieveById invPropertyRetrieveById = new InvPropertyRetrieveById();

		for (Object key: keys) {
			//first, make sure that serial number is actually still associated with this disbursement (and wasn't deleted)
			if (disbLineIcRcs.contains(key)) {
				List ids = (List) selectedPropertyMap.get(key);
				for (Object invPropIdObj: ids) {
					//retrieve the property record
					Map request = new HashMap();
					request.put("dbsession", dbs);
					request.put("InvProperty_icProperty", invPropIdObj);

					InvProperty invProperty = (InvProperty) invPropertyRetrieveById.executeTask(request);

					//change the status to 05 (inactive)
					invProperty.setStatus("05");
					if (disbLineMap.containsKey(key)) {
						invProperty.setIcDsbLine((BigDecimal)disbLineMap.get(key));
					} else if (disbLineMap.containsKey((new BigDecimal(key.toString())))) {
						invProperty.setIcDsbLine((BigDecimal)disbLineMap.get((new BigDecimal(key.toString()))));
					}
					invProperty.setIcPoLine((BigDecimal)disbLineMap.get(key));

					//update the serial number
					dbs.update(invProperty);
				}
			}
		}

		this.setStatus(80);

		return new Object();
	}
}
