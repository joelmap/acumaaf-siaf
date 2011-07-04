package br.ufscar.acumaaf.siaf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufscar.acumaaf.siaf.model.Dadosessao;

public class DadoSessaoDAO {
	
	public static EntityManagerFactory emf;
	public static EntityManager em;

	public DadoSessaoDAO() {
		emf = Persistence.createEntityManagerFactory("ACUMAAF-SIAF");
		em = emf.createEntityManager();
	}

	// Registra um novo dado para a sessão de atividade física
	public boolean save(Dadosessao dadoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			System.out.println("Fez o Begin");
			em.persist(dadoSessao); // persiste o objeto
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

	// Atualiza determinado dado de uma sessão de atividade física
	public boolean update(Dadosessao dadoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			dadoSessao = em.merge(dadoSessao); // persiste o objeto e devolve sincronizado
			em.getTransaction().commit(); // encerra a transação
			em.refresh(dadoSessao); // atualiza a referência do objeto
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}

	// Deleta determinado dado de uma sessão de atividade física
	public boolean delete(Dadosessao dadoSessao) {
		em.getTransaction().begin(); // inicia uma transação
		try {
			Dadosessao ref = em.find(Dadosessao.class, dadoSessao.getCodigodadosessao()); // atualiza a referência do objeto antes de removê-lo																		   
			em.remove(ref); // remove o objeto do banco e dados
			em.getTransaction().commit(); // encerra a transação
			return true;
		} catch (Exception e) {
			System.out.println("Entrou no rollback");
			em.getTransaction().rollback(); // desfaz a transação
			return false;
		}
	}
	
	// Recupera um dado de uma sessão de atividade física pelo cógido
	public Dadosessao findDadoSessaoByCode(Dadosessao dadoSessao) {
		Query query = em.createQuery("SELECT d FROM Dadosessao d WHERE v.codigodadosessao =:codigo");
		query.setParameter("codigo", dadoSessao.getCodigodadosessao());
		return (Dadosessao)query.getSingleResult();
	}

	//Recupera uma lista com todos os dados de sessões de atividade física
	@SuppressWarnings("unchecked")
	public List<Dadosessao> findDadosSessaoAll() {
		Query query = em.createQuery("SELECT d FROM Dadosessao d");
		List<Dadosessao> listaDadosSessao = query.getResultList();
		return listaDadosSessao;
	}
	
	//Recupera uma lista com todos os dados de sessões de atividade física de um usuário selecionado pelo
	//Profissional de saúde e cujo dado seja o seu peso. Assumindo código 2 para o instrumento de coleta "Peso"
	@SuppressWarnings("unchecked")
	public List<Dadosessao> findDadoPesoByUsuario(Integer codigo) {
		Query query = em.createQuery("SELECT d FROM Dadosessao d WHERE d.usuario.codigousuario =:codigo " +
				                     "AND d.instrumentocoleta.codigoinstrumentocoleta = 2 ORDER By d.sessao.data ASC");
		query.setParameter("codigo", codigo);
		List<Dadosessao> listaDadosSessao = query.getResultList();
		return listaDadosSessao;
	}
	
	//Recupera uma lista com todos os dados de sessões de atividade física de um usuário selecionado pelo
	//Profissional de saúde e cujo dado seja a pressão arterial. Assumindo código 1 para o instrumento de coleta "Pressão Arterial"
	@SuppressWarnings("unchecked")
	public List<Dadosessao> findDadoPressaoArterialByUsuario(Integer codigo) {
		Query query = em.createQuery("SELECT d FROM Dadosessao d WHERE d.usuario.codigousuario =:codigo " +
				                     "AND d.instrumentocoleta.codigoinstrumentocoleta = 1 ORDER By d.sessao.data ASC");
		query.setParameter("codigo", codigo);
		List<Dadosessao> listaDadosSessao = query.getResultList();
		return listaDadosSessao;
	}
}
