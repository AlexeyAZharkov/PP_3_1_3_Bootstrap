package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserDetailsService {
   private UserDao userDaoImp = new UserDaoImp();

   public UserServiceImp(UserDao userDaoImp) {
      this.userDaoImp = userDaoImp;
   }

   @Transactional
   public void addUser(User user) {
      userDaoImp.addUser(user);
   }

   @Transactional
   public void updateUser(Long id, User updatedUser) {
      userDaoImp.updateUser(id, updatedUser);
   }

   @Transactional
   public void deleteUser(Long id) {
      userDaoImp.deleteUser(id);
   }

   @Transactional(readOnly = true)
   public User getUserById(Long id) {
      return userDaoImp.getUserById(id);
   }

   @Transactional(readOnly = true)
   public User getUserByName(String name) {
      return userDaoImp.getUserByName(name);
   }

   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userDaoImp.listUsers();
   }

   @Override
   @Transactional(readOnly = true)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user = Optional.ofNullable(userDaoImp.getUserByName(username));
//      User user = userRepository.findByUsername(username);
      if (user.isEmpty()) {
         throw new UsernameNotFoundException("User not found");
      }
      return user.get();

//      System.out.println("username  -- " + username);

//      if (username == "user1") {
//         return new User("user1", "Zh", "pass1");
////         return new User("user1", "Zh", "pass1");
//      }


//      User user = userDaoImp.getUserByName(username);
//      if(user == null) {
//         throw new UsernameNotFoundException("User not found!");
//      }
//      return user;

   }
}
