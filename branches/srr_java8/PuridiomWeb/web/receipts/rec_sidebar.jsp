<%@ page import="com.tsa.puridiom.steporder.*" %>

<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>
<TR><TD COLSPAN="3" HEIGHT="1px" CLASS="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR>
<TR CLASS="sidebar"><TD COLSPAN="3" HEIGHT="5px">&nbsp;</TD></TR>

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
  String	s_recsteps = "";
  String    accountPref = "";
  String	s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOREC", "N");
  String	s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOREC", "Y");
  String 	enabled_steps =  propertiesManager.getProperty("RECEIVING", "ENABLED RECEIVING IN N-STEP", "N");

  boolean	b_current_process = false;

  int	i_step = 1;

  ProcessSteps steps = null;
  s_recsteps = "recsteps_" + s_rec_type.toLowerCase();
  if (receiptMethod.equals("FinalizeReceipt") && s_rec_type.equals("A"))
	  s_recsteps += "f";

  steps = new ProcessSteps(s_recsteps, oid);

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

    boolean step0 = s_process_code.equals("STEP0");
    boolean step1 = s_process_code.equals("STEP1");
    boolean step2 = s_process_code.equals("STEP2");
    boolean step3 = s_process_code.equals("STEP3");

    boolean isLineInStep1 = enableStep1;
    boolean isLineInStep2 = enableStep2;
    boolean isLineInStep3 = enableStep3;

    if(enabled_steps.equals("Y"))
    {
    	/*	644.4 SRR - Inspection step should always be viewable */
	    if(step1 && !isLineInStep1)
	    {
	    	continue;
	    }

	    if(step2 && !isLineInStep2)
	    {
	    	continue;
	    }
	    if(step3 && !isLineInStep3)
	    {
	    	continue;
	    }
    } else {
	    if(s_process_code.equals("STEP0") /*|| s_process_code.equals("STEP1")*/ || s_process_code.equals("STEP2") || s_process_code.equals("STEP3"))
	    {
	    	continue;
	    }
    }


    if (b_current_process)
    {
%>
<tsa:hidden name="accountPref" value="<%=accountPref%>"/>
<TR>
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
<% if(!receiptMethod.equals("ReceiveByOrder")){ %>
<%	if (s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) >= 0 && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) <= 0) { %>
<tr>
	<td colspan="3" class="processOff" align="center">
		<table border=0 cellpadding=1 cellspacing=0 width=100%>
		<tr>

		<%
			if (s_current_page.equals("/receipts/rec_validation.jsp") &&
				s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0 &&
				(receiptMethod.equals("ReceiveByPackage") || receiptMethod.equals("ReceiveFromNothing") || receiptMethod.equals("ReceiveByOrder") || receiptMethod.equals("Return") || receiptMethod.equals("ReceiveByTransfer"))) { %>
      			<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptForward(); void(0);" onclick="this.blur();">Forward</a></div></div></td>
		<%	} else if (s_current_page.equals("/receipts/rec_validation.jsp") &&
				s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0 &&
				receiptMethod.equals("FinalizeReceipt") &&
				!HiltonUtility.isEmpty(s_rec_number)) { %>
      			<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptForward(); void(0);" onclick="this.blur();">Finalize</a></div></div></td>
		<%	} else if (s_current_page.equals("/receipts/rec_validation.jsp") &&
				s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0 &&
				receiptMethod.equals("FinalizeReceipt") &&
				!HiltonUtility.isEmpty(s_rec_number)) { %>
				<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptForward; void(0);" onclick="this.blur();">Finalize</a></div></div></td>
		<%	} else if (
				(s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0 && receiptMethod.equals("FinalizeReceipt"))) { %>
				<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: recSave(); void(0);" onclick="this.blur();">Save</a></div></div></td>
		<%	} else if(s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0){%>
				<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: recSave(); void(0);" onclick="this.blur();">Save</a></div></div></td>
		<%	} %>
			</tr>
			</table>
		</td>
	</tr>
	<%	} %>
<%	} else {%>
	<tr>
		<td colspan="3" class="processOff" align="center">
			<table border=0 cellpadding=1 cellspacing=0 width=100%>
			<tr>
			<% if ((s_current_page.equals("/receipts/rec_validation.jsp"))){%>
				<% if(s_rec_status.equals(DocumentStatus.RCV_INPROGRESS) && !s_inspectionRequired && !s_markTagRequired) {%>
						<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptSubmit(); void(0);" onclick="this.blur();">Submit</a></div></div></td>
				<%	} else if ((s_rec_status.equals(DocumentStatus.RCV_INPROGRESS) && !(enableStep1 || enableStep2 || enableStep3 || allRejected)) ||
						(s_rec_status.equals(DocumentStatus.RCV_STEP_1) && enableStep1 && s_engineerAssigned.equals(uid)) ||
						(enableStep2 && user.getMarker().equals("Y"))){%>
						<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptSubmit(); void(0);" onclick="this.blur();">Submit</a></div></div></td>
				<% } else if(enableStep3){%>
						<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptForward(); void(0);" onclick="this.blur();">Forward</a></div></div></td>
				<%	} else if(allRejected){%>
						<td align=center><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: receiptForward(); void(0);" onclick="this.blur();">Forward</a></div></div></td>
				<%	} %>
			<%} %>
		</tr>
		</table>
	</td>
</tr>
<%	} %>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var autoRecNumber = <%=s_autonumber.equals("Y")%>;
	var showAutoRecNumber = <%=s_showauto.equals("Y")%>;

// end hiding contents -->
</SCRIPT>