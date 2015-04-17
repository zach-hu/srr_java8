package com.tsa.puridiom.graphs;

public class DashboardFactory
{

	private static DashBoard DASHBOARD;

	public static DashBoard getInstance(String organizationId, String type, String userId)
	{
		if(DashboardFactory.DASHBOARD == null)
		{
			DashboardFactory.DASHBOARD = new DashBoard(organizationId, userId);
		}

		return DashboardFactory.DASHBOARD;
	}

	public static DashBoard getNewInstance(String organizationId, String type, String userId)
	{
		DashboardFactory.DASHBOARD = new DashBoard(organizationId, userId);
		return DashboardFactory.DASHBOARD;

	}

}
