package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Dadosessao;

public class RelatorioDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public RelatorioDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Recupera um dado de uma sessão de atividade física pelo cógido
	public Dadosessao findDadoSessaoByCode(Dadosessao dadoSessao) {
		Query query = em.createQuery("SELECT d FROM Dadosessao d WHERE v.codigodadosessao =:codigo");
		query.setParameter("codigo", dadoSessao.getCodigodadosessao());
		return (Dadosessao)query.getSingleResult();
	}

	//Recupera uma lista com todos os dados de sessões de atividade física
	@SuppressWarnings("unchecked")
	public List<Dadosessao> findDadosSessaoAll() {
		Query query = em.createQuery("SELECT d FROM Dadosessao d");
		List<Dadosessao> listaDadosSessao = query.getResultList();
		return listaDadosSessao;
	}

}
