/*
 * Copyright 2010 The gwtquery plugins team.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tsa.puridiom.client.requision;

import static com.google.gwt.query.client.GQuery.$;
import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.draggable.client.events.DragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStartEvent.DragStartEventHandler;
import gwtquery.plugins.droppable.client.DroppableOptions.AcceptFunction;
import gwtquery.plugins.droppable.client.events.DragAndDropContext;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellList;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellTable;
import gwtquery.plugins.droppable.client.gwt.DragAndDropColumn;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.tsa.puridiom.client.requision.PurchaseRequisionData.Category;
import com.tsa.puridiom.client.requision.PurchaseRequisionData.ContactInfo;

/**
 * This Class shows how to implement drag and drop in CellList.
 * 
 * @author Jigar Mistry
 */

@SuppressWarnings("deprecation")
public class PurchaseRequisionUI implements EntryPoint {
	final private PopupPanel popupPanel = new PopupPanel(true);

	private DateTimeFormat format = DateTimeFormat.getFormat("MM-dd-yyyy");

	private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00;(#,##0.00)");
	
	private static final int MAX_ORDER = 10;

	private int order = 5;

	/**
	 * The images used for this example.
	 */
	static interface Images extends ClientBundle {
		public Images INSTANCE = GWT.create(Images.class);
		ImageResource contact();
	}

	private Button okButton;
	
	/**
	 * The Cell used to render a {@link ContactInfo}. Code coming from the GWT
	 * showcase
	 * 
	 */
	private static class ContactCell extends AbstractCell<ContactInfo> {
		/**
		 * The html of the image used for contacts.
		 * 
		 */
		private final String imageHtml;

		public ContactCell(ImageResource image) {
			this.imageHtml = AbstractImagePrototype.create(image).getHTML();
		}

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context,
				ContactInfo value, SafeHtmlBuilder sb) {
			// Value can be null, so do a null check..
			if (value == null) {
				return;
			}

			if (value.getCategory().getDisplayName().equals("Others")) {
				sb
						.appendHtmlConstant("<table border='0' cellspacing='0' cellpadding='1' width='100%' class='browseRow'>");
				sb.appendHtmlConstant("<tr><td class=browseRow>");
				sb
						.appendHtmlConstant("<table border='0' class='browseRow' cellspacing='0' cellpadding='2' width='100%'><tr class='browseRow'>");
				sb
						.appendHtmlConstant("<td height='18px' class='browseRow' align='left' width='15%' valign='top'>");
				sb.appendEscaped(value.getTypes());
				sb
						.appendHtmlConstant("</td><td height='18px' class='browseRow' align='left' width='10%' valign='top' onMouseOver='showDetails("
								+ value.getId()
								+ ");' onMouseOut='hideDetails("
								+ value.getId() + ");'>");
				sb.appendEscaped(value.getRequitionNumber());
				sb
						.appendHtmlConstant("</td><td height='18px' class='browseRow' align='left' width='12%' valign='top'>");
				sb.appendEscaped(value.getDateAssigned().toString());
				sb
						.appendHtmlConstant("</td><td height='18px' class='browseRow' align='right' width='15%' valign='top'>");
				sb.appendEscaped(value.getTotal().toString());
				sb
						.appendHtmlConstant("</td><td height='18px' class='browseRow' align='left' width='15%' valign='top'>");
				sb.appendEscaped(value.getCurrentApprover());
				sb
						.appendHtmlConstant("</td><td height='18px' class='browseRow' align='left' width='15%' valign='top'>");
				sb.appendEscaped(value.getRequisitioner());
				sb.appendHtmlConstant("</td></tr></table>");
				sb
						.appendHtmlConstant("<div  id='details"
								+ value.getId()
								+ "' style='visibility:hidden;display:none;' class='browseRow'>");
				sb
						.appendHtmlConstant("	<table  border='0' cellspacing='0' cellpadding='0' class='browseRow' width='95%' align='right'>");
				sb.appendHtmlConstant("		<tr class=browseRow>");
				sb.appendHtmlConstant("			<td class=browseRow>");
				sb
						.appendHtmlConstant("				<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>");
				sb.appendHtmlConstant("					<tr>");
				sb
						.appendHtmlConstant("						<td colspan=4 height=18px class=browseRow width=40% valign=top>");
				sb.appendHtmlConstant("							<b>Supplier Code:</b>");
				sb
						.appendHtmlConstant("							&nbsp;"
								+ value.getSupplierCode());
				sb.appendHtmlConstant("						</td>");
				sb
						.appendHtmlConstant("						<td colspan=4 height=18px class=browseRow width=60% valign=top>");
				sb.appendHtmlConstant("							<b>Supplier Name:</b>");
				sb
						.appendHtmlConstant("							&nbsp;"
								+ value.getSupplierName());
				sb.appendHtmlConstant("						</td>");
				sb.appendHtmlConstant("					</tr>");
				sb.appendHtmlConstant("				</table>");
				sb.appendHtmlConstant("			</td>");
				sb.appendHtmlConstant("		</tr>");
				sb.appendHtmlConstant("		<tr class=browseRow>");
				sb.appendHtmlConstant("			<td class=browseRow>");
				sb
						.appendHtmlConstant("				<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>");
				sb.appendHtmlConstant("					<tr>");
				sb.appendHtmlConstant("					</tr>");
				sb.appendHtmlConstant("				</table>");
				sb.appendHtmlConstant("			</td>");
				sb.appendHtmlConstant("		</tr>");
				sb.appendHtmlConstant("		<tr class=browseRow>");
				sb.appendHtmlConstant("			<td class=browseRow>");
				sb
						.appendHtmlConstant("				<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>");
				sb.appendHtmlConstant("					<tr>");
				sb
						.appendHtmlConstant("						<td colspan=4 height=18px class=browseRow width=100% valign=top>");
				sb
						.appendHtmlConstant("							<b><font title='Click here to select a valid Suggested Buyer'>Suggested Buyer</font>:</b> ");
				sb.appendHtmlConstant("							&nbsp;"
						+ value.getSuggestedBuyer());
				sb.appendHtmlConstant("						</td>");
				sb.appendHtmlConstant("					</tr>");
				sb.appendHtmlConstant("				</table>");
				sb.appendHtmlConstant("			</td>");
				sb.appendHtmlConstant("		</tr>");
				sb.appendHtmlConstant("		<tr class=browseRow>");
				sb.appendHtmlConstant("			<td class=browseRow>");
				sb
						.appendHtmlConstant("				<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>");
				sb.appendHtmlConstant("					<tr> ");
				sb.appendHtmlConstant("					</tr>");
				sb.appendHtmlConstant("				</table>");
				sb.appendHtmlConstant("			</td>");
				sb.appendHtmlConstant("		</tr>");
				sb.appendHtmlConstant("		<tr class=browseRow>");
				sb.appendHtmlConstant("			<td class=browseRow>");
				sb
						.appendHtmlConstant("				<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>");
				sb.appendHtmlConstant("					<tr>");
				sb
						.appendHtmlConstant("						<td colspan=4 height=18px class=browseRow width=100% valign=top>");
				sb
						.appendHtmlConstant("							<b><font title='Click here to Enter a valid Purpose'>Purpose</font>:</b>");
				sb.appendHtmlConstant("							&nbsp;" + value.getPuropse());
				sb.appendHtmlConstant("						</td>");
				sb.appendHtmlConstant("					</tr>");
				sb.appendHtmlConstant("				</table>");
				sb.appendHtmlConstant("			</td>");
				sb.appendHtmlConstant("		</tr>");
				sb.appendHtmlConstant("	</table>");
				sb.appendHtmlConstant("</div>");
				sb.appendHtmlConstant("</td></tr></table>");
			} else {
				sb.appendHtmlConstant("<table>");

				// Add the contact image.
				/*
				 * sb.appendHtmlConstant("<tr><td rowspan='3'><div>");
				 * sb.appendHtmlConstant(imageHtml);
				 * sb.appendHtmlConstant("</div></td>");
				 */

				// Add the name and address.
				sb.appendHtmlConstant("<td style='font-size:95%;'>");
				sb.appendEscaped(value.toString());
				/*
				 * sb.appendHtmlConstant("</td></tr><tr><td>");
				 * sb.appendEscaped(value.getAddress());
				 */
				sb.appendHtmlConstant("</td></tr></table>");
			}
		}
	}

	/**
	 * Object handling the drop event.
	 * 
	 * @author Julien Dramaix (julien.dramaix@gmail.com)
	 * 
	 */
	private class DropHandler implements DropEventHandler {

		@SuppressWarnings("unchecked")
		public void onDrop(DropEvent event) {
			// retrieve the category linked to panel where the draggable was
			// dropped.
			DroppableWidget<ShowMorePagerPanel> droppabelWidget = (DroppableWidget<ShowMorePagerPanel>) event
					.getDroppableWidget();
			ShowMorePagerPanel dropPanel = droppabelWidget.getOriginalWidget();
			Category dropCategory = dropPanel.getCategory();

			// retrieve the ContactInfo associated with the draggable element
			ContactInfo draggedContact = event.getDraggableData();
			Category oldCategory = draggedContact.getCategory();

			if (oldCategory == dropCategory) {
				return;
			}

			// change the category of the contact that was being dragged and
			// prevent
			// the data source.
			draggedContact.setCategory(dropCategory);
			PurchaseRequisionData.get().moveContact(draggedContact, oldCategory);

			// contactForm.setContact(draggedContact);

		}

	}

	/*
	 * Form displaying info of the selected contact
	 */
	// private ContactInfoForm contactForm;
	public void onModuleLoad() {
		final Hyperlink widget = new Hyperlink("Add Basket", "Order");

		widget.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				if (order < MAX_ORDER) {
					order++;
					if (order == 6)
						RootPanel.get("order6")
								.add(createList(Category.ORDER6));
					else if (order == 7)
						RootPanel.get("order7")
								.add(createList(Category.ORDER7));
					else if (order == 8)
						RootPanel.get("order8")
								.add(createList(Category.ORDER8));
					else if (order == 9)
						RootPanel.get("order9")
								.add(createList(Category.ORDER9));
					else if (order == 10) {
						RootPanel.get("order10").add(
								createList(Category.ORDER10));
						widget.setVisible(Boolean.FALSE);
					}
				}
			}
		});
		// add the contact form
		// contactForm = new ContactInfoForm();
		// RootPanel.get("contactForm").add(contactForm);

		// add the 4 lists for the 4 different categories
		// RootPanel.get("cell").add(createList(Category.OTHERS));
		RootPanel.get("cell").add(createDragAndDropCellTable());
		RootPanel.get("link").add(widget);
		RootPanel.get("order1").add(createList(Category.ORDER1));
		RootPanel.get("order2").add(createList(Category.ORDER2));
		RootPanel.get("order3").add(createList(Category.ORDER3));
		RootPanel.get("order4").add(createList(Category.ORDER4));
		RootPanel.get("order5").add(createList(Category.ORDER5));
		RootPanel.get("button").add(getOkButton());
		
		createPopupMenu();
		popupPanel.hide();
	}

	private Button getOkButton() {
		okButton = new Button("Submit");
		okButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				List<ContactInfo> orderList1 = (List<ContactInfo>) PurchaseRequisionData
						.get().getDataProvider(Category.ORDER1).getList();
				;
				List<ContactInfo> orderList2 = (List<ContactInfo>) PurchaseRequisionData
						.get().getDataProvider(Category.ORDER2).getList();
				;
				List<ContactInfo> orderList3 = (List<ContactInfo>) PurchaseRequisionData
						.get().getDataProvider(Category.ORDER3).getList();
				;
				List<ContactInfo> orderList4 = (List<ContactInfo>) PurchaseRequisionData
						.get().getDataProvider(Category.ORDER4).getList();
				;
				List<ContactInfo> orderList5 = (List<ContactInfo>) PurchaseRequisionData
						.get().getDataProvider(Category.ORDER5).getList();
				;

				for (ContactInfo contactInfo : orderList1) {
					Window
							.alert("Order 1: "
									+ contactInfo.getRequitionNumber());
				}

				for (ContactInfo contactInfo : orderList2) {
					Window
							.alert("Order 2: "
									+ contactInfo.getRequitionNumber());
				}

				for (ContactInfo contactInfo : orderList3) {
					Window
							.alert("Order 3: "
									+ contactInfo.getRequitionNumber());
				}

				for (ContactInfo contactInfo : orderList4) {
					Window
							.alert("Order 4: "
									+ contactInfo.getRequitionNumber());
				}

				for (ContactInfo contactInfo : orderList5) {
					Window
							.alert("Order 5: "
									+ contactInfo.getRequitionNumber());
				}
			}
		});

		return okButton;
	}

	/**
	 * This method create the CellTable for the contacts
	 */
	private DroppableWidget<ShowMorePagerPanel> createDragAndDropCellTable() {
		// Create a DragAndDropCellTable.
		DragAndDropCellTable<ContactInfo> cellTable = new DragAndDropCellTable<ContactInfo>(
				PurchaseRequisionData.ContactInfo.KEY_PROVIDER);
		// Create a Pager to control the table.
		/*
		 * SimplePager.Resources pagerResources = GWT
		 * .create(SimplePager.Resources.class); SimplePager pager = new
		 * SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		 */
		ShowMorePagerPanel pager = new ShowMorePagerPanel(Category.OTHERS);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final MultiSelectionModel<ContactInfo> selectionModel = new MultiSelectionModel<ContactInfo>(
				PurchaseRequisionData.ContactInfo.KEY_PROVIDER);
		cellTable.setSelectionModel(selectionModel);
		cellTable.setWidth("100%");
		
		// Initialize the columns.
		initTableColumns(cellTable, selectionModel);

		// Add the CellList to the adapter in the database.
		PurchaseRequisionData.get().addDataDisplay(cellTable, Category.OTHERS);

		// fill the helper when the drag operation start

		cellTable.addDragStartHandler(new DragStartEventHandler() {

			public void onDragStart(DragStartEvent event) {
				//ContactInfo contact = event.getDraggableData();
				Element helper = event.getHelper();
				helper.setInnerHTML((new ContactCell(Resource.INSTANCE
						.contact())).imageHtml);
			}
		});

		DroppableWidget<ShowMorePagerPanel> droppabelPanel = new DroppableWidget<ShowMorePagerPanel>(pager);
		// setup the drop operation
		droppabelPanel.addDropHandler(new DropHandler());
		// use an AcceptFunction to accept only draggable coming from an other panel
		droppabelPanel.setAccept(new AcceptFunction() {

			public boolean acceptDrop(DragAndDropContext ctx) {
				// retrieve the dragging ContactInfo
				ContactInfo draggedContact = ctx.getDraggableData();
				Category dragCategory = draggedContact.getCategory();
				// accept only contact coming from an other panel.
				return dragCategory != Category.OTHERS;
			}
		});
		return droppabelPanel;
	}

	private void initTableColumns(DragAndDropCellTable<ContactInfo> cellTable,
			final SelectionModel<ContactInfo> selectionModel) {
		String[] colNames = getColumns();

		for (final String columnName : colNames) {
			DragAndDropColumn<ContactInfo, String> column = new DragAndDropColumn<ContactInfo, String>(
					new TextCell()) {
				@Override
				public String getValue(ContactInfo object) {
					if (columnName.equals("Type"))
						return object.getTypes();
					else if (columnName.equals("Req. #"))
						return object.getRequitionNumber();
					else if (columnName.equals("Date Assigned"))
						return format.format(object.getDateAssigned());
					else if (columnName.equals("Total"))
						return "$" + numberFormat.format(object.getTotal());
					else if (columnName.equals("Current Approver"))
						return object.getCurrentApprover();
					else if (columnName.equals("Requisitioner"))
						return object.getRequisitioner();
					else if (columnName.equals("Supplier Code"))
						return object.getSupplierCode();
					else if (columnName.equals("Supplier Name"))
						return object.getSupplierName();
					else if (columnName.equals("Suggested Buyer"))
						return object.getSuggestedBuyer();
					else
						return "";
				}
				
				@Override
				public void render(Context context, ContactInfo object,
						SafeHtmlBuilder sb) {
					if (columnName.equals("Type"))
						sb.appendHtmlConstant(object.getTypes());
					else if (columnName.equals("Req. #"))
						sb.appendHtmlConstant(object.getRequitionNumber());
					else if (columnName.equals("Date Assigned"))
						sb.appendHtmlConstant(format.format(object.getDateAssigned()));
					else if (columnName.equals("Total"))
						sb.appendHtmlConstant("$" + numberFormat.format(object.getTotal()));
					else if (columnName.equals("Current Approver"))
						sb.appendHtmlConstant(object.getCurrentApprover());
					else if (columnName.equals("Requisitioner"))
						sb.appendHtmlConstant(object.getRequisitioner());
					else if (columnName.equals("Supplier Code"))
						sb.appendHtmlConstant(object.getSupplierCode());
					else if (columnName.equals("Supplier Name"))
						sb.appendHtmlConstant(object.getSupplierName());
					else if (columnName.equals("Suggested Buyer"))
						sb.appendHtmlConstant(object.getSuggestedBuyer());
				}
			};
			
			ListHandler<ContactInfo> columnSortHandler = new ListHandler<ContactInfo>(PurchaseRequisionData.get().getDataProvider(Category.OTHERS).getList());
			columnSortHandler.setComparator(column, new Comparator<ContactInfo>() {
				public int compare(ContactInfo o1, ContactInfo o2) {
					if (o1 == o2) {
				    	return 0;
					}
					
				    // Compare the name columns.
				    if (o1 != null && columnName.equals("Type")) {
				    	return (o2 != null) ? o1.getTypes().toLowerCase().compareTo(o2.getTypes().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Req. #")) {
				    	return (o2 != null) ? o1.getRequitionNumber().toLowerCase().compareTo(o2.getRequitionNumber().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Date Assigned")) {
				    	return (o2 != null) ? o1.getDateAssigned().compareTo(o2.getDateAssigned()) : 1;
				    } else if (o1 != null && columnName.equals("Total")) {
				    	return (o2 != null) ? o1.getTotal().compareTo(o2.getTotal()) : 1;
				    } else if (o1 != null && columnName.equals("Current Approver")) {
				    	return (o2 != null) ? o1.getCurrentApprover().toLowerCase().compareTo(o2.getCurrentApprover().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Requisitioner")) {
				    	return (o2 != null) ? o1.getRequisitioner().toLowerCase().compareTo(o2.getRequisitioner().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Supplier Code")) {
				    	return (o2 != null) ? o1.getSupplierCode().toLowerCase().compareTo(o2.getSupplierCode().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Supplier Name")) {
				    	return (o2 != null) ? o1.getSupplierName().toLowerCase().compareTo(o2.getSupplierName().toLowerCase()) : 1;
				    } else if (o1 != null && columnName.equals("Suggested Buyer")) {
				    	return (o2 != null) ? o1.getSuggestedBuyer().toLowerCase().compareTo(o2.getSuggestedBuyer().toLowerCase()) : 1;
				    }
				    return -1;
				}
			});

			cellTable.addColumnSortHandler(columnSortHandler);
			
			if (columnName.equals("Type"))
				cellTable.setColumnWidth(column, "18%");
			else if (columnName.equals("Req. #"))
				cellTable.setColumnWidth(column, "21%");
			else if (columnName.equals("Date Assigned"))
				cellTable.setColumnWidth(column, "15%");
			else if (columnName.equals("Total"))
				cellTable.setColumnWidth(column, "10%");
			else if (columnName.equals("Current Approver"))
				cellTable.setColumnWidth(column, "18%");
			else if (columnName.equals("Requisitioner"))
				cellTable.setColumnWidth(column, "18%");
			
			column.setSortable(true);
			cellTable.addColumn(column, columnName);
			
			if (columnName.equals("Type"))
				column.setCellDraggableOnly();
			else if (columnName.equals("Total"))
				column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			initDragOperation(column);
		}
	}

	private void initDragOperation(DragAndDropColumn<?, ?> column) {
		// retrieve draggableOptions on the column
		DraggableOptions draggableOptions = column.getDraggableOptions();
		// use template to construct the helper. 
		// The content of the div will be set after
		draggableOptions.setHelper($(Templates.INSTANCE.outerHelper().asString()));
		// opacity of the helper
		draggableOptions.setOpacity((float) 0.8);
		// cursor to use during the drag operation
		draggableOptions.setCursor(Cursor.MOVE);
		// set the revert option
		draggableOptions.setRevert(RevertOption.ON_INVALID_DROP);
		// prevents dragging when user click on the category drop-down list
		draggableOptions.setCancel("select");
	}

	public native static String[] getColumns() /*-{
		return $wnd.getColumns();
	}-*/;

	static interface Templates extends SafeHtmlTemplates {
		Templates INSTANCE = GWT.create(Templates.class);

		@Template("<div id='dragHelper' style='border:1px solid black; background-color:#ffffff; color:black; width:150px;'></div>")
		SafeHtml outerHelper();
	}

	private DraggableOptions createDraggableOptions() {
		DraggableOptions options = new DraggableOptions();
		// use a clone of the original cell as drag helper
		options.setHelper(HelperType.CLONE);
		// set the opacity of the drag helper
		options.setOpacity((float) 0.9);
		// append the drag helper to the body element
		options.setAppendTo("body");
		return options;

	}

	/**
	 * Code coming from GWT showcase.
	 * 
	 * We just use a {@link DragAndDropCellList} instead of a {@link CellList}
	 * and make the pager panel droppable.
	 * 
	 * @param contactForm
	 * 
	 * @return
	 */
	private DroppableWidget<ShowMorePagerPanel> createList(
			final Category category) {

		// Create a ConcactCel
		ContactCell contactCell = new ContactCell(Images.INSTANCE.contact());

		// Create a drag and drop cell list
		DragAndDropCellList<ContactInfo> cellList = new DragAndDropCellList<ContactInfo>(
				contactCell, Resource.INSTANCE,
				PurchaseRequisionData.ContactInfo.KEY_PROVIDER) {
			@Override
			public void sinkEvents(int eventBitsToAdd) {
				super.sinkEvents(Event.ONMOUSEUP | Event.ONCLICK | Event.ONMOUSEDOWN);
			}
			
			@Override
			protected void onBrowserEvent2(Event event) {
				switch (DOM.eventGetType(event)) {
					case Event.ONMOUSEUP:
						if (DOM.eventGetButton(event) == Event.BUTTON_RIGHT) {
							int x = DOM.eventGetClientX(event);
							int y = DOM.eventGetClientY(event);
							popupPanel.setPopupPosition(x, y);
							popupPanel.show();
					    } else
					    	super.onBrowserEvent2(event);
						
						break;
					
					default:
						super.onBrowserEvent2(event);
						break;
				}
			}
		};

		Resource.INSTANCE.cellListStyle().ensureInjected();

		// The cell of this cell list are only draggable
		cellList.setCellDraggableOnly();
		// setup the drag operation
		cellList.setDraggableOptions(createDraggableOptions());

		cellList.setPageSize(30);
		cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);

		final SingleSelectionModel<ContactInfo> selectionModel = new SingleSelectionModel<ContactInfo>(
				PurchaseRequisionData.ContactInfo.KEY_PROVIDER);
		
		cellList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					
					public void onSelectionChange(SelectionChangeEvent event) {
						// contactForm.setContact(selectionModel.getSelectedObject());
					}
				});

		cellList.addDragStartHandler(new DragStartEventHandler() {
			@Override
			public void onDragStart(DragStartEvent event) {
				//ContactInfo contact = event.getDraggableData();
				Element helper = event.getHelper();
				//SafeHtmlBuilder sb = new SafeHtmlBuilder();
				// reuse the contact cell to render the inner html of the drag
				// helper.
				// new ContactCell(Resource.INSTANCE.contact()).render(null,
				// contact, sb);
				helper.setInnerHTML((new ContactCell(Resource.INSTANCE
						.contact())).imageHtml);
			}
		});

		PurchaseRequisionData.get().addDataDisplay(cellList, category);

		ShowMorePagerPanel pagerPanel = new ShowMorePagerPanel(category);
		pagerPanel.setDisplay(cellList);

		// make the pager panel droppable.
		DroppableWidget<ShowMorePagerPanel> droppabelPanel = new DroppableWidget<ShowMorePagerPanel>(
				pagerPanel);
		// setup the drop operation
		droppabelPanel.setDroppableHoverClass("orange-border");
		droppabelPanel.setActiveClass("yellow-border");
		droppabelPanel.addDropHandler(new DropHandler());
		// use an AcceptFunction to accept only draggable coming from an other
		// panel
		droppabelPanel.setAccept(new AcceptFunction() {

			public boolean acceptDrop(DragAndDropContext ctx) {
				// retrieve the dragging ContactInfo
				ContactInfo draggedContact = ctx.getDraggableData();
				Category dragCategory = draggedContact.getCategory();
				// accept only contact coming from an other panel.
				return dragCategory != category;
			}

		});

		return droppabelPanel;
	}
	
	private void createPopupMenu() {
		  MenuBar popupMenuBar = new MenuBar(true);
		  MenuItem createSolicitation = new MenuItem("Create Solicitation", true, createSolicitationCommand);
		  MenuItem createPurchaseOrder = new MenuItem("Create Purchase Order", true, createPurchaseOrderCommand);
		  MenuItem printWorksheet = new MenuItem("Print Worksheet", true, printWorksheetCommand);
		  MenuItem emptyBasket = new MenuItem("Empty Basket", true, emptyBasketCommand);
		 
		  popupPanel.setStyleName("popup");
		  createSolicitation.addStyleName("popup-item");
		  createPurchaseOrder.addStyleName("popup-item");
		  printWorksheet.addStyleName("popup-item");
		  emptyBasket.addStyleName("popup-item");
		 
		  popupMenuBar.addItem(createSolicitation);
		  popupMenuBar.addItem(createPurchaseOrder);
		  popupMenuBar.addItem(printWorksheet);
		  popupMenuBar.addItem(emptyBasket);
		 
		  popupMenuBar.setVisible(true);
		  popupPanel.add(popupMenuBar);
	}
	
	Command createSolicitationCommand = new Command() {
		public void execute() {
			popupPanel.hide();
		    Window.alert("Create Solicitation.");
		}
	};
	
	Command createPurchaseOrderCommand = new Command() {
		public void execute() {
			popupPanel.hide();
		    Window.alert("Create Purchase Order.");
		}
	};
	
	Command printWorksheetCommand = new Command() {
		public void execute() {
			popupPanel.hide();
		    Window.alert("Print Worksheet.");
		}
	};
	
	Command emptyBasketCommand = new Command() {
		public void execute() {
			popupPanel.hide();
		    Window.alert("Empty Basket.");
		}
	};
}
