package com.github.responsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataResponse {
   
    @JsonProperty(value = "name")
    public String name;
    @JsonProperty(value = "full_name")
    public String full_name;
    @JsonProperty(value = "private")
    public String privatee;
    
    @JsonProperty(value = "default_branch")
    public String default_branch;
   
}
