package com.tsa.puridiom.timer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class LoadSchedule {
	private String organizationId;

	public List loadMe(String organizationId) {
		return this.loadMe("schedule.xml", organizationId);
	}

	public String filepath(String organizationId) {
		return DictionaryManager.getInstance("host", organizationId)
				.getProperty("schedules");
	}

	public List loadMe(String fileName, String organizationId) {
		this.setOrganizationId(organizationId);
		List tasks = new ArrayList();
		try {
			System.out.println("loading schedule: " + fileName + " oid = "
					+ organizationId);
			Log.debug(this, fileName);
			File f = Utility.getOidFile(this.filepath(organizationId)
					+ fileName, this.getOrganizationId());
			System.out.println("Schedule found: " + f.getPath());
			if (f.exists()) {
				DOMBuilder docBuilder = new DOMBuilder();
				Document document = docBuilder.build(f);
				tasks = populateScheduleFromXML(document);
			} else {
				Log.error(this, "Schedule not found: " + f.getPath());
				// throw new Exception("Schedule file not found for: " +
				// fileName);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// system.out.println(exception.toString());
		}
		return tasks;
	}

	private List populateScheduleFromXML(Document document) {
		List taskElements = document.getRootElement().getChildren("job");
		List tasks = new ArrayList();

		int order = 0;

		for (int i = 0; i < taskElements.size(); i++) {
			Element jobElement = (Element) taskElements.get(i);

			order++;

			if (jobElement.getChild("enabled") != null) {
				if (jobElement.getChild("enabled").getText().trim().equals(
						"false")) {
					continue;
				}
			}

			Job job = new Job();
			job.setOrganizationId(this.getOrganizationId());

			job.setClassName(jobElement.getChildText("class"));
			System.out.println("class: " + job.getClassName());
			job.setSchedule(jobElement.getChildText("time"));

			Element lastRunElement = jobElement.getChild("last-run");

			if (lastRunElement != null) {
				job.setLastRun(lastRunElement.getText().trim());
			}

			job.setOrder(order);

			tasks.add(job);
		}
		return tasks;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
