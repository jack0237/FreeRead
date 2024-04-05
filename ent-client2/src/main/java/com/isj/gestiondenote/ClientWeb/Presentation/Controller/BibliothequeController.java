package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.entities.Enseignant;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.RequestInterceptor;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
@Slf4j

@Controller
public class BibliothequeController {

        @GetMapping("/listLivre")
        public String listeCategorie(Model model, HttpSession session) {
            ModalWithHttpHeader.model(model, session);
            Modal.model(model);
            String accessToken = (String) session.getAttribute("accessToken");
            model.addAttribute("accessToken", accessToken);
            RestTemplate restTemplate = new RestTemplate();

            //we're getting all categories from the library service
            Object[] Subject = restTemplate.getForObject(URL.BASE_URL_BIB + "/Subject", Object[].class);
            model.addAttribute("Subject", Subject);


            return "layout/gestionbiblio/BiblioPages/meslivres";

        }

//    @GetMapping("/listLivre")
//    public String pagelisteEtudiant(@RequestParam(name = "code") String code, Model model){
//        RestTemplate restTemplate = new RestTemplate();
//        Object Subject = restTemplate.getForObject(URL.BASE_URL_BIB + "/Subject", Object[].class );
//        return "layout/gestionbiblio/BiblioPages/meslivres";
//    }
}
