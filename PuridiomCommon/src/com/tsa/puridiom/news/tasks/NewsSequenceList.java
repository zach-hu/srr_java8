package com.tsa.puridiom.news.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class NewsSequenceList extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		List newsOrdered = new ArrayList();
		List newsOrderedFinal = new ArrayList();
		try {
			String newItemList = (String) incomingRequest.get("itemList");
			String[] newsOrder = newItemList.replaceAll("news_", "").split(";");
			List newsList = (List) incomingRequest.get("newsList");
			Map icPosNew = new HashMap();

			for (int i = 0; i < newsOrder.length; i++) {
				newsOrdered.add(newsList.get(Integer.parseInt(newsOrder[i])));
			}

			for (int w = 0; w < newsOrdered.size(); w++) {
				 News news = (News) newsOrdered.get(w);
				 icPosNew.put(news.getIcNews().toString(),new Integer(w));
			}
			for (int m = 0; m < newsList.size(); m++) {
				 News news2 = (News) newsList.get(m);
				 String icNews2 = news2.getIcNews().toString();
				 newsOrderedFinal.add(icPosNew.get(icNews2));
			}
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return newsOrderedFinal;
	}
}
