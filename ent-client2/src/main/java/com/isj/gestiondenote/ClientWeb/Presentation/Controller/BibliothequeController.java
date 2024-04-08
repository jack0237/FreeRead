package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.modelIntervention.Categorie;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
@Slf4j

@Controller
public class BibliothequeController {

    @GetMapping("/gestionbibliotheque")
    public String pageProfileBiblio(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/gestBiblio";
    }
    @GetMapping("/Ouvrages")
    public String pageOuvragBiblio(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/Ouvrages";
    }

    @GetMapping("/Acat")
    public String pageAcat(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/Acategorie";
    }

    @GetMapping("/Categorie")
    public String pageCategorieBiblio(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();

        //we're getting all categories from the library service
        Object[] Subject = restTemplate.getForObject(URL.BASE_URL_BIB + "/Subject", Object[].class);
        model.addAttribute("Subject", Subject);


        return "layout/gestionbiblio/Categorie";

    }
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

    @PostMapping("/ajoutCategorie")
    public String ajoutCategorie(Model model, @ModelAttribute Categorie object, HttpSession session) {
        Modal.model(model);
        RestTemplate restTemplate = new RestTemplate();

        Object[] Subject = restTemplate.postForObject(URL.BASE_URL_BIB, object, Object[].class);
        System.out.print(Subject);
        model.addAttribute("Subject", Subject);
        return "redirect:/layout/gestionbiblio/Categorie";
    }

}
