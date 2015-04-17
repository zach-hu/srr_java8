	var myTable;
	var totalRows = 0;
	var editRow = -1;
	var inEditMode = false;
	var selectedComment = -1;
	var hasCommentOrder = false;

	function setDisplay() {
		if (totalRows <= 0)
		{
			var noComments = document.getElementById("noCommentsMsg");
			noComments.style.visibility = "visible";
			noComments.style.display = "block";

			var deleteAllLink = document.getElementById("deleteAllLink");
			if (deleteAllLink) {
				deleteAllLink.style.visibility = "hidden";
				deleteAllLink.style.display = "none";
			}
		}
	}

	function addNew() {
		frm.deleteall.value = false;

		var selectedRowHTML;
		var noComments = document.getElementById("noCommentsMsg");
		noComments.style.visibility = "hidden";
		noComments.style.display = "none";

		var deleteAllLink = document.getElementById("deleteAllLink");
		deleteAllLink.style.visibility = "visible";
		deleteAllLink.style.display = "block";

		var classicNav = document.getElementById("classicNavigation");
		classicNav.style.visibility = "visible";
		classicNav.style.display = "block";

		count = myTable.rows.length;

		myRow = createRow(myTable);
		count++;

		selectedRowHTML = (hasCommentOrder) ? "<a href=\"javascript: //\" onclick=\"javascript: highlightRow(" + (count - 1) + ");\"><img src=\"" + contextPath + "/images/next.gif\" border=\"0\" alt=\"Select this line\"></a>" : "";

		myCell = createCell(myRow);
		myCell.id = "cmt_num_" + (count-1);
		myCell.align = "right";
		myCell.width = ((hasCommentOrder) ? "8" : "4") + "%";
		myCell.innerHTML = "&nbsp;" + count + ".&nbsp;" + selectedRowHTML +
			"<input type=hidden name=\"DocComment_commentOrder\" value=\"" + (count) + "\">";

		myCell = createCell(myRow);
		myCell.id = "cmt_id_" + (count-1);
		myCell.width = ((hasCommentOrder) ? "16" : "20") + "%";
		myCell.innerHTML = "<div id=\"comment_id_" + (count - 1) + "\">&nbsp;</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_edit";
		myCell.width = "42%";
		myCell.innerHTML = "<div id=\"comment_edit_" + (count - 1) + "\">" +
			"<a href=\"javascript: editCmt(" + (count - 1) + "); void(0);\"><div id=\"cmt_title\" name=\"cmt_title\"></div></a>" +
			"<input type=hidden name=\"DocComment_commentTitle\" id=\"commentTitle_" + (count-1) + "\" maxlength=\"60\" value=\"\">" +
			"<input type=hidden name=\"DocComment_commentId\" maxlength=15 value=\"\">" +
			"<input type=hidden name=\"DocText_icText\" value=\"\">" +
			"<input type=hidden name=\"DocText_stdText\" id=\"commentText_" + (count-1) + "\" value=\"\">" +
			"<input type=hidden name=\"DocComment_commentPublic\" id=\"commentPublic_" + (count - 1) + "\" value=\"\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_print";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<div id=\"comment_print_" + (count - 1) + "\">" +
			"<input type=\"checkbox\" id=\"cboxPrint_" + (count-1) + "\" name=\"cboxPrint_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setPrint(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentPrint\" id=\"commentPrint_" + (count-1) + "\" value=\"N\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_bold";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<div id=\"comment_bold_" + (count - 1) + "\">" +
			"<input type=\"checkbox\" id=\"cboxBold_" + (count-1) + "\" name=\"cboxBold_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setBold(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentBold\" id=\"commentBold_" + (count-1) + "\" value=\"N\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_place";
		myCell.align = "center";
		myCell.width = "15%";
		myCell.innerHTML = "<div id=\"comment_place_" + (count - 1) + "\">" +
			"<select name=\"DocComment_commentPlace\" id=\"commentPlace_" + (count-1) + "\"><option value=\"A\" selected>After</option><option value=\"B\">Before</option></select>" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_del_" + count;
		myCell.align = "center";
		myCell.width = "5%";
		myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete Comment line " + count  + "?')) { deleteMe(" + (count-1) + "); } void(0);\"><img src=\"" + contextPath + "/images/delete.gif\" alt=\"Delete\" border=\"0\"></a>";

		totalRows++

		editRow = count -1;
		editCmt(count-1);

		if (frm.edit_commentTitle) {
			frm.edit_commentTitle.focus();
		}
		
		if (hasCommentOrder) {
			hideCommentsMoveRow();
		}
	}

	function editCmt(row) {
		var commentList = document.getElementById("commentList");
		var classicNav = document.getElementById("classicNavigation");
		var commentEdit = document.getElementById("commentEdit");
		var commentOptions = document.getElementById("commentOptions");

		commentList.style.visibility = "hidden";
		commentList.style.display = "none";
		classicNav.style.visibility = "hidden";
		classicNav.style.display = "none";
		commentEdit.style.visibility = "visible";
		commentEdit.style.display = "block";
		if (displayOptions == "N")
		{
			commentOptions.style.visibility = "hidden";
			commentOptions.style.display = "none";
		}

		var cmtId =  document.getElementById("cmt_id_" + row).innerHTML;
		var cmtTitle =  document.getElementById("commentTitle_" + row).value;
		var cmtText = document.getElementById("commentText_" + row).value;
		var cmtPublic = document.getElementById("commentPublic_" + row).value;

		var cmtRowsTotal = myTable.rows.length;

		document.getElementById("editCommentId").innerHTML = cmtId;
		frm.edit_commentTitle.value = cmtTitle;
		frm.edit_stdText.value = cmtText;
		frm.edit_cboxPrint.checked = document.getElementById("cboxPrint_" + row).checked;
		frm.edit_cboxBold.checked = document.getElementById("cboxBold_" + row).checked;
		frm.edit_commentPlace.value = document.getElementById("commentPlace_" + row).value;

		editRow = row;
		inEditMode = true;
	}

	function cancelEdit() {
		var commentList = document.getElementById("commentList");
		var commentEdit = document.getElementById("commentEdit");
		var classicNav = document.getElementById("classicNavigation");

		commentEdit.style.visibility = "hidden";
		commentEdit.style.display = "none";
		commentList.style.visibility = "visible";
		commentList.style.display = "block";
		classicNav.style.visibility = "visible";
		classicNav.style.display = "block";

		var currentRows = myTable.rows.length;

		if (currentRows == 0) {
			return;
		} else if (currentRows > 1) {
			if (isEmpty(frm.DocComment_commentTitle[editRow].value)) {
				deleteMe(editRow);
			}
		} else {
			if (isEmpty(frm.DocComment_commentTitle.value)) {
				deleteMe(editRow);
			}
		}
		inEditMode = false;

		if (hasCommentOrder) {
			checkNumComments();
		}
	}

	function populateTitle(title, row) {
/*
		var titleArea = document.all("cmt_title");

		if (titleArea.length > 1) {
			titleArea(row).innerText = title;
		} else {
			titleArea.innerText = title;
		}
*/
		var titleArea = document.getElementsByName("cmt_title");

		titleArea[row].innerHTML = title;

		document.getElementById("commentTitle_" + row).value = title;
		newRecord = -1;
	}

	function populateCmt() {
		var cmtTitle =  frm.edit_commentTitle.value;
		var cmtText = frm.edit_stdText.value;

		if (isEmpty(cmtTitle)) {
			alert("You must enter a title for this comment.");
			return false;
		}

		populateTitle(cmtTitle, editRow);
		document.getElementById("commentText_" + editRow).value = cmtText;

		document.getElementById("cboxPrint_" + editRow).checked = frm.edit_cboxPrint.checked;
		//document.getElementById("cboxPrint_" + editRow).fireEvent("onclick");
		setPrint(editRow);
		document.getElementById("cboxBold_" + editRow).checked = frm.edit_cboxBold.checked;
		//document.getElementById("cboxBold_" + editRow).fireEvent("onclick");
		setBold(editRow);
		document.getElementById("commentPlace_" + editRow).value = frm.edit_commentPlace.value;

		var commentList = document.getElementById("commentList");
		var commentEdit = document.getElementById("commentEdit");
		var classicNav = document.getElementById("classicNavigation");

		commentEdit.style.visibility = "hidden";
		commentEdit.style.display = "none";
		commentList.style.visibility = "visible";
		commentList.style.display = "block";
		classicNav.style.visibility = "visible";
		classicNav.style.display = "block";

		inEditMode = false;

		if (hasCommentOrder) {
			checkNumComments();
		}
		checkDisplayOptions();
	}

	function setPrint(row){
		var cbox = document.getElementById("cboxPrint_" + row);
		var cmtPrint = document.getElementById("commentPrint_" + row);
		if (cbox.checked==true || defaultPrint == "Y")
		{
			cmtPrint.value = "Y";
		}
		else
		{
			cmtPrint.value = "N";
		}
	}

	function setBold(row){
		var cbox = document.getElementById("cboxBold_" + row);
		var cmtBold = document.getElementById("commentBold_" + row);
		if (cbox.checked==true)
		{
			cmtBold.value = "Y";
		}
		else
		{
			cmtBold.value = "N";
		}
	}

	function deleteMe(row) {
		var currentRows = myTable.rows.length;

		if (currentRows == 0) {
			return;
		}
		else if (currentRows > 1) {
			for (var i = row; i < (currentRows - 1); i++) {
				var cmtOrder = frm.DocComment_commentOrder[i + 1].value;
				var cmtId = frm.DocComment_commentId[i + 1].value;
				var cmtTitle = frm.DocComment_commentTitle[i + 1].value;
				var cmtPrint = frm.DocComment_commentPrint[i + 1].value;
				var cmtBold = frm.DocComment_commentBold[i + 1].value;
				var cmtPlace = frm.DocComment_commentPlace[i + 1].value;
				var cmtPublic = frm.DocComment_commentPublic[i + 1].value;
				var icText = frm.DocText_icText[i + 1].value;
				var stdText = frm.DocText_stdText[i + 1].value;

				frm.DocComment_commentOrder[i].value = cmtOrder;
				frm.DocComment_commentId[i].value = cmtId;
				frm.DocComment_commentTitle[i].value = cmtTitle;
				frm.DocComment_commentPrint[i].value = cmtPrint;
				frm.DocComment_commentBold[i].value = cmtBold;
				frm.DocComment_commentPlace[i].value = cmtPlace;
				frm.DocComment_commentPublic[i].value = cmtPublic;
				frm.DocText_icText[i].value = icText;
				frm.DocText_stdText[i].value = stdText;

				if (cmtPrint == "Y") {
					document.getElementById("cboxPrint_" + i).checked = true;
				} else {
					document.getElementById("cboxPrint_" + i).checked = false;
				}
				if (cmtBold == "Y") {
					document.getElementById("cboxBold_" + i).checked = true;
				} else {
					document.getElementById("cboxBold_" + i).checked = false;
				}

				populateTitle(cmtTitle, i);
				document.getElementById("cmt_id_" + i).innerHTML = cmtId;

			}
		}

		myTable.deleteRow(currentRows - 1);

		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";

			var noComments = document.getElementById("noCommentsMsg");
			noComments.style.visibility = "visible";
			noComments.style.display = "block";

			var deleteAllLink = document.getElementById("deleteAllLink");
			deleteAllLink.style.visibility = "hidden";
			deleteAllLink.style.display = "none";
		}

		if (hasCommentOrder) {
			checkNumComments();
			checkSelectedComment(row);
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Comments?"))
		{
			frm.deleteall.value = "TRUE";
			count = myTable.rows.length;

			for (i = 0; i < count; i++)
			{
				myTable.deleteRow(0);
			}
			var noComments = document.getElementById("noCommentsMsg");
			noComments.style.visibility = "visible";
			noComments.style.display = "block";

			var deleteAllLink = document.getElementById("deleteAllLink");
			deleteAllLink.style.visibility = "hidden";
			deleteAllLink.style.display = "none";
		}

		if (hasCommentOrder) {
			checkNumComments();
			resetSelectedComment();
		}
	}

	function isRowEmpty (k)
	{
		if (maxRows > 1)
		{
			if ( isEmpty(frm.DocComment_commentId[k].value) == false ) return false;
			if ( isEmpty(frm.DocComment_commentTitle[k].value) == false ) return false;
			if ( isEmpty(frm.DocText_icText[k].value) == false ) return false;
		}
		else if (maxRows == 1)
		{
			if(frm.DocComment_commentId[k]){
				if ( isEmpty(frm.DocComment_commentId[k].value) == false ) return false;
				if ( isEmpty(frm.DocComment_commentTitle[k].value) == false ) return false;
				if ( isEmpty(frm.DocText_icText[k].value) == false ) return false;
			} else {
				if ( isEmpty(frm.DocComment_commentId.value) == false ) return false;
				if ( isEmpty(frm.DocComment_commentTitle.value) == false ) return false;
				if ( isEmpty(frm.DocText_icText.value) == false ) return false;
			}
		}

		return ( true );
	}

	function addStandardComment() {
		frm.browseName.value = "stdcomment";
		doSubmit('/browse/browse_std_comment.jsp', currentmethod + ';BrowseRetrieve');
	}

	function validateForm()
	{
		if (inEditMode) {
			if (confirm("Click 'Ok' to save current changes.")) {
				populateCmt();
			}
		}
		maxRows = myTable.rows.length;

		if (maxRows <= 0)
		{
			frm.deleteall.value = "TRUE";
		}

		for ( var i = 0; i < maxRows; i++)
		{
			if ( isRowEmpty(i) )
			{
				deleteMe(i);
			}
		}
		return true;
	}

	function reqSave()
	{
		if (isNA(reqnumber))
		{
			if (autoReqNumber && !showAutoReqNumber) {
				doSubmit(currentpage, "RequisitionGetFormNumber;" + currentmethod + ";" + currentprocessmethod);
			} else {
				popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
				doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			}
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function rfqSave()
	{
		if (isNA(rfqnumber))
		{
			popupParameters = "formtype=RFQ;formnumber=" + rfqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function poSave()
	{
		if (isNA(ponumber))
		{
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			//popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function disbSave()
	{
		if (disbnumber == "N/A")
		{
			popupParameters = "formtype=DSB;formnumber=" + disbnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function checkSelectedComment(commentIndex) {
		if (commentIndex == selectedComment) {
			resetSelectedComment();
		}
	}

	function resetSelectedComment() {
		selectedComment = -1;
	}

	function highlightRow(commentIndex) {
		var comments = document.getElementById('cmt_comments').getElementsByTagName('TR');
		var currentComment = comments[commentIndex];

		if (commentIndex == selectedComment) {
			currentComment.className = '';
			resetSelectedComment();
		} else {
			if (selectedComment > -1) {
				comments[selectedComment].className = '';
			}
			currentComment.className = 'selectedRow';
			selectedComment = commentIndex;
		}
	}

	function move(flag) {

		var comments = document.getElementById('cmt_comments').getElementsByTagName('TR');
		var friendCommentIndex = -1;
		var commentsTotal = comments.length;
		var currentComment;
		var nextComment;
		var tmpComment;

		if (selectedComment > -1) {

			switch(flag) {
				case 'U':
					friendCommentIndex = selectedComment - 1;
					break;
				case 'D':
					friendCommentIndex = selectedComment + 1;
					break;
			}

			if ((friendCommentIndex > -1) && (friendCommentIndex < commentsTotal)) {

				currentComment = comments[selectedComment];

				nextComment = comments[friendCommentIndex];

				moveInnerHtml('comment_id', selectedComment, friendCommentIndex);
				moveInnerHtml('comment_edit', selectedComment, friendCommentIndex);
				moveInnerHtml('comment_print', selectedComment, friendCommentIndex);
				moveInnerHtml('comment_bold', selectedComment, friendCommentIndex);
				moveInnerHtml('comment_place', selectedComment, friendCommentIndex);

				/* To RFQ_notes */
				if (document.getElementById('comment_post_' + selectedComment) != undefined) {
					moveInnerHtml('comment_post', selectedComment, friendCommentIndex);
				}

				currentComment.className = '';
				nextComment.className = 'selectedRow';
				selectedComment = friendCommentIndex;
			}

		} else {
			alert("Please select a comment to be moved.");
		}
	}

	function moveInnerHtml(id, currentIndex, targetIndex) {
		var sourceElement = document.getElementById(id + '_' + currentIndex);
		var targetElement = document.getElementById(id + '_' + targetIndex);
		var tmpHTML = sourceElement.innerHTML;

		sourceElement.innerHTML = targetElement.innerHTML;

		targetElement.innerHTML = tmpHTML;
	}

	function checkNumComments() {
		var comments = document.getElementById('cmt_comments').getElementsByTagName('TR');
		var numComments = comments.length;
		var divMoveComments = document.getElementById('commentsMoveRow');

		if (numComments > 1) {
			divMoveComments.style.visibility = 'visible';
			divMoveComments.style.display = 'block';
		} else {
			divMoveComments.style.visibility = 'hidden';
			divMoveComments.style.display = 'none';
		}
	}

	function hideCommentsMoveRow() {
		var divMoveComments = document.getElementById('commentsMoveRow');
		divMoveComments.style.visibility = 'hidden';
		divMoveComments.style.display = 'none';
	}

	function checkDisplayOptions() {
		if (displayOptions == "N") {
			maxRows = myTable.rows.length;

			for (i = 0; i < maxRows; i++)
			{
				var printRow = document.getElementById("comment_print_" + i);
				var boldRow = document.getElementById("comment_bold_" + i);
				var placeRow = document.getElementById("comment_place_" + i);
				printRow.style.visibility = 'hidden';
				printRow.style.display = 'none';
				boldRow.style.visibility = 'hidden';
				boldRow.style.display = 'none';
				placeRow.style.visibility = 'hidden';
				placeRow.style.display = 'none';
			}

		}
	}
