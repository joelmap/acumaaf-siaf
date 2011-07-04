package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Periodicidade;


public class PeriodicidadeDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public PeriodicidadeDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra um novo registro para Periodicidade
	public boolean save(Periodicidade periodicidade) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(periodicidade); // persiste o objeto
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
	
	// Atualiza determinado registro de Periodicidade no banco de dados
	public boolean update(Periodicidade periodicidade) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			periodicidade = em.merge(periodicidade); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(periodicidade); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado registro de Periodicidade no banco de dados
	public boolean delete(Periodicidade periodicidade) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Periodicidade ref = em.find(Periodicidade.class, periodicidade.getCodigoperiodicidade()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Periodicidade pelo código
	public Periodicidade findPeriodicidadeByCode(Integer codigo) {
		Query query = em.createQuery("SELECT p FROM Periodicidade p WHERE p.codigoperiodicidade =:codigo");
		query.setParameter("codigo", codigo);
		return (Periodicidade)query.getSingleResult();
	}
	
	//Recupera uma lista com todos os registros de Periodicidadetodas cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Periodicidade> findPeriodicidadeAll() {
		Query query = em.createQuery("SELECT p FROM Periodicidade p");
		List<Periodicidade> listaPeriodicidades = query.getResultList();
		return listaPeriodicidades;
	}
}