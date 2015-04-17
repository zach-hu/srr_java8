/*
 * Created on Jun 2, 2003
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.tsa.puridiom.steporder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.XmlFile;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProcessSteps
{
	private String xmlFile;
	private XmlFile xFile;
	private ArrayList steps = new ArrayList();
	public String sClassName = "ProcessSteps";
	private String organizationId = "puridiom";

	public ProcessSteps(String asXml, String organizationId)
	{
	    this.xmlFile = asXml;
	    this.setOrganizationId(organizationId);

		String path = DictionaryManager.getInstance("host", this.organizationId).getProperty("steps-xml-path", "");
	    File f = Utility.getOidFile(path + this.xmlFile + ".xml", organizationId);
	    xFile = new XmlFile(f.toString());

		this.getOrder();

	}

	public ProcessSteps(String asXml)
	{
	    this(asXml, "puridiom");
	}

	public int getSize()
	{
		return this.steps.size();
	}
	/**
	 * Method getOrder
	 * main method to be called will create the order.
	 * @return int
	 */
	private int getOrder()
	{
		int iOrd = -1;
		List xmlSteps = xFile.getRootChildren("step");

		for(int curStep = 0; curStep < xmlSteps.size(); curStep++)
		{
			setStep(xmlSteps.get(curStep));
		}

		return iOrd;
	}

	/**
	 * @param object
	 */
	private void setStep(Object element)
	{
		Element xStep = (Element)element;
		try
		{
			StepOrder sOrd = new StepOrder();
			sOrd.setProcess(xStep.getAttributeValue("process"));
			sOrd.setVisible(xStep.getAttributeValue("visible"));
			//sOrd.setOrder(xStep.getAttribute("order").getIntValue());
			sOrd.setMethod(xStep.getChildText("method"));
			sOrd.setLabel(xStep.getChildText("label"));
			sOrd.setLink(xStep.getChildText("link"));
			if(sOrd.getVisible().equals("1"))
			{
				//int index = sOrd.getOrder();
				this.steps.add(sOrd);
			}
		}
		catch(Exception e)
		{
			Log.error(this, e.getMessage());
		}
	}
	public String getLabel(int aIndex)
	{
		return this.getStep(aIndex).getLabel();
	}

	public String getLink(int aIndex)
	{
		return this.getStep(aIndex).getLink();
	}

	private StepOrder getStep(int aIndex)
	{
		return (StepOrder)this.steps.get(aIndex);
	}

	public String getProcess(int aIndex)
	{
		return this.getStep(aIndex).getProcess();
	}

	public String getVisible(int aIndex)
	{
		return this.getStep(aIndex).getVisible();
	}

	public String getMethod(int aIndex)
	{
		return this.getStep(aIndex).getMethod();
	}

	public int getOrder(int aIndex)
	{
		return this.getStep(aIndex).getOrder();
	}

	public String toString()
	{
		return this.sClassName + String.valueOf(this.steps.size());
	}
    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return organizationId;
    }
    /**
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
}
