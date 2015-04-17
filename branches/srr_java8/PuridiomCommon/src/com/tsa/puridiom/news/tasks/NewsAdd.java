package com.tsa.puridiom.news.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class NewsAdd extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			News news = (News) incomingRequest.get("news");
			if (news == null) {
				throw new Exception("News was not found.");
			}

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			dbs.add(news);

			result = news;
			this.setStatus(dbs.getStatus());
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
