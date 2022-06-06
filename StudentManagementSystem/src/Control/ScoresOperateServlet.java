package Control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ScoresOperateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScoresDeal sdl = new ScoresDeal();
//        接收操作名
        String operate = request.getParameter("operate");
        String strId = request.getParameter("id");
//        操作为delete时
        if(operate.equals("delete")){
//            验证有无输入或输入是否为数字
            if(strId.equals("")||!sdl.isNumber(strId)){
                request.setAttribute("message", "请输入正确值,且不可空");
                request.getRequestDispatcher("Delete.jsp").forward(request, response);
            } else {
                try {
//                    将id对应的记录删除
                    int id =Integer.parseInt(strId);
                    if (sdl.deleteResult(id)){
                        request.setAttribute("message", "删除成功");
                        request.getRequestDispatcher("Delete.jsp").forward(request, response);
                    } else {
                        request.setAttribute("message", "删除不成功，学号不存在");
                        request.getRequestDispatcher("Delete.jsp").forward(request, response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
//        操作为search时，search为修改页面查询按钮
        else if(operate.equals("search")){
//            验证有无输入或输入是否为数字
            if(strId.equals("")||!sdl.isNumber(strId)){
                request.setAttribute("message","请输入正确值,且不可空");
                request.getRequestDispatcher("Modify.jsp").forward(request, response);}
            else {
                int id =Integer.parseInt(strId);
                try {
//                    验证id是否存在
                    if (!sdl.isIdExist(id)){
                        request.setAttribute("message","学号不存在");
                        request.getRequestDispatcher("Modify.jsp").forward(request, response);
                    }
//                    查找该id对应的记录并以对象发送到修改页面
                    else {
                        Student stu = sdl.search(id);
                        request.setAttribute("stu",stu);
                        request.getRequestDispatcher("Modify.jsp").forward(request, response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        else {
//            接收传入的姓名，操作系统成绩，微机原理成绩，计算机网络成绩
            Student student = new Student();
            boolean flage = false;
            String strName = request.getParameter("name");
            String strCzxt = request.getParameter("czxt");
            String strWjyl = request.getParameter("wjyl");
            String strJsjwl = request.getParameter("jsjwl");
//            flage为验证输入信息是否有空值或是否为数字
            if (strId.equals("")||strName.equals("")||strCzxt.equals("")||strWjyl.equals("")||strJsjwl.equals("")){
                flage = true;
            } else if (!sdl.isNumber(strId)||!sdl.isNumber(strCzxt)||!sdl.isNumber(strWjyl)||!sdl.isNumber(strJsjwl)){
                flage = true;
            } else {
                student.setId(Integer.parseInt(strId));
                student.setName(strName);
                student.setCzxt(Integer.parseInt(strCzxt));
                student.setWjyl(Integer.parseInt(strWjyl));
                student.setJsjwl(Integer.parseInt(strJsjwl));
            }
//            操作为add时
            if(operate.equals("add")){
                if(flage){
                    request.setAttribute("message", "请输入正确值,且不可空");
                    request.getRequestDispatcher("Add.jsp").forward(request, response);
                } else {
                    try {
//                        添加该记录，在addResult中验证该记录是否存在
                        if (sdl.addResult(student)){
                            request.setAttribute("message", "添加成功");
                            request.getRequestDispatcher("Add.jsp").forward(request, response);
                        } else {
                            request.setAttribute("message", "添加失败，学号已存在");
                            request.getRequestDispatcher("Add.jsp").forward(request, response);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
//            操作为modify时
            if(operate.equals("modify")){
                if(flage){
                    request.setAttribute("message", "请输入正确值,且不可空");
                    request.getRequestDispatcher("Modify.jsp").forward(request, response);
                } else {
                    try {
//                        修改该记录，在modifyResult验证该记录是否存在
                        if (sdl.modifyResult(student)){
                            request.setAttribute("message", "修改成功");
                            request.getRequestDispatcher("Modify.jsp").forward(request, response);
                        } else{
                            request.setAttribute("message", "修改不成功，学号不存在");
                            request.getRequestDispatcher("Modify.jsp").forward(request, response);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
    }
}
