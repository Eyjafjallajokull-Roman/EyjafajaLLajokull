package homework2.servlet;

import homework2.dao.ProductDao;
import homework2.dao.imp.ProductDaoImp;
import homework2.dao.model.Product;
import homework2.service.ProductService;
import homework2.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Log4j
@WebServlet("/info")
public class ProductInfo extends HttpServlet {
    private final ProductService productService;

    public ProductInfo() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        log.info(id);
        log.info("i`m in update get");

        Product product = productService.read(id);



        request.setAttribute("productName", product.getName());
        request.setAttribute("price",product.getPrice());
        request.setAttribute("description",product.getDescription());
        request.setAttribute("id", id);

        request.getRequestDispatcher("/product.jsp").forward(request,response);


    }
}
