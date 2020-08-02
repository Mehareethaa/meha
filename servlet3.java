package bankpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet3")
public class servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			con=DriverManager.getConnection("jdbc:mysql://localhost/bar", "root", "Meha@2803");
			Statement st=con.createStatement();
			ServletContext context=getServletContext();
			Object obj=context.getAttribute("acc_no");
			String value=obj.toString();
			String amount=request.getParameter("amount");
			long balance=Long.parseLong(amount);
		    rs=st.executeQuery("select * from acc_details where acc_no="+value);
	        long totbal=0;
	        while(rs.next())
	        {
	                                             long s1=rs.getLong(4);
	                                             if(s1>=1000)
	                                             {
	                                             totbal=s1-balance;
	                                             }
	                                             else{
	                                             out.println("Your balance is less than 1000,You can't tranfer the amount");
	                                             totbal=s1;
	                                             }
	                                 }
	                                 pst = con.prepareStatement("update acc_details set acc_balance=? where acc_no="+value);
	                                                                                 pst.setLong(1,totbal);
	                                             int i = pst.executeUpdate();
	                    
	                    
	                    ResultSet rs1=st.executeQuery("select acc_no,user_name,acc_balance from acc_details");
	                    out.println("<html>");
	                    out.println("<body>");
	                    out.println("Last Transactions");
	                    out.println("<table border=1 width=50% height=50% >");
	                    out.println("<tr><th>acc_no</th> <th>user_name</th> <th>acc_balance</th></tr");
	                    while(rs1.next())
	                    {
	                 	   int an=rs1.getInt("acc_no");
	                 	   String un=rs1.getString("user_name");
	                 	   long ad=rs1.getLong("acc_balance");
	                 	   out.println("<tr><td>" + an + "</td> <td>"+un+"</td> <td>"+ad+"</td> </tr>");}
	                    out.println("</table>");
	                    out.println("</body>");
	                    out.println("</html>");


			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
