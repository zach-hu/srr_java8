package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvProperty;
import com.tsa.puridiom.entity.InvReturn;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * build the property list from the inv_return_property_fields form submission
 *
 * @author corbinj
 */
public class AddProperty extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map map = (Map)object;

		DBSession dbs = (DBSession)map.get("dbsession") ;
		List serialNumbers = (List) map.get("addedProperty");
		InvReturn invReturn = (InvReturn)map.get("invReturn");

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

		for (Object invPropObj: serialNumbers) {
			InvProperty invProp = (InvProperty) invPropObj;

			invProp.setDateIn(invReturn.getRecDate());
			invProp.setIcRc(invReturn.getIcBin());
			invProp.setOwner(invReturn.getOwner());
			invProp.setItemNumber(invReturn.getItemNumber());
			invProp.setIcProperty(new BigDecimal(ukg.getUniqueKey()));

			dbs.add(invProp);
		}

		super.setStatus(dbs.getStatus());

		return serialNumbers;
	}
}
