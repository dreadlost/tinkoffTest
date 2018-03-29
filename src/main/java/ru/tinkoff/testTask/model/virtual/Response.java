package ru.tinkoff.testTask.model.virtual;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Response {

    @JsonProperty("APPLICATION_ID")
    Long applicationId;

    @JsonProperty("CONTACT_ID")
    Long contactId;

    @JsonProperty("DT_CREATE")
    Date date;

    @JsonProperty("PRODUCT_NAME")
    String name;

    public Response(Long applicationId, Long contactId, Date date, String name) {
        this.applicationId = applicationId;
        this.contactId = contactId;
        this.date = date;
        this.name = name;
    }
    public Response(){}

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
