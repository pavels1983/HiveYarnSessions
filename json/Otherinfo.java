
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
    "callerContext",
    "amWebServiceVersion",
    "inProgressLogsURL_1",
    "vertexNameIdMapping",
    "queueName",
    "applicationAttemptId",
    "callerType",
    "startTime",
    "callerId",
    "applicationId",
    "initTime",
    "user",
    "status",
    "completionApplicationAttemptId",
    "numFailedTaskAttempts",
    "numKilledTaskAttempts",
    "numCompletedTasks",
    "numSucceededTasks",
    "numFailedTasks",
    "timeTaken",
    "diagnostics",
    "endTime",
    "numKilledTasks"
})
public class Otherinfo {

    @JsonProperty("callerContext")
    private String callerContext;
    @JsonProperty("amWebServiceVersion")
    private String amWebServiceVersion;
    @JsonProperty("inProgressLogsURL_1")
    private String inProgressLogsURL1;
    @JsonProperty("vertexNameIdMapping")
    private VertexNameIdMapping vertexNameIdMapping;
    @JsonProperty("queueName")
    private String queueName;
    @JsonProperty("applicationAttemptId")
    private String applicationAttemptId;
    @JsonProperty("callerType")
    private String callerType;
    @JsonProperty("startTime")
    private Long startTime;
    @JsonProperty("callerId")
    private String callerId;
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("initTime")
    private Long initTime;
    @JsonProperty("user")
    private String user;
    @JsonProperty("status")
    private String status;
    @JsonProperty("completionApplicationAttemptId")
    private String completionApplicationAttemptId;
    @JsonProperty("numFailedTaskAttempts")
    private Integer numFailedTaskAttempts;
    @JsonProperty("numKilledTaskAttempts")
    private Integer numKilledTaskAttempts;
    @JsonProperty("numCompletedTasks")
    private Integer numCompletedTasks;
    @JsonProperty("numSucceededTasks")
    private Integer numSucceededTasks;
    @JsonProperty("numFailedTasks")
    private Integer numFailedTasks;
    @JsonProperty("timeTaken")
    private Long timeTaken;
    @JsonProperty("diagnostics")
    private String diagnostics;
    @JsonProperty("endTime")
    private Long endTime;
    @JsonProperty("numKilledTasks")
    private Integer numKilledTasks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("callerContext")
    public String getCallerContext() {
        return callerContext;
    }

    @JsonProperty("callerContext")
    public void setCallerContext(String callerContext) {
        this.callerContext = callerContext;
    }

    @JsonProperty("amWebServiceVersion")
    public String getAmWebServiceVersion() {
        return amWebServiceVersion;
    }

    @JsonProperty("amWebServiceVersion")
    public void setAmWebServiceVersion(String amWebServiceVersion) {
        this.amWebServiceVersion = amWebServiceVersion;
    }

    @JsonProperty("inProgressLogsURL_1")
    public String getInProgressLogsURL1() {
        return inProgressLogsURL1;
    }

    @JsonProperty("inProgressLogsURL_1")
    public void setInProgressLogsURL1(String inProgressLogsURL1) {
        this.inProgressLogsURL1 = inProgressLogsURL1;
    }

    @JsonProperty("vertexNameIdMapping")
    public VertexNameIdMapping getVertexNameIdMapping() {
        return vertexNameIdMapping;
    }

    @JsonProperty("vertexNameIdMapping")
    public void setVertexNameIdMapping(VertexNameIdMapping vertexNameIdMapping) {
        this.vertexNameIdMapping = vertexNameIdMapping;
    }

    @JsonProperty("queueName")
    public String getQueueName() {
        return queueName;
    }

    @JsonProperty("queueName")
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @JsonProperty("applicationAttemptId")
    public String getApplicationAttemptId() {
        return applicationAttemptId;
    }

    @JsonProperty("applicationAttemptId")
    public void setApplicationAttemptId(String applicationAttemptId) {
        this.applicationAttemptId = applicationAttemptId;
    }

    @JsonProperty("callerType")
    public String getCallerType() {
        return callerType;
    }

    @JsonProperty("callerType")
    public void setCallerType(String callerType) {
        this.callerType = callerType;
    }

    @JsonProperty("startTime")
    public Long getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("callerId")
    public String getCallerId() {
        return callerId;
    }

    @JsonProperty("callerId")
    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("initTime")
    public Long getInitTime() {
        return initTime;
    }

    @JsonProperty("initTime")
    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("completionApplicationAttemptId")
    public String getCompletionApplicationAttemptId() {
        return completionApplicationAttemptId;
    }

    @JsonProperty("completionApplicationAttemptId")
    public void setCompletionApplicationAttemptId(String completionApplicationAttemptId) {
        this.completionApplicationAttemptId = completionApplicationAttemptId;
    }

    @JsonProperty("numFailedTaskAttempts")
    public Integer getNumFailedTaskAttempts() {
        return numFailedTaskAttempts;
    }

    @JsonProperty("numFailedTaskAttempts")
    public void setNumFailedTaskAttempts(Integer numFailedTaskAttempts) {
        this.numFailedTaskAttempts = numFailedTaskAttempts;
    }

    @JsonProperty("numKilledTaskAttempts")
    public Integer getNumKilledTaskAttempts() {
        return numKilledTaskAttempts;
    }

    @JsonProperty("numKilledTaskAttempts")
    public void setNumKilledTaskAttempts(Integer numKilledTaskAttempts) {
        this.numKilledTaskAttempts = numKilledTaskAttempts;
    }

    @JsonProperty("numCompletedTasks")
    public Integer getNumCompletedTasks() {
        return numCompletedTasks;
    }

    @JsonProperty("numCompletedTasks")
    public void setNumCompletedTasks(Integer numCompletedTasks) {
        this.numCompletedTasks = numCompletedTasks;
    }

    @JsonProperty("numSucceededTasks")
    public Integer getNumSucceededTasks() {
        return numSucceededTasks;
    }

    @JsonProperty("numSucceededTasks")
    public void setNumSucceededTasks(Integer numSucceededTasks) {
        this.numSucceededTasks = numSucceededTasks;
    }

    @JsonProperty("numFailedTasks")
    public Integer getNumFailedTasks() {
        return numFailedTasks;
    }

    @JsonProperty("numFailedTasks")
    public void setNumFailedTasks(Integer numFailedTasks) {
        this.numFailedTasks = numFailedTasks;
    }

    @JsonProperty("timeTaken")
    public Long getTimeTaken() {
        return timeTaken;
    }

    @JsonProperty("timeTaken")
    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    @JsonProperty("diagnostics")
    public String getDiagnostics() {
        return diagnostics;
    }

    @JsonProperty("diagnostics")
    public void setDiagnostics(String diagnostics) {
        this.diagnostics = diagnostics;
    }

    @JsonProperty("endTime")
    public Long getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("numKilledTasks")
    public Integer getNumKilledTasks() {
        return numKilledTasks;
    }

    @JsonProperty("numKilledTasks")
    public void setNumKilledTasks(Integer numKilledTasks) {
        this.numKilledTasks = numKilledTasks;
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
