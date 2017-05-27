package it.morfoza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.groups.ConvertGroup;

import java.util.List;

import static jdk.internal.dynalink.support.NameCodec.encode;

@Controller
public class SkarbonkaControler {

    private PiggyService piggyService;

    @Autowired
    public SkarbonkaControler(PiggyService piggyService) {
        this.piggyService = piggyService;
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/piggybanks")
    public String Piggybanks(
                         @RequestParam(value = "city", required = false) String city,
                         @RequestParam(value = "date", required = false) String date,
                         @RequestParam(value = "target", required = false) String targer, Model model) {

        if (isStringEmpty(city)) {
            String error = encode("Wpisz nazwę miasta!");
            return "redirect:/?error= " + error;
        }



        model.addAttribute("city", city);
        model.addAttribute("date", date);

        return "piggybanks";

    }

    @RequestMapping("/all")
    public String all(Model model) {
        List<PiggyBank> piggyBanks = piggyService.getAll();
        model.addAttribute("piggyBanks", piggyBanks);
        return "piggybanks";

    }



    private boolean isStringEmpty(String string) {
        return string == null || string.equals("");
    }



}
