<!-- saved from url=(0022)http://internet.e-mail -->

<html>

<head>

<script language="JavaScript">
function getElement(elementName)
{
   var numDocuments = document.forms[0].elements.length;
   var i = 0
   for (i = 0; i < numDocuments; i++)
   {
      if (document.forms[0].elements[i].name == elementName)
      {
         return document.forms[0].elements[i];
      }
   }
   return null;
}

function recalculateLineTotal(pElem)
{
   var tempIndex = pElem.name.lastIndexOf('_');
   if (tempIndex == -1)
   {
      return;
   }
   var lineIndex = pElem.name.substring(tempIndex + 1);

   var item_qty = getElement("qty_" + lineIndex);
   var item_amt = getElement("amt_" + lineIndex);

   if ((item_qty == null) || (item_amt == null))
   {
      return;
   }

   var line_total = getElement("line_total_" + lineIndex);
   if ((item_qty.value == "") || (item_amt.value == ""))
   {
      line_total.value = ""
   }
   else
   {
      line_total.value = formatAsMoney(item_qty.value * item_amt.value);
   }

   recalculateSubTotal();
}

function recalculateSubTotal()
{
   var subTotal = 0;

   if (document.forms[0].line_total_1.value != "")
   {
      subTotal += 1 * document.forms[0].line_total_1.value;
   }

   if (document.forms[0].line_total_2.value != "")
   {
      subTotal += 1 * document.forms[0].line_total_2.value;
   }

   if (document.forms[0].line_total_3.value != "")
   {
      subTotal += 1 * document.forms[0].line_total_3.value;
   }

   if (document.forms[0].line_total_4.value != "")
   {
      subTotal += 1 * document.forms[0].line_total_4.value;
   }

   document.forms[0].sub_total.value = formatAsMoney(subTotal);
}

function formatFieldAsMoney(pElem)
{
   pElem.value = formatAsMoney(pElem.value);
}

function formatAsMoney(mnt)
{
   mnt -= 0;
   mnt = (Math.round(mnt*100))/100;
   return (mnt == Math.floor(mnt)) ? mnt + '.00'
             : ( (mnt*10 == Math.floor(mnt*10)) ?
                      mnt + '0' : mnt);
}

function processInvoice()
{
   if ((!document.forms[0].order_number.value) ||
       (document.forms[0].order_number.value == ""))
   {
      alert("Order number is required.");
      return;
   }

   var tempInvoiceNumber = document.forms[0].invoice_number.value;
   if (tempInvoiceNumber == "")
   {
      tempInvoiceNumber = Math.round((Math.random()*123459)+1);
      document.forms[0].invoice_number.value = tempInvoiceNumber
   }

   top.saveOrderData(document.forms[0], document.forms[0].order_number.value);

   alert("Invoice accepted.  Invoice #: " + tempInvoiceNumber);
   window.location="menu.html";
}

function cancelInvoice()
{
   var cancelConfirm = confirm("Cancel invoice?");
   if (cancelConfirm == true)
   {
      window.location="menu.html";
   }
}

function retrieveOrder()
{
   if (document.forms[0].order_number.value == "")
   {
      alert("Please enter a valid order number.");
   }
   else
   {
      /*(
      var tempFrame = null;
      for (frame in top.frames)
      {
         tempFrame = top.frames[frame];
         if (tempFrame != null)
         {
         for (bar in tempFrame)
         {
            alert("TempFrame[" + frame + "] obj[" + bar + "]=" + tempFrame[bar]);
         }
         }
      }
      */

      var orderData = top.getOrderData(document.forms[0].order_number.value);
      if (!orderData)
      {
         alert("Order not found.");
      }
      else
      {
         var tempValue = null;
         var numElements = document.forms[0].elements.length;
         var i = 0

         for (i = 0; i < numElements; i++)
         {
            if (document.forms[0].elements[i].type == "text")
            {
                tempValue = orderData[document.forms[0].elements[i].name];
                if (tempValue)
                {
                  document.forms[0].elements[i].value = tempValue;
                }
                else
                {
                   document.forms[0].elements[i].value = "";
                }
            }
          }

         recalculateLineTotal(document.forms[0].qty_1);
         recalculateLineTotal(document.forms[0].qty_2);
         recalculateLineTotal(document.forms[0].qty_3);
         recalculateLineTotal(document.forms[0].qty_4);
      }
   }
}

function numbersonly(myfield, e, dec)
{
  var key;
  var keychar;

  if (window.event)
    key = window.event.keyCode;
  else if (e)
    key = e.which;
  else
    return true;

  keychar = String.fromCharCode(key);

  // control keys
  if ((key==null) || (key==0) || (key==8) ||
      (key==9) || (key==13) || (key==27))
    return true;

  // numbers
  else if ((("0123456789").indexOf(keychar) > -1))
    return true;

  // decimal point jump
  else if (dec && (keychar == "."))
  {
     myfield.form.elements[dec].focus();
     return false;
  }
  else if ((".").indexOf(keychar) > -1)
        return true;
  else
    return false;
}
</script>

<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=contextPath%>/system/orange.css">
<title>Electronic Invoice</title>
</head>

<form METHOD="post" ACTION="processInvoice.html">
<body>
	
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Submit Invoice</div>
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

<b><font size="2">Order:</font></b>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber4">
  <tr>
    <td width="50%">
   	<table border="0" cellpadding="0" cellspacing="4">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order #:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="order_number" size="20" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">&nbsp;<button  STYLE="font-size: 12px" onclick="retrieveOrder()">Lookup</button>
		</td>

      </tr>
    </table>
	</td>
    <td width="50%">
    <table border="0" cellpadding="0" cellspacing="4" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
     	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
        <tr>
          <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Account #:&nbsp;</font></td>
          <td width="70%" style="border-style: none; border-width: medium">
            <input type="text" name="account_number" size="20" STYLE="font-size: 12px">
  		</td>
        </tr>
      </table>
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr><td><br></br></td></tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Ordered By:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_name" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Vendor:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_code" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Phone:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_phone_number" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Address:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Shipped To:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">&nbsp;</td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_line_2" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">&nbsp;</td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_line_2" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_city" size="20" STYLE="font-size: 12px">
          <font size="2">, </font>
          <input type="text" name="vendor_address_state" size="2" maxlength="2" STYLE="font-size: 12px"></FONT>
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_city" size="20" STYLE="font-size: 12px">
          <font size="2">, </font>
          <input type="text" name="ordered_by_address_state" size="2" maxlength="2" STYLE="font-size: 12px"></FONT>
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:&nbsp;</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:&nbsp;</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
  </tr>
  <tr><td colspan=2><hr color="#000099"></hr></td></tr>
</table>


<P></P>
<b><font size="2">Invoice:</font></b>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber4">
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Invoice #:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="invoice_number" size="20" STYLE="font-size: 12px">
		</td>

      </tr>
    </table>
	</td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Entered By:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Invoice Date:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="invoice_date" size="15" STYLE="font-size: 12px" value="02-03-2005">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Phone:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by_phone_number" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Terms:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <SELECT NAME="terms" STYLE="font-size: 12px">
            <OPTION value="15_DAYS">Net 15 days</OPTION>
            <OPTION value="30_DAYS">Net 30 days</OPTION>
            <OPTION value="PER_CONTRACT">Per Contract</OPTION>
          </SELECT>
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: solid; border-bottom-width: 0">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Email:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by_email" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Gross Amt:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="gross_amount" size="15" STYLE="font-size: 12px" STYLE="text-align:right" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Remit To:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_name" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Tax Amt:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="tax_amount" size="15" STYLE="font-size: 12px" STYLE="text-align:right" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Address:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">VAT:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="vat_amount" size="15" STYLE="font-size: 12px" STYLE="text-align:right" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_line_2" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Shipping Amt:&nbsp;</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="shipping_amount" size="15" STYLE="font-size: 12px" STYLE="text-align:right" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:&nbsp;</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_city" size="20" STYLE="font-size: 12px">
          <font size="2">, </font>
          <input type="text" name="remit_to_address_state" size="2" maxlength="2" STYLE="font-size: 12px"></FONT>
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="0" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">&nbsp;</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:&nbsp;</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr><td colspan=2><hr color="#000099"></hr></td></tr>
</table>

<P></P>
<b><font size="2">Notes:</font></b>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber8">
  <tr>
    <td width="100%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" name="note_one" size="90" STYLE="font-size: 12px">
    </P>
    </td>
  </tr>
  <tr>
    <td width="100%" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: none; border-top-width: medium; border-bottom-style: solid; border-bottom-width: 0">
    <p align="center">
    <input type="text" name="note_two" size="90" STYLE="font-size: 12px">
    </td>
  </tr>
  <tr><td colspan=2><hr color="#000099"></hr></td></tr>
</table>


<P></P>
<b><font size="2">Detail:</font></b>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber8">
  <TH><font size="2">Line</FONT></TH><TH><font size="2">Description</font></th><TH><font size="2">Quantity</FONT></th><TH><font size="2">Price</font></TH><TH><font size="2">Line Total</FONT></TH>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">01</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_1" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_1" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_1" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_1" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">02</font></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_2" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_2" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_2" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_2" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">03</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_3" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_3" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_3" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_3" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">04</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_4" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_4" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 0; border-right-style: solid; border-right-width: 0; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_4" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_4" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">&nbsp;</P>
    </td>
    <td width="65" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
    <td width="4" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
    <td width="7" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 0; border-bottom-style: none; border-bottom-width: medium">
    <p align="left"><font size="2"><B>Sub Total:</B></FONT></P>
    </td>
    <TD><p align="center"><input type="text" name="sub_total" size="7" STYLE="font-size: 12px" value="0.00" READONLY="readonly"></P></TD>
  </tr>
</table>

<P></P>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="680px" id="AutoNumber8">
  <tr>
    <td width="100%">
    <p align="center">
    <button onclick="processInvoice()">Submit Invoice</BUTTON>
    <!-- <input type="submit" value="Cancel" size="90"> -->
    <button onclick="cancelInvoice()">Cancel</BUTTON>
    </P>
    </td>
  </tr>
</table>

</body>
</FORM>

</html>

