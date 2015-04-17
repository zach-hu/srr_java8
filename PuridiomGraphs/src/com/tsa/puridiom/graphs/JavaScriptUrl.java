/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaScriptUrl implements CategoryURLGenerator
{

    /* (non-Javadoc)
     * @see org.jfree.chart.urls.CategoryURLGenerator#generateURL(org.jfree.data.category.CategoryDataset, int, int)
     */
    public String generateURL(CategoryDataset dataset, int series, int category)
    {
        String url = "javascript: viewOrders(";
        String endUrl = "); void(0);";
        Comparable seriesKey = dataset.getRowKey(series);
        url += "\\'" + seriesKey.toString() + "\\'";
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
