<!-- saved from url=(0022)http://internet.e-mail -->

<html>

<head>

<script language="JavaScript">
function fillInOrderInfo()
{
   document.forms[0].order_number.value=top["review_order_number"];
   if (top["review_order_number"])
   {
      retrieveOrder();
   }
}

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

   document.forms[0].reviewed.value = "reviewed";

   top.saveOrderData(document.forms[0], document.forms[0].order_number.value);

   alert("Invoice saved.");
   window.location="invoice_menu.html";
}

function cancelInvoice()
{
   var cancelConfirm = confirm("Cancel?");
   if (cancelConfirm == true)
   {
      window.location="invoice_menu.html";
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
            if ((document.forms[0].elements[i].type == "text") ||
                (document.forms[0].elements[i].type == "select-one"))
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
<title>Electronic Invoice</title>
</head>

<form METHOD="post" ACTION="processInvoice.html">
<body ONLOAD="fillInOrderInfo()">

<H2>Invoice Review</H2>
<HR></hr>
<b><font size="2">Order:</font></b>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber4">
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="4" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Order #:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="order_number" size="20" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)"><button  STYLE="font-size: 12px" onclick="retrieveOrder()">Lookup</button>
		</td>

      </tr>
    </table>
	</td>
    <td width="50%" style="border-left-style: none; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="4" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
     	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
        <tr>
          <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Account #:</font></td>
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
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Ordered By:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_name" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Vendor:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_code" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Phone:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_phone_number" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Address:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Shipped To:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
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
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">&nbsp;</td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_line_2" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:</font></td>
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
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_city" size="20" STYLE="font-size: 12px">
          <font size="2">, </font>
          <input type="text" name="ordered_by_address_state" size="2" maxlength="2" STYLE="font-size: 12px"></FONT>
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="vendor_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="ordered_by_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
  </tr>
</table>

<P></P>
<b><font size="2">Invoice:</font></b>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber4">
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Invoice #:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="invoice_number" size="20" STYLE="font-size: 12px">
		</td>

      </tr>
    </table>
	</td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Entered By:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Invoice Date:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="invoice_date" size="15" STYLE="font-size: 12px" value="02-03-2005">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Phone:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by_phone_number" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Terms:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <SELECT NAME="terms">
            <OPTION value="15_DAYS">Net 15 days</OPTION>
            <OPTION value="30_DAYS">Net 30 days</OPTION>
            <OPTION value="PER_CONTRACT">Per Contract</OPTION>
          </SELECT>
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: solid; border-bottom-width: 1">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Email:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="entered_by_email" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: none; border-right-width: medium; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Gross Amt:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="gross_amount" size="15" STYLE="font-size: 12px" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Remit To:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_name" size="20" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Tax Amt:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="tax_amount" size="15" STYLE="font-size: 12px" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Address:</font></td>
        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_line_1" size="25" STYLE="font-size: 12px">
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">VAT:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="vat_amount" size="15" STYLE="font-size: 12px" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber5">
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
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">
        <p align="right"><font size="2">Shipping Amt:</font></td>
        <td width="50%" style="border-style: none; border-width: medium">
          <input type="text" name="shipping_amount" size="15" STYLE="font-size: 12px" value="0.0" onKeyPress="return numbersonly(this,event)" ONCHANGE="formatFieldAsMoney(this)">
		</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">City, State:</font></td>
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
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
   	<table border="1" cellpadding="0" cellspacing="0" style="border-width:0; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="50%" style="border-style: none; border-width: medium">&nbsp;</td>
      </tr>
    </table>
    </td>
    <td width="50%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: none; border-bottom-width: medium">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-width: 0" bordercolor="#111111" width="100%" id="AutoNumber6">
      <tr>
        <td width="30%" style="border-style: none; border-width: medium">
          <p align="right"><font size="2">Postal Code:</font></td>

        <td width="70%" style="border-style: none; border-width: medium">
          <input type="text" name="remit_to_address_postal_code" size="5" maxlength="5" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)">
        </td>
      </tr>
    </table>
    </td>
  </tr>
</table>

<P></P>
<b><font size="2">Notes:</font></b>
<table border="1" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber8">
  <tr>
    <td width="100%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">
    <input type="text" name="note_one" size="90" STYLE="font-size: 12px">
    </P>
    </td>
  </tr>
  <tr>
    <td width="100%" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: none; border-top-width: medium; border-bottom-style: solid; border-bottom-width: 1">
    <p align="center">
    <input type="text" name="note_two" size="90" STYLE="font-size: 12px">
    </td>
  </tr>
</table>


<P></P>
<b><font size="2">Detail:</font></b>
<table border="1" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber8">
  <TH><font size="2">Line</FONT></TH><TH><font size="2">Description</font></th><TH><font size="2">Quantity</FONT></th><TH><font size="2">Price</font></TH><TH><font size="2">Line Total</FONT></TH>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">01</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_1" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_1" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_1" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_1" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
    <TD>
      <SELECT NAME="status_1">
        <OPTION value="accept_status">Accept</OPTION>
        <OPTION value="reject_status">Reject</OPTION>
        <OPTION value="hold_status">Hold</OPTION>
      </SELECT>
    </TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">02</font></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_2" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_2" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_2" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_2" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
    <TD>
      <SELECT NAME="status_2">
        <OPTION value="accept_status">Accept</OPTION>
        <OPTION value="reject_status">Reject</OPTION>
        <OPTION value="hold_status">Hold</OPTION>
      </SELECT>
    </TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">03</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_3" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_3" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_3" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_3" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
    <TD>
      <SELECT NAME="status_3">
        <OPTION value="accept_status">Accept</OPTION>
        <OPTION value="reject_status">Reject</OPTION>
        <OPTION value="hold_status">Hold</OPTION>
      </SELECT>
    </TD>
  </tr>
  <tr>
    <td width="2" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center"><FONT SIZE="2">04</FONT></P>
    </td>
    <td width="65" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="item_description_4" size="65" STYLE="font-size: 12px">
    </P>
    </td>
    <td width="4" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="qty_4" size="4" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <td width="7" style="border-left-style: solid; border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left">
    <input type="text" name="amt_4" size="7" STYLE="font-size: 12px" onKeyPress="return numbersonly(this, event)" ONCHANGE="recalculateLineTotal(this)">
    </P>
    </td>
    <TD><p align="CENTER"><input type="text" name="line_total_4" size="7" STYLE="font-size: 12px" READONLY="readonly"></P></TD>
    <TD>
      <SELECT NAME="status_4">
        <OPTION value="accept_status">Accept</OPTION>
        <OPTION value="reject_status">Reject</OPTION>
        <OPTION value="hold_status">Hold</OPTION>
      </SELECT>
    </TD>

    </SELECT>
  </tr>
  <tr>
    <td width="2" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="center">&nbsp;</P>
    </td>
    <td width="65" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
    <td width="4" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">&nbsp;</TD>
    <td width="7" style="border-left-style: none; border-left-width: medium; border-right-style: none; border-right-width: none; border-top-style: solid; border-top-width: 1; border-bottom-style: none; border-bottom-width: medium">
    <p align="left"><font size="2"><B>Sub Total:</B></FONT></P>
    </td>
    <TD><p align="center"><input type="text" name="sub_total" size="7" STYLE="font-size: 12px" value="0.00" READONLY="readonly"></P></TD>
  </tr>

  </SELECT>
</table>

<tsa:hidden name="reviewed" value=""/>
<P></P>
<table border="0" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber8">
  <tr>
    <td width="100%">
    <p align="center">
    <button onclick="processInvoice()">Save</BUTTON>
    <!-- <input type="submit" value="Cancel" size="90"> -->
    <button onclick="cancelInvoice()">Cancel</BUTTON>
    </P>
    </td>
  </tr>
</table>

</body>
</FORM>

</html>

