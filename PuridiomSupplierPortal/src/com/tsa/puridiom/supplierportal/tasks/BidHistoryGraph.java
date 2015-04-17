package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsa.puridiom.entity.RfqBidHistoryPK;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.RfqVendorPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.File;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

public class BidHistoryGraph extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			List rfqBidHistoryList=(List)incomingRequest.get("bidHistoryList");
			List rfqBidVendorList=(List) incomingRequest.get("rfqBidVendorList");
			ArrayList A1=new ArrayList();
			ArrayList[]AL=new ArrayList[Integer.valueOf("2").intValue()];
			List VendorList = new ArrayList();

			for(int j1=0; j1<rfqBidVendorList.size(); j1++)
			{
				RfqVendor bidVen = (RfqVendor) rfqBidVendorList.get(j1);
				RfqVendorPK bidVenPK = bidVen.getComp_id();
				VendorList.add(bidVenPK.getVendorId());
				//String s_vendor= bidVenPK.getVendorId();

			}

			for(int k=0;k<VendorList.size();k++)
			{
				for (int i = 0; i < rfqBidHistoryList.size(); i++)
				{
					RfqBidHistory bidHis = (RfqBidHistory) rfqBidHistoryList.get(i);
					RfqBidHistoryPK bidHisPK = bidHis.getComp_id();
					String s_ven = (String) bidHisPK.getVendorId();
					if(s_ven.equalsIgnoreCase((String) VendorList.get(k)))
					{

						A1.add(bidHis.getUnitPrice());
					}
				}
				AL[k]=A1;
				A1=new ArrayList();
			}

			ViewGraph demo = new ViewGraph();
			final CategoryDataset dataset = demo.createDataset(AL);
	        JFreeChart chart = demo.createChart(dataset);
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(500, 270));
	        String oid = (String)incomingRequest.get("organizationId");
	        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String nameIt = ukg.getUniqueKey().toString();
            nameIt = DictionaryManager.getInstance("host", oid).getProperty("reportsOut", "") + nameIt + ".png";
            File file = new File(nameIt);
	        ChartUtilities.saveChartAsPNG(file, chart, 500, 300);
	        result = nameIt;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}



	public class ViewGraph {

	    public ViewGraph()
	    {

	    }

	    private CategoryDataset createDataset(ArrayList[] SeriesList)
	    {
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (int i = 0; i < SeriesList.length; i++)
		    {
	        	int x=i+1;
	        	String serie1="Supplier"+ x;
	        	ArrayList A2=new ArrayList();
	        	A2=SeriesList[i];
	        	if(SeriesList[i] != null)
	        	{
		        	for(int j=0;j<A2.size();j++)
		        	{
		        		int y=j+1;
		        		String type="M"+ j;
		        		dataset.addValue((Number)A2.get(j), serie1, type);
		        	}
	        	}
		    }
	        return dataset;
	    }

		//Line Chart Demo3, only lines
	    private JFreeChart createChart(final CategoryDataset dataset) {
	        // create the chart...
	        final JFreeChart chart = ChartFactory.createLineChart(
	            "My Bid-History Graph",    // chart title
	            "Moments",                 // domain axis label
	            "Prices",                  // range axis label
	            dataset,                   // data
	            PlotOrientation.VERTICAL,  // orientation
	            true,                      // include legend
	            true,                      // tooltips
	            false                      // urls
	        );

	        chart.setBackgroundPaint(Color.white);
	        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);

	        // customise the range axis...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setAutoRangeIncludesZero(true);

	        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();



	        renderer.setSeriesStroke(
	            0, new BasicStroke(
	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
	                1.0f, new float[] {10.0f, 6.0f}, 0.0f
	            )
	        );
	        renderer.setSeriesStroke(
	            1, new BasicStroke(
	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
	                1.0f, new float[] {6.0f, 6.0f}, 0.0f
	            )
	        );
	        renderer.setSeriesStroke(
	            2, new BasicStroke(
	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
	                1.0f, new float[] {2.0f, 6.0f}, 0.0f
	            )
	        );
	        return chart;
	    }
	}
}