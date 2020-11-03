package homework2.servlet;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Log4j
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        log.info("try to connect");
        if (httpSession!=null) {

            log.info("this user want to logout");
            {

                if (httpSession.getAttribute("userName") != null) {
                    log.info("something1");
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    try (PrintWriter writer = response.getWriter())
                    {
                        writer.write("Success");
                    }
                    httpSession.invalidate();
                } else {
                    log.info("something2");
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    try (PrintWriter writer = response.getWriter())
                    {
                        writer.write("User still here");
                    }
                }
            }
        }
    }

}
