<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PurchaseCard purchaseCard = (PurchaseCard) request.getAttribute("purchaseCard");
	boolean newPurchaseCard = false;

	if (purchaseCard == null)
	{
		purchaseCard = new PurchaseCard();
		purchaseCard.setPcardCode("") ;
		purchaseCard.setPcardNumber("") ;
		purchaseCard.setPcardSecureCode("") ;
		purchaseCard.setPcardName("");
		purchaseCard.setPcardExpires("") ;
		purchaseCard.setPcardHolder("");
		purchaseCard.setDateEntered(d_today);
		purchaseCard.setDateExpires(d_today);
		purchaseCard.setOwner(uid);
		purchaseCard.setStatus("02");
		newPurchaseCard = true;
	}
	else
	{
	    String newPurchaseCardString = HiltonUtility.ckNull((String) request.getAttribute("newPurchaseCard"));
		if (newPurchaseCardString.equals("Y"))
		{
	      newPurchaseCard = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, purchaseCard.getOwner());
	String duplicatePurchaseCardErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicatePurchaseCardErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicatePurchaseCardFailurePage" value="/admin/systemtables/purchasecard.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Purchase Card</div>
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

<%	if (!HiltonUtility.isEmpty(duplicatePurchaseCardErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicatePurchaseCardErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Card Number Alias:&nbsp;</td>
			<td><input type="text" name="PurchaseCard_pcardCode" value="<%=purchaseCard.getPcardCode()%>" size=25 maxlength=15 <% if (! newPurchaseCard) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newPurchaseCard) { %>
				<a href="javascript: if (verifyAction('Delete this purchase card?')) { doSubmit('/browse/browse_sys_tables.jsp', 'PurchaseCardDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Purchase Card</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Card Number:&nbsp;</td>
			<td colspan=2><input type="text" name="PurchaseCard_pcardNumber" value="<%=purchaseCard.getPcardNumber()%>" size=35 maxlength=20></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Card Expires:&nbsp;</td>
			<td colspan=2><input type="text" name="PurchaseCard_pcardExpires" value="<%=purchaseCard.getPcardExpires()%>" size=15 maxlength=5></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Card Security Code:&nbsp;</td>
			<td colspan=2><input type="text" name="PurchaseCard_pcardSecureCode" value="<%=purchaseCard.getPcardSecureCode()%>" size=15 maxlength=4></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Card Type:&nbsp;</td>
			<td colspan=2><input type="text" name="PurchaseCard_pcardName" value="<%=purchaseCard.getPcardName()%>" size=35 maxlength=20></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Card Name:&nbsp;</td>
			<td colspan=2><input type="text" name="PurchaseCard_pcardHolder" value="<%=purchaseCard.getPcardHolder()%>" size=70 maxlength=45></td>
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
						<select name="PurchaseCard_status" onchange="setStatus();">
							<option value="01" <% if (purchaseCard.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (purchaseCard.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (purchaseCard.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="PurchaseCard_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(purchaseCard.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="PurchaseCard_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(purchaseCard.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('PurchaseCard_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('PurchaseCard_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="PurchaseCard_owner" tabindex=51 size=30 maxlength=15 value="<%=purchaseCard.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newPurchaseCard) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'PurchaseCardUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'PurchaseCardAdd'); void(0);">
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
	frm.browseName.value = "purchasecard-admin";
	setNavCookie("/admin/systemtables/purchasecard.jsp", "PurchaseCardRetrieveById", "Purchase Card <%=purchaseCard.getPcardCode()%>");

	var status = "<%=purchaseCard.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newPurchaseCard) { %>
			frm.PurchaseCard_pcardNumber.focus();
<%	} else { %>
			frm.PurchaseCard_pcardCode.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.PurchaseCard_status[frm.PurchaseCard_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

// End Hide script -->
</SCRIPT>