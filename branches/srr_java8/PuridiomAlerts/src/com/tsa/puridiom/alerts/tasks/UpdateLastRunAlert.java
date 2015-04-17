package com.tsa.puridiom.alerts.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class UpdateLastRunAlert
{
	private Document xmlDocument = null;
	private String fileName = "";
	private String organizationId = "";


	public UpdateLastRunAlert(String _organizationId)
	{
		String _fileName = DictionaryManager.getInstance("host", organizationId).getProperty("alerts-path");
		this.fileName = _fileName;
		this.organizationId = _organizationId;
		this.xmlDocument = Utility.loadXml(this.fileName, this.organizationId);
	}

	public void setLastRun(String alertName)
	{
		List alerts =  this.xmlDocument.getRootElement().getChildren("alert");

		for(int i = 0; i < alerts.size(); i++)
        {
       	 	Element alertElement = (Element)alerts.get(i);
       	 	String name = alertElement.getChildText("name");
       	 	if(name.equalsIgnoreCase(alertName))
       	 	{
	       	 	Element lastRunEl = alertElement.getChild("last-run");
	            if(lastRunEl != null)
	            {
	            	CDATA cdata = new CDATA(Dates.today("yyyy/MM/dd", ""));
	 	           	List cdataL = new ArrayList();
	 	           	cdataL.add(cdata);
	 	           	lastRunEl.setContent(cdataL);
	 	          	this.output();
	            }
       	 	}
        }
	}

	private void output(FileOutputStream fos) throws IOException
    {
        XMLOutputter outputter = new XMLOutputter("", true);
        outputter.setExpandEmptyElements(true);
        outputter.setTextNormalize(false);
        outputter.setNewlines(false);

        outputter.output(this.xmlDocument, fos);
    }

    private void output()
    {
        try
        {
        	File f = Utility.getOidFile(this.fileName, this.organizationId);
            if(f.isFile())
            {
                FileOutputStream fos = new FileOutputStream(f);
                this.output(fos);
            }
        }
        catch(IOException ie)
        {
            Log.error(this, ie.toString());
        }
    }

}
