package br.com.accisunm.monitoramento.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMember;
import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMonitoramento;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
	   // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
	   @SuppressWarnings("unused")
	   //@Produces
	   @PersistenceContext(unitName="primary")
	   private EntityManager em;
	   
	   @PersistenceContext(unitName="monitoramentoMySql")
	   private EntityManager emMonit;
	  
	   
	   
	   @Produces
	   public Logger produceLog(InjectionPoint injectionPoint) {
	      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	   }
	   
	   @Produces
	   @PersistenceUnitMonitoramento
	   public EntityManager entityManagerMonitoramento(){
		   return emMonit;
	   }
	   
	   @Produces
	   @PersistenceUnitMember
	   @Default
	   public EntityManager entityManagerMember(){
		   return em;
	   
	   }
	   
	   
	  /* public void close(@Disposes EntityManager entityManager){
		   entityManager.close();
	   }*/
	   
	   
	}
