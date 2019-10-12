package chushka.web.servlets;

import chushka.models.service.ProductServiceModel;
import chushka.models.view.ProductDetailsViewModel;
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

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private static final String DETAILS_PRODUCT_HTML_FILE_PATH = "C:\\Users\\anato\\OneDrive\\Documents\\SOFTUNI\\Java\\JavaWebBasics\\JavaWebDevelopmentBasics-January2019\\SERVLETS-exercise\\src\\main\\resources\\views\\details-product.html";
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final HtmlReader htmlReader;

    @Inject
    public ProductDetailsServlet(ProductService productService, ModelMapper modelMapper, HtmlReader htmlReader) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getQueryString().split("=")[1];
        ProductServiceModel productServiceModel=this.productService.findProductByName(name);
        ProductDetailsViewModel productDetailsViewModel=this.modelMapper.
                map(productServiceModel,ProductDetailsViewModel.class);
        String htmlContent=this.htmlReader.readHtmlFile(DETAILS_PRODUCT_HTML_FILE_PATH)
                .replace("{{productName}}",productDetailsViewModel.getName())
                .replace("{{productDescription}}",productDetailsViewModel.getDescription())
                .replace("{{productType}}",productDetailsViewModel.getType());
        resp.getWriter().println(htmlContent);
    }
}
