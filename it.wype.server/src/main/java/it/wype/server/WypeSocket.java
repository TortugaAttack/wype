package it.wype.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class WypeSocket{
	
  private SSLServerSocket s;

  public void open(){
	  
	try{
		
      this.s =(SSLServerSocket)SSLServerSocketFactory.getDefault().createServerSocket(5555, 5, InetAddress.getByName("0.0.0.0"));

  	  this.s.accept();
	} 
	catch(final IOException ex){

      throw new RuntimeException(ex);
	}
  }
}