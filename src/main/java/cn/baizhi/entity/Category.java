package cn.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category implements Serializable {
    private String id;
    private String cateName;
    private Integer levels;
    private String parentId;
}
