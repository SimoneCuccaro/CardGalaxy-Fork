package controller;

import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.recensione.Recensione;
import model.recensione.RecensioneManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RecensioneServlet", value = "/reviews/*")
public class RecensioneServlet extends HttpServlet implements ErrorHandler {

    private RecensioneManager recensioneManager;
    public void init() throws ServletException{
        super.init();
        recensioneManager=new RecensioneManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    //inserimento recensione
                    break;
                case "/delete":
                    break;
                case  "/remove":
                    break;
                case "/modify":
                    //click sul pulsante modifica recensione(lato utente)
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
                    //click per visualizzare le recensioni di un dato prodotto(lato utente e admin)
                    //si dovra inserire pulsante elimina recensioni alle recensioni solo dell'utente in sessione
                    //(se utentesessionid==utenterecensioneid) allora mostra pulsante elimina recensione
                    //admin vede sempre pulsante elimina su tuttte le recensioni
                    break;
                case "/managereviews":
                    authorize(request.getSession(false));
                    ArrayList<Recensione> recensione = recensioneManager.retrieveRecensioni();
                    request.setAttribute("reviews",recensione);
                    request.getRequestDispatcher("/WEB-INF/admin-views/managereviews.jsp").forward(request,response);
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