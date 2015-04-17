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

	VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) request.getAttribute("vendorInsuranceDefault");
	Vendor vendor = (Vendor) request.getAttribute("vendor");

	String vendorId = (String) request.getAttribute("Vendor_vendorId");
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));

	String updateHandler = "VendorInsuranceDefaultUpdate";
	if (vendorInsuranceDefault == null)	{
		vendorInsuranceDefault = new VendorInsuranceDefault();
		vendorInsuranceDefault.setVendorId(vendorId);
		vendorInsuranceDefault.setCoverage1("P");
		vendorInsuranceDefault.setCoverage2("P");
		vendorInsuranceDefault.setCoverage3("P");
		vendorInsuranceDefault.setCoverage4("P");
		vendorInsuranceDefault.setCoverage5("P");
		vendorInsuranceDefault.setCoverage6("P");
		updateHandler = "VendorInsuranceDefaultAdd";
	}

	String iclLevel = "0";
	if (vendor != null)	{
		iclLevel = vendor.getIclLevel().toString();
	}

	String contactName = "";
	String contactType = "";
	Contact contact = (Contact) request.getAttribute("contact");
	if (contact != null)
	{
		contactName = contact.getFirstName() + " " + contact.getLastName();
		contactType = contact.getComp_id().getContactType();
	}

	String messageValidation1 = "<label class='error'>Failed</label>";
	String messageValidation2 = "<label class='error'>Failed</label>";
	String messageValidation3 = "<label class='error'>Failed</label>";
	String messageValidation4 = "<label class='error'>Failed</label>";
	String messageValidation5 = "<label class='error'>Failed</label>";
	String messageValidation6 = "<label class='error'>Failed</label>";

	if (vendorInsuranceDefault == null) {
		vendorInsuranceDefault = new VendorInsuranceDefault();
	}
	else
	{
		if (vendorInsuranceDefault.getInsuranceOverride().equalsIgnoreCase("Y"))
		{
			messageValidation1 = "<label class='success'>Passed</label>";
			messageValidation2 = "<label class='success'>Passed</label>";
			messageValidation3 = "<label class='success'>Passed</label>";
			messageValidation4 = "<label class='success'>Passed</label>";
			messageValidation5 = "<label class='success'>Passed</label>";
			messageValidation6 = "<label class='success'>Passed</label>";
		}
		else
		{
			String[] vendorInsuranceDefaultResults = (String[]) request.getAttribute("vendorInsuranceDefaultResults");
			if (vendorInsuranceDefaultResults != null && vendorInsuranceDefaultResults.length == 6)
			{
				if (vendorInsuranceDefaultResults[0].equalsIgnoreCase("Y")) { messageValidation1 = "<label class='success'>Passed</label>"; }
				if (vendorInsuranceDefaultResults[1].equalsIgnoreCase("Y")) { messageValidation2 = "<label class='success'>Passed</label>"; }
				if (vendorInsuranceDefaultResults[2].equalsIgnoreCase("Y")) { messageValidation3 = "<label class='success'>Passed</label>"; }
				if (vendorInsuranceDefaultResults[3].equalsIgnoreCase("Y")) { messageValidation4 = "<label class='success'>Passed</label>"; }
				if (vendorInsuranceDefaultResults[4].equalsIgnoreCase("Y")) { messageValidation5 = "<label class='success'>Passed</label>"; }
				if (vendorInsuranceDefaultResults[5].equalsIgnoreCase("Y")) { messageValidation6 = "<label class='success'>Passed</label>"; }
			}
		}
	}

	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	String	s_current_process = "INSURANCE";
	String	s_current_page = "/admin/supplier/supplier_insurance.jsp";
	String	s_current_method = updateHandler;
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorInsuranceDefault_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="pageFrom" value="supplier_insurance"/>

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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
			<div id="VendorInsurance">
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td colspan="3" align=center class=label>
						<a href="javascript: viewInsCategoryLevel(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-iclLevel", "Insurance Category Level", true)%></a>
						<input type=text name="as_iclLevel" value="<%=iclLevel%>" size="5" disabled>
					</td>
					<td colspan="4" align=left class=label>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceOverride", "Insurance Override", true)%>
						<input type=checkbox name="ckboxInsuranceOverride" value="Y"<% if (vendorInsuranceDefault.getInsuranceOverride().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_insuranceOverride); setReadOnly(this); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_insuranceOverride" value="<%=vendorInsuranceDefault.getInsuranceOverride()%>"/>
					</td>
				</tr>
				<tr>
					<td colspan="7" align=center class=label>&nbsp;</td>
				</tr>
				<tr>
					<td align=center width=180px>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center width=100px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverageStatus", "Coverage", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center width=120px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpires", "Expiration Date", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-nameInsured", "Name Insured", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-waiver", "Waiver Subrog.", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-limit", "Insurance Limit", true)%></td>
					<td align=center width=10%>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage1")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage1", "Coverage 1", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage1">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage1().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage1().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage1().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage1().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage1().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage1().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires1" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires1(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named1" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed1().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed1().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named1); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named1" value="<%=vendorInsuranceDefault.getNamed1()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver1); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver1" value="<%=vendorInsuranceDefault.getWaiver1()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit1" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit1(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation1%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage2")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage2", "Coverage 2", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage2">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage2().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage2().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage2().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage2().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage2().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage2().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires2" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires2(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named2" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed2().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed2().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named2); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named2" value="<%=vendorInsuranceDefault.getNamed2()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver2); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver2" value="<%=vendorInsuranceDefault.getWaiver2()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit2" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit2(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation2%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage3")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage3", "Coverage 3", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage3">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage3().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage3().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage3().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage3().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage3().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage3().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires3" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires3(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named3" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed3().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed3().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named3); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named3" value="<%=vendorInsuranceDefault.getNamed3()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver3); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver3" value="<%=vendorInsuranceDefault.getWaiver3()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit3" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit3(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation3%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage4")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage4", "Coverage 4", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage4">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage4().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage4().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage4().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage4().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage4().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage4().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires4" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires4(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named4" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed4().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed4().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named4); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named4" value="<%=vendorInsuranceDefault.getNamed4()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver4); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver4" value="<%=vendorInsuranceDefault.getWaiver4()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit4" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit4(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation4%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage5")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage5", "Coverage 5", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage5">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage5().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage5().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage5().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage5().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage5().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage5().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires5" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires5(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named5" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed5().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed5().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named5); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named5" value="<%=vendorInsuranceDefault.getNamed5()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver5); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver5" value="<%=vendorInsuranceDefault.getWaiver5()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit5" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit5(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation5%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-coverage6")%>>
					<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage6", "Coverage 6", true)%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center>
						<select name="VendorInsuranceDefault_coverage6">
							<option value="A" <% if (vendorInsuranceDefault.getCoverage6().equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (vendorInsuranceDefault.getCoverage6().equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (vendorInsuranceDefault.getCoverage6().equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (vendorInsuranceDefault.getCoverage6().equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (vendorInsuranceDefault.getCoverage6().equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (vendorInsuranceDefault.getCoverage6().equals("W")) {%>selected<%}%>>Waived</option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center>
						<input type=text name="VendorInsuranceDefault_expires6" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires6(), oid, userDateFormat)%>" size=12>
						<a href="javascript: show_calendar('VendorInsuranceDefault_expires6', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
			<% if (oid.equalsIgnoreCase("bsc04p")) {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<select name="VendorInsuranceDefault_named6" size=1>
							<option value=""> </option>
							<option <% if (vendorInsuranceDefault.getNamed6().equals("N")) {%>selected<%}%> value="N">No</option>
							<option	<% if (vendorInsuranceDefault.getNamed6().equals("Y")) {%>selected<%}%> value="Y">Yes</option>
						</select>
					</td>
			<%} else {%>
					<td <%=HtmlWriter.isVisible(oid, "sup-nameInsured")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getNamed6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_named6); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_named6" value="<%=vendorInsuranceDefault.getNamed6()%>"/>
					</td>
			<% } %>
					<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
						<input type=checkbox name="ckbox" value="Y"<% if (vendorInsuranceDefault.getWaiver6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.VendorInsuranceDefault_waiver6); void(0);">
						<tsa:hidden name="VendorInsuranceDefault_waiver6" value="<%=vendorInsuranceDefault.getWaiver6()%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
						<input type=text name="VendorInsuranceDefault_limit6" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit6(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation6%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceContact")%>>
					<td align=right class=label>
						<a href="javascript: viewContact(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceContact", "Insurance Contact", true)%>:</a>
					</td>
					<td align=left colspan=4>&nbsp;
					<%	if (!HiltonUtility.isEmpty(contactName)) { %>
						<%=contactName%>
						<tsa:hidden name="VendorInsuranceDefault_insuranceContact" value="<%=contactType%>"/>
					<%	} else { %>
						No <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceContact", "Insurance Contact", true)%>
					<%	} %>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sup-insuranceComments")%>>
					<td align=right class=label valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceComments", "Insurance Comments")%>:</td>
					<td align=center colspan=4>
						<textarea title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-insuranceCommentsHelper", "")%>" name="VendorInsuranceDefault_insuranceNotes" rows="6" cols="64" wrap="nonvirtual" maxlength=60 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this,60);" onchange="textCounter(this,60);">${esapi:encodeForHTML(vendorInsuranceDefault.insuranceNotes)}</textarea>
					</td>
				</tr>
				</table>
			</div>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
		if (frm.VendorInsuranceDefault_insuranceOverride.value == "Y")
		{
			checkInputSettings();
			allowInputEdit(frm.ckboxInsuranceOverride, true);
		}
	}

	function setFlagFromCkbox(ckbox, fld) {
		if (ckbox.checked) {
			fld.value = "Y";
		} else {
			fld.value = "N";
		}
	}

	function setReadOnly(ckbox) {
		if (ckbox.checked) {
			checkInputSettings();
			allowInputEdit(frm.ckboxInsuranceOverride, true);
		} else {
			checkInputSettingsEdit();
		}
	}

	function addUp(field)
	{
		var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		var p = nformat(eval(nfilter(field)), price_dec);
		field.value = p;
	}

	function validateForm()
	{
		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}/>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		return true;
	}

	function viewContact()
	{
		doSubmit('/admin/supplier/supplier_contact.jsp','VendorInsuranceDefaultUpdate;ContactInsuranceRetrieveByVendorId');
	}

	function viewInsCategoryLevel()
	{
		popupParameters = "InsCategoryLevel_iclLevel=<%=iclLevel%>";
		doSubmitToPopup('/admin/supplier/supplier_inscategorylevel.jsp', 'InsCategoryLevelRetrieveByIclLevel', 'WIDTH=680px', 'HEIGHT=500px');
	}

// End Hide script -->
</SCRIPT>
