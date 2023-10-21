package in.astro.service;

import in.astro.payloads.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO dto);
    UserDTO updateUser(UserDTO dto, int id);
    UserDTO getUserById(int user_id);
    List<UserDTO> getAllUsers();
    void deleteUser(int id);
}
