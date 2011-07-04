package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Diasemana;


public class DiaSemanaDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public DiaSemanaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra um novo registro para Dias da Semana
	public boolean save(Diasemana diaSemana) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(diaSemana); // persiste o objeto
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
	
	// Atualiza determinado registro de Dias da Semana no banco de dados
	public boolean update(Diasemana diaSemana) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			diaSemana = em.merge(diaSemana); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(diaSemana); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado registro de Dias da Semana no banco de dados
	public boolean delete(Diasemana diaSemana) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Diasemana ref = em.find(Diasemana.class, diaSemana.getCodigodiasemana()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Dias da Semana pelo código
	public Diasemana findDiaSemanaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT d FROM Diasemana d WHERE d.codigodiasemana =:codigo");
		query.setParameter("codigo", codigo);
		return (Diasemana)query.getSingleResult();
	}
	
	//Recupera uma lista com todos os registros de Dias da Semana cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Diasemana> findDiaSemanaAll() {
		Query query = em.createQuery("SELECT d FROM Diasemana d");
		List<Diasemana> listaDiaSemana = query.getResultList();
		return listaDiaSemana;
	}
}