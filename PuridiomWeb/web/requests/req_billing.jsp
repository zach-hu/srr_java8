<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<SCRIPT SRC="<%=contextPath%>/scripts/req-audit.js"></SCRIPT>
<%
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	BigDecimal bd_ic_req_header = requisitionHeader.getIcReqHeader();
	String	s_req_number = requisitionHeader.getRequisitionNumber();
	String	s_req_type = requisitionHeader.getRequisitionType();
	String	s_req_subtype = requisitionHeader.getRqSubType();
	String	s_fiscal_year = requisitionHeader.getFiscalYear();
	String	s_req_status = requisitionHeader.getStatus();
	String	s_bill_to_code = requisitionHeader.getBillToCode();
	String	s_bill_to_contact = requisitionHeader.getBillToContact();

	String	s_fob_code = requisitionHeader.getFobCode();
	String vendorId = (String) requisitionHeader.getVendorId();
	String isDisable = "";
	if(s_req_type.equalsIgnoreCase("C")){
		isDisable="DISABLED";
	}

	if( HiltonUtility.isEmpty(s_fob_code) && s_req_type.equalsIgnoreCase("C") && oid.equalsIgnoreCase("BLY07P"))
	{
	    if (!HiltonUtility.isEmpty(vendorId) ) {
    		Object vendor = VendorManager.getInstance().getVendor(oid, vendorId);
    		if( vendor != null  && vendor instanceof Vendor)
    		{
    			s_fob_code= (String) ( ((Vendor)vendor).getFobId());
    		}
    	}
	}
	String	s_pcardNumber = (String) HiltonUtility.ckNull(requisitionHeader.getPcardNumber());
	String	s_subPcardNumber = "";
	String	s_pcardHolder = (String) HiltonUtility.ckNull(requisitionHeader.getPcardHolder());
	if (s_pcardNumber.length() > 4) {
		s_subPcardNumber = s_pcardNumber.substring(s_pcardNumber.length() - 4, s_pcardNumber.length());
	} else {
		s_subPcardNumber = s_pcardNumber;
	}
	if (s_subPcardNumber.length() > 0) {
		s_subPcardNumber = "************" + s_subPcardNumber;
	}
	String	s_expYear = "";
	String	s_expMonth = "";
	if (requisitionHeader.getPcardExpires().length() > 6) {
		s_expYear = requisitionHeader.getPcardExpires().substring(3,7);
		s_expMonth = requisitionHeader.getPcardExpires().substring(0,3);
	}

	String	user_pcardNumber = (String) HiltonUtility.ckNull(user.getpCardNumber());
	String	user_pcardHolder = (String) HiltonUtility.ckNull(user.getpCardHolder());
	String	user_subPcardNumber = "";

	if (user_pcardNumber.length() > 4) {
		user_subPcardNumber = user_pcardNumber.substring(user_pcardNumber.length() - 4, user_pcardNumber.length());
	} else {
		user_subPcardNumber = user_pcardNumber;
	}
	if (user_subPcardNumber.length() > 0) {
		user_subPcardNumber = "************" + user_subPcardNumber;
	}
	String	user_expYear = "";
	String	user_expMonth = "";
	if (user.getpCardExpires() != null && user.getpCardExpires().length() > 6) {
		user_expYear = user.getpCardExpires().substring(3,7);
		user_expMonth = user.getpCardExpires().substring(0,3);
	}
	String user_pCardType = user.getpCardType();

	Address billTo = (Address) requisitionHeader.getBillToAddress();
	if (billTo == null)
	{
		billTo = new Address();
	}

	if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}
	String	s_current_process = "HEADER_BILLING";
	String	s_current_page = "/requests/req_billing.jsp";
	String	s_current_method = "RequisitionHeaderUpdate";
	String	s_current_process_method = "";

	boolean labViewGroup = false;
	List groupList = user.getUserRoles();
	for (Iterator it = groupList.iterator(); it.hasNext(); )
    {
		if (((String)it.next()).indexOf("LAB VIEW") >= 0)
		{
			labViewGroup = true;
			break;
		}
	}
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_req_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="country" value="<%=requisitionHeader.getUdf1Code()%>"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="billing_information" defaultString="Billing Information"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<%@ include file="/requests/req_info.jsp" %>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr>
	<tsa:td align="center" valign="top">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tsa:tr>
			<tsa:td  align="center" valign="top">
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="basic">
				<tsa:tr field="req-billToCode" docType="s_req_type">
					<tsa:td align="RIGHT" cssClass="basic"><A HREF="javascript: browseLookup('RequisitionHeader_billToCode', 'bill_to'); void(0);" title="Click here to choose the Bill To Code for this requisition or enter a Bill To Code in the box on the right."><tsa:label labelName="req-billToCode" defaultString="Bill To" docType="s_req_type" checkRequired="true"></tsa:label></A>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_billToCode" tabIndex="1"  maxLength="15" value="<%=requisitionHeader.getBillToCode()%>" onchange="upperCase(this); getNewInfo('billTo', this);" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-addressLine1">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-addressLine1" defaultString="Address"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_addressLine1" maxLength="30" value="<%=billTo.getAddressLine1()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-addressLine2">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-addressLine2" defaultString="Address 2"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_addressLine2" maxLength="30" value="<%=billTo.getAddressLine2()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-addressLine3">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-addressLine3" defaultString="Address 3"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_addressLine3" maxLength="30" value="<%=billTo.getAddressLine3()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-addressLine4">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-addressLine4" defaultString="Address 4"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_addressLine4" maxLength="30" value="<%=billTo.getAddressLine4()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-city">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-city" defaultString="City"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_city" maxLength="30" value="<%=billTo.getCity()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-state">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-state" defaultString="State"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_state" maxLength="30" value="<%=billTo.getState()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-zip">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-zip" defaultString="Zip/Postal Code"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_postalCode" maxLength="30" value="<%=billTo.getPostalCode()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-country">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-country" defaultString="Country"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="Address_country" maxLength="25" value="<%=billTo.getCountry()%>" onfocus="this.blur();" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-bil-attention">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-bil-attention" defaultString="Attention" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="midtext" name="RequisitionHeader_billToContact" tabIndex="2" maxLength="40" value="<%=s_bill_to_contact%>" /></tsa:td>
				</tsa:tr>
				<%
				if (oid.equalsIgnoreCase("WPC08P")) {
					Vendor vendorR = (Vendor) request.getAttribute("vendor");
					String s_vendorTerms = "";
					if(vendorR != null) {
						s_vendorTerms = vendorR.getVendTerms();
					}
				%>
				<tsa:tr>
					<tsa:td align="right"><tsa:label labelName="req-paymentTerms" defaultString="Terms"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td> <tsa:input type="midtext" name="Vendor_vendTerms" maxLength="20" value="<%=s_vendorTerms%>" readonly="readonly" disabled="disabled" /></tsa:td>
				</tsa:tr>
				<% } %>
				</TABLE>
			</tsa:td>
			<tsa:td valign="top">
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="basic">
				<tsa:tr field="req-pcardName">
					<tsa:td align="right">
						<tsa:label labelName="req-default-from-profile" defaultString="Default from Profile" />:&nbsp;
					</tsa:td>
					<tsa:td>
						<tsa:input type="checkbox" name="pcardDefaultFrmProfile" onclick="pcardDefaultFromProfile();"/>
						<% if ("ON".equals(propertiesManager.getProperty("REQ OPTIONS", "GHOSTNUMBER", "OFF"))) { %>
							<input type="button" onClick="this.disabled=true;getGhostNumber();" value="Get Ghost Number"/>
						<% } %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-pcardName">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-pcardName" defaultString="Card Type" />:&nbsp;</tsa:td>
					<tsa:td><SELECT NAME="RequisitionHeader_pcardName" maxLength="30">
						<OPTION value="" <%if (requisitionHeader.getPcardName().equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
<%
		Map paymentTypes = DictionaryManager.getInstance("payment-type", oid).getPropertyMap();
		Iterator typeIterator = paymentTypes.keySet().iterator();
		String pCardType = "";
		String pCardName = "";
		while (typeIterator.hasNext())
		{
			pCardType = (String) typeIterator.next();
			pCardName = (String) paymentTypes.get(pCardType);
%>
						<OPTION value="<%=pCardType%>" <%if (requisitionHeader.getPcardName().equalsIgnoreCase(pCardType)) {%>SELECTED<%}%>><%=pCardName%></OPTION>
<%	}	%>
						</SELECT></tsa:td>
				</tsa:tr>
				<tsa:tr field="req-pcardHolder">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-pcardHolder" defaultString="Name" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="text" name="RequisitionHeader_pcardHolder" tabIndex="1" size="22" maxLength="45" value="<%=s_pcardHolder%>" onchange="upperCase(this); " />
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-pcardNumber">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-pcardNumber" defaultString="Card #" checkRequired="true"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><tsa:input type="text" name="as_pcardNumber" size="22" maxLength="20" value="<%=s_subPcardNumber%>" onchange="upperCase(this); setPcardNumber(this);" />
						<tsa:hidden name="RequisitionHeader_pcardNumber" value="<%=s_pcardNumber%>"/>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-pcardExpirationDate">
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-pcardExpirationDate" defaultString="Date Expires"></tsa:label>:&nbsp;</tsa:td>
					<tsa:td><SELECT NAME="as_expMonth" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expMonth.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="JAN" <%if (s_expMonth.equalsIgnoreCase("JAN")) {%>SELECTED<%}%>><tsa:label labelName="req-jan" defaultString="Jan"></tsa:label></OPTION>
							<OPTION value="FEB" <%if (s_expMonth.equalsIgnoreCase("FEB")) {%>SELECTED<%}%>><tsa:label labelName="req-feb" defaultString="Feb"></tsa:label></OPTION>
							<OPTION value="MAR" <%if (s_expMonth.equalsIgnoreCase("MAR")) {%>SELECTED<%}%>><tsa:label labelName="req-mar" defaultString="Mar"></tsa:label></OPTION>
							<OPTION value="APR" <%if (s_expMonth.equalsIgnoreCase("APR")) {%>SELECTED<%}%>><tsa:label labelName="req-apr" defaultString="Apr"></tsa:label></OPTION>
							<OPTION value="MAY" <%if (s_expMonth.equalsIgnoreCase("MAY")) {%>SELECTED<%}%>><tsa:label labelName="req-may" defaultString="May"></tsa:label></OPTION>
							<OPTION value="JUN" <%if (s_expMonth.equalsIgnoreCase("JUN")) {%>SELECTED<%}%>><tsa:label labelName="req-jun" defaultString="Jun"></tsa:label></OPTION>
							<OPTION value="JUL" <%if (s_expMonth.equalsIgnoreCase("JUL")) {%>SELECTED<%}%>><tsa:label labelName="req-jul" defaultString="Jul"></tsa:label></OPTION>
							<OPTION value="AUG" <%if (s_expMonth.equalsIgnoreCase("AUG")) {%>SELECTED<%}%>><tsa:label labelName="req-aug" defaultString="Aug"></tsa:label></OPTION>
							<OPTION value="SEP" <%if (s_expMonth.equalsIgnoreCase("SEP")) {%>SELECTED<%}%>><tsa:label labelName="req-sep" defaultString="Sep"></tsa:label></OPTION>
							<OPTION value="OCT" <%if (s_expMonth.equalsIgnoreCase("OCT")) {%>SELECTED<%}%>><tsa:label labelName="req-oct" defaultString="Oct"></tsa:label></OPTION>
							<OPTION value="NOV" <%if (s_expMonth.equalsIgnoreCase("NOV")) {%>SELECTED<%}%>><tsa:label labelName="req-nov" defaultString="Nov"></tsa:label></OPTION>
							<OPTION value="DEC" <%if (s_expMonth.equalsIgnoreCase("DEC")) {%>SELECTED<%}%>><tsa:label labelName="req-dec" defaultString="Dec"></tsa:label></OPTION>
						</SELECT>
						<SELECT NAME="as_expYear" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expYear.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="2005" <%if (s_expYear.equalsIgnoreCase("2005")) {%>SELECTED<%}%>><tsa:label labelName="req-2005" defaultString="2005"></tsa:label></OPTION>
							<OPTION value="2006" <%if (s_expYear.equalsIgnoreCase("2006")) {%>SELECTED<%}%>><tsa:label labelName="req-2006" defaultString="2006"></tsa:label></OPTION>
							<OPTION value="2007" <%if (s_expYear.equalsIgnoreCase("2007")) {%>SELECTED<%}%>><tsa:label labelName="req-2007" defaultString="2007"></tsa:label></OPTION>
							<OPTION value="2008" <%if (s_expYear.equalsIgnoreCase("2008")) {%>SELECTED<%}%>><tsa:label labelName="req-2008" defaultString="2008"></tsa:label></OPTION>
							<OPTION value="2009" <%if (s_expYear.equalsIgnoreCase("2009")) {%>SELECTED<%}%>><tsa:label labelName="req-2009" defaultString="2009"></tsa:label></OPTION>
							<OPTION value="2010" <%if (s_expYear.equalsIgnoreCase("2010")) {%>SELECTED<%}%>><tsa:label labelName="req-2010" defaultString="2010"></tsa:label></OPTION>
							<OPTION value="2011" <%if (s_expYear.equalsIgnoreCase("2011")) {%>SELECTED<%}%>><tsa:label labelName="req-2011" defaultString="2011"></tsa:label></OPTION>
							<OPTION value="2012" <%if (s_expYear.equalsIgnoreCase("2012")) {%>SELECTED<%}%>><tsa:label labelName="req-2012" defaultString="2012"></tsa:label></OPTION>
							<OPTION value="2013" <%if (s_expYear.equalsIgnoreCase("2013")) {%>SELECTED<%}%>><tsa:label labelName="req-2013" defaultString="2013"></tsa:label></OPTION>
							<OPTION value="2014" <%if (s_expYear.equalsIgnoreCase("2014")) {%>SELECTED<%}%>><tsa:label labelName="req-2014" defaultString="2014"></tsa:label></OPTION>
							<OPTION value="2015" <%if (s_expYear.equalsIgnoreCase("2015")) {%>SELECTED<%}%>><tsa:label labelName="req-2015" defaultString="2015"></tsa:label></OPTION>
						</SELECT>
						<tsa:hidden name="RequisitionHeader_pcardExpires" value="<%=requisitionHeader.getPcardExpires()%>"/>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="req-fob">
				<% if( s_req_type.equalsIgnoreCase("C") && oid.equalsIgnoreCase("BLY07P")){ %>
					<tsa:td align="RIGHT" cssClass="basic"><tsa:label labelName="req-fob" defaultString="FOB"></tsa:label>:&nbsp;</tsa:td>
				<% } else if(labViewGroup){ %>
					<tsa:td align="RIGHT" cssClass="basic"><a href="javascript: browseStd('RequisitionHeader_fobCode', 'FOB', false, true); void(0);" title='Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-fob", "FOB")%> for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-fob", "FOB")%> in the box on the right.'><tsa:label labelName="req-fob" defaultString="FOB"></tsa:label></a>:&nbsp;</tsa:td>
				<% } else { %>
					<tsa:td align="RIGHT" cssClass="basic"><a href="javascript: browseStd('RequisitionHeader_fobCode', 'FOB'); void(0);" title='Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-fob", "FOB")%> for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-fob", "FOB")%> in the box on the right.'><tsa:label labelName="req-fob" defaultString="FOB"></tsa:label></a>:&nbsp;</tsa:td>
				<% } %>
					<tsa:td><tsa:input type="mintext" name="RequisitionHeader_fobCode" tabIndex="1" maxLength="15" value="<%=s_fob_code%>" onchange="upperCase(this);" disabled="<%=isDisable %>" />
					</tsa:td>
				</tsa:tr>
				</TABLE>
			</tsa:td>
<%	if (s_view.equals("WIZARD")) { %>
			<tsa:td rowspan="3" align="right" valign="top"><%@ include file="/requests/req_sidebar.jsp" %></tsa:td>
<%	} %>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><tsa:label labelName="req-save" defaultString="Save"></tsa:label></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number%)>";
	var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var user_pcardType = "<%=user_pCardType%>";
	var user_pcardNumber = "<%=user_pcardNumber%>";
	var user_pcardHolder = "<%=user_pcardHolder%>";
	var user_subPcardNumber = "<%=user_subPcardNumber%>";
	var user_expYear = "<%=user_expYear%>";
	var user_expMonth = "<%=user_expMonth%>";
	var user_pcardExpires = "<%=user.getpCardExpires()%>";

function thisLoad()
{
	f_StartIt();
<%	if ((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0 && s_req_type.equals("M"))  || s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) {  %>
			checkInputSettings();
<%	} else if(labViewGroup==true) { %>
		checkInputSettings();
		allowInputEdit(frm.RequisitionHeader_fobCode, true);
<%	} else { %>
		setInvalidFields("<%=invalidFields%>");
<%	} %>
}

	function setPcardNumber(field) {
		frm.RequisitionHeader_pcardNumber.value = field.value;
	}

	function setExpirationDate() {
		frm.RequisitionHeader_pcardExpires.value = frm.as_expMonth.value + frm.as_expYear.value;
	}

	function pcardDefaultFromProfile() {
		frm.RequisitionHeader_pcardNumber.value = user_pcardNumber;
		frm.as_pcardNumber.value = user_subPcardNumber;
		frm.RequisitionHeader_pcardName.value = user_pcardType;
		frm.RequisitionHeader_pcardHolder.value = user_pcardHolder;
		frm.as_expMonth.value = user_expMonth;
		frm.as_expYear.value = user_expYear;
		frm.RequisitionHeader_pcardExpires.value = user_pcardExpires;
	}

	<% if ("ON".equals(propertiesManager.getProperty("REQ OPTIONS", "GHOSTNUMBER", "OFF"))) { %>
		function getGhostNumber() {
			var num = rand();
			frm.as_pcardNumber.value = num;
			frm.RequisitionHeader_pcardNumber.value = num;
		}
	<% } %>

	function rand ( ) {
	  return ( Math.floor ( Math.random ( ) * 1000000000000000 + 4000000000000000 ) );
	}

// End Hide script -->
</SCRIPT>
