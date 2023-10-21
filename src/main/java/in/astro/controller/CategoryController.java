package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.CategoryDTO;
import in.astro.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

//    Create Category
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO dto){
        CategoryDTO category = categoryService.createCategory(dto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

//    Update Category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO dto, @PathVariable Integer categoryId){
        CategoryDTO category = this.categoryService.updateCategory(dto,categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

//    Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Successfully Deleted Category with id :"+categoryId,true),HttpStatus.OK);
    }

//    Get specific Category with id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
        CategoryDTO category = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

//    Get All Categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }


}
