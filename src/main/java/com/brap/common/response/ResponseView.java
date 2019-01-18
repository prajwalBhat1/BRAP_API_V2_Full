package com.brap.common.response;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseView<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,Object> metadata;
    private T dataContent;
    private ErrorCollection errorContent;

    public T getDataContent() {
        return dataContent;
    }

    public void setDataContent(T dataContent) {
        this.dataContent = dataContent;
    }

    public ErrorCollection getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(ErrorCollection errorContent) {
        this.errorContent = errorContent;
    }

    public void addMetaDataProperty(String key, Object value) {
        if (metadata == null) {
            metadata = new HashMap<>();
        }
        metadata.put(key,value);
    }

    @JsonProperty(value = "metadata")
    public HashMap<String,Object> getMetaDataMap() {
        if (metadata == null) {
            return new HashMap<>();
        }
        return metadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("metadata", metadata).append("dataContent", dataContent).append("errorContent", errorContent).toString();
    }
}

