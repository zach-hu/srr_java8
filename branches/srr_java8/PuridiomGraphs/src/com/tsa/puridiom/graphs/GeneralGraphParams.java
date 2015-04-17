package com.tsa.puridiom.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.ValueDataset;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;

public class GeneralGraphParams
{
	private int idGraphic ;
	private int position;
    private int width = 200;
    private int heigth = 200;
    private PrintWriter writer = null;
    private String contextPath = "";
    private String typeGraph = "";
    private String title = "";
    private String XTitle = "";
    private String YTitle = "";
    private CategoryDataset dataset = null;
    private String ImgName = "";
    private String userId = "";
    private String sqlQuery = "";
    private String graphInfo = "";
    private DBSession dbSession;
    private String organizationId;
    private String typeData;
    private String userTimeZone = "";

    private double startInterval ;
    private double normalInterval ;
    private double warningInterval ;
    private double criticalInterval ;
    private double endInterval ;

    private ChartRenderingInfo chartRenderingInfo = null;
    private boolean hasData = false;
    private String formType = "REQ";
    private boolean createImageMap = true;

    private String link = "";
    private String enabled = "true";
    private List userWhere = new ArrayList();
    private String name = "";

    public String getChart(GeneralGraphParams params) throws Exception
    {
    	if (params.getTypeGraph().equals(TypeGraph.barChart))
    	{
    		return this.getBarChart(params);
    	}
    	else if (params.getTypeGraph().equals(TypeGraph.pieChart))
    	{
    		return this.getPieChart(params);
    	}
    	else if (params.getTypeGraph().equals(TypeGraph.meterChart))
    	{
    		return this.getMeterChart(params);
    	}
    	else
    		return this.getUnavailableChart(new GeneralGraphParams());
    }

    public String getBarChart(GeneralGraphParams params) throws Exception
    {
    	String temp;

    		GeneralSqlParams generalSqlParams = new GeneralSqlParams();
    		generalSqlParams.setUserFilter(params.getUserWhere());
    		generalSqlParams.setSqlQuery(sqlQuery);
    		generalSqlParams.createDefaultParams(this.getUserId(), this.getOrganizationId(), this.getUserTimeZone());
    		generalSqlParams.replaceSqlQuery();

    		GeneralGraphData generalGraphData = new GeneralGraphData();
    		generalGraphData.setDbSession(this.getDbSession());
    		generalGraphData.createTypesData(params.getTypeData());

    		//creating dataset
    		CategoryDataset categoryDataset = new DefaultCategoryDataset();
    		categoryDataset = generalGraphData.createCategoryDataset(generalSqlParams, this.getOrganizationId());
    		//creating chart
    		JFreeChart jfreechart = ChartFactory.createBarChart3D("", params.getXTitle(), params.getYTitle(), categoryDataset , PlotOrientation.HORIZONTAL, true, true, false);
    		jfreechart = GeneralChartUtilities.formatBarChart(jfreechart, params);

    		temp = GeneralChartUtilities.formatMe(params, jfreechart);

    		this.setGraphInfo(temp);

    		return temp;

    }

    public String getPieChart(GeneralGraphParams params) throws Exception
    {
    	String temp;

    		GeneralSqlParams generalSqlParams = new GeneralSqlParams();
    		generalSqlParams.setSqlQuery(sqlQuery);
    		generalSqlParams.createDefaultParams(this.getUserId(), this.getOrganizationId(), this.getUserTimeZone());
    		generalSqlParams.replaceSqlQuery();

    		GeneralGraphData generalGraphData = new GeneralGraphData();
    		generalGraphData.setDbSession(this.getDbSession());
    		generalGraphData.createTypesData(params.getTypeData());

    		//	creating dataset
    		PieDataset pieDataset = new DefaultPieDataset();
    		pieDataset = generalGraphData.createPieDataset(generalSqlParams, this.getOrganizationId());

    		//	creating chart
    		JFreeChart jfreechart = ChartFactory.createPieChart3D("",pieDataset,false,true,true);

    		jfreechart = GeneralChartUtilities.formatPieChart(jfreechart);
    		temp = GeneralChartUtilities.formatMe(params, jfreechart);
    		this.setGraphInfo(temp);

    		return temp;

    }

    public String getMeterChart(GeneralGraphParams params) throws Exception
    {
    	String temp;

    		GeneralSqlParams generalSqlParams = new GeneralSqlParams();
    		generalSqlParams.setSqlQuery(sqlQuery);
    		generalSqlParams.createDefaultParams(this.getUserId(), this.getOrganizationId(), this.getUserTimeZone());
    		generalSqlParams.replaceSqlQuery();

    		GeneralGraphData generalGraphData = new GeneralGraphData();
    		generalGraphData.setDbSession(this.getDbSession());
    		generalGraphData.createTypesData(params.getTypeData());

    		//creating dataset
    		ValueDataset valueDataset = new DefaultValueDataset();
    		valueDataset = generalGraphData.createValueDataset(generalSqlParams, this.getOrganizationId());

    		MeterPlot plot = new MeterPlot(valueDataset);

    		plot.setRange(new Range( this.getStartInterval() ,this.getEndInterval() ) );

    		plot.setDialBackgroundPaint(Color.white);
    		plot.setDialOutlinePaint(Color.black);
    		plot.setNeedlePaint(Color.black);
    		plot.setValuePaint(Color.black);

    		Stroke stroke = new BasicStroke(2);
    		//plot.addInterval(new MeterInterval("Normal", new Range(this.getNormalInterval() ,this.getWarningInterval() ), Color.green, stroke, new Color(170,240,170) ));
    		//plot.addInterval(new MeterInterval("Warning", new Range(this.getWarningInterval() , this.getCriticalInterval()), new Color(255,255,0), stroke, new Color(250,250,140) ));
    		//plot.addInterval(new MeterInterval("Critical", new Range(this.getCriticalInterval(), this.getEndInterval()), Color.red, stroke, new Color(240,160,160) ));

    		plot.addInterval(new MeterInterval("Normal", new Range(this.getNormalInterval() ,this.getWarningInterval() ), Color.green, stroke, Color.green ));
    		plot.addInterval(new MeterInterval("Warning", new Range(this.getWarningInterval() , this.getCriticalInterval()), new Color(255,255,0), stroke, new Color(255,255,0) ));
    		plot.addInterval(new MeterInterval("Critical", new Range(this.getCriticalInterval(), this.getEndInterval()), Color.red, stroke, Color.red ));

    		plot.setUnits("Day(s)");
    		plot.setNoDataMessage("No Information Available");

    		JFreeChart jfreechart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
    		jfreechart.setBackgroundPaint(Color.white);
    		jfreechart.setBorderPaint(Color.white);
    		jfreechart.setBorderVisible(false);
    		TextTitle chartTitle = jfreechart.getTitle();
    		chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));

    		temp = GeneralChartUtilities.formatMe(params, jfreechart);
    		this.setGraphInfo(temp);
    		return temp;

    }

    public String getUnavailableChart(GeneralGraphParams params) throws Exception
    {
    	params.setTitle("graph not available");
    	MeterPlot meterPlot = new MeterPlot(null);
    	JFreeChart jfreechart = new JFreeChart( "CAN NOT GENERATE THE GRAPH" , JFreeChart.DEFAULT_TITLE_FONT, meterPlot, false);
    	jfreechart.setBackgroundPaint(Color.white);
        jfreechart.setBorderPaint(Color.white);
        TextTitle chartTitle = jfreechart.getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
    	String temp = GeneralChartUtilities.formatMe(params, jfreechart);
        this.setGraphInfo(temp);

        return temp;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getHeigth()
    {
        return heigth;
    }
    public void setHeigth(int heigth)
    {
        this.heigth = heigth;
    }
    public void setHeigth(String heigth)
	{
		if(!HiltonUtility.isEmpty(heigth))
		{
			this.heigth = Integer.parseInt(heigth);
		}
	}

    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public void setWidth(String width)
	{
		if(!HiltonUtility.isEmpty(width))
		{
			this.width = Integer.parseInt(width);
		}
	}

    public PrintWriter getWriter()
    {
        return writer;
    }
    public void setWriter(PrintWriter writer)
    {
        this.writer = writer;
    }
    public String getContextPath()
    {
        return contextPath;
    }
    public void setContextPath(String contextPath)
    {
        this.contextPath = contextPath;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[GraphParams:");
        buffer.append(" width: ");
        buffer.append(width);
        buffer.append(" heigth: ");
        buffer.append(heigth);
        buffer.append(" writer: ");
        buffer.append(writer);
        buffer.append(" contextPath: ");
        buffer.append(contextPath);
        buffer.append("]");
        return buffer.toString();
    }

    public String getXTitle()
    {
        // TODO Auto-generated method stub
        return XTitle;
    }
    public CategoryDataset getDataset()
    {
        return dataset;
    }
    public void setDataset(CategoryDataset categorydataset)
    {
        this.dataset = categorydataset;
    }
    public String getYTitle()
    {
        return YTitle;
    }
    public void setYTitle(String title)
    {
        YTitle = title;
    }
    public void setXTitle(String title)
    {
        XTitle = title;
    }
    public ChartRenderingInfo getChartRenderingInfo()
    {
        return chartRenderingInfo;
    }
    public void setChartRenderingInfo(ChartRenderingInfo chartRenderingInfo)
    {
        this.chartRenderingInfo = chartRenderingInfo;
    }
    public String getImgName()
    {
        return ImgName;
    }
    public void setImgName(String imgName)
    {
        ImgName = imgName;
    }
    public boolean getHasData()
    {
    	return hasData;
    }
    public void setHasData(boolean bHasData)
    {
    	this.hasData = bHasData;
    }
    public boolean getCreateImageMap()
    {
    	return createImageMap;
    }
    public void setCreateImageMap(boolean bImgMap)
    {
    	this.createImageMap = bImgMap;
    }

	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}

	/**
	 * @param graphInfo the graphInfo to set
	 */
	public void setGraphInfo(String graphInfo) {
		this.graphInfo = graphInfo;
	}

	/**
	 * @return the graphInfo
	 */
	public String getGraphInfo() {
		return graphInfo;
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

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setDbSession(DBSession dbSession) {
		this.dbSession = dbSession;
	}

	public DBSession getDbSession() {
		return dbSession;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setTypeGraph(String typeGraph) {
		this.typeGraph = typeGraph;
	}

	public String getTypeGraph() {
		return typeGraph;
	}

	public void setTypeData(String typeData) {
		this.typeData = typeData;
	}

	public String getTypeData() {
		return typeData;
	}

	public void setIdGraphic(int idGraphic) {
		this.idGraphic = idGraphic;
	}

	public void setIdGraphic(String idGraphic)
	{
		if(!HiltonUtility.isEmpty(idGraphic))
		{
			this.idGraphic = Integer.parseInt(idGraphic);
		}
	}

	public int getIdGraphic() {
		return idGraphic;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPosition(String position)
	{
		if(!HiltonUtility.isEmpty(position))
		{
			this.position = Integer.parseInt(position);
		}
	}

	public int getPosition() {
		return position;
	}

	public void setNormalInterval(double normalInterval) {
		this.normalInterval = normalInterval;
	}
	public void setNormalInterval(String normalInterval) {
		if(!HiltonUtility.isEmpty(normalInterval))
		{
			this.normalInterval = Double.parseDouble(normalInterval);
		}
	}

	public double getNormalInterval() {
		return normalInterval;
	}

	public void setWarningInterval(double warningInterval) {
		this.warningInterval = warningInterval;
	}
	public void setWarningInterval(String warningInterval) {
		if(!HiltonUtility.isEmpty(warningInterval))
		{
			this.warningInterval = Double.parseDouble(warningInterval);
		}
	}


	public double getWarningInterval() {
		return warningInterval;
	}


	public void setStartInterval(double startInterval) {
		this.startInterval = startInterval;
	}
	public void setStartInterval(String startInterval) {
		if(!HiltonUtility.isEmpty(startInterval))
		{
			this.startInterval = Double.parseDouble(startInterval);
		}
	}

	public double getStartInterval() {
		return startInterval;
	}

	public void setEndInterval(double endInterval) {
		this.endInterval = endInterval;
	}
	public void setEndInterval(String endInterval) {
		if(!HiltonUtility.isEmpty(endInterval))
		{
			this.endInterval = Double.parseDouble(endInterval);
		}
	}

	public double getEndInterval() {
		return endInterval;
	}

	public void setCriticalInterval(double criticalInterval) {
		this.criticalInterval = criticalInterval;
	}
	public void setCriticalInterval(String criticalInterval) {
		if(!HiltonUtility.isEmpty(criticalInterval))
		{
			this.criticalInterval = Double.parseDouble(criticalInterval);
		}
	}

	public double getCriticalInterval() {
		return criticalInterval;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getEnabled() {
		return enabled;
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

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }
}
