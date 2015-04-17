package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class UserProfileRetrieveDisplayName extends Task {
    public Object  executeTask (Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String userId = (String ) incomingRequest.get("UserProfile_userId");
            String organizationId = (String ) incomingRequest.get("UserProfile_organizationId");
            StringBuffer userName = new StringBuffer();

            String queryString = "select UserProfile.firstName, UserProfile.lastName from UserProfile UserProfile where UserProfile.userId = ? and UserProfile.organizationId = ? ";
            List resultList = dbs.query(queryString, new Object[] {userId.toUpperCase(), organizationId.toUpperCase() } , new Type[] { Hibernate.STRING, Hibernate.STRING }) ;

            if (resultList.size()> 0) {
            	Object results[] = (Object[]) resultList.get(0);
                String firstName = (String) results[0];
                String lastName = (String) results[1];

                if (!HiltonUtility.isEmpty(firstName)) {
                    userName.append(firstName.trim() + " ");
                }
                if (!HiltonUtility.isEmpty(lastName)) {
                    userName.append(lastName.trim());
                }
            }
            if (HiltonUtility.isEmpty(userName.toString())) {
                userName.append(userId);
            }

            this.setStatus(dbs.getStatus()) ;

            result = userName.toString().trim();
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return result;
    }

}