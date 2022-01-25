package com.sanvalero.AAMaria_Arruda.controller;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import com.sanvalero.AAMaria_Arruda.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/** CONTROLADOR PARA GESTIÓN DE PRODUCTOS **/

@Controller
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value ="/product/{id}")
    public String product(Model model, @PathVariable long id) throws ProductNotFoundException {
        try{
            Product product = productService.findProduct(id);
            model.addAttribute("product", product);
        }catch (Exception eox){
            System.out.println(eox.getLocalizedMessage());
            model.addAttribute("MESSAGE", eox.getLocalizedMessage());
        }
        return "product";
    }

    @GetMapping("/index")
    public String productSearch(Model model){
        model.addAttribute("product", new Product());
        return "index";
    }

    @PostMapping("/productSearch={$name}")
    public String productSearch(Product product, Model model, String name){
        List<Product> foundProducts = productService.findByName(name);
        model.addAttribute("foundProduct", foundProducts);
        return "productSearch";
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public String handleException(HttpServletRequest request, ProductNotFoundException exception){
        return "product_error";
    }

    @ExceptionHandler
    public ModelAndView handleExepction(HttpServletRequest request, Exception exception){
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");

        return mav;
    }
}
