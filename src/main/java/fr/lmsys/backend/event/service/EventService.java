package fr.lmsys.backend.event.service;

import java.util.List;
import java.util.Set;

import fr.lmsys.backend.event.modele.Event;
import fr.lmsys.backend.event.modele.TypeEvenement;

public interface EventService {

	public Event saveEvent(Event event, String contextPath) throws Exception;

	public Event updateEvent(Event event, Long id);
	
	public List<Event> getEvents();
	public Event findEvent(String idEvent);
	
	public Set<Event> getEventsByUser(String idUser);
	public List<TypeEvenement> getTypeEvents();

}