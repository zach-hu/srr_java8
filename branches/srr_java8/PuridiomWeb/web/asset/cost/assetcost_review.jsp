<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	AssetCost 			assetCost			= (AssetCost) request.getAttribute("assetCost");
	//In general, We get information about some members of the asset cost in this case tagNumber, itemNumber, trackingNumber-->
	String AssetCost_tagNumber	= (String)request.getAttribute("AssetCost_tagNumber");
	String AssetCost_sequenceNo	= "";
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");
	String Asset_trackingNumber = (String)request.getAttribute("Asset_trackingNumber");
	String action 				= "";

	if (HiltonUtility.isEmpty(Asset_trackingNumber)) { Asset_trackingNumber = "N/A"; }

	if (AssetCost_tagNumber.equalsIgnoreCase("")) {
		AssetCost_tagNumber = (String)request.getAttribute("Asset_tagNumber");
	}

	String s_quantity_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String s_current_process = "ASSETCOST";
	String s_current_page = "/asset/cost/assetcost_review.jsp";
	String s_current_method = "AssetCostUpdate";
	String s_current_process_method	= "";

	if(assetCost != null) {
		AssetCost_tagNumber = assetCost.getComp_id().getTagNumber();
		AssetCost_sequenceNo = assetCost.getComp_id().getSequenceNo().toString();
		action = "new";
	} else {
		assetCost = new AssetCost();
	}

	String s_zero = "";
	String s_tag_number = AssetCost_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	String s_tracking_number = Asset_trackingNumber;

	String accessASTP = "";		if (role.getAccessRights("ASTP")<1) { accessASTP = "disabled"; }
	pageContext.setAttribute("oid", oid);
%>

<tsa:hidden name="Asset_trackingNumber" value="<%=Asset_trackingNumber%>"/>
<tsa:hidden name="AssetCost_tagNumber" value="<%=AssetCost_tagNumber%>"/>
<tsa:hidden name="AssetCost_sequenceNo" value="<%=AssetCost_sequenceNo%>"/>
<tsa:hidden name="Asset_tagNumber" value="<%=AssetCost_tagNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=Asset_itemNumber%>"/>
<tsa:hidden name="Asset_description" value="<%=Asset_description%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
		<tsa:tr><tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="assetcost" defaultString="Asset Cost" /><%=headerEncoder.encodeForHTML(s_tracking_number)%></div></tsa:td></tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="100%" /></tsa:td>
	<tsa:td valign="bottom" align="right" width="*">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tsa:tr><tsa:td align="right" valign="top" noWrap="nowrap"><%@ include file="/asset/asset_display_number.jsp"%></tsa:td></tsa:tr>
			<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
			<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="2" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td align="center" width="530px"></tsa:td>
  <tsa:td align="center" width="150px"><a href="javascript: browseAsset('assetcost'); void(0);"><tsa:label labelName="ass-history" defaultString="History"></tsa:label></a></tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tsa:tr>
      <tsa:td width="300px" align="center" valign="top">
      	<%@ include file="/asset/cost/assetcost_data.jsp" %>
      </tsa:td>
      <tsa:td rowspan="5" align="right" valign="top">
      	<table border="0" cellpadding="0" cellspacing="0">
      	<tsa:tr><tsa:td>
      		<%@ include file="/asset/asset_sidebar.jsp"%>
      	</tsa:td></tsa:tr>
      	<!--In this part, We have two buttons to add and update assets-->
      	<tsa:tr><tsa:td align="center">
      		<% if (role.getAccessRights("ASSETS")>=2 && !action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: addassetcost('<%=action%>'); void(0);"><tsa:label labelName="ass-save" defaultString="Save"></tsa:label></a></div>
			<% } else if (role.getAccessRights("ASSETS")>=1 && action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: updateassetcost(); void(0);"><tsa:label labelName="ass-save" defaultString="Save"></tsa:label></a></div>
			<% } %>
      	</tsa:td></tsa:tr>
      	</table>
      </tsa:td>
	</tsa:tr>
	</table>
  </tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
<% if (role.getAccessRights("ASSETS")>=2 && action.equalsIgnoreCase("new")) { %>
	<tsa:td align="center"><div id="pxbutton"><a href="javascript: addassetcost('<%=action%>'); void(0);"><tsa:label labelName="ass-add" defaultString="Add"></tsa:label></a></div></tsa:td>
<% } %>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	//setNavCookie('/asset/asset_general.jsp', 'DoNothing', 'Asset Information');
	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("Asset Cost Review") < 0)
	{
		setNavCookie("<%= s_current_page %>", "<%= s_current_method %>", "Asset Cost Review");
	}

	<!--We have a function to show a browse with contain some parameters about asset cost -->
	function browseOrders()
	{
		popupParameters = "formField=AssetCost_poNumber;browseName=assetcost_poblanketbrowse;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}
//-->
</script>