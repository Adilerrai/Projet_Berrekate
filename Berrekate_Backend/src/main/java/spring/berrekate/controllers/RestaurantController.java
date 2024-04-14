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

import spring.berrekate.dto.RestaurantDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.Restaurant;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.services.ImageService;
import spring.berrekate.services.RestaurantService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<RestaurantDTO> getAllRestaurants() {
		return restaurantService.findAll();
		
	}
	
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<RestaurantDTO> getAllRestaurantsById(@PathVariable("id") int id) {
		return restaurantService.findAllRestaurantsById(id);
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public RestaurantDTO getRestaurantById(@PathVariable("id") int id) {
		return restaurantService.findRestaurantById(id);
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Restaurant insertRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
		  return  restaurantService.create(restaurantDTO);
	  }
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editRestaurant(@RequestBody RestaurantDTO restaurantDTO){

		restaurantService.updateRestaurant(restaurantDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeRestaurant(@PathVariable("id") int id) {
		RestaurantDTO restaurant = restaurantService.findRestaurantById(id);
		if (restaurant == null) {
			System.out.println("Unable to delete. Restaurant with id " + id + " not found");
		}
		restaurantService.deleteRestaurantById(id);
	}
	

	@PostMapping("/upload/{restaurant_id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("restaurant_id") int restaurant_id, @RequestParam("myFile") MultipartFile file) throws IOException {


				ImageModel savedImage = imageService.createRestaurantImages(file,restaurant_id);

		        return savedImage;


		    }
			@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
			public List<ImageModel> getAllRestaurantImagesById(@PathVariable("id") int id) {
				return restaurantService.findAllImagesByRestaurantId(id);
			}
}