package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //hello로 들어오면 이 함수를 호출한다.
    @GetMapping("hello")
    public String hello(Model model) {//MVC에서 M에 해당하는 model이다.
        model.addAttribute("data", "hello!!");
        return "hello"; //여기서 hello는 templates의 hello.html을 의미한다. String을 return하면 기본적으로 spring은 template의 파일을 찾는다.
    }

    @GetMapping("hello-mvc")
    //파라미터가 있기 hello-mvc를 요청하면 에러뜸. 파라미터가 없다고 뜬다. hello-mvc?name=spring! 이렇게 해야 올바르게 동작함.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //문자가 아닌 객체를 넘겼다. 이런 방식을 API방식이라고 보통 얘기한다. 자동으로 JSON으로 넘김.

    }

    //static class outer class의 생성 없이도 inner class 생성 가능.
    //hello-appi에서 사용한다.
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
