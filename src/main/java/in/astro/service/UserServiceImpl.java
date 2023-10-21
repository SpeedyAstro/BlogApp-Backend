package in.astro.service;

import in.astro.entity.User;
import in.astro.exceptions.ResourceNotFoundException;
import in.astro.payloads.UserDTO;
import in.astro.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = userRepository.save(dtoToEntity(dto));
        return entityToDto(user);
    }

    @Override
    public UserDTO updateUser(UserDTO dto, int id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        dto.setId(id);
        user.setName(dto.getName());
        user.setAbout(dto.getAbout());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User updatedUser = userRepository.save(user);
        return entityToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(int user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User","id",user_id));
        return entityToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteUser(int id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepository.delete(user);
    }

    // model mapper
    public User dtoToEntity(UserDTO dto){
//        User user = this.modelMapper.map(dto,User.class);
        User user = new User();
        BeanUtils.copyProperties(dto,user);
//        user.setId(dto.getId());
//        user.setName(dto.getName());
//        user.setAbout(dto.getAbout());
//        user.setEmail(dto.getEmail());
//        user.setPassword(dto.getPassword());
        return user;
    }
    public UserDTO entityToDto(User user){
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user,dto);
//        dto.setId(user.getId());
//        dto.setName(user.getName());
//        dto.setAbout(user.getAbout());
//        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
        return dto;
    }
}
