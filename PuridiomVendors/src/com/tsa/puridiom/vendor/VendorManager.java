package com.tsa.puridiom.vendor;

import com.tsa.puridiom.entity.*;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Creation date: May 2005
 * @author: Kelli Knisely
 */
public class VendorManager {
    private static VendorManager INSTANCE = new VendorManager();
    private HashMap organizations = new HashMap();

    private VendorManager() {
        super();
    }
    /**
     * @return com.tsa.puridiom.vendor.VendorManager
     */
    public static VendorManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VendorManager();
        }
        return INSTANCE;
    }
    /**
     * @return Object (either com.tsa.puridiom.entity.Vendor or com.tsa.puridiom.entity.VendorRegister
     * @param organizationId java.lang.String
     * @param vendorId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public Object getVendor(String organizationId, String vendorId) throws java.lang.Exception {
        Object result = null;

        try {
            if (Utility.isEmpty(vendorId) || Utility.isEmpty(organizationId)) {
                Vendor vendor = new Vendor();
                vendor.setVendorId(vendorId);
                return vendor;
            }
            else {
                Map vendors = new HashMap();
                if (this.organizations.containsKey(organizationId)) {
                    vendors = (Map) this.organizations.get(organizationId);
                }
                if (vendors.containsKey(vendorId)) {
                    result = vendors.get(vendorId);
                }
                else {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("vendorId", vendorId);
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("Vendor_vendorId", vendorId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    result = (Vendor) processParameters.get("vendor");

                    if (result != null) {
                        vendors.put(vendorId, result);
                    }
                    else {
                        result = this.getVendorRegister(organizationId, vendorId, false);
                        if (result == null) {
                        	result = new Vendor();
                        }
                    }
                    this.organizations.put(organizationId, vendors);
                }
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            return result;
        }
    }

    public Object getVendorRegister(String organizationId, String vendorId) throws java.lang.Exception {
    	return this.getVendorRegister(organizationId, vendorId, true);
    }

    /**
     * @return com.tsa.puridiom.entity.VendorRegister
     * @param organizationId java.lang.String
     * @param vendorId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public VendorRegister getVendorRegister(String organizationId, String vendorId , boolean returnEmptyVendorRegister) throws java.lang.Exception {
        VendorRegister vendorRegister = null;

        try {
            if (Utility.isEmpty(vendorId) || Utility.isEmpty(organizationId)) {
                vendorRegister = new VendorRegister();
                VendorRegisterPK pk = new VendorRegisterPK();
                pk.setVendorId(vendorId);
                vendorRegister.setComp_id(pk);
                return vendorRegister;
            }
            else {
                Map vendors = new HashMap();
                if (this.organizations.containsKey(organizationId)) {
                    vendors = (Map) this.organizations.get(organizationId);
                }
                if (vendors.containsKey(vendorId)) {
                    Object vendorObject = vendors.get(vendorId);
                    if (vendorObject instanceof VendorRegister) {
                        vendorRegister = (VendorRegister) vendorObject;
                    }
                }
                else {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("vendorId", vendorId);
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("VendorRegister_vendorId", vendorId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("vendorregister-retrieve-by-vendorid.xml");

                    process.executeProcess(processParameters);

                    vendorRegister = (VendorRegister) processParameters.get("vendorRegister");

                    if (vendorRegister != null) {
                        vendors.put(vendorId, vendorRegister);
                    }
                    else if (returnEmptyVendorRegister) {
                        vendorRegister = new VendorRegister();
                    }
                    this.organizations.put(organizationId, vendors);
                }
            }
        }
        catch (Exception e) {
        	Log.debug(this, e.getMessage());
			e.printStackTrace();
            throw e;
        }
        finally {
            return vendorRegister;
        }
    }

    /**
     * @return java.lang.String vendorName
     * @param organizationId java.lang.String
     * @param vendorId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public String getVendorName(String organizationId, String vendorId) throws java.lang.Exception {
        String vendorName = vendorId;

        try {
            if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId)) {
                Object vendorObject = this.getVendor(organizationId, vendorId);
                if (vendorObject != null) {
                    if (vendorObject instanceof Vendor) {
                        Vendor vendor = (Vendor) vendorObject;
                        vendorName = vendor.getVendorName();
                    } else if (vendorObject instanceof VendorRegister) {
                        VendorRegister vendorRegister = (VendorRegister) vendorObject;
                        vendorName = vendorRegister.getVendorName();
                    }
                }
                if (Utility.isEmpty(vendorName)) {
                    vendorName = vendorId;
                }
            }
        }
        catch (Exception e) {
        	Log.debug(this, e.getMessage());
			e.printStackTrace();
            throw e;
        }
        finally {
            return vendorName;
        }
    }

    /**
     * @param vendor Vendor
     * @exception java.lang.Exception
     */
    public void setVendorInCache(String organizationId, Vendor vendor) throws Exception {
        try {
            if (vendor == null) throw new Exception ("Vendor was null.");

            String	vendorId = vendor.getVendorId();

            if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(vendorId)) {
                HashMap vendors = new HashMap();

                organizationId = organizationId.trim().toUpperCase();
                vendorId = vendorId.trim().toUpperCase();

                if (this.organizations.containsKey(organizationId)) {
                    vendors = (HashMap) this.organizations.get(organizationId);
                }

                vendors.put(vendorId, vendor);

                this.organizations.put(organizationId, vendors);
            }
        }
        catch (Exception e) {
        	Log.debug(this, e.getMessage());
			e.printStackTrace();
            throw e;
        }
    }

    public String stripVendorName(String vendorName) throws Exception{
        try {
			String checkFor = "";
			String replaceWith = "";
			String phrases[][] = {{" THE"," "}, {"THE ",""}, { " CO.", " "}, {" CO ", " "}, {"INC.", ""}, {" INC", " "}, {"INCORPORATED", ""}, {",",""}, {".",""}};
			int rows = phrases.length;

			for (int n = 0; n < rows; n++) {
				checkFor = phrases[n][0];
				replaceWith = phrases[n][1];

				vendorName = Utility.searchAndReplace(vendorName, checkFor, replaceWith, true).trim();
			}

			vendorName = Utility.searchAndReplace(vendorName, "  ", " ", true).trim();
        } catch (Exception e) {
        	Log.debug(this, e.getMessage());
			e.printStackTrace();
        	throw e;
        }
    	return vendorName;
    }

    /**
     * Returns a BigDecimal that represents the value of this object.
     */

    public BigDecimal getLeadDays(String organizationId, String vendorId) throws java.lang.Exception {

    	BigDecimal leadDays = new BigDecimal("0");

        try {
            if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId)) {
                Object vendorObject = this.getVendor(organizationId, vendorId);
                if (vendorObject != null) {
                    if (vendorObject instanceof Vendor) {
                        Vendor vendor = (Vendor) vendorObject;
                        leadDays = vendor.getLeadDays();
                    }
                }
            }
        }
        catch (Exception e) {
        	Log.debug(this, e.getMessage());
			e.printStackTrace();
			throw e;
        }
        finally {
            return leadDays;
        }
    }

    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.vendor.VendorManager]");
        return sb.toString();
    }
}
