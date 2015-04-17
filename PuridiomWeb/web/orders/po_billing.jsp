<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean showCreditCardFields = propertiesManager.getProperty("CREDITCARD", "SHOWCREDITCARDFIELDS", "N").equalsIgnoreCase("Y")? true: false;
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_type = poHeader.getPoType();
	String	s_po_status = poHeader.getStatus();
	String s_buyer_code = poHeader.getBuyerCode();

	String	s_bill_to_code = poHeader.getBillToCode();
	String	s_bill_to_contact = poHeader.getBillToContact();
	String	s_fob_code = poHeader.getFobCode();
	String	s_pcardNumber = (String) HiltonUtility.ckNull(poHeader.getPcardNumber());
	String	s_pcardHolder = (String) HiltonUtility.ckNull(poHeader.getPcardHolder());
	String	s_subPcardNumber = "";

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";

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
	if (poHeader.getPcardExpires().length() > 0) {
		s_expYear = poHeader.getPcardExpires().substring(3,7);
		s_expMonth = poHeader.getPcardExpires().substring(0,3);
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
	if (user.getpCardExpires() != null && user.getpCardExpires().length() > 0) {
		user_expYear = user.getpCardExpires().substring(3,7);
		user_expMonth = user.getpCardExpires().substring(0,3);
	}
	String user_pCardType = user.getpCardType();

	Address billTo = (Address) poHeader.getBillToAddress();
	if (billTo != null)
	{
		s_address_line_1 = billTo.getAddressLine1();
		s_address_line_2 = billTo.getAddressLine2();
		s_address_line_3 = billTo.getAddressLine3();
		s_address_line_4 = billTo.getAddressLine4();
		s_city = billTo.getCity();
		s_state = billTo.getState();
		s_postal_code = billTo.getPostalCode();
		s_country = billTo.getCountry();
	}

	if (s_po_number == null)			{	s_po_number = "N/A";		}
	if (s_po_status == null )			{	s_po_status = "3000";		}
	if (s_bill_to_code == null)			{	s_bill_to_code = "";			}
	if (s_bill_to_contact == null)		{	s_bill_to_contact = "";		}
	if (s_fob_code == null)			{	s_fob_code = "";				}
	if (s_address_line_1 == null)		{	s_address_line_1 = "";		}
	if (s_address_line_2 == null)		{	s_address_line_2 = "";		}
	if (s_address_line_3 == null)		{	s_address_line_3 = "";		}
	if (s_address_line_4 == null)		{	s_address_line_4 = "";		}
	if (s_city == null)					{	s_city = "";						}
	if (s_state == null)					{	s_state = "";					}
	if (s_postal_code == null)			{	s_postal_code = "";			}
	if (s_country == null)				{	s_country = "";				}

	boolean bAllowEdit = true;
	if ( (role.getAccessRights("PO") < 2) || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0)
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowEdit = false;
	}

	String	s_current_process = "HEADER_BILLING";
	String	s_current_page = "/orders/po_billing.jsp";
	String	s_current_method = "PoHeaderUpdate";
	String	s_current_process_method = "";
	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=poHeader.getFiscalYear()%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=poHeader.getItemLocation()%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=poHeader.getBuyerCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Schedule_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="country" value="<%=poHeader.getUdf1Code()%>"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="billing_information" defaultString="Billing Information" /></div>
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
<%@ include file="/orders/po_info.jsp" %>
<br>
<%@ include file="/system/error_msg.jsp" %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=48% align=center valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr <%=HtmlWriter.isVisible(oid, "po-billToCode", s_po_type)%>>
					<td align=right nowrap><a href="javascript: browseLookup('PoHeader_billToCode', 'bill_to'); void(0);" title="Click here to choose the <tsa:label labelName="po-billToCode" defaultString="Bill To" /> for this order or enter the <tsa:label labelName="po-billToCode" defaultString="Bill To" /> in the box on the right."><tsa:label labelName="po-billToCode" defaultString="Bill To" checkRequired="true" /></a>:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_billToCode" tabIndex="2" maxLength="15" value="<%=s_bill_to_code%>" onchange="upperCase(this); getNewInfo('billTo', this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine1")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-addressLine1" defaultString="Address 1" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_addressLine1" maxLength="40" value="<%=s_address_line_1%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine2")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-addressLine2" defaultString="Address 2" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_addressLine2" maxLength="40" value="<%=s_address_line_2%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine3")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-addressLine3" defaultString="Address 3" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_addressLine3" maxLength="40" value="<%=s_address_line_3%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine4")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-addressLine4" defaultString="Address 4" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_addressLine4" maxLength="40" value="<%=s_address_line_4%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-city")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-city" defaultString="City" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_city" maxLength="30" value="<%=s_city%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-state")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-state" defaultString="State" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_state" maxLength="15" value="<%=s_state%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-zip")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-zip" defaultString="Zip/Postal Code" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_postalCode" maxLength="15" value="<%=s_postal_code%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-country")%>>
					<td align=right nowrap><tsa:label labelName="po-bil-country" defaultString="Country" />:&nbsp;</td>
					<td width=50%><tsa:input type="midtext" name="Address_country" maxLength="25" value="<%=s_country%>" onfocus="this.blur()" disabled="true" /></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
			<td width=4%>&nbsp;</td>
			<td width=48% align=center valign=top>
			<%
				String show_pcardName = HtmlWriter.isVisible(oid, "po-pcardName", s_po_type);
				String show_pcardNumber = HtmlWriter.isVisible(oid, "po-pcardNumber", s_po_type);
				String show_pcardHolder = HtmlWriter.isVisible(oid, "po-pcardHolder", s_po_type);
				String show_pcardExpirationDate = HtmlWriter.isVisible(oid, "po-pcardExpirationDate", s_po_type);
				if (oid.equalsIgnoreCase("bsc04p") && ( s_po_type.equals("BO") || s_po_type.equals("RO")))
				{
					show_pcardName = show_pcardNumber = show_pcardHolder = show_pcardExpirationDate = "";
				}
			%>
			<% if (showCreditCardFields){%>			
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="basic" width=100%>
				<TR <%=show_pcardName %>><td align="right"><tsa:label labelName="default-profile-from" defaultString="Default from Profile" />:&nbsp;</td>
				<td>
					<tsa:input type="checkbox" name="pcardDefaultFrmProfile" onclick="pcardDefaultFromProfile();"/>
					<input type="button" onClick="this.disabled=true;getGhostNumber();" value="Get Ghost Number"/></td>
				</TR>
				<TR <%=show_pcardName %>>
					<TD ALIGN="RIGHT" CLASS="basic" width="140" nowrap><tsa:label labelName="po-pcardName" defaultString="Card Type" />:&nbsp;</TD>
					<TD>
						<SELECT NAME="PoHeader_pcardName" MAXLENGTH="30">
							<OPTION value="" <%if (poHeader.getPcardName().equalsIgnoreCase("")) {%>SELECTED<%}%>></VALUE>
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
							<OPTION value="<%=pCardType%>" <%if (poHeader.getPcardName().equalsIgnoreCase(pCardType)) {%>SELECTED<%}%>><%=pCardName%></VALUE>
<%	}	%>
						</SELECT>
				</TR>
				<TR <%=show_pcardNumber%>>
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-pcardNumber" defaultString="Card #" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="as_pcardNumber" tabIndex="1" size="22" maxLength="20" value="<%=s_subPcardNumber%>" onchange="upperCase(this); setPcardNumber(this);" />
						<tsa:hidden name="PoHeader_pcardNumber" value="<%=s_pcardNumber%>"/>
					</TD>
				</TR>
				<TR <%=show_pcardHolder%>>
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-pcardHolder" defaultString="Name" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="PoHeader_pcardHolder" tabIndex="1" size="22" maxLength="45" value="<%=s_pcardHolder%>" onchange="upperCase(this);" />
					</TD>
				</TR>
				<TR <%=show_pcardExpirationDate%>>
					<TD ALIGN="RIGHT" CLASS="basic"><tsa:label labelName="po-pcardExpirationDate" defaultString="Expiration Date" />:&nbsp;</TD>
					<TD><SELECT NAME="as_expMonth" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expMonth.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="JAN" <%if (s_expMonth.equalsIgnoreCase("JAN")) {%>SELECTED<%}%>>Jan</OPTION>
							<OPTION value="FEB" <%if (s_expMonth.equalsIgnoreCase("FEB")) {%>SELECTED<%}%>>Feb</OPTION>
							<OPTION value="MAR" <%if (s_expMonth.equalsIgnoreCase("MAR")) {%>SELECTED<%}%>>Mar</OPTION>
							<OPTION value="APR" <%if (s_expMonth.equalsIgnoreCase("APR")) {%>SELECTED<%}%>>Apr</OPTION>
							<OPTION value="MAY" <%if (s_expMonth.equalsIgnoreCase("MAY")) {%>SELECTED<%}%>>May</OPTION>
							<OPTION value="JUN" <%if (s_expMonth.equalsIgnoreCase("JUN")) {%>SELECTED<%}%>>Jun</OPTION>
							<OPTION value="JUL" <%if (s_expMonth.equalsIgnoreCase("JUL")) {%>SELECTED<%}%>>Jul</OPTION>
							<OPTION value="AUG" <%if (s_expMonth.equalsIgnoreCase("AUG")) {%>SELECTED<%}%>>Aug</OPTION>
							<OPTION value="SEP" <%if (s_expMonth.equalsIgnoreCase("SEP")) {%>SELECTED<%}%>>Sep</OPTION>
							<OPTION value="OCT" <%if (s_expMonth.equalsIgnoreCase("OCT")) {%>SELECTED<%}%>>Oct</OPTION>
							<OPTION value="NOV" <%if (s_expMonth.equalsIgnoreCase("NOV")) {%>SELECTED<%}%>>Nov</OPTION>
							<OPTION value="DEC" <%if (s_expMonth.equalsIgnoreCase("DEC")) {%>SELECTED<%}%>>Dec</OPTION>
						</SELECT>
						<SELECT NAME="as_expYear" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expYear.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="2005" <%if (s_expYear.equalsIgnoreCase("2005")) {%>SELECTED<%}%>>2005</OPTION>
							<OPTION value="2006" <%if (s_expYear.equalsIgnoreCase("2006")) {%>SELECTED<%}%>>2006</OPTION>
							<OPTION value="2007" <%if (s_expYear.equalsIgnoreCase("2007")) {%>SELECTED<%}%>>2007</OPTION>
							<OPTION value="2008" <%if (s_expYear.equalsIgnoreCase("2008")) {%>SELECTED<%}%>>2008</OPTION>
							<OPTION value="2009" <%if (s_expYear.equalsIgnoreCase("2009")) {%>SELECTED<%}%>>2009</OPTION>
							<OPTION value="2010" <%if (s_expYear.equalsIgnoreCase("2010")) {%>SELECTED<%}%>>2010</OPTION>
							<OPTION value="2011" <%if (s_expYear.equalsIgnoreCase("2011")) {%>SELECTED<%}%>>2011</OPTION>
							<OPTION value="2012" <%if (s_expYear.equalsIgnoreCase("2012")) {%>SELECTED<%}%>>2012</OPTION>
							<OPTION value="2013" <%if (s_expYear.equalsIgnoreCase("2013")) {%>SELECTED<%}%>>2013</OPTION>
							<OPTION value="2014" <%if (s_expYear.equalsIgnoreCase("2014")) {%>SELECTED<%}%>>2014</OPTION>
							<OPTION value="2015" <%if (s_expYear.equalsIgnoreCase("2015")) {%>SELECTED<%}%>>2015</OPTION>
						</SELECT>
						<tsa:hidden name="PoHeader_pcardExpires" value="<%=poHeader.getPcardExpires()%>"/>
					</TD>
				</TR>
				</TABLE>
				<% }%>
			<br>

				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr><td colspan=2><br><br></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>>
					<td align=right width=140 nowrap><tsa:label labelName="po-bil-attention" defaultString="Attention" checkRequired="true" />:&nbsp;</td>
					<td><tsa:input type="midtext" name="PoHeader_billToContact" tabIndex="4" maxLength="40" value="<%=s_bill_to_contact%>" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-paymentTerms")%>>
					<td align=right nowrap><a href="javascript: browseLookup('PoHeader_termsCode', 'payment-terms'); void(0);" title="Click here to choose the <tsa:label labelName="po-paymentTerms" defaultString="Terms" /> for this order or enter the <tsa:label labelName="po-paymentTerms" defaultString="Terms" /> in the box on the right."><tsa:label labelName="po-paymentTerms" defaultString="Terms" checkRequired="true" /></a>:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_termsCode" tabIndex="6" maxLength="15" value="<%=poHeader.getTermsCode()%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "po-fob")%>>
					<td align=right nowrap><a href="javascript: browseStd('PoHeader_fobCode', 'FOB'); void(0);" title="Click here to choose the <tsa:label labelName="po-fob" defaultString="FOB" /> for this order or enter the <tsa:label labelName="po-fob" defaultString="FOB" /> in the box on the right."><tsa:label labelName="po-fob" defaultString="FOB" checkRequired="true" /></a>:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_fobCode" tabIndex="7" maxLength="15" value="<%=s_fob_code%>" onchange="upperCase(this);" /></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=3 align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoHeaderUpdate;PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%=poHeader.getFiscalYear()%>";
	var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
	var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
	var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";

	var user_pcardType = "<%=HiltonUtility.ckNull(user_pCardType)%>";
	var user_pcardNumber = "<%=HiltonUtility.ckNull(user_pcardNumber)%>";
	var user_pcardHolder = "<%=HiltonUtility.ckNull(user_pcardHolder)%>";
	var user_subPcardNumber = "<%=HiltonUtility.ckNull(user_subPcardNumber)%>";
	var user_expYear = "<%=HiltonUtility.ckNull(user_expYear)%>";
	var user_expMonth = "<%=HiltonUtility.ckNull(user_expMonth)%>";
	var user_pcardExpires = "<%=HiltonUtility.ckNull(user.getpCardExpires())%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (!bAllowEdit) { %>
			checkInputSettings();
<%	} else {%>
		setInvalidFields("<%= headerEncoder.encodeForJavaScript(invalidFields) %>");
<%	}%>
	}

	function setPcardNumber(field) {
		frm.PoHeader_pcardNumber.value = field.value;
	}

	function setExpirationDate() {
		frm.PoHeader_pcardExpires.value = frm.as_expMonth.value + frm.as_expYear.value;
	}

	function pcardDefaultFromProfile() {
		frm.PoHeader_pcardNumber.value = user_pcardNumber;
		frm.as_pcardNumber.value = user_subPcardNumber;
		frm.PoHeader_pcardName.value = user_pcardType;
		frm.PoHeader_pcardHolder.value = user_pcardHolder;
		frm.as_expMonth.value = user_expMonth;
		frm.as_expYear.value = user_expYear;
		frm.PoHeader_pcardExpires.value = user_pcardExpires;
	}

	function getGhostNumber() {
		var num = rand();
		frm.as_pcardNumber.value = num;
		frm.PoHeader_pcardNumber.value = num;
	}

	function rand ( ) {
	  return ( Math.floor ( Math.random ( ) * 1000000000000000 + 4000000000000000 ) );
	}

// End Hide script -->
</SCRIPT>

