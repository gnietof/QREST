package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteStorageObject {

    private String type; // ibmcloud_cos
    private String region;
    @JsonProperty("region_type")
    private String regionType; // regional | cross-region | single-site
    @JsonProperty("bucket_crn")
    private String bucketCRN;
    @JsonProperty("object_name")
    private String objectName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getBucketCRN() {
		return bucketCRN;
	}

	public void setBucketCRN(String bucketCRN) {
		this.bucketCRN = bucketCRN;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}


}
