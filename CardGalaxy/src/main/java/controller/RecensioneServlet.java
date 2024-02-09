package controller;

import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.recensione.Recensione;
import model.recensione.RecensioneManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**La classe <code>RecensioneServlet</code> rappresenta la servlet che gestisce gli oggetti di tipo Recensione
 * e le loro iterazioni con le interfaccie utente
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
@WebServlet(name = "RecensioneServlet", value = "/reviews/*")
public class RecensioneServlet extends Controller implements ErrorHandler {


    /**Oggetto di tipo RecensioneManager usato per la gestione delle recensioni all' interno della servlet
     *
     */
    private RecensioneManager recensioneManager;

    /**Oggetto di tipo GiftCardManager usato per la gestione delle GiftCard all' interno della servlet
     *
     */
    private GiftCardManager giftCardManager;
    public void init() throws ServletException{
        super.init();
        recensioneManager=new RecensioneManager();
        giftCardManager=new GiftCardManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/add":
                    //inserimento recensione
                    GiftCard giftCard1 = (GiftCard) request.getSession(false).getAttribute("prodotto");
                    int idutente1=getUtenteSession(request.getSession(false)).getId();
                    String text=request.getParameter("review");
                    GregorianCalendar gregorianCalendar=new GregorianCalendar();
                    int giorno=gregorianCalendar.get(Calendar.DATE);
                    int mese=gregorianCalendar.get(Calendar.MONTH)+1;
                    int anno=gregorianCalendar.get(Calendar.YEAR);
                    String data=giorno+"/"+mese+"/"+anno;
                    Recensione recensione=new Recensione(idutente1,giftCard1.getId_prodotto(),data,text);
                    recensioneManager.inserisciRecensione(recensione);
                    Boolean addString=true;
                    request.getSession(false).setAttribute("addString",addString);
                    response.sendRedirect(contextPath+"/reviews/show2");
                    break;
                case "/delete":
                    GiftCard giftCard = (GiftCard) request.getSession(false).getAttribute("prodotto");
                    int idutente=getUtenteSession(request.getSession(false)).getId();
                    recensioneManager.rimuoviRecensione(idutente,giftCard.getId_prodotto());
                    Boolean deleteString=true;
                    request.getSession(false).setAttribute("deleteString",deleteString);
                    response.sendRedirect(contextPath+"/reviews/show2");
                    break;
                case  "/remove":
                    int giftid= Integer.parseInt(request.getParameter("giftid"));
                    int userid= Integer.parseInt(request.getParameter("userid"));
                    recensioneManager.rimuoviRecensione(userid,giftid);
                    Boolean removeString=true;
                    request.getSession(false).setAttribute("removeString",removeString);
                    response.sendRedirect(contextPath + "/reviews/managereviews");
                    break;
                case "/modify":
                    //click sul pulsante modifica recensione(lato utente)
                    GiftCard giftCard2 = (GiftCard) request.getSession(false).getAttribute("prodotto");
                    int idutente2=getUtenteSession(request.getSession(false)).getId();
                    String text1=request.getParameter("review");
                    GregorianCalendar gregorianCalendar1=new GregorianCalendar();
                    int giorno1=gregorianCalendar1.get(Calendar.DATE);
                    int mese1=gregorianCalendar1.get(Calendar.MONTH)+1;
                    int anno1=gregorianCalendar1.get(Calendar.YEAR);
                    String data1=giorno1+"/"+mese1+"/"+anno1;
                    Recensione recensione1=new Recensione(idutente2,giftCard2.getId_prodotto(),data1,text1);
                    recensioneManager.aggiornaRecensione(recensione1);
                    Boolean updateString=true;
                    request.getSession(false).setAttribute("updateString",updateString);
                    response.sendRedirect(contextPath+"/reviews/show2");
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
                    //(se utentesessionid==utenterecensioneid) allora mostra pulsante elimina recensione
                    int idprodotto= Integer.parseInt(request.getParameter("idprodotto"));
                    GiftCard prodotto=giftCardManager.retrieveGiftCardByID(idprodotto);
                    ArrayList<Recensione> recensioni=recensioneManager.retrieveRecensioniByProdotto(idprodotto);
                    request.getSession(false).setAttribute("recensioni",recensioni);
                    request.getSession(false).setAttribute("prodotto",prodotto);
                    request.getRequestDispatcher("/WEB-INF/views/reviews.jsp").forward(request,response);
                    break;
                case"/show2":
                    GiftCard giftCard= (GiftCard) request.getSession(false).getAttribute("prodotto");
                    ArrayList<Recensione> recensioni2=recensioneManager.retrieveRecensioniByProdotto(giftCard.getId_prodotto());
                    request.getSession(false).setAttribute("recensioni",recensioni2);
                    request.getRequestDispatcher("/WEB-INF/views/reviews.jsp").forward(request,response);
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