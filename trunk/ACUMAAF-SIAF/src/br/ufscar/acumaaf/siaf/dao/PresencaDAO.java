package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Presenca;

public class PresencaDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public PresencaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra uma nova Presença
	public void save(Presenca presenca) {
		em.getTransaction().begin(); // inicia uma transação
			try {
				System.out.println("Fez o Begin");
				em.persist(presenca); // persiste o objeto
				System.out.println("Chamou o persist");
				em.getTransaction().commit(); // encerra a transação
				System.out.println("Fez o Commit");
				em.refresh(presenca);
			} catch (Exception e) {
				System.out.println("Entrou no rollback");
				em.getTransaction().rollback(); // desfaz a transação
			}
	}
	
	// Atualiza determinada Presença no banco de dados
	public boolean update(Presenca presenca) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			presenca = em.merge(presenca); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(presenca); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada Presença no banco de dados
	public boolean delete(Presenca presenca) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Presenca ref = em.find(Presenca.class, presenca.getCodigopresenca()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Presença pelo código
	public Presenca findPresencaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT p FROM Presenca p WHERE p.codigopresenca =:codigo");
		query.setParameter("codigo", codigo);
		return (Presenca)query.getSingleResult();
	}
	
	// Recupera um registro de Presença pelo código da sessao e pelo codigo do usuario
	@SuppressWarnings("unchecked")
	public List<Presenca> findPresencaByCodeUserAndCodeSessao(Presenca presenca) {
		Query query = em.createQuery("SELECT p FROM Presenca p WHERE p.usuario.codigousuario =:codigo AND p.sessao.codigosessao =:sessao");
		query.setParameter("codigo", presenca.getUsuario().getCodigousuario());
		query.setParameter("sessao", presenca.getSessao().getCodigosessao());
		return (List<Presenca>) query.getResultList();
	}
	
	//Recupera uma lista de todas as Presenças cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Presenca> findPresencaAll() {
		Query query = em.createQuery("SELECT p FROM Presenca p");
		List<Presenca> listaAtividadeFisica = query.getResultList();
		return listaAtividadeFisica;
	}
}