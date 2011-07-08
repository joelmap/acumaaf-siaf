package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Usuario;

public class UsuarioDAO {

	public static EntityManagerFactory emf;
	public static EntityManager em;

	public UsuarioDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Cadastra um novo usuário
	public boolean save(Usuario user) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(user); // persiste o objeto
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

	// Atualiza determinado usuário no banco de dados
	public boolean update(Usuario user) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			user = em.merge(user); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(user); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado usuário no banco de dados
	public boolean delete(Usuario user) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Usuario ref = em.find(Usuario.class, user.getCodigousuario()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um usuário pelo código
	public Usuario findUserByCode(Integer codigo) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.codigousuario =:codigo");
		query.setParameter("codigo", codigo);
		return (Usuario)query.getSingleResult();
	}
	
	// Recupera um usuário pelo nome de usuário
	public Usuario findUserByUser(String usuario) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario =:usuario");
		query.setParameter("usuario", usuario);
		return (Usuario)query.getSingleResult();
	}
	
	// Recupera usuario e senha de um Usuário do SIAF para autenticá-lo
	@SuppressWarnings("unchecked")
	public boolean checaUsuarioSenha(String usuario, String senha) {
		List<Usuario> listaUsuarios = null;
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario =:usuario AND u.senha =:senha");
		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);
		listaUsuarios = (List<Usuario>)query.getResultList();
		System.out.println("LISTA DE USUARIOS" + listaUsuarios);
		if(listaUsuarios.isEmpty()){
			return false;
		}
		else{
			return true;
		}	
		
	}

	//Recupera uma lista com todos os usuários cadastrados no banco de dados
	@SuppressWarnings("unchecked")
	public List<Usuario> findUserAll() {
		List<Usuario> listaUsuarios = null;
		Query query = em.createQuery("SELECT u FROM Usuario u");
		listaUsuarios = (List<Usuario>)query.getResultList();
		return listaUsuarios;
	}
	
	//Recupera uma lista com todos os usuários matriculados em um determinado grupo de atividade física
	@SuppressWarnings("unchecked")
	public List<Usuario> findListaFrequencia(Integer codigoSessao) {
		List<Usuario> listaUsuarios = null;
		Query query = em.createQuery("SELECT u FROM Usuario u, Matriculausuariogrupoatividadefisica m, Grupoatividadefisica g, Sessao s" +
				                     " WHERE (m.usuario.codigousuario = u.codigousuario AND m.grupoatividadefisica.codigogrupoatividadefisica = g.codigogrupoatividadefisica" +
				                     " AND g.codigogrupoatividadefisica = s.grupoatividadefisica.codigogrupoatividadefisica AND s.codigosessao=:codigosessao)");
		query.setParameter("codigosessao", codigoSessao);
		listaUsuarios = (List<Usuario>)query.getResultList();
		return listaUsuarios;
	}
	
	//Recupera uma lista com todos os usuários que contêm algum registro na tabela "dadosessao"
	@SuppressWarnings("unchecked")
	public List<Usuario> findUsuarioContemDadoSessao() {
		List<Usuario> listaUsuarios = null;
		Query query = em.createQuery("SELECT DISTINCT u FROM Usuario u, Dadosessao d WHERE (u.codigousuario = d.usuario.codigousuario) ORDER BY u.nome");
		listaUsuarios = (List<Usuario>)query.getResultList();
		return listaUsuarios;
	}
	
	//Recupera uma lista com todos os usuários que não estão sincronizados com o SMC
	@SuppressWarnings("unchecked")
	public List<Usuario> findListaUsuariosNaoSincronizado() {
		List<Usuario> listaUsuarios = null;
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.sincronizado = false");
		listaUsuarios = (List<Usuario>)query.getResultList();
		return listaUsuarios;
	}

}
