package com.github.layfoundation.vo;

import com.github.layfoundation.validate.CustomGroupSequenceProvider;
import com.github.layfoundation.validate.group.Girl;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@GroupSequenceProvider(CustomGroupSequenceProvider.class)
public class UserVo implements Serializable {
    @NotBlank(message = "名字不能为空")
    @Size(min = 2, max = 50, message = "名字长度的范围为2~50")
    private String name;

    @Email(message = "邮箱格式不对")
    private String email;

    @NotNull(message = "年龄不能为空")
    @Min(18)
    @Max(100)
    private Integer age;

    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotEmpty(message = "性别为女时照片不能为空", groups = {Girl.class})
    private List<String> photoList;
}
