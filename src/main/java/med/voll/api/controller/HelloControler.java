package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //notação para definir uma classe de controller de uma api rest
@RequestMapping("/hello")//definindo o url que esse controler uirá responder
public class HelloControler {

    @GetMapping
    public String olaMundo() {
        return "Hello World spring!";
    }
}
