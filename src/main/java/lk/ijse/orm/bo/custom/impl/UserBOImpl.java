package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.UserBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.UserDAO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.UserDTO;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

public class UserBOImpl implements UserBO {
     UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);




    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserId(),dto.getUsername(),dto.getPassword(),dto.getPossession()));
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User dto : users) {
            UserDTO userDTO = new UserDTO(dto.getUserId(),dto.getUsername(),dto.getPassword(),dto.getPossession());

            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserId(),dto.getUsername(),dto.getPassword(),dto.getPossession()));
    }

    @Override
    public boolean deleteUser(int userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(String.valueOf(userId));
    }

    @Override
    public UserDTO findUsername(String username) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(username);
        if (user != null) {
            return new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getPossession()
            );
        }
        return null;
    }

    @Override
    public UserDTO findPositionByUserName(String username) {
        // Retrieve user by username
        User user = userDAO.findByUsername(username);

        // If user exists, return UserDTO with user's details
        if (user != null) {
            return new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), user.getPossession());
        } else {
            // Optional: Add logging if user is not found for debugging purposes
            return null;
        }
    }


}
