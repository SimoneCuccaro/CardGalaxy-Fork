package controller;

import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.richiestasupporto.RichiestaSupportoManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RichiestaSupportoServlet", value = "/RichiestaSupportoServlet/*")
public class RichiestaSupportoServlet extends HttpServlet implements ErrorHandler {
    private RichiestaSupportoManager richiestaSupportoManager;
    public void init() throws ServletException{
        super.init();
        richiestaSupportoManager=new RichiestaSupportoManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/submit":
                    //click sul pulsante invia la richiesta
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
                case "/create":
                    //click per andare sulla pagina di invio richiesta
                    break;
                case "/showall":
                    //per andare sulla pagina admin e visualizzare tutte le richieste
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