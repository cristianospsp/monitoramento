package br.com.accisunm.monitoramento.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.accisunm.monitoramento.model.Funcionario;
import br.com.accisunm.monitoramento.repository.FuncionarioRepository;


@Stateless
public class FuncionarioService {

	@Inject
	private FuncionarioRepository repository;

	
	
	public void incluir(Funcionario funcionario) {
		repository.incluir(funcionario);
	}
}
