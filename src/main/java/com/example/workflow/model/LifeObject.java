
package com.example.workflow.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "sbu"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)

public class LifeObject implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("totalIncome")
    private Long totalIncome;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

}
