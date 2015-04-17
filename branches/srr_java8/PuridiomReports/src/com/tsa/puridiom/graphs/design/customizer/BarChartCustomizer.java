package com.tsa.puridiom.graphs.design.customizer;

import java.awt.Color;
import java.awt.Font;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.Title;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

public class BarChartCustomizer implements JRChartCustomizer
{

	public void customize(JFreeChart chart, JRChart jasperChart)
	{
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 100.0);
        renderer.setPositiveItemLabelPosition(itemlabelposition);


		//renderer.setSeriesPaint(0, Color.green);
		//renderer.setSeriesPaint(1, Color.orange);

		//chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		//chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("Verdana", Font.PLAIN, 7));

		//Title legend = (LegendTitle) chart.getSubtitle(0);
        //legend.setPosition(RectangleEdge.LEFT);
        //legend.setItemFont(new Font("Verdana", Font.PLAIN, 8));
        //legend.setBorder(new BlockBorder(0, 0, 0, 0));

		/*System.out.println("getCategoryLabelPositionOffset : "+chart.getCategoryPlot().getDomainAxis().getCategoryLabelPositionOffset());
		System.out.println("getCategoryMargin : "+chart.getCategoryPlot().getDomainAxis().getCategoryMargin());
		System.out.println("getLowerMargin : "+chart.getCategoryPlot().getDomainAxis().getLowerMargin());
		System.out.println("getMaximumCategoryLabelLines : "+chart.getCategoryPlot().getDomainAxis().getMaximumCategoryLabelLines());
		System.out.println("getMaximumCategoryLabelWidthRatio : "+chart.getCategoryPlot().getDomainAxis().getMaximumCategoryLabelWidthRatio());
		*/
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 7));
        plot.setDomainAxis(domainAxis);
        plot.setRenderer(renderer);

	}
}