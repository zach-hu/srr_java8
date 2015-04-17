/*
 * Created on Feb 06, 2007
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
 * @author kathleen
 *
 */

public class OrdersByStatusGraphData extends GraphData
{
    private List poData = null;

    public List getPoData()
    {
        return poData;
    }

    public void setPoData(List poData)
    {
        this.poData = poData;
    }

    public String getPoBarChart(GraphParams params)
    {
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
    public OrdersByStatusGraphData(Map incomingRequest)
    {
        this.setPoData((List)incomingRequest.get("ordersbystatus"));
    }

    public CategoryDataset getDataset(GraphParams params)
    {
        List data = this.getPoData();

        Iterator iter = data.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        double ad[][] = new double[1][data.size()];
        String categories[] = {"categories"};
        String series[] = new String[data.size()];

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            ad[0][i] = ((Long)valuesTemp[0]).doubleValue();
            
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], params.getOid());
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

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double ad[][] = new double[poCount.size()][1];
        int i = 0;
        String series[] = new String[poCount.size()];
        String category[] = {"Pos"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            ad[0][i] = ((Long)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM");

            i++;
        }

        return DatasetUtilities.createCategoryDataset(series, category, ad);
    }

    public PieDataset createPieDataset()
    {
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        List data = this.getPoData();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            defaultpiedataset.setValue(TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM"), ((Long)valuesTemp[0]).doubleValue());
        }

        return defaultpiedataset;
    }

    protected List getDataList(BuyerGetGraphData graphData)
    {
        List list = graphData.getPoCount();
        return list;
    }
}
