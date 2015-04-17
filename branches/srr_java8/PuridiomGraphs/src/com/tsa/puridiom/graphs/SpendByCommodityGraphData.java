/*
 * Created on Aug 14, 2009
 */
package com.tsa.puridiom.graphs;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.tsa.puridiom.commodity.CommodityManager;
/**
 * @author Kelli
 */

public class SpendByCommodityGraphData
{
    private List commodityData = null;

    public List getCommodityData()
    {
        return commodityData;
    }

    public void setCommodityData(List commodityData)
    {
        this.commodityData = commodityData;
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
        String temp = TsaChartUtilities.getVerticalBarImg(params);

        return temp;
    }

    public String getPieChart(PieParams params)
    {
        ((PieParams)params).setPieDataset(this.createPieDataset(params));
        String temp = TsaChartUtilities.getPieImg(params);

        return temp;
    }

    public SpendByCommodityGraphData(Map incomingRequest)
    {
        List tempList = (List)incomingRequest.get("spendByCommodity");
        this.setCommodityData(tempList);
    }

    public CategoryDataset getDataset(GraphParams params)
    {
    	CategoryDataset catData = null ;
        List data = this.getCommodityData();

        double ad[][] = new double[1][data.size()];
        String categories[] = {"categories"};
        String series[] = new String[data.size()];
        Map commodityKeys = new HashMap();

        for(int i = 0; i < data.size(); i++)
        {
            Object valuesTemp[] = (Object[])data.get(i);

            String commodityCode = (String)valuesTemp[1];
            String commodityDesc = commodityCode;
            //defaultpiedataset.setValue((String)valuesTemp[1], TsaChartUtilities.getFormattedCurrency((BigDecimal)valuesTemp[0], "", params.getOid()));
            if (commodityCode == null) {
            	commodityDesc = "Other";
            } else {
            	try {
            		commodityDesc = CommodityManager.getInstance().getCommodityDescription(params.getOid(), commodityCode);
            	} catch (Exception commodityException){
            	}
            }
            if (commodityDesc != null && commodityDesc.length() > 20) {
            	commodityDesc = commodityDesc.substring(0, 20) + "...";
            }
            commodityKeys.put(commodityCode, commodityDesc);
            ad[0][i] = ((BigDecimal)valuesTemp[0]).doubleValue();
            series[i] = commodityDesc;
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
    	try {
            List data = this.getCommodityData();
            List commodityCodeList = new ArrayList();

            for(int i = 0; i < data.size(); i++)
            {
                Object valuesTemp[] = (Object[])data.get(i);
                String commodityCode = (String)valuesTemp[1];
                String commodityDesc = commodityCode;
                //defaultpiedataset.setValue((String)valuesTemp[1], TsaChartUtilities.getFormattedCurrency((BigDecimal)valuesTemp[0], "", params.getOid()));
                if (commodityCode == null) {
                	commodityDesc = "Other";
                } else {
                	try {
                		commodityDesc = CommodityManager.getInstance().getCommodityDescription(params.getOid(), commodityCode);
                	} catch (Exception commodityException){
                	}
                }
                if (commodityDesc != null && commodityDesc.length() > 20) {
                	commodityDesc = commodityDesc.substring(0, 20) + "...";
                }
                BigDecimal bdTemp = null;
                if (valuesTemp.length > 1) {
                	if (valuesTemp[0] != null) {
                		bdTemp = (BigDecimal) valuesTemp[0];;
                	}
                }
                if (defaultpiedataset.getKeys().contains(commodityDesc)) {
                    defaultpiedataset.setValue(commodityDesc + "[" + commodityCode + "]", bdTemp);
                } else {
                    defaultpiedataset.setValue(commodityDesc, bdTemp);
                }
                commodityCodeList.add(commodityCode);
            }

            if (data.size() > 0)
            {
            	params.setHasData(true);
            	params.setDataCodeList(commodityCodeList);
            }
            else
            {
            	params.setHasData(false);
            }
    	}
    	catch (Exception e1) {
    		e1.printStackTrace();
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
        String category[] = {"Commodity"};
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
        buffer.append("[SpendByCommodityGraphData:");
        buffer.append(" commodityData: ");
        if(this.commodityData != null)
        {
            buffer.append(commodityData);
        }
        else
        {
            buffer.append("null");
        }
        buffer.append("]");
        return buffer.toString();
    }
}
