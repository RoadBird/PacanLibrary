package j2017.library.model;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "authors")
public class Author {
	public static final String FIRST_NAME = "id_firstname";
	public static final String MIDDLE_NAME = "id_middlename";
	public static final String LAST_NAME = "id_lastname";
	public static final String ALIAS = "alias";
	public static final String BIRTH_DATE = "birth_date";
	public static final String DATE_GONE = "death_date";
	
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
		Author other = (Author) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return firstNameId.getFirstName() + " " + middleNameId.getMiddleName() + " " + lastNameId.getLastName()
				+ " " + authorAlias.trim();
	}
	
	
}
