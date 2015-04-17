<%
	List	languageList = propertiesManager.getListFromDelimitedProperty("MISC", "LANGUAGES", "");
%>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" valign="top">
		<table id="altText" border="0" cellspacing="0" cellpadding="2" class="browseRow">
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "alttext-language")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alttext-language", "Language")%></td>
			<td <%=HtmlWriter.isVisible(oid, "alttext-description")%>  nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alttext-description", "Description")%></td>
			<td nowrap>&nbsp;
				<tsa:hidden name="AltText_source" value="<%=source%>"/>
				<tsa:hidden name="AltText_id" value="<%=id%>"/>
				<tsa:hidden name="AltText_itemNumber" value="<%=itemNumber%>"/>
				<tsa:hidden name="AltText_icLine" value="<%=icLine%>"/>
			</td>
		</tr>
		</table>
<%	if (bAllowEdit) { %>
		<table border=0 cellspacing=0 cellpadding=2 width=600px align=center>
			<tr>
				<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
				<td>&nbsp;</td>
				<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
			</tr>
		</table>
<%	} %>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
<%	if (bAllowEdit) {%>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAltTextList(); void(0);">Save</a></div></td>
<%	}%>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);">Return</a></div></td>
	</tr>
</table>

<div style="visibility:hidden; display:none;" id="languageSelectOptions">
<%	if (languageList != null) {
			String languageOption = "";

			for (int il = 0; il < languageList.size(); il++) {
				languageOption = HiltonUtility.ckNull((String) languageList.get(il)).trim().toUpperCase();
%>
		<option value="<%=languageOption%>"><%=languageOption%></option>
<%		}
		}%>
</div>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="AltText_language" value=""/>
<tsa:hidden name="AltText_icText" value=""/>
<tsa:hidden name="DocText_icText" value=""/>
<tsa:hidden name="DocText_stdText" value=""/>
</div>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	//setNavCookie("/admin/systemtables/commodity.jsp", "DoNothing");

	var myTable = document.getElementById("altText");
	var totalRows = myTable.rows.length - 1;
	var currentRow = 0;
	var languageOptionCount = <%=languageList.size()%>;

<%	int i_rowcount = 0;
		if (altTextList != null && altTextList.size() > 0) {
			for (int i = 0; i < altTextList.size(); i++) {
				AltText altText = (AltText) altTextList.get(i);%>
				addNew();
				frm.AltText_language[<%=i%>].value = "<%=altText.getComp_id().getLanguage()%>";
				frm.AltText_icText[<%=i%>].value = "<%=altText.getIcText()%>";
				frm.DocText_icText[<%=i%>].value = "<%=altText.getIcText()%>";
				frm.DocText_stdText[<%=i%>].value = "<%=altText.getDocText().getStdText()%>";
<%			i_rowcount++;
			}
		}%>

	if (totalRows == 0) {
		addNew();
	}

	function thisLoad() {
		f_StartIt();
	}

	function addNew() {
		var languageSelectOptions = document.getElementById("languageSelectOptions").innerHTML;

		frm.deleteall.value = "FALSE";
		myTable = document.getElementById("altText");

		myRow = createRow(myTable);

		var count = myTable.rows.length - 1;

		myCell = createCell(myRow);
		myCell.vAlign = "top";
		myCell.innerHTML =
		"<input type=hidden name=\"AltText_icText\" value=\"\">" +
		"<input type=hidden name=\"DocText_icText\" value=\"\">" +
		"<select name=\"AltText_language\" onfocus='setCurrentRow(" + count + ");'>" +
		         languageSelectOptions +
		"</select>";
		<%=HtmlWriter.cellVisibility(oid, "alttext-language")%>
		<%=HtmlWriter.cellDisplay(oid, "alttext-language")%>

		myCell = createCell(myRow);
		myCell.vAlign = "top";
		myCell.innerHTML = "<textarea name=\"DocText_stdText\" cols=61 rows=5 tabindex=" + eval(count *2) + " onKeyDown=\"textCounter(this, 2000);\" onKeyUp=\"textCounter(this,2000);\" onfocus=\"setCurrentRow(" + count + ")\";></textarea>";
		<%=HtmlWriter.cellVisibility(oid, "alttext-description")%>
		<%=HtmlWriter.cellDisplay(oid, "alttext-description")%>

		myCell = createCell(myRow);
		myCell.vAlign = "top";
		id = "delete_" + count;
		myCell.id = id;
<%	if (bAllowEdit) {%>
		myCell.innerHTML = "<a href=\"javascript: if (confirmDelete()) { deleteMe(" + eval(count-1) + "); void(0); } \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";
<%	} else {%>
		myCell.innerHTML = "&nbsp;";
<%	}%>

		var previousLangInd = 0;
		if (count > 1) {
			previousLangInd = frm.AltText_language[count - 2].selectedIndex;
		}

		var newIndex = eval(previousLangInd + 1);
		if (languageOptionCount > newIndex) {
			frm.AltText_language[count - 1].selectedIndex = newIndex;
		}
		totalRows++;
	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function deleteMe(row) {
		myTable = document.getElementById("altText");
		var rowCount = myTable.rows.length - 1;

		if (rowCount == 0) {
			return;
		}
		else if (rowCount > 0)
		{
			for (var i = row; i < (rowCount - 1); i++)
			{
				var languageInd = frm.AltText_language[i + 1].selectedIndex;
				var icText = frm.AltText_icText[i + 1].value;
				var stdText = frm.DocText_stdText[i + 1].value;

				frm.AltText_language[i].selectedIndex = languageInd;
				frm.AltText_icText[i].value = icText;
				frm.DocText_icText[i].value = icText;
				frm.DocText_stdText[i].value = stdText;
			}
		}

		deleteRow(myTable, rowCount);

		rowCount = rowCount - 1;

		if (rowCount <= 0)
		{
			frm.deleteall.value = "TRUE";
			addNew();
		}
	}

	function confirmDelete() {
		return confirm('Are you sure you want to delete this record?');
	}

	function deleteAll() {
		if (confirm("Are you sure you wish to delete all records?")) {
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("altText");

			var rowCount = myTable.rows.length - 1;
			for (i = rowCount;i > 0; i--)
			{
				myTable.deleteRow(i);
			}
		}
	}

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("AltTextUpdateByItem") >= 0) {
			myTable = document.getElementById("altText");

			var selectedLanguages = "";
			var rowCount = myTable.rows.length - 1;
			for (i = 0; i < rowCount; i++) {
				var language = frm.AltText_language[i].selectedIndex;
				if (selectedLanguages.indexOf(language + ";") >= 0) {
					alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "alttext-multilanguage-error", "You can only specify one description for each language.")%>");
					frm.AltText_language[i].focus();
					return false;
				}
				selectedLanguages = selectedLanguages + language + ";";
			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>