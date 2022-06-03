package com.example.moviegf6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;



@WebServlet(name = "FrontControllerServlet", value = "/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String command = request.getParameter("command");
            switch (command) {
                case "login":
                    MovieAccessor movieA = new MovieAccessor();
                    movieA.setMovie("Back to the Future", "An Adventurous Sci-Fi Comedy");
                    movieA.setMovie("Freedom", "A Man is Trying to Keep his Dark Secret");
                    movieA.setMovie("Joker", "A Serious Thriller based on a Comic");
                    LoginCommand.process(request, response);
                    break;
                case "falsePassword":
                    FalsePasswordCommand.process(request, response);
                    break;
                case "success":
                    SuccessCommand.process(request, response);
                    break;
                case "delete":
                    DeleteCommand.process(request, response);
                    break;
                case "details":
                    DetailsCommand.process(request, response);
                    break;
                case "addToCart":
                    HttpSession session = request.getSession(true);
                    ShoppingCart cart = (ShoppingCart)
                            session.getAttribute("cart");
                    if(cart != null){
                        cart.addProduct(request.getParameter("movie"));
                    }else {
                        cart = new CartBean();
                        cart.initialize();
                        session.setAttribute("cart", cart);
                        cart.addProduct(request.getParameter("movie"));
                    }
                    AddToCartCommand.process(request, response);
                    break;
                default:
                    UnknownCommand.process(request, response);
            }
        } catch (Exception e) {
            ErrorCommand.process(request, response);
        }
    }


    public static abstract class FrontCommand {
        protected ServletContext context;
        protected HttpServletRequest request;
        protected HttpServletResponse response;

        public void init(/*javax.servlet.ServletContext servletContext,*/ HttpServletRequest request, HttpServletResponse response) {
            //this.context = context;
            this.request = request;
            this.response = response;
        }

        public static void process() throws ServletException, IOException {
        }

        public static void forward(String target, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dp =
                    request.getRequestDispatcher(target);
            dp.forward(request, response);
        }
    }

    class MovieCommand extends FrontCommand {
        public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("/movie.jsp", request, response);
        }
    }

    static class UnknownCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Create a Logger
            Logger logger
                    = Logger.getLogger(
                    UnknownCommand.class.getName());

            // Call info method
            logger.info("No Knwon Command Was Passed in Request");
            forward("/unknown.jsp", request, response);
        }

    }

    static class DetailsCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            MovieAccessor movieA = new MovieAccessor();
            request.setAttribute("movieId", movieA.getMovie(Integer.parseInt(request.getParameter("movieId"))));
            forward("/details.jsp", request, response);
        }
    }

    static class DeleteCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            MovieAccessor movieA = new MovieAccessor();
            MovieEntity movieToDelete = movieA.getMovie(Integer.parseInt(request.getParameter("delete")));
            movieA.delete(movieToDelete);
            forward("/details.jsp", request, response);
        }
    }

    static class SuccessCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            MovieAccessor movieA = new MovieAccessor();
            List<MovieEntity> movies = movieA.findAll();
            request.setAttribute("allMovies", movies);
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

            int count = movies.size();

            int pageNumber = count / recordsPerPage;

            if (pageNumber % recordsPerPage > 0) {

                pageNumber++;
            }

            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            forward("/success.jsp", request, response);
        }
    }

    static class AddToCartCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("/cart.jsp", request, response);
        }
    }

    static class ErrorCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("/error.jsp", request, response);
        }
    }

    static class FalsePasswordCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("/falsePassword.jsp", request, response);
        }
    }

    public static class LoginCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                if (request.getParameter("username") != null || request.getParameter("pwd2") != null) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("pwd2");

                    if (password.equals("Password1")) {

                        RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                        rd.forward(request, response);
                    } else {

                        RequestDispatcher rd = request.getRequestDispatcher("/falsePassword.jsp");
                        rd.forward(request, response);
                    }
                } else if (request.getParameter("movie") != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("/details.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception e) {
                RequestDispatcher rd = request.getRequestDispatcher("/unknown.jsp");
                rd.forward(request, response);
            }
        }

    }
}