package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Equipesaudefamilia;


public class EquipeSaudeFamiliaDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public EquipeSaudeFamiliaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra uma nova Equipe de Saúde da Família
	public boolean save(Equipesaudefamilia equipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(equipeSaudeFamilia); // persiste o objeto
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

	// Atualiza determinada Equipe de Saúde da Família no banco de dados
	public boolean update(Equipesaudefamilia equipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			equipeSaudeFamilia = em.merge(equipeSaudeFamilia); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(equipeSaudeFamilia); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada Equipe de Saúde da Família no banco de dados
	public boolean delete(Equipesaudefamilia equipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Equipesaudefamilia ref = em.find(Equipesaudefamilia.class, equipeSaudeFamilia.getCodigoequipesaudefamilia()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera uma Equipe de Saúde da Família pelo código
	public Equipesaudefamilia findEquipeSaudeFamiliaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT e FROM Equipesaudefamilia e WHERE e.codigoequipesaudefamilia =:codigo");
		query.setParameter("codigo", codigo);
		return (Equipesaudefamilia)query.getSingleResult();
	}

	//Recupera uma lista de todas as Equipe de Saúde da Família cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Equipesaudefamilia> findEquipeSaudeFamiliaAll() {
		Query query = em.createQuery("SELECT e FROM Equipesaudefamilia e");
		List<Equipesaudefamilia> listaUnidadeSaude = query.getResultList();
		return listaUnidadeSaude;
	}

}
