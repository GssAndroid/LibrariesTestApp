package com.example.ws1;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class api extends Activity {

	final static String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
	final static String METHOD_NAME = "ZGssmwfmTestApi1"; //Operation Name form WSDL
	final static String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0B1187AA52617F1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
	final static String URL ="http://75.99.152.10:7500/sap/bc/srt/rfc/sap/z_gssmwfm_test_api1/110/z_gssmwfm_test_api1/z_gssmwfm_test_api1"; // address location
	
	private static volatile String result=null;


	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.api_main);
	}	 
		
		 public String getResult(){
	
		    	 /*** Application logic here *********/	
		  try {
			  
			  Thread thread = new Thread(new Runnable() {

				    @Override
				    public void run() {
				        try  {
				         
				        	 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				   		     SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				   		            SoapEnvelope.VER11);
				   		     envelope.setOutputSoapObject(request);
				        	 AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
				        	 androidHttpTransport.call(SOAP_ACTION, envelope);
				        	 result = envelope.getResponse().toString();
						     System.out.println("result is : " +result);
						     
						     runOnUiThread(new Runnable() {
						            public void run() {
						              TextView tv = (TextView)findViewById(R.id.tv);
						              tv.setText(result);
						            }
						          });

						     
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				        
				    }
				    
				});
			  
			 	thread.start();
			 		     
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return result;
		  
		}

	
}

