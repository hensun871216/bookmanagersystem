package bookmanager.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bookmanager.bussiness.IUserService;

public class LoginServlet extends HttpServlet {
	IUserService iUserService;

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		this.doPost(httpServletRequest, httpServletResponse);
	}

	@Override
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		String userName = (String) httpServletRequest.getParameter("userName");
		String password = (String) httpServletRequest.getParameter("password");
		boolean isSuccess = iUserService.userLogin(userName, password);
		if(isSuccess) {
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute("userName", userName);
			httpServletResponse.sendRedirect("users/userHome.jsp");
		} else {
			httpServletRequest.setAttribute("errorMsg", "your password and username do not match, please check it!");
			httpServletRequest.getRequestDispatcher("login.jsp").forward(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void init() throws ServletException {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		iUserService = webApplicationContext.getBean(IUserService.class);
		super.init();
	}
	
}	
