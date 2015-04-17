package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import com.tsagate.foundation.database.DBSession;

public class PieGraph extends Graph
{
	public Object createDataset()
	{
		return this.getGraphData().createPieDataset(this.getSqlParams(), this.getOrganizationId());
	}

	public void createChart(DBSession dbSession)
	{
    		try
    		{
				//create chart
	    		this.setChart(ChartFactory.createPieChart3D("", (PieDataset)this.createDataset() , false, true, true));
	    	}
    		catch (Exception e)
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void formatChart()
	{

        /*PiePlot pieplot = (PiePlot)jfreechart.getPlot();
        pieplot.setLabelFont(new Font("Verdana", 0, 9));
        pieplot.setNoDataMessage("No data available");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setURLGenerator(new StandardPieURLGenerator());
        */
        PiePlot3D plot = (PiePlot3D) this.getChart().getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No Information Available");
        plot.setURLGenerator(new GeneralJavaScriptPoByPoTypeUrl());
        //plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}") );
        plot.setLabelBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.white);

        //plot.setToolTipGenerator(new HiltonPieToolTipGenerator());
        //plot.setToolTipGenerator();

        this.getChart().setBackgroundPaint(Color.white);
        this.getChart().setBorderPaint(Color.white);
        this.getChart().setBorderVisible(false);
        /**
         * legend
         */
        //StandardLegend legend = (StandardLegend)jfreechart.getLegend();
       /* StandardLegend legend = new StandardLegend();
        legend.setOutlinePaint(Color.white);
        //legend.setAnchor(Legend.SOU);
        legend.setItemFont(new Font("Verdana", Font.PLAIN, 9));
        legend.setPreferredWidth(100);
        legend.setOutlinePaint(Color.white);
        legend.setBackgroundPaint(Color.white);
        //jfreechart.setLegend(legend);

         */
        //jfreechart.setLegend(null);
        TextTitle chartTitle = this.getChart().getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
	}
}
