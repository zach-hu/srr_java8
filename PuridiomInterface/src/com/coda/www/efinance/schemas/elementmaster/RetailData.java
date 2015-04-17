/**
 * RetailData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element
 *                 contains details for a Retail store.
 */
public class RetailData  implements java.io.Serializable {
    private java.lang.String ERetailCode;

    /* If
     *                         set, store is open on
     *                     Sunday. */
    private java.lang.Boolean openSunday;

    /* If set, store is open on
     *                     Monday. */
    private java.lang.Boolean openMonday;

    /* If set, store is open on
     *                     Tuesday. */
    private java.lang.Boolean openTuesday;

    /* If set, store is open on
     *                     Wednesday. */
    private java.lang.Boolean openWednesday;

    /* If set, store is open on
     *                     Thursday. */
    private java.lang.Boolean openThursday;

    /* If set, store is open on
     *                     Friday. */
    private java.lang.Boolean openFriday;

    /* If set, store is open on
     *                     Saturday. */
    private java.lang.Boolean openSaturday;

    /* The first date used to enter
     *                         trading details for this
     *                     store. */
    private java.util.Calendar firstTradingDate;

    /* The
     *                         final date used to enter trading details for
     * this store. */
    private java.util.Calendar lastTradingDate;

    /* The
     *                         code for the tax group that this store belongs
     * to within Retail */
    private java.lang.String taxGroupCode;

    /* The
     *                         value of the store category entity
     *                     master. */
    private java.lang.String entityValue;

    /* This
     *                         element contains user-defined text
     *                     fields. */
    private com.coda.www.efinance.schemas.elementmaster.UDFTextField[] UDFTextFields;

    /* This
     *                         element contains user-defined number
     *                     fields. */
    private com.coda.www.efinance.schemas.elementmaster.UDFNumberField[] UDFNumberFields;

    /* This
     *                         element contains user-defined date
     *                     fields. */
    private com.coda.www.efinance.schemas.elementmaster.UDFDateField[] UDFDateFields;

    /* This element contains
     *                         user-defined element fields. */
    private com.coda.www.efinance.schemas.elementmaster.UDFElmField[] UDFElmFields;

    /* This
     *                         element contains details of store closure
     *                     periods. */
    private com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[] storeClosures;

    public RetailData() {
    }

    public RetailData(
           java.lang.String ERetailCode,
           java.lang.Boolean openSunday,
           java.lang.Boolean openMonday,
           java.lang.Boolean openTuesday,
           java.lang.Boolean openWednesday,
           java.lang.Boolean openThursday,
           java.lang.Boolean openFriday,
           java.lang.Boolean openSaturday,
           java.util.Calendar firstTradingDate,
           java.util.Calendar lastTradingDate,
           java.lang.String taxGroupCode,
           java.lang.String entityValue,
           com.coda.www.efinance.schemas.elementmaster.UDFTextField[] UDFTextFields,
           com.coda.www.efinance.schemas.elementmaster.UDFNumberField[] UDFNumberFields,
           com.coda.www.efinance.schemas.elementmaster.UDFDateField[] UDFDateFields,
           com.coda.www.efinance.schemas.elementmaster.UDFElmField[] UDFElmFields,
           com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[] storeClosures) {
           this.ERetailCode = ERetailCode;
           this.openSunday = openSunday;
           this.openMonday = openMonday;
           this.openTuesday = openTuesday;
           this.openWednesday = openWednesday;
           this.openThursday = openThursday;
           this.openFriday = openFriday;
           this.openSaturday = openSaturday;
           this.firstTradingDate = firstTradingDate;
           this.lastTradingDate = lastTradingDate;
           this.taxGroupCode = taxGroupCode;
           this.entityValue = entityValue;
           this.UDFTextFields = UDFTextFields;
           this.UDFNumberFields = UDFNumberFields;
           this.UDFDateFields = UDFDateFields;
           this.UDFElmFields = UDFElmFields;
           this.storeClosures = storeClosures;
    }


    /**
     * Gets the ERetailCode value for this RetailData.
     * 
     * @return ERetailCode
     */
    public java.lang.String getERetailCode() {
        return ERetailCode;
    }


    /**
     * Sets the ERetailCode value for this RetailData.
     * 
     * @param ERetailCode
     */
    public void setERetailCode(java.lang.String ERetailCode) {
        this.ERetailCode = ERetailCode;
    }


    /**
     * Gets the openSunday value for this RetailData.
     * 
     * @return openSunday   * If
     *                         set, store is open on
     *                     Sunday.
     */
    public java.lang.Boolean getOpenSunday() {
        return openSunday;
    }


    /**
     * Sets the openSunday value for this RetailData.
     * 
     * @param openSunday   * If
     *                         set, store is open on
     *                     Sunday.
     */
    public void setOpenSunday(java.lang.Boolean openSunday) {
        this.openSunday = openSunday;
    }


    /**
     * Gets the openMonday value for this RetailData.
     * 
     * @return openMonday   * If set, store is open on
     *                     Monday.
     */
    public java.lang.Boolean getOpenMonday() {
        return openMonday;
    }


    /**
     * Sets the openMonday value for this RetailData.
     * 
     * @param openMonday   * If set, store is open on
     *                     Monday.
     */
    public void setOpenMonday(java.lang.Boolean openMonday) {
        this.openMonday = openMonday;
    }


    /**
     * Gets the openTuesday value for this RetailData.
     * 
     * @return openTuesday   * If set, store is open on
     *                     Tuesday.
     */
    public java.lang.Boolean getOpenTuesday() {
        return openTuesday;
    }


    /**
     * Sets the openTuesday value for this RetailData.
     * 
     * @param openTuesday   * If set, store is open on
     *                     Tuesday.
     */
    public void setOpenTuesday(java.lang.Boolean openTuesday) {
        this.openTuesday = openTuesday;
    }


    /**
     * Gets the openWednesday value for this RetailData.
     * 
     * @return openWednesday   * If set, store is open on
     *                     Wednesday.
     */
    public java.lang.Boolean getOpenWednesday() {
        return openWednesday;
    }


    /**
     * Sets the openWednesday value for this RetailData.
     * 
     * @param openWednesday   * If set, store is open on
     *                     Wednesday.
     */
    public void setOpenWednesday(java.lang.Boolean openWednesday) {
        this.openWednesday = openWednesday;
    }


    /**
     * Gets the openThursday value for this RetailData.
     * 
     * @return openThursday   * If set, store is open on
     *                     Thursday.
     */
    public java.lang.Boolean getOpenThursday() {
        return openThursday;
    }


    /**
     * Sets the openThursday value for this RetailData.
     * 
     * @param openThursday   * If set, store is open on
     *                     Thursday.
     */
    public void setOpenThursday(java.lang.Boolean openThursday) {
        this.openThursday = openThursday;
    }


    /**
     * Gets the openFriday value for this RetailData.
     * 
     * @return openFriday   * If set, store is open on
     *                     Friday.
     */
    public java.lang.Boolean getOpenFriday() {
        return openFriday;
    }


    /**
     * Sets the openFriday value for this RetailData.
     * 
     * @param openFriday   * If set, store is open on
     *                     Friday.
     */
    public void setOpenFriday(java.lang.Boolean openFriday) {
        this.openFriday = openFriday;
    }


    /**
     * Gets the openSaturday value for this RetailData.
     * 
     * @return openSaturday   * If set, store is open on
     *                     Saturday.
     */
    public java.lang.Boolean getOpenSaturday() {
        return openSaturday;
    }


    /**
     * Sets the openSaturday value for this RetailData.
     * 
     * @param openSaturday   * If set, store is open on
     *                     Saturday.
     */
    public void setOpenSaturday(java.lang.Boolean openSaturday) {
        this.openSaturday = openSaturday;
    }


    /**
     * Gets the firstTradingDate value for this RetailData.
     * 
     * @return firstTradingDate   * The first date used to enter
     *                         trading details for this
     *                     store.
     */
    public java.util.Calendar getFirstTradingDate() {
        return firstTradingDate;
    }


    /**
     * Sets the firstTradingDate value for this RetailData.
     * 
     * @param firstTradingDate   * The first date used to enter
     *                         trading details for this
     *                     store.
     */
    public void setFirstTradingDate(java.util.Calendar firstTradingDate) {
        this.firstTradingDate = firstTradingDate;
    }


    /**
     * Gets the lastTradingDate value for this RetailData.
     * 
     * @return lastTradingDate   * The
     *                         final date used to enter trading details for
     * this store.
     */
    public java.util.Calendar getLastTradingDate() {
        return lastTradingDate;
    }


    /**
     * Sets the lastTradingDate value for this RetailData.
     * 
     * @param lastTradingDate   * The
     *                         final date used to enter trading details for
     * this store.
     */
    public void setLastTradingDate(java.util.Calendar lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }


    /**
     * Gets the taxGroupCode value for this RetailData.
     * 
     * @return taxGroupCode   * The
     *                         code for the tax group that this store belongs
     * to within Retail
     */
    public java.lang.String getTaxGroupCode() {
        return taxGroupCode;
    }


    /**
     * Sets the taxGroupCode value for this RetailData.
     * 
     * @param taxGroupCode   * The
     *                         code for the tax group that this store belongs
     * to within Retail
     */
    public void setTaxGroupCode(java.lang.String taxGroupCode) {
        this.taxGroupCode = taxGroupCode;
    }


    /**
     * Gets the entityValue value for this RetailData.
     * 
     * @return entityValue   * The
     *                         value of the store category entity
     *                     master.
     */
    public java.lang.String getEntityValue() {
        return entityValue;
    }


    /**
     * Sets the entityValue value for this RetailData.
     * 
     * @param entityValue   * The
     *                         value of the store category entity
     *                     master.
     */
    public void setEntityValue(java.lang.String entityValue) {
        this.entityValue = entityValue;
    }


    /**
     * Gets the UDFTextFields value for this RetailData.
     * 
     * @return UDFTextFields   * This
     *                         element contains user-defined text
     *                     fields.
     */
    public com.coda.www.efinance.schemas.elementmaster.UDFTextField[] getUDFTextFields() {
        return UDFTextFields;
    }


    /**
     * Sets the UDFTextFields value for this RetailData.
     * 
     * @param UDFTextFields   * This
     *                         element contains user-defined text
     *                     fields.
     */
    public void setUDFTextFields(com.coda.www.efinance.schemas.elementmaster.UDFTextField[] UDFTextFields) {
        this.UDFTextFields = UDFTextFields;
    }


    /**
     * Gets the UDFNumberFields value for this RetailData.
     * 
     * @return UDFNumberFields   * This
     *                         element contains user-defined number
     *                     fields.
     */
    public com.coda.www.efinance.schemas.elementmaster.UDFNumberField[] getUDFNumberFields() {
        return UDFNumberFields;
    }


    /**
     * Sets the UDFNumberFields value for this RetailData.
     * 
     * @param UDFNumberFields   * This
     *                         element contains user-defined number
     *                     fields.
     */
    public void setUDFNumberFields(com.coda.www.efinance.schemas.elementmaster.UDFNumberField[] UDFNumberFields) {
        this.UDFNumberFields = UDFNumberFields;
    }


    /**
     * Gets the UDFDateFields value for this RetailData.
     * 
     * @return UDFDateFields   * This
     *                         element contains user-defined date
     *                     fields.
     */
    public com.coda.www.efinance.schemas.elementmaster.UDFDateField[] getUDFDateFields() {
        return UDFDateFields;
    }


    /**
     * Sets the UDFDateFields value for this RetailData.
     * 
     * @param UDFDateFields   * This
     *                         element contains user-defined date
     *                     fields.
     */
    public void setUDFDateFields(com.coda.www.efinance.schemas.elementmaster.UDFDateField[] UDFDateFields) {
        this.UDFDateFields = UDFDateFields;
    }


    /**
     * Gets the UDFElmFields value for this RetailData.
     * 
     * @return UDFElmFields   * This element contains
     *                         user-defined element fields.
     */
    public com.coda.www.efinance.schemas.elementmaster.UDFElmField[] getUDFElmFields() {
        return UDFElmFields;
    }


    /**
     * Sets the UDFElmFields value for this RetailData.
     * 
     * @param UDFElmFields   * This element contains
     *                         user-defined element fields.
     */
    public void setUDFElmFields(com.coda.www.efinance.schemas.elementmaster.UDFElmField[] UDFElmFields) {
        this.UDFElmFields = UDFElmFields;
    }


    /**
     * Gets the storeClosures value for this RetailData.
     * 
     * @return storeClosures   * This
     *                         element contains details of store closure
     *                     periods.
     */
    public com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[] getStoreClosures() {
        return storeClosures;
    }


    /**
     * Sets the storeClosures value for this RetailData.
     * 
     * @param storeClosures   * This
     *                         element contains details of store closure
     *                     periods.
     */
    public void setStoreClosures(com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[] storeClosures) {
        this.storeClosures = storeClosures;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetailData)) return false;
        RetailData other = (RetailData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ERetailCode==null && other.getERetailCode()==null) || 
             (this.ERetailCode!=null &&
              this.ERetailCode.equals(other.getERetailCode()))) &&
            ((this.openSunday==null && other.getOpenSunday()==null) || 
             (this.openSunday!=null &&
              this.openSunday.equals(other.getOpenSunday()))) &&
            ((this.openMonday==null && other.getOpenMonday()==null) || 
             (this.openMonday!=null &&
              this.openMonday.equals(other.getOpenMonday()))) &&
            ((this.openTuesday==null && other.getOpenTuesday()==null) || 
             (this.openTuesday!=null &&
              this.openTuesday.equals(other.getOpenTuesday()))) &&
            ((this.openWednesday==null && other.getOpenWednesday()==null) || 
             (this.openWednesday!=null &&
              this.openWednesday.equals(other.getOpenWednesday()))) &&
            ((this.openThursday==null && other.getOpenThursday()==null) || 
             (this.openThursday!=null &&
              this.openThursday.equals(other.getOpenThursday()))) &&
            ((this.openFriday==null && other.getOpenFriday()==null) || 
             (this.openFriday!=null &&
              this.openFriday.equals(other.getOpenFriday()))) &&
            ((this.openSaturday==null && other.getOpenSaturday()==null) || 
             (this.openSaturday!=null &&
              this.openSaturday.equals(other.getOpenSaturday()))) &&
            ((this.firstTradingDate==null && other.getFirstTradingDate()==null) || 
             (this.firstTradingDate!=null &&
              this.firstTradingDate.equals(other.getFirstTradingDate()))) &&
            ((this.lastTradingDate==null && other.getLastTradingDate()==null) || 
             (this.lastTradingDate!=null &&
              this.lastTradingDate.equals(other.getLastTradingDate()))) &&
            ((this.taxGroupCode==null && other.getTaxGroupCode()==null) || 
             (this.taxGroupCode!=null &&
              this.taxGroupCode.equals(other.getTaxGroupCode()))) &&
            ((this.entityValue==null && other.getEntityValue()==null) || 
             (this.entityValue!=null &&
              this.entityValue.equals(other.getEntityValue()))) &&
            ((this.UDFTextFields==null && other.getUDFTextFields()==null) || 
             (this.UDFTextFields!=null &&
              java.util.Arrays.equals(this.UDFTextFields, other.getUDFTextFields()))) &&
            ((this.UDFNumberFields==null && other.getUDFNumberFields()==null) || 
             (this.UDFNumberFields!=null &&
              java.util.Arrays.equals(this.UDFNumberFields, other.getUDFNumberFields()))) &&
            ((this.UDFDateFields==null && other.getUDFDateFields()==null) || 
             (this.UDFDateFields!=null &&
              java.util.Arrays.equals(this.UDFDateFields, other.getUDFDateFields()))) &&
            ((this.UDFElmFields==null && other.getUDFElmFields()==null) || 
             (this.UDFElmFields!=null &&
              java.util.Arrays.equals(this.UDFElmFields, other.getUDFElmFields()))) &&
            ((this.storeClosures==null && other.getStoreClosures()==null) || 
             (this.storeClosures!=null &&
              java.util.Arrays.equals(this.storeClosures, other.getStoreClosures())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getERetailCode() != null) {
            _hashCode += getERetailCode().hashCode();
        }
        if (getOpenSunday() != null) {
            _hashCode += getOpenSunday().hashCode();
        }
        if (getOpenMonday() != null) {
            _hashCode += getOpenMonday().hashCode();
        }
        if (getOpenTuesday() != null) {
            _hashCode += getOpenTuesday().hashCode();
        }
        if (getOpenWednesday() != null) {
            _hashCode += getOpenWednesday().hashCode();
        }
        if (getOpenThursday() != null) {
            _hashCode += getOpenThursday().hashCode();
        }
        if (getOpenFriday() != null) {
            _hashCode += getOpenFriday().hashCode();
        }
        if (getOpenSaturday() != null) {
            _hashCode += getOpenSaturday().hashCode();
        }
        if (getFirstTradingDate() != null) {
            _hashCode += getFirstTradingDate().hashCode();
        }
        if (getLastTradingDate() != null) {
            _hashCode += getLastTradingDate().hashCode();
        }
        if (getTaxGroupCode() != null) {
            _hashCode += getTaxGroupCode().hashCode();
        }
        if (getEntityValue() != null) {
            _hashCode += getEntityValue().hashCode();
        }
        if (getUDFTextFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFTextFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFTextFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUDFNumberFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFNumberFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFNumberFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUDFDateFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFDateFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFDateFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUDFElmFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFElmFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFElmFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStoreClosures() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStoreClosures());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStoreClosures(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetailData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ERetailCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ERetailCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openSunday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenSunday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openMonday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenMonday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openTuesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenTuesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openWednesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenWednesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openThursday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenThursday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openFriday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenFriday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openSaturday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OpenSaturday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstTradingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FirstTradingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTradingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTradingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxGroupCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxGroupCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "EntityValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFTextFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFText"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFNumberFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumber"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFDateFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDate"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFElmFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElm"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeClosures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "StoreClosures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Closure"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
