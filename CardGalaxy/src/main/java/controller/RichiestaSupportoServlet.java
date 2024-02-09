package controller;

import jakarta.servlet.RequestDispatcher;
import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.ordine.Ordine;
import model.richiestasupporto.RichiestaSupporto;
import model.richiestasupporto.RichiestaSupportoManager;
import model.validator.RichiestaValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**La classe <code>RichiestaSupportoServlet</code> rappresenta la servlet che gestisce gli oggetti di tipo RichiestaSupporto
 * e le loro iterazioni con le interfaccie utente
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
@WebServlet(name = "RichiestaSupportoServlet", value = "/help/*")
public class RichiestaSupportoServlet extends Controller implements ErrorHandler {

    /**Oggetto di tipo RichiestaSupportoManager usato per la gestione delle richieste di supporto all' interno della servlet
     *
     */
    private RichiestaSupportoManager richiestaSupportoManager;
    public void init() throws ServletException{
        super.init();
        richiestaSupportoManager=new RichiestaSupportoManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/submit":
                    request.setAttribute("back","/WEB-INF/views/help.jsp");
                    validate(RichiestaValidator.validateRichiesta(request));
                    RichiestaSupporto richiestaSupporto=new RichiestaSupporto();
                    richiestaSupporto.setRichiesta(request.getParameter("request"));
                    richiestaSupporto.setOggetto_richiesta(request.getParameter("object"));
                    richiestaSupporto.setId_utente(getUtenteSession(request.getSession(false)).getId());
                    richiestaSupportoManager.inserisciRichiestaSupporto(richiestaSupporto);
                    Boolean addRequest=true;
                    request.getSession(false).setAttribute("addRequest",addRequest);
                    response.sendRedirect(contextPath + "/help/create");
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
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
            RequestDispatcher dispatcher;
            String resource;
            switch (path) {
                case "/create":
                    if(getUtenteSession(request.getSession(false))==null){
                        Boolean helpString=true;
                        request.getSession(true).setAttribute("helpString",helpString);
                        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
                    }
                    request.getRequestDispatcher("/WEB-INF/views/help.jsp").forward(request,response);
                    break;
                case "/showall":
                    authorize(request.getSession(false));
                    ArrayList<RichiestaSupporto> richieste = richiestaSupportoManager.retrieveAllRequest();
                    request.setAttribute("richieste",richieste);
                    request.getRequestDispatcher("/WEB-INF/admin-views/managehelprequests.jsp").forward(request, response);
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