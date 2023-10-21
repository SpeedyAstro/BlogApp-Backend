package in.astro.service;

import in.astro.payloads.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO dto);
    CategoryDTO updateCategory(CategoryDTO dto,Integer category_id);
    void deleteCategory(Integer category_id);
    CategoryDTO getCategory(Integer category_id);
    List<CategoryDTO> getAllCategory();
}
