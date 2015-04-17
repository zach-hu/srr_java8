<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@ page import="com.tsa.puridiom.usermanager.*" %>


<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/date_check.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	errorMsg = (String) request.getAttribute("errorMsg");
	CatalogItem catItem = (CatalogItem) request.getAttribute("catalogItem");
	String newItemString = (String) request.getAttribute("newItem");
	List umCodeList = (List) request.getAttribute("validUmCodeList");
	String fromPage = HiltonUtility.ckNull((String) request.getAttribute("OriginalFromPage"));
	boolean newItem = false;

	if (HiltonUtility.isEmpty(errorMsg)|| errorMsg == null || errorMsg.equalsIgnoreCase("null")){
		errorMsg = null;
    }

	if (catItem == null)
	{
		Catalog catalog = (Catalog) request.getAttribute("catalog");
		catItem = new CatalogItem();
		CatalogItemPK comp_id = new CatalogItemPK();
		comp_id.setCatalogId((String) request.getAttribute("Catalog_catalogId"));
		catItem.setComp_id(comp_id);
		catItem.setDateEntered(d_today);
		catItem.setDateExpires(d_today);
		catItem.setOwner(uid);
		catItem.setStatus("02");
		if (catalog != null) {
			catItem.setReceiptRequired(catalog.getReceiptRequired());
		}
		newItem = true;
	}
	if (!HiltonUtility.isEmpty(newItemString) && newItemString.equals("true")) {
		newItem = true;
	}
	if (!HiltonUtility.isEmpty(errorMsg)&& errorMsg!=null && !errorMsg.equalsIgnoreCase("null")){
		newItem = true;
    }
	CatalogItemPK catItemPK = (CatalogItemPK) catItem.getComp_id();
	UserProfile owner = UserManager.getInstance().getUser(oid, catItem.getOwner());

	boolean imageBool =(!HiltonUtility.isEmpty(catItem.getImageFile()));
	boolean uploadImageAccessBool = false;
	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);
	String uploadImagesAccess = propertiesManager.getProperty("UPLOADIMAGES","ENABLED","N");
	String uploadImageRole = "";

	if (roleR.getAccessRights("UPLOADIMAGES") < 1) {
		uploadImageRole = "disabled";
	}
	else {
		uploadImageRole = Integer.toString(roleR.getAccessRights("UPLOADIMAGES"));
	}

	if (uploadImagesAccess.equalsIgnoreCase("Y") && uploadImageRole != "disabled"){
		uploadImageAccessBool = true;
	}

	if (newItem) {
		catItem.setTaxable("Y");
	}

	String	s_use_subcommodity = propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N");
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="CatalogItem_id_catalogId" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="CatalogItem_id_itemNumber" value="<%=catItemPK.getItemNumber()%>"/>
<tsa:hidden name="CatalogPriceBrk_catalogId" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="CatalogPriceBrk_itemNumber" value="<%=catItemPK.getItemNumber()%>"/>
<tsa:hidden name="KitItem_parentCatalogId" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="KitItem_parentItemNumber" value="<%=catItemPK.getItemNumber()%>"/>
<tsa:hidden name="CatalogItem_catalogId" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="Catalog_catalogId" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="CatalogItem_imageFile"value="<%=catItem.getImageFile()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=catItem.getIcAccount()%>"/>
<tsa:hidden name="CatalogItem_unitMeasure" value="<%=catItem.getUmCode()%>"/>
<tsa:hidden name="AltText_source" value="CAT"/>
<tsa:hidden name="AltText_id" value="<%=catItemPK.getCatalogId()%>"/>
<tsa:hidden name="AltText_itemNumber" value="<%=catItemPK.getItemNumber()%>"/>
<tsa:hidden name="currentPage" value="/admin/catalog/catalog_item.jsp"/>
<!-- tsa:hidden name="fromPage" value="<%=fromPage%>"/-->
<tsa:hidden name="fromSave" value=""/>
<tsa:hidden name="Labels_moduleAccess" value="CATALOG ITEMS"/>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Catalog Item")%> <%=catItemPK.getItemNumber()%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1>
		<tr>
			<td align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalog", "Catalog")%>:</b>&nbsp;<%=catItemPK.getCatalogId()%></td>
			<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: doSubmit('/admin/catalog/cat_item_accounts.jsp', 'AccountRetrieveByLine'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accounts", "Accounts")%></a></b></td>
			<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: doSubmit('/admin/catalog/cat_item_pricebreaks.jsp', 'CatalogPriceBrkRetrieveByItem'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "priceBreaks", "Price Breaks")%></a></b></td>
			<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: doSubmit('/admin/catalog/cat_item_kits.jsp', 'KitItemRetrieveByParent'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "kitItems", "Kit Items")%></a></b></td>
			<% if (propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "CREATECOMMENTS", "N").equalsIgnoreCase("Y")) { %>
			<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: doSubmit('/admin/catalog/cat_item_notes.jsp', 'CatalogItemUpdateForComments;DocCommentRetrieveByLine'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "notes", "Notes")%></a></b></td>
			<% } %>
			<td align=right nowrap width=100px><a href="javascript: validateCatalogItem(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			<td align=right nowrap><a href="javascript: validateCatalogItem(); void(0);">Validate Information</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign="top" align="center">
		<table border=0 cellspacing=0 cellpadding=1 width=140px>
<%	if (imageBool && (!(catItem.getImageFile().substring(0,5)).equals("http:"))) {%>
		<tr>
			<td align="center">
				<br><img src="<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>/<%=catItemPK.getCatalogId()%>/<%=catItem.getImageFile()%>" alt="Item Image" border="1" width="100px" height="100px">
			</td>
		</tr>
<%	} %>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=1 width=150px>
<%	if (newItem && uploadImageAccessBool) {%>
				<tr height="25px">
					<td align="right" valign="top" width=80%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImageNew(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Upload Image")%>:</a>&nbsp;</div>
					</td>
					<td align="center" valign="top" width=20%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImageNew(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Upload Item Image" border=0></a></div>
					</td>
				</tr>
<%	}
		if (!newItem && imageBool && uploadImageAccessBool) {%>
				<tr height="25px">
					<td align="right" valign="top" width=80%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImage(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uploadImage", "Upload Image")%>:</a>&nbsp;</div>
					</td>
					<td align="center" valign="top" width=20%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImage(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Upload Item Image" border=0></a></div>
					</td>
                </tr>
<%	}
		if (!newItem &&!imageBool && uploadImageAccessBool) {%>
                <tr height="25px">
					<td align="right" valign="top" width=80%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImage(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uploadImage", "Upload Image")%>:</a>&nbsp;</div>
					</td>
					<td align="center" valign="top" width=20%>
						<div id="browse_CatalogItem_uploadCatalogImage"><a href="javascript: uploadItemImage(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Upload Item Image" border=0></a></div>
					</td>
                </tr>
<%	}
		if (imageBool && uploadImageAccessBool) {%>
				<tr height="25px">
					<td align="right" valign="top" width=80%>
						<div id="browse_CatalogItem_deleteCatalogImage"><a href="javascript: deleteItemImage(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteImage", "Delete Image")%>:</a>&nbsp;</div>
					</td>
					<td align="center" valign="top" width=20%>
						<div id="browse_CatalogItem_deleteCatalogImage"><a href="javascript: deleteItemImage(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete Item Image" border=0></a></div>
					</td>
				</tr>
<%	}
		if (!newItem && imageBool) {%>
				<tr height="25px">
					<td align="right" valign="top"width=80%>
						<div id="browse_CatalogItem_viewCatalogImage"><a href="javascript: viewImage('CAT'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enlargeImage", "Enlarge Image")%>:</a>&nbsp;</div>
					</td>
					<td align="center" valign="top"width=20%>
						<div id="browse_CatalogItem_viewCatalogImage"><a href="javascript: viewImage('CAT'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" alt="View Item Image" border=0></a></div>
					</td>
				</tr>
<%	}%>
				</table>
			</td>
		</tr>
		</table>
	</td>

	<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=550px>
		<tr>
			<td align="center">
				<table border=0 cellspacing=0 cellpadding=1>
				<tr>
					<td nowrap align=RIGHT><tsa:label labelName="cat-itemNumber" defaultString="Item Number" checkRequired="true"/>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="CatalogItem_itemNumber" size=25 maxlength=30 tabindex=2 value="<%=catItemPK.getItemNumber()%>" <%	if (! newItem) { %> disabled<% } %> onChange="alphaNumericCheck(this);"></td>
					<%if (!HiltonUtility.isEmpty(errorMsg)) {%>
					<td width=50% align=rigth class=error><%=errorMsg%></td>
					<%}%>
					<% if(oid.equalsIgnoreCase("WPC08P") || oid.equalsIgnoreCase("SRR10P")) { %>
					<td nowrap align=right>
						<a href="javascript: viewCatalogSecurity(); void(0);" >
						&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewCatalogSecurity", "View Catalog Security")%></a>
					</td>
					<% } else {%>
					<td nowrap align=right>&nbsp</td>
					<% } %>
					<td nowrap align=right>
                    <% if (roleR.getAccessRights("CATITEM") >= 3 ) { %>
	                    <% if (!newItem) { %>
							<a href="javascript: if (verifyAction('Delete this catalog item?')) { doSubmit('/browse/browse.jsp', 'CatalogItemDelete;BrowseRetrieve'); }" >
							<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Item</a>
	                     <%	} else {%>
							<a href="javascript: getCatalogItemInfo();" >Get Catalog Item Info</a>
		                 <%	} %>
                    <% } %>
					</td>
				</tr>
				<tr>
					<td nowrap align=RIGHT valign=top><tsa:label labelName="cat-description" defaultString="Item Number" checkRequired="true"/>:&nbsp</td>
				  <td colspan=3><TEXTAREA NAME="CatalogItem_description" COLS=61 ROWS=5 tabindex=4 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);">${esapi:encodeForHTML(catalogItem.description)}</TEXTAREA></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan=3 <%=HtmlWriter.isVisible(oid, "cat-itemdescription-alttext")%>><a href="javascript: viewAltLanguages(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-itemdescription-alttext", "Alternate Language Descriptions", true)%></a>&nbsp;</td>
				</tr>
				<tr><td colspan=4>&nbsp;</td></tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "unitPrice", "Unit Price")%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="CatalogItem_cost" size=15 tabindex=6 value="<%=HiltonUtility.getFormattedDollar(catItem.getCost(), oid)%>" onchange="this.value=nformat(eval(nfilter(this)), <%=s_dollar_decimals%>);"></td>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "manufacturer", "Manufacturer")%>:&nbsp;</td>
				  <td><INPUT TYPE="TEXT" NAME="CatalogItem_mfgName" size=15 maxlength=25 tabindex=20 value="<%=catItem.getMfgName()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right><a href="javascript: browseLookup('CatalogItem_umCode', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-umCode", "U/M")%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="CatalogItem_umCode" size=15 maxlength=15 tabindex=8 value="<%=catItem.getUmCode()%>" onchange="upperCase(this);"></td>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "modelNumber", "Model Number")%>:&nbsp;</td>
				  <td><INPUT TYPE="TEXT" NAME="CatalogItem_modelNumber" size=15 maxlength=20 tabindex=22 value="<%=catItem.getModelNumber()%>" ></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cat-eoq")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-eoq", "EOQ", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-eoq")%> ><INPUT TYPE="TEXT" NAME="CatalogItem_stockEoq" SIZE="15" tabindex=10 value="<%=HiltonUtility.getFormattedQuantity(catItem.getStockEoq(), oid)%>" ></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN01")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf1Code', 'LN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN01", "Udf 1")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN01")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf1Code" SIZE="15" MAXLENGTH="15" tabindex=24 value="<%=catItem.getUdf1Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cat-umConv")%> nowrap align=right><a href="javascript: browseLookup('CatalogItem_umConv', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-umConv", "Conversion U/M")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-umConv")%>><INPUT TYPE="TEXT" NAME="CatalogItem_umConv" SIZE="15" MAXLENGTH="15" tabindex=12 value="<%=catItem.getUmConv()%>" onchange="upperCase(this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN02")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf2Code', 'LN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN02", "Udf 2", true)%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN02")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf2Code" SIZE="15" MAXLENGTH="15" tabindex=26 value="<%=catItem.getUdf2Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cat-umFactor")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-umFactor", "Conv. U/M Factor")%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-umFactor")%> ><INPUT TYPE="TEXT" NAME="CatalogItem_umFactor" SIZE="15" tabindex=14 value="<%=catItem.getUmFactor()%>" ></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN03")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf3Code', 'LN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN03", "Udf 3")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN03")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf3Code" SIZE="15" MAXLENGTH="15" tabindex=28 value="<%=catItem.getUdf3Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right <%= HtmlWriter.isVisible(oid, "cat-commodity") %>><A HREF="javascript: <% if (s_use_subcommodity.equalsIgnoreCase("Y")) {%>browseCommodity('CatalogItem_commodity','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%>browseCommodityByType(); <%}%>void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-commodity", "Commodity", true)%>:</a>&nbsp;</td>
					<td <%= HtmlWriter.isVisible(oid, "cat-commodity") %>><INPUT TYPE="TEXT" NAME="CatalogItem_commodity" size=25 maxlength=15 tabindex=16 value="<%=catItem.getCommodity()%>" onchange="upperCase(this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN04")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf4Code', 'LN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN04", "Udf 4")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN04")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf4Code" SIZE="15" MAXLENGTH="15" tabindex=30 value="<%=catItem.getUdf4Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right <%= HtmlWriter.isVisible(oid, "cat-receiptOption") %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptOption", "Receipt Option")%>:&nbsp;</td>
					<td <%= HtmlWriter.isVisible(oid, "cat-receiptOption") %>>
						<select name="CatalogItem_receiptRequired" tabindex=18>
							<%	if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
								<option <% if (catItem.getReceiptRequired().equals("R")) { %> selected <% } %> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptRequired", "Receipt Required")%></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
								<option <% if (catItem.getReceiptRequired().equals("P")) { %> selected <% } %> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyReceived", "Previously Received")%></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
									<%	if (!oid.equals("MSG07P")) { %>
										<option <% if (catItem.getReceiptRequired().equals("N")) { %> selected <% } %> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noReceiptRequired", "No Receipt Required")%></option>
									<%	} %>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
									<option <% if (catItem.getReceiptRequired().equals("E")) { %> selected <% } %> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "endUserReceipt", "End User Receipt")%></option>
							<%	} %>
						</select>
					</td>
				<%if (!oid.equalsIgnoreCase("vse06p") && !oid.equalsIgnoreCase("bly07p")) { %>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN05")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf5Code', 'LN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN05", "Udf 5")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN05")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf5Code" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getUdf5Code()%>" onchange="upperCase(this);"></td>
				<% } %>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cat-minReqQty")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-minReqQty", "Min Request Qty")%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-minReqQty")%>><INPUT TYPE="TEXT" NAME="CatalogItem_minReqQty" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getMinReqQty()%>" onchange="this.value=nformat(eval(nfilter(this)), 0);"></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN06")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf6Code', 'LN06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN06", "Udf 6")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN06")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf6Code" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getUdf6Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cat-maxReqQty")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-maxReqQty", "Max Request Qty")%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-maxReqQty")%>><INPUT TYPE="TEXT" NAME="CatalogItem_maxReqQty" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getMaxReqQty()%>" onchange="this.value=nformat(eval(nfilter(this)), 0);"></td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN07")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf7Code', 'LN07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN07", "Udf 7")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN07")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf7Code" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getUdf7Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right>&nbsp;</td>
					<td>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN08")%> nowrap align=right><tsa:label labelName="cat-LN08" defaultString="Udf 8" checkRequired="true" />:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN08")%>><tsa:input type="dropdown" name="CatalogItem_udf8Code"  size="15" value="<%=catItem.getUdf8Code()%>" labelName="cat-LN08" onchange="upperCase(this);"/></td>
				</tr>
				<tr>
					<td nowrap align=right>&nbsp;</td>
					<td>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN09")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf9Code', 'LN09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN09", "Udf 9")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN09")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf9Code" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getUdf9Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right>&nbsp;</td>
					<td>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN10")%> nowrap align=right><a href="javascript: browseStd('CatalogItem_udf10Code', 'LN10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN10", "Udf 10")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "cat-LN10")%>><INPUT TYPE="TEXT" NAME="CatalogItem_udf10Code" SIZE="15" MAXLENGTH="15" tabindex=32 value="<%=catItem.getUdf10Code()%>" onchange="upperCase(this);"></td>
				</tr>
			  </table>
			</td>
		</tr>
		</table>

		<br>

		<table border=0 cellspacing=0 cellpadding=0>
		<tr>
			<tsa:td field="cat-shelfLifeRqd"noWrap="nowrap" align="right" width="100px"><tsa:label labelName="cat-shelfLifeRqd" defaultString="Shelf Life Required" checkRequired="false"/></tsa:td>
			<tsa:td field="cat-shelfLifeRqd">
				<input type="checkbox" name="c_checkbox" value="" <%if (catItem.getShelfLifeRqd().equals("Y")) { %>checked<%}%>onclick="setCheckbox(frm.CatalogItem_shelfLifeRqd, 0)">
				<tsa:hidden name="CatalogItem_shelfLifeRqd" value="<%=catItem.getShelfLifeRqd()%>"/>
			</tsa:td>
			<td nowrap align=right width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "asset", "Asset")%>:&nbsp;</td>
			<td>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" tabindex=35 value="" <% if (catItem.getAsset().equals("Y") || newItem) { %>" CHECKED <% } %>onclick="setCheckbox(frm.CatalogItem_asset, 1)">
				<tsa:hidden name="CatalogItem_asset" value="<%=catItem.getAsset()%>"/>
			</td>
			<tsa:td id="taxableLabelRow" field="taxable" align="right" width="100px" noWrap="nowrap">
			<tsa:label labelName="taxable" defaultString="Taxable" noinstance="yes"/>:
			</tsa:td>
			<tsa:td id="taxableFieldRow" field="taxable" noWrap="nowrap">
			<%String taxable = "";
			if (catItem.getTaxable().equals("Y") && !newItem) {
				taxable = "Y";
			}else{
				taxable = "N";
			}%>
			<tsa:input type="checkbox" title="Taxable" name="c_checkbox" tabIndex="39" value="Y" checked="<%= taxable %>" onclick="setCheckbox(frm.CatalogItem_taxable, 2)"/>
			<tsa:hidden name="CatalogItem_taxable" value="<%= catItem.getTaxable() %>"/>
			</tsa:td>
			<td nowrap align=right width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "kit", "Kit")%>:&nbsp;</td>
			<td>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" tabindex=38 value="" <% if (catItem.getKit().equals("Y")) { %>" CHECKED <% } %> onclick="setCheckbox(frm.CatalogItem_kit, 3)">
				<tsa:hidden name="CatalogItem_kit" value="<%=catItem.getKit()%>"/>
			</td>
			<td nowrap align=right width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "restrictedItem", "Restricted Item")%>:&nbsp;</td>
			<td>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" tabindex=40 value="" <% if (catItem.getItemRestricted().equals("Y")) { %>" CHECKED <% } %>  onclick="setCheckbox(frm.CatalogItem_itemRestricted, 4)">
				<tsa:hidden name="CatalogItem_itemRestricted" value="<%=catItem.getItemRestricted()%>"/>
			</td>
		</tr>
		</table>

		<br>

		<hr width=475px align=center class=browseHR>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="CatalogItem_status" onchange="setStatus();" tabindex=44>
							<option value="01" <% if (catItem.getStatus().equals("01")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
							<option value="02" <% if (catItem.getStatus().equals("02")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
							<option value="03" <% if (catItem.getStatus().equals("03")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateEntered", "Date Entered")%>:&nbsp;</td>
					<td width="130px" nowrap>
						<input type=text name="CatalogItem_dateEntered" size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(catItem.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td><div id="dateExpiresLabel"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExpires", "Date Expires")%>:&nbsp;</div></td>
					<td>
						<div id="dateExpiresField">
							<input type=text name="CatalogItem_dateExpires" size=15 maxlength=15 tabindex=46 value="<%=HiltonUtility.getFormattedDate(catItem.getDateExpires(), oid, userDateFormat, userDateFormat)%>" onchange="checkDate(this);">
							<a href="javascript: show_calendar('CatalogItem_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
						</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
				<td <%=HtmlWriter.isVisible(oid, "securityType")%> width="100px">
					<select tabindex="13" name="CatalogSecurity_accessType" id="CatalogSecurity_accessType" size="1" onchange="browseAccess();">
						<option value="UI" SELECTED><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user", "User")%></option>
						<option value="PI"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userPool", "Pool")%></option>
					</select>
				</td>
				<td align=right><a href="javascript: browseLookup('CatalogItem_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="CatalogItem_owner" size=30 maxlength=15 tabindex=48 value="<%=catItem.getOwner()%>" onchange="upperCase(this);getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td colspan="2" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-ownerName", "Owner Name")%>:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan="2"><br><br></td></tr>
		<tr>
			<td width=50% align=center>
				<div id="pxbutton">
		<% if (roleR.getAccessRights("CATITEM") >= 3 ) { %>
			<%	if (! newItem) { %>
					<a href="javascript: submitThis('CatalogItemUpdate'); void(0);">
			<%	} else { %>
					<a href="javascript: submitThis('CatalogItemAdd'); void(0);">
			<%	} %>
				Save</a>
				</div>
		<% } %>
			</td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: browseFilter('catalogitem-admin'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setOriginalFilter("CatalogItem_id_catalogId", "=", "<%=catItemPK.getCatalogId()%>");
	frm.browseName.value = "catalogitem-admin";
	var itemNumber = frm.CatalogItem_id_itemNumber.value;
	var status = "<%=catItem.getStatus()%>";
	setStatus();
	var currentRow = 0;

	function thisLoad()
	{
		f_StartIt() ;
		<%	if ( roleR.getAccessRights("CATITEM") <= 1 ) { %>
		checkInputSettings();
		<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.CatalogItem_status[frm.CatalogItem_status.selectedIndex].value;
		if ((status == "01"))
		{
			displayArea("dateExpiresLabel");
			displayArea("dateExpiresField");
		}
		else
		{
			hideArea("dateExpiresLabel");
			hideArea("dateExpiresField");
		}
	}

	function uploadItemImageNew(){

	 var uploadImageRole = <%=uploadImageRole%>
     //alert(uploadImageRole);

    	if (uploadImageRole== "1"){
 			//alert("You Only Can See This Option");
 		}
    	if (uploadImageRole!= "1"){
	  		doSubmit('/admin/catalog/catalog_item_image_new.jsp', 'CatalogItemAdd');
		}
	}

	function uploadItemImage(){

	 var uploadImageRole = <%=uploadImageRole%>
     //alert(uploadImageRole);

    	if (uploadImageRole== "1"){
 			//alert("You Only Can See This Option");
 		}
    	if (uploadImageRole!= "1"){
	    	doSubmit('/admin/catalog/catalog_item_image_new.jsp', 'DoNothing');
		}
	}

    function deleteItemImage(){
		frm.CatalogItem_imageFile.value = '';

		var uploadImageRole = <%=uploadImageRole%>
       //alert(uploadImageRole);

		if (uploadImageRole== "1"){
 			//alert("You Only Can See This Option");
 		}
    	if (uploadImageRole!= "1"){
		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemUpdate;CatalogItemRetrieveById');
		}

	}

	function viewImage(type) {
		var imgFileName = "";
		var imageFile = frm.CatalogItem_imageFile.value;
		var catalogName = frm.CatalogItem_id_catalogId.value;
		var directoryName = frm.CatalogItem_id_catalogId.value;
		var imageUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "/")%>";

		if (imageUrl.substring(imageUrl.length - 1) != "/") {
			imageUrl = imageUrl + "/";
		}

		imageUrl = imageUrl + directoryName + "/";
		imgFileName = imageFile;

		if (imgFileName.indexOf("http://") >= 0)
		{
			if (catalogName == "BOISE" || catalogName == "BOISE-WEB" || catalogName == "RESTRICTED") {
				var udf2 = frm.CatalogItem_udf2code.value;
				var imgFileName = "http://www.officemaxsolutions.com/cif/viewItem.html?nid=" + udf2;
			}
			doSubmitToPopup(imgFileName, 'DoNothing');
		}
		else
		{
			doSubmitToPopup(imageUrl + imgFileName, 'DoNothing');
		}
	}

	function validateForm() {

		if(frm.handler.value.indexOf("CatalogItemAdd") >= 0)
        {
			var alertMessage = "";

			if ( isEmpty(frm.CatalogItem_itemNumber.value)) {
				alert("Please enter a valid Item Number.");
				return false;
			}

        }
		return true;
	}

	function browseCommodityByType()
	{
		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		popupParameters = "browseName=commodity";
		popupParameters = popupParameters + ";formField=CatalogItem_commodity;allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=BO;logicalOperator=OR;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=Commodity_commodityType;operator==;filter_txt=SP;logicalOperator=AND;originalFilter=Y;sort=N";
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		<% } else { %>
		browseCommodity('CatalogItem_commodity', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
		<% } %>
	}

	function getCatalogItemInfo() {
		frm.CatalogItem_itemNumber.value = trim(frm.CatalogItem_itemNumber);
		if (!isEmpty(frm.CatalogItem_itemNumber.value)) {
			doSubmit('admin/catalog/catalog_item.jsp', 'CatalogItemCopy;CatalogItemRetrieveById');
		}
	}

	function alphaNumericCheck(x)
	{
		x.value=x.value.toUpperCase().replace(/([^0-9A-Z- () / _.*])/g,"").trim();
	}

	function viewCatalogSecurity(){
		var newInputField = "<input type='hidden' name='CatalogSecurity_catalogId' value='" + "<%=catItemPK.getCatalogId()  %>" + "'>" +
      					 	"<input type='hidden' name='CatalogSecurity_itemNumber' value='" + "<%=catItemPK.getItemNumber()%>" + "'>" +
      					 	"<input type='hidden' name='fromPage' value='catalogitemjsp'>";
     setHiddenFields(newInputField);
		doSubmit('/admin/catalog/catalogsecurity.jsp', 'CatalogSecurityRetreieveByCatalogItem');
	}

	function viewAltLanguages() {
		doSubmit('/admin/catalog/cat_item_altlanguages.jsp', 'AltTextRetrieveByItem');
	}

	function saveCatalogItem(handler) {
	    if(frm.handler.value.indexOf("CatalogItemAdd") >= 0 || frm.handler.value.indexOf("CatalogItemUpdate"))
        {
	        var umCodeValid = false;
  	        var umConvValid = false;
			<% if(umCodeList!=null && umCodeList.size() > 0)
				for (int i=0; i<umCodeList.size(); i++) { %>
				if (frm.CatalogItem_umCode.value == "<%=umCodeList.get(i).toString()%>")
				{
					umCodeValid = true;

				}
				if (frm.CatalogItem_umConv.value == "<%=umCodeList.get(i).toString()%>")
				{
					umConvValid = true;
				}
			<% } %>

			if ( umCodeValid==false && orgId.toLowerCase()=='bly07p' ) {
				alert("Please enter a valid UOM.");
				return false;
			}
			if ( umConvValid==false && orgId.toLowerCase()=='bly07p' ) {
				alert("Please enter a valid Vendor UOM.");
				return false;
			}
        }
		doSubmit('/browse/browse.jsp', handler +';BrowseRetrieve');
	}

	function submitThis(handler)
	{
		frm.fromSave.value = "Y";
		doSubmit('/admin/catalog/catalog_item_validation.jsp', handler + ';CatalogItemValidate');
	}

	function validateCatalogItem()
	{
		var handler = "";
		<%	if (!newItem) { %>
		handler = "CatalogItemUpdate";
		<%	} else { %>
		handler = "CatalogItemAdd";
		<%	} %>
		doSubmit('/admin/catalog/catalog_item_validation.jsp', handler + ';CatalogItemValidate');
	}


	function browseAccess()
	{
		var type=frm.CatalogSecurity_accessType.value;
		if (type == "UI")
		{
			browseLookup('CatalogItem_owner', 'user'); getNewInfo('user', this); void(0);
		}
		else if (type == "PI")
		{
			browseLookup('CatalogItem_owner', 'userPool'); getNewInfo('user', this); void(0);
		}
	}

// End Hide script -->
</SCRIPT>