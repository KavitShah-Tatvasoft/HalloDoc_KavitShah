package hallodoc.enumerations;

public enum MessageTypeEnum {

		SEND_LINK(1, "Send Link"),
		SEND_AGREEMENT(2, "Send Agreement");
		
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
