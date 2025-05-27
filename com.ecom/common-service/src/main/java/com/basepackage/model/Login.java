package com.basepackage.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false) 
    private String username; 

    @Column(nullable = false)
    private String email; 

    
    @Column(nullable = false)
    private String password; 

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  
    
}
