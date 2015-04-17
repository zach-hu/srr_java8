package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.AppRule;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ApprovalRioBuildApproverList extends Task
{
	Map incomingRequest;
	List appLogList = new ArrayList();
	public Object executeTask(Object object) throws Exception
	{
		incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		BigDecimal amtToApprove = (BigDecimal) incomingRequest.get("amtToApprove") ;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession");

		List rqlList = (List)incomingRequest.get("requisitionLineList") ;
        String 	icHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
        String	itemOwner = "";
        
        try {
        	
			for (int i = 0; i < rqlList.size(); i++) {
				RequisitionLine rql = (RequisitionLine) rqlList.get(i);
				String	item = rql.getItemNumber() ;
				String 	catalog = rql.getCatalogId() ;
				String	source = rql.getItemSource() ;
				boolean		restricted = false;
				
				String	sql = null ;
				
				if (source.startsWith("IN") || source.startsWith("CA") || source.startsWith("ST")) {
					Object [] args = null;
					Type [] types = null;
					if (source.startsWith("IN")) {
						// Inventory Item
			            sql = "Select invItem.owner, invItem.restrictedItem from InvItem invItem where invItem.itemNumber = ?";
			            args = new Object[1];
			            types = new Type[1];
			            args[0] = item;
			            types[0] = Hibernate.STRING;
					} else {
						// Catalog Item
			            sql = "Select ct.owner, ct.itemRestricted from CatalogItem ct where ct.id.itemNumber = ? and ct.id.catalogId = ?";
			            args = new Object[2];
			            types = new Type[2];
			            args[0] = item;
			            types[0] = Hibernate.STRING;
			            args[1] = catalog;
			            types[1] = Hibernate.STRING;
					}
				
					List   itList = dbs.query(sql, args, types);
		            
		            if(itList.size() > 0) {
		                Object arrayTemp[] = (Object[])itList.get(0);
		                
		                String	owner = (String)arrayTemp[0];
		                String	restrictedItem  = (String)arrayTemp[1];
					
		                if (! HiltonUtility.isEmpty( restrictedItem ) && ! HiltonUtility.isEmpty(owner)) {
		                	if (restrictedItem.equalsIgnoreCase("Y")) {
		                		itemOwner = owner  ;
		                		restricted = true; 
//		                		break;
		                	}
		            	}
	            	}
				}
				
				if (restricted) {
					// Add another approver
					if(itemOwner.startsWith("@"))
		            {
		                String	poolId = itemOwner.substring(1);    // Hack @
		            	incomingRequest.put("AppPool_poolid",poolId) ;
		            	incomingRequest.put("AppPooluser_poolid",poolId) ;
		            	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
		            	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);
	
		            	String pFlag = "P" ;
		            	if (appPool != null) {
		            		this.setApprovalPoolUsers(icHeader, source, appPool, item);
						} 
		            }
		            else
		            {
		            	UserProfile pUser = UserManager.getInstance().getUser(organizationId,itemOwner);
						if (pUser.getStatus().equals("02")) {
							ApprovalLog appLog = new ApprovalLog();
				            ApprovalLogPK pk = new ApprovalLogPK();
				            pk.setIcHeader(new BigDecimal(icHeader));
				            pk.setIcLine(new BigDecimal(0)) ;
			                pk.setSequence(new BigDecimal(0)) ;
			                pk.setUserId(itemOwner) ;
			                appLog.setComp_id(pk);
			                appLog.setCallForward(itemOwner) ;
		   	                appLog.setApproverName(pUser.getDisplayName());
			   	            appLog.setRuleType("RIO");
			   	            appLog.setRuleOrder(new BigDecimal(PropertiesManager.getInstance(organizationId).getProperty("MISC", "RIOORDER", "30")));
			   	            appLog.setAmount(new BigDecimal(0)) ;
			            	appLog.setApproverAmount(new BigDecimal(0)) ;
			            	appLog.setApproverType("U") ;
		   	    	        appLog.setApproved("N") ;
		   	    	        appLog.setAuthorized("N") ;
		   	    	        appLog.setRuleAction("A") ;
		   	    	        appLog.setApproverSig("") ;
		   	    	        appLog.setApproverNotes("") ;
		   	    	        appLog.setPoolid(" ") ;
		   	    	        appLog.setPooldesc("") ;
		   	    	        appLog.setRuleSource(" ") ;
		  	    	        appLog.setUdfValues("[Restricted Item # " + item + " Approval.]") ;
		   	    	        appLog.setExcludeLess(new BigDecimal(0)) ;
		   	    	        appLogList.add(appLog) ;
		            	}
					}
				}
			}			
			this.status = Status.SUCCEEDED;	
				
        }
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return appLogList ;
	}
	
	private void setApprovalPoolUsers(String icHeader, String source, AppPool appPool, String item) throws Exception
	{
		AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
		List poolUsersList = (List) task.executeTask(this.incomingRequest);
		String	oid = (String) incomingRequest.get("organizationId");

		if ((poolUsersList != null) && (!poolUsersList.isEmpty()))
		{
			for (Iterator iterator = poolUsersList.iterator(); iterator.hasNext();)
			{
				AppPooluser poolUser = (AppPooluser) iterator.next();
				String userId = poolUser.getComp_id().getUserId();
				UserProfile pUser = UserManager.getInstance().getUser(oid, userId);

				if (pUser.getStatus().equals("02"))
				{
					ApprovalLog appLog = new ApprovalLog();
	                ApprovalLogPK pk = new ApprovalLogPK();
	                pk.setIcHeader(new BigDecimal(icHeader));
	                pk.setIcLine(new BigDecimal(0)) ;
	                pk.setSequence(new BigDecimal(0)) ;
	                pk.setUserId(userId) ;
	                appLog.setComp_id(pk);
	                appLog.setCallForward(userId) ;
   	                appLog.setApproverName(pUser.getDisplayName());
	   	            appLog.setRuleType("RIO");
	   	            appLog.setRuleOrder(new BigDecimal(PropertiesManager.getInstance(oid).getProperty("MISC", "RIOORDER", "30")));
	   	            appLog.setAmount(new BigDecimal(0)) ;
	            	appLog.setApproverAmount(new BigDecimal(0)) ;
	            	appLog.setApproverType("U") ;
   	    	        appLog.setApproved("N") ;
   	    	        appLog.setAuthorized("N") ;
   	    	        appLog.setRuleAction("A") ;
   	    	        appLog.setApproverSig("") ;
   	    	        appLog.setApproverNotes("") ;
   	    	        appLog.setPoolid(appPool.getPoolid()) ;
   	    	        appLog.setPooldesc("") ;
   	    	        appLog.setRuleSource(" ") ;
  	    	        appLog.setUdfValues("[Restricted Item # " + item + " Approval.]") ;
   	    	        appLog.setExcludeLess(new BigDecimal(0)) ;
   	    	        appLogList.add(appLog) ;
				}
			}
		}
	}


}