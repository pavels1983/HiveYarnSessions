
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
    "timestamp",
    "eventtype",
    "eventinfo"
})
public class Event {

    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("eventtype")
    private String eventtype;
    @JsonProperty("eventinfo")
    private Eventinfo eventinfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("eventtype")
    public String getEventtype() {
        return eventtype;
    }

    @JsonProperty("eventtype")
    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    @JsonProperty("eventinfo")
    public Eventinfo getEventinfo() {
        return eventinfo;
    }

    @JsonProperty("eventinfo")
    public void setEventinfo(Eventinfo eventinfo) {
        this.eventinfo = eventinfo;
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
