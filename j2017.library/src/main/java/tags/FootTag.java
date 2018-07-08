package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FootTag extends SimpleTagSupport {
	private static final String content = "</div>" +
	"<div class='footerDiv'>" +
		"<p>Copyright...</p>" +
	"</div>";
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println(content);
	}
}
