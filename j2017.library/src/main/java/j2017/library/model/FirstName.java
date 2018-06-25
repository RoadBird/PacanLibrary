package j2017.library.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "firstnames")
public class FirstName {
	public static final String FIELD_NAME = "fname";
	
	@DatabaseField(id = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, columnName = FIELD_NAME)
	private String firstName;
	
	public FirstName() {
	}

	public FirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}
}
