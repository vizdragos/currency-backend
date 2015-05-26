package com.currency.web;


public class ResponseMessage {

	private String code;
	private String message;

	public ResponseMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResponseMessage that = (ResponseMessage) o;

		if (code != null ? !code.equals(that.code) : that.code != null) return false;
		if (message != null ? !message.equals(that.message) : that.message != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ResposeMessage{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				'}';
	}

	public static Builder builder() {
		return new Builder();
	}

	public final static class Builder {
		private Builder() {
		}

		private String code;
		private String message;

		public Builder withCode(String code) {
			this.code = code;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public ResponseMessage build() {
			return new ResponseMessage(code, message);
		}
	}
}
