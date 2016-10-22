package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by Дмитрий on 19.10.2016.
 */
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String role;
//    private Locale locale;
//    private Currency currency;
    private String userStatus;

    public UserDto() {

    }

//    public UserDto(long userId, String firstName, String lastName, String email, String login,
//                   String password, String role, Locale locale, Currency currency, String userStatus) {
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.login = login;
//        this.password = password;
//        this.role = role;
//        this.locale = locale;
//        this.currency = currency;
//        this.userStatus = userStatus;
//    }

    public UserDto(long userId, String firstName, String lastName, String email, String login, String password, String role, String userStatus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public UserDto(String firstName, String lastName, String email, String login, String password, String role, String userStatus) {
        this.userId = FieldValue.UNDEFINED_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (userId != userDto.userId) return false;
        if (!firstName.equals(userDto.firstName)) return false;
        if (!lastName.equals(userDto.lastName)) return false;
        if (!email.equals(userDto.email)) return false;
        if (!login.equals(userDto.login)) return false;
        if (!password.equals(userDto.password)) return false;
        if (!role.equals(userDto.role)) return false;
        return userStatus.equals(userDto.userStatus);

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + userStatus.hashCode();
        return result;
    }
}
