
package com.example.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lifeObject"
})
@Data
public class LifeObjectMain implements Serializable {

    @Valid
    @NotNull(message="lifeObject cannot be null")
    @JsonProperty("lifeObject")
    private LifeObject lifeObject;
    private final static Long serialVersionUID = -3399828753346018182L;

}
