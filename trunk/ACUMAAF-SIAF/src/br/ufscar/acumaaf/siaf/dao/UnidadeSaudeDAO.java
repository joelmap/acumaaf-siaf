package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Unidadesaude;

public class UnidadeSaudeDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public UnidadeSaudeDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra uma nova unidade de saúde
	public boolean save(Unidadesaude unidadesaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(unidadesaude); // persiste o objeto
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

	// Atualiza determinada unidade de saúde no banco de dados
	public boolean update(Unidadesaude unidadesaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			unidadesaude = em.merge(unidadesaude); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(unidadesaude); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada unidade de saúde no banco de dados
	public boolean delete(Unidadesaude unidadesaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Unidadesaude ref = em.find(Unidadesaude.class, unidadesaude.getCodigounidadesaude()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera uma unidade de saúde pelo código
	public Unidadesaude findUnidadeSaudeByCode(Integer codigo) {
		Query query = em.createQuery("SELECT u FROM Unidadesaude u WHERE u.codigounidadesaude =:codigo");
		query.setParameter("codigo", codigo);
		return (Unidadesaude)query.getSingleResult();
	}

	//Recupera uma lista de todas as unidades de saúde cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Unidadesaude> findUnidadeSaudeAll() {
		Query query = em.createQuery("SELECT u FROM Unidadesaude u");
		List<Unidadesaude> listaUnidadeSaude = query.getResultList();
		return listaUnidadeSaude;
	}

}
