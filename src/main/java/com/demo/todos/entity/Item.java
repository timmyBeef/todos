package com.demo.todos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Item")
@Table(name = "item")
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "item_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "deadline",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime deadline;

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

    @Column(
            name = "order_num"
    )
    private Integer orderNum = 0;

    @ManyToOne
    @JoinColumn(
            name = "ilist_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "ilist_item_fk"
            )
    )
    private IList ilist;

    public Item(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

}
