<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form_check_printing.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<script value="JavaScript">

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

    if (!recordSelected)
    {
      alert("You have not selected any invoices to print!");
      return false;
    }

    if (!verifyAction('Execute printing process for these invoices?')) {
    	return false;
    }

//  doSubmit('/invoice/inv_printing_checks.jsp', 'PrintCheckInvoices');
    doSubmit('/menu/main_menu.jsp', 'PrintCheckInvoices');
}

</script>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="browsePage" value="/browse/browse.jsp"/>
<tsa:hidden name="fromPage" value="/browse/browse.jsp"/>
<% if ( browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") ){ %>

<tsa:hidden name="discountsAnyway" value="Y"/>
<% } %>
<%
  String sumTotal = HiltonUtility.ckNull((String) request.getAttribute("sumTotal"));
  String rowTotal = HiltonUtility.ckNull((String) request.getAttribute("rowTotal"));
  if (HiltonUtility.isEmpty(sumTotal)){
	  sumTotal = "0.00";
  }
  if (HiltonUtility.isEmpty(rowTotal)){
	  rowTotal = "0.00";
  }

  String icPoHeaderFromContractNaming = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));

  String catalogId = "";
  if ( browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") )
  {
%>
<table border=0 width=680px>
<tr>
  <td width=20% align=center>&nbsp;</td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: addInvoiceForPayment();void(0);">add invoice</a></div></td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: browseFilter('print-check-invoices');">return</a></div></td>
  <td width=20% align=center><div id="pxbutton"><a href="javascript: printCheckInvoices(); void(0);">print checks</a></div></td>
</tr>
</table>
<% } %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

<%	if (browseObject.getBrowseName().equalsIgnoreCase("invoices-approved") || browseObject.getBrowseName().equalsIgnoreCase("print-assigned-reqs") || browseObject.getBrowseName().equalsIgnoreCase("myopenreqs") ||
		browseObject.getBrowseName().equalsIgnoreCase("requisitionheader-assigned") || browseObject.getBrowseName().equalsIgnoreCase("print-check-invoices") ||
		browseObject.getBrowseName().equalsIgnoreCase("invoice-exported-reset") ) { %>
      displayArea("selectPrintDisplay");
      hideArea("resetOriginalOption");
      hideArea("resetButton");
<%	} %>

  function checkHiddenMenu()
  {
<%		if (browseObject.getBrowseName().indexOf("catalog") >= 0 || browseObject.getBrowseName().equals("req-approval-list") || browseObject.getBrowseName().equals("req-check-approval-list")) {%>
    hideArea("navTable");
    displayArea("menuBarSpacer");
<%		} %>
  }


  function viewInvoice(ic) {
    frm.viewType.value = "WIZARD";
    var myCell = document.getElementById("hiddenFields");
    myCell.innerHTML = "";
    var newInputField = "<input type='hidden' name='InvoiceHeader_icIvcHeader' value='" + ic + "'>";

    myCell.innerHTML = newInputField;
    doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
  }

  function returnMe(code)
  {
    var title = '<%=browseObject.getTitle()%>';
    var myCell = document.getElementById("hiddenFields");

    if ((title.indexOf("Inventory Location")) >= 0)
    {
      myCell.innerHTML = "";
      var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"InvBinLocation_itemLocation\" value=\"" + code + "\" >";
      myCell.innerHTML = newInputField;
//      doSubmit('/inventory/physical_count.jsp', 'InvBinLocationRetrieveByLocation');
	  doSubmit('/inventory/physical_count.jsp', 'InvItemRetrieveBinsByLocation') ;
    }
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

  function viewAuditRecord(ic)
  {
    popupParameters = "AuditRecord_ic=" + ic;
    doSubmitToPopup('/admin/auditrecord.jsp', 'AuditRecordRetrieveById', 'height=450px', 'width=680px');
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

// End Hide script -->
</SCRIPT>