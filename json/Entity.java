
package ru.rosbank.hdp.json;

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
    "entitytype",
    "entity",
    "starttime",
    "events",
    "domain",
    "relatedentities",
    "primaryfilters",
    "otherinfo"
})
public class Entity {

    @JsonProperty("entitytype")
    private String entitytype;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("starttime")
    private Long starttime;
    @JsonProperty("events")
    private List<Event> events = null;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("relatedentities")
    private Relatedentities relatedentities;
    @JsonProperty("primaryfilters")
    private Primaryfilters primaryfilters;
    @JsonProperty("otherinfo")
    private Otherinfo otherinfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("entitytype")
    public String getEntitytype() {
        return entitytype;
    }

    @JsonProperty("entitytype")
    public void setEntitytype(String entitytype) {
        this.entitytype = entitytype;
    }

    @JsonProperty("entity")
    public String getEntity() {
        return entity;
    }

    @JsonProperty("entity")
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @JsonProperty("starttime")
    public Long getStarttime() {
        return starttime;
    }

    @JsonProperty("starttime")
    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("relatedentities")
    public Relatedentities getRelatedentities() {
        return relatedentities;
    }

    @JsonProperty("relatedentities")
    public void setRelatedentities(Relatedentities relatedentities) {
        this.relatedentities = relatedentities;
    }

    @JsonProperty("primaryfilters")
    public Primaryfilters getPrimaryfilters() {
        return primaryfilters;
    }

    @JsonProperty("primaryfilters")
    public void setPrimaryfilters(Primaryfilters primaryfilters) {
        this.primaryfilters = primaryfilters;
    }

    @JsonProperty("otherinfo")
    public Otherinfo getOtherinfo() {
        return otherinfo;
    }

    @JsonProperty("otherinfo")
    public void setOtherinfo(Otherinfo otherinfo) {
        this.otherinfo = otherinfo;
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
