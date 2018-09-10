package fr.lmsys.backend.event.modele;


	public class Mail {
		String to;
		String from;
		String subject;
		String text;
		
		
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}

}
