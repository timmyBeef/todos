package com.demo.todos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "User")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_name_unique", columnNames = "user_name")
        }
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @Column(
            name = "create_time",
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(
            name = "update_time",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private java.util.List<IList> lists = new ArrayList<>();

    public User(String userName, String encodedPassword) {
        this.userName = userName;
        this.encodedPassword = encodedPassword;
    }

    public void addList(IList list) {
        if (!this.lists.contains(list)) {
            this.lists.add(list);
            list.setUser(this);
        }
    }

    public void removeList(IList list) {
        if (this.lists.contains(list)) {
            this.lists.remove(list);
            list.setUser(null);
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lists=" + lists +
                '}';
    }
}
