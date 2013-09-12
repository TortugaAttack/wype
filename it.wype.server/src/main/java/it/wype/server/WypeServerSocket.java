package it.wype.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class WypeServerSocket{
	
  private SSLServerSocket s;
  
  @Inject
  private Logger log;

  public void open(){
	  
	try{
		
      this.s =(SSLServerSocket)SSLServerSocketFactory.getDefault().createServerSocket(5555, 5, InetAddress.getByName("0.0.0.0"));

      this.log.info(String.format("Opening sslss %s. ", this.s));
      
  	  this.s.accept();
	} 
	catch(final IOException ex){

      throw new RuntimeException(ex);
	}
  }
}