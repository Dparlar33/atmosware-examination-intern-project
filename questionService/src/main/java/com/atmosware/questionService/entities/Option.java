package com.atmosware.questionService.entities;

import com.atmosware.questionService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "options")
public class Option extends BaseEntity {
    private String description;
    private boolean isCorrect;
    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;
}
