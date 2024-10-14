package service.impl;

import dao.RoleDAO;
import dao.impl.RoleDAOImpl;
import model.Role;
import service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

}
