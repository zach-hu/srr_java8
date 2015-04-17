package com.tsa.puridiom.supplierportal.graphs.tasks;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.math.BigDecimal;
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
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.RfqVendorPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;



public class BidHistoryVendorGraph extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		int j = 0;
		BigDecimal temp1=new BigDecimal(0);
		try
		{
			List rfqBidHistoryList=(List) incomingRequest.get("bidHistoryVendorList");
			String s_qty = (String) incomingRequest.get("qty");
			List rfqBidVendorList=(List) incomingRequest.get("rfqBidVendorList");
			List VendorList = new ArrayList(Integer.valueOf(s_qty).intValue());
			List SequenceList = new ArrayList(Integer.valueOf(s_qty).intValue());
			ArrayList A1=new ArrayList();
			ArrayList[]AL=new ArrayList[Integer.valueOf(s_qty).intValue()];

			for(int j1=0;j1<rfqBidVendorList.size();j1++)
			{
				RfqVendor bidVen = (RfqVendor) rfqBidVendorList.get(j1);
				RfqVendorPK bidVenPK = bidVen.getComp_id();
				String s_vendor= bidVenPK.getVendorId();
				VendorList.add(s_vendor);
			}

			for(int k1=0;k1<VendorList.size();k1++){
					String s_vendor=(String) VendorList.get(k1);
					String s_seq="0";
					for(int h1=0;h1<rfqBidHistoryList.size();h1++)
					{
						RfqBidHistory bidHis = (RfqBidHistory) rfqBidHistoryList.get(h1);
						RfqBidHistoryPK bidHisPK = bidHis.getComp_id();
						String	s_vendor1 = (String)bidHisPK.getVendorId();
						String s_sequence = bidHisPK.getBidSequence().toString();
						if(s_vendor.equalsIgnoreCase(s_vendor1)&& Integer.valueOf(s_sequence).intValue()>Integer.valueOf(s_seq).intValue())
							{
        					s_seq = s_sequence;
							}
					}
					SequenceList.add(s_seq);
					temp1=new BigDecimal(0);
				}

			for(int k=0;k<VendorList.size();k++)
			{
				String tam_seq=(String)SequenceList.get(k);
				for(int h=0;h<Integer.valueOf(tam_seq).intValue();h++)
				{
					for(int g=0;g<rfqBidHistoryList.size();g++)
					{
					RfqBidHistory bidHis = (RfqBidHistory) rfqBidHistoryList.get(g);
					RfqBidHistoryPK bidHisPK = bidHis.getComp_id();
					BigDecimal s_price=bidHis.getUnitPrice();
					String s_seq=bidHisPK.getBidSequence().toString();
					String s_ven=bidHisPK.getVendorId();
					if((Integer.valueOf(s_seq).intValue())==h+1 && s_ven.equalsIgnoreCase((String) VendorList.get(k)))
						{
							temp1 = temp1.add(bidHis.getUnitPrice()) ;
						}
					}
					A1.add(temp1);
					temp1=new BigDecimal(0);
				}
				AL[k]=A1;
				A1=new ArrayList();
			}

			GraphView demo = new GraphView();
			final CategoryDataset dataset = demo.createDataset(AL);
	        final JFreeChart chart = demo.createChart(dataset);
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        String oid = (String)incomingRequest.get("organizationId");
	        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String nameIt = ukg.getUniqueKey().toString();
            nameIt = DictionaryManager.getInstance("host", oid).getProperty("reportsOut", "") + nameIt + ".png";
            File file = new File(nameIt);
            System.out.println("nameit: " + nameIt);
            result = nameIt;
	        ChartUtilities.saveChartAsPNG(file, chart, 500, 300);
	        chartPanel.setPreferredSize(new Dimension(500, 270));
	        this.status = Status.SUCCEEDED;

		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}



	public class GraphView {

	    public GraphView()
	    {

	    }
	    private CategoryDataset createDataset(ArrayList[] SeriesList)
	    {
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    	System.out.println("serieslist: " + SeriesList);
	        for (int i = 0; i < SeriesList.length; i++)
		    {
	        	int x=i+1;
	        	String serie1="Supplier"+ x;
	        	ArrayList A2=new ArrayList();
	        	System.out.println("serieslist[" + i + "]" + SeriesList[i]);
	        	if(SeriesList[i] != null)
	        	{
		        	A2=SeriesList[i];
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
