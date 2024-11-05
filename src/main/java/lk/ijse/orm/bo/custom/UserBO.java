package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.dto.UserDTO;
import lk.ijse.orm.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBo {
    UserDTO findUserByUsernameAndPassword(String username, String password);
    boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(int userId) throws SQLException, ClassNotFoundException;
    UserDTO findUsername(String username) throws SQLException, ClassNotFoundException;
}
