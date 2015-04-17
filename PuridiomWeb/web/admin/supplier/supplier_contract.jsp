<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String poNumber = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_poNumber"));

	InsCategoryLevel insCategoryLevel = (InsCategoryLevel) request.getAttribute("insCategoryLevel");
	if (insCategoryLevel == null) {
		insCategoryLevel = new InsCategoryLevel();
	}

	String messageValidation1 = "<label class='error'>Failed</label>";
	String messageValidation2 = "<label class='error'>Failed</label>";
	String messageValidation3 = "<label class='error'>Failed</label>";
	String messageValidation4 = "<label class='error'>Failed</label>";
	String messageValidation5 = "<label class='error'>Failed</label>";
	String messageValidation6 = "<label class='error'>Failed</label>";

	VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) request.getAttribute("vendorInsuranceDefault");
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
%>

<tsa:hidden name="Vendor_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendorName%>"/>
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_contract.jsp"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Contract Insurance</div>
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
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="60%"><b>Supplier:</b>&nbsp;</td>
			 <td align=left width="40%"><%=vendorName%></td>
		</tr>
		<tr>
			 <td align=right width="60%"><b>Contract:&nbsp;</b></td>
			 <td align=left width="40%"><%=poNumber%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (vendorInsuranceDefault.getInsuranceOverride().equalsIgnoreCase("Y")) { %>
<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
  <td width=100% align=center class=error>INSURANCE OVERRIDE ON</td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=50% align=center>
		<tr <%=HtmlWriter.isVisible(oid, "sup-contractInsuranceLevel")%>>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-contractInsuranceLevel", "Contract Insurance Level", true)%>:&nbsp;</td>
			<td><input type="text" name="InsCategoryLevel_iclLevel" value="<%=insCategoryLevel.getIclLevel()%>" size=10 maxlength=10></td>
		</tr>
		</table>

		<br>

		<table border=0 cellpadding=2 cellspacing=0 width=800px align=center>
		<tr>
			<td align=center width=25%>&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center width=5% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-iclrequired", "Required")%></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center width=20% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-icllimit", "Minimal Dollar Limit", true)%></td>
			<td align=center width=10%>&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-coverageStatus")%> align=center width=10% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverageStatus", "Coverage", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-dateExpires")%> align=center width=10% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-dateExpires", "Expiration Date", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center width=10% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-limit", "Insurance Limit", true)%></td>
			<td align=center width=10%>&nbsp;</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage1")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage1", "Coverage 1", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired1); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired1" value="<%=insCategoryLevel.getIclRequired1()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum1" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum1(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires1" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires1(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit1" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit1(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation1%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage2")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage2", "Coverage 2", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired2); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired2" value="<%=insCategoryLevel.getIclRequired2()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum2" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum2(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires2" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires2(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit2" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit2(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation2%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage3")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage3", "Coverage 3", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired3); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired3" value="<%=insCategoryLevel.getIclRequired3()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum3" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum3(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires3" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires3(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit3" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit3(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation3%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage4")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage4", "Coverage 4", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired4); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired4" value="<%=insCategoryLevel.getIclRequired4()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum4" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum4(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires4" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires4(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit4" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit4(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation4%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage5")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage5", "Coverage 5", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired5); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired5" value="<%=insCategoryLevel.getIclRequired5()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum5" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum5(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires5" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires5(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit5" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit5(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation5%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage6")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage6", "Coverage 6", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired6); void(0);">
				<tsa:hidden name="InsCategoryLevel_iclRequired6" value="<%=insCategoryLevel.getIclRequired6()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum6" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum6(), oid)%>" style="text-align:right" onchange="addUp(this);" size=20>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<img src="<%=contextPath%>/images/next.gif" border="0">
			</td>
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
				<input type=text name="VendorInsuranceDefault_expires6" value="<%=HiltonUtility.getFormattedDate(vendorInsuranceDefault.getExpires6(), oid, userDateFormat)%>" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="VendorInsuranceDefault_limit6" value="<%=HiltonUtility.getFormattedPrice(vendorInsuranceDefault.getLimit6(), oid)%>" style="text-align:right" onchange="addUp(this);" size=15>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-validate")%> align=center><%=messageValidation6%></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contracts.jsp', 'ContractsRetrieveByVendorId'); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function setCheckbox(fld, x)
	{
		fld.value = 'N';
		if ( frm.c_checkbox[x].checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function setFlagFromCkbox(ckbox, fld)
	{
		if (ckbox.checked) {
			fld.value = "Y";
		} else {
			fld.value = "N";
		}
	}

	function addUp(field, decimals)
	{
		var price_dec = decimals;
		if (price_dec == undefined) {
			price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
		}
		var p = nformat(eval(nfilter(field)), price_dec);
		field.value = p;
	}

// End Hide script -->
</SCRIPT>