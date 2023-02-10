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

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String path = getServletContext().getRealPath("/WEB-INF/data.txt");
        HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
        }

        User user1 = new User("abe", "password");
        User user2 = new User("barb", "password");
        users.add(user1);
        users.add(user2);
        session.setAttribute("users", users);
//        try (Scanner dataFile = new Scanner(Paths.get(path))) {
//
//            while (dataFile.hasNextLine()) {
//                String userData = dataFile.nextLine();
//                String[] userDataSplit = userData.split(" ");
//                String username = userDataSplit[0];
//                String password = userDataSplit[1];
//                request.setAttribute("username", username);
//                request.setAttribute("password", password);
//                User newUser = new User(username, password);
//                users.add(newUser);                
//            }
//        } catch (Exception e1) {
//            System.out.println("Error in Opening File! " + e1.getMessage());
//        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ((username == null || username.equals("")) || (password == null || password.equals(""))) {            
            request.setAttribute("nullInvalid", true);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }

        if (username != null && password != null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            User newUser = new User(username, password);
            if (newUser.login(user1) || newUser.login(user2)) {
                session.setAttribute("user", newUser);
                
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                        .forward(request, response);
            } else {
                request.setAttribute("uORpInvalid", true);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }

        //boolean userFound = false;
        //int i = 0;
        //while (i < users.size() && !userFound) {
        //    User currentUser = users.get(i);
        //    if (user.equals(currentUser)) {
        //        userFound = true;
        //        session.setAttribute("user", user);
        //        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        //session.setAttribute("user", user);
        //    }
        //    i++;
        //}
        //if (i == users.size()){
        //    request.setAttribute("invalid2", true);
        //}
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }

    }

}
