<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/autoawardOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/budget.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/auto_accounting.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String actionStep = (String)request.getAttribute("actionStep");
	if(browseObject.getBrowseName().equalsIgnoreCase("auto-close")){
		String poFrom = (String) request.getAttribute("PoHeader_poFrom");
		String poTo = (String) request.getAttribute("PoHeader_poTo");
		String poNumber = (String) request.getAttribute("PoHeader_poNumber");
		String poEnt = (String) request.getAttribute("Account_fld1");
		String poDept = (String) request.getAttribute("Account_fld2");
%>
<tsa:hidden name="PoHeader_poFrom" value="<%=poFrom%>"/>
<tsa:hidden name="PoHeader_poTo" value="<%=poTo%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="Account_fld1" value="<%=poEnt%>"/>
<tsa:hidden name="Account_fld2" value="<%=poDept%>"/>
<% } %>

<script value=JavaScript>
  function Accept()
  {
	doSubmit('menu/main_menu.jsp','PoAutoCancel');
  }

  function printCheckInvoices()
  {

    var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("InvoiceHeader_icIvcHeader");
    var recordSelected = false;

    if (ckboxElements.length > 1)
    {
      for (var i = 0; i < ckboxElements.length; i++)
      {
        if (ckboxElements(i).checked)
        {
          recordSelected = true;
          break;
        }
      }

      if (recordSelected)
      {
        for (var i = ckboxElements.length - 1; i >= 0 ; i--)
        {
          if (!ckboxElements(i).checked || ckboxElements(i).disabled==true)
          {
            if (icHeaderElements != undefined && icHeaderElements(i))
            {
              icHeaderElements(i).removeNode(true);
            }
          }
          else
          {
          	if(frm.c_checkbox2[i].checked)
          	{
          		frm.discountsAnyway.value=frm.discountsAnyway.value + 'Y';
          	}
          	else
          	{
          		frm.discountsAnyway.value=frm.discountsAnyway.value + 'N';
          	}
           }
        }
      }
    }
    else
    {
      var ckbox = ckboxElements;
      if (ckbox.checked)
      {
        recordSelected = true;
      }
    }

    if (!recordSelected) {
      alert("You have not selected any invoices to print!");
      return false;
    }

    if (!verifyAction('Execute printing process for these invoices?')) {
    	return false;
    }

//    doSubmit('/invoice/inv_printing_checks.jsp', 'PrintCheckInvoices');
    doSubmit('/menu/main_menu.jsp', 'PrintCheckInvoices');
  }

function resetExportedInvoices()
{
	var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("InvoiceHeader_icIvcHeader");
    var recordSelected = false;

    if (ckboxElements.length > 1)
    {
      for (var i = 0; i < ckboxElements.length; i++)
      {
        if (ckboxElements(i).checked)
        {
          recordSelected = true;
          break;
        }
      }

      if (recordSelected)
      {
        for (var i = ckboxElements.length - 1; i >= 0 ; i--)
        {
          if (!ckboxElements(i).checked)
          {
            if (icHeaderElements != undefined && icHeaderElements(i))
            {
              icHeaderElements(i).removeNode(true);
            }
          }
        }
      }
    }
    else
    {
      var ckbox = ckboxElements;
      if (ckbox.checked)
      {
        recordSelected = true;
      }
    }

    if (!recordSelected) {
      alert("You have not selected any invoices to reset!");
      return false;
    }

	doSubmit('/menu/main_menu.jsp', 'ResetExportedInvoices');
}

  function addInvoiceForPayment()
  {

	popupParameters = "browseName=print-check-invoices-popup;";

    var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("InvoiceHeader_icIvcHeader");

    var recordSelected = false;

	if (ckboxElements.length > 1)
	{
	    for (var i = 0; i < ckboxElements.length; i++)
	    {
		    if (ckboxElements(i).disabled==false)
		    {
		    	popupParameters = popupParameters + "colname=InvoiceHeader_icIvcHeader;operator=<>;filter_txt=" + icHeaderElements(i).value + ";logicalOperator=AND;originalFilter=N;sort=N;"
		    }
		}
    }
    else
    {
        if (ckboxElements.disabled==false)
	    {
			popupParameters = popupParameters + "colname=InvoiceHeader_icIvcHeader;operator=<>;filter_txt=" + icHeaderElements.value + ";logicalOperator=AND;originalFilter=N;sort=N;"
    	}
    }

	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	//browseLookup('', 'print-check-invoices-popup');
  }

  function printRequisitions()
  {
    var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("RequisitionHeader_icReqHeader");
    var recordSelected = false;

    if (ckboxElements.length > 1)
    {
      for (var i = 0; i < ckboxElements.length; i++)
      {
        if (ckboxElements(i).checked)
        {
          recordSelected = true;
          break;
        }
      }

      if (recordSelected)
      {
        for (var i = ckboxElements.length - 1; i >= 0 ; i--)
        {
          if (!ckboxElements(i).checked)
          {
            if (icHeaderElements != undefined && icHeaderElements(i))
            {
              icHeaderElements(i).removeNode(true);
            }
          }
        }
      }
    }
    else
    {
      var ckbox = ckboxElements;
      if (ckbox.checked)
      {
        recordSelected = true;
      }
    }

    if (!recordSelected) {
      alert("You have not selected any requisitions to print!");
      return false;
    }

    doSubmit('/requests/req_batch_pdf.jsp', 'PrintAssignedRequisitions');
  }

  function cancelCloseRequisitionsList(action) {
    var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("RequisitionHeader_icReqHeader");
    var newInputField = '';
    var headerInputField = "<input type=hidden name='RequisitionHeader_icReqHeader' value='";
    var tailInputField = "'>";
    var dummyInputField = headerInputField + tailInputField;
    var reason = document.forms[0].reason.value;

    if (ckboxElements.length > 1)
    {
      for (var i = 0; i < ckboxElements.length; i++)
      {
        if (ckboxElements(i).checked)
        {
          newInputField += headerInputField + icHeaderElements(i).value + tailInputField;
        }
      }
            }
    else if (ckboxElements.checked)
    {
      newInputField += headerInputField + icHeaderElements(0).value + tailInputField;
      }

    if (isEmpty(newInputField))
    {
      alert('You have not selected any requisitions to ' + action + '!');
        }
    else if (!isEmpty(reason))
      {
      if (confirm(action.charAt(0).toUpperCase() + action.substring(1, action.length) + ' selected Requisitions?')) {
        document.forms[0].reqaction.value = action;
        setHiddenFields(newInputField + dummyInputField);
        doSubmit('/menu/main_menu.jsp', 'RequisitionsListCancelClose');
            }
          }
    else
    {
      alert('You have not input a reason!');
      }

      return;
        }
</script>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="browsePage" value="/browse/browse.jsp"/>
<tsa:hidden name="fromPage" value="/browse/browse.jsp"/>
<% if ( browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") ){ %>
<tsa:hidden name="discountsAnyway" value="Y"/>
<% } %>
<%
	  String fromPage = HiltonUtility.ckNull((String) request.getAttribute("OriginalFromPage"));
	  String sumTotal = HiltonUtility.ckNull((String) request.getAttribute("sumTotal"));
	  String rowTotal = HiltonUtility.ckNull((String) request.getAttribute("rowTotal"));
	  if (HiltonUtility.isEmpty(sumTotal)){
		  sumTotal = "0.00";
	  }
	  if (HiltonUtility.isEmpty(rowTotal)){
		  rowTotal = "0.00";
	  }

	  String icPoHeaderFromContractNaming = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));
	  String displayCancel = (String) request.getAttribute("displayCancel");
	  if (displayCancel == null)
	  {
	    displayCancel = "FALSE";
	    if (browseObject.getBrowseName().equals("req-approval-list") || browseObject.getBrowseName().equals("req-check-approval-list") || browseObject.getBrowseName().equals("po-approval-list"))
	    {
	      displayCancel = "TRUE";
	    }
	  }
	  if (displayCancel.equals("TRUE"))
	  {
		  if ( oid.equalsIgnoreCase("VSE06P") && browseObject.getBrowseName().equals("po_rfqbrowse")){ %>
			<table border=0 width=<%=formWidth%>>
			  <tr>
			    <td align=center><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_select_type.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
			  </tr>
			</table>
		  <% } else if ( oid.equalsIgnoreCase("TTR09P") && browseObject.getBrowseName().equals("rfq_createfrom_requisitionheader")){ %>
			<table border=0 width=<%=formWidth%>>
			  <tr>
			    <td align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_select_type.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
			  </tr>
			</table>
		  <% } else {%>
			<table border=0 width=<%=formWidth%>>
			  <tr>
			    <td align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
			  </tr>
			</table>
		 <% }
	 }
	  String catalogId = "";
	  if (browseObject.getBrowseName().equals("catalogitem-admin"))
	  {
	    catalogId = (String) request.getAttribute("Catalog_catalogId");
%>
<tsa:hidden name="OriginalFromPage" value="<%=fromPage%>"/>
<table border=0 width=<%=formWidth%>>
<tr>
  <% if (role.getAccessRights("CATITEM") >= 3 ) { %>
  <td width=50% align=center>
    <div id="pxbutton"><a href="javascript: addCatalogItem(); void(0);">Add</a></div>
  </td>
  <% } %>
  <td width=50% align=center>
    <div id="pxbutton"><a href="javascript: cancelMe(); void(0);">Return</a></div>
  </td>
</tr>
</table>
<tsa:hidden name="Catalog_catalogId" value="<%=catalogId%>"/>
<%	}
    else if (browseObject.getBrowseName().indexOf("catalog") >= 0)
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <% if (role.getAccessRights("CAT") >= 3 ) { %>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: addCatalog(); void(0);">Add</a></div></td>
  <% } %>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: browseFilter('catalog'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}  else if (browseObject.getBrowseName().equals("app_pool")) { %>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: addAppPool(); void(0);">Add</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}  else if (browseObject.getBrowseName().equals("cat_pool")) { %>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: addCatPool(); void(0);">Add</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/catalog/catalogsecurity.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}  else if (browseObject.getBrowseName().equals("approver-admin")) { %>
<table border=0 width=<%=formWidth%>>
  <tr>
    <td align=center>
      <div id="pxbutton"><a href="javascript: setBrowseName(); resetNavCookie(9); doSubmit('/browse/browse_filter_options.jsp', 'BrowseGetOptions'); void(0);">Return</a></div>
    </td>
  </tr>
</table>
<%	}  else if (browseObject.getBrowseName().equals("supplierorders")) {
		String purchaseHistoryId = HiltonUtility.ckNull((String) request.getAttribute("Vendor_vendorId"));
      String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));	//used for temp vendor add from PO process
%>
<table border=0 width=<%=formWidth%>>

<%if ( oid.equalsIgnoreCase("MSG07P")){ %>
<tr><td>&nbsp;<td><tr>
<tr>
  <td width="" align="center" colspan="1" style="font-weight: bold"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sumTotal", "PO Total")%>: $<%= HiltonUtility.getFormattedDollar(sumTotal, oid) %>
    <tsa:hidden name="sumTotal" value="<%=sumTotal %>"/>
  </td>
  <td width="" align="center" colspan="1" style="font-weight: bold"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rowTotal", "Total # of PO's")%>: <%=rowTotal %>
    <tsa:hidden name="rowTotal" value="<%=rowTotal %>"/>
  </td>
</tr>
<%} %>
<tr>
  <td width="100%" align="center" colspan="2">
    <div id="pxbutton"><a href="javascript: viewSupplier('<%=purchaseHistoryId %>'); void(0);">Return</a></div>
    <tsa:hidden name="PurchaseHistory_vendorId" value="<%=purchaseHistoryId %>"/>
    <tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader %>"/>
  </td>
</tr>
</table>
<%}	else if (browseObject.getBrowseName().indexOf("admin-userprofile") >= 0)
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: addUserProfile(); void(0);">Add</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/admin_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>
<%	}

else if (browseObject.getBrowseName().indexOf("asset") >= 0)
{ %>
  <table border=0 width=<%=formWidth%>>
    <tr>
      <td align=center>
      <%if (browseObject.getBrowseName().equalsIgnoreCase("asset"))
      {%>
        <div id="pxbutton"><a href="javascript: addAssetOption(); void(0);">Add</a></div>
      <%}
      if (browseObject.getBrowseName().equalsIgnoreCase("assetnote"))
      {%>
        <div id="pxbutton"><a href="javascript: addAssetNoteOption(); void(0);">Add</a></div>
      <%}
      if (browseObject.getBrowseName().equalsIgnoreCase("assetcost"))
      {%>
        <div id="pxbutton"><a href="javascript: addAssetCostOption(); void(0);">Add</a></div>
      <%}
      if (browseObject.getBrowseName().equalsIgnoreCase("assetlocation"))
      {%>
        <div id="pxbutton"><a href="javascript: addAssetLocationOption(); void(0);">Add</a></div>
      <%}
      if (browseObject.getBrowseName().equalsIgnoreCase("assetservice"))
      {%>
        <div id="pxbutton"><a href="javascript: addAssetServiceOption(); void(0);">Add</a></div>
      <%}%>
      </td>
      <td align=center>
      <%
      if (browseObject.getBrowseName().equalsIgnoreCase("asset"))
      {
        if (request.getAttribute("Asset_description")==null) {%>
        <div id="pxbutton"><a href="javascript: doSubmit('/browse/asset-search.jsp','DoNothing'); void(0);">Return</a></div>
        <%} else {%>
        <div id="pxbutton"><a href="javascript: viewInvItemAsset(); void(0);">Return</a></div>
        <%}
      }
      else if (browseObject.getBrowseName().indexOf("asset_location") >= 0)
      {%>
        <div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
      <%}

      else if (browseObject.getBrowseName().indexOf("lease") >= 0)
      {%>
        <div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
      <%}
      else if (browseObject.getBrowseName().indexOf("warrantyexpire") >= 0)
      {%>
        <div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
      <%}
      else if (browseObject.getBrowseName().indexOf("unprinted") >= 0)
      {%>
        <div id="pxbutton"><a href="javascript: Barcode(); void(0);"><img class=button src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Return"></a>
        <div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>

      <%}
      else
      {%>
        <div id="pxbutton"><a href="javascript: returnToAsset(); void(0);">Return</a></div>
      <%}%>
      </td>
    </tr>
  </table>
<%	}
else if (browseObject.getBrowseName().indexOf("asst") >= 0)
{ %>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}

else if (browseObject.getBrowseName().indexOf("budget") >= 0)
{ %>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
	    <div id="pxbutton"><a href="javascript: doSubmit('budget/budget_review.jsp','DoNothing'); void(0);">Add</a></div>
    </td>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}

else if (browseObject.getBrowseName().indexOf("capital_clearing_account") >= 0)
{ %>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('admin/auto_accounting/capital_clearing_account_review.jsp','DoNothing'); void(0);">Add</a></div>
    </td>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('admin/auto_accounting/auto_accounting_menu.jsp','DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}

else if (browseObject.getBrowseName().indexOf("report_queue") >= 0)
{ %>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}
    else if (browseObject.getBrowseName().equalsIgnoreCase("invoices-approved") && oid.equalsIgnoreCase("akdoc"))
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: printInvoices(); void(0);">Print</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}
    else if (browseObject.getBrowseName().equalsIgnoreCase("print-assigned-reqs") || browseObject.getBrowseName().equalsIgnoreCase("requisitionheader-assigned"))
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: printRequisitions(); void(0);">Print</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}
    else if ( browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") )
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=20% align=center>&nbsp;</td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: addInvoiceForPayment();void(0);">Add Invoice</a></div></td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: browseFilter('print-check-invoices');">Return</a></div></td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: printCheckInvoices(); void(0);">Print Checks</a></div></td>
</tr>
</table>
<%	}
    else if ( browseObject.getBrowseName().equalsIgnoreCase("invoice-exported-reset") )
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=20% align=center>&nbsp;</td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: resetExportedInvoices();void(0);">Reset</a></div></td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: browseFilter('invoice-exported-reset');">Return</a></div></td>
</tr>
</table>
<%	}
    else if (browseObject.getBrowseName().equalsIgnoreCase("myopenreqs"))
    {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width="150px" valign="top" align="right"><b>Reason:&nbsp;</b></td>
  <td colspan="2"><textarea wrap="virtual" name="reason" tabindex=4 rows=5 cols=75></textarea></td>
</tr>
</table>
<br>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=33% align=center><div id="pxbutton"><a href="javascript: cancelCloseRequisitionsList('close');">Close</a></div></td>
  <td width=33% align=center><div id="pxbutton"><a href="javascript: cancelCloseRequisitionsList('cancel'); ">Cancel</a></div></td>
  <td width=33% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%
  }
  else if (browseObject.getBrowseName().equals("auditrecord"))
  {
    String returnpage = HiltonUtility.ckNull((String) request.getAttribute("returnpage"));
    if (returnpage.equals("approvalsmenu"))
    {
      returnpage = "/admin/approvals/approvals_menu.jsp";
    }
    else
    {
      returnpage = "/admin/admin_menu.jsp";
    }
%>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnpage)%>', 'DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}
    else if (browseObject.getBrowseName().equalsIgnoreCase("myreqitems")) {
%>
<table border=0 width=<%=formWidth%>>
<tr>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: requestSelectedItems(); void(0);">Request Items</a></div></td>
  <td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>
<%	}
    else  if (browseObject.getBrowseName().equals("prm-req-worlkloadview"))  {  %>
	<tsa:hidden name="pagereturn" value="summaryworkload"/>
    <table border=0 width=<%=formWidth%>>
      <tr>
      <td align=center>
      	<div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
      </td>
      </tr>
    </table>
<%  }
    else  if (browseObject.getBrowseName().equals("summ-req-buyer"))  {  %>
    <table border=0 width=<%=formWidth%>>
      <tr>
      <td align=center>
      	<div id="pxbutton"><a href="javascript: browseWorkloadView(); void(0);">Return</a></div>
      </td>
      </tr>
    </table>
<%  }
    else  if (browseObject.getBrowseName().equals("invoice-approval-list"))  {  %>
    <table border=0 width=<%=formWidth%>>
      <tr>
      <td align=center>
      	<div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
      </td>
      </tr>
    </table>
<% }
    else  if (browseObject.getBrowseName().equals("labels"))  {  %>
	<table border=0 width=<%=formWidth%>>
	<tr>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/admin_menu.jsp', 'DoNothing'); void(0);">Save</a></div></td>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
	</tr>
	</table>
<% }
    else if (browseObject.getBrowseName().indexOf("receipt-in-step") >= 0){  %>
   	<table border=0 width=<%=formWidth%>>
       <tr>
       <td align=center>
       	<div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
       </td>
       </tr>
     </table>
<% }

String fromMain = "";
if (browseObject.getBrowseName().equals("app-udf1"))
{
  fromMain = HiltonUtility.ckNull((String) request.getAttribute("fromMainMenu"));
%>
  <tsa:hidden name="fromMainMenu" value="<%=fromMain%>"/>
  <table border=0 width=<%=formWidth%>>
    <tr>
    <td align=center>
    	<div id="pxbutton"><a href="javascript: doSubmit('/admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
    </td>
    </tr>
  </table>
<%	}
%>

<%@ include file="/system/footer.jsp" %>

<SCRIPT>

  var assetTrackingNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_trackingNumber"))%>';
  var assetTNum = "<input class='Asset_trackingNumber' type='hidden' name='Asset_trackingNumber'/>";

<%	if (browseObject.getBrowseName().indexOf("app") >= 0 || browseObject.getBrowseName().indexOf("catalog") >= 0 || browseObject.getBrowseName().equals("req-approval-list") || browseObject.getBrowseName().equals("req-check-approval-list")  || browseObject.getBrowseName().equals("report_queue") || browseObject.getBrowseName().equals("auditrecord") || browseObject.getBrowseName().equals("supplierorders") || browseObject.getBrowseName().equals("report_queue_public") || browseObject.getBrowseName().equals("prm-req-worlkloadview") ||  browseObject.getBrowseName().equalsIgnoreCase("summ-req-buyer"))
    { %>
      hideArea("resetOriginalOption");
<%	}	%>

	<% if (browseObject.getBrowseName().equalsIgnoreCase("req-pipeline")){ %>
		displayArea("filterOptions");
		displayArea("resetOption");
		displayArea("resetOriginalOption");
		hideArea("resetFilterOption");
	<% }%>

<%	if (browseObject.getBrowseName().equalsIgnoreCase("invoices-approved") || browseObject.getBrowseName().equalsIgnoreCase("print-assigned-reqs") || browseObject.getBrowseName().equalsIgnoreCase("myopenreqs") ||
		browseObject.getBrowseName().equalsIgnoreCase("requisitionheader-assigned") || browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") ||
		browseObject.getBrowseName().equalsIgnoreCase("invoice-exported-reset") ) { %>
      displayArea("selectPrintDisplay");
      hideArea("resetOriginalOption");
      hideArea("resetButton");
<%	} %>

<%String	s_now = Dates.today(userDateFormat, userTimeZone); %>

<%    if ( !browseObject.getBrowseName().equals("supplierorders") ) {	%>
  setNavCookie("/browse/browse.jsp", "BrowseRetrieveById", "<%=headerEncoder.encodeForJavaScript(browseObject.getTitle())%>");
  <%	if (browseObject.getBrowseName().equals("admin-user-profile-audittrail")) { %>
  setBrowseCookie("admin-user-profile-audittrail-list");
  <%	} else { %>
  setBrowseCookie("<%=browseObject.getBrowseName()%>");
  <%	} %>
<%	}	%>

  function checkHiddenMenu()
  {
<%		if (browseObject.getBrowseName().indexOf("catalog") >= 0 || browseObject.getBrowseName().equals("req-approval-list") || browseObject.getBrowseName().equals("req-check-approval-list")) {%>
    hideArea("navTable");
    displayArea("menuBarSpacer");
<%		} %>
  }

  function viewReq(ic, type) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    frm.RequisitionHeader_icReqHeader.value = ic;
    doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
  }

  function viewReceipt(ic) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='ReceiptHeader_icRecHeader' value='" + ic + "'>";
    var actionStep = "<%=headerEncoder.encodeForJavaScript(actionStep)%>";
    newInputField = newInputField + "<input type='hidden' id='actionStepHiddenField' name='actionStep'>";

    myCell.innerHTML = newInputField;
    $("#actionStepHiddenField").val(actionStep);

    doSubmit('/receipts/rec_review.jsp', 'ReceiptRetrieve');
  }

  function viewRfq(ic, type) {
    frm.viewType.value = "WIZARD";
    frm.RfqHeader_icRfqHeader.value = ic;
    doSubmit('/rfq/rfq_review.jsp','RfqRetrieve');
  }

  function approveRfq(ic, type, seq) {
    frm.viewType.value = "WIZARD";
    frm.RfqHeader_icRfqHeader.value = ic;
    var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='ApprovalLog_icLine' value='0'>";
    newInputField = newInputField + "<input type='hidden' name='ApprovalLog_sequence' value='" + seq + "'>";
    newInputField = newInputField + "<input type='hidden' name='ApprovalLog_userId' value='${esapi:encodeForJavaScript(userId)}'>";
    setHiddenFields(newInputField);
    doSubmit('/rfq/rfq_approval.jsp','ApprovalLogRetrieveById;ApprovalLogRetrieveByHeader;RfqRetrieve');
  }

  function viewPo(ic, type) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

    myCell.innerHTML = newInputField;
    doSubmit('/orders/po_review.jsp','PoRetrieve');
  }

  function viewDsb(ic, type) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='DisbHeader_icDsbHeader' value='" + ic + "'>";

    myCell.innerHTML = newInputField;
    doSubmit('/inventory/dsb_review.jsp', 'DisbursementRetrieve');
  }

  function viewInvoice(ic) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='InvoiceHeader_icIvcHeader' value='" + ic + "'>";

    myCell.innerHTML = newInputField;
    doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
  }

  function viewSale(ic) {
    frm.viewType.value = "WIZARD";
    frm.SaleHeader_icSaleHeader.value = ic;
    doSubmit('/sales/sale_review.jsp','SaleRetrieve');
  }

  function createReq(ic, type)
  {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'><input type='hidden' name='RequisitionHeader_poType' value='<%=headerEncoder.encodeForJavaScript(s_req_type)%>'>";
    myCell.innerHTML = newInputField;
    if (frm.viewType.value == "CLASSIC")
    {
      doSubmit('/requests/req_summary.jsp', 'RequisitionCreate;RequisitionRetrieve');
    }
    else
    {
      doSubmit('/requests/req_review.jsp', 'RequisitionCreate;RequisitionRetrieve');
    }
  }

  function createRfqFromReq(ic)
  {
    frm.RequisitionHeader_icReqHeader.value = ic;
    if (frm.viewType.value == "CLASSIC")
    {
      doSubmit('/rfq/rfq_summary.jsp', 'RfqCreateFromRequisition;RfqRetrieve');
    }
    else
    {
      doSubmit('/rfq/rfq_review.jsp', 'RfqCreateFromRequisition;RfqRetrieve');
    }
  }

  function createReplenishReq(invlocation)
  {
	clearFilters();
	frm.viewType.value = "WIZARD";
    setOriginalFilter("InvLocation_id_itemLocation", "=", invlocation);
    frm.browseName.value = 'invitem-replenish';
    doSubmit('/browse/browse_replenish_items.jsp', 'BrowseRetrieve');
  }

  function createReleaseReq(icPoHeader)
  {
    frm.viewType.value = "WIZARD";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + icPoHeader + "'>";
    newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_requisitionType' value='E'>";
    setHiddenFields(newInputField);
    doSubmit('/requests/req_review.jsp', 'RequisitionCreateFromPo;RequisitionRetrieve');
  }

  function createChangeReq(icPoHeader)
  {
    frm.viewType.value = "WIZARD";

    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + icPoHeader + "'>" +
      "<input type='hidden' name='RequisitionHeader_requisitionType' value='C'>";

    setHiddenFields(newInputField);
    doSubmit('/requests/req_review.jsp', 'RequisitionCreateFromPo;RequisitionRetrieve');
  }

  function createRevisionReq(icPoHeader)
  {
    frm.viewType.value = "WIZARD";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + icPoHeader + "'>";
    newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_requisitionType' value='V'>";
    setHiddenFields(newInputField);
    doSubmit('/requests/req_review.jsp', 'RequisitionCreateFromPo;RequisitionRetrieve');
  }

<%	if (oid.equalsIgnoreCase("BSC04P")) {%>
  function importContractFromReq(rqhic, rqtype)
  {
	frm.RequisitionHeader_icReqHeader.value = rqhic;
   	var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + <%=icPoHeaderFromContractNaming%> + "'>" +
    "<input type='hidden' name='RequisitionHeader_requisitionType' value='P'>";
	setHiddenFields(newInputField);
    doSubmit('/orders/po_review.jsp', 'PoImportFromReq');
  }
<%	} %>

  function createOrderFromReq(rqhic, rqtype)
  {
    clearFilters();
    var today = '<%=s_now%>';
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    frm.RequisitionHeader_icReqHeader.value = rqhic;

    var requisitionTypeHidden = "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"" + rqtype + "\">";
	setHiddenFields(requisitionTypeHidden);

    frm.createdfrom.value ='REQ';
    if ("<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "RO" || "<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "SR" || "<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "DR")
    {
      if ("<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "RO")
      {
        setOriginalFilter("PoHeader_poType", "=", "BO");
      }
      else if ("<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "SR")
      {
        setOriginalFilter("PoHeader_poType", "=", "SB");
      }
      else if ("<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "DR")
      {
        setOriginalFilter("PoHeader_poType", "=", "DO");
      }
      setOriginalFilter("PoHeader_effectiveDate", "<=", today);
      setOriginalFilter("PoHeader_expirationDate", ">=", today);

      browse('po_blanketbrowse');
    }
    else if ("<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "PR")
    {
      doSubmit('/orders/po_review.jsp', 'PoCreateFromReq;PoCreateFromReqCheckSuppliers');
    }
    else
    {
      if (frm.viewType.value == "WIZARD")
      {
		if( "<%=oid%>" == "BSC04P" && "<%=headerEncoder.encodeForJavaScript(s_po_type)%>" == "CT")
		{
	        doSubmit('/orders/po_general_info.jsp', 'PoCreateFromReq;PoCreateFromReqCheckSuppliers');
        }
        else
        {
	        doSubmit('/orders/po_review.jsp', 'PoCreateFromReq;PoCreateFromReqCheckSuppliers');
        }
      }
      else
      {
        doSubmit('/orders/po_summary.jsp', 'PoCreateFromReq;PoCreateFromReqCheckSuppliers');
      }
    }

  }

  function createOrderFromRfq(rfqhic, rfqtype)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='RfqVendor_icRfqHeader' value='" + rfqhic + "'><input type='hidden' name='RfqBid_icRfqHeader' value='" + rfqhic + "'>";
    myCell.innerHTML = newInputField;

    doSubmit('/orders/po_select_supplier.jsp', 'RfqVendorRetrieveByHeader');
  }

  function createRelease(ic, number)
  {
    var createHandler = "PoCreateRelease";
    var createdFrom = "<%=createdFrom%>";
    var icRfqHeader = "<%=icRfqHeader%>";
    var icReqHeader = "<%=icReqHeader%>";
    var vendorId = "<%=vendorId%>";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='PoHeader_poNumber' value='" + number + "'><input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

	if (createdFrom == "REQ") {
      frm.createdfrom.value = createdFrom;
      frm.RequisitionHeader_icReqHeader.value = icReqHeader;
	  createHandler = "PoReleaseFromReqBlanket";
    }

    if (createdFrom == "RFQ") {
      frm.createdfrom.value = createdFrom;
      frm.RfqHeader_icRfqHeader.value = icRfqHeader;
      frm.RfqVendor_vendorId.value = '<%=vendorId%>';
      createHandler = "PoCreateReleaseFromRfq";
    }

    myCell.innerHTML = newInputField;

    if (frm.viewType.value == "WIZARD")
    {
      doSubmit('/orders/po_review.jsp', createHandler + ';PoRetrieve');
    }
    else
    {
      doSubmit('/orders/po_summary.jsp', createHandler + ';PoRetrieve');
    }
  }

  function createPurchaseRelease(icReqHeader, icPoHeader)
  {
	createHandler = "PoReleaseFromRevReq";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + icPoHeader + "'>";
    frm.RequisitionHeader_icReqHeader.value = icReqHeader;

    myCell.innerHTML = newInputField;

    if (frm.viewType.value == "WIZARD")
    {
      doSubmit('/orders/po_review.jsp', createHandler + ';PoRetrieve');
    }
    else
    {
      doSubmit('/orders/po_summary.jsp', createHandler + ';PoRetrieve');
    }
  }


  function createDisb(ic, type)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    frm.RequisitionHeader_icReqHeader.value = ic;
    var newInputField = "<input type='hidden' name='RequisitionHeader_requisitionType' value='" + type + "'><input type='hidden' name='preview' value='TRUE'>";
    myCell.innerHTML = newInputField;
    doSubmit('/inventory/dsb_items_preview.jsp', 'DisbursementCreatePreview');
  }

  function createDisbFromBackorder(ic, type) {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='RequisitionHeader_requisitionType' value='" + type + "'>";
    frm.RequisitionHeader_icReqHeader.value = ic;

    myCell.innerHTML = newInputField;
    doSubmit('/inventory/dsb_summary.jsp', 'DisbursementCreateBackorder;DisbursementRetrieve');
    //doSubmit('/inventory/dsb_items_preview.jsp', 'DisbursementCreateBackorderPreview');
  }

  function createInvoiceFromOrder(ic) {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
//		frm.RequisitionHeader_icReqHeader.value = ic;

    myCell.innerHTML = newInputField;
    doSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreateFromOrder');
  }

  function viewSupplier(vendor){
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Vendor_vendorId\" value=\"" + vendor + "\" >";
    myCell.innerHTML = newInputField;
    doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById');
  }


  function viewCatalog(catalog)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Catalog_catalogId\" value=\"" + catalog + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalog + "\" >";
    myCell.innerHTML = newInputField;
    doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
  }

  function viewCatalogItem(catalogId, itemNum)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalogId + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_itemNumber\" value=\"" + itemNum + "\" >";
    myCell.innerHTML = newInputField;
    <%	if (oid.equalsIgnoreCase("BLY07P")) {%>
    doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById;UnitOfMeasureRetrieveValidUmCode');
   	<%	} else {%>
   	doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById');
   	<%	} %>
  }

  function viewInvItem(itemNum) {
  	viewInvItem(itemNum,"INV") ;
  	}

  function viewInvItem(itemNum, itemSource) {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField =  "<input type=\"hidden\" name=\"InvItem_itemNumber\" value=\"" + itemNum + "\" ><input type=\"hidden\" name=\"itemAction\" value=\"UPDATE\">";
    myCell.innerHTML = newInputField;
    if (itemSource == "IVP") {
	    doSubmit('/inventory/inv_part.jsp','InvItemRetrieveById');
    } else {
	    doSubmit('/inventory/inv_item.jsp','InvItemRetrieveById');
    }
  }

  function viewBomItem(itemNum, itemDesc) {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField =  "<input type=\"hidden\" name=\"bomSource\" value=\"M\" ><input type=\"hidden\" name=\"InvItem_itemNumber\" value=\"" + itemNum + "\" ><input type=\"hidden\" name=\"itemAction\" value=\"UPDATE\">";
	newInputField = newInputField + "<input type=\"hidden\" name=\"InvItem_description\" value=\"" + itemDesc + "\" >"
    myCell.innerHTML = newInputField;
    doSubmit('/inventory/bom_master.jsp', 'BomMethodRetrieveByItem');
  }

  function returnMe(code)
  {
    var title = '<%=headerEncoder.encodeForJavaScript(browseObject.getTitle())%>';
    var myCell = document.getElementById("hiddenFields");

    if ((title.indexOf("Inventory Location")) >= 0)
    {
      myCell.innerHTML = "";
      var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"InvBinLocation_itemLocation\" value=\"" + code + "\" >";
      myCell.innerHTML = newInputField;
//      doSubmit('/inventory/physical_count.jsp', 'InvBinLocationRetrieveByLocation');
	doSubmit('inventory/physical_menu.jsp', 'DoNothing');
//	  doSubmit('/inventory/physical_count.jsp', 'InvItemRetrieveBinsByLocation') ;

    }
    if (title.indexOf("Approver") >= 0)
    {
      var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"AppRule_userId\" value=\"" + code + "\" >";
      setHiddenFields(newInputField);
<%		if (appMenuAction.equalsIgnoreCase("SWAP"))
      {	%>
        doSubmit('/admin/approvals/user_rules_swap.jsp', 'AppRuleRetrieveBy');
<%		}
      else
      {	%>

        doSubmit('/admin/approvals/user_rules_setup.jsp', 'AppRuleRetrieveBy');
<%		}	%>
    }
  }
/*
  function getApproverRules(code, description){
    var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"AppRule_udf1Code\" value=\"" + code + "\" >";
    newInputField = newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"as_tableDescription\" value=\"" + description + "\" >";
    setHiddenFields(newInputField);
    doSubmit('/admin/approvals/app_udf1_rules_setup.jsp', 'AppRuleRetrieveBy');
  }
*/
  	function getApproverRules(type, code)
	{
    	var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"AppRule_udf1Code\" value=\"" + code + "\" >";
    	newInputField = newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableType\" value=\"" + type + "\" >";
    	newInputField = newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableKey\" value=\"" + code + "\" >";
    	setHiddenFields(newInputField);
    	doSubmit('/admin/approvals/app_udf1_rules_setup.jsp', 'AppRuleRetrieveBy;StdTableRetrieveById');
  	}

	function getApproverRulesByVendor(vendorId, description)
	{
	  var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"AppRule_udf1Code\" value=\"" + vendorId + "\" >";
	  newInputField = newInputField + "<INPUT TYPE=\"HIDDEN\" NAME=\"AppRule_Description\" value=\"" + description + "\" >";
	  setHiddenFields(newInputField);
	  doSubmit('/admin/approvals/app_udf1_rules_setup.jsp', 'AppRuleRetrieveBy');
	}

  function adminUserProfile(userId, organizationId) {
    var myCell = document.getElementById("profileFields");
    var hiddenFields = myCell.innerHTML;

    hiddenFields = hiddenFields + "<input type=hidden name=\"UserProfile_userId\" value=\"" + userId  + "\">";
    hiddenFields = hiddenFields + "<input type=hidden name=\"UserProfile_organizationId\" value=\"" + organizationId  + "\">";

    myCell.innerHTML = "";
    myCell.innerHTML = hiddenFields;

    <% if (oid.equalsIgnoreCase("vse06p") || oid.equalsIgnoreCase("qri06p")){ %>
    doSubmit('admin/user/user_profile.jsp', 'AuditTrailSetup;UserProfileValidateRetrieve');
    <% } else { %>
    doSubmit('admin/user/user_profile.jsp', 'AuditTrailSetup');
    <% } %>
  }

	function adminUserLog(userId) {
		var myCell = document.getElementById("profileFields");
		var hiddenFields = myCell.innerHTML;

		hiddenFields = hiddenFields + "<input type=hidden name=\"UserLog_userId\" value=\"" + userId  + "\">";

		myCell.innerHTML = "";
		myCell.innerHTML = hiddenFields;

		doSubmit('admin/user/user_log_audittrail.jsp', 'UserLogRetrieveByUserId');
	}

	function adminUserProfileAuditTrail(userId) {
		clearFilters();
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		setOriginalFilter("AuditRecord_entity1", "=", userId);
		browse('admin-user-profile-audittrail');
	}

  function adminSecurityGroup(groupId) {
    var myCell = document.getElementById("profileFields");
    var hiddenFields = myCell.innerHTML;

    hiddenFields = hiddenFields + "<input type=hidden name=\"SecurityProfile_groupId\" value=\"" + groupId  + "\">";
    hiddenFields = hiddenFields + "<input type=hidden name=\"SecurityGroup_groupId\" value=\"" + groupId  + "\">";

    myCell.innerHTML = "";
    myCell.innerHTML = hiddenFields;

    doSubmit('admin/user/user_group_profile.jsp', 'DoNothing');
  }

  function receiveByOrder(ic) {
    var newInputField = "<input type='hidden' name='ReceiptHeader_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='ReceiveByOrder'>";
<%	if (!oid.equalsIgnoreCase("vse06pa")) { %>
    newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='" + ic + "'>";
<%	} %>
    setHiddenFields(newInputField);
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
	    doSubmit('/receipts/rec_general_info.jsp','ReceiptCreateFromOrder');
<%	} else {%>
<%	if (PropertiesManager.getInstance(oid).getProperty("REC DEFAULTS", "SINGLEPAGERECEIPT", "N").equalsIgnoreCase("Y")) {%>
		doSubmit('/receipts/rec_item_entry.jsp','ReceiptCreateRetrieve');
<%  } else { %>
		doSubmit('/receipts/rec_general_info.jsp','ReceiptCreateFromOrder;ReceiptGetFormNumber;ReceiptLineCreateFromPoLineList');
<%	}	%>
<%	} %>
  }

  function receiveByTransfer(ic) {
    frm.RequisitionHeader_icReqHeader.value = ic;
    var newInputField = "<input type='hidden' name='ReceiptHeader_icReqHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='ReceiptHeader_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='ReceiveByTransfer'>";
    newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='RequisitionLine_icReqHeader' value='" + ic + "'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_general_info_t.jsp','ReceiptCreateFromReqTransfer;ReceiptLineCreateFromReqLineList');
//    doSubmit('/receipts/rec_transfer.jsp','ReceiptCreateRetrieve');
  }

  function receiveByPackage(ic) {
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='ReceiveByOrder'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_package_entry.jsp','ReceiptCreateRetrieve');
  }

  function finalizeReceipt(ric) {
    var newInputField = "<input type='hidden' name='ReceiptHeader_icRecHeader' value='" + ric + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='FinalizeReceipt'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_item_entry.jsp','ReceiptRetrieve');
  }

  function receiptAdjustment(ric) {
    var newInputField = "<input type='hidden' name='ReceiptHeader_icRecHeader' value='" + ric + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='Adjustment'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_adjustment.jsp','ReceiptRetrieve');
  }

  function receiptReturn(pic) {
    var newInputField = "<input type='hidden' name='ReceiptHeader_icPoHeader' value='" + pic + "'>";
    newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='Return'>";
    newInputField = newInputField + "<input type='hidden' name='createAction' value='SAVE'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_return.jsp','ReceiptCreateRetrieve');
  }

  function createInvReturn(icReqLine, itemNumber, itemLocation)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='RequisitionLine_icReqLine' value='" + icReqLine + "'>";
    myCell.innerHTML = newInputField;
    doSubmit('/inventory/inv_return_summary.jsp', 'InvReturnCreatePreview');
  }

	function createInvReturnDsb(icDsbLine)
	{
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='DisbLine_icDsbLine' value='" + icDsbLine + "'>";
		myCell.innerHTML = newInputField;
		doSubmit('/inventory/inv_return_summary.jsp', 'InvReturnDsbCreatePreview');
	}

  function viewInvReturn(icReqLine, icBin, itemNumber)
  {
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='InvReturn_icReqLine' value='" + icReqLine + "'>";
    newInputField = newInputField + "<input type='hidden' name='InvBinLocation_icRc' value='" + icBin + "'>";
    newInputField = newInputField + "<input type='hidden' name='InvItem_itemNumber' value='" + itemNumber + "'>";
    myCell.innerHTML = newInputField;
    doSubmit('/inventory/inv_return_summary.jsp', 'InvReturnRetrieve');
  }

  function addAppPool()
  {
    doSubmit('/admin/approvals/app_pool.jsp', 'DoNothing')
  }

  function addCatPool()
  {
    doSubmit('/admin/catalog/cat_pool.jsp', 'DoNothing')
  }

  function viewAppPool(poolid)
  {
    var newInputField = "<input type='hidden' name='AppPool_poolid' value='" + poolid + "'><input type='hidden' name='AppPooluser_poolid' value='" + poolid + "'>";
    setHiddenFields(newInputField);
    doSubmit('/admin/approvals/app_pool.jsp', 'AppPoolRetrieveById;AppPooluserRetrieveByPool');
  }

  function viewCatPool(poolid)
  {
    var newInputField = "<input type='hidden' name='AppPool_poolid' value='" + poolid + "'><input type='hidden' name='AppPooluser_poolid' value='" + poolid + "'>";
    setHiddenFields(newInputField);
    doSubmit('/admin/catalog/cat_pool.jsp', 'AppPoolRetrieveById;AppPooluserRetrieveByPool');
  }

  function addCatalog()
  {
    doSubmit('/admin/catalog/catalog.jsp', 'DoNothing')
  }

  function addCatalogItem()
  {
    var catId = "<%=headerEncoder.encodeForJavaScript(catalogId)%>";
    var newInputField = "<input type='hidden' name='CatalogItem_catalogId' value='" + catId + "'>";
    setHiddenFields(newInputField);
    doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemCreate');
  }

  function cancelMe()
  {
    var catId = "<%=headerEncoder.encodeForJavaScript(catalogId)%>";
    var newInputField = "<input type='hidden' name='CatalogItem_catalogId' value='" + catId + "'>";
    setHiddenFields(newInputField);
    frm.browseName.value = 'catalogitem-admin';
    doSubmit('/browse/browse_filter_options.jsp', 'CatalogUpdate;BrowseGetOptions');
  }

  function retrieveReqApproval(ic)
  {
    frm.RequisitionHeader_icReqHeader.value = ic;
    var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
  }

  function retrievePoApproval(ic)
  {
    var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
    newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    doSubmit('/orders/po_approval.jsp', 'PoApprovalRetrieve');
  }

  	function retrieveInvoiceApproval(ic)
  	{
    	var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
    	newInputField = newInputField + "<input type='hidden' name='InvoiceHeader_icIvcHeader' value='" + ic + "'>";
    	setHiddenFields(newInputField);
    	doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve');
  	}

	function retrieveInventoryApproval(ic)
  	{
    	var newInputField = "<input type='hidden' name='InvItem_itemNumber' value='" + ic + "'>";
    	setHiddenFields(newInputField);
    	doSubmit('/inventory/inv_approval.jsp', 'InvItemRetrieveById');
  	}

  function addUserProfile(){
    <% if (oid.equalsIgnoreCase("vse06p")) { %>
    doSubmit('admin/user/user_profile_add.jsp', 'UserProfileValidateRetrieve');
    <% } else { %>
    doSubmit('admin/user/user_profile_add.jsp', 'DoNothing');
    <% } %>
  }

  function viewReceiptAttachments(ic, number, type) {
    var newInputField = "<input type=hidden name=DocAttachment_icHeader value='" + ic + "'>";
    newInputField = newInputField + "<input type=hidden name=allowEdit value='N'>";
    newInputField = newInputField + "<input type=hidden name=returnPage value='/browse/browse.jsp'>";
    newInputField = newInputField + "<input type=hidden name=returnHandler value='BrowseRetrieveById'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptNumber value='" + number + "'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptType value='" + type + "'>";

    setHiddenFields(newInputField);
    doSubmit('/receipts/rec_attachments.jsp', 'DocAttachmentRetrieveByHeader');
  }

  function printReceiptLineLabels(ic, number, type) {
    popupParameters="ReceiptLine_icRecLine=" + ic + ";viewNow=Y" ;
/*
    var newInputField = "<input type=hidden name=ReceiptLine_icRecLine value='" + ic + "'>";
    newInputField = newInputField + "<input type=hidden name=allowEdit value='N'>";
    newInputField = newInputField + "<input type=hidden name=returnPage value='/browse/browse.jsp'>";
    newInputField = newInputField + "<input type=hidden name=returnHandler value='BrowseRetrieveById'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptNumber value='" + number + "'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptType value='" + type + "'>";

    setHiddenFields(newInputField);
*/
//    doSubmit('/browse/browse.jsp', 'PrintRecLabelPdf');
    doSubmitToPopup('', 'PrintRecLabelsByLinePdf', 'width=400px', 'height=600px');
  }

  function printReceiptHeaderLabels(ic, number, type) {
    popupParameters="ReceiptLine_icRecHeader=" + ic + ";viewNow=Y" ;

    var newInputField = "<input type=hidden name=ReceiptLine_icRecHeader value='" + ic + "'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_icRecHeader value='" + ic + "'>";
    newInputField = newInputField + "<input type=hidden name=allowEdit value='N'>";
    newInputField = newInputField + "<input type=hidden name=returnPage value='/browse/browse.jsp'>";
    newInputField = newInputField + "<input type=hidden name=returnHandler value='BrowseRetrieveById'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptNumber value='" + number + "'>";
    newInputField = newInputField + "<input type=hidden name=ReceiptHeader_receiptType value='" + type + "'>";

    setHiddenFields(newInputField);

    doSubmit('/receipts/rec_labels.jsp', 'ReceiptHeaderLabelRetrieve');
//    doSubmitToPopup('', 'PrintRecLabelsByHeaderPdf', 'width=400px', 'height=600px');
  }

  function returnToAsset()
  {
    var ic = '<%=headerEncoder.encodeForJavaScript((String)request.getAttribute("Asset_tagNumber"))%>';
    var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
    var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='Asset_tagNumber' id='Asset_tagNumber'><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'/><input type='hidden' name='Asset_description' id='Asset_description'>";
    setHiddenFields(newInputField);
    $('#Asset_tagNumber').val(ic);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit('/asset/asset_general.jsp', 'AssetRetrieveById');
  }

  function addAssetOption()
  {
	var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
	var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='Asset_tagNumber' value=''><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'><input type='hidden' name='Asset_description' id='Asset_description'/>"+assetTNum;
    setHiddenFields(newInputField);
    $('.Asset_trackingNumber:last').val(assetTrackingNumber);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit("/asset/asset_general.jsp", 'DoNothing');
  }

  function addAssetNoteOption()
  {
    var ic = '<%=headerEncoder.encodeForJavaScript((String)request.getAttribute("Asset_tagNumber"))%>';
    var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
    var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='AssetNote_tagNumber' id='AssetNote_tagNumber'/><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'/><input type='hidden' name='Asset_description' id='Asset_description'/>"+assetTNum;
    setHiddenFields(newInputField);
    $('.Asset_trackingNumber:last').val(assetTrackingNumber);
    $('#AssetNote_tagNumber').val(ic);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit('/asset/note/assetnote_review.jsp', 'DoNothing');
  }

  function addAssetCostOption()
  {
    var ic = '<%=headerEncoder.encodeForJavaScript((String)request.getAttribute("Asset_tagNumber"))%>';
    var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
    var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='AssetCost_tagNumber' id='AssetCost_tagNumber'/><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'/><input type='hidden' name='Asset_description' id='Asset_description'/>"+assetTNum;
    setHiddenFields(newInputField);
    $('.Asset_trackingNumber:last').val(assetTrackingNumber);
    $('#AssetCost_TagNumber').val(ic);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit("/asset/cost/assetcost_review.jsp", 'DoNothing');
  }

  function addAssetLocationOption()
  {
    var ic = '<%=headerEncoder.encodeForJavaScript((String)request.getAttribute("Asset_tagNumber"))%>';
    var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
    var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='AssetLocation_tagNumber' id='AssetLocation_tagNumber'/><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'/><input type='hidden' name='Asset_description' id='Asset_description'/>"+assetTNum;
    setHiddenFields(newInputField);
    $('.Asset_trackingNumber:last').val(assetTrackingNumber);
    $('#AssetLocation_tagNumber').val(ic);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit("/asset/location/assetlocation_review.jsp", 'DoNothing');
  }

  function addAssetServiceOption()
  {
    var ic = '<%=headerEncoder.encodeForJavaScript((String)request.getAttribute("Asset_tagNumber"))%>';
    var itemNumber = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_itemNumber"))%>';
    var description = '<%=headerEncoder.encodeForJavaScript((String) request.getAttribute("Asset_description"))%>';
    var newInputField = "<input type='hidden' name='AssetService_tagNumber' id='AssetService_tagNumber'/><input type='hidden' name='Asset_itemNumber' id='Asset_itemNumber'/><input type='hidden' name='Asset_description' id='Asset_description'/>"+assetTNum;
    setHiddenFields(newInputField);
    $('.Asset_trackingNumber:last').val(assetTrackingNumber);
    $('#AssetService_tagNumber').val(ic);
    $('#Asset_itemNumber').val(itemNumber);
    $('#Asset_description').val(description);
    doSubmit("/asset/service/assetservice_review.jsp", 'DoNothing');
  }

  function viewSupplierEvaluation(icPoHeader, vendorId, poNumber)
    {
        var newInputField = "<input type='hidden' name='PerformanceDetail_icPoHeader' value='"+ icPoHeader +"'>"+
        "<input type=hidden name='Vendor_vendorId' value='"+ vendorId +"'>" +
        "<input type=hidden name='poNumber' value='"+ poNumber +"'>" +
        "<input type=hidden name='returnPage' value='/admin/supplier/supplier_info.jsp'>" +
        "<input type=hidden name='returnHandler' value='VendorRetrieveById'>";
        setHiddenFields(newInputField);
      <%if(role.getAccessRights("VEND") == 99)
      {%>
        doSubmit("/admin/supplier/supplier_eval.jsp", "VendorPerformanceCreate");
      <%}
      else
      {%>
          doSubmit("/admin/supplier/supplier_eval.jsp", "ViewSupplierEvaluation");
        <%}%>
    }
    function createSupplierEvaluation(icPoHeader, vendorId, poNumber, rated)
    {
        var newInputField = "<input type='hidden' name='PerformanceDetail_icPoHeader' value='"+ icPoHeader +"'>"+
        "<input type=hidden name='Vendor_vendorId' value='"+ vendorId +"'>" +
        "<input type=hidden name='poNumber' value='"+ poNumber +"'>" +
        "<input type=hidden name='returnPage' value='/admin/supplier/supplier_info.jsp'>" +
        "<input type=hidden name='returnHandler' value='VendorRetrieveById'>" +
		"<input type=hidden name='PoHeader_vendorId' value='" + vendorId + "'>" +
		"<input type=hidden name='PoHeader_rated' value='" + rated + "'>";
        setHiddenFields(newInputField);

		frm.PoHeader_icPoHeader.value = icPoHeader;

		doSubmit("/admin/supplier/supplier_eval.jsp", "VendorPerformanceRetrieveByOrder");
    }

    function viewOrder(icPoHeader)
    {
        popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
      doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=<%=formWidth%>', 'HEIGHT=700px');
    }

    function Barcode()
    {
      popupParameters = "uid=${esapi:encodeForJavaScript(userId)}" + ";oid=<%=oid%>";
      var j=0;
      for (var i=0; i<15; i++) {
        var check = frm.c_checkbox[i];
      //	alert(i);
        if (check.checked==true) {
          popupParameters = popupParameters + ";Asset_tagNumber" + j + "=" + frm.Asset_tagNumber[i].value;
          j++;
        }
      }

    alert(popupParameters);
    popupParameters = popupParameters + ";qty=" + j;
    //doSubmit('','AssetUnprinted');
    doSubmitToPopup('','AssetUnprinted','width=775px','height=850px');
    }

    function viewReportQueue(rqName,icRQ) {
      frm.browseName.value = rqName;
      popupParameters = "browseName=" + rqName;
      var newInputField = "<input type=hidden name='ReportQueue_icReportQueue' value='" + icRQ + "'>";
      setHiddenFields(newInputField);
      doSubmit("/browse/browse_filter_reportqueue_options.jsp", "ReportQueueRetrieveById");
    }

  function printInvoices()
  {
    var ckboxElements = frm.elements.item("c_checkbox");
    var icHeaderElements = frm.elements.item("InvoiceHeader_icIvcHeader");
    var recordSelected = false;

    if (ckboxElements.length > 1)
    {
      for (var i = 0; i < ckboxElements.length; i++)
      {
        if (ckboxElements(i).checked)
        {
          recordSelected = true;
          break;
        }
      }
      if (recordSelected)
      {
        for (var i = ckboxElements.length - 1; i >= 0 ; i--)
        {
          if (!ckboxElements(i).checked)
          {
            if (icHeaderElements != undefined && icHeaderElements(i))
            {
              icHeaderElements(i).removeNode(true);
            }
          }
        }
      }
    }
    else
    {
      var ckbox = ckboxElements;
      if (ckbox.checked)
      {
        recordSelected = true;
      }
    }

    if (!recordSelected) {
      alert("You have not selected any invoices to print!");
      return false;
    }

    doSubmit('/invoice/iv_batch_pdf.jsp', 'BrowseSetInputRequestValues;PrintApprovedInvoices');
  }

  function viewRoutingList(ic)
  {
<%	if (user.isAnOverrider()) {%>
    frm.RequisitionHeader_icReqHeader.value = ic;
    var newInputFields = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>" +
    	"<input type=hidden name='override' value='Y'>";
    setHiddenFields(newInputFields);
    doSubmit('/requests/req_approval.jsp', 'InspectionRetrieveMultipleDetail;RequisitionApprovalRetrieve');
<%	} else {%>
    popupParameters = "RequisitionHeader_icReqHeader=" + ic + ";ApprovalLog_icHeader=" + ic + ";previewOnly=Y";
    doSubmitToPopup('/requests/req_routinglist.jsp', 'ApprovalLogRetrieveByHeader', 'width=<%=formWidth%>', 'height=480px');
<%	}%>
  }

  function viewPoRoutingList(ic, type)
  {
<%	if (user.isAnOverrider()) {%>
	var newInputFields = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
	newInputFields = newInputFields + "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
    newInputFields = newInputFields + "<input type=hidden name='override' value='Y'>";
    setHiddenFields(newInputFields);
    doSubmit('/orders/po_approval.jsp', 'PoApprovalRetrieve');
<%	} else {%>
	viewPo(ic, type);
<%	}%>
  }

  function viewInvoiceRoutingList(ic)
  {
<%	if (user.isAnOverrider()) {%>
    var newInputFields = "<input type='hidden' name='InvoiceHeader_icIvcHeader' value='" + ic + "'>" +
    	"<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>" +
    	"<input type=hidden name='override' value='Y'>";
    setHiddenFields(newInputFields);
    doSubmit('/invoice/iv_approval.jsp', 'InvoiceApprovalRetrieve');
<%	} else {%>
    popupParameters = "InvoiceHeader_icIvcHeader=" + ic + ";ApprovalLog_icHeader=" + ic + ";previewOnly=Y;formtype=IVC";
    //doSubmitToPopup('/invoice/iv_routinglist_popup.jsp', 'ApprovalLogRetrieveByHeader', 'height=450px', 'width=<%=formWidth%>');
<%	}%>
  }

  function selectItemsForOrder(rfqhic, rfqtype)
  {
    frm.RfqHeader_icRfqHeader.value = rfqhic;

    doSubmit('/rfq/rfq_intent_to_award.jsp', 'RfqRetrieve');
    //doSubmit('/orders/po_select_supplier.jsp', 'RfqVendorRetrieveByHeader');
  }

  function viewAuditRecord(ic)
  {
    popupParameters = "AuditRecord_ic=" + ic;
    doSubmitToPopup('/admin/auditrecord.jsp', 'AuditRecordRetrieveById', 'height=450px', 'width=<%=formWidth%>');
  }

  function viewMyOpenReq(ic, type)
  {
    frm.viewType.value = "WIZARD";
    var icHeaderElements = frm.elements.item("RequisitionHeader_icReqHeader");
    for (var i = icHeaderElements.length - 1; i >= 0 ; i--)
    {
      if (icHeaderElements != undefined && icHeaderElements(i))
      {
        icHeaderElements(i).removeNode(true);
      }
    }
    var newInputField = "<input type=hidden name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
  }

	function requestSelectedItems() {
		var lookupHandler = "CatalogItemLookup";
		var pg = "/requests/req_review.jsp";
		var handlers = "BrowseSetInputRequestValues;RecentReqItemSetItemLookupValues;RequisitionCreate;" + lookupHandler + ";RequisitionRetrieve";
		var hiddenFields = "";

		frm.viewType.value = "WIZARD";

		hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"P\">";
		hiddenFields = hiddenFields + "<input type=hidden name=\"formtype\" value=\"REQ\">";

		setHiddenFields(hiddenFields);

		doSubmit(pg, handlers);
	}

	function viewPrequalifiedVendor(vendorId, emailAddr) {
		 var newInputField = "<input type='hidden' name='VendorRegister_contactEmailAddr' value='" + emailAddr + "'>";
		setHiddenFields(newInputField);
<%	if (oid.equals("BSC04P")) {%>
		doSubmit('/admin/supplier/bsc_prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
<%	} else {%>
		doSubmit('/admin/supplier/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
<%	}%>
	}

	function summaryProcurementWorkloadReq(code,type)
  {

  	setOriginalFilter("RequisitionHeader_assignedBuyer", "=", code );

  	if(type == "A" )
  	{	setOriginalFilter("RequisitionHeader_status", ">", '1030' );
  		setOriginalFilter("RequisitionHeader_status", "<", '2000' );
	}
	else if(type == "S" )
  		 {
  		 	setOriginalFilter("RequisitionHeader_status", ">=", '2000' );
  			setOriginalFilter("RequisitionHeader_status", "<", '3000' );
		 }
		 else if(type == "PA" )
  		 {
  		 	setOriginalFilter("RequisitionHeader_status", ">=", '3000' );
  			setOriginalFilter("RequisitionHeader_status", "<", '3030' );
  		}
		else if(type == "T" )
  		 {
  		 	setOriginalFilter("RequisitionHeader_status", ">", '1030' );
  			setOriginalFilter("RequisitionHeader_status", "<", '3030' );
  		}
  	browse('summ-req-buyer');
  }
	  function viewInspectorAssignment() {

		    frm.browseName.value = "inspector-assignment-wkld-by-line";
		    setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "UNASSIGNED", "OR", "Y", "N");
		    setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "PURCHASING", "", "Y", "N");


		    setHiddenFields("<input type=hidden name=\"assignFrom\" value=\"Unassigned\">");


	    doSubmit('/workload/inspector_assignment.jsp', 'BrowseRetrieve');
	}

  function viewBuyerAssignment( code ) {
	clearSortMe();
  var newInputField =  "<input type=hidden name=\"buyerAssigned\" value=\"" + code + "\" >";
  <%-- var buyerhidden = "<input type=hidden name=\"buyerAssigned\" value=\"" + buyer + "\">"; --%>

<%	if (PropertiesManager.getInstance(oid).getProperty("ASSIGNMENT", "AssignByLine", "N").equalsIgnoreCase("Y")) {%>
    frm.browseName.value = "buyer-assignment-wkld-by-line";
    setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
    setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
<%	} else {%>
    frm.browseName.value = "buyer-assignment-wkld";
    setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
    setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
<%	}%>

    setHiddenFields("<input type=hidden name=\"assignFrom\" value=\"Unassigned\">");
    setHiddenFields(newInputField);

    doSubmit('/workload/buyer_assignment.jsp', 'BrowseRetrieve');
  }

	function browseWorkloadView() {
        document.getElementById("originalFilterFields").innerHTML='';
		browse('prm-req-worlkloadview');
		doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
	}

	function reviewXrefComboGeneral(icXref,xrefType) {
		var hiddenFields = "";
		hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_icXref\" value=\""+icXref+"\">";
		doSubmit('/admin/auto_accounting/autoaccounting_review.jsp', 'XrefComboRetrieveBy');
	}

	function receiptPackageOrder(ic) {
		var newInputField = "<input type='hidden' name='receiptMethod' value='ReceiveByPackage'>";
		newInputField = newInputField + "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='" + ic + "'>";

		setHiddenFields(newInputField);
		doSubmit('/receipts/rec_general_info.jsp','ReceiptPackageCreateFromOrder;ReceiptLineCreateFromPoLineList');
	}

	function receiptFinalizeOrder(ic) {
		var newInputField = "<input type='hidden' name='ReceiptHeader_icRecHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='receiptMethod' value='FinalizeReceipt'>";
		setHiddenFields(newInputField);
		doSubmit('/receipts/rec_review.jsp','ReceiptRetrieve');
	}

	function viewPreviewDisb(icDsbHeader) {
		popupParameters = "DisbHeader_icDsbHeader=" + icDsbHeader;
		doSubmitToPopup('/inventory/dsb_preview.jsp', 'DisbursementRetrieve', 'WIDTH=<%=formWidth%>', 'HEIGHT=700px');
	}

	function viewInventoryReqPreview(icReqHeader) {
		popupParameters = "RequisitionHeader_icReqHeader=" + icReqHeader;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=<%=formWidth%>', 'HEIGHT=540px');
	}

	function viewLabel(ic, labelCode) {
		var newInputField = "<input type='hidden' name='Labels_ic' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/labels/labels_edit.jsp','LabelsRetrieveByIc');
	}

	function getLabelHelp(code, module, moduleType) {
		alert("Help for labels management will be available soon!");
		return;
	}

	function viewReceiptPDF(icReceiptHeader) {
		popupParameters = "ReceiptHeader_icRecHeader=" + icReceiptHeader;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		popupParameters = popupParameters + ";emailTo=N";
		doSubmitToPopupFull('', 'PrintRecPdf');
	}

	function viewInvBinLocation(ic, itemNumber, itemLocation, qtyReceived, itemCost, commodity, umCode, icMsr, inventoryFlag, lineNumber, msrNumber, icLineHistory) {

		frm.browseName.value = 'items-receipt-inventory';

		var newInputField = "<input type=hidden name=\"InvItem_itemNumber\" value=\""+itemNumber+"\">"+
		"<input type=hidden name=\"InvLocation_itemNumber\" value=\""+itemNumber+"\">"+
		"<input type=hidden name=\"InvBinLocation_itemNumber\" value=\""+itemNumber+"\">"+
		"<input type=hidden name=\"InvLocation_itemLocation\" value=\""+itemLocation+"\">"+
		"<input type=hidden name=\"InvBinLocation_itemLocation\" value=\""+itemLocation+"\">"+
		"<input type=hidden name=\"duomQtyReceived\" value=\"0\">"+
		"<input type=hidden name=\"index\" value=\"0\">"+
		"<input type=hidden name=\"receiptRow\" value=\"0\">"+
		"<input type=hidden name=\"qtyReceived\" size=10 value=\""+qtyReceived+"\" >"+
		"<input type=hidden name=\"commodity\" value=\""+commodity+"\">"+
		"<input type=hidden name=\"InvBinLocation_cost\" value=\""+itemCost+"\">"+
		"<input type=hidden name=\"PoLine_umCode\" value=\""+umCode+"\">"+
		"<input type=hidden name=\"InvBinLocation_tempIc\" value=\""+ic+"\">"+
		"<input type=hidden name=\"ReceiptLine_icRecLine\" value=\""+ic+"\">"+
		"<input type=hidden name=\"PoHeader_icMsrHeader\" value=\""+icMsr+"\">" +
		"<input type=hidden name=\"PoHeader_msrNumber\" value=\""+msrNumber+"\">" +
		"<input type=hidden name=\"ReceiptLine_inventoryFlag\" value=\""+inventoryFlag+"\">"  +
		"<input type=hidden name=\"PoLine_icLineHistory\" value=\""+icLineHistory+"\">" +
		"<input type=hidden name=\"ReceiptLine_lineNumber\" value=\""+lineNumber+"\">";
		"<input type=hidden name=\"RequisitionLine_icLineHistory\" value=\""+icLineHistory+"\">";

		setHiddenFields(newInputField);

		doSubmit('inventory/inv_bin_locations_receive.jsp', 'ReceiptLineRetrieve;RequisitionLineRetrieveByMsr;InvBinLocationRetrieveByItem');
	}

	function filterMeWithField()
	{
		var filterValue = trim(frm.filter);
		if ( isEmpty(filterValue ) )
		{
			alert("Don't you want to specify what you want to filter first?")
			frm.filter.focus();
			return;
		}

		var colname = frm.dbcolumn[frm.dbcolumn.selectedIndex].value;
		var operator = frm.filtor[frm.filtor.selectedIndex].value;

		if (colname.indexOf('_trackingNumber')>=0) browseName = browseName + '-pro';

		setFilter(colname, operator, filterValue);

		frm.browseName.value = browseName;
		frm.pageSize.value = pageSize;
		doSubmit(frm.browsePage.value, 'BrowseFilterSort');
	}

</SCRIPT>