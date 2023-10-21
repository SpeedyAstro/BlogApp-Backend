package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.UserDTO;
import in.astro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    //    Create User
    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto) {
        UserDTO user = userService.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //    Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO dto, @PathVariable int userId) {
        UserDTO userDTO = userService.updateUser(dto, userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //    Delete User
    @DeleteMapping("/{userid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userid) {
        this.userService.deleteUser(userid);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
//  Fetch All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/{userid}")
    public ResponseEntity<?> getUser(@PathVariable int userid){
        return new ResponseEntity<>(userService.getUserById(userid),HttpStatus.OK);
    }
}
