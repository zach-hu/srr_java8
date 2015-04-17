<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%	AssetLocation		assetLocation		= (AssetLocation) request.getAttribute("assetLocation");

	//In general, We get information about some members of the asset location entity in this case tagNumber, sequenceNo, itemNumber, trackingNumber-->
	String AssetLocation_tagNumber	= (String)request.getAttribute("AssetLocation_tagNumber");
	String AssetLocation_sequenceNo	= "";
	String Asset_itemNumber 		= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description		= (String)request.getAttribute("Asset_description");
	String Asset_trackingNumber 	= (String)request.getAttribute("Asset_trackingNumber");
	String action 				= "";

	if (HiltonUtility.isEmpty(Asset_trackingNumber)) { Asset_trackingNumber = "N/A"; }

	if (AssetLocation_tagNumber.equalsIgnoreCase("")) {
		AssetLocation_tagNumber = (String)request.getAttribute("Asset_tagNumber");
	}

	String s_quantity_decimals		= PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String s_current_process		= "ASSETLOCATION";
	String s_current_page			= "/asset/location/assetlocation_review.jsp";
	String s_current_method			= "DoNothing";
	String s_current_process_method	= "";

	UserProfile owner = new UserProfile();

	if(assetLocation != null) {
		AssetLocation_tagNumber = assetLocation.getComp_id().getTagNumber();
		AssetLocation_sequenceNo = assetLocation.getComp_id().getSequenceNo().toString();
		action = "new";
		owner = UserManager.getInstance().getUser(oid, assetLocation.getUserId());
	} else {
		assetLocation = new AssetLocation();
		owner = UserManager.getInstance().getUser(oid, uid);
	}

	String s_zero = "";
	String s_tag_number = AssetLocation_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	String s_tracking_number = Asset_trackingNumber;

	String accessASTN = "";		if (role.getAccessRights("ASTN")<1) { accessASTN = "disabled"; }
	String accessASTF = "";		if (role.getAccessRights("ASTF")<1) { accessASTF = "disabled"; }
	String accessASTB = "";		if (role.getAccessRights("ASTB")<1) { accessASTB = "disabled"; }
	pageContext.setAttribute("oid", oid);
%>

<tsa:hidden name="Asset_trackingNumber" value="<%=Asset_trackingNumber%>"/>
<tsa:hidden name="AssetLocation_tagNumber" value="<%=AssetLocation_tagNumber%>"/>
<tsa:hidden name="AssetLocation_sequenceNo" value="<%=AssetLocation_sequenceNo%>"/>
<tsa:hidden name="Asset_tagNumber" value="<%=AssetLocation_tagNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=Asset_itemNumber%>"/>
<tsa:hidden name="Asset_description" value="<%=Asset_description%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
		<tsa:tr><tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12">
		<tsa:label labelName="asset-assetlocation" defaultString="Asset Location" /><%=headerEncoder.encodeForHTML(s_tracking_number)%></div></tsa:td></tsa:tr>
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
  <!--In this part, We can show a list for asset location-->
  <tsa:td align="center" width="530px"></tsa:td>
  <tsa:td align="center" width="150px"><a href="javascript: browseAsset('assetlocation'); void(0);"><tsa:label labelName="assetlocation-history" defaultString="History"/></a></tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tsa:tr>
      <tsa:td width="680px" align="center" valign="top">
      	<%@ include file="/asset/location/assetlocation_data.jsp" %>
      </tsa:td>
      <tsa:td rowspan="7" colspan="15" align="right" valign="top">
      	<table border="0" cellpadding="0" cellspacing="0">
      	<tsa:tr><tsa:td colspan="12" align="right">
      		<%@ include file="/asset/asset_sidebar.jsp"%></tsa:td>
      	</tsa:tr>
      	<!--In this part, We can add and save an asset location-->
      	<tsa:tr><tsa:td align="center">
      		<% if (role.getAccessRights("ASSETS")>=2 && !action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: addassetlocation('<%=action%>'); void(0);"><tsa:label labelName="ass-save" defaultString="Save"></tsa:label></a>
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

<!--In this part, We can add and save an asset location-->
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
<% if (role.getAccessRights("ASSETS")>=2 && action.equalsIgnoreCase("new")) { %>
<tsa:td align="center"><div id="pxbutton"><a href="javascript: addassetlocation('<%=action%>'); void(0);"><% if (action.equalsIgnoreCase("new")) { %><tsa:label labelName="ass-add" defaultString="Add" ></tsa:label> <% } else { %><tsa:label labelName="ass-save" defaultString="Save"></tsa:label><% } %></a></div></tsa:td>
<% } %>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	//setNavCookie('/asset/asset_general.jsp', 'DoNothing', 'Asset Information');
	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("Asset Location") < 0)
	{
		setNavCookie("<%= s_current_page %>", "<%= s_current_method %>", "Asset Location");
	}
//-->
</script>