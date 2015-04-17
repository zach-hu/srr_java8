<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.DashboardFactory"%>
<%@ page import="com.tsa.puridiom.graphs.DashBoard"%>
<%@ page import="com.tsa.puridiom.graphs.*" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/styles/yui/tree.jsp">

<script type="text/javascript" src="<%=contextPath%>/scripts/yui/yahoo/yahoo.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/event/event.js"></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/treeview/treeview.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/scripts/yui/treeview/TaskNode.js"></script>

<%
if(HiltonUtility.isEmpty(uid)){					uid = (String) request.getParameter("uid");		}
if(HiltonUtility.isEmpty(oid)){					oid = (String) request.getParameter("oid");		}

DashBoard dashboard = DashboardFactory.getInstance(oid, "buyer", uid);
Map graphs = dashboard.getGraphs();
Iterator graphIterator = graphs.keySet().iterator();
%>
<script type="text/javascript">

	var tree;
	var nodes = [];
	var nodeIndex;

	function treeInit()
	{
		buildRandomTextNodeTree();
	}

	function buildRandomTextNodeTree()
	{
		tree = new YAHOO.widget.TreeView("treeDiv1");
		<%while (graphIterator.hasNext())
		{
			String graphName = (String) graphIterator.next();%>
			var tmpNode = new YAHOO.widget.TaskNode("<%=graphName%>", tree.getRoot(), false, <%if(dashboard.getGraph(graphName).isUserHidden()){%>false<%}
			else{%>true<%}%>);
		<%}%>
        tree.subscribe("checkClick", onCheckClick);
        // tree.subscribe("labelClick", onLabelClick);
		tree.draw();
	}


	function onCheckClick(node)
	{
		if(node.checked)
		{
			parent.showGraph(node.label);
		}
		else
		{
			parent.hideGraph(node.label);
		}
    }


</script>

 <div id="treeDiv1"></div>
 <div><a href="javascript: dashboard();">Refresh Me</a> </div>
<%@ include file="/system/footer_popup.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	function thisLoadPopup()
	{
  		addHandle(document.getElementsByTagName('body').item(0), window);
  		treeInit();
	}

	function uncheckMe(graphId)
	{
		var topNodes = tree.getRoot().children;
        for(var i=0; i<topNodes.length; ++i)
        {
        	if(topNodes[i].label == graphId)
        	{
        		topNodes[i].checkClick();
        		//parent.hideGraph(node.label);
        	}
        }

	}


	function dashboard()
	{
    	parent.dashboard();
  }

	// End Hide script -->
</SCRIPT>