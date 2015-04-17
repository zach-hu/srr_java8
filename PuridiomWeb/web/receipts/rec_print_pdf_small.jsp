<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="thms" nowrap colspan="2">&nbsp;&nbsp;Print Options&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
						<tr>
							<td width="15px" align="center" id="viewpdf"><a href="javascript: viewNow();"><img class="button" src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Print" ></a>
							<tsa:hidden name="viewNow" value="Y" /></td>
							<td nowrap id="viewpdf"><a href="javascript: viewNow();">View Pdf</a></td>
						</tr>
<% if (oid.equalsIgnoreCase("VSE06P")) { %>
						<tr>
							<td width="15px" align="center" id="printlabels"><a href="javascript: printLabels();"><img class="button" src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Print Labels" ></a>
							<tsa:hidden name="printLabels" value="Y" /></td>
							<td nowrap id="printlabels"><a href="javascript: printLabels();">Print Labels</a></td>
						</tr>
<% } %>
						<tr>
							<td width="15px" align="center" id="emailto"><a href="javascript: emailPdf();"><img class="button" src="<%=contextPath%>/images/email.gif" border="0" alt="Print" ></a>
							</td>
							<td nowrap id="emailto"><a href="javascript: emailPdf();">Email To</a>&nbsp;<input type="text" name="email" value="" size="60" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>

<tr><td><br><br></td></tr>