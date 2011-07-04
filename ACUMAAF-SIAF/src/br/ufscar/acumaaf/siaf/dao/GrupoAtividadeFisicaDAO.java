package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;


public class GrupoAtividadeFisicaDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public GrupoAtividadeFisicaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}
	
	// Cadastra um novo Grupo de Atividade Física
	public boolean save(Grupoatividadefisica grupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			em.persist(grupoAtividadeFisica); // persiste o objeto
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
	
	// Atualiza determinado Grupo de Atividade Física no banco de dados
	public boolean update(Grupoatividadefisica grupoAtividadefisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			//System.out.println("Fez o Begin");
			grupoAtividadefisica = em.merge(grupoAtividadefisica); // persiste o objeto e devolve sincronizado
			//System.out.println("Fez o merge");
			em.getTransaction().commit(); // encerra a transação
			//System.out.println("Fez o commit");
			em.refresh(grupoAtividadefisica); // atualiza a referência do objeto
			//System.out.println("Fez o refresh");
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado Grupo de Atividade Física no banco de dados
	public boolean delete(Grupoatividadefisica grupoAtividadefisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Grupoatividadefisica ref = em.find(Grupoatividadefisica.class, grupoAtividadefisica.getCodigogrupoatividadefisica()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			//System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um registro de Grupo de Atividade Física pelo código
	public Grupoatividadefisica findGrupoAtividadeFisicaByCode(Integer codigo) {
		Query query = em.createQuery("SELECT g FROM Grupoatividadefisica g WHERE g.codigogrupoatividadefisica =:codigo");
		query.setParameter("codigo", codigo);
		return (Grupoatividadefisica)query.getSingleResult();
	}
	
	//Recupera uma lista com todos os Grupo de Atividades Físicas cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Grupoatividadefisica> findGrupoAtividadeFisicaAll() {
		Query query = em.createQuery("SELECT g FROM Grupoatividadefisica g");
		List<Grupoatividadefisica> listaGrupoAtividadeFisica = query.getResultList();
		return listaGrupoAtividadeFisica;
	}
}