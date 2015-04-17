<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.*"%>
<%@ page import="com.tsa.puridiom.supplierportal.graphs.tasks.*" %>

<%
List rfqBidHistoryList = (List) request.getAttribute("bidHistoryList");

//VentanaGrafica demo = new VentanaGrafica("My Bid-History Graph");

String	s_title = "Bid History";
String	s_bidItemNumber=(String)request.getAttribute("bidItemNumber");
String	s_bidQuantity=(String)request.getAttribute("bidQuantity");
String	s_bidCommodity=(String)request.getAttribute("bidCommodity");
String	s_bidVariance=(String)request.getAttribute("bidVariance");
String	s_auctionDueDate=(String)request.getAttribute("auctionDueDate");
String graphName = (String)request.getAttribute("graphName");
//out.println("graphName" + graphName);
%>

<tsa:hidden name="RfqHeader_bidItem" value="<%=s_bidItemNumber%>"/>
<tsa:hidden name="RfqHeader_bidQuantity" value="<%=s_bidQuantity%>"/>
<tsa:hidden name="RfqHeader_bidCommodity" value="<%=s_bidCommodity%>"/>
<tsa:hidden name="RfqHeader_bidVariance" value="<%=s_bidVariance%>"/>
<tsa:hidden name="RfqHeader_auctionDueDate" value="<%=s_auctionDueDate%>"/>
<tsa:hidden name="RfqHeader_auctionDueDate" value="<%=rfqBidHistoryList.size()%>"/>



<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
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
<table cellpadding=2 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td width=100px align=right class=label>Item #:</td>
	<td valign=top>&nbsp;<%=s_bidItemNumber%></td>
</tr>
<tr>
	<td width=100px align=right class=label>Quantity:</td>
	<td valign=top>&nbsp;<%=s_bidQuantity%></td>
</tr>
<tr>
	<td width=100px align=right class=label>Commodity:</td>
	<td valign=top>&nbsp;<%=s_bidCommodity%></td>
</tr>
<tr>
	<td width=100px align=right class=label>Bid Variance:</td>
	<td valign=top>&nbsp;<%=s_bidVariance%>%</td>
</tr>
<tr>
	<td width=100px align=right class=label>Due Date:</td>
	<td valign=top>&nbsp;<%=s_auctionDueDate%></td>
</tr>

</table>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<br><td width=34% align=center><img src="<%=contextPath%>/supplierportal/images/chart.jpg" class=button border=0 tabIndex=999></td>
</tr>
</table>



<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<br><td width=34% align=center><a href="javascript: window.close();  void(0);"><img src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0 tabIndex=999></a></td>
</tr>
</table>


<br>


<br>

</form>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>