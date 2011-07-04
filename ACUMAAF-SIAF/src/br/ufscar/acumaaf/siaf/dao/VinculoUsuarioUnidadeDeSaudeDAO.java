package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Vinculousuariounidadedesaude;

public class VinculoUsuarioUnidadeDeSaudeDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public VinculoUsuarioUnidadeDeSaudeDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra um novo vínculo entre usuário e unidade de saúde
	public boolean save(Vinculousuariounidadedesaude vinculoUsuarioUnidadeDeSaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(vinculoUsuarioUnidadeDeSaude); // persiste o objeto
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

	// Atualiza determinado vínculo entre usuário e uma unidade de saúde no banco de dados
	public boolean update(Vinculousuariounidadedesaude vinculoUsuarioUnidadeDeSaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			vinculoUsuarioUnidadeDeSaude = em.merge(vinculoUsuarioUnidadeDeSaude); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(vinculoUsuarioUnidadeDeSaude); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado vínculo entre usuário e uma unidade de saúde no banco de dados
	public boolean delete(Vinculousuariounidadedesaude vinculoUsuarioUnidadeDeSaude) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Vinculousuariounidadedesaude ref = em.find(Vinculousuariounidadedesaude.class, vinculoUsuarioUnidadeDeSaude.getCodigovinculo()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um vínculo de usuário com uma unidade de saúde pelo código
	public Vinculousuariounidadedesaude findVinculoUsuarioUnidadeSaudeByCode(Vinculousuariounidadedesaude vinculoUsuarioUnidadeDeSaude) {
		Query query = em.createQuery("SELECT v FROM Vinculousuariounidadesaude v WHERE v.codigovinculo =:codigo");
		query.setParameter("codigo", vinculoUsuarioUnidadeDeSaude.getCodigovinculo());
		return (Vinculousuariounidadedesaude)query.getSingleResult();
	}

	//Recupera uma lista todos os vínculos entre os usuários e as unidades de saúde cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Vinculousuariounidadedesaude> findVinculoUsuarioUnidadeSaudeAll() {
		Query query = em.createQuery("SELECT v FROM Vinculousuariounidadedesaude v");
		List<Vinculousuariounidadedesaude> listaVinculoUsuarioUnidadeSaude = query.getResultList();
		return listaVinculoUsuarioUnidadeSaude;
	}

}
