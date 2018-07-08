package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HeadTag extends SimpleTagSupport {
	private static final String content = "<div class='headerDiv'>" +
		"<img alt='Book'" +
			"src='http://icons.veryicon.com/256/System/Line/Open%20Book.png'>" +
		"<div id='headerText'>" +
			"<p id='Pe'>MY BEST LIBRARY</p>" +
			"<div id='headerContacts'>" +
				"Contacts" +
				"<ul>" +
					"<li myattr='myValue'>Contact phone</li>" +
					"<li>Contact email</li>" +
					"<li>Contact skype</li>" +
				"</ul>" +
			"</div>" +
		"</div>" +
	"</div>" +
	"<div class='menuDiv'>" +
		"<p class='menuBut' id='menu'>MENU</p>" +
		"<p>|</p>" +
		"<p class='menuBut' id='add'>ADD</p>" +
		"<p>|</p>" +
		"<p class='menuBut' id='remove'>REMOVE</p>" +
		"<p>|</p>" +
		"<p class='menuBut' id='edit'>EDIT</p>" +
		"<p>|</p>" +
		"<p class='menuBut' id='text'>TEXT</p>" +
	"</div>" +
	"<div class='booksDiv'>" +
		"<div id='filtersBooks'>" +
			"<p>Filters:</p>" +
			"<br>" +
			"<p>Author name:</p>" +
			"<p>" +
				"<input id='authorFilter'>" +
			"</p>" +
			"<p>Title book:</p>" +
			"<p>" +
				"<input id='titleFilter'>" +
			"</p>" +
			"<p>Publish year:</p>" +
			"<p>" +
				"<input id='publishFirstYearFilter'> - <input id='publishLastYearFilter'>" +
			"</p>" +
			"<button id='filterButton'>Filter</button>" +
		"</div>";
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println(content);
	}
}
