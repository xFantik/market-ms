package ru.pb.market;


import java.util.List;


public class UserDto {
    private Long id;

    private String username;

    private String email;

    private List<String> roles;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDto(Long id, String username, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }



    public UserDto(String name, String email, List<String> roles) {
        this.username = name;
        this.email = email;
        this.roles = roles;
    }
}
