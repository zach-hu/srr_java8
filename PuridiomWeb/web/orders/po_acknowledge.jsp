<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String errorMessage = HiltonUtility.ckNull((String) request.getAttribute("errorMessage"));
	String vendorName = VendorManager.getInstance().getVendorName(oid, poHeader.getVendorId());
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_title = OrderType.toString(poHeader.getPoType(), oid);
%>
<br>
<br>
<br>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<% if (!HiltonUtility.isEmpty(errorMessage))
	{
%>
    <tr>
      <td align=center width=680px>
        <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseRow>
          <tr>
            <td colspan=3><br><br></td>
          </tr>
          <tr class = 'error'>
            <td align=center><%= errorMessage %>.</td>
		  </tr>
        </table>
      </td>
    </tr>
<% } else {
%>
	<tr>
		<td width="5%">&nbsp;</td>
		<td width="90%" align="center" valign="top">
			<table border="1" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr>
					<td align="center">
					Thank you, <%= s_po_title %> <span class="hdr12">#<%= s_po_number %></span>
					has been acknowledge by <%= vendorName %>!
					</td>
				</tr>
			</table>
		</td>
		<td width="5%">&nbsp;</td>
	</tr>
<% } %>
</table>
<br>
<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td align=center>

<% 	List poHeaderList = (List) request.getAttribute("poPendingAcknowledgmentList");

	if (poHeaderList == null)
	{
		poHeaderList = new ArrayList();
	}

 	if (poHeaderList.size() > 0)
	{
%>
		Other Orders Waiting My Acknowledgment
		<br>
		<br>
      				<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0">
      					<tr>
      						<td valign="top" align="center" width="680px">
		      					<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
									<tr>
										<td width=5px>&nbsp;</td>
										<td width=670px class=browseHdrDk align=center valign=top>
											<table border=0 cellspacing=0 cellpadding=3 width=665px>
												<tr>
													<td nowrap height=18px class=browseHdrDk width=25% align=left> Type </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Order# </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Order Date </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Total </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Sent </td>
												</tr>
											</table>
											<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
											<%
											for(int j = 0; j < poHeaderList.size(); j++)
											{
												  Object poPendingAcknowledgment[] = (Object[]) poHeaderList.get(j);
												  PoHeader order = (PoHeader) poPendingAcknowledgment[0];
												  ApprovalLink approvalLink = (ApprovalLink) poPendingAcknowledgment[1];
											 %>
												  <table border=0 cellspacing=0 cellpadding=2 width=665px>
								    					<td align=left width=25% valign=top><%= OrderType.toString(order.getPoType(), oid) %></td>
														<td align=left width=15% valign=top><%= order.getPoNumber() %></td>
														<td align=left width=15% valign=top><%= HiltonUtility.getFormattedDate(order.getPoDate(), oid, userDateFormat) %></td>
														<td align=left width=15% valign=top><%= HiltonUtility.getFormattedDollar(order.getTotal(), oid) %></td>
														<td align=left width=15% valign=top><%= approvalLink.getLogDate() %></td>
													</table>
								        	<%
											}
											%>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<% } %>
		</td>
	</tr>
</table>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	hideArea("navTable");
	displayArea("menubarSpacer");
// End Hide script -->
</SCRIPT>