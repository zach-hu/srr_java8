package com.tsa.puridiom.news.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class NewsGetSequence extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		BigDecimal newSequence = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			String queryString = "select max(news.sequence) from News as news";
			List resultList = dbs
					.query(queryString, new Object[] {}, new Type[] {});

			newSequence = (BigDecimal) resultList.get(0);
			if(newSequence==null)
			{
				newSequence = new BigDecimal(0);
			}
			else
			{
				newSequence = newSequence.add(new BigDecimal(1));
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return newSequence;
	}
}
