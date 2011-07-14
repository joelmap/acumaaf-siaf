package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Sessao;

public class SessaoDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public SessaoDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra uma sessão de atividade física no banco de dados
	public boolean save(Sessao sessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(sessao); // persiste o objeto
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

	// Atualiza determinada sessão de atividade física 
	public boolean update(Sessao sessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			sessao = em.merge(sessao); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(sessao); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada sessão de atividade física no banco de dados
	public boolean delete(Sessao sessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Sessao ref = em.find(Sessao.class, sessao.getCodigosessao()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera uma sessão de atividade física pelo código
	public Sessao findSessaoByCode(Sessao sessao) {
		Query query = em.createQuery("SELECT s FROM Sessao s WHERE s.codigosessao =:codigo");
		query.setParameter("codigo", sessao.getCodigosessao());
		return (Sessao) query.getSingleResult();
	}

	//Recupera uma lista de todas sessões de atividade física cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Sessao> findSessaoAll() {
		Query query = em.createQuery("SELECT s FROM Sessao s");
		List<Sessao> listaSessoes = query.getResultList();
		return listaSessoes;
	}
	
	
	//Recupera uma lista de todas sessões de atividade física que não estão sincronizadas com o SMC
	@SuppressWarnings("unchecked")
	public List<Sessao> findListaSessoesNaoSincronizadas() {
		Query query = em.createQuery("SELECT s FROM Sessao s WHERE s.sincronizado = false");
		List<Sessao> listaSessoes = query.getResultList();
		return listaSessoes;
	}
	
	// Recupera uma sessão de atividade física pelo código inteiro
	public Sessao findSessaoByCode(Integer codigo) {
		Query query = em.createQuery("SELECT s FROM Sessao s WHERE s.codigosessao =:codigo");
		query.setParameter("codigo", codigo);
		return (Sessao) query.getSingleResult();
	}

}
