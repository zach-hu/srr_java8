<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/assetOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<%	Asset				asset 				= (Asset)request.getAttribute("asset");
	AssetLocation		assetLocation		= (AssetLocation)request.getAttribute("assetLocation");
	AssetCost			assetCost			= (AssetCost)request.getAttribute("assetCost");
	AssetNote			assetNote			= (AssetNote)request.getAttribute("assetNote");
	AssetService		assetService		= (AssetService)request.getAttribute("assetService");

	String Asset_tagNumber		= "";
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");
	String Asset_trackingNumber = (String)request.getAttribute("Asset_trackingNumber");
	String Asset_printed		= "";

	if (HiltonUtility.isEmpty(Asset_trackingNumber)) { Asset_trackingNumber = "N/A"; }
	if (HiltonUtility.isEmpty(Asset_description)) { Asset_description = ""; }
	String usert 				= uid;
	String action 				= "";

	String s_quantity_decimals		= PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String s_current_process 		= "ASSETREVIEW";
	String s_current_page 			= "/asset/asset_review.jsp";
	String s_current_method 		= "DoNothing";
	String s_current_process_method = "";

	if(asset != null) {
		Asset_tagNumber = asset.getTagNumber();
		Asset_description = asset.getDescription();
		Asset_itemNumber = asset.getItemNumber();
		Asset_trackingNumber = asset.getTrackingNumber();
		usert = asset.getOwner();
		Asset_printed = asset.getPrinted();
	} else {
		asset = new Asset();
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, usert);

	String s_zero = "";
	String s_tag_number = Asset_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	String s_tracking_number = Asset_trackingNumber;
	String s_asset_printed = Asset_printed;

	String accessASTC = "";		if (role.getAccessRights("ASTC")<1) { accessASTC = "disabled"; }
	String accessASTD = "";		if (role.getAccessRights("ASTD")<1) { accessASTD = "disabled"; }
	String accessASTO = "";		if (role.getAccessRights("ASTO")<1) { accessASTO = "disabled"; }
	String accessASTP = "";		if (role.getAccessRights("ASTP")<1) { accessASTP = "disabled"; }
	String accessASTS = "";		if (role.getAccessRights("ASTS")<1) { accessASTS = "disabled"; }
	String accessASTU = "";		if (role.getAccessRights("ASTU")<1) { accessASTU = "disabled"; }
	String accessASTUDFS = "";	if (role.getAccessRights("ASTUDFS")<1) { accessASTUDFS = "disabled"; }
	pageContext.setAttribute("oid", oid);
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	viewType = "${esapi:encodeForJavaScript(s_view)}";
	tagNumber = "<%=s_tracking_number%>";
	printed = "<%=Asset_printed%>";

	Array91= createAssetOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<tsa:hidden name="Asset_trackingNumber" value="<%=Asset_trackingNumber%>"/>
<tsa:hidden name="Asset_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=Asset_itemNumber%>"/>
<tsa:hidden name="Asset_description" value="<%=Asset_description%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="AssetCost_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="AssetLocation_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="AssetNote_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="AssetService_tagNumber" value="<%=Asset_tagNumber%>"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="assetreview" defaultString="Asset Review" /><%=" " + s_tracking_number%></div></td></tr>
		</table>
	</td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="100%" /></tsa:td>
	<tsa:td valign="bottom" align="right" width="*">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tsa:tr><tsa:td align="right" valign="top" noWrap="nowrap"><%@ include file="/asset/asset_display_number.jsp"%></tsa:td></tsa:tr>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</tsa:td>
</tr>
</table>

<br>

<table border="0" cellpadding="2" cellspacing="0" width="680px">
<% if (Utility.isEmpty(Asset_trackingNumber)) {%>
<tr>
  <td align="center" width="530px"></td>
  <td align="center" width="150px"><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
</tr>
<%} %>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tr>
      <td width="380px" align="center" valign="top">
      	<%@ include file="/asset/asset_data_review.jsp" %>
      </td>
      <td rowspan="5" align="right" valign="top"><%@ include file="/asset/asset_sidebar.jsp"%></td>
	</tr>
	</table>
  </td>
</tr>
</table>



<br>
<table border=0>

	<tr>
		<td align="left" colspan=1>
		 <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp; <tsa:label labelName="assetlocation" defaultString="Asset Location" /></td>
             </tr>
            </table>

		</td>

	<td  align="right" colspan=1>
		 <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp; <tsa:label labelName="assetnote" defaultString="Asset Note" /></td>
              </tr>
            </table>
		</td>
	</tr>
	<tr>
		<%if(assetLocation != null){%>
		<td align="right" valign="top"><%@ include file="/asset/location/assetlocation_data_review.jsp"%></td>
		<%}
		  else {%>
			<table border=0 cellspacing=0 cellpadding=2 width="300px">

			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=120px>
				<tr align="right"><div style="text-align: right">Name:</tr>
				<tr align="right"><div style="text-align: right">User Name:</tr>
				<tr align="right"><div style="text-align: right">Telephone:</tr>
				<tr align="right"><div style="text-align: right">Email:</tr>
				<tr align="right"><div style="text-align: right">Ship To:</tr>
				<tr align="right"><div style="text-align: right">Facility:</tr>
				</table>
			</td>

			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=120px>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				</table>
			</td>
		 <%}%>

			<%if(assetNote != null){%>
				<td  align="right" valign="top"><%@ include file="/asset/note/assetnote_data_review.jsp"%></td>
			<%}
			else{%>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width="150px">
				<tr align="right"><div style="text-align: right">Date Changed:</tr>
				<tr align="right"><div style="text-align: right">Date Entered:</tr>
				<tr align="right"><div style="text-align: right">Entered By:</tr>
				<tr align="right"><div style="text-align: right">Description:</tr>
				</table>
			</td>

			<td>
				<table border=0 cellspacing=0 cellpadding=2 width="100px">
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				</table>
			</td>
			<%}%>
	</tr>
</table>

<table border=0 width="400px" cellspacing=0 cellpadding=2>
	<tr>
	<td  align="right" colspan=1>
		 <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp; <tsa:label labelName="assetservice" defaultString="Asset Service" /></td>
              </tr>
         </table>
	</td>

	<td  align="right" colspan=1>
		 <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp; <tsa:label labelName="assetcost" defaultString="Asset Cost" /></td>
              </tr>
         </table>
	</td>
	</tr>

		<tr>
		<% if(assetService != null){%>
			<td><%@ include file="/asset/service/assetservice_data_review.jsp"%></td>
		<%}
		else{%>
		<tr>
			<table border=0 cellspacing=0 cellpadding=2 width="250px">
			<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width="120px">
				<tr><div style="text-align: right">Initiated By:</tr>
				<tr><div style="text-align: right">Initiated Date:</tr>
				<tr><div style="text-align: right">Service Call Date:</tr>
				<tr><div style="text-align: right">Response Date:</tr>
				<tr><div style="text-align: right">Completion Date:</tr>
				<tr><div style="text-align: right">Service Action:</tr>
				<tr><div style="text-align: right">Response cost:</tr>
				</table>
			</td>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width="120px">
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				<tr> <td align="right"></td><td align="left">&nbsp;</td></tr>
				</table>
			</td>
		<% } %>
		<%if(assetCost != null){%>
              <td align="right" valign="top"><%@ include file="/asset/cost/assetcost_data_review.jsp"%></td>
			 <%}
		else{%>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width="150px">
				<tr align="right"> <td><div style="text-align: right">Receiverd:</tr>
				<tr align="right"> <td><div style="text-align: right">Purchased:</tr>
				<tr align="right"> <td><div style="text-align: right">Amount:</tr>
				<tr align="right"> <td><div style="text-align: right">Po Number:</tr>
				<tr align="right"> <td><div style="text-align: right">Extend Life:</tr>
				<tr align="right"> <td><div style="text-align: right">Supplier:</tr>
				<tr align="right"> <td><div style="text-align: right">Description:</tr>
				</table>
				</td>
				<td>
				<table border=0 cellspacing=0 cellpadding=2 width="100px">
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				<tr align="right"></tr>
				</table>
			    </td>
			</tr>
			<%}%>
			</table>
			</tr>
	</table>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("Asset Review") < 0)
	{
		setNavCookie('<%= s_current_page %>', '<%= s_current_method %>', 'Asset Review');
	}
	//setNavCookie('/asset/asset_review.jsp', 'DoNothing', 'Asset Information');

	function duplicateRecords() {
		var assetCloneQty = prompt("How many would you like to create:","1");
		var newInputField = "<input type='hidden' name='Asset_cloneQty' value='" + assetCloneQty + "'>";
		setHiddenFields(newInputField);
		doSubmit('/asset/asset_review.jsp','AssetAddClones;AssetRetrieve');
	}

	function printBarcode() {
		input_box=confirm("Are sure you want to Print Barcode?");
		if (input_box==true) {
			popupParameters = "Asset_tagNumber=<%=Asset_tagNumber%>";
			doSubmitToPopup('','AssetPrintBarcode', 'width=775px', 'height=850px');
			doSubmit('/asset/asset_review.jsp','AssetUpdateBarcode;AssetRetrieve');
		}
	}
	function reprintBarcode() {
		input_box=confirm("Are sure you want to RePrint Barcode?");
		if (input_box==true) {
			popupParameters = "Asset_tagNumber=<%=Asset_tagNumber%>";
			doSubmitToPopup('','AssetPrintBarcode', 'width=775px', 'height=850px');
			doSubmit('/asset/asset_review.jsp','AssetUpdateBarcode;AssetRetrieve');
		}
	}

-->
</script>