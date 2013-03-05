package br.com.accisunm.monitoramento.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.accisunm.monitoramento.model.Funcionario;
import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMember;
import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMonitoramento;
import br.com.accisunm.monitoramento.repository.FuncionarioRepository;
import br.com.accisunm.monitoramento.service.FuncionarioService;
import br.com.accisunm.monitoramento.util.Resources;

@RunWith(Arquillian.class)
public class FuncionarioTest {
	
	
   @Deployment
   public static Archive<?> createTestArchive() {
	   Archive<?> archive = ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Resources.class,FuncionarioRepository.class,FuncionarioService.class,Funcionario.class,PersistenceUnitMember.class,PersistenceUnitMonitoramento.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // Deploy our test datasource
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
	   System.out.println(archive.toString(true));
	   return archive;
   }
	
	@Inject
	private FuncionarioService service;
	
	@Inject
	private Logger log;
	
	@Test
	public void incluir(){
		log.info("Executando teste de inclusão de Funcionario");
		Funcionario funcionario = criarFuncionario("Danilo 1","daniloteste@test.com.br",123456l,"tecnico","dep tecnico");
		service.incluir(funcionario);
		assertNotNull(funcionario.getId());
		log.info("Funcionario "+funcionario.getNome() + " incluido com ID: "+funcionario.getId());
	}
	
	
	@Test(expected=Exception.class)
	public void incluirFuncionarioEmailRepetido(){
		log.info("incluindo primeiro funcionario...");
		Funcionario funcionario = criarFuncionario("Danilo 1","daniloteste@test.com.br",123459l,"tecnico","dep tecnico");
		service.incluir(funcionario);
		assertNotNull(funcionario.getId());
		
		log.info("tentando incluir o segundo funcionario com Email repetido...");
		
		Funcionario funcionario2 = criarFuncionario("Danilo 1","daniloteste@test.com.br",123457l,"tecnico","dep tecnico");
		
		service.incluir(funcionario2);
		assertNull(funcionario2.getId());
	}
	
	
	
	
	

	private Funcionario criaFuncionarioPadrao(){
		return criarFuncionario("Func Padrão","padrao@test.com.br",99828888l,"padrao","dep padrão");
	}
	
	
	private Funcionario criarFuncionario(String nome,String email,Long matricula,String funcao, String departamento) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setEmail(email);
		funcionario.setMatricula(matricula);
		funcionario.setFuncao(funcao);
		funcionario.setDepartamento(departamento);
		funcionario.setNascimento(new Date());
		return funcionario;
	}
	
	
}
