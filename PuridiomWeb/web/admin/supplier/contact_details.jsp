<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.BlackBox" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	vendorId = (String) request.getAttribute("Vendor_vendorId");
	String	vendorName = (String) request.getAttribute("Vendor_vendorName");
	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));  //used for temp vendor add from PO process
	Contact contact = (Contact) request.getAttribute("contact");
	boolean newContact = false;
	if (contact == null) {
		contact = new Contact();
		newContact = true;
	}
	ContactPK contactPK = (ContactPK) contact.getComp_id();
	if (contactPK == null) {
		contactPK = new ContactPK();
		contactPK.setContactCode("");
		contactPK.setContactType("ALTERNATE");
		contactPK.setVendorId(vendorId);

		contact.setComp_id(contactPK);
		newContact = true;
	}
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_audit_trail = propertiesManager.getProperty("AUDITTRAIL", "SUPPLIERMANAGEMENT", "N");
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<tsa:hidden name="Vendor_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendorName%>"/>
<tsa:hidden name="Contact_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Contact_contactCode" value="<%=contactPK.getContactCode()%>"/>
<tsa:hidden name="Contact_contactType" value="<%=contactPK.getContactType()%>"/>
<tsa:hidden name="Address_addressType" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<% if (!newContact) { %>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<% } %>
<tsa:hidden name="changeFields" value="N"/>
<tsa:hidden name="Vendor_lastChange" value=""/>
<tsa:hidden name="Vendor_lastChangedBy" value=""/>
<tsa:hidden name="Vendor_apBatchid" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contactInformation", "Contact Information")%></div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
				<table border=0>
				<tr>
					<td colspan=4 align=center>
<%	if (contactPK.getContactType().equals("DEFAULT")) { %><font class="error"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "default", "Default")%></font>
<%	} else {%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alternate", "Alternate")%></b><%}%>
						<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contactSupplier", "Contact for Supplier")%> '${esapi:encodeForHTML(Vendor_vendorId)}'</b>					</td>
				</tr>
				<tr><td colspan=4><br></td></tr>
				<tr valign=bottom>
					<td <%=HtmlWriter.isVisible(oid, "cnt-salutation")%> valign=bottom align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-salutation", "Salut.", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-firstName")%> valign=bottom align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-firstName", "First Name", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-middleInitial")%> valign=bottom align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-middleInitial", "M.I.", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-lastName")%> valign=bottom align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-lastName", "Last Name", true)%></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "cnt-salutation")%> valign=top align=right><input type=text name="Contact_salutation" tabindex=1 size=5 maxlength=5 value="<%=contact.getSalutation()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-firstName")%> valign=top><input type=text name="Contact_firstName" tabindex=2 size=15 maxlength=20 value="<%=contact.getFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-middleInitial")%> valign=top><input type=text name="Contact_middleInit" tabindex=3 size=5 maxlength=2 value="<%=contact.getMiddleInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "cnt-lastName")%> valign=top><input type=text name="Contact_lastName" tabindex=4 size=15 maxlength=20 value="<%=contact.getLastName()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-title")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-title", "Title", true)%>:</td>
					<td colspan=3><input type=text name="Contact_contactTitle" tabindex=5 size=30 maxlength=30 value="<%=contact.getContactTitle()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-telephoneNumber")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_phoneNumber" tabindex=6 size=30 maxlength=30 value="<%=contact.getPhoneNumber()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-mobileNumber")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-mobileNumber", "Mobile Number", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_mobileNumber" tabindex=6 size=30 maxlength=30 value="<%=contact.getMobileNumber()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-faxNumber")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-faxNumber", "Fax Number", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_faxNumber" tabindex=7 size=30 maxlength=30 value="<%=contact.getFaxNumber()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-emailAddress")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-emailAddress", "Email Address", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_emailAddr" tabindex=8 size=50 maxlength=50 value="<%=contact.getEmailAddr()%>" onchange="checkemail(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-info1")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-info1", "Info 1", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_info1" tabindex=9 size=50 maxlength=50 value="<%=contact.getInfo1()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-info2")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-info2", "Info 2", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_info2" tabindex=10 size=50 maxlength=50 value="<%=contact.getInfo2()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-info3")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-info3", "Info 3", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_info3" tabindex=11 size=50 maxlength=50 value="<%=contact.getInfo3()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-info4")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-info4", "Info 4", true)%>:</td>
					<td  colspan=3><input type=text name="Contact_info4" tabindex=12 size=50 maxlength=50 value="<%=contact.getInfo4()%>"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-status")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-status", "Status", true)%>:</td>
					<td  colspan=3>
						<select name="Contact_status" tabIndex=13>
<% if (!(HiltonUtility.isEmpty(contact.getStatus()))) { %>
							<option value="01" <% if ((contact.getStatus()).indexOf("01")>= 0){ out.println("SELECTED"); }%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
							<option value="02" <% if ((contact.getStatus()).indexOf("02")>= 0){ out.println("SELECTED"); }%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
							<option value="03" <% if ((contact.getStatus()).indexOf("03")>= 0){ out.println("SELECTED"); }%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
<% } else { %>
							<option value="01" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
							<option value="02" SELECTED><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
							<option value="03" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
<% } %>
						</select>
					</td>
				</tr>

				<% if(oid.equalsIgnoreCase("bly07p")) { %>
					  <tr><td>&nbsp;</td></tr>
				      <tr <%=HtmlWriter.isVisible(oid, "cnt-password")%>>
				         <td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-password", "Contact Password", true)%>:</td>
					     <td><input type=password name="contactPassword" autocomplete="off" tabindex=14 size=15 maxlength=12 value="<% if (!HiltonUtility.isEmpty(contact.getContactPassword())) {%>******<%}%>"></td>
				      </tr>
				      <tr>
					 <td colspan=4 align=center>
					    <% if (contactPK.getContactType().equals("ALTERNATE") && !HiltonUtility.isEmpty(contactPK.getContactCode())) { %>
						<a href="javascript: setAsDefaultContact(); void(0);"><img src="<%=contextPath%>/images/asset3.gif" border="0" alt="Make this Contact the new Default Contact"></a>
						<a href="javascript: setAsDefaultContact(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "setDefaultContact", "Set as Default Contact")%></a>
					    <% } %>
					  </td>
				      </tr>
				    <% }else{ %>
			          	  <tr <%=HtmlWriter.isVisible(oid, "cnt-password")%>>
					       <td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-password", "Contact Password", true)%>:</td>
					     	<td>
							<input type=password name="contactPassword" autocomplete="off" tabindex=14 size=15 maxlength=12 value="<% if (!HiltonUtility.isEmpty(contact.getContactPassword())) {%>******<%}%>">
						</td>
						<td colspan=2>
						<% if (contactPK.getContactType().equals("ALTERNATE") && !HiltonUtility.isEmpty(contactPK.getContactCode())) { %>
							<a href="javascript: setAsDefaultContact(); void(0);"><img src="<%=contextPath%>/images/asset3.gif" border="0" alt="Make this Contact the new Default Contact"></a>
							<a href="javascript: setAsDefaultContact(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "setDefaultContact", "Set as Default Contact")%></a>
						<% } %>
						</td>
					</tr>
				<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-contactType")%>>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-contactType", "Contact Type", true)%>:</td>
					<td colspan=3><input type=text name="as_contactType" tabindex=12 size=15 maxlength=12 value="<%=contact.getComp_id().getContactType()%>" disabled></td>
				</tr>
			</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%
	String	updateHandler = "ContactUpdate";
	if (HiltonUtility.isEmpty(contactPK.getContactCode())) {
		updateHandler = "ContactAdd";
	}
%>
<% if (( (String)request.getAttribute("pageFrom")).indexOf("contact") >=0) {
	 if (role.getAccessRights("VEND") > 1) {
		if (oid.equalsIgnoreCase("MSG07P")) { %>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/admin/supplier/supplier_contact_list.jsp', '<%=updateHandler%>;VendorRetrieveById;ContactAddressRetrieveBySupplier'); void(0);">Save</a></div></td>
		<% } else { %>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: submitForm('/admin/supplier/supplier_contact_list.jsp', '<%=updateHandler%>;VendorRetrieveById;ContactAddressRetrieveBySupplier'); void(0);">Save</a></div></td>
<%		 }
	 }
%>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'VendorRetrieveById;ContactAddressRetrieveBySupplier'); void(0);">Return</a></div<</td>
<% } else if (( (String)request.getAttribute("pageFrom")).indexOf("supplier") >=0) {
	 if (role.getAccessRights("VEND") > 1) {
		if (oid.equalsIgnoreCase("MSG07P")) { %>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/admin/supplier/supplier_info.jsp', '<%=updateHandler%>;VendorRetrieveById'); void(0);">Save</a></div></td>
		<% } else { %>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: submitForm('/admin/supplier/supplier_info.jsp', '<%=updateHandler%>;VendorRetrieveById'); void(0);">Save</a></div></td>
<% 		}
	 }
%>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);">Return</a></div></td>
<% } %>

</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2 || (oid.equals("BLY07P") && contactPK.getContactType().equals("DEFAULT"))) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("<%=updateHandler%>") >= 0) {
			if (frm.contactPassword.value != "*****") {
				setHiddenFields("<input type=hidden name=" + '"' + "Contact_contactPassword"+ '"' + " value=" + '"' + frm.contactPassword.value + '"' + ">");
			}
		}
		return true;
	}

	function setAsDefaultContact()
	{
	//	frm.Contact_contactCode.value = "001";
	//	frm.Contact_contactType.value = "DEFAULT";
	//	doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactUpdate;VendorRetrieveById;ContactAddressRetrieveBySupplier');
		doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactSetAsDefault;VendorRetrieveById;ContactAddressRetrieveBySupplier');
	}

	function submitThis(page, handlers)
	{
		<% if (oid.equalsIgnoreCase("msg07p")) { %>
		setChangeFields();
		<% } %>
		if(frm.Contact_lastName.value == "")
			alert("Last Name is required");
		else
			doSubmit(page, handlers);
	}

	function setChangeFields()
	{
		var salutation   = (frm.Contact_salutation)   ? ( (frm.Contact_salutation.value   != "<%=HiltonUtility.ckNull(contact.getSalutation())%>" )   ? true : false ) : false;
		var firstName    = (frm.Contact_firstName)    ? ( (frm.Contact_firstName.value    != "<%=HiltonUtility.ckNull(contact.getFirstName())%>" )    ? true : false ) : false;
		var middleInit   = (frm.Contact_middleInit)   ? ( (frm.Contact_middleInit.value   != "<%=HiltonUtility.ckNull(contact.getMiddleInit())%>" )   ? true : false ) : false;
		var lastName     = (frm.Contact_lastName)     ? ( (frm.Contact_lastName.value     != "<%=HiltonUtility.ckNull(contact.getLastName())%>" )     ? true : false ) : false;
		var contactTitle = (frm.Contact_contactTitle) ? ( (frm.Contact_contactTitle.value != "<%=HiltonUtility.ckNull(contact.getContactTitle())%>" ) ? true : false ) : false;
		var phoneNumber  = (frm.Contact_phoneNumber)  ? ( (frm.Contact_phoneNumber.value  != "<%=HiltonUtility.ckNull(contact.getPhoneNumber())%>" )  ? true : false ) : false;
		var mobileNumber = (frm.Contact_mobileNumber) ? ( (frm.Contact_mobileNumber.value != "<%=HiltonUtility.ckNull(contact.getMobileNumber())%>" ) ? true : false ) : false;
		var faxNumber    = (frm.Contact_faxNumber)    ? ( (frm.Contact_faxNumber.value    != "<%=HiltonUtility.ckNull(contact.getFaxNumber())%>" )    ? true : false ) : false;
		var emailAddr    = (frm.Contact_emailAddr)    ? ( (frm.Contact_emailAddr.value    != "<%=HiltonUtility.ckNull(contact.getEmailAddr())%>" )    ? true : false ) : false;
		var info1        = (frm.Contact_info1)        ? ( (frm.Contact_info1.value        != "<%=HiltonUtility.ckNull(contact.getInfo1())%>" )        ? true : false ) : false;
		var info2        = (frm.Contact_info2)        ? ( (frm.Contact_info2.value        != "<%=HiltonUtility.ckNull(contact.getInfo2())%>" )        ? true : false ) : false;
		var info3        = (frm.Contact_info3)        ? ( (frm.Contact_info3.value        != "<%=HiltonUtility.ckNull(contact.getInfo3())%>" )        ? true : false ) : false;
		var info4        = (frm.Contact_info4)        ? ( (frm.Contact_info4.value        != "<%=HiltonUtility.ckNull(contact.getInfo4())%>" )        ? true : false ) : false;
		var status       = (frm.Contact_status)       ? ( (frm.Contact_status.value       != "<%=HiltonUtility.ckNull(contact.getStatus())%>" )       ? true : false ) : false;

		if(salutation||firstName||middleInit||lastName||contactTitle||phoneNumber||mobileNumber||faxNumber||emailAddr||info1||info2||info3||info4||status)
		{
			frm.changeFields.value = "Y";
			frm.Vendor_lastChange.value = "<%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>";
			frm.Vendor_lastChangedBy.value = "<%=user.getUserId()%>";
			frm.Vendor_apBatchid.value = "";
		}
	}

	function setAuditTables()
	{
		frm.auditTables.value = "Contact";
	}
	function getFields(el)
	{
		if(el.type != "hidden" && el.name.indexOf("Contact_") > -1)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
	
	function getFieldsJquery()
	{
		var fieldsToAuditJquery = $(":input:not([type=hidden])[name^='Contact_']");
		return fieldsToAuditJquery;
    }
    function buildAuditIc()
	{
		return frm.Vendor_vendorId.value;
	}
	function viewAuditTrail(vendorId)
	{
		popupParameters = popupParameters + "browseName=audittrail";
		popupParameters = popupParameters + ";colname=AuditRecord_entity1;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function submitForm(page, handlers)
	{
		if (frm.Contact_firstName && trim(frm.Contact_firstName) == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-firstName", "First Name")%> is required.");
			frm.Contact_firstName.focus();
		} else if (frm.Contact_lastName && trim(frm.Contact_lastName) == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-lastName", "Last Name")%> is required.");
			frm.Contact_lastName.focus();
		} else if (frm.Contact_phoneNumber && trim(frm.Contact_phoneNumber) == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-telephoneNumber", "Phone Number")%> is required.");
			frm.Contact_phoneNumber.focus();
		} else {
			doSubmit(page, handlers);
		}
	}

// End Hide script -->
</SCRIPT>