package com.hbs.io;

import lombok.*;

/**
 * Created By Soumojit_Basak on 28-02-2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;

    private String remark;

    public AuthenticationResponse(String remark) {
        this.remark = remark;
    }

}
