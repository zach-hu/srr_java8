/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.design;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
//import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultIntervalCategoryDataset;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VerticalChart implements Serializable
{

    /* (non-Javadoc)
     * @see de.laures.cewolf.ChartPostProcessor#processChart(java.lang.Object, java.util.Map)
     */
    public void processChart(Object chart, Map params)
    {
        JFreeChart jfreechart = (JFreeChart)chart;

        CategoryPlot plot = (CategoryPlot)jfreechart.getCategoryPlot();

        jfreechart.setBackgroundPaint(Color.white);
        this.setLegend(jfreechart);
        this.setBackground(plot);
        this.setTitle(jfreechart);
        this.setNumberAxis(plot);
        //this.setLabels(plot);
    }

    private void setLabels(CategoryPlot plot)
    {
        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 7));
        plot.setDomainAxis(domainAxis);
    }
    private void setBars(CategoryPlot categoryPlot)
    {
        CategoryPlot plot = (CategoryPlot)categoryPlot;
        //plot.getVerticalValueAxis();

    }
    private void setNumberAxis(CategoryPlot plot)
    {
        NumberAxis axis = (NumberAxis)plot.getRangeAxis();
        axis.setVisible(false);

        plot.setRangeAxis(axis);
    }
    private void setLegend(JFreeChart chart)
    {
        /*StandardLegend legend = (StandardLegend)chart.getLegend();
        legend.setBackgroundPaint(Color.white);
        legend.setItemFont(new Font("Verdana", Font.PLAIN, 8));
        legend.setOutlinePaint(Color.white);
        */
    }

    private void setCustomLegend(JFreeChart chart, CategoryPlot plot)
    {

    }

    private void setBackground(CategoryPlot plot)
    {
        //plot.setLabelsVisible(true);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.white);
    }

    private void setTitle(JFreeChart chart)
    {
        TextTitle title =  chart.getTitle();
        title.setFont(new Font("Verdana", Font.BOLD, 9));
        chart.setTitle(title);
    }


}