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
package com.tsa.puridiom.client.msrrequest;

import static com.google.gwt.query.client.GQuery.$;
import gwtquery.plugins.draggable.client.DraggableOptions;
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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.tsa.puridiom.client.msrrequest.MSRRequestData.Category;
import com.tsa.puridiom.client.msrrequest.MSRRequestData.ContactInfo;

/**
 * This Class shows how to implement drag and drop in CellList.
 *
 * @author Jigar Mistry
 */

public class MSRRequestUI implements EntryPoint {
	final FormPanel form = new FormPanel();

	private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00;(#,##0.00)");

	private ListBox sourceType1 = null;
	private ListBox sourceType2 = null;
	private ListBox sourceType3 = null;
	private ListBox sourceType4 = null;
	private ListBox sourceType5 = null;
	private ListBox sourceType6 = null;

	/**
	 * The images used for this example.
	 */
	static interface Images extends ClientBundle {
		public Images INSTANCE = GWT.create(Images.class);
		ImageResource contact();
	}

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

			if (!value.getCategory().getDisplayName().equals("Others")) {
				sb.appendHtmlConstant("<table>");

				// Add the contact image.

				/*sb.appendHtmlConstant("<tr><td rowspan='3'><div>");
				sb.appendHtmlConstant(imageHtml);
				sb.appendHtmlConstant("</div></td>");*/


				// Add the name and address.
				sb.appendHtmlConstant("<tr><td style='font-size:95%;'>");
				sb.appendEscaped(value.getItemNumber() + " " + value.getSupplierId());
				sb.appendHtmlConstant("</td></tr><td><tr>");
				sb.appendEscaped(value.getDescription());
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
			DroppableWidget<ShowMorePagerPanel> droppabelWidget = (DroppableWidget<ShowMorePagerPanel>) event.getDroppableWidget();
			ShowMorePagerPanel dropPanel = droppabelWidget.getOriginalWidget();
			Category dropCategory = dropPanel.getCategory();

			// retrieve the ContactInfo associated with the draggable element
			ContactInfo draggedContact = event.getDraggableData();
			Category oldCategory = draggedContact.getCategory();

			if (oldCategory == dropCategory) {
				return;
			}
			if(!dropCategory.equals(Category.OTHERS)) {
				List<ContactInfo> orderList = (List<ContactInfo>) MSRRequestData.get().getDataProvider(dropCategory).getList();
				if(orderList != null && orderList.size() > 0) {
					if(draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals("")){
						Window.alert("Vinimaya item in bucket must have a blanket order number defined in catalog.");
						return;
					} else if(!orderList.get(0).getProcLevel().equals(draggedContact.getProcLevel())) {
						Window.alert("All items in bucket must have the same line Procurement Level.");
						return;
					} else if((orderList.get(0).getItemSource() != null && orderList.get(0).getItemSource().equals("INV") && orderList.get(0).getItemLocation() != null && !orderList.get(0).getItemLocation().equals("")) && ((draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("")) || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV")))) {
						Window.alert("SRR Inventory bucket, all items must be an inventory item.");
						return;
					} else if((orderList.get(0).getItemLocation() == null || orderList.get(0).getItemLocation().equals("")) && !orderList.get(0).getSupplierId().equals(draggedContact.getSupplierId())) {
						Window.alert("All items in a bucket must have the same vendor id with the exception of SRR Inventory Bucket.");
						return;
					} else if(!orderList.get(0).getBlanketOrder().equals(draggedContact.getBlanketOrder())) {
						Window.alert("All items in bucket must have the same blanket order number defined in catalog.");
						return;
					} else if(orderList.get(0).getItemSource().equals("CAT") && orderList.get(0).getCatalog().startsWith("SRNS") && !orderList.get(0).getBlanketOrder().equals("") &&
							(!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals(""))) {
						Window.alert("All items in bucket must come from the same catalog");
						return;
					} else if((!orderList.get(0).getItemSource().equals("CAT") || !orderList.get(0).getCatalog().startsWith("SRNS") || orderList.get(0).getBlanketOrder().equals("")) &&
							(draggedContact.getItemSource().equals("CAT") && draggedContact.getCatalog().startsWith("SRNS") && !draggedContact.getBlanketOrder().equals(""))) {
						Window.alert("All items in bucket must come from the same catalog");
						return;
					} else if(orderList.get(0).getItemSource().equals("XML") && orderList.get(0).getCatalog().equals("VINIMAYA") && !orderList.get(0).getBlanketOrder().equals("") &&
							(!draggedContact.getItemSource().equals("XML") || !draggedContact.getCatalog().equals("VINIMAYA") || draggedContact.getBlanketOrder().equals(""))) {
						Window.alert("All items in bucket must come from the same catalog");
						return;
					} else if((!orderList.get(0).getItemSource().equals("XML") || !orderList.get(0).getCatalog().equals("VINIMAYA") || orderList.get(0).getBlanketOrder().equals("")) &&
							(draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && !draggedContact.getBlanketOrder().equals(""))) {
						Window.alert("All items in bucket must come from the same catalog");
						return;
					}
				} else {
					int index = -1;

					if(draggedContact.getItemLocation() != null && !draggedContact.getItemLocation().equals("") && draggedContact.getItemSource() != null && draggedContact.getItemSource().equals("INV"))
						index = 1;
					if(draggedContact.getSupplierId() != null && "VINIMAYA".equals(draggedContact.getCatalog()))
						index = 4;
					/*if(!"NA".equals(draggedContact.getRadNuclear()))
						index = 3;
					if(draggedContact.getIcPoLine() != null && draggedContact.getIcPoLine().equals("0"))
						index = 6;*/
					if(draggedContact.getItemSource().equals("CAT") && draggedContact.getCatalog().startsWith("SRNS") && !draggedContact.getBlanketOrder().equals(""))
						index = 0;

					boolean flag = false;
					boolean catalogFlag = false;
					boolean vinimayaFlag = false;

					if(Category.ORDER1.equals(dropCategory)) {
						if(sourceType1.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType1.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType1.setSelectedIndex(index == -1 ? sourceType1.getSelectedIndex() : index);
					} else if(Category.ORDER2.equals(dropCategory)) {
						if(sourceType2.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType2.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType2.setSelectedIndex(index == -1 ? sourceType2.getSelectedIndex() : index);
					} else if(Category.ORDER3.equals(dropCategory)) {
						if(sourceType3.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType3.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType3.setSelectedIndex(index == -1 ? sourceType3.getSelectedIndex() : index);
					} else if(Category.ORDER4.equals(dropCategory)) {
						if(sourceType4.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType4.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType4.setSelectedIndex(index == -1 ? sourceType4.getSelectedIndex() : index);
					} else if(Category.ORDER5.equals(dropCategory)) {
						if(sourceType5.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType5.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType5.setSelectedIndex(index == -1 ? sourceType5.getSelectedIndex() : index);
					} else if(Category.ORDER6.equals(dropCategory)) {
						if(sourceType6.getSelectedIndex() == 1 && (draggedContact.getItemLocation() == null || draggedContact.getItemLocation().equals("") || (draggedContact.getItemSource() == null || !draggedContact.getItemSource().equals("INV"))))
							flag = true;
						else if(sourceType6.getSelectedIndex() == 0 && (!draggedContact.getItemSource().equals("CAT") || !draggedContact.getCatalog().startsWith("SRNS") || draggedContact.getBlanketOrder().equals("")))
							catalogFlag = true;
						else if((sourceType1.getSelectedIndex() == 4 || index == 4) && (draggedContact.getItemSource().equals("XML") && draggedContact.getCatalog().equals("VINIMAYA") && draggedContact.getBlanketOrder().equals(""))){
							vinimayaFlag = true;
							sourceType1.setSelectedIndex(index);
						}
						else
							sourceType6.setSelectedIndex(index == -1 ? sourceType6.getSelectedIndex() : index);
					}

					if(flag) {
						Window.alert("SRR Inventory bucket, all items must be an inventory item");
						return;
					}
					if(catalogFlag) {
						Window.alert("STORES Catalog/Spare parts bucket, all items in bucket must come from SRNS catalog");
						return;
					}
					if(vinimayaFlag) {
						Window.alert("SRNS Market Place bucket, item must have a blanket order number defined in catalog");
						return;
					}
				}
			}

			// change the category of the contact that was being dragged and
			// prevent
			// the data source.
			draggedContact.setCategory(dropCategory);
			MSRRequestData.get().moveContact(draggedContact, oldCategory);

			// contactForm.setContact(draggedContact);
		}
	}

	/*
	 * Form displaying info of the selected contact
	 */
	// private ContactInfoForm contactForm;
	public void onModuleLoad() {
		// add the contact form
		// contactForm = new ContactInfoForm();
		// RootPanel.get("contactForm").add(contactForm);

		// add the 4 lists for the 4 different categories
		// RootPanel.get("cell").add(createList(Category.OTHERS));
		RootPanel.get("mainLabel").add(new Label("Lines to be sourced:"));
		RootPanel.get("cell").add(createDragAndDropCellTable());

		if(RootPanel.get("label1") != null && RootPanel.get("label2") != null && RootPanel.get("label3") != null && RootPanel.get("label4") != null && RootPanel.get("label5") != null && RootPanel.get("label6") != null) {
			RootPanel.get("label1").add(new Label("Source Type 1"));
			RootPanel.get("listBox1").add(sourceType1 = getListBox(Category.ORDER1));
			RootPanel.get("order1").add(createList(Category.ORDER1));
			RootPanel.get("label2").add(new Label("Source Type 2"));
			RootPanel.get("listBox2").add(sourceType2 = getListBox(Category.ORDER2));
			RootPanel.get("order2").add(createList(Category.ORDER2));
			RootPanel.get("label3").add(new Label("Source Type 3"));
			RootPanel.get("listBox3").add(sourceType3 = getListBox(Category.ORDER3));
			RootPanel.get("order3").add(createList(Category.ORDER3));
			RootPanel.get("label4").add(new Label("Source Type 4"));
			RootPanel.get("listBox4").add(sourceType4 = getListBox(Category.ORDER4));
			RootPanel.get("order4").add(createList(Category.ORDER4));
			RootPanel.get("label5").add(new Label("Source Type 5"));
			RootPanel.get("listBox5").add(sourceType5 = getListBox(Category.ORDER5));
			RootPanel.get("order5").add(createList(Category.ORDER5));
			RootPanel.get("label6").add(new Label("Source Type 6"));
			RootPanel.get("listBox6").add(sourceType6 = getListBox(Category.ORDER6));
			RootPanel.get("order6").add(createList(Category.ORDER6));
			addClickEventToSubmitButton();
		}
	}

	ListBox getListBox(final Category category) {
	    final ListBox widget = new ListBox();
	    widget.addStyleName("demo-ListBox");
	    widget.addItem("STORES Catalog/Spare parts", "P");
	    widget.addItem("SRR Inventory", "I");
	    widget.addItem("Internal Catalogs/Strategic Agreements", "R");
	    widget.addItem("Direct Purchase", "D");
	    widget.addItem("SRNS Market Place", "S");
	    /*widget.addItem("Credit Card", "C");
	    widget.addItem("Change Request", "X");*/

	    String sourceType = getSourceType();
	    int selectedIndex = ("P".equals(sourceType) ? 0 : "I".equals(sourceType) ? 1 : "R".equals(sourceType) ? 2 : "D".equals(sourceType) ? 3 : 4);

	    widget.setSelectedIndex(selectedIndex);
	    widget.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setPrevSelectedIndex(widget.getSelectedIndex());
			}
	    });

	    widget.addChangeHandler(new ChangeHandler() {
	    	public void onChange(ChangeEvent event) {
	    		int selectedIndex = widget.getSelectedIndex();

	    		List<ContactInfo> orderList = (List<ContactInfo>) MSRRequestData.get().getDataProvider(category).getList();
				if(orderList != null && orderList.size() > 0) {
					if((orderList.get(0).getProcLevel().equals("1") || orderList.get(0).getProcLevel().equals("2")) && (orderList.get(0).getMarkTag().equals("TAG") || orderList.get(0).getMarkTag().equals("TAG HR")) && !"NA".equals(orderList.get(0).getRadNuclear()) && orderList.get(0).getHeader().startsWith("On Site") && selectedIndex == 5) {
						Window.alert("Source type can't be Credit Card");
						selectedIndex = getPrevSelectedIndex();
					}
					if(!"NA".equals(orderList.get(0).getRadNuclear()))
						selectedIndex = 3;
					if(orderList.get(0).getSupplierId() != null && "VINIMAYA".equals(orderList.get(0).getCatalog()))
						selectedIndex = 4;
					if(orderList.get(0).getItemSource() != null && orderList.get(0).getItemSource().equals("INV") && orderList.get(0).getItemLocation() != null && !orderList.get(0).getItemLocation().equals(""))
						selectedIndex = 1;
					if(orderList.get(0).getItemSource().equals("CAT") && orderList.get(0).getCatalog().startsWith("SRNS") && !orderList.get(0).getBlanketOrder().equals(""))
						selectedIndex = 0;
					if(((orderList.get(0).getItemSource() != null && !orderList.get(0).getItemSource().equals("INV")) || (orderList.get(0).getItemLocation() != null && orderList.get(0).getItemLocation().equals(""))) && selectedIndex == 1) {
						Window.alert("SRR Inventory bucket, all items must be an inventory item");
						selectedIndex = getPrevSelectedIndex();
					}
				}

				widget.setSelectedIndex(selectedIndex);
	    	}
	    });

	    return widget;
	}

	public native Integer getPrevSelectedIndex() /*-{
		return $wnd.prevSelectedIndex;
	}-*/;

	public native void setPrevSelectedIndex(Integer selectedIndex) /*-{
		$wnd.prevSelectedIndex = selectedIndex;
	}-*/;

	private void addClickEventToSubmitButton() {
		Anchor a = Anchor.wrap(Document.get().getElementById("submitButton"));
		a.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				List<ContactInfo> orderList1 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER1).getList();
				List<ContactInfo> orderList2 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER2).getList();
				List<ContactInfo> orderList3 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER3).getList();
				List<ContactInfo> orderList4 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER4).getList();
				List<ContactInfo> orderList5 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER5).getList();
				List<ContactInfo> orderList6 = (List<ContactInfo>) MSRRequestData.get().getDataProvider(Category.ORDER6).getList();

				RootPanel.get("hidden").add(new Hidden("source_type_cmb1", sourceType1.getValue(sourceType1.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList1) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket1", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket1", contactInfo.getIcReqLine()));
				}

				RootPanel.get("hidden").add(new Hidden("source_type_cmb2", sourceType2.getValue(sourceType2.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList2) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket2", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket2", contactInfo.getIcReqLine()));
				}

				RootPanel.get("hidden").add(new Hidden("source_type_cmb3", sourceType3.getValue(sourceType3.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList3) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket3", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket3", contactInfo.getIcReqLine()));
				}

				RootPanel.get("hidden").add(new Hidden("source_type_cmb4", sourceType4.getValue(sourceType4.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList4) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket4", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket4", contactInfo.getIcReqLine()));
				}
				
				RootPanel.get("hidden").add(new Hidden("source_type_cmb5", sourceType5.getValue(sourceType5.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList5) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket5", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket5", contactInfo.getIcReqLine()));
				}
				
				RootPanel.get("hidden").add(new Hidden("source_type_cmb6", sourceType6.getValue(sourceType6.getSelectedIndex())));

				for (ContactInfo contactInfo : orderList6) {
					RootPanel.get("hidden").add(new Hidden("source_ic_history_bucket6", contactInfo.getIcHistory()));
					RootPanel.get("hidden").add(new Hidden("source_ic_req_line_bucket6", contactInfo.getIcReqLine()));
				}

				submitForm();
			}
		});
	}

	public native static void submitForm() /*-{
		return $wnd.submitForm();
	}-*/;

	/**
	 * This method create the CellTable for the contacts
	 */
	private DroppableWidget<ShowMorePagerPanel> createDragAndDropCellTable() {
		// Create a DragAndDropCellTable.
		DragAndDropCellTable<ContactInfo> cellTable = new DragAndDropCellTable<ContactInfo>(
				MSRRequestData.ContactInfo.KEY_PROVIDER) {
			@Override
			protected Object getValueKey(ContactInfo value) {
				setTitle(value.getDescription());
				return super.getValueKey(value);
			}
		};
		// Create a Pager to control the table.
		/*
		 * SimplePager.Resources pagerResources = GWT
		 * .create(SimplePager.Resources.class); SimplePager pager = new
		 * SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		 */

		ShowMorePagerPanel pager = new ShowMorePagerPanel(Category.OTHERS, getColumns().length > 7 ? 1200 : 900);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final MultiSelectionModel<ContactInfo> selectionModel = new MultiSelectionModel<ContactInfo>(
				MSRRequestData.ContactInfo.KEY_PROVIDER);
		cellTable.setPageSize(MSRRequestData.getItemNumbers() != null ? MSRRequestData.getItemNumbers().length : 0);
		cellTable.setSelectionModel(selectionModel);
		cellTable.setWidth("100%");

		// Initialize the columns.
		initTableColumns(cellTable, selectionModel);

		// Add the CellList to the adapter in the database.
		MSRRequestData.get().addDataDisplay(cellTable, Category.OTHERS);

		//final DroppableWidget<ShowMorePagerPanel> droppabelPanel = new DroppableWidget<ShowMorePagerPanel>(pager);
		final DroppableWidget<ShowMorePagerPanel> droppabelPanel = new DroppableWidget<ShowMorePagerPanel>(pager);
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

		// fill the helper when the drag operation start

		cellTable.addDragStartHandler(new DragStartEventHandler() {

			public void onDragStart(DragStartEvent event) {
				//ContactInfo contact = event.getDraggableData();
				Element helper = event.getHelper();
				helper.setInnerHTML((new ContactCell(Resource.INSTANCE
						.contact())).imageHtml);
			}
		});

		return droppabelPanel;
	}

	private void initTableColumns(DragAndDropCellTable<ContactInfo> cellTable,
			final SelectionModel<ContactInfo> selectionModel) {
		String[] colNames = getColumns();
		int index = 0;

		TextColumn<ContactInfo> numColumn = new TextColumn<ContactInfo>() {
		    @Override
		    public String getValue(ContactInfo object) {
		        return object.getLineNumber();
		    }
		};
		cellTable.addColumn(numColumn, "Line");
		cellTable.setColumnWidth(numColumn, "2%");

		for (final String columnName : colNames) {
			final int jIndex = index;

			DragAndDropColumn<ContactInfo, String> column = new DragAndDropColumn<ContactInfo, String>(
					new TextCell()) {
				@Override
				public String getValue(ContactInfo object) {
					String desc = object.getDescription();

					if (columnName.equals("Item #/Desc."))
						return object.getItemNumber() + " - " +  (desc.length() > 30 ? desc.substring(0, 30) + "..." : desc);
					/*else if (columnName.equals("Supplier Id"))
						return object.getSupplierId();*/
					else if (columnName.equals("Catalog/Proc Level"))
						return object.getCatalog() + "<br />" + object.getProcLevel();
					/*else if (columnName.equals("Commodity/Ind Class"))
						return object.getCommodity() + "<br />" + object.getIndClass();*/
					else if (columnName.equals("Quantity"))
						return numberFormat.format(object.getQuantity());
					else if (columnName.equals("U/M"))
						return object.getUorm();
					else if (columnName.equals("Unit Cost"))
						return "$" + numberFormat.format(object.getUnitCost());
					else if (columnName.equals("Ext. Cost"))
						return "$" + numberFormat.format(object.getExtCost());
					else if (columnName.equals("Requisition #"))
						return object.getReqNumber();
					else if (jIndex == 7 && columnName.equals("Status"))
						return object.getReqStatus();
					else if (columnName.equals("Kit/Inventory Location"))
						return object.getReqLocation();
					else if (columnName.equals("PO #"))
						return object.getPoNumber();
					else if (jIndex == 10 && columnName.equals("Status"))
						return object.getPoStatus();
					else if (columnName.equals("Receipt #"))
						return object.getRecieptNumber();
					else if (jIndex == 12 && columnName.equals("Status"))
						return object.getRecieptStatus();
					else if (columnName.equals("Invoice #"))
						return object.getInvoiceNumber();
					else if (jIndex == 14 && columnName.equals("Status"))
						return object.getInvoiceStatus();
					else
						return "";
				}

				@Override
				public void render(Context context, ContactInfo object,
						SafeHtmlBuilder sb) {
					String desc = object.getDescription();

					if (columnName.equals("Item #/Desc."))
						sb.appendHtmlConstant(object.getItemNumber() + " - " + (desc.length() > 30 ? desc.substring(0, 30) + "..." : desc));
					/*else if (columnName.equals("Supplier Id"))
						sb.appendHtmlConstant(object.getSupplierId());*/
					else if (columnName.equals("Catalog/Proc Level"))
						sb.appendHtmlConstant(object.getCatalog() + "<br />" + object.getProcLevel());
					/*else if (columnName.equals("Commodity/Ind Class"))
						sb.appendHtmlConstant(object.getCommodity() + "<br />" + object.getIndClass());*/
					else if (columnName.equals("Quantity"))
						sb.appendHtmlConstant(numberFormat.format(object.getQuantity()));
					else if (columnName.equals("U/M"))
						sb.appendHtmlConstant(object.getUorm());
					else if (columnName.equals("Unit Cost"))
						sb.appendHtmlConstant("$" + numberFormat.format(object.getUnitCost()));
					else if (columnName.equals("Ext. Cost"))
						sb.appendHtmlConstant("$" + numberFormat.format(object.getExtCost()));
					else if (columnName.equals("Requisition #"))
						sb.appendHtmlConstant(object.getReqNumber());
					else if (columnName.equals("Kit/Inventory Location")) {
						if(object.isPopup()){
							sb.appendHtmlConstant(object.getReqLocation());
						} else {
							int lne = (int) Double.parseDouble(object.getLineNumber()) ;
							String line = NumberFormat.getFormat("0000").format(lne);
							sb.appendHtmlConstant("<a href=\"javascript: showInvBinLocation('" + object.getMsrNumber() +"."+line+"', '" + object.getReqLocation() +"'); void(0);\">" + object.getReqLocation() + "</a>");
						}
					} else if (jIndex == 7 && columnName.equals("Status"))
						sb.appendHtmlConstant(object.getReqStatus());
					else if (columnName.equals("PO #"))
						sb.appendHtmlConstant(object.getPoNumber());
					else if (jIndex == 10 && columnName.equals("Status"))
						sb.appendHtmlConstant(object.getPoStatus());
					else if (columnName.equals("Receipt #"))
						sb.appendHtmlConstant(object.getRecieptNumber());
					else if (jIndex == 12 && columnName.equals("Status"))
						sb.appendHtmlConstant(object.getRecieptStatus());
					else if (columnName.equals("Invoice #"))
						sb.appendHtmlConstant(object.getInvoiceNumber());
					else if (jIndex == 14 && columnName.equals("Status"))
						sb.appendHtmlConstant(object.getInvoiceStatus());
				}
			};

			ListHandler<ContactInfo> columnSortHandler = new ListHandler<ContactInfo>(MSRRequestData.get().getDataProvider(Category.OTHERS).getList());
			columnSortHandler.setComparator(column, new Comparator<ContactInfo>() {
				public int compare(ContactInfo o1, ContactInfo o2) {
					if (o1 == o2) {
				    	return 0;
					}

				    // Compare the name columns.
				    if (o1 != null && columnName.equals("Item #/Desc.")) {
				    	return (o2 != null) ? o1.getItemNumber().toLowerCase().compareTo(o2.getItemNumber().toLowerCase()) : 1;
				    } /*else if (o1 != null && columnName.equals("Supplier Id")) {
				    	return (o2 != null) ? o1.getSupplierId().toLowerCase().compareTo(o2.getSupplierId().toLowerCase()) : 1;
				    } */else if (o1 != null && columnName.equals("Catalog/Proc Level")) {
				    	return (o2 != null) ? o1.getCatalog().compareTo(o2.getCatalog()) : 1;
				    } /*else if (o1 != null && columnName.equals("Commodity/Ind Class")) {
				    	return (o2 != null) ? o1.getIndClass().toLowerCase().compareTo(o2.getIndClass().toLowerCase()) : 1;
				    } */
				    return -1;
				}
			});

			cellTable.addColumnSortHandler(columnSortHandler);

			boolean flag = getColumns().length < 8 ? true : false;
			if (columnName.equals("Item #/Desc."))
				cellTable.setColumnWidth(column, flag ? "30%" : "17%");
			/*else if (columnName.equals("Supplier Id"))
				cellTable.setColumnWidth(column, flag ? "10%" : "4%");*/
			else if (columnName.equals("Catalog/Proc Level"))
				cellTable.setColumnWidth(column, flag ? "15%" : "8%");
			/*else if (columnName.equals("Commodity/Ind Class"))
				cellTable.setColumnWidth(column, flag ? "15%" : "8%");*/
			else if (columnName.equals("Quantity"))
				cellTable.setColumnWidth(column, flag ? "10%" : "4%");
			else if (columnName.equals("U/M"))
				cellTable.setColumnWidth(column, flag ? "10%" : "4%");
			else if (columnName.equals("Unit Cost"))
				cellTable.setColumnWidth(column, flag ? "10%" : "4%");
			else if (columnName.equals("Ext. Cost"))
				cellTable.setColumnWidth(column, flag ? "10%" : "4%");
			else if (columnName.equals("Requisition #"))
				cellTable.setColumnWidth(column, "8%");
			else if (columnName.equals("PO #"))
				cellTable.setColumnWidth(column, "8%");
			else if (columnName.equals("Receipt #"))
				cellTable.setColumnWidth(column, "8%");
			else if (columnName.equals("Invoice #"))
				cellTable.setColumnWidth(column, "8%");
			else if (columnName.equals("Status"))
				cellTable.setColumnWidth(column, "4%");

			if (columnName.equals("Item #/Desc.") || columnName.equals("Supplier Id") || columnName.equals("Catalog/Proc Level") || columnName.equals("Commodity/Ind Class"))
				column.setSortable(true);

			cellTable.addColumn(column, columnName);

			if (columnName.equals("Item #/Desc.")) {
				column.setCellDraggableOnly();
				// cellTable.getColumnSortList().push(column);
			} else if (columnName.equals("Unit Cost") || columnName.equals("Ext. Cost"))
				column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			else if (columnName.equals("Catalog/Proc Level") || columnName.equals("Commodity/Ind Class") || columnName.equals("Quantity") || columnName.equals("U/M") || columnName.equals("Requisition #") || columnName.equals("PO #") || columnName.equals("Status") || columnName.equals("Receipt #") || columnName.equals("Invoice #") || columnName.equals("Kit/Inventory Location")) {
				column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			}
			initDragOperation(column);
			index++;
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

		draggableOptions.setAppendTo("body");
	}

	public native static String[] getColumns() /*-{
		return $wnd.getColumns();
	}-*/;

	public native static String getSourceType() /*-{
		return $wnd.getSourceType();
	}-*/;

	static interface Templates extends SafeHtmlTemplates {
		Templates INSTANCE = GWT.create(Templates.class);

		@Template("<div id='dragHelper' style='border:1px solid black; background-color:#ffffff; color:black; width:150px;'></div>")
		SafeHtml outerHelper();
	}

	private DraggableOptions createDraggableOptions() {
		DraggableOptions options = new DraggableOptions();
		// use a clone of the original cell as drag helper
		options.setHelper($(Templates.INSTANCE.outerHelper().asString()));
		// opacity of the helper
		options.setOpacity((float) 0.8);
		// cursor to use during the drag operation
		options.setCursor(Cursor.MOVE);
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
				MSRRequestData.ContactInfo.KEY_PROVIDER);

		Resource.INSTANCE.cellListStyle().ensureInjected();

		// The cell of this cell list are only draggable
		cellList.setCellDraggableOnly();
		// setup the drag operation
		cellList.setDraggableOptions(createDraggableOptions());

		cellList.setPageSize(30);
		cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);

		final SingleSelectionModel<ContactInfo> selectionModel = new SingleSelectionModel<ContactInfo>(
				MSRRequestData.ContactInfo.KEY_PROVIDER);

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

		MSRRequestData.get().addDataDisplay(cellList, category);

		ShowMorePagerPanel pagerPanel = new ShowMorePagerPanel(category, getColumns().length > 7 ? 1200 : 900);
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
}