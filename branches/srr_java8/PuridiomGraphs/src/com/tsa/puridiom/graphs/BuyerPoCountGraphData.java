/*
 * Created on Dec 8, 2004
 *
 */
package com.tsa.puridiom.graphs;

import java.io.IOException;
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

public class BuyerPoCountGraphData
{
    private List poData = null;
    private List reqData = null;
    private List vendorData = null;

    public List getPoData()
    {
        return poData;
    }

    public void setPoData(List poData)
    {
        this.poData = poData;
    }

    public List getReqData()
    {
        return reqData;
    }

    public void setReqData(List reqData)
    {
        this.reqData = reqData;
    }

    public List getVendorData()
    {
        return vendorData;
    }

    public void setVendorData(List vendorData)
    {
        this.vendorData = vendorData;
    }

    public String getPoBarChart(GraphParams params)
    {
        params.setDataset(this.getDataset(params));
        String temp = TsaChartUtilities.getBarImg(params);

        return temp;

    }

    public String getPoPieChart(PieParams params)
    {
        ((PieParams)params).setPieDataset(this.createPieDataset(params));
        String temp = TsaChartUtilities.getPieImg(params);

        return temp;

    }

    public BuyerPoCountGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("buyerPoCount");
        this.setPoData(tempList);
        tempList = (List)incomingRequest.get("reqCount");
        this.setReqData(tempList);
        tempList = (List)incomingRequest.get("vendorCount");
        this.setVendorData(tempList);
    }

    public CategoryDataset getDataset(GraphParams params)
    {
    	CategoryDataset catData = null ;
        List data = this.getPoData();

        Iterator iter = data.listIterator();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        double ad[][] = new double[1][data.size()];
        String categories[] = {"Orders"};
        String series[] = new String[data.size()];
        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            ad[0][i] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], params.getOid());
        }

    	catData = DatasetUtilities.createCategoryDataset(categories, series, ad);
        if (data.size() > 0)
        {
        	params.setHasData(true);
        }
        else
        {
        	params.setHasData(false);
        }


        return catData;
    }

    public PieDataset createPieDataset(PieParams params)
    {
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        List data = this.getPoData();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            defaultpiedataset.setValue(TsaChartUtilities.poType((String)valuesTemp[1], params.getOid()), ((Integer)valuesTemp[0]).doubleValue());
        }

        if (data.size() > 0)
        {
        	params.setHasData(true);
        }
        else
        {
        	params.setHasData(false);
        }

        return defaultpiedataset;
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
            ad[i][0] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOM");

            i++;
            //dataset.addValue(((Integer)valuesTemp[0]), "Pos", this.status((String)valuesTemp[1]));
        }

        return DatasetUtilities.createCategoryDataset(series, category, ad);
        //dataset.addValue(new Integer(0), "Reqs", "req1");
        //return dataset;
    }

    protected List getDataList(BuyerGetGraphData graphData)
    {
        List list = graphData.getPoCount();
        return list;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[BuyerPoCountGraphData:");
        buffer.append(" poData: ");
        if(this.poData != null)
        {
            buffer.append(poData);
        }
        else
        {
            buffer.append("null");
        }
        /*buffer.append(" reqData: ");
        buffer.append(reqData);
        buffer.append(" vendorData: ");
        buffer.append(vendorData);*/
        buffer.append("]");
        return buffer.toString();
    }
}
