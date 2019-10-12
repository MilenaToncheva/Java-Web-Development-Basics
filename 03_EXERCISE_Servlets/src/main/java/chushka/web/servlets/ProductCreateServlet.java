package chushka.web.servlets;

import chushka.domain.entities.Type;
import chushka.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Proxy;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private static final String CREATE_PRODUCT_HTML_FILE_PATH = "C:\\Users\\anato\\OneDrive\\Documents\\SOFTUNI\\Java\\JavaWebBasics\\JavaWebDevelopmentBasics-January2019\\SERVLETS-exercise\\src\\main\\resources\\views\\create-product.html";
    private final HtmlReader htmlReader;
    private final ProductService productService;

    @Inject
    public ProductCreateServlet(HtmlReader htmlReader, ProductService productService) {
        this.htmlReader = htmlReader;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileContent = this.htmlReader.readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH).replace("{{typeOptions}}", this.formatTypeOptions());
        resp.getWriter().println(fileContent);
    }

    private String formatTypeOptions() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(Type.values())
                .forEach(t -> sb.append(String.format("<option value=\"%s\">%s</option>", t.name(), t.name())).append(System.lineSeparator()));
        return sb.toString().trim();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel product = new ProductServiceModel();

        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        product.setType(req.getParameter("type"));

        this.productService.saveProduct(product);
        resp.sendRedirect("/");
    }
}
