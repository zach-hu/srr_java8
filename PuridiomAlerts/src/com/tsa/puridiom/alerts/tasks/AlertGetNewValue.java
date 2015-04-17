package com.tsa.puridiom.alerts.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AlertGetNewValue extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		Map changeArg = new HashMap();
		String type = (String)incomingRequest.get("alerttype");
		String organizationId = (String)incomingRequest.get("organizationId");
		try
		{
			List alertList = (List) incomingRequest.get("alertList");
			List chkNameList = new ArrayList();
			List offsetNameList = new ArrayList();
			List operatorNameList = new ArrayList();
			List sendToList_Bchk = new ArrayList();
			List sendToList_Rchk = new ArrayList();
			List sendToList_Ochk = new ArrayList();
			List sendToList_Mchk = new ArrayList();
			String [] sendToList_Manual = (String []) incomingRequest.get("manualSendTo");


			if (alertList !=null)
			{
				for(int i = 0; i < alertList.size(); i++)
				{
					Alert alert = (Alert)alertList.get(i);
					String name_chk = (String) alert.getName();
					String name_offset = (String) alert.getName();
					String name_operator = (String) alert.getName();
					String name_sendTo_bchk = (String) alert.getName();
					String name_sendTo_rchk = (String) alert.getName();
					String name_sendTo_ochk = (String) alert.getName();
					String name_sendTo_mchk = (String) alert.getName();
					name_chk = (String) incomingRequest.get(alert.getName() + "_chk");
					name_offset = (String) incomingRequest.get(alert.getName() + "_offset");
					name_operator = (String) incomingRequest.get(alert.getName()+"_operator");
					name_sendTo_bchk = (String) incomingRequest.get(alert.getName()+"_buyer_chk");
					name_sendTo_rchk = (String) incomingRequest.get(alert.getName()+"_buyer_chk");
					name_sendTo_ochk = (String) incomingRequest.get(alert.getName()+"_buyer_chk");
					name_sendTo_mchk = (String) incomingRequest.get(alert.getName()+"_buyer_chk");
					chkNameList.add(name_chk);
					offsetNameList.add(name_offset);
					operatorNameList.add(name_operator);
					sendToList_Bchk.add(name_sendTo_bchk);
					sendToList_Rchk.add(name_sendTo_rchk);
					sendToList_Ochk.add(name_sendTo_ochk);
					sendToList_Mchk.add(name_sendTo_mchk);
					int x=0;
				}
				changeArg.put("chkNameList",chkNameList);
				changeArg.put("offsetNameList",offsetNameList);
				changeArg.put("sendToList_Bchk",sendToList_Bchk);
				changeArg.put("sendToList_Rchk",sendToList_Rchk);
				changeArg.put("sendToList_Ochk",sendToList_Ochk);

			}

			ret =  changeArg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Process Name was not found for alert type: " + type, e);
		}
		return ret;
	}


}
