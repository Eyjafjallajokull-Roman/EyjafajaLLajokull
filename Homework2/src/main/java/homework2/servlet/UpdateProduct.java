package homework2.servlet;


import homework2.dao.model.Product;
import homework2.service.ProductService;
import homework2.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j
@WebServlet("/update")
public class UpdateProduct extends HttpServlet {

    private ProductService productService;


    public UpdateProduct() {
        this.productService = new ProductServiceImpl();
    }


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        log.info(id);
        log.info("i`m in update get");

        Product product = productService.read(id);



        req.setAttribute("productName", product.getName());
        req.setAttribute("price",product.getPrice());
        req.setAttribute("description",product.getDescription());
        req.setAttribute("id", id);

        req.getRequestDispatcher("/updateProduct.jsp").forward(req,res);



    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res){

log.info("Danulyak1");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));




        Product product = new Product(id,name,description,price);


        log.info("Danulyak2");
        productService.update(product);
//        doGet(req, res);
        res.sendRedirect("cabinet.jsp");



    }
}
