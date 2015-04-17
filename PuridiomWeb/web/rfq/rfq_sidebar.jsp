<%@ page import="com.tsa.puridiom.steporder.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<table border=0 cellpadding=0 cellspacing=0>
<tr><td colspan="3" height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" height="1px" width="100%"></td></tr>
<tr class="sidebar"><td colspan="3" height="5px">&nbsp;</td></tr>

<%
	HashMap processMap = new HashMap();
	String	s_process_code = "";
	String	s_process_link = "";
	String	s_process_label = "";
	String	s_process_method = "";

	String	s_previous_link = "";
	String	s_previous_label = "";
	String	s_previous_method = "";
	String	s_next_link = "";
	String	s_next_label = "";
	String	s_next_method = "";
	String	s_autonumber = PropertiesManager.getInstance(oid).getProperty("AUTONUMBER", "AUTORFQ", "N");
	String	s_showauto = PropertiesManager.getInstance(oid).getProperty("AUTONUMBER", "SHOWAUTORFQ", "Y");

	boolean	b_current_process = false;

	int	i_step = 1;
	String	stepsXml = "";
	String rfqType = (String) request.getAttribute("RfqHeader_rfqType");
	if (!HiltonUtility.isEmpty(rfqType)) {
		stepsXml = "rfqsteps_" + rfqType.toLowerCase();
	} else {
		stepsXml = "rfqsteps_rq";
	}

	ProcessSteps steps = new ProcessSteps(stepsXml, oid);

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		s_process_link = steps.getLink(ip);
		s_process_label = steps.getLabel(ip);
		s_process_method = steps.getMethod(ip);

		processMap.put(s_process_code, "TRUE");

		if (s_current_process.equals(s_process_code))
		{
			b_current_process = true;
			s_current_process_method = s_process_method;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod(ip - 1);
			}
			if ( (ip + 1) < steps.getSize())
			{
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
<TR HEIGHT="25px">
	<TD WIDTH="5px" CLASS="processOn">&nbsp;</TD>
  <TD valign=center>
	<div id="pxsmallbutton"><%=i_step%></div>
  </TD>
  <TD CLASS="processOn" NOWRAP>
    &nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_process_label, s_process_label, false)%>&nbsp;
  </TD>
</TR>
<%
		}
		else
		{
%>
<TR>
	<TD CLASS="processOff" WIDTH="5px">&nbsp;</TD>
  	<TD CLASS="processOff" valign=middle>
    	<div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');"><%=i_step%></a></div>
  	</TD>
  	<TD CLASS="processOff" NOWRAP valign=middle>
    	&nbsp;<A HREF="javascript: doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_process_label, s_process_label, false)%></A>&nbsp;
  	</TD>
</TR>
<%			if (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM"))
			{
%>
				<%//@ include file="/xchange/requests/req_item_sidebar.jsp" %>
<%			}

		}

		i_step++;
	}
%>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>
<tr><td colspan="3" height="1px" class="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>

<tr>
	<td colspan=3>
		<table border=0 width=100%>
		<tr>
			<td align=left>
<%	if (s_previous_link.length() > 0) { %>
				<table border=0>
				<tr>
          <td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');"><<</a></div></td>
          <td class=processOn><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');" class=processOn title="<%=s_previous_label%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "back", "Back")%></a></td>
				</tr>
				</table>
<%	} %>
			</td>
			<td align=right>
<%	if (s_next_link.length() > 0) { %>
				<table border=0>
				<tr>
          <td class=processOn><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');" class=processOn title="<%=s_next_label%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "next", "Next")%></a></td>
          <td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');">>></a></div></td>
        </tr>
        </table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan="3" class="processOff"><br></td></tr>
<%
	String	jsMethod = "";
	String buttonImg = "";
	String	buttonAltText = "";
	String  buttonText = "" ;
	String	doubleForward = PropertiesManager.getInstance(oid).getProperty("RFQ OPTIONS", "DOUBLEFORWARD", "Y");
	if (role.getAccessRights("RFQ") > 1) {
		if ((s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
			if ( s_current_page.equals("/rfq/rfq_validate.jsp") && !s_rfqNumber.equals("N/A") ) {
				RfqHeader tempRfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
				if (tempRfqHeader == null || (doubleForward.equals("Y") || (!HiltonUtility.isEmpty(tempRfqHeader.getWebpost()) && !tempRfqHeader.getWebpost().equals("N")))) {
					jsMethod = "rfqForward();";
					buttonImg = "button_submit.gif";
					buttonText = "Submit" ;
					buttonAltText = "Submit for Bids";
				} else if (tempRfqHeader.getRfqType().equals("PR") && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) <= 0)) {
					jsMethod = "rfqReturn();";
					buttonImg = "button_return_pricing.gif";
					buttonText = "Return Pricing" ;
					buttonAltText = "Return Pricing to Requisitioner";
				} else {
					jsMethod = "rfqForward();";
					buttonImg = "button_finalize.gif";
					buttonText = "Finalize" ;
					buttonAltText = "Finalize / Supplier Selection";
				}
			} else if (!s_current_page.equals("/rfq/rfq_review.jsp") || s_rfqNumber.equals("N/A")) {
				jsMethod = "rfqSave();";
				buttonImg = "button_save.gif";
				buttonText = "Save" ;
				buttonAltText = "Save";
			}
		} else	if ((s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) == 0) && s_current_page.equals("/rfq/rfq_validate.jsp")) {
			RfqHeader tempRfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
			if (tempRfqHeader != null && tempRfqHeader.getRfqType().equals("PR")) {
				jsMethod = "rfqReturn();";
				buttonImg = "button_return_pricing.gif";
				buttonText = "Return Pricing" ;
				buttonAltText = "Return Pricing to Requisitioner";
			} else {
				jsMethod = "rfqForward();";
					buttonImg = "button_finalize.gif";
					buttonText = "Finalize" ;
					buttonAltText = "Finalize / Supplier Selection";
			}
		}
	}
	if (!HiltonUtility.isEmpty(buttonText)) {%>
<tr><td colspan="3" class="processOff"><br></td></tr>
<tr>
	<td colspan="3" class="processOff" align="center">
		<%	if (jsMethod.equals("rfqForward();")) {%>
		<div id="forward_link">
		<%	}%>
		      		<div id="pxbutton"><a href="javascript: <%=jsMethod%> void(0);"><%=buttonText%></a></div>

		<%	if (jsMethod.equals("rfqForward();")) {%>
		</div>
		<%	}%>
	</td>
</tr>
<%}%>
</table>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var autoRfqNumber = <%=s_autonumber.equals("Y")%>;
	var showAutoRfqNumber = <%=s_showauto.equals("Y")%>;

// end hiding contents -->
</SCRIPT>