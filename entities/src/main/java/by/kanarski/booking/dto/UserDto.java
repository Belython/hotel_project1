package by.kanarski.booking.dto;

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
    private Locale locale;
    private Currency currency;
    private String userStatus;

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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
        if (!locale.equals(userDto.locale)) return false;
        if (!currency.equals(userDto.currency)) return false;
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
        result = 31 * result + locale.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + userStatus.hashCode();
        return result;
    }
}
