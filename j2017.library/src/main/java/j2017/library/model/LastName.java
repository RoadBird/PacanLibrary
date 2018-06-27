package j2017.library.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "last_names")
public class LastName {
	public static final String FIELD_NAME = "lname";
	
	@DatabaseField(id = true)
	private Integer id;

	@DatabaseField(canBeNull = false, columnName = FIELD_NAME)
	private String lastName;
	
	public LastName() {
		super();
	}

	public LastName(String lastName) {
		super();
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
