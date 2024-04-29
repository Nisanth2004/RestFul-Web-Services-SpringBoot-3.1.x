package com.nisanth.restfulwebservices;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorld
{

    private MessageSource messageSource;

    public HelloWorld(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/nisan")
    public String helloworld()
    {
        return  "Nisanth";
    }

    @GetMapping(path = "/hello-worldbean")
    public  HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello world");
    }

    @RequestMapping(method = RequestMethod.GET,path = "/nisan-internationalization")
    public String helloworldInternationalization()
    {
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);


    }
}
