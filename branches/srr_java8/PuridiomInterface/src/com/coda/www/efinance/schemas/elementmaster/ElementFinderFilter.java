/**
 * ElementFinderFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element holds parameters you can
 *                 specify to find a particular element
 *             master.
 */
public class ElementFinderFilter  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange numberRange;

    /* The company
     *                     code. */
    private java.lang.String companyCode;

    /* The element level to which
     *                         the element master you want to find
     *                     belongs. */
    private short level;

    /* The
     *                         code for the element master you want to
     *                     find. */
    private java.lang.String code;

    /* The
     *                         abbreviated name for the element master you
     * want
     *                         to find. */
    private java.lang.String shortName;

    /* The name or the short name of
     *                         the element master you want to
     *                     find. */
    private java.lang.String nameOrShortName;

    /* The
     *                         currency code of the element master you want
     * to
     *                     find. */
    private java.lang.String currencyCode;

    /* An
     *                         element group to which the required element
     * master belongs. */
    private java.lang.String elementGroup;

    /* The mnemonic of the element
     *                         master you want to find. */
    private java.lang.String mnemonic;

    /* The post code of the element
     *                         you want to find. */
    private java.lang.String postCode;

    /* The bank account number of
     *                         the element you want to
     *                     find. */
    private java.lang.String accNum;

    /* Specifies whether or not the
     *                         required element master is
     *                     matchable. */
    private java.lang.String matchable;

    /* Specifies whether or not the
     *                         required element master is a customer or
     *                     supplier. */
    private java.lang.String customerSupplier;

    /* Specifies the element
     *                         customer or supplier status. */
    private java.lang.String customerSupplierExt;

    /* Specifies whether or not the
     *                         required element master is for an umbrella
     * element. */
    private java.lang.String umbrella;

    /* When
     *                         TRUE, only element masters to which the current
     * user has read/write access are returned. A
     *                         user's access to element masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master. */
    private java.lang.Boolean enableMasterSecurityFilter;

    /* The
     *                         code for the element master at the end of
     * the
     *                         selection range. */
    private java.lang.String codeRangeEndCode;

    /* The
     *                         start date of a date range for selecting element
     * masters that were last modified within this date
     *                     range. */
    private java.util.Calendar lastModifiedFrom;

    /* The
     *                         end date of a date range for selecting element
     * masters that were last modified within this date
     *                     range. */
    private java.util.Calendar lastModifiedTo;

    /* The
     *                         fax number of the element you want to find.
     * All
     *                         addresses on the element are searched for
     * this
     *                         fax number. */
    private java.lang.String fax;

    /* The telephone number of the
     *                         element you want to find. All addresses on
     * the
     *                         element are searched for this telephone
     *                     number. */
    private java.lang.String telephone;

    /* The tax number of the element
     *                         you want to find. */
    private java.lang.String taxNumber;

    /* If TRUE, specifies that
     *                         element master names should be
     *                     returned. */
    private java.lang.Boolean returnNames;

    public ElementFinderFilter() {
    }

    public ElementFinderFilter(
           com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange numberRange,
           java.lang.String companyCode,
           short level,
           java.lang.String code,
           java.lang.String shortName,
           java.lang.String nameOrShortName,
           java.lang.String currencyCode,
           java.lang.String elementGroup,
           java.lang.String mnemonic,
           java.lang.String postCode,
           java.lang.String accNum,
           java.lang.String matchable,
           java.lang.String customerSupplier,
           java.lang.String customerSupplierExt,
           java.lang.String umbrella,
           java.lang.Boolean enableMasterSecurityFilter,
           java.lang.String codeRangeEndCode,
           java.util.Calendar lastModifiedFrom,
           java.util.Calendar lastModifiedTo,
           java.lang.String fax,
           java.lang.String telephone,
           java.lang.String taxNumber,
           java.lang.Boolean returnNames) {
           this.numberRange = numberRange;
           this.companyCode = companyCode;
           this.level = level;
           this.code = code;
           this.shortName = shortName;
           this.nameOrShortName = nameOrShortName;
           this.currencyCode = currencyCode;
           this.elementGroup = elementGroup;
           this.mnemonic = mnemonic;
           this.postCode = postCode;
           this.accNum = accNum;
           this.matchable = matchable;
           this.customerSupplier = customerSupplier;
           this.customerSupplierExt = customerSupplierExt;
           this.umbrella = umbrella;
           this.enableMasterSecurityFilter = enableMasterSecurityFilter;
           this.codeRangeEndCode = codeRangeEndCode;
           this.lastModifiedFrom = lastModifiedFrom;
           this.lastModifiedTo = lastModifiedTo;
           this.fax = fax;
           this.telephone = telephone;
           this.taxNumber = taxNumber;
           this.returnNames = returnNames;
    }


    /**
     * Gets the numberRange value for this ElementFinderFilter.
     * 
     * @return numberRange
     */
    public com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange getNumberRange() {
        return numberRange;
    }


    /**
     * Sets the numberRange value for this ElementFinderFilter.
     * 
     * @param numberRange
     */
    public void setNumberRange(com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange numberRange) {
        this.numberRange = numberRange;
    }


    /**
     * Gets the companyCode value for this ElementFinderFilter.
     * 
     * @return companyCode   * The company
     *                     code.
     */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }


    /**
     * Sets the companyCode value for this ElementFinderFilter.
     * 
     * @param companyCode   * The company
     *                     code.
     */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }


    /**
     * Gets the level value for this ElementFinderFilter.
     * 
     * @return level   * The element level to which
     *                         the element master you want to find
     *                     belongs.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ElementFinderFilter.
     * 
     * @param level   * The element level to which
     *                         the element master you want to find
     *                     belongs.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this ElementFinderFilter.
     * 
     * @return code   * The
     *                         code for the element master you want to
     *                     find.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElementFinderFilter.
     * 
     * @param code   * The
     *                         code for the element master you want to
     *                     find.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the shortName value for this ElementFinderFilter.
     * 
     * @return shortName   * The
     *                         abbreviated name for the element master you
     * want
     *                         to find.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this ElementFinderFilter.
     * 
     * @param shortName   * The
     *                         abbreviated name for the element master you
     * want
     *                         to find.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the nameOrShortName value for this ElementFinderFilter.
     * 
     * @return nameOrShortName   * The name or the short name of
     *                         the element master you want to
     *                     find.
     */
    public java.lang.String getNameOrShortName() {
        return nameOrShortName;
    }


    /**
     * Sets the nameOrShortName value for this ElementFinderFilter.
     * 
     * @param nameOrShortName   * The name or the short name of
     *                         the element master you want to
     *                     find.
     */
    public void setNameOrShortName(java.lang.String nameOrShortName) {
        this.nameOrShortName = nameOrShortName;
    }


    /**
     * Gets the currencyCode value for this ElementFinderFilter.
     * 
     * @return currencyCode   * The
     *                         currency code of the element master you want
     * to
     *                     find.
     */
    public java.lang.String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this ElementFinderFilter.
     * 
     * @param currencyCode   * The
     *                         currency code of the element master you want
     * to
     *                     find.
     */
    public void setCurrencyCode(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the elementGroup value for this ElementFinderFilter.
     * 
     * @return elementGroup   * An
     *                         element group to which the required element
     * master belongs.
     */
    public java.lang.String getElementGroup() {
        return elementGroup;
    }


    /**
     * Sets the elementGroup value for this ElementFinderFilter.
     * 
     * @param elementGroup   * An
     *                         element group to which the required element
     * master belongs.
     */
    public void setElementGroup(java.lang.String elementGroup) {
        this.elementGroup = elementGroup;
    }


    /**
     * Gets the mnemonic value for this ElementFinderFilter.
     * 
     * @return mnemonic   * The mnemonic of the element
     *                         master you want to find.
     */
    public java.lang.String getMnemonic() {
        return mnemonic;
    }


    /**
     * Sets the mnemonic value for this ElementFinderFilter.
     * 
     * @param mnemonic   * The mnemonic of the element
     *                         master you want to find.
     */
    public void setMnemonic(java.lang.String mnemonic) {
        this.mnemonic = mnemonic;
    }


    /**
     * Gets the postCode value for this ElementFinderFilter.
     * 
     * @return postCode   * The post code of the element
     *                         you want to find.
     */
    public java.lang.String getPostCode() {
        return postCode;
    }


    /**
     * Sets the postCode value for this ElementFinderFilter.
     * 
     * @param postCode   * The post code of the element
     *                         you want to find.
     */
    public void setPostCode(java.lang.String postCode) {
        this.postCode = postCode;
    }


    /**
     * Gets the accNum value for this ElementFinderFilter.
     * 
     * @return accNum   * The bank account number of
     *                         the element you want to
     *                     find.
     */
    public java.lang.String getAccNum() {
        return accNum;
    }


    /**
     * Sets the accNum value for this ElementFinderFilter.
     * 
     * @param accNum   * The bank account number of
     *                         the element you want to
     *                     find.
     */
    public void setAccNum(java.lang.String accNum) {
        this.accNum = accNum;
    }


    /**
     * Gets the matchable value for this ElementFinderFilter.
     * 
     * @return matchable   * Specifies whether or not the
     *                         required element master is
     *                     matchable.
     */
    public java.lang.String getMatchable() {
        return matchable;
    }


    /**
     * Sets the matchable value for this ElementFinderFilter.
     * 
     * @param matchable   * Specifies whether or not the
     *                         required element master is
     *                     matchable.
     */
    public void setMatchable(java.lang.String matchable) {
        this.matchable = matchable;
    }


    /**
     * Gets the customerSupplier value for this ElementFinderFilter.
     * 
     * @return customerSupplier   * Specifies whether or not the
     *                         required element master is a customer or
     *                     supplier.
     */
    public java.lang.String getCustomerSupplier() {
        return customerSupplier;
    }


    /**
     * Sets the customerSupplier value for this ElementFinderFilter.
     * 
     * @param customerSupplier   * Specifies whether or not the
     *                         required element master is a customer or
     *                     supplier.
     */
    public void setCustomerSupplier(java.lang.String customerSupplier) {
        this.customerSupplier = customerSupplier;
    }


    /**
     * Gets the customerSupplierExt value for this ElementFinderFilter.
     * 
     * @return customerSupplierExt   * Specifies the element
     *                         customer or supplier status.
     */
    public java.lang.String getCustomerSupplierExt() {
        return customerSupplierExt;
    }


    /**
     * Sets the customerSupplierExt value for this ElementFinderFilter.
     * 
     * @param customerSupplierExt   * Specifies the element
     *                         customer or supplier status.
     */
    public void setCustomerSupplierExt(java.lang.String customerSupplierExt) {
        this.customerSupplierExt = customerSupplierExt;
    }


    /**
     * Gets the umbrella value for this ElementFinderFilter.
     * 
     * @return umbrella   * Specifies whether or not the
     *                         required element master is for an umbrella
     * element.
     */
    public java.lang.String getUmbrella() {
        return umbrella;
    }


    /**
     * Sets the umbrella value for this ElementFinderFilter.
     * 
     * @param umbrella   * Specifies whether or not the
     *                         required element master is for an umbrella
     * element.
     */
    public void setUmbrella(java.lang.String umbrella) {
        this.umbrella = umbrella;
    }


    /**
     * Gets the enableMasterSecurityFilter value for this ElementFinderFilter.
     * 
     * @return enableMasterSecurityFilter   * When
     *                         TRUE, only element masters to which the current
     * user has read/write access are returned. A
     *                         user's access to element masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master.
     */
    public java.lang.Boolean getEnableMasterSecurityFilter() {
        return enableMasterSecurityFilter;
    }


    /**
     * Sets the enableMasterSecurityFilter value for this ElementFinderFilter.
     * 
     * @param enableMasterSecurityFilter   * When
     *                         TRUE, only element masters to which the current
     * user has read/write access are returned. A
     *                         user's access to element masters is
     *                         controlled by the master security lists on
     * the
     *                         user's capability
     *                     master.
     */
    public void setEnableMasterSecurityFilter(java.lang.Boolean enableMasterSecurityFilter) {
        this.enableMasterSecurityFilter = enableMasterSecurityFilter;
    }


    /**
     * Gets the codeRangeEndCode value for this ElementFinderFilter.
     * 
     * @return codeRangeEndCode   * The
     *                         code for the element master at the end of
     * the
     *                         selection range.
     */
    public java.lang.String getCodeRangeEndCode() {
        return codeRangeEndCode;
    }


    /**
     * Sets the codeRangeEndCode value for this ElementFinderFilter.
     * 
     * @param codeRangeEndCode   * The
     *                         code for the element master at the end of
     * the
     *                         selection range.
     */
    public void setCodeRangeEndCode(java.lang.String codeRangeEndCode) {
        this.codeRangeEndCode = codeRangeEndCode;
    }


    /**
     * Gets the lastModifiedFrom value for this ElementFinderFilter.
     * 
     * @return lastModifiedFrom   * The
     *                         start date of a date range for selecting element
     * masters that were last modified within this date
     *                     range.
     */
    public java.util.Calendar getLastModifiedFrom() {
        return lastModifiedFrom;
    }


    /**
     * Sets the lastModifiedFrom value for this ElementFinderFilter.
     * 
     * @param lastModifiedFrom   * The
     *                         start date of a date range for selecting element
     * masters that were last modified within this date
     *                     range.
     */
    public void setLastModifiedFrom(java.util.Calendar lastModifiedFrom) {
        this.lastModifiedFrom = lastModifiedFrom;
    }


    /**
     * Gets the lastModifiedTo value for this ElementFinderFilter.
     * 
     * @return lastModifiedTo   * The
     *                         end date of a date range for selecting element
     * masters that were last modified within this date
     *                     range.
     */
    public java.util.Calendar getLastModifiedTo() {
        return lastModifiedTo;
    }


    /**
     * Sets the lastModifiedTo value for this ElementFinderFilter.
     * 
     * @param lastModifiedTo   * The
     *                         end date of a date range for selecting element
     * masters that were last modified within this date
     *                     range.
     */
    public void setLastModifiedTo(java.util.Calendar lastModifiedTo) {
        this.lastModifiedTo = lastModifiedTo;
    }


    /**
     * Gets the fax value for this ElementFinderFilter.
     * 
     * @return fax   * The
     *                         fax number of the element you want to find.
     * All
     *                         addresses on the element are searched for
     * this
     *                         fax number.
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this ElementFinderFilter.
     * 
     * @param fax   * The
     *                         fax number of the element you want to find.
     * All
     *                         addresses on the element are searched for
     * this
     *                         fax number.
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the telephone value for this ElementFinderFilter.
     * 
     * @return telephone   * The telephone number of the
     *                         element you want to find. All addresses on
     * the
     *                         element are searched for this telephone
     *                     number.
     */
    public java.lang.String getTelephone() {
        return telephone;
    }


    /**
     * Sets the telephone value for this ElementFinderFilter.
     * 
     * @param telephone   * The telephone number of the
     *                         element you want to find. All addresses on
     * the
     *                         element are searched for this telephone
     *                     number.
     */
    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
    }


    /**
     * Gets the taxNumber value for this ElementFinderFilter.
     * 
     * @return taxNumber   * The tax number of the element
     *                         you want to find.
     */
    public java.lang.String getTaxNumber() {
        return taxNumber;
    }


    /**
     * Sets the taxNumber value for this ElementFinderFilter.
     * 
     * @param taxNumber   * The tax number of the element
     *                         you want to find.
     */
    public void setTaxNumber(java.lang.String taxNumber) {
        this.taxNumber = taxNumber;
    }


    /**
     * Gets the returnNames value for this ElementFinderFilter.
     * 
     * @return returnNames   * If TRUE, specifies that
     *                         element master names should be
     *                     returned.
     */
    public java.lang.Boolean getReturnNames() {
        return returnNames;
    }


    /**
     * Sets the returnNames value for this ElementFinderFilter.
     * 
     * @param returnNames   * If TRUE, specifies that
     *                         element master names should be
     *                     returned.
     */
    public void setReturnNames(java.lang.Boolean returnNames) {
        this.returnNames = returnNames;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElementFinderFilter)) return false;
        ElementFinderFilter other = (ElementFinderFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numberRange==null && other.getNumberRange()==null) || 
             (this.numberRange!=null &&
              this.numberRange.equals(other.getNumberRange()))) &&
            ((this.companyCode==null && other.getCompanyCode()==null) || 
             (this.companyCode!=null &&
              this.companyCode.equals(other.getCompanyCode()))) &&
            this.level == other.getLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.nameOrShortName==null && other.getNameOrShortName()==null) || 
             (this.nameOrShortName!=null &&
              this.nameOrShortName.equals(other.getNameOrShortName()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            ((this.elementGroup==null && other.getElementGroup()==null) || 
             (this.elementGroup!=null &&
              this.elementGroup.equals(other.getElementGroup()))) &&
            ((this.mnemonic==null && other.getMnemonic()==null) || 
             (this.mnemonic!=null &&
              this.mnemonic.equals(other.getMnemonic()))) &&
            ((this.postCode==null && other.getPostCode()==null) || 
             (this.postCode!=null &&
              this.postCode.equals(other.getPostCode()))) &&
            ((this.accNum==null && other.getAccNum()==null) || 
             (this.accNum!=null &&
              this.accNum.equals(other.getAccNum()))) &&
            ((this.matchable==null && other.getMatchable()==null) || 
             (this.matchable!=null &&
              this.matchable.equals(other.getMatchable()))) &&
            ((this.customerSupplier==null && other.getCustomerSupplier()==null) || 
             (this.customerSupplier!=null &&
              this.customerSupplier.equals(other.getCustomerSupplier()))) &&
            ((this.customerSupplierExt==null && other.getCustomerSupplierExt()==null) || 
             (this.customerSupplierExt!=null &&
              this.customerSupplierExt.equals(other.getCustomerSupplierExt()))) &&
            ((this.umbrella==null && other.getUmbrella()==null) || 
             (this.umbrella!=null &&
              this.umbrella.equals(other.getUmbrella()))) &&
            ((this.enableMasterSecurityFilter==null && other.getEnableMasterSecurityFilter()==null) || 
             (this.enableMasterSecurityFilter!=null &&
              this.enableMasterSecurityFilter.equals(other.getEnableMasterSecurityFilter()))) &&
            ((this.codeRangeEndCode==null && other.getCodeRangeEndCode()==null) || 
             (this.codeRangeEndCode!=null &&
              this.codeRangeEndCode.equals(other.getCodeRangeEndCode()))) &&
            ((this.lastModifiedFrom==null && other.getLastModifiedFrom()==null) || 
             (this.lastModifiedFrom!=null &&
              this.lastModifiedFrom.equals(other.getLastModifiedFrom()))) &&
            ((this.lastModifiedTo==null && other.getLastModifiedTo()==null) || 
             (this.lastModifiedTo!=null &&
              this.lastModifiedTo.equals(other.getLastModifiedTo()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.telephone==null && other.getTelephone()==null) || 
             (this.telephone!=null &&
              this.telephone.equals(other.getTelephone()))) &&
            ((this.taxNumber==null && other.getTaxNumber()==null) || 
             (this.taxNumber!=null &&
              this.taxNumber.equals(other.getTaxNumber()))) &&
            ((this.returnNames==null && other.getReturnNames()==null) || 
             (this.returnNames!=null &&
              this.returnNames.equals(other.getReturnNames())));
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
        if (getNumberRange() != null) {
            _hashCode += getNumberRange().hashCode();
        }
        if (getCompanyCode() != null) {
            _hashCode += getCompanyCode().hashCode();
        }
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getNameOrShortName() != null) {
            _hashCode += getNameOrShortName().hashCode();
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        if (getElementGroup() != null) {
            _hashCode += getElementGroup().hashCode();
        }
        if (getMnemonic() != null) {
            _hashCode += getMnemonic().hashCode();
        }
        if (getPostCode() != null) {
            _hashCode += getPostCode().hashCode();
        }
        if (getAccNum() != null) {
            _hashCode += getAccNum().hashCode();
        }
        if (getMatchable() != null) {
            _hashCode += getMatchable().hashCode();
        }
        if (getCustomerSupplier() != null) {
            _hashCode += getCustomerSupplier().hashCode();
        }
        if (getCustomerSupplierExt() != null) {
            _hashCode += getCustomerSupplierExt().hashCode();
        }
        if (getUmbrella() != null) {
            _hashCode += getUmbrella().hashCode();
        }
        if (getEnableMasterSecurityFilter() != null) {
            _hashCode += getEnableMasterSecurityFilter().hashCode();
        }
        if (getCodeRangeEndCode() != null) {
            _hashCode += getCodeRangeEndCode().hashCode();
        }
        if (getLastModifiedFrom() != null) {
            _hashCode += getLastModifiedFrom().hashCode();
        }
        if (getLastModifiedTo() != null) {
            _hashCode += getLastModifiedTo().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getTelephone() != null) {
            _hashCode += getTelephone().hashCode();
        }
        if (getTaxNumber() != null) {
            _hashCode += getTaxNumber().hashCode();
        }
        if (getReturnNames() != null) {
            _hashCode += getReturnNames().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElementFinderFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberRange");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "NumberRange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderNumberRange"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CompanyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameOrShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "NameOrShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mnemonic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mnemonic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PostCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Matchable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CustomerSupplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerSupplierExt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CustomerSupplierExt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("umbrella");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Umbrella"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enableMasterSecurityFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "EnableMasterSecurityFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeRangeEndCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CodeRangeEndCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastModifiedFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastModifiedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telephone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Telephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ReturnNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
