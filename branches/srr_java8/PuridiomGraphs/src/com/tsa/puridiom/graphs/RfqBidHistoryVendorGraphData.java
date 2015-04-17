/*
 * Created on Dec 8, 2004
 *
 */
package com.tsa.puridiom.graphs;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.demo.servlet.NoDataException;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 */

public class RfqBidHistoryVendorGraphData
{
    private List Data = null;
    private String variable = null;
    private String variable1 = null;

    public List getVendorData()
    {
        return Data;
    }

    public void setVendorData(List Data,String variable, String variable1)
    {
        this.Data = Data;
        this.variable = variable;
        this.variable1 = variable;
     }

    public RfqBidHistoryVendorGraphData(List vendorData,String oid, String vendorName)
    {
    	this.setVendorData(vendorData,oid,vendorName);
    }

    public String getTimeSeriesChart(GraphParams params)
    {
    	Object result = null;
    	params.setImgName("");
    	XYDataset vendor_dataset = TsaChartUtilities.createDatasetTimeSeries(Data,variable);
    	JFreeChart jfreechart = TsaChartUtilities.createTimeSeriesChart(vendor_dataset,params);
        ChartPanel chartPanel = new ChartPanel(jfreechart);
        result = TsaChartUtilities.formatMe(params, jfreechart);
        return (String) result;
    }

    public String getLineChart(GraphParams params)
    {
    	Object result = null;
    	params.setImgName("");
    	DefaultCategoryDataset vendor_dataset = TsaChartUtilities.createDatasetLineChart(Data,variable);
        JFreeChart jfreechart = TsaChartUtilities.createLineChart(vendor_dataset,params);
        ChartPanel chartPanel = new ChartPanel(jfreechart);
        result = TsaChartUtilities.formatMe(params, jfreechart);
        return (String) result;
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
}
