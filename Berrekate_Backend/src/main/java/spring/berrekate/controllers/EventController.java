package spring.berrekate.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.berrekate.dto.EventDTO;
import spring.berrekate.dto.UsersEventsDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.repositories.EventRepository;
import spring.berrekate.services.EventService;
import spring.berrekate.services.ImageService;
import spring.berrekate.services.UsersEventsService;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private ImageService imageService;

	@Autowired
	private UsersEventsService usersEventsService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<EventDTO> getAllHotels() {
		return eventService.findAll();
	}


	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<EventDTO> getAllEventsById(@PathVariable("id") int id) {
		return eventService.findAllEventsById(id);
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public EventDTO getHotelById(@PathVariable("id") int id) {
		return eventService.findEventById(id);
	}



	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public EventRepository insertEvent(@RequestBody EventDTO eventDTO) {
		  return (EventRepository) eventService.create(eventDTO);
	  }
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editEvent(@RequestBody EventDTO eventDTO){

		eventService.updateEvent(eventDTO);
		return ResponseEntity.ok(new MessageResponse("Event updated successfully!"));
	}
	@RequestMapping(value = "/editGM", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editEventGM(@RequestBody EventDTO eventDTO){

		eventService.updateEventGM(eventDTO);
		return ResponseEntity.ok(new MessageResponse("Event updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeEvent(@PathVariable("id") int id) {
		EventDTO event = eventService.findEventById(id);
		if (event == null) {
			System.out.println("Unable to delete. Event with id " + id + " not found");
		}
		eventService.deleteEventById(id);
	}

	@RequestMapping(value = "/adduserevent", method = RequestMethod.POST)
	public UsersEventsDTO addUserEvent(@RequestBody UsersEventsDTO userEvent) {
		return usersEventsService.addUserEvent(userEvent);
	}
	@RequestMapping(value = "/edituserevent", method = RequestMethod.POST)
	public UsersEventsDTO editUserEventOption(@RequestBody UsersEventsDTO userEvent) {
		return usersEventsService.changeOptionUser(userEvent);
	}
	@RequestMapping(value = "/checkuserevent/{userid}/{eventid}", method = RequestMethod.GET)
	public List<UsersEventsDTO> checkUserEventOption(@PathVariable("userid") int user_id, @PathVariable("eventid") int event_id) {
		return usersEventsService.showIfUserEvent(user_id, event_id);
	}


			@PostMapping("/upload/{event_id}")
			@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
			public ImageModel uplaodImage(@PathVariable("event_id") int event_id, @RequestParam("myFile") MultipartFile file) throws IOException {


				ImageModel savedImage = imageService.createEventImages(file,event_id);


		        return savedImage;


		    }

			@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
			public List<ImageModel> getAllEventImagesById(@PathVariable("id") int id) {
				return eventService.findAllEventImagesById(id);
			}
}
