package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** Módulo de conexão *. */
	// Parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3333/usuarios?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";

	/** The password. */
	private String password = "ale12345";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexao
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(this.driver);
			con = DriverManager.getConnection(this.url, this.user, this.password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD CREATE *.
	 *
	 * @param usuario the usuario
	 */
	public void inserirUsuario(JavaBeans usuario) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			// abrir a conexao
			Connection con = conectar();
			// Preparar a query para execucao no db
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros (?) pelas variaveis
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getPhone());
			pst.setString(3, usuario.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando o ArrayList
		ArrayList<JavaBeans> usuarios = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// Abrir conexao com o db
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				usuarios.add(new JavaBeans(idcon, nome, phone, email));
			}
			con.close();
			return usuarios;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD UPDATE *.
	 *
	 * @param usuario the usuario
	 */
	public void selecionarUsuario(JavaBeans usuario) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, usuario.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				usuario.setIdcon(rs.getString(1));
				usuario.setNome(rs.getString(2));
				usuario.setPhone(rs.getString(3));
				usuario.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Editar usuario.
	 *
	 * @param usuario the usuario
	 */
	// editar o usuario
	public void editarUsuario(JavaBeans usuario) {
		String update = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getPhone());
			pst.setString(3, usuario.getEmail());
			pst.setString(4, usuario.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD DELETE *.
	 *
	 * @param usuario the usuario
	 */
	public void deletaUsuario(JavaBeans usuario) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, usuario.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * //teste de conexao public void testeConexao() { try { Connection con =
	 * conectar(); System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e); } }
	 */
}
