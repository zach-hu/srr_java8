/*
 * Created on Jan 21, 2010
 */
package com.tsa.puridiom.graphs;

import java.util.List;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.PieDataset;

/**
 * @author Kelli
 */
public class CommodityPieUrlGenerator extends StandardPieURLGenerator
{
	private List commodityCodeList = null;
	
	public void setCommodityCodeList (List commodityCodeList) {
		this.commodityCodeList = commodityCodeList;
	}
	
	public List getCommodityCodeList() {
		return this.commodityCodeList;
	}

    public String generateURL(PieDataset dataset, Comparable key, int pieIndex)
    {
        String url = "javascript: viewByCommodity(";
        String endUrl = "); void(0);";
        int index = dataset.getIndex(key);
        String commodityCode = null;
        if (this.commodityCodeList != null && this.commodityCodeList.size() > pieIndex) {
        	commodityCode = (String) this.commodityCodeList.get(index);
        }
        if (commodityCode == null) {
        	commodityCode = String.valueOf(key);
        }
        url += "\\'" + commodityCode + "\\'";
        url += endUrl;

        return url;

    }
}
