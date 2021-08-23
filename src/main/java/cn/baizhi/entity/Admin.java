package cn.baizhi.entity;


//管理员实体类

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data  //生成个get set  toString
@AllArgsConstructor  //生成有参构造
@NoArgsConstructor    //生成无参构造
public class Admin  implements Serializable {
    private String id;
    private String username;
    private String password;
    private Integer status;

}
