package com.tsa.puridiom.supplierportal.graphs.tasks;

import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class VendorItemBidHistory extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String oid = (String)incomingRequest.get("organizationId");
		try
		{
			List rfqbidhistorylist = (List)incomingRequest.get("rfqBidHistoryList");
			System.out.println("listL " + rfqbidhistorylist);
			String vendorId = (String)incomingRequest.get("RfqBidHistory_vendorId ");
			if(Utility.isEmpty(vendorId)){	vendorId = "";	}
			if(rfqbidhistorylist != null)
			{
				if(rfqbidhistorylist.size() < 1)
				{
					rfqbidhistorylist.add(new RfqBidHistory());
				}
				JFreeChart chart = this.createChart(this.createDataset(rfqbidhistorylist, vendorId));
				result = this.writeGraph(oid, chart);

			}
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e) {
			this.setStatus(Status.FAILED);
		}
		// TODO Auto-generated method stub
		return result;
	}

	private String writeGraph(String oid, JFreeChart chart)
	{

        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
        String nameIt = ukg.getUniqueKey().toString();
        nameIt = DictionaryManager.getInstance("host", oid).getProperty("reportsOut", "") + nameIt + ".png";
        File file = new File(nameIt);
        System.out.println("nameit: " + nameIt);

        try {
			ChartUtilities.saveChartAsPNG(file, chart, 500, 300);
		} catch (IOException e) {
			Log.error(this, "bid history was not produced");
			e.printStackTrace();
		}
        return nameIt;
	}
	private JFreeChart createChart(XYDataset dataset)
	{
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Bid History",  // title
	            "Bid",             // x-axis label
	            "Unit Price",   // y-axis label
	            dataset,            // data
	            false,               // create legend?
	            false,               // generate tooltips?
	            false               // generate URLs?
	        );

		chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setShapesVisible(true);
            renderer.setShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        axis.setTickLabelsVisible(false);
        return chart;
	}
	private XYDataset createDataset(List rfqbidhistorylist, String vendorId)
	{
		System.out.println("dataset");
        TimeSeries s1 = new TimeSeries(vendorId, Month.class);
        for(int i = 0; i < rfqbidhistorylist.size(); i++)
        {
        	RfqBidHistory bid = (RfqBidHistory)rfqbidhistorylist.get(i);
        	System.out.println("bid " + i);
        	s1.add(new Month(i +1, 2006), bid.getUnitPrice());
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }


}
