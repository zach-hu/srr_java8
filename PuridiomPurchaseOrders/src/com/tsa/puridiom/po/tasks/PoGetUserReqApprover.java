package com.tsa.puridiom.po.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoGetUserReqApprover extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List list = null;
		List listUserProfile = new ArrayList();
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List listApprover = (List)incomingRequest.get("Requisition_lastApprover");

			if(listApprover != null && listApprover.size() >0 )
			{
				for(int i = 0; i < listApprover.size(); i++)
				{
					ApprovalLog approval = (ApprovalLog)listApprover.get(i);
					String userId = approval.getUserId();
					String queryString = "from UserProfile as u where u.userId = ?)";
					list = dbs.query(queryString, new Object[] {userId} , new Type[] { Hibernate.STRING}) ;
					if (list != null && list.size() > 0)
					{
						UserProfile userProfile = (UserProfile)list.get(0);
						approval.setAlternateUserid(userProfile.getFirstName()+" "+userProfile.getLastName());
						approval.setUdfValues(userProfile.getTitle());
						listUserProfile.add(userProfile);
					}
					else
					{
						listUserProfile.add(userId);
					}
				}
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return listUserProfile;
	}
}
