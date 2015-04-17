package com.tsa.puridiom.news.tasks;

import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsUpdateFromAdminInterface extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
			Map incomingRequest = (Map)object;
			Object result = null;

			try
			{
				String organizationId = (String)incomingRequest.get("organizationId");
				List newsOld = (List)incomingRequest.get("newsList");
				List newsSequenceList = (List)incomingRequest.get("newsSequenceList");
				int	newsCount = newsOld.size();

				for (int i=0; i < newsCount; i++) {
			      News news = (News) newsOld.get(i);

			      if (newsSequenceList.get(i)== null)
			      {
			    	  newsSequenceList.set(i, new Integer(-1));
			      }
			      Integer newPosInteger = (Integer)(newsSequenceList.get(i));
			      int newPos = newPosInteger.intValue();
			      BigDecimal newSequence = new BigDecimal(newPos);

			      String icNews = news.getIcNews().toString();
			      String index = String.valueOf(i);
			      String incomingStringText = "whatsNewText_"+icNews+"_"+index;
			      String incomingStringFont = "whatsNewFont_"+icNews+"_"+index;
			      String incomingStringLink = "whatsNewLink_"+icNews+"_"+index;
			      String incomingStringImage = "whatsNewImage_"+icNews+"_"+index;
			      String incomingStringAltTag = "whatsNewAltTag_"+icNews+"_"+index;

			      String newsText= (String)incomingRequest.get(incomingStringText);
			      String newsFont= (String)incomingRequest.get(incomingStringFont);
			      String newsLink= (String)incomingRequest.get(incomingStringLink);
			      String newsImage= (String)incomingRequest.get(incomingStringImage);
			      String newsAltTag= (String)incomingRequest.get(incomingStringAltTag);
			      //Falta Actualizar el Sequence
			      news.setNewsText(newsText);
			      news.setNewsFont(newsFont);
			      news.setNewsLink(newsLink);
			      news.setNewsImage(newsImage);
			      news.setNewsAltTag(newsAltTag);

			      news.setSequence(newSequence);

			      PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				  PuridiomProcess process = processLoader.loadProcess("news-only-update.xml");
				  Map newsMap = new HashMap();
				  newsMap.put("organizationId", organizationId);
				  newsMap.put("news", news);
				  process.executeProcess(newsMap);
				  this.setStatus(process.getStatus());
			    }

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
