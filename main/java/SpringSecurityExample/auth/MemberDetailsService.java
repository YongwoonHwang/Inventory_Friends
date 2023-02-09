package SpringSecurityExample.auth;


//import SpringSecurityExample.model.User;
//import SpringSecurityExample.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MemberDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String userID) {
//        User user = userRepository.findByUserID(userID);
//        System.out.println("유저아이디 = " + userID);
//        System.out.println("유저정보 = " + user);
//        if(user != null){
//            return new MemberDetails(user);
//        }
//        return null;
//    }
//}

import SpringSecurityExample.model.User;
import SpringSecurityExample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userID) {
        User user = userRepository.findByUserID(userID);
        System.out.println("유저 아이디 = " + userID);
        System.out.println("유저 정보 = " + user);
        if(user != null){

            return new MemberDetails(user);
        }
        return null;
    }
}

