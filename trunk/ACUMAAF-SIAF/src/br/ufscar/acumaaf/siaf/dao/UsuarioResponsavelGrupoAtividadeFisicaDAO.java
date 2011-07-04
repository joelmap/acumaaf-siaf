package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Usuarioresponsavelgrupoatividadefisica;

public class UsuarioResponsavelGrupoAtividadeFisicaDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public UsuarioResponsavelGrupoAtividadeFisicaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra um vínculo de usuário responsável para um grupo de atividade física
	public boolean save(Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(usuarioResponsavelGrupoAtividadeFisica); // persiste o objeto
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

	// Atualiza determinado vínculo de um usuário responsável para um grupo de atividade física
	public boolean update(Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			usuarioResponsavelGrupoAtividadeFisica = em.merge(usuarioResponsavelGrupoAtividadeFisica); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(usuarioResponsavelGrupoAtividadeFisica); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado vínculo entre usuário responsável e um grupo de atividade fisica no banco de dados
	public boolean delete(Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Usuarioresponsavelgrupoatividadefisica ref = em.find(Usuarioresponsavelgrupoatividadefisica.class, usuarioResponsavelGrupoAtividadeFisica.getCodigousuarioresponsavelgrupoatividadefisica()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um vínculo de usuário responsável e um grupo de atividade física
	public Usuarioresponsavelgrupoatividadefisica findUsuarioResponsavelGrupoAtividadeFisicaByCode(Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica) {
		Query query = em.createQuery("SELECT u FROM Usuarioresponsavelgrupoatividadefisica u WHERE u.codigousuarioresponsavelgrupoatividadefisica =:codigo");
		query.setParameter("codigo", usuarioResponsavelGrupoAtividadeFisica.getCodigousuarioresponsavelgrupoatividadefisica());
		return (Usuarioresponsavelgrupoatividadefisica) query.getSingleResult();
	}

	//Recupera uma lista todos os vínculos entre os usuários responsáveis e os grupos de atividade física cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Usuarioresponsavelgrupoatividadefisica> findUsuarioResponsavelGrupoAtividadeFisicaAll() {
		Query query = em.createQuery("SELECT u FROM Usuarioresponsavelgrupoatividadefisica u");
		List<Usuarioresponsavelgrupoatividadefisica> listaUsuarioResponsavelGrupoAtividadeFisica = query.getResultList();
		return listaUsuarioResponsavelGrupoAtividadeFisica;
	}

}
