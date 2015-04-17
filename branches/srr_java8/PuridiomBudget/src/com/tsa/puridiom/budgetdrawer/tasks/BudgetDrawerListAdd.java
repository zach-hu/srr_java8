package com.tsa.puridiom.budgetdrawer.tasks;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.BudgetDrawer;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class BudgetDrawerListAdd extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			BudgetCenter budgetCenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			String authority[] = (String[]) incomingRequest.get("BudgetDrawer_authority");
			String authType[] = (String[]) incomingRequest.get("BudgetDrawer_type");
			String status[] = (String[]) incomingRequest.get("BudgetDrawer_status");
			String budgetId = (String) incomingRequest.get("BudgetCenter_budgetId");
			String deletedAuthTypes = (String) incomingRequest.get("Deleted_authTypes");
			String deletedAuthorities = (String) incomingRequest.get("Deleted_authorities");
			int qtyExisted = Integer.valueOf((String) incomingRequest.get("qtyAuthInitial")).intValue();

			for(int i=0; i<authority.length ;i++) {
				if (!Utility.isEmpty(authority[i])) {
					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("organizationId", organizationId);
					newIncomingRequest.put("userId", userId);
					newIncomingRequest.put("BudgetDrawer_budgetId", budgetId);
					newIncomingRequest.put("BudgetDrawer_authType", authType[i].trim());
					newIncomingRequest.put("BudgetDrawer_authority", authority[i].trim());
					newIncomingRequest.put("BudgetDrawer_owner", userId);
					newIncomingRequest.put("BudgetDrawer_status", status[i]);
					newIncomingRequest.put("budgetCenter", budgetCenter);
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("budgetdrawer-add.xml");

					if (i < qtyExisted)
						process = processLoader.loadProcess("budgetdrawer-update.xml");
					process.executeProcess(newIncomingRequest);
				}
			}

			String[] authTypes = deletedAuthTypes.split(",");
			String[] authorities = deletedAuthorities.split(",");

			for (int ix = 0; ix < authorities.length; ix++) {
				if (authTypes[ix] != null && authorities[ix] != null) {
					if (authTypes[ix].length() > 0)	{
						String	authTp = authTypes[ix];
						if (authTp.length() > 0)  {
							/*Map newIncomingRequest = new HashMap();
							newIncomingRequest.put("BudgetDrawer_budgetId", budgetId);
							newIncomingRequest.put("BudgetDrawer_authType", authTp);
							newIncomingRequest.put("BudgetDrawer_authority", authorities[ix].trim());
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
							PuridiomProcess process = processLoader.loadProcess("budgetdrawer-delete.xml");
							process.executeProcess(newIncomingRequest);*/
							List budgetDrawerList = (List)incomingRequest.get("budgetDrawerList");
							for(int i=0; i<budgetDrawerList.size(); i++)
							{
								BudgetDrawer budgetDrawer = (BudgetDrawer)budgetDrawerList.get(i);
								if(budgetDrawer.getComp_id().getAuthType().equalsIgnoreCase(authTp) &&
										budgetDrawer.getComp_id().getAuthority().equalsIgnoreCase(authorities[ix]))
								{
							        String queryString = "delete from BUDGET_DRAWER where BUDGET_ID=? and AUTH_TYPE= ? and AUTHORITY= ?";
							        Object [] args = new Object[3];
							        Integer [] types = new Integer[3];
							        args[0] = budgetDrawer.getComp_id().getBudgetId();
							        types[0] = Types.VARCHAR;
							        args[1] = budgetDrawer.getComp_id().getAuthType();
							        types[1] = Types.VARCHAR;
							        args[2] = budgetDrawer.getComp_id().getAuthority();
							        types[2] = Types.VARCHAR;
									dbs.sqlUpdate(queryString, args, types);

									break;
								}
							}
						}
					}
				}
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}