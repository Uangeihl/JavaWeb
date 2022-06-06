package Control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接收账号与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        接收操作名
        String operate = request.getParameter("operate");
//        将账号密码作为User对象保存
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        UserDeal udl = new UserDeal();
//        操作为login时
        if (operate.equals("login")) {
            try {
//                验证是否该用户信息是否正确，正确就跳转到ShowScoresServlet，不正确返回错误信息
                if (udl.isLogin(user)) response.sendRedirect("ShowScoresServlet");
                else {
                    request.setAttribute("message", "您的信息有误，请重新登录！");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            操作为register时
        } else if (operate.equals("register")) {
//            接收确认密码
            String checkpassword = request.getParameter("checkpassword");
            try {
//                验证输入值有无空值，有空值就提示错误信息
                if (username.equals("")||password.equals("") || checkpassword.equals("")) {
                    request.setAttribute("message", "账号或密码不可为空，请重新注册！");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
//                    验证密码与确认密码是否一致，不一致就提示错误信息
                } else if (!password.equals(checkpassword)) {
                    request.setAttribute("message", "密码与确认密码不一致，请重新注册！");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
//                    验证用户名是否已存在，存在就提示错误信息
                } else if (udl.isUserExist(user)) {
                    request.setAttribute("message", "用户名已被占用，请更换用户名或直接登录！");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
//                    插入该注册信息，插入成功后转到登录页面
                } else if(udl.isInsertSuccess(user)) {
                    request.setAttribute("message", "注册成功，请登录！");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "您的信息有误，请重新注册！");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
