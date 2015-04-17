<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/date_check.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_now = Dates.today(userDateFormat, userTimeZone);
	Catalog catalog = (Catalog) request.getAttribute("catalog");
	Long itemCount = (Long) request.getAttribute("catalogItemCount");
	String fromPage = HiltonUtility.ckNull((String) request.getAttribute("OriginalFromPage"));
	boolean newCatalog = false;
	boolean hasItems = false;

	String	s_lastupdateddate = "";
	String	s_lastupdateby = "";

	if (catalog == null)
	{
		catalog = new Catalog();
		catalog.setDateEntered(d_today);
		catalog.setDateExpires(d_today);
		catalog.setOwner(uid);
		catalog.setStatus("02");
		newCatalog = true;
		s_lastupdateddate = HiltonUtility.getFormattedDate(new Date(), oid, userDateFormat);
		s_lastupdateby = HiltonUtility.ckNull(uid);
	}
	else
	{
		s_lastupdateddate = HiltonUtility.getFormattedDate(catalog.getLastUpdatedDate(), oid, userDateFormat);
		s_lastupdateby = HiltonUtility.ckNull(catalog.getLastUpdatedBy());
	}

	if (newCatalog){
		catalog.setOrdersOnly("Y");
	}

	BigDecimal punchoutId	= catalog.getIcPunchout();
	String	catalogId		= catalog.getCatalogId();

	UserProfile owner = UserManager.getInstance().getUser(oid, catalog.getOwner());
	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);

	if (itemCount != null && itemCount.intValue() > 0)
	{
		hasItems = true;
	}
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Catalog_source" value="<%=catalog.getSource()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=catalog.getIcAccount()%>"/>
<tsa:hidden name="Catalog_icAccount" value="<%=catalog.getIcAccount()%>"/>
<tsa:hidden name="fromPage" value="<%=fromPage%>"/>
<tsa:hidden name="fromSave" value=""/>
<tsa:hidden name="Labels_moduleAccess" value="CATALOGS"/>
<tsa:hidden name="catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="punchoutId" value="<%=punchoutId%>"/>


<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogInformation", "Catalog Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
<%	if (!newCatalog) { %>
		<tr>
			<td align=right>
				<table border=0 cellspacing=0 cellpadding=1 width=135px>
				<tr>
					<td align=right nowrap><b><a href="javascript: doSubmit('/admin/catalog/catalog_accounts.jsp', 'CatalogUpdate;AccountRetrieveByLine'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accounts", "Accounts")%></a></b></td>
					<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: browseItems(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogItems", "Catalog Items")%></a></b></td>
					<td align=right nowrap>&nbsp;|&nbsp;<b><a href="javascript: doSubmit('admin/catalog/punchout.jsp', 'Punchout'); void(0);">Punchout Administration</a></b></td>
					<!--td align=left nowrap><b><a href="javascript: doSubmit('/admin/catalog/catalog_item_import.jsp', 'CatalogUpdate;CatalogRetrieveById;CatalogItemRetrieveByCatalog'); void(0);">Import Items</a> | </b></td-->
					<!--td align=left nowrap><b><a href="javascript: void(0);">Export Items</a></b></td-->
					<!-- Note to whoever adds this link (for the Export Items)... please be sure to call the CatalogUpdate handler first so that any changes made will be saved - THANKS!  -->
				</tr>
				</table>
			</td>
		</tr>
<%	}%>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=<%=formEntryWidth%> align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center>
				<table border=0 cellspacing=0 cellpadding=1>
				<tr>
					<td nowrap align=right><tsa:label labelName="catalogId" defaultString="Catalog ID" checkRequired="true"/>:</td>
					<td>&nbsp;<input type=text name="Catalog_catalogId" SIZE=20 MAXLENGTH="15" value="<%=catalog.getCatalogId()%>" <%	if (!newCatalog) { %>disabled<% } %> onchange="alphaNumericCheck(this);"></td>
					<td nowrap colspan=2 align=right>
<%	if (!newCatalog && (roleR.getAccessRights("CAT") >= 3 )) { %>
						<a href="javascript: deleteCatalog(); void(0);" >
						<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteCatalog", "Delete Catalog")%></a>
<%	} %>
					</td>
				</tr>
				<tr>
					<td nowrap align=right><tsa:label labelName="catalogTitle" defaultString="Catalog Title" checkRequired="true"/>:</td>
					<td colspan=2>&nbsp;<input type=text NAME="Catalog_title" size=60 maxlength=60 value="<%=catalog.getTitle()%>"></td>
					<% if (PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE", "N").equalsIgnoreCase("Y")) { %>
					<td nowrap colspan=2 align=right>
						<a href="javascript: viewCatalogSecurity(); void(0);" >
						&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewCatalogSecurity", "View Catalog Security")%></a>
					</td>
					<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cat-currency")%>>
					<td nowrap align=right><a href="javascript: browseLookup('Catalog_currencyCode', 'curr_code'); void(0);" title="Click here to choose the Currency Type for this catalog or enter the Currency Type in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-currency", "Currency", true)%>:</a>&nbsp;</td>
					<td colspan=3>&nbsp;<input type=text NAME="Catalog_currencyCode" title="Enter type of Currency" size=15 maxlength=15 value="<%=catalog.getCurrencyCode()%>" onchange="upperCase(this);"></td>

				</tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "webcatalog", "Web Catalog")%>:</td>
					<td>
						<input type=checkbox name="c_checkbox" value="" <% if (catalog.getWebCatalog().equals("Y")) {%>CHECKED<%}%> ONCLICK="setCheckbox(frm.Catalog_webCatalog, 0)">
						<input type="hidden" name="Catalog_webCatalog" value="<%=catalog.getWebCatalog()%>"/>
					</td>
				</tr>
				<tr>
					<td nowrap align=right><a href="javascript: browseLookup('Catalog_vendorId', 'vendor'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorID", "Vendor ID")%>:</a></td>
					<td>&nbsp;<input type=text name="Catalog_vendorId" size=20 maxlength=15 value="<%=catalog.getVendorId()%>" onchange="upperCase(this); checkPoNumber(this.value);"></td>
				</tr>
				<tr>
					<td nowrap align=right><a href="javascript: browseOrders(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "blanketNumber", "Blanket Number")%>:</a></td>
					<td>
						&nbsp;<input type=text name="Catalog_poNumber" size=20 maxlength=20 value="<%=catalog.getPoNumber()%>" onchange="upperCase(this);">
						<tsa:hidden name="Catalog_icPoHeader" value="<%=catalog.getIcPoHeader()%>"/>
						<tsa:hidden name="as_vendorId" value="<%=catalog.getVendorId()%>"/>
					</td>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allowReleases", "Allow Releases")%>:</td>
					<td width="100px">
						<input type=checkbox name="c_checkbox" value="" <% if (catalog.getAllowReleases().equals("Y")) {%>CHECKED<%}%> ONCLICK="setCheckbox(frm.Catalog_allowReleases, 1)">
						<tsa:hidden name="Catalog_allowReleases" value="<%=catalog.getAllowReleases()%>"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "consolidateReleases", "Consolidate Releases")%>:</td>
					<td width="100px">
						<select name="Catalog_consolidateReleases">
							<option <% if (catalog.getConsolidateReleases().equals("N")) { %> selected <% } %> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autoreleaseWithoutConsolidation", "Autorelease without consolidation")%></option>
							<option <% if (catalog.getConsolidateReleases().equals("Y")) { %> selected <% } %> value="Y"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "consolidateShipRequisition", "Consolidate by Ship To/Requisition")%></option>
							<option <% if (catalog.getConsolidateReleases().equals("S")) { %> selected <% } %> value="S"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "consolidateRequisitionShip", "Consolidate by Requisition/Ship To")%></option>
						</select>
					</td>
				</tr>
				<tr><td colspan=4><br></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "cat-receiptOptions")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-receiptOptions", "Receipt Option", true)%>:&nbsp;</td>
					<td>
						<select name="Catalog_receiptRequired">
							<% if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
									<option <% if (catalog.getReceiptRequired().equals("R")) { %> selected <% } %> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptRequired", "Receipt Required")%></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
									<option <% if (catalog.getReceiptRequired().equals("P")) { %> selected <% } %> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyReceived", "Previously Received")%></option>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
									<%	if (!oid.equals("MSG07P")) {%>
										<option <% if (catalog.getReceiptRequired().equals("N")) { %> selected <% } %> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noReceiptRequired", "No Receipt Required")%></option>
									<%	}%>
							<%	}
								if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
									<option <% if (catalog.getReceiptRequired().equals("E")) { %> selected <% } %> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "endUserReceipt", "End User Receipt")%></option>
							<%	}%>

						</select>
					</td>
				</tr>
				<tr><td colspan=4><br></td></tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "punchoutCatalog", "Punchout Catalog")%>:</td>
					<td align=left><input type=checkbox name="c_checkbox" value="" <% if (catalog.getExternalCatalog().equals("Y")) {%>CHECKED<%}%> ONCLICK="setCheckbox(frm.Catalog_externalCatalog, 2)">
						<tsa:hidden name="Catalog_externalCatalog" value="<%=catalog.getExternalCatalog()%>"/>
					</td>
					<tsa:td id="forPurchasingUseOnlyLabelRow" field="forPurchasingUseOnly" align="right" noWrap="nowrap">
					<tsa:label labelName="forPurchasingUseOnly" defaultString="For Purchasing Use Only" noinstance="yes"/>
					</tsa:td>
					<tsa:td id="forPurchasingUseOnlyFieldRow" field="forPurchasingUseOnly" noWrap="nowrap">
						<input type="checkbox" title="For Purchasing Use Only" name="c_checkbox" value="Y" <% if (catalog.getOrdersOnly().equalsIgnoreCase("Y")) {%> checked <% } %> onclick="setCheckbox(frm.Catalog_ordersOnly, 3);"/>
						<tsa:hidden name="Catalog_ordersOnly" value="<%= catalog.getOrdersOnly() %>"/>
					</tsa:td>
				</tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "XMLFilename", "XML Filename")%>:</td>
					<td colspan=3>&nbsp;<input type=text name="Catalog_requestXml" value="<%=catalog.getRequestXml()%>" size=30 maxLength=30></td>
				</tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "punchoutURL", "Punchout URL")%>:</td>
					<td colspan=4>&nbsp;<input type=text name="Catalog_punchoutUrl" value="<%=catalog.getPunchoutUrl()%>" size=70 maxLength=255></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br>

		<hr width=90% align=center class=browseHR>
		<br>
		<table border=0 cellpadding=1 cellspacing=0 width=80% height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="Catalog_status" onchange="setStatus();">
							<option value="01" <% if (catalog.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (catalog.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (catalog.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateEntered", "Date Entered")%>:&nbsp;</td>
					<td>
						<input type=text name="Catalog_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(catalog.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td align=right><div id="dateExpiresLabel"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExpires", "Date Expires")%>:&nbsp;</div></td>
					<td>
						<div id="dateExpiresField">
							<input type=text name="Catalog_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(catalog.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
							<a href="javascript: show_calendar('Catalog_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
						</div>
					</td>
				</tr>
				<tr>
					<tsa:td id="lastUpdateDatedLabelRow" field="lastUpdatedDate" align="right" noWrap="nowrap">
					<tsa:label labelName="lastUpdatedDate" defaultString="Last Updated Date" noinstance="yes"/>:
					</tsa:td>
					<tsa:td  id="lastUpdatedDateFieldRow" field="lastUpdatedDate" noWrap="nowrap">
					<tsa:input type="mintext" title="Last Updated Date" name="Catalog_lastUpdatedDate" tabIndex="5" value="<%= s_lastupdateddate %>" disabled="disabled"/>
					</tsa:td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('Catalog_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="Catalog_owner" tabindex=51 size=30 maxlength=15 value="<%=catalog.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-ownerName", "Owner Name")%>:&nbsp;</td>
			  <td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				<tr>
					<tsa:td id="lastUpdatedByLabelRow" field="lastUpdatedBy" align="right" noWrap="nowrap">
					<tsa:label labelName="lastUpdatedBy" defaultString="Last Updated By" noinstance="yes"/>:
					</tsa:td>
					<tsa:td id="lastUpdatedByFieldRow" field="lastUpdatedBy" noWrap="nowrap">
					<tsa:input type="mintext" title="Last Updated By" name="Catalog_lastUpdatedBy" tabIndex="6" value="<%= s_lastupdateby %>" disabled="disabled"/>
					</tsa:td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan valign=top>
				<table border=0>


				</table>
			</td>
		</tr>

		</table>

	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
<% if (roleR.getAccessRights("CAT") >= 3 ) { %>
	<td width=50% align=center>
		<div id="pxbutton">
	<%	if (!newCatalog) { %>
		<a href="javascript: submitThis('CatalogUpdate'); void(0);">
	<%	} else { %>
		<a href="javascript: submitThis('CatalogAdd'); void(0);">
	<%	} %>
		Save</a></div>
	</td>
<% } %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('catalog'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	function thisLoad()
	{
		f_StartIt() ;
		<%	if ( roleR.getAccessRights("CAT") <= 1 ) { %>
		checkInputSettings();
		<%	} %>
	}

	frm = document.purchaseform;
	frm.browseName.value = 'catalog';

	var status = "<%=catalog.getStatus()%>";
	setStatus();

	var menuText;
<%	if (newCatalog) { %>
	menuText = "Add New Catalog";
<%	} else { %>
	menuText = "Catalog <%=catalog.getCatalogId()%>";
<%	}%>
	setNavCookie("/admin/catalog/catalog.jsp", "CatalogRetrieveById", menuText);
	//hideArea("navTable");
	displayArea("menuBarSpacer");

	function setStatus()
	{
		status = frm.Catalog_status[frm.Catalog_status.selectedIndex].value;
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

	function deleteCatalog()
	{
<%	if (hasItems) { %>
			alert("Catalog <%=catalog.getCatalogId()%> has Items on file.  Can not Delete!");
<%	} else { %>
			if (verifyAction('Delete this catalog?'))
			 {
			 	doSubmit('/browse/browse.jsp', 'CatalogDelete;BrowseRetrieve');
			 }
<%	} %>
	}

	function browseOrders()
	{
		popupParameters = "formField=Catalog_poNumber;browseName=catalog_poblanketbrowse;allowBrowse=" + frm.allowBrowse.value;
		popupParameters = popupParameters + ";colname=PoHeader_expirationDate;operator=>=;filter_txt=<%=s_now%>;logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function checkPoNumber(vendor)
	{
		if (!isEmpty(frm.as_vendorId.value) && !isEmpty(frm.Catalog_poNumber.value))
		{
			if (frm.as_vendorId.value != vendor)
			{
				if (confirm("The vendor you selected does not match the vendor on the Order you have selected.  Would you like to continue?"))
				{
					frm.Catalog_vendorId.value = vendor;
					frm.as_vendorId.value = "";
					frm.Catalog_poNumber.value = "";
				}
				else
				{
					frm.Catalog_vendorId.value = frm.as_vendorId.value;
				}
			}
		}

		if (isEmpty(vendor))
		{
			frm.Catalog_vendorId.value = vendor;
			frm.as_vendorId.value = "";
		}
	}

	function browseItems()
	{
		frm.browseName.value = 'catalogitem-admin';

		doSubmit('/browse/browse_filter_options.jsp', 'CatalogUpdate;BrowseGetOptions');
	}

	function viewCatalogSecurity(){
		frm.fromPage.value = "catalogjsp";
		var newInputField = "<input type='hidden' name='CatalogSecurity_catalogId' value='" + "<%=catalog.getCatalogId() %>" + "'>" +
      					 	"<input type='hidden' name='CatalogSecurity_itemNumber' value='0'>";
     setHiddenFields(newInputField);
		doSubmit('/admin/catalog/catalogsecurity.jsp', 'CatalogSecurityRetreieveByCatalog');
	}

	function submitThis(handler)
	{
		if (frm.Catalog_catalogId && frm.Catalog_catalogId.value == "")
		{
			alert("You must enter a Catalog ID!");
			return;
		}
		setupValues();
		//doSubmit('/browse/browse.jsp', handler + ';BrowseRetrieve');
		doSubmit('/admin/catalog/catalog_validation.jsp', handler + ';CatalogValidate');
	}

	function setupValues()
	{
		frm.Catalog_lastUpdatedDate.value="<%=s_now %>" ;
      	frm.Catalog_lastUpdatedBy.value="${esapi:encodeForJavaScript(userId)}";
	}

// End Hide script -->
</SCRIPT>