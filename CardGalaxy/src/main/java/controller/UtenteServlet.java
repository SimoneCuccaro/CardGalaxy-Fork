package controller;

import jakarta.servlet.annotation.MultipartConfig;
import model.Controller;
import model.errors.InvalidRequestException;
import model.errors.ErrorHandler;
import model.utente.Utente;
import model.utente.UtenteManager;
import model.utente.UtenteSession;
import model.validator.UtenteValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "UtenteServlet", value = "/user/*")
@MultipartConfig
public class UtenteServlet extends Controller implements ErrorHandler{

    private UtenteManager utenteManager;

    public void init()throws ServletException{
        super.init();
        utenteManager=new UtenteManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/signin":
                    //effettuazione del login
                    request.setAttribute("back", "/WEB-INF/views/login.jsp");
                    Utente tmpUtente=new Utente();
                    tmpUtente.setEmail(request.getParameter("username"));
                    tmpUtente.setPass(request.getParameter("password"));
                    tmpUtente = utenteManager.retrieveUtentePass(tmpUtente.getUsername(), tmpUtente.getPass());
                    if (tmpUtente!=null) {
                        UtenteSession utenteSession = new UtenteSession(tmpUtente);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        if (utenteSession.isAdmin()) {
                            response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/admin");
                        } else {
                            response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/home");
                        }
                    } else {
                        throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                break;
                case "/logout":
                    //effettuazione del logout
                    response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/home");
                    break;
                case "/create":
                    //effettuazione del register account
                    request.setAttribute("back", "/WEB-INF/views/site/signup.jsp");
                    validate(UtenteValidator.validateUtente(request));
                    Utente utente=new Utente();
                    //mettere parametri registrazione qui
                    utenteManager.creaUtente(utente);
                    if(utente!=null){
                        UtenteSession utenteSession = new UtenteSession(utente);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/home");
                    }else{
                        internalError();
                    }
                    break;
                case "/update":
                    //effettuazione del modify account(utente)
                    response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/details");
                    break;
                case "/delete":
                    //anche un utente puo cancellare il suo account(mettere controllo e poi redirect alla home o pagina che conferma eliminazione account)
                    response.sendRedirect("/CardGalaxy_war_exploded/UtenteServlet/showallusers");
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
                        case "/home":
                            //pagina iniziale
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                            break;
                        case "/login":
                            //click sul pulsante di login
                            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                            break;
                        case "/register":
                            //click sul pulsante di registrazione
                            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
                            break;
                        case "/admin":
                            //pagina iniziale admin
                            request.getRequestDispatcher("/WEB-INF/admin-views/dashboard.jsp").forward(request, response);
                            break;
                        case "/profile":
                            //click su pagina personale
                            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                            break;
                        case "/details":
                            //click su dettagli account(utente)
                            request.getRequestDispatcher("/WEB-INF/views/accountdetails.jsp").forward(request, response);
                            break;
                        case "/modify":
                            //click su modifica account(utente)
                            request.getRequestDispatcher("/WEB-INF/views/editaccount.jsp").forward(request, response);
                            break;
                        case "/showallusers":
                            //click su lista utenti(admin)
                            request.getRequestDispatcher("/WEB-INF/admin-views/managecustomers.jsp").forward(request, response);
                            break;
                        case "/aboutus":
                            request.getRequestDispatcher("/WEB-INF/views/aboutus.jsp").forward(request, response);
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
