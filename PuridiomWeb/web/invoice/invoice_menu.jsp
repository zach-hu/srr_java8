<!-- saved from url=(0022)http://internet.e-mail -->
<html>

<head>
<script language="JavaScript">
function cancel()
{
    window.location="menu.html";
}

function reviewInvoice(index)
{
   var orderNum = document.forms[0]["order_number_" + index].value;
   if (!orderNum)
   {
      alert("Invalid invoice.");
      return;
   }

   top["review_order_number"] = orderNum;
   window.location="review.html";
}

function retrieveInvoices()
{
   document.forms[0].vendor_1.value = "";
   document.forms[0].order_number_1.value = "";
   document.forms[0].invoice_number_1.value = "";
   document.forms[0].note_1.value = "";
   document.forms[0].sub_total_1.value = "";
   document.forms[0].reviewed_1.value = "";

   document.forms[0].vendor_2.value = "";
   document.forms[0].order_number_2.value = "";
   document.forms[0].invoice_number_2.value = "";
   document.forms[0].note_2.value = "";
   document.forms[0].sub_total_2.value = "";
   document.forms[0].reviewed_2.value = "";

   document.forms[0].vendor_3.value = "";
   document.forms[0].order_number_3.value = "";
   document.forms[0].invoice_number_3.value = "";
   document.forms[0].note_3.value = "";
   document.forms[0].sub_total_3.value = "";
   document.forms[0].reviewed_3.value = "";

   document.forms[0].vendor_4.value = "";
   document.forms[0].order_number_4.value = "";
   document.forms[0].invoice_number_4.value = "";
   document.forms[0].note_4.value = "";
   document.forms[0].sub_total_4.value = "";
   document.forms[0].reviewed_4.value = "";

   document.forms[0].vendor_5.value = "";
   document.forms[0].order_number_5.value = "";
   document.forms[0].invoice_number_5.value = "";
   document.forms[0].note_5.value = "";
   document.forms[0].sub_total_5.value = "";
   document.forms[0].reviewed_5.value = "";

   var allOrders = top["all_orders"];
   var tempOrder = null;
   var index = 1;
   for (orderNumber in allOrders)
   {
      tempOrder = allOrders[orderNumber];
      if (tempOrder["vendor_code"])
         document.forms[0]["vendor_" + index].value = tempOrder["vendor_code"];
      if (tempOrder["order_number"])
         document.forms[0]["order_number_" + index].value = tempOrder["order_number"];
      if (tempOrder["invoice_number"])
         document.forms[0]["invoice_number_" + index].value = tempOrder["invoice_number"];
      if (tempOrder["note_one"])
         document.forms[0]["note_" + index].value = tempOrder["note_one"];
      if (tempOrder["sub_total"])
         document.forms[0]["sub_total_" + index].value = tempOrder["sub_total"];
      if (tempOrder["reviewed"])
      {
         document.forms[0]["reviewed_" + index].value = "X";
      }
      index++;
   }
}
</script>

<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=contextPath%>/system/orange.css">
<title>Electronic Invoice Presentation and Payment System - Review</title>
</head>

<form METHOD="post" ACTION="processInvoice.html">
	
<BODY ONLOAD="retrieveInvoices()">
	
<br></br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Review</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr>
				<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br></br>


<b><font size="2">Select An Invoice For Review:</font></b>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber4">
  <TH  class=browseHdr><font size="2">Vendor</FONT></TH><TH class=browseHdr><font size="2">Order #</font></th><TH class=browseHdr><font size="2">Invoice #</FONT></th><TH class=browseHdr><font size="2">Note</font></TH><TH class=browseHdr><font size="2">Line Total</FONT></TH><TH class=browseHdr>Reviewed</TH><TH class=browseHdr>&nbsp;</TH>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="vendor_1" size="15" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="order_number_1" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="15" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="invoice_number_1" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="note_1" size="52" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="sub_total_1" size="8" STYLE="font-size: 12px"  READONLY="readonly">
    </P>
    </td>
    <td align="center" width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" align="center" name="reviewed_1" size="1" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <TD><button size="2" onclick="reviewInvoice(1)">Review</BUTTON></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="vendor_2" size="15" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="5" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="order_number_2" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="invoice_number_2" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="note_2" size="52" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="sub_total_2" size="8" STYLE="font-size: 12px"  READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" align="center" name="reviewed_2" size="1" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <TD><button size="2" onclick="reviewInvoice(2)">Review</BUTTON></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="vendor_3" size="15" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="5" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="order_number_3" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="invoice_number_3" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="note_3" size="52" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="sub_total_3" size="8" STYLE="font-size: 12px"  READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" align="center" name="reviewed_3" size="1" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <TD><button size="2" onclick="reviewInvoice(3)">Review</BUTTON></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="vendor_4" size="15" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="5" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="order_number_4" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="invoice_number_4" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="note_4" size="52" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="sub_total_4" size="8" STYLE="font-size: 12px"  READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" align="center" name="reviewed_4" size="1" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <TD><button size="2" onclick="reviewInvoice(4)">Review</BUTTON></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="vendor_5" size="15" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="5" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="order_number_5" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="invoice_number_5" size="7" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="note_5" size="52" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="sub_total_5" size="8" STYLE="font-size: 12px"  READONLY="readonly">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" align="center" name="reviewed_5" size="1" STYLE="font-size: 12px" READONLY="readonly">
    </P>
    </td>
    <TD><button size="2" onclick="reviewInvoice(5)">Review</BUTTON></TD>
  </tr>
</TABLE>

<P></P>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber8">
  <tr>
    <td width="100%">
    <p align="center">
    <button onclick="cancel()">Cancel</BUTTON>
    </P>
    </td>
  </tr>
</table>
</BODY>
</FORM>
</HTML>




