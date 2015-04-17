<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	//setNavCookie("/admin/systemtables/commodity.jsp", "DoNothing");

	var myTable = document.getElementById("security");
	var TotalRows = myTable.rows.length ;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;

	var currentRow = 0;


	if (TotalRows == 0)
	{
		addNew();
	}

	function thisLoad()
	{
		f_StartIt();
	}


	function addNew()
	{
		<%
			String s_accesIdType="", s_accesId="", s_owner="", s_owner_name="", s_dateEntered="",  s_dateChanged="", s_lastChangedBy="", s_lastChangedBy_name="";

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30);
			s_owner = user.getUserId();
			s_owner_name = user.getDisplayName();
			s_dateEntered = new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
			s_dateChanged = new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
			s_lastChangedBy = user.getUserId();
			s_lastChangedBy_name = user.getDisplayName();

		%>

		frm.deleteall.value = "FALSE";
		myTable = document.getElementById("security");
		//TotalRows = TotalRows+1;

		myRow = createRow(myTable);
		maxRows++;

		//myCell = createCell(myRow);
		//myCell.innerHTML = "";

		count = maxRows - 1;

		myCell = createCell(myRow);
		id = "accessType";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML =
		"<input type=\"hidden\" name=\"CatalogSecurity_catalogId\" value=\"<%=catalogId%>\">" +
		"<input type=\"hidden\" name=\"CatalogSecurity_itemNumber\" value=\"<%=itemNumber%>\">" +
		"<select name=\"CatalogSecurity_accessType\" onfocus='setCurrentRow(" + count + ");'>" +
		          "<option value=\"UI\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user", "User")%></option>" +
		          "<option value=\"UD\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userdepartment", "Department")%></option>" +
		          "<option value=\"UU1\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udfs", "Ship To Code")%></option>" +
		          "<option value=\"UU2\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udfs", "Location")%></option>" +
		          "<option value=\"UU3\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udfs", "Region")%></option>" +
		          "<option value=\"UU4\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udfs", "Name Udf4")%></option>" +
		          "<option value=\"UU5\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udfs", "Name Udf5")%></option>" +
		          "<option value=\"UL\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userlocation", "User Location")%></option>" +
		          "<option value=\"UT\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-type", "User Type")%></option>" +
		          "<option value=\"UR\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userrequisitioner", "Requisitioner")%></option>" +
		          "<option value=\"HU\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-header-udf", "Header UDF1")%></option>" +
		          "<option value=\"LU\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-lineUdf", "Line UDF2")%></option>" +
		"</select>";
		<%=HtmlWriter.cellVisibility(oid, "accessType")%>
		<%=HtmlWriter.cellDisplay(oid, "accessType")%>

		myCell = createCell(myRow);
		id = "accessId";
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"CatalogSecurity_accessId\" size=20 value=\"<%=s_accesId%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "accessID")%>
		<%=HtmlWriter.cellDisplay(oid, "accessID")%>

		myCell = createCell(myRow);
		id = "accessIdDescription";
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"CatalogSecurity_accessDescription\" size=40 value=\"\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled>";
		<%=HtmlWriter.cellVisibility(oid, "accessIDDescription")%>
		<%=HtmlWriter.cellDisplay(oid, "accessIDDescription")%>

		myCell = createCell(myRow);
		id = "owner";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"as_catalogSecurityOwner\" size=20 value=\"<%=s_owner_name%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled>" +
		"<input type=\"hidden\" name=\"CatalogSecurity_owner\" value=\"<%=s_owner%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "owner")%>
		<%=HtmlWriter.cellDisplay(oid, "owner")%>


		myCell = createCell(myRow);
		id = "dateEntered";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"CatalogSecurity_dateEntered\" size=20 value=\"<%=s_dateEntered%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled>";
		<%=HtmlWriter.cellVisibility(oid, "dateEntered")%>
		<%=HtmlWriter.cellDisplay(oid, "dateEntered")%>


		myCell = createCell(myRow);
		id = "dateChanged";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"CatalogSecurity_dateChanged\" size=20 value=\"<%=s_dateChanged%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled>";
		<%=HtmlWriter.cellVisibility(oid, "dateChanged")%>
		<%=HtmlWriter.cellDisplay(oid, "dateChanged")%>


		myCell = createCell(myRow);
		id = "lastChangedBy";
		myCell.id = id;
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"as_catalogSecurityLastChangedBy\" size=20 value=\"<%=s_lastChangedBy_name%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");' disabled>" +
		"<input type=\"hidden\" name=\"CatalogSecurity_lastChangedBy\" size=20 value=\"<%=s_lastChangedBy%>\" onChange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "lastChangedBy")%>
		<%=HtmlWriter.cellDisplay(oid, "lastChangedBy")%>


		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		//myCell.width = "25px";
		myCell.innerHTML = "<a href=\"javascript: if (confirmDelete()) { deleteMe(" + count + "); void(0); } \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		currentRow = count;
		//count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function browseAccess()
	{
		var type;
		count = myTable.rows.length;

		if (count > 1)
		{
			type = frm.CatalogSecurity_accessType[currentRow].value;
		}
		else
		{
			type = frm.CatalogSecurity_accessType.value;
		}

		if (type == "UI")
		{
			browseSchedule('CatalogSecurity_accessId', 'user'); void(0);
		}
		else if (type == "PI")
		{
			browseSchedule('CatalogSecurity_accessId', 'userPool'); void(0);
		}
		else if (type == "UD")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "UU1")
		{
			browseSchedule('CatalogSecurity_accessId','ship_to'); void(0);
		}
		else if (type == "UU2")
		{
			browseSetup('stdTable', 'RQ01','CatalogSecurity_accessId'); void(0);
		}
		else if (type == "UU3")
		{
			browseSetup('stdTable', 'RQ02','CatalogSecurity_accessId'); void(0);
		}
		else if (type == "UU4")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "UU5")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "UL")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "UT")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "UR")
		{
			browseSchedule('CatalogSecurity_accessId', 'requisitioner'); void(0);
		}
		else if (type == "HU")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
		else if (type == "LU")
		{
			browseSchedule('CatalogSecurity_accessId', 'department'); void(0);
		}
	}

	/*function browseAccess(){
	return;
	}*/

	function deleteMe(row)
	{
		var accRows = maxRows;

		myTable = document.getElementById("security");

		if (accRows == 0)
		{
			return;
		}
		else if (accRows > 1)
		{
			for (var i = row; i < (accRows - 1); i++)
			{
				var catalogId = frm.CatalogSecurity_catalogId[i+1].value;
				var itemNumber = frm.CatalogSecurity_itemNumber[i+1].value;
				var accessType = frm.CatalogSecurity_accessType[i + 1].value;
				var accessId = frm.CatalogSecurity_accessId[i + 1].value;
				var owner = frm.CatalogSecurity_owner[i + 1].value;
				var dateEntered = frm.CatalogSecurity_dateEntered[i + 1].value;
				var dateChanged = frm.CatalogSecurity_dateChanged[i + 1].value;
				var lastChangedBy = frm.CatalogSecurity_lastChangedBy[i + 1].value;

				frm.CatalogSecurity_catalogId[i].value = catalogId;
				frm.CatalogSecurity_itemNumber[i].value = itemNumber;
				frm.CatalogSecurity_accessType[i].value = accessType;
				frm.CatalogSecurity_accessId[i].value = accessId;
				frm.CatalogSecurity_owner[i].value = owner;
				frm.CatalogSecurity_dateEntered[i].value = dateEntered;
				frm.CatalogSecurity_dateChanged[i].value = dateChanged;
				frm.CatalogSecurity_lastChangedBy[i].value = lastChangedBy;
			}
		}

		deleteRow(myTable, accRows - 1);

		accRows = accRows - 1;

		if (accRows <= 0)
		{
			frm.deleteall.value = "TRUE";
		}

		maxRows = maxRows - 1;

		if (accRows <= 0)
		{
			addNew();
		}
	}

	function confirmDelete() {
		return confirm('Are you sure you want to delete this security access?');
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all security access for this Order?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("security");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function changeAccessId()
	{
		count = 0;
		if (myTable.rows.length > 0) {
			count = myTable.rows.length;
		}

		if (count > 1) {
			frm.CatalogSecurity_accessId[currentRow].value = "";
		}
		else {
			frm.CatalogSecurity_accessId.value = "";
		}
	}

	function formFieldArray()
	{
   		var myArray = new Array();
   		var msg = "";
   		myTable = document.getElementById("security");
   		count = myTable.rows.length;

		if (count > 1)
		{
			for (var i = 0; i < frm.CatalogSecurity_accessType.length ; i++)
			{
				var accessType = frm.CatalogSecurity_accessType[i].value;
				var nameAccessType;
				var accessId = frm.CatalogSecurity_accessId[i].value;
				if(accessType == "UI")
				{
			  		nameAccessType = "User";
				}
				else if(accessType == "UD")
				{
			  		nameAccessType = "Department";
				}
				else if(accessType == "UU1")
				{
			  		nameAccessType = "Ship To Code ";
				}
				else if(accessType == "UU2")
				{
			  		nameAccessType = "Location";
				}
				else if(accessType == "UU3")
				{
			  		nameAccessType = "Region";
				}

				if( myArray["'" + accessType + "-" + accessId +  "'"] == undefined )
				{
					myArray["'" + accessType + "-" + accessId +  "'"] = accessType + " - " + accessId;
				}
				else
				{
					msg += "Already exits record " + nameAccessType + " - " + accessId  + "\n";
				}
			}
		}
   		return msg;
	}

	function validateFormCatalogSecurity()
	{
   		var msg = formFieldArray();

	   	if( msg.length > 1)
   		{
			alert("Please fix the following problems:\n\n "+ msg );
			return false;
   		}
		return true;
	}


// End Hide script -->
</SCRIPT>

