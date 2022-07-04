package com.Item.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Item.model.ApiResponse;
import com.Item.model.Item;
import com.Item.repository.ItemRepository;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/item")


public class ItemController {
@Autowired

private ItemRepository itemRepo;
@GetMapping("/all")

public ApiResponse<List<Item>>  listItem() {
	return new ApiResponse<>(HttpStatus.OK.value(), "Item fetched successfully", 
			itemRepo.findAll()) ;
}

@GetMapping("/getone/{id}")
public ApiResponse<Item> getItemById(@PathVariable("id") long id){
	
	Optional<Item> itemData=itemRepo.findById(id);
	if(itemData.isPresent()) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Get One by id Item fetched successfully", 
				itemData);}
				else {
					return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Get One by id Item fetched failed", 
							null);
				}
				}
	@PostMapping("/add")
	public ApiResponse<Item> saveItem( Item item){
		
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "Item Added Successfully",
					
					itemRepo.save(item));
			
		}catch(Exception e) {
			return new ApiResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Item not Added", null);
		}
	}
	@PutMapping("/update")
	public ApiResponse<Item>  updateItem(Item item) {
		try {
			return new ApiResponse<>(HttpStatus.OK.value(), "Item Updated Successfully",
					
					itemRepo.save(item));
			
		}catch(Exception e) {
			return new ApiResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Item not Updated", null);
		}	
		}
	
	@DeleteMapping("/delete/{id}")
	 public ApiResponse<Void> delete(@PathVariable("id") long id){
		 try { itemRepo.deleteById(id);
				return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Item deleted Successfully",
					 null);
					 }catch (Exception e) {
							return new ApiResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Item not deleted",
									 null);

					 }
	 }
	
	//@DeleteMapping("/delete/all")
	// public void deleteAll() {
		 //itemRepo.deleteAll();
	 }

