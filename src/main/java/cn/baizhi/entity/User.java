package cn.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User implements Serializable {
    @Excel(name = "用户id")
    private String id;
    @Excel(name = "用户名")
    private  String username;
    @Excel(name = "用户号码")
    private String phone;
    @Excel(name = "用户头像",type = 2)
    private String headimg;
    @Excel(name = "用户描述")
    private String brief;
    @Excel(name = "微信")
    private String wechat;
    @Excel(name = "日期",format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatdate;
    @Excel(name = "状态")
    private Integer status;

}
