package com.slcp.devops.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.slcp.devops.entity.SysAttendance;
import com.slcp.devops.entity.SysRole;
import com.slcp.devops.entity.SysAdmin;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author Slcp
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -99322056007168973L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rid;
    private String nickname;
    private String username;
    private String password;
    private Boolean status;
    private String token;
    private String roleName;
    private String roleDesc;
    private LocalDateTime updateTime;
}
