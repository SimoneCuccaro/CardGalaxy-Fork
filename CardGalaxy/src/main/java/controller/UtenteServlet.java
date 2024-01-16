package controller;

import jakarta.servlet.annotation.MultipartConfig;
import model.Controller;
import model.carrello.Carrello;
import model.carrello.CarrelloManager;
import model.carrello.CarrelloSession;
import model.carrello.CartItems;
import model.errors.InvalidRequestException;
import model.errors.ErrorHandler;
import model.ordine.OrdineManager;
import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.utente.Utente;
import model.utente.UtenteManager;
import model.utente.UtenteSession;
import model.validator.UtenteValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@WebServlet(name = "UtenteServlet", value = "/user/*")
@MultipartConfig
public class UtenteServlet extends Controller implements ErrorHandler{

    private UtenteManager utenteManager;
    private OrdineManager ordineManager;
    private GiftCardManager giftCardManager;

    private CarrelloManager carrelloManager;

    public void init()throws ServletException{
        super.init();
        utenteManager=new UtenteManager();
        ordineManager = new OrdineManager();
        giftCardManager = new GiftCardManager();
        carrelloManager=new CarrelloManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            HttpSession session = request.getSession(false);
            switch (path) {
                case "/signin":
                    //effettuazione del login
                    request.setAttribute("back", "/WEB-INF/views/login.jsp");
                    Utente tmpUtente=new Utente();
                    tmpUtente.setUsername(request.getParameter("user"));
                    tmpUtente.setPass(request.getParameter("password"));
                    tmpUtente = utenteManager.retrieveUtentePass(tmpUtente.getUsername(), tmpUtente.getPass());
                    if (tmpUtente!=null) {
                        UtenteSession utenteSession = new UtenteSession(tmpUtente);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        if (utenteSession.isAdmin()) {
                            Boolean adminString=true;
                            request.getSession(false).setAttribute("adminString",adminString);
                            response.sendRedirect(contextPath + "/user/admin");
                        } else {
                            Boolean userString=true;
                            request.getSession(false).setAttribute("loginString",userString);
                            CarrelloSession cart=new CarrelloSession(carrelloManager.retrieveCarrelloByUtente(utenteSession.getId()));
                            request.getSession(false).setAttribute("cart",cart);
                            response.sendRedirect(contextPath + "/user/profile");
                        }
                    } else {
                        throw new InvalidRequestException("Invalid credentials", List.of("Invalid credentials"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                break;
                case "/logout":
                    //effettuazione del logout
                    List<CartItems> items= (List<CartItems>) getSessionCart(request.getSession(false)).getItems();
                    carrelloManager.saveToCart(getUtenteSession(request.getSession(false)).getId(),items);
                    session.removeAttribute("utenteSession");
                    session.removeAttribute("cart");
                    session.invalidate();
                    response.sendRedirect(contextPath + "/user/home");
                    break;
                case "/register":
                    // aggiungere alert di avvenuta operazione
                    request.setAttribute("back", "/WEB-INF/views/register.jsp");
                    validate(UtenteValidator.validateUtente(request));
                    Utente utente=new Utente();
                    utente.setEmail(request.getParameter("email"));
                    utente.setNome(request.getParameter("name"));
                    utente.setCognome(request.getParameter("surname"));
                    utente.setUsername(request.getParameter("user"));
                    utente.setPass(request.getParameter("password"));
                    utente.setIndirizzo(request.getParameter("address"));
                    utente.setNazione(request.getParameter("nation"));
                    utente.setCitta(request.getParameter("city"));
                    utente.setCap(Integer.parseInt(request.getParameter("cap")));
                    utente.setData_nascita(request.getParameter("bday"));
                    utente.setAdmin(false);
                    utenteManager.creaUtente(utente);
                    if(utente!=null){
                        UtenteSession utenteSession = new UtenteSession(utente);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        CarrelloSession cart=new CarrelloSession();
                        request.getSession(false).setAttribute("cart",cart);
                        Boolean userString=true;
                        request.getSession(false).setAttribute("registerString",userString);
                        response.sendRedirect(contextPath + "/user/profile");
                    }else{
                        internalError();
                    }
                    break;
                case "/update":
                    // aggiungere alert di avvenuta operazione
                    request.setAttribute("back","/WEB-INF/views/editaccount.jsp");
                    validate(UtenteValidator.validateUtente(request));
                    Utente u = new Utente();
                    u.setUsername(request.getParameter("user"));
                    u.setNome(request.getParameter("name"));
                    u.setCognome(request.getParameter("surname"));
                    u.setEmail(request.getParameter("email"));
                    u.setCap(Integer.parseInt(request.getParameter("cap")));
                    u.setNazione(request.getParameter("nation"));
                    u.setCitta(request.getParameter("city"));
                    u.setIndirizzo(request.getParameter("address"));
                    u.setData_nascita(request.getParameter("bday"));
                    u.setPass(request.getParameter("password"));
                    u.setAdmin(false);
                    u.setId(getUtenteSession(session).getId());
                    utenteManager.aggiornaUtente(u);
                    request.getSession(false).removeAttribute("user");
                    if(u!=null){
                        UtenteSession utenteSession = new UtenteSession(u);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        Boolean updateString=true;
                        request.getSession(false).setAttribute("updateString",updateString);
                        response.sendRedirect(contextPath + "/user/profile");
                    }else{
                        internalError();
                    }
                    break;
                case "/delete":
                    utenteManager.cancellaUtente(getUtenteSession(session).getId());
                    session.removeAttribute("utenteSession");
                    session.removeAttribute("cart");
                    Boolean deleteString=true;
                    request.getSession(false).setAttribute("deleteString",deleteString);
                    response.sendRedirect(contextPath + "/user/login");
                    break;
                case "/remove":
                    utenteManager.cancellaUtente(Integer.parseInt(request.getParameter("customerid")));
                    Boolean removeString=true;
                    request.getSession(false).setAttribute("removeString",removeString);
                    response.sendRedirect(contextPath + "/user/showallusers");
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
                    HttpSession session = request.getSession(false);
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
                            authorize(request.getSession(false));
                            Integer allUsers = utenteManager.countUsers();
                            Double allProfits = ordineManager.getGuadagno();
                            Integer allCards = giftCardManager.countAllGiftCard();
                            request.setAttribute("countUsers",allUsers);
                            request.setAttribute("countProfits",allProfits);
                            request.setAttribute("countCards",allCards);
                            request.getRequestDispatcher("/WEB-INF/admin-views/dashboard.jsp").forward(request, response);
                            break;
                        case "/profile":
                            //click su pagina personale
                            authenticate(request.getSession(false));
                            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
                            break;
                        case "/details":
                            //click su dettagli account(utente)
                            authenticate(request.getSession(false));
                            Utente u = utenteManager.retrieveUtente(getUtenteSession(session).getId());
                            request.setAttribute("user",u);
                            request.getRequestDispatcher("/WEB-INF/views/accountdetails.jsp").forward(request, response);
                            break;
                        case "/modify":
                            //click su modifica account(utente)
                            authenticate(request.getSession(false));
                            Utente ut = utenteManager.retrieveUtente(getUtenteSession(session).getId());
                            request.getSession(false).setAttribute("user",ut);
                            request.getRequestDispatcher("/WEB-INF/views/editaccount.jsp").forward(request, response);
                            break;
                        case "/showallusers":
                            authorize(request.getSession(false));
                            ArrayList<Utente> utenti = utenteManager.retrieveUtenti();
                            utenti.removeIf(Utente::is_admin);
                            request.setAttribute("customers",utenti);
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
