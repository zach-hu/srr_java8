package iseries.wsbeans.updatestockorder;

public class UpdateStockOrderPortTypeProxy implements iseries.wsbeans.updatestockorder.UpdateStockOrderPortType {
  private String _endpoint = null;
  private iseries.wsbeans.updatestockorder.UpdateStockOrderPortType updateStockOrderPortType = null;
  
  public UpdateStockOrderPortTypeProxy() {
    _initUpdateStockOrderPortTypeProxy();
  }
  
  public UpdateStockOrderPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initUpdateStockOrderPortTypeProxy();
  }
  
  private void _initUpdateStockOrderPortTypeProxy() {
    try {
      updateStockOrderPortType = (new iseries.wsbeans.updatestockorder.UpdateStockOrderLocator()).getUpdateStockOrderSOAP11port_http();
      if (updateStockOrderPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)updateStockOrderPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)updateStockOrderPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (updateStockOrderPortType != null)
      ((javax.xml.rpc.Stub)updateStockOrderPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.updatestockorder.UpdateStockOrderPortType getUpdateStockOrderPortType() {
    if (updateStockOrderPortType == null)
      _initUpdateStockOrderPortTypeProxy();
    return updateStockOrderPortType;
  }
  
  public java.lang.String poi961_XML(iseries.wsbeans.updatestockorder.xsd.POI961Input param0) throws java.rmi.RemoteException{
    if (updateStockOrderPortType == null)
      _initUpdateStockOrderPortTypeProxy();
    return updateStockOrderPortType.poi961_XML(param0);
  }
  
  public iseries.wsbeans.updatestockorder.xsd.POI961Result poi961(iseries.wsbeans.updatestockorder.xsd.POI961Input param0) throws java.rmi.RemoteException{
    if (updateStockOrderPortType == null)
      _initUpdateStockOrderPortTypeProxy();
    return updateStockOrderPortType.poi961(param0);
  }
  
  
}