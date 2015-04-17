package iseries.wsbeans.getworkordervalidation;

public class GetWorkOrderValidationPortTypeProxy implements iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationPortType {
  private String _endpoint = null;
  private iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationPortType getWorkOrderValidationPortType = null;
  
  public GetWorkOrderValidationPortTypeProxy() {
    _initGetWorkOrderValidationPortTypeProxy();
  }
  
  public GetWorkOrderValidationPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initGetWorkOrderValidationPortTypeProxy();
  }
  
  private void _initGetWorkOrderValidationPortTypeProxy() {
    try {
      getWorkOrderValidationPortType = (new iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationLocator()).getGetWorkOrderValidationSOAP11port_http();
      if (getWorkOrderValidationPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)getWorkOrderValidationPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)getWorkOrderValidationPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (getWorkOrderValidationPortType != null)
      ((javax.xml.rpc.Stub)getWorkOrderValidationPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.getworkordervalidation.GetWorkOrderValidationPortType getGetWorkOrderValidationPortType() {
    if (getWorkOrderValidationPortType == null)
      _initGetWorkOrderValidationPortTypeProxy();
    return getWorkOrderValidationPortType;
  }
  
  public iseries.wsbeans.getworkordervalidation.xsd.POI957Result poi957(iseries.wsbeans.getworkordervalidation.xsd.POI957Input param0) throws java.rmi.RemoteException{
    if (getWorkOrderValidationPortType == null)
      _initGetWorkOrderValidationPortTypeProxy();
    return getWorkOrderValidationPortType.poi957(param0);
  }
  
  public java.lang.String poi957_XML(iseries.wsbeans.getworkordervalidation.xsd.POI957Input param0) throws java.rmi.RemoteException{
    if (getWorkOrderValidationPortType == null)
      _initGetWorkOrderValidationPortTypeProxy();
    return getWorkOrderValidationPortType.poi957_XML(param0);
  }
  
  
}