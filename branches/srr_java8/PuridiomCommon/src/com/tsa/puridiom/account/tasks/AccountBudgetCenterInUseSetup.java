package com.tsa.puridiom.account.tasks;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountBudgetCenterInUseSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List newAccountList = new ArrayList();

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");

			BudgetCenter budgetCenter = (BudgetCenter) incomingRequest.get("budgetCenter");
			List accountList = (List) incomingRequest.get("accountList");

			if (accountList != null && accountList.size() > 0)
			{
				for (int i=0; i<accountList.size(); i++)
				{
					Account account = (Account)accountList.get(i);
					if (account.getAccountType().equals("RQL"))
					{
						HashMap processParameters = new HashMap();
						processParameters.put("organizationId", organizationId);
						processParameters.put("RequisitionLine_icReqLine", account.getComp_id().getIcLine().toString());

						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-by-id.xml");
						process.executeProcess(processParameters);

						RequisitionLine rql = (RequisitionLine) processParameters.get("requisitionLine");
						if (rql != null && (rql.getCommodityCode().equals(budgetCenter.getBudget5()) || budgetCenter.getBudget5().equals("*")))
						{
							newAccountList.add(account);
							this.setStatus(Status.SUCCEEDED);
							return newAccountList;
						}
					}
					if (account.getAccountType().equals("POL"))
					{
						HashMap processParameters = new HashMap();
						processParameters.put("organizationId", organizationId);
						processParameters.put("PoLine_icPoLine", account.getComp_id().getIcLine().toString());

						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("poline-retrieve-by-id.xml");
						process.executeProcess(processParameters);

						PoLine pol = (PoLine) processParameters.get("poLine");
						if (pol != null && (pol.getCommodity().equals(budgetCenter.getBudget5()) || budgetCenter.getBudget5().equals("*")))
						{
							newAccountList.add(account);
							this.setStatus(Status.SUCCEEDED);
							return newAccountList;
						}
					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return newAccountList;
	}
}