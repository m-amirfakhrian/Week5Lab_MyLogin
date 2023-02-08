package servlets;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Majid
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = getServletContext().getRealPath("/WEB-INF/data.txt");
        HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");

        try (Scanner dataFile = new Scanner(Paths.get(path))) {

            while (dataFile.hasNextLine()) {
                String userData = dataFile.nextLine();
                String[] userDataSplit = userData.split(" ");
                User newUser = new User(userDataSplit[0], userDataSplit[1]);
                users.add(newUser);
                System.out.println(newUser);
            }
        } catch (Exception e1) {
            System.out.println("Error in Opening File! " + e1.getMessage());
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ((username == null || username.equals("")) || (password == null || password.equals(""))) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("invalid", true);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }

        boolean userFound = false;
        int i = 0;

        User currentUser = users.get(0);
        while (i < users.size() && !userFound) {
            User user = new User(username, password);
            if (user.equals(currentUser)) {
                userFound = true;
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                session.setAttribute("user", user);
            }

            //session.setAttribute("password", password);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}