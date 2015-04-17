/*
 * Created on Sep 30, 2003
 */
package com.tsa.puridiom.labels;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

import java.util.*;

/**
 * @author renzo
 */
public class LabelsManager
{
	/*
	 * HashMap<String(oid), HashMap<String(labelCode), List(Labels(class))>)
	 */
	private HashMap labelsMap = new HashMap();
	private static LabelsManager instance = null;

	public static LabelsManager getInstance()
	{
		if (LabelsManager.instance == null)
		{
			LabelsManager.instance = new LabelsManager();
		}
		return LabelsManager.instance;
	}

	/**
	 * @param labelsArguments
	 * language: Label Language- default to "en"
	 * organizationId: default to hilton
	 * labelCode: Label to look for
	 * module: req/po/rfq/empty
	 * moduleType: R/P/...
	 *
	 * @return
	 */
	public Labels getLabel(Map labelsArguments)
	{
		String language = (String)labelsArguments.get("language");
		if(HiltonUtility.isEmpty(language)){		language = "en";	}
		String oid = (String)labelsArguments.get("organizationId");
		if(HiltonUtility.isEmpty(oid)){		oid = "hilton";	}
		String labelCode = (String)labelsArguments.get("labelCode");
		if(HiltonUtility.isEmpty(labelCode)){		return new Labels();		}
		List labelsList = this.checkLabelsCache(oid, language);

		String moduleType = (String)labelsArguments.get("moduleType");
		String module = (String)labelsArguments.get("module");
		Labels label = this.findLabel(moduleType, module, language, labelsList);

		return label;
	}
	private Labels findLabel(String moduleType, String module, String language, List labelsList)
	{
		Labels label = null;
		if(!HiltonUtility.isEmpty(moduleType))
		{
			label = this.findByModuleType(labelsList, moduleType, module, language);
			if(label == null){		label = this.findByLanguage(labelsList, language);	}
		}
		else if(!HiltonUtility.isEmpty(module))
		{
			label = this.findByModule(labelsList, module, language);
			if(label == null){		label = this.findByLanguage(labelsList, language);	}
		}
		else
		{
			label = this.findByLanguage(labelsList, language);
		}
		return label;
	}
	private Labels findByLanguage(List labelsList,  String language)
	{
		Labels label = null;
		Labels labelEnglish = null;

		for(int i = 0; i < labelsList.size(); i++)
		{
			Labels tmpLabel = (Labels)labelsList.get(i);
			if(tmpLabel.getLanguage().equalsIgnoreCase("en")){		labelEnglish = tmpLabel;	}
			if(tmpLabel.getLanguage().equalsIgnoreCase(language)){		label= tmpLabel;		}
		}
		if(label == null){		label = labelEnglish;	}
		return label;
	}

	private Labels findByModule(List labelsList, String module, String language)
	{
		Labels label = null;
		Labels labelByModule = null;

		for(int i = 0; i < labelsList.size(); i++)
		{
			Labels tmpLabel = (Labels)labelsList.get(i);
			if(tmpLabel.getModule().equalsIgnoreCase(module) && tmpLabel.getLanguage().equalsIgnoreCase("en"))
			{
				labelByModule = tmpLabel;
			}

			if(tmpLabel.getModule().equalsIgnoreCase(module) && tmpLabel.getLanguage().equalsIgnoreCase(language))
			{
				label= tmpLabel;
			}
		}
		if(label == null)
		{
			label = labelByModule;
		}

		return label;
	}

	private Labels findByModuleType(List labelsList, String moduleType, String module, String language)
	{
		Labels label = null;
		Labels labelByModuleLanguage = null;
		Labels labelByModule = null;
		for(int i = 0; i < labelsList.size(); i++)
		{
			Labels tmpLabel = (Labels)labelsList.get(i);
			if(tmpLabel.getModule().equalsIgnoreCase(module) && tmpLabel.getLanguage().equalsIgnoreCase("en"))
			{
				labelByModule = tmpLabel;
			}

			if(tmpLabel.getModule().equalsIgnoreCase(module) && tmpLabel.getLanguage().equalsIgnoreCase(language))
			{
				labelByModuleLanguage = tmpLabel;
			}
			if(tmpLabel.getModule().equalsIgnoreCase(module) &&
					tmpLabel.getModuleType().equalsIgnoreCase(moduleType) &&
					tmpLabel.getLanguage().equalsIgnoreCase(language))
			{
				label = tmpLabel;
				i = labelsList.size();
			}
		}
		if(label == null)
		{
			label = labelByModuleLanguage;
			if(label == null)
			{
				label = labelByModule;
			}
		}

		return label;
	}


	public Map getCacheByOid(String oid)
	{
		return (Map)this.labelsMap.get(oid);
	}

	private List checkLabelsCache(String oid, String labelCode)
	{
		Map cacheByOid = (Map)this.labelsMap.get(oid);
		if(cacheByOid == null)
		{
			cacheByOid = new HashMap();
		}
		Map cacheByCode = (Map)cacheByOid.get(labelCode);
		List labelsList = null;
		if(cacheByCode == null)
		{
			labelsList = this.loadLabel(oid, labelCode);
			cacheByOid.put(labelCode, labelsList);
		}
		else
		{
			labelsList = (List)cacheByCode.get(labelCode);
		}
		return labelsList;
	}

	public void loadAll(String o, String language)
	{
		try
		{
			if(HiltonUtility.isEmpty(language)){		language = "en";		}
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", o);
			incomingRequest.put("language", language);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(o) ;
			PuridiomProcess process = processLoader.loadProcess("labels-retrieve-all.xml");

			process.executeProcess(incomingRequest);
			List qry = (List)incomingRequest.get("labelsList");
			HashMap languageProps = new HashMap();
			for(int i = 0; i < qry.size(); i++)
			{
				Labels label = (Labels)qry.get(i);
				languageProps.put(label.getLabelCode(), label);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public List loadLabel(String o, String labelCode)
	{
		List labelsList = null;
		try
		{
			Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", o);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(o) ;
			PuridiomProcess process = processLoader.loadProcess("labels-retrieve-by-code.xml");
			incomingRequest.put("Labels_labelCode", labelCode);

			process.executeProcess(incomingRequest);
			labelsList = (List)incomingRequest.get("labelsList");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return labelsList;
	}
}
