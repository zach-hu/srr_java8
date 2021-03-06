<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>


<%
  RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
  UserProfile requisitioner = UserManager.getInstance().getUser(oid, reqHeader.getRequisitionerCode());
  String emailAction = (String)request.getAttribute("emailAction");
  if(emailAction == null)
  {
    emailAction = "NONE";
  }
  //DO NOT change to use the isEmpty methods. This fields should be null if they are not set

%>
   <tsa:hidden name="emailAction" value="<%=emailAction%>"/>
  <tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
  <tsa:hidden name="as_return" value="TRUE"/>
  <tsa:hidden name="allowBrowse" value="TRUE"/>
  <tsa:hidden name="reqaction" value="APPROVE"/>

 <table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td valign="top" width="135px" height="30px">
    <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
    <tr>
      <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class="hdr12" valign="middle">
        <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="requisitionInformation" defaultString="Requisition Information" /></div>
      </td>
    </tr>
    </table>
  </td>
  <td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign="bottom" align="right" height="30px">
    <table border="0" cellspacing="0" cellpadding="1" width="100%">
    <tr>
      <td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
      <td width="100px"><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></td>
    </tr>
    <tr>
      <td align="right"><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
      <td width="100px"><%=DocumentStatus.toString(reqHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0">
    <tr>
      <td width="1000px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
      <tr>
        <td align="center" width="680px">
          <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseRow">
            <tr>
              <td colspan="3"><br><br></td>
            </tr>
            <tr>
              <td colspan="3" align="center"><tsa:label labelName="forwardReadOnlyCopy" defaultString="Forward a Read Only Copy to" />:<p>
                  <b><A HREF="javascript: browseLookup('forwardTo', 'approver'); void(0);"><tsa:label labelName="userId" defaultString="User ID" />:</a></b>&nbsp;
            <tsa:input type="text" name="forwardTo" onfocus="this.blur();" onchange="upperCase(this);" /></td>
            </tr>
            <tr>
              <td valign="middle" nowrap colspan="3" align="center">&nbsp;&nbsp;</td>
            </tr>
            <tr>
              <td nowrap colspan="3" align="center" valign="middle"><p><tsa:label labelName="includeMessage" defaultString="Include this Message" />:</p><p>
              <tsa:input type="textarea" name="SendQueue_messagetext" wrap="VIRTUAL" rows="8" cols="67" /></td>
            </tr>
          </table>
        </td>
      </tr>
    </table>

    <table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td align="center" width="680px">
    <table border="0" cellspacing="0" cellpadding="0" width="665px">
    <tr>
      <td colspan="3"><br><br></td>
    </tr>
    <tr>
      <td align="center"><a href="javascript: emailMe(); void(0);"><img src="<%=contextPath%>/images/email_copy.gif" border=0 alt="Forward a copy of this e-mail"></a><BR><font face=Verdana size=-2><tsa:label labelName="copy-email" defaultString="a copy of this e-mail" /></font></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<SCRIPT value=JavaScript>
<!-- Hide script

    frm = document.purchaseform;

    function emailMe()
    {
      if (frm.forwardTo.value.length <= 0)
      {
        alert("You must first select an approver to reroute this Requisition!");
        browseLookup('forwardTo', 'approver');
      }
      else
      {
        frm.reqaction.value = "FORWARDCOPY";
        doSubmit('/requests/req_forward.jsp', 'RequisitionForwardCopy');
      }
    }

// End Hide script -->
</SCRIPT>
    <%@ include file="/system/footer.jsp" %>