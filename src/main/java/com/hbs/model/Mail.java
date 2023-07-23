package com.hbs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Created By Soumojit Basak on 23 Jul, 2023
 */
@Getter
@Setter
@NoArgsConstructor
public class Mail {
    private String from;
    private String mailTo;
    private String subject;
    private Map<String, Object> props;
}
