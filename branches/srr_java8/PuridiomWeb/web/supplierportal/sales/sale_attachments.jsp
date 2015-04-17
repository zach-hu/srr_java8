<%	if (attachmentList != null && attachmentList.size() > 0) {%>
	<tr><td colspan=2><br></td></tr>
	<tr>
		<td valign=top><b>Attachments:</b></td>
		<td colspan=5>
			<table border=0 cellspacing=0 cellpadding=2 width=100% class=summary id="attachments">
	<%	for(int i = 0; i < attachmentList.size(); i++) {
				DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
				
				if (docAttachment.getDocPost().equalsIgnoreCase("Y")) {
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
	%>
			<tr>
				<td width=25px align=center valign=middle>
		<%		if (ext.equalsIgnoreCase("DOC")) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
		<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
		<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
		<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
		<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
		<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
		<%		} else {%>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
		<%		}%>
				</td>
				<td>
					<a href="javascript: openAttachment(<%=i%>); void(0);"><%=docAttachment.getDocTitle()%></a>
					<tsa:hidden name="docFilename" value="<%=filename%>"/>
				</td>
	<%			if (i == 0) {%>
				<td colspan=2 rowspan=<%=attachmentList.size()%>>
					<table border=0 cellspacing=0 cellpadding=2 width=100% class=summary>
					<tr>
						<td align=right nowrap><b>Reserve:</b></td>
						<td>$1200.00</td>
						<td align=right><b>Currency:</b></td>
						<td><%=saleHeader.getCurrency()%></td>
					</tr>
					<tr>
						<td align=right nowrap><b>Bid Increment:</b></td>
						<td>10 %</td>
						<td align=right><b>Taxable:</b></td>
						<td><% if (saleLine.getTaxable().equalsIgnoreCase("Y")) {%>Yes<%} else {%>No<%}%></td>
					</tr>
					</table>
				</td>
<%				}
				}
			}%>
			</table>
		</td>
	</tr>
<%	}%>