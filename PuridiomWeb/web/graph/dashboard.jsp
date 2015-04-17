<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.DashboardFactory"%>
<%@ page import="com.tsa.puridiom.graphs.DashBoard"%>
<%@ page import="com.tsa.puridiom.graphs.*" %>
<%@page import="java.util.Date"%>
<link rel="stylesheet" type="text/css" href="./scripts/yui/container/assets/container.css" />
<link rel="stylesheet" type="text/css" href="./system/styles/yui/tree.jsp">
<script type="text/javascript" src="./scripts/yui/dragdrop/dragdrop.js"></script>
<script type="text/javascript" src="./scripts/yui/container/container.js"></script>
<script type="text/javascript" src="./scripts/yui/treeview/treeview.js" ></script>
<script type="text/javascript" src="./scripts/yui/treeview/GraphNode.js"></script>
<script type="text/javascript" src="./scripts/pajax.js"></script>
<style>
<!--
	#container {height:15em;}
	.icon-ppt { height: 22px; }
-->
</style>
<%GeneralSqlParams sqlParams = new GeneralSqlParams();
String sqlToday = HiltonUtility.getFormattedDate(sqlParams.getToday(), oid, userDateFormat);
String sqlThisYear = HiltonUtility.getFormattedDate(sqlParams.getThisYear(), oid, userDateFormat);
String sqlThisQuarter = HiltonUtility.getFormattedDate(sqlParams.getThisQuarter(), oid, userDateFormat);
String sqlThisMonth = HiltonUtility.getFormattedDate(sqlParams.getThisMonth(), oid, userDateFormat);
String sqlThisWeek = HiltonUtility.getFormattedDate(sqlParams.getThisWeek(), oid, userDateFormat);
%>

<!-- <a href="javascript: panelList();">test</a> -->
<div id="container">
</div>


<tsa:hidden name="graphName" value="" />
<tsa:hidden name="prefName" value="" />
<tsa:hidden name="prefValue" value="" />

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
		var grahPanel = null;

		var sqlToday = '<%=sqlToday%>';
		var sqlThisYear = '<%=sqlThisYear%>';
		var sqlThisQuarter = '<%=sqlThisQuarter%>';
		var sqlThisMonth = '<%=sqlThisMonth%>';
		var sqlThisWeek = '<%=sqlThisWeek%>';

		function buildTreeGraphList(graphTitle, graphId, checked)
		{

			var tmpNode = new YAHOO.widget.GraphNode(graphTitle, tree.getRoot(), false, false, graphId);
			//tmpNode.labelStyle = "icon-" + tmpNode.index;
        	//tree.subscribe("checkClick", onCheckClick);
       		// tree.subscribe("labelClick", onLabelClick);
		}

		function buildGraphListPanel()
		{
			listpanel = new YAHOO.widget.Panel("graphListPanel", {xy:[550, 150], width:"330px", visible:true, draggable:false, close:false } );
			listpanel.setHeader('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dashoptionstitle", "DashBoard Options", true)%>');
			listpanel.setBody("<div id=\"treeGraph\"></div>");
			//listpanel.setFooter("foot");
			listpanel.render("container");
		}

		function setLoadingPanel()
		{
			grahPanel.setHeader('');
			grahPanel.setBody("<img src=\"./images/ajax-loader.gif\">Loading..... Please Wait");
			//grahPanel.setFooter("");
			grahPanel.render("container");
		}

		function buildGraphPanel()
		{
			grahPanel = new YAHOO.widget.Panel("graphPanel", {xy:[100, 150], width:"350px", visible:true, draggable:false, close:false } );
			grahPanel.setHeader('');
			grahPanel.setBody("Please Select a graph from the left");
			var gPeriod = "<select name=\"userFilter\" id=\"userFilter\" size=\"1\">" +
				  					"<option value=\":none\">None</option>" +
									"<option value=\":thisyear\">This Year</option>" +
									"<option value=\":thisweek\">This Quarter</option>" +
									"<option value=\":thismonth\">This Month</option>" +
									"<option value=\":thisweek\">This Week</option>" +
									"</select>" +
									"&nbsp;&nbsp;" +
									"<a href=\"javascript: saveUserFilter( );\">Filter</a>";

			grahPanel.setFooter(gPeriod);
			grahPanel.render("container");
		}

		function buildGraphs()
		{
			buildGraphListPanel();
			buildGraphPanel();
			tree = new YAHOO.widget.TreeView("treeGraph");


			<%DashBoard dashboard = DashboardFactory.getInstance(oid, "buyer", uid);
			Map graphs = dashboard.getGraphs();
			Iterator graphIterator = graphs.keySet().iterator();
			List appLeadGraphList = new ArrayList();
			while (graphIterator.hasNext())
			{
				String graphName = (String) graphIterator.next();
				Graph tmpGraph = dashboard.getGraph(graphName);%>
				buildTreeGraphList('<%=tmpGraph.getTitle()%>', '<%=graphName%>');

			<%}%>
			tree.subscribe("checkClick", onCheckClick);
			tree.subscribe("labelClick", onLabelClick);
			tree.draw();
			loadFirstGraph();
		}

		function loadFirstGraph()
		{
			var node = tree.getNodeByIndex(1);
			node.checkClick();
		}

		function onLabelClick(node)
		{
			node.checkClick();
		}

		function onCheckClick(node)
		{
			if(node.checked)
			{
				uncheckRest(node);
				getGraph(node.graphId);

			}
			else
			{
				//panelsArray[node.index -1].hide();
				/*for(var i=0; i < visiblePanelsArray.length; ++i)
	        	{
	        		if(visiblePanelsArray[i].id == panelsArray[node.index -1].id)
	        		{
	        			visiblePanelsArray[i].hide();
	        			visiblePanelsArray.splice(i, 1);
	        		}
	        	}*/
	        	//grahPanel.hide();
	        	grahPanel.setHeader("");
	        	grahPanel.setBody("Please Select a Graph from the left");

	        	uncheckAll();
			}
			treeTravel(panelsArray[node.index -1]);
	    }

	    function treeTravel()
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

		function setUserFilterSelected(filter)
		{
			var selectEl = document.getElementById("userFilter");
			for (var i=0; i<selectEl.options.length; i++)
			{
				if(selectEl.options[i].value == filter)
					selectEl.options[i].selected = true;
			}
		}

		function panelList()
		{
			alert(panelsArray);
		}

		function newGraphPanel(graphId, graphX, graphY, gUrl, gTitle, gH, gW, gPeriod)
		{

			// Instantiate a Panel from script
			//panel = new YAHOO.widget.Panel("panel" + counterX, {xy:[graphX, graphY], width:gW, visible:true, draggable:true, close:true } );
			grahPanel.setHeader(gTitle);
			grahPanel.setBody(gUrl);
			//grahPanel.setFooter("<div class=\"graphft\">Period: " + gPeriod + "</div");
			setUserFilterSelected(gPeriod);

			grahPanel.cfg.setProperty("width", gW);
			grahPanel.render("container");
			grahPanel.show();
			frm.graphName.value = graphId;
		}
		function graphPanel(counterX, graphId)
		{

			// Instantiate a Panel from script
			panel = new YAHOO.widget.Panel("panel" + counterX, {visible:false, draggable:true, close:false } );
			panel.setHeader("<div id='" + graphId + "_title'></div>");
			panel.setBody("<div id='" + graphId + "_url'></div>");
			panel.setFooter("<div id='" + graphId + "_period' class=\"graphft\"></div>");
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
		setLoadingPanel();
		startRequest(url);
	}

	function saveUserFilter()
	{
		var selectEl = document.getElementById('userFilter');
		var url = "./PuridiomGraphs?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&userFilter=" + selectEl.value +  "&graphName=" + frm.graphName.value;
		setLoadingPanel();
		startRequest(url);
	}

	function showMe()
	{
		YAHOO.example.container.panel2.show();
	}
	function saveMe()
	{
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";

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

	function uncheckAll() {
        var topNodes = tree.getRoot().children;
        for(var i=0; i<topNodes.length; ++i) {
            topNodes[i].uncheck();
        }
    }
    function uncheckRest(node) {
        var topNodes = tree.getRoot().children;
        for(var i=0; i<topNodes.length; ++i)
        {
        	if(node.graphId != topNodes[i].graphId)
        	{
            	topNodes[i].uncheck();
            }
        }
    }

    function viewRfqsByStatus(status)
	{
	     setOriginalFilter("RfqHeader_status", "=", status);
	     getUserFilterSelectedDate("RfqHeader_rfqDate");
	     browse('rfqHeader');
	}

    function viewReqs(status)
	{
	     setOriginalFilter("RequisitionHeader_status", "=", status);
	     getUserFilterSelectedDate("RequisitionHeader_requisitionDate");
	     browse('requisitionheader');
	}

	function getUserFilterSelectedDate(dateColumn)
	{
		var selectEl = document.getElementById('userFilter');
	      var userFilterValue = selectEl.value;
	      if(userFilterValue.indexOf("thisyear") > 0)
	      {
	      		setAdvancedFilter(dateColumn, ">=", sqlThisYear, "AND", "Y", "ASC");
	      }
	      else if(userFilterValue.indexOf("thismonth") > 0)
	      {
	      		setAdvancedFilter(dateColumn, ">=", sqlThisMonth, "AND", "Y", "ASC");

	      }
	      else if(userFilterValue.indexOf("thisquarter") > 0)
	      {
	      		setAdvancedFilter(dateColumn, ">=", sqlThisQuarter, "AND", "Y", "ASC");
	      }
	      else if(userFilterValue.indexOf("thisweek") > 0)
	      {
	      		setAdvancedFilter(dateColumn, ">=", sqlThisWeek, "AND", "Y", "ASC");
	      }
	}

	function viewPOsByStatus(status)
  	{
      setOriginalFilter("PoHeader_status", "=", status);
      getUserFilterSelectedDate("PoHeader_poDate");

      browse('poHeader');
  	}

</script>