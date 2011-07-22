package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Matriculausuariogrupoatividadefisica;


public class MatriculaUsuarioGrupoAtividadeFisicaDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public MatriculaUsuarioGrupoAtividadeFisicaDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra uma nova matrícula de um usuário no grupo de atividade física
	public boolean save(Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(matriculaUsuarioGrupoAtividadeFisica); // persiste o objeto
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

	// Atualiza determinada matrícula de um usuário no Grupo de Atividade Física
	public boolean update(Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			matriculaUsuarioGrupoAtividadeFisica = em.merge(matriculaUsuarioGrupoAtividadeFisica); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(matriculaUsuarioGrupoAtividadeFisica); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinada matricula de um usuário no grupo de atividade física
	public boolean delete(Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Matriculausuariogrupoatividadefisica ref = em.find(Matriculausuariogrupoatividadefisica.class, matriculaUsuarioGrupoAtividadeFisica.getCodigomatriculausuariogrupoatividadefisica()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera uma matrícula de um usuário no grupo de Atividade Física
	public Matriculausuariogrupoatividadefisica findMatriculaUsuarioGrupoAtividadeFisicaByCode(Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica) {
		Query query = em.createQuery("SELECT m FROM Matriculausuariogrupoatividadefisica m WHERE m.codigomatriculausuariogrupoatividadefisica =:codigo");
		query.setParameter("codigo", matriculaUsuarioGrupoAtividadeFisica.getCodigomatriculausuariogrupoatividadefisica());
		return (Matriculausuariogrupoatividadefisica)query.getSingleResult();
	}

	//Recupera uma lista todos os vínculos entre os usuários e grupos de atividade física cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Matriculausuariogrupoatividadefisica> findMatriculaUsuarioGrupoAtividadeFisicaAll() {
		Query query = em.createQuery("SELECT m FROM Matriculausuariogrupoatividadefisica m");
		List<Matriculausuariogrupoatividadefisica> listaMatriculaUsuarioGrupoAtividadeFisica = query.getResultList();
		return listaMatriculaUsuarioGrupoAtividadeFisica;
	}

	//Recupera uma lista todos os vínculos entre os usuários e grupos de atividade física não sincronizados com o SMC
	@SuppressWarnings("unchecked")
	public List<Matriculausuariogrupoatividadefisica> findListaMatriculaUsuarioGrupoAtividadeFisicaNaoSincronizado() {
		Query query = em.createQuery("SELECT m FROM Matriculausuariogrupoatividadefisica m WHERE m.sincronizado = false");
		List<Matriculausuariogrupoatividadefisica> listaMatriculaUsuarioGrupoAtividadeFisica = query.getResultList();
		return listaMatriculaUsuarioGrupoAtividadeFisica;
	}
}
