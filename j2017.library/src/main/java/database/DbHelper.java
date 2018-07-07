package database;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import j2017.library.model.Author;
import j2017.library.model.Book;
import j2017.library.model.FirstName;
import j2017.library.model.LastName;
import j2017.library.model.MiddleName;

public class DbHelper {
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/library?user=root&password=180794Mm&serverTimezone=UTC";
	private Dao<FirstName, Integer> firstNameDao;
	private Dao<MiddleName, Integer> middleNameDao;
	private Dao<LastName, Integer> lastNameDao;
	private Dao<Author, Integer> authorDao;
	private Dao<Book, Integer> bookDao;
	private JdbcConnectionSource connectionSource = null;
	private static Logger logger = LogManager.getLogger(DbHelper.class.getName());
	private static DbHelper instance;

	private DbHelper() {
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL);
			logger.info("Connection to database done: " + connectionSource);
		} catch (SQLException e) {
			logger.error("Connection to database fail " + e.getMessage());
		}
	}

	public static DbHelper getInstance() {
		try {
			Class.forName(DbHelper.class.getName());
			synchronized (DbHelper.class) {
				if (instance == null) {
					instance = new DbHelper();
				}
				return instance;
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Dao<FirstName, Integer> getFirstNameDao() {
		if(firstNameDao == null) {
			try {
				firstNameDao = DaoManager.createDao(connectionSource, FirstName.class);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return firstNameDao;
	}

	public Dao<MiddleName, Integer> getMiddleNameDao() {
		if(middleNameDao == null) {
			try {
				middleNameDao = DaoManager.createDao(connectionSource, MiddleName.class);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return middleNameDao;
	}

	public Dao<LastName, Integer> getLastNameDao() {
		if(lastNameDao == null) {
			try {
				lastNameDao = DaoManager.createDao(connectionSource, LastName.class);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return lastNameDao;
	}

	public Dao<Author, Integer> getAuthorDao() {
		if(authorDao == null) {
			try {
				authorDao = DaoManager.createDao(connectionSource, Author.class);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return authorDao;
	}

	public Dao<Book, Integer> getBookDao() {
		if(bookDao == null) {
			try {
				bookDao = DaoManager.createDao(connectionSource, Book.class);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return bookDao;
	}
	
	public void close() {
		if (connectionSource != null) {
			try {
				connectionSource.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
