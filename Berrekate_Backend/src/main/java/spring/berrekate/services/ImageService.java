package spring.berrekate.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import spring.berrekate.entities.ImageModel;
import spring.berrekate.repositories.ImageRepository;

@Service
@Transactional
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	public ImageModel createCityImages(MultipartFile file, int city_id) throws IOException {

		ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0,0, city_id,0 );



		ImageModel savedImage = imageRepository.save(img);
		return savedImage;
	}
	
	public ImageModel createUserProfileImage(MultipartFile file, int user_id) throws IOException {

		ImageModel beforeImage=imageRepository.findUserImageById(user_id);
		if(beforeImage!=null)
			{
			ImageModel img = new ImageModel(Math.toIntExact(beforeImage.getId()),file.getOriginalFilename(),file.getContentType(),file.getBytes());
			ImageModel savedImage = imageRepository.save(img);
			return savedImage;
			}
		else {
			ImageModel img = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0, 0,0,user_id);
			ImageModel savedImage = imageRepository.save(img);
			return savedImage;
		}
	}

	public ImageModel createHotelImages(MultipartFile file, int hotel_id) throws IOException {

		System.out.println("Ajunge sa creeze imagine pentru hotel?");
		

		ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),hotel_id,0,0,0, 0,0 );

		ImageModel savedImage = imageRepository.save(img);

		return savedImage;
	}
	
	public ImageModel createRestaurantImages(MultipartFile file, int restaurant_id) throws IOException {



		ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,restaurant_id,0,0, 0,0 );

		ImageModel savedImage = imageRepository.save(img);

		return savedImage;
	}
	
	public ImageModel createTouristAttractionImages(MultipartFile file, int touristAttraction_id) throws IOException {



		ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,touristAttraction_id,0, 0,0 );

		ImageModel savedImage = imageRepository.save(img);

		return savedImage;
	}
	
	public ImageModel createEventImages(MultipartFile file, int event_id) throws IOException {


		ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0,event_id, 0,0 );

		ImageModel savedImage = imageRepository.save(img);

		return savedImage;
	}

    public void deleteById(long imageID) {
		imageRepository.deleteById(imageID);
    }
}
