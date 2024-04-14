package spring.berrekate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.berrekate.dto.TypeOfFoodDTO;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.repositories.TypeOfFoodRepository;
import spring.berrekate.services.TypeOfFoodService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/typeoffood")
public class TypeOfFoodController {

	@Autowired
	private TypeOfFoodService typeOfFoodService;


	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<TypeOfFoodDTO> getAllCities() {
		return typeOfFoodService.findAll();
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public TypeOfFoodDTO getCityById(@PathVariable("id") int id) {
		return typeOfFoodService.findTypeOfFoodById(id);
	}


	
	  @RequestMapping(value = "/insert", method = RequestMethod.POST) 
	  @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public TypeOfFoodRepository insertCity(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {

		  return typeOfFoodService.create(typeOfFoodDTO); 
	  }
	  
		@RequestMapping(value = "/edit", method = RequestMethod.PUT)
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<?> editTypeOfFood(@RequestBody TypeOfFoodDTO typeOfFoodDTO){
			typeOfFoodService.updateTypeOfFood(typeOfFoodDTO);
			return ResponseEntity.ok(new MessageResponse("food updated successfully!"));
		}
	  
	 
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeCity(@PathVariable("id") int id) {
		TypeOfFoodDTO typeOfFood = typeOfFoodService.findTypeOfFoodById(id);
		if (typeOfFood == null) {
			System.out.println("Unable to delete.TypeOfFood with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		typeOfFoodService.deleteTypeOfFoodById(id);

	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	public List<TypeOfFoodDTO> getAllRestaurantTypesOfFoods(@PathVariable("id") int restaurant_id) {
		return typeOfFoodService.getRestaurantTypeOfFoods(restaurant_id);
	}
}