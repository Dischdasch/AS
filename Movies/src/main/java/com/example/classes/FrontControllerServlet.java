package com.example.classes;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.util.logging.Logger;



@WebServlet(name = "FrontControllerServlet", value = "/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String command = request.getParameter("command");
            switch (command) {
                case "login":
                    LoginCommand.process(request, response);
                    break;
                case "falsePassword":
                    FalsePasswordCommand.process(request, response);
                    break;
                case "success":
                    SuccessCommand.process(request, response);
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
            Movie movie = Movie.find(request.getParameter("name"));
            //request.setAttribute("helper",new ArtistHelper(art));
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
            forward("/details.jsp", request, response);
        }
    }

    static class SuccessCommand extends FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        /*static class UserCommand extends FrontCommand {
            protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

            }

            protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                processRequest(request, response);
            }

            protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                processRequest(request, response);
            }

            public String getServletInfo() {
                return "Short description";
            }// </editor-fold>

            public void process(HttpServletRequest request) {
                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    StreamSource xslFirstStage = new StreamSource(new File("C:\\Users\\YonePC\\Videos\\ASAPLICACIONCURSOSPRACTICA1\\src\\java\\frontController\\FirstStageAlumnos.xsl"));
                    StreamSource xslSecondStage = new StreamSource(new File("C:\\Users\\YonePC\\Videos\\ASAPLICACIONCURSOSPRACTICA1\\src\\java\\frontController\\Alumnos.xsl"));
                    Transformer firstTransformer = transformerFactory.newTransformer(xslFirstStage);
                    Transformer secondTransformer = transformerFactory.newTransformer(xslSecondStage);

                    StreamSource xml = new StreamSource(new File("C:\\Users\\YonePC\\Videos\\ASAPLICACIONCURSOSPRACTICA1\\src\\java\\frontController\\Alumnos.xml"));
                    PrintWriter writer = response.getWriter();
                    Result result = new StreamResult(writer);


                    OutputStream afterFirstStage = new FileOutputStream("C:\\Users\\YonePC\\Videos\\ASAPLICACIONCURSOSPRACTICA1\\src\\java\\frontController\\afterFirstStage.xsl");
                    firstTransformer.transform(xml, new StreamResult(afterFirstStage));
                    secondTransformer.transform((Source) afterFirstStage, (Result) xslSecondStage);
                    writer.println(writer.toString());

                    TransformerHandler tfh = (SAXTransformerFactory)transformerFactory.newInstance().newTransformer(xslSecondStage);
                    tfh.setResult(new StreamResult("finalOutput.xml"));

                    firstTransformer.transform(xslFirstStage, new SAXResult(tfh));

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (TransformerException te) {
                    te.printStackTrace();

                }
            }
        }*/
    }
}