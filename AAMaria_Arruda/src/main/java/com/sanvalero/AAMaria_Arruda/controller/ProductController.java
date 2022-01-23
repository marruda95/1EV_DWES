package com.sanvalero.AAMaria_Arruda.controller;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import com.sanvalero.AAMaria_Arruda.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/** CONTROLADOR PARA GESTIÓN DE PRODUCTOS **/

@Controller
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value ="/product/{id}")
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
