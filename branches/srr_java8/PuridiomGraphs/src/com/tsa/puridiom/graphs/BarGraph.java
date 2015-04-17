package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarGraph extends Graph
{
	public Object createDataset()
	{
		return this.getGraphData().createCategoryDataset(this.getSqlParams(), this.getOrganizationId());
	}

	public void createChart()
	{
    		try
    		{
				//create chart
	    		this.setChart(ChartFactory.createBarChart3D("", this.getXTitle(), this.getYTitle(), (DefaultCategoryDataset)this.createDataset() , PlotOrientation.HORIZONTAL, true, true, false));
	    	}
    		catch (Exception e)
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void formatChart()
	{
        CategoryPlot categoryplot = this.getChart().getCategoryPlot();
        categoryplot.setNoDataMessage("No Information Available");
        /*	renderer	*/
        //BarRenderer renderer = (BarRenderer) categoryplot.getRenderer();

        /*
         * custom renderer

         * */
        Paint colors1[] = null;
        if(this.getFormType().equalsIgnoreCase("req"))
        {
        	colors1 =  new Paint[] {Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue };
        }
        else
        {
        	colors1 =  new Paint[] {Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.red };
        }

        CustomBarRenderer3D renderer =new CustomBarRenderer3D(colors1);

        /*
         * links
         */
        //renderer.setSeriesPaint(0, Color.cyan); //first bar
        renderer.setDrawBarOutline(false);
//        renderer.setMaxBarWidth(0.10);
        renderer.setMaximumBarWidth(10) ;
        //renderer.setSeriesPaint(1,new Color(153,51,102)); // second bar
        if(this.getFormType().equalsIgnoreCase("req"))
        {
        	renderer.setItemURLGenerator((CategoryURLGenerator)JavaScriptUrlFactory.getUrlGenerator(this));
        }
        else if(this.getFormType().equalsIgnoreCase("rfq"))
        {
        	renderer.setItemURLGenerator(new JavaScriptRfqUrl());
        }

        renderer.setToolTipGenerator(new ReqToolTipGenerator());

        /*
         * labels
         */
        //renderer.setLabelGenerator(new LabelGenerator());
        renderer.setItemLabelFont(new Font("Verdana", Font.BOLD, 8));
        renderer.setItemLabelsVisible(true);
        //renderer.setItemLabelPaint(Color.white);
        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 100.0);
        renderer.setPositiveItemLabelPosition(itemlabelposition);

        ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.TOP_CENTER, TextAnchor.BASELINE_CENTER, -Math.PI / 100.0);
        renderer.setPositiveItemLabelPositionFallback(p2);
        categoryplot.getDomainAxis().setLabelFont(new Font("Verdana", Font.BOLD, 8));
        //categoryplot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        if(categoryplot.getCategories().size() < 5)
        {
            categoryplot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        }
        else
        {
            renderer.setItemMargin(0.00001);
            categoryplot.getDomainAxis().setCategoryMargin(0.0005);
            categoryplot.getDomainAxis().setCategoryMargin(0.3);
            categoryplot.getDomainAxis().setLowerMargin(0.00);
            categoryplot.getDomainAxis().setLowerMargin(0.00);
            categoryplot.getDomainAxis().setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

            //renderer.setMargin(0.05);
        }
        categoryplot.setRenderer(renderer);
        /*
         * legend
         * StandardLegend is replaced by LegendTitle which places the legend into the same mechanism as other chart (sub)titles.

LineAndShapeRenderer:
setDrawShapes() --> setBaseShapesVisible().
setDrawLines() --> setBaseLinesVisible().

StandardXYItemRenderer:
setPlotShapes() --> setBaseShapesVisible().

JFreeChart:
setLegend(Object) --> addLegend(LegendTitle);

         */
        this.getChart().clearSubtitles();
        TextTitle chartTitle = this.getChart().getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
        this.getChart().setBackgroundPaint(Color.white);
	}

}
