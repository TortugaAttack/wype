package it.wype.server;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main{

  public static void main(String[] args){
	  
    final WeldContainer weld = new Weld().initialize();
      
    final Server s =weld.instance().select(Server.class).get();
    
    s.start();
  }
}