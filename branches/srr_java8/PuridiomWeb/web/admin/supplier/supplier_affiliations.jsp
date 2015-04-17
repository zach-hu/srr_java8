<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.contact.ContactManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	Vendor parentVendor = (Vendor) request.getAttribute("vendor");
	String vendorName = parentVendor.getVendorName();
	String vendorType = parentVendor.getVendorType();
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));
	List vendorAffiliateList = (List) request.getAttribute("vendorAffiliateList");
	if (vendorAffiliateList == null) {
		vendorAffiliateList = new ArrayList();
	}
	List	affiliateIdList = new ArrayList();

	String	s_current_process = "AFFILIATIONS";
	String	s_current_page = "/admin/supplier/supplier_affiliations.jsp";
	String	s_current_method = "VendorAffiliateUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorAffiliate_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAffiliate_affiliateId" value=""/>
<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%></div>
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
<%@ include file="/admin/admin_info.jsp" %>
<br>

<div style="width:<%=formEntryWidth%>;align:center;">
	<div style="width:76%;valign:top;float:left;">
		<!-- start rounded corners -->
		<div id="container" style="width: 680px; align:left; margin:5;">
		<b class="rtop">
		  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
		</b>
		<table cellpadding=0 cellspacing=0 border=0 class=browseHdr width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=0 class=browseHdr width=100%>
				<tr>
					<td nowrap class=browseHdr width=24%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-affiliate", "Affiliate", false)%></td>
					<td nowrap class=browseHdr width=34%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-vendorName", "Affiliate Name", false)%></td>
					<td nowrap class=browseHdr width=24%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-city", "City", false)%></td>
					<td nowrap class=browseHdr width=14%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-state", "State", false)%></td>
					<td width="4%">&nbsp;</td>
				</tr>
				</table>
				<div id="browseBorder" class=browseHdr style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 680px; align:center; overflow-y:visible; overflow-x:auto;">

<%	if (parentVendor.getStatus().equals("04")) {%>
				<div id="noRecords">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td align=center><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-rfqonlyvendor", "This supplier cannot establish affiliations because it is an RFQ only supplier.")%></b></td></tr>
				</table>
				</div>
<%	} else if (!vendorType.equals("P")) {%>
				<div id="noRecords">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td align=center><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-notaparentvendor", "This supplier cannot establish affiliations because it is not setup as a 'Parent' supplier.")%></b></td></tr>
				</table>
				</div>
<%	} else if (vendorAffiliateList == null || vendorAffiliateList.size() == 0) {%>
				<div id="noRecords">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td align=center><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-noaffiliations", "No affiliations have been established with this supplier.")%></b></td></tr>
				</table>
				</div>
<%	} else {%>
				<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow id="affiliateTable">
<%		for (int i=0; i < vendorAffiliateList.size(); i++) {
			VendorAffiliate vendorAffiliate = (VendorAffiliate) vendorAffiliateList.get(i);
			String affiliateId = vendorAffiliate.getComp_id().getAffiliateId();
			Vendor affiliateVendor = (Vendor) VendorManager.getInstance().getVendor(oid, affiliateId);
			Contact affiliateContact = vendorAffiliate.getContact();
			Address affiliateAddress = vendorAffiliate.getAddress();
%>
						<tsa:hidden name="VendorAffiliate_AffiliateIds" value="<%=vendorAffiliate.getComp_id().getAffiliateId() %>"/>
						<tr>
							<td width=25%><%=affiliateVendor.getVendorId()%></td>
							<td width=35%><%=affiliateVendor.getVendorName()%></td>
							<td width=25%><%=affiliateAddress.getCity()%></td>
							<td width=15%><%=affiliateAddress.getState()%></td>
							<td><a href="javascript: deleteAffiliate(<%=i%>)"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"/></a></td>
						</tr>
						<tr>
							<td colspan=4>
								<table border=0 cellpadding=2 cellspacing=0 width=100%>
								<tr>
									<td width=5%>&nbsp;</td>
									<td width=30%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-contactName", "Contact Name", false)%></b>: <%=affiliateContact.getDisplayName()%></td>
									<td width=30%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number", false)%></b>: <%=affiliateContact.getPhoneNumber()%></td>
									<td width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-emailAddress", "Email Address", false)%></b>: <%=affiliateContact.getEmailAddr()%></td>
								</tr>
								</table>
							</td>
						</tr>
<%		}
	}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
		<!-- end rounded corners -->
<%	if (vendorType.equals("P")) {%>
		<div style="align:center;">
			<table border=0 cellpadding=2 cellspacing=0 width=100%>
			<tr><td align=center valign=top><br><a href="javascript: browseAffiliates(); void(0);"><font class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-addaffiliations", "Add Affiliations")%></b></font></a></td></tr>
			</table>
		</div>
<%	}%>
	</div>
	<div style="align:right;"><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></div>
</div>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var currentRow = 0;
	var maxRows = 0;

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function browseAffiliates() {
		frm.browseName.value = "vendor-affiliates";
		doSubmit('/browse/browse_filter_affiliates.jsp', 'BrowseGetOptions');
	}

	function deleteAffiliate(row) {
		if (frm.VendorAffiliate_AffiliateIds.length == undefined) {
			frm.VendorAffiliate_affiliateId.value = frm.VendorAffiliate_AffiliateIds.value;
		} else {
			frm.VendorAffiliate_affiliateId.value = frm.VendorAffiliate_AffiliateIds[row].value;
		}
		doSubmit('/admin/supplier/supplier_affiliations.jsp',
				 'VendorAffiliateDelete;VendorAffiliateRetrieveByVendorId;VendorRetrieveById');
	}

// End Hide script -->
</SCRIPT>