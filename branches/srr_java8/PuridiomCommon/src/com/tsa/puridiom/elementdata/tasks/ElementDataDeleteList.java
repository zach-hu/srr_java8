package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.ElementData;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ElementDataDeleteList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List dataList = (List) incomingRequest.get("formDataList") ;

			for (int i = 0; i < dataList.size(); i++) {
				ElementData ed = (ElementData) dataList.get(i) ;
				ElementDataDeleteById task = new ElementDataDeleteById() ;
				incomingRequest.put("elementData", ed) ;
				task.executeTask(incomingRequest) ;
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}