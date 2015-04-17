/*
 * Created on Dec 8, 2004
 *
 */
package com.tsa.puridiom.graphs;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.demo.servlet.NoDataException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author renzo
 *
 */

public class RfqsByStatusGraphData extends GraphData
{
    private List rfqData = null;

    public List getRfqData()
    {
        return rfqData;
    }

    public void setRfqData(List rfqData)
    {
        this.rfqData = rfqData;
    }

    public String getBarChart(GraphParams params)
    {
        //params.setDataset(null);
        params.setDataset(this.getDataset(params));
        params.setImgName("");
        String temp = TsaChartUtilities.getBarImg(params);

        return temp;

    }

    public String getPieChart(PieParams params)
    {
        params.setDataset(null);
        params.setPieDataset(this.createPieDataset());
        params.setImgName("");
        String temp = TsaChartUtilities.getPieImg(params);

        return temp;

    }
    public RfqsByStatusGraphData(Map incomingRequest)
    {
        this.setRfqData((List)incomingRequest.get("rfqsbystatus"));
    }

    public CategoryDataset getDataset(GraphParams params)
    {
        List data = this.getRfqData();
        if (data == null) data = new java.util.ArrayList() ;

        Iterator iter = data.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //double ad[][] = new double[data.size()][1];
        double ad[][] = new double[1][data.size()];
        String categories[] = {"categories"};
        String series[] = new String[data.size()];

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            ad[0][i] = ((Long)valuesTemp[0]).doubleValue();

            series[i] = TsaChartUtilities.status((String)valuesTemp[1], params.getOid());
            //dataset.addValue(((Integer)valuesTemp[0]).doubleValue(), categories[i], "Requisitions");
            //dataset.addValue(5.0, series2, category1);
        }

        if (data.size() > 0)
        {
        	params.setHasData(true);
        }
        else
        {
        	params.setHasData(false);
        }

        return DatasetUtilities.createCategoryDataset(categories, series, ad);
        //return dataset;
    }

    private CategoryDataset createStackedDataset() throws NoDataException
    {
        List poCount = this.getRfqData();

        if (poCount.size() == 0)
        {
            System.out.println("No data has been found");
            throw new NoDataException();
        }

         //  Create and populate a CategoryDataset
        Iterator iter = poCount.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double ad[][] = new double[poCount.size()][1];
        int i = 0;
        String series[] = new String[poCount.size()];
        String category[] = {"Pos"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            ad[i][0] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM");

            i++;
            //dataset.addValue(((Integer)valuesTemp[0]), "Pos", this.status((String)valuesTemp[1]));
        }

        return DatasetUtilities.createCategoryDataset(series, category, ad);
        //dataset.addValue(new Integer(0), "Reqs", "req1");
        //return dataset;
    }

    public PieDataset createPieDataset()
    {
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        List data = this.getRfqData();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            defaultpiedataset.setValue(TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM"), ((Integer)valuesTemp[0]).doubleValue());
        }

        return defaultpiedataset;
    }

    protected List getDataList(BuyerGetGraphData graphData)
    {
        List list = graphData.getPoCount();
        return list;
    }
}
