/*
 * Created on Aug 14, 2009
 */
package com.tsa.puridiom.graphs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.tsa.puridiom.common.utility.HiltonUtility;
/**
 * @author Kelli
 */

public class SpendByDepartmentGraphData
{
    private List dataList = null;

    public List getDataList()
    {
        return dataList;
    }

    public void setDataList(List dataList)
    {
        this.dataList = dataList;
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
        if (dataList != null) {
            for (int i=0; i < dataList.size(); i++) {
                Object dataArray[] = (Object[]) dataList.get(i);
                BigDecimal bdtemp =  (BigDecimal) dataArray[0];
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
            tickUnits = dif.divide(new BigDecimal(dataList.size()),1000, RoundingMode.HALF_UP).intValue();
        }

        if (tickUnits > 0) {
            params.setTickUnits(tickUnits);
        } else {
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

    public SpendByDepartmentGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("spendByDepartment");

        if(tempList != null){
	        BigDecimal sum = new BigDecimal(0);
	        boolean ckNull = false;
	        for (int i = 0; i < tempList.size(); i++) {
	        	Object valuesTemp[] = (Object[])tempList.get(i);
	        	
	        	String department = (String)valuesTemp[1];
	        	
	        	if (HiltonUtility.isEmpty(department)) {
	        		BigDecimal add = (BigDecimal)valuesTemp[0];
	        		sum = sum.add(add);
	        		ckNull = true;
	        		tempList.remove(i);
	        		i--;
	            }
			}
	
	        if(ckNull){
		        Object[] obj = new Object[2];
		        obj[0]= sum;
		        obj[1]= "Other";

		        tempList.add(obj);
	        }
        }
        this.setDataList(tempList);
    }

    public CategoryDataset getDataset(GraphParams params)
    {
        CategoryDataset catData = null ;
        List data = this.getDataList();

        double ad[][] = new double[1][data.size()];
        String categories[] = {"categories"};
        String series[] = new String[data.size()];

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            String department = (String)valuesTemp[1];
            if (HiltonUtility.isEmpty(department)) {
                department = "Other";
            }

            ad[0][i] = ((BigDecimal)valuesTemp[0]).doubleValue();
            series[i] = department ;
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
        List data = this.getDataList();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);
            String department = (String)valuesTemp[1];
            //defaultpiedataset.setValue((String)valuesTemp[1], TsaChartUtilities.getFormattedCurrency((BigDecimal)valuesTemp[0], "", params.getOid()));
            if (HiltonUtility.isEmpty(department)) {
                department = "Other";
            }
            defaultpiedataset.setValue(department, (BigDecimal)valuesTemp[0]);
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
        String category[] = {"Department"};
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
        buffer.append("[SpendByDepartmentGraphData:");
        buffer.append(" dataList: ");
        if(this.dataList != null)
        {
            buffer.append(dataList);
        }
        else
        {
            buffer.append("null");
        }
        buffer.append("]");
        return buffer.toString();
    }
}
