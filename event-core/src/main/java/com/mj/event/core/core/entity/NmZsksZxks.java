package com.mj.event.core.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("nm_zxks_zxks")
public class NmZsksZxks {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String title;
    private String name;
    private String context;
    private Date date;
    private Date heartDate;
}
