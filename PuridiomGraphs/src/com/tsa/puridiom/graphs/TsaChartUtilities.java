/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.awt.GradientPaint;

import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.Legend;
//import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
//import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
// import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformType;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TsaChartUtilities
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

    public static JFreeChart createBarChart(GraphParams params)
    {

        JFreeChart jfreechart = ChartFactory.createBarChart(params.getTitle(),
        			params.getXTitle(),
        			params.getYTitle(),
        			params.getDataset(),
        			PlotOrientation.HORIZONTAL,
        			true,
        			true,
        			false);

        return TsaChartUtilities.formatBarChart(jfreechart, params);
        //return jfreechart;
    }

    public static JFreeChart createVerticalBarChart(GraphParams params)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart(params.getTitle(),
        			params.getXTitle(),
        			params.getYTitle(),
        			params.getDataset(),
        			PlotOrientation.VERTICAL,
        			true,
        			true,
        			false);

        CategoryPlot plot = jfreechart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );

        return TsaChartUtilities.formatBarChart(jfreechart, params);
    }

 static JFreeChart createLineChart(CategoryDataset dataset,GraphParams params) {

        // create the chart...

        JFreeChart chart = ChartFactory.createLineChart(params.getTitle(), params.getXTitle(), params.getYTitle(), dataset, PlotOrientation.VERTICAL, true, false, false);

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // customise the renderer...
        LineAndShapeRenderer renderer
            = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setFillPaint(Color.white);

        renderer.setSeriesFillPaint(0, Color.red);
        renderer.setSeriesFillPaint(1, Color.white);
        renderer.setUseFillPaint(true);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.61);
        domainAxis.setMaximumCategoryLabelWidthRatio((float) 0.61);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        return chart;
    }

    public static JFreeChart createTimeSeriesChart(XYDataset dataset, GraphParams params)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(params.getTitle(), params.getXTitle(), params.getYTitle(), dataset, true, false, false);
		jfreechart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) jfreechart.getPlot();
	    plot.setBackgroundPaint(Color.white);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    plot.setDomainCrosshairVisible(true);
	    plot.setRangeCrosshairVisible(true);

	    XYItemRenderer r = plot.getRenderer();
		        if (r instanceof XYLineAndShapeRenderer) {
		            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
		            renderer.setBaseShapesVisible(true);
		            renderer.setBaseShapesFilled(true);
		        }

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		return jfreechart;
	}

    static DefaultCategoryDataset createDatasetLineChart(List Data,String variable) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i = 0; i < Data.size(); i++) {
    	List vendorValuesList = (List) Data.get(i);
    	List valuesList = (List) vendorValuesList.get(1);
    	List valuesList2 = (List) vendorValuesList.get(2);
    	for(int j = 0; j < valuesList.size(); j++)
            {
    		Number a=(Number)valuesList.get(j);
    		String aa= valuesList2.get(j).toString();
    		dataset.addValue(a, vendorValuesList.get(0).toString(), aa);
            }
    	}
         return dataset;
    }

    public static XYDataset createDatasetTimeSeries(List Data,String variable) {
    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	for(int i = 0; i < Data.size(); i++) {
    		List vendorValuesList = (List) Data.get(i);
    		TimeSeries s1 = new TimeSeries((String) vendorValuesList.get(0), Date.class);
    		List valuesList = (List) vendorValuesList.get(1);
    		List valuesList2 = (List) vendorValuesList.get(2);
    		for(int j = 0; j < valuesList.size(); j++)
            {
            	s1.add((RegularTimePeriod) valuesList2.get(j),(Number) valuesList.get(j));
            }
            dataset.addSeries(s1);
            dataset.setDomainIsPointsInTime(true);
    	}
        return dataset;
    }

    public static JFreeChart createPieChart(GraphParams params)
    {
        PieParams pie = (PieParams)params;
        /*JFreeChart jfreechart = ChartFactory.createBarChart3D(
                pie.getTitle(),
                pie.getXTitle(),
                pie.getYTitle(),
                pie.getPieDataset(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
                */
        JFreeChart jfreechart = ChartFactory.createPieChart3D(
                pie.getTitle(),  // chart title
                pie.getPieDataset(),                // data
                false,                   // include legend
                true,	// include tooltips
                true
            );


        return TsaChartUtilities.formatPieChart(jfreechart, params);
    }

    public static JFreeChart formatPieChart(JFreeChart jfreechart, GraphParams params)
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
        if (params.getFormType().equals("spendbycommodity")) {
        	CommodityPieUrlGenerator pieUrlGenerator = new CommodityPieUrlGenerator();
        	pieUrlGenerator.setCommodityCodeList(params.getDataCodeList());
        	plot.setURLGenerator(pieUrlGenerator);
        } else {
            plot.setURLGenerator(new HiltonPieUrlGenerator());
        }        
        //plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}") );
        plot.setLabelBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.white);
        plot.setLabelFont(new Font("Verdana", 0, 8));
        
//        plot.setLabelGenerator(new StandardPieItemLabelGenerator(
//        		"{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

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


        return jfreechart;

//        return jfreechart;
    }
    public static JFreeChart formatBarChart(JFreeChart chart, GraphParams params)
    {
        CategoryPlot categoryplot = chart.getCategoryPlot();
        categoryplot.setNoDataMessage("No Information Available");

        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setDomainGridlinePaint(Color.white);

        Color colors2[] = null ;
        Paint colors1[] = null;
        if(params.getFormType().equalsIgnoreCase("req"))
        {
        	colors1 =  new Paint[] {Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue };
        	colors2 =  new Color[] {Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue };
        }
        else
        {
        	colors1 =  new Paint[] {Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.red };
        	colors2 =  new Color[] {Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.red };
        }

        TsaChartUtilities utils = new TsaChartUtilities();
        CustomBarRenderer3D renderer =new CustomBarRenderer3D(colors1);

        for (int ix = 0; ix < 2; ix++) {
        	GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, colors2[ix], 0.0f, 0.0f, new Color(64, 0, 0));
        	renderer.setSeriesPaint(ix, gp0);
        }

        renderer.setDrawBarOutline(false);

        renderer.setMaximumBarWidth(10) ;
//        setMaxBarWidth(0.10);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));

        //renderer.setSeriesPaint(1,new Color(153,51,102)); // second bar
        if(params.getFormType().equalsIgnoreCase("req"))
        {
        	JavaScriptReqUrl javaScriptReqUrl = new JavaScriptReqUrl();
        	javaScriptReqUrl.setOrganizationId(params.getOid());
        	renderer.setItemURLGenerator(javaScriptReqUrl);
        }
        else if(params.getFormType().equalsIgnoreCase("rfq"))
        {
        	renderer.setItemURLGenerator(new JavaScriptRfqUrl());
        }
        else if(params.getFormType().equalsIgnoreCase("po"))
        {
        	renderer.setItemURLGenerator(new JavaScriptPoUrl());
        }
        else if(params.getFormType().equalsIgnoreCase("vch"))
        {
        	JavaScriptInvoiceUrl javaScriptInvoiceUrl = new JavaScriptInvoiceUrl();
        	javaScriptInvoiceUrl.setOrganizationId(params.getOid());
        	renderer.setItemURLGenerator(javaScriptInvoiceUrl);
        }
        else if(params.getFormType().equalsIgnoreCase("commodity"))
        {
        	//renderer.setItemURLGenerator(new JavaScriptCommdoUrl());
        }

        else if(params.getFormType().equalsIgnoreCase("spend"))
        {
        	//	Spend By Supplier	//
        	JavaScriptPoByUrl jsUrl = new JavaScriptPoByUrl();
        	jsUrl.setLink("viewOrdersBySupplier");
        	renderer.setItemURLGenerator(jsUrl);
        }
        else if(params.getFormType().equalsIgnoreCase("spendbycommodity"))
        {
        	JavaScriptPoByUrl jsUrl = new JavaScriptPoByUrl();
        	jsUrl.setLink("viewOrdersByCommodity");
        	renderer.setItemURLGenerator(jsUrl);
        }
        else if(params.getFormType().equalsIgnoreCase("spendbydepartment"))
        {
        	JavaScriptPoByUrl jsUrl = new JavaScriptPoByUrl();
        	jsUrl.setLink("viewOrdersByDepartment");
        	renderer.setItemURLGenerator(jsUrl);
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
        NumberAxis rangeAxis = (NumberAxis) categoryplot.getRangeAxis();
        if (params.getFormType().startsWith("spend")) {
        	int tickUnits = 1000;
        	if (params.getTickUnits() > 0) {
        		tickUnits = params.getTickUnits();
        	}
            rangeAxis.setTickUnit(new NumberTickUnit(tickUnits, CurrencyManager.getInstance(params.getOid()).getCurrencyFormat("")));
        } else {
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }
        categoryplot.setRangeAxis(rangeAxis);
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


        //CustomCategoryPlot categoryplot = (CustomCategoryPlot)plot;

        //CategoryAxis categoryAxis = categoryplot.getDomainAxis();
        /*categoryAxis.setVisible(true);
        categoryAxis.setAxisLinePaint(Color.white);
        categoryAxis.setAxisLineVisible(false);
        //categoryAxis.setFixedDimension(25);
        categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 9));
        //categoryAxis.setCategoryMargin(0);
        categoryAxis.setTickLabelsVisible(true);
        categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 6));
        categoryAxis.setLabelPaint(Color.white);
        categoryAxis.setAxisLinePaint(Color.white);
        */
        //categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);


        //categoryplot.setDomainAxis(categoryAxis);

        //ValueAxis valueAxis = categoryplot.getRangeAxis();
        //valueAxis.setAutoRange(false);
        //valueAxis.setVisible(false);
        //valueAxis.setAxisLinePaint(Color.white);
        //categoryplot.setRangeAxis(valueAxis);

        //NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        //numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //numberaxis.setAxisLineVisible(true);
        //categoryplot.setRangeAxis(valueAxis);

        //BarRenderer renderer = (BarRenderer)categoryplot.getRenderer();
        //CustomBarRenderer renderer = new CustomBarRenderer(new Paint[] {
        //        Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue
         //   });

        renderer.setItemURLGenerator(new JavaScriptUrl());
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        renderer.setItemMargin(10);
        categoryplot.setRenderer(renderer);
        //LabelGenerator labelGen = new LabelGenerator();

        //renderer.setLabelGenerator(labelGen);
        //renderer.setItemLabelsVisible(true);
        //renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 9));
        //ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, 0);

        //renderer.setPositiveItemLabelPosition(itemlabelposition);

        //categoryplot.setForegroundAlpha(1.0f);

        // left align the category labels...
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

        /*StandardLegend legend = (StandardLegend)chart.getLegend();
        //Legend legend = new Legend();
        //legend.
        legend.setOutlinePaint(Color.white);
        legend.setAnchor(Legend.SOUTH);
        legend.setItemFont(new Font("Verdana", Font.PLAIN, 9));
        legend.setPreferredWidth(100);
        legend.setOutlinePaint(Color.white);
        legend.setBackgroundPaint(Color.white);
        chart.setLegend(legend);
        */
        TextTitle chartTitle = chart.getTitle();
        chartTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    public static GraphParams writeChartFile(GraphParams params, JFreeChart chart)
    {
        //      Write the chart image to the temporary directory
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        String filename = "";
        try
        {
            //filename = ServletUtilities.saveChartAsPNG(chart, params.getWidth(), params.getHeigth(), info, params.getSession());
            //filename = ServletUtilities.saveChartAsJPEG(chart, params.getWidth(), params.getHeigth(), info, params.getSession());
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String nameIt = ukg.getUniqueKey().toString();
            nameIt = DictionaryManager.getInstance("host", params.getOid()).getProperty("reportsOut", "") + nameIt + ".png";
            File file = new File(nameIt);
            ChartUtilities.saveChartAsPNG(file, chart, params.getWidth()-10, params.getHeigth()-10, info);
            params.setChartRenderingInfo(info);
            //          Write the image map to the PrintWriter
            //String imageMap = TsaChartUtilities.getImageMap(filename, info);
            params.setImgName(file.getName());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            params.getWriter().flush();
        }

        return params;
    }

    public static GraphParams writeChart(GraphParams params, JFreeChart chart)
    {
        //      Write the chart image to the temporary directory
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        String filename = "";
        try
        {
            filename = ServletUtilities.saveChartAsPNG(chart, params.getWidth(), params.getHeigth(), info, params.getSession());
            //filename = ServletUtilities.saveChartAsJPEG(chart, params.getWidth(), params.getHeigth(), info, params.getSession());

            params.setChartRenderingInfo(info);
            //          Write the image map to the PrintWriter
            params.setImgName(filename);
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
            params.getWriter().flush();
        }

        return params;
    }

    public static CategoryDataset createCategoryDataset(List data)
    {
        //  Create and populate a CategoryDataset
        Iterator iter = data.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double ad[][] = new double[data.size()][1];
        int i = 0;
        String series[] = new String[data.size()];
        String category[] = {"Pos"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            ad[i][0] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM");

            i++;
        }

        return DatasetUtilities.createCategoryDataset(series, category, ad);
    }

    public static String status(String status, String oid)
    {
        return DocumentStatus.toString(status, oid);
    }

    public static String poType(String type)
    {
        return OrderType.toString(type, "PURIDIOM");
    }

    public static String poType(String type, String oid)
    {
        return OrderType.toString(type, oid);
    }

    public static String getImg(GraphParams params)
    {
        return getBarImg(params);
    }

    public static String getBarImg(GraphParams params)
    {
        JFreeChart chart = TsaChartUtilities.createBarChart(params);
        return TsaChartUtilities.formatMe(params, chart);
    }

    public static String getVerticalBarImg(GraphParams params)
    {
        JFreeChart chart = TsaChartUtilities.createVerticalBarChart(params);
        return TsaChartUtilities.formatMe(params, chart);
    }

    public static String getPieImg(GraphParams params)
    {
        JFreeChart chart = TsaChartUtilities.createPieChart(params);

        return TsaChartUtilities.formatMe(params, chart);
    }

    public static String formatMe(GraphParams params, JFreeChart chart)
    {
        params = TsaChartUtilities.writeChart(params, chart);
        String temp = TsaChartUtilities.getHtmlSrc(params.getImgName(), params);

        return temp;
    }
    public static String getHtmlSrc(String filename, GraphParams params)
    {
        //String imageMap = TsaChartUtilities.getImageMap(filename, params.getChartRenderingInfo());
        String imageMap = "";

        if(params.getCreateImageMap())
        {
        	imageMap = TsaChartUtilities.getImageMap(filename, params.getChartRenderingInfo());
        }


        StringBuffer imgTag = new StringBuffer(imageMap);
        imgTag.append(" <img src=\"");
        imgTag.append(params.getContextPath());
        imgTag.append("/servlet/DisplayChart?filename=");
        imgTag.append(filename);
        imgTag.append("\" width=\"" + params.getWidth() + "\" height=\"" + params.getHeigth() + "\" border=\"0\" usemap=\"#");
        //imgTag.append("\" width=\"500\" height=\"500\" border=\"0\" usemap=\"#");
        imgTag.append(filename);
        imgTag.append("\">");

        params.setDataset(null);
        params.setImgName(null);
        return imgTag.toString();
    }

    public static String getHtmlFileSrc(String filename, GraphParams params)
    {
        String imageMap = TsaChartUtilities.getImageMap(filename, params.getChartRenderingInfo());

        /*StringBuffer graphURL = new StringBuffer(imageMap);
        graphURL.append(" " + params.getContextPath());
        graphURL.append("/servlet/DisplayChart?filename=");
        graphURL.append(filename);
        */
        StringBuffer imgTag = new StringBuffer(imageMap);
        imgTag.append(" <img src=\"");
        imgTag.append(params.getContextPath());
        imgTag.append("/");
        //imgTag.append("/servlet/DisplayChart?filename=");
        filename = DictionaryManager.getInstance("host", params.getOid()).getProperty("reportsDisplay", "") + filename;

        imgTag.append(filename);
        imgTag.append("\" width=\"" + (params.getWidth() + 10) + "\" height=\"" + (params.getHeigth() + 10) + "\" border=\"0\" usemap=\"#");
        //imgTag.append("\" width=\"500\" height=\"500\" border=\"0\" usemap=\"#");
        imgTag.append(filename);
        imgTag.append("\">");

        params.setDataset(null);
        params.setImgName(null);
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
                    //sb.append(System.getProperty("line.separator"));
                }
            }
        }
        sb.append("</MAP>");
        //pw.println(sb.toString());
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public static JFreeChart formatStackedBarChart(JFreeChart chart)
    {
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        /*CategoryAxis categoryAxis = categoryplot.getDomainAxis();
        categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 6));
        categoryAxis.setCategoryMargin(0);
        categoryAxis.setTickLabelsVisible(true);
        categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 9));
        */

        ValueAxis valueAxis = categoryplot.getRangeAxis();
        valueAxis.setVisible(false);
        BarRenderer renderer = new BarRenderer();
        /*CustomRenderer renderer = new CustomRenderer(new Paint[] {
                Color.red, Color.yellow, Color.green
            });
            */
        renderer.setItemURLGenerator(new StandardCategoryURLGenerator("temp.jsp","series","section"));
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        //LabelGenerator labelGen = new LabelGenerator();

        //renderer.setLabelGenerator(labelGen);
        renderer.setItemLabelsVisible(true);
        renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 9));

        //categoryplot.setDomainAxis(categoryAxis);
        categoryplot.setRangeAxis(valueAxis);
        categoryplot.setRenderer(renderer);


        return chart;
    }

    public static String getFormattedCurrency(Object object, String currencyCode, String organizationId) {
    	return HiltonUtility.getFormattedCurrency(object, currencyCode, organizationId);
    }
}
