/*
 * Created on Feb. 16, 2005
 */
package com.tsa.puridiom.account.tasks;

import java.util.*;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;

/**
 * @author Administrator
 */
public class ItemAccountAdd extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("accountList")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("item-account-add.xml");
				Object lineItem = null;

				List accountList = (List) incomingRequest.get("accountList");
				String type = "";

				if (accountList.size() > 0) {
					Set keySet = incomingRequest.keySet();
					String formtype = (String) incomingRequest.get("formtype");
					if (formtype.equalsIgnoreCase("REQ"))
					{
						incomingRequest.put("RequisitionLine_icAccount",	incomingRequest.get("Account_icLine")) ;
						lineItem = incomingRequest.get("requisitionLine");
					}
					else if (formtype.equalsIgnoreCase("PO"))
					{
						incomingRequest.put("PoLine_icAccount",	incomingRequest.get("Account_icLine")) ;
						lineItem = incomingRequest.get("poLine");
					}
					else if (formtype.equalsIgnoreCase("DSB"))
					{
						incomingRequest.put("DisbLine_icAccount",	incomingRequest.get("Account_icLine")) ;
					}

					for (int i = 0; i < accountList.size(); i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("Account_icHeader",incomingRequest.get("Account_icHeader")) ;
						updateParameters.put("Account_icLine",incomingRequest.get("Account_icLine")) ;
						updateParameters.put("userNameUdf1",incomingRequest.get("userNameUdf1")) ;
						updateParameters.put("userNameUdf2",incomingRequest.get("userNameUdf2")) ;
						updateParameters.put("userNameUdf3",incomingRequest.get("userNameUdf3")) ;
						updateParameters.put("userNameUdf4",incomingRequest.get("userNameUdf4")) ;
						updateParameters.put("userNameUdf5",incomingRequest.get("userNameUdf5")) ;
						updateParameters.put("userNameUdf6",incomingRequest.get("userNameUdf6")) ;
						updateParameters.put("userNameUdf7",incomingRequest.get("userNameUdf7")) ;
						updateParameters.put("Account_fld1",incomingRequest.get("Account_fld1")) ;
						updateParameters.put("Account_fld2",incomingRequest.get("Account_fld2")) ;
						updateParameters.put("Account_fld3",incomingRequest.get("Account_fld3")) ;
						updateParameters.put("Account_fld4",incomingRequest.get("Account_fld4")) ;
						updateParameters.put("Account_fld5",incomingRequest.get("Account_fld5")) ;
						updateParameters.put("Account_fld6",incomingRequest.get("Account_fld6")) ;
						updateParameters.put("Account_fld7",incomingRequest.get("Account_fld7")) ;
						updateParameters.put("lineItem", lineItem);

						Account account = (Account) accountList.get(i);
						updateParameters.put("stdAccount", account);

						if (formtype.equalsIgnoreCase("REQ"))
						{
							type = "RQL";
						}
						else if (formtype.equalsIgnoreCase("PO"))
						{
							type = "POL";
						}
						else if (formtype.equalsIgnoreCase("DSB"))
						{
							type = "DBL";
						}

						updateParameters.put("Account_accountType", type);

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("Account_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}

						process.executeProcess(updateParameters);
					}

					incomingRequest.put("Account_accountType", type);
				}
			}
			else {
				throw new Exception("The value for Account_icHeader was not found.");
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
