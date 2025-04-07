package br.com.unilab.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Classe Controller de Main do FrontEnd do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
