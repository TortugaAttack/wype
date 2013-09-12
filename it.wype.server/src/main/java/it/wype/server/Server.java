package it.wype.server;

import javax.inject.Inject;

public class Server{

  @Inject
  private WypeSocket ws;
	
  public void start(){
	  
	this.ws.open();
  }
}