package bankpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet2")
public class servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		ServletContext context=getServletContext();
		Object obj=context.getAttribute("acc_no");
		String value=obj.toString();
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<Form method=post action=servlet3>");
		out.println("<b>Click the Fund Transfer Button after entering the amount<b>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Account Number : "+ value);
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Transfer Amount:</td> <td><input type=number name=amount value=0>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<input type=submit value=Fund Transfer>");
		out.println("</br>");
		out.println("</Form>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
