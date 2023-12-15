package controller;

import jakarta.servlet.RequestDispatcher;
import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.ordine.Ordine;
import model.richiestasupporto.RichiestaSupporto;
import model.richiestasupporto.RichiestaSupportoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RichiestaSupportoServlet", value = "/help/*")
public class RichiestaSupportoServlet extends Controller implements ErrorHandler {
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
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
            RequestDispatcher dispatcher;
            String resource;
            switch (path) {
                case "/create":
                    authenticate(request.getSession(false));
                    resource = "/WEB-INF/views/help.jsp";
                    request.getRequestDispatcher(resource).forward(request,response);
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