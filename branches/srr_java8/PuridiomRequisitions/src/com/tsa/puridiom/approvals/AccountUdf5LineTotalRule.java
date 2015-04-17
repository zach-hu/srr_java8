package com.tsa.puridiom.approvals;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountUdf5LineTotalRule extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List rqlList = (List) incomingRequest.get("requisitionLineList");
			String extraSubjectFYI = HiltonUtility.ckNull((String)incomingRequest.get("extraSubjectFYI"));

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			for (Iterator it = rqlList.iterator(); it.hasNext();)
			{
				RequisitionLine rql = (RequisitionLine) it.next();

				String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";
				List accountList = dbs.query(queryString, new Object[] { rql.getIcReqHeader(), rql.getIcReqLine() },
						new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

				if ((accountList != null && accountList.size() > 0))
				{
					for (Iterator it2 = accountList.iterator(); it2.hasNext();)
					{
						Account account = (Account) it2.next();
						String fld5 = account.getFld5(); // HOYA Ringisho

						if ( HiltonUtility.isEmpty(fld5) && ((rql.getLineTotal().compareTo(new BigDecimal("1000"))) >= 0) )
						{
							incomingRequest.put("AccountUdf5LineTotalRule", "passed");
							extraSubjectFYI += " - The Extended Cost of a Line Item is > $1,000";
							incomingRequest.put("extraSubjectFYI", extraSubjectFYI);
						}
						if ( !HiltonUtility.isEmpty(fld5) )
						{
							incomingRequest.put("AccountUdf5LineNotEmptyRule", "passed");
						}
					}
				}
			}

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AccountUdf5LineTotalRule", e);
		}
		return result;
	}
}