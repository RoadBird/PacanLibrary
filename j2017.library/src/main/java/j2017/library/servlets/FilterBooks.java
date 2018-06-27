package j2017.library.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import j2017.library.model.Author;
import j2017.library.model.Book;
import j2017.library.model.FirstName;
import j2017.library.model.LastName;
import j2017.library.model.MiddleName;

/**
 * Servlet implementation class DBRequestServlet
 */
public class FilterBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/library?user=root&password=180794Mm&serverTimezone=UTC";
	private Dao<FirstName, Integer> firstNameDao;
	private Dao<MiddleName, Integer> middleNameDao;
	private Dao<LastName, Integer> lastNameDao;
	private Dao<Author, Integer> authorDao;
	private Dao<Book, Integer> bookDao;
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
			firstNameDao = DaoManager.createDao(connectionSource, FirstName.class);
			lastNameDao = DaoManager.createDao(connectionSource, LastName.class);
			middleNameDao = DaoManager.createDao(connectionSource, MiddleName.class);
			authorDao = DaoManager.createDao(connectionSource, Author.class);
			bookDao = DaoManager.createDao(connectionSource, Book.class);
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authorName = String.valueOf(request.getAttribute("author"));
		String title = String.valueOf(request.getAttribute("title"));
		String year = String.valueOf(request.getAttribute("published"));
		try {
			List<Book> bookList = bookDao.queryForAll();
			for(Book b : bookList){
				System.out.println(b.getAuthor().getFirstNameId().getFirstName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		if(connectionSource != null){
			try {
				connectionSource.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
