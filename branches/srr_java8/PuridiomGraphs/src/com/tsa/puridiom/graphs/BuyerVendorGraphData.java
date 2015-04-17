/*
 * Created on Dec 8, 2004
 *
 */
package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
//import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.demo.servlet.NoDataException;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
//import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

/**
 * @author renzo
 *
 */

public class BuyerVendorGraphData
{
    private List poData = null;
    private List reqData = null;
    private List vendorData = null;

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

    static class CustomBarRenderer extends BarRenderer
    {
        public Paint getItemPaint(int i, int j)
        {
            return colors[i % colors.length];
        }

        private Paint colors[];

        public CustomBarRenderer(Paint apaint[])
        {
            colors = apaint;
        }
    }
    static class CustomRenderer extends StackedBarRenderer
    {
        public Paint getItemPaint(int i, int j)
        {
            return colors[i % colors.length];
        }

        private Paint colors[];

        public CustomRenderer(Paint apaint[])
        {
            colors = apaint;
        }
    }


    public BuyerVendorGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("poCount");
        this.setPoData(tempList);
        tempList = (List)incomingRequest.get("reqCount");
        this.setReqData(tempList);
        tempList = (List)incomingRequest.get("vendorCount");
        this.setVendorData(tempList);
    }

    public String getPoBarChart(GraphParams params)
    {
        params.setDataset(TsaChartUtilities.createCategoryDataset(this.getPoData()));

        return TsaChartUtilities.getImg(params);

    }
    private JFreeChart formatBarChart(JFreeChart chart)
    {
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        CategoryAxis categoryAxis = categoryplot.getDomainAxis();
        categoryAxis.setVisible(false);
        categoryAxis.setAxisLinePaint(Color.white);
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setFixedDimension(25);
        categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 6));
		categoryAxis.setCategoryMargin(0);
		categoryAxis.setTickLabelsVisible(true);
		categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 9));
		categoryplot.setDomainAxis(categoryAxis);

        ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setAutoRange(false);
		valueAxis.setVisible(false);
		categoryplot.setRangeAxis(valueAxis);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		categoryplot.setRangeAxis(valueAxis);
		//BarRenderer renderer = (BarRenderer)categoryplot.getRenderer();
        CustomBarRenderer renderer = new CustomBarRenderer(new Paint[] {
                Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue
            });

        renderer.setItemURLGenerator(new StandardCategoryURLGenerator("temp.jsp","series","section"));
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        //renderer.setItemMargin(10);

        //LabelGenerator labelGen = new LabelGenerator();
        //renderer.setLabelGenerator(labelGen);
		renderer.setItemLabelsVisible(true);
		renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 5));
		ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45D);
		renderer.setPositiveItemLabelPosition(itemlabelposition);
        categoryplot.setRenderer(renderer);
		categoryplot.setOutlinePaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.white);

		categoryplot.setDomainGridlinePaint(Color.white);


		chart.setBackgroundPaint(Color.white);
		chart.setBorderVisible(false);

		//StandardLegend legend = (StandardLegend)chart.getLegend();
		//legend.setOutlinePaint(Color.white);


		return chart;

    }


    private JFreeChart formatStackedBarChart(JFreeChart chart)
    {/*
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        CategoryAxis categoryAxis = categoryplot.getDomainAxis();
        categoryAxis.setVisible(false);
        categoryAxis.setAxisLinePaint(Color.white);
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setFixedDimension(25);

       //categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 6));
		//categoryAxis.setCategoryMargin(0);
		//categoryAxis.setTickLabelsVisible(true);
		//categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 9));

        DecimalFormat decimalformat = new DecimalFormat("##,###");
        decimalformat.setNegativePrefix("(");
        decimalformat.setNegativeSuffix(")");
		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setAutoRange(false);


		valueAxis.setVisible(false);
		StackedBarRenderer renderer = (StackedBarRenderer)categoryplot.getRenderer();
		renderer.setMaxBarWidth(5);
		renderer.setItemURLGenerator(new StandardCategoryURLGenerator("temp.jsp","series","section"));
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        renderer.setItemMargin(10);
        LabelGenerator labelGen = new LabelGenerator();

        renderer.setLabelGenerator(labelGen);
		renderer.setItemLabelsVisible(true);
		renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 5));

		categoryplot.setDomainAxis(categoryAxis);

		categoryplot.setRangeAxis(valueAxis);
		categoryplot.setRenderer(renderer);
		categoryplot.setOutlinePaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.white);

		categoryplot.setDomainGridlinePaint(Color.white);
		chart.setBackgroundPaint(Color.white);
		chart.setBorderVisible(false);

		StandardLegend legend = (StandardLegend)chart.getLegend();
		legend.setOutlinePaint(Color.white);
		*/

		return chart;
    }

    private CategoryDataset createStackedDataset() throws NoDataException
    {
        List count = this.getVendorData();

        if (count.size() == 0)
        {
			System.out.println("No data has been found");
			throw new NoDataException();
		}

	 	//  Create and populate a CategoryDataset
		Iterator iter = count.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double ad[][] = new double[count.size()][3];
        int i = 0;
        String series[] = new String[count.size()];
        String category[] = {"1", "Vendor", "2"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            BigDecimal bd = (BigDecimal)valuesTemp[0];
            if(bd.compareTo(new BigDecimal(10000)) > 0)
            {
                bd = bd.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            }
            ad[i][1] = (bd).doubleValue();
            series[i] = (String)valuesTemp[1];
            System.out.println(ad[i][1]);
            i++;
		}

        return DatasetUtilities.createCategoryDataset(series, category, ad);
        //dataset.addValue(new Integer(0), "Reqs", "req1");
        //return dataset;
    }

    private CategoryDataset createBarDataset() throws NoDataException
    {
        List count = this.getVendorData();

        if (count.size() == 0)
        {
			System.out.println("No data has been found");
			throw new NoDataException();
		}

	 	//  Create and populate a CategoryDataset
		Iterator iter = count.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //double ad[][] = new double[1][count.size()];
        double ad[][] = new double[count.size()][1];
        String series[] = {"Vendor"};
        String categories[] = new String[count.size()];
        for(int i = 0; i < count.size(); i++)
        {
            Object valuesTemp[] = (Object[])count.get(i);
			//dataset.addValue(((BigDecimal)valuesTemp[0]).doubleValue(), "Vendor", (String)valuesTemp[1]);
			ad[i][0] = ((BigDecimal)valuesTemp[0]).doubleValue();
			categories[i] = (String)valuesTemp[1];
		}

        return DatasetUtilities.createCategoryDataset(categories, series, ad);
    }
    private String tempBuildDataSet(HttpSession session, PrintWriter pw, int w, int h)
    {
        String filename = null;
        try
        {
            CategoryDataset dataset = this.createStackedDataset();
            JFreeChart chart = this.createStackedBarChart(dataset);
            filename = this.writeChart(chart, session, pw, h, w);
        }
        catch (NoDataException e)
        {
            System.out.println(e.toString());
			filename = "public_nodata_500x300.png";
        }
        catch (Exception e)
        {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
			filename = "public_error_500x300.png";
		}
		return filename;
    }
    private String tempBarBuildDataSet(HttpSession session, PrintWriter pw, int w, int h)
    {
        String filename = null;
        try
        {
            CategoryDataset dataset = this.createBarDataset();
            JFreeChart chart = this.createBarChart(dataset);
            filename = this.writeChart(chart, session, pw, h, w);
        }
        catch (NoDataException e)
        {
            System.out.println(e.toString());
			filename = "public_nodata_500x300.png";
        }
        catch (Exception e)
        {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
			filename = "public_error_500x300.png";
		}
		return filename;
    }

    private String combinedChart(HttpSession session, PrintWriter pw, int w, int h)
    {
        String filename = null;
        try
        {
            JFreeChart chart = this.combined();
            filename = this.writeChart(chart, session, pw, h, w);
        }
        catch (Exception e)
        {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
			filename = "public_error_500x300.png";
		}
		return filename;
    }

    private String writeChart(JFreeChart chart, HttpSession session, PrintWriter pw, int h, int w) throws IOException
    {
//      Write the chart image to the temporary directory
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

		String filename = ServletUtilities.saveChartAsPNG(chart, h, w, info, session);
		//h,w

		//  Write the image map to the PrintWriter
		ChartUtilities.writeImageMap(pw, filename, info, true);
		pw.flush();
		System.out.println("filename: " + filename);
		return filename;
    }

    private JFreeChart createStackedBarChart(CategoryDataset categorydataset)
    {
        JFreeChart jfreechart = ChartFactory.createStackedBarChart("", "", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);

        return this.formatStackedBarChart(jfreechart);
    }

    private JFreeChart createBarChart(CategoryDataset categorydataset)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart("", "Vendor", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);

        return this.formatBarChart(jfreechart);
        //return jfreechart;
    }
    protected List getDataList(BuyerGetGraphData graphData)
    {
        this.setPoData(graphData.getPoCount());
        this.setReqData(graphData.getReqCount());
        this.setVendorData(graphData.getBuyerCount());

        return this.getPoData();
    }
    public List getPoData()
    {
        return poData;
    }
    private void setPoData(List poData)
    {
        this.poData = poData;
    }
    public List getReqData()
    {
        return reqData;
    }
    private void setReqData(List reqData)
    {
        this.reqData = reqData;
    }
    public List getVendorData()
    {
        return vendorData;
    }
    private void setVendorData(List vendorData)
    {
        this.vendorData = vendorData;
    }

    private LegendItemCollection createLegendItems()
    {
        LegendItemCollection legenditemcollection = new LegendItemCollection();
        /*LegendItem legenditem = new LegendItem("Pendig Order", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 34, 255));
        LegendItem legenditem1 = new LegendItem("Awarded", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 255, 34));
        LegendItem legenditem2 = new LegendItem("Recdeived", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 34, 34));
        LegendItem legenditem3 = new LegendItem("Allied", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 255, 34));
        legenditemcollection.add(legenditem);
        legenditemcollection.add(legenditem1);
        legenditemcollection.add(legenditem2);
        legenditemcollection.add(legenditem3);*/
        return legenditemcollection;
    }

    public String getImg(GraphParams params)
    {
        String filename = this.tempBuildDataSet(params.getSession(), params.getWriter(), params.getHeigth(), params.getWidth());
        StringBuffer graphURL = new StringBuffer(params.getContextPath());
        graphURL.append("/servlet/DisplayChart?filename=");
        graphURL.append(filename);

        StringBuffer imgTag = new StringBuffer("<img src=\"");
        imgTag.append(graphURL);
        imgTag.append("\" width=\"" + params.getWidth() + "\" height=\"" + params.getHeigth() + "\" border=\"0\" usemap=\"#");
        imgTag.append(filename);
        imgTag.append("\">");

        return imgTag.toString();
    }


    public String getBarImg(GraphParams params)
    {
        String filename = this.tempBarBuildDataSet(params.getSession(), params.getWriter(), params.getHeigth(), params.getWidth());
        //String filename = this.tempBuildDataSet(params.getSession(), params.getWriter());
        StringBuffer graphURL = new StringBuffer(params.getContextPath());
        graphURL.append("/servlet/DisplayChart?filename=");
        graphURL.append(filename);

        StringBuffer imgTag = new StringBuffer("<img src=\"");
        imgTag.append(graphURL);
        imgTag.append("\" width=\"" + params.getWidth() + "\" height=\"" + params.getHeigth() + "\" border=\"0\" usemap=\"#");
        //imgTag.append("\" width=\"500\" height=\"500\" border=\"0\" usemap=\"#");
        imgTag.append(filename);
        imgTag.append("\">");

        return imgTag.toString();
    }

    public String getCombinedImg(GraphParams params)
    {
        String filename = this.combinedChart(params.getSession(), params.getWriter(), params.getHeigth(), params.getWidth());

        StringBuffer graphURL = new StringBuffer(params.getContextPath());
        graphURL.append("/servlet/DisplayChart?filename=");
        graphURL.append(filename);

        StringBuffer imgTag = new StringBuffer("<img src=\"");
        imgTag.append(graphURL);
        imgTag.append("\" width=\"" + params.getWidth() + "\" height=\"" + params.getHeigth() + "\" border=\"0\" usemap=\"#");
        //imgTag.append("\" width=\"500\" height=\"500\" border=\"0\" usemap=\"#");
        imgTag.append(filename);
        imgTag.append("\">");

        return imgTag.toString();
    }

    public String tempBuildDataSet(HttpSession session, PrintWriter pw)
    {
        List poCount = this.getVendorData();

        String filename = null;
        try
        {
	        if (poCount.size() == 0)
	        {
				System.out.println("No data has been found");
				throw new NoDataException();
			}

		 	//  Create and populate a CategoryDataset
			Iterator iter = poCount.listIterator();

	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        while (iter.hasNext())
	        {
	            Object valuesTemp[] = (Object[])iter.next();

				dataset.addValue(((BigDecimal)valuesTemp[0]), "Vendor", (String)valuesTemp[1]);
			}

			//  Create the chart object
			CategoryAxis categoryAxis = new CategoryAxis("");
			categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 6));
			categoryAxis.setCategoryMargin(0);
			categoryAxis.setTickLabelsVisible(true);
			categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 9));


			ValueAxis valueAxis = new NumberAxis("");
			valueAxis.setVisible(false);
			//BarRenderer renderer = new BarRenderer();
			CustomRenderer renderer = new CustomRenderer(new Paint[] {
		            Color.red, Color.blue, Color.green
		        });
			renderer.setItemURLGenerator(new StandardCategoryURLGenerator("temp.jsp","series","section"));
	        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
	        //LabelGenerator labelGen = new LabelGenerator();

	        //renderer.setLabelGenerator(labelGen);
			renderer.setItemLabelsVisible(true);
			renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 9));


	        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45D);
	        renderer.setPositiveItemLabelPosition(itemlabelposition);


			CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
			plot.setOutlinePaint(Color.white);
			plot.setRangeGridlinesVisible(false);

			JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
			chart.setBackgroundPaint(java.awt.Color.white);



			//  Write the chart image to the temporary directory
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

			filename = ServletUtilities.saveChartAsPNG(chart, 200, 200, info, session);

			//  Write the image map to the PrintWriter
			ChartUtilities.writeImageMap(pw, filename, info, true);
			pw.flush();
        }
        catch (NoDataException e)
        {
			System.out.println(e.toString());
			filename = "public_nodata_500x300.png";
		}
        catch (Exception e)
        {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
			filename = "public_error_500x300.png";
		}
		return filename;
    }

    private JFreeChart combined()
    {
        JFreeChart jfreechart = null;
        try
        {
	        CategoryDataset categorydataset = createBarDataset();
	        NumberAxis numberaxis = new NumberAxis("Value");
	        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        CustomBarRenderer barrenderer = new CustomBarRenderer(new Paint[] {
	                Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue
	            });
	        CategoryPlot categoryplot = new CategoryPlot(categorydataset, null, numberaxis, barrenderer);
	        categoryplot.setDomainGridlinesVisible(true);

	        CategoryDataset categorydataset1 = createStackedDataset();
	        NumberAxis numberaxis1 = new NumberAxis("Value");
	        numberaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        StackedBarRenderer barrenderer1 = (StackedBarRenderer)categoryplot.getRenderer();

	        barrenderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        CategoryPlot categoryplot1 = new CategoryPlot(categorydataset1, null, numberaxis1, barrenderer1);
	        categoryplot1.setDomainGridlinesVisible(true);

	        CategoryAxis categoryaxis = new CategoryAxis("Category");
	        CombinedDomainCategoryPlot combineddomaincategoryplot = new CombinedDomainCategoryPlot(categoryaxis);
	        combineddomaincategoryplot.add(categoryplot, 2);
	        combineddomaincategoryplot.add(categoryplot1, 1);
	        jfreechart = new JFreeChart("Combined Domain Category Plot Demo", new Font("SansSerif", 1, 12), combineddomaincategoryplot, true);
	        //jfreechart.getLegend().setAnchor(3);
        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return jfreechart;
    }
}
