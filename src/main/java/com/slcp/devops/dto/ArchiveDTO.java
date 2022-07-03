package com.slcp.devops.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:31
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class ArchiveDTO implements Serializable {
    private static final long serialVersionUID = -3109782578272943999L;

    private Date createTime;
    private List<FirstPageDTO> data;
}
