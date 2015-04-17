package com.tsa.puridiom.supplierportal.graphs.tasks;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
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

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsa.puridiom.entity.RfqBidHistoryPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;



public class BidHistoryGeneralGraph extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		int j = 0;
		try
		{
			List rfqBidHistoryList=(List)incomingRequest.get("bidHistoryGeneralList");
			String s_qty = (String) incomingRequest.get("qty");
			List BidList = new ArrayList(Integer.valueOf(s_qty).intValue());
			ArrayList A1=new ArrayList();
			ArrayList[]AL=new ArrayList[Integer.valueOf(s_qty).intValue()];
			for(int k=0;k<Integer.valueOf(s_qty).intValue();k++)
			{
				BidList.add(incomingRequest.get("edit1"+String.valueOf(k)));
			}

			for (int i = 0; i < BidList.size(); i++)
		    {
				 for(int k=0;k<rfqBidHistoryList.size();k++){
					RfqBidHistory bidHis = (RfqBidHistory) rfqBidHistoryList.get(k);
					RfqBidHistoryPK bidHisPK = bidHis.getComp_id();
				    String	s_code = bidHisPK.getIcRfqLine().toString();
		        	String s_code1 = (String)BidList.get(i);
        			if(s_code.equalsIgnoreCase(s_code1))
        			{
        				A1.add(bidHis.getUnitPrice());
        			}
           		}
        		AL[i]=A1;
        		A1=new ArrayList();
     		}
			VentanaGrafica demo = new VentanaGrafica();
			final CategoryDataset dataset = demo.createDataset(AL);
	        final JFreeChart chart = demo.createChart(dataset);
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(500, 270));
	        ChartUtilities.saveChartAsJPEG(new File("C:/HiltonProjects/workspace/HiltonSupplierPortalWebDemo/web/images/chart.jpg"), chart, 500, 300);
			this.status = Status.SUCCEEDED;

		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}



	public class VentanaGrafica {

	    public VentanaGrafica()
	    {

	    }
	    private CategoryDataset createDataset(ArrayList[] SeriesList)
	    {
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (int i = 0; i < SeriesList.length; i++)
		    {
	        	int x=i+1;
	        	String serie1="Item"+ x;
	        	ArrayList A2=new ArrayList();
	        	A2=SeriesList[i];
	        	for(int j=0;j<A2.size();j++)
	        	{
	        		int y=j+1;
	        		String type="M"+ j;
	        		dataset.addValue((Number)A2.get(j), serie1, type);
	        	}
		    }
	        return dataset;
	    }

	    private JFreeChart createChart(final CategoryDataset dataset) {
	        final JFreeChart chart = ChartFactory.createLineChart(
	            "My Bid-History-General-Graph", // chart title
	            "Moments",                      // domain axis label
	            "Prices",                       // range axis label
	            dataset,                        // data
	            PlotOrientation.VERTICAL,       // orientation
	            true,                           // include legend
	            true,                           // tooltips
	            false                           // urls
	        );

	        chart.setBackgroundPaint(Color.white);

	        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);

	        // customise the range axis...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setAutoRangeIncludesZero(true);

	        // customise the renderer...
	        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
	        //renderer.setDrawShapes(true);
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
	        // OPTIONAL CUSTOMISATION COMPLETED.
	        return chart;
	    }

	}

}
