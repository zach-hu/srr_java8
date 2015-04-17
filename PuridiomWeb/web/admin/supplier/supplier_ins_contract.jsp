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
	String	vendorId = (String) request.getAttribute("Vendor_vendorId");
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String	icPoHeader = (String) request.getAttribute("VendorInsurance_icPoHeader");
	String	contNumber = (String) request.getAttribute("VendorInsurance_contNumber");
	VendorInsurance vendorInsurance = (VendorInsurance) request.getAttribute("vendorInsurance");
	String	updateHandler = "VendorInsuranceUpdate";

	if (vendorInsurance == null || HiltonUtility.isEmpty(vendorInsurance.getContNumber()))	{
		vendorInsurance = new VendorInsurance();
		vendorInsurance.setVendorId(vendorId);
		vendorInsurance.setContNumber(contNumber);

		updateHandler = "VendorInsuranceAdd";
	}

	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	String	s_current_process = "CONTRACTS";
	String	s_current_page = "/admin/supplier/supplier_ins_contract.jsp";
	String	s_current_method = "VendorInsuranceUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="Vendor_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendorName%>"/>
<tsa:hidden name="VendorAccount_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=vendorInsurance.getContNumber()%>"/>
<tsa:hidden name="Contact_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Address_addressType" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Address_addressCode" value=""/>
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_info.jsp"/>
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-contracts", "Ins./Contract")%></div>
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
	<td valign=bottom align=middle>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="right"><img src="<%=contextPath%>/images/returnto.gif" border="0">&nbsp;</td>
			<td id="VendorInsuranceLink" align="right" NOWRAP><a href="javascript: setOptions('VendorInsurance'); void(0);">View Insurance Information</a></td>
			<td id="VendorComplianceLink" align="right" NOWRAP><a href="javascript: setOptions('VendorCompliance'); void(0);">View Compliance Information</a></td>
		</tr>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
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
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center width=100px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverageStatus", "Coverage")%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center width=120px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpires", "Date Expires", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-nameInsured", "Name Insured")%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-waiver", "Waiver Subrog.")%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-limit", "Insurance Limit", true)%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage1")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage1", "Coverage 1", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage1">
							<option value="A" <% if (vendorInsurance.getCoverage1().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage1().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage1().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage1().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage1().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage1().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires1(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named1); void(0);">
						<tsa:hidden name="VendorInsurance_named1" value="<%=vendorInsurance.getNamed1()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver1); void(0);">
						<tsa:hidden name="VendorInsurance_waiver1" value="<%=vendorInsurance.getNamed1()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit1" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit1(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage2")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage2", "Coverage 2", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage2">
							<option value="A" <% if (vendorInsurance.getCoverage2().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage2().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage2().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage2().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage2().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage2().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires2(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named2); void(0);">
						<tsa:hidden name="VendorInsurance_named2" value="<%=vendorInsurance.getNamed2()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver2); void(0);">
						<tsa:hidden name="VendorInsurance_waiver2" value="<%=vendorInsurance.getNamed2()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit2" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit2(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage3")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage3", "Coverage 3", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage3">
							<option value="A" <% if (vendorInsurance.getCoverage3().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage3().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage3().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage3().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage3().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage3().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires3(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named3); void(0);">
						<tsa:hidden name="VendorInsurance_named3" value="<%=vendorInsurance.getNamed1()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver3); void(0);">
						<tsa:hidden name="VendorInsurance_waiver3" value="<%=vendorInsurance.getNamed3()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit3" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit3(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage4")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage4", "Coverage 4", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage4">
							<option value="A" <% if (vendorInsurance.getCoverage4().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage4().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage4().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage4().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage4().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage4().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires4(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named4); void(0);">
						<tsa:hidden name="VendorInsurance_named4" value="<%=vendorInsurance.getNamed4()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver4); void(0);">
						<tsa:hidden name="VendorInsurance_waiver4" value="<%=vendorInsurance.getNamed4()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit4" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit4(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage5")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage5", "Coverage 5", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage5">
							<option value="A" <% if (vendorInsurance.getCoverage5().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage5().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage5().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage5().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage5().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage5().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires5(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named5); void(0);">
						<tsa:hidden name="VendorInsurance_named5" value="<%=vendorInsurance.getNamed5()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver5); void(0);">
						<tsa:hidden name="VendorInsurance_waiver5" value="<%=vendorInsurance.getNamed5()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit5" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit5(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage6")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage6", "Coverage 6", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsurance_coverage6">
							<option value="A" <% if (vendorInsurance.getCoverage6().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsurance.getCoverage6().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsurance.getCoverage6().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsurance.getCoverage6().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsurance.getCoverage6().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsurance.getCoverage6().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsurance_expires6" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getExpires6(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsurance_expires6', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getNamed6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_named6); void(0);">
						<tsa:hidden name="VendorInsurance_named6" value="<%=vendorInsurance.getNamed6()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsurance.getWaiver6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsurance_waiver6); void(0);">
						<tsa:hidden name="VendorInsurance_waiver6" value="<%=vendorInsurance.getNamed6()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsurance_limit6" value="<%=HiltonUtility.getFormattedPrice(vendorInsurance.getLimit6(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceContact")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceContact", "Insurance Contact", true)%>:</td>
					<td colspan=4><input type=text name="VendorInsurance_insuranceContact" value="<%=vendorInsurance.getInsuranceContact()%>" size="61" maxlength="30"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceComments")%>>
					<td align=right class=label valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceComments", "Insurance Comments", true)%>:</td>
					<td colspan=4><textarea name="VendorInsurance_insuranceNotes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(vendorInsurance.insuranceNotes)}</textarea></td>
				</tr>
				</table>
			</div>

			<div id="VendorCompliance" style="visiblity: hidden; display:none;">
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td>
						<table width=350px>
						<tr>
							<td align=center width=175px>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center width=120px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateCertified", "Date Certified", true)%></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=center width=100px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certificationStatus", "Status", true)%></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> align=center width=100px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpiration", "Date Expires", true)%></td>
							<td rowspan="6">
								<table width=200px cellpadding=1 cellspacing=0>
								<tr><td colspan="2"><br></td></tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR1")%>>
									<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR1", "Compliance UDF 1", true)%>:</td>
									<td><input type=text name="VendorInsurance_certUdf1" value="<%=vendorInsurance.getCertUdf1()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR2")%>>
									<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR2", "Compliance UDF 2", true)%>:</td>
									<td><input type=text name="VendorInsurance_certUdf2" value="<%=vendorInsurance.getCertUdf2()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR3")%>>
									<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR3", "Compliance UDF 3", true)%>:</td>
									<td><input type=text name="VendorInsurance_certUdf3" value="<%=vendorInsurance.getCertUdf3()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR4")%>>
									<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR4", "Compliance UDF 4", true)%>:</td>
									<td><input type=text name="VendorInsurance_certUdf4" value="<%=vendorInsurance.getCertUdf4()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR5")%>>
									<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR5", "Compliance UDF 5", true)%>:</td>
									<td><input type=text name="VendorInsurance_certUdf5" value="<%=vendorInsurance.getCertUdf5()%>"></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification1")%>>
							<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certification1", "Certification 1", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> nowrap>
								<input type=text name="VendorInsurance_certifiedDate1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate1(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_certifiedDate1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsurance_certStatus1">
									<option value="A" <% if (vendorInsurance.getCertStatus1().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus1().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus1().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus1().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus1().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus1().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> nowrap>
								<input type=text name="VendorInsurance_dateExpires1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getDateExpires1(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_dateExpires1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification2")%>>
							<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certification2", "Certification 2", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> nowrap>
								<input type=text name="VendorInsurance_certifiedDate2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate2(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_certifiedDate2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsurance_certStatus2">
									<option value="A" <% if (vendorInsurance.getCertStatus2().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus2().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus2().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus2().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus2().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus2().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> nowrap>
								<input type=text name="VendorInsurance_dateExpires2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getDateExpires2(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_dateExpires2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification3")%>>
							<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certification3", "Certification 3", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> nowrap>
								<input type=text name="VendorInsurance_certifiedDate3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate3(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_certifiedDate3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsurance_certStatus3">
									<option value="A" <% if (vendorInsurance.getCertStatus3().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus3().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus3().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus3().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus3().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus3().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> nowrap>
								<input type=text name="VendorInsurance_dateExpires3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getDateExpires3(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_dateExpires3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification4")%>>
							<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certification4", "Certification 4", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> nowrap>
								<input type=text name="VendorInsurance_certifiedDate4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate4(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_certifiedDate4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsurance_certStatus4">
									<option value="A" <% if (vendorInsurance.getCertStatus4().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus4().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus4().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus4().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus4().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus4().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> nowrap>
								<input type=text name="VendorInsurance_dateExpires4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getDateExpires4(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_dateExpires4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification5")%>>
							<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certification5", "Certification 5", true)%>:</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> nowrap>
								<input type=text name="VendorInsurance_certifiedDate5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate5(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_certifiedDate5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsurance_certStatus5">
									<option value="A" <% if (vendorInsurance.getCertStatus5().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus5().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus5().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus5().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus5().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus5().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateExpiration")%> nowrap>
								<input type=text name="VendorInsurance_dateExpires5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getDateExpires5(), oid, userDateFormat)%>" size=12>
								<a href="javascript: show_calendar('VendorInsurance_dateExpires5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceContact")%>>
							<td align=right class=label width=175px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-complianceContact", "Compliance Contact", true)%>:</td>
							<td colspan="3"><input type=text name="VendorInsurance_certContact" value="<%=vendorInsurance.getCertContact()%>" size="61" maxlength="30"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceNotes")%>>
							<td align=right class=label valign=top nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-complianceNotes", "Compliance Notes", true)%>:</td>
							<td colspan="3"><textarea name="VendorInsurance_complianceNotes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(vendorInsurance.complianceNotes)}</textarea></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</div>
	</td>
	<!--<td width=130px align=right valign=top>
		<hr size=0 width=120px>

		<table border=0 width=120px valign=top cellpadding=2 cellspacing=0 id=processSteps>
		<tr height=25px>
			<td><img id="VendorInsuranceProcessImg" src="<%=contextPath%>/images/step1on.gif" class="processOnImg" border=0></td>
			<td nowrap>
				<a href="javascript: setOptions('VendorInsurance'); void(0);" id="VendorInsuranceProcessLink" class=processOn>Insurance</a>
			</td>
		</tr>
		<tr height=25px>
			<td><img id="VendorComplianceProcessImg" src="<%=contextPath%>/images/step2off.gif" class="processOffImg" border=0></td>
			<td nowrap>
				<a href="javascript: setOptions('VendorCompliance'); void(0);" id="VendorComplianceProcessLink" class=processOff>Compliance</a>
			</td>
		</tr>
		<tr height=25px>
			<td><img id="VendorDocumentProcessImg" src="<%=contextPath%>/images/step3off.gif" class="processOffImg" border=0></td>
			<td nowrap>
				<a href="javascript: uploadDocs(); void(0);" id="VendorDocumentProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "attachments", "Attachments")%></a>
			</td>
		</tr>
		</table>

		<hr size=0 width=120px>
	</td>
	<td valign=top align="right"><%//@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>-->
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (role.getAccessRights("VEND") > 1) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/admin/supplier/supplier_contracts.jsp', '<%=updateHandler%>;ContractsRetrieveByVendorId'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contracts.jsp', 'ContractsRetrieveByVendorId'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var selectedType = "";
	var vendorId = frm.Vendor_vendorId.value;

	function thisLoad() {
		setOptions("VendorInsurance");

		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setOptions(type) {
		if (type != selectedType) {
			var myText = document.getElementById(type + "Link");


			myText.style.visibility = "hidden";
			myText.style.display = "none";

			if (selectedType.length > 0) {
				myText = document.getElementById(selectedType + "Link");

				myText.style.visibility = "visible";
				myText.style.display = "block";

				hideArea(selectedType);
			}

			displayArea(type);

			selectedType = type;
		}
	}

	function uploadDocs()
	{
	    var newInputField = "<input type=hidden name='VendorDocument_icRfqHeader' value=0>" +
	    	"<input type=hidden name='VendorDocument_vendorId' value=" + vendorId + ">" +
	    	"<input type=hidden name=allowEdit value=Y>" +
	    	"<input type=hidden name=returnPage value='/admin/supplier/supplier_ins_contract.jsp'>" +
	    	"<input type=hidden name=returnHandler value='VendorInsuranceRetrieveById'>";
	    setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_attachments.jsp', '<%=updateHandler%>;VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
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

	function submitThis(page, handlers)
	{
		var messageInsurance = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverageStatus", "Coverage")%> is Approved";
		var messageCompliance = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-certificationStatus", "Status")%> is Approved";
		if (selectedType == "VendorInsurance") {
			messageInsurance = "If " + messageInsurance;
			messageCompliance ="In Compliance Information, if " + messageCompliance;
		} else {
			messageCompliance = "If " + messageCompliance;
			messageInsurance ="In Insurance Information, if " + messageInsurance;
		}

		for (var i = 1; i <= 7; i++)
		{
			var vendorInsuranceCoverage = document.getElementById("VendorInsurance_coverage" + i);
			var vendorInsuranceExpires = document.getElementById("VendorInsurance_expires" + i);
			var vendorInsuranceLimit = document.getElementById("VendorInsurance_limit" + i);

			var vendorInsuranceCertStatus = document.getElementById("VendorInsurance_certStatus" + i);
			var vendorInsuranceDateExpires = document.getElementById("VendorInsurance_dateExpires" + i);

			if (vendorInsuranceCoverage && vendorInsuranceCoverage.value == "A" &&
				vendorInsuranceCoverage.parentNode.parentNode.style.display == "" &&
				vendorInsuranceExpires && (vendorInsuranceExpires.value == "" || !chkdate(vendorInsuranceExpires)))
			{
				alert(messageInsurance + ", you must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpires", "Date Expires")%>");
				if (selectedType == "VendorInsurance") {
					vendorInsuranceExpires.focus();
				}
				return false;
			}

			if (vendorInsuranceCoverage && vendorInsuranceCoverage.value == "A" &&
				vendorInsuranceCoverage.parentNode.parentNode.style.display == "" &&
				vendorInsuranceLimit && vendorInsuranceLimit.value <= 0)
			{
				alert(messageInsurance + ", the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-limit", "Insurance Limit")%> should be more than 0");
				if (selectedType == "VendorInsurance") {
					vendorInsuranceLimit.focus();
				}
				return false;
			}

			if (vendorInsuranceCertStatus && vendorInsuranceCertStatus.value == "A" &&
				vendorInsuranceCertStatus.parentNode.parentNode.style.display == "" &&
				vendorInsuranceDateExpires && (vendorInsuranceDateExpires.value == "" || !chkdate(vendorInsuranceDateExpires)))
			{
				alert(messageCompliance + ", you must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpiration", "Date Expires")%>");
				if (selectedType == "VendorCompliance") {
					vendorInsuranceDateExpires.focus();
				}
				return false;
			}
		}

		doSubmit(page, handlers);
	}

// End Hide script -->
</SCRIPT>