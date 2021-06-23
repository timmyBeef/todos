package com.demo.todos.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "IList")
@Table(name = "ilist")
public class IList {
    @Id
    @SequenceGenerator(
            name = "ilist_sequence",
            sequenceName = "ilist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ilist_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "name",
//            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "due_date",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime dueDate;

    @Column(
            name = "create_time",
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE",
            nullable = false
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
            mappedBy = "ilist",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private java.util.List<Item> items = new LinkedList<>();

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_list_fk"
            )
    )
    private User user;

    public IList(String name, LocalDateTime dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public void addItem(Item item) {
        if (!this.items.contains(item)) {
            this.items.add(item);
            item.setIlist(this);
        }
    }

    public void removeItem(Item item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
            item.setIlist(null);
        }
    }

    @Override
    public String toString() {
        return "IList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", items=" + items +
                '}';
    }
}
