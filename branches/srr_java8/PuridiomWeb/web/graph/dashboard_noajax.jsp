<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.DashboardFactory"%>
<%@ page import="com.tsa.puridiom.graphs.DashBoard"%>
<%@ page import="com.tsa.puridiom.graphs.*" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/scripts/yui/container/assets/container.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/styles/yui/tree.jsp">
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/dragdrop/dragdrop.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/container/container.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/treeview/treeview.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/treeview/TaskNode.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/pajax.js"></script>
<style>
<!--
	#container {height:15em;}
-->
</style>
<!-- <a href="javascript: panelList();">test</a> -->
<div id="container">
</div>


<tsa:hidden name="graphName" value="" />
<tsa:hidden name="prefName" value="" />
<tsa:hidden name="prefValue" value="" />
<tsa:hidden name="userFilter" value=""  style="text-align: center"/>

<div id="options"><a href="javascript: getGraph();">send</a>
<div id="results">&nbsp;</div>

<style>
<!--
.yui-panel .ft {
	background-color:#3d77cb;
	color:#FFF;
	font-size:100%;
	line-height:100%;
	font-weight:bold;
	overflow:hidden;
	padding:4px;
	text-align: center;
}
.yui-panel .hd {
	text-align: center;
}
-->
</style>


<script>
		var tree = null;
		var panelsArray = [];
		var visiblePanelsArray = [];

		function buildTreeGraphList(graphName, checked)
		{

			var tmpNode = new YAHOO.widget.TaskNode(graphName, tree.getRoot(), false, false);
        	//tree.subscribe("checkClick", onCheckClick);
       		// tree.subscribe("labelClick", onLabelClick);
		}

		function buildGraphListPanel()
		{
			listpanel = new YAHOO.widget.Panel("graphListPanel", {xy:[700, 150], width:"350px", visible:true, draggable:false, close:false } );
			listpanel.setHeader('gTitle');
			listpanel.setBody("<div id=\"treeGraph\"></div>");
			listpanel.setFooter("foot");
			listpanel.render("container");
		}

		function buildGraphs()
		{
			buildGraphListPanel();
			tree = new YAHOO.widget.TreeView("treeGraph");


			<%DashBoard dashboard = DashboardFactory.getInstance(oid, "buyer", uid);
			Map graphs = dashboard.getGraphs();
			Iterator graphIterator = graphs.keySet().iterator();
			int x = 0;
			int y = 100;
			int counterY = 1;
			int counterX = 1;
			List appLeadGraphList = new ArrayList();
			while (graphIterator.hasNext())
			{
				counterY++;
				String graphName = (String) graphIterator.next();

					dashboard.setGraph(graphName);
					Graph graph = dashboard.getGraph(graphName);
					List userWhere = graph.getUserWhere();
				  	if(userWhere.size() > 0)
				  	{
				  		String filter = (String)userWhere.get(1);%>
				  		gPeriod = "<select name=\"userFilter_<%=graph.getName()%>\" id=\"userFilter_<%=graph.getName()%>\" size=\"1\">" +
				  					"<option value=\":none\" <%if(filter.equalsIgnoreCase(":none")) {%>selected=\"selected\" <%} %>>None</option>" +
									"<option value=\":thisyear\" <%if(filter.equalsIgnoreCase(":thisyear")) {%>selected=\"selected\"<%} %>>This Year</option>" +
									"<option value=\":thisweek\" <%if(filter.equalsIgnoreCase(":thisquarter")) {%>selected=\"selected\"<%} %>>This Quarter</option>" +
									"<option value=\":thismonth\" <%if(filter.equalsIgnoreCase(":thismonth")) {%>selected=\"selected\"<%} %>>This Month</option>" +
									"<option value=\":thisweek\" <%if(filter.equalsIgnoreCase(":thisweek")) {%>selected=\"selected\"<%} %>>This Week</option>" +
									"</select>" +
									"&nbsp;&nbsp;" +
									"<a href=\"javascript: saveMe('userFilter_<%=graph.getName()%>', '<%=graph.getName()%>' );\">Filter</a>";
				  	<%} %>
					panelsArray.push(newPanel(<%=x%>, <%=y%>, <%=counterX%>, '<%=graph.getGraphInfo()%>', '<%=graph.getTitle()%>', '<%=graph.getHeigth()%>', '<%=graph.getWidth()%>', gPeriod));
					<%x = (400 * (counterX%2));
					y += (400 * (counterY%2));
					counterX++;%>
					buildTreeGraphList('<%=graph.getTitle()%>', true);

			<%}%>
			tree.subscribe("checkClick", onCheckClick);
			tree.draw();
		}

		function onCheckClick(node)
		{
			if(node.checked)
			{
				visiblePanelsArray.push(panelsArray[node.index -1]);
			}
			else
			{
				panelsArray[node.index -1].hide();
				for(var i=0; i < visiblePanelsArray.length; ++i)
	        	{
	        		if(visiblePanelsArray[i].id == panelsArray[node.index -1].id)
	        		{
	        		visiblePanelsArray[i].hide();
	        			visiblePanelsArray.splice(i, 1);
	        		}
	        	}
			}
			treeTravel(panelsArray[node.index -1]);
	    }

	    function treeTravel(panel)
		{
			var currentPosition = [0,100];
			var counterY = 1;
			var counterX = 1;
	        for(var i=0; i < visiblePanelsArray.length; ++i)
	        {

	        		counterY++;
	        		if((counterX%2) == 1)
	        		{
						currentPosition[0] = 0;
	        		}
	        		else
	        		{
	        			currentPosition[0] = parseInt(visiblePanelsArray[i - 1].cfg.getProperty("width"));
	        		}
	        		visiblePanelsArray[i].cfg.setProperty("xy", currentPosition);
	        		//panelsArray[node.index -1].render("container");
	        		visiblePanelsArray[i].show();
	        		currentPosition[1] += (400 * (counterY%2));
	        		counterX++;

	        }
		}
		function panelList()
		{
			alert(panelsArray);
		}

		function newPanel(graphX, graphY, counterX, gUrl, gTitle, gH, gW, gPeriod)
		{

			// Instantiate a Panel from script
			panel = new YAHOO.widget.Panel("panel" + counterX, {xy:[graphX, graphY], width:gW, visible:false, draggable:true, close:true } );
			panel.setHeader(gTitle);
			panel.setBody(gUrl);
			panel.setFooter("<div class=\"graphft\">Period: " + gPeriod + "</div");
			panel.render("container");

			//YAHOO.example.container.panel2.show();
			return panel;
		}

		YAHOO.util.Event.addListener(window, "load", buildGraphs);
</script>

<script language='Javascript1.2' type="text/javascript">
	//setNavCookie('/graph/main_graph.jsp', 'GeneralGraphic', 'Procurement Dashboard');
	frm = document.purchaseform;

	function getGraph(graphName)
	{
		var url = "./PuridiomGraphs?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&graphName=" + graphName;
		startRequest(url);
	}
	function showMe()
	{
		YAHOO.example.container.panel2.show();
	}
	function saveMe(option, graphName)
	{
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
		frm.graphName.value = graphName;
		var selectEl = document.getElementById(option);

		frm.userFilter.value = selectEl.value;

		doSubmit('/graph/dashboard.jsp', 'GraphUserPreferenceUpdate');
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

