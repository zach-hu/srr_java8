<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	List	disbLineList = (List) request.getAttribute("disbLineList");
	String	s_disb_number = (String) request.getAttribute("disbNumber");
	int	i_line_count = 0;

	if (disbLineList != null && disbLineList.size() > 0)
	{
		DisbLine disbLine = (DisbLine) disbLineList.get(0);
		s_disb_number = disbLine.getDisbNumber();
		i_line_count = disbLineList.size();
	}
%>

<table border=0 cellpadding=0 cellspacing=0 width=655px>
<tr><td><br></td></tr>
<tr>
	<td width=100%>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12>Disbursement History</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom align=left><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px width=100%>
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
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align=center>
		<font class=formType>Disbursement </font><font class=hdr12>#<%=s_disb_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align=center>
		<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td width=100% align=center valign=top>
					<table border=0 cellspacing=0 cellpadding=2 width=500px>
						<tr class=mnav>
							<td nowrap width=35px class=mnav align=right>Line&nbsp;&nbsp;</td>
							<td nowrap width=175px class=mnav>Item #</td>
							<td nowrap width=70px class=mnav align=right>Quantity</td>
							<td width=15px class=mnav>&nbsp;</td>
							<td nowrap width=70px class=mnav>UOM</td>
							<td nowrap width=70px class=mnav align=right>Unit Price</td>
							<td nowrap width=70px class=mnav align=right>Total&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center>
		<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td width=100% align=center valign=top>
					<table border=0 cellspacing=0 cellpadding=2 width=500px>
<%	if (disbLineList == null)
		{
			/**	LINE ITEM HISTORY	**/
			DisbLine disbLine = (DisbLine) request.getAttribute("disbLine");
			if (disbLine != null){		
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(disbLine.getUnitPrice(), oid);
				BigDecimal bd_total = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price), oid);
%>
					<tr>
							<td width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(disbLine.getLineNumber(), 0)%>&nbsp;</td>
							<td nowrap width=175px valign=top><b><%=disbLine.getItemNumber()%></b></td>
							<td width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td width=70px valign=top><%=disbLine.getUmCode()%></td>
							<td width=70px valign=top align=right><%=bd_unit_price%></td>
							<td width=70px valign=top align=right><%=bd_total%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=disbLine.getDescription()%></td>
						</tr>
<%
			}
		}
		else
		{
			/**	ALL REQUISITION HISTORY		**/
			for (int i = 0; i < i_line_count; i++)
			{
				DisbLine disbLine = (DisbLine) disbLineList.get(i);
				String	s_item_number = disbLine.getItemNumber();
				String	s_item_description = disbLine.getDescription();
				String s_item_umcode = disbLine.getUmCode();
				BigDecimal bd_line_number = disbLine.getLineNumber();
				BigDecimal bd_quantity = disbLine.getQuantity().setScale(Integer.valueOf(s_quantity_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
				BigDecimal bd_unit_price = disbLine.getUnitPrice().setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);

				if (s_item_number == null)		{	s_item_number = "";			}
				if (s_item_description == null)	{	s_item_description = "";	}
				if (s_item_umcode == null)		{	s_item_umcode = "";		}

				BigDecimal bd_total = (bd_quantity.multiply(bd_unit_price)).setScale(Integer.valueOf(s_dollar_decimals).intValue(), BigDecimal.ROUND_HALF_UP);
%>
						<tr>
							<td width=35px align=right valign=top><%=HiltonUtility.getBigDecimalFormatted(disbLine.getLineNumber(), 0)%>&nbsp;</td>
							<td nowrap width=175px valign=top><b><%=s_item_number%></b></td>
							<td width=70px valign=top align=right><%=bd_quantity%></td>
							<td width=15px >&nbsp;</td>
							<td width=70px valign=top><%=s_item_umcode%></td>
							<td width=70px valign=top align=right><%=bd_unit_price%></td>
							<td width=70px valign=top align=right><%=bd_total%></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan=6><%=s_item_description%></td>
						</tr>
<%
			}
		}
%>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr><td><br><br><br></td></tr>
<tr>
	<td width=100% align=center valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr class=mnav height=18px>
			<td nowrap class=mnav>Date/Time</td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav>User</td>
			<td width=15px class=mnav>&nbsp;</td>
			<td nowrap class=mnav>Description</td>
		</tr>
<%
	List	historyLogList = (List) request.getAttribute("historyLogList");
	if (historyLogList != null)
	{
		for (int i = 0; i < historyLogList.size(); i++)
		{
			HistoryLog history = (HistoryLog) historyLogList.get(i);
			String	s_user = history.getUserid();
			String	s_date = history.getLogDate();
			String	s_time = history.getLogTime();
			String	s_description = history.getDescription();

			if (s_user == null)			{	s_user = "";			}
			if (s_date == null)			{	s_date = "";			}
			if (s_time == null)			{	s_time = "";			}
			if (s_description == null)	{	s_description = "";	}

			UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height=18px>
				<td nowrap valign=top><%=s_date%>&nbsp;<%=s_time%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=userProfile.getDisplayName()%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=s_description%></td>
			</tr>
<%
		}
	}
	if (historyLogList == null || historyLogList.size() <= 0)
	{
%>
		<tr height=18px>
			<td colspan=5 align=center><b>There is no history available at this time!</b></td>
		</tr>
<%
		}
%>
		</table>
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0></a></td>
</tr>

</table>