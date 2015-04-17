<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.SubContractor" %>
<%@ page import="java.math.BigDecimal" %>
<%
		SubContractor subContractor = (SubContractor) request.getAttribute("subContractor");
		String	poNumber = (String) request.getAttribute("SubContractor_poNumber");
		String	releaseNumberString = (String) request.getAttribute("SubContractor_releaseNumber");
		String	contractno = (String) request.getAttribute("SubContractor_contractno");
		if (HiltonUtility.isEmpty(releaseNumberString))
		{
			releaseNumberString = "0";
		}
		BigDecimal	releaseNumber = new BigDecimal(releaseNumberString);
		if (subContractor == null)
		{
			subContractor = new SubContractor();
			SubContractorPK comp_id = new SubContractorPK();
			comp_id.setPoNumber(poNumber);
			comp_id.setReleaseNumber(releaseNumber);
			subContractor.setComp_id(comp_id);
			subContractor.setDateEntered(d_today);
			subContractor.setDateExpires(d_today);
			subContractor.setOwner(uid);
			subContractor.setStatus("02");
		}
		String	s_refresh = (String) request.getAttribute("refreshOpener");
		if (HiltonUtility.isEmpty(s_refresh))		{	s_refresh = "N";			}
%>
<tsa:hidden name="SubContractor_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="SubContractor_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="SubContractor_contractno" value="<%=contractno%>"/>
<tsa:hidden name="SubContractor_dateEntered" value="<%=HiltonUtility.getFormattedDate(subContractor.getDateEntered(), oid, userDateFormat)%>"/>
<tsa:hidden name="SubContractor_dateExpires" value="<%=HiltonUtility.getFormattedDate(subContractor.getDateExpires(), oid, userDateFormat)%>"/>
<tsa:hidden name="SubContractor_owner" value="<%=subContractor.getOwner()%>"/>
<tsa:hidden name="SubContractor_status" value="<%=subContractor.getStatus()%>"/>
<tsa:hidden name="refreshOpener" value="<%=s_refresh%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="subContractorInfo" defaultString="Sub-Contractor&nbsp;Info" /></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="500px">
<tr>
	<td valign="top">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="250px" align="center" valign="top">
			    <table border="0" cellpadding="0" cellspacing="0" width="100%">
			    <tsa:tr field="po-supplier">
			      <td align="right" nowrap><tsa:label labelName="po-supplier" defaultString="Supplier" checkRequired="true" /></a>:&nbsp;</td>
			      <td nowrap>
			      <% if (!HiltonUtility.isEmpty(subContractor.getComp_id().getSubName())) { %>
			      		<tsa:input labelName="po-supplier"  type="text" name="SubContractor_subName" tabIndex="2" size="25" maxLength="40" value="<%=subContractor.getComp_id().getSubName()%>"  readonly="true" />
			      <% } else { %>
			      		<tsa:input labelName="po-supplier"  type="text" name="SubContractor_subName" tabIndex="2" size="25" maxLength="40" value="<%=subContractor.getComp_id().getSubName()%>" />
			      <% } %>
			      </td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-addressLine1">
			      <td align="right" nowrap><tsa:label labelName="po-sup-addressLine1" defaultString="Company Name" />:&nbsp;</td>
			      <td><tsa:input labelName="po-sup-addressLine1" type="text" name="SubContractor_addressLine1" tabIndex="4" size="25" maxLength="40" value="<%=subContractor.getAddressLine1()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-addressLine2">
			      <td align="right" nowrap><tsa:label labelName="po-sup-addressLine2" defaultString="Address 2" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-addressLine2" type="text" name="SubContractor_addressLine2" tabIndex="6" size="25" maxLength="40" value="<%=subContractor.getAddressLine2()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-addressLine3">
			      <td align="right" nowrap><tsa:label labelName="po-sup-addressLine3" defaultString="Address 3" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-addressLine3" type="text" name="SubContractor_addressLine3" tabIndex="8" size="25" maxLength="40" value="<%=subContractor.getAddressLine3()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-addressLine4">
			      <td align="right" nowrap><tsa:label labelName="po-sup-addressLine4" defaultString="Address 4" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-addressLine4" type="text" name="SubContractor_addressLine4" tabIndex="10" size="25" maxLength="40" value="<%=subContractor.getAddressLine4()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-city">
			      <td align="right" nowrap><tsa:label labelName="po-sup-city" defaultString="City" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-city" type="text" name="SubContractor_city" tabIndex="12" size="25" maxLength="30" value="<%=subContractor.getCity()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-state">
			      <td align="right" nowrap><tsa:label labelName="po-sup-state" defaultString="State" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-state" type="text" name="SubContractor_state" tabIndex="14" size="25" maxLength="15" value="<%=subContractor.getState()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-zip">
			      <td align="right" nowrap><tsa:label labelName="po-sup-zip" defaultString="Postal Code" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-zip" type="text" name="SubContractor_postalCode" tabIndex="16" size="25" maxLength="15" value="<%=subContractor.getPostalCode()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-sup-country">
			      <td align="right" nowrap><tsa:label labelName="po-sup-country" defaultString="Country" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-sup-country" type="text" name="SubContractor_country" tabIndex="18" size="25" maxLength="25" value="<%=subContractor.getCountry()%>" /></td>
			    </tsa:tr>
			    </table>
			</td>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tsa:tr field="po-contact-name">
			      <td align="right" nowrap><tsa:label labelName="po-contact-name" defaultString="Contact Name" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-contact-name" type="text" name="SubContractor_contactName" tabIndex="20" size="25" maxLength="40" value="<%=subContractor.getContactName()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-cnt-email">
			      <td align="right" nowrap><tsa:label labelName="po-cnt-email" defaultString="Contact Email" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-cnt-email" type="text" name="SubContractor_contactEmail" tabIndex="22" size="25" maxLength="50" value="<%=subContractor.getContactEmail()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-contact-phone">
			      <td align="right" nowrap><tsa:label labelName="po-contact-phone" defaultString="Contact Phone" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-contact-phone" type="text" name="SubContractor_contactPhone" tabIndex="24" size="25" maxLength="30" value="<%=subContractor.getContactPhone()%>" /></td>
			    </tsa:tr>
			    <tsa:tr field="po-contact-fax">
			      <td align="right" nowrap><tsa:label labelName="po-contact-fax" defaultString="Contact Fax" />:&nbsp;</td>
			      <td width="50%"><tsa:input labelName="po-contact-fax" type="text" name="SubContractor_contactFax" tabIndex="26" size="25" maxLength="20" value="<%=subContractor.getContactFax()%>" /></td>
			    </tsa:tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr><td colspan="2"><br><br></td></tr>
				<tr>
					<td align="right" valign="top"><tsa:label labelName="contract-info" defaultString="Contract Info" />:&nbsp;</td>
					<td><tsa:input labelName="contract-info" type="textarea" wrap="virtual" name="SubContractor_continfo" tabIndex="30" rows="5" cols="60" onkeydown="textCounter(this, 2000);" onkeyup="textCounter(this,2000);" onchange="textCounter(this,2000);">${esapi:encodeForHTML(subContractor.continfo)}</tsa:input></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table width=100% border=0>
<tr>
<%	if (role.getAccessRights("PO") < 2) { %>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); window.top.hidePopWin();"><tsa:label labelName="close" defaultString="Close" /></a></div></td>
<%	} else { %>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); submitThis();"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
	<td align=center><br><div id="pxbutton"><a href="javascript: void(0); window.top.hidePopWin();"><tsa:label labelName="close" defaultString="Close" /></a></div></td>
<%	} %>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var rowSelect;
	frm = document.purchaseform;

	var browser = browserCheck();

	if (frm.refreshOpener.value == "Y")
	{
		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";
		window.parent.doSubmit('/orders/po_supplier.jsp', 'PoHeaderVendorRetrieveById');
		window.top.hidePopWin();
	}


	function submitThis()
	{
		frm.refreshOpener.value = "Y";
		if (window.parent.frm.formAction.value == "CREATE")
		{
			doSubmit('/orders/po_sub_contractor.jsp', 'SubContractorAdd');
		}
		else
		{
			doSubmit('/orders/po_sub_contractor.jsp', 'SubContractorUpdate');
		}
	}

// End Hide script -->
</SCRIPT>


