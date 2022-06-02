package com.example.classes;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart)
                session.getAttribute("cart");
        if(cart != null){
            cart.addProduct(request.getParameter("movie"));
        }else {
            cart = new CartBean();
            session.setAttribute("cart", cart);
            cart.addProduct(request.getParameter("movie"));
        }
        AddToCartCommand.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static void forward(String target, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dp =
                request.getRequestDispatcher(target);
        dp.forward(request, response);
    }

    static class AddToCartCommand extends FrontControllerServlet.FrontCommand {
        public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            forward("/cart.jsp", request, response);
        }
    }
}
