<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
%>

<script language='Javascript1.2' src='<%=contextPath%>/supplierportal/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/supplierportal/scripts/dynamicTables.js' type='text/javascript'></script>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="newStatus" value="<%=DocumentStatus.IVC_APPROVING%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr>
  <td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tr>
          <td height="1px" class="darkShadow"><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
        </tr>
        <tr>
          <td nowrap class="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12">Routing List</div>
          </td>
        </tr>
        </table>
      </td>
      <td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
      <td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="2px" class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
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
    <font class="formType">Invoice </font><font class="hdr12">#<%=invoiceHeader.getInvoiceNumber()%></font>
  </td>
</tr>
<tr><td><br></td></tr>
</table>

<div id="routinglist" style="display:none;">
<table border="0" cellspacing="0" cellpadding="0" width="640px">
  <tr height="20px">
    <td>
      <table border="0" align="center" width="25px">

      </table>
    </td>
    <td width="620px" class="browseHdrDk" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="2" width="620px">
           <tr>
				<td nowrap="nowrap" width="1%" class="browseHdrDk">&nbsp;</td>
				<td nowrap="nowrap" width="100%" class="browseHdrDk">Your invoice will be forwarded to:</td>
          </tr>
        </table>
          <div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 630px; height: 190px; align:center; overflow-y:visible; overflow-x:auto;">
          <table id="approvers" border="0" cellpadding="0" cellspacing="0" width="620px" class="browseRow">
            <tr height="15px">
				<td width="1%" class="browseRow" NOWRAP align="right">&nbsp;</td>
                <td width="100%" class="browseRow" NOWRAP><%=invoiceHeader.getOrderByName()%>&nbsp;</td>
            </tr>
          </table>
        </div>
      </td>
  </tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="2" CELLPADDING="2" VALIGN="BOTTOM" WIDTH="680px">
  <TR id="_link">
    <td align="center"><a href="javascript: invoiceForward(); void(0);"><img class="button" src="<%=contextPath%>/supplierportal/images/button_forward.gif" border=0 alt="Forward"></a>&nbsp;</td>
    <td align="center"><a HREF="javascript: doSubmitHide()"><img class="button" src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 alt="Return to Invoice"></a></td>
  </TR>
</TABLE>
</div>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

  var currentRow = -1;
  //list should show up to allow user to add approvers. -RR
  //Approvals(Routing list) will be byPassed if an XML rule type of "Z" was true -RR 03/10/05
  <%//if (byPassRoutingList && reqAction.equals("FORWARD")) {%>
  	//displayArea('noroutinglist');
    //reqForward();
  <%//} else if (!ruleStatus.equalsIgnoreCase("FAILED")) {%>
  displayArea('routinglist');
  <%//}%>


  function invoiceForward()
  {
		hideArea('_link');
		doSubmit('/invoice/iv_forward.jsp', 'InvoiceForward');
  }

  function doSubmitHide()
  {
  		hideArea('_link');
  		doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
  }

  function addUser()
  {
  	popupParameters="browseName=approver";
  	popupParameters=popupParameters + ";formField=ApprovalLog_userId";
    doSubmitToLookup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  }

  function deleteUser(row)
  {
    myTable = document.getElementById("approvers");
    myTable.deleteRow(row);

    var rowCount = document.all.approvers.rows.length;
    if (row < rowCount)
    {
      for (var i = eval(row + 1); i <= rowCount; i++)
      {
        var sequence = document.getElementById("sequence" + i).innerText;
        setInnerText("sequence" + i, i);

        if (document.getElementById("deleteLink" + i).innerHTML.length > 0)
        {
          var deleteLink = "<a href=\"javascript: deleteUser('" + (i - 1) + "'); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" alt=\"Delete\" border=\"0\">";
          setInnerHTML("deleteLink" + i, deleteLink);
        }
      }
    }

    doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
  }

  function highlightRow(row)
  {
    if (currentRow == row)
    {
      removeHighlight(row);
    }
    else
    {
      var myRow = document.all.approvers.rows(row);

      if (currentRow > -1)
      {
        var oldRow = document.all.approvers.rows(currentRow);
        setRowClassName(oldRow, "browseRow");
      }

      setRowClassName(myRow, "selectedRow");
      setCurrentRow(row);
    }
  }

  function removeHighlight(row)
  {
    var myRow = document.all.approvers.rows(row);

    setRowClassName(myRow, "browseRow");
    setCurrentRow(-1);
  }

  function setCurrentRow(row)
  {
    currentRow = row;
  }

  function moveUp()
  {
    if (currentRow == -1)
    {
      alert("You must first select an approver to move up on the list.");
    }
    else if (currentRow > 0)
    {
      var myTable = document.all.approvers;
      var rowCount = document.all.approvers.rows.length;

      var approverUpText = document.getElementById("approverText" + currentRow).innerText;
      var approverDownText = document.getElementById("approverText" + (currentRow - 1)).innerText;
      setInnerText("approverText" + (currentRow), approverDownText);
      setInnerText("approverText" + (currentRow - 1), approverUpText);

      var callForwardUpText = document.getElementById("callForwardText" + currentRow).innerText;
      var callForwardDownText = document.getElementById("callForwardText" + (currentRow - 1)).innerText;
      setInnerText("callForwardText" + (currentRow), callForwardDownText);
      setInnerText("callForwardText" + (currentRow - 1), callForwardUpText);

      var authorityUpText = document.getElementById("authorityText" + currentRow).innerText;
      var authorityDownText = document.getElementById("authorityText" + (currentRow - 1)).innerText;
      setInnerText("authorityText" + (currentRow), authorityDownText);
      setInnerText("authorityText" + (currentRow - 1), authorityUpText);

      var deleteUpLink = document.getElementById("deleteLink" + currentRow).innerHTML;
      var deleteDownLink = document.getElementById("deleteLink" + (currentRow - 1)).innerHTML;
      var newUpLink = "";
      var newDownLink = "";

      if (deleteUpLink.length > 0)
      {
        newUpLink = "<a href=\"javascript: deleteUser('" + (currentRow - 1) + "'); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" alt=\"Delete\" border=\"0\">";
      }
      setInnerHTML("deleteLink" + (currentRow - 1), newUpLink);

      if (deleteDownLink.length > 0)
      {
        newDownLink = "<a href=\"javascript: deleteUser('" + (currentRow) + "'); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" alt=\"Delete\" border=\"0\">";
      }
      setInnerHTML("deleteLink" + (currentRow), newDownLink);

      var hiddenFieldsUp = document.getElementById("hiddenFields" + currentRow).innerHTML;
      var hiddenFieldsDown = document.getElementById("hiddenFields" + (currentRow - 1)).innerHTML;
      setInnerHTML("hiddenFields" + (currentRow), hiddenFieldsDown);
      setInnerHTML("hiddenFields" + (currentRow - 1), hiddenFieldsUp);

      highlightRow(currentRow - 1);
    }
  }

  function moveDown()
  {
    var myTable = document.all.approvers;
    var rowCount = document.all.approvers.rows.length;

    if (currentRow == -1)
    {
      alert("You must first select an approver to move down on the list.");
    }
    if (currentRow > -1 && currentRow < (rowCount - 1))
    {
      var approverDownText = document.getElementById("approverText" + currentRow).innerText;
      var approverUpText = document.getElementById("approverText" + (currentRow + 1)).innerText;
      setInnerText("approverText" + (currentRow + 1), approverDownText);
      setInnerText("approverText" + (currentRow), approverUpText);

      var callForwardDownText = document.getElementById("callForwardText" + currentRow).innerText;
      var callForwardUpText = document.getElementById("callForwardText" + (currentRow + 1)).innerText;
      setInnerText("callForwardText" + (currentRow + 1), callForwardDownText);
      setInnerText("callForwardText" + (currentRow), callForwardUpText);

      var authorityDownText = document.getElementById("authorityText" + currentRow).innerText;
      var authorityUpText = document.getElementById("authorityText" + (currentRow + 1)).innerText;
      setInnerText("authorityText" + (currentRow + 1), authorityDownText);
      setInnerText("authorityText" + (currentRow), authorityUpText);

      var deleteDownLink = document.getElementById("deleteLink" + currentRow).innerHTML;
      var deleteUpLink = document.getElementById("deleteLink" + (currentRow + 1)).innerHTML;
      var newDownLink = "";
      var newUpLink = "";

      if (deleteDownLink.length > 0)
      {
        newDownLink = "<a href=\"javascript: deleteUser('" + (currentRow + 1) + "'); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" alt=\"Delete\" border=\"0\">";
      }
      setInnerHTML("deleteLink" + (currentRow + 1), newDownLink);

      if (deleteUpLink.length > 0)
      {
        newUpLink = "<a href=\"javascript: deleteUser('" + (currentRow) + "'); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" alt=\"Delete\" border=\"0\">";
      }
      setInnerHTML("deleteLink" + (currentRow), newUpLink);

      var hiddenFieldsDown = document.getElementById("hiddenFields" + currentRow).innerHTML;
      var hiddenFieldsUp = document.getElementById("hiddenFields" + (currentRow + 1)).innerHTML;
      setInnerHTML("hiddenFields" + (currentRow + 1), hiddenFieldsDown);
      setInnerHTML("hiddenFields" + (currentRow), hiddenFieldsUp);

      highlightRow(currentRow + 1);
    }
  }

  function updateList()
  {
    //doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>