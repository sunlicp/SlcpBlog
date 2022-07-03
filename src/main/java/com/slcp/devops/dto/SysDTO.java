package com.slcp.devops.dto;

import com.slcp.devops.entity.SysAttendance;
import com.slcp.devops.entity.SysRole;
import com.slcp.devops.entity.SysAdmin;
import lombok.Data;

import java.io.Serializable;


/**
 * @author Slcp
 */
@Data
public class SysDTO implements Serializable {
    private SysAdmin sysAdmin;
    private SysAttendance sysAttendance;
    private SysRole sysRole;

}
