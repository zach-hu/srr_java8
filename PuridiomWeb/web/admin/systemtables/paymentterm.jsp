<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PaymentTerm paymentTerm = (PaymentTerm) request.getAttribute("paymentTerm");
	boolean newPaymentTerm = false;

	if (paymentTerm == null)
	{
		paymentTerm = new PaymentTerm();
		paymentTerm.setDateEntered(d_today);
		paymentTerm.setDateExpires(d_today);
		paymentTerm.setOwner(uid);
		paymentTerm.setStatus("02");
		newPaymentTerm = true;
	}
	else
	{
	    String newPaymentTermString = HiltonUtility.ckNull((String) request.getAttribute("newPaymentTerm"));
		if (newPaymentTermString.equals("Y"))
		{
	      newPaymentTerm = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, paymentTerm.getOwner());
	String duplicatePaymentTermErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicatePaymentTermErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="PaymentTerm_termTypeFlag" value="<%=paymentTerm.getTermTypeFlag()%>"/>
<tsa:hidden name="duplicatePaymentTermFailurePage" value="/admin/systemtables/paymentterm.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Payment Term</div>
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

<%	if (!HiltonUtility.isEmpty(duplicatePaymentTermErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicatePaymentTermErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=100px align=right nowrap height=18px>Payment Code:&nbsp;</td>
			<td><input type="text" name="PaymentTerm_termsCode" value="<%=paymentTerm.getTermsCode()%>" size=25 maxlength=15 <% if (! newPaymentTerm) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newPaymentTerm) { %>
				<a href="javascript: if (verifyAction('Delete this payment term?')) { doSubmit('/browse/browse_sys_tables.jsp', 'PaymentTermDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Payment Term</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="PaymentTerm_termDescription" value="<%=paymentTerm.getTermDescription()%>" size=70 maxlength=60></td>
		</tr>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td width=100px align=right nowrap>Payment Type:&nbsp;</td>
					<td colspan=2>
						<br>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td>Payment is due:</td>
						</tr>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td height=1px class=darkShadow width=100% colspan=3><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
								</tr>
								<tr>
									<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
									<td width=100%>
										<table border=0 cellpadding=0 cellspacing=0>
										<tr><td><input type=radio name="termType" value="N" <% if (paymentTerm.getTermTypeFlag().compareTo(new BigDecimal(0)) == 0) { %> checked <% } %> onclick="setTermTypeFlag('0');">in a fixed number of days&nbsp;</td></tr>
										<tr><td><input type=radio name="termType" value="N" <% if (paymentTerm.getTermTypeFlag().compareTo(new BigDecimal(0)) > 0) { %> checked <% } %> onclick="setTermTypeFlag('1');">by a particular date of the following month&nbsp;</td></tr>
										</table>
									</td>
									<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
								</tr>
								<tr>
									<td height=1px class=darkShadow width=100% colspan=3><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan=3><br></td>
				</tr>
				<tr>
					<td nowrap colspan=2 align=right>Payment Days or Date:&nbsp;</td>
					<td align=right><input type=text name="PaymentTerm_termDays" value="<%=paymentTerm.getTermDays()%>" size=10></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td nowrap width=200px align=right>Discount:&nbsp;</td>
					<td align=right><input type=text name="PaymentTerm_discount" value="<%=paymentTerm.getDiscount()%>" size=10></td>
				</tr>
				<tr>
					<td nowrap colspan=2 align=right>Number of days over which discount extends:&nbsp;</td>
					<td align=right><input type=text name="PaymentTerm_discountDays" value="<%=paymentTerm.getDiscountDays()%>" size=10></td>
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
						<select name="PaymentTerm_status" onchange="setStatus();">
							<option value="01" <% if (paymentTerm.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (paymentTerm.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (paymentTerm.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="PaymentTerm_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(paymentTerm.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="PaymentTerm_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(paymentTerm.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('PaymentTerm_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('PaymentTerm_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="PaymentTerm_owner" tabindex=51 size=30 maxlength=15 value="<%=paymentTerm.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newPaymentTerm) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'PaymentTermUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'PaymentTermAdd'); void(0);">
<%	} %>
		<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "paymentterm-admin";
	setNavCookie("/admin/systemtables/paymentterm.jsp", "PaymentTermRetrieveById", "Payment Term <%=paymentTerm.getTermsCode()%>");

	var status = "<%=paymentTerm.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newPaymentTerm) { %>
			frm.PaymentTerm_termDescription.focus();
<%	} else { %>
			frm.PaymentTerm_termsCode.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.PaymentTerm_status[frm.PaymentTerm_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setTermTypeFlag(x)
	{
		frm.PaymentTerm_termTypeFlag.value = x;
	}

// End Hide script -->
</SCRIPT>