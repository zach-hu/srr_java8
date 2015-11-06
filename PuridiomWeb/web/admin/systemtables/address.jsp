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

	Address address = (Address) request.getAttribute("address");
	boolean newAddress = false;

	if (address == null)
	{
		address = new Address();
		AddressPK comp_id = new AddressPK();
		comp_id.setAddressType("ADDR");
		address.setComp_id(comp_id);
		address.setDateEntered(d_today);
		address.setDateExpires(d_today);
		address.setOwner(uid);
		address.setStatus("02");
		newAddress = true;
	}
	else
	{
	    String newAddressString = HiltonUtility.ckNull((String) request.getAttribute("newAddress"));
		if (newAddressString.equals("Y"))
		{
	      newAddress = true;
	    }
	}
	AddressPK addressPK = address.getComp_id();
	UserProfile owner = UserManager.getInstance().getUser(oid, address.getOwner());
	String duplicateAddressErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateAddressErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="Address_addressType" value="ADDR"/>
<tsa:hidden name="duplicateAddressFailurePage" value="/admin/systemtables/address.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Address</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateAddressErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateAddressErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=180px align=right nowrap height=18px>Address Code:&nbsp;</td>
			<td><input type="text" name="Address_addressCode" value="<%=addressPK.getAddressCode()%>" size=25 maxlength=15 <% if (! newAddress) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td align=right>
<%	if (! newAddress) { %>
				<a href="javascript: if (verifyAction('Delete this address?')) { doSubmit('browse/browse_sys_tables.jsp', 'AddressDelete;BrowseRetrieve'); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Address</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>Address Line 1:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addressLine1" value="<%=address.getAddressLine1()%>" size=40 maxlength=40></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>Address Line 2:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addressLine2" value="<%=address.getAddressLine2()%>" size=40 maxlength=40></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>Address Line 3:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addressLine3" value="<%=address.getAddressLine3()%>" size=40 maxlength=40></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "addr-addressLine4")%>>
			<td width=180px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-addressLine4", "Address Line 4")%>:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addressLine4" value="<%=address.getAddressLine4()%>" size=40 maxlength=40></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>City:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_city" value="<%=address.getCity()%>" size=40 maxlength=30></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>State:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_state" value="<%=address.getState()%>" size=40 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>Postal Code:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_postalCode" value="<%=address.getPostalCode()%>" size=40 maxlength=15></td>
		</tr>
		<tr>
			<td width=180px align=right nowrap>Country:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_country" value="<%=address.getCountry()%>" size=40 maxlength=25></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "addr-taxCode")%>>
			<td width=180px align=right nowrap><a href="javascript: browseLookup('Address_taxCode', 'taxcode'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-taxCode", "Tax Code")%>:</a>&nbsp;</td>
			<td colspan=2><input type="text" name="Address_taxCode" value="<%=address.getTaxCode()%>" size=40 maxlength=15 onChange="alphaNumericCheck(this);" <% if (!oid.equalsIgnoreCase("hoy08p")) { %>readonly<% } %>></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "addr-addrFld10")%>>
			<td width=180px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-addrFld10", "Address Field 10")%>:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addrFld10" value="<%=address.getAddrFld10()%>" size=40 maxlength=15 onChange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "addr-addrFld15")%>>
			<td width=180px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-addrFld15", "Address Field 15", true)%>:&nbsp;</td>
			<td colspan=2><input type="text" name="Address_addrFld15" value="<%=address.getAddrFld15()%>" size=40 maxlength=15 onChange="upperCase(this);"></td>
		</tr>
		</table>

		<br>

		<br>
		<table border=0 cellpadding=0 cellspacing=0 align=center width=300px>
		<tr>
			<td align=center>
				<table border=1 cellspacing=0 cellpadding=0 class=browseHdr width=100%>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr align=center><b>Location Flags</b></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 width=100%>
						<tr>
							<td valign=top>
								<table border=0 cellpadding=0 cellspacing=2 align=center width=100%>
								<tr <%=HtmlWriter.isVisible(oid, "addr-inventory")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getInventory().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_inventory, 0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-inventory", "Inventory")%>
										<tsa:hidden name="Address_inventory" value="<%=address.getInventory()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-billTo")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getBillTo().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_billTo, 1);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-billTo", "Bill To")%>
										<tsa:hidden name="Address_billTo" value="<%=address.getBillTo()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-issuedBy")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getIssuedBy().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_issuedBy, 2);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-issuedBy", "Issued By")%>
										<tsa:hidden name="Address_issuedBy" value="<%=address.getIssuedBy()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-administeredBy")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getAdministeredBy().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_administeredBy, 3);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-administeredBy", "Administered By")%>
										<tsa:hidden name="Address_administeredBy" value="<%=address.getAdministeredBy()%>"/>
									</td>
								</tr>
								</table>
							</td>
							<td valign=top>
								<table border=0 cellpadding=0 cellspacing=2 align=center width=100%>
								<tr <%=HtmlWriter.isVisible(oid, "addr-shipTo")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getShipTo().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_shipTo, 4);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-shipTo", "Ship To")%>
										<tsa:hidden name="Address_shipTo" value="<%=address.getShipTo()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-invoiceTo")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getInvoiceTo().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_invoiceTo, 5);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-invoiceTo", "Invoice To")%>
										<tsa:hidden name="Address_invoiceTo" value="<%=address.getInvoiceTo()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-offerTo")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getOfferTo().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_offerTo, 6);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-offerTo", "Offer To")%>
										<tsa:hidden name="Address_offerTo" value="<%=address.getOfferTo()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "addr-paymentBy")%>>
									<td>
										<input type=checkbox name="c_checkbox" value="Y" <% if (address.getPaymentBy().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Address_paymentBy, 7);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-paymentBy", "Payment By")%>
										<tsa:hidden name="Address_paymentBy" value="<%=address.getPaymentBy()%>"/>
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
		</tr>
		</table>

		<br>

		<hr width=475px align=center class=browseHR>
		<br>
		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="Address_status" onchange="setStatus();">
							<option value="01" <% if (address.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (address.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (address.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="Address_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(address.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="Address_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(address.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('Address_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td nowrap align=right><a href="javascript: browseLookup('Address_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="Address_owner" tabindex=51 size=30 maxlength=15 value="<%=address.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newAddress) { %>
		<div id="pxbutton"><a href="javascript: submitThis('AddressUpdate;BrowseRetrieve'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript:  submitThis('SysTablesAddressAdd'); void(0);">Save</a></div>
<%	} %>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('address'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "address";
	setNavCookie("/admin/systemtables/address.jsp", "AddressRetrieveById", "Address <%=addressPK.getAddressCode()%>");

	var status = "<%=address.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newAddress) { %>
			frm.Address_addressLine1.focus();
<%	} else { %>
			frm.Address_addressCode.focus();
<%	} %>
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function setStatus()
	{
		status = frm.Address_status[frm.Address_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setCheckbox(fld, x)
	{
		fld.value = 'N';
		if ( frm.c_checkbox[x].checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function submitThis(handlers)
	{
		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
		if (frm.Address_addrFld15 && frm.Address_addrFld15.value == "")
		{
			alert("You must enter a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addr-addrFld15", "Address Field 15")%>!");
			return true;
		}
		<% } %>
		doSubmit('/browse/browse_sys_tables.jsp', handlers);
	}
	
	function getFieldsJquery()
	{
		var fieldsToAuditJquery = $(":input:not([type=hidden])[name='Address_']");
		return fieldsToAuditJquery;
    }


	function buildAuditIc()
	{
		return frm.Address_addressCode.value;
	}

// End Hide script -->
</SCRIPT>