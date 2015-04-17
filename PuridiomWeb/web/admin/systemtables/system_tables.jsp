<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.BrowseManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ include file="/admin/systemtables/systable_labels.jsp" %>

<%
	BrowseManager.getInstance().cleanupBrowsesBySession(oid, sid);
%>

<tsa:hidden name="SystemTables" value="TRUE"/>
<tsa:hidden name="tableType" value=""/>

<script language='Javascript1.2' type="text/javascript">
<!--
    var oid = "<%=oid%>";
	if (oid == "VSE06P")
	{
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/scripts/vse06p/systemtablesOptionArrays.js' type='text/javascript'><\/scr" + "ipt>");
	}
	else
	{
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/scripts/systemtablesOptionArrays.js' type='text/javascript'><\/scr" + "ipt>");
	}
	document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/scripts/systemtablesOptions.js' type='text/javascript'><\/scr" + "ipt>");
//-->
</script>

<script language='Javascript1.2' type="text/javascript">
<!--

	accountAllocationsAccess = <%=role.getAccessRights("ALLOC")%>;
	accountUdfsAccess = <%=role.getAccessRights("ACCTUDFS")%>;
	addressesAccess = <%=role.getAccessRights("ADDR")%>;
	insCategoryLevelAccess = <%=role.getAccessRights("INSCATLVL")%>;
	bidResponseCodesAccess = <%=role.getAccessRights("RESP")%>;
	bidWaiverCodesAccess = <%=role.getAccessRights("BIDW")%>;
	buyerRemarksAccess = <%=role.getAccessRights("BYRM")%>;
	carriersAccess = <%=role.getAccessRights("CARR")%>;
	catalogUdfsAccess = <%=role.getAccessRights("CATUDFS")%>;
	checklistEntryAccess = <%=role.getAccessRights("CKLIST")%>;
	commentsAccess = <%=role.getAccessRights("CMT")%>;
	commodityCodesAccess = <%=role.getAccessRights("COMM")%>;
	countryAccess = <%=role.getAccessRights("COUNTRY")%>;
	currencyCodesAccess = <%=role.getAccessRights("CURR")%>;
	dateFormatsAccess = <%=role.getAccessRights("DFMT")%>;
	departmentsAccess = <%=role.getAccessRights("DEPT")%>;
	dispositionCodesAccess = <%=role.getAccessRights("DISP")%>;
	fiscalYearAccess = <%=role.getAccessRights("AUTOGEN")%>;
	fobCodesAccess = <%=role.getAccessRights("FOB")%>;
	formPrintsAccess = <%=role.getAccessRights("FPRT")%>;
	inspectionCodesAccess = <%=role.getAccessRights("INSP")%>;
	inspectionCriteriaAccess = <%=role.getAccessRights("INSPCRIT")%>;
	inspectionUdfsAccess = <%=role.getAccessRights("INSPUDFS")%>;
	languagesAccess = <%=role.getAccessRights("LANG")%>;
	lineUdfsAccess = <%=role.getAccessRights("LINUDFS")%>;
	paymentTermsAccess = <%=role.getAccessRights("PAYM")%>;
	perforationTypeAccess = <%=role.getAccessRights("PPTP")%>;
	priorityCodesAccess = <%=role.getAccessRights("PRIO")%>;
	procurementLevelAccess = <%=role.getAccessRights("PROCLVL")%>;
	productCodesAccess = <%=role.getAccessRights("PROD")%>;
	pCardCodesAccess = <%=role.getAccessRights("PCRD")%>;
	purchaseOrderUdfsAccess = <%=role.getAccessRights("POUDFS")%>;
	questionsAccess = <%=role.getAccessRights("QUEST")%>;
	reasonForSavingsAccess = <%=role.getAccessRights("SAVE")%>;
	receivingUdfsAccess = <%=role.getAccessRights("RCVUDFS")%>;
	rejectionCodesAccess = <%=role.getAccessRights("REJ")%>;
	requisitionUdfsAccess = <%=role.getAccessRights("REQUDFS")%>;
	shipViaCodesAccess = <%=role.getAccessRights("SHP")%>;
	solicitationUdfsAccess = <%=role.getAccessRights("RFQUDFS")%>;
	stateProvinceCodesAccess = <%=role.getAccessRights("STAT")%>;
	taxCodesAccess = <%=role.getAccessRights("TAX")%>;
	timeZonesAccess = <%=role.getAccessRights("TMZN")%>;
	transactionCodesAccess = <%=role.getAccessRights("TRAN")%>;
	turnTypeAccess = <%=role.getAccessRights("FPTT")%>;
	unitsOfMeasureAccess = <%=role.getAccessRights("UM")%>;
	userTypesAccess = <%=role.getAccessRights("USRT")%>;
	voucherReasonCodesAccess = <%=role.getAccessRights("RCOD")%>;
	voucherUdfsAccess = <%=role.getAccessRights("VCHUDFS")%>;
	xrefComboAccess = <%=role.getAccessRights("XREFCOMBO")%>;

	reviewFinalizeAccess = <%=role.getAccessRights("REVIEWFINALIZE")%>;
	reviewFinalizeEnabled = '<%= PropertiesManager.getInstance(oid).getProperty("REVIEWFINALIZE", "ENABLED", "N") %>';

	/*	Asset Tables	*/
	buildingCodesAccess = <%=role.getAccessRights("ASTB")%>;
	depreciationMethodsAccess = <%=role.getAccessRights("ASTD")%>;
	facilityCodesAccess = <%=role.getAccessRights("ASTF")%>;
	nameCodesAccess = <%=role.getAccessRights("ASTN")%>;
	ownerCodesAccess = <%=role.getAccessRights("ASTO")%>;
	purchaseSuppliersAccess = <%=role.getAccessRights("ASTP")%>;
	securityCodesAccess = <%=role.getAccessRights("ASTS")%>;
	serviceContractAccess = <%=role.getAccessRights("ASTC")%>;
	trackingUdfsAccess = <%=role.getAccessRights("ASTUDFS")%>;
	upgradeRequirementsAccess = <%=role.getAccessRights("ASTU")%>;

	/*	Inventory Tables */
	binUdfsAccess = <%=role.getAccessRights("INVBUDFS")%>;
	formTablesAccess = <%=role.getAccessRights("INVFORM")%>;
	itemCatalogsAccess = <%=role.getAccessRights("INVCATS")%>;
	itemManagedByAccess = <%=role.getAccessRights("MANB")%>;
	reasonCodesAccess = <%=role.getAccessRights("IVRC")%>;
	inventoryUdfsAccess = <%=role.getAccessRights("INVUDFS")%>;
	inventoryLocationUdfsAccess = <%=role.getAccessRights("INVLOCUDFS")%>;

	/* MRP Tables */
	workCenterAccess = <%=role.getAccessRights("WORKCENTER") %> ;
	routingTaskAccess = <%=role.getAccessRights("ROUTETASK")%>;
	routingMethodAccess = <%=role.getAccessRights("ROUTEMETH")%>;
	routingStageAccess = <%=role.getAccessRights("ROUTESTAGE")%>;
	machineAccess = <%=role.getAccessRights("MACHINE")%>;

	/*	Supplier Tables	*/
	complianceUdfsAccess = <%=role.getAccessRights("SCOMPUDFS")%>;
	contractUdfsAccess = <%=role.getAccessRights("SCONTUDFS")%>;
	insuranceStatusAccess = <%=role.getAccessRights("SCST")%>;
	insuranceLiabilityCoverageAccess = <%=role.getAccessRights("SILC")%>;
	certificationTypesAccess = <%=role.getAccessRights("VSBA")%>;
	ownershipTypesAccess = <%=role.getAccessRights("VBOT")%>;
	diverseCertOrgsAccess = <%=role.getAccessRights("VDCO")%>;
	supplierTypesAccess = <%=role.getAccessRights("SCTY")%>;
	supplierUdfsAccess = <%=role.getAccessRights("VNDUDFS")%>;

	/* Puridiom Status and Document Types*/
	purstatusanddocstatus = <%=role.getAccessRights("PURSTATDOCTYPE")%>;
	
	MenuArray0 = createGeneralSystemTables(MenuArray0[0]);
	MenuArray1 = createAssetTables(MenuArray1[0]);
	MenuArray2 = createInventoryTables(MenuArray2[0]);
	MenuArray3 = createSupplierTables(MenuArray3[0]);
	MenuArray4 = createMrpTables(MenuArray4[0]);
	<% if (role.getAccessRights("AUTOACCOUNTING") > 0 && (oid.equalsIgnoreCase("MSG07P") || oid.equalsIgnoreCase("BLY07P"))) { %>
	UserProfile_nameUdf1 = "<%=user.getNameUdf1() %>";
	MenuArray5 = createAutoAccountingTables(MenuArray5[0]);
	<% } %>

//-->
</script>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>System Tables</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td>&nbsp;</td>
	<td width="48%" valign=top align=center id="leftMenu">&nbsp;</td>
	<td>&nbsp;</td>
	<td width="48%" valign=top align=center id="rightMenu">&nbsp;</td>
	<td>&nbsp;</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie('/admin/systemtables/system_tables.jsp', 'DoNothing', 'System Tables');

	var browser = browserCheck();
	var userId = '${esapi:encodeForJavaScript(userId)}';
	var status = "";

	function changeDisplayArea(img, areaName) {
		var imgName = img.src;

		if (imgName.indexOf("<%=contextPath%>/images/arrow_minimize_blue.gif") >= 0) {
			hideArea(areaName);
			img.src = "<%=contextPath%>/images/arrow_maximize_blue.gif";
		}
		else {
			displayArea(areaName);
			img.src = "<%=contextPath%>/images/arrow_minimize_blue.gif";
		}
	}

	function setTableType(tableType) {
		frm.tableType.value = tableType;
	}

	function browse(x)
	{
		frm.browseName.value = x;
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function browseFilter(x, code)
	{
		frm.browseName.value = x;
		frm.tableType.value = code;
		doSubmit('/browse/browse_filter_options.jsp', 'BrowseGetOptions');
	}

	function browseStdTable(code, hideCode)
	{
		var oid = "<%=oid%>";

		if (oid == "QRI06P" && code == "LN01") {
			browseSubCommodityAdmin();
		} else {
			frm.tableType.value = code;
			setOriginalFilter("StdTable_id_tableType", "=", code);
			if (hideCode) {
				browse('stdtable-admin-no-code');
			} else {
				browse('stdtable-admin');
			}
		}
	}

	function browseXrefCombo(type)
	{
		setOriginalFilter("XrefCombo_xrefType", "=", type);

		if (type == 'CARP') {
			browse('xrefcombo-carp');
		}
		if (type == 'BINS') {
			browse('xrefcombo-bins');
		}
	}

	function browseCommodityAdmin()
	{
		var commodityType = "<%=HiltonUtility.ckNull(PropertiesManager.getInstance(oid).getProperty("MISC", "CommodityType", ""))%>";
		if (!isEmpty(commodityType)) {
			doSubmit('browse/browse_commodity_tree_admin.jsp', 'CommodityRetrieveTree');
		} else {
			browse("commodity-admin");
		}
	}

	function browseSubCommodityAdmin()
	{
		var commodityType = "<%=HiltonUtility.ckNull(PropertiesManager.getInstance(oid).getProperty("MISC", "SubCommodityType", ""))%>";
		if (!isEmpty(commodityType)) {
			doSubmit('browse/browse_subcommodity_tree_admin.jsp', 'SubCommodityRetrieveTree');
		} else {
			browse("subcommodity-admin");
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
