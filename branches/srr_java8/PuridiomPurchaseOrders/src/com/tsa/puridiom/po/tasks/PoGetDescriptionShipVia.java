package com.tsa.puridiom.po.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.StdTable;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoGetDescriptionShipVia extends Task
{
	public Object executeTask(Object object) throws Exception
    {
		StdTable ret = null;
        Map incomingRequest = (Map)object;
        List list = null;
        String description = "";
        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        	String queryString = "from StdTable s where s.id.tableType = 'SHP' and s.id.tableKey = ?";
			list = dbs.query(queryString, new Object[] {poHeader.getShipViaCode()} , new Type[] { Hibernate.STRING}) ;
			if(list != null && list.size() > 0){
				ret = (StdTable)list.get(0);
				description = ret.getDescription();
			}
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return description;
    }
}
