package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.ImageDTO;
import spring.berrekate.dto.UserDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.User;
import spring.berrekate.repositories.ImageRepository;
import spring.berrekate.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return UserDetailsImpl.build(user);
	}

	public UserDTO findUserByIdSimplified(long userId) {
		User usr = userRepository.findById(userId);
		return convertToDto(usr);
	}

	public UserDTO findUserById(long userId) {
		User usr = userRepository.findById(userId);
		return convertToDto(usr);
	}

	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	public ImageDTO findUserImageById(int user_id) {
		ImageModel imageModel = imageRepository.findUserImageById(user_id);
		return convertToDto(imageModel);
	}

	private ImageDTO convertToDto(ImageModel imageModel) {
		return modelMapper.map(imageModel, ImageDTO.class);
	}

	private UserDTO convertToDto(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}