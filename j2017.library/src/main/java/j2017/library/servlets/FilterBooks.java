package j2017.library.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import j2017.library.model.FirstName;

/**
 * Servlet implementation class DBRequestServlet
 */
public class FilterBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/library?user=root&password=admin";
	private Dao<FirstName, Integer> accountDao;
	private JdbcConnectionSource connectionSource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterBooks() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL);
			accountDao = DaoManager.createDao(connectionSource, FirstName.class);
			System.out.println("Connection to database done: " + connectionSource);
		} catch (SQLException e) {
			System.out.println("Connection to database fail " + e.getMessage());
			//TODO handle database fail
		}
    	
    	
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
