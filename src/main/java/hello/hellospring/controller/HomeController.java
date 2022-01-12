package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //static파일에 index.html이 있지만 컨트롤러에 우선권이 있다고 강의 초반에 설명했었다.
    public String home() {
        return "home"; //resources/templates에서 파일을 찾는다.
    }
}
