package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.Legend;
//import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
//import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import com.tsagate.properties.DictionaryManager;

public class GraphUtilities
{
    /*static class LabelGenerator extends StandardCategoryLabelGenerator
    {

        public String generateItemLabel(CategoryDataset categorydataset, int i, int j)
        {
            return categorydataset.getRowKey(i).toString();
        }

        LabelGenerator()
        {
        }
    }*/
	public static String filepath(String organizationId)
	{
		return DictionaryManager.getInstance("host", organizationId).getProperty("item-image-path");
	}


    public static JFreeChart formatPieChart(JFreeChart jfreechart)
    {
        /*PiePlot pieplot = (PiePlot)jfreechart.getPlot();
        pieplot.setLabelFont(new Font("Verdana", 0, 9));
        pieplot.setNoDataMessage("No data available");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setURLGenerator(new StandardPieURLGenerator());
        */
        PiePlot3D plot = (PiePlot3D) jfreechart.getPlot();
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

        jfreechart.setBackgroundPaint(Color.white);
        jfreechart.setBorderPaint(Color.white);
        jfreechart.setBorderVisible(false);
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
        TextTitle chartTitle = jfreechart.getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
        return jfreechart;

//        return jfreechart;
    }
    public static JFreeChart formatBarChart(JFreeChart chart, GeneralGraphParams params)
    {
        CategoryPlot categoryplot = chart.getCategoryPlot();
        categoryplot.setNoDataMessage("No Information Available");
        /*	renderer	*/
        //BarRenderer renderer = (BarRenderer) categoryplot.getRenderer();

        /*
         * custom renderer

         * */
        Paint colors1[] = null;
        if(params.getFormType().equalsIgnoreCase("req"))
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
        // renderer.setMaxBarWidth(0.10);
        renderer.setMaximumBarWidth(10) ;
        //renderer.setSeriesPaint(1,new Color(153,51,102)); // second bar
        if(params.getFormType().equalsIgnoreCase("req"))
        {
        	renderer.setItemURLGenerator((CategoryURLGenerator)JavaScriptUrlFactory.getUrlGenerator(params));
        }
        else if(params.getFormType().equalsIgnoreCase("rfq"))
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
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
                );

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
        chart.clearSubtitles();
        TextTitle chartTitle = chart.getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
        chart.setBackgroundPaint(Color.white);
        return chart;
    }
    public static JFreeChart formatBarChart1(JFreeChart chart)
    {
        CategoryPlot categoryplot = chart.getCategoryPlot();
        categoryplot.setDomainGridlinePaint(Color.black);
        categoryplot.setRangeGridlinePaint(Color.black);

        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

        BarRenderer renderer = (BarRenderer) categoryplot.getRenderer();
        /*
         * labels
         */
        //renderer.setLabelGenerator(new LabelGenerator());
        renderer.setItemLabelsVisible(true);
        renderer.setItemLabelFont(new Font("Verdana", Font.BOLD, 9));
        renderer.setItemLabelPaint(Color.white);
        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 90.0);
        renderer.setPositiveItemLabelPosition(itemlabelposition);

        ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 90.0);
        renderer.setPositiveItemLabelPositionFallback(p2);



        renderer.setItemURLGenerator(new JavaScriptUrl());
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        renderer.setItemMargin(10);
        categoryplot.setRenderer(renderer);

        CategoryAxis axis = categoryplot.getDomainAxis();
        CategoryLabelPositions p = axis.getCategoryLabelPositions();

        CategoryLabelPosition left = new CategoryLabelPosition(
            RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,
            TextAnchor.CENTER_LEFT, 0.0,
            CategoryLabelWidthType.RANGE, 0.30f
        );
        axis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));
        categoryplot.setDomainAxis(axis);
        //categoryplot.setRenderer(renderer);
        categoryplot.setOutlinePaint(Color.white);
        //categoryplot.setRangeGridlinePaint(Color.white);

        categoryplot.setDomainGridlinePaint(Color.white);


        chart.setBackgroundPaint(Color.lightGray);
        chart.setBorderVisible(false);


        TextTitle chartTitle = chart.getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
        chart.setBackgroundPaint(Color.white);
        return chart;
    }


    public static Graph writeChart(Graph graph)
    {
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        String filename = "";
        try
        {
        	//filename = ServletUtilities.saveChartAsPNG(chart, params.getWidth(), params.getHeigth(), info, params.getSession());
        	filename = "jfc"+System.currentTimeMillis()+".png";

        	//method to save in a specific directory.
        	final File file = new File(filepath(graph.getOrganizationId())+ File.separator +filename);
        	ChartUtilities.saveChartAsPNG(file, graph.getChart(), graph.getWidth(), graph.getHeigth(),info);
            graph.setChartRenderingInfo(info);
            //          Write the image map to the PrintWriter
            graph.setImgName(filename);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //params.getWriter().flush();
        }

        return graph;
    }

    public static String formatMe(Graph graph)
    {
        graph = GraphUtilities.writeChart(graph);
        String temp = GraphUtilities.getHtmlSrc( graph);

        return temp;
    }
    public static String getHtmlSrc(Graph graph)
    {
        String imageMap = TsaChartUtilities.getImageMap(graph.getImgName(), graph.getChartRenderingInfo());
        //String imageMap = "";
        StringBuffer imgTag = new StringBuffer("");
        imgTag.append(" <graph id=\"");
        imgTag.append(graph.getName());
        imgTag.append("\" width=\"");
        imgTag.append(graph.getWidth());

        //title
        imgTag.append("\" title=\"");
        imgTag.append(graph.getTitle());

        //user filter
        imgTag.append("\" ufilter=\"");
        List userWhere = graph.getUserWhere();
	  	if(userWhere.size() > 0)
	  	{
	  		String filter = (String)userWhere.get(1);
	  		imgTag.append(filter);
	  	}

	  	imgTag.append("\">");

        /*
         *
         */

        if(graph.isCreateImageMap())
        {
        	imageMap = GeneralChartUtilities.getImageMap(graph.getImgName() , graph.getChartRenderingInfo());
        	imgTag.append(imageMap);
        }

        imgTag.append(" <img src=\"");
        //imgTag.append(params.getContextPath());
        //imgTag.append("/servlet/DisplayChart?filename=");
        imgTag.append( DictionaryManager.getInstance("host", graph.getOrganizationId()).getProperty("item-image-url", "/") );
        imgTag.append("/"+ graph.getImgName());
        imgTag.append("\" width=\"" + graph.getWidth() + "\" height=\"" + graph.getHeigth() + "\" border=\"0\" usemap=\"#");
        //imgTag.append("\" width=\"500\" height=\"500\" border=\"0\" usemap=\"#");
        imgTag.append(graph.getImgName());
        imgTag.append("\"/>");
        imgTag.append(" </graph>");

        //params.setDataset(null);
        //params.setImgName(null);
        return imgTag.toString();
    }

    public static String getImageMap(String name, ChartRenderingInfo info)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<MAP NAME=\"" + name + "\">");
        //sb.append(System.getProperty("line.separator"));
        EntityCollection entities = info.getEntityCollection();
        if (entities != null)
        {
            Iterator iterator = entities.iterator();
            while (iterator.hasNext())
            {
                ChartEntity entity = (ChartEntity) iterator.next();
                String area = entity.getImageMapAreaTag(new StandardToolTipTagFragmentGenerator(),
                        new StandardURLTagFragmentGenerator());
                if (area.length() > 0)
                {
                    sb.append(area);
                }
            }
        }
        sb.append("</MAP>");
        //pw.println(sb.toString());
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
