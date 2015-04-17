package com.tsa.puridiom.news.tasks;
import com.tsa.puridiom.address.tasks.AddressSetValuesPK;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.Map;

public class NewsDeleteById extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		News news = (News)incomingRequest.get("news");
		if(news == null)
		{
			news = new News();
		}
		news.setIcNews((BigDecimal)incomingRequest.get("ic_news"));
		dbs.delete(news);
		this.setStatus(dbs.getStatus()) ;
		return news;
	}

}