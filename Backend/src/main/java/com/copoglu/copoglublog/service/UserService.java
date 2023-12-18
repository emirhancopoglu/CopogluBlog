package com.copoglu.copoglublog.service;

import com.copoglu.copoglublog.configuration.JwtTokenProvider;
import com.copoglu.copoglublog.entities.User;
import com.copoglu.copoglublog.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public List<User> GetAllUsers() {
        return userJpaRepository.findAll();
    }

    public User AddUser(User user) {
        return userJpaRepository.save(user);
    }

    public Optional <User> findbyId(Long id) {
        return userJpaRepository.findById(id);

    }


    public User updateById(Long id, User updatedUser) {
        User existUser = userJpaRepository.findById(id).orElse(null);

        if(existUser !=null){
            existUser.setUsername(updatedUser.getUsername());
            existUser.setPassword(updatedUser.getPassword());
            return userJpaRepository.save(existUser);

        }
        else {
            //Güncellenecek kullanıcı bulunamadı demek burada boş yerine bir hata mesajı döndüreceksin.
            return null;
        }


    }


    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = userJpaRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            String token = jwtTokenProvider.generateToken(user.getUsername(), user.getId());
            user.setToken(token);
            updateToken(user);

            //Dto -> araştır.
            return user; // Kullanıcı bulundu, bilgileri döndür
        } else {
            throw new UsernameNotFoundException("Kullanıcı adı veya şifre yanlış"); // Kullanıcı bulunamadı
        }

    }

    public void updateToken(User user) {
        userJpaRepository.save(user);
    }

    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

}
