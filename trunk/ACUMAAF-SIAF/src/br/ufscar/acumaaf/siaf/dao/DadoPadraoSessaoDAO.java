package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Dadopadraosessao;


public class DadoPadraoSessaoDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public DadoPadraoSessaoDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Registra um novo dado padrão para para participantes de uma sessão de atividade física
	public boolean save(Dadopadraosessao dadoPadraoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(dadoPadraoSessao); // persiste o objeto
			System.out.println("Chamou o persist");
			em.getTransaction().commit(); // encerra a transação
			System.out.println("Fez o Commit");
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}

	}

	// Atualiza determinado dado padrão para participantes de uma sessão de atividade física
	public boolean update(Dadopadraosessao dadoPadraoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			dadoPadraoSessao = em.merge(dadoPadraoSessao); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(dadoPadraoSessao); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado dado padrão para participantes de uma sessão de atividade física
	public boolean delete(Dadopadraosessao dadoPadraoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Dadopadraosessao ref = em.find(Dadopadraosessao.class, dadoPadraoSessao.getCodigodadopadraosessao()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um dado padrão para participantes de uma sessão de atividade física pelo cógido
	public Dadopadraosessao findDadoPadraoSessaoByCode(Dadopadraosessao dadoPadraoSessao) {
		Query query = em.createQuery("SELECT d FROM Dadopadraosessao d WHERE v.codigodadopadraosessao =:codigo");
		query.setParameter("codigo", dadoPadraoSessao.getCodigodadopadraosessao());
		return (Dadopadraosessao) query.getSingleResult();
	}

	//Recupera uma lista com todos os dados  padrões para participantes de sessões de atividade física
	@SuppressWarnings("unchecked")
	public List<Dadopadraosessao> findDadosPadroesSessaoAll() {
		Query query = em.createQuery("SELECT d FROM Dadopadraosessao d");
		List<Dadopadraosessao> listaDadosPadroesSessao = query.getResultList();
		return listaDadosPadroesSessao;
	}

}
