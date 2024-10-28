package com.experiment.web.vo;

public class UserAddressWrapper {
    private UserVO user;
    private AddressVO address;

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserAddressWrapper{" +
				"user=" + user +
				", address=" + address +
				'}';
	}
}