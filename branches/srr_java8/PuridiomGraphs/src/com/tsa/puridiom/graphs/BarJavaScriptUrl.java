package com.tsa.puridiom.graphs;

import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;

public class BarJavaScriptUrl implements CategoryURLGenerator
{
	public String link;

	public String generateURL(CategoryDataset arg0, int arg1, int arg2) {

		return null;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
