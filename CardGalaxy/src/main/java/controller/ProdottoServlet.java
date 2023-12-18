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
    private static String uploadRoot;

    public void init()throws ServletException{
        super.init();
        giftCardManager=new GiftCardManager();
        uploadRoot=FileServlet.getUploadPath()+File.separator+"uploads"+File.separator;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            String contextPath = request.getContextPath();
            switch (path) {
                case "/add":
                    //inserimento prodotto nel database
                    request.setAttribute("back","/WEB-INF/admin-views/addproduct.jsp");
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
                    g.setFoto(fileName);
                    try (InputStream fileStream = filePart.getInputStream()) {
                        File file = new File("C:\\Users\\giuli\\IdeaProjects\\CardGalaxy-Fork\\CardGalaxy\\src\\main\\webapp\\WEB-INF\\images"+fileName);
                        Files.copy(fileStream, file.toPath());
                    } catch (FileNotFoundException e) {
                        throw new InvalidRequestException("File non valido", List.of("File non valido"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    giftCardManager.inserisciGiftCard(g);
                    Boolean addProduct=true;
                    request.getSession(false).setAttribute("addProduct",addProduct);
                    response.sendRedirect(contextPath + "/products/showall");
                    break;
                case "/remove":
                    int id= Integer.parseInt(request.getParameter("giftid"));
                    giftCardManager.rimuoviGiftCard(id);
                    Boolean removeProduct=true;
                    request.getSession(false).setAttribute("removeProduct",removeProduct);
                    response.sendRedirect(contextPath + "/products/showall");
                    break;
                case "/update":
                    request.setAttribute("back","/WEB-INF/admin-views/editproduct.jsp");
                    validate(ProdottoValidator.validateProdotto(request));
                    GiftCard g1=new GiftCard();
                    g1.setId_prodotto(Integer.parseInt(request.getParameter("giftid")));
                    g1.setAvailable(Boolean.parseBoolean(request.getParameter("availability")));
                    g1.setNome(request.getParameter("name"));
                    g1.setPiattaforma(request.getParameter("platform"));
                    g1.setDescrizione(request.getParameter("description"));
                    g1.setPrezzo(Double.parseDouble(request.getParameter("price")));
                    Part filePart1 = request.getPart("photo");
                    Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
                    String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
                    fileName1=timestamp1.getTime()+fileName1;
                    g1.setFoto(fileName1);
                    try (InputStream fileStream = filePart1.getInputStream()) {
                        File file1 = new File("C:\\Users\\giuli\\IdeaProjects\\CardGalaxy-Fork\\CardGalaxy\\src\\main\\webapp\\WEB-INF\\images"+fileName1);
                        Files.copy(fileStream, file1.toPath());
                    } catch (FileNotFoundException e) {
                        throw new InvalidRequestException("File non valido", List.of("File non valido"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    giftCardManager.aggiornaGiftCard(g1);
                    Boolean editProduct=true;
                    request.getSession(false).setAttribute("editProduct",editProduct);
                    response.sendRedirect(contextPath + "/products/showall");
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
                    request.setAttribute("id",id);
                    request.getRequestDispatcher("/WEB-INF/admin-views/infoproduct.jsp").forward(request, response);
                    break;
                case "/modify":
                    authorize(request.getSession(false));
                    int id1= Integer.parseInt(request.getParameter("giftid"));
                    GiftCard giftCard1=giftCardManager.retrieveGiftCardByID(id1);
                    request.setAttribute("giftCard",giftCard1);
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