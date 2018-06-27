package j2017.library.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
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
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/library?user=root&password=admin&serverTimezone=UTC";
	private Dao<FirstName, Integer> firstNameDao;
	private Dao<MiddleName, Integer> middleNameDao;
	private Dao<LastName, Integer> lastNameDao;
	private Dao<Author, Integer> authorDao;
	private Dao<Book, Integer> bookDao;
	private JdbcConnectionSource connectionSource = null;
	private Logger logger = LogManager.getLogger(FilterBooks.class.getName());
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterBooks() {
		super();
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
			logger.info("Connection to database done: " + connectionSource);
			gson = new Gson();
		} catch (SQLException e) {
			logger.error("Connection to database fail " + e.getMessage());
			// TODO handle database fail
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("author", request.getParameter("author"));
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("published", request.getParameter("published"));
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = request.getReader();
		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		String body = sb.toString();
		logger.info("JSON body: " + body);
		Gson gson = new Gson();
		RequestBody rb = gson.fromJson(body, RequestBody.class);
		logger.info("Request parameters: author name - " + rb.author + ", title book - " + rb.titleBook + ", published - "
				+ rb.published);
		

	}
	
	private static class RequestBody{
		private String author;
		@SerializedName("title")
		private String titleBook;
		private String published;
	}
}
