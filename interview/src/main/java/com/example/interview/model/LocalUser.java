
package com.example.interview.model;

import lombok.Data;

@Data
public class LocalUser {
    private String id;
    private String userName;
    private String[] roles;
}
