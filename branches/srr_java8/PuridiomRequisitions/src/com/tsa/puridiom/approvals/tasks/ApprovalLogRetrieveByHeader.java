/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */
public class ApprovalLogRetrieveByHeader extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");
			String organizationId = (String)incomingRequest.get("organizationId");

			BigDecimal bdh = new BigDecimal(icHeader) ;

	        String queryString = "from ApprovalLog as a where a.id.icHeader = ?" ;

			List list = dbs.query(queryString, bdh, Hibernate.BIG_DECIMAL) ;
			
			if (list != null) {
			    for (int i=0; i< list.size(); i++) {
			        ApprovalLog approvalLog = (ApprovalLog) list.get(i);
			        if (approvalLog.getApproverType().equals("P")) {
			            if (Utility.isEmpty(approvalLog.getPooldesc())) {
			                approvalLog.setApproverName("[" + approvalLog.getPoolid() + "]");
			            } else {
			                approvalLog.setApproverName(approvalLog.getPooldesc());
			            }
			        }
			        else {
			            String userName = UserManager.getInstance().getUser(organizationId, approvalLog.getComp_id().getUserId()).getDisplayName();
			            if (!Utility.isEmpty(approvalLog.getPoolid()) && !approvalLog.getPoolid().equals(approvalLog.getComp_id().getUserId())) {
			                if (Utility.isEmpty(approvalLog.getPooldesc())) {
			                    approvalLog.setApproverName(userName + " for [" + approvalLog.getPoolid() + "]");
			                } else {
			                    approvalLog.setApproverName(userName + " for " + approvalLog.getPooldesc());
			                }
			            } else {
			                approvalLog.setApproverName(userName);
			            }
			        }
			        list.set(i, approvalLog);
			    }
			}

			ret = list;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Routing List could not be retrieved.", e);
		}
		return ret ;
	}

	private String getPooldesc(Map incomingRequest, String poolId) {
	    String poolDesc = "";
	    
	    try {
	    	incomingRequest.put("AppPool_poolid", poolId);
	    	
	    	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
	    	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);
	    	
	    	poolDesc = appPool.getPooldesc();
	    } catch (Exception e) {
	        Log.error(this, e.getMessage());
	    } finally {
	        return poolDesc;
	    }
	}
}
