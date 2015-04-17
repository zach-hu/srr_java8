package com.tsa.puridiom.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.userpreference.UserPreferenceManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;


public class Graph implements IGraph
{
	private int id ;
	private int position;
    private int width = 200;
    private int heigth = 200;
    private String typeGraph = "";
    private String title = "";
    private String XTitle = "";
    private String YTitle = "";
    private CategoryDataset dataset = null;
    private String ImgName = "";
    private String userId = "";
    private String sqlQuery = "";
    private String graphInfo = "";
    private String typeData;
    private String userTimeZone = "";
    private ChartRenderingInfo chartRenderingInfo = null;
    private boolean hasData = false;
    private String formType = "REQ";
    private boolean createImageMap = true;
    private GeneralSqlParams sqlParams;
    private GeneralGraphData graphData;

    private String link = "";
    private String enabled = "true";
    private List userWhere = new ArrayList();
    private boolean userHidden = false;
    private String name = "";
    private String organizationId;
    private JFreeChart chart;

	public ChartRenderingInfo getChartRenderingInfo() {
		return chartRenderingInfo;
	}
	public void setChartRenderingInfo(ChartRenderingInfo chartRenderingInfo) {
		this.chartRenderingInfo = chartRenderingInfo;
	}
	public boolean isHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public boolean isCreateImageMap() {
		return createImageMap;
	}
	public void setCreateImageMap(boolean createImageMap) {
		this.createImageMap = createImageMap;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.graphs.IGraph#getEnabled()
	 */
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public List getUserWhere() {
		return userWhere;
	}
	public void setUserWhere(List userWhere) {
		this.userWhere = userWhere;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int idGraphic) {
		this.id = idGraphic;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setWidth(String width)
	{
		if(!HiltonUtility.isEmpty(width))
		{
			this.width = Integer.parseInt(width);
		}
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(String heigth)
	{
		if(!HiltonUtility.isEmpty(heigth))
		{
			this.heigth = Integer.parseInt(heigth);
		}
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	public String getTypeGraph() {
		return typeGraph;
	}
	public void setTypeGraph(String typeGraph) {
		this.typeGraph = typeGraph;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getXTitle() {
		return XTitle;
	}
	public void setXTitle(String title) {
		XTitle = title;
	}
	public String getYTitle() {
		return YTitle;
	}
	public void setYTitle(String title) {
		YTitle = title;
	}
	public CategoryDataset getDataset() {
		return dataset;
	}
	public void setDataset(CategoryDataset dataset) {
		this.dataset = dataset;
	}
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.graphs.IGraph#getImgName()
	 */
	public String getImgName() {
		return ImgName;
	}
	public void setImgName(String imgName) {
		ImgName = imgName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public String getGraphInfo() {
		return graphInfo;
	}
	public void setGraphInfo(String graphInfo) {
		this.graphInfo = graphInfo;
	}
	public String getTypeData() {
		return typeData;
	}
	public void setTypeData(String typeData) {
		this.typeData = typeData;
	}
    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }
    public String getUserTimeZone() {
        return this.userTimeZone;
    }

	public void buildMeFromXmlElement(Element graphElement)
	{
		this.setTitle( graphElement.getChildText("title") );
		this.setXTitle( graphElement.getChildText("xTitle"));
		this.setHeigth( graphElement.getChildText("height"));
		this.setWidth( graphElement.getChildText("width"));
		this.setSqlQuery( graphElement.getChildText("sql"));

		this.setLink(graphElement.getChildText("link"));
		this.setName(graphElement.getChildText("name"));

		if ( graphElement.getChildText("enabled") != null )
		{
			this.setEnabled(graphElement.getChildText("enabled"));
		}
		else
		{
			this.setEnabled("true");
		}

		if( graphElement.getChildTextTrim("typeData")!=null &&  graphElement.getChildTextTrim("typeData").length() > 0 )
		{
			this.setTypeData(graphElement.getChildText("typeData"));
		}
		this.buildMeForType(graphElement);

		 this.loadUserFilter(graphElement);
	}

	public String getUserPreference(String preferenceName, String defaultValue)
	{
		String value = "";
		try {
			value = UserPreferenceManager.getInstance().getUserPreference(this.organizationId, this.getUserId(), preferenceName.toUpperCase(), defaultValue);
		}
		catch (Exception e)
		{
			value = defaultValue;
			e.printStackTrace();
		}
		return value;
	}

	public void loadUserFilter(Element graphElement)
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
				String tmp =  this.getName() + "dashWhere";
				value = UserPreferenceManager.getInstance().getUserPreference(this.organizationId, this.getUserId(), tmp.toUpperCase(), "");

			}
			catch (Exception e)
			{
				value = "";
				e.printStackTrace();
			}
			if(HiltonUtility.isEmpty(value)){		value = ":none";	}
			argumentList.add(value);
			this.setUserWhere(argumentList);
		}
	}

	public void saveUserWhere(String userWhereName, String value)
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.organizationId);
			PuridiomProcess process = processLoader.loadProcess("userpreference-update.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("UserPreference_userId", this.getUserId());
			incomingRequest.put("UserPreference_property", userWhereName.toUpperCase());
			incomingRequest.put("UserPreference_value", value);
			incomingRequest.put("organizationId", this.organizationId);

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

	public void buildMeForType(Element graphElement)
	{

	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public Object createDataset(GeneralSqlParams generalSqlParams)
	{
		return null;
	}

	public String getGraph()
	{
		return this.getGraphInfo();
	}

	/**
	 * @see com.tsa.puridiom.graphs.IGraph#getGraph()
	 */
	public String setGraph()
	{
		setupGraph(null);
		createChart();
		formatChart();
		setGraphInfo(GraphUtilities.formatMe(this));
		return this.getImgName();
	}

	public void setupGraph(DBSession dbsession)
	{
		this.setSqlParams();
		this.setGraphData(dbsession);
	}
	public GeneralSqlParams getSqlParams() {
		return sqlParams;
	}
	public void setSqlParams() {
		this.sqlParams = new GeneralSqlParams();
		this.sqlParams.setUserFilter(this.getUserWhere());
		this.sqlParams.setSqlQuery(this.getSqlQuery());
		this.sqlParams.createDefaultParams(this.getUserId(), this.getOrganizationId(), this.getUserTimeZone());
		this.sqlParams.replaceSqlQuery();
	}
	public GeneralGraphData getGraphData() {
		return graphData;
	}
	public void setGraphData(DBSession dbSession)
	{
		this.graphData = new GeneralGraphData();
		this.graphData.setDbSession(dbSession);
		this.graphData.createTypesData(this.getTypeData());
	}

	public void formatChart()	{	}
	public void createChart() {	}

	public String setGraph(DBSession dbsession) {
		setupGraph(dbsession);
		createChart();
		formatChart();
		setGraphInfo(GraphUtilities.formatMe(this));
		return this.getImgName();
	}

	public boolean isUserHidden()
	{
		return this.getUserPreference((this.getName() + "hidden").toUpperCase(), "N").equalsIgnoreCase("Y");
	}
}
