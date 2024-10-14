package service;

import model.Position;
import model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(int id);
}
