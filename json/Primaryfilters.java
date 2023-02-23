
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
    "dagName",
    "queueName",
    "callerId",
    "applicationId",
    "user",
    "status"
})
public class Primaryfilters {

    @JsonProperty("dagName")
    private List<String> dagName = null;
    @JsonProperty("queueName")
    private List<String> queueName = null;
    @JsonProperty("callerId")
    private List<String> callerId = null;
    @JsonProperty("applicationId")
    private List<String> applicationId = null;
    @JsonProperty("user")
    private List<String> user = null;
    @JsonProperty("status")
    private List<String> status = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dagName")
    public List<String> getDagName() {
        return dagName;
    }

    @JsonProperty("dagName")
    public void setDagName(List<String> dagName) {
        this.dagName = dagName;
    }

    @JsonProperty("queueName")
    public List<String> getQueueName() {
        return queueName;
    }

    @JsonProperty("queueName")
    public void setQueueName(List<String> queueName) {
        this.queueName = queueName;
    }

    @JsonProperty("callerId")
    public List<String> getCallerId() {
        return callerId;
    }

    @JsonProperty("callerId")
    public void setCallerId(List<String> callerId) {
        this.callerId = callerId;
    }

    @JsonProperty("applicationId")
    public List<String> getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(List<String> applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("user")
    public List<String> getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(List<String> user) {
        this.user = user;
    }

    @JsonProperty("status")
    public List<String> getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(List<String> status) {
        this.status = status;
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
