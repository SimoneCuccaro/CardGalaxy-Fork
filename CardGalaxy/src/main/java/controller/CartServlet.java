package controller;

import model.Controller;
import model.carrello.CarrelloManager;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart/*")
public class CartServlet extends Controller implements ErrorHandler {

    private CarrelloManager carrelloManager;
    private GiftCardManager giftCardManager;
    public void init() throws ServletException{
        super.init();
        carrelloManager=new CarrelloManager();
        giftCardManager=new GiftCardManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/add":
                    authenticate(request.getSession(false));
                    //aggiunge prodotto al carrello
                    int idprod= Integer.parseInt(request.getParameter("idprod"));
                    GiftCard gc=giftCardManager.retrieveGiftCardByID(idprod);
                    getSessionCart(request.getSession(false)).addProduct(gc,1);
                    response.sendRedirect(contextPath+"/products/shopnow");
                    break;
                case "/remove":
                    authenticate(request.getSession(false));
                    //rimuovo prodotto dal carrello
                    int idprodotto= Integer.parseInt(request.getParameter("idprodotto"));
                    carrelloManager.removeFromCart(getUtenteSession(request.getSession(false)).getId(),idprodotto);
                    getSessionCart(request.getSession(false)).removeProduct(idprodotto);
                    Boolean cartString=true;
                    request.getSession(false).setAttribute("cartString",cartString);
                    response.sendRedirect(contextPath+"/cart/show");
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