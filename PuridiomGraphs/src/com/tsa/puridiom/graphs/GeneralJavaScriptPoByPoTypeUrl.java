package com.tsa.puridiom.graphs;

import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.PieDataset;
import com.tsa.puridiom.common.documents.OrderType;


public class GeneralJavaScriptPoByPoTypeUrl extends StandardPieURLGenerator
{

    public String generateURL(PieDataset dataset, Comparable key, int pieIndex)
    {
        String url = "javascript: graphOrdersBrowse(";
        String endUrl = "); void(0);";
        String keyword = "";

        String status = key.toString();
        String	statusValue = OrderType.toValue(status, "PURIDIOM");

        url += "'" + statusValue + "'";
        url += endUrl;

        return url;

    }
}
