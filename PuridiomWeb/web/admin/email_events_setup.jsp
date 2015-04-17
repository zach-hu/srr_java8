<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	rfqLabel = "RFQ";

	if (propertiesManager.getProperty("RFQ OPTIONS", "solicitation", "N").equalsIgnoreCase("Y")) {
		rfqLabel = "Solicitation";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Email Event Administration</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<br>

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td align=center width=100%>
		<table border=1 cellspacing=0 cellpadding=0 width=600px class=browseHdr>
		<tr>
			<td align=center width=100% class=browseHdr>
				<table border=0 cellspacing=0 cellpadding=2 width=600px class=browseHdr>
				<tr>
					<td width=40% align=center class=browseHdr>&nbsp;</td>
					<td width=10% align=center class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%></b></td>
					<td width=10% align=center class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqBy", "Req. By")%></b></td>
					<td width=10% align=center class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approver", "Approver")%></b></td>
					<td width=10% align=center class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyer", "Buyer")%></b></td>
					<td width=20% align=center class=browseHdr><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commentId", "Comment Id")%></b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align=center width=100%>
				<table id="reqEvents" border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6><b>Requisition Events</b></td>
				</tr>
				<tr>
					<td width=40%>
						Requisition is forwarded for approval.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHFWD"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHFWD\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHFWD\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHFWDCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHFWDCMT", "")%>">
					</td>
				</tr>
				<tr>
					<td>
						Requisition is approved for purchase.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHAPP"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHAPP\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHAPP\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHAPPCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHAPPCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Requisition is rejected.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHREJ"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHREJ\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHREJ\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHREJCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHREJCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Requisition is assigned to buyer.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHBUY"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHBUY\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHBUY\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHBUYCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHBUYCMT", "")%>"></td>
				</tr>
<%	if (propertiesManager.getProperty("Req Options", "PricingReq", "N").equalsIgnoreCase("Y")) {%>
				<tr>
					<td>
						Pricing Requisition is forwarded for Pricing.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHFWP"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHFWP\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHFWP\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHFWP"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHFWPCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Pricing Requisition is returned to Requisitioner.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHPRI"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHPRI\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RQHPRI\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RQHPRICMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RQHPRICMT", "")%>"></td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6 height=25px valign=bottom><b><%=rfqLabel%> Events</b></td>
				</tr>
				<tr>
					<td width=40%>
						Pricing <%=rfqLabel%> is returned to Requisitioner.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RFHPRI"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RFHPRI\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"RFHPRI\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="RFHPRICMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "RFHPRICMT", "")%>"></td>
				</tr>
<%	}   %>

				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6 height=25px valign=bottom><b>Order Events</b></td>
				</tr>
				<tr>
					<td width=40%>
						Order is forwarded for approval.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHFWD"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHFWD\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHFWD\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHFWDCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POHFWDCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Order has been Rejected.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHREJ"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHREJ\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHREJ\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHREJCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POHREJCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Order is Approved/Awarded.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHAWD"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHAWD\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHAWD\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHAWDCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POHAWDCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Partial receipt against order.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHPRC"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHPRC\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHPRC\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHPRCCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POHPRCCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Order has been fully received.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHFRC"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHFRC\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POHFRC\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POHFRCCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POHFRCCMT", "")%>"></td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6 height=25px valign=bottom><b>Line Item Events</b></td>
				</tr>
				<tr>
					<td width=40%>
						End User Receipt Required (After Award).
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POLRRQ"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POLRRQ\", \"\")%>"/>
						<tsa:hidden name="flags" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POLRRQ\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POLRRQCMT"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS", "POLRRQCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Line Item Received.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POLREC"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"POLREC\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="POLRECCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "POLRECCMT", "")%>"></td>
				</tr>
				</table>
<%	if (propertiesManager.getProperty("Approvals","vchapprove","N").equalsIgnoreCase("Y")) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6 height=25px valign=bottom><b>Voucher Events</b></td>
				</tr>
				<tr>
					<td width=40%>
						Voucher is forwarded for approval.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHFWD"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"VCHFWD\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHFWDCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "VCHFWDCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Voucher has been Rejected.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHREJ"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"VCHREJ\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHREJCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "VCHREJCMT", "")%>"></td>
				</tr>
				<tr>
					<td>
						Voucher is Approved.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHAWD"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"VCHAWD\", \"\")%>"/>
					</td>
					<td align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHAWDCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "VCHAWDCMT", "")%>"></td>
				</tr>
<%	}
	if (propertiesManager.getProperty("Voucher","AutoMethod","N").equalsIgnoreCase("Y")) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=600px>
				<tr>
					<td colspan=6 height=25px valign=bottom><b>Voucher (Auto Method) Events</b></td>
				</tr>
<%		if (propertiesManager.getProperty("Voucher","NoInvoiceQtyException","Y").equalsIgnoreCase("Y")) {%>
				<tr>
					<td width=40%>
						Invoice Quantity Exception.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHQTY"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"VCHQTY\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHQTYCMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "VCHQTYCMT", "")%>"></td>
				</tr>
<%		}%>
				<tr>
					<td width=40%>
						Invoice Price/Frieght Exception.
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHPFE"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\", \"VCHPFE\", \"\")%>"/>
					</td>
					<td width=10% align=center><input type=checkbox name=ownerFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=requestorFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=approverFlag value="Y"></td>
					<td width=10% align=center><input type=checkbox name=buyerFlag value="Y"></td>
					<td width=20% align=center>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="VCHPFECMT"/>
						<input type=text name=Property_value value="<%=propertiesManager.getProperty("MAILEVENTS", "VCHPFECMT", "")%>"></td>
				</tr>
				</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=600px>
		<tr>
			<td align=center width=50%>
				<table border=0 cellspacing=0 cellpadding=0 width=100%>
				<tr>
					<td align=right>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="UseMailEvents"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\",\"UseMailEvents\",\"N\")%>"/>
						<input type=checkbox name="enableMailEvents" value="Y" <% if (propertiesManager.getProperty("MAILEVENTS","UseMailEvents","N").equalsIgnoreCase("Y")) {%>checked<%}%>>
					</td>
					<td width=245px class=label>Enable Email Events</td>
				</tr>
				<tr>
					<td align=right>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="IgnoreSelfMail"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\",\"IgnoreSelfMail\",\"N\")%>"/>
						<input type=checkbox name="ignoreSelfMail" value="Y" <% if (propertiesManager.getProperty("MAILEVENTS","IgnoreSelfMail","N").equalsIgnoreCase("Y")) {%>checked<%}%>>
					</td>
					<td width=245px class=label>Ignore self addressed Email</td>
				</tr>
				<tr>
					<td align=right>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="SendCreateMessage"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MAILEVENTS\",\"SendCreateMessage\",\"N\")%>"/>
						<input type=checkbox name="sendCreateMessage" value="Y" <% if (propertiesManager.getProperty("MAILEVENTS","SendCreateMessage","N").equalsIgnoreCase("Y")) {%>checked<%}%>>
					</td>
					<td width=245px class=label>Append Creation Message</td>
				</tr>
				</table>
			</td>
			<td align=center width=50%>
				<table border=0 cellspacing=2 cellpadding=0 width=100%>
				<tr>
					<td class=label>Administrative Email Address</td>
				</tr>
				<tr>
					<td>
						<tsa:hidden name="Property_section" value="MAILEVENTS"/>
						<tsa:hidden name="Property_property" value="AdminEmailAddr"/>
						<input type=text name="Property_value" value="<%=propertiesManager.getProperty("MAILEVENTS","AdminEmailAddr","")%>" size=40>
					</td>
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
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'PropertyUpdate'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setEventFlags();

	function setEventFlags() {
		var count = document.all.ownerFlag.length;
		var flags = "";

		for (var i=0; i < count; i++) {
			flags = frm.Property_value[i*2].value;

			if (flags.length == 4) {
				if (flags.substring(0, 1) == "Y") {
					frm.ownerFlag[i].checked = true;
				}
				else {
					frm.ownerFlag[i].checked = false;
				}
				if (flags.substring(1, 2) == "Y") {
					frm.requestorFlag[i].checked = true;
				}
				else {
					frm.requestorFlag[i].checked = false;
				}
				if (flags.substring(2, 3) == "Y") {
					frm.approverFlag[i].checked = true;
				}
				else {
					frm.approverFlag[i].checked = false;
				}
				if (flags.substring(3, 4) == "Y") {
					frm.buyerFlag[i].checked = true;
				}
				else {
					frm.buyerFlag[i].checked = false;
				}
			}
		}
	}

	function setEventValues() {
		var count = document.all.ownerFlag.length;
		var flags = "";

		for (var i=0; i < count; i++) {
			flags = "";

			if (frm.ownerFlag[i].checked) {
				flags = "Y";
			}
			else {
				flags = "N";
			}
			if (frm.requestorFlag[i].checked) {
				flags = flags + "Y";
			}
			else {
				flags = flags + "N";
			}
			if (frm.approverFlag[i].checked) {
				flags = flags + "Y";
			}
			else {
				flags = flags + "N";
			}
			if (frm.buyerFlag[i].checked) {
				flags = flags + "Y";
			}
			else {
				flags = flags + "N";
			}

			frm.Property_value[i*2].value = flags;
		}

		count = document.all.Property_value.length;

		if (frm.enableMailEvents.checked) {
			frm.Property_value[count - 4].value = "Y";
		}
		else {
			frm.Property_value[count - 4].value = "N";
		}
		if (frm.ignoreSelfMail.checked) {
			frm.Property_value[count - 3].value = "Y";
		}
		else {
			frm.Property_value[count - 3].value = "N";
		}

		if (frm.sendCreateMessage.checked) {
			frm.Property_value[count - 2].value = "Y";
		}
		else {
			frm.Property_value[count - 2].value = "N";
		}
	}

	function validateForm() {
		setEventValues();

		return true;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>