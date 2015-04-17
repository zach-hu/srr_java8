package com.tsa.puridiom.news.tasks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class NewsSetValues extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
			Map incomingRequest = (Map)object;
			Object result = null;

			try
			{
				News news = (News) incomingRequest.get("news");
				if (news == null)
				{
					news = new News();
				}

				if (incomingRequest.containsKey("News_icNews"))
				{
					String icNewsString = (String) incomingRequest.get("News_icNews");
					if (Utility.isEmpty(icNewsString))
					{
						icNewsString = "0";
					}
					BigDecimal icNews = new BigDecimal ( icNewsString );
					news.setIcNews(icNews);
				} else {
					UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
					news.setIcNews(new BigDecimal(ukg.getUniqueKey().toString()));
				}
				if (incomingRequest.containsKey("News_sequence"))
				{
					String newsSequenceString = (String ) incomingRequest.get("News_sequence");
					if (Utility.isEmpty(newsSequenceString))
					{
						newsSequenceString = "0";
					}
					BigDecimal newsSequence = new BigDecimal ( newsSequenceString );
					news.setSequence(newsSequence);
				}
				if (incomingRequest.containsKey("News_newsText"))
				{
					String newsText = (String ) incomingRequest.get("News_newsText");
					news.setNewsText(newsText);
				}

				if (incomingRequest.containsKey("News_newsFont"))
				{
					String newsFont = (String ) incomingRequest.get("News_newsFont");
					news.setNewsFont(newsFont);
				}

				if (incomingRequest.containsKey("News_newsLink"))
				{
					String newsLink = (String ) incomingRequest.get("News_newsLink");
					news.setNewsLink(newsLink);
				}

				if (incomingRequest.containsKey("News_newsImage"))
				{
					String newsImage = (String ) incomingRequest.get("News_newsImage");
					news.setNewsImage(newsImage);
				}

				if (incomingRequest.containsKey("News_newsAltTag"))
				{
					String newsAltTag = (String ) incomingRequest.get("News_newsAltTag");
					news.setNewsAltTag(newsAltTag);
				}

				result = news;
				this.status = Status.SUCCEEDED;
			}
			catch (Exception e)
			{
				this.status = Status.FAILED;
				throw e;
			}
			return result;
		}

}
