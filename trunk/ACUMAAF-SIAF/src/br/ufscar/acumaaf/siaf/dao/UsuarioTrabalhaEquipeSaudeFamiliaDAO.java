package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Usuariotrabalhaequipesaudefamilia;


public class UsuarioTrabalhaEquipeSaudeFamiliaDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public UsuarioTrabalhaEquipeSaudeFamiliaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra um novo vínculo entre Profissional de Saúde e Equipe de Saúde da Família
	public boolean save(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(usuarioTrabalhaEquipeSaudeFamilia); // persiste o objeto
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

	// Atualiza determinado vínculo entre Profissional de Saúde e Equipe de Saúde da Família
	public boolean update(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			usuarioTrabalhaEquipeSaudeFamilia = em.merge(usuarioTrabalhaEquipeSaudeFamilia); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(usuarioTrabalhaEquipeSaudeFamilia); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado vínculo entre Profissional de Saúde e Equipe de Saúde da Família no banco de dados
	public boolean delete(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Usuariotrabalhaequipesaudefamilia ref = em.find(Usuariotrabalhaequipesaudefamilia.class, usuarioTrabalhaEquipeSaudeFamilia.getCodigousuariotrabalhaequipesaudefamilia()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um vínculo entre Profissional de Saúde e Equipe de Saúde da Família pelo código
	public Usuariotrabalhaequipesaudefamilia findVinculoUsuarioTrabalhaEquipeSaudeFamiliaByCode(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia) {
		Query query = em.createQuery("SELECT u FROM Usuariotrabalhaequipesaudefamilia u WHERE u.codigousuariotrabalhaequipesaudefamilia =:codigo");
		query.setParameter("codigo", usuarioTrabalhaEquipeSaudeFamilia.getCodigousuariotrabalhaequipesaudefamilia());
		return (Usuariotrabalhaequipesaudefamilia)query.getSingleResult();
	}

	//Recupera uma lista de todos os vínculos entre os Profissionais de Saúde e Equipes de Saúde da Família cadastradas no banco de dados
	@SuppressWarnings("unchecked")
	public List<Usuariotrabalhaequipesaudefamilia> findUsuarioTrabalhaEquipeSaudeFamiliaAll() {
		Query query = em.createQuery("SELECT u FROM Usuariotrabalhaequipesaudefamilia u");
		List<Usuariotrabalhaequipesaudefamilia> listaUsuarioTrabalhaEquipeSaudeFamilia = query.getResultList();
		return listaUsuarioTrabalhaEquipeSaudeFamilia;
	}

}
