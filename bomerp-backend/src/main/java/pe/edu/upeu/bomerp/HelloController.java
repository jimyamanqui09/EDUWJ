package pe.edu.upeu.bomerp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/v1/hello")
    public String holaMundo() {
        return "Hola BomERP";
    }
}