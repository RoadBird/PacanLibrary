package j2017.library.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import database.DbHelper;
import j2017.library.model.Author;
import j2017.library.model.Book;
import j2017.library.model.FirstName;
import j2017.library.model.LastName;
import j2017.library.model.MiddleName;

public class FilterAuthors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao<FirstName, Integer> firstNameDao;
	private Dao<MiddleName, Integer> middleNameDao;
	private Dao<LastName, Integer> lastNameDao;
	private Dao<Author, Integer> authorDao;
	private Dao<Book, Integer> bookDao;
	private Logger logger = LogManager.getLogger(FilterBooks.class.getName());
	private Gson gson;
	
	public FilterAuthors() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		firstNameDao = DbHelper.getInstance().getFirstNameDao();
		middleNameDao = DbHelper.getInstance().getMiddleNameDao();
		lastNameDao = DbHelper.getInstance().getLastNameDao();
		authorDao = DbHelper.getInstance().getAuthorDao();
		bookDao = DbHelper.getInstance().getBookDao();
		gson = new Gson();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String term = request.getParameter("term");
		logger.info("Request autocomplete for authors by: " + term);
		
		response.getWriter().write(getJsonFiltredAuthors(term));
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
	
	private synchronized List<Author> getAuthorsFromBd(String author) {
		QueryBuilder<FirstName, Integer> fnames = firstNameDao.queryBuilder();
		QueryBuilder<MiddleName, Integer> mnames = middleNameDao.queryBuilder();
		QueryBuilder<LastName, Integer> lnames = lastNameDao.queryBuilder();
		QueryBuilder<Author, Integer> queryAuthor = authorDao.queryBuilder();
		List<Author> authorList = new LinkedList<Author>();
		try {
			String s;
			if (author == null || (s = author.replaceAll("\\.", "").trim()) == "") {
				authorList = authorDao.queryForAll();
				return authorList;
			}
			String[] names = s.split("\\s+");
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
	
	private synchronized List<Book> getBooksFromBdByAuthor(Author author) {
		if (author != null) {
			try {
				return bookDao.queryForEq(Book.AUTHOR, author);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}
}
