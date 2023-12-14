package controller;

import model.Controller;
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
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProdottoServlet", value = "/products/*")
public class ProdottoServlet extends Controller implements ErrorHandler {

    private GiftCardManager giftCardManager;

    public void init()throws ServletException{
        super.init();
        giftCardManager=new GiftCardManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    //inserimento prodotto nel database
                    break;
                case "/remove":
                    //rimozione di un prodotto nel database
                    break;
                case "/update":
                    //update dati prodotto
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
                case "/showall":
                    authorize(request.getSession(false));
                    ArrayList<GiftCard> giftCards = giftCardManager.retrieveTutteGiftCard();
                    request.setAttribute("giftCards",giftCards);
                    request.getRequestDispatcher("/WEB-INF/admin-views/manageproducts.jsp").forward(request, response);
                    break;
                case "/add":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/admin-views/addproduct.jsp").forward(request, response);
                    break;
                case "/info":
                    authorize(request.getSession(false));
                    //click su pagina info prodotto(admin)
                    break;
                case "/modify":
                    authorize(request.getSession(false));
                    //click su pagina modifica prodotto
                    break;
                case "/shopnow":
                    request.getRequestDispatcher("/WEB-INF/views/shopnow.jsp").forward(request, response);
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