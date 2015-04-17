<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String  dualUOMRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	Commodity commodity = (Commodity) request.getAttribute("commodity");
	boolean newCommodity = false;

	if (commodity == null)
	{
		commodity = new Commodity();
		commodity.setDateEntered(d_today);
		commodity.setDateExpires(d_today);
		commodity.setOwner(uid);
		commodity.setStatus("02");
		commodity.setCommodityType("BO");
		commodity.setTaxable("Y");
		newCommodity = true;
	}
	else
	{
	    String newCommodityString = HiltonUtility.ckNull((String) request.getAttribute("newCommodity"));
		if (newCommodityString.equals("Y"))
		{
	      newCommodity = true;
	    }
	}

	String s_receipt_required = commodity.getReceiptRequired();

	if (HiltonUtility.isEmpty(commodity.getTaxable())) {
		commodity.setTaxable("Y");
	}

	String s_commodity = commodity.getCommodity();

	UserProfile owner = UserManager.getInstance().getUser(oid, commodity.getOwner());
	UserProfile buyer = UserManager.getInstance().getUser(oid, commodity.getBuyerCode());

	String duplicateCommodityErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateCommodityErrorMsg"));

	List insCategoryLevelList = (List) request.getAttribute("insCategoryLevelList");
	String insCategoryLevelActive = propertiesManager.getProperty("VENDOR", "INSCATLVLACTIVE", "N");
%>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="Commodity_asset" value="<%=commodity.getAsset()%>"/>
<tsa:hidden name="Commodity_failsafe" value="<%=commodity.getFailsafe()%>"/>
<tsa:hidden name="Commodity_vchFailsafe" value="<%=commodity.getVchFailsafe()%>"/>
<tsa:hidden name="Commodity_commodityType" value="<%=commodity.getCommodityType()%>"/>
<tsa:hidden name="Commodity_duomRequired" value="<%=commodity.getDuomRequired()%>"/>
<tsa:hidden name="Commodity_serialNumbersRequired" value="<%=commodity.getSerialNumbersRequired()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=commodity.getIcAccount()%>"/>
<tsa:hidden name="duplicateCommodityFailurePage" value="/admin/systemtables/commodity.jsp"/>
<tsa:hidden name="VendorCommRel_commodityCode" value="<%=commodity.getCommodity()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commodity", "Commodity Code")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
<%	if (! newCommodity) { %>
		<tr>
			<td align=right nowrap><b><a href="javascript: doSubmit('/admin/systemtables/commodity_accounts.jsp', 'AccountRetrieveByLine'); void(0);">Accounts</a></b></td>
		</tr>
<%	} %>
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

<%	if (!HiltonUtility.isEmpty(duplicateCommodityErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateCommodityErrorMsg%></td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=100px align=right nowrap height=18px>Code:&nbsp;</td>
			<td colspan=2><input type="text" name="Commodity_commodity" value="<%=commodity.getCommodity()%>" size=25 maxlength=15 <% if (! newCommodity) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newCommodity && role.getAccessRights("COMM") > 3) { %>
				<a href="javascript: if (verifyAction('Delete this <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commodity", "Commodity Code")%>?')) { browseCommodityAdmin('CommodityDelete'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Commodity</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap>Description:&nbsp;</td>
			<td colspan=3><input type="text" name="Commodity_description" value="<%=commodity.getDescription()%>" size=70 maxlength=255></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "comm-taxable")%>>
			<td <%=HtmlWriter.isVisible(oid, "comm-taxable")%> nowrap align=right valign=middle><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comm-taxable", "Taxable")%>:&nbsp;</td>
			<td>
				<input type="checkbox" name="c_checkbox" <% if (commodity.getTaxable().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox(frm.Commodity_taxable, 0)">
				<tsa:hidden name="Commodity_taxable" value="<%=commodity.getTaxable()%>"/></input>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "comm-taxCode")%>>
			<td width=100px align=right nowrap><a href="javascript: browseLookup('Commodity_taxCode', 'taxcode'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comm-taxCode", "Tax Code", true)%>:</a>&nbsp;</td>
			<td colspan=3><input type="text" name="Commodity_taxCode" value="<%=commodity.getTaxCode()%>" size=30 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=100px align=right nowrap><a href="javascript: browseLookup('Commodity_buyerCode', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerCode", "Buyer Code", true)%>:</a>&nbsp;</td>
			<td colspan=2><input type="text" name="Commodity_buyerCode" value="<%=commodity.getBuyerCode()%>" size=30 maxlength=15 onchange="upperCase(this); getNewInfo('buyer', this);"></td>
			<%	if (oid.equalsIgnoreCase("bly07P")) {%>
			<td><a href="javascript: viewCommodityDepartmentBuyer(); void(0);">Buyers by <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%>:</a>&nbsp;</td>
			<%} %>
		</tr>
		<tr>
			<td width=100px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerName", "Buyer Name")%>:&nbsp;</td>
			<td colspan=3><input type="text" name="as_buyerName" value="<%=buyer.getDisplayName()%>" size=30 disabled></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "comm-receiptOptions")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comm-receiptOptions", "Receipt Options",true)%>:&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "comm-receiptOptions")%>>
								<select id="Commodity_receiptRequired" tabindex=10 name="Commodity_receiptRequired" size=1>
		<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("X") || HiltonUtility.isEmpty(s_receipt_required)) {%> SELECTED <%}%> value=""></option>
		<%	}
			if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("R")) {%> SELECTED <%}%> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptrequired", "Receipt Required", false)%></option>
		<%	}
			if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("P")) {%> SELECTED <%}%> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyreceived", "Previously Received", false)%></option>
		<%	}
			if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("N")) {%> SELECTED <%}%> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noreceiptrequired", "No Receipt Required", false)%></option>
		<%	}
			if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
									<option <% if (s_receipt_required.equals("E")) {%> SELECTED <%}%> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enduserreceipt", "End User Receipt", false)%></option>
		<%	}%>
							</select>
							</td>
						</tr>
		<tr>
			<td width=100px align=right nowrap>Asset:&nbsp;</td>
			<td>
				<input type="checkbox" name="c_checkbox"
				<% if (commodity.getAsset().equalsIgnoreCase("Y")) { %> checked <% } %> onClick="setCheckbox('Commodity_asset', 1);">
			</td>
			<td align=right nowrap>Serial Numbers Required:&nbsp;</td>
			<td>
				<input type="checkbox" name="serialNumbersRequired_checkbox"
				<% if (commodity.getSerialNumbersRequired().equalsIgnoreCase("Y")) { %> checked <% } %>
					onClick="setSerialNumbersRequiredCheckbox();">
			</td>
		</tr>
		<%if (dualUOMRequired.equals("Y")) { %>
		<tr>
			<td colspan=4 nowrap>
				Secondary Unit of Measure/Qty Required:&nbsp;
				<input type="checkbox" name="c_checkboxDuomRequired"
					<% if (commodity.getDuomRequired().equalsIgnoreCase("Y")) { %> checked <% } %>
					onClick="setCheckboxDuomRequired();">
			</td>
		</tr>
		<% } %>
		<tr>
			<td colspan=4 align=center>
				<br>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td nowrap valign=top align=right>
						Variance Factor:&nbsp;<input type="text" name="Commodity_variance" value="<%=commodity.getVariance()%>" size=5 onkeypress="onlyNumbers();">%
						&nbsp;&nbsp;&nbsp;At Receiving:&nbsp;
					</td>
					<td>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td height=1px class=darkShadow width=100% colspan=3><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
						</tr>
						<tr>
							<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
							<td width=100%>
								<table border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td><input type=radio name="failsafe" value="N" <% if (commodity.getFailsafe().equals("N")) { %> checked <% } %> onclick="setFailsafe('N');">Do Not Check&nbsp;</td>
								</tr>
								<tr>
									<td><input type=radio name="failsafe" value="Y" <% if (commodity.getFailsafe().equals("Y")) { %> checked <% } %> onclick="setFailsafe('Y');">Enforce Variance&nbsp;</td>
								</tr>
								<tr>
									<td><input type=radio name="failsafe" value="W" <% if (commodity.getFailsafe().equals("W")) { %> checked <% } %> onclick="setFailsafe('W');">Warning Only&nbsp;</td>
								</tr>
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
				<tr><td><br></td></tr>
				<tr>
					<td nowrap valign=top align=right>
						Invoice Variance Factor:&nbsp;<input type="text" name="Commodity_vchVariance" value="<%=commodity.getVchVariance()%>" size=5 onkeypress="onlyNumbers();">%
						&nbsp;&nbsp;&nbsp;At Invoicing:&nbsp;
					</td>
					<td>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td height=1px class=darkShadow width=100% colspan=3><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
						</tr>
						<tr>
							<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
							<td width=100%>
								<table border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td><input type=radio name="vchFailsafe" value="N" <% if (commodity.getVchFailsafe().equals("N")) { %> checked <% } %> onclick="setVchFailsafe('N');">Do Not Check&nbsp;</td>
								</tr>
								<tr>
									<td><input type=radio name="vchFailsafe" value="Y" <% if (commodity.getVchFailsafe().equals("Y")) { %> checked <% } %> onclick="setVchFailsafe('Y');">Enforce Variance&nbsp;</td>
								</tr>
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
				<% if (oid.equalsIgnoreCase("msg07p")) { %>
				<tr><td><br></td></tr>
				<tr>
					<td nowrap valign=top align=right>Commodity Type:&nbsp;</td>
					<td>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td height=1px class=darkShadow width=100% colspan=3><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
						</tr>
						<tr>
							<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1px" height="1px" /></td>
							<td width=100%>
								<table border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td><input type=radio name="type" value="SP" <% if (commodity.getCommodityType().equals("SP")) { %> checked <% } %> onclick="setCommodityType('SP');">Spend&nbsp;</td>
								</tr>
								<tr>
									<td><input type=radio name="type" value="SU" <% if (commodity.getCommodityType().equals("SU")) { %> checked <% } %> onclick="setCommodityType('SU');">Supplier&nbsp;</td>
								</tr>
								<tr>
									<td><input type=radio name="type" value="BO" <% if (commodity.getCommodityType().equals("BO")) { %> checked <% } %> onclick="setCommodityType('BO');">Both&nbsp;</td>
								</tr>
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
				<% } %>
				<%	if (insCategoryLevelActive.equalsIgnoreCase("Y")) { %>
				<tr><td><br></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "comm-iclLevel")%>>
					<td nowrap align=left>
						<a href="javascript: browseLookup('Commodity_iclLevel', 'inscategorylevel'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comm-iclLevel", "Insurance Category Level", true)%>:</a>
						<input type="text" name="Commodity_iclLevel" value="<%=commodity.getIclLevel()%>" size=10 maxlength=4 onChange="alphaNumericCheck(this); addUp(this, 0);">
					</td>
					<td>&nbsp;</td>
				</tr>
				<% } %>
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
						<select name="Commodity_status" onchange="setStatus();">
							<option value="01" <% if (commodity.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (commodity.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (commodity.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="Commodity_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(commodity.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr id="dateExpires">
					<td align=right>Date Expires:&nbsp;</td>
					<td>
						<input type=text name="Commodity_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(commodity.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('Commodity_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('Commodity_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="Commodity_owner" tabindex=51 size=30 maxlength=15 value="<%=commodity.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (role.getAccessRights("COMM") > 1) { %>
	<td width=50% align=center>
<%	if (! newCommodity) { %>
		<div id="pxbutton"><a href="javascript:  browseCommodityAdmin('CommodityUpdate'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript:  browseCommodityAdmin('CommodityAdd'); void(0);">Save</a></div>
<%	} %>
	</td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browseCommodityAdmin(''); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "commodity-admin";
	setNavCookie("/admin/systemtables/commodity.jsp", "CommodityRetrieveById", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commodity", "Commodity Code")%> <%=commodity.getCommodity()%>");

	var status = "<%=commodity.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newCommodity) { %>
			frm.Commodity_description.focus();
<%	} else { %>
			frm.Commodity_commodity.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.Commodity_status[frm.Commodity_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			document.getElementById("dateExpires").style.display = "";
		}
		else
		{
			document.getElementById("dateExpires").style.display = "none";
		}
	}

	function setSerialNumbersRequiredCheckbox()
	{
		frm.Commodity_serialNumbersRequired.value = 'N';
		if ( frm.serialNumbersRequired_checkbox.checked )
		{
			frm.Commodity_serialNumbersRequired.value = 'Y';
		}
		return true;
	}

	function setCheckboxDuomRequired()
	{
		frm.Commodity_duomRequired.value = 'N';
		if ( frm.c_checkboxDuomRequired.checked )
		{
			frm.Commodity_duomRequired.value = 'Y';
		}
		return true;
	}

	function setFailsafe(x)
	{
		frm.Commodity_failsafe.value = x;
	}

	function setVchFailsafe(x)
	{
		frm.Commodity_vchFailsafe.value = x;
	}

	function setCommodityType(x)
	{
		frm.Commodity_commodityType.value = x;
	}

	function onlyNumbers(){
		var key=window.event.keyCode;
		if (key < 48 || key > 57){
			window.event.keyCode=0;
		}
	}

	function browseCommodityAdmin(updateHandler)
	{
		var commodityType = "<%=HiltonUtility.ckNull(PropertiesManager.getInstance(oid).getProperty("MISC", "CommodityType", ""))%>";

		var validate = true;

		if (!isEmpty(updateHandler)) {
			updateHandler = updateHandler + ";";
			if (updateHandler.indexOf("CommodityUpdate") >= 0 && "<%=insCategoryLevelActive%>" == "Y")
			{
				updateHandler = updateHandler + "VendorUpdateIclLevel;";
			}
			validate = validateInsCategoryLevel();
		}

		if (validate)
		{
			if (!isEmpty(commodityType)) {
				doSubmit('browse/browse_commodity_tree_admin.jsp', updateHandler + 'CommodityRetrieveTree');
			} else {
				doSubmit('browse/browse_sys_tables.jsp', updateHandler + 'BrowseRetrieve')
			}
		}
		else
		{
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comm-iclLevel", "Insurance Category Level")%> invalid");
		}
	}

	function viewCommodityDepartmentBuyer()
   {
     var newInputField = "<input type='hidden' name='Commodity_commodity' value='" + "<%=s_commodity %>" + "'>";
     var newInputField = "<input type='hidden' name='CommodityDepartmentBuyer_commodity' value='" + "<%=s_commodity %>" + "'>";
     setHiddenFields(newInputField);
     doSubmit('/admin/systemtables/commodity_bydeparment.jsp', 'CommodityDepartmentBuyerRetrieveByCommodity');
	 //doSubmit('/admin/systemtables/commodity.jsp', 'CommodityRetrieveById');
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

	function validateInsCategoryLevel()
	{
		<%	if (insCategoryLevelActive.equalsIgnoreCase("Y")) { %>
			var validate = false;
			frm.Commodity_iclLevel.value = trim(frm.Commodity_iclLevel);

			<%	if (insCategoryLevelList != null) {
					for (int i = 0; i < insCategoryLevelList.size(); i++) {
						BigDecimal iclLevel = (BigDecimal)insCategoryLevelList.get(i); %>
						if ("<%=iclLevel%>" == frm.Commodity_iclLevel.value) {
							validate = true;
						}
			<%		}
				} %>

			return validate;
		<%	} else { %>
			return true;
		<%	} %>
	}

// End Hide script -->
</SCRIPT>
