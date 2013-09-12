package it.wype.client;

import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class WypeSocket{
	
	private SSLSocket s;
	
	private void connect(String host){
		
		try{
			
			this.s = (SSLSocket) SSLSocketFactory.getDefault().createSocket(host, 5555);
		
		}
		catch(final IOException ex){
			throw new RuntimeException(ex);
		}
	}
	
	private void send(){
		
	}
}