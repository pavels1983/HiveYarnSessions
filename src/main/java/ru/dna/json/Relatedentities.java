
package ru.dna.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "TEZ_DAG_EXTRA_INFO",
    "TEZ_VERTEX_ID"
})
public class Relatedentities {

    @JsonProperty("TEZ_DAG_EXTRA_INFO")
    private List<String> tEZDAGEXTRAINFO = null;
    @JsonProperty("TEZ_VERTEX_ID")
    private List<String> tEZVERTEXID = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TEZ_DAG_EXTRA_INFO")
    public List<String> getTEZDAGEXTRAINFO() {
        return tEZDAGEXTRAINFO;
    }

    @JsonProperty("TEZ_DAG_EXTRA_INFO")
    public void setTEZDAGEXTRAINFO(List<String> tEZDAGEXTRAINFO) {
        this.tEZDAGEXTRAINFO = tEZDAGEXTRAINFO;
    }

    @JsonProperty("TEZ_VERTEX_ID")
    public List<String> getTEZVERTEXID() {
        return tEZVERTEXID;
    }

    @JsonProperty("TEZ_VERTEX_ID")
    public void setTEZVERTEXID(List<String> tEZVERTEXID) {
        this.tEZVERTEXID = tEZVERTEXID;
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
