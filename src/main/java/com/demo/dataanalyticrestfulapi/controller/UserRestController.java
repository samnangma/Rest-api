package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.Reposity.UserRepository;
import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.request.UserRequest;
    import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import com.demo.dataanalyticrestfulapi.service.UserService;
import com.demo.dataanalyticrestfulapi.utils.Response;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

//    private boolean isUserFounds(int id){
//        User user = userService.findUserById(id);
//        return user != null;
//    }

    private Response<User> userNotFound(int id){
        return Response.<User>notFound().setMessage("Cannot find user with id: "+id).setSuccess(false).setStatus(Response.Status.NOT_FOUND);
    }
    UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public Response<PageInfo<User>> getAllUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size
            , @RequestParam (defaultValue =  "", required = false) String username){
        try {
            PageInfo<User> response = userService.allUsers(page, size, username);
            return Response.<PageInfo<User>>ok().setPayload(response).setMessage("Successfully retrieved all users ! ");
        } catch (Exception ex){
            return Response.<PageInfo<User>>exception().setMessage("Failed to retrived the users! Exception occured ! ");
        }

    }
    @GetMapping("/{id}")
    public Response<User> findUserByID(@PathVariable int id){
        try {
            User response  = userService.findUserById(id);
            if ( response != null){
                return Response.<User>ok().setPayload(response).setSuccess(true).setMessage("Suceessfully retrived user with id = "+id);
            } else {
                return Response.<User>notFound().setMessage("User with id " + id + "doesn't exist").setSuccess(false);
            }
        } catch (Exception ex){
            return Response.<User>exception().setMessage("Failed to retrieved user wiht id = " + id);
        }


    }

    @PostMapping("/new-user")
    public Response<User> createUser(@Valid @RequestBody UserRequest request){
        try {
            int userID = userService.createNewUser(request);
            if(userID > 0 ){
                User response = new User().setUsername(request.getUsername()).setAddress(request.getAddress()).setGender(request.getGender()).
                setUserId(userID);

                return Response.<User>createdSuccess().setPayload(response).setMessage("Create User Successfully").setSuccess(true);

            } else {
                return Response.<User>BAD_REQUEST().setMessage("Failed to create User");
            }
        } catch (Exception exception){
            return Response.<User>exception().setMessage("Exception occurs! Failed to create a new user").setSuccess(false);
        }
//        return "Successfully";
    }

    @PostMapping("/user-accounts")
    public Response<List<UserAccount>> getAllUserAccounts(){
        try {
            List<UserAccount> data = userService.getAllUserAccounts();
                    return Response.<List<UserAccount>>ok().setPayload(data).setMessage("Successfully retrieved all users accounts");
        } catch (Exception ex){
            return Response.<List<UserAccount>>exception().setMessage("Exception occurs ! Failed to retrieved all users accounts!")
                    .setSuccess(false);

        }
    }

    @PutMapping("/{id}")
    public Response<User> updateUser(@PathVariable int id , @RequestBody UserRequest request){
        try {
            int affectedRow = userService.updateUser(request, id);
            if (affectedRow > 0) {
                User response = new User().setUserId(id).setUsername(request.getUsername())
                        .setAddress(request.getAddress()).setGender(request.getGender());

                return Response.<User>updateSuccess().setMessage("Successfully update the user ")
                        .setPayload(response).setSuccess(true);
            } else {
                return Response.<User>notFound().setMessage("Cannot update , user with id = " + id + " doesn't exist ! ").setSuccess(false);
            }
        } catch (Exception ex) {
            return Response.<User>exception().setMessage("Failed to update user , Exception Occurred!");
        }
    }

    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable int id){
        try {
            int affectedRow = userService.removeUSer(id);
            if (affectedRow > 0) {
                return Response.deleteSuccess().setMessage("Successfully remove the user!").setSuccess(true);
            } else {
                return Response.<Object>notFound().setMessage("User with id = "+id + "doesn't exist in our system ").setSuccess(false);
            }
        } catch (Exception ex) {
            return Response.<Object>exception().setMessage("Failed to update user , Exception Occurred!").setSuccess(false);
        }
    }
}



