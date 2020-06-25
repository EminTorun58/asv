package com.numero.uno.wedify.wedify.features.usertype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserType")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull(groups = MandatoryId.class)
    @Null(groups = Create.class)
    private Long id;

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;

    public interface MandatoryId {
    }

    public interface Create {
    }

}
