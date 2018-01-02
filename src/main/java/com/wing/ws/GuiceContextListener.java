package com.wing.ws;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wing.ws.resource.MarcasResource;

public class GuiceContextListener extends GuiceServletContextListener{
	
    @Override
    protected Injector getInjector() {
    	/*Injector injector = Guice.createInjector(new ServletModuleConfig(), new JpaPersistModule("impressorasPU"), new GuiceModule());
    	injector.getInstance(JpaInitializer.class);*/
        return Guice.createInjector(new ServletModuleConfig(), new GuiceModule());
    }
    
    
    public static class ServletModuleConfig extends ServletModule {

        @Override
        protected void configureServlets() {
            // Pega o nome do pacote de resources a partir de um resource
            // qualquer
            String resources = MarcasResource.class.getPackage().getName();

            ResourceConfig config = new PackagesResourceConfig(resources);
        	
           
            //JpaPersistModule module = new JpaPersistModule("impressorasPU");

            //install(module);
            
            //filter("/*").through(PersistFilter.class);
            
            for (Class<?> resource : config.getClasses()) {
                bind(resource);
            }
            //bind(FileResource.class);
            
            HashMap<String, String> options = new HashMap<String, String>();
            
            options.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
//            options.put("com.sun.jersey.spi.container.ContainerRequestFilters", "com.wing.ws.filter.PersistRequestFilter");
            options.put("com.sun.jersey.spi.container.ContainerResponseFilters", "com.wing.ws.filter.AuthResponseFilter");
           
            serve("/impressora/*").with(GuiceContainer.class, options);

        }
    }
    /*class JpaInitializer {
    	@Inject
    	public JpaInitializer(PersistService persistService) {
    		persistService.start();
    	}
    }*/
}