package com.github.layfoundation.vo;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

@Data
public class Address implements Serializable {
    @NotBlank(message = "地址名称不能为空")
    private String name;

    private String longitude;

    private String latitude;

}
