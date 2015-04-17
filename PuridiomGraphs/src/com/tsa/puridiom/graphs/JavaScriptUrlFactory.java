package com.tsa.puridiom.graphs;

public class JavaScriptUrlFactory
{
	public static Object getUrlGenerator(GeneralGraphParams ggp)
	{
		String link = ggp.getLink();
		GeneralJavaScriptReqByStatusUrl cug = null;
		if(TypeGraph.barChart.equalsIgnoreCase(ggp.getTypeGraph()))
		{
			cug = new GeneralJavaScriptReqByStatusUrl();
			cug.setLink(link);
		}

		return cug;
	}

	public static Object getUrlGenerator(Graph graph)
	{
		String link = graph.getLink();
		GeneralJavaScriptReqByStatusUrl cug = null;
		if (graph instanceof BarGraph) {
			cug = new GeneralJavaScriptReqByStatusUrl();
			cug.setLink(link);
		}

		return cug;
	}

}
