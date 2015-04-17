package com.tsa.puridiom.usergrouprel.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserGroupRelRetrieveCountByGroupId extends Task
{
	public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String groupId = (String ) incomingRequest.get("UserGroupRel_groupId");
            String queryString = "select count(*) from UserGroupRel as UserGroupRel where UserGroupRel.id.groupId = ? ";
            List resultList = dbs.query(queryString, new Object[] {groupId} , new Type[] { Hibernate.STRING}) ;

            if (resultList != null && resultList.size() > 0)
            {
                result = (Long) resultList.get(0);
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