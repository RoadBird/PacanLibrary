package j2017.library.model;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "authors")
public class Author {
	public static final String FIRST_NAME = "first_name_id";
	public static final String MIDDLE_NAME = "middle_name_id";
	public static final String LAST_NAME = "last_name_id";
	public static final String ALIAS = "author_alias";
	public static final String BIRTH_DATE = "birth_date";
	public static final String DATE_GONE = "date_gone";
	
	@DatabaseField(id = true)
	private Integer id;
	
	@DatabaseField(foreign = true, foreignColumnName = "id", canBeNull = false, columnName = FIRST_NAME)
	private FirstName firstNameId;
	
	@DatabaseField(foreign = true, foreignColumnName = "id", canBeNull = false, columnName = MIDDLE_NAME)
	private MiddleName middleNameId;
	
	@DatabaseField(foreign = true, foreignColumnName = "id", canBeNull = false, columnName = LAST_NAME)
	private LastName lastNameId;
	
	@DatabaseField(columnName = ALIAS)
	private String authorAlias;
	
	@DatabaseField(columnName = BIRTH_DATE)
	private Date birthDate;
	
	@DatabaseField(columnName = DATE_GONE)
	private Date dateGone;

	public Author() {
	}

	public Author(FirstName firstNameId, MiddleName middleNameId, LastName lastNameId, String authorAlias,
			Date birthDate, Date dateGone) {
		this.firstNameId = firstNameId;
		this.middleNameId = middleNameId;
		this.lastNameId = lastNameId;
		this.authorAlias = authorAlias;
		this.birthDate = birthDate;
		this.dateGone = dateGone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FirstName getFirstNameId() {
		return firstNameId;
	}

	public void setFirstNameId(FirstName firstNameId) {
		this.firstNameId = firstNameId;
	}

	public MiddleName getMiddleNameId() {
		return middleNameId;
	}

	public void setMiddleNameId(MiddleName middleNameId) {
		this.middleNameId = middleNameId;
	}

	public LastName getLastNameId() {
		return lastNameId;
	}

	public void setLastNameId(LastName lastNameId) {
		this.lastNameId = lastNameId;
	}

	public String getAuthorAlias() {
		return authorAlias;
	}

	public void setAuthorAlias(String authorAlias) {
		this.authorAlias = authorAlias;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDateGone() {
		return dateGone;
	}

	public void setDateGone(Date dateGone) {
		this.dateGone = dateGone;
	}
}
