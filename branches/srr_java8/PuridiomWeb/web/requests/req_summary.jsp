<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
  RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
  BigDecimal b_req_ic_header = requisitionHeader.getIcReqHeader();
  String	s_req_status =requisitionHeader.getStatus();
  String	s_req_type = requisitionHeader.getRequisitionType();
  String	s_req_subtype = requisitionHeader.getRqSubType();
  String 	s_fiscal_year = requisitionHeader.getFiscalYear();
  String	s_req_number = requisitionHeader.getRequisitionNumber();

  if (HiltonUtility.isEmpty(s_req_number)){s_req_number = "N/A";}

  List lineList = (List) requisitionHeader.getRequisitionLineList();
  int	i_line_count = 0;

  if (lineList != null) {
    i_line_count = lineList.size();
  }
%>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/reqOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>

<%
	String cancelAccessOwnReq = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getRequisitionerCode()));
	String cancelAccessAssBuy = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getAssignedBuyer()));
	String	closeAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getBuyer()));
	String	convertAccess = String.valueOf(uid.equalsIgnoreCase(requisitionHeader.getOwner()) || uid.equalsIgnoreCase(requisitionHeader.getBuyer()) || uid.equalsIgnoreCase(requisitionHeader.getRequisitionerCode()));
	String deleteAccess = String.valueOf( uid.equalsIgnoreCase(requisitionHeader.getOwner()) );
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String displayViewType = propertiesManager.getProperty("REQ OPTIONS", "DISPLAYVIEWTYPE", "N");
	String	s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOREQ", "N");
	String	s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOREQ", "Y");
%>

<script language='Javascript1.2' type="text/javascript">
<!--

  viewType = "<%=s_view%>";
  displayViewType = "<%=displayViewType%>";
  reqNumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
  reqType = "<%=s_req_type%>";
  reqStatus = "<%=s_req_status%>";
  reqLineCount = <%=i_line_count%>;
  reqInProgress = "<%=DocumentStatus.REQ_INPROGRESS%>";
  reqRejected = "<%=DocumentStatus.REQ_REJECTED%>";
  reqRecalled = "<%=DocumentStatus.REQ_RECALLED%>";
  reqForwarded = "<%=DocumentStatus.REQ_FORWARDED%>";
  reqApproving = "<%=DocumentStatus.REQ_APPROVING%>";
  reqApproved = "<%=DocumentStatus.REQ_APPROVED%>";
  reqAmmended = "<%=DocumentStatus.REQ_AMMENDED%>";
  reqAwarded = "<%=DocumentStatus.PO_AWARDED%>";
  reqReturning = "<%=DocumentStatus.INV_RETURNING%>";
  reqReturned = "<%=DocumentStatus.INV_RETURNED%>";
  rfqPriced = "<%=DocumentStatus.RFQ_PRICED%>";
  templateStatus = "<%=DocumentStatus.TEMPLATE%>";
  cancelAccessOwnReq = "<%=cancelAccessOwnReq%>";
  cancelAccessAssBuy = "<%=cancelAccessAssBuy%>";
  invInProgress = "<%=DocumentStatus.INV_INPROGRESS%>";
  invBackordered = "<%=DocumentStatus.INV_BACKORDERED%>";
  closeAccess = "<%=closeAccess%>";
  convertAccess = "<%=convertAccess%>";
  deleteAccess = "<%=deleteAccess%>";
  isABuyer = "<%=user.isABuyer()%>";

  Array91= createReqOptionsMenu(Array91[0]);

//-->
</SCRIPT>
<%
  String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
  String	s_view_prices = propertiesManager.getProperty("REQ OPTIONS", "ViewPrices", "Y");
  String	s_assign_by_line = propertiesManager.getProperty("ASSIGNMENT","AssignByLine","N");
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>

<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=requisitionHeader.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=requisitionHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=requisitionHeader.getItemLocation()%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=i_line_count%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="as_itemNumber" value=""/>
<tsa:hidden name="pricingAction" value=""/>
<tsa:hidden name="frompage" value="summary"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12><%=RequisitionType.toString(s_req_type, oid)%> <tsa:label labelName="information" defaultString="Information" /></div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
      <td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
    </tr>
    <tr>
      <td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
      <td width=125px><%=DocumentStatus.toString(requisitionHeader.getStatus(), oid)%></td>
    </tr>
    </table>
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

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0)
    {
      if (i_line_count > 0 && !s_req_number.equals("N/A"))
      {
        if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>
    <td id="forward_link" align=center width=150px nowrap><a href="javascript: validateReq('FORWARD'); void(0);"><tsa:label labelName="forwardSupplyRoom" defaultString="Forward to Supply Room" /></a></td>
<%			} else { %>
    <td id="forward_link" align=center width=150px nowrap><a href="javascript: validateReq('FORWARD'); void(0);"><tsa:label labelName="forwardApproval" defaultString="Forward for Approval" /></a></td>
<%			}
        if (s_req_type.equals("N") && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) { %>
    <td id="forward_link" align=center width=150px nowrap><a href="javascript: validateReq('FORWARD'); void(0);"><tsa:label labelName="forwardPricing" defaultString="Forward for Pricing" /></a></td>
<%			}
        else
        { %>
    <td width=150px>&nbsp;</td>
<%			}
      }
      else
      { %>
    <td width=350px>&nbsp;</td>
<%		}
    }
    else
    { %>
    <td width=350px>&nbsp;</td>
<%	} %>
  <td width=100px align=right nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu98')" onMouseOver="popUp('Menu98',event)"><tsa:label labelName="schedules" defaultString="Schedules" /></a></td>
  <td width=200px align=right nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><tsa:label labelName="moreOptions" defaultString="More Options" /></a></td>
</tr>
</table>

<br>

<%
  String	s_requisitioner_code = requisitionHeader.getRequisitionerCode();
  UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);
  String	s_buyer_code = requisitionHeader.getBuyer();
  UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
  String	s_authorizedby_code = requisitionHeader.getAuthorizationCode();
  UserProfile authorizedBy = UserManager.getInstance().getUser(oid, s_authorizedby_code);
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=680px>
    <tr>
      <td width=50% align=center valign=top>
        <table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
        <tr>
          <td width=100% align=center valign=top>
            <table border=0 cellspacing=0 cellpadding=0 width=100% class=BrowseHdr>
            <tr>
              <td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/requests/req_general_info.jsp', 'RequisitionHeaderRetrieveById'); void(0)"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
              <td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
              <td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('generalDetails', 'Details'); void(0);"><img id='generalDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
            <tr <%=HtmlWriter.isVisible(oid, "requestDate")%>>
              <td nowrap align=right><b><tsa:label labelName="requestDate" defaultString="Request Date" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), oid, userDateFormat)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-fiscalYear")%>>
              <td nowrap align=right><b><tsa:label labelName="req-fiscalYear" defaultString="Fiscal Year" />:&nbsp;</b></td>
              <td nowrap>
                <%=requisitionHeader.getFiscalYear()%>
                <tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=requisitionHeader.getFiscalYear()%>"/>
              </td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-requisitionerName")%>>
              <td nowrap align=right width=40%><b><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" />:</b>&nbsp;</td>
              <td nowrap><%=requisitioner.getDisplayName()%></td>
            </tr>
            </table>
            <div id="generalDetails" style="display:none;">
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-requisitionerPhone")%>>
              <td nowrap align=right valign=top width=40%><b><tsa:label labelName="req-requisitionerPhone" defaultString="Phone Number" />:</b></td>
              <td valign=top><%=requisitioner.getPhoneNumber()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-department")%>>
              <td nowrap align=right width=40%><b><tsa:label labelName="req-department" defaultString="Department" />:&nbsp;</b></td>
              <td nowrap><%=requisitionHeader.getDepartmentCode()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-buyerName")%>>
              <td nowrap align=right><b><tsa:label labelName="req-buyerName" defaultString="Buyer" />:&nbsp;</b></td>
              <td nowrap><%=buyer.getDisplayName()%></td>
            </tr>
<% if (oid.equalsIgnoreCase("bsc04p")) {
		String	s_designatedapprover_code = requisitionHeader.getUdf9Code();
		UserProfile designatedApprover = UserManager.getInstance().getUser(oid, s_designatedapprover_code);
%>
            <tr <%=HtmlWriter.isVisible(oid, "req-RQ09")%>>
              <td nowrap align=right valign=top><b><tsa:label labelName="req-RQ09" defaultString="UDF9" />:</b></td>
              <td valign=top><%=designatedApprover.getDisplayName()%></td>
            </tr>
<%	} else { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-authorizedByName")%>>
              <td nowrap align=right><b><tsa:label labelName="req-authorizedByName" defaultString="Authorized By" />:&nbsp;</b></td>
              <td nowrap><%=authorizedBy.getDisplayName()%></td>
            </tr>
<%	}
	if (s_assign_by_line.equalsIgnoreCase("N") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {
		String s_assigned_buyer = requisitionHeader.getAssignedBuyer();
		UserProfile assignedBuyer = UserManager.getInstance().getUser(oid, s_assigned_buyer);%>
			<tr <%=HtmlWriter.isVisible(oid, "req-assignedBuyer")%>>
              <td nowrap align=right valign=top><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b></td>
              <td valign=top><%=assignedBuyer.getDisplayName()%></td>
            </tr>
<%	}%>
			<tr <%=HtmlWriter.isVisible(oid, "req-currency")%>>
				<td nowrap align=right valign=top><b><tsa:label labelName="req-currency" defaultString="Currency" />:</b></td>
				<td valign=top><%=requisitionHeader.getCurrencyCode()%></td>
			</tr>
            </table>
            </div>
          </td>
        </tr>
        </table>
      </td>
<%
  String	s_ship_address_line_1 = "";
  String	s_ship_address_line_2 = "";
  String	s_ship_address_line_3 = "";
  String	s_ship_address_line_4 = "";
  String	s_ship_city = "";
  String	s_ship_state = "";
  String	s_ship_postal_code = "";
  String	s_ship_country = "";

  Address shipTo = (Address) requisitionHeader.getShipToAddress();
  if (shipTo != null)
  {
    s_ship_address_line_1 = shipTo.getAddressLine1();
    s_ship_address_line_2 = shipTo.getAddressLine2();
    s_ship_address_line_3 = shipTo.getAddressLine3();
    s_ship_address_line_4 = shipTo.getAddressLine4();
    s_ship_city = shipTo.getCity();
    s_ship_state = shipTo.getState();
    s_ship_postal_code = shipTo.getPostalCode();
    s_ship_country = shipTo.getCountry();
  }
%>
      <td width=50% align=center valign=top>
      <table border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
        <tr>
          <td width=100% align=center valign=top>
            <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
            <tr>
              <td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/requests/req_shipping.jsp', 'RequisitionHeaderShipToRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
              <td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
              <%	if (!HiltonUtility.isEmpty(s_ship_address_line_2 + s_ship_address_line_3 + s_ship_address_line_4 + s_ship_city + s_ship_state + s_ship_postal_code + s_ship_country) || HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat).length() > 0 || HiltonUtility.isEmpty(requisitionHeader.getPriorityCode()) || HiltonUtility.isEmpty(requisitionHeader.getShippingCode()) || HiltonUtility.isEmpty(requisitionHeader.getRouting()))
              {%>
              <td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('shippingDetails', 'Details'); void(0);"><img id='shippingDetailsImg' valign='baseline 'src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
              <%	}%>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getShipToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td></tr>
            </table>

            <div id="shippingDetails" style="display:none;">
            <table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (!HiltonUtility.isEmpty(s_ship_address_line_2))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td></tr>
<%		}
      if (!HiltonUtility.isEmpty(s_ship_address_line_3))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td></tr>
<%		}
      if (!HiltonUtility.isEmpty(s_ship_address_line_4))
      { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td></tr>
<%		}%>
            <tr><td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-country")%>><td class=browseRow height=12px nowrap><%=s_ship_country%></td></tr>
            </table>
            </div>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getShipAttn())) { %>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-shp-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-shp-attention" defaultString="Attention" />:&nbsp<%=requisitionHeader.getShipAttn()%></td></tr>
            </table>
<%		} %>
            <div id="shippingDetails" style="display:none;">
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-requiredDate")%>>
              <td class=browseRow height=12px nowrap>
                <tsa:label labelName="req-requiredDate" defaultString="Required By" />:&nbsp<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>
                <tsa:hidden name="RequisitionHeader_requiredBy" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>"/>
              </td>
            </tr>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getPriorityCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-priority")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-priority" defaultString="Priority" />:&nbsp<%=requisitionHeader.getPriorityCode()%></td></tr>
<%		} %>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getShippingCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-shipVia")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-shipVia" defaultString="Ship Via" />:&nbsp<%=requisitionHeader.getShippingCode()%></td></tr>
<%		} %>
<%		if (!HiltonUtility.isEmpty(requisitionHeader.getRouting())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-routing")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-routing" defaultString="Routing" />:&nbsp;<%=requisitionHeader.getRouting()%></td></tr>
<%		} %>
            </table>
            </div>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr><td colspan=2>&nbsp;</td></tr>
<%
  String	s_vend_address_line_1 = "";
  String	s_vend_address_line_2 = "";
  String	s_vend_address_line_3 = "";
  String	s_vend_address_line_4 = "";
  String	s_vend_city = "";
  String	s_vend_state = "";
  String	s_vend_postal_code = "";
  String	s_vend_country = "";

  Address vendor = (Address) requisitionHeader.getVendorAddress();
  if (vendor != null)
  {
    s_vend_address_line_1 = vendor.getAddressLine1();
    s_vend_address_line_2 = vendor.getAddressLine2();
    s_vend_address_line_3 = vendor.getAddressLine3();
    s_vend_address_line_4 = vendor.getAddressLine4();
    s_vend_city = vendor.getCity();
    s_vend_state = vendor.getState();
    s_vend_postal_code = vendor.getPostalCode();
    s_vend_country = vendor.getCountry();
  }
%>
    <tr>
      <td width=50% align=center valign=top>
      <%if (!s_req_type.equals("S"))		/* Supply Request */
      {%>
        <table id=supplierTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
        <tr>
          <td width=100% align=center valign=top>
            <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
            <tr>
              <td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('requests/req_supplier.jsp','RequisitionHeaderVendorRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
              <td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></td>
              <%	if (!HiltonUtility.isEmpty(s_vend_address_line_2 + s_vend_address_line_3 + s_vend_address_line_4 + s_vend_city + s_vend_state + s_vend_postal_code + s_vend_country))
              {%>
              <td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('supplierDetails', 'Details'); void(0);"><img id='supplierDetailsImg' valign='baseline 'src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
              <%	}%>
            </tr>
            </table>
        </tr>
        <tr>
          <td class=browseRow id=supplierRows>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-supplier")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getVendorId()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_1%></td></tr>
            </table>
            <div id="supplierDetails" style="display:none;">
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_vend_address_line_2))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_2%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_3))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_3%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_4))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_vend_address_line_4%></td></tr>
<%	}%>
            <tr><td class=browseRow height=12px nowrap><%=s_vend_city%>&nbsp;<%=s_vend_state%>&nbsp;<%=s_vend_postal_code%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-country")%>><td class=browseRow height=12px nowrap><%=s_vend_country%></td></tr>
            </table>
            </div>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(requisitionHeader.getVendorAttn())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-sup-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-sup-attention" defaultString="Attn" />: <%=requisitionHeader.getVendorAttn()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      <%}%>
      </td>
<%
  String	s_bill_address_line_1 = "";
  String	s_bill_address_line_2 = "";
  String	s_bill_address_line_3 = "";
  String	s_bill_address_line_4 = "";
  String	s_bill_city = "";
  String	s_bill_state = "";
  String	s_bill_postal_code = "";
  String	s_bill_country = "";

  Address billTo = (Address) requisitionHeader.getBillToAddress();
  if (billTo != null)
  {
    s_bill_address_line_1 = billTo.getAddressLine1();
    s_bill_address_line_2 = billTo.getAddressLine2();
    s_bill_address_line_3 = billTo.getAddressLine3();
    s_bill_address_line_4 = billTo.getAddressLine4();
    s_bill_city = billTo.getCity();
    s_bill_state = billTo.getState();
    s_bill_postal_code = billTo.getPostalCode();
    s_bill_country = billTo.getCountry();
  }
%>
      <td width=50% align=center valign=top>
      <%if (!s_req_type.equals("S") && !s_req_type.equals("I"))		/* Supply Request */
      {%>
        <table id=billingTable border=1 cellspacing=0 cellpadding=0 width=325px class=browseHdr>
        <tr>
          <td width=100% align=center valign=top>
            <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
              <td width=4% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/requests/req_billing.jsp', 'RequisitionHeaderBillToRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
              <td width=93% class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billing_information", "Billing Information")%></td>
              <td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('billingDetails', 'Details'); void(0);"><img id='billingDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;</td>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow id=billingRows>
            <table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-billToCode")%>><td class=browseRow height=12px nowrap><b><%=requisitionHeader.getBillToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_1%></td></tr>
            </table>
            <div id="billingDetails" style="display:none;">
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_bill_address_line_2))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_2%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_3))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_3%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_4))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_bill_address_line_4%></td></tr>
<%	} %>
            <tr><td class=browseRow height=12px nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-country")%>><td class=browseRow height=12px nowrap><%=s_bill_country%></td></tr>
            </table>
            </div>
<%	if (!HiltonUtility.isEmpty(requisitionHeader.getBillToContact())) { %>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-bil-attention")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-bil-attention" defaultString="Attn" />:&nbsp<%=requisitionHeader.getBillToContact()%></td></tr>
            </table>
<%	} %>
            <div id="billingDetails" style="display:none;">
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-taxCode")%>><td class=browseRow height=12px nowrap><tsa:label labelName="req-taxCode" defaultString="Tax Code" />:&nbsp<%=requisitionHeader.getTaxCode()%></td></tr>
            </table>
            </div>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
    <%}%>
  </td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
    <tr>
      <td width=100% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td width=3% class=browseHdr height=18px nowrap><a href="javascript: doSubmit('/requests/req_items.jsp', 'RequisitionHeaderUpdate;RequisitionLineRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
          <td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%>  width=13% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" /></td>
<%		if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>
              <td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> width=10% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-inventoryLocation" defaultString="Inventory Location" /></td>
<%		} else {%>
              <td <%=HtmlWriter.isVisible(oid, "req-catalog")%> width=10% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-catalog" defaultString="Catalog" /></td>
<%		}%>
          <td <%=HtmlWriter.isVisible(oid, "req-commodity")%> width=19% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-commodity" defaultString="Commodity" /></td>
          <td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=8% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-quantity" defaultString="Quantity" /></td>
          <td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=8% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-uom" defaultString="UOM" /></td>
          <td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=13% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" /></td>
          <td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-extendedCost" defaultString="Ext Cost" /></td>
          <td <%=HtmlWriter.isVisible(oid, "req-lineStatus")%> width=20% class=browseHdr align=center height=18px nowrap><tsa:label labelName="req-hdg-lineStatus" defaultString="Line Status" /></td>
          <%	if (i_line_count > 1) {%>
          <td width=3% class=browseHdr height=18px nowrap><a href="javascript: toggleDetailsDisplay('itemDetails', 'Items'); void(0);"><img id='itemDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Details"></a>&nbsp;
          <%	}%>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=100% align=center valign=top>
      <tsa:hidden name="RequisitionLine_icReqLine" value=""/>
        <table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td>
<%
    for (int i = 0; i < i_line_count; i++)
    {
      RequisitionLine reqLine = (RequisitionLine) lineList.get(i);
      if (i == 1)
      {
%>
            <div id="itemDetails" style="display:none;">
<%
      }
      BigDecimal b_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
      BigDecimal b_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
      BigDecimal b_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
      BigDecimal b_extended_cost = HiltonUtility.getFormattedDollar(b_quantity.multiply(b_unit_price).multiply(b_um_factor), oid);
%>
            <table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
            <tr>
              <td width=3% class=browseRow nowrap align=center><a href="javascript: viewItem(<%=i%>); void(0);"><%=i+1%></a></td>
              <td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> width=13% class=browseRow nowrap align=center>
                <%=reqLine.getItemNumber()%>
                <tsa:hidden id="<%= \"icReqLine_\" + i%>" name="<%= \"icReqLine_\" + i%>" value="<%=reqLine.getIcReqLine()%>"/>
              </td>
<%		if ( s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I") ) { %>
              <td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> width=10% class=browseRow nowrap align=center><%=reqLine.getItemLocation()%></td>
<%		} else {%>
              <td <%=HtmlWriter.isVisible(oid, "req-catalog")%> width=10% class=browseRow nowrap align=center><%=reqLine.getCatalogId()%></td>
<%		}%>
              <td <%=HtmlWriter.isVisible(oid, "req-commodity")%> width=19% class=browseRow nowrap align=center><%=reqLine.getCommodityCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=8% class=browseRow nowrap align=center><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%></td>
              <td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=8% class=browseRow nowrap align=center><%=reqLine.getUmCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "req-unitCost")%> width=13% class=browseRow nowrap align=center><%=b_unit_price%></td>
              <td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseRow nowrap align=center><%=b_extended_cost%></td>
              <td <%=HtmlWriter.isVisible(oid, "req-lineStatus")%> width=20% class=browseRow nowrap align=center><%=DocumentStatus.toString(reqLine.getStatus(), oid)%></td>
              <td width=3% class=browseRow>&nbsp;</td>
            </tr>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
              <td colspan=8 class=browseRow><%=reqLine.getDescription()%></td>
            </tr>
<%
		List accountList = (List) reqLine.getAccountList();

		if (accountList != null)
		{
			BigDecimal bd_total_perc = new BigDecimal(0.00);
			BigDecimal bd_total_amt = new BigDecimal(0.00);

			for (int ix = 0; ix < accountList.size(); ix++)
			{
				Account account = (Account) accountList.get(ix);

				BigDecimal bd_alloc_perc = account.getAllocPercent();
				BigDecimal bd_alloc_amt = account.getAllocAmount();

				if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
				if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

				bd_total_perc = bd_total_perc.add(bd_alloc_perc);
				bd_total_amt = bd_total_amt.add(bd_alloc_amt);

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
%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></td>
				</tr>
<%			}
		}

		if (!(reqLine.getRequisitionNumber().equalsIgnoreCase(requisitionHeader.getRequisitionNumber())) || (!reqLine.getRequisitionerCode().equals(requisitionHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(reqLine.getRequisitionerCode())))
		{	%>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
<%			if (!reqLine.getRequisitionNumber().equals(requisitionHeader.getRequisitionNumber())) {%>
	             <td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionNumber")%> colspan=4 class=browseRow><b><tsa:label labelName="ln-req-requisitionNumber" defaultString="Requisition #" />:</b>&nbsp;<%=reqLine.getRequisitionNumber()%></td>
<%			}
			if (!reqLine.getRequisitionerCode().equals(requisitionHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(reqLine.getRequisitionerCode())) {%>
	             <td <%=HtmlWriter.isVisible(oid, "ln-req-requisitionerName")%> colspan=4 class=browseRow><b><tsa:label labelName="ln-req-requisitionerName" defaultString="Requisitioner Name" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, reqLine.getRequisitionerCode()).getDisplayName()%></td>
<%			}%>
	           </tr>
<%		}

		if (s_assign_by_line.equalsIgnoreCase("Y") && s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0) {%>
            <tr <%=HtmlWriter.isVisible(oid, "req-assignedBuyer")%>>
              <td class=browseRow nowrap>&nbsp;</td>
              <td colspan=8 class=browseRow><b><tsa:label labelName="req-assignedBuyer" defaultString="Assigned To" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, reqLine.getAssignedBuyer()).getDisplayName()%></td>
            </tr>
<%		}%>
            </table>
<%	}
      if (i_line_count > 1) {%>
            </div>
<%		}%>
          </td>
        </tr>
        </table>
<!--
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) { %>
          <td width=10%>&nbsp;</td>
          <td align=center valign=middle width=30%>
            <table border=0 cellspacing=0 cellpadding=1 class=browseRow>
            <tr>
              <td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> valign=middle align=right nowrap><tsa:label labelName="req-itemNumber" defaultString="Item/Part #" />:</td>
              <td valign=middle align=center><input type=text name="RequisitionLine_itemNumber" value="" size=12></td>
              <td valign=middle>
                <tsa:hidden name="createAction" value="SAVE"/>
                <a href="javascript: addItem();"><IMG SRC="<%=contextPath%>/images/cmd_add_item.gif" BORDER="0" ALT="Add Item"></a>
              </td>
            </tr>
            </table>
          </td>
          <td width=1% id="filterFields">&nbsp;</td>
          <td width=35% valign=middle align=center>
            <table border=0 cellspacing=0 cellpadding=1 class=browseRow>
            <tr>
              <td valign=middle align=right nowrap>Keyword(s):</td>
              <td valign=middle align=center><input type=text name="as_keywords" value="" size=18></td>
              <td valign=middle><a href="javascript: setItemNumber(); itemSearch(); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Item Search"></a></td>
              <td valign=middle>
                <div id="itemtype" style="visibility:visible; display:block;">
                <table border=0 cellpadding=0 cellspacing=0>
                <tr>
                  <td valign=middle align=right><input type=radio name="as_item_type" tabindex=10 value="CAT" CHECKED></td>
                  <td valign=middle nowrap>Catalog</td>
                  <td valign=middle align=right><input type=radio name="as_item_type" tabindex=12 value="INV"></td>
                  <td valign=middle nowrap>Inventory</td>
                </tr>
                </table>
                </div>
              </td>
            </tr>
            </table>
          </td>
<%	} %>
        </tr>
        </table>
-->

        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr><td class=browseRow width=100%><hr size=0></td></tr>
        </table>

        <div id="totals" style="display:none;">
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "req-subtotal")%>>
              <td width=65% class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</td>
              <td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getSubtotal(), oid)%></td>
              <td width=25% class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-discountAmount")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-discountAmount" defaultString="Discount" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getDiscountAmount(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-taxAmount")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-taxAmount" defaultString="Tax" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getTaxAmount(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-shippingCharges")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shipping" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getShippingCharges(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-shippingTaxAmount")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getShippingTaxAmt(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-otherCharges")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getOtherCharges(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "req-otherTaxAmount")%>>
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(requisitionHeader.getOtherTaxAmount(), oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
        </div>

        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr>
              <td width=64% class=browseRow nowrap align=right><b><tsa:label labelName="total" defaultString="Total" />:</b></td>
              <td width=3% nowrap align=right><a href="javascript: doSubmit('/requests/req_totals.jsp', 'RequisitionHeaderRetrieveById'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseRow border=0 alt="Edit"></a></td>
              <td <%=HtmlWriter.isVisible(oid, "req-total")%> width=64% class=browseRow nowrap align=right><b><tsa:label labelName="req-total" defaultString="Total" />:</b></td>
              <td <%=HtmlWriter.isVisible(oid, "req-total")%> width=13% class=browseRow nowrap align=center><%=HiltonUtility.getFormattedDollar(requisitionHeader.getTotal(), oid)%></td>
              <td width=17% class=browseRow nowrap align=right>&nbsp;</td>
              <td width=3% nowrap align=right><a href="javascript: toggleDetailsDisplay('totals', 'Totals'); void(0);"><img id='totalsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Totals"></a></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
    <tr>
      <td>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
        <tr>
          <td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/requests/req_accounts.jsp', 'AccountRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
          <td width=68% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="account" defaultString="Account" /></b></td>
          <td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="percentAlloc" defaultString="Percent Alloc" />.</b></td>
          <td width=15% height=18px class=browseHdr align=right>&nbsp;<b><tsa:label labelName="amountAlloc" defaultString="Amount Alloc" />.</b></td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
			List accList = (List) request.getAttribute("accountAllocAmountList");
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null)
			{
				for (int i = 0; i < accList.size(); i++)
				{
					Object obj[] = (Object[]) accList.get(i);
					BigDecimal bd_alloc_amt = (BigDecimal) obj[0];
					String	s_account = "";
					String udf = "";
					for (int j = 1; j < 16; j ++)
					{
						udf = (String) obj[j];
						if (!HiltonUtility.isEmpty(udf))
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + udf;
							}
							else
							{
								s_account = udf;
							}
						}
					}
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td width=75% class=browseRow><%=s_account%></td>
          <td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_alloc_amt, oid)%></td>
        </tr>
        </table>
<%			}
				if (accList.size() > 0) {	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=75% class=browseRow>&nbsp;</td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow><tsa:label labelName="thereAreNoAccountsRequisition" defaultString="There are no accounts associated with this requisition" />.</td></tr>
				</table>
<%		}	%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<% List cmtList = (List) requisitionHeader.getDocCommentList(); %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
    <tr>
      <td>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
        <tr>
          <td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/requests/req_notes.jsp', 'DocCommentRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
          <td width=72% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="comment" defaultString="Comment" /></b></td>
          <td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="print" defaultString="Print" /></b></td>
          <td width=7% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="bold" defaultString="Bold" /></b></td>
          <td width=10% height=18px class=browseHdr align=center>&nbsp;<b><tsa:label labelName="placement" defaultString="Placement" /></b></td>
          <%	if (cmtList != null && cmtList.size() > 1) {%>
          <td width=2% height=18px class=browseHdr nowrap><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Notes"></a></td>
          <%	}%>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
int size = 0;
  if (cmtList != null)
  {
    size = cmtList.size();
    for(int i = 0; i < size; i++)
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
        s_cmt_place = "After";
      }
      else if (s_cmt_place.equals("B"))
      {
        s_cmt_place = "Before";
      }
%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td width=74% class=browseRow>&nbsp;<%=s_cmt_title%></td>
          <td width=7% class=browseRow align=center valign=top><%=s_cmt_print%></td>
          <td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
          <td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
          <td width=2% class=browseRow align=center valign=top>&nbsp;</td>
        </tr>
        </table>
        <div id="commentDetails" style="display:none;">
        <table>
        <tr>
          <td width=75px>&nbsp;</td>
          <td width=100%><%=s_cmt_text%></td>
        </tr>
        </table>
        </div>
<% 	}
  }
  if (size == 0) {%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr><td class=browseRow><tsa:label labelName="thereAreNoCommentsRequisition" defaultString="There are no comments associated with this requisition" />.</td></tr>
        </table>
<%}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
    <tr>
      <td>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
        <tr>
          <td width=2% height=18px class=browseHdr><a href="javascript: doSubmit('/requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class=browseHdr border=0 alt="Edit"></a></td>
          <td width=78% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
          <td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
          <td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
    List atcList = (List) requisitionHeader.getDocAttachmentList();
    int	ai = 0;
    if (atcList != null)
    {
      for(int i = 0; i < atcList.size(); i++)
      {
        DocAttachment docAttachment = (DocAttachment) atcList.get(i);
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
              <td width=25px>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
                </td>
                <td>
                  <a href="javascript: openDocument(<%=i%>); void(0);"><%=docAttachment.getDocTitle()%></a>
                  <tsa:hidden name="docFilename" value="<%=filename%>"/>
                </td>
              </tr>
              </table>
            </td>
            <td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
            <td width=10% class=browseRow align=center valign=top></td>
          </tr>
          </table>
<%		}
    }
    if (ai == 0) {%>
          <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
          <tr><td class=browseRow><tsa:label labelName="thereAreNoAttachmentsRequisition" defaultString="There are no attachments associated with this requisition" />.</td></tr>
          </table>
<%	}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	}%>

<br><br>

<%	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=50% align=center>
<%		if (s_req_number == null || s_req_number.equals("N/A")) { %>
    <a href="javascript: reqSave(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
<%		} else { %>
    <div id="forward_link" style="visibility: visible;"><a href="javascript: validateReq('FORWARD'); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></div>
<%		} %>
  </td>
  <td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Main Menu"></a></td>
</tr>
</table>
<%	} %>

<table>
  <tr id="hiddenFields"></tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;

  var fiscalyear = "<%=requisitionHeader.getFiscalYear()%>";
  var itemLocation = "<%=requisitionHeader.getItemLocation()%>";
  var autoReqNumber = <%=s_autonumber.equals("Y")%>;
  var showAutoReqNumber = <%=s_showauto.equals("Y")%>;
  var currentpage = "/requests/req_summary.jsp";

<%	if (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) {
      if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) { %>
      hideArea('itemtype');
    //  frm.as_item_type[1].checked = "TRUE";
<%		}
    } %>

  function toggleDetailsDisplay(areaName, type) {
    var myAreas = document.getElementsByName(areaName);
    var myArea;
    var myImg = document.getElementById(areaName + "Img");
    var hideArea = false;

    for (var i = 0; i < myAreas.length; i++) {
      myArea = myAreas(i);


      if (myArea.style.display == "none") {
        myArea.style.display = "block";
      }
      else {
        hideArea = true;
        myArea.style.display = "none";
      }
    }

    if (type == undefined) {
      type = "Details";
    }

    if (hideArea) {
      myImg.src = "<%=contextPath%>/images/arrows_down.gif";
      myImg.alt = "View" + type;
    }
    else {
      myImg.src = "<%=contextPath%>/images/arrows_up.gif";
      myImg.alt = "Hide " + type;
    }
  }

  function viewItem(row) {
    var num = document.getElementById("icReqLine_" + row);
    frm.RequisitionLine_icReqLine.value = num.value;
    doSubmit('/requests/req_item.jsp','RequisitionLineRetrieve');
  }

  function addItem()
  {
    var line_count = frm.lineCount.value;
    line_count++;
    frm.lineCount.value = line_count;
    if (frm.RequisitionLine_itemNumber.value.length > 0)
    {
      doSubmit('/requests/req_item.jsp','RequisitionLineItemLookup');
    }
    else
    {
      doSubmit('/requests/req_item.jsp','RequisitionLineCreate');
    }
  }

  function reqSave()
  {
	var reqNumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
    if (reqNumber == "N/A")
    {
		if (autoReqNumber && !showAutoReqNumber)
		{
			doSubmit(currentpage, "RequisitionGetFormNumber;RequisitionRetrieve");
		}
		else
		{
	    	popupParameters = "formtype=REQ;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=save";
    		doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
    	}
	}
  }

  function reqSaveAs()
  {
		if (autoReqNumber && !showAutoReqNumber)
		{
			if (confirm("Save this requisition as a new request?"))
			{
				frm.RequisitionHeader_requisitionNumber.value = "";
				frm.RequisitionHeader_fiscalYear.value = fiscalyear;
				doSubmit(currentpage, "RequisitionSaveas;RequisitionRetrieve");
			}
		}
		else
		{
			popupParameters = "formtype=REQ;formnumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>;fiscalyear=" + fiscalyear + ";action=saveas";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
    	}
  }

  function reqForward()
  {
    var line_count = frm.lineCount.value;

    if (line_count > 0)
    {
      frm.reqaction.value = "FORWARD";
<%		if (s_req_type.equalsIgnoreCase("N") && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) { %>
        doSubmitToPopup('/requests/req_forward_to.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
<%		} else { %>
        doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
<%		}%>
    }
    else
    {
      alert("You must add items before forwarding a Requisition!");
    }
  }

  function reqPreview()
  {
    popupParameters = "RequisitionHeader_icReqHeader=<%=b_req_ic_header%>";
    doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=540px');
  }

  function validateReq(action)
  {
    if(action == 'FORWARD')
    {
      hideForwardButton();
    }
    frm.reqaction.value = "VALIDATE";
    var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
    popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
    popupParameters = popupParameters + ";reqaction=" + action;
    //doSubmitToPopup('/requests/req_validate.jsp', 'RequisitionValidate', 'width=675px', 'height=450px');
    doSubmit('/requests/req_validate_no_popup.jsp', 'RequisitionValidate');
  }

  function viewHistory()
  {
    popupParameters = "HistoryLog_icHeaderHistory=<%=requisitionHeader.getIcHeaderHistory()%>;formtype=REQ;RequisitionLine_icReqHeader=<%=b_req_ic_header%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
    doSubmitToPopup('/requests/req_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
  }

  function browseSchedules(type, xml)
  {
    popupParameters = "Schedule_documentType=RQH;Schedule_scheduleType=" + type + ";Schedule_icHeader=<%=requisitionHeader.getIcReqHeader()%>";
    doSubmitToPopup('base/schedules.jsp', 'ScheduleRetrieveAllLinesBy', 'WIDTH=700px', 'HEIGHT=300px');
  }

  function switchView()
  {
    frm.viewType.value = "WIZARD";
    doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
  }

  function viewAttachments() {
    doSubmit('/requests/req_attachments.jsp', 'DocAttachmentRetrieveByHeader');
  }

  function openDocument(row)
  {
    var filename = "";
    if (document.all("docFilename").length > 1)
    {
      filename = frm.docFilename[row].value;
    }
    else
    {
      filename = frm.docFilename.value;
    }
    openAttachment(filename);
  }

  function getRoutingList(action)
  {
    frm.reqaction.value = "ROUTING";
    var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
    popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
    popupParameters = popupParameters + ";reqaction=" + action;
    doSubmitToPopup('/requests/req_routinglist.jsp', 'RequisitionGetRoutingList', 'width=675px', 'height=450px');
  }

  function setItemNumber()
  {
    frm.as_itemNumber.value = frm.RequisitionLine_itemNumber.value;
  }

  function viewAppNotes()
  {
    var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
    popupParameters = "RequisitionHeader_icReqHeader=" + icHeader;
    popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;
    doSubmitToPopup('/requests/req_approval_notes.jsp', 'ApprovalLogRetrieveByHeader', 'width=675px', 'height=450px');
  }

	function hideForwardButton()
    {
      hideArea('forward_link');
    }

	function resendReqToApprover() {
		if (verifyAction("Resend this Requisition to the current approver?")) {
			var icHeader = "<%=requisitionHeader.getIcReqHeader()%>";
			popupParameters = "SendQueue_docic=" + icHeader;

			doSubmitToPopup('/requests/req_approver_email_resent.jsp', 'RequisitionResendApproverEmail', 'width=680px', 'height=350px');
		} else {
			return;
		}
	}

	function createPurchaseOrderFromReq()
	{
		var msg = 'Create Purchase Order from this Requisition?';
		if (reqType == "E") {
			msg = 'Create Release Order from this Requisition?';
		} else if (reqType == "C") {
			msg = 'Create Revision from this Requisition?';
		}

		if (verifyAction(msg))
		{
			if (frm.viewType.value == "WIZARD")
			{
				doSubmit('/orders/po_review.jsp', 'PoCreateFromReq;PoRetrieve');
			}
			else
			{
				doSubmit('/orders/po_summary.jsp', 'PoCreateFromReq;PoRetrieve');
			}
		}
		else
		{
			return false;
		}
	}

	function convertToPurchaseReq()
    {
    	if (confirm("Convert this to a Purchase Requisition?"))
    	{
			doSubmit('/requests/req_summary.jsp', 'PricingReqConvertToPurchaseReq;RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
		}
	}


// End Hide script -->
</SCRIPT>