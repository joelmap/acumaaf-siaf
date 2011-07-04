package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Atividadefisica;

public class AtividadeFisicaDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public AtividadeFisicaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra uma nova Atividade Física
	public boolean save(Atividadefisica atividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(atividadeFisica); // persiste o objeto
			//System.out.println("Chamou o persist");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o Commit");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Atualiza determinada Atividade Física no banco de dados
	public boolean update(Atividadefisica atividadefisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			atividadefisica = em.merge(atividadefisica); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(atividadefisica); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada Atividade Física no banco de dados
	public boolean delete(Atividadefisica atividadefisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Atividadefisica ref = em.find(Atividadefisica.class, atividadefisica.getCodigoatividadefisica()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Atividade Física pelo código
	public Atividadefisica findAtividadeFisicaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT a FROM Atividadefisica a WHERE a.codigoatividadefisica =:codigo");
		query.setParameter("codigo", codigo);
		return (Atividadefisica)query.getSingleResult();
	}
	
	//Recupera uma lista de todas as Atividades Físicas cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Atividadefisica> findAtividadeFisicaAll() {
		Query query = em.createQuery("SELECT a FROM Atividadefisica a");
		List<Atividadefisica> listaAtividadeFisica = query.getResultList();
		return listaAtividadeFisica;
	}
}