<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	AssetNote			assetNote			= (AssetNote) request.getAttribute("assetNote");
	//In general, We get information about some members of the asset note entity in this case tagNumber, sequenceNo, itemNumber, trackingNumber-->
	String AssetNote_tagNumber	= (String)request.getAttribute("AssetNote_tagNumber");
	String AssetNote_sequenceNo	= "";
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");
	String Asset_trackingNumber 	= (String)request.getAttribute("Asset_trackingNumber");
	String usert 				= uid;
	String action 				= "";

	if (HiltonUtility.isEmpty(Asset_trackingNumber)) { Asset_trackingNumber = "N/A"; }

	if (AssetNote_tagNumber.equalsIgnoreCase("")) {
		AssetNote_tagNumber = (String)request.getAttribute("Asset_tagNumber");
	}

	String s_quantity_decimals		= PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String s_current_process		= "ASSETNOTE";
	String s_current_page			= "/asset/note/assetnote_review.jsp";
	String s_current_method			= "AssetNoteUpdate";
	String s_current_process_method	= "";

	if(assetNote != null) {
		AssetNote_tagNumber = (String)request.getAttribute("AssetNote_tagNumber");
		AssetNote_sequenceNo = assetNote.getComp_id().getSequenceNo().toString();
		action = "new";
		usert = assetNote.getUserId();
	} else {
		assetNote = new AssetNote();
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, usert);

	String s_zero = "";
	String s_tag_number = AssetNote_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	String s_tracking_number = Asset_trackingNumber;

	String accessASTN = "";		if (role.getAccessRights("ASTN")<1) { accessASTN = "disabled"; }
%>

<tsa:hidden name="Asset_trackingNumber" value="<%=Asset_trackingNumber%>"/>
<tsa:hidden name="AssetNote_tagNumber" value="<%=AssetNote_tagNumber%>"/>
<tsa:hidden name="AssetNote_sequenceNo" value="<%=AssetNote_sequenceNo%>"/>
<tsa:hidden name="Asset_tagNumber" value="<%=AssetNote_tagNumber%>"/>
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
		<tsa:tr><tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="assetnote" defaultString="Asset Note" /></div></tsa:td></tsa:tr>
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
  <!--In this part, We can show a list for asset notes-->
  <tsa:td align="center" width="530px"></tsa:td>
  <tsa:td align="center" width="150px"><a href="javascript: browseAsset('assetnote'); void(0);"><tsa:label labelName="assetnote-history" defaultString="History"/></a></tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tsa:tr>
      <tsa:td width="300px" align="center" valign="top">
      	<%@ include file="/asset/note/assetnote_data.jsp" %>
      </tsa:td>
      <tsa:td rowspan="5" align="right" valign="top">
      	<table border="0" cellpadding="0" cellspacing="0">
      	<tsa:tr><tsa:td>
      		<%@ include file="/asset/asset_sidebar.jsp"%>
      	</tsa:td></tsa:tr>
      	<tsa:tr><tsa:td align="center">
      		<!--In this part, We can add and update asset notes-->
      		<% if (role.getAccessRights("ASSETS")>=2 && !action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: addassetnote('<%=action%>'); void(0);"><tsa:label labelName="ass-add" defaultString="Add a Note" ></tsa:label></a></div>
			<% } else if (role.getAccessRights("ASSETS")>=1 && action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: updateassetnote(); void(0);"><tsa:label labelName="ass-save" defaultString="Update an Asset" ></tsa:label></a></div>
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
<!--In this part, We can add and save asset notes -->
<% if (role.getAccessRights("ASSETS")>=2 && action.equalsIgnoreCase("new")) { %>
	<tsa:td align="center"><div id="pxbutton"><a href="javascript: addassetnote('<%=action%>'); void(0);"><% if (action.equalsIgnoreCase("new")) { %><tsa:label labelName="ass-add" defaultString="Add" ></tsa:label> <% } else { %><tsa:label labelName="ass-save" defaultString="Save"></tsa:label><% } %></a></div></tsa:td>
<% } %>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/asset/asset_general.jsp', 'DoNothing', 'Asset Information');
//-->
</script>