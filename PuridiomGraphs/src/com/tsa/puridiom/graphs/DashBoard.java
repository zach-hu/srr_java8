package com.tsa.puridiom.graphs;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class DashBoard
{
	private String organizationId;
	private String userId;
	private String type = "buyer-dashboard.xml";
	private Map graphs = new HashMap();
	private DBSession dbsession;

	public DashBoard(String organizationId, String userId)
	{
		this.organizationId = organizationId;
		this.userId = userId;
		this.setDbsession();
		this.loadMe();
	}

	public String filepath()
	{
		return DictionaryManager.getInstance("host", organizationId).getProperty("graphs");
	}

	public void loadMe()
	{
		File f = Utility.getOidFile(this.filepath() + this.type, this.organizationId);
		 if (f.exists())
         {
			 try
			 {
	             DOMBuilder docBuilder = new DOMBuilder();
	             Document document = docBuilder.build(f);
	             populateDashboardFromXml(document);
			 }
			 catch (Exception e) {
				e.printStackTrace();
			}
         }
         else
         {
         	Log.error(this, "Dashboard not found: " + f.getPath());
         }
	}

	public void populateDashboardFromXml(Document document)
	{
		List graphsEl =  document.getRootElement().getChildren("graph");

		for(int i = 0; i < graphsEl.size(); i++)
        {
			Graph graph = null;
			try
			{
				Element graphElement = (Element)graphsEl.get(i);
				String type = graphElement.getChildText("type");
				graph = GraphFactory.getGraph(type);
				graph.setUserId(this.userId);
				graph.setOrganizationId(this.organizationId);
				graph.buildMeFromXmlElement(graphElement);
				this.graphs.put(graph.getName(), graph);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
        }
	}

	public Graph getGraph(String graphName)
	{
		return  ((Graph)this.graphs.get(graphName));
	}

	public void setGraph(String graphName)
	{
		((IGraph)this.graphs.get(graphName)).setGraph(this.getDbsession());
	}

	public Map getGraphs() {
		return graphs;
	}

	public void setGraphs(Map graphs) {
		this.graphs = graphs;
	}

	/**
	 * Not to be used by any other class.
	 * This session is used Only to get data for the graphs.
	 * @return session
	 */
	private DBSession getDbsession()
	{
		return dbsession;
	}

	private void setDbsession()
	{
		try
		{
			this.dbsession = new DBSession(this.organizationId);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveUserWhere(String userWhereName, String value, String organizationId, String userId)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("userpreference-update.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("UserPreference_userId", userId);
			incomingRequest.put("UserPreference_property", userWhereName.toUpperCase());
			incomingRequest.put("UserPreference_value", value);
			incomingRequest.put("organizationId", organizationId);

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
}
