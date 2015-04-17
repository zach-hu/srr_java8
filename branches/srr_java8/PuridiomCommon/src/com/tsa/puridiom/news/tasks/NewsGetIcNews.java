package com.tsa.puridiom.news.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class NewsGetIcNews extends Task {
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		BigDecimal newIcNews = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			String queryString = "select max(news.icNews) from News as news";
			List resultList = dbs
					.query(queryString, new Object[] {}, new Type[] {});

			newIcNews = (BigDecimal) resultList.get(0);
			if(newIcNews==null)
			{
				newIcNews = new BigDecimal(0);
			}
			else
			{
				newIcNews = newIcNews.add(new BigDecimal(1));
			}
			this.setStatus(dbs.getStatus());
		}

		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return newIcNews;

	}
}
