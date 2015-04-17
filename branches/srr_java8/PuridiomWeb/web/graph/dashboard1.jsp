<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.DashboardFactory"%>
<%@ page import="com.tsa.puridiom.graphs.DashBoard"%>
<%@ page import="com.tsa.puridiom.graphs.*" %>
<div style="position: absolute; ">
<%DashBoard dashboard = DashboardFactory.getInstance(oid, "buyer", uid);
Map graphs = dashboard.getGraphs();
Iterator graphIterator = graphs.keySet().iterator();
%>
				<%int iCols = 0;
				int iRows = 0;
				int top=0;
				while (graphIterator.hasNext())
				{
					String graphName = (String) graphIterator.next();
					dashboard.setGraph(graphName);
					//Graph graph = (Graph)graphs.get(graphName);%>
					<iframe id="<%=graphName%>" name="<%=graphName%>" src="<%=contextPath %>/graph/graph.jsp?uid=${esapi:encodeForHTMLAttribute(userId)}&oid=<%=oid %>&graphName=<%=graphName %>" height="400px" width="400px" marginheight="0" hspace="0" frameborder="0" scrolling="no"
						style="overflow-y:hidden; overflow-x:hidden;  position: absolute; top: <%=100+(iRows*400)%>px; left: <%=50+(iCols*400)%>px;"></iframe>
					<%top++;
					iCols++;
					if ((top%2) == 0)
					{
						iRows++;
						iCols = 0;
					}
				}%>

<iframe id="options" name="options" src="<%=contextPath %>/graph/graphOptions.jsp?uid=${esapi:encodeForHTMLAttribute(userId)}&oid=<%=oid %>" height="400px" width="400px" marginheight="0" hspace="0" frameborder="0" scrolling="no"
style="position: absolute; overflow-y:hidden; overflow-x:hidden; left: 850px; top: 100px;"></iframe>
</div>

<script language='Javascript1.2' type="text/javascript">
	//setNavCookie('/graph/main_graph.jsp', 'GeneralGraphic', 'Procurement Dashboard');
	frm = document.purchaseform;

	function saveMe()
	{
		doSubmit('/graph/dashboard.jsp', 'UpdateDashboard');
	}

	function uncheckOption(graphId)
	{
		var iframe = document.getElementById('options');
        var doc = iframe.contentWindow;
        doc.uncheckMe(graphId);
	}

	function hideGraph(graphId)
	{
		var iframe = document.getElementById(graphId);
         var doc = iframe.contentWindow;
         doc.displayArea('pageLoading');
         doc.hideGraph();
	}

	function dashboard()
	{
    	doSubmit('graph/dashboard.jsp', 'DoNothing');
  	}

  	function showGraph(graphId)
  	{
  		 var iframe = document.getElementById(graphId);
         var doc = iframe.contentWindow;
         doc.displayArea('pageLoading');
         doc.showGraph();
	}

</script>
<%@ include file="/system/footer.jsp" %>

