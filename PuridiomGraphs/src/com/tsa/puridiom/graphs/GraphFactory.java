package com.tsa.puridiom.graphs;

public class GraphFactory
{
	public static Graph getGraph(String type)
	{
		Graph graph = null;
		try
		{
			type = type.substring(0, 1).toUpperCase() + type.substring(1);
			graph = (Graph)Class.forName("com.tsa.puridiom.graphs." + type + "Graph").newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return graph;
	}

}
