package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Indicador;

public class IndicadorDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public IndicadorDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra um novo Indicador
	public boolean save(Indicador indicador) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(indicador); // persiste o objeto
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
	
	// Atualiza determinado Indicador no banco de dados
	public boolean update(Indicador indicador) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			indicador = em.merge(indicador); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(indicador); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado Indicador no banco de dados
	public boolean delete(Indicador indicador) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Indicador ref = em.find(Indicador.class, indicador.getCodigoindicador()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Indicador pelo código
	public Indicador findIndicadorByCode(Integer codigo) {
		Query query = em.createQuery("SELECT i FROM Indicador i WHERE i.codigoindicador =:codigo");
		query.setParameter("codigo", codigo);
		return (Indicador)query.getSingleResult();
	}
	
	//Recupera uma lista com todos os Indicadores cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Indicador> findIndicadorAll() {
		Query query = em.createQuery("SELECT i FROM Indicador i");
		List<Indicador> listaIndicador = query.getResultList();
		return listaIndicador;
	}
}