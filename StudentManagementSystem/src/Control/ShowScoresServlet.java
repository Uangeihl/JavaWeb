package Control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowScoresServlet extends HttpServlet {
//    定义当前页，记录总数，每一页显示记录数，页码数
    private int pageNow=1;
    private int rowCount=0;
    private int pageSize=10;
    private int pageCount=0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScoresDeal sdl = new ScoresDeal();
//        计算rowCount的值
        try {
            rowCount = sdl.getRowCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pageCount = sdl.getPageCount(pageSize,rowCount);

        String sql = "";
        String str_pageNow = request.getParameter("pageNow");
//        如果StudentManagement.jsp传了pageNow来，将他转换为int，否则为默认值1
        if(str_pageNow != null){
            pageNow = Integer.parseInt(str_pageNow);
        }
//        每页显示pageSize条记录
        if(pageNow>1){
            sql = "select * from manager.score limit " + pageSize*(pageNow-1) +"," + pageSize +"";
        }else{
            sql = "select * from manager.score limit " + pageSize +"";
        }
//        将记录添加到列表中
        ArrayList<Student> al = null;
        try {
            al = sdl.showSelectResult(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        将成绩列表，当前页码，记录总数，页码总数，，传入到StudentManagement.jsp
        request.setAttribute("selresult",al);
        request.setAttribute("pageNow",pageNow+"");
        request.setAttribute("rowCount",rowCount+"");
        request.setAttribute("pageCount",pageCount+"");
        request.getRequestDispatcher("StudentManagement.jsp").forward(request,response);
    }
}
