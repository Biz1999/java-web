package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The usuario. */
	JavaBeans usuario = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			usuarios(request, response);
		} else if (action.equals("/insert")) {
			novoUsuario(request, response);
		} else if (action.equals("/select")) {
			listarUsuario(request, response);
		} else if (action.equals("/update")) {
			editarUsuario(request, response);
		} else if (action.equals("/delete")) {
			deletarUsuario(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Usuarios.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar usuarios
	protected void usuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhar a lista ao documento usuarios.jsp
		request.setAttribute("usuarios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("usuarios.jsp");
		rd.forward(request, response);
	}

	/**
	 * Novo usuario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contato
	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis
		this.usuario.setNome(request.getParameter("nome"));
		this.usuario.setPhone(request.getParameter("phone"));
		this.usuario.setEmail(request.getParameter("email"));
		// invocar o método inserirUsuario passando o objeto usuario
		dao.inserirUsuario(this.usuario);
		// Redirecionar para usuarios.jsp
		response.sendRedirect("main");

	}

	/**
	 * Listar usuario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar contato
	protected void listarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Id do contato a ser editado
		String idcon = request.getParameter("idcon");
		// Setar variavel JavaBeans
		usuario.setIdcon(idcon);
		// Metodo seleciona contato
		dao.selecionarUsuario(usuario);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idcon", usuario.getIdcon());
		request.setAttribute("nome", usuario.getNome());
		request.setAttribute("phone", usuario.getPhone());
		request.setAttribute("email", usuario.getEmail());
		// Encaminhar para editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar usuario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis JavaBeans
		usuario.setIdcon(request.getParameter("idcon"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setPhone(request.getParameter("phone"));
		usuario.setEmail(request.getParameter("email"));
		// executar a edição
		dao.editarUsuario(this.usuario);
		// redirecionar aos usuarios
		response.sendRedirect("main");
	}

	/**
	 * Deletar usuario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// DELETAR USUARIO
	protected void deletarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		// setar a variavel idcon JavaBeans
		usuario.setIdcon(idcon);
		dao.deletaUsuario(usuario);
		// redirecionar aos usuarios
		response.sendRedirect("main");
		
	}
}
