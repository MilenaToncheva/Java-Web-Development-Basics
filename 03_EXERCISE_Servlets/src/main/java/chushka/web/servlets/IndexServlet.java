package chushka.web.servlets;

import chushka.models.view.AllProductsViewModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private static final String HTML_FILE_PATH = "C:\\Users\\anato\\OneDrive\\Documents\\SOFTUNI\\Java\\JavaWebBasics" +
            "\\JavaWebDevelopmentBasics-January2019\\SERVLETS-exercise\\src\\main\\resources\\views\\Index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String html = this.htmlReader.readHtmlFile(HTML_FILE_PATH).replace("{{listItems}}", this.formatListItems());

        out.println(html);
    }

    private String formatListItems() {
        List<AllProductsViewModel> allProducts = this.productService.findAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, AllProductsViewModel.class))
                .collect(Collectors.toList());
        StringBuilder allProductsList = new StringBuilder();
        allProducts.forEach(p -> allProductsList.append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>", p.getName(), p.getName())).append(System.lineSeparator()));
        System.out.println();
        return allProductsList.toString().trim();
    }
}
