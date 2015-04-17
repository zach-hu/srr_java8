<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printoptions", "Print Options")%>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
						<tr>
							<td width="15px" align="center" id="viewpdf"><input type="radio" name="print_option" value="pdf" checked />
							<tsa:hidden name="viewNow" value="Y" /></td>
							<td nowrap id="viewpdf" colspan="2"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewPdf", "View Pdf")%></td>
						</tr>
						<tr>
							<td width="15px" align="center" id="emailto"><input type="radio" name="print_option" value="eu" /></td>
							<td nowrap id="emailto" align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "emailTo", "Email To")%>&nbsp;</td>
							<td nowrap><input type="text" name="email" value="" size="35" onfocus="selectEmailTo();" /></td>
						</tr>
						<tr>
							<td width="15px" align="center">&nbsp;</td>
							<td nowrap valign="top" align="right"><label for="notes"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "notes", "Notes")%></label>&nbsp;</td>
							<td nowrap><textarea name="notes" cols="55" rows="4" id="notes"></textarea></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>