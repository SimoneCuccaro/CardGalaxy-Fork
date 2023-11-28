package controller;

import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.rispostasupporto.RispostaSupportoManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RispostaSupportoServlet", value = "/RispostaSupportoServlet/*")
public class RispostaSupportoServlet extends HttpServlet implements ErrorHandler {

    private RispostaSupportoManager rispostaSupportoManager;
    public void init() throws ServletException{
        super.init();
        rispostaSupportoManager=new RispostaSupportoManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/submit":
                    //click per inviare una risposta
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
                    //click sul pulsante per inviare una risposta (all' interno della pagina in cui ci sono tutte le richieste?)
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