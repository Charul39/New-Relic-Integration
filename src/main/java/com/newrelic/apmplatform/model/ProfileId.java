package com.newrelic.apmplatform.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@SuppressWarnings("serial")
@Embeddable
public class ProfileId implements Serializable {
	
	private Long user_id;
	
	private String profile_name;

	public ProfileId() {
		super();
	}

	public ProfileId(Long user_id, String profile_name) {
		super();
		this.user_id = user_id;
		this.profile_name = profile_name;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	@Override
	public String toString() {
		return "ProfileId [user_id=" + user_id + ", profile_name=" + profile_name + "]";
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileId)) return false;
        ProfileId that = (ProfileId) o;
        return Objects.equals(getUser_id(), that.getUser_id()) &&
                Objects.equals(getProfile_name(), that.getProfile_name());
    }
	@Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getProfile_name());
    }
	
	
}
