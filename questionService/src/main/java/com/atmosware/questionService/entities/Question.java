package com.atmosware.questionService.entities;


import com.atmosware.questionService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
    private String userRole;
    private String description;
    private int optionCount;
    private String imageUrl;
    private Status status;

    @OneToMany(mappedBy = "question")
    private List<Option> options;
}
