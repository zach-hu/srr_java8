<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>

<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

  PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
  String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
  String tableType = "AC";
  if (oid.equalsIgnoreCase("qri06p"))
  {
		String location = "ACCOUNT_" + poHeader.getUdf1Code();
		tableType = propertiesManager.getProperty("BROWSE", location, "AC");
  }
  String	s_po_number = poHeader.getPoNumber();
  String	s_po_status = poHeader.getStatus();
  String	s_po_type = poHeader.getPoType();
  BigDecimal	bd_release_number = poHeader.getReleaseNumber();
  BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
  String	currencyCode = poHeader.getCurrencyCode() ;
  BigDecimal	bd_zero = new BigDecimal(0);
  UserProfile buyer = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode());
  String emailAction = (String)request.getAttribute("emailAction");
  if(emailAction == null){		emailAction = "NONE";		}
  String fromEmail = (String)request.getAttribute("fromEmail");
  if(fromEmail == null){		fromEmail = "N";		}
  String fromApproval = (String)request.getAttribute("fromApproval");
  if(fromApproval == null){		fromApproval = "N";		}
  String override = (String)request.getAttribute("override");
  if (override == null) {	  	override = "N";		  }

  //DO NOT change to use the isEmpty methods. This fields should be null if they are not set
  String alreadyApproved = (String)request.getAttribute("alreadyApproved");
  String invalidApprover = (String)request.getAttribute("invalidApprover");

  String alreadyApprovedMsg = "";
  if(override.equalsIgnoreCase("Y") || (alreadyApproved == null && invalidApprover == null))
  {%>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="as_return" value="TRUE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="poaction" value="APPROVE"/>
<input type=hidden name="override" value="<%=override%>">
<input type=hidden name="approveForAll" value="N">


<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td valign="top" width="135px" height="30px">
    <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
    <tr>
      <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class="hdr12" valign="middle">
        <div style="margin-left:10px; margin-right:10px" class="hdr12">Order Information</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign="bottom" align="right" height="30px">
    <%@ include file="/orders/po_display_number.jsp" %>
  </td>
</tr>
</table>

<br>

<%@ include file="/orders/po_approval_buttons.jsp" %>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td align="center" width="680px">
    <table id="routingTable" border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
        <tr>
          <td width="100%" align="center" valign="top" class="browseHdrDk">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr>
              <td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver")%></td>
              <td <%=HtmlWriter.isVisible(oid, "app-title")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-title", "Title")%></td>
              <!--td width=15% class="browseHdrDk" nowrap>&nbsp;Approval Type</td-->
              <td <%=HtmlWriter.isVisible(oid, "routingAssigned")%> width="15%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAssigned", "Date Assigned")%></td>
              <td <%=HtmlWriter.isVisible(oid, "routingApproved")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproved", "Date Approved")%></td>
              <td <%=HtmlWriter.isVisible(oid, "routing-callForward")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=100% align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class=summary>
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding=2 width="100%" class=summary>
<%
    boolean hasPreviousNotes = false;
    List routingList = (List) request.getAttribute("approvalLogList");
    if (routingList != null)
    {
      for (int i = 0; i < routingList.size(); i++)
      {
        ApprovalLog appLog = (ApprovalLog) routingList.get(i);
        ApprovalLogPK appLogPK = appLog.getComp_id();
        String	s_approver_code = appLogPK.getUserId();
        UserProfile approver = UserManager.getInstance().getUser(oid, s_approver_code);
        UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());

        String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
        if (appLog.getApproved().equals("A"))
        {
          s_approved_date = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currentApprover", "Current Approver");
        }

        if (!HiltonUtility.isEmpty(appLog.getApproverNotes()))
        {
          hasPreviousNotes = true;
        }
        String	altApproverMsg = "";
        if(appLog.getApproved().equals("D")){
        	String	s_approver_deferred = appLog.getDeferId();
			if(!HiltonUtility.isEmpty(s_approver_deferred))
			{
				UserProfile approverDeferred = UserManager.getInstance().getUser(oid, s_approver_deferred);
				altApproverMsg = approver.getDisplayName() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnDefer", "deferred approval to") + "  " + approverDeferred.getDisplayName();
			}
		}
%>
            <tr>
            <%if (!HiltonUtility.isEmpty(altApproverMsg))	{ %>
              <td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" class=summary><%=altApproverMsg%></td>
            <%} else { %>
              <td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" nowrap class=summary><%=approver.getDisplayName()%></td>
            <%} %>
              <td <%=HtmlWriter.isVisible(oid, "app-title")%> width="20%" nowrap class=summary><%=approver.getTitle()%></td>
              <!--td width="15%" nowrap class=summary><%=appLog.getRuleType()%></td-->
              <td <%=HtmlWriter.isVisible(oid, "routingAssigned")%> width="15%" nowrap class=summary><%=HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat)%></td>
              <td <%=HtmlWriter.isVisible(oid, "routingApproved")%> width="20%" nowrap class=summary><%=s_approved_date%></td>
              <td <%=HtmlWriter.isVisible(oid, "routing-callForward")%> width="20%" nowrap class=summary><%=callForward.getDisplayName()%></td>
            </tr>
<%
      }  //end for loop
      if (hasPreviousNotes)
      {
  %>
            <tr><td><br></td></tr>
            <tr><td colspan="6"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-previousnotes", "Previous Approver Notes")%></b></td></tr>
<%
        for (int i = 0; i < routingList.size(); i++)
        {
          ApprovalLog appLog = (ApprovalLog) routingList.get(i);
          ApprovalLogPK appLogPK = appLog.getComp_id();
          String	s_approver_code = appLogPK.getUserId();
          UserProfile approver = UserManager.getInstance().getUser(oid, s_approver_code);
          String	s_approver_name = approver.getFirstName() + " " + approver.getLastName();
          if (!HiltonUtility.isEmpty(appLog.getApproverNotes()))
          {
%>

            <tr><td colspan="6"><%=s_approver_name%> wrote:</td></tr>
            <tr><td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=appLog.getApproverNotes()%><br><Br></td></tr>
<%				}
        }  //end for loop
      }  //end if hasPreviousNotes
    }
%>
            <tr><td colspan="6"><br></td></tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
<tr>
  <td align="center" width="680px">
    <table border="0" cellpadding="0" cellspacing="2" align="center" width=665px  class="summary">
    <tr <%=HtmlWriter.isVisible(oid, "approvalNotes")%>>
      <td colspan=2><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalNotes", "Notes")%>:</b></td>
    </tr>
    <tr>
      <td <%=HtmlWriter.isVisible(oid, "approvalNotes")%>><TEXTAREA NAME="ApprovalLog_approverNotes" WRAP="VIRTUAL" ROWS="8" COLS="67"></TEXTAREA></td>
      <td valign="top">
        <table border="0" cellpadding="0" cellspacing=2 align="center" class=summary valign="top">
<%	if (propertiesManager.isModuleActive("SIGNATURE") && !oid.equalsIgnoreCase("DTN07P")) {%>
        <tr <%=HtmlWriter.isVisible(oid, "app-signature")%>>
          <td valign="middle" nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-signature", "Signature")%>:&nbsp;</b>
          <input type="checkbox" name="c_app_signed" value="N"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "app-signaturePassword")%>>
          <td valign="middle" nowrap><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-signaturePassword", "Approver")%>:&nbsp;</b>
            &nbsp;<input type="password" name="signature_password" autocomplete="off" value="" onchange="upperCase(this);"></td>
        </tr>
        <tr>
          <td width="100%"><hr color="#9999CC"></td>
        </tr>
<%	}%>
          <tr <%=HtmlWriter.isVisible(oid, "reroute")%>>
            <td valign="middle" nowrap><b><A HREF="javascript: browseLookup('routTo', 'approver'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reroute", "Reroute To")%>:</b></a>&nbsp;
            <input type="text" name="routTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tr>
          <tr <%=HtmlWriter.isVisible(oid, "reroute")%>>
            <td valign="bottom" nowrap>
              <input type="checkbox" name="c_checkbox" value="Y" onclick="setReturn();" onfocus="checkAction('reroute');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnmereroute", "Return to me after reroute")%>.
              <tsa:hidden name="returnTo" value=""/></td>
            </td>
          </tr>
          <tr <%=HtmlWriter.isVisible(oid, "defer")%>>
            <td valign="middle" nowrap><b><A HREF="javascript: browseLookup('deferTo', 'approver'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defer", "Defer To")%>:</b></a>&nbsp;
            <input type="text" name="deferTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td colspan="2"><br><br></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="665px">
    <tr>
      <td width=50% align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
        <tr>
          <td>
            <table border=1 cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tr <%=HtmlWriter.isVisible(oid, "po-buyerName")%>>
              <td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-buyerName", "Buyer")%>:</b>&nbsp;</td>
              <td nowrap width="100%"><%=buyer.getDisplayName()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-department")%>>
              <td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-department", "Department")%>:&nbsp;</b></td>
              <td nowrap><%=poHeader.getDepartmentCode()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "app-telephoneNumber")%>>
              <td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-telephoneNumber", "Telephone Number")%>:</b>&nbsp;</td>
              <td nowrap><%=buyer.getPhoneNumber()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "poDate")%>>
              <td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderDate", "Order Date")%>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-currency")%>>
              <td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-currency", "Currency")%>:</b>&nbsp;</td>
              <td nowrap><%=poHeader.getCurrencyCode()%></td>
            </tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getInternalComments())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-purpose")%>>
              <td nowrap align="right" valign="top"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-purpose", "Purpose")%>:</b>&nbsp;</td>
              <td width="100%"><%=poHeader.getInternalComments()%></td>
            </tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td width=50% align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
        <tr>
          <td>
            <table border=1 cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address shipTo = (Address) poHeader.getShipToAddress();
    if (shipTo == null)
    {
      shipTo = new Address();
    }
%>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getShipToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine1()%></td></tr>
<%		if (!HiltonUtility.isEmpty(shipTo.getAddressLine2()))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine2()%></td></tr>
<%		}
      if (!HiltonUtility.isEmpty(shipTo.getAddressLine3()))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine3()%></td></tr>
<%		}
      if (!HiltonUtility.isEmpty(shipTo.getAddressLine4()))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine4()%></td></tr>
<%		} %>
            <tr><td class="browseRow" height="12px" nowrap><%=shipTo.getCity()%>&nbsp;<%=shipTo.getState()%>&nbsp;<%=shipTo.getPostalCode()%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-country")%>><td class="browseRow" height="12px" nowrap><%=shipTo.getCountry()%></td></tr>
<%		if (!HiltonUtility.isEmpty(poHeader.getShipToContact())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-attention", "Attn")%>:&nbsp;<%=poHeader.getShipToContact()%></td></tr>
<%		}
      if (poHeader.getPoDate() != null) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-requiredDate")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-requiredDate", "Required By")%>:&nbsp;<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%></td></tr>
<%		} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="665px">
    <tr>
      <td width="50%" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
        <tr>
          <td>
            <table border=1 cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address vendor = (Address) poHeader.getVendorAddress();
    if (vendor == null)
    {
      vendor = new Address();
    }
%>
        <tr>
          <td class="browseRow" id="supplierRows">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-supplier")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getVendorId()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=vendor.getAddressLine1()%></td></tr>
<%	if (!HiltonUtility.isEmpty(vendor.getAddressLine2()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=vendor.getAddressLine2()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(vendor.getAddressLine3()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=vendor.getAddressLine3()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(vendor.getAddressLine4()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=vendor.getAddressLine4()%></td></tr>
<%	}%>
            <tr><td class="browseRow" height="12px" nowrap><%=vendor.getCity()%>&nbsp;<%=vendor.getState()%>&nbsp;<%=vendor.getPostalCode()%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-country")%>><td class="browseRow" height="12px" nowrap><%=vendor.getCountry()%></td></tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getVendContactCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-attention", "Attn")%>:&nbsp;<%=poHeader.getVendContactCode()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td width="50%" align="center" valign="top">
        <table id="billingTable" border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
        <tr>
          <td>
            <table id="billingTable" border="1" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billing_information", "Billing Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address billTo = (Address) poHeader.getBillToAddress();
    if (billTo == null)
    {
      billTo = new Address();
    }
%>
        <tr>
          <td class="browseRow" id="billingRows">
            <table border="0" cellspacing="0" cellpadding="2" width=210px class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-billToCode")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getBillToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine1()%></td></tr>
<%	if (!HiltonUtility.isEmpty(billTo.getAddressLine2()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine2()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine3()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine3()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine4()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine4()%></td></tr>
<%	} %>
            <tr><td class="browseRow" height="12px" nowrap><%=billTo.getCity()%>&nbsp;<%=billTo.getState()%>&nbsp;<%=billTo.getPostalCode()%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-country")%>><td class="browseRow" height="12px" nowrap><%=billTo.getCountry()%></td></tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getBillToContact())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-attention", "Attn")%>:&nbsp;<%=poHeader.getBillToContact()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

<div id="commentBefore" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="665px">
    <tr>
      <td width="50%" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comments", "Comment")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
<%
      int iBefore = 0;
      List cmtList = (List) poHeader.getDocCommentList();
      if (cmtList != null && cmtList.size() > 0)
      {
        for(int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("A"))
          {
            continue;
          }
          //if (s_cmt_print.equals("N"))
          //{
          //  continue;
          //}
          iBefore++;
%>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100% class=browseRow>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
			</table>
<%			}
    } %>
            
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
</div>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="665px">
    <tr>
      <td align="center" valign="top">
        <table border="1"  cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
        <tr>
          <td class="browseHdrDk">
            <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
            <tr height="18px">
              <td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=7% class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-lineNumber", "Line #")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="25%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-itemNumber", "Item/Part #")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=13% class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-commodity", "Commodity")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% class="browseHdrDk" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-quantity", "Quantity")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-uom")%> width=7% class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-uom", "UOM")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=13% class="browseHdrDk" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-hdg-extendedCost", "Extended Cost")%></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width="100%" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
          <td>
            <table id="itemRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%	int iRow = 0;
    List lineList = (List) poHeader.getPoLineList();
    for (int i = 0; i < lineList.size(); i++)
    {
      PoLine poLine = (PoLine) lineList.get(i);

      BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
      BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid);
      BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(poLine.getUmFactor(), oid);
      BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
      AltText altText = poLine.getAltText();
%>
<% 			List commentList = (List) poLine.getDocCommentList();
			if (commentList != null && commentList.size() > 0)
			{ %>
						<tr>
							<td valign=top width=55% colspan=6>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
			
<%				for (int ix = 0; ix < commentList.size(); ix++)
				{
					DocComment docComment = (DocComment) commentList.get(ix);
					DocText docText = docComment.getDocText();

					String s_cmt_title = docComment.getCommentTitle();
					String s_cmt_print = docComment.getCommentPrint();
					String s_cmt_bold = docComment.getCommentBold();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();

					if (s_cmt_place.equals("A"))
					{
						continue;
					}	%>
						<tr>
							<td class=browseRow nowrap width=10%>&nbsp;</td>
							<td class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}	%>
							</table>
							</td>
						</tr>
<%			}	%>
            <tr>
              <td <%=HtmlWriter.isVisible(oid, "po-lineNumber")%> width=7% class="browseRow" nowrap align="right"><%=i+1%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="25%" class="browseRow" nowrap>
                <%=i + 1%><%=poLine.getItemNumber()%>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width=13% class="browseRow" nowrap><%=poLine.getCommodity()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width=10% class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-uom")%> width=7% class="browseRow" nowrap><%=poLine.getUmCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width=13% class="browseRow" nowrap align="right"><%=bd_extended_cost%></td>

            </tr>
            <tr>
              <td class="browseRow" nowrap>&nbsp;</td>
              <td colspan=7 class="browseRow"><%=poLine.getDescription()%></td>
            </tr>

			<%	if (altText != null && DictionaryManager.isVisible(oid, "po-description-alttext")) {
				DocText docText = altText.getDocText();
				if (docText != null) {%>
					<tr id=itemRows>
						<td class=browseRow nowrap>&nbsp;</td>
						<td colspan=9 class=browseRow><%=docText.getStdText()%></td>
					</tr>
				<% }
            }
            if (poLine != null) {
		     	List inspList = poLine.getInspectionList();
				if (inspList != null && !inspList.isEmpty()) {
				%>
				<tr>
						<tsa:td colspan="7" height="18px">
							<table cellpadding="0" cellspacing="0" border="0"  width="100%">
							<tsa:tr>
					          <tsa:td align="center"><a href="javascript: inspection('<%=iRow%>')" title="Inspection"><img id='inspectionImg<%=iRow%>' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Inspections"></a>
									<a href="javascript: inspection('<%=iRow%>')" title="Inspection">&nbsp;Receipt Inspection Details</a></tsa:td>
					        </tsa:tr>
							<td>
							<table cellspacing="0">
							<div id="inspectionDiv" value="<%=iRow%>" style="display: none">
							<ol>
						<%
							for (int ix = 0; ix < inspList.size(); ix++) {
							InspectionHeader insp = (InspectionHeader) inspList.get(ix);
							InspectionStd inspStd = insp.getInspectionStd();
							if(inspStd == null){inspStd = new InspectionStd();}
							%>
							    <% String inspType = insp.getInspectType() ;
							    		String inspDesc = "Receipt Inspection" ;
							    		if (inspType == null) inspType = "RI" ;
							    		if (inspType.equals("FI"))   {
							    			inspDesc = "Field Inspection" ;
							    		} else if (inspType.equals("GI"))   {
							    			inspDesc = "General Inspection" ;
							    		} else if (inspType.equals("CG"))   {
							    			inspDesc = "CGD Inspection" ;
							    		}
							    %>
								<li>
									<b><%=HiltonUtility.ckNull(inspDesc) %></b>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>&nbsp;&nbsp;:&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspStd.getDescription())%>
									<br>
									&nbsp;&nbsp;&nbsp;CGD No : <%=HiltonUtility.ckNull(insp.getCgdNo())%>&nbsp;&nbsp;CGD Rev : <%=HiltonUtility.ckNull(insp.getCgdRev())%>
								</li>
									<ul>
								<%	if (insp.getInspectionLineList() != null)
									{
										List inspLineLi = (List) insp.getInspectionLineList();
											if (inspLineLi != null) {
												for (int j = 0; j < inspLineLi.size(); j++)
												{
														InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
									%>
														<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
														<br></br>
														<br>
									<%
												}
											}
										}
									if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) { %>
								<%} else {%>
												<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
												<br></br>
												<br>
								<%}%>
									</ul>
						<%}%>
							</ol>
							</div>
							<br></br>
							</table>
							</td>
							</table>
					</tsa:td>
				</tr>
				<% 	}
			}
            
            iRow++;
            
			List laccountList = (List) poLine.getAccountList();
			if ( oid.equalsIgnoreCase("dtn07p") && laccountList.size() == 0 )
			{
				laccountList = (List) poHeader.getAccountList();
			}
			if (laccountList != null)
			{
				BigDecimal bd_total_perc = new BigDecimal(0.00);
				BigDecimal bd_total_amt = new BigDecimal(0.00);

				for (int ix = 0; ix < laccountList.size(); ix++)
				{
					Account laccount = (Account) laccountList.get(ix);
					BigDecimal bd_alloc_perc = laccount.getAllocPercent();
					BigDecimal bd_alloc_amt = laccount.getAllocAmount();
					if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
					if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}
					bd_total_perc = bd_total_perc.add(bd_alloc_perc);
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
					String	s_account = "";
					String s_fld4 = "";
					String s_fld5 = "";
					String	s_accArray[] = new String[15];

					s_accArray[0] = laccount.getFld1();
					s_accArray[1] = laccount.getFld2();
					s_accArray[2] = laccount.getFld3();
					s_accArray[3] = laccount.getFld4();
					s_accArray[4] = laccount.getFld5();
					s_accArray[5] = laccount.getFld6();
					s_accArray[6] = laccount.getFld7();
					s_accArray[7] = laccount.getFld8();
					s_accArray[8] = laccount.getFld9();
					s_accArray[9] = laccount.getFld10();
					s_accArray[10] = laccount.getFld11();
					s_accArray[11] = laccount.getFld12();
					s_accArray[12] = laccount.getFld13();
					s_accArray[13] = laccount.getFld14();
					s_accArray[14] = laccount.getFld15();

					for (int j = 0; j <15; j++)
					{
						if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + s_accArray[j];
							}
							else
							{
								s_account = s_accArray[j];
							}
						}
					}
					if ( !HiltonUtility.isEmpty(s_accArray[3])){	s_fld4 = s_accArray[3];	}
					if ( !HiltonUtility.isEmpty(s_accArray[4])){	s_fld5 = s_accArray[4];	}%>
					<tr>
						<td class=browseRow nowrap>&nbsp;</td>
<%
  BigDecimal bd_alloc_amt_by_account = bd_extended_cost.multiply(bd_alloc_perc).multiply(new BigDecimal(0.01));
  if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
%>
						<td colspan=7 class=browseRow><b>Account:</b>&nbsp;
						<A HREF="javascript: showBudget('<%=s_account%>','<%=poHeader.getFiscalYear()%>','<%=poLine.getCommodity()%>'); void(0);"><%=s_account%></A>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getFormattedCurrency(bd_alloc_amt_by_account, currencyCode, oid)%></td>
<%} else  {%>
						<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getFormattedCurrency(bd_alloc_amt_by_account, currencyCode, oid)%></td>
<% } %>
					</tr>

				<%			if (oid.equalsIgnoreCase("qri06p"))
					{	%>
						<tr>
							<td colspan="8">
								<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<%							if ( !HiltonUtility.isEmpty(s_fld4))
									{	%>
										<tr>
											<td width="50px">&nbsp;</td>
											<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "UDF4")%>:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
										</tr>
				<%							}
									if ( !HiltonUtility.isEmpty(s_fld5))
									{	%>
										<tr>
											<td width="50px">&nbsp;</td>
											<td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "UDF5")%>:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
										</tr>
				<%							}	%>
								</table>
							</td>
						</tr>
				<%			}
				}
			}

			List attachmentList = (List) poLine.getDocAttachmentList();
            boolean flagAttachment = false;
            if (attachmentList != null && attachmentList.size() > 0)
			{ %>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan="4">
						<table>
				<%	for (int ix = 0; ix < attachmentList.size(); ix++)
					{
						DocAttachment docAttachment = (DocAttachment) attachmentList.get(ix);
						String	filename = docAttachment.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow align="right">
							<%if(!flagAttachment){%>
								<b><tsa:label labelName="po-catalogItemAttachment" defaultString="Attachments" />:&nbsp;</b>
								<%flagAttachment=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td nowrap>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
							</td>
						</tr>
<%
					} %>
						</table>
					</td>
				</tr>
<%	if (commentList != null && commentList.size() > 0)
	{ %>
			<tr>
				<td valign=top width=55% colspan=6>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>

<%				for (int ix = 0; ix < commentList.size(); ix++)
	{
		DocComment docComment = (DocComment) commentList.get(ix);
		DocText docText = docComment.getDocText();

		String s_cmt_title = docComment.getCommentTitle();
		String s_cmt_print = docComment.getCommentPrint();
		String s_cmt_bold = docComment.getCommentBold();
		String s_cmt_place = docComment.getCommentPlace();
		String s_cmt_text = docText.getStdText();

		if (s_cmt_place.equals("B"))
		{
			continue;
		}	%>
			<tr>
				<td class=browseRow nowrap width=10%>&nbsp;</td>
				<td class=browseRow>
					<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
					<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
				</td>
			</tr>
<%				}	%>
				</table>
				</td>
			</tr>
<%			}
		} %>
		<tr>
			<td colspan=6><hr size=0></td>
		</tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>

        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
<%

    int	acci = 0;
    List accList = (List) poHeader.getAccountList();
    if (accList != null && accList.size() > 0)
    {
%>
          <td valign="top">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr>
              <td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "account", "Account")%>:&nbsp;</b></td>
<%
      for (int i = 0; i < accList.size(); i++)
      {
        Account account = (Account) accList.get(i);

        String	s_account = "";
        String	s_accArray[] = new String[15];

        s_accArray[0] = account.getFld1();
        s_accArray[1] = account.getFld2();
        s_accArray[2] = account.getFld3();
        s_accArray[3] = account.getFld4();
        s_accArray[4] = account.getFld5();
        s_accArray[5] = account.getFld6();
        s_accArray[6] = account.getFld7();
        s_accArray[7] = account.getFld8();
        s_accArray[8] = account.getFld9();
        s_accArray[9] = account.getFld10();
        s_accArray[10] = account.getFld11();
        s_accArray[11] = account.getFld12();
        s_accArray[12] = account.getFld13();
        s_accArray[13] = account.getFld14();
        s_accArray[14] = account.getFld15();

        for (int j = 0; j <15; j++)
        {
          if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
          {
            if (s_account.length() > 0)
            {
              s_account = s_account + s_account_separator + s_accArray[j];
            }
            else
            {
              s_account = s_accArray[j];
            }
          }
        }
        acci++;
%>
              <td nowrap><%=s_account%></td>
              <td width="15px">&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
<%		} %>
            </tr>
            </table>
          </td>
<%	} %>
          <td width="100%">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-subtotal")%>>
              <td width=72% class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-subtotal", "Subtotal")%>:</td>
              <td width=13% class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%></td>
              <td width="15%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-discountAmount")%>>
        <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-discountAmount", "Discount")%>:</td>
        <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%></td>
        <td class="browseRow" nowrap align="right">&nbsp;</td>
      </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-taxAmount")%>>
              <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxAmount", "Tax")%>:</td>
              <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%></td>
              <td class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%>>
              <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingCharges", "Shipping")%>:</td>
              <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%></td>
              <td class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%>>
        <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingTaxAmount", "Shipping Tax")%>:</td>
        <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%></td>
        <td class="browseRow" nowrap align="right">&nbsp;</td>
      </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-otherCharges")%>>
              <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherCharges", "Other")%>:</td>
              <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%></td>
              <td class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%>>
        <td class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherTaxAmount", "Other Tax")%>:</td>
        <td class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%></td>
        <td class="browseRow" nowrap align="right">&nbsp;</td>
      </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-Total")%>>
              <td class="browseRow" nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-total", "Total")%>:</b></td>
              <td class="browseRow" nowrap align="right">$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></td>
              <td class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>

      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<div id="commentAfter" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="665px">
    <tr>
      <td align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
            <tr>
              <td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comment", "Comment")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
<%
      int iAfter = 0;
      if (cmtList != null)
      {
        for (int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("B"))
          {
            continue;
          }
          //if (s_cmt_print.equals("N"))
          //{
          //  continue;
          //}
          iAfter++;
%>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100% class=browseRow>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<%			}
      }%>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
</div>

<br>
<br>

<div id="attachments" style="display:none;">
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr>
					<td class=browseHdrDk height=18px nowrap>&nbsp;Attachment</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
<%
		List attachmentList = (List) poHeader.getDocAttachmentList();
		int ai = 0;
		if (attachmentList != null) {
			for (int i = 0; i < attachmentList.size(); i++) {
				DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
				if (docAttachment == null) {
					continue;
				}
				String	filename = docAttachment.getDocFilename();
				String	ext = filename.substring(filename.lastIndexOf(".") + 1);
				ai++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px align=center valign=middle>
<%			if (ext.equalsIgnoreCase("DOC")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%			} else if (ext.equalsIgnoreCase("PDF")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%			} else if (ext.equalsIgnoreCase("XLS")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%			} else if (ext.equalsIgnoreCase("MPP")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%			} else if (ext.equalsIgnoreCase("PPT")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%			} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%			} else {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%			}%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>
  <% 		}
      }
      if (ai == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no attachments associated with this solicitation.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<br>
<br>

<%@ include file="/orders/po_approval_buttons.jsp" %>

<br>

<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;

<%	if (HiltonUtility.ckNull(override).equals("Y") && user.isAnOverrider()) {%>
		displayArea('adminApproverButtons');
		hideArea('approverButtons');
<%	}
	if (iBefore > 0) { %>
      displayArea('commentBefore');
<%	}
    if (iAfter > 0) { %>
      displayArea('commentAfter');
<%	}
	if (ai > 0) { %>
	displayArea('attachments');
<%	} %>

  function setFirstFocus()
  {
    // please do not take this out!  its here because otherwise when the page loads it will focus on the first text box which is at the BOTTOM of the page
    // we do NOT want to focus there!!!
  }

  function checkHiddenMenu()
  {
  	hideArea("navTable");
  	displayArea("menuBarSpacer");
  }

  function openDocument(row) {
		var filename = "";
		if (document.all("docFilename").length > 1) {
			filename = frm.docFilename[row].value;
		}
		else {
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

  function setReturn()
  {
    var rtn = "N";

    if (frm.c_checkbox.checked)
    {
      rtn = "Y";
    }

    frm.returnTo.value = rtn;
  }

  function checkAction(s) {
    //frm.rq_action.value = s;

    if ((s == 'approve') || (s == 'reject')) {
      frm.routTo.value = '';
      frm.c_checkbox.checked = false;
      frm.returnTo.value = "N";
    }
    else if (s == 'reroute') {
      //frm.rq_action[2].checked = true;
    }
  }

  function rejectMe()
  {
    frm.poaction.value = "REJECT";
    frm.deferTo.value="";
    frm.routTo.value="";
    if (frm.ApprovalLog_approverNotes.value.length <= 0)
    {
      alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nobuyernotes", "Please first enter notes to the buyer explaining why you are rejecting this order")%>.");
    }
    else
    {
      doSubmit('/orders/po_forward_options.jsp', 'PoReject');
    }
  }

  function deferMe()
  {
	frm.routTo.value="";
    if (frm.deferTo.value.length <= 0)
    {
      alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nopoapproverdefer", "You must first select an approver to defer this Order")%>!");
      browseLookup('deferTo', 'approver');
    }
    else
    {
		if (isEmpty(frm.ApprovalLog_approverNotes.value)) {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nodeferponotes", "Please first enter notes explaining why you are deferring this order")%>.");
			return false;
		}
      frm.poaction.value = "DEFER";
      doSubmit('/orders/po_forward_options.jsp', 'PoDefer');
    }
  }  
  
  function rerouteMe()
  {
	frm.deferTo.value="";
    if (frm.routTo.value.length <= 0)
    {
      alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noapproverreoute", "You must first select an approver to reroute this Order")%>!");
      browseLookup('routTo', 'approver');
    }
    else
    {
      frm.poaction.value = "REROUTE";
      doSubmit('/orders/po_forward_options.jsp', 'PoReroute');
    }
  }

	function showBudget(accs, fy, commodity) {
		var burl = "/budget/budget_view.jsp" ;
		popupParameters = "accountString=" + accs + ";fiscalYear=" + fy + ";currencyCode=<%=currencyCode%>;";
		popupParameters = popupParameters + "commodity=" + commodity + ";";
		doSubmitToPopup(burl, "BudgetCenterRetrieveView", "WIDTH=500", "HEIGHT=300") ;
	}

	function doSubmitApprove(overrideAll)
	{
  	  frm.routTo.value="";
  	  frm.deferTo.value="";
  	  frm.returnTo.value="N";
  	  if (frm.override.value == "Y" && isEmpty(frm.ApprovalLog_approverNotes.value)) {
 		alert("You must enter approval notes to approve on behalf of another approver.");
  		return false;
  	  }
  	  frm.approveForAll.value = overrideAll;
      hideAreaWithBlock('approve_link');
<%	  if ("Y".equals(propertiesManager.getProperty("PO OPTIONS", "COSTPOINTORDERS", "N"))) { %>
		 doSubmit('orders/po_costpoint_award.jsp', 'PoRetrieve');
<%	  } else { 	%>
	     doSubmit('/orders/po_forward_options.jsp', 'PoApprove');
<%    }  %>
	}
// End Hide script -->
</SCRIPT>

  <%}
  else
  {/*
  *Already approved Order!
  */
    alreadyApprovedMsg = (String)request.getAttribute("Message");%>
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
      <tr>
        <td align="center" width="680px">
          <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseRow">
            <tr>
              <td colspan=3><br><br></td>
            </tr>
            <tr>
              <td align="center">You have already processed <%=poHeader.getDisplayPoNumber()%>.</td>
            </tr>
            <%if(fromApproval.equalsIgnoreCase("Y"))
			{%>
				<tr>
					<td>
						<%@ include file="/orders/po_waiting_approval_others.jsp" %>
					</td>
				</tr>
			<%}%>
          </table>
        </td>
      </tr>
    </table>

  <%}%>
  <tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>" />
  <tsa:hidden name="PoLine_icPoHeader" value="<%= poHeader.getIcPoHeader()%>" />
  <tsa:hidden name="formtype" value="PO"/>
  <tsa:hidden name="fromApproval" value="<%=fromApproval%>"/>
  <tsa:hidden name="emailAction" value="<%=emailAction%>"/>
  <tsa:hidden name="fromEmail" value="<%=fromEmail%>"/>
      <%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
  	function retrieveOrderApproval(ic)
  	{
		frm.PoHeader_icPoHeader.value = ic;
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/orders/po_approval.jsp', 'PoApprovalRetrieve');
  	}

  	function inspection(iRow)
  	{
  		var myImg = document.getElementById("InspectionImg" + iRow);
  		if ($('div[value*="'+ iRow + '"]').is(":hidden")) {
  			$('div[value*="' + iRow + '"]').slideDown("slow");
  			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
  			myImg.alt = "Hide Inspections";
  		} else {
  			$('div[value*="' + iRow + '"]').hide();
  			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
  			myImg.alt = "View Inspections";
  		}
  	}
  // End Hide script -->
</SCRIPT>
