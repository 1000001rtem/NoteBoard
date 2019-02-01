package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @autor Artem Eremin on 23.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultDTO implements Serializable {

    private String message;
    private boolean success = false;

    public ResultDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResultDTO(boolean success) {
        this.success = success;
    }
}
