package com.tsa.puridiom.xrefcombo.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class XrefComboDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		XrefCombo xrefCombo = (XrefCombo)incomingRequest.get("xrefCombo");

		if(xrefCombo == null)
		{
			xrefCombo = new XrefCombo();
		}

		XrefComboSetValuesPK setValues = new XrefComboSetValuesPK();
		setValues.setValues(incomingRequest, xrefCombo);
		dbs.delete(xrefCombo);
		this.setStatus(dbs.getStatus()) ;
		return xrefCombo ;

	}

}