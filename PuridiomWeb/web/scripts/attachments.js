	var myTable;
	var totalRows = 0;
	var inEditMode = false;
	var validFileTypes;

	function thisLoad() {
		f_StartIt();
		setDisplay();
	}

	function setDisplay() {
		if (totalRows <= 0)
		{
			//var noAttachments = document.getElementById("noAttachmentsMsg");
			//noAttachments.style.visibility = "visible";
			//noAttachments.style.display = "block";
		}
	}

	function deleteMe(row) {
		var docIc = "";
		if (totalRows > 1) {
			docIc = frm.docIc[row].value;
		} else {
			docIc = frm.docIc.value;
		}
		frm.DocAttachment_delete.value='TRUE';
		var newInputField = "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'>";
		setHiddenFields(newInputField);
		if(frm.formtype.value == "INV"){
			doSubmit(currentPage, 'InvItemRetrieveById;DocAttachmentDelete;DocAttachmentRetrieveByLine');
		}else{
			doSubmit(currentPage, 'DocAttachmentDelete;DocAttachmentRetrieveByLine');
		}
		
	}

	function editAttachment(row) {
		var docIc = "";
		var docTitle = "";
		if (totalRows > 1) {
			docIc = frm.docIc[row].value;
			docTitle = frm.docTitle[row].value;
		} else {
			docIc = frm.docIc.value;
			docTitle = frm.docTitle.value;
		}
		frm.DocAttachment_edit_docIc.value=docIc;
		frm.DocAttachment_edit_docTitle.value=docTitle;
		//var newInputField = "<input type='hidden' name='DocAttachment_edit_docIc' value='" + docIc + "'>";
		//alert(newInputField);
		//setHiddenFields(newInputField);
		//alert(docIc);
		//alert(docTitle);
		doSubmit(editNewPage, 'DoNothing');

	}

	function setPrint(row) {
		if (totalRows > 1) {
			if (frm.cboxPrint[row].checked) {
				frm.docPrint[row].value = "Y";
			} else {
				frm.docPrint[row].value = "N";
			}
		} else {
			if (frm.cboxPrint.checked) {
				frm.docPrint.value = "Y";
			} else {
				frm.docPrint.value = "N";
			}
		}
	}

	function setPost(row) {
		if (totalRows > 1) {
			if (frm.cboxPost[row].checked) {
				frm.docPost[row].value = "Y";
			} else {
				frm.docPost[row].value = "N";
			}
		} else {
			if (frm.cboxPost.checked) {
				frm.docPost.value = "Y";
			} else {
				frm.docPost.value = "N";
			}
		}
	}

	function openDocument(row) {
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		openAttachment(filename);
	}

	function saveAttachments() {
		if (frm.DocAttachment_delete.value == 'TRUE') { return true ; }
		var newInputFields = "";
		var handlers = "DocAttachmentUpdateByHeader;" + returnHandler;

		if (totalRows > 1) {
		    frm.hasDocs.value = "YES" ;
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;
				var docPrint = frm.docPrint[i].value;
				var docPost = "N";

				if (frm.docPost != null && frm.docPost != undefined) {
					docPost = frm.docPost[i].value;
				}

				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + docTitle + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + docPrint + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + docPost + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type='hidden' name='DocAttachment_docIc' value='" + frm.docIc.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + frm.docTitle.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + frm.docPrint.value + "'>";
			if (frm.docPost != null && frm.docPost != undefined) {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + frm.docPost.value + "'>";
			} else {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='N'>";
			}
		} else {
		}

		setHiddenFields(newInputFields);
		doSubmit(returnPage, handlers);
	}

	function addStandardAttachment() {
		frm.browseName.value = "stdattachment";
		doSubmit('/browse/browse_std_attachment.jsp', 'DocAttachmentUpdateByHeader;BrowseRetrieve');
	}