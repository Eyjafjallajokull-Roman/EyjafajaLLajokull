package homework2.servlet;

import homework2.dao.model.User;
import homework2.service.UserService;
import homework2.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.util.Objects;

@Log4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    public LoginServlet(){
        this.userService = new UserServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");


        User user;
        user = userService.readByEmail(email);
        if (!Objects.isNull(user)){

            log.info("object not null");
            String password = request.getParameter("password");
            if (user.getPassword().equals(password)){


                log.info("you successfully log to your account");
                HttpSession session = request.getSession(true);
                session.setAttribute("userName", user.getName());
                session.setAttribute("userEmail", user.getEmail());

                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                try (PrintWriter writer = response.getWriter())
                {
                    log.info("good");
                    writer.write("Success");
                }



            }
            else {
                log.info("Invalid password");
            }

        }
        else {

            log.info("User with this email" + email + " don`t exist");
        }



    }

}
