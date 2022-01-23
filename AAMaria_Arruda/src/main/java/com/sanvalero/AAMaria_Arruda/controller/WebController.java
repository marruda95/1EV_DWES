package com.sanvalero.AAMaria_Arruda.controller;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import com.sanvalero.AAMaria_Arruda.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private final ProductService productService;
    public WebController(@NonNull ProductService productService) { this.productService = productService; }


    @GetMapping(value = "/")
    @PostMapping(value = "/")
    public String index(Model model){
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

    @RequestMapping("/checkout")
    public String checkout(Model model) { return "checkout"; }

    @ExceptionHandler
    public String handleException(HttpServletRequest request, Exception exception) {
        return "error";
    }
}

