/*
 * Created on Feb 8, 2005
 */
package com.tsa.puridiom.graphs;

import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.PieDataset;
import com.tsa.puridiom.common.documents.OrderType;

/**
 * @author renzo
 */
public class HiltonPieUrlGenerator extends StandardPieURLGenerator
{

    public String generateURL(PieDataset dataset, Comparable key, int pieIndex)
    {
        String url = "javascript: graphOrdersBrowse(";
        String endUrl = "); void(0);";
        String keyword = "";

        String keyValue = dataset.getValue(key).toString();
        
        url += "\\'" + keyValue + "\\'";
        url += endUrl;

        return url;

    }
}
