package com.tsa.puridiom.news.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class NewsSetupFromAdminInterface extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			BigDecimal newsGetIcNews = (BigDecimal)incomingRequest.get("newsGetIcNews");
			BigDecimal newsGetSequence = (BigDecimal)incomingRequest.get("newsGetSequence");

			String News_icNews = newsGetIcNews.toString();
			String News_sequence = newsGetSequence.toString();

			incomingRequest.put("News_icNews", News_icNews);
			incomingRequest.put("News_sequence", News_sequence);
		}

		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return null;
	}
}