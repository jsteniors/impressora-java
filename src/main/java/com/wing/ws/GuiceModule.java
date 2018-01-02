package com.wing.ws;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.wing.dao.DAO;
import com.wing.model.Cartucho;
import com.wing.model.Categoria;
import com.wing.model.Impressora;
import com.wing.model.Marca;
import com.wing.model.Tipo;
import com.wing.model.Usuario;
import com.wing.util.LocalDateDeserializer;
import com.wing.util.LocalDateSerializer;
import com.wing.util.LocalTimeDeserializer;
import com.wing.util.LocalTimeSerializer;

public class GuiceModule implements Module {

	@Override
	public void configure(Binder binder) {
		
	}
	
	@Singleton
    @Provides
    public ObjectMapper objectMapper(){
    	SimpleModule module = new SimpleModule("simple-module", Version.unknownVersion());
    	module.addSerializer(new LocalTimeSerializer());
    	module.addSerializer(new LocalDateSerializer());
    	module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
    	module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(module);
        return mapper;
    }
    
    @Singleton
    @Provides
    public JacksonJsonProvider jacksonJsonProvider(ObjectMapper mapper){
        return new JacksonJsonProvider(mapper);
    }
    
    
    /*@Provides
    public JpaPersistModule persistModule() {
    	return new JpaPersistModule("impressorasPU");
    }*/
    
    @Singleton
    @Provides
    public DAO<Marca> getMarcaDAO(){
    	return new DAO<Marca>(Marca.class);
    }
    
    @Singleton
    @Provides
    public DAO<Impressora> getImpressoraDAO(){
    	return new DAO<Impressora>(Impressora.class);
    }
    
    @Singleton
    @Provides
    public DAO<Categoria> getCategoriaDAO(){
    	return new DAO<Categoria>(Categoria.class);
    }    
    
    @Singleton
    @Provides
    public DAO<Tipo> getTipoDAO(){
    	return new DAO<Tipo>(Tipo.class);
    }
    
    @Singleton
    @Provides
    public DAO<Cartucho> getCartuchoDAO(){
    	return new DAO<Cartucho>(Cartucho.class);
    }

    @Singleton
    @Provides
    public DAO<Usuario> getUsuarioDAO(){
    	return new DAO<Usuario>(Usuario.class);
    }
    
    /*@Singleton
    @Provides
    public JpaPersistanceService entityManagerProvider() {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("impressorasPU");
		return new JpaPersistanceService(emf);
    }*/
    
    /*@Singleton
    @Provides
    public TesteDAO getTesteDAO(EntityManager em) {
    	return new TesteDAO(em);
    }*/
    
}
