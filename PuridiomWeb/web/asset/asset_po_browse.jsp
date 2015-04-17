<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	List assetList = (List) request.getAttribute("assetList");
	if (assetList==null) {
		assetList = new ArrayList();
	}
	//In this part we get some attributes of asset, poline, and poheader entities-->
	String action = (String) request.getAttribute("action");
	String process = (String) request.getAttribute("process");
	String urlret = (String) request.getAttribute("urlret");
	String PoHeader_icPoHeader = (String) request.getAttribute("PoHeader_icPoHeader");
	String lineCount = "";
	String frompage = "";
	String functionret = "";
	String Asset_icLineKey = "";
	String Asset_purchaseOrder = "";
	String PoLine_icPoHeader = "";
	String PoLine_icPoLine = "";
	String PoHeader_releaseNumber = "";
	String PoHeader_revisionNumber = "";
	String PoHeader_poType = "";
	String PoHeader_status = "";

	//In this part we get the icLineKey of asset entity-->
	if (action.indexOf("po")==0) {
		Asset_icLineKey = (String)request.getAttribute("Asset_icLineKey");
	}

	//In this part we get the po-items and the icPoHeader of PoLine entity-->
	if (action.equalsIgnoreCase("forward") || action.equalsIgnoreCase("po-items")) {
		Asset_purchaseOrder = (String)request.getAttribute("Asset_purchaseOrder");
		if (action.equalsIgnoreCase("po-items")) {
			PoLine_icPoHeader = (String) request.getAttribute("PoLine_icPoHeader");
			PoHeader_status = (String) request.getAttribute("PoHeader_status");
		}
	}

	//In this part we get the frompage, frompage, icPoHeader of PoLine entity and the release and revision Number of PoHeader entity -->
	if (action.equalsIgnoreCase("po-item")) {
		frompage = (String)request.getAttribute("frompage");
		lineCount = (String)request.getAttribute("frompage");
		PoLine_icPoLine = (String) request.getAttribute("PoLine_icPoLine");
		PoHeader_releaseNumber = (String) request.getAttribute("PoHeader_releaseNumber");
		PoHeader_revisionNumber = (String) request.getAttribute("PoHeader_revisionNumber");
		PoHeader_poType = (String)request.getAttribute("PoHeader_poType");
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_current_process = "ASSETTOTALS";
	String	s_current_page = "/asset/asset_totals.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
%>

<tsa:hidden name="urlret" value="<%=urlret%>"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
				<tsa:tr><tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle"><div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="assetpolinebrowse" defaultString="Asset Browse" /></div></tsa:td></tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="100%" /></tsa:td>
	<tsa:td valign="bottom" align="right">
		<table cellpadding="0" cellspacing="0" border="0">
			<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
			<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tsa:tr>
      <tsa:td width="680px" valign="top">
      	<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0">
      	<tsa:tr><tsa:td valign="top" align="center" width="680px">
      	<table id="browseTable" border="0" cellspacing="0" cellpadding="0" width="680px">
		<tsa:tr>
			<tsa:td width="5px">&nbsp;</tsa:td>
			<tsa:td width="670px" cssClass="browseHdrDk" align="center" valign="top">
			<!--In this part we show some parameters of asset entity like Item Number, Tag Number, Status, Serial, etc  -->
			<table border="0" cellspacing="0" cellpadding="3" width="665px">
				<tsa:tr>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="ass-item-number" defaultString="Item Number"></tsa:label> </tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="20%" align="left"> <tsa:label labelName="ass-tag-number" defaultString="Tag Number"></tsa:label> </tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"><tsa:label labelName="status" defaultString="Status" /></tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="ass-serial" defaultString="Serial"></tsa:label> </tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="10%" align="center"> <tsa:label labelName="ass-generate-tag" defaultString="Generate Tag"></tsa:label> </tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="10%" align="center"> <tsa:label labelName="ass-cancel-tag" defaultString="Cancel Tag"></tsa:label> </tsa:td>
					<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="10%" align="center"> <tsa:label labelName="ass-add-new-tag" defaultString="Add New Tag"></tsa:label> </tsa:td>
				</tsa:tr>
			</table>
			<div id="browseBorder" class="browseHdrDk" style="border: solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align: center; overflow-y: visible; overflow-x: auto;">
		<%
			for(int i=0; i<assetList.size(); i++)
			{
			  Asset asset = (Asset) assetList.get(i);
			  String classtd = "";
			  if (i%2==0)
			  { classtd = "browseRow"; }
			  else
			  { classtd = "summary"; }
		%>
			<table id="browseRows" border="0" cellspacing="0" cellpadding="0" width="665px" class="browseRow">
				<tsa:tr>
					<tsa:td cssClass="<%=classtd%>">
					<!--In this part we show a list with their respective tagNumber-->
					<tsa:hidden name="Asset_tagNumberList" value="<%=asset.getTagNumber()%>"/>
					<table border="0" cellspacing="0" cellpadding="2" width="665px" class="<%=classtd%>">
					<tsa:tr cssClass="<%=classtd%>">
						<!--In this part we get the ItemNumber -->
						<tsa:td cssClass="<%=classtd%>" align="left" width="15%" valign="top">
							<%=asset.getItemNumber()%>
						</tsa:td>
						<!--In this part we have a function to show the asset according with the tagNumber attribute -->
						<tsa:td cssClass="<%=classtd%>" align="left" width="20%" valign="top">
							<a href="javascript: viewassetdata('<%=asset.getTagNumber()%>','<%=asset.getItemNumber()%>','<%=asset.getDescription()%>');" onMouseOver="showDetails(<%=i%>);" onMouseOut="hideDetails(<%=i%>);"><%=asset.getTrackingNumber()%></a>
						</tsa:td>
						<!--In this part we get the Status -->
						<tsa:td cssClass="<%=classtd%>" align="left" width="15%" valign="top">
							<%=asset.getAssetStatus()%>
						</tsa:td>
						<!--In this part we get the SerialNumber -->
						<tsa:td cssClass="<%=classtd%>" align="left" width="15%" valign="top">
							<%=asset.getSerialNumber()%>
						</tsa:td>
						<!--In this part we have a function to generate a tag according with the tagNumber attribute -->
						<tsa:td cssClass="<%=classtd%>" align="center" width="10%" valign="top">
							<% if (!HiltonUtility.isNA(asset.getTrackingNumber())) {%>
								<tsa:input type="checkbox" name="Asset_generateTag<%=i%>" disabled="disabled" onclick="javascript: if (document.purchaseform.Asset_generateTag<%=i%>.checked) {document.purchaseform.Asset_generateTag<%=asset.getTagNumber()%>.value=1} else {document.purchaseform.Asset_generateTag<%=asset.getTagNumber()%>.value=0}" />
							<%}else { %>
								<tsa:input type="checkbox" name="Asset_generateTag<%=i%>" onclick="javascript: if (document.purchaseform.Asset_generateTag<%=i%>.checked) {document.purchaseform.Asset_generateTag<%=asset.getTagNumber()%>.value=1} else {document.purchaseform.Asset_generateTag<%=asset.getTagNumber()%>.value=0}" />
							<%} %>							
							<tsa:hidden name="Asset_generateTag<%=asset.getTagNumber()%>" value="0"/>
						</tsa:td>
						<!--In this part we have a function to cancel a tag according with the tagNumber attribute -->
						<tsa:td cssClass="<%=classtd%>" align="center" width="10%" valign="top">
						<% if (asset.getAssetStatus().equalsIgnoreCase("Cancelled")) {%>
							<tsa:input type="checkbox" name="Asset_cancelTag<%=i%>" disabled="disabled" onclick="javascript: if (document.purchaseform.Asset_cancelTag<%=i%>.checked) {document.purchaseform.Asset_cancelTag<%=asset.getTagNumber()%>.value=1} else {document.purchaseform.Asset_cancelTag<%=asset.getTagNumber()%>.value=0}" />
						<%}else {%>
							<tsa:input type="checkbox" name="Asset_cancelTag<%=i%>" onclick="javascript: if (document.purchaseform.Asset_cancelTag<%=i%>.checked) {document.purchaseform.Asset_cancelTag<%=asset.getTagNumber()%>.value=1} else {document.purchaseform.Asset_cancelTag<%=asset.getTagNumber()%>.value=0}" />
						<%} %>							
							<tsa:hidden name="Asset_cancelTag<%=asset.getTagNumber()%>" value="0"/>
						</tsa:td>
						<!--In this part we have a function to add a new tag according with the tagNumber attribute -->
						<tsa:td cssClass="<%=classtd%>" align="center" width="10%" valign="top">
							<tsa:input type="checkbox" name="Asset_addNewTag<%=i%>" onclick="javascript: if (document.purchaseform.Asset_addNewTag<%=i%>.checked) {document.purchaseform.Asset_addNewTag<%=asset.getTagNumber()%>.value=1} else {document.purchaseform.Asset_addNewTag<%=asset.getTagNumber()%>.value=0}" />
							<tsa:hidden name="Asset_addNewTag<%=asset.getTagNumber()%>" value="0"/>
						</tsa:td>
					</tsa:tr>
					<tsa:tr>
						<tsa:td colspan="7">
							<div  id="details<%=i%>" style="visibility: hidden; display: none;" class="<%=classtd%>">
								<table id="detailRows" border="0" cellspacing="0" cellpadding="0" class="<%=classtd%>" width="95%" align="right">
								<tsa:tr cssClass="<%=classtd%>">
									<tsa:td cssClass="<%=classtd%>">
										<!--In this part we show information and details-->
										<table border="0" cellspacing="0" cellpadding="0" class="<%=classtd%>" width="100%">
										<tsa:tr>
											<tsa:td height="18px" cssClass="<%=classtd%>" width="10%" valign="top">
												<b><tsa:label labelName="ass-description" defaultString="Description"></tsa:label>: </b><%=asset.getDescription()%>
											</tsa:td>
										</tsa:tr>
										</table>
									</tsa:td>
								</tsa:tr>
								</table>
							</div>
						</tsa:td>
					</tsa:tr>
					</table>
					</tsa:td>
				</tsa:tr>
			</table>
		<%
			}
		%>
			</div>
			</tsa:td>
			<tsa:td width="5px">&nbsp;</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="3" height="5px"></tsa:td></tsa:tr>
		</table>
		<!--In this part we have two function the first button validate the correct fields and the second return to the last page-->
		<table align="center" border="0" cellspacing="0" cellpadding="2" width="665px">
			<tsa:tr><tsa:td align="center" valign="bottom"><a href="javascript: changesTag(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border="0" alt="Create Assets"></a></tsa:td>
			<tsa:td align="center" valign="bottom"><a href="javascript: returnPoLine(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Create Assets"></a></tsa:td></tsa:tr>
		</table>
		</tsa:td></tsa:tr>
	</table>
      </tsa:td>
	</tsa:tr>
	</table>

<br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/asset/asset_totals.jsp', 'DoNothing', 'Asset Information');

	<!--In this part we show information and details-->
	function showDetails(row) {
		displayArea("details" + row);
	}

	function hideDetails(row) {
		hideArea("details" + row);
	}

	<!--In this part we go to other page that contain the general information of asset -->
	function viewassetdata(tagNumber,itemNumber,description) {
		var newInputField = "<input type='hidden' name='Asset_tagNumber' value='"+tagNumber+"'>"+"<input type='hidden' name='Asset_itemNumber' value='"+itemNumber+"'>"+"<input type='hidden' name='Asset_description' value='"+description+"'>";
		setHiddenFields(newInputField);
		doSubmit('/asset/asset_general.jsp','AssetRetrieveById');
	}

	<!--In this part according with the icPoHeader return an object of PoLine Entity-->
 	function returnPoLine() {
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='<%=PoHeader_icPoHeader%>'>";

		<% if (action.equalsIgnoreCase("po-items")) {
			functionret="PoLineRetrieveByHeader"; %>
			newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='<%=PoLine_icPoHeader%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_status' value='<%=PoHeader_status%>'>";
		<% } else if (action.equalsIgnoreCase("po-item")) {
			functionret="PoLineRetrieve"; %>
			newInputField = newInputField + "<input type='hidden' name='PoLine_icPoLine' value='<%=PoLine_icPoLine%>'>";
			newInputField = newInputField + "<input type='hidden' name='frompage' value='<%=frompage%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_releaseNumber' value='<%=PoHeader_releaseNumber%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_revisionNumber' value='<%=PoHeader_revisionNumber%>'>";
			newInputField = newInputField + "<input type='hidden' name='lineCount' value='<%=lineCount%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_poType' value='<%=PoHeader_poType%>'>";

		<% } else if (action.equalsIgnoreCase("po-review") || action.equalsIgnoreCase("forward")) {
			functionret="PoRetrieve";
		   } %>
		setHiddenFields(newInputField);
		doSubmit('<%=urlret%>.jsp','<%=functionret%>');
	}

	function changesTag() {
		var newInputField = "<input type='hidden' name='action' value='<%=action%>'>";
			newInputField = newInputField + "<input type='hidden' name='process' value='<%=process%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='<%=PoHeader_icPoHeader%>'>";

		<% if (action.indexOf("po")==0) { %>
			newInputField = newInputField + "<input type='hidden' name='Asset_icLineKey' value='<%=Asset_icLineKey%>'>";

		<% }
		   if (action.equalsIgnoreCase("po-item")) { %>
			newInputField = newInputField + "<input type='hidden' name='PoLine_icPoLine' value='<%=PoLine_icPoLine%>'>";
			newInputField = newInputField + "<input type='hidden' name='frompage' value='<%=frompage%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_releaseNumber' value='<%=PoHeader_releaseNumber%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_revisionNumber' value='<%=PoHeader_revisionNumber%>'>";
			newInputField = newInputField + "<input type='hidden' name='lineCount' value='<%=lineCount%>'>";
			newInputField = newInputField + "<input type='hidden' name='PoHeader_poType' value='<%=PoHeader_poType%>'>";

		<% } else if (action.equalsIgnoreCase("forward") || action.equalsIgnoreCase("po-items"))  { %>
			newInputField = newInputField + "<input type='hidden' name='Asset_purchaseOrder' value='<%=Asset_purchaseOrder%>'>";
			 <% if (action.equalsIgnoreCase("po-items")) { %>
				newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='<%=PoLine_icPoHeader%>'>";
				newInputField = newInputField + "<input type='hidden' name='PoHeader_status' value='<%=PoHeader_status%>'>";
			 <% }
		   } %>
		setHiddenFields(newInputField);
		doSubmit('/asset/asset_po_browse.jsp','AssetChangesTags;<%=process%>');
		//doSubmit('/asset/asset_po_browse.jsp','AssetGenerateTag;AssetCancelTag;AssetAddNewTag;<%=process%>');
	}

//-->
</script>