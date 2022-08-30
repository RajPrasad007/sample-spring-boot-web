package com.example.WebApp.user;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class UserController {

    private static String UPLOADED_FOLDER = "/home/frank/WebApp/src/main/resources/media/";

    @GetMapping("")
    public String homePage(){
        return "homepage";
    }

    @GetMapping("/register")
    public String signUpForm(){
        return "signup_form";
    }




    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String UploadFormGet(){
        return "upload";
    }

    @PostMapping("/upload")
    public String Upload(@RequestParam("file") MultipartFile file, Model model){
        if (file.isEmpty()){
            model.addAttribute("message", "Please specify a file!");
            return null;
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String mimetype = new MimetypesFileTypeMap().getContentType(fileName);
        String type = mimetype.split("/")[0];
        if (type.equals("image")){
            try {
                Path path = Paths.get(UPLOADED_FOLDER+fileName);
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e){
                e.printStackTrace();
            }
            model.addAttribute("message", "You successfully uploaded " + fileName + "!");
        }else {
            model.addAttribute("message", "Only image files are accepted!");
        }
        return null;
    }

    @GetMapping("/users")
    public String listUsersPage(){
        return "list_users";
    }



    @GetMapping("/blogs")
    public String blogPage(){
        return "blog";
    }
    
}
