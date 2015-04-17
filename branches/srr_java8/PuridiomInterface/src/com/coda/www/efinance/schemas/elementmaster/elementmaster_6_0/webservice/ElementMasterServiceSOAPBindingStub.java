/**
 * ElementMasterServiceSOAPBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class ElementMasterServiceSOAPBindingStub extends org.apache.axis.client.Stub implements com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAddresses");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetAddressesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetAddressesRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetAddressesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetAddressesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ListPunchoutItemCodes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ListPunchoutItemCodesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListPunchoutItemCodesRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListPunchoutItemCodesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ListPunchoutItemCodesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("List");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "ListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetBanks");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetBanksRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetBanksRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetBanksResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetBanksResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Add");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "AddRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">AddRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">AddResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "AddResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "GetResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">UpdateRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">UpdateResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Fetch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "FetchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "FetchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Set");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "SetRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">SetRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">SetResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "SetResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "DeleteRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">DeleteRequest"), com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">DeleteResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "DeleteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    public ElementMasterServiceSOAPBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ElementMasterServiceSOAPBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ElementMasterServiceSOAPBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Companies");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ExtensionRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "FullKeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.FullKeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Key.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.KeyDataElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyDataElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.KeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Reason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReasonText");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReasonText.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReqKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeysWithShortName");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReqKeysWithShortName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAcCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAddress");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAddressName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccNum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccRef");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankSort");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankSwift");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCloseBracket");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrAgy");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrPayIndex");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrRating");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrRef");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtARCOptions");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCalcDisc");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCalcTax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCreditLimitCurrency");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtElmVocabEnum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtForceDisperse");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtLanguageInf");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtOneOfN");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtPlBal");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllLogical");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllOperator");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllVocab");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtSplit");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatPayDocLine");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatPayElm");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatRec");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtSummary");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtYesNoSome");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDate");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDateB");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDllName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNumAlphaB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCodeWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmLevel");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmSubsLevel");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeEMailAddrB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeExtRefB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeFax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeFederalTax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeIBANB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeItemCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeLanguage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMatchingOffsetDays");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMoney");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMoneyB");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeOpenBracket");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePercent");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePostCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeQtyTitle");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeSIC");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeSocialSecurity");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeStatUserB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeStoreCloseReason");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTerm");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTermB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAny");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUdfText");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUWord");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeVATB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeWord");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>AddRequest>AddOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequestAddOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>AddResponse>MultiCompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponseMultiCompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>FetchRequest>FetchOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequestFetchOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>GetRequest>GetOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequestGetOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>SetRequest>SetOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequestSetOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>SetResponse>MultiCompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponseMultiCompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>UpdateRequest>UpdateOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateRequestUpdateOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>UpdateResponse>MultiCompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponseMultiCompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">AddRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">DeleteRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetAddressesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetAddressesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetBanksRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetBanksResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListPunchoutItemCodesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListPunchoutItemCodesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">SetRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">SetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">UpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddressElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddressElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.BankElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Bank");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.BankElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CommentData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Comment");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.DiscountElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Discount");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.DiscountElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Element.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmFullKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKeyWithTemporaryElmFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmReqFullKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmTemporaryElmFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FetchKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.FetchKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GroupData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GroupCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransaction");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.LastTransaction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransactions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.LastTransactions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Master");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Master.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MediaData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MediaCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MnemonicData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mnemonic");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDeviceData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDeviceCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.PunchoutElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutAdvancedParam");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.PunchoutElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.QuantityElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Quantity");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.QuantityElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosures");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Closure");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RuleElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Rule");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RuleElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRowEx");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRowEx.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFDateField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFDateField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDate");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFElmField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFElmField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElm");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFNumberField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFNumberField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFTextField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFTextField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFText");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UmbrellaData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UmbrellaData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtElmVocabEnum");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }


    /**
     * Retrieves the specified element's address information from
     * the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse getAddresses(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesRequest getAddressesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/GetAddresses");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetAddresses"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAddressesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Produces a list of punchout product item codes associated with
     * the specified element masters. The punchout item is used as a template
     * for items created by punchout.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse listPunchoutItemCodes(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesRequest listPunchoutItemCodesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/ListPunchoutItemCodes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ListPunchoutItemCodes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {listPunchoutItemCodesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves extended key information for the specified element
     * masters from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse list(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListRequest listRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/List");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "List"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {listRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified element's bank information from the
     * database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse getBanks(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksRequest getBanksRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/GetBanks");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetBanks"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getBanksRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Creates new element masters in the database using the element
     * information you provide.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse add(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequest addRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Add");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Add"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified element masters from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse get(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequest getRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Get");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Updates element masters in the database using the element information
     * you provide.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse update(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateRequest updateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {updateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Fetches the specified element information from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse fetch(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequest fetchRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Fetch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Fetch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fetchRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Sets values for specified fields in specified element masters.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse set(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequest setRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Set");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Set"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {setRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Deletes the specified element masters from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse delete(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteRequest deleteRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/ElementMaster/Delete");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deleteRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
