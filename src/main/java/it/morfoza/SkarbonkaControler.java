package it.morfoza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

@Controller
public class SkarbonkaControler {

    private PiggyService piggyService;

    @Autowired
    public SkarbonkaControler(PiggyService piggyService) {
        this.piggyService = piggyService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/all";
    }

    @RequestMapping("/registration_form")
    public String registration_form(){
        return "registration_form";
    }


    @RequestMapping("/all")
    public String all(Model model) {
        List<PiggyBank> piggyBanks = piggyService.getAll();
        model.addAttribute("piggyBanks", piggyBanks);
        return "piggybanks";
    }

    @RequestMapping("/log_in_form")
    public String log_in_form(){return "log_in_form";}

    @RequestMapping("/add_piggybank_form")
    public String addForm() {
        return "add_piggybank_form";
    }

    @RequestMapping("/edit_piggybank_form")
    public String editForm(@RequestParam(value = "id", required = true) long id, Model model) {
        PiggyBank piggyBank = piggyService.getById(id);
        model.addAttribute("piggyBank", piggyBank);
        return "edit_piggybank_form";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        List<PiggyBank> piggyBanks = piggyService.getAll();
        model.addAttribute("piggyBanks", piggyBanks);
        return "admin";
    }

    @RequestMapping("/add_piggybank")
    public String addPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "target", required = true) long target,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "long_description",required = true)String long_description,
            @RequestParam(value = "url_image",required = false)String url_image
                                                                 ) {


        if (isStringEmpty(name)) {
            String url = "Wpisz nazwę!";
            String error = encodeUrl(url);
            return "redirect:/?error= " + error;
        }
        PiggyBank pig = new PiggyBank(name,  LocalDateTime.now().toString(), new Money(target), new Money(0),description,long_description,url_image);

        piggyService.add(pig);

        return "redirect:/all";
    }


    @RequestMapping("/edit_piggybank")
    public String edit(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "target", required = true) long target,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "long_description",required = true)String long_description,
            @RequestParam(value = "url_image",required = false)String url_image
    ) {

        if (isStringEmpty(name)) {
            String error = encodeUrl("Wpisz nazwę!");
            return "redirect:/?error= " + error;
        }
        PiggyBank piggyBank = piggyService.getById(id);
        piggyBank.setName(name);
        piggyBank.setDescription(description);
        piggyBank.setLong_description(long_description);
        piggyBank.setTarget(new Money(target));
        piggyBank.setUrl_image(url_image);

        piggyService.update(piggyBank);

        return "redirect:/admin";
    }

    @RequestMapping("/piggybank")
    public String piggyBank(@RequestParam(value = "id", required = true) long id, Model model) {
        PiggyBank piggyBank = piggyService.getById(id);
        model.addAttribute("piggyBank", piggyBank);
        return "piggybank";

    }


    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id", required = true) long id, Model model) {
        piggyService.delete(id);
        return "redirect:admin";
    }

    @RequestMapping("/payin")
    public String all(@RequestParam(value = "id", required = true) long id, @RequestParam(value = "amount", required = true)  long amount) {
        piggyService.pay(id, new Money(amount), "");
        return "redirect:/all";
    }

    @RequestMapping("/payments")
    public String payments(@RequestParam(value = "id", required = true) long id, Model model) {
        model.addAttribute("payments", piggyService.getPaymentsForPiggyBank(id));
        model.addAttribute("piggyBank", piggyService.getById(id));
        return "payments";
    }


    @RequestMapping("/confirm_payment")
    public void confirmPayment(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println(req.getParameterMap());

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println(name + "=" + req.getParameter(name));
        }

        String controlParam = req.getParameter("control");
        String amountParam = req.getParameter("operation_amount");
        String status = req.getParameter("operation_status");

        if ("completed".equals(status)) {
            long piggyBankId = Long.parseLong(controlParam);
            piggyService.pay(piggyBankId, new Money(new BigDecimal(amountParam)), req.getParameter("email"));
        }

        try {
            resp.getWriter().print("OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isStringEmpty(String string) {
        return string == null || string.equals("");
    }


    private static String encodeUrl(String url)  {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}

