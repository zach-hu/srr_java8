/*
 * Created on Sept 18, 2009
 */
package com.tsa.puridiom.graphs;

import org.jfree.data.category.CategoryDataset;

import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author Kelli
 */
public class JavaScriptPoByUrl extends BarJavaScriptUrl
{
	public String getLink()
	{
		if(HiltonUtility.isEmpty(this.link))
		{
			this.link = "viewPoBySupplier";
		}
		return link;
	}
    public String generateURL(CategoryDataset dataset, int series, int category)
    {
    	String url = "javascript: " + this.getLink()  + "(";
        String endUrl = "); void(0);";
        Comparable columnKey = dataset.getColumnKey(category);
        String columnValue = columnKey.toString();

        url += "'" + columnValue + "'";
        url += endUrl;
        return url;
    }
}
