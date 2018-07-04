package j2017.library.model;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {
	public static final String AUTHOR = "id_author";
	public static final String TITLE = "title";
	public static final String RELEASE_DATE = "release_date";
	public static final String DESCRIPTION = "description";
	public static final String COVER_PATH = "cover_path";
	
	
	@DatabaseField(id = true)
	private Integer id;
	
	@DatabaseField(columnName = TITLE)
	private String title;
	
	@DatabaseField(foreign = true, foreignColumnName = "id", canBeNull = false, columnName = AUTHOR)
	private Author author;
	
	@DatabaseField(columnName = RELEASE_DATE)
	private Date releaseDate;
	
	@DatabaseField(columnName = DESCRIPTION)
	private String description;
	
	@DatabaseField
	private String isbn;
	
	@DatabaseField(columnName = COVER_PATH)
	private String coverPath;

	public Book() {
	}

	public Book(String title, Author author, Date releaseDate, String description, String isbn,
			String coverPath) {
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.description = description;
		this.isbn = isbn;
		this.coverPath = coverPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
