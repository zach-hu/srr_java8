<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.vendorinsurancedefault.VendorInsuranceDefaultManager" %>
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

	VendorInsurance vendorInsurance = (VendorInsurance) request.getAttribute("vendorInsurance");
	List insCoverageList = (List) request.getAttribute("insCoverageList");
	String coverage = "";
	String	vendorId = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	String	contNumber = s_po_number;
	String	contType = "";
	String contEffective = "";
	String contExpires = "";
	String	contAdmin = "";
	String	contOwner = "";
	String	contStatus = "";
	String contRequestDate = "";
	String	contDescription = "";

	if(oid.equalsIgnoreCase("bsc04p")){
		contType = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contType"));
		contEffective = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contEffective"));
		contExpires = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contExpires"));
		contAdmin = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contAdmin"));
		contOwner = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contOwner"));
		contStatus = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contStatus"));
		contRequestDate = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contRequestDate"));
		contDescription = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_contDescription"));
	}
	String	updateHandler = "VendorInsuranceUpdate";
	if (vendorInsurance == null || HiltonUtility.isEmpty(vendorInsurance.getContNumber()))	{
		//vendorInsurance = new VendorInsurance();
		vendorInsurance = (VendorInsurance) VendorInsuranceDefaultManager.getInstance().getVendorInsuranceFromDefault(oid, poHeader.getVendorId());
		vendorInsurance.setVendorId(vendorId);
		vendorInsurance.setContNumber(contNumber);
		updateHandler = "VendorInsuranceAdd";
	}

	if (insCoverageList == null) {
		insCoverageList = new ArrayList();
	}

	boolean bAllowEdit = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equalsIgnoreCase(s_buyer_code))
	{
		bAllowEdit = false;
	}

	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	String	s_current_process = "INSURANCE";
	String	s_current_page = "/orders/po_insurance.jsp";
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
<tsa:hidden name="VendorInsurance_contNumber" value="<%=contNumber%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendorId%>"/>
<%if(oid.equalsIgnoreCase("bsc04p")){ %>
	<tsa:hidden name="VendorInsurance_contType" value="<%=contType%>"/>
	<tsa:hidden name="VendorInsurance_contEffective" value="<%=contEffective%>"/>
	<tsa:hidden name="VendorInsurance_contExpires" value="<%=contExpires%>"/>
	<tsa:hidden name="VendorInsurance_contAdmin" value="<%=contAdmin%>"/>
	<tsa:hidden name="VendorInsurance_contOwner" value="<%=contOwner%>"/>
	<tsa:hidden name="VendorInsurance_contStatus" value="<%=contStatus%>"/>
	<tsa:hidden name="VendorInsurance_contRequestDate" value="<%=contRequestDate%>"/>
	<tsa:hidden name="VendorInsurance_contDescription" value="<%=contDescription%>"/>
<%} %>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="insurance" defaultString="Insurance" /></div>
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
			<div id="VendorInsurance">
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=center width=180px>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center width=100px class=label><tsa:label labelName="sup-coverageStatus" defaultString="Coverage" checkRequired="true" /></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center width=120px class=label><tsa:label labelName="sup-dateExpires" defaultString="Expiration Date" checkRequired="true" /></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center width=50px class=label><tsa:label labelName="sup-nameInsured" defaultString="Name Insured" checkRequired="true" /></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center width=50px class=label><tsa:label labelName="sup-waiver" defaultString="Waiver Subrog." checkRequired="true" /></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center width=50px class=label><tsa:label labelName="sup-limit" defaultString="Insurance Limit" checkRequired="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage1")%>>
					<td align=right class=label><tsa:label labelName="sup-coverage1" defaultString="Coverage 1" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage1">
<%	 coverage = vendorInsurance.getCoverage1();%>
<%@ include file="/base/vendorins_coverage_options.jsp" %>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<tsa:input type="text" name="VendorInsurance_expires1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires1(), oid, userDateFormat)%>" size="12" />
						<a href="javascript: show_calendar('VendorInsurance_expires1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsurance_named1" size=1>
							<option value=""> </option>
							<option <% if (vendorInsurance.getNamed1().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsurance.getNamed1().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named1); void(0);">
						<tsa:hidden name="VendorInsurance_named1" value="<%=vendorInsurance.getNamed1()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver1); void(0);">
						<tsa:hidden name="VendorInsurance_waiver1" value="<%=vendorInsurance.getWaiver1()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<tsa:input type="text" name="VendorInsurance_limit1" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit1(), oid)%>" style="text-align:right" onchange="addUp(this);" size="12" />
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage2")%>>
					<td align=right class=label><tsa:label labelName="sup-coverage2" defaultString="Coverage 2" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage2">
<%	 coverage = vendorInsurance.getCoverage2();%>
<%@ include file="/base/vendorins_coverage_options.jsp" %>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<tsa:input type="text" name="VendorInsurance_expires2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires2(), oid, userDateFormat)%>" size="12" />
						<a href="javascript: show_calendar('VendorInsurance_expires2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsurance_named2" size=1>
							<option value=""> </option>
							<option <% if (vendorInsurance.getNamed2().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsurance.getNamed2().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named1); void(0);">
						<tsa:hidden name="VendorInsurance_named2" value="<%=vendorInsurance.getNamed2()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver2); void(0);">
						<tsa:hidden name="VendorInsurance_waiver2" value="<%=vendorInsurance.getWaiver2()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<tsa:input type="text" name="VendorInsurance_limit2" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit2(), oid)%>" style="text-align:right" onchange="addUp(this);" size="12" />
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage3")%>>
					<td align=right class=label><tsa:label labelName="sup-coverage3" defaultString="Coverage 3" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage3">
<%	 coverage = vendorInsurance.getCoverage3();%>
<%@ include file="/base/vendorins_coverage_options.jsp" %>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<tsa:input type="text" name="VendorInsurance_expires3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires3(), oid, userDateFormat)%>" size="12" />
						<a href="javascript: show_calendar('VendorInsurance_expires3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsurance_named3" size=1>
							<option value=""> </option>
							<option <% if (vendorInsurance.getNamed3().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsurance.getNamed3().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named3); void(0);">
						<tsa:hidden name="VendorInsurance_named3" value="<%=vendorInsurance.getNamed3()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver3); void(0);">
						<tsa:hidden name="VendorInsurance_waiver3" value="<%=vendorInsurance.getWaiver3()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<tsa:input type="text" name="VendorInsurance_limit3" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit3(), oid)%>" style="text-align:right" onchange="addUp(this);" size="12" />
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage4")%>>
					<td align=right class=label><tsa:label labelName="sup-coverage4" defaultString="Coverage 4" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage4">
<%	 coverage = vendorInsurance.getCoverage4();%>
<%@ include file="/base/vendorins_coverage_options.jsp" %>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<tsa:input type="text" name="VendorInsurance_expires4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires4(), oid, userDateFormat)%>" size="12" />
						<a href="javascript: show_calendar('VendorInsurance_expires4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsurance_named4" size=1>
							<option value=""> </option>
							<option <% if (vendorInsurance.getNamed4().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsurance.getNamed4().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named4); void(0);">
						<tsa:hidden name="VendorInsurance_named4" value="<%=vendorInsurance.getNamed4()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver4); void(0);">
						<tsa:hidden name="VendorInsurance_waiver4" value="<%=vendorInsurance.getWaiver4()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<tsa:input type="text" name="VendorInsurance_limit4" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit4(), oid)%>" style="text-align:right" onchange="addUp(this);" size="12" />
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage5")%>>
					<td align=right class=label><tsa:label labelName="sup-coverage5" defaultString="Coverage 5" checkRequired="true" />:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage5">
<%	 coverage = vendorInsurance.getCoverage5();%>
<%@ include file="/base/vendorins_coverage_options.jsp" %>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<tsa:input type="text" name="VendorInsurance_expires5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires5(), oid, userDateFormat)%>" size="12" />
						<a href="javascript: show_calendar('VendorInsurance_expires5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsurance_named5" size=1>
							<option value=""> </option>
							<option <% if (vendorInsurance.getNamed5().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsurance.getNamed5().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named5); void(0);">
						<tsa:hidden name="VendorInsurance_named5" value="<%=vendorInsurance.getNamed5()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver5); void(0);">
						<tsa:hidden name="VendorInsurance_waiver5" value="<%=vendorInsurance.getWaiver5()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<tsa:input type="text" name="VendorInsurance_limit5" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit5(), oid)%>" style="text-align:right" onchange="addUp(this);" size="12" />
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceContact")%>>
					<td align=right class=label><tsa:label labelName="sup-insuranceContact" defaultString="Insurance Contact" checkRequired="true" />:</td>
					<td align=center colspan=4><tsa:input type="text" name="VendorInsurance_insuranceContact" value="<%=vendorInsurance.getInsuranceContact()%>" size="61" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"sup-insuranceContactHelper\", \"\")%>" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceComments")%>>
					<td align=right class=label valign=top><tsa:label labelName="sup-insuranceComments" defaultString="Insurance Comments" />:</td>
					<td align=center colspan=4>
						<tsa:input type="textarea" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, \"sup-insuranceCommentsHelper\", \"\")%>" name="VendorInsurance_insuranceNotes" rows="6" cols="64" wrap="nonvirtual" maxLength="60" onkeydown="textCounter(this, 60);" onkeyup="textCounter(this,60);" onchange="textCounter(this,60);">${esapi:encodeForHTML(vendorInsurance.insuranceNotes)}</tsa:input>
					</td>
				</tr>
				</table>
			</div>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
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
	}

	function setFlagFromCkbox(ckbox, fld) {
		if (ckbox.checked) {
			fld.value = "Y";
		} else {
			fld.value = "N";
		}
	}

	function addUp(field)
	{
		var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		var p = nformat(eval(nfilter(field)), price_dec);
		field.value = p;
	}

// End Hide script -->
</SCRIPT>
