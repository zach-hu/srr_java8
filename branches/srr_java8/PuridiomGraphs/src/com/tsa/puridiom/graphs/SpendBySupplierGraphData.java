/*
 * Created on Aug 14, 2009
 */
package com.tsa.puridiom.graphs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author Kelli
 */

public class SpendBySupplierGraphData
{
    private List vendorData = null;

    public List getVendorData()
    {
        return vendorData;
    }

    public void setVendorData(List vendorData)
    {
        this.vendorData = vendorData;
    }

    public String getBarChart(GraphParams params)
    {
        params.setDataset(this.getDataset(params));
        String temp = TsaChartUtilities.getBarImg(params);

        return temp;
    }

    public String getVerticalBarChart(GraphParams params)
    {
    	params.setDataset(this.getDataset(params));
        
        BigDecimal max = new BigDecimal(0);
        BigDecimal min = new BigDecimal(0);
        if (vendorData != null) {
	        for (int i=0; i < vendorData.size(); i++) {
	        	Object vendorDataArray[] = (Object[]) vendorData.get(i);
	        	BigDecimal bdtemp =  (BigDecimal) vendorDataArray[0];
	        	if (bdtemp != null && bdtemp.compareTo(max) > 0) {
	        		max = bdtemp;
	        	}
	        	if (bdtemp != null && bdtemp.compareTo(min) < 0) {
	        		min = bdtemp;
	        	}
	        }
        }
        
        BigDecimal dif = max.subtract(min);
        int tickUnits = 0;
        if (dif.compareTo(new BigDecimal(1000)) > 0) {
        	tickUnits = dif.divide(new BigDecimal(vendorData.size()),1000, RoundingMode.HALF_UP).intValue();
        }
        
        if (tickUnits > 0) {
        	params.setTickUnits(tickUnits);
        }
        else {
        	params.setTickUnits(100);
        }
        String temp = TsaChartUtilities.getVerticalBarImg(params);

        return temp;
    }

    public String getPieChart(PieParams params)
    {
        ((PieParams)params).setPieDataset(this.createPieDataset(params));
        String temp = TsaChartUtilities.getPieImg(params);

        return temp;
    }

    public SpendBySupplierGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("spendBySupplier");
        this.setVendorData(tempList);
    }

    public CategoryDataset getDataset(GraphParams params)
    {
    	CategoryDataset catData = null ;
        List data = this.getVendorData();

        double ad[][] = new double[1][data.size()];
        String categories[] = {"categories"};
        String series[] = new String[data.size()];

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            ad[0][i] = ((BigDecimal)valuesTemp[0]).doubleValue();
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
        List data = this.getVendorData();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            //defaultpiedataset.setValue((String)valuesTemp[1], TsaChartUtilities.getFormattedCurrency((BigDecimal)valuesTemp[0], "", params.getOid()));
            defaultpiedataset.setValue((String)valuesTemp[1], (BigDecimal)valuesTemp[0]);
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
/*
    private CategoryDataset createStackedDataset() throws NoDataException
    {
        List poCount = this.getVendorData();

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
        String category[] = {"Suppliers"};
        while (iter.hasNext())
        {
            Object valuesTemp[] = (Object[])iter.next();
            ad[i][0] = ((Integer)valuesTemp[0]).doubleValue();
            series[i] = TsaChartUtilities.status((String)valuesTemp[1], "PURIDIOMX");

            i++;
            //dataset.addValue(((Integer)valuesTemp[0]), "Pos", this.status((String)valuesTemp[1]));
        }

        return DatasetUtilities.createCategoryDataset(series, category, ad);
    }

    protected List getDataList(BuyerGetGraphData graphData)
    {
        List list = graphData.getVendorCount();
        return list;
    }
*/    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[SupplierSpendGraphData:");
        buffer.append(" vendorData: ");
        if(this.vendorData != null)
        {
            buffer.append(vendorData);
        }
        else
        {
            buffer.append("null");
        }
        buffer.append("]");
        return buffer.toString();
    }
}
