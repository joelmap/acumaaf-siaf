package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Msnusuario;

public class MsnUsuarioDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public MsnUsuarioDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra uma nova mensagem
	public boolean save(Msnusuario msn) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(msn); // persiste o objeto
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
	
	// Atualiza determinada mensagem no banco de dados
	public boolean update(Msnusuario msn) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			msn = em.merge(msn); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(msn); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada mensagem no banco de dados
	public boolean delete(Msnusuario msn) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Msnusuario ref = em.find(Msnusuario.class, msn.getCodigomsnusuario()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera uma mensagem pelo código
	public Msnusuario findMsnByCode(Integer codigo) {
		Query query = em.createQuery("SELECT m FROM Msnusuario m WHERE m.codigomsnusuario =:codigo");
		query.setParameter("codigo", codigo);
		return (Msnusuario)query.getSingleResult();
	}
	
	//Recupera uma lista de todas as mensagens cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Msnusuario> findMsnAll() {
		Query query = em.createQuery("SELECT m FROM Msnusuario m");
		List<Msnusuario> listaMsn = query.getResultList();
		return listaMsn;
	}
	
	//Recupera uma lista de todas as mensagens que ainda não foram lidas 
	@SuppressWarnings("unchecked")
	public List<Msnusuario> findMsnNaoLida(Integer codigo) {
		Query query = em.createQuery("SELECT m FROM Msnusuario m WHERE m.statusmensagem =: false AND m.usuario2.codigousuario =:codigo");
		query.setParameter("codigo", codigo);
		List<Msnusuario> listaMsn = query.getResultList();
		return listaMsn;
	}
	
	//Recupera uma lista de todas as mensagens que foram lidas 
	@SuppressWarnings("unchecked")
	public List<Msnusuario> findMsnLida(Integer codigo) {
		Query query = em.createQuery("SELECT m FROM Msnusuario m WHERE m.statusmensagem =: true AND m.usuario2.codigousuario =:codigo");
		query.setParameter("codigo", codigo);
		List<Msnusuario> listaMsn = query.getResultList();
		return listaMsn;
	}
}