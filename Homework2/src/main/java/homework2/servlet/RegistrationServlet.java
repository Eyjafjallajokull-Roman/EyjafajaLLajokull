package homework2.servlet;

import homework2.dao.model.User;
import homework2.service.UserService;
import homework2.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Objects;

@Log4j
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    public RegistrationServlet() {
        userService = new UserServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("Starting user registration.");

        String email = request.getParameter("email");

        if (Objects.isNull(userService.readByEmail(email))) {

            log.info("Registration for new user");
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            User user = new User(name, email, password, "user");
            userService.create(user);
            log.info("User was registered : " + user);

            HttpSession session = request.getSession(true);
            session.setAttribute("userName", name);
            session.setAttribute("userEmail", email);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter writer = response.getWriter()) {
                log.info("kek13");
                writer.write("Success");
            }
        } else {
            log.info("User with email : " + email + " already registered! Redirection to login page ...");
            try(PrintWriter writer = response.getWriter()) {
                writer.write("Exist");

            }
        }


    }
}
