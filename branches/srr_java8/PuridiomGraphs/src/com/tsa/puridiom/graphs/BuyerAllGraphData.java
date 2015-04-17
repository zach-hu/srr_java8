/*
 * Created on Dec 8, 2004
 *
 */
package com.tsa.puridiom.graphs;

import java.awt.Paint;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.demo.servlet.NoDataException;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * @author renzo
 *
 */

public class BuyerAllGraphData extends BuyerGraphData
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
    /**
     * @param organizationId
     * @param userId
     */
    public BuyerAllGraphData(String organizationId, String userId)
    {
        super(organizationId, userId);

    }

    public BuyerAllGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("poCount");
        this.setPoData(tempList);
        tempList = (List)incomingRequest.get("reqCount");
        this.setReqData(tempList);
        tempList = (List)incomingRequest.get("vendorCount");
        this.setVendorData(tempList);
    }


    private JFreeChart formatStackedBarChart(JFreeChart chart)
    {/*
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        CategoryAxis categoryAxis = categoryplot.getDomainAxis();
        categoryAxis.setVisible(false);
        categoryAxis.setAxisLinePaint(Color.white);
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setFixedDimension(25);

        ///*categoryAxis.setTickLabelFont(new Font("Verdana", Font.BOLD, 6));
		categoryAxis.setCategoryMargin(0);
		categoryAxis.setTickLabelsVisible(true);
		categoryAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 9));
		//

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setVisible(false);
		StackedBarRenderer renderer = (StackedBarRenderer)categoryplot.getRenderer();
		renderer.setMaxBarWidth(5);
		renderer.setItemURLGenerator(new JavaScriptUrl());
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        renderer.setItemMargin(10);
        LabelGenerator labelGen = new LabelGenerator();

        renderer.setLabelGenerator(labelGen);
		renderer.setItemLabelsVisible(true);
		renderer.setItemLabelFont(new Font("Verdana", Font.PLAIN, 9));

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
        List poCount = this.getPoData();

        if (poCount.size() == 0)
        {
			System.out.println("No data has been found");
			throw new NoDataException();
		}

	 	//  Create and populate a CategoryDataset
		Iterator iter = poCount.listIterator();

        //DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double ad[][] = new double[poCount.size()][3];
        int i = 0;
        String series[] = new String[poCount.size()];
        String category[] = {"1", "Pos", "2"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            ad[i][1] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = this.status((String)valuesTemp[1]);

            i++;
		}

        return DatasetUtilities.createCategoryDataset(series, category, ad);
        //dataset.addValue(new Integer(0), "Reqs", "req1");
        //return dataset;
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

    private String writeChart(JFreeChart chart, HttpSession session, PrintWriter pw, int h, int w) throws IOException
    {
//      Write the chart image to the temporary directory
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

		String filename = ServletUtilities.saveChartAsPNG(chart, h, w, info, session);

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

    public LegendItemCollection createLegendItems()
    {
        LegendItemCollection legenditemcollection = new LegendItemCollection();
        /*LegendItem legenditem = new LegendItem("Pendig Order", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 34, 255));
        LegendItem legenditem1 = new LegendItem("Awarded", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 255, 34));
        LegendItem legenditem2 = new LegendItem("Recdeived", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 34, 34));
        LegendItem legenditem3 = new LegendItem("Allied", "-", Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 255, 34));
        legenditemcollection.add(legenditem);
        legenditemcollection.add(legenditem1);
        legenditemcollection.add(legenditem2);
        legenditemcollection.add(legenditem3);
        */
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
}
