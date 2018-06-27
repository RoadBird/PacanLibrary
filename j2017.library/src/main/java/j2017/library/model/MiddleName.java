package j2017.library.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "middle_names")
public class MiddleName {
public static final String FIELD_NAME = "mname";
	
	@DatabaseField(id = true)
	private Integer id;

	@DatabaseField(canBeNull = false, columnName = FIELD_NAME)
	private String middleName;

	public MiddleName() {
		super();
	}

	public MiddleName(String middleName) {
		super();
		this.middleName = middleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	
}
