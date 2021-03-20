package com.example.mongo_user.domain.services;

    import com.example.mongo_user.domain.entities.LoginInfo;
    import com.example.mongo_user.domain.repositories.LoginInfoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

@Service
public class LoginInfoService {

    @Autowired
    LoginInfoRepository loginInfoRepository;

//    LoginInfo findById(Integer id) {
//        return loginInfoRepository.findById_login(id);
//    }
}
