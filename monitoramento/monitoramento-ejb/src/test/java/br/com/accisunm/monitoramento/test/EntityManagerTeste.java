package br.com.accisunm.monitoramento.test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMember;
import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMonitoramento;
import br.com.accisunm.monitoramento.util.Resources;

@RunWith(Arquillian.class)
public class EntityManagerTeste {

	
	@Deployment
	   public static Archive<?> createTestArchive() {
	      
		   Archive<?> archive = ShrinkWrap.create(WebArchive.class, "test.war")
	            .addClasses(Resources.class,PersistenceUnitMember.class,PersistenceUnitMonitoramento.class)
	            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	            // Deploy our test datasource
	            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
		   
		   System.out.println(archive.toString(true));
		   
		   return archive;
	   }
	
	
	@Inject 
	@PersistenceUnitMonitoramento
	EntityManager em;
	
	@Test
	public void teste(){
		
		Assert.assertNotNull(em);
	}
	
	/*@Test
	public void testeEhEntityManagerMember(){
		Assert.assertEquals(expected, em.getEntityManagerFactory().getPersistenceUnitUtil().)
	}*/
	
}
