package SpringSecurityExample.controller;


import SpringSecurityExample.auth.MemberDetails;
import SpringSecurityExample.auth.MemberDetailsService;
import SpringSecurityExample.model.User;
import SpringSecurityExample.repository.UserRepository;
import SpringSecurityExample.session.ApiSessionExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipal;

@Controller

public class HelloController {



    @Autowired
    MemberDetailsService memberDetailsService;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(
            /*SessionAttribute */
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) User loginMember,Model model,Authentication authentication) {
        /*세션에 회원 정보가 없으면 main페이지 반환*/
        if (loginMember == null) {

            return "index";
        }
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        model.addAttribute("info",memberDetails.getUserName() + "님 로그인 중입니다.");
        /*세션에 사용자 정보가 있다면 마찬가지로 main페이지 반환*/
        model.addAttribute("member", loginMember);
        
        return "index";


//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "index";
//        }
//        // Member 로 타입 캐스팅
//        User loginMember = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        System.out.println("홈 화면에서의 loginMember정보 = " + loginMember);
//
//        // 세션에 회원 데이터가 없으면 home
//        if (loginMember == null) {
//            return "index";
//        }
//
//        // 세션이 유지되면 로그인으로 이동
//        model.addAttribute("member", loginMember);
//        return "successlogin";

    }

    @GetMapping("/expired")
    public String expiredPage() {
        throw new ApiSessionExpiredException();
    }

    @GetMapping("/check-session")
    @ResponseBody
    public ResponseEntity<String> checkSession(@AuthenticationPrincipal UserPrincipal loginAccount) {
        if(loginAccount ==null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }




    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        @RequestParam(value = "error",required = false) String error,
                        @RequestParam(value = "exception",required = false) String exception,
                        Model model,HttpServletRequest request,Authentication authentication) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);


        return "login";
    }
    @GetMapping("/successlogin")
    public String successlogin(@ModelAttribute User user, Model model, Authentication authentication, HttpServletResponse response, HttpServletRequest request) {
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        model.addAttribute("info",memberDetails.getUserName() + "님");
        User loginMember =  userRepository.findByUserID(memberDetails.getUsername()); /* 유저 정보를 저장할 변수 설정*/
        System.out.println("form.getUserID의 정보 = " + memberDetails.getUsername());
        System.out.println("successlogin 로그인 유저정보 = " + loginMember);
        HttpSession session = request.getSession();         /*세션 변수 선언*/
        session.setAttribute("loginMember",loginMember);  /*선언한 세션 변수에 저장한 유저정보를 담는다*/

        return "successlogin";
    }

    @PostMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        /*세션이 없더라도 새로 생성하면 안되므로 세션의 생성을 막아놈*/
        HttpSession session = request.getSession(false);
        /*세션값이 있다면 세션무효화*/
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }



    @PostMapping("/joinProc")
    public String joinProc(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/";
    }
    /**
     * 세션 만료 캐치
     * @param e
     * @return
     */
    @ExceptionHandler(ApiSessionExpiredException.class)
    public ModelAndView handleApiSessionExpiredException(ApiSessionExpiredException e) {
        return new ModelAndView("expired");
    }
}
