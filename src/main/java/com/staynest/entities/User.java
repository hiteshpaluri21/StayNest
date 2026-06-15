package com.staynest.entities;

import com.staynest.enums.Role;
import com.staynest.enums.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false)
    private Role role;

    @Column(name = "Email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "PropertyID")
    private Integer propertyId;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    // ── Constructors ──────────────────────────────────────────────────────────

    public User() {}

    public User(String name, Role role, String email, String phone,
                Integer propertyId, String password, UserStatus status) {
        this.name       = name;
        this.role       = role;
        this.email      = email;
        this.phone      = phone;
        this.propertyId = propertyId;
        this.password   = password;
        this.status     = status;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Integer getUserId()                       { return userId; }
    public void    setUserId(Integer userId)         { this.userId = userId; }

    public String  getName()                         { return name; }
    public void    setName(String name)              { this.name = name; }

    public Role    getRole()                         { return role; }
    public void    setRole(Role role)                { this.role = role; }

    public String  getEmail()                        { return email; }
    public void    setEmail(String email)            { this.email = email; }

    public String  getPhone()                        { return phone; }
    public void    setPhone(String phone)            { this.phone = phone; }

    public Integer getPropertyId()                   { return propertyId; }
    public void    setPropertyId(Integer propertyId) { this.propertyId = propertyId; }

    public String  getPassword()                     { return password; }
    public void    setPassword(String password)      { this.password = password; }

    public UserStatus getStatus()                    { return status; }
    public void       setStatus(UserStatus status)   { this.status = status; }

    @Override
    public String toString() {
        return "User{userId=" + userId + ", name='" + name + "', role=" + role
                + ", email='" + email + "', status=" + status + "}";
    }
}
