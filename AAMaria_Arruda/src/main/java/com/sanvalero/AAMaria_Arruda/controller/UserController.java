package com.sanvalero.AAMaria_Arruda.controller;

import com.sanvalero.AAMaria_Arruda.domain.User;
import com.sanvalero.AAMaria_Arruda.exception.UserRegistrationException;
import com.sanvalero.AAMaria_Arruda.service.FileService;
import com.sanvalero.AAMaria_Arruda.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/** CONTROLADOR PARA LA GESTIÓN DE USUARIOS **/
@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    // Logear con un usuario creado
    @GetMapping("/login")
    public String login(Model model) { return "login"; }

    // Registar un nuevo usuario
    @GetMapping("/registration")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // El usuario es redirigido a crear un nuevo usuario
    @PostMapping("/new-user")
    public String addUser(@ModelAttribute User user, Model model) throws UserRegistrationException {
        logger.info("inicio addUser");
        boolean userAdded = userService.add(user);
        if(!userAdded)
            throw new UserRegistrationException("Error al registrar el usuario, intente de nuevo.");

        logger.info("Usuario creado: " + user);
        model.addAttribute("user",user);
        logger.info("final addUser");
        return "new-user";
    }

    // tras subir una foto a tu usuario, recarga perfil

    @PostMapping("/user-image")
    public String setUserImage(@RequestParam("image")MultipartFile imageFile) {
        fileService.uploadFile(imageFile);
        return "redirect:/profile";
    }

    // ENLACE PARA ENTRAR AL PERFIL DEL USUARIO
    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        String remoteUsername = request.getRemoteUser();
        User remoteUser = userService.findByUsername(remoteUsername);
        model.addAttribute("user", remoteUser);
        return "profile";
    }

    // ERROR DE REGISTRO
    @ExceptionHandler(UserRegistrationException.class)
    public ModelAndView handleUserRegistrationException(HttpServletRequest request, UserRegistrationException exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "No se ha podido registrar el usuario. Por favor contacte con soporte técnico");
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler
    public ModelAndView handleException(HttpServletRequest request, Exception exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
