package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name",name);   // "name"은 키값, name은 파라미터에서 넘어 온 값
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody       //http의 body 부분에 이 데이터를 직접 넣겠다 !
    public String helloString(@RequestParam("name") String name){
        return "<h1>hello</h1> " + name;  // view resolver를 거치지 않고 바로 꼬우...
    }

    @GetMapping("hello-api")
    @ResponseBody   //http의 body 부분에 이 데이터를 직접 넣겠다 !
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;       // 근데 객체를 리턴한다 >>  HttpMessageConverter가 작동해서 json으로 변환해서 웹브라우저에 전달! {name:value값}
    }

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
