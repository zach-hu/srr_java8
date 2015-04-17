<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);

	VendorInsuranceDefault vendorInsurance = (VendorInsuranceDefault) request.getAttribute("vendorInsurance");
	List certStatusList = new ArrayList();
	List certOriginalStatusList = (List) request.getAttribute("certStatusList");
	List certStatusList_1 = (List) request.getAttribute("certStatusList_1");
	List certStatusList_2 = (List) request.getAttribute("certStatusList_2");
	List certStatusList_3 = (List) request.getAttribute("certStatusList_3");
	String certStatus = "";
	String	vendorId = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	String	contNumber = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contNumber"));
	String	updateHandler = "VendorInsuranceAdd";
	if (certOriginalStatusList == null) {
		certOriginalStatusList = new ArrayList();
	}

	boolean bAllowEdit = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equalsIgnoreCase(s_buyer_code))
	{
		bAllowEdit = false;
	}

	String	s_current_process = "COMPLIANCE";
	String	s_current_page = "/orders/po_compliance.jsp";
	String	s_current_method = updateHandler;
	String	s_current_process_method = "";
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorInsurance.getVendorId()%>"/>

<tsa:hidden name="VendorInsurance_coverage1" value="<%=vendorInsurance.getCoverage1()%>"/>
<tsa:hidden name="VendorInsurance_expires1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires1(), oid, userDateFormat)%>" />
<tsa:hidden name="VendorInsurance_named1" value="<%=vendorInsurance.getNamed1()%>" />
<tsa:hidden name="VendorInsurance_waiver1" value="<%=vendorInsurance.getWaiver1()%>" />
<tsa:hidden name="VendorInsurance_limit1" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit1(), oid)%>" />
<tsa:hidden name="VendorInsurance_coverage2" value="<%=vendorInsurance.getCoverage2()%>"/>
<tsa:hidden name="VendorInsurance_expires2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires2(), oid, userDateFormat)%>" />
<tsa:hidden name="VendorInsurance_named2" value="<%=vendorInsurance.getNamed2()%>" />
<tsa:hidden name="VendorInsurance_waiver2" value="<%=vendorInsurance.getWaiver2()%>" />
<tsa:hidden name="VendorInsurance_limit2" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit2(), oid)%>" />
<tsa:hidden name="VendorInsurance_coverage3" value="<%=vendorInsurance.getCoverage3()%>"/>
<tsa:hidden name="VendorInsurance_expires3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires3(), oid, userDateFormat)%>" />
<tsa:hidden name="VendorInsurance_named3" value="<%=vendorInsurance.getNamed3()%>" />
<tsa:hidden name="VendorInsurance_waiver3" value="<%=vendorInsurance.getWaiver3()%>" />
<tsa:hidden name="VendorInsurance_limit3" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit3(), oid)%>" />
<tsa:hidden name="VendorInsurance_coverage4" value="<%=vendorInsurance.getCoverage4()%>"/>
<tsa:hidden name="VendorInsurance_expires4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires4(), oid, userDateFormat)%>" />
<tsa:hidden name="VendorInsurance_named4" value="<%=vendorInsurance.getNamed4()%>" />
<tsa:hidden name="VendorInsurance_waiver4" value="<%=vendorInsurance.getWaiver4()%>" />
<tsa:hidden name="VendorInsurance_limit4" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit4(), oid)%>" />
<tsa:hidden name="VendorInsurance_coverage5" value="<%=vendorInsurance.getCoverage5()%>"/>
<tsa:hidden name="VendorInsurance_expires5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires5(), oid, userDateFormat)%>" />
<tsa:hidden name="VendorInsurance_named5" value="<%=vendorInsurance.getNamed5()%>" />
<tsa:hidden name="VendorInsurance_waiver5" value="<%=vendorInsurance.getWaiver5()%>" />
<tsa:hidden name="VendorInsurance_limit5" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit5(), oid)%>" />
<tsa:hidden name="VendorInsurance_insuranceContact" value="<%=vendorInsurance.getInsuranceContact()%>" />
<tsa:hidden name="VendorInsurance_insuranceComments" value="<%=vendorInsurance.getInsuranceNotes()%>" />

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="compliance" defaultString="Compliance" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
						<table border=0 cellpadding=2 cellspacing=0 width=100%>
						<tr>
							<td>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center class=label><tsa:label labelName="sup-dateCertified" defaultString="Date Certified" checkRequired="true" /></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center class=label><tsa:label labelName="sup-certificationStatus" defaultString="Status" checkRequired="true" /></td>
							<td colspan=2>&nbsp;</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification1")%>>
							<td align=right class=label nowrap><div id="certification_1"><tsa:label labelName="sup-certification1" defaultString="Certification 1" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_1">
									<tsa:input type="text" name="VendorInsurance_certifiedDate1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate1(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_1">
									<select name="VendorInsurance_certStatus1">
							<%	certStatus = vendorInsurance.getCertStatus1();
								if (oid.equalsIgnoreCase("bsc04p")) {
									certStatusList = certStatusList_1;
								} else {
									certStatusList = certOriginalStatusList;
								} %>
							<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR1")%> align=right class=label nowrap><div id="udf_1"><tsa:label labelName="sup-SCR1" defaultString="UDF 1" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR1")%>><div id="certUDF_1"><tsa:input type="text" name="VendorInsurance_certUdf1" value="<%=vendorInsurance.getCertUdf1()%>" maxLength="15" /></div></td>
						</tr>
<% if(oid.equalsIgnoreCase("BSC04P")){ %>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification7")%>>
							<td align=right class=label nowrap><div id="certification_7"><tsa:label labelName="sup-certification7" defaultString="Certification 7" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_7">
									<tsa:input type="text" name="VendorInsurance_certifiedDate7" value="" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate7', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_7">
									<select name="VendorInsurance_certStatus7">
									<option value="A" <% if (certStatus.equals("A")) {%>selected<%}%>>Applicable</option>
									<option value="N" <% if (certStatus.equals("N")) {%>selected<%}%>>Not Applicable</option>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR7")%> align=right class=label nowrap><div id="udf_7"><tsa:label labelName="sup-SCR7" defaultString="UDF 7" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR7")%> align=center><div id="certUDF_7"><tsa:input type="text" name="VendorInsurance_certUdf7" value="" maxLength="15" /></div></td>
						</tr>
<%} %>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification2")%>>
							<td align=right class=label><div id="certification_2"><tsa:label labelName="sup-certification2" defaultString="Certification 2" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_2">
									<tsa:input type="text" name="VendorInsurance_certifiedDate2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate2(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_2">
									<select name="VendorInsurance_certStatus2">
							<%	certStatus = vendorInsurance.getCertStatus2();
							    certStatusList = certOriginalStatusList; %>
							<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR2")%> align=right class=label nowrap><div id="udf_2"><tsa:label labelName="sup-SCR2" defaultString="UDF 2" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR2")%>><div id="certUDF_2"><tsa:input type="text" name="VendorInsurance_certUdf2" value="<%=vendorInsurance.getCertUdf2()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification3")%>>
							<td align=right class=label><div id="certification_3"><tsa:label labelName="sup-certification3" defaultString="Certification 3" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_3">
									<tsa:input type="text" name="VendorInsurance_certifiedDate3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate3(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_3">
									<select name="VendorInsurance_certStatus3">
									<%	certStatus = vendorInsurance.getCertStatus3();
									if (oid.equalsIgnoreCase("bsc04p")) {
										certStatusList = certStatusList_1;
									} else {
										certStatusList = certOriginalStatusList;
									} %>
									<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR3")%> align=right class=label nowrap><div id="udf_3"><tsa:label labelName="sup-SCR3" defaultString="UDF 3" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR3")%>><div id="certUDF_3"><tsa:input type="text" name="VendorInsurance_certUdf3" value="<%=vendorInsurance.getCertUdf3()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification4")%>>
							<td align=right class=label><div id="certification_4"><tsa:label labelName="sup-certification4" defaultString="Certification 4" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_4">
									<tsa:input type="text" name="VendorInsurance_certifiedDate4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate4(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_4">
									<select name="VendorInsurance_certStatus4">
								<%	certStatus = vendorInsurance.getCertStatus4();
								if (oid.equalsIgnoreCase("bsc04p")) {
									certStatusList = certStatusList_1;
								} else {
									certStatusList = certOriginalStatusList;
								} %>
								<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR4")%> align=right class=label nowrap><div id="udf_4"><tsa:label labelName="sup-SCR4" defaultString="UDF 4" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR4")%>><div id="certUDF_4"><tsa:input type="text" name="VendorInsurance_certUdf4" value="<%=vendorInsurance.getCertUdf4()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification5")%>>
							<td align=right class=label><div id="certification_5"><tsa:label labelName="sup-certification5" defaultString="Certification 5" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_5">
									<tsa:input type="text" name="VendorInsurance_certifiedDate5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate5(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_5">
									<select name="VendorInsurance_certStatus5">
								<%	certStatus = vendorInsurance.getCertStatus5();
									if (oid.equalsIgnoreCase("bsc04p")) {
										certStatusList = certStatusList_3;
									} else {
										certStatusList = certOriginalStatusList;
									} %>
								<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR5")%> align=right class=label nowrap><div id="udf_5"><tsa:label labelName="sup-SCR5" defaultString="UDF 5" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR5")%>><div id="certUDF_5"><tsa:input type="text" name="VendorInsurance_certUdf5" value="<%=vendorInsurance.getCertUdf5()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification6")%>>
							<td align=right class=label nowrap><div id="certification_6"><tsa:label labelName="sup-certification6" defaultString="Certification 6" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_6">
									<tsa:input type="text" name="VendorInsurance_certifiedDate6" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate6(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsurance_certifiedDate6', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center>
								<div id="certStatus_6">
									<select name="VendorInsurance_certStatus6">
							<%	certStatus = vendorInsurance.getCertStatus6();
								if (oid.equalsIgnoreCase("bsc04p")) {
									certStatusList = certStatusList_2;
								} else {
									certStatusList = certOriginalStatusList;
								} %>
								<%@ include file="/base/vendorins_certstatus_options.jsp" %>
									</select>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR6")%> align=right class=label nowrap><div id="udf_6"><tsa:label labelName="sup-SCR6" defaultString="UDF 6" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR6")%>><div id="certUDF_6"><tsa:input type="text" name="VendorInsurance_certUdf6" value="<%=vendorInsurance.getCertUdf6()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceContact")%>>
							<td align=right class=label width=175px><tsa:label labelName="sup-complianceContact" defaultString="Compliance Contact" checkRequired="true" />:</td>
							<td colspan=4><tsa:input type="text" name="VendorInsurance_certContact" value="<%=vendorInsurance.getComplianceContact()%>" size="61" /></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceNotes")%>>
							<td align=right class=label valign=top><tsa:label labelName="sup-complianceNotes" defaultString="Compliance Notes" checkRequired="true" />:</td>
							<td colspan=4>
								<tsa:input type="textarea" name="VendorInsurance_complianceNotes" rows="6" cols="64" wrap="nonvirtual" maxLength="60" onkeydown="textCounter(this, 60);" onkeyup="textCounter(this,60);" onchange="textCounter(this,60);">${esapi:encodeForHTML(vendorInsurance.complianceNotes)}</tsa:input>
							</td>
						</tr>
						</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<!--
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', '<%=updateHandler%>;VendorRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 tabIndex=20></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_contracts.jsp', 'ContractsRetrieveByVendorId'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 tabIndex=21></a></td>
</tr>
</table>
-->

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
	var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
	var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
	var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
		var oid = '<%= oid %>';
		var tmpSection;

		if (oid == 'BSC04P') {
			alphabetizeFields();
		}
	}

	function alphabetizeFields() {
		var labelArray = new Array(6);
		var elementSchemeArray = new Array(6);
		var elementArray;
		var certificationElement;
		var arrayElement;
		var index;
		var elementIndex;
		var keyString = '---';

		for (var i = 0; i < labelArray.length; i++) {
			elementIndex = i + 1;
			certificationElement = document.getElementById('certification_' + elementIndex);

			if (certificationElement.childNodes.length > 1) {
				arrayElement = certificationElement.firstChild.innerHTML;
			} else {
				arrayElement = certificationElement.innerHTML;
			}

			index = 0;

			elementArray = new Array(5);

			elementArray[index++] = document.getElementById('certification_' + elementIndex).innerHTML;
			elementArray[index++] = document.getElementById('certifiedDate_' + elementIndex).innerHTML;
			elementArray[index++] = document.getElementById('certStatus_' + elementIndex).innerHTML;
			elementArray[index++] = document.getElementById('udf_' + elementIndex).innerHTML;
			elementArray[index++] = document.getElementById('certUDF_' + elementIndex).innerHTML;

			labelArray[i] = arrayElement + keyString + elementIndex;
			elementSchemeArray[i] = elementArray;
		}

		labelArray.sort();

		for (var i = 0; i < labelArray.length; i++) {
			elementIndex = labelArray[i].substring(labelArray[i].lastIndexOf(keyString) + keyString.length);

			index = 0;
			document.getElementById('certification_' + (i + 1)).innerHTML = elementSchemeArray[elementIndex - 1][index++];
			document.getElementById('certifiedDate_' + (i + 1)).innerHTML = elementSchemeArray[elementIndex - 1][index++];
			document.getElementById('certStatus_' + (i + 1)).innerHTML = elementSchemeArray[elementIndex - 1][index++];
			document.getElementById('udf_' + (i + 1)).innerHTML = elementSchemeArray[elementIndex - 1][index++];
			document.getElementById('certUDF_' + (i + 1)).innerHTML = elementSchemeArray[elementIndex - 1][index++];
		}
	}

// End Hide script -->
</SCRIPT>