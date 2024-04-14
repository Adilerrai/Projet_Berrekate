package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.EventDTO;
import spring.berrekate.entities.Event;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.repositories.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

	private final EventRepository eventRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
		this.eventRepository = eventRepository;
		this.modelMapper = modelMapper;
	}

	public List<EventDTO> findAll() {
		List<Event> events = eventRepository.findAll();
		return events.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public EventDTO findEventById(int eventId) {
		Event event = eventRepository.findById(eventId);
		return convertToDto(event);
	}

	public Event create(EventDTO eventDTO) {
		Event event = convertToEntity(eventDTO);
		Event savedEvent = eventRepository.save(event);
		return savedEvent;
	}

	public void updateEvent(EventDTO eventDTO) {
		Event event = convertToEntity(eventDTO);
		eventRepository.save(event);
	}

	public void deleteEventById(int id) {
		eventRepository.deleteById(id);
	}

	private EventDTO convertToDto(Event event) {
		return modelMapper.map(event, EventDTO.class);
	}

	private Event convertToEntity(EventDTO eventDTO) {
		return modelMapper.map(eventDTO, Event.class);
	}

	public List<ImageModel> findAllEventImagesById(int id) {
		return eventRepository.findAllEventImagesById(id);
	}

	public void updateEventGM(EventDTO eventDTO) {
		Event event = convertToEntity(eventDTO);
		eventRepository.save(event);
	}

	public List<EventDTO> findAllEventsById(int id) {
		List<Event> events = eventRepository.findAllEventsByCity_Id(id);
		return events.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
}