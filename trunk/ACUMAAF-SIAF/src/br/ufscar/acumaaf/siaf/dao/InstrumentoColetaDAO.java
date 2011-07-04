package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Instrumentocoleta;


public class InstrumentoColetaDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public InstrumentoColetaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra um novo Instrumento de Coleta
	public boolean save(Instrumentocoleta instrumentoColeta) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(instrumentoColeta); // persiste o objeto
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
	
	// Atualiza determinado Instrumento de Coleta no banco de dados
	public boolean update(Instrumentocoleta instrumentoColeta) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			instrumentoColeta = em.merge(instrumentoColeta); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(instrumentoColeta); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado Instrumento de Coleta no banco de dados
	public boolean delete(Instrumentocoleta instrumentoColeta) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Instrumentocoleta ref = em.find(Instrumentocoleta.class, instrumentoColeta.getCodigoinstrumentocoleta()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Instrumento de Coleta pelo código
	public Instrumentocoleta findInstrumentoColetaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT i FROM Instrumentocoleta i WHERE i.codigoinstrumentocoleta =:codigo");
		query.setParameter("codigo", codigo);
		return (Instrumentocoleta)query.getSingleResult();
	}
	
	//Recupera uma lista com todos os Instrumentos de Coleta cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Instrumentocoleta> findInstrumentoColetaAll() {
		Query query = em.createQuery("SELECT i FROM Instrumentocoleta i");
		List<Instrumentocoleta> listaInstrumentoColeta = query.getResultList();
		return listaInstrumentoColeta;
	}
}