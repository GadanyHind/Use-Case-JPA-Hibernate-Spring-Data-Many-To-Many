package ma.enset.ensetjpa.service;

import ma.enset.ensetjpa.entities.Role;
import ma.enset.ensetjpa.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authentificate(String userName, String password);


}
