//package spring.licenta.services;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import spring.licenta.dto.CommentDTO;
//import spring.licenta.entities.Comment;
//import spring.licenta.repositories.CommentRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class CommentService {
//
//	private final CommentRepository commentRepository;
//	private final ModelMapper modelMapper;
//
//	@Autowired
//	public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
//		this.commentRepository = commentRepository;
//		this.modelMapper = modelMapper;
//	}
//
//	public List<CommentDTO> findAll() {
//		List<Comment> comments = commentRepository.findAll();
//		return comments.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//	}
//
//	public CommentDTO findCommentById(int commentId) {
//		Comment comment = commentRepository.findById(commentId);
//		return convertToDto(comment);
//	}
//
//	public Comment create(CommentDTO commentDTO) {
//		Comment comment = convertToEntity(commentDTO);
//		Comment savedComment = commentRepository.save(comment);
//		return savedComment;
//	}
//
//	public void updateComment(CommentDTO commentDTO) {
//		Comment comment = convertToEntity(commentDTO);
//		commentRepository.save(comment);
//	}
//
//	public void deleteCommentById(int id) {
//		commentRepository.deleteById(id);
//	}
//
//	private CommentDTO convertToDto(Comment comment) {
//		return modelMapper.map(comment, CommentDTO.class);
//	}
//
//	private Comment convertToEntity(CommentDTO commentDTO) {
//		return modelMapper.map(commentDTO, Comment.class);
//	}
//
//    public List<CommentDTO> findAllHotelCommentsById(int hotelId) {
//		List<Comment> comments = commentRepository.findAllByHotelId(hotelId);
//		return comments.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//
//    }
//
//	public List<CommentDTO> findAllEventCommentsById(int eventId) {
//		List<Comment> comments = commentRepository.findAllByEventId(eventId);
//		return comments.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//	}
//
//	public List<CommentDTO> findAllRestaurantCommentsById(int restaurantId) {
//		List<Comment> comments = commentRepository.findAllByRestaurantId(restaurantId);
//		return comments.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//	}
//
//	public List<CommentDTO> findAllTouristAttractionCommentsById(int touristAttractionId) {
//		List<Comment> comments = commentRepository.findAllByTouristAttractionId(touristAttractionId);
//		return comments.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//	}
//
//	public List<Comment> getCommentsByRatingHotel(int hotelId) {
//		List<Comment> comments = commentRepository.findAllByRestaurantId(hotelId);
//		return comments.stream()
//				.collect(Collectors.toList());
//	}
//
//	public List<Double> getCommentsByRatingRestaurant(int restaurantId) {
//		List<Double> comments = commentRepository.getCommentsByRatingRestaurant(restaurantId);
//		return comments.stream()
//				.collect(Collectors.toList());
//	}
//}