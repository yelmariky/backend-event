package fr.lmsys.backend.event.service.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lmsys.backend.event.jpa.DocumentMetadataEntity;
import fr.lmsys.backend.event.jpa.EventEntity;
import fr.lmsys.backend.event.jpa.TypeEvenementEntity;
import fr.lmsys.backend.event.jpa.UsersEntity;
import fr.lmsys.backend.event.modele.DocumentMetadata;
import fr.lmsys.backend.event.modele.Event;
import fr.lmsys.backend.event.modele.TypeEvenement;
import fr.lmsys.backend.event.modele.User;
import fr.lmsys.backend.event.repositories.EventRepository;
import fr.lmsys.backend.event.repositories.TypeEventRepository;
import fr.lmsys.backend.event.repositories.UserRepository;
import fr.lmsys.backend.event.service.ArchiveService;
import fr.lmsys.backend.event.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	TypeEventRepository typeEventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	@Autowired
	ArchiveService archiveService;
	// static Mapper mapper = new DozerBeanMapper();

	public Event saveEvent(Event event, String contextPath) throws Exception {
		List<UsersEntity> users = userRepository.findByIdUsers(event.getUserEvent().getIdUsers());
		if (CollectionUtils.isEmpty(users)) {
			throw new Exception("user [" + event.getUserEvent().getEmail() + "] is not found");
		}
		Comparator<DocumentMetadata> orderByDate = (DocumentMetadata o1, DocumentMetadata o2) -> o1.getDocumentDate()
				.compareTo(o2.getDocumentDate());
		String idusers=String.valueOf(users.get(0).getIdUsers());
		List<DocumentMetadata> images = archiveService
				.findDocumentsByPersonne(idusers);
		images.sort(orderByDate);

		Optional<DocumentMetadata> image = images.stream()
				.sorted((e1, e2) -> e2.getDocumentDate().compareTo(e1.getDocumentDate())).findFirst();
		
		DocumentMetadata imageGet = image.orElseThrow(() ->new Exception("Image with user "+ idusers+" not exist !!!!"));
		imageGet.setUrlImage(contextPath + event.getUserEvent().getIdUsers() + "/files/" + imageGet.getFileName());

		/**
		 * java.util.Optional<Employee> employee = employees .stream()
		 * .sorted((e1, e2) -> e1.getHireDate()
		 * .compareTo(e2.getHireDate())).findFirst();
		 */
		HashSet<DocumentMetadata> imageRecent = new HashSet<DocumentMetadata>();
		imageRecent.add(imageGet);

		// inclure l'image la plus récente
		event.setImages(imageRecent);

		EventEntity eventJpa = dozerBeanMapper.map(event, EventEntity.class);

		eventJpa.attachToUser(users.get(0));
		if (eventJpa != null && eventJpa.getImages() != null) {
			for (DocumentMetadataEntity f : eventJpa.getImages()) {
				eventJpa.addImages(f);
			}
		}

		// eventJpa.getUserEvent().addEventsRealise(eventJpa);
		eventJpa.setIdEvent(UUID.randomUUID().toString());
		return dozerBeanMapper.map(eventRepository.save(eventJpa), Event.class);
	}

	public Event updateEvent(Event event, Long id) {
		Optional<EventEntity> eventToUpdate = eventRepository.findById(id);

		if (eventToUpdate.isPresent()) {
			dozerBeanMapper.map(event, eventToUpdate.get());

			eventRepository.save(eventToUpdate.get());
			return dozerBeanMapper.map(eventToUpdate.get(), Event.class);
		} else {
			return null;
		}
	}

	public List<Event> getEvents() {
		List<EventEntity> eventty = (List<EventEntity>) eventRepository.findAll();
		

		List<Event> events= eventty.stream().map(s -> dozerBeanMapper.map(s, Event.class)).collect(Collectors.toList());
		/*events.forEach(s->{DocumentMetadata a = s.getImages().iterator().next(); 
		//System.out.println(a.getUrlImage()+"***"+a.getFileName());
		});*/
		return events;
	}
	
	public List<TypeEvenement> getTypeEvents() {
		List<TypeEvenementEntity> eventty = (List<TypeEvenementEntity>) typeEventRepository.findAll();
		

		List<TypeEvenement> typeEvents= eventty.stream().map(s -> dozerBeanMapper.map(s, TypeEvenement.class)).collect(Collectors.toList());
		return typeEvents;
	}

	public Event findEvent(String idEvent) {
		List<EventEntity> eventsEn = eventRepository.findByIdEvent(idEvent);
		//EventEntity eventEntity = CollectionUtils.isEmpty(eventsEn) ? null : eventsEn.get(0);

		return CollectionUtils.isEmpty(eventsEn) ? null:dozerBeanMapper.map(eventsEn.get(0), Event.class);

	}

	@Override
	public Set<Event> getEventsByUser(String idUser) {
		List<UsersEntity> usersE = userRepository.findByIdUsers(idUser);

		//User user = CollectionUtils.isEmpty(usersE) ? null : dozerBeanMapper.map(usersE.get(0), User.class);

		return CollectionUtils.isEmpty(usersE)?null:dozerBeanMapper.map(usersE.get(0), User.class).getEventsRealises();
	}

}