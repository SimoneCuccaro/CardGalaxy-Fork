package controller;

import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.ordine.OrdineManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrdineServlet", value = "/OrdineServlet/*")
public class OrdineServlet extends HttpServlet  implements ErrorHandler {

    private OrdineManager ordineManager;
    public void init() throws ServletException{
        super.init();
        ordineManager=new OrdineManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    //aggiungere ordine al database
                    break;
                case "/update":
                    //effettuare modifica ordine
                    break;
                case "/undone":
                    //utente annulla ordine
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
                    //click su pagina che mostra tutti gli ordini(utente)
                    authenticate(request.getSession(false));
                    break;
                case "/manageorder":
                    //click su pagina che mostra tutti gli ordini(admin)
                    authorize(request.getSession(false));
                    break;
                case "/info":
                    //click su pagina per specifiche ordine(utente)
                    authenticate(request.getSession(false));
                    break;
                case "/admininfo":
                    //click su pagina per specifiche ordine(admin)
                    authorize(request.getSession(false));
                    break;
                case "/modify":
                    //click su pagina per modificare ordine(solo lato utente)
                    authenticate(request.getSession(false));
                    break;
                case "/make":
                    //click su pagina per effettuare ordine
                    authenticate(request.getSession(false));
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