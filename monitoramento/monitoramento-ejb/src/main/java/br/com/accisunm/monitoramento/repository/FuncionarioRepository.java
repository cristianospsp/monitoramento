package br.com.accisunm.monitoramento.repository;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.accisunm.monitoramento.model.Funcionario;
import br.com.accisunm.monitoramento.qualifier.PersistenceUnitMonitoramento;


public class FuncionarioRepository {

	@Inject
	@PersistenceUnitMonitoramento
	private EntityManager entityManager;
	
	@TransactionAttribute
	public void incluir(Funcionario funcionario) {
		//throw new RuntimeException("ainda não foi implementado");
		entityManager.persist(funcionario);
	}

	
}
