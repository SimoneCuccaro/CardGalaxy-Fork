package controller;

import model.Controller;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.validator.ProdottoValidator;
import model.validator.UtenteValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProdottoServlet", value = "/products/*")
@MultipartConfig
public class ProdottoServlet extends Controller implements ErrorHandler {

    private GiftCardManager giftCardManager;

    public void init()throws ServletException{
        super.init();
        giftCardManager=new GiftCardManager();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/add":
                    //inserimento prodotto nel database
                    request.setAttribute("back","/WEB-INF/admin-views/manageproducts.jsp");
                    validate(ProdottoValidator.validateProdotto(request));
                    GiftCard g=new GiftCard();
                    g.setAvailable(true);
                    g.setNome(request.getParameter("name"));
                    g.setPiattaforma(request.getParameter("platform"));
                    g.setDescrizione(request.getParameter("description"));
                    g.setPrezzo(Double.parseDouble(request.getParameter("price")));
                    Part filePart = request.getPart("photo");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    fileName=timestamp.getTime()+fileName;
                    g.setFoto("/images/"+ fileName);
                    try (InputStream fileStream = filePart.getInputStream()) {
                        //cambiare path e metterlo dinamico
                        File file = new File("C:\\Users\\giuli\\IdeaProjects\\KozmoMusic\\src\\main\\webapp\\images\\" + fileName);
                        Files.copy(fileStream, file.toPath());
                    } catch (FileNotFoundException e) {
                        throw new InvalidRequestException("File non valido", List.of("File non valido"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    giftCardManager.inserisciGiftCard(g);
                    Boolean added=true;
                    request.getSession(false).setAttribute("addDone",added);
                    response.sendRedirect(contextPath + "/products/showall");
                    break;
                case "/remove":
                    int id= Integer.parseInt(request.getParameter("giftid"));
                    giftCardManager.rimuoviGiftCard(id);
                    Boolean remove=true;
                    request.getSession(false).setAttribute("removeDone",remove);
                    response.sendRedirect(contextPath + "/products/showall");
                    break;
                case "/update":
                    //update dati prodotto
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
                    authorize(request.getSession(false));
                    ArrayList<GiftCard> giftCards = giftCardManager.retrieveTutteGiftCard();
                    request.setAttribute("giftCards",giftCards);
                    request.getRequestDispatcher("/WEB-INF/admin-views/manageproducts.jsp").forward(request, response);
                    break;
                case "/add":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/admin-views/addproduct.jsp").forward(request, response);
                    break;
                case "/info":
                    authorize(request.getSession(false));
                    int id= Integer.parseInt(request.getParameter("giftid"));
                    GiftCard giftCard=giftCardManager.retrieveGiftCardByID(id);
                    request.setAttribute("giftCard",giftCard);
                    request.getRequestDispatcher("/WEB-INF/admin-views/productinfo.jsp").forward(request, response);
                    break;
                case "/modify":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/admin-views/editproduct.jsp").forward(request, response);
                    break;
                case "/shopnow":
                    request.getRequestDispatcher("/WEB-INF/views/shopnow.jsp").forward(request, response);
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