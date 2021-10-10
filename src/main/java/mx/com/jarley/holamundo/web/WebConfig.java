package mx.com.jarley.holamundo.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean // este metodo es para selecionar el idioma por default
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
        // un interceptor para poder cambiar de idiomas
    @Bean
    public LocaleChangeInterceptor localeChange(){
        
        var lci= new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    // registramos el interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registro){
    registro.addInterceptor(localeChange());
    }
    
    // ahora que agregamos seguridad a la aplicacion no permite incresar al index asi nomas asi q tenemos que hacer 
    // el mapping 
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){

            registro.addViewController("/").setViewName("index");
            
            // para mostrar la pagina de inicio ya deberia averse echo loggin asi q tenemos q agregar una pagina d elogin 
            registro.addViewController("/login");
            registro.addViewController("/Errores/403").setViewName("/Errores/403");
    }
    
    
    
}
