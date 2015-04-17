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
package com.tsa.puridiom.client.requision;

import java.util.ArrayList;
import java.util.Date;
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
public class PurchaseRequisionData {
    
	/**
	 * A contact category. Family", "Friends", "Business", "Others
	 */
	public static enum Category {
		ORDER1("Order #1"), ORDER2("Order #2"), ORDER3("Order #3"), ORDER4("Order #4"), ORDER5(
				"Order #5"), ORDER6("Order #6"), ORDER7("Order #7"), ORDER8("Order #8"), ORDER9(
				"Order #9"), ORDER10("Order #10"), OTHERS("Others");

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

		private String types;
		private String requitionNumber;
		private Date dateAssigned;
		private Double total;
		private String currentApprover;
		private String requisitioner;
		private String supplierCode;

		public String getSupplierCode() {
			return supplierCode;
		}

		public void setSupplierCode(String supplierCode) {
			this.supplierCode = supplierCode;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}

		public String getSuggestedBuyer() {
			return suggestedBuyer;
		}

		public void setSuggestedBuyer(String suggestedBuyer) {
			this.suggestedBuyer = suggestedBuyer;
		}

		public String getPuropse() {
			return puropse;
		}

		public void setPuropse(String puropse) {
			this.puropse = puropse;
		}

		private String supplierName;
		private String suggestedBuyer;
		private String puropse;

		private Category category;
		private final int id;

		public ContactInfo(Category category) {
			this.id = nextId;
			nextId++;
			setCategory(category);
		}

		public int compareTo(ContactInfo o) {
			return (o == null || o.requitionNumber == null) ? -1
					: -o.requitionNumber.compareTo(requitionNumber);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof ContactInfo) {
				return id == ((ContactInfo) o).id;
			}
			return false;
		}

		public String getRequisitioner() {
			return requisitioner;
		}

		/**
		 * @return the contact's address
		 */
		public String getTypes() {
			return types;
		}

		/**
		 * @return the contact's birthday
		 */
		public Date getDateAssigned() {
			return dateAssigned;
		}

		/**
		 * @return the category of the conteact
		 */
		public Category getCategory() {
			return category;
		}

		/**
		 * @return the contact's firstName
		 */
		public Double getTotal() {
			return total;
		}

		/**
		 * @return the contact's full name
		 */
		public final String getRequitionNumber() {
			return requitionNumber;
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
		public String getCurrentApprover() {
			return currentApprover;
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
		public void setTypes(String types) {
			this.types = types;
		}

		/**
		 * Set the contact's birthday.
		 * 
		 * @param birthday
		 *            the birthday
		 */
		public void setRequitionNumber(String requitionNumber) {
			this.requitionNumber = requitionNumber;
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

		/**
		 * Set the contact's first name.
		 * 
		 * @param firstName
		 *            the firstName to set
		 */
		public void setDateAssigned(Date dateAssigned) {
			this.dateAssigned = dateAssigned;
		}

		/**
		 * Set the contact's last name.
		 * 
		 * @param lastName
		 *            the lastName to set
		 */
		public void setTotal(Double total) {
			this.total = total;
		}

		public void setCurrentApprover(String currentApprover) {
			this.currentApprover = currentApprover;
		}

		public void setRequisitioner(String requisitioner) {
			this.requisitioner = requisitioner;
		}

		@Override
		public String toString() {
			return getRequitionNumber() + " " + getRequisitioner();
		}
	}

	/**
	 * The constants used in this Content Widget.
	 */
	static interface DatabaseConstants extends Constants {
		@DefaultStringArrayValue(value = { "Family", "Friends", "Business",
				"Others" })
		String[] contactDatabaseCategories();
	}

	private static String[] types = null;
	private static String[] requitionNumber = null;
	private static String[] dateAssigned = null;
	private static String[] total = null;
	private static String[] currentApprover = null;
	private static String[] requisitioner = null;
	private static String[] supplierCode = null;
	private static String[] supplierName = null;
	private static String[] suggestedBuyer = null;
	private static String[] purpose = null;

	/**
	 * The singleton instance of the database.
	 */
	private static PurchaseRequisionData instance;

	/**
	 * Get the singleton instance of the contact database.
	 * 
	 * @return the singleton instance
	 */
	public static PurchaseRequisionData get() {
		if (instance == null) {
			instance = new PurchaseRequisionData();
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
	private PurchaseRequisionData() {
		// Initialize the categories.
		categories = Category.values();
		// init data providers list
		for (Category c : categories) {
			dataProviderByCategory.put(c, new ListDataProvider<ContactInfo>());
		}

		types = getTypes();
		requitionNumber = getRequitionNumber();
		dateAssigned = getDateAssigned();
		total = getTotal();
		currentApprover = getCurrentApprover();
		requisitioner = getRequisitioner();
		supplierCode = getSupplierCode();
		supplierName = getSupplierName();
		suggestedBuyer = getSuggestedBuyer();
		purpose = getPurpose();

		// Generate initial data.
		generateContacts(types.length);
	}

	public native static String[] getTypes() /*-{
		return $wnd.getTypes();
	}-*/;

	public native static String[] getRequitionNumber() /*-{
		return $wnd.getRequitionNumber();
	}-*/;

	public native static String[] getDateAssigned() /*-{
		return $wnd.getDateAssigned();
	}-*/;

	public native static String[] getTotal() /*-{
		return $wnd.getTotal();
	}-*/;

	public native static String[] getCurrentApprover() /*-{
		return $wnd.getCurrentApprover();
	}-*/;

	public native static String[] getRequisitioner() /*-{
		return $wnd.getRequisitioner();
	}-*/;

	public native static String[] getSupplierCode() /*-{
		return $wnd.getSupplierCode();
	}-*/;

	public native static String[] getSupplierName() /*-{
		return $wnd.getSupplierName();
	}-*/;

	public native static String[] getSuggestedBuyer() /*-{
		return $wnd.getSuggestedBuyer();
	}-*/;

	public native static String[] getPurpose() /*-{
		return $wnd.getPurpose();
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
	@SuppressWarnings("deprecation")
	private ContactInfo createContactInfo(int i) {
		// set all created contact in OTHERS category
		ContactInfo contact = new ContactInfo(Category.OTHERS);
		contact.setCurrentApprover(currentApprover[i]);
		contact.setDateAssigned(new Date(Integer.parseInt(dateAssigned[i].substring(6)) - 1900, Integer.parseInt(dateAssigned[i].substring(0, 2)), Integer.parseInt(dateAssigned[i].substring(3, 5))));
		contact.setRequisitioner(requisitioner[i]);
		contact.setRequitionNumber(requitionNumber[i]);
		contact.setTotal(NumberFormat.getDecimalFormat().parse(total[i]));
		contact.setTypes(types[i]);
		contact.setSupplierCode(supplierCode[i]);
		contact.setSupplierName(supplierName[i]);
		contact.setSuggestedBuyer(suggestedBuyer[i]);
		contact.setPuropse(purpose[i]);
		
		return contact;
	}

	public ListDataProvider<ContactInfo> getDataProvider(Category c) {
		return dataProviderByCategory.get(c);
	}
}