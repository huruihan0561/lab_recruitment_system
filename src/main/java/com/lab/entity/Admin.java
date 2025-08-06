package com.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("admin")
@Schema(description = "管理员实体类")
public class Admin {
    @TableId(type = IdType.AUTO)
    @Schema(description = "管理员ID")
    private Integer id;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "密码")
    private String password;
    
   @Schema(description = "电话号码")
    private String phone;

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
