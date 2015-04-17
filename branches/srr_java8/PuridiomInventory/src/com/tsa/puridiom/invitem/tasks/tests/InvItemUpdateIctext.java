package com.tsa.puridiom.invitem.tasks.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.invitem.tasks.InvItemRetrieveBy;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class InvItemUpdateIctext
{
	public static void  main (String[] args) throws Exception
	{
		InvItemRetrieveBy invItemRetrieveBy = new InvItemRetrieveBy();
		if(args.length > 0){
			String oid = args[0];
			if(!HiltonUtility.isEmpty(oid)){
				DBSession dbs = new DBSession(oid);
				Map incomingRequest = new HashMap();
				incomingRequest.put("InvItem_icText", "0");
				incomingRequest.put("dbsession", dbs);
				incomingRequest.put("organizationId", oid);
				incomingRequest.put("userId", "SYSADM");
				List invItemList = (List)invItemRetrieveBy.executeTask(incomingRequest);
				System.out.println("Database Status: " + dbs.getStatus());
				if(invItemList != null){
					for (Iterator iterator = invItemList.iterator(); iterator.hasNext();) {
						InvItem invItem = (InvItem) iterator.next();
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
						PuridiomProcess process = processLoader.loadProcess("invitem-update.xml");
						Map updateParameters = new HashMap();
						UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
						Long id = ukg.getUniqueKey();
						updateParameters.put("InvItem_itemNumber", invItem.getItemNumber());
						updateParameters.put("InvItem_icText", id.toString());
						updateParameters.put("organizationId", oid);
						updateParameters.put("userId", "SYSADM");
						process.executeProcess(updateParameters);
					}
					System.out.println("Database Status: " + dbs.getStatus());
					System.out.println("Update InvItem_icText: " + invItemList.size() + " totals");
				} 
			} else {
				throw new Exception("OID is empty, please enter a oid");
			}
		} else {
			throw new Exception("Please enter a oid");
		}
	}

}
