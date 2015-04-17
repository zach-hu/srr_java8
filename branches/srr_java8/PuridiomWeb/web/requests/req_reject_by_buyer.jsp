<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>

<%
  String icReqHeader = (String)request.getAttribute("RequisitionHeader_icReqHeader");
  String s_req_number = (String)request.getAttribute("RequisitionHeader_requisitionNumber");
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr>
  <td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tr>
          <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
        </tr>
        <tr>
          <td nowrap class="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="rejectionNotes" defaultString="Rejection Notes" /></div>
          </td>
        </tr>
        </table>
      </td>
      <td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
      <td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td align="center">
    <span class="formType"><tsa:label labelName="requisition" defaultString="Requisition" /> </span><span class="hdr12">#<%=headerEncoder.encodeForHTML(s_req_number)%></span>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td width="100%" align="center" valign="top">
    <div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
		<table border="0" cellpadding="0" cellspacing="0" width="445px">
			<tr class="summary" height="20px">
				<td nowrap valign="middle" align="center"><tsa:input type="textarea" name="ApprovalLog_approverNotes" rows="10" cols="77" /></td>
			</tr>
		</table>
  </td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="655px">
	<TR>
	  <TD align="center"><a href="javascript: rejectReq(); void(0);"><img class="button" src="<%=contextPath%>/images/button_reject.gif" border=0 alt="Reject"></a></TD>
	  <TD align="center"><a HREF="javascript: doSubmit('requests/req_review.jsp', 'DoNothing;RequisitionRetrieve');"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Requisition"></a></TD>
	</TR>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();
  
  function rejectReq()
  {
      doSubmit('/requests/req_review.jsp', 'RequisitionRejectByBuyer;RequisitionRetrieve');
  }


// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>