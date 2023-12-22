package controller;

import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.richiestasupporto.RichiestaSupporto;
import model.richiestasupporto.RichiestaSupportoManager;
import model.rispostasupporto.RispostaSupporto;
import model.rispostasupporto.RispostaSupportoManager;
import model.utente.Utente;
import model.utente.UtenteManager;
import model.validator.RispostaValidator;
import model.validator.UtenteValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RispostaSupportoServlet", value = "/response/*")
public class RispostaSupportoServlet extends Controller implements ErrorHandler {

    private RispostaSupportoManager rispostaSupportoManager;
    private RichiestaSupportoManager richiestaSupportoManager;
    private UtenteManager utenteManager;
    public void init() throws ServletException{
        super.init();
        rispostaSupportoManager=new RispostaSupportoManager();
        utenteManager=new UtenteManager();
        richiestaSupportoManager=new RichiestaSupportoManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/submit":
                    //click per inviare una risposta
                    request.setAttribute("back", "/WEB-INF/admin-views/makeresponse.jsp");
                    validate(RispostaValidator.validateRisposta(request));
                    int requestid= Integer.parseInt(request.getParameter("requestid"));
                    int userid= Integer.parseInt(request.getParameter("userid"));
                    String risposta=request.getParameter("response");
                    RispostaSupporto rispostaSupporto=new RispostaSupporto();
                    rispostaSupporto.setId_richiesta_supporto(requestid);
                    rispostaSupporto.setRisposta(risposta);
                    rispostaSupporto.setId_utente(userid);
                    rispostaSupportoManager.inserisciRispostaSupporto(rispostaSupporto);
                    request.getSession(false).removeAttribute("requestid");
                    request.getSession(false).removeAttribute("userid");
                    request.getSession(false).removeAttribute("request_object");
                    request.getSession(false).removeAttribute("user_username");
                    Boolean responseDone=true;
                    request.getSession(false).setAttribute("responseDone",responseDone);
                    response.sendRedirect(contextPath + "/help/showall");
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
                    authorize(request.getSession(false));
                    int requestid= Integer.parseInt(request.getParameter("requestid"));
                    int userid= Integer.parseInt(request.getParameter("userid"));
                    Utente u;
                    u=utenteManager.retrieveUtente(userid);
                    RichiestaSupporto richiesta;
                    richiesta=richiestaSupportoManager.retrieveRichiestaSupportoByID(requestid);
                    request.getSession(false).setAttribute("requestid",richiesta.getId_richiesta());
                    request.getSession(false).setAttribute("userid",u.getId());
                    request.getSession(false).setAttribute("request_object",richiesta.getOggetto_richiesta());
                    request.getSession(false).setAttribute("user_username",u.getUsername());
                    request.getRequestDispatcher("/WEB-INF/admin-views/makeresponse.jsp").forward(request, response);
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