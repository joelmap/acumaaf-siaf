package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Ipaq;

public class IpaqDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public IpaqDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	// Cadastra um novo registro para IPAQ
	public boolean save(Ipaq ipaq) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(ipaq); // persiste o objeto
			//System.out.println("Chamou o persist");
			em.getTransaction().commit(); // encerra a transação
			System.out.println("Fez o Commit");
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Atualiza determinado registro do  IPAQ no banco de dados
	public boolean update(Ipaq ipaq) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			ipaq = em.merge(ipaq); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			System.out.println("Fez o commit");
			em.refresh(ipaq); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado registro do IPAQ no banco de dados
	public boolean delete(Ipaq ipaq) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Ipaq ref = em.find(Ipaq.class, ipaq.getCodigoipaq()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro do IPAQ pelo código
	public Ipaq findIpaqByCode(Integer codigo) {
		Query query = em.createQuery("SELECT a FROM Ipaq i WHERE i.codigoipaq =:codigo");
		query.setParameter("codigo", codigo);
		return (Ipaq)query.getSingleResult();
	}
	
	//Recupera uma lista todos os registros de IPAQ cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Ipaq> findIpaqAll() {
		Query query = em.createQuery("SELECT i FROM Ipaq i");
		List<Ipaq> listaIpaqs = query.getResultList();
		return listaIpaqs;
	}
}