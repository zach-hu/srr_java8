package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.account.tasks.AccountRetrieveBy;
import com.tsa.puridiom.account.tasks.AccountRetrieveByLine;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.XrefCombo;
import com.tsa.puridiom.xrefcombo.tasks.XrefComboRetrieveBy;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;

public class RequisitionLineUpdateAccountFromCommodity extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			String originalCommodityCode = HiltonUtility.ckNull((String)incomingRequest.get("originalCommodityCode"));
			String newCommodityCode = "";

			List accountCommodityList = new ArrayList();
			List accountList = new ArrayList();

			if (requisitionLine != null)
			{
				String commodityCode = requisitionLine.getCommodityCode();
				String icAccount = CommodityManager.getInstance().getCommodityIcAccount(organizationId, commodityCode).toString();

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", dbs);
				newIncomingRequest.put("Account_icHeader", icAccount);
				newIncomingRequest.put("Account_icLine", "0");

				AccountRetrieveByLine accountRetrieveByLine = new AccountRetrieveByLine();
				accountCommodityList = (List)accountRetrieveByLine.executeTask(newIncomingRequest);

				Map accountIncomingRequest = new HashMap();
				accountIncomingRequest.put("dbsession", dbs);
				accountIncomingRequest.put("Account_icHeader", requisitionLine.getIcReqHeader().toString());
				if (requisitionLine.getReqType().compareTo(RequisitionType.ADMIN_CHECK_REQUEST) != 0)
				{
					accountIncomingRequest.put("Account_icLine", requisitionLine.getIcReqLine().toString());
				}
				AccountRetrieveBy accountRetrieveBy = new AccountRetrieveBy();
				accountList = (List)accountRetrieveBy.executeTask(accountIncomingRequest);

				newCommodityCode = requisitionLine.getCommodityCode();
			}

			if (accountList != null && accountList.size() > 0)
			{
				for (int i = 0; i < accountList.size(); i++)
				{
					Account account = (Account)accountList.get(i);
					String fld2 = "";

					if (accountCommodityList != null && accountCommodityList.size() > 0)
					{
						for (int j = 0; j < accountCommodityList.size(); j++)
						{
							Account accountCommodity = (Account)accountCommodityList.get(j);
							if (account.getFld1().equalsIgnoreCase(accountCommodity.getFld1()))
							{
								fld2 = accountCommodity.getFld2();
							}
						}
					}

					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("dbsession", dbs);
					newIncomingRequest.put("XrefCombo_code1", account.getFld1());
					newIncomingRequest.put("XrefCombo_code2", fld2);
					newIncomingRequest.put("XrefCombo_code3", account.getFld3());

					XrefComboRetrieveBy xrefComboRetrieveBy = new XrefComboRetrieveBy();
					List xrefComboList = (List)xrefComboRetrieveBy.executeTask(newIncomingRequest);

					String fld4 = "";
					if (xrefComboList != null && xrefComboList.size() > 0)
					{
						XrefCombo xrefCombo = (XrefCombo)xrefComboList.get(0);
						fld4 = xrefCombo.getCode4();
					}

					account.setFld2(fld2);
					if (!HiltonUtility.isEmpty(originalCommodityCode) && !originalCommodityCode.equalsIgnoreCase(newCommodityCode))
					{
						account.setFld4(fld4);
					}

					dbs.update(account);
				}
			}

	        this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
