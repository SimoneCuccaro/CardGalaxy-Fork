package controller;

import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.prodotto.GiftCardManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                    //click su pagina mostra prodotti(admin)
                    break;
                case "/add":
                    //click su pagina aggiungi prodotto
                    break;
                case "/info":
                    //click su pagina info prodotto(admin)
                    break;
                case "/modify":
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