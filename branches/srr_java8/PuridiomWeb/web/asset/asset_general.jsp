<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	//In this part we get information about assets, like TagNumber, itemNumber, description, trackingNumber
	Asset	asset= (Asset)request.getAttribute("asset");
	Asset Asset_TagNumber = (Asset) request.getAttribute("Asset_TagNumber");

	String Asset_tagNumber		= "";
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");
	String Asset_trackingNumber 	= (String)request.getAttribute("Asset_trackingNumber");
	if (HiltonUtility.isEmpty(Asset_description)) { Asset_description = ""; }
	if (HiltonUtility.isEmpty(Asset_itemNumber)) { Asset_itemNumber = ""; }
	String usert 				= uid;
	String action 				= "";

	String s_quantity_decimals		= PropertiesManager.getInstance(oid).getProperty("MISC", "QtyDecimals", "2");
	String s_current_process 		= "ASSET";
	String s_current_page 			= "/asset/asset_general.jsp";
	String s_current_method 		= "AssetUpdate";
	String s_current_process_method = "";

	if(asset != null) {
		Asset_tagNumber = asset.getTagNumber();
		Asset_description = asset.getDescription();
		Asset_itemNumber = asset.getItemNumber();
		Asset_trackingNumber = asset.getTrackingNumber();
		action = "new";
		usert = asset.getOwner();
	} else {
		asset = new Asset();
		action = (String)request.getAttribute("action");
		if (action==null) { action = ""; }
		if (action.equalsIgnoreCase("itemnew")) {
			Asset_itemNumber = "";
			Asset_description = "";
		}
	}
	if (HiltonUtility.isEmpty(Asset_trackingNumber)) { Asset_trackingNumber = ""; }

	UserProfile owner = UserManager.getInstance().getUser(oid, usert);

	String s_zero = "";
	String s_tag_number = Asset_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	String s_tracking_number = Asset_trackingNumber;

	String accessASTC = "";		if (role.getAccessRights("ASTC")<1) { accessASTC = "disabled"; }
	String accessASTD = "";		if (role.getAccessRights("ASTD")<1) { accessASTD = "disabled"; }
	String accessASTO = "";		if (role.getAccessRights("ASTO")<1) { accessASTO = "disabled"; }
	String accessASTP = "";		if (role.getAccessRights("ASTP")<1) { accessASTP = "disabled"; }
	String accessASTS = "";		if (role.getAccessRights("ASTS")<1) { accessASTS = "disabled"; }
	String accessASTU = "";		if (role.getAccessRights("ASTU")<1) { accessASTU = "disabled"; }
	String accessASTUDFS = "";	if (role.getAccessRights("ASTUDFS")<1) { accessASTUDFS = "disabled"; }

%>
<tsa:hidden name="DocAttachment_icHeader" value="<%=Asset_tagNumber%>"/>

<tsa:hidden name="Asset_trackingNumber" value="<%=Asset_trackingNumber%>"/>
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

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<!--In this part we have a function to attach a document-->
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow" colspan="2"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
		<tsa:tr><tsa:td noWrap="noWrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12">
		<tsa:label labelName="asset-asset" defaultString="Asset" /><%=" " + s_tracking_number%></div></tsa:td>
		<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12"><a href="javascript: viewAttachments('<%=Asset_tagNumber%>'); void(0);"><img src="<%=contextPath%>/images/clip.gif" border="0" alt="Attachments"></a></div></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="100%" /></tsa:td>
	<tsa:td valign="bottom" align="right" width="*">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tsa:tr><tsa:td width="100%" align="right" valign="top" noWrap="nowrap"><%@ include file="/asset/asset_display_number.jsp"%></tsa:td></tsa:tr>
			<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
			<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
  <tsa:td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tsa:tr>
      <tsa:td width="300px" align="center" valign="top">
      	<%@ include file="/asset/asset_data.jsp" %>
      </tsa:td>
      <tsa:td rowspan="5" align="right" valign="top">
      	<table border="0" cellpadding="0" cellspacing="0">
      	<tsa:tr><tsa:td>
      		<%@ include file="/asset/asset_sidebar.jsp"%>
      	</tsa:td></tsa:tr>
      	<!--In this part we have two function to add and update an asset-->
      	<tsa:tr><tsa:td align="center">
      		<% if (role.getAccessRights("ASSETS")>=2 && !action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: addasset('<%=headerEncoder.encodeForJavaScript(action)%>'); void(0);"><tsa:label labelName="ass-save" defaultString="Save"></tsa:label></a></div>
			<% } else if (role.getAccessRights("ASSETS")>=1 && action.equalsIgnoreCase("new")) { %>
				<div id="pxbutton"><a href="javascript: updateasset(); void(0);"><tsa:label labelName="ass-save" defaultString="Save"></tsa:label></a></div>
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
<!--In this part we have a function to add a new asset
<% if (role.getAccessRights("ASSETS")>=2 && action.equalsIgnoreCase("new")) { %>
	<tsa:td align="center"><a href="javascript: addasset('<%=headerEncoder.encodeForJavaScript(action)%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0 alt="Add an Asset"></a></tsa:td>
<% } %>-->
<!--In this part we have a function to return to the main page-->
<tsa:td align="center"><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><tsa:label labelName="ass-return" defaultString="Return"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>

<!--In this part we have a list that contain the attachment files in diferents ways and formats like doc, pdf, xls, gif, and bmp-->
<%
    List attachmentList = (List)asset.getDocAttachmentList();
      int ai = 0;
      if (attachmentList != null)
      {
        	for(int i = 0; i < attachmentList.size(); i++)
        	{
          		DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
          		if (docAttachment == null)
          		{
            		continue;
          		}
          		String	filename = docAttachment.getDocFilename();
          		String	ext = filename.substring(filename.lastIndexOf(".") + 1);
          		ai++;
				%>
		        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        		<tsa:tr>
		        <tsa:td width="80%" cssClass="browseRow">

        		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="browseRow">
            	<tsa:tr>
	               <tsa:td width="25px" align="center" valign="middle">
					<%
					if (ext.equalsIgnoreCase("DOC"))
						{%>
        	      			<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border="0" alt="Open Attached MS Word Document"></a>
						<%}

					else if (ext.equalsIgnoreCase("PDF"))
						{%>
		        	      <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document"></a>
						<%}
					else if (ext.equalsIgnoreCase("XLS"))
						{%>
			              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document"></a>
						<%}
					else if (ext.equalsIgnoreCase("MPP"))
						{%>
			              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border="0" alt="Open Attached MS Project Document"></a>
						<%}
					else if (ext.equalsIgnoreCase("PPT"))
						{%>
			              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document"></a>
						<%}
					else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
              				<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border="0" alt="Open Attached Image"></a>
						<%}
					else {%>
		              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0" alt="Open Attached Document"></a>
						<%}
					%>
              </tsa:td>

              <tsa:td>
                <a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
                <tsa:hidden name="docFilename" value="<%=filename%>"/>
              </tsa:td>
              </tsa:tr>
              </table>

          		</tsa:td>

        	    <tsa:td width="10%" cssClass="browseRow" align="center" valign="top"><%=docAttachment.getDocPrint()%></tsa:td>
    	        <tsa:td width="10%" cssClass="browseRow" align="center" valign="top"></tsa:td>

	            </tsa:tr>
        		</table>
  				<%
  				}
      	}
%>
<br><br>




<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/asset/asset_general.jsp', 'DoNothing', 'Asset Information');

	<!--In this part we have a function to go to other page that follow to attach a document-->
	function viewAttachments(Asset_tagNumber)
	{
		if (Asset_tagNumber == ""){
			alert("You must save the asset before you can add an attachment.");
		}
		else {
       		doSubmit('/asset/asset_attachments.jsp', 'DocAttachmentRetrieveByHeader');
		}
  	}

	<!--In this part we have a function to show some features of an asset-->
	function browseOrders()
	{
		popupParameters = "formField=Asset_purchaseOrder;browseName=asset_poblanketbrowse;allowBrowse=" + frm.allowBrowse.value;
		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	frm.Asset_statusOriginal.value = frm.Asset_assetStatus.value;

	<% if (asset.getLease().equalsIgnoreCase("L")) { %>
		displayArea('AssetLeased');
	<% } %>

//-->
</script>