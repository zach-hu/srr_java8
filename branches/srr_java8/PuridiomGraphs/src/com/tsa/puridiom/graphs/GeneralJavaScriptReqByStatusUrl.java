/*
 * Created on Dec 15, 2004
 */
package com.tsa.puridiom.graphs;

import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author renzo
 */
public class GeneralJavaScriptReqByStatusUrl extends BarJavaScriptUrl
{
	public String getLink()
	{
		if(HiltonUtility.isEmpty(this.link))
		{
			this.link = "viewReqsStatus";
		}
		return link;
	}
    /* (non-Javadoc)
     * @see org.jfree.chart.urls.CategoryURLGenerator#generateURL(org.jfree.data.category.CategoryDataset, int, int)
     */
    public String generateURL(CategoryDataset dataset, int series, int category)
    {
    	String url = "javascript: " + this.getLink()  + "(";
        //String url = "javascript: " +  		"viewReqsStatus" + "(";
        String endUrl = "); void(0);";
        Comparable seriesKey = dataset.getRowKey(series);
        int count = dataset.getColumnCount();
        int index =count - category - 1;
        Comparable columnKey = dataset.getColumnKey(category);
        String status = columnKey.toString();
        String	statusValue = DocumentStatus.toValue(status, "PURIDIOM");

        url += "'" + statusValue + "'";
        url += endUrl;
        return url;
    }
    /*
    public String generateURL(CategoryDataset dataset, int series, int category) {
        String url = this.prefix;
        Comparable seriesKey = dataset.getRowKey(series);
        Comparable categoryKey = dataset.getColumnKey(category);
        boolean firstParameter = url.indexOf("?") == -1;
        url += firstParameter ? "?" : "&";

            url += this.seriesParameterName + "="
                + URLEncoder.encode(seriesKey.toString());
            url += "&" + this.categoryParameterName + "="
                + URLEncoder.encode(categoryKey.toString());
        return url;
    }
    */

}
