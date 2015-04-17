<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_replenish_items.jsp"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="R"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: createReplenishment(); void(0);">Create</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("filterTextDisplay");
	hideArea("resetOriginalOption");

	function createReplenishment()
	{
		var hiddenFields = "";

		if (checkItems())
		{
			var checkboxes = document.all("c_checkbox");
			if (typeof checkboxes != "undefined" && checkboxes != null && checkboxes.length > 1)
			{
				for (var i = 0; i < checkboxes.length; i++)
				{
					var cbox = checkboxes(i);
					if (cbox.checked == true)
					{
						var itemLocation = frm.elements.item("InvLocation_id_itemLocation")(i).value;
						var itemNumber = frm.elements.item("InvLocation_id_itemNumber")(i).value;
						var qty = frm.elements.item("InvLocation_qtyEoq")(i).value;

						hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
						hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
					}
				}
			}
			else
			{
				var cbox = checkboxes;

				if (typeof checkboxes != "undefined" && checkboxes != null && cbox.checked == true)
				{
					if (frm.elements.item("InvLocation_id_itemNumber").length > 1) {
						var itemLocation = frm.elements.item("InvLocation_id_itemLocation")(0).value;
						var itemNumber = frm.elements.item("InvLocation_id_itemNumber")(0).value;
						var qty = frm.elements.item("InvLocation_qtyEoq")(0).value;
					} else {
						var itemLocation = frm.elements.item("InvLocation_id_itemLocation").value;
						var itemNumber = frm.elements.item("InvLocation_id_itemNumber").value;
						var qty = frm.elements.item("InvLocation_qtyEoq").value;
					}

					hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
					hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"" + qty + "\">";
				}
			}
			setHiddenFields(hiddenFields);

	<%	if (s_view.equals("CLASSIC")) { %>
				doSubmit('requests/req_summary.jsp', 'BrowseSetInputRequestValues;RequisitionCreate;InvItemLookup;RequisitionRetrieve');
	<%	}
			else if (s_view.equals("WIZARD")) {%>
				doSubmit('requests/req_general_info.jsp', 'BrowseSetInputRequestValues;RequisitionCreate;InvItemLookup;RequisitionLineRetrieveByHeader');
	<%	} %>
		}
	}

	function checkItems()
	{
		var checkboxes = document.all("c_checkbox");

		if (typeof checkboxes != "undefined" && checkboxes != null && checkboxes.length > 1)
		{
			for (var i = 0; i < checkboxes.length; i++)
			{
				if (frm.c_checkbox[i].checked)
				{
					return true;
				}
			}
		}
		else
		{
			var cbox = checkboxes;

			if (typeof checkboxes != "undefined" && checkboxes != null && cbox.checked == true)
			{
				return true;
			}
		}

//		alert("You have not selected any items to Replenish!");
		return true ;
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('browse/browse_replenish_items.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>