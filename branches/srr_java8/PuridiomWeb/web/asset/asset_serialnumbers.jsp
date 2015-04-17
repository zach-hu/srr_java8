<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<!--In this part we get information of asset entity -->
<%	List assetList = (List)request.getAttribute("assetList");
	String assetNumber = (String)request.getAttribute("Asset_number");
	String Asset_tagNumber = (String)request.getAttribute("Asset_tagNumber");
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_current_process = "ASSETTOTALS";
	String	s_current_page = "/asset/asset_totals.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String  action = "";

	String s_zero = "";
	String s_tag_number = Asset_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	pageContext.setAttribute("oid", oid);
%>

<tsa:hidden name="Asset_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=Asset_itemNumber%>"/>
<tsa:hidden name="Asset_description" value="<%=Asset_description%>"/>
<tsa:hidden name="AssetLocation_tagNumber" value=""/>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<!--In this part we get the asset serial numbers -->
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
				<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="assetserialnumbers" defaultString="Asset Serial Numbers" /></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td align=right valign=top nowrap><%@ include file="/asset/asset_display_number.jsp"%></td></tr>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tr>
      <td width="300px" align="center" valign="top">
   		<!--In this part we show the asset serial numbers and tagnumber value-->
      	<table border=0 <%if (assetList.size()<7) {%>height="200px"<%}%>>
		<tr>
			<td height="20px">Tag Number</td><td>Serial Number</td>
		</tr>
			<%
				for (int i=0; i<assetList.size(); i++) {
				Asset asset = (Asset) assetList.get(i);
			%>
		<tr>
			<!--In this part we can show the asset according with the tagnumber value-->
			<td height="20px"><a href="javascript: viewasset2('<%=Asset_tagNumber%>'); void(0);"> <%=asset.getTagNumber()%></a> </td>
			<td> <input type="text" name="Asset_serialNumber"> </td>
		</tr>
			<% } %>
		<tr><td>&nbsp;</td><tr>
		</table>
		<table>
		<tr>
			<!--In this part we have a function to update the assetitem and save the serial number-->
			<td colspan="2" align="center"> <a href="javascript: updateassetitem(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save Serial Numbers"></a> </td>
		</tr>
		</table>
      </td>
      <td rowspan="5" align="right" valign="top"><%@ include file="/asset/asset_sidebar.jsp"%></td>
	</tr>
	</table>
  </td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/asset/asset_totals.jsp', 'DoNothing', 'Asset Information');

	function viewasset2(tagNumber)
	{
		frm.Asset_tagNumber.value = tagNumber;
		doSubmit("/asset/asset_general.jsp", "AssetRetrieveById");
	}
//-->
</script>