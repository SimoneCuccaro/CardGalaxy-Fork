package controller;

import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.ordine.Ordine;
import model.ordine.OrdineManager;
import model.prodotto.GiftCard;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

@WebServlet(name = "OrdineServlet", value = "/orders/*")
public class OrdineServlet extends Controller implements ErrorHandler {

    private OrdineManager ordineManager;
    public void init() throws ServletException{
        super.init();
        ordineManager=new OrdineManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String contextPath = request.getContextPath();
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    //aggiungere ordine al database
                    break;
                case "/update":
                    int orderid= Integer.parseInt(request.getParameter("orderid"));
                    int productid= Integer.parseInt(request.getParameter("productid"));
                    int quantity= Integer.parseInt(request.getParameter("quantity"));
                    ordineManager.updateContenuto(quantity,orderid,productid);
                    Hashtable<GiftCard,Integer> prodotti=ordineManager.retriveProdotti(orderid);
                    double totale=0;
                    Enumeration<GiftCard> e=prodotti.keys();
                    while(e.hasMoreElements()){
                        GiftCard k=e.nextElement();
                        totale+= k.getPrezzo()*prodotti.get(k);
                    }
                    ordineManager.updateOrder(totale);
                    Ordine ordinetmp=ordineManager.retrieveOrdineById(orderid);
                    ordinetmp.setProdottoList(ordineManager.retriveProdotti(ordinetmp.getId()));
                    request.getSession(false).setAttribute("order",ordinetmp);
                    Boolean updateDone=true;
                    request.getSession(false).setAttribute("updateDone",updateDone);
                    response.sendRedirect(contextPath+"/orders/updateDone");
                    break;
                case "/remove":
                    ordineManager.removeOrder(Integer.parseInt(request.getParameter("orderid")));
                    Boolean removeOrder=true;
                    request.getSession(false).setAttribute("removeOrder",removeOrder);
                    response.sendRedirect(contextPath + "/orders/showall");
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
                    ArrayList<Ordine> ordiniutente = ordineManager.retrieveOrdiniByUtente(getUtenteSession(request.getSession(false)).getId());
                    request.setAttribute("orders",ordiniutente);
                    request.getRequestDispatcher("/WEB-INF/views/orderhistory.jsp").forward(request, response);
                    break;
                case "/manageorders":
                    //click su pagina che mostra tutti gli ordini(admin)
                    authorize(request.getSession(false));
                    ArrayList<Ordine> ordini = ordineManager.retrieveOrdini();
                    request.setAttribute("ordini",ordini);
                    request.getRequestDispatcher("/WEB-INF/admin-views/manageorders.jsp").forward(request, response);
                    break;
                case "/info":
                    //click su pagina per specifiche ordine(utente)
                    authenticate(request.getSession(false));
                    int idorder= Integer.parseInt(request.getParameter("orderid"));
                    Ordine order=ordineManager.retrieveOrdineById(idorder);
                    order.setProdottoList(ordineManager.retriveProdotti(order.getId()));
                    request.setAttribute("order",order);
                    request.getRequestDispatcher("/WEB-INF/views/orderdetails.jsp").forward(request, response);
                    break;
                case "/admininfo":
                    //click su pagina per specifiche ordine(admin)
                    authorize(request.getSession(false));
                    int id= Integer.parseInt(request.getParameter("orderid"));
                    Ordine ordine=ordineManager.retrieveOrdineById(id);
                    ordine.setProdottoList(ordineManager.retriveProdotti(ordine.getId()));
                    request.setAttribute("order",ordine);
                    request.getRequestDispatcher("/WEB-INF/admin-views/orderspecifics.jsp").forward(request, response);
                    break;
                case "/modify":
                    //click su pagina per modificare ordine(solo lato utente)
                    authenticate(request.getSession(false));
                    int idordine= Integer.parseInt(request.getParameter("orderid"));
                    Ordine ordinetmp=ordineManager.retrieveOrdineById(idordine);
                    ordinetmp.setProdottoList(ordineManager.retriveProdotti(ordinetmp.getId()));
                    request.getSession(false).setAttribute("order",ordinetmp);
                    request.getRequestDispatcher("/WEB-INF/views/editorder.jsp").forward(request, response);
                    break;
                case "/updateDone":
                    Boolean check= (Boolean) request.getSession(false).getAttribute("updateDone");
                    if(check==null || check==false){
                        notAllowed();
                    }
                    request.getRequestDispatcher("/WEB-INF/views/editorder.jsp").forward(request, response);
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