package com.yannick.gestionpresence.events;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
	 private String message;
	    private Long idPersonnel;

	    public NotificationEvent(Object source, String message, Long idPersonnel) {
	        super(source);
	        this.message = message;
	        this.idPersonnel = idPersonnel;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public Long getIdPersonnel() {
	        return idPersonnel;
	    }
}