package com.tsa.puridiom.graphs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserPreference;
import com.tsa.puridiom.userpreference.UserPreferenceManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
//import com.tsa.puridiom.menu.*;

public class LoadGraphic
{
	private String organizationId = "";
	private String userId = "";
	private String contextPath;
	private DBSession dbSession;
	private Map incomingRequest;

	public List loadMe(String organizationId, String userId, String contextPath)
    {
		this.setOrganizationId(organizationId);
		this.setUserId(userId);
		this.setContextPath(contextPath);
		return this.loadMe("graphics.xml", organizationId);
    }

	public String filepath(String organizationId)
	{
		return DictionaryManager.getInstance("host", organizationId).getProperty("graphs");
	}
	public List loadMe(String fileName, String organizationId )
    {
		this.setOrganizationId(organizationId);
		List graphics = new ArrayList();
        try
        {
            //Log.debug(this, fileName);
            File f = Utility.getOidFile(this.filepath(organizationId) + fileName, this.getOrganizationId());

            if (f.exists())
            {
                DOMBuilder docBuilder = new DOMBuilder();
                Document document = docBuilder.build(f);
                graphics = populateGraphicsFromXML(document);
            }
            else
            {
            	Log.error(this, "GRAPHIC not found: " + f.getPath());
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            //system.out.println(exception.toString());
        }
        return graphics;
    }

	private List populateGraphicsFromXML(Document document) throws Exception
	{
		List graphicElements =  document.getRootElement().getChildren("graphic");
		List graphics = new ArrayList();

		for(int i = 0; i < graphicElements.size(); i++)
        {
			GeneralGraphParams generalGraphParams = new GeneralGraphParams();
			try
			{
				Element graphElement = (Element)graphicElements.get(i);

				//generalGraphParams.setOid(this.getOrganizationId());
				generalGraphParams.setUserId(this.getUserId());
				generalGraphParams.setContextPath(this.getContextPath());
				generalGraphParams.setDbSession(this.getDbSession());
				generalGraphParams.setOrganizationId(this.getOrganizationId());

				generalGraphParams.setTitle( graphElement.getChildText("title") );
				generalGraphParams.setXTitle( graphElement.getChildText("xTitle"));
				generalGraphParams.setHeigth( graphElement.getChildText("height"));
				generalGraphParams.setWidth( graphElement.getChildText("width"));
				generalGraphParams.setSqlQuery( graphElement.getChildText("sql"));
				generalGraphParams.setTypeGraph(graphElement.getChildText("type"));
				generalGraphParams.setLink(graphElement.getChildText("link"));
				generalGraphParams.setName(graphElement.getChildText("name"));

				if ( graphElement.getChildText("enabled") != null )
				{
					generalGraphParams.setEnabled(graphElement.getChildText("enabled"));
				}
				else{
					generalGraphParams.setEnabled("true");
				}

				if( graphElement.getChildTextTrim("typeData")!=null &&  graphElement.getChildTextTrim("typeData").length() > 0 )
				{
					generalGraphParams.setTypeData(graphElement.getChildText("typeData"));
				}
				if( graphElement.getChildTextTrim("interval")!=null  )
				{
					generalGraphParams.setStartInterval( graphElement.getChild("interval").getChildTextTrim("start") );
					generalGraphParams.setNormalInterval( graphElement.getChild("interval").getChildTextTrim("normal") );
					generalGraphParams.setWarningInterval( graphElement.getChild("interval").getChildTextTrim("warning") );
					generalGraphParams.setCriticalInterval( graphElement.getChild("interval").getChildTextTrim("critical") );
					generalGraphParams.setEndInterval( graphElement.getChild("interval").getChildTextTrim("end") );
				}
				generalGraphParams = this.loadUserFilter(graphElement, generalGraphParams);

				if(!generalGraphParams.getEnabled().equalsIgnoreCase("false"))
				{
					generalGraphParams.setEnabled("true");
					generalGraphParams.getChart(generalGraphParams);
					graphics.add(generalGraphParams);
				}

			}
			catch (Exception exception)
			{
				generalGraphParams = new GeneralGraphParams();
				exception.printStackTrace();
				generalGraphParams.getUnavailableChart(generalGraphParams);
				graphics.add(generalGraphParams);
			}
        }
		return graphics;

	}

	public GeneralGraphParams loadUserFilter(Element graphElement, GeneralGraphParams graphParams)
	{
		/*
		 * <userWhere>
			<column>header.requisitionDate</column>
			<operand><![CDATA[=]]></operand>
			<value>:thisyear:thismonth:thisweek</value>
		</userWhere>
		 */
		Element userWhere = graphElement.getChild("userWhere") ;
		if(userWhere != null)
		{
			List argumentList = new ArrayList();
			argumentList.add(userWhere.getChildText("column"));
			String value = "";
			try
			{
				String fromRequest = (String)this.getIncomingRequest().get(graphParams.getName() + "_userFilter");
				if(HiltonUtility.isEmpty(fromRequest))
				{
					String tmp =  graphParams.getName() + "dashWhere";
					value = UserPreferenceManager.getInstance().getUserPreference(this.getOrganizationId(), this.getUserId(), tmp.toUpperCase(), "");
				}
				else
				{
					value = fromRequest;
					this.saveUserWhere(graphParams.getName() + "dashWhere", value);
				}
			}
			catch (Exception e)
			{
				value = "";
				e.printStackTrace();
			}
			if(HiltonUtility.isEmpty(value)){		value = ":none";	}
			argumentList.add(value);
			graphParams.setUserWhere(argumentList);
		}

		return graphParams;
	}

	public void saveUserWhere(String userWhereName, String value)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("userpreference-update.xml");
			incomingRequest.put("UserPreference_userId", this.getUserId());
			incomingRequest.put("UserPreference_property", userWhereName.toUpperCase());
			incomingRequest.put("UserPreference_value", value);

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{

			}
			else
			{

			}
		}
		catch (Exception exception)
		{

		}
	}


	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param contextPath the contextPath to set
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * @return the contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}

	public void setDbSession(DBSession dbSession) {
		this.dbSession = dbSession;
	}

	public DBSession getDbSession() {
		return dbSession;
	}

	public Map getIncomingRequest() {
		return incomingRequest;
	}

	public void setIncomingRequest(Map incomingRequest) {
		this.incomingRequest = incomingRequest;
	}

}
