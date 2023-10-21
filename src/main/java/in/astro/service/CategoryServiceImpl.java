package in.astro.service;

import in.astro.entity.Category;
import in.astro.exceptions.ResourceNotFoundException;
import in.astro.payloads.CategoryDTO;
import in.astro.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private CategoryRepository repository;
    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = this.repository.save(dtoToEntity(dto));
        return entityToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO dto, Integer category_id) {
        Category category = this.repository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", category_id));
        category.setCategory_title(dto.getCategory_title());
        category.setCategory_description(dto.getCategory_description());
        Category save = this.repository.save(category);
        return entityToDto(save);
    }

    @Override
    public void deleteCategory(Integer category_id) {
        Category category = this.repository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", category_id));
        this.repository.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer category_id) {
        Category category = this.repository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "category ID", category_id));
        return entityToDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = this.repository.findAll();
        List<CategoryDTO> collect = categories.stream().map(category -> this.entityToDto(category)).collect(Collectors.toList());
        return collect;
    }

    public CategoryDTO entityToDto(Category category){
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category,dto);
        return dto;
    }
    public Category dtoToEntity(CategoryDTO dto){
        Category category = new Category();
        BeanUtils.copyProperties(dto,category);
        return category;
    }
}
