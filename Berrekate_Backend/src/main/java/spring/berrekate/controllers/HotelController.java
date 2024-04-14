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

import spring.berrekate.dto.HotelDTO;
import spring.berrekate.entities.Hotel;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.services.HotelService;
import spring.berrekate.services.ImageService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<HotelDTO> getAllHotels() {
		return hotelService.findAll();
	}
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<HotelDTO> getAllHotelsById(@PathVariable("id") int id) {
		return hotelService.findAllHotelsById(id);
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public HotelDTO getHotelById(@PathVariable("id") int id) {
		return hotelService.findHotelById(id);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Hotel insertHotel(@RequestBody HotelDTO hotelDTO) {
		  return hotelService.create(hotelDTO);
	  }
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editHotel(@RequestBody HotelDTO hotelDTO){
		//UserDTO user = userService.findUserById(id);
		//userDTO.setId(id);
		hotelService.updateHotel(hotelDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeHotel(@PathVariable("id") int id) {
		HotelDTO hotel = hotelService.findHotelById(id);
		if (hotel == null) {
			System.out.println("Unable to delete. Hotel with id " + id + " not found");
		}
		hotelService.deleteHotelById(id);
	}
	

		@PostMapping("/upload/{hotel_id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ImageModel uplaodImage(@PathVariable("hotel_id") int hotel_id, @RequestParam("myFile") MultipartFile file) throws IOException {


			ImageModel savedImage = imageService.createHotelImages(file,hotel_id);
	        System.out.println("Image saved");


	        return savedImage;


	    }

		@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
		public List<ImageModel> getAllHotelImagesById(@PathVariable("id") int id) {
			return hotelService.findAllHotelImagesById(id);
		}
}
