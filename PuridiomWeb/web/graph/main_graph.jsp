<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.tsa.puridiom.usermanager.UserManager"%>
<%@page import="com.tsa.puridiom.entity.UserProfile"%>
<%@page import="com.tsa.puridiom.graphs.GeneralGraphParams"%>
<%@page import="com.tsa.puridiom.graphs.LoadGraphic"%>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	List graphics = (List) request.getAttribute("graphics");

	int numCol = Integer.parseInt( PropertiesManager.getInstance(oid).getProperty("MISC", "DASHBOARDCOLUMNS","2")) ;
	int numRow = graphics.size()/numCol;
	int numLastCol = graphics.size()%numCol;
	String fiscalYear = HiltonUtility.getFiscalYear(oid, userTimeZone);
%>

<script language='Javascript1.2' type="text/javascript">
	document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/scripts/vse06p/dynamicTables.js' type='text/javascript'><\/scr" + "ipt>");
	function showOrHidden(name)
	{
		if ( document.getElementById(name).style.display=="none" )
		{

			document.getElementById(name).style.display="";
			document.getElementById("show"+name).style.display="";
			document.getElementById("hide"+name).style.display="none";
		}
		else if ( document.getElementById(name).style.display=="" )
		{

			document.getElementById("show"+name).style.display="none";
			document.getElementById("hide"+name).style.display="";
			document.getElementById(name).style.display="none";
		}
	}

	function viewReqs(status)
  	{
		setOriginalFilter("RequisitionHeader_status", "=", status);
      	//setOriginalFilter("RequisitionHeader_fiscalYear", "=", "<%=fiscalYear%>");
      	//setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      	browse('allrequisitions');
  	}

	function viewReqsStatus(status)
  	{
		setOriginalFilter("RequisitionHeader_status", "=", status);
      	//setOriginalFilter("RequisitionHeader_fiscalYear", "=", "<%=fiscalYear%>");
      	setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      	browse('allrequisitions');
  	}

  	function viewRfqsByStatus(status)
    {
      setOriginalFilter("RfqHeader_status", "=", status);
      //setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      //setOriginalFilter("RfqHeader_fiscalYear", "=", "<%=fiscalYear%>");
      browse('allrfqs');
    }

    function viewPOsByStatus(status)
    {
      setOriginalFilter("PoHeader_status", "=", status);
      //setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      //setOriginalFilter("RfqHeader_fiscalYear", "=", "<%=fiscalYear%>");
      browse('poheader');
    }

    function viewOrdersByStatus(status)
    {
      setOriginalFilter("PoHeader_status", "=", status);
      //setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      //setOriginalFilter("RfqHeader_fiscalYear", "=", "<%=fiscalYear%>");
      browse('poheader');
    }


  	function graphOrdersBrowse(type)
  	{
    	setOriginalFilter("PoHeader_poType", "=", type);
      	setOriginalFilter("PoHeader_fiscalYear", "=", "<%=fiscalYear%>");
      	browse('mypos');
  	}
</script>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>


<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Procurement Dashboard</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>

<%	for (int i=0; i <= numRow ; i++)
	{
%>
	<tr>
	<%	for (int j=0; j < numCol ; j++)
		{
			if(i == numRow && j >= numLastCol)
			{
			}
			else
			{
				GeneralGraphParams graphic = (GeneralGraphParams) graphics.get(i*numCol+j);
	%>
			<td valign="top" align="center" style="border:1px solid #7777ff;" >
				<div style="width:98%; border-bottom:1px solid #7777ff; padding: 2 2 2 2; margin-top:5px;">
			  	<a style="text-decoration:none; color:#7777ff; font-weight:bold" href="javascript:showOrHidden('<%=graphic.getImgName()%>');"> <%=graphic.getTitle()%>
			  	<img src="<%=contextPath%>/images/up.gif" id="show<%=graphic.getImgName()%>" border=0 style="margin-bottom:-4px">
			  	<img src="<%=contextPath%>/images/down.gif" id="hide<%=graphic.getImgName()%>" border=0 style="display:none;margin-bottom:-4px">
			  	</a>

				</div>
				<div id= "<%= graphic.getImgName()%>">
    		  	<table border="0" cellspacing="0" cellpadding=0>
					  <tr>
						<td><%=graphic.getGraphInfo()%></td>
			  	  	  </tr>
			  	</table>
			  	<%List userWhere = graphic.getUserWhere();
			  	if(userWhere.size() > 0)
			  	{%>
			  	<table>
			  		<tr>
			  			<td>Period: </td>
			  			<td><select name="<%=graphic.getName()%>_userFilter" size=1>
			  				<%String filter = (String)userWhere.get(1);%>
			  					<option value=":none" <%if(filter.equalsIgnoreCase(":none")) {%>selected="selected" <%} %>>None</option>
								<option value=":thisyear" <%if(filter.equalsIgnoreCase(":thisyear")) {%>selected="selected"<%} %>>This Year</option>
								<option value=":thisweek" <%if(filter.equalsIgnoreCase(":thisquarter")) {%>selected="selected"<%} %>>This Quarter</option>
								<option value=":thismonth" <%if(filter.equalsIgnoreCase(":thismonth")) {%>selected="selected"<%} %>>This Month</option>
								<option value=":thisweek" <%if(filter.equalsIgnoreCase(":thisweek")) {%>selected="selected"<%} %>>This Week</option>
								</select> </td>
						<td><a href="javascript: saveMe();">Filter</a> </td>
			  		</tr>
			  	</table>
			  	<%} %>
	   			</div>

			</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
	<%
			}
		}
	%>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
<%
	}
%>
</table>

<script language='Javascript1.2' type="text/javascript">
	setNavCookie('/graph/main_graph.jsp', 'GeneralGraphic', 'Procurement Dashboard');

	function saveMe()
	{
		doSubmit('/graph/main_graph.jsp', 'GeneralGraphic');
	}
</script>
<%@ include file="/system/footer.jsp" %>

