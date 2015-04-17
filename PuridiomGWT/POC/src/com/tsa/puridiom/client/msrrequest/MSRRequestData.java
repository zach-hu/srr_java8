/*
 * Copyright 2010 Google Inc.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

/**
 * 
 * @author Jigar Mistry
 */
public class MSRRequestData {
    
	/**
	 * A category. 
	 */
	public static enum Category {
		ORDER1("Source Type 1"), ORDER2("Source Type 2"), ORDER3("Source Type 3"), ORDER4("Source Type 4"), ORDER5("Source Type 5"), ORDER6("Source Type 6"), OTHERS("Others");

		private final String displayName;

		private Category(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	/**
	 * Information about a contact.
	 */
	public static class ContactInfo implements Comparable<ContactInfo> {
		/**
		 * The key provider that provides the unique ID of a contact.
		 */
		public static final ProvidesKey<ContactInfo> KEY_PROVIDER = new ProvidesKey<ContactInfo>() {
			public Object getKey(ContactInfo item) {
				return item == null ? null : item.getId();
			}
		};

		private static int nextId = 0;

		private String itemNumber;
		private String supplierId;
		private String catalog;
		private String procLevel;
		private String commodity;
		private String indClass;
		private Double quantity;
		private String uorm;
		private Double unitCost;
		private Double extCost;
		private String icHistory;
		private String icReqLine;
		private String description;
		private String itemLocation;
		private String itemSource;
		private String blanketOrder; 
		private String reqNumber;
		private String reqStatus;
		private String reqLocation;
		private String poNumber;
		private String poStatus;
		private String recieptNumber;
		private String recieptStatus;
		private String invoiceNumber;
		private String invoiceStatus;
		private String lineNumber;
		private String radNuclear;
		private String markTag;
		private String traceability;
		private String header;
		private String icPoLine;
		private String msrNumber;
		private String popup;
		
		private Category category;
		private final int id;
		
		public String getCommodity() {
			return commodity;
		}

		public void setCommodity(String commodity) {
			this.commodity = commodity;
		}

		public String getIndClass() {
			return indClass;
		}

		public void setIndClass(String indClass) {
			this.indClass = indClass;
		}

		public Double getQuantity() {
			return quantity;
		}

		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}

		public Double getUnitCost() {
			return unitCost;
		}

		public String getUorm() {
			return uorm;
		}

		public void setUorm(String uorm) {
			this.uorm = uorm;
		}

		public void setUnitCost(Double unitCost) {
			this.unitCost = unitCost;
		}

		public Double getExtCost() {
			return extCost;
		}

		public void setExtCost(Double extCost) {
			this.extCost = extCost;
		}

		public void setSupplierId(String supplierId) {
			this.supplierId = supplierId;
		}

		public ContactInfo(Category category) {
			this.id = nextId;
			nextId++;
			setCategory(category);
		}

		public int compareTo(ContactInfo o) {
			return (o == null || o.supplierId == null) ? -1
					: -o.supplierId.compareTo(supplierId);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof ContactInfo) {
				return id == ((ContactInfo) o).id;
			}
			return false;
		}

		public String getProcLevel() {
			return procLevel;
		}

		/**
		 * @return the contact's address
		 */
		public String getItemNumber() {
			return itemNumber;
		}

		/**
		 * @return the category of the conteact
		 */
		public Category getCategory() {
			return category;
		}

		/**
		 * @return the contact's full name
		 */
		public final String getSupplierId() {
			return supplierId;
		}

		public String getIcHistory() {
			return icHistory;
		}

		public void setIcHistory(String icHistory) {
			this.icHistory = icHistory;
		}

		public String getIcReqLine() {
			return icReqLine;
		}

		public void setIcReqLine(String icReqLine) {
			this.icReqLine = icReqLine;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getItemLocation() {
			return itemLocation;
		}

		public void setItemLocation(String itemLocation) {
			this.itemLocation = itemLocation;
		}

		public String getItemSource() {
			return itemSource;
		}

		public void setItemSource(String itemSource) {
			this.itemSource = itemSource;
		}

		public String getBlanketOrder() {
			return blanketOrder;
		}

		public void setBlanketOrder(String blanketOrder) {
			this.blanketOrder = blanketOrder;
		}

		public String getReqNumber() {
			return reqNumber;
		}

		public void setReqNumber(String reqNumber) {
			this.reqNumber = reqNumber;
		}

		public String getReqStatus() {
			return reqStatus;
		}

		public void setReqStatus(String reqStatus) {
			this.reqStatus = reqStatus;
		}

		public String getReqLocation() {
			return reqLocation;
		}

		public void setReqLocation(String reqLocation) {
			this.reqLocation = reqLocation;
		}

		public String getPoNumber() {
			return poNumber;
		}

		public void setPoNumber(String poNumber) {
			this.poNumber = poNumber;
		}

		public String getPoStatus() {
			return poStatus;
		}

		public void setPoStatus(String poStatus) {
			this.poStatus = poStatus;
		}

		public String getRecieptNumber() {
			return recieptNumber;
		}

		public void setRecieptNumber(String recieptNumber) {
			this.recieptNumber = recieptNumber;
		}

		public String getRecieptStatus() {
			return recieptStatus;
		}

		public void setRecieptStatus(String recieptStatus) {
			this.recieptStatus = recieptStatus;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public String getInvoiceStatus() {
			return invoiceStatus;
		}

		public void setInvoiceStatus(String invoiceStatus) {
			this.invoiceStatus = invoiceStatus;
		}

		public String getLineNumber() {
			return lineNumber;
		}

		public void setLineNumber(String lineNumber) {
			this.lineNumber = lineNumber;
		}

		public String getRadNuclear() {
			return radNuclear;
		}

		public void setRadNuclear(String radNuclear) {
			this.radNuclear = radNuclear;
		}

		public String getMarkTag() {
			return markTag;
		}

		public void setMarkTag(String markTag) {
			this.markTag = markTag;
		}

		public String getTraceability() {
			return traceability;
		}

		public void setTraceability(String traceability) {
			this.traceability = traceability;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public String getIcPoLine() {
			return icPoLine;
		}

		public void setIcPoLine(String icPoLine) {
			this.icPoLine = icPoLine;
		}

		/**
		 * @return the unique ID of the contact
		 */
		public int getId() {
			return this.id;
		}

		/**
		 * @return the contact's lastName
		 */
		public String getCatalog() {
			return catalog;
		}

		@Override
		public int hashCode() {
			return id;
		}

		/**
		 * Set the contact's address.
		 * 
		 * @param address
		 *            the address
		 */
		public void setItemNumber(String itemNumber) {
			this.itemNumber = itemNumber;
		}

		/**
		 * Set the contact's birthday.
		 * 
		 * @param birthday
		 *            the birthday
		 */
		public void setRequitionNumber(String requitionNumber) {
			this.supplierId = requitionNumber;
		}

		/**
		 * Set the contact's category.
		 * 
		 * @param category
		 *            the category to set
		 */
		public void setCategory(Category category) {
			assert category != null : "category cannot be null";
			this.category = category;
		}

		public void setCatalog(String catalog) {
			this.catalog = catalog;
		}

		public void setProcLevel(String procLevel) {
			this.procLevel = procLevel;
		}

		public String getMsrNumber() {
			return msrNumber;
		}

		public void setMsrNumber(String msrNumber) {
			this.msrNumber = msrNumber;
		}

		public void setPopup(String popup) {
			this.popup = popup;
		}

		public boolean isPopup() {
			return this.popup.equals("Y");
		}

	}

	/**
	 * The constants used in this Content Widget.
	 */
	static interface DatabaseConstants extends Constants {
		String[] contactDatabaseCategories();
	}

	private static String[] itemNumbers = null;
	private static String[] supplierIds = null;
	private static String[] quantities = null;
	private static String[] catalogs = null;
	private static String[] procLevels = null;
	private static String[] commodities = null;
	private static String[] indClasses = null;
	private static String[] uorms = null;
	private static String[] unitCosts = null;
	private static String[] extCosts = null;
	private static String[] icHistories = null;
	private static String[] icReqLines = null;
	private static String[] descriptions = null;
	private static String[] itemLocations = null;
	private static String[] itemSources = null;
	private static String[] blanketOrders = null;
	private static String[] reqNumbers = null;
	private static String[] reqStatuses = null;
	private static String[] reqLocations = null;
	private static String[] poNumbers = null;
	private static String[] poStatuses = null;
	private static String[] recieptNumbers = null;
	private static String[] recieptStatuses = null;
	private static String[] invoiceNumbers = null;
	private static String[] invoiceStatuses = null;
	private static String[] lineNumbers = null;
	private static String[] radNuclears = null;
	private static String[] markTags = null;
	private static String[] traceabilities = null;
	private static String[] headers = null;
	private static String[] icPoLines = null;
	private static String msrNumber = null;
	private static String popup = null;
	
	/**
	 * The singleton instance of the database.
	 */
	private static MSRRequestData instance;

	/**
	 * Get the singleton instance of the contact database.
	 * 
	 * @return the singleton instance
	 */
	public static MSRRequestData get() {
		if (instance == null) {
			instance = new MSRRequestData();
		}
		return instance;
	}

	private final Category[] categories;

	/**
	 * The provider that holds the list of contacts in the database.
	 */
	private Map<Category, ListDataProvider<ContactInfo>> dataProviderByCategory = new HashMap<Category, ListDataProvider<ContactInfo>>();

	/**
	 * Construct a new contact database.
	 */
	private MSRRequestData() {
		// Initialize the categories.
		categories = Category.values();
		// init data providers list
		for (Category c : categories) {
			dataProviderByCategory.put(c, new ListDataProvider<ContactInfo>());
		}

		itemNumbers = getItemNumbers();
		supplierIds = getSupplierIds();
		quantities = getQuantities();
		catalogs = getCatalogs();
		procLevels = getProcLevels();
		commodities = getCommodities();
		indClasses = getIndClasses();
		uorms = getUorms();
		unitCosts = getUnitCosts();
		extCosts = getExtCosts();
		icHistories = getIcHistories();
		icReqLines = getIcReqLines();
		descriptions = getDescriptions();
		itemLocations = getItemLocations();
		itemSources = getItemSources();
		blanketOrders = getBlanketOrders();
		reqNumbers = getReqNumber();
		reqStatuses = getReqStatus();
		reqLocations = getReqLocation();
		poNumbers = getPoNumber();
		poStatuses = getPoStatus();
		recieptNumbers = getRecNumber();
		recieptStatuses = getRecStatus();
		invoiceNumbers = getInvNumber();
		invoiceStatuses = getInvStatus();
		lineNumbers = getLineNumbers();
		radNuclears = getRadNuclears();
		markTags = getMarkTags(); 
		traceabilities = getTraceabilities();  
		headers = getHeaders();
		icPoLines = getIcPoLines();
		msrNumber = getMsrNumber();
		popup = isPopup();
		// Generate initial data.
		generateContacts(itemNumbers.length);
	}

	public native static String[] getItemNumbers() /*-{
		return $wnd.getItemNumbers();
	}-*/;

	public native static String[] getSupplierIds() /*-{
		return $wnd.getSupplierIds();
	}-*/;

	public native static String[] getQuantities() /*-{
		return $wnd.getQuantities();
	}-*/;

	public native static String[] getCatalogs() /*-{
		return $wnd.getCatalogs();
	}-*/;

	public native static String[] getProcLevels() /*-{
		return $wnd.getProcLevels();
	}-*/;

	public native static String[] getCommodities() /*-{
		return $wnd.getCommodities();
	}-*/;

	public native static String[] getIndClasses() /*-{
		return $wnd.getIndClasses();
	}-*/;

	public native static String[] getUorms() /*-{
	return $wnd.getUorms();
	}-*/;
	
	public native static String[] getUnitCosts() /*-{
		return $wnd.getUnitCosts();
	}-*/;

	public native static String[] getExtCosts() /*-{
		return $wnd.getExtCosts();
	}-*/;
	
	public native static String[] getIcHistories() /*-{
		return $wnd.getIcHistories();
	}-*/;
	
	public native static String[] getIcReqLines() /*-{
		return $wnd.getIcReqLines();
	}-*/;
	
	public native static String[] getDescriptions() /*-{
		return $wnd.getDescriptions();
	}-*/;

	public native static String[] getItemLocations() /*-{
		return $wnd.getItemLocations();
	}-*/;
	
	public native static String[] getItemSources() /*-{
		return $wnd.getItemSources();
	}-*/;
	
	public native static String[] getBlanketOrders() /*-{
		return $wnd.getBlanketOrders();
	}-*/;
	
	public native static String[] getReqNumber() /*-{
		return $wnd.getReqNumber();
	}-*/;
	
	public native static String[] getReqStatus() /*-{
		return $wnd.getReqStatus();
	}-*/;
	
	public native static String[] getReqLocation() /*-{
		return $wnd.getReqLocation();
	}-*/;

	public native static String[] getPoNumber() /*-{
		return $wnd.getPoNumber();
	}-*/;
	
	public native static String[] getPoStatus() /*-{
		return $wnd.getPoStatus();
	}-*/;
	
	public native static String[] getRecNumber() /*-{
		return $wnd.getRecNumber();
	}-*/;
	
	public native static String[] getRecStatus() /*-{
		return $wnd.getRecStatus();
	}-*/;
	
	public native static String[] getInvNumber() /*-{
		return $wnd.getInvNumber();
	}-*/;
	
	public native static String[] getInvStatus() /*-{
		return $wnd.getInvStatus();
	}-*/;
	
	public native static String[] getLineNumbers() /*-{
		return $wnd.getLineNumbers();
	}-*/;
	
	public native static String[] getRadNuclears() /*-{
		return $wnd.getRadNuclears();
	}-*/;
	
	public native static String[] getMarkTags() /*-{
		return $wnd.getMarkTags();
	}-*/;
	
	public native static String[] getTraceabilities() /*-{
		return $wnd.getTraceabilities();
	}-*/;
	
	public native static String[] getHeaders() /*-{
		return $wnd.getHeaders();
	}-*/;
	
	public native static String[] getIcPoLines() /*-{
		return $wnd.getIcPoLines();
	}-*/;
	
	public native static String getMsrNumber() /*-{
		return $wnd.getMsrNumber();
	}-*/;

	public native static String isPopup() /*-{
		return $wnd.isPopup();
	}-*/;

	/**
	 * Add a new contact.
	 * 
	 * @param contact
	 *            the contact to add.
	 */
	public void addContact(ContactInfo contact) {
		List<ContactInfo> contacts = getDataProvider(contact.getCategory())
				.getList();
		// Remove the contact first so we don't add a duplicate.
		contacts.remove(contact);
		contacts.add(contact);
	}

	/**
	 * Add a display to the database. The current range of interest of the
	 * display will be populated with data.
	 * 
	 * @param display
	 *            a {@Link HasData}.
	 */
	public void addDataDisplay(HasData<ContactInfo> display,
			Category categorySelected) {
		getDataProvider(categorySelected).addDataDisplay(display);
	}

	/**
	 * Generate the specified number of contacts and add them to the data
	 * provider.
	 * 
	 * @param count
	 *            the number of contacts to generate.
	 */
	public void generateContacts(int count) {
		for (int i = 0; i < count; i++) {
			addContact(createContactInfo(i));
		}
	}

	/**
	 * Add a new contact.
	 * 
	 * @param contact
	 *            the contact to add.
	 */
	public void moveContact(ContactInfo contact, Category oldCategory) {
		List<ContactInfo> previousList = getDataProvider(oldCategory).getList();
		List<ContactInfo> newList = getDataProvider(contact.getCategory())
				.getList();
		previousList.remove(contact);
		newList.add(contact);
	}

	/**
	 * Get the categories in the database.
	 * 
	 * @return the categories in the database
	 */
	public Category[] queryCategories() {
		return categories;
	}

	/**
	 * Query all contacts for the specified category.
	 * 
	 * @param category
	 *            the category
	 * @return the list of contacts in the category
	 */
	public List<ContactInfo> queryContactsByCategory(Category category) {
		return new ArrayList<ContactInfo>(getDataProvider(category).getList());
	}

	/**
	 * Refresh displays by category.
	 */
	public void refreshDisplays(Category c) {
		getDataProvider(c).refresh();
	}

	/**
	 * Create a new random {@link ContactInfo}.
	 * 
	 * @return the new {@link ContactInfo}.
	 */
	private ContactInfo createContactInfo(int i) {
		// set all created contact in OTHERS category
		ContactInfo contact = new ContactInfo(Category.OTHERS);
		contact.setCatalog(catalogs[i]);
		contact.setProcLevel(procLevels[i]);
		contact.setRequitionNumber(supplierIds[i]);
		contact.setQuantity(NumberFormat.getDecimalFormat().parse(quantities[i]));
		contact.setItemNumber(itemNumbers[i]);
		contact.setCommodity(commodities[i]);
		contact.setIndClass(indClasses[i]);
		contact.setUorm(uorms[i]);
		contact.setUnitCost(NumberFormat.getDecimalFormat().parse(unitCosts[i]));
		contact.setExtCost(NumberFormat.getDecimalFormat().parse(extCosts[i]));
		contact.setIcHistory(icHistories[i]);
		contact.setIcReqLine(icReqLines[i]);
		contact.setDescription(descriptions[i]);
		contact.setItemLocation(itemLocations[i]);
		contact.setItemSource(itemSources[i]);
		contact.setBlanketOrder(blanketOrders[i]);
		contact.setReqNumber(reqNumbers[i]);
		contact.setReqStatus(reqStatuses[i]);
		contact.setReqLocation(reqLocations[i]);
		contact.setPoNumber(poNumbers[i]);
		contact.setPoStatus(poStatuses[i]);
		contact.setRecieptNumber(recieptNumbers[i]);
		contact.setRecieptStatus(recieptStatuses[i]);
		contact.setInvoiceNumber(invoiceNumbers[i]);
		contact.setInvoiceStatus(invoiceStatuses[i]);
		contact.setLineNumber(lineNumbers[i]);
		contact.setRadNuclear(radNuclears[i]);
		contact.setInvoiceStatus(invoiceStatuses[i]);
		contact.setTraceability(traceabilities[i]);
		contact.setRadNuclear(radNuclears[i]);
		contact.setIcPoLine(icPoLines[i]);
		contact.setMsrNumber(msrNumber);
		contact.setPopup(popup);
		
		return contact;
	}

	public ListDataProvider<ContactInfo> getDataProvider(Category c) {
		return dataProviderByCategory.get(c);
	}
}