package com.tsa.puridiom.graphs;

import com.tsagate.foundation.database.DBSession;

public interface IGraph {

	public abstract String getEnabled();

	public abstract String getImgName();

	public abstract String getGraph();
	public abstract String setGraph(DBSession dbsession);

	public abstract void createChart();

}