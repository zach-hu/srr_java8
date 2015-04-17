package com.tsa.puridiom.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jdom.Element;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;

public class MeterGraph extends Graph
{
	private double startInterval ;
    private double normalInterval ;
    private double warningInterval ;
    private double criticalInterval ;
    private double endInterval ;

	public Object createDataset()
	{
		return this.getGraphData().createValueDataset(this.getSqlParams(), this.getOrganizationId());
	}

	public void createChart()
	{
    		try
    		{
				//create chart
    			this.setChart(new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, this.createMeterPlot(), false));
	    	}
    		catch (Exception e)
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public MeterPlot createMeterPlot()
	{
		MeterPlot plot = new MeterPlot((DefaultValueDataset)this.createDataset());

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

		return plot;
	}

	public void formatChart()
	{
		this.getChart().setBackgroundPaint(Color.white);
		this.getChart().setBorderPaint(Color.white);
		this.getChart().setBorderVisible(false);
		TextTitle chartTitle = this.getChart().getTitle();
		chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
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

	public void buildMeForType(Element graphElement)
	{
		this.setStartInterval( graphElement.getChild("interval").getChildTextTrim("start") );
		this.setNormalInterval( graphElement.getChild("interval").getChildTextTrim("normal") );
		this.setWarningInterval( graphElement.getChild("interval").getChildTextTrim("warning") );
		this.setCriticalInterval( graphElement.getChild("interval").getChildTextTrim("critical") );
		this.setEndInterval( graphElement.getChild("interval").getChildTextTrim("end") );
	}

}
