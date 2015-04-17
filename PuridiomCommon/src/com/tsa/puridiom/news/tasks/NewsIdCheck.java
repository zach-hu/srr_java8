package com.tsa.puridiom.news.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class NewsIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		BigDecimal icNews = new BigDecimal( incomingRequest.get("ic_news").toString() );

		if(Utility.isObjectEmpty(icNews))
		{
				this.setStatus(Status.FAILED);
				throw new TsaException("News id is necessary to retrieve a News");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}