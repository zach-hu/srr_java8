package com.tsa.puridiom.news.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.News;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class NewsDeleteList extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
				String organizationId = (String) incomingRequest.get("organizationId");
				String[] stringDeletedNews = ((String) incomingRequest.get("deletedList")).split(";");
	            String firtsDelete = stringDeletedNews[0];

					if (stringDeletedNews!=null && !HiltonUtility.isEmpty(firtsDelete))
					{
						for (int i = 0; i < stringDeletedNews.length; i++) {

						incomingRequest.put("ic_news", new BigDecimal(stringDeletedNews[i]));
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("news-delete.xml");

						process.executeProcess(incomingRequest);
						this.setStatus(process.getStatus());
						}
					}

			this.status = Status.SUCCEEDED;
			}
			catch (Exception e) {
				this.status = Status.FAILED;
				throw e;
			}
		return result;
	}
}
