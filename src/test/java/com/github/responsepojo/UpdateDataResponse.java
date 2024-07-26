package com.github.responsepojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDataResponse {
	@JsonProperty(value = "name")
    public String name;
    @JsonProperty(value = "description")
    public String description;
   
    @JsonProperty(value = "private")
    public String privatee;
}
