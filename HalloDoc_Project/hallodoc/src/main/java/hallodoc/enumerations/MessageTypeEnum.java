package hallodoc.enumerations;

public enum MessageTypeEnum {

		SEND_LINK(1, "Send Link"),
		SEND_AGREEMENT(2, "Send Agreement"),
		SEND_FILES(3,"Send Files"),
		SEND_ORDERS(4,"Send New Order"),
		SEND_CREDENTIALS_PROVIDER(5,"Send Provider Credentials");
		private int messageTypeId;
		private String messageType;
		
		
		private MessageTypeEnum(int messageTypeId, String messageType) {
			this.messageTypeId = messageTypeId;
			this.messageType = messageType;
		}


		public int getMessageTypeId() {
			return messageTypeId;
		}


		public void setMessageTypeId(int messageTypeId) {
			this.messageTypeId = messageTypeId;
		}


		public String getMessageType() {
			return messageType;
		}


		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}
		
}
