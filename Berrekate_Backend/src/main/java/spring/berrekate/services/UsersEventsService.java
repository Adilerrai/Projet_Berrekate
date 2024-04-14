package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.UsersEventsDTO;
import spring.berrekate.entities.UsersEvents;
import spring.berrekate.repositories.EventRepository;
import spring.berrekate.repositories.UsersEventsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersEventsService {

	@Autowired
	UsersEventsRepository usersEventsR;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	private ModelMapper modelMapper;

	public UsersEventsDTO addUserEvent(UsersEventsDTO toAdd) {
		UsersEvents userEventAdd = convertToEntity(toAdd);
		usersEventsR.save(userEventAdd);
		return convertToDto(userEventAdd);
	}

	public UsersEventsDTO changeOptionUser(UsersEventsDTO toChange) {
		UsersEvents newUserEvent = convertToEntity(toChange);
		usersEventsR.save(newUserEvent);
		return convertToDto(newUserEvent);
	}

	public List<UsersEventsDTO> showIfUserEvent(long user_id, long event_id) {
		List<UsersEvents> usersEvents = usersEventsR.checkUserEvent(user_id, event_id);
		return usersEvents.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	private UsersEventsDTO convertToDto(UsersEvents usersEvents) {
		return modelMapper.map(usersEvents, UsersEventsDTO.class);
	}

	private UsersEvents convertToEntity(UsersEventsDTO usersEventsDTO) {
		return modelMapper.map(usersEventsDTO, UsersEvents.class);
	}
}