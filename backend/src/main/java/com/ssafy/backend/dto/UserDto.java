package com.ssafy.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class UserDto implements UserDetails {
    @ApiModelProperty(value = "유저 아이디(PK)")
    private Long userId; // 회원 고유 아이디
    @ApiModelProperty(value = "이메일")
    private String userEmail;
    @ApiModelProperty(value = "비밀번호")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;
    @ApiModelProperty(value = "닉네임")
    private String userNickname;
    @ApiModelProperty(value = "직업")
    private String userJob;
    @ApiModelProperty(value = "성별 (FEMALE, MALE)")
    private String userGender;
    @ApiModelProperty(value = "유저 권한 (ROLE_ADMIN, ROLE_USER)")
    private String userRole;
    @ApiModelProperty(value = "프로필 이미지 파일(등록 & 삭제용)")
    private MultipartFile userProfileImgFile;
    @ApiModelProperty(value = "프로필 이미지 url")
    private String userProfileImg;
    @ApiModelProperty(value = "생일")
    private String userBirth;


    // 이하 코드는 security 를 위한 용도
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<? extends GrantedAuthority> authorities;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
