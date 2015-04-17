<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.*"%>

<%@ page import="java.io.PrintWriter" %>
<%
String	s_title = "Bid History";
List unitPricesList = (List) request.getAttribute("unitPricesList");
RfqBidHistoryVendorGraphData vendorData = new RfqBidHistoryVendorGraphData(unitPricesList,"test","ACME");
GraphParams params = new GraphParams();
params.setHeigth(350);
params.setWidth(450);
params.setSession(session);
params.setWriter(new PrintWriter(out));
params.setTitle("General Bid History");
params.setXTitle("Date");
params.setYTitle("UnitPrice");
params.setContextPath(request.getContextPath());
params.setOid(oid);
params.setCreateImageMap(false);
//String graphImg = vendorData.getTimeSeriesChart(params);
String graphImg = vendorData.getLineChart(params);
%>
<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign="bottom"><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign="bottom" align="right" height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>


<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<br><td width=34% align="center"><%=graphImg%></td>
</tr>
</table>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<br><td width=34% align="center"><a href="javascript: window.close();  void(0);"><img src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0 tabIndex=999></a></td>
</tr>
</table>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>