<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="com.tsa.puridiom.pomanager.PoManager" %>
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
		vendorInsurance.setCertStatus1("P");
		vendorInsurance.setCertStatus2("P");
		vendorInsurance.setCertStatus3("P");
		vendorInsurance.setCertStatus4("P");
		vendorInsurance.setCertStatus5("P");
		vendorInsurance.setCertStatus6("P");
		vendorInsurance.setCertStatus7("P");

		updateHandler = "VendorInsuranceAdd";
	}

	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	String	s_current_process = "COMPLIANCES";
	String	s_current_page = "/admin/supplier/supplier_compliance.jsp";
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
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_compliance.jsp"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Compliance</div>
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
			 <td align=left width="40%"><%=contNumber%></td>
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
<%@ include file="/admin/admin_info.jsp" %>
<br>
<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
			<div id="VendorCompliance">
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
									<td align=right class=label nowrap>
									<%	if (DictionaryManager.isLink(oid, "sup-SCR1")) { %>
									<a href="javascript: browseStd('VendorInsurance_certUdf1', 'SCR1'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR1", "Compliance UDF 1", true)%>:</a>
									<%	} else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR1", "Compliance UDF 1", true)%>:
									<%	} %>
									</td>
									<td><input type=text name="VendorInsurance_certUdf1" value="<%=vendorInsurance.getCertUdf1()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR2")%>>
									<td align=right class=label nowrap>
									<%	if (DictionaryManager.isLink(oid, "sup-SCR2")) { %>
									<a href="javascript: browseStd('VendorInsurance_certUdf2', 'SCR2'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR2", "Compliance UDF 2", true)%>:</a>
									<%	} else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR2", "Compliance UDF 2", true)%>:
									<%	} %>
									</td>
									<td><input type=text name="VendorInsurance_certUdf2" value="<%=vendorInsurance.getCertUdf2()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR3")%>>
									<td align=right class=label nowrap>
									<%	if (DictionaryManager.isLink(oid, "sup-SCR3")) { %>
									<a href="javascript: browseStd('VendorInsurance_certUdf3', 'SCR3'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR3", "Compliance UDF 3", true)%>:</a>
									<%	} else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR3", "Compliance UDF 3", true)%>:
									<%	} %>
									</td>
									<td><input type=text name="VendorInsurance_certUdf3" value="<%=vendorInsurance.getCertUdf3()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR4")%>>
									<td align=right class=label nowrap>
									<%	if (DictionaryManager.isLink(oid, "sup-SCR4")) { %>
									<a href="javascript: browseStd('VendorInsurance_certUdf4', 'SCR4'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR4", "Compliance UDF 4", true)%>:</a>
									<%	} else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR4", "Compliance UDF 4", true)%>:
									<%	} %>
									</td>
									<td><input type=text name="VendorInsurance_certUdf4" value="<%=vendorInsurance.getCertUdf4()%>"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "sup-SCR5")%>>
									<td align=right class=label nowrap>
									<%	if (DictionaryManager.isLink(oid, "sup-SCR5")) { %>
									<a href="javascript: browseStd('VendorInsurance_certUdf5', 'SCR5'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR5", "Compliance UDF 5", true)%>:</a>
									<%	} else { %>
									<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-SCR5", "Compliance UDF 5", true)%>:
									<%	} %>
									</td>
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
	<td valign=top align="right"><%@ include file="/admin/supplier/compliance_sidebar.jsp" %></td>
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

	var selectedType = "";

	function thisLoad() {
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function uploadDocs()
	{
	    var newInputField = "<input type=hidden name='VendorDocument_icRfqHeader' value=0>" +
	    	"<input type=hidden name='VendorDocument_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}>" +
	    	"<input type=hidden name=allowEdit value=Y>" +
	    	"<input type=hidden name=returnPage value='/admin/supplier/supplier_compliance.jsp'>" +
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
		//Add validations
		doSubmit(page, handlers);
	}

// End Hide script -->
</SCRIPT>