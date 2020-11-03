package homework2.servlet;

import com.google.gson.Gson;
import homework2.dao.model.Product;
import homework2.exception.AlreadyExistException;
import homework2.service.ProductService;
import homework2.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@Log4j
@WebServlet("/product")
public class ProductServlet extends HttpServlet {


    private final ProductService productService;


    public ProductServlet() {
        this.productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response){
        List<Product> products = productService.readAll();
        log.info("get all products");


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter())
        {
            log.info("good5");
            writer.write(new Gson().toJson(products));
        }
        log.info("ALL GOOD");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try {
        double price = Double.parseDouble(req.getParameter("price"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Product product = new Product(name,price,description);

        productService.create(product);

        log.info("you successfully add product");

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = res.getWriter())
        {
            log.info("good2");
            writer.write(new Gson().toJson(product));
        }

        }catch (SQLException | AlreadyExistException e){

            log.error(e.getMessage(), e);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = res.getWriter())
            {
                log.info("good3");
                writer.write("{\"message\": \"" + e.getMessage() + "\"");
            }
        }


    }




}
