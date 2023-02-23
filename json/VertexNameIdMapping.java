
package ru.rosbank.hdp.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Map 1",
    "Map 2",
    "Map 4",
    "Reducer 3",
    "Map 3",
    "Reducer 2"
})
public class VertexNameIdMapping {

    @JsonProperty("Map 1")
    private String map1;
    @JsonProperty("Map 2")
    private String map2;
    @JsonProperty("Map 4")
    private String map4;
    @JsonProperty("Reducer 3")
    private String reducer3;
    @JsonProperty("Map 3")
    private String map3;
    @JsonProperty("Reducer 2")
    private String reducer2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Map 1")
    public String getMap1() {
        return map1;
    }

    @JsonProperty("Map 1")
    public void setMap1(String map1) {
        this.map1 = map1;
    }

    @JsonProperty("Map 2")
    public String getMap2() {
        return map2;
    }

    @JsonProperty("Map 2")
    public void setMap2(String map2) {
        this.map2 = map2;
    }

    @JsonProperty("Map 4")
    public String getMap4() {
        return map4;
    }

    @JsonProperty("Map 4")
    public void setMap4(String map4) {
        this.map4 = map4;
    }

    @JsonProperty("Reducer 3")
    public String getReducer3() {
        return reducer3;
    }

    @JsonProperty("Reducer 3")
    public void setReducer3(String reducer3) {
        this.reducer3 = reducer3;
    }

    @JsonProperty("Map 3")
    public String getMap3() {
        return map3;
    }

    @JsonProperty("Map 3")
    public void setMap3(String map3) {
        this.map3 = map3;
    }

    @JsonProperty("Reducer 2")
    public String getReducer2() {
        return reducer2;
    }

    @JsonProperty("Reducer 2")
    public void setReducer2(String reducer2) {
        this.reducer2 = reducer2;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
