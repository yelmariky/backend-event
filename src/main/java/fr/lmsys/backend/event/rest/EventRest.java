package fr.lmsys.backend.event.rest;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lmsys.backend.event.modele.Event;
import fr.lmsys.backend.event.modele.TypeEvenement;
import fr.lmsys.backend.event.service.EventService;


@RestController
//@CrossOrigin
@RequestMapping("/api/events")
public class EventRest   {
	@Autowired
	private EventService eventService;
	private static final Logger logger = LoggerFactory.getLogger(EventRest.class);
	@Value("${backend.host}")
	String urlName;
	
/*
	@RequestMapping(value = "/_save", method = RequestMethod.POST)
	public void saveEvent(@RequestBody Event event) throws Exception {
		eventService.saveEvent(event);
	}
*/	
	 @PostMapping
	public ResponseEntity<Event> saveEvent(@RequestBody Event event,HttpServletRequest request) throws  Exception {
		 if(event.getStartEvent().compareTo(event.getEndEvent())>0){
			 return new ResponseEntity<Event>(event, HttpStatus.NOT_ACCEPTABLE);
		 }
		 
		 return new ResponseEntity<Event>(eventService.saveEvent(event, urlName+request.getContextPath()+"/api/upload/user/"), HttpStatus.OK);
	}
	 
	@GetMapping
	//@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public List<Event> getAllEvents() {
		logger.info("call events: OK");
		return eventService.getEvents();
	}
	
	@GetMapping(value = "/typevent/_getAll")
	//@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public List<TypeEvenement> getAllTypeEvents(HttpServletRequest http) {
		logger.info("url: {} au port  {}",http.getLocalAddr(),http.getLocalPort());
		return eventService.getTypeEvents();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findEvent(@PathVariable(value = "id") String id) {
		return new ResponseEntity<Event>(eventService.findEvent(id), HttpStatus.OK);

	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> findEventByUser(@PathVariable(value = "id") String id) {
		return new ResponseEntity<Set<Event>>(eventService.getEventsByUser(id), HttpStatus.OK);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Event> updateIframe(@PathVariable(value = "id") Long id, @RequestBody Event event) {
		Event iframeToUpdate = eventService.updateEvent(event, id);
		if (event == null) {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Event>(iframeToUpdate, HttpStatus.OK);

	}
	
	
	
/*	//@CrossOrigin
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody DocumentMetadata handleFileUpload(
            @RequestParam(value="file", required=true) MultipartFile file ,
            @RequestParam(value="person", required=true) String person,
            @RequestParam(value="date", required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        
        try {
            Document document = new Document(file.getBytes(), file.getOriginalFilename(), date, person );
           
			archiveService.save(document);
            return document.getMetadata();
        } catch (RuntimeException e) {
            logger.error("Error while uploading.", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error while uploading.", e);
            throw new RuntimeException(e);
        }      
    }
    

	*/

	
/*	 @GET
	    @Path("now")
	    @JsonDeserialize(using = LocalDateDeserializer.class)
	    public LocalDate get() {
	        return LocalDate.now();
	    }
*/
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<Object> nfeHandler(Exception ex) {
//		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}

}
