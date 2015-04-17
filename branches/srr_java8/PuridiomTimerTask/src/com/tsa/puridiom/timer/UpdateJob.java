/**
 *
 */
package com.tsa.puridiom.timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author JOHNNY
 *
 */
public class UpdateJob {

	private Document xmlDocument = null;

	private File scheduleFile = null;

	private String organizationId = "";

	public UpdateJob(String _organizationId)
	{
		String fileName = "schedule.xml";
		this.organizationId = _organizationId;

		try
		{
			System.out.println("loading schedule: " + fileName + " oid = " + this.getOrganizationId());
			Log.debug(this, fileName);

			this.scheduleFile = Utility.getOidFile(this.filepath(this.getOrganizationId()) + fileName, this.getOrganizationId());

			System.out.println("Schedule found: " + this.getScheduleFile().getPath());

			if (this.getScheduleFile().exists())
			{
				DOMBuilder docBuilder = new DOMBuilder();
				this.xmlDocument = docBuilder.build(this.getScheduleFile());
			}
			else
			{
				Log.error(this, "Schedule not found: " + this.getScheduleFile().getPath());
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public String filepath(String organizationId)
	{
		return DictionaryManager.getInstance("host", organizationId).getProperty("schedules");
	}

	public synchronized void setLastRun(int jobOrder) throws Exception
	{
		Element jobElement = (Element) this.getXmlDocument().getRootElement().getChildren("job").get(jobOrder - 1);
		Element lastRunElement = jobElement.getChild("last-run");

		CDATA cdata;
		List cdataList;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss z");
		String hour = " 00:00:00";
		String lastRun = "";
		String mask = "yyyy/MM/dd";

		if (lastRunElement != null)
		{
			lastRun = lastRunElement.getTextNormalize();
			if(HiltonUtility.isEmpty(lastRun))
			{
				return;
			}

			if (lastRun.length() > mask.length()) {
				hour = lastRunElement.getTextNormalize().substring(mask.length(), lastRun.length());
			}
			else
			{
				hour = sdf.format(Calendar.getInstance().getTime());
			}

			cdata = new CDATA(new SimpleDateFormat("yyyy/MM/dd").format(Dates.add(calendar.getTime(), 0)) + " "+ sdf.format(sdf.parse(hour.trim())));
			cdataList = new ArrayList();
			cdataList.add(cdata);
			lastRunElement.setContent(cdataList);
		}
		else
		{
			hour = sdf.format(Calendar.getInstance().getTime());
			cdata = new CDATA(new SimpleDateFormat("yyyy/MM/dd").format(Dates.add(calendar.getTime(), 0)) + " "+ sdf.format(sdf.parse(hour.trim())));
			cdataList = new ArrayList();
			cdataList.add(cdata);
			lastRunElement = new Element("last-run");
			lastRunElement.setContent(cdataList);

			jobElement.addContent(lastRunElement);
		}
	}

	public synchronized void setScheduleLastRun()
	{
		List elements = this.getXmlDocument().getRootElement().getChildren("last-run");
		Element lastRunElement = null;
		if(elements.size() > 0)
		{
			lastRunElement = (Element) elements.get(0);
		}
		else {
			lastRunElement = new Element("last-run");
			elements.add(lastRunElement);
		}

		CDATA cdata = null;
		List cdataList;
		Calendar calendar = Calendar.getInstance();

		if (lastRunElement != null) {
			try
			{
				cdata = new CDATA(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z").format(Dates.add(calendar.getTime(), 0)));
			} catch(Exception exception)
			{
				Log.error(this, "It is not possible parse the specified hour in: sdf.parse(hour.trim())");
			}
			cdataList = new ArrayList();
			cdataList.add(cdata);
			lastRunElement.setContent(cdataList);
		}
	}

	public String getScheduleLastRun()
	{
		String lastRunElementText = "";

		if(this.getXmlDocument().getRootElement().getChild("last-run") != null){
			Element lastRunElement = (Element) this.getXmlDocument().getRootElement().getChild("last-run");
			lastRunElementText = lastRunElement.getTextNormalize();
		}

		return lastRunElementText;
	}

	public synchronized void setRunning(String status)
	{
		List elements = this.getXmlDocument().getRootElement().getChildren("running");
		Element runningElement = null;

		if(elements.size() > 0)
		{
			runningElement = (Element) elements.get(0);
		} else
		{
			runningElement = new Element("running");
			elements.add(runningElement);
		}
		runningElement.setText(status);
	}

	public String getRunning()
	{
		List elements = this.getXmlDocument().getRootElement().getChildren("running");
		Element runningElement = null;
		String value = "";

		if(elements.size() > 0)
		{
			runningElement = (Element) elements.get(0);
			value = runningElement.getText();
		}

		return value;
	}

	public synchronized void output() {
		try {
			XMLOutputter outputter = new XMLOutputter("", true);
			outputter.setExpandEmptyElements(true);
			outputter.setTextNormalize(false);
			outputter.setNewlines(false);

			outputter.output(this.getXmlDocument(), new FileOutputStream(this.getScheduleFile()));
//			outputter.output(this.getXmlDocument(), System.out);

		} catch (IOException ie) {
			Log.error(this, ie.toString());
		}
	}

	/**
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @return the scheduleFile
	 */
	public File getScheduleFile() {
		return scheduleFile;
	}

	/**
	 * @param scheduleFile the scheduleFile to set
	 */
	public void setScheduleFile(File scheduleFile) {
		this.scheduleFile = scheduleFile;
	}

	/**
	 * @return the xmlDocument
	 */
	public Document getXmlDocument() {
		return xmlDocument;
	}

	/**
	 * @param xmlDocument the xmlDocument to set
	 */
	public void setXmlDocument(Document xmlDocument) {
		this.xmlDocument = xmlDocument;
	}

}
