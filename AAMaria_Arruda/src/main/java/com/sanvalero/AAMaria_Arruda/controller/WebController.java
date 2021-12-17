package com.sanvalero.AAMaria_Arruda.controller;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import com.sanvalero.AAMaria_Arruda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.NonNull;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ProductService productService;
    public WebController(@NonNull ProductService productService) { this.productService = productService; }


    @GetMapping(value = "/")
    @PostMapping(value = "/")
    public String index(Model model){
        System.out.println("Test");
        try{
            List<Product> allProducts = productService.findAllProducts();
            model.addAttribute("products" , allProducts);
        }catch (Exception eox){
            System.out.println(eox.getLocalizedMessage());
            model.addAttribute("MESSAGE" , eox.getLocalizedMessage() );
        }
        return "index";
    }

    @GetMapping(value = "/category/{categoryName}")
    public String productsByCategory(Model model, @PathVariable String categoryName){
        System.out.println("Test");
        try{
            List<Product> categoryProducts = productService.findByCategory(categoryName);
            model.addAttribute("products", categoryProducts);
            model.addAttribute("categoryList", true);
            model.addAttribute("category", categoryName);
        }catch (Exception eox){
            System.out.println(eox.getLocalizedMessage());
            model.addAttribute("MESSAGE", eox.getLocalizedMessage());
        }
        return "index";
    }

    @GetMapping(value = "/product/{id}")
    public String product(Model model, @PathVariable long id) throws ProductNotFoundException {
        System.out.println("Test");
        try{
            Product product = productService.findProduct(id);
            model.addAttribute("product", product);
        }catch (Exception eox){
            System.out.println(eox.getLocalizedMessage());
            model.addAttribute("MESSAGE", eox.getLocalizedMessage());
        }
        return "product";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleException(HttpServletRequest request, ProductNotFoundException exception){
        return "product_error";
    }

    @ExceptionHandler
    public String handleException(HttpServletRequest request, Exception exception) {
        return "error";
    }
}

