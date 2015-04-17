package com.tsa.puridiom.alert.tasks;

import com.tsa.puridiom.entity.AlertConfig;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AlertUpdateList extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		//value to be used in the query
		BigDecimal var1 = new BigDecimal (1);
		//value to be used to add one to var1
		BigDecimal addition = new BigDecimal (1);

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			//If offsetdays input fails, error = true
			boolean error = false;

			for(int i=1; i < 8; i++)
			{
				String queryString = "from AlertConfig as AlertConfig where icAlert = ? ";
				List resultList = dbs.query(queryString, new Object[] {var1} , new Type[] {Hibernate.BIG_DECIMAL});
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus());

				Object obj = resultList.get(0);
				AlertConfig alertConfig = (AlertConfig) obj;

				// Update Enabled
				String enabled = alertConfig.getEnabled();
				String	EC = "EC" + i;
				String EC1 = (String) incomingRequest.get(EC);
				if (enabled.equals(compare(EC1))){}
				else
					alertConfig.setEnabled(compare(EC1));

				// Update Buyer
				String buyer = alertConfig.getBuyer();
				String	BC = "BC" + i;
				String	BC1 = (String) incomingRequest.get(BC);
				if (buyer.equals(compare(BC1))){}
				else
					alertConfig.setBuyer(compare(BC1));

				// Update Owner
				String owner = alertConfig.getOwner();
				String	OC = "OC" + i;
				String	OC1 = (String) incomingRequest.get(OC);
				if (owner.equals(compare(OC1))){}
				else
					alertConfig.setOwner(compare(OC1));

				// Update Req
				String req = alertConfig.getReq();
				String	RC = "RC" + i;
				String	RC1 = (String) incomingRequest.get(RC);
				if (req.equals(compare(RC1))){}
				else
					alertConfig.setReq(compare(RC1));

				// Update Approver
				String approver = alertConfig.getApprover();
				String	AC = "AC" + i;
				String	AC1 = (String) incomingRequest.get(AC);
				if (approver.equals(compare(AC1))){}
				else
					alertConfig.setApprover(compare(AC1));

				// Update Offset Days
				BigDecimal offset = alertConfig.getOffsetDays();
				String	ODT = "ODT" + i;
				String	ODTString = (String) incomingRequest.get(ODT);

				try {
				BigDecimal ODT1 = new BigDecimal(ODTString);
				if (offset.equals(ODT1)){}
				else
					alertConfig.setOffsetDays(ODT1);
				dbs.update(alertConfig);

				}
				catch (Exception e){
					error = true;
				}

				var1 = var1.add(addition);
			}//end for loop

			//place ODTerror into the Map
			incomingRequest.put("ODTerror", error);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		this.setStatus(Status.SUCCEEDED) ;

		return result;
	}

	private String compare(String s){
		if (s == null)
			s = "n";
		else if (s.equals("on"))
			s = "y";
		else
			s = "n";
		return s;
	}

}