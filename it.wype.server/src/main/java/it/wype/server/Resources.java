package it.wype.server;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class Resources{
	
  @Produces
  private Logger produceLogger(final InjectionPoint ip){
	
	return Logger.getLogger(ip.getBean().getBeanClass().getName());
  }
}