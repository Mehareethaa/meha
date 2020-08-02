package bankpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter out=null;
	  Connection con;
	  PreparedStatement pst;
	  ResultSet rs;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String result;
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bar", "root", "Meha@2803");
			ServletContext context=getServletContext();
			context.setAttribute("acc_no", "");
			String acc_no=request.getParameter("acc_no");
			String pass=request.getParameter("pass");
			pst=con.prepareStatement("select acc_no,user_name,acc_balance from acc_details where acc_no=? and pass=?");
			pst.setString(1,acc_no);
			pst.setString(2,pass);
			boolean row=false;
			row=rs.next();
			if(row=true) {
				result=rs.getString(1);
				context.setAttribute("acc_no", result);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/servlet2");
				if(dispatcher==null)
				{
				}
				dispatcher.forward(request,response);
				con.close();
			}
			else {
				out=response.getWriter();
				response.setContentType("text/html");
				out.println("<html>");
				out.println("<body bgcolor=pink>");
				out.println("Please check the Accno and Balance");
				out.println("</body>");
				out.println("</html>");
				out.close();
				
			}
		
			
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
