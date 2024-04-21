package ma.enset.ensetjpa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.ensetjpa.entities.Role;
import ma.enset.ensetjpa.entities.User;
import ma.enset.ensetjpa.repositories.RoleRepository;
import ma.enset.ensetjpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUserName(userName);
        if (user != null) {
            Role role = findRoleByRoleName(roleName);
            if (role != null) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            } else {
                System.out.println("TARRA");
            }
        } else {
            System.out.println("TARRA");

        }
    }

    @Override
    public User authentificate(String userName, String password) {
       User user=userRepository.findByUserName(userName);
       if(user==null) throw new RuntimeException("Bad credentials !!");
       if (user.getPassword().equals(password)){
           return user;
       }
        throw new RuntimeException("Bad credentials !!");
    }

    //userRepository.save(user);

    }

