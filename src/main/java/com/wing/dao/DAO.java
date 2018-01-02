package com.wing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.wing.util.JPAUtil;
import com.wing.util.Parameter;

public class DAO<T> {
	
	private Class<T> classe;
	
	public DAO(Class<T> classe){
		this.classe = classe;
	}
	
	public DAO(Class<T> classe, EntityManager em){
		this.classe = classe;
	}
	
	
	 public void insert(T entity){
		 /*EntityManager manager = JPAUtil.managerByContext(req);
		 manager.persist(entity);*/
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
	 }
	    public List<T> listAll(){
//	    	EntityManager manager = JPAUtil.managerByContext(req);
	        EntityManager manager = JPAUtil.getEntityManager();
	        CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
	        query.select(query.from(classe));
	        TypedQuery<T> typed = manager.createQuery(query);
	        typed.setHint("org.hibernate.cacheable", "true");
	        List<T> result = typed.getResultList();
	        manager.close();
	        return  result;
	    }
	   
	    public void delete(T entity){
	    	/*EntityManager manager = JPAUtil.managerByContext(req);
	    	manager.remove(entity);*/
	    	EntityManager manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.remove(manager.merge(entity));
			manager.getTransaction().commit();
			manager.close();
	    }
	    
	    public void update(T entity){
	    	/*EntityManager manager = JPAUtil.managerByContext(req);
	    	manager.merge(entity);*/
	        EntityManager manager = JPAUtil.getEntityManager();
	        manager.getTransaction().begin();
	        manager.merge(entity);
	        manager.getTransaction().commit();
	        manager.close();
	    }

	    public T findbyId(Integer id){
	    	//EntityManager manager = JPAUtil.managerByContext(req);
	        EntityManager manager = JPAUtil.getEntityManager();
	        T result = manager.find(classe, id);
	        manager.close();
	        return result;
	    }
	    
	    public List<T> listByQuery(String queryName,List<Parameter> params){
//	    	EntityManager manager = JPAUtil.managerByContext(req);
	        EntityManager manager = JPAUtil.getEntityManager();
	        Query query = manager.createNamedQuery(queryName);
	        for(Parameter param:params){
	        	if(!param.isStringConcat())
	        		query.setParameter(param.getKey(), param.getValue());
	        	else query.setParameter(param.getKey(), "%"+param.getValue()+"%");
	        }
	        List<T> result = query.getResultList();
	        manager.close();
	        return result;
	    }
	    
	    public T findByQuery(String queryName,List<Parameter> params){
	        try {
//	        	EntityManager manager = JPAUtil.managerByContext(req);
	            EntityManager manager = JPAUtil.getEntityManager();
	            Query query = manager.createNamedQuery(queryName).setMaxResults(1);
	            for (Parameter param : params){
	            	if(!param.isStringConcat())
	            		query.setParameter(param.getKey(), param.getValue());
	            	else query.setParameter(param.getKey(), "%"+param.getValue()+"%");
	            	
	            }
	            T result = (T)query.getSingleResult();
	            manager.close();
	            return result;
	        }catch (NoResultException ex){
	            return null;
	        }
	    }
	    
	    /*public List<T> lists(){
	    	CriteriaBuilder builder = em.getCriteriaBuilder();
	    	CriteriaQuery<T> criteriaQuery= builder.createQuery(classe);
	    	TypedQuery<T> query = em.createQuery(criteriaQuery);
	    	return query.getResultList();
	    }*/

}
