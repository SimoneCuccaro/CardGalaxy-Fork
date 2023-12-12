package controller;

import model.carrello.CarrelloManager;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart/*")
public class CartServlet extends HttpServlet implements ErrorHandler {

    private CarrelloManager carrelloManager;
    public void init() throws ServletException{
        super.init();
        carrelloManager=new CarrelloManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    authenticate(request.getSession(false));
                    //aggiunge prodotto al carrello
                    break;
                case "/remove":
                    authenticate(request.getSession(false));
                    //rimuovo prodotto dal carrello
                    break;
                default:
                    notFound();
            }
        }catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/show":
                    authenticate(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request,response);
                    break;
                default:
                    notFound();
            }
        }catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        }

    }
}