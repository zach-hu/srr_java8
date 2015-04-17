<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	VendorInsuranceDefault vendorInsurance = (VendorInsuranceDefault) request.getAttribute("vendorInsuranceDefault");
	Vendor vendor = (Vendor) request.getAttribute("vendor");
	String	vendorId = (String) request.getAttribute("Vendor_vendorId");
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));

	String updateHandler = "VendorInsuranceDefaultUpdate";
	if (vendorInsurance == null)	{
		vendorInsurance = new VendorInsuranceDefault();
		vendorInsurance.setVendorId(vendorId);
		vendorInsurance.setCertStatus1("P");
		vendorInsurance.setCertStatus2("P");
		vendorInsurance.setCertStatus3("P");
		vendorInsurance.setCertStatus4("P");
		vendorInsurance.setCertStatus5("P");
		vendorInsurance.setCertStatus6("P");

		updateHandler = "VendorInsuranceDefaultAdd";
	}

	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	List contractsList = (List) request.getAttribute("contractsList");

	String contactName = "";
	String contactType = "";
	Contact contact = (Contact) request.getAttribute("contact");
	if (contact != null)
	{
		contactName = contact.getFirstName() + " " + contact.getLastName();
		contactType = contact.getComp_id().getContactType();
	}

	List certStatusList = new ArrayList();
	List certOriginalStatusList = new ArrayList();
	List certStatusList_1 = new ArrayList();
	List certStatusList_2 = new ArrayList();
	List certStatusList_3 = new ArrayList();
	String certStatus = "";

	String	s_current_process = "COMPLIANCES";
	String	s_current_page = "/admin/supplier/supplier_compliances.jsp";
	String	s_current_method = updateHandler;
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorInsuranceDefault_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAccount_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value=""/>
<tsa:hidden name="VendorInsurance_contNumber" value=""/>
<tsa:hidden name="Address_addressCode" value=""/>
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_compliances.jsp"/>
<tsa:hidden name="pageFrom" value="supplier_compliances"/>

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%>&nbsp;</div>
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

<table border="0" cellpadding="0" cellspacing="2" width="<%=formEntryWidth%>">
<tr>
	<td>
	<table>
	<tr>
	<td width="680px" align="center" valign="top">
		<table border=0 cellpadding=2 cellspacing=0>
			<tr <%=HtmlWriter.isVisible(oid, "sup-complianceContactLink")%>>
				<td align=right class=label>
					<a href="javascript: viewContact(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-complianceContactLink", "Compliance Contact", true)%>:</a>
				</td>
				<td align=left colspan=4>&nbsp;
				<%	if (!HiltonUtility.isEmpty(contactName)) { %>
					<%=contactName%>
				<%	} else { %>
					No <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-complianceContactLink", "Compliance Contact", true)%>
				<%	} %>
				</td>
			</tr>
		</table>
		<br>
		<table border=1 cellspacing=0 cellpadding=0 width="500px" class=browseHdr align="center">
		<tr>
			<td align="center">
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contract", "Contract #")%></b></td>
					<!--<td width=15% height=18px class=browseHdr>&nbsp;<b>Type</b></td>-->
					<td width="20%" height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b>Effective Date</b></td>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b>Expiration Date</b></td>
					<td width="15%" height=18px class=browseHdr align="right">&nbsp;<b>Amount</b></td>
					<!--<td width=15% height=18px class=browseHdr>&nbsp;<b>Requestor</b></td>-->
					<td width="5%" height=18px class=browseHdr>&nbsp;</td>
				</tr>
				</table>
			</td>
	    </tr>
		<tr>
			<td>
				<div id="Contracts">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (contractsList != null && contractsList.size() > 0)
		{
			for (int i = 0; i < contractsList.size(); i++)
			{
				PoHeader poHeader = (PoHeader) contractsList.get(i);
				String	s_buyer_code = poHeader.getBuyerCode();
				UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
%>
				<tr>
					<td width="20%"><a href="javascript: selectMe('<%=poHeader.getIcPoHeader()%>','<%=poHeader.getPoNumber()%>'); void(0);"><%=poHeader.getPoNumber()%></a></td>
					<!--<td width="15%"><%=OrderType.toString(poHeader.getPoType())%></td>-->
					<td width="20%"><%=DocumentStatus.toString(poHeader.getStatus())%></td>
					<td width="20%"><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%></td>
					<td width="20%"><%=HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), oid, userDateFormat)%></td>
					<td width="15%" align="right">$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></td>
					<!--<td width="15%"><%=buyer.getDisplayName()%></td>-->
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6"><b>Description:</b>&nbsp;<%=poHeader.getInternalComments()%><br><br></td>
				</tr>
<%		}
		}
		else
		{
%>
				<tr><td class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-nocontractsmsg", "There are no contracts associated with this supplier.")%></td></tr>
<%	} %>
				</table>
				</div>
			</td>
		</tr>
		</table>
		</td>
	</tr>

	<tr height=50px>&nbsp;</tr>

	<tr>
	<td>
	<table border=0 cellpadding=0 cellspacing=0 align="center" width="500px">
	<tr>
		<td valign=top>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
						<tr>
							<td align=center width=175px>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center class=label><tsa:label labelName="sup-dateCertified" defaultString="Date Certified" checkRequired="true" /></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> align=left class=label><tsa:label labelName="sup-certificationStatus" defaultString="Status" checkRequired="true" /></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification1")%>>
							<td align=right class=label nowrap><div id="certification_1"><tsa:label labelName="sup-certification1" defaultString="Certification 1" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_1">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate1" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate1(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate1', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus1">
									<option value="A" <% if (vendorInsurance.getCertStatus1().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus1().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus1().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus1().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus1().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus1().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR1")%> align=right class=label nowrap><div id="udf_1"><tsa:label labelName="sup-SCR1" defaultString="UDF 1" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR1")%>><div id="certUDF_1"><tsa:input type="text" name="VendorInsuranceDefault_certUdf1" value="<%=vendorInsurance.getCertUdf1()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification2")%>>
							<td align=right class=label><div id="certification_2"><tsa:label labelName="sup-certification2" defaultString="Certification 2" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_2">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate2" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate2(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate2', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus2">
									<option value="A" <% if (vendorInsurance.getCertStatus2().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus2().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus2().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus2().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus2().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus2().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR2")%> align=right class=label nowrap><div id="udf_2"><tsa:label labelName="sup-SCR2" defaultString="UDF 2" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR2")%>><div id="certUDF_2"><tsa:input type="text" name="VendorInsuranceDefault_certUdf2" value="<%=vendorInsurance.getCertUdf2()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification3")%>>
							<td align=right class=label><div id="certification_3"><tsa:label labelName="sup-certification3" defaultString="Certification 3" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_3">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate3" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate3(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate3', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus3">
									<option value="A" <% if (vendorInsurance.getCertStatus3().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus3().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus3().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus3().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus3().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus3().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR3")%> align=right class=label nowrap><div id="udf_3"><tsa:label labelName="sup-SCR3" defaultString="UDF 3" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR3")%>><div id="certUDF_3"><tsa:input type="text" name="VendorInsuranceDefault_certUdf3" value="<%=vendorInsurance.getCertUdf3()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification4")%>>
							<td align=right class=label><div id="certification_4"><tsa:label labelName="sup-certification4" defaultString="Certification 4" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_4">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate4" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate4(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate4', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus4">
									<option value="A" <% if (vendorInsurance.getCertStatus4().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus4().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus4().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus4().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus4().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus4().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR4")%> align=right class=label nowrap><div id="udf_4"><tsa:label labelName="sup-SCR4" defaultString="UDF 4" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR4")%>><div id="certUDF_4"><tsa:input type="text" name="VendorInsuranceDefault_certUdf4" value="<%=vendorInsurance.getCertUdf4()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification5")%>>
							<td align=right class=label><div id="certification_5"><tsa:label labelName="sup-certification5" defaultString="Certification 5" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_5">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate5" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate5(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate5', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus5">
									<option value="A" <% if (vendorInsurance.getCertStatus5().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus5().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus5().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus5().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus5().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus5().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR5")%> align=right class=label nowrap><div id="udf_5"><tsa:label labelName="sup-SCR5" defaultString="UDF 5" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR5")%>><div id="certUDF_5"><tsa:input type="text" name="VendorInsuranceDefault_certUdf5" value="<%=vendorInsurance.getCertUdf5()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-certification6")%>>
							<td align=right class=label nowrap><div id="certification_6"><tsa:label labelName="sup-certification6" defaultString="Certification 6" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-dateCertified")%> align=center nowrap>
								<div id="certifiedDate_6">
									<tsa:input type="text" name="VendorInsuranceDefault_certifiedDate6" value="<%=HiltonUtility.getFormattedDate(vendorInsurance.getCertifiedDate6(), oid, userDateFormat)%>" size="12" />
									<a href="javascript: show_calendar('VendorInsuranceDefault_certifiedDate6', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</div>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-certificationStatus")%> nowrap>
								<select name="VendorInsuranceDefault_certStatus6">
									<option value="A" <% if (vendorInsurance.getCertStatus6().equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (vendorInsurance.getCertStatus6().equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (vendorInsurance.getCertStatus6().equals("U")) {%>selected<%}%>>Denied</option>
									<option value="W" <% if (vendorInsurance.getCertStatus6().equals("W")) {%>selected<%}%>>Waived</option>
									<option value="I" <% if (vendorInsurance.getCertStatus6().equals("I")) {%>selected<%}%>>Improvement Plan</option>
									<option value="N" <% if (vendorInsurance.getCertStatus6().equals("N")) {%>selected<%}%>>NA</option>
								</select>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR6")%> align=right class=label nowrap><div id="udf_6"><tsa:label labelName="sup-SCR6" defaultString="UDF 6" checkRequired="true" />:</div></td>
							<td <%=HtmlWriter.isVisible(oid, "sup-SCR6")%>><div id="certUDF_6"><tsa:input type="text" name="VendorInsuranceDefault_certUdf6" value="<%=vendorInsurance.getCertUdf6()%>" maxLength="15" /></div></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceContact")%>>
							<td align=right class=label width=175px><tsa:label labelName="sup-complianceContact" defaultString="Compliance Contact" checkRequired="true" />:</td>
							<td colspan=4><tsa:input type="text" name="VendorInsuranceDefault_complianceContact" value="<%=contactName%>" size="61" /></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "sup-complianceNotes")%>>
							<td align=right class=label valign=top><tsa:label labelName="sup-complianceNotes" defaultString="Compliance Notes" checkRequired="true" />:</td>
							<td colspan=4>
								<tsa:input type="textarea" name="VendorInsuranceDefault_complianceNotes" rows="6" cols="64" wrap="nonvirtual" maxLength="60" onkeydown="textCounter(this, 60);" onkeyup="textCounter(this,60);" onchange="textCounter(this,60);">${esapi:encodeForHTML(vendorInsuranceDefault.complianceNotes)}</tsa:input>
							</td>
						</tr>
						</table>
				</td>

			</tr>
		</table>
		</td>
		</tr>
	</table>
	</td>
	<td valign=top align="left"><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
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

	function selectMe(icPoHeader, contnumber)
	{
		frm.VendorInsurance_icPoHeader.value = icPoHeader;
		frm.VendorInsurance_contNumber.value = contnumber;
		doSubmit('/admin/supplier/supplier_compliance.jsp', 'VendorInsuranceRetrieveById');
	}

	function validateForm()
	{
		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name=\"PurchaseHistory_vendorId\" value=\"${esapi:encodeForJavaScript(Vendor_vendorId)}\"></input>";
			<%--var newInputField = "<tsa:hidden name='PurchaseHistory_vendorId' value='${esapi:encodeForJavaScript(Vendor_vendorId)}'/>";--%>
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		return true;
	}

	function viewContact()
	{
		doSubmit('/admin/supplier/supplier_contact.jsp','ContactComplianceRetrieveByVendorId');
	}

// End Hide script -->
</SCRIPT>