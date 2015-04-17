<%@ page import="com.tsa.puridiom.steporder.*" %>


<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%><table border=0 cellpadding=0 cellspacing=0 align=right>
<tr><td colspan="3" height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" height="1px" width="100%"></td></tr>
<tr class="sidebar"><td colspan="3" height="5px">&nbsp;</td></tr>

<%
	HashMap processMap = new HashMap();
	String	s_process_code = "";
	String	s_process_link = "";
	String	s_process_label = "";
	String	s_process_method = "";
	String	s_previous_code = "";
	String	s_previous_link = "";
	String	s_previous_label = "";
	String	s_previous_method = "";
	String	s_next_code = "";
	String	s_next_link = "";
	String	s_next_label = "";
	String	s_next_method = "";
	String	accountPref = "";
	String	s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOPO", "N");
	String	s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOPO", "Y");
	String	orgReqType = poHeader.getOriginalReqType();

	boolean	b_current_process = false;

	int	i_step = 1;

	ProcessSteps steps = null;

	if (s_po_type.equals("BO"))		/*	Blanket Order	*/
	{
		steps = new ProcessSteps("posteps_bo", oid);
	}
	else if (s_po_type.equals("RO"))	/* Release Order */
	{
		steps = new ProcessSteps("posteps_ro", oid);
	}
	else if (s_po_type.equals("DO"))	/* Delivery Order */
	{
		steps = new ProcessSteps("posteps_do", oid);
	}
	else if (s_po_type.equals("DR"))	/* Delivery Release */
	{
		steps = new ProcessSteps("posteps_dr", oid);
	}
	else if (s_po_type.equals("SO"))	/* Service Order */
	{
		steps = new ProcessSteps("posteps_so", oid);
	}
	else if (s_po_type.equals("SB"))	/* Service Blanket */
	{
		steps = new ProcessSteps("posteps_sb", oid);
	}
	else if (s_po_type.equals("SR"))	/* Service Release */
	{
		steps = new ProcessSteps("posteps_sr", oid);
	}
	else if (s_po_type.equals("CT"))	/* Contract */
	{
		steps = new ProcessSteps("posteps_ct", oid);
	}
	else if (s_po_type.equals("PR"))	/* Contract */
	{
		steps = new ProcessSteps("posteps_pr", oid);
	}
	else		/* Purchase Order */
	{
		steps = new ProcessSteps("posteps_po", oid);
	}

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		s_process_link = steps.getLink(ip);
		s_process_label = steps.getLabel(ip);
		s_process_method = steps.getMethod(ip);


		if (s_process_code.equals("HEADER_VALIDATION") && s_po_status.compareTo(DocumentStatus.PO_APPROVING) >= 0) {
				// DO NOT DISPLAY VALIDATION STEP
				continue;
		}

		if (s_process_code.equals("HEADER_ACCOUNTS")) {
			if (orgReqType.equalsIgnoreCase("M")) {
				//DO NOT DISPLAY ACCOUNT STEP
				continue;
			}
		}


		processMap.put(s_process_code, "TRUE");

		if (s_current_process.equals(s_process_code))
		{
			b_current_process = true;
			s_current_process_method = s_process_method;

			if (ip > 0)
			{
				s_previous_code = steps.getProcess(ip - 1);
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod(ip - 1);
			}
			if ( (ip + 1) < steps.getSize())
			{
				s_next_code = steps.getProcess(ip + 1);
				s_next_link = steps.getLink(ip + 1);
				s_next_label = steps.getLabel(ip + 1);
				s_next_method = steps.getMethod(ip + 1);
			}
		}
		else if (s_current_process.indexOf("LINE") == 0 && (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM")))
		{
			b_current_process = false;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod (ip -1);
			}
			if ( (ip + 1) < steps.getSize())
			{
				s_next_link = steps.getLink(ip + 1);
				s_next_label = steps.getLabel(ip + 1);
				s_next_method = steps.getMethod(ip + 1);
			}
		}
		else
		{
			b_current_process = false;
		}

		if (b_current_process)
		{
%>
<tsa:hidden name="accountPref" value="<%=accountPref%>"/>
<TR HEIGHT="25px">
	<TD WIDTH="5px" CLASS="processOn">&nbsp;</TD>
	<TD CLASS="processOn">
		<div id="pxsmallbutton"><%=i_step%></div>
	</TD>
	<TD CLASS="processOn" NOWRAP>&nbsp;<tsa:label labelName="<%= s_process_label %>" defaultString="<%= s_process_label %>" checkRequired="false" />&nbsp;</TD>
</TR>
<%
		}
		else
		{
			if (s_process_code.equals("INSURANCE") || s_process_code.equals("COMPLIANCE")) { %>
<TR HEIGHT="25px">
	<TD CLASS="processOff" WIDTH="5px">&nbsp;</TD>
	<TD CLASS="processOff">
		<div id="pxsmallbutton"><A HREF="javascript: if (checkVendorInsurance('<%=s_process_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>'); } " class="processOff"><%=i_step%></A></div>
	</TD>
	<TD CLASS="processOff" NOWRAP>
		&nbsp;<A HREF="javascript: if (checkVendorInsurance('<%=s_process_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>'); } " class="processOff"><tsa:label labelName="<%= s_process_label%>" defaultString="<%= s_process_label %>" checkRequired="false" /></A>&nbsp;
	</TD>
</TR>
			<%} else {%>
<TR HEIGHT="25px">
	<TD CLASS="processOff" WIDTH="5px">&nbsp;</TD>
	<TD CLASS="processOff">
		<div id="pxsmallbutton"><A HREF="javascript: if (checkVendorInsurance('<%=s_process_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_process_link%>', 'PoHeaderRetrieveById;<%=s_current_method%>;<%=s_process_method%>'); } " class="processOff"><%=i_step%></A></div>
	</TD>
	<TD CLASS="processOff" NOWRAP>
		&nbsp;<A HREF="javascript: if (checkVendorInsurance('<%=s_process_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_process_link%>', 'PoHeaderRetrieveById;<%=s_current_method%>;<%=s_process_method%>'); } " class="processOff"><tsa:label labelName="<%= s_process_label%>" defaultString="<%= s_process_label %>" checkRequired="false" /></A>&nbsp;
	</TD>
</TR>
<%			}
		}
		i_step++;
	}
%>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>
<tr><td colspan="3" height="1px" class="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>

<tr>
	<td colspan="3">
		<table border="0" width="100%">
		<tr>
			<td align="left">
<%	if (s_previous_link.length() > 0) { %>
				<table border="0">
				<tr>
					<td class="processOn"><div id="pxsmallbutton"><a href="javascript: if (checkVendorInsurance('<%=s_previous_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>'); } "><<</a></div></td>
					<td class="processOn"><a href="javascript: if (checkVendorInsurance('<%=s_previous_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>'); } " class="processOn" title="<%=s_previous_label%>"><tsa:label labelName="back" defaultString="Back" /></a></td>
				</tr>
				</table>
<%	} %>
			</td>
			<td align="right">
<%
	//do not display a next link if the next link goes to validation and the po status is greater than 3005

	if (s_next_label.contains("validate") && Integer.parseInt(s_po_status) > Integer.parseInt(DocumentStatus.PO_REJECTED)) {
		s_next_link = "";
	}

	if (s_next_link.length() > 0) { %>
				<table border="0">
				<tr>
					<td class="processOn"><a href="javascript: if (checkVendorInsurance('<%=s_next_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>'); } " class="processOn" title="<%=s_next_label%>"><tsa:label labelName="next" defaultString="Next" /></a></td>
					<td class="processOn"><div id="pxsmallbutton"><a href="javascript: if (checkVendorInsurance('<%=s_next_code%>', '<%= headerEncoder.encodeForJavaScript(s_po_type) %>')<%if(s_current_page.equals("/orders/po_security.jsp")){%> && validation()<%}%>) { doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>'); } ">>></a></div></td>
				</tr>
				</table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>

<tr><td colspan="3" class="processOff"><br></td></tr>
<%	if (role.getAccessRights("PO") > 1)
	{
			if ( s_po_status.compareTo(DocumentStatus.CT_AWARDED) == 0 && s_current_page.equals("/orders/po_review.jsp") && !s_po_number.equals("N/A")) {%>
			<tr>
				<td colspan="3" class="processOff" align="center">
					<div id="createPoLink" style="visibility: visible;">
					<table>
					  <tr><td>
						  	<select name="newType" size="1" onchange=";">
					  		 	<option value="PO"><tsa:label labelName="purchaseOrder" defaultString="Purchase Order" /></option>
					  		 	<option value="BO"><tsa:label labelName="blanketOrder" defaultString="Blanket Order" /></option>
					  		 	<option value="SO"><tsa:label labelName="serviceOrder" defaultString="Service Order" /></option>
					  		 	<option value="SB"><tsa:label labelName="serviceBlanket" defaultString="Service Blanket" /></option>
					  		 	<option value="DO"><tsa:label labelName="deliverOrder" defaultString="Deliver Order" /></option>
					  		 	<option value="CT"><tsa:label labelName="contractRecord" defaultString="Contract Record" /></option></select></td>
						   <td>
							 	<div id="pxbutton"><a tabindex=52 href="javascript: createContractedPo(); void(0);" alt="Create PO from Contract">Create PO</a></div>
						   </td></tr>
					</table>
					</div>
				</td>
			</tr>
<%		}
			else if ( (s_po_status.compareTo(DocumentStatus.PO_APPROVING) < 0) || (bAdminChanges && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0) )
			{
				if ( !oid.equalsIgnoreCase("akdoc") || user.isAnAdminBuyer() || user.getUserId().equalsIgnoreCase(s_buyer_code) ) {	%>
					<tr>
					<td colspan="3" class="processOff" align="center">
					<% if ( s_current_page.equals("/orders/po_validation.jsp") && !HiltonUtility.isNA(s_po_number))
					{ %>
						<DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a tabindex=51 href="javascript: orderForward(); void(0);"><tsa:label labelName="poforward" defaultString="Forward/Award" /></a></div></DIV>
					<% } else if ( s_current_page.equals("/orders/po_security.jsp") ) { %>
							<div id="pxbutton"><a tabindex=51 href="javascript: submitThis(); void(0);">Save</a></div>
					<% } else {
						if(oid.equalsIgnoreCase("vse06p")){%>
		        			<div id="pxbutton"><a tabindex=50 href="javascript: poSaveCheckAccount(); void(0);">Save</a></div>
						<%} else if (s_po_number.equals("N/A") || !s_current_page.equals("/orders/po_review.jsp")) { %>
							<div id="pxbutton"><a tabindex=50 href="javascript: poSave(); void(0);">Save</a></div>
						<%}
					} %>
					</td>
					</tr>
			<%	}
			}
			//if ( s_current_page.equals("/orders/po_review.jsp") && (s_po_status.compareTo(DocumentStatus.PO_AWARDED) == 0) && (s_flag.equals("Y")))
//			{%>
				<tr>
				<td colspan="3" class="processOff" align="center">
				<div id="mxp_link" style="visibility: hidden;">
					<div id="pxbutton"><a tabindex=50 href="javascript: poMXP(); void(0);">Update MXP</a></div>
				</div>
				</td>
				</tr>
		<%	//}
	} %>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var autoPoNumber = <%=s_autonumber.equals("Y")%>;
	var showAutoPoNumber = <%=s_showauto.equals("Y")%>;

// end hiding contents -->
</SCRIPT>