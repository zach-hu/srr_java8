<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.po.tasks.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.Date.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PoBlanketInfo blanketInfo = (PoBlanketInfo) request.getAttribute("blanketInfo");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String s_buyer_code = poHeader.getBuyerCode();

	if (Utility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (Utility.isEmpty(s_po_status))		{	s_po_status = "3000";									}

	boolean bAllowEdit = true;
	if (role.getAccessRights("PO") < 2 || s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 || s_po_type.equals("RO") || s_po_type.equals("DR") || s_po_type.equals("SR"))
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equalsIgnoreCase(s_buyer_code))
	{
		bAllowEdit = false;
	}

	String	s_current_process = "BLANKET_INFO";
	String	s_current_page = "/orders/po_blanket.jsp";
	String	s_current_method = "PoHeaderUpdate";
	String	s_current_process_method = "";

	String linkPriorOrder_icPoHeader = "";
	String linkNextOrder_icPoHeader = "";

	if ( request.getAttribute("linkPriorOrder_icPoHeader") != null )
	{
		linkPriorOrder_icPoHeader = ((BigDecimal) request.getAttribute("linkPriorOrder_icPoHeader")).toString();
	}
	if ( request.getAttribute("linkNextOrder_icPoHeader") != null )
	{
		linkNextOrder_icPoHeader = ((BigDecimal) request.getAttribute("linkNextOrder_icPoHeader")).toString();
	}
	BigDecimal BLimit = blanketInfo.getPoBlanket();
	BigDecimal BRelease = blanketInfo.getPoReleaseLimit();
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
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>


<tsa:hidden name="PoHeader_linkPriorOrder_icPoHeader" value="<%=linkPriorOrder_icPoHeader%>"/>
<tsa:hidden name="PoHeader_linkNextOrder_icPoHeader" value="<%=linkNextOrder_icPoHeader%>"/>
<tsa:hidden name="PoHeader_linkPriorOrder_oldIcPoHeader" value="<%=linkPriorOrder_icPoHeader%>"/>
<tsa:hidden name="PoHeader_linkNextOrder_oldIcPoHeader" value="<%=linkNextOrder_icPoHeader%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>


<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="blanket-information" defaultString="Blanket Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><tsa:label labelName="order-number" defaultString="Order #" />:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="release-number" defaultString="Release #" />:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="revision-number" defaultString="Revision #" />:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
		</tr>
		</table>
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
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
					<td align=right nowrap><tsa:label labelName="effectiveDate" defaultString="Effective Date" checkRequired="true" />:&nbsp;</td>
					<td nowrap>
						<tsa:input type="mintext" name="PoHeader_effectiveDate" tabIndex="1" value="<%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid, userDateFormat)%>" onchange="checkDate(this);" />
						<a href="javascript: show_calendar('PoHeader_effectiveDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "cnt-expirationDate")%>>
					<td align=right nowrap><tsa:label labelName="expirationDate" defaultString="Expiration Date" checkRequired="true" />:&nbsp;</td>
					<td nowrap>
						<tsa:input type="mintext" name="PoHeader_expirationDate" tabIndex="3" value="<%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid, userDateFormat)%>" onchange="checkDate(this);" />
						<a href="javascript: show_calendar('PoHeader_expirationDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "autoImport")%>>
		          <td align=right nowrap><tsa:label labelName="autoImport" defaultString="Auto Import" checkRequired="true" />:&nbsp;</td>
		          <td>
		            <input type=checkbox name="c_checkbox" value="Y" <% if (HiltonUtility.ckNull(poHeader.getAutoImport()).equals("Y")) { %> CHECKED <% } %> ONCLICK="setCheckbox(frm.PoHeader_autoImport, 0);">
		            <tsa:hidden name="PoHeader_autoImport" value="<%=poHeader.getAutoImport()%>"/>
		          </td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "autoPayment")%>>
		          <td align=right nowrap><tsa:label labelName="autoPayment" defaultString="Auto Payment" checkRequired="true" />:&nbsp;</td>
		          <td>
		            <input type=checkbox name="c_checkbox" value="Y" <% if (HiltonUtility.ckNull(poHeader.getAutoPayment()).equals("Y")) { %> CHECKED <% } %> ONCLICK="setCheckbox(frm.PoHeader_autoPayment, 1);">
		            <tsa:hidden name="PoHeader_autoPayment" value="<%=poHeader.getAutoPayment()%>"/>
		          </td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "autoRelease")%>>
		          <td align=right nowrap><tsa:label labelName="autoRelease" defaultString="Auto Release" checkRequired="true" />:&nbsp;</td>
		          <td>
		            <input type=checkbox name="c_checkbox" value="Y" <% if (HiltonUtility.ckNull(poHeader.getAutoRelease()).equals("Y")) { %> CHECKED <% } %> ONCLICK="setCheckbox(frm.PoHeader_autoRelease, 2);">
		            <tsa:hidden name="PoHeader_autoRelease" value="<%=poHeader.getAutoRelease()%>"/>
		          </td>
		        </tr>
		        <!--tr <%=HtmlWriter.isVisible(oid, "autoInterval")%>>
					<td align=right nowrap><tsa:label labelName="autoInterval" defaultString="Release Interval" />:&nbsp;</td>
					<td nowrap>
						<input type=text name="PoHeader_autoInterval" size=5 value="<%=Utility.getBigDecimalFormatted(poHeader.getAutoInterval(), 0)%>" onfocus="this.blur();" style="text-align:right" maxlength="3"> (days)
					</td>
				</tr-->
				<tr <%=HtmlWriter.isVisible(oid, "interval")%>>
		          <td align=right nowrap><tsa:label labelName="interval" defaultString="Interval" checkRequired="true" />:&nbsp;</td>
		          <td>
		            <select name="PoHeader_interval">
						<option value="null"></option>
						<option value="Weekly">Weekly</option>
						<option value="Bi-Weekly">Bi-Weekly</option>
						<option value="Semi-Monthly">Semi-Monthly</option>
						<option value="Monthly">Monthly</option>
						<option value="Quarterly">Quarterly</option>
					</select>
		          </td>
		        </tr>
				<tr><td colspan=2><br></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "orderTotal")%>>
					<td align=right nowrap><tsa:label labelName="orderTotal" defaultString="Order Total" />:&nbsp;</td>
					<td nowrap><tsa:input type="mintext" name="PoHeader_orderTotal" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getOrderTotal(), oid)%>" onfocus="this.blur();" disabled="true" style="text-align:right" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "availableOrder")%>>
					<td align=right nowrap><tsa:label labelName="availableOrder" defaultString="Available Order" />:&nbsp;</td>
					<td><tsa:input type="mintext" name="PoHeader_availableOrder" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getAvailableOrder(), oid)%>" onfocus="this.blur();" disabled="true" style="text-align:right" /></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<% if(oid.equalsIgnoreCase("wpc08p")) {
					if (BLimit.compareTo(new BigDecimal(0)) > 0){ %>
					<tr <%=HtmlWriter.isVisible(oid, "blanketLimit")%>>
						<td align=right nowrap><tsa:label labelName="blanketLimit" defaultString="Blanket Limit" checkRequired="true" />:&nbsp;</td>
						<td><tsa:input type="mintext" name="PoHeader_blanketLimit" tabIndex="15" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoBlanket(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
					</tr>

					<% }
					else { %>
						<tr <%=HtmlWriter.isVisible(oid, "blanketLimit")%>>
							<td align=right nowrap><tsa:label labelName="blanketLimit" defaultString="Blanket Limit" checkRequired="true" />:&nbsp;</td>
							<td><tsa:input type="mintext" name="PoHeader_blanketLimit" tabIndex="15" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
						</tr>
					<% } %>


					<% if (BRelease.compareTo(new BigDecimal(0)) > 0) { %>
						<tr <%=HtmlWriter.isVisible(oid, "releaseLimit")%>>
						<td align=right nowrap><tsa:label labelName="releaseLimit" defaultString="Release Limit" checkRequired="true" />:&nbsp;</td>
						<td><tsa:input type="mintext" name="PoHeader_releaseLimit" tabIndex="17" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoReleaseLimit(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
					</tr>

					<% }
					else { %>
						<tr <%=HtmlWriter.isVisible(oid, "releaseLimit")%>>
							<td align=right nowrap><tsa:label labelName="releaseLimit" defaultString="Release Limit" checkRequired="true" />:&nbsp;</td>
							<td><tsa:input type="mintext" name="PoHeader_releaseLimit" tabIndex="15" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
						</tr>
					<% }
					}
				else {%>
					<tr <%=HtmlWriter.isVisible(oid, "blanketLimit")%>>
						<td align=right nowrap><tsa:label labelName="blanketLimit" defaultString="Blanket Limit" checkRequired="true" />:&nbsp;</td>
						<td><tsa:input type="mintext" name="PoHeader_blanketLimit" tabIndex="15" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoBlanket(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "releaseLimit")%>>
						<td align=right nowrap><tsa:label labelName="releaseLimit" defaultString="Release Limit" checkRequired="true" />:&nbsp;</td>
						<td><tsa:input type="mintext" name="PoHeader_releaseLimit" tabIndex="17" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoReleaseLimit(), oid)%>" onchange="correctNumber(this);" style="text-align:right" /></td>
					</tr>
				<% } %>
				<tr><td colspan=2>&nbsp;</td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "createSchedule")%>>
		          <td align="left" colspan="2">
		          	<a href="javascript: createSchedule(); void(0);" title="click to create the schedule based on the selected interval.">
		          		<tsa:label labelName="createSchedule" defaultString="Create Schedule" checkRequired="true" />
		          	</a>
		          </td>
		        </tr>
				<tr><td colspan=2><br></td></tr>

				<tr <%=HtmlWriter.isVisible(oid, "blanketSupercedes")%>>
					<td align=right nowrap width=50%><a href="javascript: browseLookup('PoHeader_linkPriorOrder', 'supercedes'); void(0);" title="Click here to choose the supercedes for this order."><tsa:label labelName="blanketSupercedes" defaultString="Blanket Supercedes" checkRequired="true" /></a>:&nbsp;</td>
					<td width=50%><tsa:input type="maxtext" name="PoHeader_linkPriorOrder" tabIndex="18" maxLength="30" value="<%= poHeader.getLinkPriorOrder()%>" onfocus="this.blur();" disabled="true" /></td>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "blanketSupercededBy")%>>
					<td align=right nowrap width=50%><a href="javascript: browseLookup('PoHeader_linkNextOrder', 'supercededBy'); void(0);" title="Click here to choose the superceded by for this order."><tsa:label labelName="blanketSupercededBy" defaultString="Blanket Superceded By" checkRequired="true" /></a>:&nbsp;</td>
					<td width=50%><tsa:input type="maxtext" name="PoHeader_linkNextOrder" tabIndex="19" maxLength="30" value="<%= poHeader.getLinkNextOrder()%>"  onfocus="this.blur();"  disabled="true" /></td>
				</tr>

				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=3 align=center>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr><td colspan=2><br></td></tr>
				<tr><td colspan=2 class=hdr12 align=center><b><small><tsa:label labelName="blanket-totals" defaultString="BLANKET TOTALS" /></small></b></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "numberOfReleases")%>>
					<td nowrap align=right><tsa:label labelName="numberOfReleases" defaultString="Number of Releases" />:&nbsp;</td>
					<td><tsa:input type="midtext" name="PoHeader_releaseCount" value="<%=Utility.getBigDecimalFormatted(blanketInfo.getReleaseCount(), 0)%>" onfocus="this.blur();" disabled="true" style="text-align:right" /></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "releaseTotal")%>>
					<td align=right nowrap><tsa:label labelName="releaseTotal" defaultString="Release Total" />:&nbsp;</td>
					<td><tsa:input type="midtext" name="PoHeader_releaseTotal" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getReleaseTotal(), oid)%>" onfocus="this.blur();" disabled="true" style="text-align:right" /></td>
				</tr>
				<tr><td colspan=2>&nbsp;</td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "availableBlanket")%>>
					<td align=right nowrap><tsa:label labelName="availableBlanket" defaultString="Available Blanket" />:&nbsp;</td>
					<td><tsa:input type="midtext" name="PoHeader_availableBlanket" value="<%=HiltonUtility.getFormattedDollar(blanketInfo.getAvailableBlanket(), oid)%>" onfocus="this.blur();" disabled="true" style="text-align:right" /></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=3 align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equals("CLASSIC")) { %>
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

	var allowEdit;

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>

<%	if ( bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0 ) {
			if ( s_po_type.equals("BO") || s_po_type.equals("DO") || s_po_type.equals("SB") ) {	%>
				frm.PoHeader_linkPriorOrder.readOnly = false;
				frm.PoHeader_linkPriorOrder.contentEditable = true;
				frm.PoHeader_linkPriorOrder.disabled = false;
				frm.PoHeader_linkNextOrder.readOnly = false;
				frm.PoHeader_linkNextOrder.contentEditable = true;
				frm.PoHeader_linkNextOrder.disabled = false;
<%		}
		}	%>
	}

	function createSchedule()
	{
		var effectiveDate = frm.PoHeader_effectiveDate.value;
		var expirationDate = frm.PoHeader_expirationDate.value;
		var selectInterval = frm.PoHeader_interval
		var interval = selectInterval.options[selectInterval.selectedIndex].value;

		if (effectiveDate == '' || expirationDate == '' || interval == 'null') {
			var msg = '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "effectiveDate", "Effective Date")%>' + ', '
						+ '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "expirationDate", "Expiration Date")%>' + ' and '
						+ '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "interval", "Interval")%>' + ' are required to create schedule';
			alert(msg);
			return;
		}

		doSubmit('orders/po_review.jsp', 'PoHeaderUpdate;ScheduleAddFromInterval;PoRetrieve');
		//alert('effectiveDate: ' + effectiveDate + ' - ' + 'expirationDate: ' + expirationDate + ' - ' + 'interval: ' + interval);
	}

	function correctNumber(fld)
	{
		fld.value = nformat(eval(nfilter(fld)), 2);
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

// End Hide script -->
</SCRIPT>