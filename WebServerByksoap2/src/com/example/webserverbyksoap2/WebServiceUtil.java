package com.example.webserverbyksoap2;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
import org.ksoap2.SoapEnvelope;  
import org.ksoap2.SoapFault;  
import org.ksoap2.serialization.SoapObject;  
import org.ksoap2.serialization.SoapSerializationEnvelope;  
import org.ksoap2.transport.HttpTransportSE;  
import org.xmlpull.v1.XmlPullParserException;  


public class WebServiceUtil {
	
	// ����webservice�������ռ�   
    public static final String SERVICE_NAMESPACE = "http://WebXml.com.cn/";  
    // ����webservice�ṩ�����url   
    public static final String SERVICE_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";  

    // ����Զ��webservice��ȡʡ���б�   
    public static List<String> getProvinceList() {  
        // ���� �ķ���   
        String methodName = "getRegionProvince";  
        // ����HttpTransportSE�������   
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  

        try {  
            ht.debug = true;  
            // ʹ��SOAP1.1Э�鴴��Envelop����   
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
            // ʵ����SoapObject����   
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);  
            envelope.bodyOut = soapObject;  
            // ������.NET�ṩ��webservice���ֽϺõļ�����   
            envelope.dotNet = true;  
  
            // ����webservice   
            ht.call(SERVICE_NAMESPACE + methodName, envelope);  
            
            if (envelope.getResponse() != null) {  
                // ��ȡ��������Ӧ���ص�SOAP��Ϣ   
                SoapObject result = (SoapObject) envelope.bodyIn;  
                SoapObject detail = (SoapObject) result.getProperty(methodName + "Result");
                
                // ������������Ӧ��SOAP��Ϣ   
                return parseProvinceOrCity(detail);  
            }  
        } catch (SoapFault e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (XmlPullParserException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    // ����ʡ�ݻ�ȡ�����б�   
    public static List<String> getCityListsByProvince(String province) {  

        String methodName = "getSupportCityString";  

        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  
        
        ht.debug = true;  
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);  

        soapObject.addProperty("theRegionCode", province);  
        
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        
        envelope.bodyOut = soapObject;  
        envelope.dotNet = true;  
  
        // ����webservice   
        try {  
            ht.call(SERVICE_NAMESPACE + methodName, envelope);  
            if (envelope.getResponse() != null) {  
                // ��ȡ��������Ӧ���ص�SOAP��Ϣ   
                SoapObject result = (SoapObject) envelope.bodyIn;  
                SoapObject detail = (SoapObject) result.getProperty(methodName  
                        + "Result");  
                // ������������Ӧ��SOAP��Ϣ   
                return parseProvinceOrCity(detail);  
            }  
        } catch (SoapFault e) {  
            e.printStackTrace();  
        } catch (IOException e) {   
            e.printStackTrace();  
        } catch (XmlPullParserException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    // ����ʡ�ݻ����   
    public static List<String> parseProvinceOrCity(SoapObject detail) {  
        ArrayList<String> result = new ArrayList<String>();  
        for (int i = 0; i < detail.getPropertyCount(); i++) {  
            // ������ÿ��ʡ��   
            result.add(detail.getProperty(i).toString().split(",")[0]);  
        }  
        return result;  
    }  
  
    // ���ݳ����ַ�����ȡ��Ӧ�������   
    public static SoapObject getWeatherByCity(String cityName) {  
        String methodName = "getWeather";  
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  
        ht.debug = true;  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);  
        soapObject.addProperty("theCityCode", cityName);  
        envelope.bodyOut = soapObject;  
        envelope.dotNet = true;  
  
        try {  
            ht.call(SERVICE_NAMESPACE + methodName, envelope);  
            SoapObject result = (SoapObject) envelope.bodyIn;  
            SoapObject detail = (SoapObject) result.getProperty(methodName + "Result");  
            return detail;  
        } catch (IOException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (XmlPullParserException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }  
        return null;  
    }  
    

    /*
     * DataSet����ֵ����
     */
    public static void getRegionDataset(){
    	// ���� �ķ���   
        String methodName = "getRegionDataset";  
        // ����HttpTransportSE�������   
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  

        try {  
            ht.debug = true;  
            // ʹ��SOAP1.1Э�鴴��Envelop����   
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
            // ʵ����SoapObject����   
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);  
            envelope.bodyOut = soapObject;  
            // ������.NET�ṩ��webservice���ֽϺõļ�����   
            envelope.dotNet = true;  
  
            // ����webservice   
            ht.call(SERVICE_NAMESPACE + methodName, envelope);  
            
            if (envelope.getResponse() != null) { 
            	
            	soapObject = (SoapObject) envelope.bodyIn;
            	
            	SoapObject soap1 = (SoapObject)soapObject.getProperty(0);
            	
            	SoapObject soap2 = (SoapObject)soap1.getProperty(1);

            	for (int i = 0; i < soap2.getPropertyCount(); i++) 
            	{
            		 SoapObject soap3=(SoapObject) soap2.getProperty(i);
            		 
            		 for (int j=0;j<soap3.getPropertyCount();j++){
            			 SoapObject soap4=(SoapObject) soap3.getProperty(j);
            			 System.out.println(soap4.getProperty(0).toString()+" "+soap4.getProperty(1).toString());
            		 }
	           	}

            }
            
        } catch (SoapFault e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (XmlPullParserException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }  
  
    }


    /*
     * ��������DataSet����ֵ����
     * @param cityCode
     */
    public static void getSupportCityDataset(String cityCode){
    	// ���� �ķ���   
        String methodName = "getSupportCityDataset";  
        // ����HttpTransportSE�������   
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  

        try {  
            ht.debug = true;  
            // ʹ��SOAP1.1Э�鴴��Envelop����   
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
            // ʵ����SoapObject����   
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);  
            
            //������
            soapObject.addProperty("theRegionCode", cityCode);  
            
            envelope.bodyOut = soapObject;  
            // ������.NET�ṩ��webservice���ֽϺõļ�����   
            envelope.dotNet = true;  
  
            // ����webservice   
            ht.call(SERVICE_NAMESPACE + methodName, envelope);  
            
            if (envelope.getResponse() != null) { 
            	
            	soapObject = (SoapObject) envelope.bodyIn;
            	
            	SoapObject soap1 = (SoapObject)soapObject.getProperty(0);
            	
            	SoapObject soap2 = (SoapObject)soap1.getProperty(1);

            	for (int i = 0; i < soap2.getPropertyCount(); i++) 
            	{
            		 SoapObject soap3=(SoapObject) soap2.getProperty(i);
            		 
            		 for (int j=0;j<soap3.getPropertyCount();j++){
            			 SoapObject soap4=(SoapObject) soap3.getProperty(j);
            			 System.out.println(soap4.getProperty(0).toString()+" "+soap4.getProperty(1).toString());
            		 }
	           	}

            }
            
        } catch (SoapFault e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (XmlPullParserException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }  
  
    }
}






























