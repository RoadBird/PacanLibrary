package j2017.library.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

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
	private Logger logger = LogManager.getLogger(FilterBooks.class.getName());
	private Gson gson;

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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String term = request.getParameter("term");
		String param = getServletConfig().getInitParameter("filter");
		if (param != null && param.equals("author")) {
			response.getWriter().write(getJsonFiltredAuthors(term));
		} else if (param != null && param.equals("book")) {
			String author = request.getParameter("author");
			if (author == null || author.trim().equals("")) {
				response.getWriter().write(getJsonFiltredBooks(term, null));
			} else {
				response.getWriter().write(getJsonFiltredBooks(term, author));
			}
		}
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
		RequestBody rb = gson.fromJson(body, RequestBody.class);
		logger.info("Request parameters: author name - " + rb.author + ", title book - " + rb.titleBook
				+ ", published : " + rb.firstPublished + " to " + rb.lastPublished);
		List<Author> aList = getAuthorsFromBd(rb.author);
		if(aList.isEmpty()){
			request.setAttribute("books", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		List<Book> bList = getBooksFromBdByTitle(rb.titleBook, Date.valueOf(rb.firstPublished), Date.valueOf(rb.lastPublished));
		if(bList.isEmpty()){
			request.setAttribute("books", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		for(Author a : aList){
			bList.retainAll(getBooksFromBdByAuthor(a));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		request.setAttribute("books", bList);;
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private synchronized List<Author> getAuthorsFromBd(String author) {
		QueryBuilder<FirstName, Integer> fnames = firstNameDao.queryBuilder();
		QueryBuilder<MiddleName, Integer> mnames = middleNameDao.queryBuilder();
		QueryBuilder<LastName, Integer> lnames = lastNameDao.queryBuilder();
		QueryBuilder<Author, Integer> queryAuthor = authorDao.queryBuilder();
		List<Author> authorList = new LinkedList<Author>();
		try {
			String s;
			if (author == null || (s = author.replaceAll("\\.", "").replaceAll("-", "").trim()) == "") {
				authorList = authorDao.queryForAll();
				return authorList;
			}
			String[] names = s.split(" ");
			for (int i = 0; i < names.length; i++) {
				fnames.where().like(FirstName.FIELD_NAME, "%" + names[i] + "%");
				mnames.where().like(MiddleName.FIELD_NAME, "%" + names[i] + "%");
				lnames.where().like(LastName.FIELD_NAME, "%" + names[i] + "%");
				List<Author> al = queryAuthor.joinOr(fnames).joinOr(mnames).joinOr(lnames).query();
				if (i == 0) {
					authorList.addAll(al);
				} else {
					authorList.retainAll(al);
				}
				if (authorList.size() < 1) {
					return authorList;
				}
				fnames.reset();
				mnames.reset();
				lnames.reset();
				queryAuthor.reset();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorList;

	}

	private synchronized String getJsonFiltredAuthors(String term) {
		LinkedList<String> names = new LinkedList<>();
		for (Author author : getAuthorsFromBd(term)) {
			String middleName = author.getMiddleNameId().getMiddleName() == "" ? ""
					: " " + author.getMiddleNameId().getMiddleName();
			String lastName = author.getLastNameId().getLastName() == "" ? ""
					: " " + author.getLastNameId().getLastName();
			names.add(author.getFirstNameId().getFirstName() + middleName + lastName);
		}
		return gson.toJson(names);
	}

	private synchronized List<Book> getBooksFromBdByTitle(String title, Date firstYear, Date lastYear) {
		QueryBuilder<Book, Integer> queryBuilder = bookDao.queryBuilder();
		LinkedList<Book> books = new LinkedList<>();
		title = title.trim();
		try {
			if ((title == null || title == "") && firstYear == null && lastYear == null) {
				books.addAll(bookDao.queryForAll());
			} else if (firstYear == null && lastYear == null) {
				queryBuilder.where().like(Book.TITLE, "%" + title + "%");
				books.addAll(queryBuilder.query());
			} else if (firstYear != null && lastYear == null) {
				queryBuilder.where().like(Book.TITLE, "%" + title + "%").and().ge(Book.RELEASE_DATE, firstYear);
				books.addAll(queryBuilder.query());
			} else if (firstYear == null && lastYear != null) {
				queryBuilder.where().like(Book.TITLE, "%" + title + "%").and().le(Book.RELEASE_DATE, lastYear);
				books.addAll(queryBuilder.query());
			} else if (firstYear != null && lastYear != null) {
				queryBuilder.where().like(Book.TITLE, "%" + title + "%").and().between(Book.RELEASE_DATE, firstYear,
						lastYear);
				books.addAll(queryBuilder.query());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return books;
	}

	private synchronized List<Book> getBooksFromBdByAuthor(Author author) {
		if (author != null) {
			try {
				return bookDao.queryForEq(Book.AUTHOR, author);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return new LinkedList<>();
	}

	private synchronized String getJsonFiltredBooks(String term, String author) {
		Set<String> titles = new HashSet<>();
		Set<Book> bookSet = new HashSet<>();
		if (author != null) {
			for (Author a : getAuthorsFromBd(author)) {
				bookSet.addAll(getBooksFromBdByAuthor(a));
				bookSet.retainAll(getBooksFromBdByTitle(term, null, null));
			}
		} else {
			bookSet.addAll(getBooksFromBdByTitle(term, null, null));
		}
		for (Book b : bookSet) {
			titles.add(b.getTitle());
		}
		return gson.toJson(titles);
	}

	private static class RequestBody {
		private String author;
		@SerializedName("title")
		private String titleBook;
		private String firstPublished;
		private String lastPublished;

	}

	@Override
	public void destroy() {
		super.destroy();
		if (connectionSource != null) {
			try {
				connectionSource.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
